package cjfw.wms.portal.common.sample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.portal.common.sample.dto.ZoneManagerGetReqDto;
import cjfw.wms.portal.common.sample.dto.ZoneManagerGetResDto;
import cjfw.wms.portal.common.sample.dto.ZoneManagerSaveReqDto;
import cjfw.wms.portal.common.sample.entity.ZoneManagerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ZoneManagerService {

	private final UserContext userContext;

	private final CommonDao commonDao;

	/**
	 * 
	 * @description : 샘플 목록조회 기능을 구현한 Method
	 * @issues : ----------------------------------------------------------- DATE
	 *         AUTHOR MAJOR_ISSUE
	 *         -----------------------------------------------------------
	 *         2025.04.21 KimSunHo (sunhokim6229@cj.net) 생성
	 */
	public List<ZoneManagerGetResDto> getZoneList(ZoneManagerGetReqDto zoneManagerGetReqDto) {

		List<ZoneManagerGetResDto> list = commonDao.selectList("zoneManagerService.getZoneManagerList",
				zoneManagerGetReqDto);

		return list;
	}

	/**
	 * 
	 * @description : 샘플 저장 기능을 구현한 Method
	 * @issues : ----------------------------------------------------------- DATE
	 *         AUTHOR MAJOR_ISSUE
	 *         -----------------------------------------------------------
	 *         2025.04.21 KimSunHo (sunhokim6229@cj.net) 생성
	 */
	public String saveZone(ZoneManagerSaveReqDto zoneManagerSaveReqDto) {

		List<ZoneManagerSaveReqDto.Zone> zones = zoneManagerSaveReqDto.getZones();

		if (zones != null) {
			for (ZoneManagerSaveReqDto.Zone zone : zones) {

				ZoneManagerEntity entity = ModelMapperUtil.map(zone, userContext, ZoneManagerEntity.class);

				if ((CanalFrameConstants.INSERT).equals(zone.getRowStatus())) {
					ZoneManagerGetReqDto reqDto = new ZoneManagerGetReqDto();
					reqDto.setSchDccode(zone.getDccode());
					reqDto.setSchZone(zone.getZone());

					int cnt = commonDao.getTotalCount("zoneManagerService.getZoneManagerList", reqDto);
					if (cnt > 0) {
						// 중복된 데이터 체크
						throw new UserHandleException("MSG.COM.VAL.067", new String[] { "LABEL.COM.USR.INF" });
					}

					commonDao.insert("zoneManagerService.insert", entity);
				} else if ((CanalFrameConstants.UPDATE).equals(zone.getRowStatus())) {
					commonDao.update("zoneManagerService.update", entity);
				} else if ((CanalFrameConstants.DELETE).equals(zone.getRowStatus())) {
					commonDao.delete("zoneManagerService.deletee", entity);
				}
			}
		}

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
