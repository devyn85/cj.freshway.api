package cjfw.wms.dp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.dp.dto.DpUnloadReqDto;
import cjfw.wms.dp.dto.DpUnloadResCarLogDto;
import cjfw.wms.dp.dto.DpUnloadResDetailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.28
 * @description :입고하차관리  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DpUnloadService {
    private final UserContext userContext;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPDP_UNLOAD";
    private transient static final String TEMPTABLETYPE = "DP";
    private transient static final String PROCESSTYPE = "";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(DpUnloadService.class.getSimpleName()) + ".";

    /**
     * @description : 입고하차관리 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 입고하차관리 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <T> Map<String, Object> getDetailList(T reqDto) {
//        Integer checkCnt = commonDao.selectOne(SERVICEID_PREFIX + "checkDataDetailList", reqDto);
        List<DpUnloadResCarLogDto> carLogList = commonDao.selectList(SERVICEID_PREFIX + "getCarLogList", reqDto);
//        if (Objects.isNull(checkCnt) || checkCnt == 0) {
//            carLogList = commonDao.selectList(SERVICEID_PREFIX + "getQrDataList", reqDto);
//        } else {
//            carLogList = commonDao.selectList(SERVICEID_PREFIX + "getCarLogList", reqDto);
//        }
        List<DpUnloadResDetailDto> detailList = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", reqDto);
        Integer maxCnt = commonDao.selectOne(SERVICEID_PREFIX + "getDataMaxDetailList", reqDto);

        Map<String, Object> result = new HashMap<>();
        result.put("carLogList", carLogList);
        result.put("detailList", detailList);
        result.put("maxCnt", maxCnt);
        return result;
    }

    /**
     * @description : 입고하차관리 엑셀 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R> List<R> getExcelList(DpUnloadReqDto reqDto) {
        if ("Y".equals(reqDto.getSkuYn())) {
            return commonDao.selectList(SERVICEID_PREFIX + "getExcelListForSKU", reqDto);
        } else {
            return commonDao.selectList(SERVICEID_PREFIX + "getExcelList", reqDto);
        }
    }

    /**
     * @description : 입고하차관리 하차등록
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String saveCarLog(DpUnloadReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        DpUnloadReqDto reqDto = ModelMapperUtil.map(paramDto, DpUnloadReqDto.class);
        DpUnloadResCarLogDto data = reqDto.getSaveList().get(0); // 저장리스트의 첫번째 데이터

        //삭제일때 carlog, qrlog 테이블 삭제
        if("D".equals(data.getRowStatus())) {
            commonDao.delete(SERVICEID_PREFIX +"deleteCarLog", data);
            commonDao.delete(SERVICEID_PREFIX +"deleteQrLog", data);
            return CanalFrameConstants.MSG_COM_SUC_CODE;
        }

        DpUnloadReqDto dto = new DpUnloadReqDto();

        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"DCCODE", "STORERKEY", "DOCDT", "SLIPDT", "SLIPNO", "CARRIERKEY", "CUSTKEY", "DELIVERYGROUP", "TEMPERATURE1", "TEMPERATURE2", "TEMPOPTITYPE", "DELIVERYTIME", "DELIVERYMEMO", "IUFLAG", "CHANNEL"};
        Object[] valueList = {data.getDccode(),
            data.getStorerkey(),
            data.getSlipdt(),
            data.getSlipdt(),
            data.getDocno(),
            data.getCarrierkey(),
            data.getCustkey(),
            data.getDeliverygroup(),
            data.getTemperature1(),
            data.getTemperature2(),
            data.getTempoptitype(),
            data.getDeliverytime(),
            StringUtil.nvl(data.getDeliverymemo()),
            StringUtil.nvl(data.getIuflag()),
            StringUtil.nvl(data.getChannel())};
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(dto);
        log.info("rv->{}", rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl((String) dto.getResultMessage());
        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);

        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            log.error("▶하차등록 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"하차등록"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/


        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
