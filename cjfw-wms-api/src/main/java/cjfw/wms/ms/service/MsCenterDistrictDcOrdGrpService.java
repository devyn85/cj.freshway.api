package cjfw.wms.ms.service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.ms.dto.MsCenterDistrictDcOrdGrpReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictDcOrdGrpResDto;
import cjfw.wms.ms.service.district.DistrictProcessorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsCenterDistrictDcOrdGrpService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * 
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCenterDistrictDcOrdGrpService.";
	private final CommonDao commonDao;
	private final DistrictProcessorFactory<MsCenterDistrictDcOrdGrpReqDto> districtProcessorFactory;
	
	/**
	 * @description : 주문 그룹 우선순위 리스트 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDistrictDcOrdGrpResDto> getMasterList(MsCenterDistrictDcOrdGrpReqDto dto){
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	public List<MsCenterDistrictDcOrdGrpResDto> getOrdGrpMatrixList(MsCenterDistrictDcOrdGrpReqDto dto){
		return commonDao.selectList(SERVICEID_PREFIX + "getOrdGrpMatrixList", dto);
	}

	public List<MsCenterDistrictDcOrdGrpResDto> getDcOrdGrpListByPrDccode(MsCenterDistrictDcOrdGrpReqDto dto){
		return commonDao.selectList(SERVICEID_PREFIX + "getDcOrdGrpListByPrDccode", dto);
	}

	public String saveMasterList(List<MsCenterDistrictDcOrdGrpReqDto> dtoList){
		return districtProcessorFactory.saveMasterList(dtoList);
	}
	
}
