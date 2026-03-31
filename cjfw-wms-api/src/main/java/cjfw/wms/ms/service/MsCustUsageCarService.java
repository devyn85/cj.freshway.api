package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsCustUsageCarReqDto;
import cjfw.wms.ms.dto.MsCustUsageCarResDto;
import cjfw.wms.ms.entity.MsCustUsageCarEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.08.29 
 * @description : 기준정보 > 거래처기준정보 > 거래처별전용차량정보  Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCustUsageCarService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCustUsageCarService.";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	
	/**
	 * @description : 거래처별전용차량정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCustUsageCarResDto> getMasterList(MsCustUsageCarReqDto dto) {
		List<MsCustUsageCarResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
	
	
	/**
	 * @description :거래처별전용차량정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<MsCustUsageCarReqDto> dtoList) {
		if (null != dtoList) {
			for(MsCustUsageCarReqDto dto : dtoList) {
				MsCustUsageCarEntity entity = ModelMapperUtil.map(dto, userContext, MsCustUsageCarEntity.class);
				commonDao.update(SERVICEID_PREFIX + "saveMasterList", entity);
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 엑셀업로드 유효성검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.21 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCustUsageCarResDto> getValidateSaveList(List<MsCustUsageCarReqDto> dtoList) {
		List<MsCustUsageCarResDto> result = new ArrayList<MsCustUsageCarResDto>();
		if(dtoList != null) {
			result = commonDao.selectList(SERVICEID_PREFIX + "getValidateSaveList", dtoList);
			//중복확인
			Set<String> uniqueKeys = new HashSet<>();
			Set<String> duplicatedKeys = new HashSet<>();
			for(MsCustUsageCarResDto resultDto : result) {
				String dccode = resultDto.getDccode(); 
			    String custkey = resultDto.getCustkey(); 
			    String carno = resultDto.getCarno();
			    
			    // 유니크 키 조합 (예시: 언더바 "_"로 연결)
			    String compositeKey = dccode + "_" + custkey + "_" + carno;
			    
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
//		    if (!duplicatedKeys.isEmpty()) {
//		        for (MsCustUsageCarResDto editDto : result) {
//		        	String dccode = editDto.getDccode();
//		            String custkey = editDto.getCustkey();
//		            String carno = editDto.getCarno();
//		            // 유니크 키 조합
//		            String compositeKey = dccode + "_" + custkey + "_" + carno;
//		            // 중복 리스트에 포함된 유니크 키인 경우 (첫 번째 건 포함)
//		            if (duplicatedKeys.contains(compositeKey)) {
//		                // 이미 오류가 아닌 경우에만 중복 오류를 설정
//		            	editDto.setDuplicateChk("N");
//		            }
//		        }
//		    }
		}
		return result;
	}
	
}
