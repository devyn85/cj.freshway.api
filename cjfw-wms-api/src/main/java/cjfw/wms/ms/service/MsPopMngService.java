package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.ms.dto.MsPopMngPOPResDto;
import cjfw.wms.ms.dto.MsPopMngReqDto;
import cjfw.wms.ms.dto.MsPopMngResDto;
import cjfw.wms.ms.entity.MsPopMngEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.07.18 
 * @description : 기준정보 > 권역기준정보 > 거래처별POP관리 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsPopMngService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msPopMngService.";
	private transient static final String PAKAGE_NAME = "SPMS_CUSTXPOP";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 거래처별POP관리 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsPopMngResDto> getMasterList(MsPopMngReqDto dto) {
		List<MsPopMngResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
	
	/**
	 * @description : rolltainer 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsPopMngResDto> getRolltainerList(MsPopMngReqDto dto) {
		List<MsPopMngResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getRolltainerList", dto);
		return result;
	}
		
//	/**
//	 * @description :거래처별POP관리 MERGE
//	 * @issues :<pre> 
//	 * ----------------------------------------------------------- 
//	 * DATE       AUTHOR                MAJOR_ISSUE 
//	 * ----------------------------------------------------------- 
//	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
//	 */
//	@Transactional
//	public String saveMasterList(List<MsPopMngReqDto> dtoList) {
//		if (null != dtoList) {
//			for (MsPopMngReqDto dto : dtoList) {
//				MsPopMngEntity entity = ModelMapperUtil.map(dto, userContext, MsPopMngEntity.class);
//				// 데이터 pk 확인
//				String isNew = commonDao.selectOne(SERVICEID_PREFIX + "getValidateUpdate", entity);
////				if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
//				entity.setIsNew(isNew);
//				if(isNew.equals("N")) {
//					// 기 데이터 업데이트 처리
//					commonDao.update(SERVICEID_PREFIX +"updateMasterList", entity);
//					commonDao.insert(SERVICEID_PREFIX +"insertHISList", entity);
//					commonDao.insert(SERVICEID_PREFIX +"insertIFList", entity);
//					continue;
//				}else {
//					// fromdate 변경 데이터 -> todate 변경 후 신규데이터 추가
//					commonDao.update(SERVICEID_PREFIX +"deleteMasterList", entity);					
//					commonDao.insert(SERVICEID_PREFIX +"insertHISList", entity);
//					commonDao.insert(SERVICEID_PREFIX +"insertIFList", entity);
//				}
////				}
//				// 행추가 및 fromdate 변경 시 신규 데이터 추가
//				commonDao.insert(SERVICEID_PREFIX +"insertMasterList", entity);
//				String newSerialkey = entity.getNewSerialkey(); // <selectKey>로 DTO에 주입된 값
//			    entity.setSerialkey(newSerialkey);
//			    entity.setIsNew("N");	// serialkey로 insert
//				commonDao.insert(SERVICEID_PREFIX +"insertHISList", entity);
//				commonDao.insert(SERVICEID_PREFIX +"insertIFList", entity);
//			}
//		}
//		return CanalFrameConstants.MSG_COM_SUC_CODE;
//	}
	/**
	 * @description :거래처별POP관리 MERGE
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.25 parkyosep (dytpq362@cj.net)생성 </pre>
	 */
	@Transactional
	public String saveMasterList(List<MsPopMngReqDto> dtoList) {
		if (null != dtoList) {
			for (MsPopMngReqDto dto : dtoList) {
				MsPopMngEntity entity = ModelMapperUtil.map(dto, userContext, MsPopMngEntity.class);
				MsPopMngResDto result =  commonDao.selectOne(SERVICEID_PREFIX +"chkVal", dto);
//				String dataVal = result.getDataVal();
				String dateVal = result.getDateVal();
				String serialkey = result.getSerialkey();
				if (dateVal != null && Integer.parseInt(dateVal) > 0) {
					 throw new UserHandleException("해당 일자의 해당 고객 정보가 이미 등록되어 있습니다.");
				}
//				else if(dataVal != null && Integer.parseInt(dataVal) > 0) {
//					throw new UserHandleException("이미 등록된 정보입니다.");
//				}
				else {
					if(serialkey!= null && !"".equals(serialkey)) {
						entity.setSerialkey(serialkey);
						commonDao.insert(SERVICEID_PREFIX +"updateList", entity); // 행 수정일경우
						commonDao.insert(SERVICEID_PREFIX +"insertHISList", entity);
						commonDao.insert(SERVICEID_PREFIX +"insertIFList", entity);
					}else {
						commonDao.insert(SERVICEID_PREFIX +"insertMasterList", entity); // 신규행일때
						entity.setSerialkey(entity.getNewSerialkey());
						commonDao.insert(SERVICEID_PREFIX +"insertHISList", entity);
						commonDao.insert(SERVICEID_PREFIX +"insertIFList", entity);
					}
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	/**
	 * @description :거래처별POP관리 MERGE
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.25 parkyosep (dytpq362@cj.net)생성 </pre>
	 */
	@Transactional
	public String saveMasterList2(List<MsPopMngReqDto> dtoList) {
		if (null != dtoList) {
			for (MsPopMngReqDto dto : dtoList) {
				MsPopMngEntity entity = ModelMapperUtil.map(dto, userContext, MsPopMngEntity.class);
				List<MsPopMngResDto> result =  commonDao.selectList(SERVICEID_PREFIX +"chkVal_backup", entity);
				String staDt = null;
			    String endDt = null;
			    String serialkey = null;
			    String prevSerialkey = null;
			    
//				String staDt = result.get(0).getStaDt();
//				String endDt = result.get(0).getEndDt();
//				String serialkey = result.get(0).getSerialkey();
//				String prevSerialkey = result.get(0).getPrevSerialkey();
			    if (result != null && !result.isEmpty() && result.get(0) != null) {
			        MsPopMngResDto resDto = result.get(0); 
			        staDt = resDto.getStaDt();
			        endDt = resDto.getEndDt();
			        serialkey = resDto.getSerialkey();
			        prevSerialkey = resDto.getPrevSerialkey();
			    }
				
				if(serialkey!= null && !"".equals(serialkey)) {
					// FROMDATE 가 동일한 데이터-> 기존데이터 업데이트
					entity.setSerialkey(serialkey);
					entity.setIsNew("N");
					commonDao.update(SERVICEID_PREFIX +"updateMasterList", entity);
					commonDao.insert(SERVICEID_PREFIX +"insertHISList", entity);
					commonDao.insert(SERVICEID_PREFIX +"insertIFList", entity);	
				}
				else {
					if(staDt != null && !"".equals(staDt)) { // 입력 데이터보다 fromDt가 작은 데이터가 있을 경우, formDt중 가장 작은 데이터 중에서 큰 데이터의 종료일자를 수정한다. 
						 // TODATE 만 바꿔서 넣을예정
						entity.setSerialkey(prevSerialkey);
						entity.setIsNew("N");
						commonDao.update(SERVICEID_PREFIX +"prevUpdate", entity);
						commonDao.insert(SERVICEID_PREFIX +"insertHISList", entity);
						commonDao.insert(SERVICEID_PREFIX +"insertIFList", entity);	
						entity.setIsNew("Y");
					}
					if (endDt != null && !"".equals(endDt)){
						entity.setTodate(endDt); // 입력 데이터보다 fromDt가 큰 데이터가 있을 경우 ,  입력데이터의 toDt를 변경한 후 데이터 넣어야 함
					}
					if("U".equals(dto.getRowStatus())) {
						entity.setSerialkey(dto.getSerialkey());
						commonDao.insert(SERVICEID_PREFIX +"updateList", entity); // 행 수정일경우
					}else {
						commonDao.insert(SERVICEID_PREFIX +"insertMasterList", entity); // 신규행일때
						String newSerialkey = entity.getNewSerialkey(); // <selectKey>로 DTO에 주입된 값
					    entity.setSerialkey(newSerialkey);
	
					}		
					    entity.setIsNew("N");	// serialkey로 insert
						commonDao.insert(SERVICEID_PREFIX +"insertHISList", entity);
						commonDao.insert(SERVICEID_PREFIX +"insertIFList", entity);	
				}


			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	/**
	 * @description :거래처별POP관리 일괄 재전송 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String saveMasterResend(MsPopMngReqDto dto) {
		MsPopMngEntity entity = ModelMapperUtil.map(dto, userContext, MsPopMngEntity.class);
		commonDao.update(SERVICEID_PREFIX +"saveMasterResend", entity);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description :POP일괄업로드(excel대용량) & 유효성검증
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsPopMngResDto> saveExcelList(MsPopMngReqDto dto) {
		List<MsPopMngResDto> result = new ArrayList<MsPopMngResDto>();
		dto.setSpid(dto.getGSpid());
		// 임시테이블 삭제(1/3)
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTempTM", dto);

		List<MsPopMngEntity> saveList = dto.getSaveList(); // 저장리스트
		
		log.info("▶saveList.size->{}",saveList);
		log.info("▶saveList.size->{}",saveList.size());

		int chunkSize = 100;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		for (int i = 0; i < saveList.size(); i++) {
			MsPopMngEntity entity = saveList.get(i);
			// 임시테이블에 등록(2/3)
			CmSyProcessTempWdEntity tempEntity = ModelMapperUtil.map(entity, userContext, CmSyProcessTempWdEntity.class);
			tempEntity.setProcesstype(dto.getProcesstype()); // 프로세스타입

			insertList.add(tempEntity);

			// 100개마다 혹은 마지막 루프일 때 insert(3/3)
			if (insertList.size() == chunkSize || i == saveList.size() -1) {
				commonDao.insert(SERVICEID_PREFIX + "insertTemp", insertList);
				insertList.clear();
			}
		}
		/*END.Temp Table Insert*/
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(dto, dto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 및 실행 - 업무(2/4)
		String[] keyList = {"PROCESSTYPE","PROCESSCREATOR","SPID"};
		Object[] valueList = {dto.getProcesstype(), dto.getGUserId(), dto.getGSpid()};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		dto.setAvc_EXECUTEMODE("NOCOMMIT");
		
		commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, dto);
		
		// 프로시저 OUT Parameter(3/4)
		String resultCode    = (String)dto.getResultCode();
		String resultMessage = (String)dto.getResultMessage();
		
		// 프로시저 Exception 처리(4/4)
		if (!resultCode.equals("0")) {
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"대량업로드"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		//custkey 중복확인
		Set<String> uniqueKeys = new HashSet<>();
		Set<String> duplicatedKeys = new HashSet<>();
		
		List<MsPopMngResDto> selectResult = commonDao.selectList(SERVICEID_PREFIX + "getExcelUploadResult", dto);
		for(MsPopMngResDto resultDto : selectResult) {
			//중복확인
			String dccode = resultDto.getDccode(); 
			String popno = resultDto.getPopno();
			String carno = resultDto.getCarno();
			String custkey = resultDto.getCustkey();
			String formDate = resultDto.getFromdate();
		    
		    
		    // 유니크 키 조합 (예시: 언더바 "_"로 연결)
			String compositeKey = dccode + "_" + popno + "_" + carno + "_" + custkey+ "_" +formDate;
			if (uniqueKeys.contains(compositeKey)) {
				duplicatedKeys.add(compositeKey);
	            // 이 시점에서는 현재 데이터에 중복 오류를 즉시 설정하고,
	            // 이전 데이터들은 루프가 끝난 후 한 번에 업데이트
	            resultDto.setProcessYn("E");
	            resultDto.setProcessMsg("중복된 고객코드입니다.");
	        } else {
	            // 처음 등장한 유니크 키는 uniqueCustKeys에 추가
	        	uniqueKeys.add(compositeKey);
	        }
						
			result.add(resultDto);
		}
		// 중복이 발견된 유니크 키에 대해 이전에 정상 처리된 건도 오류 처리
	    if (!duplicatedKeys.isEmpty()) {
	        for (MsPopMngResDto editDto : result) {
	        	//중복확인
				String dccode = editDto.getDccode(); 
				String popno = editDto.getPopno();
				String carno = editDto.getCarno();
				String custkey = editDto.getCustkey();
			    
			    // 유니크 키 조합 (예시: 언더바 "_"로 연결)
				String compositeKey = dccode + "_" + popno + "_" + carno + "_" + custkey;
	            // 중복 리스트에 포함된 유니크 키 인 경우 (첫 번째 건 포함)
	            if (duplicatedKeys.contains(compositeKey) && !"E".equals(editDto.getProcessYn())) {
	                // 이미 주소 오류가 아닌 경우에만 중복 오류를 설정
	            	editDto.setProcessYn("E");
	            	editDto.setProcessMsg("중복된 고객코드입니다.");
	            }
	        }
	    }
		
		return result;
	}
	
	/**
	 * @description : 코드 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<MsPopMngPOPResDto> getCodeDetailList(MsPopMngReqDto dto) {
		List<MsPopMngPOPResDto> result = new ArrayList<MsPopMngPOPResDto>();
		if (null != dto) {
			result = commonDao.selectList(SERVICEID_PREFIX + "getCodeDetailList", dto);
		}
		return result;
	}
	
	/**
	 * @description :대표POP변경 codelist  & 대표POP변경 bulk update 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Transactional
	@CacheEvict(cacheNames = "userCode", allEntries = true)
	public String saveCodeList(List<MsPopMngReqDto> dtoList) {
		if (null != dtoList) {
			for (MsPopMngReqDto dto : dtoList) {
				MsPopMngEntity entity = ModelMapperUtil.map(dto, userContext, MsPopMngEntity.class);
				commonDao.update(SERVICEID_PREFIX +"saveCodeList", entity);
				commonDao.update(SERVICEID_PREFIX +"updateBulkPOPList", entity);
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
