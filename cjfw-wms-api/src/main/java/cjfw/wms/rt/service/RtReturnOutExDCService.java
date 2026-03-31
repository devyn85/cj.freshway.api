package cjfw.wms.rt.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.rt.dto.RtReturnOutExDCReqDto;
import cjfw.wms.rt.dto.RtReturnOutExDCResDto;
import cjfw.wms.rt.entity.RtReturnOutExDCEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.27 
 * @description : 외부비축협력사반품지시 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.27    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RtReturnOutExDCService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "rtReturnOutExDCService.";	
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";
	private transient static final String PAKAGE_NAME = "SPRT_RETURNOUT";	
	private transient static final String TEMPTABLETYPE = "WD"; 
	private transient static final String PROCESSTYPE = "RETURNOUT"; 
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 외부비축협력사반품지시 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.27    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<RtReturnOutExDCResDto> getDataHeaderList(RtReturnOutExDCReqDto rtReturnOutExDCReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderList", rtReturnOutExDCReqDto);
	}
	
	
	/**
	 * @description : 외부비축협력사반품지시 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.27    KimSunHo(sunhokim6229@cj.net)   생성
	 */
    public String saveConfirm(RtReturnOutExDCReqDto paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        // 파라미터 위변조 적용(reqDto->paramDto)
        RtReturnOutExDCReqDto reqDto = ModelMapperUtil.map(paramDto, RtReturnOutExDCReqDto.class);
        
        // 이전 일괄 요청 작업을 삭제한다
        RtReturnOutExDCEntity rtReturnOutExDCEntity = ModelMapperUtil.map(reqDto, userContext, RtReturnOutExDCEntity.class);
        rtReturnOutExDCEntity.setProcesstype(PROCESSTYPE);
        rtReturnOutExDCEntity.setSpid(reqDto.getGSpid());
        commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTemp" + TEMPTABLETYPE, rtReturnOutExDCEntity);
        
        List<RtReturnOutExDCResDto> saveHeaderList = reqDto.getSaveHeaderList(); // 저장리스트
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드
        BigDecimal   headerwdQty = null; 
        BigDecimal   detailwdQty = null; 
        
        log.info("▶saveHeaderList.size->{}",saveHeaderList);
        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
        if (null != saveHeaderList) {
            for (RtReturnOutExDCResDto dto : reqDto.getSaveHeaderList()) {                 
                dto.setProcesstype(PROCESSTYPE);
                dto.setListNo("DS_HEADER");
                dto.setWdDate(reqDto.getWdDate());
                dto.setToDccode(dto.getCustkey());
                dto.setToDcname(dto.getCustname());
                
                headerwdQty = dto.getWdQty();
                
                if (headerwdQty.compareTo(BigDecimal.ZERO) == 1) {
                    //-1은 작다, 0은 같다, 1은 크다 
                    rtReturnOutExDCEntity = ModelMapperUtil.map(dto, userContext, RtReturnOutExDCEntity.class);
                    commonDao.insert(SERVICEID_PREFIX +"saveHeaderInfoTempWD", rtReturnOutExDCEntity);
                }
            }
        }
         
        List<RtReturnOutExDCResDto> saveDetailList = reqDto.getSaveDetailList(); // 저장리스트
        
        log.info("▶saveDetailList.size->{}",saveDetailList);
        
        if (null != saveDetailList) {
            for (RtReturnOutExDCResDto dto : reqDto.getSaveDetailList()) {
                dto.setProcesstype(PROCESSTYPE);
                dto.setListNo("DS_DETAIL");
                dto.setWdDate(reqDto.getWdDate());
                dto.setToDccode(dto.getCustkey());
                dto.setToDcname(dto.getCustname());
                
                detailwdQty = dto.getWdQty();
                
                if (detailwdQty.compareTo(BigDecimal.ZERO) == 1) {
                    //-1은 작다, 0은 같다, 1은 크다 
                    rtReturnOutExDCEntity = ModelMapperUtil.map(dto, userContext, RtReturnOutExDCEntity.class);
                    commonDao.insert(SERVICEID_PREFIX +"saveDetailInfoTempWD", rtReturnOutExDCEntity);
                }                
            }
        }
                
        // PKG 파라마터 세팅 - 공통(1/4)
        RtReturnOutExDCResDto procDto = saveDetailList.get(0);
        reqDto.setGDccode(avc_DCCODE);
        ProcedureParametersFactory.initParamDto(reqDto, procDto, PAKAGE_NAME);
        procDto.setAvc_DCCODE(reqDto.getFixdccode());
        procDto.setAvc_ORGANIZE(reqDto.getFixdccode());
        
        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
        String[] keyList = {"PROCESSTYPE","AVC_COMMAND"};
        Object[] valueList = {"RETURNOUT", "INSERT"};
        
        procDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, procDto); 
                    
        // 프로시저 OUT Parameter(3/4)
        resultCode    = (String)procDto.getResultCode();
        resultMessage = (String)procDto.getResultMessage();
        
        log.info("resultCode->{}",resultCode);
        log.info("resultMessage->{}",resultMessage);
        
        // 프로시저 Exception 처리(4/4)
        if(!"0".equals(resultCode)){
            log.error("▶외부비축협력사반품지시 저장 -> 저장 오류 발생 ");
            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }  

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
}
