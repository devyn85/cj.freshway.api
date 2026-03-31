package cjfw.wms.tm.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.tm.dto.TmIndividualDispatchConfirmReqDto;
import cjfw.wms.tm.dto.TmIndividualDispatchPopReqDto;
import cjfw.wms.tm.dto.TmIndividualDispatchPopResDto;
import cjfw.wms.tm.dto.TmIndividualDispatchReqDto;
import cjfw.wms.tm.dto.TmIndividualDispatchResDto;
import cjfw.wms.tm.dto.TmInplanUpdateDto;
import cjfw.wms.tm.dto.TmSavePlanRouteReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : devyn
 * @date : 2026.03.04
 * @description : 개별배차 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.03.04 devyn                 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmIndividualDispatchService {

    private transient static final String SERVICEID_PREFIX = "tmIndividualDispatchService.";
    private static final String UPLOAD_SERVICEID_PREFIX = "tmDispatchUploadMapper.";
    private static final String PAKAGE_NAME = "SPTM_INPLAN";
    private static final String COMMAND_CREATION_WD = "CREATION_WD";
    private static final String CONFIRM = "CONFIRM";
    private static final int BATCH_SIZE = 350;

    private final CommonDao commonDao;
    private final UserContext userContext;
    private final CmCommonService cmCommonService;
    private final TmDispatchUploadService tmDispatchUploadService;

    /**
     * @description : 개별배차 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.04 devyn                 생성 </pre>
     */
    public List<TmIndividualDispatchResDto> getIndividualDispatchList(TmIndividualDispatchReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getIndividualDispatchList", reqDto);
    }

    /**
     * @description : 개별배차 팝업 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.04 devyn                 생성 </pre>
     */
    public Page<TmIndividualDispatchPopResDto> getIndividualDispatchPopList(TmIndividualDispatchPopReqDto reqDto, Page page) {
        return commonDao.selectPageList(SERVICEID_PREFIX + "getIndividualDispatchPopList", reqDto, page);
    }

    /**
     * @description : 개별배차 배차확정 (1.CREATION_WD → 2.CONFIRM → 3.Route생성)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.04 devyn                 생성 </pre>
     */
    @Transactional
    public String updateConfirmDispatch(List<TmIndividualDispatchConfirmReqDto> reqDtoList) {

        // DTO 변환
        List<TmIndividualDispatchConfirmReqDto> dtoList = reqDtoList.stream()
                .map(req -> ModelMapperUtil.map(req, userContext, TmIndividualDispatchConfirmReqDto.class))
                .toList();

        validateDispatchVehicles(dtoList);

        // carno, truthcustkey가 있는 항목 (1-1, 3단계에서 공통 사용)
        List<TmIndividualDispatchConfirmReqDto> dispatchableDtoList = dtoList.stream()
                .filter(dto -> dto.getCarno() != null && dto.getTruthcustkey() != null)
                .toList();

        // 1. CREATION_WD SP 호출 - (dccode, slipDt, storerkey) 기준 그룹핑
        callSpByGroup(dtoList,
                dto -> dto.getDccode() + "|" + dto.getSlipDt() + "|" + dto.getStorerkey(),
                this::creationWd);

        // 1-1. TM_INPLAN carno 업데이트 (CREATION_WD 후 carno가 null이므로 CONFIRM 전에 DOCNO 기준 업데이트)
        List<TmSavePlanRouteReqDto> tmInplanList = dispatchableDtoList.stream()
                .filter(dto -> dto.getDocno() != null)
                .collect(Collectors.toMap(
                        dto -> dto.getDccode() + "|" + dto.getSlipDt() + "|" + dto.getTruthcustkey() + "|" + dto.getDocno(),
                        dto -> dto,
                        (existing, duplicate) -> existing))
                .values().stream()
                .map(item -> {
                    TmSavePlanRouteReqDto dto = TmSavePlanRouteReqDto.builder()
                            .dccode(item.getDccode())
                            .deliveryDate(item.getSlipDt())
                            .carno(item.getCarno())
                            .truthCustkey(item.getTruthcustkey())
                            .docno(item.getDocno())
                            .roundSeq(item.getPriority() != null ? Integer.parseInt(item.getPriority()) : 1)
                            .dlvDeliveryPriority(0)
                            .status("00")
                            .splitDeliveryYn("N")
                            .build();
                    dto.setRegId(userContext.getUserId());
                    return dto;
                })
                .collect(Collectors.toList());

        if (!tmInplanList.isEmpty()) {
            for (int i = 0; i < tmInplanList.size(); i += BATCH_SIZE) {
                List<TmSavePlanRouteReqDto> batch = tmInplanList.subList(i, Math.min(i + BATCH_SIZE, tmInplanList.size()));
                commonDao.update(SERVICEID_PREFIX + "updateTmInplanCarnoByDocno", batch);
            }
        }

        // 2. CONFIRM SP 호출 - (dccode, slipDt, storerkey, carno) 기준 그룹핑 (carno가 있는 항목만)
        List<TmIndividualDispatchConfirmReqDto> confirmDtoList = dtoList.stream()
                .filter(dto -> dto.getCarno() != null)
                .toList();
        callSpByGroup(confirmDtoList,
                dto -> dto.getDccode() + "|" + dto.getSlipDt() + "|" + dto.getStorerkey() + "|" + dto.getCarno(),
                this::confirmDispatch);

        // 3. Route 생성 (TM_PLAN_ROUTE)
        if (!dispatchableDtoList.isEmpty()) {
            TmIndividualDispatchConfirmReqDto first = dispatchableDtoList.get(0);
            List<TmInplanUpdateDto> updateList = dispatchableDtoList.stream()
                    .map(dto -> TmInplanUpdateDto.builder()
                            .carno(dto.getCarno())
                            .truthCustKey(dto.getTruthcustkey())
                            .priority(1)
                            .build())
                    .toList();

            // 엑셀 업로드 수동 배차 시 사용되는 메소드 사용
            tmDispatchUploadService.createEmptyPlanRoutes(updateList, first.getDccode(), first.getSlipDt());
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * 그룹핑 키 기준으로 docno를 배치 분할하여 SP를 호출하는 공통 메서드
     */
    private void callSpByGroup(List<TmIndividualDispatchConfirmReqDto> dtoList,
                               java.util.function.Function<TmIndividualDispatchConfirmReqDto, String> groupKeyFn,
                               java.util.function.BiConsumer<TmIndividualDispatchConfirmReqDto, String> spCaller) {

        Map<String, List<TmIndividualDispatchConfirmReqDto>> groups = dtoList.stream()
                .collect(Collectors.groupingBy(groupKeyFn));

        for (List<TmIndividualDispatchConfirmReqDto> group : groups.values()) {
            List<String> docnoList = group.stream()
                    .map(TmIndividualDispatchConfirmReqDto::getDocno)
                    .filter(Objects::nonNull)
                    .distinct()
                    .collect(Collectors.toList());

            TmIndividualDispatchConfirmReqDto baseDto = group.get(0);
            for (int i = 0; i < docnoList.size(); i += BATCH_SIZE) {
                String docnoBatch = String.join(",", docnoList.subList(i, Math.min(i + BATCH_SIZE, docnoList.size())));
                spCaller.accept(baseDto, docnoBatch);
            }
        }
    }

    private void validateDispatchVehicles(List<TmIndividualDispatchConfirmReqDto> dtoList) {
        List<String> requestedCarNos = dtoList.stream()
                .map(TmIndividualDispatchConfirmReqDto::getCarno)
                .filter(Objects::nonNull)
                .filter(StringUtil::isNotEmpty)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));

        if (requestedCarNos.isEmpty()) {
            return;
        }

        Set<String> availableCarNos = new HashSet<>();

        for (int i = 0; i < requestedCarNos.size(); i += BATCH_SIZE) {
            List<String> batchCarNos = requestedCarNos.subList(i, Math.min(i + BATCH_SIZE, requestedCarNos.size()));

            TmIndividualDispatchPopReqDto vehicleReqDto = TmIndividualDispatchPopReqDto.builder()
                    .multiSelect(String.join(",", batchCarNos))
                    .build();

            List<TmIndividualDispatchPopResDto> vehicles =
                    commonDao.selectList(SERVICEID_PREFIX + "getIndividualDispatchPopList", vehicleReqDto);

            availableCarNos.addAll(vehicles.stream()
                    .map(TmIndividualDispatchPopResDto::getCode)
                    .filter(Objects::nonNull)
                    .toList());
        }

        List<String> invalidCarNos = requestedCarNos.stream()
                .filter(carno -> !availableCarNos.contains(carno))
                .toList();

        if (!invalidCarNos.isEmpty()) {
            throw new UserHandleException("개별배차 가능한 차량이 아닙니다. " + String.join(", ", invalidCarNos));
        }
    }

    /**
     * @description : CREATION_WD SP 호출 (TM_INPLAN 생성)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.06 devyn                 생성 </pre>
     */
    private void creationWd(TmIndividualDispatchConfirmReqDto request, String docnoBatch) {
        String resultCode;
        String returnMessage;
        String resultMessage;

        ProcedureParametersFactory.initParamDto(request, request, PAKAGE_NAME);
        request.setAvc_COMMAND(COMMAND_CREATION_WD);
        request.setAvc_DCCODE(request.getDccode());

        String[] keyList = { "DELIVERYDT", "DCCODE", "STORERKEY", "DOCNO" };
        Object[] valueList = { request.getSlipDt(), request.getDccode(), request.getStorerkey(), docnoBatch };

        request.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(request);

        log.info("CREATION_WD rv->{}", rv);

        resultCode    = StringUtil.nvl(request.getResultCode());
        resultMessage = StringUtil.nvl(request.getResultMessage());
        returnMessage = StringUtil.nvl(request.getReturnMessage());

        log.info("CREATION_WD resultCode->{}", resultCode);
        log.info("CREATION_WD resultMessage->{}", resultMessage);
        log.info("CREATION_WD returnMessage->{}", returnMessage);
    }

    /**
     * @description : 배차확정 SP 호출
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.04 devyn                 생성 </pre>
     */
    private void confirmDispatch(TmIndividualDispatchConfirmReqDto request, String docnoBatch) {
        String resultCode;
        String returnMessage;
        String resultMessage;

        ProcedureParametersFactory.initParamDto(request, request, PAKAGE_NAME);
        request.setAvc_COMMAND(CONFIRM);
        request.setAvc_DCCODE(request.getDccode());

        String[] keyList = {
                "DELIVERYDT", "DCCODE", "DELIVERYNO",
                "STORERKEY", "DOCNO", "DELIVERYGROUP",
                "CARNO", "PRIORITY", "TRUTHCUSTKEY"
        };
        Object[] valueList = {
                request.getSlipDt(), request.getDccode(), null,
                request.getStorerkey(), docnoBatch, null,
                request.getCarno(), "1", null
        };

        request.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(request);

        log.info("CONFIRM rv->{}", rv);

        resultCode    = StringUtil.nvl(request.getResultCode());
        resultMessage = StringUtil.nvl(request.getResultMessage());
        returnMessage = StringUtil.nvl(request.getReturnMessage());

        log.info("CONFIRM resultCode->{}", resultCode);
        log.info("CONFIRM resultMessage->{}", resultMessage);
        log.info("CONFIRM returnMessage->{}", returnMessage);
    }

}