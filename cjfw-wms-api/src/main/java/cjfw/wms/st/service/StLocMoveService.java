package cjfw.wms.st.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import cjfw.wms.st.dto.StLocMoveReqDto;
import cjfw.wms.st.dto.StLocMoveResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description :재고일괄이동  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class StLocMoveService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StLocMoveService.class.getSimpleName()) + ".";
	private transient static final String PAKAGE_NAME = "SPST_MOVEMENT";
	/** 프로세스타임 */
	private transient static final String PROCESSTYPE = "ST_BATCHMOVE";	
	/** 임시테이블 타입 */
	private transient static final String TEMPTABLETYPE = "ST";		
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;	
	/** 공통.service */
	private final CmCommonService cmCommonService;	

	/** @description : 재고일괄이동 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getTab1MasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getTab1MasterList", reqDto);
	}

	/** @description : 재고일괄이동 상세 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getTab2MasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getTab2MasterList", reqDto);
	}
	
	/**
	 * @description : 재고일괄이동 저장 처리 Method
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.30 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	public StLocMoveResDto saveMasterList(StLocMoveReqDto paramDto) throws Exception {
		StLocMoveResDto resultDto = new StLocMoveResDto();
		
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		StLocMoveReqDto reqDto = ModelMapperUtil.map(paramDto, StLocMoveReqDto.class);
		List<StLocMoveResDto> saveList = reqDto.getSaveList(); // 저장리스트
		
		reqDto.setProcesstype(PROCESSTYPE);     // 프로세스타입
		reqDto.setTemptabletype(TEMPTABLETYPE); // 임시테이블타입	
		
		// START.필수입력
		//if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getInvoiceprintkey()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"Invoiceprintkey"} ) + resultMessage ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
		// END.필수입력
		
		/*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 

		int chunkSize = 200;
		List<CmSyProcessTempStEntity> insertList = new ArrayList<>();
		for (int i = 0; i < saveList.size(); i++) { 
			StLocMoveResDto dto = saveList.get(i);
        	// 임시테이블에 등록(2/3)
        	CmSyProcessTempStEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempStEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입 
        	log.info("▶dto->{}",dto);
        	
        	
        	// UI.params
			/* DTO 필드(카멜) → Entity 필드(카멜) 1 : 1 순서 매핑 */
			String columnsDto    ="FROM_DCCODE|FROM_STORERKEY|FROM_ORGANIZE|FROM_AREA|FROM_SKU|FROM_LOC|FROM_LOT|FROM_STOCKID|FROM_STOCKGRADE|FROM_STOCKTYPE|FROM_ORDERQTY|FROM_UOM|TO_DCCODE|TO_STORERKEY|TO_ORGANIZE|TO_AREA|TO_SKU|TO_LOC|TO_LOT|TO_STOCKID|TO_STOCKGRADE|TO_STOCKTYPE|TO_ORDERQTY|TO_UOM|ETCQTY1|ETCQTY2|QTYEXPECTED|REASONCODE|REASONMSG";
			String columnsEntity ="FROM_DCCODE|FROM_STORERKEY|FROM_ORGANIZE|FROM_AREA|FROM_SKU|FROM_LOC|FROM_LOT|FROM_STOCKID|FROM_STOCKGRADE|FROM_STOCKTYPE|FROM_ORDERQTY|FROM_UOM|TO_DCCODE|TO_STORERKEY|TO_ORGANIZE|TO_AREA|TO_SKU|TO_LOC|TO_LOT|TO_STOCKID|TO_STOCKGRADE|TO_STOCKTYPE|TO_ORDERQTY|TO_UOM|ETCQTY1|ETCQTY2|SRCSERIALKEY1|TRAFFICCOP|MEMO1";
			entity = (CmSyProcessTempStEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
    		
        	// START.필수입력 check - 그리드 변수 등
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromLot()))         ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromLot"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromStockid()))     ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromStockid"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromArea()))        ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromArea"}));
    		if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getToLoc()))           ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"이동로케이션"}));
    		if (entity.getToOrderqty() == null                         ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"이동수량"}));
    		

    		// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
            	insertList.clear();
            }
        }
        /*END.Temp Table Insert*/	
        
        
        /*START.PAKAGE 호출*/
		// PKG 파라마터 세팅 - 공통(1/4)
		StLocMoveReqDto dto = new StLocMoveReqDto();
		ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList = { "PROCEDURE"        ,"PROCESSTYPE"     ,"TEMPTABLETYPE",  "PROCESSCREATOR", "SPID",         
						    // 업무파라이터 없음
				            //"REASONCODE", "REASONMSG" 20260306@sss 그리드 사유코드/사유명 로 변경
						   };
		Object[] valueList = { PAKAGE_NAME, reqDto.getProcesstype(), reqDto.getTemptabletype(),reqDto.getGUserId(), reqDto.getGSpid(),
				            // 업무파라이터 없음
							//reqDto.getReasoncode(),reqDto.getReasonmsg() 
						     };
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		int rv = cmCommonService.saveProcedure(dto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(dto.getResultCode());
		resultMessage = StringUtil.nvl(dto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶재고일괄이동 - 저장 시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"재고일괄이동-저장"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		 /*END.PAKAGE 호출*/     
		
		/*START.최종 화면그리드 데이터 조회 - 저장처리결과*/
		resultDto.setResultList(commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", reqDto));
		/*END.최종 화면그리드 데이터 조회 - 저장처리결과*/	
       
		return resultDto;
	}		

}
