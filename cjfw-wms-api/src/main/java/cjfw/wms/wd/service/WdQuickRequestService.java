package cjfw.wms.wd.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.wd.dto.WdQuickRequestReqDto;
import cjfw.wms.wd.dto.WdQuickRequestResDto;
import cjfw.wms.wd.dto.WdQuickRequestResVOCDto;
import cjfw.wms.wd.entity.WdQuickRequestEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description :퀵접수(VSR)및처리  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class WdQuickRequestService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = StringUtils.uncapitalize(WdQuickRequestService.class.getSimpleName()) + ".";
	/** PAKAGE NAME - 퀵접수(VSR) 처리 */
	private static final String PAKAGE_NAME = "SPWD_VOC_QUICK_ORDER";	
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;	
	/** 공통.service */
	private final CmCommonService cmCommonService;	

	/** @description : 퀵접수(VSR)및처리 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getTab1MasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getTab1MasterList", reqDto);
	}

	/** @description : 퀵접수(VSR)및처리 상세 조회 - VOC 퀵주문접수 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getTab2MasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getTab2MasterList", reqDto);
	}
	
	/** @description : 퀵접수(VSR)및처리 퀵주문접수 상세 조회 - VOC 집하지 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getTab2DetailList01(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getTab2DetailList01", reqDto);
	}	
	
	/** @description : 퀵접수(VSR)및처리 퀵주문접수 조회 - VOC 집하지 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getTab3DetailList01(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getTab3DetailList01", reqDto);
	}	
	
	
	/** @description : 퀵접수(VSR)및처리 저장 - 센터 접수 처리(VOC)
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public WdQuickRequestReqDto saveMasterList01(WdQuickRequestReqDto paramDto) {
		WdQuickRequestReqDto resultDto = new WdQuickRequestReqDto();
        int iCnt = 0; // 등록 건수
        int uCnt = 0; // 수정 건수
        int dCnt = 0; // 삭제 건수
        int rv = 0; // 반환 값

	    WdQuickRequestReqDto reqDto = ModelMapperUtil.map(paramDto, WdQuickRequestReqDto.class);
        List<WdQuickRequestResDto> saveList = reqDto.getSaveList01();
        log.info("▶saveList01.size->{}", saveList);
        
        // 접수처리(1/2) - 센터접수번호 채번 - 여러개의 VOC번호를 하나의 센터접수번호로 처리하기 위함
        String rcptNo = getRcptNo(reqDto).get(0).getRcptNo();
        reqDto.setRcptNo(rcptNo); 
        
        for (WdQuickRequestResDto dto : saveList) {
        	if(CanalFrameConstants.INSERT.equals(dto.getRowStatus())) {
        		dto.setRcptNo(rcptNo); // 센터접수번호 세팅(여러개의 VOC번호를 하나의 센터접수번호로 처리하기 위함)
        		dto.setDccode(reqDto.getFixdccode()); // 센터코드 세팅(체크를 위해)
        		
        		// START.필수입력
        		if("".equals(cjfw.core.utility.StringUtil.nvl(dto.getDccode()))) {throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"센터코드(dccode)"})); }
        		// END.필수입력
        		
            	// START.Master.체크.가능여부
                List<Map<String, Object>> chkListMap = commonDao.selectList(SERVICEID_PREFIX+"selectChkIntMaster", dto);
                String chk1 = (String) chkListMap.get(0).get("CHK1"); // 체크.접수여부
                String quickDocno = (String) chkListMap.get(0).get("QUICK_DOCNO"); // 체크.센터접수번호
                log.info("chk1(체크.접수여부):{}",chk1);
                String moreMsg  = "\n\n센터코드 : ["+dto.getDccode()+"]";
                       moreMsg += "\n상품코드   : ["+dto.getSku()+"]";
                       moreMsg += "\n상품명   : ["+dto.getSkuInfo()+"]";
                       moreMsg += "\n센터접수번호   : ["+quickDocno+"]";

                if("Y".equals(chk1)) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_059", new String[]{"[기 접수처리 완료]"}) + moreMsg ); // 처리할 수 없습니다 {0}.
                // END.Master.체크.가능여부    
                
                iCnt += commonDao.insert(SERVICEID_PREFIX + "insertMasterList", ModelMapperUtil.map(dto, userContext, WdQuickRequestEntity.class));
                log.info("▶insert iCnt:{}",iCnt);
                if (iCnt < 1) {
                    throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
                }   
        	}	
		}
        
        // 접수처리(2/2) - 여러개의 VSR 전달사항 합치기 처리
        rv = commonDao.update(SERVICEID_PREFIX + "updateMasterList01", ModelMapperUtil.map(reqDto, userContext, WdQuickRequestEntity.class));
		if (rv < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		}
		
		if ((iCnt + uCnt + dCnt) < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		} 
		
	    return resultDto;
	}		
	

	
	/** @description : 퀵접수(VSR)및처리 저장 - 운송정보/귀책정보
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public int saveMasterRespInfo(WdQuickRequestReqDto reqDto) {
		int uCnt = 0; // 수정 건수
		int rv = 0; // 반환 값
		// START.필수입력
		if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getRespDept())))   {throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"respDept"})); }// 해당 정보가 없어 처리할 수 없습니다.-{0}
		if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getRespReason()))) {throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"respReason"})); }
		if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getRespEmp())))    {throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"respEmp"})); }
		
		if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getDeliverytype())))    {throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"deliverytype"})); }
		if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getDeliveryMethod())))  {throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"deliveryMethod"})); }
		if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getArticleType())))     {throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"articleType"})); }
		if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getPayType())))         {throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"payType"})); }
		if(!"".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReservedate1()))) { // 예약일자가 입력된경우
			if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReservedate2()))) {throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"reservedate2"})); }
		}
		if(!"".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReservedate2()))) { // 예약시분가 입력된경우
			if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReservedate1()))) {throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"reservedate1"})); }
		}
		// END.필수입력               
        
        // START.귀책정보 UPDATE
		uCnt += commonDao.update(SERVICEID_PREFIX + "updateMasterResp", ModelMapperUtil.map(reqDto, userContext, WdQuickRequestEntity.class));
		// 체크.UPDATE 처리여부
		if (uCnt < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		} 	
		// END.귀책정보 UPDATE
		
		// START.API 연동 배송정보 UPDATE
		String realOpen = ContextUtil.getProperty("cf.quick.realOpen");  // realOpen.공용
		String testPrefix = "[★★★TEST데이터★★★]"; // 개발 및 테스트 시 테스트데이터 프리픽스면 api전달시 메모앞에 붙이면 접수하지 않음
		// 운영기이고 quick api오픈을 했다면 테스트데이터 프리픽스 제거
		if("prd".equals(System.getProperty("spring.profiles.active","local")) && "Y".equals(realOpen)) { 
			testPrefix = ""; 
		}		
		
		// 오픈 전 [★★★TEST데이터★★★]를 세팅하면 업체에서 접수처리 안함 - 개발 및 테스트 시 테스트데이터 프리픽스면 api전달시 메모앞에 붙이면 접수하지 않음
		reqDto.setTestPrefix(testPrefix); // 테스트데이터 프리픽스 세팅
        rv = commonDao.update(SERVICEID_PREFIX + "updateMasterList02", ModelMapperUtil.map(reqDto, userContext, WdQuickRequestEntity.class));
		if (rv < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		}
		// END.API 연동 배송정보 UPDATE
		
		uCnt++;
		
		return uCnt;
	}
	
	
	/** @description : 퀵접수(VSR)및처리 저장 - 집하지처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public WdQuickRequestReqDto saveDetailListDelivery01(WdQuickRequestReqDto paramDto) {
		WdQuickRequestReqDto resultDto = new WdQuickRequestReqDto();
		int uCnt = 0; // 수정 건수
		int dCnt = 0; // 삭제 건수
		
	    WdQuickRequestReqDto reqDto = ModelMapperUtil.map(paramDto, WdQuickRequestReqDto.class);
        List<WdQuickRequestResVOCDto> saveList = reqDto.getSaveList02();
        log.info("▶saveList02.size->{}", saveList);
        
        
        // 집하지 처리(1/2)
        for (WdQuickRequestResVOCDto dto : saveList) {
        	// START.Master.체크.가능여부
            List<Map<String, Object>> chkListMap = commonDao.selectList(SERVICEID_PREFIX+"selectChkInsMaster", dto);
            String chk1 = (String) chkListMap.get(0).get("CHK1"); // 체크.중복여부
            log.info("chk1(체크.진행상태):{}",chk1);
            String moreMsg  = "\n\n센터코드 : ["+dto.getDccode()+"]";
                   moreMsg += "\n상품코드   : ["+dto.getSku()+"]";
                   moreMsg += "\n상품명   : ["+dto.getSkuInfo()+"]";
            if("Y".equals(chk1)) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_058", new String[]{"퀵접수"}) + moreMsg); // {0} 처리 시 중복된 데이터가 존재합니다.       
            // END.Master.체크.가능여부        	
        	
            if(CanalFrameConstants.INSERT.equals(dto.getRowStatus())||CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {        	
		        // START.필수입력
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getDccode())))    {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getRcptNo())))    {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"rcptno"})); }
				
				// 필수입력값 체크 (집하지정보)                                      
				if (dto.getGthSeq() == null)                                         {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"gth_seq"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getGthCd())))     {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"gth_cd"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getGthNm())))     {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"gth_nm"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getGthAddr())))   {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"gth_addr"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getGthEmpNm())))  {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"gth_emp_nm"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getGthTel())))    {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"gth_tel"})); }
	            // END.필수입력
				
	            uCnt += commonDao.update(SERVICEID_PREFIX + "updateDetailListDelivery01", ModelMapperUtil.map(dto, userContext, WdQuickRequestEntity.class));
	            if (uCnt < 1) {
	            	throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
	            } 	
       	    } else if(CanalFrameConstants.DELETE.equals(dto.getRowStatus())) {
	            dCnt += commonDao.update(SERVICEID_PREFIX + "deleteDetailListDelivery01", ModelMapperUtil.map(dto, userContext, WdQuickRequestEntity.class));
	            if (dCnt < 1) {
	            	throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
	            } 	
   	
       	    }		
        }
        
        // 수기집하지정보 UPDATE(2/2)
        saveMasterRespInfo(reqDto);          
	    return resultDto;
	}			
	
	
	/** @description : 퀵접수(VSR)및처리 저장 - VOC 도착지 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public WdQuickRequestReqDto saveDetailListDestination01(WdQuickRequestReqDto paramDto) {
		WdQuickRequestReqDto resultDto = new WdQuickRequestReqDto();
        int iCnt = 0; // 등록 건수
        int uCnt = 0; // 수정 건수
        int dCnt = 0; // 삭제 건수

	    WdQuickRequestReqDto reqDto = ModelMapperUtil.map(paramDto, WdQuickRequestReqDto.class);
        List<WdQuickRequestResVOCDto> saveList = reqDto.getSaveList03();
        log.info("▶saveList.size->{}", saveList);
        // 도착지 처리(1/2)
        for (WdQuickRequestResVOCDto dto : saveList) {
            if(CanalFrameConstants.INSERT.equals(dto.getRowStatus())||CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {        	
		        // START.필수입력
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getDccode())))       {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getRcptNo())))       {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"rcptno"})); }
				// 필수입력값 체크 (도착지정보)                                      
				if (dto.getToSeq() == null)                                             {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"toSeq"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getMgmtCustname()))) {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"MgmtCustname"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getAddress())))      {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"address"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getPhone())))        {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"phone"})); }
	            // END.필수입력
	            
	            uCnt += commonDao.update(SERVICEID_PREFIX + "updateDetailListDestination01", ModelMapperUtil.map(dto, userContext, WdQuickRequestEntity.class));
	            if (uCnt < 1) {
	            	throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
	            } 	
       	    }  
        }
        
        // 수기집하지정보 UPDATE(2/2)
        saveMasterRespInfo(reqDto);     
        
		if ((iCnt + uCnt + dCnt) < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		} 
        
	    return resultDto;
	}		
	
	/** @description : 퀵접수(VSR)및처리 저장 - 센터접수 저장 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public WdQuickRequestReqDto saveMasterListCenterRecept01(WdQuickRequestReqDto paramDto) {
		WdQuickRequestReqDto resultDto = new WdQuickRequestReqDto();
        int iCnt = 0; // 등록 건수
        int uCnt = 0; // 수정 건수
        int dCnt = 0; // 삭제 건수

	    WdQuickRequestReqDto reqDto = ModelMapperUtil.map(paramDto, WdQuickRequestReqDto.class);
        List<WdQuickRequestResDto> saveList = reqDto.getSaveList01();
        log.info("▶saveList01.size->{}", saveList);
        
        String rcptNo = "";
        for (WdQuickRequestResDto dto : saveList) {
        	if(rcptNo.equals(dto.getRcptNo())) { // VOC번호가 동일한경우 skip - 여러개의 VOC를 한개의 센터	접수번호로 처리하기 위함
        		log.info(">>>중복처리 skip rcptNo:{}",rcptNo);
        	} else {
            	if(CanalFrameConstants.DELETE.equals(dto.getRowStatus())) {
                	// START.Master.체크.가능여부
                    List<Map<String, Object>> chkListMap = commonDao.selectList(SERVICEID_PREFIX+"selectChkDelMaster", dto);
                    String chk1 = (String) chkListMap.get(0).get("CHK1"); // 체크.삭제가능여부
                    log.info("chk1(체크.삭제가능여부):{}",chk1);
                    String moreMsg  = "\n\n센터코드 : ["+dto.getDccode()+"]";
                           moreMsg += "\n상품코드   : ["+dto.getSku()+"]";
                           moreMsg += "\n상품명   : ["+dto.getSkuInfo()+"]";

                    if(!"Y".equals(chk1)) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_059", new String[]{"[센터접수 상태 아님]"}) + moreMsg ); // 처리할 수 없습니다 {0}.
                    // END.Master.체크.가능여부    
                    
        	        dCnt += commonDao.delete(SERVICEID_PREFIX + "deleteMasterCenterRecept", ModelMapperUtil.map(dto, userContext, WdQuickRequestEntity.class));
                    if (dCnt < 1) {
                        throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
                    }   
            	}	
        	}
        	rcptNo = dto.getRcptNo();
		}
        
		if ((iCnt + uCnt + dCnt) < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		} 
		
	    return resultDto;
	}			
	
	
	/** @description : 센터접수번호 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
	*/
	public List<WdQuickRequestResDto> getRcptNo(WdQuickRequestReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getRcptNo", reqDto);
	}		
	
	/** @description : serialkey 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
	*/
	public List<WdQuickRequestResDto> getSerialkey(WdQuickRequestReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getSerialkey", reqDto);
	}		
	
	/** 
	 * @description : 퀵접수(VSR)및처리 퀵주문접수 API 전문 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getOrderRequest01(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getOrderRequest01", reqDto);
	}	
	
	/** 
	 * @description : 퀵접수(VSR)및처리 퀵주문접수 API 전문 결과 update Method
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	 */
	public int updateOrderRequest01(WdQuickRequestResDto reqDto) {
		WdQuickRequestEntity entity = ModelMapperUtil.map(reqDto, userContext, WdQuickRequestEntity.class);
		return commonDao.update(SERVICEID_PREFIX + "updateOrderRequest01", entity);
	}	
	
	
	/** @description : 퀵접수(VSR)및처리 상세 조회 - 수기퀵주문접수 List Method - getTab2MasterList공용사용
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getTab3MasterList(T reqDto) {
		// getTab2MasterList 공용사용
		return commonDao.selectList(SERVICEID_PREFIX+"getTab2MasterList", reqDto); 
	}	
	
	
	/*************************************************3번째 Tab*****************************************
	 * 수기 센터접수
	 *************************************************3번째 Tab*****************************************/
	
	/** @description : 퀵접수(VSR)및처리 저장 - 수기 센터접수 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public WdQuickRequestReqDto saveMasterListCenterRecept02(WdQuickRequestReqDto paramDto) {
		WdQuickRequestReqDto resultDto = new WdQuickRequestReqDto();
        int iCnt = 0; // 등록 건수
        int uCnt = 0; // 수정 건수
        int dCnt = 0; // 삭제 건수

	    WdQuickRequestReqDto reqDto = ModelMapperUtil.map(paramDto, WdQuickRequestReqDto.class);
        List<WdQuickRequestResDto> saveList = reqDto.getSaveList01();
        log.info("▶saveList01.size->{}", saveList);
        
        for (WdQuickRequestResDto dto : saveList) {
            if(CanalFrameConstants.INSERT.equals(dto.getRowStatus())||CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {
            	/********************************************************************
            	 * 운송정보/귀책정보 처리(1/2)
            	 ********************************************************************/            	
		        // 센터접수번호 채번 - 수기는 1:1로 채번
	            if(CanalFrameConstants.INSERT.equals(dto.getRowStatus())) {
	                reqDto.setRcptNo(getRcptNo(reqDto).get(0).getRcptNo());         
	            	dto.setRcptNo(reqDto.getRcptNo()); // 센터접수번호 세팅       		 
	            	dto.setSerialkey(getSerialkey(reqDto).get(0).getSerialkey()); // serialkey 채번
	            	//
	            	reqDto.setSerialkey(dto.getSerialkey()); // 운송정보/귀책정보 UPDATE를 위해 세팅
	            } 
	            
	            // START.필수입력
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getDccode())))        {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getRcptNo())))        {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"rcptno"})); }
				// 요청자 정보
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getReqDepartment()))) {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"reqDepartment"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getReqId())))         {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"reqId"})); }
				// 필수입력값 체크 (배송정보)
				if ("".equals(StringUtil.nvl(dto.getDeliverytype())))                    { throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"deliverytype"})); }
				if ("".equals(StringUtil.nvl(dto.getDeliveryMethod())))                  { throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"delivery_method"})); }
				if ("".equals(StringUtil.nvl(dto.getArticleType())))                     { throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"article_type"})); }
				if ("".equals(StringUtil.nvl(dto.getPayType())))                         { throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"pay_type"})); }
	            // END.필수입력
	            
	            uCnt += commonDao.update(SERVICEID_PREFIX + "mergeMasterCenterRecept02", ModelMapperUtil.map(dto, userContext, WdQuickRequestEntity.class));
	            if (uCnt < 1) {
	            	throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
	            } 	
	            
	        	/********************************************************************
	        	 * 운송정보/귀책정보 UPDATE(2/2)
	        	 ********************************************************************/	            
	            saveMasterRespInfo(reqDto);   
       	    } else if(CanalFrameConstants.DELETE.equals(dto.getRowStatus())) {
    	        dCnt += commonDao.delete(SERVICEID_PREFIX + "deleteMasterCenterRecept", ModelMapperUtil.map(dto, userContext, WdQuickRequestEntity.class));
                if (dCnt < 1) {
                    throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
                }         		 
		    }	 
        }
        
		if ((iCnt + uCnt + dCnt) < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		} 
		
	    return resultDto;
	}		
	
	/** @description : 퀵접수(VSR)및처리 저장 - 수기 집하지처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public WdQuickRequestReqDto saveDetailListDelivery02(WdQuickRequestReqDto paramDto) {
		WdQuickRequestReqDto resultDto = new WdQuickRequestReqDto();
		int uCnt = 0; // 수정 건수

	    WdQuickRequestReqDto reqDto = ModelMapperUtil.map(paramDto, WdQuickRequestReqDto.class);
        List<WdQuickRequestResVOCDto> saveList = reqDto.getSaveList02();
        log.info("▶saveList02.size->{}", saveList);
        
        // 집하지 처리(1/2)
        for (WdQuickRequestResVOCDto dto : saveList) {
            if(CanalFrameConstants.INSERT.equals(dto.getRowStatus())||CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {        	
		        // START.필수입력
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getRcptNo())))    {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"rcptno"})); }
				// 필수입력값 체크 (집하지정보)                                      
				if (dto.getGthSeq() == null)                                         {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"gth_seq"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getGthCd())))     {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"gth_cd"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getGthNm())))     {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"gth_nm"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getGthAddr())))   {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"gth_addr"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getGthEmpNm())))  {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"gth_emp_nm"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getGthTel())))    {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"gth_tel"})); }
	            // END.필수입력
	            
	            //uCnt += commonDao.update(SERVICEID_PREFIX + "updateDetailListDelivery02", ModelMapperUtil.map(dto, userContext, WdQuickRequestEntity.class));
	            uCnt += commonDao.update(SERVICEID_PREFIX + "mergeMasterCenterRecept01", ModelMapperUtil.map(dto, userContext, WdQuickRequestEntity.class));
	            if (uCnt < 1) {
	            	throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
	            } 	
       	    }  
        }
        
        // 수기집하지정보 UPDATE(2/2)
        saveMasterRespInfo(reqDto);          
	    return resultDto;
	}		
	
	/** @description : 퀵접수(VSR)및처리 저장 - 수기 도착지 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public WdQuickRequestReqDto saveDetailListDestination02(WdQuickRequestReqDto paramDto) {
		WdQuickRequestReqDto resultDto = new WdQuickRequestReqDto();
        int iCnt = 0; // 등록 건수
        int uCnt = 0; // 수정 건수
        int dCnt = 0; // 삭제 건수

	    WdQuickRequestReqDto reqDto = ModelMapperUtil.map(paramDto, WdQuickRequestReqDto.class);
        List<WdQuickRequestResVOCDto> saveList = reqDto.getSaveList03();
        log.info("▶saveList.size->{}", saveList);
        // 도착지 처리(1/2)
        for (WdQuickRequestResVOCDto dto : saveList) {
            if(CanalFrameConstants.INSERT.equals(dto.getRowStatus())||CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {        	
		        // START.필수입력
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getDccode())))       {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getRcptNo())))       {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"rcptno"})); }
				// 필수입력값 체크 (도착지정보)                                      
				if (dto.getToSeq() == null)                                             {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"toSeq"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getMgmtCustname()))) {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"MgmtCustname"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getAddress())))      {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"address"})); }
				if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getPhone())))        {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"phone"})); }
	            // END.필수입력
	            
	            uCnt += commonDao.update(SERVICEID_PREFIX + "updateDetailListDestination02", ModelMapperUtil.map(dto, userContext, WdQuickRequestEntity.class));
	            if (uCnt < 1) {
	            	throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
	            } 	
       	    }  
        }
        
        // 수기집하지정보 UPDATE(2/2)
        saveMasterRespInfo(reqDto);     
        
		if ((iCnt + uCnt + dCnt) < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		} 
        
	    return resultDto;
	}		


}
