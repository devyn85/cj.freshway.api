package cjfw.wms.dp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.07
 * @description :입고라벨출력(광역)  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class DpSkuLabelSTOService {

	private final CommonDao commonDao;

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 *
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(DpSkuLabelSTOService.class.getSimpleName()) + ".";

	/** @description : 입고라벨출력(광역) 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getMasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getMasterList", reqDto);
	}

	/** @description : 입고라벨출력(광역) 상세 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getDetailList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getDetailList", reqDto);
	}

}
