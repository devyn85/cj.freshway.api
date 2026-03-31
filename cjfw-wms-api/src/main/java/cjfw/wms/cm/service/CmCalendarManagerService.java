package cjfw.wms.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmCalendarManagerReqDto;
import cjfw.wms.cm.dto.CmCalendarManagerResDto;
import cjfw.wms.cm.entity.CmCalendarManagerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CmCalendarManagerService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "cmCalendarManager.";
	private final UserContext userContext;
	private final CommonDao commonDao;

	/**
	 * @description : 발주용휴일관리(목록) 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public List<CmCalendarManagerResDto> getMasterList (CmCalendarManagerReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
	}
	
	/**
	 * @description : 발주용휴일관리(목록) 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<CmCalendarManagerReqDto> list) {
		for (CmCalendarManagerReqDto dto : list) {
			if (null != list) {
				CmCalendarManagerEntity entity = ModelMapperUtil.map(dto, userContext, CmCalendarManagerEntity.class);

				commonDao.update(SERVICEID_PREFIX + "saveMasterList", entity);
			}
			
			// dccode가 "1000"일 경우 추가적으로 kxifupdate를 호출
			if ("1000".equals(dto.getDccode())) {
				commonDao.update(SERVICEID_PREFIX + "kxifupdate", dto);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
