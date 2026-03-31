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
import cjfw.wms.st.dto.StConvertSNReqDto;
import cjfw.wms.st.dto.StConvertSNResDetailT1Dto;
import cjfw.wms.st.dto.StConvertSNResDetailT2Dto;
import cjfw.wms.st.dto.StConvertSNResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.11
 * @description :재고 > 재고조정 > 상품이력번호변경  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StConvertSNService {
	
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    
    private transient static final String PAKAGE_NAME = "SPST_STOCK_SERIALINFO";
    //private transient static final String TEMPTABLETYPE = "SN";
    //private transient static final String PROCESSTYPE = "";
    
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StConvertSNService.class.getSimpleName()) + ".";

    /**
     * @description : 재고 > 재고조정 > 상품이력번호변경 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.11 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<StConvertSNResDto> getMasterList(StConvertSNReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * @description : 재고 > 재고조정 > 상품이력번호변경 재고현황_탭 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public List<StConvertSNResDetailT1Dto> getDetailT1List(StConvertSNReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailT1List", dto);
	}
	
	/**
	 * @description : 재고 > 재고조정 > 상품이력번호변경 입출이력_탭 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public List<StConvertSNResDetailT2Dto> getDetailT2List(StConvertSNReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailT2List", dto);
	}
	
	/**
	 * @description : 재고 > 재고조정 > 상품이력번호변경 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
    public String saveMasterList(StConvertSNReqDto paramDto) throws Exception {
      
    	// 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        StConvertSNReqDto reqDto = ModelMapperUtil.map(paramDto, StConvertSNReqDto.class);
        
        // 저장리스트의 첫번째 데이터
        StConvertSNResDto data = reqDto.getSaveDataList().get(0); 

        StConvertSNReqDto dto = new StConvertSNReqDto();

        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);
        
		// START.필수입력
        // MSG_COM_ERR_056 해당 정보가 없어 처리할 수 없습니다. - {0} 
        if ("".equals(cjfw.core.utility.StringUtil.nvl(data.getReasoncode()))) {  
        	// 사유코드
        	throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"LABEL_REASONCODE"}));      
        }
        if ("".equals(cjfw.core.utility.StringUtil.nvl(data.getReasonmsg()))) {  
        	// 사유메세지
        	throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"LABEL_REASONCODE_MSG"}));      
        }
		// END.필수입력
        // |SERIALKEY|SERIALNO|CONVSERIALNO|FACTORYNAME|FROMVALIDDT|TOVALIDDT|REASONCODE|REASONMSG"; 
        // PKG 파라마터 세팅 - 업무(2/4) 
        String[] keyList = {
        		             "SERIALKEY"       // 01. SERIALKEY                       
        		           , "SERIALNO"        // 02. SERIALNO                        
        		           , "CONVSERIALNO"    // 03. CONVSERIALNO                    
        		           , "FACTORYNAME"     // 04. FACTORYNAME                     
        		           , "FROMVALIDDT"     // 05. FROMVALIDDT  Main 그리드에 없는 컬럼    
        		           , "TOVALIDDT"       // 06. TOVALIDDT    Main 그리드에 없는 컬럼    
        		           , "REASONCODE"      // 07. REASONCODE                      
        		           , "REASONMSG"       // 08. REASONMSG                       
        		           };
        Object[] valueList = {
			  data.getSerialkey()             // 01. SERIALKEY
			, data.getSerialno()              // 02. SERIALNO
			, data.getConvserialno()          // 03. CONVSERIALNO
			, data.getFactoryname()           // 04. FACTORYNAME
			, ""                              // 05. FROMVALIDDT  Main 그리드에 없는 컬럼
			, ""                              // 06. TOVALIDDT    Main 그리드에 없는 컬럼
			, data.getReasoncode()            // 07. REASONCODE
			, data.getReasonmsg()             // 08. REASONMSG
        		};
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        // 2025.12.08 김동한 SPST_STOCK_SERIALINFO (CONVERTSN) 패키지 확인 결과 해당 컬럼으로 특별한 로직이 없음.
        //dto.setAvc_DCCODE(dto.getDccode());
		//dto.setAvc_ORGANIZE(dto.getOrganize());
        //int rv = cmCommonService.saveProcedure(dto);
        cmCommonService.saveProcedure(dto);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl((String) dto.getResultMessage());

        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_057", new String[]{"ST_CONVERT_SN"}) + resultMessage); // {0} 저장 시 문제가 발생했습니다.  상품이력번호변경
        }
        /*END.PAKAGE 호출*/


        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

}
