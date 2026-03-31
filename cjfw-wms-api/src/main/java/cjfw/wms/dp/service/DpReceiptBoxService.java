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
import cjfw.wms.dp.dto.DpReceiptBoxReqDto;
import cjfw.wms.dp.dto.DpReceiptBoxResDetailDto;
import cjfw.wms.dp.dto.DpReceiptBoxResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.09.08
 * @description :입고확정처리(수원3층)  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DpReceiptBoxService {
    private final UserContext userContext;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPDP_RECEIPT";
    private transient static final String TEMPTABLETYPE = "DP";
    private transient static final String PROCESSTYPE = "";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(DpReceiptBoxService.class.getSimpleName()) + ".";

    /**
     * @description : 입고확정처리(수원3층) 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 입고확정처리(수원3층) 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", reqDto);
    }

    /**
     * @description : 입고확정처리(수원3층) 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String saveMaster(DpReceiptBoxReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        DpReceiptBoxReqDto reqDto = ModelMapperUtil.map(paramDto, DpReceiptBoxReqDto.class);
        DpReceiptBoxResDto data = reqDto.getSaveMasterList().get(0); // 저장리스트의 첫번째 데이터

        DpReceiptBoxReqDto dto = new DpReceiptBoxReqDto();

        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"DCCODE", "STORERKEY", "ORGANIZE", "SLIPDT", "SLIPNO"};
        Object[] valueList = {
            data.getDccode(),
            data.getStorerkey(),
            data.getOrganize(),
            data.getSlipdt(),
            data.getSlipno()
        };
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        dto.setAvc_DCCODE(reqDto.getFixdccode());
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
     * @description : 입고확정처리(수원3층) 대상확정
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String saveDetail(DpReceiptBoxReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        DpReceiptBoxReqDto reqDto = ModelMapperUtil.map(paramDto, DpReceiptBoxReqDto.class);
        DpReceiptBoxResDetailDto data = reqDto.getSaveList().get(0); // 저장리스트의 첫번째 데이터

        DpReceiptBoxReqDto dto = new DpReceiptBoxReqDto();

        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"DCCODE", "STORERKEY", "ORGANIZE", "DOCDT", "DOCNO", "DOCLINE", "SLIPDT", "SLIPNO", "SLIPLINE", "SKU", "UOM", "BUNJA", "BUNMO", "TRANQTY_BOX", "TRANQTY_EA", "SHORTAGETRANQTY_BOX", "SHORTAGETRANQTY_EA", "TOLOC", "LOTTABLE01", "STOCKID", "STOCKGRADE", "REASONCODE", "REASONMSG", "REFERENCE02", "PLTID", "DP_UOM"};
        Object[] valueList = {
            data.getDccode(),
            data.getStorerkey(),
            data.getOrganize(),
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
            data.getTranqtyBox(),
            data.getTranqtyEa(),
            data.getShortagetranqtyBox(),
            data.getShortagetranqtyEa(),
            data.getToloc(),
            data.getLottable01(),
            data.getStockid(),
            data.getStockgrade(),
            data.getReasoncode(),
            data.getReasonmsg(),
            data.getReference02(),
            data.getPltid(),
            data.getDpUom()
        };
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        dto.setAvc_DCCODE(reqDto.getFixdccode());
        int rv = cmCommonService.saveProcedure(dto);
        log.info("rv->{}", rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl((String) dto.getResultMessage());
        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);

        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            log.error("▶대상확정 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"대상확정"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }


    /**
     * @description : 입고확정처리(수원3층) 엑셀 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getExcelList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getExcelList", reqDto);
    }


    /**
     * @description : 입고확정처리(수원3층) PLT ID 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> R getMaxStockId(T reqDto) {
        return commonDao.selectOne(SERVICEID_PREFIX + "getMaxStockId", reqDto);
    }
}
