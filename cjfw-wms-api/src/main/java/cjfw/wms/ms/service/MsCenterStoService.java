package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsCenterStoClosetypeExcelResDto;
import cjfw.wms.ms.dto.MsCenterStoClosetypeResDto;
import cjfw.wms.ms.dto.MsCenterStoPickResDto;
import cjfw.wms.ms.dto.MsCenterStoPriorityResDto;
import cjfw.wms.ms.dto.MsCenterStoReqDto;
import cjfw.wms.ms.dto.MsCenterStoResDto;
import cjfw.wms.ms.entity.MsCenterStoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.08.04 
 * @description : 기준정보 > 센터기준정보 > 센터이체마스터 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCenterStoService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCenterStoService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 센터이체마스터 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCenterStoResDto> getMasterList(MsCenterStoReqDto dto) {
		List<MsCenterStoResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
	
	/**
	 * @description : 센터이체마스터 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Transactional
	public String saveMasterList(List<MsCenterStoReqDto> dtoList) {
		if (null != dtoList) {
			for (MsCenterStoReqDto dto : dtoList) {
				MsCenterStoEntity entity = ModelMapperUtil.map(dto, userContext, MsCenterStoEntity.class);
				String fromDccode = entity.getFromDccode();
				if(fromDccode == null || fromDccode.isEmpty()) {
					commonDao.delete(SERVICEID_PREFIX +"deleteMasterList", entity);
					commonDao.update(SERVICEID_PREFIX +"updatePriority", entity);
				}else {
					commonDao.update(SERVICEID_PREFIX +"saveMasterList", entity);
					commonDao.update(SERVICEID_PREFIX +"updatePriority", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 센터이체마스터 피킹유형 자동 설정 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCenterStoPickResDto> getPickList(MsCenterStoReqDto dto) {
		List<MsCenterStoPickResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getPickList", dto);
		return result;
	}
	
	/**
	 * @description : 센터이체마스터 피킹유형 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCenterStoPickResDto> getPickTypeList(MsCenterStoReqDto dto) {
		List<MsCenterStoPickResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getPickTypeList", dto);
		return result;
	}
	
	/**
	 * @description : 센터이체마스터 피킹유형 자동 설정 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Transactional
	public String savePickList(List<MsCenterStoReqDto> dtoList) {
		
		for (MsCenterStoReqDto dto : dtoList) {
		    MsCenterStoEntity entity = ModelMapperUtil.map(dto, userContext, MsCenterStoEntity.class);

		    MsCenterStoPickResDto duplicateChk = commonDao.selectOne(SERVICEID_PREFIX + "validatePickList", entity);
		    
		    if (duplicateChk.getPickListChk() > 0) {
		    	throw new UserHandleException("중복 데이터가 있습니다.");
		    }
		    commonDao.insert(SERVICEID_PREFIX + "savePickList", entity);
		}

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 센터이체마스터 센터 제외 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCenterStoClosetypeResDto> getClosetypeDcList(MsCenterStoReqDto dto) {
		List<MsCenterStoClosetypeResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getClosetypeDcList", dto);
		return result;
	}
	
	
	/**
	 * @description : 센터이체마스터 센터 제외 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String saveClosetypeDcList(List<MsCenterStoReqDto> dtoList) {
		for (MsCenterStoReqDto dto : dtoList) {
		    MsCenterStoEntity entity = ModelMapperUtil.map(dto, userContext, MsCenterStoEntity.class);

		    MsCenterStoClosetypeResDto duplicateChk = commonDao.selectOne(SERVICEID_PREFIX + "validateClosetypeDcList", entity);
		    
		    if (duplicateChk.getClosetypeDcChk() > 0) {
		    	throw new UserHandleException("중복 데이터가 있습니다.");
		    }
		    commonDao.insert(SERVICEID_PREFIX + "saveClosetypeDcList", entity);
		}

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 센터이체마스터 상품 제외 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCenterStoClosetypeResDto> getClosetypeSkuList(MsCenterStoReqDto dto) {
		List<MsCenterStoClosetypeResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getClosetypeSkuList", dto);
		return result;
	}
	
	/**
	 * @description : 센터이체마스터 상품 제외 저장 유효성 검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String getClosetypeSkuListCheck(List<MsCenterStoReqDto> dtoList) {
		String result = "";
		for (MsCenterStoReqDto dto : dtoList) {
		    MsCenterStoEntity entity = ModelMapperUtil.map(dto, userContext, MsCenterStoEntity.class);

		    MsCenterStoClosetypeResDto duplicateChk = commonDao.selectOne(SERVICEID_PREFIX + "validateClosetypeSkuList", entity);
		    
		    if (duplicateChk.getClosetypeSkuChk() > 0) {
		    	result = "중복 데이터가 있습니다.";
		    }else {
		    	result = "유효성 검증완료";
		    }
		}
		return result;
	}
	
	/**
	 * @description : 센터이체마스터 상품 제외 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Transactional
	public String saveClosetypeSkuList(List<MsCenterStoReqDto> dtoList) {
		for (MsCenterStoReqDto dto : dtoList) {
		    MsCenterStoEntity entity = ModelMapperUtil.map(dto, userContext, MsCenterStoEntity.class);

		    MsCenterStoClosetypeResDto duplicateChk = commonDao.selectOne(SERVICEID_PREFIX + "validateClosetypeSkuList", entity);
		    
		    if (duplicateChk.getClosetypeSkuChk() > 0) {
		    	throw new UserHandleException("중복 데이터가 있습니다.");
		    }
		    commonDao.insert(SERVICEID_PREFIX + "saveClosetypeSkuList", entity);
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 센터이체마스터 상품 제외 EXCELUPLOAD 유효성 검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCenterStoClosetypeExcelResDto> validateClosetypeSkuExcelList(List<MsCenterStoReqDto> dtoList) {
		List<MsCenterStoClosetypeExcelResDto> result = new ArrayList<MsCenterStoClosetypeExcelResDto>();
		if(dtoList != null) {
			result = commonDao.selectList(SERVICEID_PREFIX + "validateClosetypeSkuExcelList", dtoList);
			
			//중복확인
			Set<String> uniqueKeys = new HashSet<>();
			Set<String> duplicatedKeys = new HashSet<>();
			for(MsCenterStoClosetypeExcelResDto resultDto : result) {
				String dccode = resultDto.getDccode(); 
			    String sku = resultDto.getSku();
			    String dcClosetype = resultDto.getDcClosetype();
			    
			    // 유니크 키 조합 (예시: 언더바 "_"로 연결)
			    String compositeKey = dccode + "_" + dcClosetype + "_" + sku;
			    
				if (uniqueKeys.contains(compositeKey)) {
					duplicatedKeys.add(compositeKey);
		            // 이 시점에서는 현재 데이터에 중복 오류를 즉시 설정하고,
		            // 이전 데이터들은 루프가 끝난 후 한 번에 업데이트
		            resultDto.setDuplicateChk("N");
		        } else {
		            // 처음 등장한 유니크 키는 uniqueCustKeys에 추가
		        	uniqueKeys.add(compositeKey);
		        }				
			}
			// 중복이 발견된 유니크 키에 대해 이전에 정상 처리된 건도 오류 처리
		    if (!duplicatedKeys.isEmpty()) {
		        for (MsCenterStoClosetypeExcelResDto editDto : result) {
		        	String dccode = editDto.getDccode();
		            String sku = editDto.getSku();
		            String dcClosetype = editDto.getDcClosetype();
		            // 유니크 키 조합
		            String compositeKey = dccode + "_" + dcClosetype  + "_" + sku;
		            // 중복 리스트에 포함된 유니크 키인 경우 (첫 번째 건 포함)
		            if (duplicatedKeys.contains(compositeKey)) {
		                // 이미 오류가 아닌 경우에만 중복 오류를 설정
		            	editDto.setDuplicateChk("N");
		            }
		        }
		    }
		}
		return result;
	}
	
	/**
	 * @description : 센터이체마스터 상품 제외 EXCELUPLOAD 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String saveClosetypeSkuExcelList(List<MsCenterStoReqDto> dtoList) {		

	    for (MsCenterStoReqDto dto : dtoList) {
	        MsCenterStoEntity entity = ModelMapperUtil.map(dto, userContext, MsCenterStoEntity.class);
	        commonDao.insert(SERVICEID_PREFIX + "saveClosetypeSkuExcelList", entity);
	    }
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 센터이체마스터 우선 순위 설정 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCenterStoPriorityResDto> getPriorityList(MsCenterStoReqDto dto) {
		List<MsCenterStoPriorityResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getPriorityList", dto);
		return result;
	}
	
	/**
	 * @description : 센터이체마스터 우선 순위 설정 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Transactional
	public String savePriorityList(List<MsCenterStoReqDto> dtoList) {
		for (MsCenterStoReqDto dto : dtoList) {
		    MsCenterStoEntity entity = ModelMapperUtil.map(dto, userContext, MsCenterStoEntity.class);
		    MsCenterStoPriorityResDto duplicateChk = commonDao.selectOne(SERVICEID_PREFIX + "validatePriorityList", entity);
		    
		    if (duplicateChk.getPriorityChk() > 0) {
		    	throw new UserHandleException("중복 데이터가 있습니다.");
		    }
		    commonDao.insert(SERVICEID_PREFIX + "updatePriorityList", entity);
		}
	    
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
