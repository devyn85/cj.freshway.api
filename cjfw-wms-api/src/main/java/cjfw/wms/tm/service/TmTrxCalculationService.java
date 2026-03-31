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
import cjfw.wms.tm.dto.TmTrxCalculationReqDto;
import cjfw.wms.tm.dto.TmTrxCalculationResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.10.10
 * @description : 운송비정산 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmTrxCalculationService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmTrxCalculationService.";
	
	private transient static final String PAKAGE_NAME = "SPTM_TRX_CALCULATION";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 운송비정산 내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<TmTrxCalculationResDto> getMasterList(TmTrxCalculationReqDto reqDto) {
	    return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
	}
	
	/**
     * @description : 월 기준 근무일수 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<TmTrxCalculationResDto> getWorkDay(TmTrxCalculationReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getWorkDay", reqDto);
    }
    
    /**
     * @description : 차수별 착지 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<TmTrxCalculationResDto> getCostPriorityList(TmTrxCalculationReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getCostPriorityList", reqDto);
    }
    
    /**
     * @description : 차수별 권역이동 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<TmTrxCalculationResDto> getRegnMoveList(TmTrxCalculationReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getRegnMoveList", reqDto);
    }
	
	/**
     * @description : 운송비정산
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveCalculation(TmTrxCalculationReqDto paramDto) {        
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        TmTrxCalculationReqDto reqDto = ModelMapperUtil.map(paramDto, TmTrxCalculationReqDto.class);
        
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드

        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
        TmTrxCalculationReqDto procedureDto = new TmTrxCalculationReqDto();
        
        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
        procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
        
        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>  
        String[] keyList = new String[] {
                "DCCODE"
               ,"STTL_DATE"
               };
        Object[] valueList = new Object[] {
                reqDto.getFixdccode()
               ,reqDto.getDeliverydate() 
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
            log.error("▶운송비정산 -> 저장 오류 발생 ");
            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        } 
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;        
    }
    
    /**
     * @description : 운송비정산 마감 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveClosing(TmTrxCalculationReqDto paramDto) {        
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        TmTrxCalculationReqDto reqDto = ModelMapperUtil.map(paramDto, TmTrxCalculationReqDto.class);
        
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드

        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
        TmTrxCalculationReqDto procedureDto = new TmTrxCalculationReqDto();
        
        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
        procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
        
        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>  
        String[] keyList = new String[] {
                "DCCODE"
               ,"STTL_YM"
               ,"COURIER"
               ,"CLOSE_YN"
               };
        Object[] valueList = new Object[] {
                reqDto.getFixdccode()
               ,reqDto.getDeliverydate().replace("-", "").substring(0,6)
               ,reqDto.getCourier()
               ,reqDto.getCloseYn()
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
            log.error("▶운송비정산 -> 마감 오류 발생 ");
            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        } 
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;        
    }

}
