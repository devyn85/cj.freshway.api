package cjfw.wms.rt.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.21
 * @description :반품판정현황  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.21 KimDongHyeon (tirran123@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class RtQCConfirmResultService {

	private final CommonDao commonDao;

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 *
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(RtQCConfirmResultService.class.getSimpleName()) + ".";

	/** @description : 반품판정현황 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.21 KimDongHyeon (tirran123@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getMasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getMasterList", reqDto);
	}

	/** @description : 반품판정현황 클레임사유 세 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.21 KimDongHyeon (tirran123@cj.net) 생성 </pre>
	 */
	public <R, T> List<R> getClaimTypeList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getClaimTypeList", reqDto);
	}

	/** @description : 반품판정현황 클레임사유 소 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.21 KimDongHyeon (tirran123@cj.net) 생성 </pre>
	 */
	public <R, T> List<R> getClaimTypeList2(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getClaimTypeList2", reqDto);
	}
}
