package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsCustBrandExcelResDto;
import cjfw.wms.ms.dto.MsCustBrandReqDto;
import cjfw.wms.ms.dto.MsCustBrandResDto;
import cjfw.wms.ms.entity.MsCustBrandEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsCustBrandService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERIVCEID_PREFIX = "msCustBrand.";
	private final UserContext userContext;
	private final CommonDao commonDao;
	
	/**
	 * @description : 본점별 브랜드 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public List<MsCustBrandResDto> getMasterList (MsCustBrandReqDto msCustBrandReqDto) {
		return commonDao.selectList(SERIVCEID_PREFIX + "getMasterList", msCustBrandReqDto);
	}
	
	/**
	 * @description : 본점별 브랜드 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<MsCustBrandReqDto> saveList) {
	    if (ObjectUtils.isNotEmpty(saveList)) {
	        
	        for (MsCustBrandReqDto saveDto : saveList) {
//				MsCustBrandEntity entity = ModelMapperUtil.map(dto, userContext, MsCustBrandEntity.class);
				commonDao.update(SERIVCEID_PREFIX + "saveMasterList", saveDto);
				log.info("merge result : {}", saveDto.toString());
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	/**
	 * 본점별브랜드 엑셀 유효성검사
	 */
	public List<MsCustBrandExcelResDto> validateExcelList(List<MsCustBrandReqDto> list) {
		List<MsCustBrandExcelResDto> result = new ArrayList<MsCustBrandExcelResDto>();
		
		if (list != null) {
			
		}

		if (list != null) {
			for (MsCustBrandReqDto dto : list) {
				MsCustBrandExcelResDto row = commonDao.selectOne(SERIVCEID_PREFIX + "validateExcelList", dto);
				result.add(row);
			}
		}
		return result;
	}

	/**
	 * 본점별브랜드 엑셀 저장
	 */
	public String saveExcelList(List<MsCustBrandReqDto> list) {
		if (list != null) {
			for (MsCustBrandReqDto dto : list) {
				MsCustBrandEntity entity = ModelMapperUtil.map(dto, userContext, MsCustBrandEntity.class);
				commonDao.update(SERIVCEID_PREFIX + "saveExcelList", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
