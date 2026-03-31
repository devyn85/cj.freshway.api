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
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.dto.StInquiryReqDto;
import cjfw.wms.st.dto.StInquiryResDetailDto;
import cjfw.wms.st.dto.StInquiryResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.02
 * @description : 재고 > 재고조사 > 재고조사등록 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.02 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StInquiryService {
	
    private final UserContext userContext;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPST_INQUIRY";
    private transient static final String PROCESSTYPE = "ST_INQUIRY";
    private transient static final String TEMPTABLETYPE = "ST";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StInquiryService.class.getSimpleName()) + ".";

    /**
     * @description : 재고 > 재고조사 > 재고조사등록 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.02 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<StInquiryResDto> getMasterList(StInquiryReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }
    
    /**
     * @description : 재고 > 재고조사 > 재고조사등록 상세 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.02 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<StInquiryResDetailDto> getDetailList(StInquiryReqDto dto) {
    	return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
    }

	/**
	 * @description : 재고 > 재고조사 > 재고조사등록 상세 저장(5건 보다 큰 경우) Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.02 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
    public String saveDetailList1(StInquiryReqDto paramDto) throws Exception {
      
    	// BATCHPROCESSCONFIRM
    	// 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";
        String sDccode = "";
        String sOrganize = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        StInquiryReqDto reqDto = ModelMapperUtil.map(paramDto, StInquiryReqDto.class);
        
        // 저장리스트
        List<StInquiryResDetailDto> saveList = reqDto.getSaveDetailList(); 

		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		
		int chunkSize = 200;
		List<CmSyProcessTempStEntity> insertList = new ArrayList<>();	
        
        for (int i = 0; i < saveList.size(); i++) {
        	
        	StInquiryResDetailDto dto = saveList.get(i);
        	log.info("### dto = "+dto.toString());
			CmSyProcessTempStEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempStEntity.class); 
			
			entity.setPackagename(PAKAGE_NAME); // 패키지명
			entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			String columnsDto    = "DCCODE     |INQUIRYDT  |INQUIRYNO   |INQUIRY_NAME|PRIORITY|INQUIRYTYPE|STORERKEY     |ORGANIZE     |AREA     |SKU     |LOC     |LOT     |STOCKID   |STOCKGRADE     |MOBILE_ADD_YN|UOM     |SCANQTY_A    |TO_LOT"; // 그리드                                                         // 그리드 변수
			String columnsEntity = "FROM_DCCODE|FROM_IOTYPE|FROM_STOCKID|BATCH_NO    |ETCQTY1 |MEMO1      |FROM_STORERKEY|FROM_ORGANIZE|FROM_AREA|FROM_SKU|FROM_LOC|FROM_LOT|TO_STOCKID|FROM_STOCKGRADE|ARCHIVECOP   |FROM_UOM|FROM_ORDERQTY|TO_LOT"; // 공통TABLE컬럼명
			entity = (CmSyProcessTempStEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
			
			sDccode = dto.getDccode();
			sOrganize = dto.getOrganize();
			
			// START.필수입력 check - 그리드 변수 생략함.
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
		reqDto.setAvc_DCCODE(sDccode);
		reqDto.setAvc_ORGANIZE(sDccode);
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		int rv = cmCommonService.saveProcedure(reqDto); 
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());

		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_057", new String[]{"ST_INQUIRY"}) + resultMessage); // {0} 저장 시 문제가 발생했습니다.  재고조사등록
		}
		/*END.PAKAGE 호출*/ 
		
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 재고 > 재고조사 > 재고조사등록 상세 저장 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.02 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public String saveDetailList2(StInquiryReqDto paramDto) throws Exception {
    	// CONFIRM
    	
    	// 프로시저 에러코드 및 메세지
    	String resultCode = "";
    	String resultMessage = "";
    	
    	// 파라미터 위변조 적용(paramDto->reqDto)
    	StInquiryReqDto reqDto = ModelMapperUtil.map(paramDto, StInquiryReqDto.class);

    	// 저장리스트의 첫번째 데이터
        StInquiryResDetailDto data = reqDto.getSaveDetailList().get(0); 

        StInquiryReqDto dto = new StInquiryReqDto();

        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);
        
		// START.필수입력
        // MSG_COM_ERR_056 해당 정보가 없어 처리할 수 없습니다. - {0} 
