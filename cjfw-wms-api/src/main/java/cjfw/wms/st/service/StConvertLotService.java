package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.st.dto.StConvertLotReqDto;
import cjfw.wms.st.dto.StConvertLotResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.05.30
 * @description : 유통기한변경 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class StConvertLotService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stConvertLotService.";
	private transient static final String PAKAGE_NAME = "SPST_CONVERT";
	/** 프로세스타임 */
	//private transient static final String PROCESSTYPE = "WD_INVOICECUSTTOTAL";	
	/** 임시테이블 타입 */
	//private transient static final String TEMPTABLETYPE = "WD";	
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.service */
	private final CmCommonService cmCommonService;

	/** @description : 유통기한변경 화면 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public List<StConvertLotResDto> getMasterList(StConvertLotReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto );
	}

	/** @description : 유통기한변경 화면 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public String saveMasterList(StConvertLotReqDto paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
		
        // 파라미터 위변조 적용(paramDto->reqDto)
        StConvertLotReqDto reqDto = ModelMapperUtil.map(paramDto, StConvertLotReqDto.class);
        List<StConvertLotResDto> saveList = reqDto.getSaveList(); // 저장리스트        
        
        log.info("▶saveList.size->{}",saveList);
        
		// START.필수입력(2/4)
		if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getConverttype()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"getConverttype"} ) ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getConverttype())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"converttype"}));
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getLottable02()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"lottable02"}));
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getLottable03()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"lottable03"}));
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getLottable04()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"lottable04"}));
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getLottable05()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"lottable05"}));
		// END.필수입력
        
        
		for (StConvertLotResDto dto : saveList) {
			// PKG 파라마터 세팅 - 공통(1/4)
			ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
			
        	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
			// START.필수입력
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getDccode())))         throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getStorerkey())))      throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getOrganize())))       throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getArea())))           throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"area"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getSku())))            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"sku"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getUom())))            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"uom"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getFromLoc())))        throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromLoc"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getFromLot())))        throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromLot"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getFromStockid())))    throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromStockid"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getFromStocktype())))  throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromStocktype"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getReasoncode())))     throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"reasoncode"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getReasonmsg())))      throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"reasonmsg"}));
			// END.필수입력
			
			String tempDt = "";
			/* 2025.12.22(20:46) 김동한. 박의병님 오더
			 * 까지 들어간 건 소비일자로
			 * 부터 들어간 건 제조일자로 파라미터 변경
			 * 까지,부터 해당사항 없으면 원래대로 소비일자로 파라미터 던짐.
			  
			 1. 까지(만료일기준)
			 2. ~부터(제조일기준)
			 3. 유통기간 해당없음
			 4. 까지(시간기준)
			
			 1,3,4 까지는 소비기한. 
			 2 부터는 제조일자
			*/
			if("2".equals(dto.getDurationtype())) {
				tempDt = dto.getManufacturedt();
			}else {
				tempDt = dto.getExpiredt();
			}
			
    		String[] keyList   = {
    				               "PROCEDURE" 
    				              ,"DCCODE"          ,"STORERKEY" ,"ORGANIZE"        ,"AREA"         ,"SKU"                     
    				              ,"UOM"             ,"FROM_LOC"  ,"FROM_LOT"        ,"FROM_STOCKID" ,"FROM_STOCKTYPE" 
    				              ,"FROM_STOCKGRADE" ,"TRANQTY"   ,"FROM_LOTTABLE01" ,"LOTTABLE01"   ,"REASONCODE"
    				              ,"REASONMSG"
    				              //
    				              ,"CONVERTTYPE" ,"LOTTABLE02","LOTTABLE03","LOTTABLE04"
    				              ,"LOTTABLE05"  
    				             };
    		Object[] valueList = {
    				              PAKAGE_NAME             
    				              // strColMerge
    				             ,dto.getDccode()         ,dto.getStorerkey()  ,dto.getOrganize()       ,dto.getArea()	       ,dto.getSku()             
    				             ,dto.getUom()            ,dto.getFromLoc()    ,dto.getFromLot()        ,dto.getFromStockid() ,dto.getFromStocktype() 
    				             //,dto.getFromStockgrade() ,dto.getTranqty()    ,dto.getFromLottable01() ,dto.getExpiredt()    ,dto.getReasoncode()    
    				             ,dto.getFromStockgrade() ,dto.getTranqty()    ,dto.getFromLottable01() ,tempDt               ,dto.getReasoncode()    
    				             ,dto.getReasonmsg()
    				             // get Parameter
    				             ,reqDto.getConverttype() ,reqDto.getLottable02(),reqDto.getLottable03(),reqDto.getLottable03()
    				             ,reqDto.getLottable05()  
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
    			log.error("▶소비기한변경 처리 시 오류 발생 ");
    			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"소비기한변경"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
    		}  	
    		 /*END.PAKAGE 호출*/  
        	
        }
		      
       
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
}
