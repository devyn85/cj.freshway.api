package cjfw.wms.wd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.wd.dto.WdDistributePlanNewReqDto;
import cjfw.wms.wd.dto.WdDistributePlanNewResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author 		: YeoSeungCheol (pw6375@cj.net) 
 * @date 		: 2025.11.06 
 * @description : 출고현황 - 미출예정확인(New) Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.06 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdDistributePlanNewService {

    private static final String SERVICEID_PREFIX = "wdDistributePlanNewService.";

    private final CommonDao commonDao;

    /**
	 * @description : 미출예정확인(New) - 미출예정 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.06 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<WdDistributePlanNewResDto> getMasterListTab1(WdDistributePlanNewReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterListTab1", dto);
    }

    /**
	 * @description : 미출예정확인(New) - 상품별 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.06 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<WdDistributePlanNewResDto> getMasterListTab2(WdDistributePlanNewReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterListTab2", dto);
    }

    /**
	 * @description : 미출예정확인(New) - 미출예정 조회 (양산(2), 수도권(3), 장성(4), 제주(5))
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.06 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<WdDistributePlanNewResDto> getMasterListTab1WithCondition(WdDistributePlanNewReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterListTab1WithCondition", dto);
    }
}
