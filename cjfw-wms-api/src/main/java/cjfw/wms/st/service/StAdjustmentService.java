package cjfw.wms.st.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.st.dto.StAdjustmentReqDto;
import cjfw.wms.st.dto.StAdjustmentResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description :센터자체감모  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class StAdjustmentService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StAdjustmentService.class.getSimpleName()) + ".";
	private transient static final String PAKAGE_NAME = "SPST_CONVERT";	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.service */
	private final CmCommonService cmCommonService;


	/** @description : 센터자체감모 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getTab1MasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getTab1MasterList", reqDto);
	}
	

	/** @description : 센터자체감모 상세 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getTab2MasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getTab2MasterList", reqDto);
	}
	
	/** @description : 센터자체감모 화면 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public StAdjustmentResDto saveMasterList(StAdjustmentReqDto paramDto) {
		StAdjustmentResDto resultDto = new StAdjustmentResDto();
		
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
		
        // 파라미터 위변조 적용(paramDto->reqDto)
        StAdjustmentReqDto reqDto = ModelMapperUtil.map(paramDto, StAdjustmentReqDto.class);
        List<StAdjustmentResDto> saveList = reqDto.getSaveList(); // 저장리스트   
        String setYn = StringUtil.nvl(reqDto.getSetYn(),"N"); // 20250929@세트여부(Y/N) 추가 by sss
        
        log.info("▶saveList.size->{}",saveList);
        log.info("▶setYn->{}",setYn);
        
		// START.필수입력
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getConverttype()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"getConverttype"} ) ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
		// END.필수입력
        
        
		for (StAdjustmentResDto dto : saveList) {
			// PKG 파라마터 세팅 - 공통(1/4)
			ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
			
        	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
			
			// START.필수입력
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getConverttype())))     throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"converttype"})); // 
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getDocdt())))           throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docdt"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getConverttype())))     throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"converttype"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getStocktranstype())))  throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"stocktranstype"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getIfSendType())))      throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"ifSendType"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getWorkprocesscode()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"workprocesscode"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getOmsFlag())))         throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"omsFlag"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getCostcd())))          throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"costcd"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getImputetype())))      throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"imputetype"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getCustkey())))         throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"custkey"}));			
			// END.필수입력
			
    		String[] keyList   = {
    				               "PROCEDURE" 
    				               // strColMerge
    							  ,"DCCODE"      ,"STORERKEY"  ,"ORGANIZE"  ,"AREA"       ,"LOC" 
    							  ,"SKU"         ,"UOM"        ,"LOT"       ,"STOCKID"    ,"STOCKGRADE" 
    							  ,"TRANQTY"     ,"LOTTABLE01" ,"SERIALNO"  ,"REASONCODE" ,"REASONMSG" 
    							  ,"PROCESSMAIN" ,"ORDERTYPE"  ,"STOCKTYPE" ,"OTHER05"
    							  // get Parameter
    							  ,"DOCDT"    ,"CONVERTTYPE" ,"STOCKTRANSTYPE" ,"IF_SEND_TYPE" ,"WORKPROCESSCODE"
    							  ,"OMS_FLAG" ,"COSTCD"      ,"IMPUTETYPE"     ,"CUSTKEY"      ,"SET_YN"
    				             };
    		Object[] valueList = {
    				              PAKAGE_NAME             
    				              // strColMerge
    				              ,dto.getDccode()  ,dto.getStorerkey()  ,dto.getOrganize() ,dto.getArea()       ,dto.getLoc() 
    				              ,dto.getSku()     ,dto.getUom()        ,dto.getLot()      ,dto.getStockid()    ,dto.getStockgrade() 
    				              ,dto.getTranqty() ,dto.getLottable01() ,dto.getSerialno() ,dto.getReasoncode() ,dto.getReasonmsg() ,dto.getProcessmain() ,dto.getOrdertype() ,dto.getStocktype() ,dto.getOther051()
    				              // get Parameter
    				              ,reqDto.getDocdt()   ,reqDto.getConverttype() ,reqDto.getStocktranstype() ,reqDto.getIfSendType() ,reqDto.getWorkprocesscode() 
    				              ,reqDto.getOmsFlag() ,reqDto.getCostcd()      ,reqDto.getImputetype()     ,reqDto.getCustkey()    ,setYn
    				             };
    		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
			dto.setAvc_DCCODE(dto.getDccode());
			dto.setAvc_ORGANIZE(dto.getOrganize());
    		int rv = cmCommonService.saveProcedure(dto); 
    		log.info("rv->{}",rv);
    		
    		// 프로시저 OUT Parameter(3/4)
    		resultCode    = StringUtil.nvl(dto.getResultCode());
    		resultMessage = StringUtil.nvl(dto.getResultMessage());
    		log.info("resultCode->{}",resultCode);
    		log.info("resultMessage->{}",resultMessage);
    		
    		// 프로시저 Exception 처리(4/4)
    		if(!"0".equals(resultCode)){
    			log.error("▶센터자체감모 처리 시 오류 발생 ");
    			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"센터자체감모"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
    		}  	
    		 /*END.PAKAGE 호출*/  
    		
    		/*START.최종 화면그리드 데이터 조회 - 없음*/
    		//resultDto.setResultList(commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", reqDto));
    		/*END.최종 화면그리드 데이터 조회 - 없음*/	    		
        	
        }
       
		return resultDto;
	}	
	
	/**
	 * @description : XML 태그변환 Method
	 * @param xml
	 * @return
	 */
	public static String convertToTag(String xml) {
		String changed = xml.replace("&lt;", "<");
		changed = changed.replace("&gt;", ">");
	    return changed;
	}
	
	/** @description : 센터자체감모 화면 ZERO 재고생성 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public StAdjustmentResDto saveZeroStock(StAdjustmentReqDto paramDto) {
		StAdjustmentResDto resultDto = new StAdjustmentResDto();
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
		
        StAdjustmentReqDto reqDto = ModelMapperUtil.map(paramDto, StAdjustmentReqDto.class);
        
		// START.필수입력
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getAvc_REQUESTMSG())))     throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"avc_REQUESTMSG"})); //
        
        
        String xml = convertToTag(reqDto.getAvc_REQUESTMSG()); // &lt; -> < , &gt; -> >
        
        log.info("▶xml->{}",xml);
        java.util.regex.Matcher m1 = null;
        m1 = java.util.regex.Pattern.compile("<DCCODE>(.*?)</DCCODE>").matcher(xml);
        reqDto.setAvc_REQUESTMSG(xml); // 문자변환후 다시 세팅	
        //
        if (m1.find() && "".equals(cjfw.core.utility.StringUtil.nvl(m1.group(1))))  throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
        m1 = java.util.regex.Pattern.compile("<STORERKEY>(.*?)</STORERKEY>").matcher(xml);
        if (m1.find() && "".equals(cjfw.core.utility.StringUtil.nvl(m1.group(1))))  throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"})); 
        m1 = java.util.regex.Pattern.compile("<ORGANIZE>(.*?)</ORGANIZE>").matcher(xml);
        if (m1.find() && "".equals(cjfw.core.utility.StringUtil.nvl(m1.group(1))))  throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"})); 
        m1 = java.util.regex.Pattern.compile("<SKU>(.*?)</SKU>").matcher(xml);
        if (m1.find() && "".equals(cjfw.core.utility.StringUtil.nvl(m1.group(1))))  throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"sku"})); 
        m1 = java.util.regex.Pattern.compile("<LOC>(.*?)</LOC>").matcher(xml);
        if (m1.find() && "".equals(cjfw.core.utility.StringUtil.nvl(m1.group(1))))  throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"loc"}));         
		// END.필수입력
        
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(paramDto,reqDto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		// N/A -> UI에서 XML로 파라미터 전달하여 처리함
				
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl(reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶센터자체감모 처리 시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"센터자체감모"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/  
       
		return resultDto;
	}		
	
	/** @description : 센터자체감모 창고 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getOrganizeList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getOrganizeList", reqDto);
	}	

}
