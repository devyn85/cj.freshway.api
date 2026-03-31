/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.timezone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.TimeZoneUtil;
import cjfw.wms.comfunc.func.timezone.dto.SampleTimeZoneGetResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SampleTimeZoneService {

    private final CommonDao commonDao;
    
    private final UserContext userContext;

    public List<SampleTimeZoneGetResDto> getList() {
        // TimeZoneUtil 사용 코드 샘플용 로그
        String timezone = "UTC"; // "Asia/Seoul", "US/Central"
        log.info("{}", TimeZoneUtil.nowFromZone(timezone, "yyyy-MM-dd HH:mm:ss"));

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userId", userContext.getUserId());
        List<SampleTimeZoneGetResDto> list = commonDao.selectList("sampleTimeZoneService.getLoginLogOutList", paramMap);
        return list;
    }
}
