package cjfw.wms.ms.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.ApiClient;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.edms.EdmsFileUploader;
import cjfw.core.exception.UserHandleException;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderEdms;
import cjfw.core.file.FileUploaderNew;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmCustPopupReqDto;
import cjfw.wms.cm.dto.CmCustPopupResDto;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.ApiAuthUtil;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.ms.dto.MsCustDeliveryInfoFileUploadReqDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoGpsReqDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoPopResDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoReqDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoResDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoUploadFileResDto;
import cjfw.wms.ms.entity.MsCustDeliveryInfoEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.06.19 
 * @description : 기준정보 > 거래처기준정보 > GPS좌표등록 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCustDeliveryInfoService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCustDeliveryInfoService.";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	private final FileUploaderEdms fileUploaderEdms;
	private final EdmsFileUploader edmsFileUploader;
	private final FileUploaderNew fileUploaderNew;
	
	private final UserContext context;
	
	@Autowired
	private ApiClient apiClient;
	
	/**
	 * @description : GPS 좌표등록 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public Page<MsCustDeliveryInfoResDto> getGpsMasterList(MsCustDeliveryInfoReqDto dto) {
		Page<MsCustDeliveryInfoResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getGpsMasterList", dto, dto);
		return result;
	}
	
	/**
	 * @description : GPS 좌표 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.19 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<MsCustDeliveryInfoResDto> saveDeliveryInfo(MsCustDeliveryInfoReqDto dto) {
		List<MsCustDeliveryInfoResDto> result;
		
		// 임시 테이블에 이전 처리했던 기록들을 삭제
		commonDao.delete(SERVICEID_PREFIX + "deleteGpsTempTable", dto);
		
		// 임시 테이블에 100개씩 끊어서 등록
		int bulkCnt = 0;
		List<MsCustDeliveryInfoGpsReqDto> list = new ArrayList<MsCustDeliveryInfoGpsReqDto>();
		for (MsCustDeliveryInfoGpsReqDto item : dto.getGpsList()) { 
        	bulkCnt++;
        	
        	item.setProcesstype(dto.getProcesstype());
        	
        	list.add(item);
            if (bulkCnt % 100 == 0) {
            	commonDao.insert(SERVICEID_PREFIX + "insertGpsTempTable", list); 
            	list.clear();
            }
        }
		// 나머지
        if (bulkCnt > 0) {
        	commonDao.insert(SERVICEID_PREFIX + "insertGpsTempTable", list); 
        }
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(dto, dto, "SPMS_CUSTDLVINFO");
		
		// PKG 파라마터 세팅 및 실행 - 업무(2/4)
		String[] keyList = {"PROCEDURE","PROCESSTYPE","PROCESSCREATOR","SPID"};
		Object[] valueList = {"SPMS_CUSTDLVINFO", dto.getProcesstype(), dto.getGUserId(), dto.getGSpid()};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		
		
		// 저장시 조회 parameter set
		String command = dto.getAvc_COMMAND();
		if(command.equals("DATAUPLOAD")) {
			// 저장시점 set
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
	        String adddateFrom = now.format(formatter);
			
			dto.setEditwho(dto.getGUserId());
			dto.setAdddateFrom(adddateFrom);
		}else {
			dto.setAvc_EXECUTEMODE("NOCOMMIT");	
		}
		commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, dto);
		
		// 프로시저 OUT Parameter(3/4)
		String resultCode    = (String)dto.getResultCode();
		String resultMessage = (String)dto.getResultMessage();
		// 프로시저 Exception 처리(4/4)
		if (!resultCode.equals("0")) {
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"GPS 좌표등록 관리"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		

		if(command.equals("DATAUPLOAD")) {
			result = commonDao.selectList(SERVICEID_PREFIX + "getGpsMasterList", dto); 
		}else {
			List<MsCustDeliveryInfoResDto> editResult = new ArrayList<MsCustDeliveryInfoResDto>();
			//custkey 중복확인
			Set<String> uniqueCustKeys = new HashSet<>();
			Set<String> duplicatedCustKeys = new HashSet<>();
			
			List<MsCustDeliveryInfoResDto> selectResult = commonDao.selectList(SERVICEID_PREFIX + "getDataPopExcelUploadGpsResult", dto);
			for(MsCustDeliveryInfoResDto resultDto : selectResult) {
				
				String custkey = resultDto.getCustkey();
				if (uniqueCustKeys.contains(custkey)) {
		            duplicatedCustKeys.add(custkey);
		            // 이 시점에서는 현재 데이터에 중복 오류를 즉시 설정하고,
		            // 이전 데이터들은 루프가 끝난 후 한 번에 업데이트
		            resultDto.setProcessYn("E");
		            resultDto.setProcessMsg("중복된 고객코드입니다.");
		        } else {
		            // 처음 등장한 CUSTKEY는 uniqueCustKeys에 추가
		            uniqueCustKeys.add(custkey);
		        }
				
				if((resultDto.getLatitude() == null || resultDto.getLatitude().isEmpty()) || (resultDto.getLongitude() == null || resultDto.getLongitude().isEmpty())) {
					resultDto.setAddressChk("Y");
					resultDto.setProcessYn("E");
					resultDto.setProcessMsg("존재하지 않는 주소값 입니다.");
				}
				editResult.add(resultDto);
			}
			// 중복이 발견된 CUSTKEY에 대해 이전에 정상 처리된 건도 오류 처리
		    if (!duplicatedCustKeys.isEmpty()) {
		        for (MsCustDeliveryInfoResDto editDto : editResult) {
		            // 중복 리스트에 포함된 CUSTKEY인 경우 (첫 번째 건 포함)
		            if (duplicatedCustKeys.contains(editDto.getCustkey()) && !"E".equals(editDto.getProcessYn())) {
		                // 이미 주소 오류가 아닌 경우에만 중복 오류를 설정
		            	editDto.setProcessYn("E");
		            	editDto.setProcessMsg("중복된 고객코드입니다.");
		            }
		        }
		    }
			
			result = editResult;
		}
		return result;
	}
	
	/**
	 * @description : 거래처 정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public Page<MsCustDeliveryInfoResDto> getMasterList(MsCustDeliveryInfoReqDto dto) {
		Page<MsCustDeliveryInfoResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getMasterList", dto, dto);
		return result;
	}
	
	/**
	 * @description : 거래처 정보 조회 (단건) & 협력사 입고검수결과 수신자 마스터정보 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public MsCustDeliveryInfoResDto getMaster(MsCustDeliveryInfoReqDto dto) {		
		MsCustDeliveryInfoResDto result = new MsCustDeliveryInfoResDto();
		result.setMasterDto(commonDao.selectOne(SERVICEID_PREFIX + "getMaster", dto));
		return result;
	}
	
	/**
	 * @description : 거래처 정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCustDeliveryInfoResDto> getMasterPrintList(MsCustDeliveryInfoReqDto dto) {		
		List<MsCustDeliveryInfoResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterPrintList", dto);		
		return result;
	}
	
	/**
	 * @description : 거래처 배송정보 저장(목록조회 화면)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<MsCustDeliveryInfoReqDto> dtoList) {
		if(dtoList != null) {
			for (MsCustDeliveryInfoReqDto dto : dtoList) {
				MsCustDeliveryInfoEntity entity = ModelMapperUtil.map(dto, userContext, MsCustDeliveryInfoEntity.class);
				commonDao.selectOne(SERVICEID_PREFIX +"updateMasterList", entity);
				if(entity.getErrCode() != 0){
					throw new UserHandleException(""+"에러코드 : "+ entity.getErrCode() + ", 에러메세지 : " + entity.getErrMsg());
				}
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCustDeliveryInfoResDto> getValidateExcelList(List<MsCustDeliveryInfoReqDto> dtoList) {
		List<MsCustDeliveryInfoResDto> result = new ArrayList<MsCustDeliveryInfoResDto>();
		if(dtoList != null) {
			result = commonDao.selectList(SERVICEID_PREFIX + "getValidateExcelList", dtoList);
			//custkey 중복확인
			Set<String> uniqueCustKeys = new HashSet<>();
			Set<String> duplicatedCustKeys = new HashSet<>();
			for(MsCustDeliveryInfoResDto resultDto : result) {
				
				String custkey = resultDto.getCustkey();
				if (uniqueCustKeys.contains(custkey)) {
		            duplicatedCustKeys.add(custkey);
		            // 이 시점에서는 현재 데이터에 중복 오류를 즉시 설정하고,
		            // 이전 데이터들은 루프가 끝난 후 한 번에 업데이트
		            resultDto.setDuplicateChk("N");
		        } else {
		            // 처음 등장한 CUSTKEY는 uniqueCustKeys에 추가
		            uniqueCustKeys.add(custkey);
		        }				
			}
			// 중복이 발견된 CUSTKEY에 대해 이전에 정상 처리된 건도 오류 처리
		    if (!duplicatedCustKeys.isEmpty()) {
		        for (MsCustDeliveryInfoResDto editDto : result) {
		            // 중복 리스트에 포함된 CUSTKEY인 경우 (첫 번째 건 포함)
		            if (duplicatedCustKeys.contains(editDto.getCustkey()) && !"N".equals(editDto.getProcessYn())) {
		                // 이미 오류가 아닌 경우에만 중복 오류를 설정
		            	editDto.setDuplicateChk("N");
		            }
		        }
		    }
		}
		return result;
	}
     
     /**
      * @description : 거래처 배송정보 저장(상세조회 화면)
      * @issues :<pre> 
      * ----------------------------------------------------------- 
      * DATE       AUTHOR                MAJOR_ISSUE 
      * ----------------------------------------------------------- 
      * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
      */
     @Transactional
    public String saveMaster(MsCustDeliveryInfoReqDto reqDto) {

        log.info("begin reqDto : {}", reqDto.toString());
         
        MsCustDeliveryInfoEntity entity = ModelMapperUtil.map(reqDto, userContext, MsCustDeliveryInfoEntity.class);
        MsCustDeliveryInfoResDto srmDto = commonDao.selectOne(SERVICEID_PREFIX +"getSrmCustdlvinfo_API", reqDto);
        
        if( ObjectUtils.isNotEmpty(entity)) {
            
            log.info("updateMasterCust entity : {}", entity.toString());
            commonDao.selectOne(SERVICEID_PREFIX +"updateMasterCust", entity);
            if(entity.getErrCode() != 0){
                throw new UserHandleException(entity.getErrMsg());
            }
            
            commonDao.selectOne(SERVICEID_PREFIX +"updateMaster", entity);
            if(entity.getErrCode() != 0){
                throw new UserHandleException(entity.getErrMsg());
            }

            if (entity.getSerialkeyHis() != null && !entity.getSerialkeyHis().isEmpty()) {
              
                commonDao.selectOne(SERVICEID_PREFIX + "updateConfirm", entity);
                // 에러 코드 체크 로직
                if (entity.getErrCode() != 0) {
                    throw new UserHandleException(entity.getErrMsg());
                }
            }
        }
        
        // CRM 배송정보 API 호출. (MD002)
        this.sendDlivInfoToCrm(reqDto);   // 배차테스를 위해 임시로 주석처리
        
        // SRM 라벨출력유형 API 호출
        if("Y".equals(srmDto.getSrmSendFlag())){
            this.sendLabelPrintToSrm(srmDto);
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
     /**
      * @description : srm 시스템 연동 api  
      * @issues :<pre> 
      * ----------------------------------------------------------- 
      * DATE       AUTHOR                MAJOR_ISSUE 
      * ----------------------------------------------------------- 
      * 2026.02.19 jun (kthis77@cj.net) 생성(refactor) </pre>
      */
    public void sendLabelPrintToSrm(MsCustDeliveryInfoResDto srmDto) {
        
        try {
            String id = "receivePickOrderPlan.do";
            String url = ContextUtil.getProperty("cf.api.srm.url") + id;
            String username = ContextUtil.getProperty("cf.api.srm.username");
            String password = ContextUtil.getProperty("cf.api.srm.password");
            
            String interfaceAuthKey = ApiAuthUtil.createBasicAuthHeader(username, password);
            
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("dlvrplcCd", srmDto.getCustkey());
            paramsMap.put("delYn", srmDto.getDelYn());
            
            JSONObject response = apiClient.callApi(url, null, interfaceAuthKey, paramsMap, null);
            
            if("F".equals(response.optString("err_cd"))){
                 throw new RuntimeException("SRM API 연동 오류: " + response.optString("err_msg_ctt"));
            }
        } catch (IOException | ParseException | JSONException e) {
            log.error("try catch exception message : {}",e.getMessage());
            e.printStackTrace();
        }
      
    }
    
	/**
	 * @description : CRM 배송 정보 API 호출 (MD002)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.19 jun (kthis77@cj.net) 생성 </pre>
	 */
    private void sendDlivInfoToCrm(MsCustDeliveryInfoReqDto saveDto) {
        // CRM 배송정보 본테이블 저장 처리. LOGISONE.CRM_CUSTDLVINFO
        // dto : 화면상의 tobe 데이터.
        Map<String, Object> crmMap = null;
        List<HashMap<String,Object>> fileList = null;
        if (ObjectUtils.isNotEmpty(saveDto)) {

            crmMap = this.settingCrmParam(saveDto); // crm 연동시 map 으로 보내야 해서 생성.
//            log.info("mergeCrmDlvInfo crmMap : {}", crmMap.toString());
            commonDao.update(SERVICEID_PREFIX +"mergeCrmDlvInfo", saveDto);
            if(crmMap.get("ERRCODE") != null && !"0".equals(crmMap.get("ERRCODE").toString())){
                throw new UserHandleException(crmMap.get("ERRMSG").toString());
            }
            
            /* CRM 배송 정보 I/F 확정 처리. */
//            log.info("updateCrmDlvInfoHis saveDto : {}", saveDto.toString());
            commonDao.update(SERVICEID_PREFIX +"updateCrmDlvInfoHis", saveDto);
            if(crmMap.get("ERRCODE") != null && !"0".equals(crmMap.get("ERRCODE").toString())){
                throw new UserHandleException(crmMap.get("ERRMSG").toString());
            }
            
            Map paramsMap = (Map) commonDao.selectOne(SERVICEID_PREFIX +"getCrmCustdlvinfo_API", saveDto);
            fileList = commonDao.selectList(SERVICEID_PREFIX+"getFileData_API", saveDto);
            
            // CRM 배송정보 파일 전송 플래그 변경
            commonDao.update(SERVICEID_PREFIX +"updateFileSendYn", saveDto);
            
            String id = "MD002";
            String url = ContextUtil.getProperty("cf.api.crm.url") + id;
            String interfaceId = ContextUtil.getProperty("cf.api.crm.interfaceId") + id;
            String interfaceAuthKey = ContextUtil.getProperty("cf.api.crm.interfaceAuthKey");
            
            try {
                JSONObject response = apiClient.callApi(url, interfaceId , interfaceAuthKey , paramsMap, fileList);
                
                if("F".equals(response.optString("err_cd"))){
                    throw new RuntimeException("CRM API 연동 오류: " + response.optString("err_msg_ctt"));
                }
            } catch (IOException | ParseException | JSONException e) {
                log.error("try catch exception message : {}",e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @description : 화면에서 보낸 crm 정보를 연동 할때 사용 할 map 형태로 변경한다.
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.02.19 jun (kthis77@cj.net) 생성 </pre>
     */
    public Map<String, Object> settingCrmParam(MsCustDeliveryInfoReqDto popDto) {
        Map<String, Object> crmMap = new HashMap<>();
        
        crmMap.put("CUSTKEY", popDto.getCustkey());
        crmMap.put("KEYTYPE", popDto.getKeytype());
        crmMap.put("CUSTNAME", popDto.getCustname());
        crmMap.put("CUSTTYPE", popDto.getCusttype());
        
        crmMap.put("ADDRESSMATCHYN", popDto.getAddressmatchyn());
        crmMap.put("ARRIVALADDRESS", popDto.getTruthaddress1());  // CRM 테이블 컬럼명은 ARRIVALADDRESS 이지만, 실제로는 도로명 주소를 의미하는 데이터이므로, MsCustDlvInfoHisPopupResDto의 필드명은 truthaddress1로 명명함.
        crmMap.put("ARRIVALDETAILADDRESS", popDto.getTruthaddress2());  // CRM 테이블 컬럼명은 ARRIVALDETAILADDRESS 이지만, 실제로는 상세주소를 의미하는 데이터이므로, MsCustDlvInfoHisPopupResDto의 필드명은 truthaddress2로 명명함.
        crmMap.put("ARRIVALPOSTALCODE", popDto.getTruthzipcode());  // CRM 테이블 컬럼명은 ARRIVALPOSTALCODE 이지만, 실제로는 우편번호를 의미하는 데이터이므로, MsCustDlvInfoHisPopupResDto의 필드명은 truthzipcode로 명명함.
        crmMap.put("FTFINSPECTIONYN", popDto.getFaceinspect()); // CRM 테이블 컬럼명은 FTFINSPECTIONYN 이지만, 실제로는 대면검수 여부를 의미하는 데이터이므로, MsCustDlvInfoHisPopupResDto의 필드명은 faceinspect로 명명함.
        crmMap.put("INSPECTIONWORKERPHONE", popDto.getInspectionworkerphone());
        crmMap.put("INSPECTORPRINTYN", popDto.getInspectorprintyn());
        crmMap.put("PARKINGHEIGHT", popDto.getParkingheight());
        crmMap.put("KEYDETAIL", popDto.getKeydetail());
        crmMap.put("DELIVERYREQUESTTIMESTART", popDto.getReqdlvtime2From());// CRM 테이블 컬럼명은 DELIVERYREQUESTTIMESTART 이지만, 실제로는 배송요청 시작시간을 의미하는 데이터이므로, MsCustDlvInfoHisPopupResDto의 필드명은 reqdlvtime2From으로 명명함.
        crmMap.put("DELIVERYREQUESTTIMEEND", popDto.getReqdlvtime2To());    // CRM 테이블 컬럼명은 DELIVERYREQUESTTIMEEND 이지만, 실제로는 배송요청 종료시간을 의미하는 데이터이므로, MsCustDlvInfoHisPopupResDto의 필드명은 reqdlvtime2To으로 명명함.
        crmMap.put("DELIVERYAVAILABLETIME", popDto.getDeliveryavailabletime());
        crmMap.put("BUILDINGOPENTIME", popDto.getBuildingopentime());
        crmMap.put("MOVEMENTENTRY", popDto.getAccessway()); // CRM 테이블 컬럼명은 MOVEMENTENTRY 이지만, 실제로는 출입구 방식을 의미하는 데이터이므로, MsCustDlvInfoHisPopupResDto의 필드명은 accessway로 명명함.
        crmMap.put("GOODSLOCATIONFROZEN", popDto.getFreezeplace());     // CRM 테이블 컬럼명은 GOODSLOCATIONFROZEN 이지만, 실제로는 냉동고 위치를 의미하는 데이터이므로, MsCustDlvInfoHisPopupResDto의 필드명은 freezeplace로 명명함.
        crmMap.put("GOODSLOCATIONREFRIG", popDto.getColdplace());       // CRM 테이블 컬럼명은 GOODSLOCATIONREFRIG 이지만, 실제로는 냉장고 위치를 의미하는 데이터이므로, MsCustDlvInfoHisPopupResDto의 필드명은 coldplace로 명명함.
        crmMap.put("GOODSLOCATIONROOM", popDto.getHtemperature()); // CRM 테이블 컬럼명은 GOODSLOCATIONROOM 이지만, 실제로는 상온보관 위치를 의미하는 데이터이므로, MsCustDlvInfoHisPopupResDto의 필드명은 htemperature로 명명함.
        crmMap.put("RETURNLOCATION", popDto.getLoadplace2()); // CRM 테이블 컬럼명은 RETURNLOCATION 이지만, 실제로는 반품장소를 의미하는 데이터이므로, MsCustDlvInfoHisPopupResDto의 필드명은 loadplace2로 명명함.
        crmMap.put("INITREQUESTDT", popDto.getInitrequestdt());
        crmMap.put("INITREQUESTTIMESTART", popDto.getInitrequesttimestart());
        crmMap.put("INITREQUESTTIMEEND", popDto.getInitrequesttimeend());
        crmMap.put("INITFTFINSPECTIONYN", popDto.getInitftfinspectionyn());
        crmMap.put("INITDELIVERYCONTACT", popDto.getInitdeliverycontact());
        crmMap.put("INITDELIVERYDESC", popDto.getInitdeliverydesc());
        crmMap.put("DELIVERYTYPE", popDto.getDeliverytype());
        crmMap.put("TEMPTARGET", popDto.getTemptarget());
        crmMap.put("LABELPRINTTYPE", popDto.getLabelprinttype());
        crmMap.put("DELIVERYNOTIYN", popDto.getDeliverynotiyn());
        crmMap.put("DELIVERYNOTIPHONE", popDto.getDeliverynotiphone());
        crmMap.put("DELIVERYNOTITIMESTART", popDto.getDeliverynotitimestart());
        crmMap.put("DELIVERYNOTITIMEEND", popDto.getDeliverynotitimeend());
        crmMap.put("EMPLOEENUMBER", popDto.getEmploeenumber());
        crmMap.put("CREATEDBYEMAIL", popDto.getCreatedbyemail());
        crmMap.put("STORETYPE", popDto.getStoretype());
        crmMap.put("EDMSFILEID", popDto.getEdmsfileid());
        
        crmMap.put("g_STORERKEY", popDto.getGStorerkey());
        crmMap.put("g_userId", popDto.getGUserId());
        
        
        
        return crmMap;
    }

    /**
	 * @description : 첨부파일 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCustDeliveryInfoUploadFileResDto> getUploadFileList(MsCustDeliveryInfoReqDto dto) {		
		List<MsCustDeliveryInfoUploadFileResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getUploadFileList", dto);		
		return result;
	}
	
	/**
	 * @description : 차량정보 파일 업로드
	 * @issues :<pre> 
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.13        JeongHyeongCheol (wjdgudcjf@cj.net)       생성
	 */
    public String saveCustDeliveryInfoFileUpload(List<MultipartFile> files, List<FileUpload> fileInfoList) {
		log.info("==================== files check {}", files.toString());
		log.info("==================== fileInfoList check {}", fileInfoList.toString());
		// NAS 파일 업로드
		List<FileUpload> fileUploadList = fileUploaderEdms.saveFiles(files, fileInfoList);		
		
		// EDMS 파일 업로드
		List<Map<String, Object>> rFileList = edmsFileUploader.batchFileRegister(fileUploadList, "1198");
		
		// TO-DO : 파일정보 업무 TABLE에 MERGE
		for(Map<String, Object> rFile : rFileList) {
			log.info("==================== EDMS 업로드 결과 {}", rFile.toString());
			
			MsCustDeliveryInfoFileUploadReqDto uploadDto = new MsCustDeliveryInfoFileUploadReqDto();			
			uploadDto.setDocType("100");
			uploadDto.setFileName((String) rFile.get("orgname"));
			uploadDto.setFileExtension(((String) rFile.get("orgname")).substring(((String) rFile.get("orgname")).lastIndexOf(".")+1));
			uploadDto.setFileLocation(ContextUtil.getProperty("cf.edms.tempDir") + "/" + context.getUserNo() + "/" + ((String) rFile.get("orgname")));
			uploadDto.setFileSizeBytes(String.valueOf(rFile.get("filesize")));
			uploadDto.setTransFileName((String) rFile.get("orgname"));
			uploadDto.setUploadResDocId(String.valueOf(rFile.get("docfileid")));
			uploadDto.setUploadFileName((String) rFile.get("orgname"));
			uploadDto.setUploadWorkplaceId(ContextUtil.getProperty("cf.edms.workPlaceId"));			
			//commonDao.insert(SERVICEID_PREFIX +"insertCmCarHttpDocAttach", uploadDto);
			
//			//유효기간 업데이트
//			String arrName = rFile.get("orgname").toString().split("\\.")[0];
//			String[] arrFld = arrName.split("_");
//			if(arrFld.length == 3){ 
//				MsCustDeliveryInfoFileUploadReqDto dto = new MsCustDeliveryInfoFileUploadReqDto();		
//				dto.setCarNo(arrFld[0]);				
//				dto.setGubun(arrFld[1]);
//				dto.setCarDt(arrFld[2]);
//				commonDao.update(SERVICEID_PREFIX +"updateCmCarHttpDocAttachDate", dto);
//			}
			
		}
		
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
	 * @description : 고객배송조건 첨부파일 임시저장(NAS에 저장, STATUS='N'으로 저장)
	 * @issues :<pre> 
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.06        YeoSeungCheol 		(pw6375@cj.net)       생성
	 */
    public String saveCustDeliveryInfoFileUploadTemp(List<MultipartFile> files, List<FileUpload> fileInfoList, String issueno1, String code1, String uploadLocation1) {
        // 경로 조작 (Java/JSP) 대응
        String uploadLocation = EtcUtil.setFilePathFilter(uploadLocation1);
        String code = EtcUtil.setFilePathFilter(code1);
        String issueno = EtcUtil.setFilePathFilter(issueno1);

        
        // 임시저장 전 transFileNm을 명시적으로 설정
        int idx = 0;
        for (FileUpload info : fileInfoList) {
            String src = info.getAttchFileNm();
            String ext = "";
            int dot = src != null ? src.lastIndexOf('.') : -1;
            if (dot > -1) {
                ext = src.substring(dot + 1);
            }
            String trans = "WMS_MS_CUSTDLV_" + System.currentTimeMillis() + "_" + idx + (ext.isEmpty() ? "" : "." + ext);
            info.setTransFileNm(trans);
            idx++;
        }

        // 모바일 이미지 저장 PATH와 맞추기 위해 아래 내용 수정
//        List<FileUpload> fileUploadList = fileUploaderEdms.saveFiles(files, fileInfoList);
        List<FileUpload> fileUploadList = fileUploaderNew.saveFiles(files, fileInfoList, ContextUtil.getProperty("cf.file.uploadPath") + "/custdlvinfo/" + issueno);

        for (FileUpload f : fileUploadList) {
            MsCustDeliveryInfoFileUploadReqDto uploadDto = new MsCustDeliveryInfoFileUploadReqDto();
            
            uploadDto.setDocType("100");
            uploadDto.setFileName(f.getAttchFileNm());
            String nm = f.getAttchFileNm();
            String ext = nm != null && nm.lastIndexOf('.') > -1 ? nm.substring(nm.lastIndexOf('.') + 1) : "";
            uploadDto.setFileExtension(ext);
            uploadDto.setFileLocation(f.getSavePathNm1() + "/" + f.getTransFileNm());
            uploadDto.setFileSizeBytes(String.valueOf(f.getAttchFileSz()));
            uploadDto.setTransFileName(f.getTransFileNm());
            // hashId/edms 값은 확정 시 세팅

            Map<String, Object> param = new HashMap<>();
            param.put("issueno", issueno);
            param.put("docType", uploadDto.getDocType());
            param.put("fileName", uploadDto.getFileName());
            param.put("fileExtension", uploadDto.getFileExtension());
            param.put("fileLocation", uploadDto.getFileLocation());
            param.put("fileSizeBytes", uploadDto.getFileSizeBytes());
            param.put("transFileName", uploadDto.getTransFileName());
            param.put("code", code);
            param.put("uploadLocation", uploadLocation);
            // 사용자 컨텍스트 바인딩
            param.put("gUserNo", userContext.getUserNo());
            param.put("gUserId", userContext.getUserId());
            // INSERT (STATUS='N' 기본)
            commonDao.insert(SERVICEID_PREFIX + "insertCustDlvInfoFile", param);
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
	 * @description : 고객배송조건 첨부파일 확정(EDMS 저장, STATUS='Y')
	 * @issues :<pre> 
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.06        YeoSeungCheol 		(pw6375@cj.net)       생성
	 */
    public String confirmCustDeliveryInfoFileUpload(MsCustDeliveryInfoReqDto dto) {
        if (dto == null || dto.getSerialkeys() == null || dto.getSerialkeys().isEmpty()) {
            return CanalFrameConstants.MSG_COM_SUC_CODE;
        }
        // 최대 2개 제한 체크
        Integer confirmed = commonDao.selectOne(SERVICEID_PREFIX + "selectCustDlvInfoConfirmedCnt", dto);
        if (confirmed == null) confirmed = 0;
        
        // code가 12인 건들은 4장까지 확정 가능
        if ("12".equals(dto.getCode()) && (confirmed + dto.getSerialkeys().size() > 4)) {
			throw new UserHandleException("첨부파일은 최대 4개까지 확정할 수 있습니다.");
		}
        if (!"12".equals(dto.getCode()) && (confirmed + dto.getSerialkeys().size() > 2)) {
        	// throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"첨부파일 확정(최대 2개)"}));
        	throw new UserHandleException("첨부파일은 최대 2개까지 확정할 수 있습니다.");
        }

        // 임시 목록 조회
        List<Map> tempList = commonDao.selectList(SERVICEID_PREFIX + "selectCustDlvInfoTempFilesBySerialkeys", dto);
        for (Object o : tempList) {
            @SuppressWarnings("unchecked") Map<String, Object> row = (Map<String, Object>) o;
            String serialkey = String.valueOf(row.get("SERIALKEY"));
            String fileLocation = String.valueOf(row.get("FILE_LOCATION"));
//            String transFileName = String.valueOf(row.get("TRANS_FILE_NAME"));
            String transFileName = (row.get("TRANS_FILE_NAME") != null && !row.get("TRANS_FILE_NAME").toString().trim().isEmpty())
                    ? row.get("TRANS_FILE_NAME").toString()
                    : String.valueOf(row.get("FILE_NAME"));

            // EDMS 업로드
            String dirPath = fileLocation;
            
            if (dirPath != null) {
                int p = dirPath.lastIndexOf('/') > -1 ? dirPath.lastIndexOf('/') : dirPath.lastIndexOf('\\');
                if (p > -1) {
                    dirPath = dirPath.substring(0, p);
                }
            }
            java.util.List<FileUpload> fuList = new java.util.ArrayList<>();
            FileUpload fu = new FileUpload();
            fu.setAttchFileNm(transFileName);
            fu.setSavePathNm1(dirPath);
            fu.setRowStatus("I");
            fuList.add(fu);

            java.util.List<java.util.Map<String, Object>> rList = edmsFileUploader.batchFileRegister(fuList, "1198");
            java.util.Map<String, Object> rMap0 = (rList != null && !rList.isEmpty()) ? rList.get(0) : new java.util.HashMap<>();
            String hashId = String.valueOf(rMap0.get("docfileid"));
            String orgname = String.valueOf(rMap0.get("orgname"));
            
            // 명시적으로 파일 경로 지정
            String baseUrl = ContextUtil.getProperty("cf.file.uploadPath");
            String fullUploadUrl = baseUrl + "/" + "custdlvinfo" + "/" + dto.getCustkey()+ "/" + orgname;

            Map<String, Object> param = new HashMap<>();
            param.put("issueno", dto.getIssueno());
            param.put("serialkey", serialkey);
            param.put("uploadResDocId", hashId);
            param.put("uploadFileName", orgname);
//            
            param.put("fileLocation", fullUploadUrl);
//            
            param.put("uploadWorkplaceId", ContextUtil.getProperty("cf.edms.workPlaceId"));
            param.put("gUserNo", userContext.getUserNo());
            param.put("gUserId", userContext.getUserId());
            // 필요 시 uploadLocation 생성 가능
            commonDao.update(SERVICEID_PREFIX + "updateCustDlvInfoFileConfirm", param);

            // EDMS 업로드 완료 후 NAS 임시 파일 삭제
            try {
                if (fileLocation != null && !fileLocation.isEmpty()) {
                    java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(fileLocation));
                }
            } catch (Exception ignore) {
                // ignore deletion failure
            }
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 고객배송조건 첨부 삭제(DEL_YN='Y')
     */
    public String deleteCustDeliveryInfoFileUpload(MsCustDeliveryInfoReqDto dto) {
        // 1) 대상 행 조회 (임시/확정 공통)
        Map<String, Object> param = new HashMap<>();
        param.put("issueno", dto.getIssueno());
        param.put("code", dto.getCode());
        param.put("serialkeys", dto.getSerialkeys());
        List<?> rows = commonDao.selectList(SERVICEID_PREFIX + "selectCustDlvInfoFilesBySerialkeys", param);

        // 2) STATUS='N' (임시/NAS)인 경우 물리 파일 삭제 시도
        for (Object o : rows) {
            @SuppressWarnings("unchecked") Map<String, Object> r = (Map<String, Object>) o;
            String status = String.valueOf(r.get("STATUS"));
            String fileLocation = String.valueOf(r.get("FILE_LOCATION"));
            if ("N".equalsIgnoreCase(status)) {
                if (fileLocation != null && !fileLocation.isEmpty()) {
                    try {
                        java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(fileLocation));
                    } catch (Exception ignore) {

                    }
                }
            }
        }

        // 3) 메타정보 소프트 삭제 처리 (저장/확정 공통)
        param.put("gUserNo", userContext.getUserNo());
        param.put("gUserId", userContext.getUserId());
        commonDao.update(SERVICEID_PREFIX + "updateCustDlvInfoSoftDeleteConfirmedBySerialkeys", param);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    
    /**
	 * @description : 추천실착지코드 팝업 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.12 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public Page<MsCustDeliveryInfoPopResDto> getTruthcustkeyList(MsCustDeliveryInfoReqDto dto) {		
		Page<MsCustDeliveryInfoPopResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getTruthcustkeyList", dto, dto);		
		return result;
	}
	
	
    /** @description : OO 기능을 구현한 Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.01.27 jun (kthis77@cj.net) 생성 </pre>
    */
    public List<CmCustPopupResDto> selectAddressInfoList(@Valid List<MsCustDeliveryInfoReqDto> list) {
        
        List<String> custKeyList = list.stream().map(MsCustDeliveryInfoReqDto :: getTruthcustkey).collect(Collectors.toList());
        CmCustPopupReqDto schDto = new CmCustPopupReqDto();
        schDto.setMultiSelect(String.join(",", custKeyList));
        schDto.setExpandedColumns("Y");
        
        prepareMulti(schDto);
        
        List<CmCustPopupResDto> result = commonDao.selectList("cmSearch10Service.getCustPopupPagingList", schDto);
        return result;
    }
    
    /* multiSelect → 999개 청크(codeGroups) */
    private void prepareMulti(CmCustPopupReqDto req) {
        String csv = req.getMultiSelect();
        if (csv == null || csv.isBlank()) return;

        List<String> items = Arrays.stream(csv.split(","))
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .distinct()
            .limit(5000)
            .collect(Collectors.toList());
        if (items.isEmpty()) return;

        int batchSize = 999;
        List<List<String>> groups = new ArrayList<>();
        for (int i = 0; i < items.size(); i += batchSize) {
            groups.add(items.subList(i, Math.min(i + batchSize, items.size())));
        }
        req.setCodeGroups(groups);
    }
    /**
	 * @description : 주소 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 *  2026.03.09  (dytpq362@cj.net) 생성  </pre>
	 */
	public List<MsCustDeliveryInfoResDto> getAddressList(MsCustDeliveryInfoReqDto dto) {		
		List<MsCustDeliveryInfoResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getAddressList", dto);		
		return result;
	}
}
