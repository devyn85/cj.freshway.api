package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsPurchaseDCNewAvgResDto;
import cjfw.wms.ms.dto.MsPurchaseDCNewReqDto;
import cjfw.wms.ms.dto.MsPurchaseDCNewResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.06.24 
 * @description : 기준정보 > 상품기준정보 > 수급마스터관리(New) Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsPurchaseDCNewService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msPurchaseDCNewService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 수발주마스터 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsPurchaseDCNewResDto> getMasterList(MsPurchaseDCNewReqDto dto) {
		List<MsPurchaseDCNewResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
		
	
	/**
	 * @description : 수발주마스터 월평균 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsPurchaseDCNewAvgResDto> getMasterAvgList(MsPurchaseDCNewReqDto dto) {
		dto.setDays(getDaysOfMonth(dto.getYyyymm()));
		List<MsPurchaseDCNewAvgResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterAvgList", dto);
		return result;
	}
	
	private List<String> getDaysOfMonth(String yyyymm){
		List<String> days = new ArrayList<String>();
	    for (int i = 1; i <= 31; i++) {
	        days.add(String.format("%02d", i)); 
	    }
		return days;
	}
	
}
