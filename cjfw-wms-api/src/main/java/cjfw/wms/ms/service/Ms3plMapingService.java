package cjfw.wms.ms.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.service.CmCommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.11.18
 * @description :3PL전산기준목록  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.18 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class Ms3plMapingService {
    private final UserContext userContext;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPMS_RECEIPT";
    private transient static final String TEMPTABLETYPE = "MS";
    private transient static final String PROCESSTYPE = "";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(Ms3plMapingService.class.getSimpleName()) + ".";

    /**
     * @description : 3PL전산기준목록 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.18 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList(T reqDto) {
        commonDao.insert(SERVICEID_PREFIX + "saveMasterList", reqDto);
        commonDao.update(SERVICEID_PREFIX + "updateFlag", reqDto);
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }
}
