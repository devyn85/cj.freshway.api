package cjfw.wms.wd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
//import cjfw.core.utility.DamoScpDbUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.wd.dto.RoInvoiceReqDto;
import cjfw.wms.wd.dto.RoInvoiceResDetailDto;
import cjfw.wms.wd.dto.RoInvoiceResDto;
import cjfw.wms.wd.dto.RoInvoiceResPrintDetailDto;
import cjfw.wms.wd.dto.RoInvoiceResPrintDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.16
 * @description :반품명세서출력   Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoInvoiceService {
    private final UserContext userContext;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPWD_INVOICE";
    private transient static final String TEMPTABLETYPE = "WD";
    private transient static final String PROCESSTYPE = "RO_INVOICECARNO";
    private transient static final String PROCESSTYPE1 = "RO_INVOICE";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(RoInvoiceService.class.getSimpleName()) + ".";

    /**
     * @description : 반품명세서출력  조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 반품명세서출력  상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", reqDto);
    }

    /**
     * @description : 반품명세서출력  인쇄 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.16 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <T> Map<String, List> getPrintMasterList(T paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        RoInvoiceReqDto reqDto = ModelMapperUtil.map(paramDto, RoInvoiceReqDto.class);
        boolean isMaster = reqDto.getIsMaster(); // 마스터 여부
        List<?> saveList = isMaster ? reqDto.getSaveList() : reqDto.getSaveDetailList(); // 저장리스트
        log.info("▶saveList.size->{}", saveList);
        reqDto.setTemptabletype(TEMPTABLETYPE); // 임시테이블타입
        reqDto.setProcesstype(isMaster ? PROCESSTYPE : PROCESSTYPE1); // 임시테이블타입

        /*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp" + TEMPTABLETYPE, reqDto);

        int chunkSize = 200;
        List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            Object dto = saveList.get(i);
            // 임시테이블에 등록(2/3)
            CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
            entity.setProcesstype(reqDto.getProcesstype()); // 프로세스타입
            entity.setTemptabletype(TEMPTABLETYPE); // 프로세스타입
            entity.setPickListNo(reqDto.getInvoicePrintKey()); // 프로세스타입
            // UI.params

            // START.필수입력 check CHECKYN|DELIVERYDT|DOCNO|SLIPDT|SLIPNO|CARNO|PRIORITY|DELIVERYGROUP|CUSTKEY
            if (isMaster) {
                if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDto)dto).getDeliverydt())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"deliverydt"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
                if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDto)dto).getCarno())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"carno"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
                if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDto)dto).getPriority())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"priority"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
                if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDto)dto).getDeliverygroup())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"deliverygroup"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
            }else {
                if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDetailDto)dto).getDeliverydt())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"deliverydt"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
                if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDetailDto)dto).getCarno())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"carno"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
                if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDetailDto)dto).getPriority())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"priority"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
                if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDetailDto)dto).getDeliverygroup())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"deliverygroup"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
//                if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDetailDto) dto).getDocno()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
//                if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDetailDto) dto).getSlipdt()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipdt"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
//                if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDetailDto) dto).getSlipno()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipno"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
//                if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDetailDto) dto).getCustkey()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"custkey"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
            }
            // END.필수입력 check
            insertList.add(entity);

            // 200개마다 혹은 마지막 루프일 때 insert(3/3)
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertTemp", insertList);
                insertList.clear();
            }
        }
        /*END.Temp Table Insert*/

        /*START.PAKAGE 호출*/
        // PKG 파라마터 세팅 - 공통(1/4)
        RoInvoiceReqDto dto = new RoInvoiceReqDto();
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // START.필수입력
        if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getInvoicePrintKey()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"invoicePrintKey"} ) + resultMessage ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
        // END.필수입력

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PROCEDURE", "PROCESSTYPE", "INVOICEPRINTTYPE", "CUSTKEY", "INVOICEPRINTKEY"};
        Object[] valueList = {PAKAGE_NAME, PROCESSTYPE, "TOTAL", reqDto.getToCustkey(), reqDto.getInvoicePrintKey()};
        String[] keyList1 = {"PROCEDURE", "PROCESSTYPE", "INVOICEPRINTTYPE", "INVOICEPRINTKEY"};
        Object[] valueList1 = {PAKAGE_NAME, PROCESSTYPE1, "TOTAL", reqDto.getInvoicePrintKey()};
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(
            isMaster ? keyList : keyList1,
            isMaster ? valueList : valueList1
        ));
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
            log.error("▶인쇄 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"인쇄"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        String[] columns = {"empphone", "driverPhone", "toPhone"};
//        List<RoInvoiceResPrintDto> masterList = DamoScpDbUtil.damoScpDecB64(commonDao.selectList(SERVICEID_PREFIX + "getPrintList", reqDto), columns);
        List<RoInvoiceResPrintDto> masterList = commonDao.selectList(SERVICEID_PREFIX + "getPrintList", reqDto);
        for (RoInvoiceResPrintDto printDto : masterList) {
            printDto.setToVataddress(cjfw.core.utility.StringUtil.nvl(printDto.getToVataddress1()) + " " + StringUtil.nvl(printDto.getToVataddress2()));
            printDto.setToAddress(cjfw.core.utility.StringUtil.nvl(printDto.getToAddress1()) + " " + StringUtil.nvl(printDto.getToAddress2()));
        }
        List<RoInvoiceResPrintDetailDto> detailList = commonDao.selectList(SERVICEID_PREFIX + "getPrintDetailList", reqDto);
        return new HashMap<>() {{
            put("masterList", masterList);
            put("detailList", detailList);
        }};
    }
}
