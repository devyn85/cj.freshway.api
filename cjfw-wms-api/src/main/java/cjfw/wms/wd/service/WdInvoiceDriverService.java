package cjfw.wms.wd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.ms.service.MsCreditInfoTotalService;
import cjfw.wms.wd.dto.WdInvoiceDetailResDto;
import cjfw.wms.wd.dto.WdInvoiceReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.11.07
 * @description : 납품서출력(배송기사용) Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdInvoiceDriverService {

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "wdInvoiceDriverService.";
    private transient static final String SERVICEID_PREFIX2 = "wdInvoiceService.";
    /**
     * 프로시져명 - 패키지호출
     */
    private transient static final String PAKAGE_NAME = "SPWD_INVOICE";
    /**
     * 프로세스타임
     */
    private transient static final String PROCESSTYPE = "WD_INVOICECUSTTOTAL";
    /**
     * 프로세스타임-거래처별
     */
    private transient static final String PROCESSTYPE2 = "WD_INVOICECUST";
    /**
     * 임시테이블 타입
     */
    private transient static final String TEMPTABLETYPE = "WD";

    /**
     * 공통.CommonDao
     */
    private final CommonDao commonDao;
    /**
     * 공통.UserContext
     */
    private final UserContext userContext;
    /**
     * 공통.service
     */
    private final CmCommonService cmCommonService;
    /**
     * 기준정보.여신정보.service
     */
    private final MsCreditInfoTotalService msCreditInfoTotalService;

    /**
     * @description : 납품서출력(배송기사용) 상세목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public List<WdInvoiceDetailResDto> getDetailList(WdInvoiceReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
    }
}
