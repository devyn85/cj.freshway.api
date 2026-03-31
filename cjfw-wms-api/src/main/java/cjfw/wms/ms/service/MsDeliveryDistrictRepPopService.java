package cjfw.wms.ms.service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsDistrictGroupPopReqDto;
import cjfw.wms.ms.dto.MsDistrictGroupPopResDto;
import cjfw.wms.ms.service.district.DistrictProcessorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.01 
 * @description : 기준정보 > 배송 권역관리 > POP Service 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.01 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsDeliveryDistrictRepPopService {
	
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * 
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msDeliveryDistrictRepPopService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
    private final DistrictProcessorFactory<MsDistrictGroupPopReqDto> districtProcessorFactory;
	
	/**
	 * @description : POP 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.01 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsDistrictGroupPopResDto> getMasterList(MsDistrictGroupPopReqDto dto){
        dto.setGMultiDccodeList(dto.getDccode().split(","));
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}


	public Boolean getRepPopExists(MsDistrictGroupPopReqDto dto) {
		String result = commonDao.selectOne(SERVICEID_PREFIX + "existsRepPop", dto);
		return result != null;
	}

    public String saveMasterList(List<MsDistrictGroupPopReqDto> dtoList) {
        return districtProcessorFactory.saveMasterList(dtoList);
    }
	
	/**
	 * @description : POP 저장/업데이트/삭제 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.01 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public Map<String, String> getTodateChildImpact(List<MsDistrictGroupPopReqDto> dtoList) {
		for (MsDistrictGroupPopReqDto dto : dtoList) {
			int count = commonDao.selectOne(SERVICEID_PREFIX + "countPopChildBeyondTodate", dto);
			if (count > 0) {
				return Map.of("affectedYn", "Y");
			}
		}
		return Map.of("affectedYn", "N");
	}


}
