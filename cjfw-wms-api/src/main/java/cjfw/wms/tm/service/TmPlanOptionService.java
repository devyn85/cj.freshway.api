package cjfw.wms.tm.service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.dto.TmDispatchOptionsReqDto;
import cjfw.wms.tm.entity.TmPlanOptionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TmPlanOptionService {
    private static final String DISPATCH_OPTION_SERVICEID_PREFIX = "tmDispatchOptionsService.";
    private final CommonDao commonDao;

    public TmPlanOption getPlanOption(TmDispatchOptionsReqDto req) {

        // 유저 설정 배차 옵션 조회
        List<TmPlanOptionEntity> dispatchOptionListDto = commonDao.selectList(
                DISPATCH_OPTION_SERVICEID_PREFIX + "getMasterList", req);

        if(dispatchOptionListDto.isEmpty()) {
            dispatchOptionListDto = commonDao.selectList(
                    DISPATCH_OPTION_SERVICEID_PREFIX + "getDefaultMasterList", req);
        }

        TmPlanOption planOption = TmPlanOption.of(dispatchOptionListDto);
        log.info("[{}] {}", req.getDccode(), planOption);
        return planOption;
    }
}
