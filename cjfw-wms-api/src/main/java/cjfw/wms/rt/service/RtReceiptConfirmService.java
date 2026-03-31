package cjfw.wms.rt.service;

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
import cjfw.wms.ib.entity.IbExpenseEmailLogEntity;
import cjfw.wms.rt.dto.RtReceiptConfirmReqDto;
import cjfw.wms.rt.dto.RtReceiptConfirmResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.09.16
 * @description :반품확정처리  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RtReceiptConfirmService {
    private final UserContext userContext;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPRT_RECEIPT";
    private transient static final String TEMPTABLETYPE = "RT";
    private transient static final String PROCESSTYPE = "";
    private transient static final String CONFIRMTYPE = "SERIALKEY";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(RtReceiptConfirmService.class.getSimpleName()) + ".";

    /**
     * @description : 반품확정처리 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 반품확정처리 mamd 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMaMdInfoList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMaMdInfoList", reqDto);
    }

    /**
     * @description : 반품확정처리 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", reqDto);
    }

    /**
     * @description : 반품확정처리 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", reqDto);
    }

    /**
     * @description : 반품확정처리 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList2", reqDto);
    }

    /**
     * @description : 반품확정처리 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getPrintDetailList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList2", reqDto);
    }

    /**
     * @description : 반품확정처리 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String saveMaster(RtReceiptConfirmReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        RtReceiptConfirmReqDto reqDto = ModelMapperUtil.map(paramDto, RtReceiptConfirmReqDto.class);
        RtReceiptConfirmResDto data = reqDto.getSaveMasterList().get(0); // 저장리스트의 첫번째 데이터

        RtReceiptConfirmReqDto dto = new RtReceiptConfirmReqDto();

        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"STORERKEY", "DCCODE", "ORGANIZE", "AREA", "DOCDT", "DOCNO", "DOCLINE", "SLIPDT", "SLIPNO", "SLIPLINE", "SKU", "UOM", "BUNJA", "BUNMO", "TRANQTY", "LOTTABLE01", "STOCKID", "STOCKGRADE", "SHORTAGETRANQTY", "TOLOC", "REASONCODE", "REASONMSG", "RETURNTYPE", "INSPECTSERIALKEY", "FORCEINSPECT", "CONFIRMTYPE"};
        Object[] valueList = {
            data.getStorerkey(),
            data.getDccode(),
            data.getOrganize(),
            data.getArea(),
            data.getDocdt(),
            data.getDocno(),
            data.getDocline(),
            data.getSlipdt(),
            data.getSlipno(),
            data.getSlipline(),
            data.getSku(),
            data.getUom(),
            data.getBunja(),
            data.getBunmo(),
            data.getTranqty(),
            data.getLottable01(),
            data.getStockid(),
            data.getStockgrade(),
            data.getShortagetranqty(),
            data.getToloc(),
            data.getReasoncode(),
            data.getReasonmsg(),
            data.getReturntype(),
            data.getInspectserialkey(),
            data.getForceinspect(),
            CONFIRMTYPE
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
     * @description : 반품확정처리 이메일
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String sendMaster(RtReceiptConfirmReqDto paramDto) throws Exception {
        IbExpenseEmailLogEntity entity = ModelMapperUtil.map(paramDto, userContext, IbExpenseEmailLogEntity.class);
        commonDao.insert("ibExpenseService.insertEmailLog", entity);
        commonDao.update(SERVICEID_PREFIX + "saveMasterEmail", paramDto);

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }


    /**
     * @description : 반품확정처리 임시저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String tempSaveMaster(RtReceiptConfirmReqDto paramDto) throws Exception {
        for (RtReceiptConfirmResDto dto : paramDto.getSaveMasterList()) {
            commonDao.update(SERVICEID_PREFIX + "tempSaveMaster", dto);
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }


    /**
     * @description : 반품확정처리 취소
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String cancelMaster(RtReceiptConfirmReqDto paramDto) throws Exception {
        for (RtReceiptConfirmResDto dto : paramDto.getSaveMasterList()) {
            commonDao.update(SERVICEID_PREFIX + "cancelMaster", dto);
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
