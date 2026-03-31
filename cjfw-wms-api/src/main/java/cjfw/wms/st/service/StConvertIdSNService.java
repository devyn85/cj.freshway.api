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
import cjfw.wms.st.dto.StConvertIdSNReqDto;
import cjfw.wms.st.dto.StConvertIdSNResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.16
 * @description ::재고 > 재고조정 > 이력상품바코드변경  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.16 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StConvertIdSNService {
	
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPST_CONVERT";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StConvertIdSNService.class.getSimpleName()) + ".";

    /**
     * @description : 재고 > 재고조정 > 이력상품바코드변경  조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<StConvertIdSNResDto> getMasterList(StConvertIdSNReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	/**
	 * @description : 재고 > 재고조정 > 이력상품바코드변경 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.16 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
    public String saveMasterList(StConvertIdSNReqDto paramDto) throws Exception {
      
    	// 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        StConvertIdSNReqDto reqDto = ModelMapperUtil.map(paramDto, StConvertIdSNReqDto.class);
        
        // 저장리스트의 첫번째 데이터
        StConvertIdSNResDto data = reqDto.getSaveDataList().get(0); 

        StConvertIdSNReqDto dto = new StConvertIdSNReqDto();

        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);
        
		// START.필수입력
        // MSG_COM_ERR_056 해당 정보가 없어 처리할 수 없습니다. - {0} 
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getToStockid()))) {  
        	// 변환유형
        	throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"LABEL_TO_STOCKID"}));      
        }
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReasoncode()))) {  
        	// 사유코드
        	throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"LABEL_REASONCODE"}));      
        }
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReasonmsg()))) {  
        	// 사유메세지
        	throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"LABEL_REASONCODE_MSG"}));      
        }
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getToStockid()))) {  
        	// 재고ID
        	throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"LABEL_TO_STOCKID"}));      
        }
		// END.필수입력
		
        // PKG 파라마터 세팅 - 업무(2/4) 
        String[] keyList = {
        		            "DCCODE"            // 01. DCCODE
        		          , "STORERKEY"         // 02. STORERKEY
        		          , "ORGANIZE"          // 03. ORGANIZE
        		          , "AREA"              // 04. AREA
        		          , "FROM_LOC"          // 05. FROM_LOC
        		          , "SKU"               // 06. SKU
        		          , "UOM"               // 07. UOM
        		          , "FROM_LOT"   	    // 08. FROM_LOT
        		          , "FROM_STOCKID"   	// 09. FROM_STOCKID
        		          , "FROM_STOCKGRADE"   // 10. FROM_STOCKGRADE
        		          , "TRANQTY"          	// 11. TRANQTY
        		          , "LOTTABLE01"        // 12. LOTTABLE01
        		          , "CONVERTTYPE"       // 13. CONVERTTYPE
        		          , "TO_STOCKID"        // 14. TO_STOCKID
        		          , "REASONCODE"        // 15. REASONCODE
        		          , "REASONMSG"         // 16. REASONMSG
        		          };
		
        Object[] valueList = {
        			  // Col
                        data.getDccode()          // 01. DCCODE         
                      , data.getStorerkey()       // 02. STORERKEY      
                      , data.getOrganize()        // 03. ORGANIZE       
                      , data.getArea()            // 04. AREA           
                      , data.getFromLoc()         // 05. FROM_LOC       
                      , data.getSku()             // 06. SKU            
                      , data.getUom()             // 07. UOM            
                      , data.getFromLot()         // 08. FROM_LOT       
                      , data.getFromStockid()     // 09. FROM_STOCKID   
                      , data.getFromStockgrade()  // 10. FROM_STOCKGRADE
                      , data.getTranqty()         // 11. TRANQTY        
                      , data.getLottable01()      // 12. LOTTABLE01     
      				  // Parameter
                      , reqDto.getConverttype()   // 13. CONVERTTYPE 변환유형
                      , reqDto.getToStockid()     // 14. TO_STOCKID  재고ID         
                      , reqDto.getReasoncode()    // 15. REASONCODE  사유코드      
                      , reqDto.getReasonmsg()     // 16. REASONMSG   사유코드명       
        		};
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        // 2025.12.08 김동한 SPST_CONVERT (CONFIRM_CI) / CORE_ST_CONVERT (CONFIRM_CI) 패키지 확인 결과 해당 컬럼으로 특별한 로직이 없음.
        //dto.setAvc_DCCODE(dto.getDccode());
		//dto.setAvc_ORGANIZE(dto.getOrganize());
        //int rv = cmCommonService.saveProcedure(dto);
        cmCommonService.saveProcedure(dto);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl((String) dto.getResultMessage());
        
        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_057", new String[]{"ST_CONVERT_ID_SN"}) + resultMessage); // {0} 저장 시 문제가 발생했습니다.  이력상품바코드변경
        }
        /*END.PAKAGE 호출*/

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
