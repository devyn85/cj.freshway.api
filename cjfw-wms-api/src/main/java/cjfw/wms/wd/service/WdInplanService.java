package cjfw.wms.wd.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpReceiptReqDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.dto.CmCodeReqDto;
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.wd.dto.WdInplanReqDto;
import cjfw.wms.wd.dto.WdInplanResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.16 
 * @description : 출고진행현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.16 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdInplanService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdInplanService.";
	
	/** 프로시져명 - 상품제외처리 */
	private transient static final String PAKAGE_NAME = "SPMS_EXCEPTION";
	/** 프로세스타임  */
	private transient static final String PROCESSTYPE = "MS_EXCEPTION";	
	/** 임시테이블타입 */
	private transient static final String TEMPTABLETYPE = "WD";		
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;	
	/** 공통.service */
	private final CmCommonService cmCommonService;


	/**
	 * @description : 출고진행현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdInplanResDto> getMasterList(WdInplanReqDto reqDto, Page page) {
		List<WdInplanResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
		return list;
	}
	

	/**
	 * @description : 공통코드 대용량 다운로드
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.01 공두경  생성 </pre>
	 */
	public void getCodeHeaderList(WdInplanReqDto dto, LargeExcel largeExcel) {
		commonDao.selectLargeExcelDataset(SERVICEID_PREFIX + "getMasterList", dto, largeExcel);
	}
	
	/**
	 * @description : 출고진행현황 - 상품제외처리
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.30 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	public String saveInquiry(WdInplanReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        // 파라미터 위변조 적용(paramDto->reqDto)
        WdInplanReqDto reqDto = ModelMapperUtil.map(paramDto, WdInplanReqDto.class);
        List<WdInplanResDto> saveList = reqDto.getSaveList(); // 저장리스트
        reqDto.setProcesstype(PROCESSTYPE);     // 프로세스타입
        reqDto.setTemptabletype(TEMPTABLETYPE); // 임시테이블타입	
		        
        log.info("▶saveList.size->{}",saveList.size());
        
		/*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		for (int i = 0; i < saveList.size(); i++) { 
			WdInplanResDto dto = saveList.get(i);
        	// 임시테이블에 등록(2/3)
        	CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입 
        	// UI.params
        	
    		// START.필수입력 check - 그리드 변수 등
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"dccode"} )  );   // 해당 정보가 없어 처리할 수 없습니다.-{0}
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"storerkey"} )  ); 
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno()))     ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"docno"} )  );     
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocline()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"docline"} )  );   
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipdt()))    ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"slipdt"} )  );    
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSku()))       ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"sku"} )  );       
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
        WdInplanReqDto dto = new WdInplanReqDto();
		ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
        
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = { "PROCEDURE" ,"PROCESSTYPE"  ,"TEMPTABLETYPE"     
							  ,"DCCODE"    ,"STORERKEY"};
		Object[] valueList = { PAKAGE_NAME      ,reqDto.getProcesstype() ,reqDto.getTemptabletype()
				              ,dto.getGDccode() ,dto.getGStorerkey()};
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
			log.error("▶출고진행현황 - 상품제외처리 시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"출고진행현황-상품제외"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		 /*END.PAKAGE 호출*/        
       
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}
	
	 public static void makeMultiValue(Object dto, String fieldName) {
		String getterMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        String setterMethodName = "setMulti" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        try {
        	Method getterMethod = dto.getClass().getMethod(getterMethodName);            
            
            String value = (String)getterMethod.invoke(dto);
            if(!isNull(value)) {
	            String[] multiList = value.split(",");
	            Method setterMethod = dto.getClass().getMethod(setterMethodName, value.getClass());
	            setterMethod.invoke(dto, multiList);
            }
        } catch (NoSuchMethodException e) {
            System.err.println("Setter method '" + setterMethodName + "' not found for field '" + fieldName + "' in " + dto.getClass().getName());
        } catch (Exception e) {
            System.err.println("Error invoking setter method '" + setterMethodName + "' for field '" + fieldName + "' in " + dto.getClass().getName() + ": " + e.getMessage());
        }
    }
     /**
     * @description : 메인 물동량 및 라벨건수
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    public <R, T> List<R> getMainMasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMainMasterList", reqDto);
	}
}
