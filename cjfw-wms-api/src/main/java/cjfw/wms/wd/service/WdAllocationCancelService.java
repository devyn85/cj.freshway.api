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
import cjfw.wms.common.CommonConstants;
import cjfw.wms.wd.dto.WdAllocationCancelDetailResDto;
import cjfw.wms.wd.dto.WdAllocationCancelDetailSubResDto;
import cjfw.wms.wd.dto.WdAllocationCancelReqDto;
import cjfw.wms.wd.dto.WdAllocationCancelResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.24 
 * @description : 출고분배취소 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdAllocationCancelService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdAllocationCancelService.";
	
	private transient static final String PAKAGE_NAME = "SPWD_ALLOCATION";
	
	private transient static final String PROCESSTYPE = "WD_ALLOCATIONCANCEL";
	
	private transient static final String PROCESSTYPE_CARNO = "WD_ALLOCATIONCANCEL_CARNO";
	
	private transient static final String CANCELTYPE = "FIXSKU";
	
	private transient static final String TEMPTABLETYPE = "WD";		
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 자동취소 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationCancelResDto> getMasterList(WdAllocationCancelReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdAllocationCancelResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return list;
	}
	
	
	/**
	 * @description : 자동취소 상세 및 지정취소 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationCancelDetailResDto> getDetailList(WdAllocationCancelReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdAllocationCancelDetailResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
		return list;
	}
	
	/**
	 * @description : 지정취소 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationCancelDetailSubResDto> getDetailSubList(WdAllocationCancelReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdAllocationCancelDetailSubResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDetailSubList", dto);
		return list;
	}
	

	/**
	 * @description : 차량별취소 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.28 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationCancelResDto> getMasterListCarno(WdAllocationCancelReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdAllocationCancelResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterListCarno", dto);
		return list;
	}

	/**
	 * @description : 자동취소 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveAutoBatch(WdAllocationCancelReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		String sAVC_DCCODE = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdAllocationCancelReqDto reqDto = ModelMapperUtil.map(paramDto, WdAllocationCancelReqDto.class);
		List<WdAllocationCancelResDto> saveList = reqDto.getSaveAutoBatchList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdAllocationCancelResDto> list = new ArrayList<WdAllocationCancelResDto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdAllocationCancelResDto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipdt"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getCustkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"custkey"}));
			//if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOther01())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"other01"}));

			// END.필수입력 check
        	insertList.add(entity);
        	
        	sAVC_DCCODE = entity.getDccode();
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
            	insertList.clear();
            }
		}
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"};
		Object[] valueList = {PAKAGE_NAME, PROCESSTYPE};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		reqDto.setAvc_DCCODE(sAVC_DCCODE);
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶자동취소시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"자동취소"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	

	/**
	 * @description : 차량번호별취소 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.01 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveCarnoBatch(WdAllocationCancelReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		String sAVC_DCCODE = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdAllocationCancelReqDto reqDto = ModelMapperUtil.map(paramDto, WdAllocationCancelReqDto.class);
		List<WdAllocationCancelResDto> saveList = reqDto.getSaveAutoBatchList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE_CARNO);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdAllocationCancelResDto> list = new ArrayList<WdAllocationCancelResDto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdAllocationCancelResDto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE_CARNO); // 프로세스타입
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipdt"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getCarno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"carno"}));
			//if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOther01())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"other01"}));

			// END.필수입력 check
        	insertList.add(entity);
        	
        	sAVC_DCCODE = entity.getDccode();
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
            	insertList.clear();
            }
		}
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"};
		Object[] valueList = {PAKAGE_NAME, PROCESSTYPE_CARNO};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		reqDto.setAvc_DCCODE(sAVC_DCCODE);
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶자동취소시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"자동취소"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	

	/**
	 * @description : 지정취소 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveAllocatonBatch(WdAllocationCancelReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
		
        // 파라미터 위변조 적용(paramDto->reqDto)
        WdAllocationCancelReqDto reqDto = ModelMapperUtil.map(paramDto, WdAllocationCancelReqDto.class);
        List<WdAllocationCancelDetailSubResDto> saveList = reqDto.getSaveList(); // 저장리스트        
        
        log.info("▶saveList.size->{}",saveList);
        
        ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
        
        WdAllocationCancelDetailSubResDto dto = saveList.get(0);
	    
		// PKG 파라마터 세팅 - 공통(1/4)
		
    	log.info("▶dto->{}",dto);
    	
    	
    	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE"
							, "CANCELTYPE"
							, "SERIALKEY"
							, "DCCODE"
							, "STORERKEY"
							, "ORGANIZE"
							, "AREA"
							, "CUSTKEY"
							, "DOCDT"
							, "DOCNO"
							, "DOCLINE"
							, "SLIPDT"
							, "SLIPNO"
							, "SLIPLINE"
							, "LOC"
							, "LOT"
							, "STOCKID"
							, "STOCKGRADE"
							, "SERIALYN"
							, "SERIALNO"
							, "BOXBARCODE"
							, "CANCELQTY"
		};
		Object[] valueList = {PAKAGE_NAME
							, CANCELTYPE
							, dto.getSerialkey()	
							, dto.getDccode()
							, dto.getStorerkey()
							, dto.getOrganize()
							, dto.getArea()
							, dto.getCustkey()
							, dto.getDocdt()
							, dto.getDocno()
							, dto.getDocline()
							, dto.getSlipdt()
							, dto.getSlipno()
							, dto.getSlipline()
							, dto.getLoc()
							, dto.getLot()
							, dto.getStockid()
							, dto.getStockgrade()
							, dto.getSerialyn()
							, dto.getSerialno()
							, dto.getBoxbarcode()
							, dto.getCancelqty()
		};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		reqDto.setAvc_DCCODE(dto.getDccode());	
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶지정취소시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"지정취소"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		 /*END.PAKAGE 호출*/  
        	
		      
       
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	


}
