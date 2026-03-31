package cjfw.wms.mg.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description :재고변경사유현황  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class MgModifyLogService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(MgModifyLogService.class.getSimpleName()) + ".";
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	
	/** @description : 재고변경사유현황 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getTab1MasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getTab1MasterList", reqDto);
	}

	/** @description : 재고변경사유현황 상세 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getTab2MasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getTab2MasterList", reqDto);
	}

}
