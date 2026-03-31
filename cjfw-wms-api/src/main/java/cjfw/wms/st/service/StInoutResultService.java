package cjfw.wms.st.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.st.dto.StInoutResultReqDto;
import cjfw.wms.st.dto.StInoutResultResPrintDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description :수불현황  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class StInoutResultService {

	private final CommonDao commonDao;

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 *
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StInoutResultService.class.getSimpleName()) + ".";

	/** @description : 수불현황 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getMasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getMasterList", reqDto);
	}

	/** @description : 수불현황 상세 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getDetailList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getDetailList", reqDto);
	}
	
	/** @description : 수불현황 출력 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  StInoutResultResPrintDto printMasterList(StInoutResultReqDto reqDto) {
		StInoutResultResPrintDto resultDto = new StInoutResultResPrintDto();
		/*START.최종 출력물 데이터 조회*/
		resultDto.setReportList(commonDao.selectList(SERVICEID_PREFIX+"printMasterList", reqDto));
		/*END.최종 출력물 데이터 조회*/
		return resultDto;
	}	

}
