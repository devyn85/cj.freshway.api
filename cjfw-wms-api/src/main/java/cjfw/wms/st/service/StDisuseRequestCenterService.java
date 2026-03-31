package cjfw.wms.st.service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.sap.JCoUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.entity.CmSyProcessTempAjEntity;
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.extend.CommonProcedureDto;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.ib.service.IbAllWeightService;
import cjfw.wms.st.dto.*;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCMIF_ST_STOCKAMT;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET;
import cjfw.wms.webservice.elecAmt.SI_MM0090_SCM_SOProxy;
import cjfw.wms.webservice.sso.webservice.SSO_NON_SAP_EAIPortTypeProxy;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_REQUEST;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_RESPONSE;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description :재고폐기요청/처리  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StDisuseRequestCenterService {
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StDisuseRequestCenterService.class.getSimpleName()) + ".";

    private static final String SERVICEID_PREFIX_OM = "omOrderCreationSTOService.";
    /** PAKAGE NAME */
    private transient static final String PAKAGE_NAME = "SPST_CONVERT";
    private static final String TEMPTABLETYPE_OM = "ST";
    private static final String PAKAGE_NAME_OM = "SPOM_ORDERCREATIONSTO";
    ///** 프로세스타임 */
    //private transient static final String PROCESSTYPE = "XX";
    ///** 임시테이블 타입 */
    //private transient static final String TEMPTABLETYPE = "XXX";
    //
    //
    /**PAKAGE NAME - AJ*/
    private transient static final String PAKAGE_NAME_AJ = "SPAJ_REQUEST";
    /**프로세스타임 - AJ*/
    private transient static final String PROCESSTYPE_AJ = "AJ_ADJUSTMENTREQ_DU";
    /**임시테이블 타입 - AJ*/
    private transient static final String TEMPTABLETYPE_AJ = "AJ";
    /** 프로세스타임 - OM */
    private static final String PROCESSTYPE_OM = "OM_ORDERCREATIONSTO";

    /**공통.CommonDao*/
    private final CommonDao commonDao;
    /** 공통.UserContext*/
    private final UserContext userContext;
    /**공통.service*/
    private final CmCommonService cmCommonService;
    /**정산.service 금액가져오기*/
    private final IbAllWeightService ibAllWeightService;
	/** SAP 함수 - 코스스트센터 */
    private transient static final String SAP_FUNCTION_NAME = "ZCO_WMS_DEPT_SELECT";
    /**  SAP 테이블명 */
    private transient static final String SAP_TABLE_NAME = "ZCOS_WMS_DEPT";
    
    /**
     * @description : 재고폐기요청/처리 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getTab1MasterList(StDisuseRequestCenterReqDto reqDto) {
        List<R> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", reqDto);
        fetchCost(list);
        if("Y".equals(reqDto.getViewPriceYn())) {
			ibAllWeightService.fetchSapPrice(list);
		}
		return list;
    }

    /**
     * @description : 재고폐기요청/처리 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getTab2MasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", reqDto); // getTab1MasterList 동일하게 조회
    }

    /**
     * @description : 재고폐기요청/처리 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getTab3MasterResultList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getTab3MasterResultList", reqDto);
    }

    /**
     * @description : 재고폐기요청/처리 전자결재 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
     */
    public List<StDisuseRequestCenterResApprovalDto> getTab4MasterApprovalList(StDisuseRequestCenterReqDto reqDto) {
        List<StDisuseRequestCenterResApprovalDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab4MasterApprovalList", reqDto);
        if("Y".equals(reqDto.getViewPriceYn())) {
			ibAllWeightService.fetchSapPrice(list);
		}
		return list;
    }

    /**
     * @description : 재고폐기요청/처리 화면 저장 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
     */
    public StDisuseRequestCenterReqDto saveMasterList(StDisuseRequestCenterReqDto paramDto) {
        StDisuseRequestCenterReqDto resultDto = new StDisuseRequestCenterReqDto();

        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        StDisuseRequestCenterReqDto reqDto = ModelMapperUtil.map(paramDto, StDisuseRequestCenterReqDto.class);
        List<StDisuseRequestCenterResDto> saveList = reqDto.getSaveList(); // 저장리스트   
        log.info("▶saveList.size->{}", saveList);

        // START.필수입력
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getRequestMm()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"requestMm"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getDisuseDiv()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"disuseDiv"}));
        // END.필수입력


        for (StDisuseRequestCenterResDto dto : saveList) {
            // PKG 파라마터 세팅 - 공통(1/4)
            ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

            // START.필수입력
            if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getRequestMm()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"requestMm"})); //

            if ("U".equals(reqDto.getRowStatus())) { // 수정인경우
                if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getReasoncode()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"reasoncode"}));
            }

            if (!dto.getRequestMm().equals(reqDto.getRequestMm())) { // 수정인경우
                throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_057", new String[]{"[요청월과 그리드의 요청월 상이]"})); //{0} 저장 시 문제가 발생했습니다.
            }
            // END.필수입력

            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
            String[] keyList = {
            	     "PROCEDURE"
            	    ,"SERIALKEY"        ,"DOCNO"            ,"DISUSETYPE"       ,"DCCODE"
            	    ,"LOC"              ,"SKU"              ,"UOM"              ,"STOCKGRADE"         ,"STOCKTYPE"
            	    ,"ADJUSTQTY"        ,"REASONCODE"       ,"RESP_PARTY_CD"    ,"LOGI_RESP_DISTB_CD" ,"RESP_DEPT_CD"
            	    ,"CUSTKEY"          ,"DISUSE_METHOD_CD" ,"STOCK_REAL_YN"    ,"REASONMSG1"         ,"REASONMSG2"
            	    ,"RMK"              ,"STORERKEY"        ,"DISUSE_COST"      ,"DISUSE_AMOUNT"      ,"ORGANIZE"
            	    ,"SEQ"              ,"REQUEST_MM"       ,"DISUSE_DIV"       ,"CRUD_FLAG"
            	    ,"TO_RESP_PARTY_CD"
            	    ,"TO_RESP_DEPT_CD"
            	    ,"CHG_CUSTKEY"    
            	};

            Object[] valueList = {
                  PAKAGE_NAME
                , dto.getSerialkey() ,dto.getReturnno()      ,dto.getDisusetype()   ,dto.getDccode()
                , dto.getLoc()       ,dto.getSku()           ,dto.getUom()          ,dto.getStockgrade()      ,dto.getStocktype()
                , dto.getAdjustqty() ,dto.getReasoncode()    ,dto.getRespPartyCd()  ,dto.getLogiRespDistbCd() ,dto.getRespDeptCd()
                , dto.getCustkey()   ,dto.getDisuseMethodCd(),dto.getStockRealYn()  ,dto.getReasonmsg1()      ,dto.getReasonmsg2()
                , dto.getRmk()       ,dto.getGStorerkey()    ,dto.getDisusecost()   ,dto.getDisuseAmount()    ,dto.getOrganize()
                , dto.getSeq()       ,reqDto.getRequestMm()  ,reqDto.getDisuseDiv() ,dto.getCrudFlag()
                , dto.getToRespPartyCd()
                , dto.getToRespDeptCd()
                , dto.getChgCustkey()
            };
            dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            dto.setAvc_DCCODE(dto.getDccode());
            //dto.setAvc_ORGANIZE(dto.getOrganize());
            int rv = cmCommonService.saveProcedure(dto);
            log.info("rv->{}", rv);

            // 프로시저 OUT Parameter(3/4)
            resultCode = StringUtil.nvl(dto.getResultCode());
            resultMessage = StringUtil.nvl(dto.getResultMessage());
            log.info("resultCode->{}", resultCode);
            log.info("resultMessage->{}", resultMessage);

            // 프로시저 Exception 처리(4/4)
            if (!"0".equals(resultCode)) {
                log.error("▶재고폐기요청 처리 시 오류 발생 ");
                throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"재고폐기요청"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }
            /*END.PAKAGE 호출*/

            /*START.최종 화면그리드 데이터 조회 - 없음*/
            //resultDto.setResultList(commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", reqDto));
            /*END.최종 화면그리드 데이터 조회 - 없음*/

        }


        return resultDto;
    }

    /**
     * @description : 제일제당(STO) 처리 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.27 공두경 (kduimux@cj.net) 생성 </pre>
     */
    public StDisuseRequestCenterReqDto saveCJSTO(StDisuseRequestCenterReqDto paramDto) {
        StDisuseRequestCenterReqDto resultDto = new StDisuseRequestCenterReqDto();

        final int chunkSize = 100;


        // 파라미터 위변조 적용(paramDto->reqDto)
        StDisuseRequestCenterReqDto reqDto = ModelMapperUtil.map(paramDto, StDisuseRequestCenterReqDto.class);
        List<StDisuseRequestCenterResCJSTODto> saveList = reqDto.getSaveSTOList(); // 저장리스트   
        log.info("▶saveList.size->{}", saveList);


        // 2. FLAG별로 그룹화 (Map<String, List<Item>>)
        Map<String, List<StDisuseRequestCenterResCJSTODto>> groupedItems = saveList.stream()
            .collect(Collectors.groupingBy(StDisuseRequestCenterResCJSTODto::getToDccode));

        // 3. 같은 FLAG끼리 묶어서 실행
        groupedItems.forEach((flag, items) -> {
            System.out.println("--- FLAG [" + flag + "] 처리 시작 (총 " + items.size() + "개) ---");
            // clear temp
            commonDao.delete(SERVICEID_PREFIX_OM + "deleteSyProcessTempST", java.util.Map.of("gUserId", userContext.getUserId(), "gSpid", userContext.getSpid()));

            List<CmSyProcessTempStEntity> insertList = new ArrayList<>();
            int i = 0;
            String sDccode = "";

            for (StDisuseRequestCenterResCJSTODto row : items) {
                CmSyProcessTempStEntity e = new CmSyProcessTempStEntity();

                e.setProcesstype(PROCESSTYPE_OM);
                e.setGUserId(userContext.getUserId());
                e.setGSpid(userContext.getSpid());

                sDccode = row.getFromDccode();
                e.setFromDccode(row.getFromDccode());
                e.setFromStorerkey(userContext.getStorerkey());
                String fromOrg = row.getFromOrganize() != null ? row.getFromOrganize() : row.getFromDccode();
                e.setFromOrganize(fromOrg);
                e.setFromArea(row.getFromArea());
                e.setFromSku(row.getFromSku());
                e.setFromStockid(row.getFromStockid());
                e.setFromStockgrade(row.getFromStockgrade());
                e.setFromUom(row.getFromUom());
                e.setFromLoc(row.getFromLoc());
                e.setFromLot(row.getFromLot());
                //e.setFromStocktype(row.getFromStocktype());

                e.setFromOrderqty(row.getToOrderqty());
                e.setToOrderqty(row.getToOrderqty());
                e.setToDccode(row.getToDccode());
                e.setToStorerkey(userContext.getStorerkey());
                String toOrg = row.getToOrganize() != null ? row.getToOrganize() : row.getToDccode();
                e.setToOrganize(toOrg);
                e.setToArea(row.getToArea());
                e.setToSku(row.getToSku());
                e.setToStockid(row.getToStockid());
                e.setToStockgrade(row.getToStockgrade());
                e.setToUom(row.getToUom());
                e.setToLoc(row.getToLoc());
                e.setToLot(row.getToLot());
                //e.setToStocktype(row.getToStocktype());

                insertList.add(e);
                if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                    commonDao.insert(SERVICEID_PREFIX_OM + "insertSyProcessTempST", insertList);
                    insertList.clear();
                }
                i++;
            }
            // call package
            CommonProcedureDto procDto = new CommonProcedureDto();
            procDto.setPackagename(PAKAGE_NAME_OM);
            procDto.setProcesstype(PROCESSTYPE_OM);
            procDto.setTemptabletype(TEMPTABLETYPE_OM);
            procDto.setAi_SPID(userContext.getSpid());
            procDto.setAvc_STORERKEY(userContext.getStorerkey());
            procDto.setAvc_WORKER(userContext.getUserId());
            // include context organize/area for logging and downstream defaults
            procDto.setAvc_ORGANIZE(userContext.getOrganize());
            procDto.setAvc_AREA(userContext.getArea());

            procDto.setAvc_DCCODE(sDccode);

            String cmd = reqDto.getAvc_COMMAND();
            if (cmd == null || cmd.isEmpty()) cmd = "CONFIRM";
            procDto.setAvc_COMMAND(cmd);


            // Default EXECUTEMODE: commit on success (""), or pass-through if provided
            if (procDto.getAvc_EXECUTEMODE() == null) {
                procDto.setAvc_EXECUTEMODE("");
            }

            String[] keyList = {
                "PROCEDURE",
                "PROCESSTYPE",
                "PROCESSCREATOR",
                "SPID",
                "DELIVERYDATE",
                "CONVERTTYPE",
                "STOTYPE"
            };
            Object[] valueList = {
                PAKAGE_NAME_OM,
                PROCESSTYPE_OM,
                userContext.getUserId(),
                userContext.getSpid(),
                reqDto.getDeliverydate(),
                "",
                reqDto.getStotype()
            };
            procDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));

            int rv = cmCommonService.saveProcedure(procDto);
            log.info("pkg rv={}, code={}, msg={}", rv, procDto.getResultCode(), procDto.getResultMessage());

            String resultCode = procDto.getResultCode();
            String resultMessage = procDto.getResultMessage();
            if (!"0".equals(resultCode)) {
                throw new UserHandleException(
                    MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"제일제당(STO)"}) + resultMessage
                );
            }
        });


        return resultDto;
    }


    /**
     * @description : 재고폐기요청/처리 확정 처리 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.05.30 SangSuSung(kduimux@cj.com) 생성 </pre>
     */
    public StDisuseRequestCenterReqDto confirmMasterList(StDisuseRequestCenterReqDto paramDto) throws Exception {
        StDisuseRequestCenterReqDto resultDto = new StDisuseRequestCenterReqDto();

        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        StDisuseRequestCenterReqDto reqDto = ModelMapperUtil.map(paramDto, StDisuseRequestCenterReqDto.class);
        List<StDisuseRequestCenterResDto> saveList = reqDto.getSaveList(); // 저장리스트

        reqDto.setProcesstype(PROCESSTYPE_AJ);     // 프로세스타입
        reqDto.setTemptabletype(TEMPTABLETYPE_AJ); // 임시테이블타입

        // START.필수입력
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getCallFrom()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"callFrom"}));
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getRequestMm()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"requestMm"}));
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getDocdt()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docdt"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getIfSendType()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"ifSendType"}));
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getWorkprocesscode()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"workprocesscode"}));
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getOmsFlag()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"omsFlag"}));
        if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getStocktranstype()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"stocktranstype"}));
        // END.필수입력

        /*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp" + TEMPTABLETYPE_AJ, reqDto);

        int chunkSize = 200;
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            StDisuseRequestCenterResDto dto = saveList.get(i);
            // 임시테이블에 등록(2/3)
            CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
            entity.setPackagename(PAKAGE_NAME_AJ); // 패키지명
            entity.setProcesstype(PROCESSTYPE_AJ); // 프로세스타입
            log.info("▶dto->{}", dto);


            // UI.params
            /* DTO 필드(카멜) → Entity 필드(카멜) 1 : 1 순서 매핑 */
            String columnsDto = "DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|QTY|OPENQTY|QTYALLOCATED|QTYPICKED|ADJUSTQTY|LOTTABLE01|REASONCODE|DISUSETYPE|RESP_PARTY_CD|LOGI_RESP_DISTB_CD|RESP_DEPT_CD|CUSTKEY|DISUSE_DIV|SERIALKEY|SEQ";
            String columnsEntity = "DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|ORDERQTY|OPENQTY|PROCESSQTY|WORKQTY|CONFIRMQTY|MIXBOXKEY|TASKKEY|DELIVERYGROUP|WAVEKEY|INVOICENO|OTHER01|CUSTKEY|OTHER02|OTHER03|SRCSERIALKEY1";
            entity = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);

            // START.필수입력 check - 그리드 변수 등
            if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));   // 해당 정보가 없어 처리할 수 없습니다.-{0}
            if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
            if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));


            // END.필수입력 check
            insertList.add(entity);

            // 200개마다 혹은 마지막 루프일 때 insert(3/3)
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList);
                insertList.clear();
            }
        }
        /*END.Temp Table Insert*/


        /*START.PAKAGE 호출*/
        // PKG 파라마터 세팅 - 공통(1/4)
        StDisuseRequestCenterReqDto dto = new StDisuseRequestCenterReqDto();
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME_AJ);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PROCEDURE", "PROCESSTYPE", "TEMPTABLETYPE", "PROCESSCREATOR", "SPID"
            // 업무파라이터
            , "DOCDT", "IF_SEND_TYPE", "WORKPROCESSCODE", "OMS_FLAG", "STOCKTRANSTYPE"
            , "CALL_FROM", "REQUEST_MM"
        };
        Object[] valueList = {PAKAGE_NAME_AJ, reqDto.getProcesstype(), reqDto.getTemptabletype(), reqDto.getGUserId(), reqDto.getGSpid(),
            // 업무파라이터
            reqDto.getDocdt(), reqDto.getIfSendType(), reqDto.getWorkprocesscode(), reqDto.getOmsFlag(), reqDto.getStocktranstype()
            , reqDto.getCallFrom(), reqDto.getRequestMm()
        };
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(dto);
        log.info("rv->{}", rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl(dto.getResultMessage());
        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);

        // 프로시저 Exception 처리(4/4)
        if (!"0".equals(resultCode)) {
            log.error("▶재고폐기요청/처리 - 저장 시 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"재고폐기요청/처리-저장"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        /*START.최종 화면그리드 데이터 조회 - 처리결과*/
        resultDto.setResultList(commonDao.selectList(SERVICEID_PREFIX + "getTab3MasterResultList", reqDto));
        /*END.최종 화면그리드 데이터 조회 - 처리결과*/

        return resultDto;
    }


    /**
     * @description : 재고폐기요청/처리 전자결재 처리 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.05.30 SangSuSung(kduimux@cj.com) 생성 </pre>
     */
    public StDisuseRequestCenterReqDto saveElectApproval(StDisuseRequestCenterReqDto paramDto) throws Exception {
        StDisuseRequestCenterReqDto resultDto = new StDisuseRequestCenterReqDto();

        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";
        String returnMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        StDisuseRequestCenterReqDto reqDto = ModelMapperUtil.map(paramDto, StDisuseRequestCenterReqDto.class);
        List<StDisuseRequestCenterResApprovalDto> saveList = reqDto.getSaveElectApprovalList(); // 저장리스트

        reqDto.setProcesstype(PROCESSTYPE_AJ);     // 프로세스타입
        reqDto.setTemptabletype(TEMPTABLETYPE_AJ); // 임시테이블타입

        // START.필수입력
        //if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getRequestMm())))	   throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"requestMm"} ));
        //if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getOmsFlag()))) 		   throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"omsFlag"} ));
        // END.필수입력

        /*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp" + TEMPTABLETYPE_AJ, reqDto);

        int chunkSize = 200;
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            StDisuseRequestCenterResApprovalDto dto = saveList.get(i);
            // 임시테이블에 등록(2/3)
            CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
            entity.setPackagename(PAKAGE_NAME_AJ); // 패키지명
            entity.setProcesstype(PROCESSTYPE_AJ); // 프로세스타입
            log.info("▶dto->{}", dto);


            // UI.params
            /* DTO 필드(카멜) → Entity 필드(카멜) 1 : 1 순서 매핑 */
            String columnsDto = "DCCODE|STORERKEY|ORGANIZE|AREA|LOC|SKU|UOM|ORDERQTY|DOCDT|DOCNO|DOCLINE|ORDERTYPE|SLIPDT|SLIPNO|SLIPLINE|SLIPTYPE|IOTYPE|STOCKAMT|PRICE|OTHER04|OTHER02|OTHER03|WEIGHT|REASONCODE|DISUSE_DIV_REQ|SERIALKEY|RESP_DEPT_CD|SEQ|CUSTKEY";
            String columnsEntity = "DCCODE|STORERKEY|ORGANIZE|AREA|LOC|SKU|UOM|ORDERQTY|DOCDT|DOCNO|DOCLINE|ORDERTYPE|SLIPDT|SLIPNO|SLIPLINE|SLIPTYPE|IOTYPE|ETCQTY1|ETCQTY2|OTHER04|OTHER02|OTHER03|INVOICEQTY|WAVEKEY|OTHER05|PROCESSMSG|OTHER01|SRCSERIALKEY1|CUSTKEY";
            entity = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);

            // START.필수입력 check - 그리드 변수 등
            if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));   // 해당 정보가 없어 처리할 수 없습니다.-{0}
            if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
            if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));
            if (entity.getInvoiceqty() == null) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"invoiceqty"}));

            // END.필수입력 check
            insertList.add(entity);

            // 200개마다 혹은 마지막 루프일 때 insert(3/3)
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertTempExcelAJ", insertList);
                insertList.clear();
            }
        }
        /*END.Temp Table Insert*/


        /*START.PAKAGE 호출*/
        // PKG 파라마터 세팅 - 공통(1/4)
        StDisuseRequestCenterReqDto dto = new StDisuseRequestCenterReqDto();
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME_AJ);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PROCEDURE", "PROCESSTYPE", "TEMPTABLETYPE", "PROCESSCREATOR", "SPID"
            // 업무파라이터
            , "CALL_FROM", "REQUEST_MM"
        };
        Object[] valueList = {PAKAGE_NAME_AJ, reqDto.getProcesstype(), reqDto.getTemptabletype(), reqDto.getGUserId(), reqDto.getGSpid()
            // 업무파라이터
            , reqDto.getCallFrom(), reqDto.getRequestMm()
        };
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(dto);
        log.info("rv->{}", rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl(dto.getResultMessage());
        returnMessage = StringUtil.nvl(dto.getReturnMessage()); // 전자결재요청정보->VC_RETURNMSG := '<APPROVALREQDT>'||PVC_APPROVALREQDT||'</APPROVALREQDT>'||'<APPROVALREQNO>'||PVC_APPROVALREQNO||'</APPROVALREQNO>

        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);
        log.info("returnMessage->{}", returnMessage);

        // 프로시저 Exception 처리(4/4)
        if (!"0".equals(resultCode)) {
            log.error("▶재고폐기요청/처리 - 결재 시 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"재고폐기요청/처리 - 결재"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        // SSO 티켓 호출
        SSO_NON_SAP_EAIPortTypeProxy proxy = new SSO_NON_SAP_EAIPortTypeProxy();
        String ssoId = "";

        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");

            SSO_NON_SAP_REQUEST reqData = new SSO_NON_SAP_REQUEST();
            reqData.setXSYS("SCM");
            reqData.setXDATS(dateFormat.format(calendar.getTime()));
            reqData.setXTIMS(timeFormat.format(calendar.getTime()));
            reqData.setXROWS(String.valueOf(1));
            //reqData.setINT_SVC_NO("1000005989");    // 글로벌 변수로 대체
            reqData.setINT_SVC_NO(reqDto.getGUserNo());    // 글로벌 변수로 대체

            SSO_NON_SAP_RESPONSE response = proxy.GET_SSO_TICKET(reqData);

            ssoId = "<SSOID>" + response.getSSO_TICKET() + "</SSOID>";
            returnMessage = returnMessage + ssoId;
        } catch (Exception e) {
            log.error("SSO ERROR", e);
        }

        /*START.최종 화면그리드 데이터 조회 - 처리결과 - 없음*/
        //resultDto.setResultList(commonDao.selectList(SERVICEID_PREFIX + "getTab3MasterResultList", reqDto));
        /*END.최종 화면그리드 데이터 조회 - 처리결과 - 없음*/

        // 처리 결과코드 설정
        resultDto.setReturnMessage(returnMessage); // 요청일자 + SEQ + SSO티켓
        return resultDto;
    }

    /**
     * @description : 재고폐기요청/처리 화면 삭제 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
     */
    public StDisuseRequestCenterReqDto deleteMasterList(StDisuseRequestCenterReqDto paramDto) {
        StDisuseRequestCenterReqDto resultDto = new StDisuseRequestCenterReqDto();

        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        StDisuseRequestCenterReqDto reqDto = ModelMapperUtil.map(paramDto, StDisuseRequestCenterReqDto.class);
        List<StDisuseRequestCenterResApprovalDto> saveList = reqDto.getSaveElectApprovalList(); // 저장리스트   
        log.info("▶saveList.size->{}", saveList);

        // START.필수입력
        //if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getRequestMm()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"requestMm"} ) ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
        // END.필수입력

        for (StDisuseRequestCenterResApprovalDto dto : saveList) {
            // PKG 파라마터 세팅 - 공통(1/4)
            ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME_AJ);
            //dto.setRequestMm(reqDto.getRequestMm()); // 요청월

            // START.필수입력
            if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getDocdt()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docdt"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
            // END.필수입력

            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
            String[] keyList = {
                "PROCEDURE"
                // strColMerge
                , "DCCODE", "STORERKEY", "ORGANIZE", "AREA", "DOCDT"
                , "DOCNO", "DOCLINE", "SLIPDT", "SLIPNO", "SLIPLINE"
                , "ORDERTYPE", "SLIPTYPE", "SKU", "UOM", "TRANQTY"
                , "STOCKID", "STOCKGRADE", "LOC", "LOT", "IOTYPE"
                , "SERIALKEY", "REQUEST_MM", "DISUSE_DIV", "RESP_DEPT_CD", "CUSTKEY", "SEQ"
                // get Parameter
                // 없음
            };
            Object[] valueList = {
                PAKAGE_NAME_AJ
                // strColMerge
                , dto.getDccode(), dto.getStorerkey(), dto.getOrganize(), dto.getArea(), dto.getDocdt()
                , dto.getDocno(), dto.getDocline(), dto.getSlipdt(), dto.getSlipno(), dto.getSlipline()
                , dto.getOrdertype(), dto.getSliptype(), dto.getSku(), dto.getUom(), dto.getTranqty()
                , dto.getStockid(), dto.getStockgrade(), dto.getLoc(), dto.getLot(), dto.getIotype()
                , dto.getSerialkey(), dto.getRequestMm(), dto.getDisuseDiv(), dto.getRespDeptCd(), dto.getCustkey(), dto.getSeq()
                // get Parameter
                // 없음
            };
            dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            int rv = cmCommonService.saveProcedure(dto);
            log.info("rv->{}", rv);

            // 프로시저 OUT Parameter(3/4)
            resultCode = StringUtil.nvl(dto.getResultCode());
            resultMessage = StringUtil.nvl(dto.getResultMessage());
            log.info("resultCode->{}", resultCode);
            log.info("resultMessage->{}", resultMessage);

            // 프로시저 Exception 처리(4/4)
            if (!"0".equals(resultCode)) {
                log.error("▶재고폐기요청 삭제 처리 시 오류 발생 ");
                throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"재고폐기요청-삭제"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }
            /*END.PAKAGE 호출*/
        }

        return resultDto;
    }

    /**
     * @description : 재고폐기요청/처리 폐기처리 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
     */
    public List<StDisuseRequestCenterResProcessDto> getTab5MasterProcessList(StDisuseRequestCenterReqDto reqDto) {
        List<StDisuseRequestCenterResProcessDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab5MasterProcessList", reqDto);
        if("Y".equals(reqDto.getViewPriceYn())) {
			ibAllWeightService.fetchSapPrice(list);
		}
		return list;
    }


    /**
     * @description : 재고폐기요청/처리 폐기처리 처리 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.05.30 SangSuSung(kduimux@cj.com) 생성 </pre>
     */
    public StDisuseRequestCenterReqDto processMasterList(StDisuseRequestCenterReqDto paramDto) throws Exception {
        StDisuseRequestCenterReqDto resultDto = new StDisuseRequestCenterReqDto();

        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        StDisuseRequestCenterReqDto reqDto = ModelMapperUtil.map(paramDto, StDisuseRequestCenterReqDto.class);
        List<StDisuseRequestCenterResProcessDto> saveList = reqDto.getSaveProcessList(); // 저장리스트   
        log.info("▶saveList.size->{}", saveList);

        // START.필수입력
        //if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getRequestMm()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"requestMm"} ) ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
        // END.필수입력

        for (StDisuseRequestCenterResProcessDto dto : saveList) {
            // PKG 파라마터 세팅 - 공통(1/4)
            ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME_AJ);
            dto.setRequestMm(reqDto.getRequestMm()); // 요청월

            // START.필수입력
            if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getDccode()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"Dccode"}));      // 해당 정보가 없어 처리할 수 없습니다.-{0}
            if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getStorerkey()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"Storerkey"}));
            if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getOrganize()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"Organize"}));
            if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getArea()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"Area"}));
            if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getSlipdt()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"Slipdt"}));
            if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getSlipno()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"Slipno"}));
            if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getSlipline()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"Slipline"}));
            if (dto.getTranqty() == null) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"Tranqty"}));
            // END.필수입력

            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
            String[] keyList = {
                "PROCEDURE"
                // strColMerge
                , "DCCODE", "STORERKEY", "ORGANIZE", "AREA", "SLIPDT"
                , "SLIPNO", "SLIPLINE", "TRANQTY"
                // get Parameter
                // 없음
            };
            Object[] valueList = {
                PAKAGE_NAME_AJ
                // strColMerge
                , dto.getDccode(), dto.getStorerkey(), dto.getOrganize(), dto.getArea(), dto.getSlipdt()
                , dto.getSlipno(), dto.getSlipline(), dto.getTranqty()
                // get Parameter
                // 없음
            };
            dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            int rv = cmCommonService.saveProcedure(dto);
            log.info("rv->{}", rv);

            // 프로시저 OUT Parameter(3/4)
            resultCode = StringUtil.nvl(dto.getResultCode());
            resultMessage = StringUtil.nvl(dto.getResultMessage());
            log.info("resultCode->{}", resultCode);
            log.info("resultMessage->{}", resultMessage);

            // 프로시저 Exception 처리(4/4)
            if (!"0".equals(resultCode)) {
                log.error("▶재고폐기요청 폐기처리 처리 시 오류 발생 ");
                throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"재고폐기요청-폐기처리"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }
            /*END.PAKAGE 호출*/
        }

        return resultDto;
    }

    /*
        코스트센터 없으면 mamd의 부서로 조회해서 채워넣기
        T Dto 필수 필드 목록
        * String deptCd
        * String respDeptCd
        * String respDeptNm
    */
    public <T> void fetchCost(List<T> list) {
        Map<String, Map<String, String>> responseMap = new LinkedHashMap<>();
        JCoDestination destination = JCoUtil.getDestination();
        JCoFunction function = JCoUtil.getFunction(SAP_FUNCTION_NAME);

        if (function == null) {
            throw new UserHandleException(SAP_FUNCTION_NAME + " not found in SAP.");
        }

        JCoParameterList importParams = function.getImportParameterList();
        importParams.setValue("I_ZDEPT", "B50G000750");

        try {
            function.execute(destination);
        } catch (Exception e) {
        }

        JCoTable table = function.getTableParameterList().getTable(SAP_TABLE_NAME);
        for (int i = 0; i < table.getNumRows(); i++) {
            table.setRow(i);
            responseMap.putIfAbsent(table.getString("ZDEPT"), new HashMap<>() {
                {
                    put("KOSTL", table.getString("KOSTL"));
                    put("KTEXT", table.getString("KTEXT"));
                }
            });
        }

        try{
            for (T item : list) {
                Method getDeptCd = item.getClass().getMethod("getDeptCd");
                Method getRespDeptCd = item.getClass().getMethod("getRespDeptCd");
                Method getRespDeptNm = item.getClass().getMethod("getRespDeptNm");
                Method setRespDeptCd = item.getClass().getMethod("setRespDeptCd", String.class);
                Method setRespDeptNm = item.getClass().getMethod("setRespDeptNm", String.class);

                //쿼리에서 cost있으면 패스
                if(StringUtils.isNotEmpty((String)getRespDeptCd.invoke(item))) {
                    continue;
                }

                //앞에붙은 0 전부빼기
                setRespDeptCd.invoke(item, Objects.isNull(responseMap.get((String)getDeptCd.invoke(item))) ? null : responseMap.get((String)getDeptCd.invoke(item)).get("KOSTL").replaceFirst("^0+", ""));
                setRespDeptNm.invoke(item, Objects.isNull(responseMap.get((String)getDeptCd.invoke(item))) ? null : responseMap.get((String)getDeptCd.invoke(item)).get("KTEXT"));
            }

        } catch (Exception e) {
            //NoSuchMethodException, InvocationTargetException, IllegalAccessException
            e.printStackTrace();
        }
    }
}
