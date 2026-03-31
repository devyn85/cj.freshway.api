package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.st.dto.StTplUserReqDto;
import cjfw.wms.st.dto.StTplUserResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkYoSep 
 * @date : 2025.11.04  
 * @description :화주조회 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.04 ParkYoSep 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StTplUserService {

    private transient static final String SERVICEID_PREFIX = "stTplUserService."; 
    
    private final CommonDao commonDao;
    
    private final UserContext userContext;


	/**
	 * @description : 화주 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	public Page<StTplUserResDto> getTplUserPopupList(StTplUserReqDto dto, Page<?> page) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getTplUserPopupList", dto,page);		
	}
	
	public  List<StTplUserResDto>  getMasterList(StTplUserReqDto req) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList" , req);
	}
    
}
