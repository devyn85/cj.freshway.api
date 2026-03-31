package cjfw.wms.st.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.entity.CmSyProcessTempSnEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.st.dto.StSkuLabelReqDto;
import cjfw.wms.st.dto.StSkuLabelResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : baechan (c_bae@cj.net)
 * @date : 2025.08.25
 * @description : 상품이력번호등록  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 baechan (c_bae@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class StSkuLabelService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StSkuLabelService.class.getSimpleName()) + ".";
	private transient static final String PAKAGE_NAME = "SPST_STOCK_SERIALINFO";
	private transient static final String PROCESSTYPE = "ST_STOCK_SERIALINFO";	
	private transient static final String TEMPTABLETYPE = "SN";
	
	private final CommonDao commonDao;
	private final CmCommonService cmCommonService;
	private final UserContext userContext;

	/** @description : 상품이력번호등록 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 baechan (c_bae@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getMasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getMasterList", reqDto);
	}
	
	/**
	/** @description : 상품이력번호등록 저장 List Method
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.27 baechan (c_bae@cj.net) 생성 </pre>
	*/
	public String saveMasterList(StSkuLabelReqDto paramDto) throws Exception {
		
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
		StSkuLabelReqDto reqDto = ModelMapperUtil.map(paramDto, StSkuLabelReqDto.class);
		
		List<StSkuLabelResDto> saveList = reqDto.getSaveList(); // 저장리스트
        String avc_COMMAND = reqDto.getAvc_COMMAND(); // 센터코드
		
		log.info("▶saveList.size->{}",saveList);
        log.info("▶avc_DCCODE->{}",avc_COMMAND);
        
		reqDto.setProcesstype(PROCESSTYPE); // 임시테이블타입
        
		/*1. 임시테이블 삭제*/
        //commonDao.insert(SERVICEID_PREFIX + "deleteTampTable", reqDto);
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp" + TEMPTABLETYPE, reqDto);
        
        /*2. 임시테이블 저장*/
		int chunkSize = 200;
		List<CmSyProcessTempSnEntity> insertList = new ArrayList<>();
		
		for (int i = 0; i < saveList.size(); i++) { 			
			StSkuLabelResDto dto = saveList.get(i);
        	// 임시테이블에 등록(2/3)
			CmSyProcessTempSnEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempSnEntity.class);
			
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입 
        	
        	// START.필수입력 check - 그리드 변수 등
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getCheckyn()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"checkyn"}));   // 해당 정보가 없어 처리할 수 없습니다.-{0}  	
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getArea()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"area"}));
            if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSku()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"sku"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDpSlipdt()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dpSlipdt"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDpSlipno()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dpSlipno"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDpSlipline()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dpSlipline"}));
            if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSerialno()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"serialno"}));
            // if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getConvserialno()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"convserialno"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSeriallevel()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"seriallevel"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSerialtype()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"serialtype"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFactoryname()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"factoryname"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPlaceoforigin()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"placeoforigin"}));
            if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getLottable01()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"lottable01"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getUom()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"uom"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrderqty()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"orderqty"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getGrossweight()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"grossweight"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getNetweight()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"netweight"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPrintedqty()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"printedqty"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDpDoctype()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dpDoctype"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getConvertlot()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"convertlot"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getButcherydt()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"butcherydt"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getfromvaliddt()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"contracttype"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromvaliddt()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromvaliddt"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTovaliddt()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"tovaliddt"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getContractcustkey()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"contractcustkey"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getBarcode()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"barcode"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPokey()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"pokey"}));
            //if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPoline()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"poline"}));
            
    		// END.필수입력
        	insertList.add(entity);

        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTemp" + TEMPTABLETYPE, insertList); 
            	insertList.clear();
            }        	
        }
        /*END.Temp Table Insert*/	
		
        /*2. 패키지 프로시저 실행*/
		StSkuLabelReqDto dto = new StSkuLabelReqDto();
		
		ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
		dto.setAvc_COMMAND(avc_COMMAND);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE" ,"PROCESSTYPE", "PROCESSCREATOR"};
		Object[] valueList = {PAKAGE_NAME ,PROCESSTYPE, reqDto.getGUserId()};
		
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

	/** @description : 상품이력번호등록 durationtype 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.02 baechan (c_bae@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getDurationTypeListByExcelUploadList(T paramDto) {
		StSkuLabelReqDto reqDto = ModelMapperUtil.map(paramDto, StSkuLabelReqDto.class);
		
		List<StSkuLabelResDto> excelUploadList = reqDto.getExcelUploadList(); // 저장리스트

		return commonDao.selectList(SERVICEID_PREFIX+"getDurationTypeList", excelUploadList);
	}
}
