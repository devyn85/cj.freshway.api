package cjfw.wms.st.service;

import java.util.ArrayList;
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
import cjfw.wms.st.dto.StConvertCGReqDto;
import cjfw.wms.st.dto.StConvertCGResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : 고혜미
 * @date : 2025.09.18 
 * @description : 재고속성변경 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.18 고혜미 (laksjd0606@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StConvertCGService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stConvertCGService.";
	private transient static final String PAKAGE_NAME = "SPST_CONVERT";
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.service */
	private final CmCommonService cmCommonService;	
	
	/**
	 * @description : 재고속성변경 목록 조회
	 * @issues : <pre>
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.18 고혜미 생성 </pre>
	 */
	public List<StConvertCGResDto> getMasterList(StConvertCGReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * @description : 재고속성변경 목록 조회
	 * @issues : <pre>
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.18 고혜미 생성 </pre>
	 */
	public List<StConvertCGResDto> getDetailList1(StConvertCGReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList1", dto);
	}	
	
	/**
	 * @description : 재고속성변경 목록 조회
	 * @issues : <pre>
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.18 고혜미 생성 </pre>
	 */
	public List<StConvertCGResDto> getDetailList2(StConvertCGReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList2", dto);
	}		
	
	/**
	 * @description : 지정취소 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.01 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveMasterList(StConvertCGReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
		
        // 파라미터 위변조 적용(paramDto->reqDto)
        StConvertCGReqDto reqDto = ModelMapperUtil.map(paramDto, StConvertCGReqDto.class);
        List<StConvertCGResDto> saveList = reqDto.getSaveList(); // 저장리스트        
        
	    List<StConvertCGResDto> list = new ArrayList<StConvertCGResDto>();
		for (StConvertCGResDto dto : saveList) {
			// PKG 파라마터 세팅 - 공통(1/4)
			ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
        	
        	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
    		String[] keyList   = {"PROCEDURE" ,"CONVERTTYPE"  ,"DCCODE"		    ,"STORERKEY"                ,"ORGANIZE"             ,"AREA"	        ,"FROM_LOC"	        ,"SKU"          ,"UOM"        ,"FROM_LOT"        ,"FROM_STOCKID"        ,"FROM_STOCKGRADE"         ,"FROM_STOCKTYPE"        ,"TRANQTY"         ,"LOTTABLE01"         ,"REASONCODE"         ,"REASONMSG"         ,"TO_STOCKGRADE"   ,"i_ERR","vc_RESULTMSG" };
    		//Object[] valueList = {PAKAGE_NAME ,"CG" 	      ,dto.getGDccode()	,dto.getGStorerkey() 	    ,dto.getOrganize()	,dto.getArea()	,dto.getFromloc()	,dto.getSku()	,dto.getUom() ,dto.getFromlot()  ,dto.getFromstockid()  , dto.getFromstockgrade()  ,dto.getFromstocktype()  ,dto.getTranqty()  ,dto.getLottable01()  ,dto.getReasoncode()  ,dto.getReasonmsg()  ,dto.getTostockgrade() , null,null};
    		// 2025.12.29 김동한 dto.getGDccode() -> dto.getDccode() 수정
    		Object[] valueList = {PAKAGE_NAME ,"CG" 	      ,dto.getDccode()	,dto.getGStorerkey() 	    ,dto.getOrganize()	,dto.getArea()	,dto.getFromloc()	,dto.getSku()	,dto.getUom() ,dto.getFromlot()  ,dto.getFromstockid()  , dto.getFromstockgrade()  ,dto.getFromstocktype()  ,dto.getTranqty()  ,dto.getLottable01()  ,dto.getReasoncode()  ,dto.getReasonmsg()  ,dto.getTostockgrade() , null,null};
    		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
			dto.setAvc_DCCODE(dto.getDccode());
			dto.setAvc_ORGANIZE(dto.getOrganize());
    		int rv = cmCommonService.saveProcedure(dto); 

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
 	
}
