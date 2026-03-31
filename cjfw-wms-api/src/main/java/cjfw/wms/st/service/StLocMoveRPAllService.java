package cjfw.wms.st.service;

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
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.dto.StLocMoveRPAllReqDto;
import cjfw.wms.st.dto.StLocMoveRPAllResDto;
import cjfw.wms.st.dto.StLocMoveRPAllResPrintDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.28 
 * @description : 출고재고보충(전센터) Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.28 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StLocMoveRPAllService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stLocMoveRPAllService.";
	
	private transient static final String PAKAGE_NAME = "SPST_REPLENISHMENT_ALL";
	private transient static final String PROCESSTYPE = "ST_REPLENISHMENT";
	private transient static final String TEMPTABLETYPE = "ST";
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;
	/** 공통.service */
	private final CmCommonService cmCommonService;

	/**
	 * @description : 출고재고보충(전센터) 목록(TAB1/2포함) 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.28 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StLocMoveRPAllResDto> getMasterList(StLocMoveRPAllReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<StLocMoveRPAllResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return list;
	}
	
	/**
	 * @description : 출고재고보충(전센터) 출력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.28 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StLocMoveRPAllResPrintDto> getPrintList(StLocMoveRPAllReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<StLocMoveRPAllResPrintDto> list = commonDao.selectList(SERVICEID_PREFIX + "getPrintList", dto);
		return list;
	}
	
	/**
	 * @description : 피킹작업지시상의 액션로직
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.03 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String save(StLocMoveRPAllReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		boolean bContinue = true;
		// 파라미터 위변조 적용(paramDto->reqDto)
		StLocMoveRPAllReqDto reqDto = ModelMapperUtil.map(paramDto, StLocMoveRPAllReqDto.class);
		
		
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		if(reqDto.getType().equals("CREATION")) {				
			String[] keyList   = {   "PROCEDURE" 
									,"DCCODE"    	 	
									,"STORERKEY"		 
									,"SLIPDT"		  
									,"SKU"		   
									,"FROMZONE"		
									,"TOZONE"		
								 };														
			Object[] valueList = {   PAKAGE_NAME 
									,reqDto.getGDccode() 	
									,reqDto.getGStorerkey()
									,reqDto.getSlipdt() 
									,reqDto.getSku()
									,reqDto.getFromZone()
									,reqDto.getToZone()
								 };
			reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		} else if(reqDto.getType().equals("SETTASK") || reqDto.getType().equals("CONFIRM") || reqDto.getType().equals("DELETE")) {		
			List<StLocMoveRPAllResDto> rawList = reqDto.getSaveList();
			log.info("▶rawList.size->{}",rawList);     
			StLocMoveRPAllResDto dto  = rawList.get(0);			    
			log.info("▶dto->{}",dto);
			String[] keyList   = {   "PROCEDURE" 
									,"DCCODE"    	 	
									,"STORERKEY"		 
									,"SLIPDT"		  
									,"SLIPNO"		   
									,"SLIPLINE"		
								 };														
			Object[] valueList = {   PAKAGE_NAME 
									,dto.getDccode()	
									,dto.getStorerkey()	
									,dto.getSlipdt()	
									,dto.getSlipno()	
									,dto.getSlipline()
								 };
			reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
			
			if(reqDto.getType().equals("SETTASK")) {
				//보충지시(SETTASK)일때
				//지시완료건은 지시완료된건으로 looptranpopup(결과창)에 보냄
				if(dto.getIfflagyn().equals("Y")) {
					bContinue = false;
				}
			}
		} 
				
		if(bContinue) {
			int rv = cmCommonService.saveProcedure(reqDto); 
			log.info("rv->{}",rv);
			
			// 프로시저 OUT Parameter(3/4)
			resultCode    = StringUtil.nvl(reqDto.getResultCode());
			resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
			log.info("resultCode->{}",resultCode);
			log.info("resultMessage->{}",resultMessage);
			
			// 프로시저 Exception 처리(4/4)
			if(!"0".equals(resultCode)){
				String sMsg = "";
				if(reqDto.getType().equals("CREATION")) {
					sMsg = "보충생성";
				} else if(reqDto.getType().equals("SETTASK")) {
					sMsg = "보충지시";
				} else if(reqDto.getType().equals("CONFIRM")) {
					sMsg = "일반보충이동";
				} else if(reqDto.getType().equals("DELETE")) {
					sMsg = "보충삭제";
				}
				log.error("▶"+sMsg+"시 오류 발생 ");
				throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{sMsg}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
			}
			return CanalFrameConstants.MSG_COM_SUC_CODE;
		} else {
			return "이미 보충지시완료된 상품입니다.";
		}
		/*END.PAKAGE 호출*/  
		
		
		
	}	
	
	/**
	 * @description : 리스트 출력
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.04 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StLocMoveRPAllResPrintDto> savePrint(StLocMoveRPAllReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		StLocMoveRPAllReqDto reqDto = ModelMapperUtil.map(paramDto, StLocMoveRPAllReqDto.class);
		List<StLocMoveRPAllResDto> saveList = reqDto.getSaveDataList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempStEntity> insertList = new ArrayList<>();
		
		List<StLocMoveRPAllResDto> list = new ArrayList<StLocMoveRPAllResDto>();		
		for (int i = 0; i < saveList.size(); i++) {
			StLocMoveRPAllResDto dto = saveList.get(i);
			
			CmSyProcessTempStEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempStEntity.class);
			entity.setPackagename(PAKAGE_NAME); // 패키지명
			entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			log.info("▶dto->{}",dto);
			String columnsDto    = "DCCODE|STORERKEY|SLIPDT|SUPPLNO|SUPPLLINE|ORDERTYPE|FROMLOC|TOLOC|SKU|STOCKID|UOM|LOTTABLE01|OPENQTY_BOX|OPENQTY_EA";
			String columnsEntity = "FROM_DCCODE|FROM_STORERKEY|WORK_NO|BATCH_NO|LIST_NO|FROM_ZONE|FROM_LOC|TO_LOC|FROM_SKU|FROM_STOCKID|FROM_UOM|FROM_LOT|ETCQTY1|ETCQTY2";
			
			entity = (CmSyProcessTempStEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromDccode()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromDccode"} )  );   // 해당 정보가 없어 처리할 수 없습니다.-{0}
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromStorerkey())) ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromStorerkey"} )  ); 
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getWorkNo()))    ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"workNo"} )  );    
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getBatchNo()))       ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"batchNo"} )  );       
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getListNo()))     ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"listNo"} )  );     
    		// END.필수입력 check
        	insertList.add(entity);
        	
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
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶리스트 출력시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"리스트 출력"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		List<StLocMoveRPAllResPrintDto> printList = commonDao.selectList(SERVICEID_PREFIX + "getPrintList", paramDto);
		return printList;
	}	
	
}
