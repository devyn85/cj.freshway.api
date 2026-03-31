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
import cjfw.wms.ms.dto.MsSkuChainExcelResDto;
import cjfw.wms.ms.dto.MsSkuChainReqDto;
import cjfw.wms.ms.dto.MsSkuChainResDto;
import cjfw.wms.ms.entity.MsSkuChainEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.06.26 
 * @description : 기준정보 > 상품기준정보 > PLT변환값 마스터 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsSkuChainService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msSkuChainService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : PLT 변환값 마스터 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsSkuChainResDto> getMasterList(MsSkuChainReqDto dto) {
		List<MsSkuChainResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
	
	
	/**
	 * @description :  상품 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.06 생성 </pre>
	 */
	public MsSkuChainResDto getSkuInfo(MsSkuChainReqDto dto) {
		MsSkuChainResDto result = commonDao.selectOne(SERVICEID_PREFIX + "getSkuInfo", dto);
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
	public List<MsSkuChainExcelResDto> getValidateSaveList(List<MsSkuChainReqDto> dtoList) {
		List<MsSkuChainExcelResDto> result = new ArrayList<MsSkuChainExcelResDto>(); 
		if(dtoList != null) {
			result = commonDao.selectList(SERVICEID_PREFIX + "getValidateSaveList", dtoList);
			//중복확인
			Set<String> uniqueKeys = new HashSet<>();
			for(MsSkuChainExcelResDto resultDto : result) {
				String dccode = resultDto.getDccode(); 
			    String sku = resultDto.getSku();
			    String plant = resultDto.getPlant();
			    
			    // 유니크 키 조합 (예시: 언더바 "_"로 연결)
			    String compositeKey = dccode + "_" + plant + "_" + sku;
			    
				if (uniqueKeys.contains(compositeKey)) {
		            // 이 시점에서는 현재 데이터에 중복 오류를 즉시 설정하고,
		            // 이전 데이터들은 루프가 끝난 후 한 번에 업데이트
		            resultDto.setDuplicateChk("N");
		        } else {
		            // 처음 등장한 유니크 키는 uniqueCustKeys에 추가
		        	uniqueKeys.add(compositeKey);
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
	 * 2025.06.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<MsSkuChainReqDto> dtoList) {
		if (null != dtoList) {
			for (MsSkuChainReqDto dto : dtoList) {
				MsSkuChainEntity entity = ModelMapperUtil.map(dto, userContext, MsSkuChainEntity.class);
				commonDao.update(SERVICEID_PREFIX +"saveMasterList", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
