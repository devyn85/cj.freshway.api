package cjfw.wms.dp.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.dp.dto.DpTaskReqDto;
import cjfw.wms.dp.dto.DpTaskResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.31
 * @description :입고검수지정  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DpTaskService {
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
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(DpTaskService.class.getSimpleName()) + ".";

    /**
     * @description : 입고검수지정 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 입고검수지정 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", reqDto);
    }

    /**
     * @description : 입고검수지정 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", reqDto);
    }

    /**
     * @description : 입고검수지정 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList2", reqDto);
    }

    /**
     * @description : 입고검수지정 지정삭제
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String saveDpTask(DpTaskReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        DpTaskReqDto reqDto = ModelMapperUtil.map(paramDto, DpTaskReqDto.class);
        DpTaskResDto data = reqDto.getSaveList().get(0); // 저장리스트의 첫번째 데이터

        DpTaskReqDto dto = new DpTaskReqDto();

        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PLANTYPE", "DCCODE", "STORERKEY", "FROMTASKDT", "TOTASKDT", "CUSTKEY", "SKU", "DEL_YN", "STATUS", "ISSUETYPE"};
        Object[] valueList = {
            data.getPlantype(), // PLANTYPE
            data.getDccode(), // DCCODE
            data.getStorerkey(), // STORERKEY
            StringUtil.nvl(data.getFromtaskdt()), // FROMTASKDT
            StringUtil.nvl(data.getTotaskdt()), // TOTASKDT
            StringUtil.nvl(data.getCustkey()), // CUSTKEY
            StringUtil.nvl(data.getSku()), // SKU
            StringUtil.nvl(data.getDelYn()), // DEL_YN
            "", // STATUS
            StringUtil.nvl(data.getIssuetype()) // ISSUETYPE
        };
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(dto);
        log.info("rv->{}", rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl((String) dto.getResultMessage());
        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);

        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            log.error("▶지정삭제 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"지정삭제"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/


        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
