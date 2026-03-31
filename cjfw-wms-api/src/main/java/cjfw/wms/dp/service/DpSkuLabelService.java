package cjfw.wms.dp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.dp.dto.DpSkuLabelReqDto;
import cjfw.wms.dp.dto.DpSkuLabelResDetailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.08.07
 * @description :입고라벨출력  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DpSkuLabelService {
    private final UserContext userContext;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPDP_INSPECTION";
    private transient static final String TEMPTABLETYPE = "DP";
    private transient static final String PROCESSTYPE = "";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(DpSkuLabelService.class.getSimpleName()) + ".";

    /**
     * @description : 입고라벨출력 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 입고라벨출력 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", reqDto);
    }

    /**
     * @description : 입고라벨출력 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", reqDto);
    }

    /**
     * @description : 입고라벨출력 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList2", reqDto);
    }

    /**
     * @description : 입고라벨출력 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getPrintDetailList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList2", reqDto);
    }

    /**
     * @description : 입고라벨출력 예외저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String saveDpSkuLabel(DpSkuLabelReqDto paramDto) throws Exception {
        DpSkuLabelResDetailDto dto = paramDto.getSaveList().get(0);
        dto.setEditwho(paramDto.getGUserId());
        if("D".equals(dto.getRowStatus())) {
            commonDao.update(SERVICEID_PREFIX + "deleteDpSkuLabel", dto);
        }else {
            commonDao.update(SERVICEID_PREFIX + "saveDpSkuLabel", dto);
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

        /**
     * @description : 입고라벨출력 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getZone(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getZone", reqDto);
    }

    /**
     * @description : 입고라벨출력 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getPrintList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPrintList", reqDto);
    }
}
