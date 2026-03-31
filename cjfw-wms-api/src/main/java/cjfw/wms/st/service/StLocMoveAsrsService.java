package cjfw.wms.st.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
import cjfw.wms.st.dto.StLocMoveAsrsReqDto;
import cjfw.wms.st.dto.StLocMoveAsrsTab1ResDto;
import cjfw.wms.st.dto.StLocMoveAsrsTab2ResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.16 
 * @description : 자동창고보충 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.16 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StLocMoveAsrsService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stLocMoveAsrsService.";
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";	
	private transient static final String PAKAGE_NAME = "SPST_MOVEMENT";
	/** 프로세스타임 */
	private transient static final String PROCESSTYPE = "ST_BATCHMOVE_ASRS";	
	/** 임시테이블 타입 */
	private transient static final String TEMPTABLETYPE = "ST";		
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;
	/** 공통.service */
	private final CmCommonService cmCommonService;

	/**
	 * @description : 자동창고보충 이동대상 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StLocMoveAsrsTab1ResDto> getMasterList(StLocMoveAsrsReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<StLocMoveAsrsTab1ResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return list;
	}
	
	/**
	 * @description : 자동창고보충 이동결과 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StLocMoveAsrsTab2ResDto> getResultList(StLocMoveAsrsReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<StLocMoveAsrsTab2ResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getResultList", dto);
		return list;
	}
	
	
	/**
	 * @description : 자동창고보충 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StLocMoveAsrsTab2ResDto> saveBatch(StLocMoveAsrsReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		StLocMoveAsrsReqDto reqDto = ModelMapperUtil.map(paramDto, StLocMoveAsrsReqDto.class);
		List<StLocMoveAsrsTab1ResDto> saveList = reqDto.getSaveBatchList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempStEntity> insertList = new ArrayList<>();
		
		List<StLocMoveAsrsTab1ResDto> list = new ArrayList<StLocMoveAsrsTab1ResDto>();		
		for (int i = 0; i < saveList.size(); i++) {
			StLocMoveAsrsTab1ResDto dto = saveList.get(i);
			
			CmSyProcessTempStEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempStEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			log.info("▶dto->{}",dto);
			String columnsDto    = "FROM_DCCODE|FROM_STORERKEY|FROM_ORGANIZE|FROM_AREA|FROM_SKU|FROM_LOC|FROM_LOT|FROM_STOCKID|FROM_STOCKGRADE|FROM_STOCKTYPE|FROM_ORDERQTY|FROM_UOM|TO_DCCODE|TO_STORERKEY|TO_ORGANIZE|TO_AREA|TO_SKU|TO_LOC|TO_LOT|TO_STOCKID|TO_STOCKGRADE|TO_STOCKTYPE|TO_ORDERQTY_BOX|TO_ORDERQTY_EA|TO_UOM|ETCQTY1|ETCQTY2";
			String columnsEntity = "FROM_DCCODE|FROM_STORERKEY|FROM_ORGANIZE|FROM_AREA|FROM_SKU|FROM_LOC|FROM_LOT|FROM_STOCKID|FROM_STOCKGRADE|FROM_STOCKTYPE|FROM_ORDERQTY|FROM_UOM|TO_DCCODE|TO_STORERKEY|TO_ORGANIZE|TO_AREA|TO_SKU|TO_LOC|TO_LOT|TO_STOCKID|TO_STOCKGRADE|TO_STOCKTYPE|TO_ORDERQTY|TO_CONFIRMQTY|TO_UOM|ETCQTY1|ETCQTY2";
						
			entity = (CmSyProcessTempStEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromDccode()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromDccode"} )  );   // 해당 정보가 없어 처리할 수 없습니다.-{0}
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromStorerkey())) ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromStorerkey"} )  ); 
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromOrganize()))    ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromOrganize"} )  );    
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromArea()))       ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromArea"} )  );       
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromSku()))     ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromSku"} )  );     
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromLoc()))     ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromLoc"} )  );     
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromLot()))     ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromLot"} )  );     
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromStockid()))     ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromStockid"} )  );     
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromStockgrade()))     ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromStockgrade"} )  );     
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromStocktype()))     ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromStocktype"} )  );     
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromUom()))     ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"fromUom"} )  );     
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getToLoc()))     ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"toLoc"} )  );     
    		if(entity.getToOrderqty() == null     			) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"toOrderqty"} )  );     
    		if(entity.getToConfirmqty() == null     		) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"toConfirmqty"} )  );     
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
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE","PROCESSCREATOR","SPID"};
		Object[] valueList = {PAKAGE_NAME, PROCESSTYPE, reqDto.getGUserId(), reqDto.getGSpid()};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		reqDto.setAvc_DCCODE(saveList.get(0).getDccode());
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶리스트 조회시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"리스트 조회"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
       
		List<StLocMoveAsrsTab2ResDto> printList = commonDao.selectList(SERVICEID_PREFIX + "getResultList", reqDto);
		
		return printList;
	}	
	
}
