package cjfw.wms.st.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.st.dto.StConvertIdReqDto;
import cjfw.wms.st.dto.StConvertIdResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description :PLT-ID변경  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class StConvertIdService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StConvertIdService.class.getSimpleName()) + ".";
	private transient static final String PAKAGE_NAME = "SPST_CONVERT";
	/** 프로세스타임 */
	//private transient static final String PROCESSTYPE = "WD_INVOICECUSTTOTAL";	
	/** 임시테이블 타입 */
	//private transient static final String TEMPTABLETYPE = "WD";	
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.service */
	private final CmCommonService cmCommonService;

	/** @description : PLT-ID변경 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getMasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getMasterList", reqDto);
	}

	/** @description :  PLT-ID변경 화면 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public String saveMasterList(StConvertIdReqDto paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
		
        // 파라미터 위변조 적용(paramDto->reqDto)
        StConvertIdReqDto reqDto = ModelMapperUtil.map(paramDto, StConvertIdReqDto.class);
        List<StConvertIdResDto> saveList = reqDto.getSaveList(); // 저장리스트        
        
        log.info("▶saveList.size->{}",saveList);
        
		// START.필수입력
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReasoncode()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"reasoncode"}));
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReasonmsg())))  throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"reasonmsg"}));
		// END.필수입력
        
        
		for (StConvertIdResDto dto : saveList) {
			// PKG 파라마터 세팅 - 공통(1/4)
			ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
			
        	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
			
			// START.필수입력
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getDccode())))         throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getStorerkey())))      throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			//
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getConverttype()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"convertType"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReasoncode())))  throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"reasoncode"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReasonmsg())))   throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"reasonmsg"}));
			// END.필수입력
			
    		String[] keyList   = {
    				               "PROCEDURE" 
    							   // strColMerge
    				              ,"DCCODE"     ,"STORERKEY" ,"ORGANIZE" ,"AREA"         ,"FROM_LOC"
    				              ,"SKU"        ,"UOM"       ,"FROM_LOT" ,"FROM_STOCKID" ,"FROM_STOCKGRADE"
    				              ,"TRANQTY"    ,"LOTTABLE01"
    				              // get Parameter
    				              ,"CONVERTTYPE","TO_STOCKID" ,"REASONCODE" ,"REASONMSG"
    				             };
    		Object[] valueList = {
    				              PAKAGE_NAME             
    				              // strColMerge
    				             ,dto.getDccode()  ,dto.getStorerkey()    ,dto.getOrganize() ,dto.getArea()        ,dto.getFromLoc()
    				             ,dto.getSku()     ,dto.getUom()          ,dto.getFromLot()  ,dto.getFromStockid() ,dto.getFromStockgrade()
    				             ,dto.getTranqty() ,dto.getLottable01(),
    				             // get Parameter
    				             reqDto.getConverttype(), reqDto.getToStockid() ,reqDto.getReasoncode() ,reqDto.getReasonmsg()
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
    			log.error("▶PLT-ID변경 처리 시 오류 발생 ");
    			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"PLT-ID변경"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
    		}  	
    		 /*END.PAKAGE 호출*/  
        	
        }
		      
       
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	

}
