package cjfw.wms.rt.service;

import java.util.ArrayList;
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
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.rt.dto.RtReturnOutReqDto;
import cjfw.wms.rt.dto.RtReturnOutResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.10.13
 * @description :협력사반품지시  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RtReturnOutService {
    private final UserContext userContext;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPRT_RECEIPT";
    private transient static final String TEMPTABLETYPE = "WD";
    private transient static final String PROCESSTYPE = "RETURNOUT";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(RtReturnOutService.class.getSimpleName()) + ".";

    /**
     * @description : 협력사반품지시 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 협력사반품지시 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", reqDto);
    }

    /**
     * @description : 협력사반품지시 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", reqDto);
    }

    /**
     * @description : 협력사반품지시 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList2", reqDto);
    }

    /**
     * @description : 협력사반품지시 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getPrintDetailList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList2", reqDto);
    }

    /**
     * @description : 협력사반품지시 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String saveMaster(RtReturnOutReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        RtReturnOutReqDto reqDto = ModelMapperUtil.map(paramDto, RtReturnOutReqDto.class);
        List<RtReturnOutResDto> saveList = reqDto.getSaveList(); // 저장리스트
        log.info("▶saveList.size->{}", saveList);
        reqDto.setTemptabletype(TEMPTABLETYPE); // 임시테이블타입
        reqDto.setProcesstype(PROCESSTYPE); // 임시테이블타입

        /*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp" + TEMPTABLETYPE, reqDto);

        int chunkSize = 200;
        List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            RtReturnOutResDto dto = saveList.get(i);
            // 임시테이블에 등록(2/3)
            CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
            entity.setProcesstype(reqDto.getProcesstype()); // 프로세스타입
            entity.setTemptabletype(reqDto.getTemptabletype()); // 프로세스타입
            // UI.params

            // START.필수입력 check CHECKYN|DELIVERYDT|DOCNO|SLIPDT|SLIPNO|CARNO|PRIORITY|DELIVERYGROUP|CUSTKEY
//            if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDto)dto).getDeliverydt())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"deliverydt"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
//            if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDto)dto).getCarno())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"carno"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
//            if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDto)dto).getPriority())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"priority"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
//            if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDto)dto).getDeliverygroup())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"deliverygroup"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
            // END.필수입력 check
            insertList.add(entity);

            // 200개마다 혹은 마지막 루프일 때 insert(3/3)
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertTemp", insertList);
                insertList.clear();
            }
        }
        /*END.Temp Table Insert*/

        // PKG 파라마터 세팅 - 공통(1/4)
        RtReturnOutReqDto dto = new RtReturnOutReqDto();
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PROCEDURE","PROCESSTYPE","TEMPTABLETYPE","AVC_COMMAND"};
        Object[] valueList = {
            PAKAGE_NAME,
            PROCESSTYPE,
            TEMPTABLETYPE,
            "INSERT"
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
            log.error("▶저장 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"저장"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 협력사반품지시 엑셀 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getExcelList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getExcelList", reqDto);
    }

    /**
     * @description : 협력사반품지시 엑셀 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getExcelList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getExcelList2", reqDto);
    }
}
