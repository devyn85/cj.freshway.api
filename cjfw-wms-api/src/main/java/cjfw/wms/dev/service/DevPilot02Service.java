package cjfw.wms.dev.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.dev.dto.DevPilot02MaskResDto;
import cjfw.wms.dev.dto.DevPilot02ReqDto;
import cjfw.wms.dev.dto.DevPilot02ResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.05.23 
 * @description : 센터상품속성 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.23    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DevPilot02Service {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "devPilot01Service.";
	/** 프로시져명 - 상품제외처리 */
	private transient static final String PAKAGE_NAME = "SPMS_EXCEPTION_TEST";	
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	/**
	 *  공통.Service
	 */
	private final CmCommonService cmCommonService;

	/**
	 * @description : 센터상품속성 목록 페이징 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.23    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public Page<DevPilot02ResDto> getMasterList(DevPilot02ReqDto dto, Page page) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getMasterList", dto, page);
	}
	
	/**
	 * @description : 출고진행현황 - 상품제외처리
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.30 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	public String saveInquiry(DevPilot02ReqDto paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        // 파라미터 위변조 적용(paramDto->reqDto)
        DevPilot02ReqDto reqDto = ModelMapperUtil.map(paramDto, DevPilot02ReqDto.class);
        List<DevPilot02ResDto> saveList = reqDto.getSaveList(); // 저장리스트        
        
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드
        
        log.info("▶saveList.size->{}",saveList);
        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
		if (null != saveList) {
			for (DevPilot02ResDto dto : reqDto.getSaveList()) { 
				// PKG 파라마터 세팅 - 공통(1/4)
				ProcedureParametersFactory.initParamDto(paramDto,dto, PAKAGE_NAME);
				
				// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
				String[] keyList = {"DCCODE", "STORERKEY"};
				Object[] valueList = {dto.getGDccode(), dto.getGStorerkey()};
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
					log.error("▶출고진행현황 - 상품제외처리 시 오류 발생 ");
					throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"출고진행현황-상품제외"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
				}  
			}

		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}		
	
	/**
	 * @description : 출고진행현황 - 상품제외처리(temp table방식)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.30 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	@SuppressWarnings("unchecked")
	public String saveInquiryByTempTable(DevPilot02ReqDto paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        List<DevPilot02ResDto> saveList = paramDto.getSaveList(); // 저장리스트
        //String avc_DCCODE = paramDto.getAvc_DCCODE(); // 센터코드
        
        log.info("▶saveList.size->{}",saveList);
        //log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
		/*START.Temp Table Insert*/
		commonDao.insert(SERVICEID_PREFIX + "deleteTampTable", paramDto); 
		
		int bulkCnt =0;
		List<DevPilot02ResDto> list = new ArrayList<DevPilot02ResDto>();
		for (DevPilot02ResDto dto : paramDto.getSaveList()) { 
        	bulkCnt++;
        	
        	dto.setPackagename(PAKAGE_NAME); // 패키지명
        	log.info("▶dto->{}",dto);
        	
        	list.add(dto);
            if (bulkCnt % 100 == 0) {
            	commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", list); 
            	list.clear();
            }
        }
        log.info("▶list.size : {}", list.size());
        if (list.size() > 0) { // 나머지
        	commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", list); 
        }
        /*END.Temp Table Insert*/		
        
        
        /*START.PAKAGE 호출*/
		// PKG 파라마터 세팅 - 공통(1/4)
        DevPilot02ResDto dto = new DevPilot02ResDto();
		ProcedureParametersFactory.initParamDto(paramDto,dto, PAKAGE_NAME);
        
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE" ,"PROCESSTYPE"  ,"TEMPTABLETYPE"     ,"DCCODE"         ,"STORERKEY"};
		Object[] valueList = {PAKAGE_NAME ,"MS_EXCEPTION" ,"SY_PROCESSTEMP_WD" ,dto.getGDccode() ,dto.getGStorerkey()};
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
			log.error("▶출고진행현황 - 상품제외처리 시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"출고진행현황-상품제외"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		 /*END.PAKAGE 호출*/
        
        
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}		
	

	/**
	 * @description : 마스킹 기능을 구현한 Method 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.14 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	public List<DevPilot02MaskResDto> getMaskingList(DevPilot02ReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMaskingList", dto);
	}	
	
}