//        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getToStockid()))) {  
//        	// 변환유형
//        	throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"LABEL_TO_STOCKID"}));      
//        }
//        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReasoncode()))) {  
//        	// 사유코드
//        	throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"LABEL_REASONCODE"}));      
//        }
//        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReasonmsg()))) {  
//        	// 사유메세지
//        	throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"LABEL_REASONCODE_MSG"}));      
//        }
//        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getToStockid()))) {  
//        	// 재고ID
//        	throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"LABEL_TO_STOCKID"}));      
//        }
		// END.필수입력
		
        // PKG 파라마터 세팅 - 업무(2/4) 
        String[] keyList = {
        		            "DCCODE"            // 01. DCCODE
        		          , "STORERKEY"         // 02. STORERKEY
        		          , "ORGANIZE"          // 03. ORGANIZE
        		          , "AREA"              // 04. AREA
        		          , "INQUIRYDT"         // 05. INQUIRYDT
        		          , "INQUIRYNO"         // 06. INQUIRYNO
        		          , "SKU"               // 07. SKU
        		          , "UOM"               // 08. UOM
        		          , "STOCKGRADE"        // 09. STOCKGRADE
        		          , "LOC"   	        // 10. LOC
        		          , "LOT"   	        // 11. LOT
        		          , "SCANQTY_A"         // 12. SCANQTY_A
        		          , "STOCKID"          	// 13. STOCKID
        		          , "TO_LOT"          	// 14. TO_LOT
        		          , "PRIORITY"          // 15. PRIORITY
        		          };
		
        Object[] valueList = {
        			  // Col
			            data.getDccode()            // 01. DCCODE
			          , data.getStorerkey()         // 02. STORERKEY
			          , data.getOrganize()          // 03. ORGANIZE
			          , data.getArea()              // 04. AREA
			          , data.getInquirydt()         // 05. INQUIRYDT
			          , data.getInquiryno()         // 06. INQUIRYNO
			          , data.getSku()               // 07. SKU
			          , data.getUom()               // 08. UOM
			          , data.getStockgrade()        // 09. STOCKGRADE
			          , data.getLoc()   	        // 10. LOC
			          , data.getLot()   	        // 11. LOT
			          , data.getScanqtyA()          // 12. SCANQTY_A
			          , data.getStockid()          	// 13. STOCKID	
			          , data.getToLot()          	// 14. TO_LOT	
			          , data.getPriority()        	// 15. PRIORITY	
        		};
        
		reqDto.setAvc_DCCODE(paramDto.getFixdccode());
		reqDto.setAvc_ORGANIZE(paramDto.getOrganize());
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        //int rv = cmCommonService.saveProcedure(dto);
        cmCommonService.saveProcedure(dto);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl((String) dto.getResultMessage());
        
        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_057", new String[]{"ST_INQUIRY"}) + resultMessage); // {0} 저장 시 문제가 발생했습니다.  재고조사등록
        }
        /*END.PAKAGE 호출*/
    	
    	return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 재고 > 재고조사 > 엑셀다운로드 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.16 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<StInquiryResDetailDto> getExcelList(StInquiryReqDto dto) {
    	return commonDao.selectList(SERVICEID_PREFIX + "getExcelList", dto);
    }
    
    /**
     * @description : 재고 > 재고조사 > 엑셀업로드 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.16 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<StInquiryResDetailDto> getExcelUpList(StInquiryReqDto dto) {

    	String lottype = commonDao.selectOne(SERVICEID_PREFIX + "getLottpye", dto);
    	
        //if (lottype.equals("") && lottype == null) {
        if (StringUtils.isBlank(lottype)) {
        	// 실사구분이 존재하지 않습니다.
            throw new UserHandleException(MessageUtil.getMessage("MSG_ST_INQUIRY_001")); 
        }
        
        dto.setLottype(lottype);
    	
        return commonDao.selectList(SERVICEID_PREFIX + "getExcelUpList", dto);
    }
}
