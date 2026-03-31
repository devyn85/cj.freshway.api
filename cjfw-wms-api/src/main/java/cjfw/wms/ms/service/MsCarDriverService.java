package cjfw.wms.ms.service;

import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.edms.EdmsFileUploader;
import cjfw.core.exception.UserHandleException;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderEdms;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmUserManagerConnectReqDto;
import cjfw.wms.cm.dto.CmUserManagerReqDto;
import cjfw.wms.cm.dto.CmUserManagerResDto;
import cjfw.wms.cm.entity.CmSyProcessTempDmDEntity;
import cjfw.wms.cm.service.CmUserManagerService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.ms.dto.MsCarDriverDetailResDto;
import cjfw.wms.ms.dto.MsCarDriverEntryInfoResDto;
import cjfw.wms.ms.dto.MsCarDriverExcelReqDto;
import cjfw.wms.ms.dto.MsCarDriverExcelResDto;
import cjfw.wms.ms.dto.MsCarDriverFileUploadReqDto;
import cjfw.wms.ms.dto.MsCarDriverReqDto;
import cjfw.wms.ms.dto.MsCarDriverResDto;
import cjfw.wms.ms.entity.MsCarDriverEntity;
import cjfw.wms.ms.entity.MsCarDriverEntryInfoEntity;
import cjfw.wms.st.dto.StInquiryReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.08.12
 * @description : 기준정보 > 차량기준정보 > 차량정보 목록 조회 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCarDriverService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCarDriverService.";
