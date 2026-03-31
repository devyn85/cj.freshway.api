package cjfw.wms.ms.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsDeliveryDistrictHistoryReqDto;
import cjfw.wms.ms.dto.MsDeliveryDistrictHistoryResDto;
import cjfw.wms.tm.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 *
 * @author : System Generated
 * @date : 2025.01.XX
 * @description : 배송 권역 이력 조회 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX System Generated 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsDeliveryDistrictHistoryService {

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "msDeliveryDistrictHistoryService.";
    private final CommonDao commonDao;
    private final UserContext userContext;

    /**
     * @description : 배송 권역 이력 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.XX System Generated 생성 </pre>
     */
    public Page<MsDeliveryDistrictHistoryResDto> getHistoryList(MsDeliveryDistrictHistoryReqDto reqDto) {
        if (reqDto.getGMultiDccode() != null && !reqDto.getGMultiDccode().equals("")) {
            reqDto.setGMultiDccodeList(reqDto.getGMultiDccode().split(","));
        }
        Page<MsDeliveryDistrictHistoryResDto> page = PaginationUtil.createPage(
                reqDto.getPageNum(),
                reqDto.getListCount()
        );
        return commonDao.selectPageList(SERVICEID_PREFIX + "getDlvDistrictHisList", reqDto, page);
    }
}

