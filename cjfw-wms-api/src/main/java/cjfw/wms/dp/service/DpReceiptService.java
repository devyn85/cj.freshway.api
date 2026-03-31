package cjfw.wms.dp.service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.sap.JCoUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.dp.dto.DpReceiptReqDto;
import cjfw.wms.dp.dto.DpReceiptResDetailDto;
import cjfw.wms.dp.dto.DpReceiptResDto;
import cjfw.wms.dp.dto.DpReceiptReverseStoEntity;
import cjfw.wms.om.dto.OmOrderCreationSTOReqDto;
import cjfw.wms.om.dto.OmOrderCreationSTOResDto;
import cjfw.wms.om.service.OmOrderCreationSTOService;
import cjfw.wms.rt.dto.RtQCConfirmReqDto;
import cjfw.wms.rt.dto.RtQCConfirmResDto;
import cjfw.wms.wd.dto.WdShipmentReqDto;
import cjfw.wms.wd.dto.WdShipmentResBillYnDto;
import com.sap.conn.jco.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.08.22
 * @description :입고확정처리  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DpReceiptService {
    private final UserContext userContext;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private final OmOrderCreationSTOService omOrderCreationSTOService;
    private transient static final String PAKAGE_NAME = "SPDP_RECEIPT";
    private transient static final String TEMPTABLETYPE = "DP";
    private transient static final String PROCESSTYPE = "";
    private transient static final String SAP_FUNCTION_NAME = "ZMM_CHECK_INVOICE";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(DpReceiptService.class.getSimpleName()) + ".";

    /**
     * @description : 입고확정처리 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 입고확정처리 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", reqDto);
    }

    /**
     * @description : 입고확정처리 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", reqDto);
    }

    /**
     * @description : 입고확정처리 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList2", reqDto);
    }

    /**
     * @description : 입고확정처리 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getPrintDetailList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList2", reqDto);
    }

    /**
     * @description : 입고확정처리 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String saveMaster(DpReceiptReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        DpReceiptReqDto reqDto = ModelMapperUtil.map(paramDto, DpReceiptReqDto.class);
        DpReceiptResDto data = reqDto.getSaveMasterList().get(0); // 저장리스트의 첫번째 데이터

        DpReceiptReqDto dto = new DpReceiptReqDto();

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
     * @description : 입고확정처리 대상확정
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String saveDetail(DpReceiptReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        DpReceiptReqDto reqDto = ModelMapperUtil.map(paramDto, DpReceiptReqDto.class);
        DpReceiptResDetailDto data = reqDto.getSaveList().get(0); // 저장리스트의 첫번째 데이터

        DpReceiptReqDto dto = new DpReceiptReqDto();

        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"DCCODE", "STORERKEY", "ORGANIZE", "DOCDT", "DOCNO", "DOCLINE", "SLIPDT", "SLIPNO", "SLIPLINE", "SKU", "UOM", "BUNJA", "BUNMO", "TRANQTY", "SHORTAGETRANQTY", "TOLOC", "LOTTABLE01", "STOCKID", "STOCKGRADE", "REASONCODE", "REASONMSG", "REFERENCE02"};
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
            data.getTranqty(),
            data.getShortagetranqty(),
            data.getToloc(),
            data.getLottable01(),
            data.getStockid(),
            data.getStockgrade(),
            data.getReasoncode(),
            StringUtil.nvl(data.getReasonmsg()),
            StringUtil.nvl(data.getReference02())
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

        //이력상품 입고확정취소시 DP_PLT_INSPECT 삭제
        if(StringUtils.isNotEmpty(data.getSerialno()) && new BigDecimal(data.getConfirmqty()).add(new BigDecimal(data.getTranqty())).compareTo(BigDecimal.ZERO) == 0) {
            commonDao.delete(SERVICEID_PREFIX +"deleteDpPltInspect", data);
        }


        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }


    /**
     * @description : 입고확정처리 엑셀 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <T> void getExcelList(T reqDto, LargeExcel largeExcel) {
		commonDao.selectLargeExcelDataset(SERVICEID_PREFIX + "getExcelList", reqDto, largeExcel);
    }

    /**
     * @description : 입고확정처리 엑셀 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <T> void getExcelList2(T reqDto, LargeExcel largeExcel) {
		commonDao.selectLargeExcelDataset(SERVICEID_PREFIX + "getExcelList2", reqDto, largeExcel);
    }


    /**
     * @description : 입고확정처리 엑셀 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getPrintList(T reqDto) {
        if ("Y".equals(((DpReceiptReqDto) reqDto).getCrossyn())) {
            return commonDao.selectList(SERVICEID_PREFIX + "getPrintCdList", reqDto);
        } else {
            return commonDao.selectList(SERVICEID_PREFIX + "getPrintList", reqDto);
        }
    }

    /**
     * @description : 입고확정처리 엑셀 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getPrintList2(T reqDto) {
        if ("Y".equals(((DpReceiptReqDto) reqDto).getCrossyn())) {
            return commonDao.selectList(SERVICEID_PREFIX + "getPrintCdList2", reqDto);
        } else {
            return commonDao.selectList(SERVICEID_PREFIX + "getPrintList2", reqDto);
        }
    }


    /**
     * @description : 입고확정처리 대상확정
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String saveBoxPlt(DpReceiptReqDto paramDto) throws Exception {
        DpReceiptResDetailDto dto = paramDto.getSaveList().get(0);
        dto.setEditwho(paramDto.getGUserId());
        commonDao.update(SERVICEID_PREFIX + "saveBoxPlt", dto);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 입고확정처리 세금계산서발행여부
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public WdShipmentResBillYnDto getBillYn(WdShipmentReqDto dto) throws JCoException {
        log.info("### parameter = " + dto.toString());
        WdShipmentResBillYnDto resultDto = new WdShipmentResBillYnDto();

        JCoDestination destination = JCoUtil.getDestination();
        JCoFunction function = JCoUtil.getFunction(SAP_FUNCTION_NAME);

        if (function == null) {
            resultDto.setResult("N");
            resultDto.setErrorMsg("ZMM_CHECK_INVOICE not found in SAP.");

            log.error("▶SAP 마감여부 조회 -> " + SAP_FUNCTION_NAME + " not found in SAP.");
            throw new UserHandleException(SAP_FUNCTION_NAME + " not found in SAP.");
        }

        JCoTable table = function.getTableParameterList().getTable("T_ZMMT1084");
        table.clear();
        table.appendRow();
        table.setValue("EBELN", dto.getDocno());
        table.setValue("EBELP", dto.getDocline());

        log.info("### table = " + table.toString());

        function.execute(destination);

        JCoParameterList exportParams = function.getExportParameterList();
        String eMessage = exportParams.getString("E_XMSGS"); // 메시지
        String eStatus = exportParams.getString("E_XSTAT");  // 상태 ('S':성공, 'E':실패)

        log.info("eStatus=" + eStatus);
        log.info("eMessage=" + eMessage);

        for (int i = 0; i < table.getNumRows(); i++) {
            table.setRow(i);
            String zbillyn = table.getString("ZIVCYN");

            log.info(zbillyn);
            log.info(table.toString());

            resultDto.setZbillyn(zbillyn);
        }

        return resultDto;
    }

    /**
     * @description : 입고확정처리 역STO
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    @Transactional
    public Map reverseSto(DpReceiptReqDto paramDto) throws Exception {
        OmOrderCreationSTOReqDto stoParamDto = ModelMapperUtil.map(paramDto, OmOrderCreationSTOReqDto.class);
        stoParamDto.setAvc_COMMAND("CONFIRM");
        stoParamDto.setDELIVERYDATE(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        List<OmOrderCreationSTOResDto> stoList = new ArrayList<>();
        for (DpReceiptResDetailDto stoDto : paramDto.getSaveList()) {
            //루트dto
            stoParamDto.setDcA(stoDto.getDccode());
            stoParamDto.setDC_B(paramDto.getStoDccode());
            stoParamDto.setSTOTYPE("STO"); //일반STO
            stoParamDto.setSTOCKTYPE("GOOD"); //일반STO

            //목록
            stoParamDto.setFixdccode(stoDto.getDccode());
            stoParamDto.setFROM_DCCODE(stoDto.getDccode());
            stoParamDto.setMULTI_SKU(stoDto.getSku());
            stoParamDto.setFROMLOC(stoDto.getToloc());
            stoParamDto.setTOLOC(stoDto.getToloc());
            List<OmOrderCreationSTOResDto> tempList = omOrderCreationSTOService.getMasterList(stoParamDto);
            if( CollectionUtils.isEmpty(tempList) ) {
                throw new UserHandleException("재고가 부족합니다.");
            }
            OmOrderCreationSTOResDto temp = tempList.get(0);

            if("BOX".equals(stoDto.getUom())){
                temp.setToOrderqty(new BigDecimal(stoDto.getRestoqty()).multiply(new BigDecimal(stoDto.getQtyperbox())));
            }else {
                temp.setToOrderqty(new BigDecimal(stoDto.getRestoqty()));
            }
            temp.setToOrderqty(new BigDecimal(stoDto.getRestoqty()));
            temp.setToDccode(paramDto.getStoDccode());
            temp.setToOrganize(paramDto.getStoDccode());
            temp.setMemo1(stoDto.getDocno() + "|" + stoDto.getDocline());
            //DP정보담기
            stoList.add(temp);
        }
        stoParamDto.setSaveList(stoList);
        log.info("▶stoParamDto->{}", stoParamDto);
        List<OmOrderCreationSTOResDto> stoResDto;
        if (stoList.size() > 0) {
            stoResDto = omOrderCreationSTOService.saveMasterList(stoParamDto);
            log.info("▶stoResDto->{}", stoResDto);
            for(OmOrderCreationSTOResDto dto : stoResDto) {
                if("E".equals(dto.getProcessflag())) {
                    continue;
                }

                String docno = dto.getMemo1().split("\\|")[0];
                String docline = dto.getMemo1().split("\\|")[1];
                //"문서번호:6002015495, 품목번호:10"
                String[] stoInfo = dto.getProcessmsg().split(",");
                String stoDocno = stoInfo[0].split(":")[1].trim();
                String stoDocline = stoInfo[1].split(":")[1].trim();
                commonDao.insert(SERVICEID_PREFIX + "insertReverseSto", DpReceiptReverseStoEntity.builder()
                        .fromDccode(dto.getFromDccode())
                        .docno(docno)
                        .docline(docline)
                        .returnqty(String.valueOf(dto.getToOrderqty()))
                        .stoDocdt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                        .stoDocno(stoDocno)
                        .stoDocline(stoDocline)
                        .addwho(paramDto.getGUserId())
                        .editwho(paramDto.getGUserId())
                    .build()
                );
            }
        } else {
            stoResDto = null;
        }

        return new HashMap<>() {{
            put("sto", stoResDto);
        }};
    }
}
