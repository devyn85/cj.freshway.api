package cjfw.wms.wd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.wd.dto.WdPickingCancelDetailResDto;
import cjfw.wms.wd.dto.WdPickingCancelReqDto;
import cjfw.wms.wd.dto.WdPickingCancelResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.10 
 * @description : 피킹취소처리 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.10 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdPickingCancelService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdPickingCancelService.";
	private transient static final String PAKAGE_NAME = "SPWD_PICKING";
	/** 프로세스타임 */
	private transient static final String PROCESSTYPE = "WD_PICKINGCANCEL";	
	/** 임시테이블 타입 */
	private transient static final String TEMPTABLETYPE = "WD";		
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;
	/** 공통.service */
	private final CmCommonService cmCommonService;

	/**
	 * @description : 피킹취소처리 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdPickingCancelResDto> getMasterList(WdPickingCancelReqDto wdPickingCancelReqDto) {
		log.info("### parameter = "+wdPickingCancelReqDto.toString());
		
		List<WdPickingCancelResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", wdPickingCancelReqDto);
		return list;
	}
	/**
	 * @description : 피킹취소처리 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdPickingCancelDetailResDto> getDetailList(WdPickingCancelReqDto wdPickingCancelReqDto) {
		
		log.info("#### parameter = "+wdPickingCancelReqDto.toString());		
		
		List<WdPickingCancelDetailResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", wdPickingCancelReqDto);
		return list;
	}
	

	/**
	 * @description : 지정취소 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.01 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String savePicking(WdPickingCancelReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
		
        // 파라미터 위변조 적용(paramDto->reqDto)
        WdPickingCancelReqDto reqDto = ModelMapperUtil.map(paramDto, WdPickingCancelReqDto.class);
        List<WdPickingCancelDetailResDto> saveList = reqDto.getSaveList(); // 저장리스트        
        
        log.info("▶saveList.size->{}",saveList);
        
	    List<WdPickingCancelDetailResDto> list = new ArrayList<WdPickingCancelDetailResDto>();
		for (WdPickingCancelDetailResDto dto : saveList) {
			// PKG 파라마터 세팅 - 공통(1/4)
			ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
			
        	log.info("▶dto->{}",dto);
        	
        	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
    		String[] keyList   = {"PROCEDURE" ,"CANCELTYPE"  ,"SERIALKEY"		,"DCCODE"         		,"STORERKEY"				,"CUSTKEY"			,"DOCDT"		,"DOCNO"		,"SLIPDT"		,"SLIPNO" 		,"CANCELQTY"};
    		Object[] valueList = {PAKAGE_NAME ,"FIXPICK" 	,dto.getSerialkey()	,dto.getDccode() 	,reqDto.getGStorerkey()	,dto.getCustkey()	,dto.getDocdt()	,dto.getDocno()	,dto.getSlipdt(),dto.getSlipno(),dto.getCancelqty()};
    		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
    		dto.setAvc_DCCODE(dto.getDccode());
    		int rv = cmCommonService.saveProcedure(dto); 
    		log.info("rv->{}",rv);
    		
    		// 프로시저 OUT Parameter(3/4)
    		resultCode    = StringUtil.nvl(dto.getResultCode());
    		resultMessage = StringUtil.nvl((String)dto.getResultMessage());
    		log.info("resultCode->{}",resultCode);
    		log.info("resultMessage->{}",resultMessage);
    		
    		// 프로시저 Exception 처리(4/4)
    		if(!"0".equals(resultCode)){
    			log.error("▶지정취소시 오류 발생 ");
    			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"지정취소"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
    		}  	
    		 /*END.PAKAGE 호출*/  
        	
        }
		      
       
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 일괄취소 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.01 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveBatch(WdPickingCancelReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		String sAVC_DCCODE = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
		WdPickingCancelReqDto reqDto = ModelMapperUtil.map(paramDto, WdPickingCancelReqDto.class);
		List<WdPickingCancelResDto> saveList = reqDto.getSaveBatchList(); // 저장리스트        
		
		log.info("▶saveList.size->{}",saveList);
		/*1. 임시테이블 삭제*/
        commonDao.insert(SERVICEID_PREFIX + "deleteTampTable", reqDto);
        
        /*2. 임시테이블 저장*/
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		for (int i = 0; i < saveList.size(); i++) { 			
			WdPickingCancelResDto dto = saveList.get(i);
        	// 임시테이블에 등록(2/3)
        	CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입 
        	
        	// START.필수입력 check - 그리드 변수 등
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode   ()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"dccode"}    ));   // 해당 정보가 없어 처리할 수 없습니다.-{0}
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"storerkey"} ));   // 해당 정보가 없어 처리할 수 없습니다.-{0}
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize ()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"organize"}  ));   // 해당 정보가 없어 처리할 수 없습니다.-{0}
            if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getCustkey  ()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"custkey"}   ));   // 해당 정보가 없어 처리할 수 없습니다.-{0}
            if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocdt    ()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"docdt"}     ));   // 해당 정보가 없어 처리할 수 없습니다.-{0}
            if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno    ()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"docno"}     ));   // 해당 정보가 없어 처리할 수 없습니다.-{0}
            if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipdt   ()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"slipdt"}    ));   // 해당 정보가 없어 처리할 수 없습니다.-{0}
            if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipno   ()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"slipno"}    ));   // 해당 정보가 없어 처리할 수 없습니다.-{0}  		

    		// END.필수입력
        	insertList.add(entity);
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
            	insertList.clear();
            }        	
        	sAVC_DCCODE = entity.getDccode();
        }
        /*END.Temp Table Insert*/	
		
		WdPickingCancelReqDto dto = new WdPickingCancelReqDto();
		ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
		dto.setAvc_DCCODE(sAVC_DCCODE);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE" ,"PROCESSTYPE"  	};
		Object[] valueList = {PAKAGE_NAME ,PROCESSTYPE};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));	
		int rv = cmCommonService.saveProcedure(dto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(dto.getResultCode());
		resultMessage = StringUtil.nvl((String)dto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶일괄취소시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"일괄취소"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	

}
