package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsOutGroupSettingReqDto;
import cjfw.wms.ms.dto.MsOutGroupSettingResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsOutGroupSettingService {
	/*
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 * 
	 */
	private static final String SERVICEID_PREFIX = "msOutGroupSettingService.";
	private final UserContext userContext;
	private final CommonDao commonDao;
	
	/**
	 * @description : 실비차 목록 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public MsOutGroupSettingResDto getMasterList (MsOutGroupSettingReqDto dto) {
		MsOutGroupSettingResDto result = new MsOutGroupSettingResDto();
		
		List<MsOutGroupSettingResDto.OutGroupSetting> outGroupSettingList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		
		List<MsOutGroupSettingResDto.OutGroup> outGroupList = outGroupSettingList.stream().map(outGroupSetting -> 
			new MsOutGroupSettingResDto.OutGroup(outGroupSetting.getOutGroupCd(), outGroupSetting.getOutGroupNm())
		).toList();
		
		result.setOutGroupSettingList(outGroupSettingList);
		result.setOutGroupList(outGroupList);
	
		return result;
	}
	
	/**
	 * @description : 실비차 배차 설정 갱신 (업데이트만)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public String updateMasterList(List<MsOutGroupSettingReqDto> dtoList) {
		for(MsOutGroupSettingReqDto dto : dtoList) {
			commonDao.update(SERVICEID_PREFIX + "updateMasterList", dto);
		}
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
	
}
