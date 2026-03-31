package cjfw.wms.cm.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.dto.CmCodeDtlReqDto;
import cjfw.wms.cm.dto.CmCodeReqDto;
import cjfw.wms.cm.dto.CmMainCodeResDto;
import cjfw.wms.cm.entity.CmCodeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.04.30 
 * @description : 기준정보 > 기타기준정보 > 코드마스터 목록 조회 및 저장 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmCodeService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmCodeService.";

	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 코드 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<CmMainCodeResDto> getCodeHeaderList(CmCodeReqDto cmCodeReqDto) {
		// 검색 기준에 따른 쿼리 분류
		if (StringUtils.isNotEmpty(cmCodeReqDto.getBasecode()) || StringUtils.isNotEmpty(cmCodeReqDto.getBasedescr())) {
			return commonDao.selectList(SERVICEID_PREFIX + "getCodeHeaderList2", cmCodeReqDto);
		} else {
			return commonDao.selectList(SERVICEID_PREFIX + "getCodeHeaderList", cmCodeReqDto);
		}
	}
	
	/**
	 * @description : 코드 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<CmMainCodeResDto> getCodeDetailList(CmCodeReqDto cmCodeReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getCodeDetailList", cmCodeReqDto);
	}
	
	/**
	 * @description : 그룹 코드 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@CacheEvict(cacheNames = "userCode", allEntries = true)
	public String saveCmGrpCode(CmCodeReqDto cmCodeReqDto) {
		if (null != cmCodeReqDto.getCodeGrpList()) {
			for (CmCodeDtlReqDto codeMst : cmCodeReqDto.getCodeGrpList()) {
				CmCodeEntity cmCodeEntity = ModelMapperUtil.map(codeMst, userContext, CmCodeEntity.class);
				if ((CanalFrameConstants.INSERT).equals(codeMst.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertCmGrpCode", cmCodeEntity);
				} else if ((CanalFrameConstants.UPDATE).equals(codeMst.getRowStatus())) {
					// 데이터번호 빈값 체크
					if (!"".equals(cmCodeEntity.getSerialkey())) {
						commonDao.insert(SERVICEID_PREFIX +"updateCmGrpCode", cmCodeEntity);
					}
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 상세 코드 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@CacheEvict(cacheNames = "userCode", allEntries = true)
	public String saveCmDtlCode(CmCodeReqDto cmCodeReqDto) {
		if (null != cmCodeReqDto.getCodeDtlList()) {
			for (CmCodeDtlReqDto codeMst : cmCodeReqDto.getCodeDtlList()) {
				CmCodeEntity cmCodeEntity = ModelMapperUtil.map(codeMst, userContext, CmCodeEntity.class);
				
				// START.코드.체크
				if (!CanalFrameConstants.DELETE.equals(codeMst.getRowStatus())) {
					checkCode(codeMst);
				}
				// END.코드.체크
				
				if ((CanalFrameConstants.INSERT).equals(codeMst.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertCmDtlCode", cmCodeEntity);
				} else if ((CanalFrameConstants.UPDATE).equals(codeMst.getRowStatus())) {
					// 데이터번호 빈값 체크
					if (!"".equals(cmCodeEntity.getSerialkey())) {
						commonDao.insert(SERVICEID_PREFIX +"updateCmDtlCode", cmCodeEntity);
					}
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 코드 체크
	 * @issues :<pre> 상품코드 정보 등을 입력 시 유효성 체크
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.02 sss (kduimux@cj.net) 생성 </pre>
	 */
	public void checkCode(CmCodeDtlReqDto dto) {
		String codelist = StringUtil.nvl(dto.getCodelist());
		String skuCheckYn = "N";
		
		if ("N_DELIVERY_CUST_SKU".equals(codelist)) { // N배송 고객사 상품
			skuCheckYn = "Y";
			dto.setVal1(dto.getData2()); // 상품코드 세팅
		}
		
		if ("Y".equals(skuCheckYn)) { // 상품코드 유효성 체크
			// START.Master.체크.상품
			List<Map<String, Object>> chkListMap = commonDao.selectList(SERVICEID_PREFIX + "checkCode01", dto);
			String chk1 = (String) chkListMap.get(0).get("CHK1"); // 체크.택배접수여부
			log.debug("chk1(체크.상품):{}", chk1);
			String moreMsg = "\n상품코드 : [" + dto.getData2() + "]";
			
			if ("Y".equals(chk1)) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] {"[매존재 상품코드]"})+moreMsg); // 해당 정보가 없어 처리할 수 없습니다. - {0}
			// END.Master.체크.상품
		}
	}

}
