package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsCenterDistrictDcOrdGrpReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictHistoryReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictHistoryResDto;
import cjfw.wms.ms.dto.MsCenterDistrictHjdongReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsCenterDistrictHistoryService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * 
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCenterDistrictHistoryService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description : 센터권역 이력 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public Page<MsCenterDistrictHistoryResDto> getMasterList(MsCenterDistrictHistoryReqDto dto, Page<?> page){
		
		if(dto.getGMultiDccode() != null) {
			dto.setGMultiDccodeList(dto.getGMultiDccode().split(","));
		}
		// like 검색조건용 
		if(dto.getSearchHjdongCd() != null && !"".equals(dto.getSearchHjdongCd())) {
	        if (dto.getSearchHjdongCd().endsWith("00000000")) {
	            dto.setSearchHjdongCd(dto.getSearchHjdongCd().substring(0, 2));
	        }
	        if (dto.getSearchHjdongCd().endsWith("00000")) {
	        	dto.setSearchHjdongCd(dto.getSearchHjdongCd().substring(0, 5));
	        }
	        if (dto.getSearchHjdongCd().endsWith("00")) {
	        	dto.setSearchHjdongCd(dto.getSearchHjdongCd().substring(0, 8));
	        }
		}
        
		return commonDao.selectPageList(SERVICEID_PREFIX + "getMasterList", dto, page);
	}
	
	/**
	 * @description : 센터권역 이력 저장 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<MsCenterDistrictHistoryReqDto> dtoList) {
		for(MsCenterDistrictHistoryReqDto dto: dtoList) {
			if(dto.getRowStatus().equals(CanalFrameConstants.INSERT)) {
				commonDao.insert(SERVICEID_PREFIX + "saveMasterList", dto);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 센터권역 이력 저장 (행정동 저장 기준) 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public String saveMasterList(MsCenterDistrictHjdongReqDto dto) {
		commonDao.insert(SERVICEID_PREFIX + "saveMasterListByHjdong", dto);
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 센터권역 이력 저장 (주문그룹 우선순위 저장 기준) 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public String saveMasterList(MsCenterDistrictDcOrdGrpReqDto dto) {
		commonDao.insert(SERVICEID_PREFIX + "saveMasterListByDcOrdGrp", dto);
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
