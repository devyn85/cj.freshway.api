package cjfw.wms.om.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.om.dto.OmAutoOrderMonitoringReqDto;
import cjfw.wms.om.dto.OmAutoOrderMonitoringResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.08.12
 * @description :자동발주모니터링  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.12 JiSooKim (jskim14@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class OmAutoOrderMonitoringService {

	private final CommonDao commonDao;

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 *
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(OmAutoOrderMonitoringService.class.getSimpleName()) + ".";

	/** @description : 자동발주모니터링 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.12 JiSooKim (jskim14@cj.net) 생성 </pre>
	*/
	public List<OmAutoOrderMonitoringResDto> getMasterList(OmAutoOrderMonitoringReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getMasterList", reqDto);
	}


}
