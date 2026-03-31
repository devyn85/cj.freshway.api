package cjfw.wms.dv.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.14
 * @description : OO 기능을 구현한 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.14 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class DvDataviewMultiSpService {

	private final CommonDao commonDao;

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(DvDataviewMultiSpService.class.getSimpleName()) + ".";



	/** @description : 일배입출차이현황 List
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.14 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getDvDataviewMultiSpList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getDvDataviewMultiSpList", reqDto);
	}

	/** @description : 상세 내역 List
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.14 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getDvDataviewMultiSpDetailList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getDvDataviewMultiSpDetailList", reqDto);
	}


}
