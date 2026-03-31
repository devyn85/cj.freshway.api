package cjfw.wms.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.cm.dto.CmFileUploadReqDto;
import cjfw.wms.cm.dto.CmFileUploadResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.29 
 * @description : 업로드 파일 정보 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmFileUploadService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmFileUploadService.";

	private final CommonDao commonDao;

	/**
	 * @description : 파일 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<CmFileUploadResDto> getFileInfoList(CmFileUploadReqDto cmFileUploadReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getFileInfoList", cmFileUploadReqDto);
	}

}
