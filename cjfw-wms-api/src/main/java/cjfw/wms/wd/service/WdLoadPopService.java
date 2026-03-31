package cjfw.wms.wd.service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.wd.dto.WdLoadPopReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdLoadPopService {
    private static final String SERVICEID_PREFIX = "wdLoadPopService.";

    private final CommonDao commonDao;
    private final UserContext userContext;


    public WdLoadPopReqDto getLoadDirectionsStatus(WdLoadPopReqDto dto) {
        return commonDao.selectOne(SERVICEID_PREFIX + "getLoadDirectionsStatus", dto);
    }

    public String updateLoadDirectionsStatus(WdLoadPopReqDto dto) {
        commonDao.update(SERVICEID_PREFIX + "updateLoadDirectionsStatus", dto);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
