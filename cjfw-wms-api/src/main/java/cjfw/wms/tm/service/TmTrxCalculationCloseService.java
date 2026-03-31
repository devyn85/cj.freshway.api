package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.dp.dto.DpReceiptExDCDetailResDto;
import cjfw.wms.tm.dto.TmTrxCalculationCloseReqDto;
import cjfw.wms.tm.dto.TmTrxCalculationCloseResDto;
import cjfw.wms.tm.dto.TmTrxCalculationReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * 
 * @author : ParkJinWoo
 * @date : 2026.02.04
 * @description : 운송료정산 기능을 구현한 Controller Class
 * @issues : ----------------------------------------------------------- DATE
 *         AUTHOR MAJOR_ISSUE
 *         -----------------------------------------------------------
 *         2026.02.04 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmTrxCalculationCloseService {

    private transient static final String SERVICEID_PREFIX = "tmTrxCalculationCloseService.";
    private transient static final String PAKAGE_NAME = "SPTM_TRX_CALCULATION";

    private final CommonDao commonDao;

    private final UserContext userContext;

    /**
     * @description : 운송료정산 조회 기능을 구현한 Method
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE        AUTHOR     MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.02.04 ParkJinWoo 생성
     */
    public List<TmTrxCalculationCloseResDto> getMasterList(TmTrxCalculationCloseReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist", reqDto);
    }

    /**
     * @description : 운송비정산 마감
     * @issues :
     * <pre>
     * ----------------------------------------------------------- 
     * DATE       AUTHOR     MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.02.04 ParkJinWoo 생성
     */
    public String saveClosing(TmTrxCalculationCloseReqDto paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        TmTrxCalculationCloseReqDto reqDto = ModelMapperUtil.map(paramDto, TmTrxCalculationCloseReqDto.class);

        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드

        log.info("▶avc_DCCODE->{}", avc_DCCODE);

        TmTrxCalculationCloseReqDto procedureDto = new TmTrxCalculationCloseReqDto();

        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
        procedureDto.setAvc_DCCODE(reqDto.getDcCode());

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = new String[] { "DCCODE", "STTL_YM", "COURIER", "CLOSE_YN" };
        Object[] valueList = new Object[] { reqDto.getDcCode(), reqDto.getSttlYm().replace("-", "").substring(0, 6),
                reqDto.getCourier(), reqDto.getCloseYn() };

        procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, procedureDto);

        // 프로시저 OUT Parameter(3/4)
        resultCode = (String) procedureDto.getResultCode();
        resultMessage = (String) procedureDto.getResultMessage();

        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);

        // 프로시저 Exception 처리(4/4)
        if (!"0".equals(resultCode)) {
            log.error("▶운송비정산 마감 -> 마감 오류 발생 ");
            throw new UserHandleException("" + "에러코드 : " + resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가
                                                                                                       // 발생했습니다.
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 운송비정산 월정산
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveCalculation(TmTrxCalculationCloseReqDto paramDto) {        
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        TmTrxCalculationCloseReqDto reqDto = ModelMapperUtil.map(paramDto, TmTrxCalculationCloseReqDto.class);
        
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드
        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
        List<TmTrxCalculationCloseResDto> saveList = reqDto.getSaveList(); // 저장리스트
        
        if (null != saveList) {
            for (TmTrxCalculationCloseResDto dto : saveList) {
                TmTrxCalculationCloseReqDto procedureDto = new TmTrxCalculationCloseReqDto();
                
                // PKG 파라마터 세팅 - 공통(1/4)
                ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
                procedureDto.setAvc_DCCODE(dto.getDcCode());
                
                // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>  
                String[] keyList = new String[] {
                        "DCCODE"
                       ,"STTL_DATE"
                       };
                Object[] valueList = new Object[] {
                        dto.getDcCode()
                       ,dto.getDeliverydate()
                       };
                
                procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, procedureDto); 
                
                // 프로시저 OUT Parameter(3/4)
                resultCode    = (String)procedureDto.getResultCode();
                resultMessage = (String)procedureDto.getResultMessage();
                
                log.info("resultCode->{}",resultCode);
                log.info("resultMessage->{}",resultMessage);
                
                // 프로시저 Exception 처리(4/4)
                if(!"0".equals(resultCode)){
                    log.error("▶운송비정산마감 -> 월정산 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                } 
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

}
