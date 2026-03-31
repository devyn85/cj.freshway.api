package cjfw.wms.wd.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.09
 * @description : 일배PO/SO연결모니터링  Service Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.09 YangChangHwan (iamai@cj.net) ����
 *         </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdPoSoMonitoringService {

	private final CommonDao commonDao;

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 *
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(WdPoSoMonitoringService.class.getSimpleName()) + ".";

	/** @description : 일배PO/SO연결모니터링 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getWdPoSoMonitoringList( T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getWdPoSoMonitoringList", reqDto);
	}

	/** @description : 일배PO/SO연결모니터링 Gird1 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getWdPoSoMonitoringGrid1List(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getWdPoSoMonitoringGrid1List", reqDto);
	}

}
