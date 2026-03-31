package cjfw.wms.st.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cjfw.wms.st.entity.ExdcSkuAmountEntity;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ib.entity.IbExpenseEmailLogEntity;
import cjfw.wms.st.dto.StStockOutOrgReqDto;
import cjfw.wms.st.dto.StStockOutOrgResDto;
import cjfw.wms.webservice.common.WmsEaiLocaterService;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCMIF_ST_STOCKAMT;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET;
import cjfw.wms.webservice.elecAmt.SI_MM0090_SCM_SOProxy;
import cjfw.wms.webservice.exDCTCS.DTSCM0540SCM;
import cjfw.wms.webservice.exDCTCS.DTSCM0540SCMResponse;
import cjfw.wms.webservice.exDCTCS.DTSCM0540SCMResponse.GTUNRECEIVEDSTOCK;
import cjfw.wms.webservice.exDCTCS.SISCM0540SCMSO;
import cjfw.wms.webservice.exDCTCS.SISCM0540SCMSOService;
import jakarta.xml.ws.BindingProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.01 
 * @description : 외부비축재고조회 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.01 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StStockOutOrgService {
    
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "stStockOutOrgService.";
    /** 공통.CommonDao */
    private final CommonDao commonDao;
    private final WmsEaiLocaterService eaiLocaterService;
    
    /**
     * @description : 외부비축재고조회 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.01 ParkJinWoo 생성
     */
    public <R, T> List<R> getDataHeaderList(StStockOutOrgReqDto reqDto) {

        String stockStatus = ((StStockOutOrgReqDto)reqDto).getStockStatus();
        String stockStatus20 = ((StStockOutOrgReqDto)reqDto).getStockStatus20();
        String stockStatus30 = ((StStockOutOrgReqDto)reqDto).getStockStatus30();
        String stockStatus40 = ((StStockOutOrgReqDto)reqDto).getStockStatus40();
        String search20 = "0";
        String search30 = "0";
        String search40 = "0";
        
        StStockOutOrgReqDto searchVal = (StStockOutOrgReqDto) reqDto;
        boolean hasDuration = searchVal.getDuratuinTerm() != null
                && !searchVal.getDuratuinTerm().trim().isEmpty();
        
        boolean hasSerial = searchVal.getSerialNo() != null
                      && !searchVal.getSerialNo().trim().isEmpty();
        
        if ("1".equals(stockStatus20)) {
            search20 = "1";
        }
        
        if ("1".equals(stockStatus30)) {
            search30 = "1";
        }
        
        if ("1".equals(stockStatus40)) {
            search40 = "1";
        }
        
        if ("1".equals(stockStatus40) && (
                searchVal.getChkQty().equals("1") ||
                searchVal.getChkQty1().equals("1") || 
                hasDuration || 
                hasSerial ||
                searchVal.getStockGrade() != null
                )) {
            search40 = "0";
        }
        
        return getAllList(reqDto, search20, stockStatus30, stockStatus40);

        /*
        if ("10".equals(stockStatus)) {
            //전체
            reqDto.setSkutpl("ALL");
            if(searchVal.getChkQty().equals("1") ||
                    searchVal.getChkQty1().equals("1") || 
                    hasDuration || 
                    hasSerial ||
                    searchVal.getStockGrade() != null) {
                    return getHeaderList(reqDto);
            } else {
                return getAllList(reqDto);
            }
        } else if ("20".equals(stockStatus)) {
            //상품
            return getHeaderList(reqDto); 
        } else if ("30".equals(stockStatus)) {
            //미착
            if (searchVal.getChkQty().equals("1") ||
                    searchVal.getChkQty1().equals("1") || 
                    hasDuration || 
                    hasSerial ||
                    searchVal.getStockGrade() != null) {
                     List<StStockOutOrgResDto> resultList = new ArrayList<StStockOutOrgResDto>();
                     return (List<R>) resultList;
            } else {
                return getSCM0540List(reqDto);
            }    
        } else if ("40".equals(stockStatus)) { 
            //위탁
            reqDto.setSkutpl("Y");
            return getHeaderList(reqDto);
        }
        */
     
    }
            
    /**
     * @description : 상품 조회 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.01 ParkJinWoo 생성
     */
    public <R, T> List<R> getHeaderList(StStockOutOrgReqDto reqDto) {
        String searchKey = "";
        String resultKey = "";
        String ioType = "";
        String dccode = "";
        String stockAmount = ((StStockOutOrgReqDto)reqDto).getStockAmount();
        
        Integer paramListCnt = 500;
        Integer paramRow = 0;
        Integer updateDsRow = 0;
        Integer findRow = 0;

        int rsltCnt = 0;
        List<StStockOutOrgResDto> searchResultList = new ArrayList<StStockOutOrgResDto>();
        searchResultList = commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist", reqDto);
        rsltCnt = searchResultList.size();
        
        if ("1".equals(stockAmount)) {
            //SI_MM0090_SCM_SOProxy proxy = new SI_MM0090_SCM_SOProxy();
    
            try {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
    
                DT_MM0090_SCM  reqData = new DT_MM0090_SCM();
                reqData.setXSYS("WMS");
                reqData.setXDATS(dateFormat.format(calendar.getTime()));
                reqData.setXTIMS(timeFormat.format(calendar.getTime()));
                reqData.setXROWS(String.valueOf('1'));
    
                DT_MM0090_SCMIF_ST_STOCKAMT param = new DT_MM0090_SCMIF_ST_STOCKAMT();
                DT_MM0090_SCMIF_ST_STOCKAMT[] paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
                if ( rsltCnt <= paramListCnt ) {
                    paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ rsltCnt ];
                }
                else {
                    paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
                }
    			
    			for (int DsRow = 0; DsRow < rsltCnt; DsRow++ ) {
                    param = new DT_MM0090_SCMIF_ST_STOCKAMT();
                    dccode = searchResultList.get(DsRow).getDcCode();
                    param.setPLANT(searchResultList.get(DsRow).getDcCode());
                    param.setSTORAGELOC(searchResultList.get(DsRow).getStorageLoc());
                    param.setSKU(searchResultList.get(DsRow).getSku());
                    param.setUOM(searchResultList.get(DsRow).getUom());
                    param.setQTY(searchResultList.get(DsRow).getSumQty().toString());
                    param.setPOLINE(searchResultList.get(DsRow).getPoLine());
                    param.setSTOCKQTY(searchResultList.get(DsRow).getStockQty().toString());
                    
                    if ("3".equals(searchResultList.get(DsRow).getSerialType())) {
                        param.setSERIALNO("");
                    } else {
                    	param.setSERIALNO(searchResultList.get(DsRow).getSerialNo());
                    }
                    
                    param.setCONVSERIALNO(searchResultList.get(DsRow).getConvSerialNo());
                    param.setSLIPDT("19000101");
                    param.setSLIPNO(searchResultList.get(DsRow).getSerialKey());
                    param.setSLIPLINE("00000");                
                    paramList[paramRow] = param;
                    paramRow++;
                    
                    if ((paramRow.equals( paramListCnt )) || String.valueOf(DsRow + 1).equals( String.valueOf(rsltCnt)))  {
                        reqData = new DT_MM0090_SCM();
                        reqData.setXSYS("WMS");
                        reqData.setXDATS(dateFormat.format(calendar.getTime()));
                        reqData.setXTIMS(timeFormat.format(calendar.getTime()));
                        reqData.setXROWS(String.valueOf(paramRow));    
                        reqData.setIF_ST_STOCKAMT(paramList);
                        
                        //SI_MM0090_SCM_SOProxy proxy = new SI_MM0090_SCM_SOProxy();
                        //DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] response = proxy.si_mm0090_scm_so(reqData);
                        
                        DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] response = getSapAmtInfo(reqData);
                   
                        for (int i = 0; i < paramRow; i++ ) {
                            if ( updateDsRow >= rsltCnt) {
                                break;
                            }
    
                            searchKey =  searchResultList.get(updateDsRow).getDcCode()
                                   + searchResultList.get(updateDsRow).getStorageLoc()
                                   + searchResultList.get(updateDsRow).getSerialKey() 
                                   + searchResultList.get(updateDsRow).getSku();
    
                            findRow = -1;
                            
                            for (int ii = 0; ii < paramRow; ii++) {
                                resultKey =  response[ii].getPLANT()
                                         + response[ii].getSTORAGELOC()
                                         + response[ii].getSLIPNO()
                                          + response[ii].getSKU();	
                                if ( searchKey.equals(resultKey) ) {
                                    findRow = ii;
                                    break;
                                }
                            }
    
                            if ( findRow >= 0 ) {
                                String stat = response[findRow].getXSTAT();	
                                if("E".equals(stat)) {
                                    searchResultList.get(updateDsRow).setStockamtmsg(response[findRow].getXMSGS());
                                } else {
                                    searchResultList.get(updateDsRow).setStockamt(response[findRow].getSTOCKAMT());
                                    searchResultList.get(updateDsRow).setStockPrice(response[findRow].getPURCHASEPRICE());
                                    searchResultList.get(updateDsRow).setStockQty1(response[findRow].getSTOCKQTY().toString());
                                }
                            }
    
                            updateDsRow++;
                            if ( searchResultList.size() - DsRow > paramListCnt ) {
                                paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
                            } else {
                                paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ searchResultList.size() - DsRow];
                            }
                        }		
                        
                        paramRow=0;
                    }
                }
            } catch(Exception e) {
                log.error("재고금액 처리중 에러발생", e);
            }
        }
        
        return(List<R>) searchResultList;		
	}
    
    /**
     * @description : SAP 단가 조회
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                        MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.04 KimSunHo(sunhokim6229@cj.net)  생성 </pre>
     */
    public DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] getSapAmtInfo(DT_MM0090_SCM reqData) {
        DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] response = null;
        
        try {
            SI_MM0090_SCM_SOProxy proxy = new SI_MM0090_SCM_SOProxy();
             response = proxy.si_mm0090_scm_so(reqData);
        } catch(Exception e) {
            log.error("재고금액 처리중 에러발생", e);
        }
        
        return response;
    }
    
    /**
     * @description : 미착재고 조회 인터페이스 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE         AUTHOR                      MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.30   KimSunHo(sunhokim6229@cj.net) 생성
     */
    private <R, T> List<R> getSCM0540List(StStockOutOrgReqDto reqDto) {
        
        String organize = ((StStockOutOrgReqDto)reqDto).getOrganize();
        String sku = ((StStockOutOrgReqDto)reqDto).getSku();
        String blNo = ((StStockOutOrgReqDto)reqDto).getBlNo(); 
        String stockAmount = ((StStockOutOrgReqDto)reqDto).getStockAmount();
        
        List<StStockOutOrgResDto> resultList = new ArrayList<StStockOutOrgResDto>();
        
        organize = (organize == null) ? "" : organize;
        sku = (sku == null) ? "" : sku;
        blNo = (blNo == null ) ? "" : blNo;
        
        DTSCM0540SCMResponse response = null;
        try {
            // EAI Locater Service 호출
            Map<String, String> locator = null;
            locator = eaiLocaterService.getLocater("SCM0540");
            if (locator == null) {
                throw new Exception("locator is null.");
            }
            
            DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
            LocalDateTime now = LocalDateTime.now();
            String dayString  = now.format(dayFormatter);
            String timeString = now.format(timeFormatter);

            // JAX-WS Service & Port 생성
            SISCM0540SCMSOService service = new SISCM0540SCMSOService();
            SISCM0540SCMSO port = service.getPort(SISCM0540SCMSO.class);
            
            // SOAP 요청 기본 파라미터 설정
            DTSCM0540SCM requst = new DTSCM0540SCM();
            requst.setXSYS("WMS");
            requst.setXDATS(dayString);
            requst.setXTIMS(timeString);
            requst.setXROWS("1");
            
            List<DTSCM0540SCM.TPARAM> paramList = new ArrayList<DTSCM0540SCM.TPARAM>();
            DTSCM0540SCM.TPARAM param = new DTSCM0540SCM.TPARAM();
            
            // 조직코드에서 조직번호 추출 (창고코드에서 물류센터코드 제거)
            String organizeNo = getOrganizeNo(organize);
            
            // 조회 요청 파라미터 설정
            param.setAREA(organizeNo);
            param.setSKU(sku);
            param.setBLNO(blNo);
            paramList.add(param); 
            requst.setTPARAM(paramList);

            String whereClause = "1 = 1";
            if (!organizeNo.isEmpty()) {
                whereClause += " AND LGORT IN ('" + organizeNo.replace(",", "','") + "')";
            }
            if (!sku.isEmpty()) {
                whereClause += " AND ITEM_CODE IN ('" + sku.replace(",", "','") + "')";
            }
            if (!blNo.isEmpty()) {
                whereClause += " AND BL_NO IN ('" + blNo.replace(",", "','") + "')";
            }

            requst.setWHERE(whereClause);

            // Endpoint, User, Password 설정
            Map<String, Object> context = ((BindingProvider) port).getRequestContext();
            context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locator.get("endpoint"));
            context.put(BindingProvider.USERNAME_PROPERTY, locator.get("username"));
            context.put(BindingProvider.PASSWORD_PROPERTY, locator.get("password"));
            
            log.info("endpoint : {}", locator.get("endpoint"));
            log.info("username : {}", locator.get("username"));
            log.info("password : {}", locator.get("password"));

            // SOAP 호출
            response = port.siSCM0540SCMSO(requst);
            log.info("response : {} ", response);
            if (response != null && !CollectionUtils.isEmpty(response.getGTUNRECEIVEDSTOCK())) {
                log.info("GTUNRECEIVEDSTOCK : {} ", response.getGTUNRECEIVEDSTOCK().size());
                
                for (GTUNRECEIVEDSTOCK stock: response.getGTUNRECEIVEDSTOCK()) {
                    log.info("stock : {}, {}, {}, {}, {}, {}, {}, {}, {}", stock.getDCCODE(), stock.getDOCNO(), stock.getDOCLINE(), stock.getSKU(), stock.getCONVSERIALNO(), stock.getSTOCKQTY(), stock.getBONDQTY(), stock.getBONDCARRYDATE(), stock.getCONTAINERNO());
//                    log.info(stock.getDCCODE());
                }
                
                List<StStockOutOrgResDto> stockList = new ArrayList<StStockOutOrgResDto>();
                
                for (GTUNRECEIVEDSTOCK stock: response.getGTUNRECEIVEDSTOCK()) {
                     StStockOutOrgResDto resDto = new StStockOutOrgResDto();
                     resDto.setStorerkey("FW00");
                     resDto.setDcCode(stock.getDCCODE());
                     resDto.setDocno(stock.getDOCNO());
                     resDto.setDocline(stock.getDOCLINE());
                     resDto.setSku(stock.getSKU());
                     resDto.setOrganize("2170-" + stock.getLGORT());   
                     resDto.setContractType(stock.getCONTRACTTYPE());
                     resDto.setCustkey(stock.getCUSTKEY());   
                     resDto.setConvSerialNo(stock.getCONVSERIALNO());
                     resDto.setStockQty(new BigDecimal(stock.getSTOCKQTY()));
                     resDto.setBondQty(new BigDecimal(stock.getBONDQTY()));
                     resDto.setUom(stock.getBASEUOM());
                     resDto.setStockBox(new BigDecimal(stock.getSTOCKBOX()));
                     resDto.setBondCarryDate(stock.getBONDCARRYDATE());
                     resDto.setEstNo(stock.getESTNO());
                     resDto.setCountryoforigin(stock.getCOUNTRYOFORIGIN());
                     resDto.setContainerno(stock.getCONTAINERNO());
                     resDto.setExpiryDay(stock.getEXPIRYDAY());
                     resDto.setLcNo(stock.getLCNO());
                     
                     stockList.add(resDto); 
                }
                
                if (stockList != null && stockList.size() > 0) { 
                    resultList = commonDao.selectList(SERVICEID_PREFIX + "getWithTempTable", stockList);
                }
                
            } else {
                log.info("response is null.");
            }
            
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return (List<R>) resultList;
    }
    
    /**
     * @description : 전체 조회 (상품 + 미착재고 조회) 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE         AUTHOR                      MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.30   KimSunHo(sunhokim6229@cj.net) 생성
     */
    @SuppressWarnings("unchecked")
    private <R, T> List<R> getAllList(StStockOutOrgReqDto reqDto, String search20, String search30, String search40) {
        List<StStockOutOrgResDto> resultList = new ArrayList<StStockOutOrgResDto>();
        
        if ("1".equals(search20)) {
            reqDto.setSkuall("Y");
        } else {
            reqDto.setSkuall("N");
        }
        
        if ("1".equals(search30)) {
            reqDto.setSkutpl("Y");
        } else {
            reqDto.setSkutpl("N");
        }
        
        if ("1".equals(search20) || "1".equals(search30)) {
            resultList.addAll(getHeaderList(reqDto));
        }
        
        if ("1".equals(search40)) {
            resultList.addAll(getSCM0540List(reqDto));
        }
       
        return (List<R>) resultList;
    }
    
    /**
     * @description : 조직코드에서 조직번호 추출
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE         AUTHOR                      MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.30   KimSunHo(sunhokim6229@cj.net) 생성
     * */
    private String getOrganizeNo(String input) {
        if (input != null && !input.isEmpty()) {
            // 쉼표 기준 split
            String[] arr = input.split(",");

            // 각 요소에서 앞 5문자 제거
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length() > 5) {
                    arr[i] = arr[i].substring(5);
                }
            }

            return String.join(",", arr);
        } else {
            return "";
        }
    }
    
    /**
     * @description : 이메일 발송
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE         AUTHOR                      MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.30   KimSunHo(sunhokim6229@cj.net) 생성
     * */
    public String sendEmail(StStockOutOrgReqDto paramDto) {
        // 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        StStockOutOrgReqDto reqDto = ModelMapperUtil.map(paramDto, StStockOutOrgReqDto.class);
        
        for (StStockOutOrgResDto emailDto : reqDto.getSaveList()) {
            // 상품코드로 이메일 발송 대상자 조회
            Map emailInfo =  commonDao.selectOne(SERVICEID_PREFIX + "getEmailId", emailDto);
            String emailId = emailInfo.get("MAIL_ID") != null ? emailInfo.get("MAIL_ID").toString() : "";
            String empName = emailInfo.get("EMP_NM") != null ? emailInfo.get("EMP_NM").toString() : "";
        
            // 이메일 주소가 존재할 경우에만 이메일 발송
            if (emailId != null && !emailId.isEmpty()) {
                
                emailId = "sunhokim6229@cj.net";
                
                String emailTitle = "상품 이메일 발송";
                String emailCont = "";
                String emailHtmlStart = "<div class=\"tablecontent_sub_title\">"
                        + "<div class=\"label\">안녕하세요. CJ 프레시웨이 입니다.<br></div>"
                        + "<div class=\"label\"><br></div>"
                        + "</div>"
                        + "<table class=\"tablecontent tablecontent_grid\" id=\"datatable1\" border=\"1\" cellpadding=\"4\" cellspacing=\"0\" bordercolor=\"grey\">"
                        + "<colgroup>"
                        + "<col class=\"col1\" width=\"100\">"
                        + "<col class=\"col1\" width=\"300\">"
                        + "<col class=\"col1\" width=\"100\">"
                        + "</colgroup>"
                        + "<thead bgcolor=\"yellow\">"
                        + "<tr>"
                        + "<th>상품코드</th>"
                        + "<th>상품명</th>"
                        + "<th>수량</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>";

                String emailHtmlEnd = "</tbody>"
                                    + "</table>";
    
                IbExpenseEmailLogEntity entity = new IbExpenseEmailLogEntity();

                emailCont = "<tr>"
                            + "<td align=\"center\">"
                            + emailDto.getSku()
                            + "</td>"
                            + "<td align=\"left\">"
                            + emailDto.getSkuname()
                            + "</td>"
                            + "<td align=\"right\">"
                            + emailDto.getQty().toString()
                            + "</td>"
                            + "</tr>";

                // email 전송
                entity.setTitle(emailTitle);
                entity.setCnts(emailHtmlStart + emailCont + emailHtmlEnd);
                entity.setSendrEmailAddr("cjfreshway@cjfreshway.co.kr");
                entity.setRcvrNm(empName);
                entity.setRcvrEmailAddr(emailId);
    
                commonDao.insert("ibExpenseService.insertEmailLog", entity);
            }//END..if (emailId != null && !emailId.isEmpty())
        }//END..for (StStockOutOrgResDto emailDto : reqDto.getSaveList())
        
        return CanalFrameConstants.MSG_COM_SUC_CODE; 
    }
    
    /**
     * @param dto 
     * @description : 상품 조회 기능을 구현한 Method
     * @issues : 테스트를 위한 임시 Method입니다.
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.01 ParkJinWoo 생성
     */
    public String saveSkuAmount() {
        StStockOutOrgReqDto reqDto = new StStockOutOrgReqDto();
        reqDto.setFixDcCode("2170");
        reqDto.setSkuall("Y");
        reqDto.setSkutpl("Y");
        reqDto.setChkQty("0");
        reqDto.setChkQty1("0");
        
        String searchKey = "";
        String resultKey = "";
        String ioType = "";
        String dccode = "";
        
        Integer paramListCnt = 500;
        Integer paramRow = 0;
        Integer updateDsRow = 0;
        Integer findRow = 0;

        int rsltCnt = 0;
        List<StStockOutOrgResDto> searchResultList = new ArrayList<StStockOutOrgResDto>();
        searchResultList = commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist", reqDto);
        rsltCnt = searchResultList.size();
        
        SI_MM0090_SCM_SOProxy proxy = new SI_MM0090_SCM_SOProxy();

        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");

            DT_MM0090_SCM  reqData = new DT_MM0090_SCM();
            reqData.setXSYS("WMS");
            reqData.setXDATS(dateFormat.format(calendar.getTime()));
            reqData.setXTIMS(timeFormat.format(calendar.getTime()));
            reqData.setXROWS(String.valueOf('1'));

            DT_MM0090_SCMIF_ST_STOCKAMT param = new DT_MM0090_SCMIF_ST_STOCKAMT();
            DT_MM0090_SCMIF_ST_STOCKAMT[] paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
            if ( rsltCnt <= paramListCnt ) {
                paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ rsltCnt ];
            }
            else {
                paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
            }
            
            for (int DsRow = 0; DsRow < rsltCnt; DsRow++ ) {
                param = new DT_MM0090_SCMIF_ST_STOCKAMT();
                dccode = searchResultList.get(DsRow).getDcCode();
                param.setPLANT(searchResultList.get(DsRow).getDcCode());
                param.setSTORAGELOC(searchResultList.get(DsRow).getStorageLoc());
                param.setSKU(searchResultList.get(DsRow).getSku());
                param.setUOM(searchResultList.get(DsRow).getUom());
                param.setQTY(searchResultList.get(DsRow).getSumQty().toString());
                param.setPOLINE(searchResultList.get(DsRow).getPoLine());
                param.setSTOCKQTY(searchResultList.get(DsRow).getStockQty().toString());
                
                if ("3".equals(searchResultList.get(DsRow).getSerialType())) {
                    param.setSERIALNO("");
                } else {
                    param.setSERIALNO(searchResultList.get(DsRow).getSerialNo());
                }
                
                param.setCONVSERIALNO(searchResultList.get(DsRow).getConvSerialNo());
                param.setSLIPDT("19000101");
                param.setSLIPNO(searchResultList.get(DsRow).getSerialKey());
                param.setSLIPLINE("00000"); 
                paramList[paramRow] = param;
                paramRow++;
                
                if ((paramRow.equals( paramListCnt )) || String.valueOf(DsRow + 1).equals( String.valueOf(rsltCnt))) {
                    reqData = new DT_MM0090_SCM();
                    reqData.setXSYS("WMS");
                    reqData.setXDATS(dateFormat.format(calendar.getTime()));
                    reqData.setXTIMS(timeFormat.format(calendar.getTime()));
                    reqData.setXROWS(String.valueOf(paramRow));    
                    reqData.setIF_ST_STOCKAMT(paramList);
                    
                    DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] response = proxy.si_mm0090_scm_so(reqData);

                    for (int i = 0; i < paramRow; i++ ) {
                        if ( updateDsRow >= rsltCnt) {
                            break;
                        }

                        searchKey =  searchResultList.get(updateDsRow).getDcCode()
                               + searchResultList.get(updateDsRow).getStorageLoc()
                               + searchResultList.get(updateDsRow).getSerialKey() 
                               + searchResultList.get(updateDsRow).getSku();

                        findRow = -1;
                        
                        for (int ii = 0; ii < paramRow; ii++) {
                            resultKey =  response[ii].getPLANT()
                                     + response[ii].getSTORAGELOC()
                                     + response[ii].getSLIPNO()
                                      + response[ii].getSKU();  
                            if ( searchKey.equals(resultKey) ) {
                                findRow = ii;
                                break;
                            }
                        }

                        if ( findRow >= 0 ) {
                            String stat = response[findRow].getXSTAT(); 
                            if("E".equals(stat)) {
                                searchResultList.get(updateDsRow).setStockamtmsg(response[findRow].getXMSGS());
                            } else {
                                searchResultList.get(updateDsRow).setStockamt(response[findRow].getSTOCKAMT());
                                searchResultList.get(updateDsRow).setStockPrice(response[findRow].getPURCHASEPRICE());
                                searchResultList.get(updateDsRow).setStockQty1(response[findRow].getSTOCKQTY().toString());
                            }
                        }

                        updateDsRow++;
                        if ( searchResultList.size() - DsRow > paramListCnt ) {
                            paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
                        } else {
                            paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ searchResultList.size() - DsRow];
                        }
                    }       
                    
                    paramRow=0;
                }//END..if ((paramRow.equals( paramListCnt )) || String.valueOf(DsRow + 1).equals( String.valueOf(rsltCnt)))
            }//END..for (int DsRow = 0; DsRow < rsltCnt; DsRow++ )
        } catch(Exception e) {
            log.error("재고금액 처리중 에러발생", e);
        }

        // 상품별 재고금액 기존 데이터 삭제 
        Map<String, Object> paramMap = new java.util.HashMap<>();
        commonDao.delete(SERVICEID_PREFIX + "delete", paramMap);
        
        Integer serialkey = 0;
        
        for (StStockOutOrgResDto dto : searchResultList ) {
            log.debug("result ==> {}, {}, {}, {}", dto.getSku(), dto.getStockQty(), dto.getStockPrice(), dto.getStockamt());
            
            
            ExdcSkuAmountEntity entityDto = new ExdcSkuAmountEntity();
            
            entityDto.setSerialkey(new BigDecimal(serialkey++));
            entityDto.setDccode(dto.getDcCode());
            entityDto.setOrganize(dto.getOrganize());
            entityDto.setSku(dto.getSku());
            entityDto.setStockgrade(dto.getStockGrade());
            entityDto.setQty(dto.getStockQty());

            String stockPrice = dto.getStockPrice();
            if (stockPrice != null && !stockPrice.trim().isEmpty()) {
                stockPrice = stockPrice.trim().replace(",", "");
                entityDto.setStockprice(new BigDecimal(stockPrice));
            } else {
                entityDto.setStockprice(BigDecimal.ZERO);
            }
            
            String stockamt = dto.getStockamt();
            if (stockamt != null && !stockamt.trim().isEmpty()) {
                stockamt = stockamt.trim().replace(",", "");
                entityDto.setStockamt(new BigDecimal(stockamt));
            } else {
                entityDto.setStockamt(BigDecimal.ZERO);
            }

            commonDao.insert(SERVICEID_PREFIX + "insert", entityDto);
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
}    
