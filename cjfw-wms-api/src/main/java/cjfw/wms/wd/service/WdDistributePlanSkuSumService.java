package cjfw.wms.wd.service;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net) 
 * @date : 2025.05.19 
 * @description : 미출 예정 확인 (상품별 합계) Service Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.19 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class WdDistributePlanSkuSumService {

	private final CommonDao commonDao;
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(WdDistributePlanSkuSumService.class.getSimpleName()) + ".";
	
	
	/** @description : 미출예정확인 조회 기능을 구현한 Method 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.19 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getWdDistributePlanSkuSumList(T reqDto) {
		
		return commonDao.selectList(SERVICEID_PREFIX + "getWdDistributePlanSkuSumList", reqDto);
	}
	

}
