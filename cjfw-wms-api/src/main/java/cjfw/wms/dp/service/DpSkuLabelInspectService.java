package cjfw.wms.dp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.24
 * @description :입고라벨출력(검수)  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class DpSkuLabelInspectService {

	private final CommonDao commonDao;

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 *
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(DpSkuLabelInspectService.class.getSimpleName()) + ".";

	/** @description : 입고라벨출력(검수) 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getDpSkuLabelInspectList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getDpSkuLabelInspectList", reqDto);
	}

	/** @description : 입고라벨출력(검수) Grid1 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getDpSkuLabelInspectGrid1List(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getDpSkuLabelInspectGrid1List", reqDto);
	}

}
