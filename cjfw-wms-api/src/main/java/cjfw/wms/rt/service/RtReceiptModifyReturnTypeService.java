package cjfw.wms.rt.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.rt.dto.RtReceiptModifyReturnTypeReqDto;
import cjfw.wms.rt.dto.RtReceiptModifyReturnTypeResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.09.10
 * @description :반품회수/미회수변경  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RtReceiptModifyReturnTypeService {
    private final UserContext userContext;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPRT_RECEIPT";
    private transient static final String TEMPTABLETYPE = "RT";
    private transient static final String PROCESSTYPE = "";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(RtReceiptModifyReturnTypeService.class.getSimpleName()) + ".";

    /**
     * @description : 반품회수/미회수변경 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.10 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 반품회수/미회수변경 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.10 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    @Transactional
    public String saveMasterList(RtReceiptModifyReturnTypeReqDto paramDto) throws Exception {
        for(RtReceiptModifyReturnTypeResDto dto : paramDto.getSaveMasterList()) {
            commonDao.update(SERVICEID_PREFIX + "saveMasterList", dto);
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
