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
import cjfw.wms.ms.dto.MsSkuChainMoqReqDto;
import cjfw.wms.ms.dto.MsSkuChainMoqResDto;
import cjfw.wms.ms.entity.MsSkuChainMoqEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.06.24 
 * @description : 기준정보 > 상품기준정보 > MOQ/LT마스터 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsSkuChainMoqService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msSkuChainMoqService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 체인 상품 정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsSkuChainMoqResDto> getMasterList(MsSkuChainMoqReqDto msSkuChainMoqReqDto) {
		List<MsSkuChainMoqResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", msSkuChainMoqReqDto);
		return result;
	}
	
	/**
	 * @description : 상품코드 유효성검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsSkuChainMoqResDto> getValidateSaveList(List<MsSkuChainMoqReqDto> dtoList) {
		List<MsSkuChainMoqResDto> result = new ArrayList<MsSkuChainMoqResDto>();
		if(dtoList != null) {
			result = commonDao.selectList(SERVICEID_PREFIX + "getValidateSaveList", dtoList);
			//중복확인
			Set<String> uniqueKeys = new HashSet<>();
			Set<String> duplicatedKeys = new HashSet<>();
			for(MsSkuChainMoqResDto resultDto : result) {
				String dccode = resultDto.getDccode(); 
			    String sku = resultDto.getSku(); 
			    
			    // 유니크 키 조합 (예시: 언더바 "_"로 연결)
			    String compositeKey = dccode + "_" + sku;
			    
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
		        for (MsSkuChainMoqResDto editDto : result) {
		        	String dccode = editDto.getDccode();
		            String sku = editDto.getSku();
		            
		            // 유니크 키 조합
		            String compositeKey = dccode + "_" + sku;
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
	 * @description :상품 정보 MERGE
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<MsSkuChainMoqReqDto> dtoList) {
		if (null != dtoList) {
			for(MsSkuChainMoqReqDto dto : dtoList) {
				MsSkuChainMoqEntity entity = ModelMapperUtil.map(dto, userContext, MsSkuChainMoqEntity.class);
				commonDao.selectOne(SERVICEID_PREFIX +"saveMasterList", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
