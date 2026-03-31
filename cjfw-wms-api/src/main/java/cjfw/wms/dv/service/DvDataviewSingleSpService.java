package cjfw.wms.dv.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.dv.dto.DvDataviewSingleSpResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.09
 * @description : 자동창고처리현황 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.09 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class DvDataviewSingleSpService {

	private final CommonDao commonDao;

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(DvDataviewSingleSpService.class.getSimpleName()) + ".";


	/** @description : 자동창고처리현황 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.09 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public <T> List<DvDataviewSingleSpResDto> getDvDataviewSingleSpList(T reqDto) {

		return commonDao.selectList(SERVICEID_PREFIX + "getDvDataviewSingleSpList", reqDto );
	}
}
