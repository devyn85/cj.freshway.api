package cjfw.wms.dp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.dp.dto.DpInspectDetailResDto;
import cjfw.wms.dp.dto.DpInspectHeaderResDto;
import cjfw.wms.dp.dto.DpInspectReqDto;
import cjfw.wms.dp.dto.DpInspectTotalResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : JangJaeHyun (jhjang43@cj.net)
 * @date : 2025.05.22
 * @description : 입고검수처리 기능을 구현한 Service Class
 * @issues :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.22        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DpInspectService {

    private transient static final String SERVICEID_PREFIX = "dpInspectService.";
    /**
     * 프로시져명 - 입고검수처리
     */
    private transient static final String PAKAGE_NAME = "SPDP_INSPECTION";
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;

    /**
     * 입고검수처리 마스터 목록 조회 기능을 구현한 Method
     */
    public List<DpInspectHeaderResDto> getMasterList(DpInspectReqDto dpInspectReqDto) {
        List<DpInspectHeaderResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dpInspectReqDto);
        return list;
    }

    /**
     * 입고검수처리 총량 목록 조회 기능을 구현한 Method
     */
    public List<DpInspectTotalResDto> getTotalList(DpInspectReqDto dpInspectReqDto) {
        List<DpInspectTotalResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTotalList", dpInspectReqDto);
        return list;
    }

    /**
     * 입고검수처리 디테일 목록 조회 기능을 구현한 Method
     */
    public List<DpInspectDetailResDto> getDetailList(DpInspectReqDto dpInspectReqDto) {
        List<DpInspectDetailResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dpInspectReqDto);
        return list;
    }

    /**
     * 입고검수처리 저장
     */
    public String saveMaster(DpInspectReqDto dpInspectReqDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        DpInspectReqDto reqDto = ModelMapperUtil.map(dpInspectReqDto, DpInspectReqDto.class);
        DpInspectTotalResDto data = reqDto.getSaveList().get(0);

        DpInspectReqDto dto = new DpInspectReqDto();

        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(dpInspectReqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"INSPECTTYPE", "DCCODE", "STORERKEY", "ORGANIZE", "CUSTKEY", "DOCDT", "SKU", "UOM", "LOTTABLE01", "TRANQTY", "DURATIONTYPE", "CHANNEL", "SLIPDT", "INSPECTREASON", "INSPECTMSG", "SLIPNO", "STOCKID"};
        Object[] valueList = {
            data.getInspectType(),
            data.getDcCode(),
            data.getStorerKey(),
            data.getOrganize(),
            data.getCustKey(),
            data.getDocDt(),
            data.getSku(),
            data.getUom(),
            data.getLotTable01(),
            data.getTranQty(),
            data.getDurationType(),
			data.getChannel(),
            data.getSlipDt(),
            data.getInspectReason(),
            data.getInspectMsg(),
            data.getSlipNo(),
            data.getStockId()
        };
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
            log.error("▶입고검수처리 - 저장 시 오류 발생 ");
            throw new UserHandleException("" + "에러코드 : " + resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