//	private transient static final String PAKAGE_NAME = "SPCM_CAR";
	
	private final CmUserManagerService cmUserManagerService;
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	private final EdmsFileUploader edmsFileUploader;
	private final FileUploaderEdms fileUploaderEdms;
	
	/**
	 * 
	 * @description : 목록조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsCarDriverResDto> getMasterList(MsCarDriverReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * 
	 * @description : 단건조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public MsCarDriverDetailResDto getMaster(MsCarDriverReqDto dto) {
		MsCarDriverDetailResDto data = commonDao.selectOne(SERVICEID_PREFIX + "getMaster", dto);
		return data;
	}
	
	/**
	 * 
	 * @description : 입출차정보 목록조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.04        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsCarDriverEntryInfoResDto> getEntryInfoList(MsCarDriverReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getEntryInfoList", dto);
	}
	
	/**
	 * 
	 * @description : 목록 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveMasterList(List<MsCarDriverReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsCarDriverEntity.class);
				
				commonDao.update(SERVICEID_PREFIX +"updateMasterList", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	/**
	 * 
	 * @description : 상세 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Transactional
	public String saveMaster(MsCarDriverReqDto dto) {
		var entity = ModelMapperUtil.map(dto, userContext, MsCarDriverEntity.class);
		
		if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
			int count = commonDao.selectOne(SERVICEID_PREFIX +"getDriverDupCount", entity);
			
			if(count > 0) {
				throw new UserHandleException(MessageUtil.getMessage("MSG.COM.VAL.067", new String[]{"차량정보"}));
			} else {
				saveDriver(dto);
			}
		} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {				
			if(dto.getDriverId() == null || dto.getDriverId().isEmpty()) {
				// DRIVER_ID 가 없을 경우 신규 생성
				saveDriver(dto);
			} else {
				commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
				commonDao.update(SERVICEID_PREFIX +"updateDriver", entity);
				//commonDao.insert(SERVICEID_PREFIX +"insertDriverIF", entity); // tobe에서는  scm0020 etl 제외
				
				// 기사 핸드폰번호 사용자 테이블 수정
				CmUserManagerReqDto cmUserManagerReqDto = new CmUserManagerReqDto();
				CmUserManagerResDto userDtlDto = ModelMapperUtil.map(dto, userContext, CmUserManagerResDto.class);
				userDtlDto.setUserId(dto.getDriverId()); 					// 사용자ID
				userDtlDto.setHandphoneNo(dto.getPhone1());					// 핸드폰번호
				userDtlDto.setRowStatus(CanalFrameConstants.UPDATE); 		// 상태값 UPDATE (고정값)
				if(dto.getCarAgentKey() == null || dto.getCarAgentKey().equals("")) {
					userDtlDto.setCustkey("9999999");
				} else {
					userDtlDto.setCustkey(dto.getCarAgentKey());
				}
				cmUserManagerReqDto.setUserDtl(userDtlDto);
				cmUserManagerService.saveDriverUser(cmUserManagerReqDto);
			}
		}
		
		List<MsCarDriverReqDto> entryInfoList = dto.getCarDriverList();
		if (null != entryInfoList) {
			for (var entryInfo : entryInfoList) {
				var entryInfoEntity = ModelMapperUtil.map(entryInfo, userContext, MsCarDriverEntryInfoEntity.class);
				
				if ((CanalFrameConstants.DELETE).equals(entryInfoEntity.getRowStatus())) {
					commonDao.delete(SERVICEID_PREFIX +"deleteEntryInfoList", entryInfoEntity);
				} else {
					commonDao.update(SERVICEID_PREFIX +"updateEntryInfoList", entryInfoEntity);	
				}
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * 
	 * @description : 기사 신규 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public void saveDriver(MsCarDriverReqDto dto) {
		var entity = ModelMapperUtil.map(dto, userContext, MsCarDriverEntity.class);
		
		String driverId = entity.getDriverId();
				
		// DRIVER_ID 가 없을 경우 신규 생성
		if((driverId == null || driverId.isEmpty()) && !dto.getDriverName().isEmpty()) {
			driverId = commonDao.selectOne(SERVICEID_PREFIX +"getInsertDriverId", entity);
			entity.setDriverId(driverId);
			
			commonDao.insert(SERVICEID_PREFIX +"insertDriver", entity);
//			commonDao.insert(SERVICEID_PREFIX +"insertDriverIF", entity); //tobe에서는  scm0020 etl 제외
			
			// 사용자테이블 등록
			// STEP1 : 신규 사용자 등록을 위한 상위 DTO 생성
			CmUserManagerReqDto cmUserManagerReqDto = new CmUserManagerReqDto();
			
			// STEP2 : 센터 권한 설정
			List<CmUserManagerConnectReqDto> connectList = new ArrayList<CmUserManagerConnectReqDto>();
			List<MsCarDriverReqDto> entryInfoList = dto.getEntryInfoList();
			String dcCode = dto.getGDccode();
			if (null != entryInfoList && entryInfoList.size() > 0) {
				dcCode = entryInfoList.get(0).getDcCode();
				for (var entryInfo : entryInfoList) {
					CmUserManagerConnectReqDto connect = new CmUserManagerConnectReqDto();
					connect.setUserId(driverId);								// 사용자ID
					connect.setDccode(entryInfo.getDcCode());									// 권한 부여할 센터코드 (STD: 전체 센터 권한)
					connect.setOrganize("STD");									// 권한 부여할 창고 (STD: 전체 창고 권한)
					connect.setRowStatus(CanalFrameConstants.INSERT);			// 상태값 INSERT (고정값)
					connectList.add(connect);
				}
			}
			cmUserManagerReqDto.setConnectList(connectList);
			
			// STEP3 : 사용자 상세 정보 DTO 생성 및 값 설정
			CmUserManagerResDto userDtlDto = ModelMapperUtil.map(dto, userContext, CmUserManagerResDto.class);
			
			userDtlDto.setUserId(driverId); 							// 사용자ID ("_WAYLO" 붙여줘야 함)
			userDtlDto.setUserNm(dto.getDriverName());					// 사용자명
			userDtlDto.setAuthority("410");								// 권한코드 (고정값 410: [파트너] 물류센터 배송기사)
			userDtlDto.setEmpType("C01");								// 소속구분 (고정값 C01: 배송업체)
			userDtlDto.setDefDccode(dcCode);					        // 기본센터코드
			userDtlDto.setDefStorerkey("FW00");							// 기본고객코드 (고정값)
			userDtlDto.setDefOrganize("STD");				            // 기본조직코드
			userDtlDto.setDefArea(dto.getGArea());						// 기본창고코드 (고정값)
			// 업체코드
			if(dto.getCarAgentKey() == null || dto.getCarAgentKey().equals("")) {
				userDtlDto.setCustkey("9999999");
			} else {
				userDtlDto.setCustkey(dto.getCarAgentKey());
			}				
			userDtlDto.setHandphoneNo(dto.getPhone1());					// 핸드폰번호
			userDtlDto.setDlvAppUserYn("Y");							// 배송APP사용여부 (고정값)
			
			userDtlDto.setRowStatus(CanalFrameConstants.INSERT); 		// 상태값 INSERT (고정값)
			userDtlDto.setDriverYn("Y"); 								// 드라이버 신규 사용자 등록여부 (고정값)
			cmUserManagerReqDto.setUserDtl(userDtlDto);
			
			// STEP4 : 신규 사용자 등록
			cmUserManagerService.saveUser(cmUserManagerReqDto);	
		} else {
			commonDao.update(SERVICEID_PREFIX +"updateDriver", entity);
//			commonDao.insert(SERVICEID_PREFIX +"insertDriverIF", entity);  // tobe에서는  scm0020 etl 제외
			
			// 기사 핸드폰번호 사용자 테이블 수정
			CmUserManagerReqDto cmUserManagerReqDto = new CmUserManagerReqDto();
			CmUserManagerResDto userDtlDto = ModelMapperUtil.map(dto, userContext, CmUserManagerResDto.class);
			userDtlDto.setUserId(dto.getDriverId()); 					// 사용자ID
			userDtlDto.setHandphoneNo(dto.getPhone1());					// 핸드폰번호
			userDtlDto.setRowStatus(CanalFrameConstants.UPDATE); 		// 상태값 UPDATE (고정값)
			cmUserManagerReqDto.setUserDtl(userDtlDto);
			cmUserManagerService.saveDriverUser(cmUserManagerReqDto);
		}
		
		if(dto.getSerialKey() == null || dto.getSerialKey().isEmpty()) {
			commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
		} else {
			commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
		}		
		
		// DRIVER 정보가 없으면 차량정보만 생성
		if(dto.getDriverName() == null || dto.getDriverName().isEmpty()) {
			return;
		}
	}
	
	/**
	 * @description : 차량정보 파일 업로드
	 * @issues :<pre> 
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String carDriverSaveFileUpload(List<MultipartFile> files, List<FileUpload> fileInfoList) {
		// NAS 파일 업로드
		List<FileUpload> fileUploadList = fileUploaderEdms.saveFiles(files, fileInfoList);		
		
		// EDMS 파일 업로드
		List<Map<String, Object>> rFileList = edmsFileUploader.batchFileRegister(fileUploadList);
		
		// TO-DO : 파일정보 업무 TABLE에 MERGE
		for(Map<String, Object> rFile : rFileList) {
			log.info("==================== EDMS 업로드 결과 {}", rFile.toString());
			
			MsCarDriverFileUploadReqDto uploadDto = new MsCarDriverFileUploadReqDto();			
			uploadDto.setDocType("256");
			uploadDto.setFileName((String) rFile.get("orgname"));
			uploadDto.setFileExtension(((String) rFile.get("orgname")).substring(((String) rFile.get("orgname")).lastIndexOf(".")+1));
			uploadDto.setFileLocation(ContextUtil.getProperty("cf.edms.tempDir") + "/" + userContext.getUserNo() + "/" + ((String) rFile.get("orgname")));
			uploadDto.setFileSizeBytes(String.valueOf(rFile.get("filesize")));
			uploadDto.setTransFileName((String) rFile.get("orgname"));
			uploadDto.setUploadResDocId(String.valueOf(rFile.get("docfileid")));
			uploadDto.setUploadFileName((String) rFile.get("orgname"));
			uploadDto.setUploadWorkplaceId(ContextUtil.getProperty("cf.edms.workPlaceId"));			
			commonDao.insert(SERVICEID_PREFIX +"insertCmCarHttpDocAttach", uploadDto);
			
			//유효기간 업데이트
			String arrName = rFile.get("orgname").toString().split("\\.")[0];
			String[] arrFld = arrName.split("_");
			if(arrFld.length == 3){ 
				MsCarDriverFileUploadReqDto dto = new MsCarDriverFileUploadReqDto();		
				dto.setCarNo(arrFld[0]);				
				dto.setGubun(arrFld[1]);
				dto.setCarDt(arrFld[2]);
				commonDao.update(SERVICEID_PREFIX +"updateCmCarHttpDocAttachDate", dto);
			}
			
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	public MsCarDriverResDto getDriverList (MsCarDriverReqDto msCarDriverReqDto){
		return commonDao.selectOne(SERVICEID_PREFIX + "getDriverList", msCarDriverReqDto);
	}
	/**
	 * @description :POP일괄업로드(excel대용량) & 유효성검증
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCarDriverDetailResDto> saveExcelList(MsCarDriverExcelReqDto paramDto) {
		List<MsCarDriverDetailResDto> result = new ArrayList<MsCarDriverDetailResDto>();
		
		 // 파라미터 위변조 적용(paramDto->reqDto)
		MsCarDriverExcelReqDto reqDto = ModelMapperUtil.map(paramDto, MsCarDriverExcelReqDto.class);

		// 저장리스트
		List<MsCarDriverExcelResDto> saveList = reqDto.getSaveList(); 

		reqDto.setPackagename(paramDto.getPakagename());
		reqDto.setProcesstype(paramDto.getProcesstype());
		
		// 임시테이블 삭제(1/3)
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTempDMD", reqDto);
		
		int chunkSize = 100;
		List<CmSyProcessTempDmDEntity> insertList = new ArrayList<>();

		for (int i = 0; i < saveList.size(); i++) {
			MsCarDriverExcelResDto dto = saveList.get(i);
			CmSyProcessTempDmDEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempDmDEntity.class);
			entity.setPackagename(paramDto.getProcesstype()); // 패키지명
			entity.setProcesstype(paramDto.getProcesstype()); // 프로세스타입
			String columnsDto    = "DCCODE|CARNO|CONTRACTTYPE|CARCAPACITY|DELIVERYYN|CARRYYN   |PROCSAMEDAYYN|PROCSTORAGEYN|OWNCARYN  |RETURNYN  |CARORDERCLOSECD|REUSEYN|OUTGROUP   |COURIER|CARAGENTKEY|FROMDATE |TODATE    |TODATE2     |VEHICLEYEAR|VEHICLETYPECD|FUELEFFICIENCY|OPTLOADWEIGHT|MAXWEIGHT |OTHER03|MAXLANDING|BASEWEIGHT2ND|MAXWEIGHT2ND|BASELANDING2ND|MAXLANDING2ND|SUBDRIVERYN|GARAGEADDRESS1|GARAGEADDRESS2|DRIVERNAME |PHONE1     |WORKFROMHOUR|WORKTOHOUR |LATITUDE|LONGITUDE|THERMOMETERNO";
			String columnsEntity = "DCCODE|SOKEY|INVOICETYPE |POKEY      |LOT       |LOTTABLE01|LOTTABLE02   |LOTTABLE03   |LOTTABLE04|LOTTABLE05|POTYPE         |SOTYPE |PLANDOCTYPE|COURIER|SITE       |PLANDOCDT|MERGEDOCDT|DELIVERYDATE|OTHER06    |OTHER07      |ORDERQTY      |OPENQTY      |PROCESSQTY|DOCLINE|IOTYPE    |WORKQTY      |INSPECTQTY  |RETURNTYPE    |RETURNWHO    |REFERENCE07|REFERENCE01   |REFERENCE02   |REFERENCE03|REFERENCE04|REFERENCE05 |REFERENCE06|OTHER01 |OTHER02  |OTHER08"; // 공통TABLE컬럼명
			entity = (CmSyProcessTempDmDEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
	
			insertList.add(entity);
			
			// 100개마다 혹은 마지막 루프일 때 insert(3/3)
			if (insertList.size() == chunkSize || i == saveList.size() -1) {
				commonDao.insert(SERVICEID_PREFIX + "insertTemp", insertList);
				insertList.clear();
			}
		}
		/*END.Temp Table Insert*/
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto, reqDto, paramDto.getProcesstype());
		
		// PKG 파라마터 세팅 및 실행 - 업무(2/4)
		String[] keyList = {"PROCESSTYPE","PROCESSCREATOR","SPID"};
		Object[] valueList = {reqDto.getProcesstype(), reqDto.getGUserId(), reqDto.getGSpid()};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		reqDto.setAvc_EXECUTEMODE("NOCOMMIT");
		
		commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, reqDto);
		
		// 프로시저 OUT Parameter(3/4)
		String resultCode    = (String)reqDto.getResultCode();
		String resultMessage = (String)reqDto.getResultMessage();
		
		// 프로시저 Exception 처리(4/4)
		if (!resultCode.equals("0")) {
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"대량업로드"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}

		result = commonDao.selectList(SERVICEID_PREFIX + "getExcelUploadResult", reqDto);
		
		return result;
	}
}
