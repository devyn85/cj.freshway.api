package cjfw.wms.ib.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ib.dto.IbExpenseStatusPopupReqDto;
import cjfw.wms.ib.dto.IbExpenseStatusPopupResDto;
import cjfw.wms.ib.dto.IbExpenseStatusReqDto;
import cjfw.wms.ib.dto.IbExpenseStatusResDto;
import cjfw.wms.webservice.common.WmsEaiLocaterService;
import cjfw.wms.webservice.exDCExpenseStatus.DT_SCM0520_SCM;
import cjfw.wms.webservice.exDCExpenseStatus.DT_SCM0520_SCMT_MASTER;
import cjfw.wms.webservice.exDCExpenseStatus.DT_SCM0520_SCM_response;
import cjfw.wms.webservice.exDCExpenseStatus.DT_SCM0520_SCM_responseT_DETAIL;
import cjfw.wms.webservice.exDCExpenseStatus.DT_SCM0520_SCM_responseT_MASTER;
import cjfw.wms.webservice.exDCExpenseStatus.SI_SCM0520_SCM_SOProxy;
import cjfw.wms.webservice.scm0550.DTSCM0550SCM;
import cjfw.wms.webservice.scm0550.DTSCM0550SCMResponse;
import cjfw.wms.webservice.scm0550.SISCM0550SCMSO;
import cjfw.wms.webservice.scm0550.SISCM0550SCMSOService;
import cjfw.wms.webservice.scm0560.DTSCM0560SCM;
import cjfw.wms.webservice.scm0560.DTSCM0560SCMResponse;
import cjfw.wms.webservice.scm0560.SISCM0560SCMSO;
import cjfw.wms.webservice.scm0560.SISCM0560SCMSOService;
import cjfw.wms.webservice.scm0570.DTSCM0570SCM;
import cjfw.wms.webservice.scm0570.DTSCM0570SCMResponse;
import cjfw.wms.webservice.scm0570.SISCM0570SCMSO;
import cjfw.wms.webservice.scm0570.SISCM0570SCMSOService;
import jakarta.xml.ws.BindingProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * 
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.09.02 
 * @description : 외부창고 원가관리리포트 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IbExpenseStatusService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "ibExpenseStatusService.";		
	private final WmsEaiLocaterService eaiLocaterService;
	private final CommonDao commonDao;

	/**
	 * @throws RemoteException 
	 * @description : 외부창고 원가관리리포트 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE          AUTHOR                  MAJOR_ISSUE
	 * ----------------------------------------------------------- 
	 * 2025.09.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public IbExpenseStatusReqDto getMasterList(IbExpenseStatusReqDto paramDto) throws RemoteException {
	    IbExpenseStatusReqDto result = new IbExpenseStatusReqDto();
	    IbExpenseStatusReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpenseStatusReqDto.class);
	    
	    SI_SCM0520_SCM_SOProxy proxy = new SI_SCM0520_SCM_SOProxy();
        
        DT_SCM0520_SCM fiData = new DT_SCM0520_SCM();
        
        DT_SCM0520_SCMT_MASTER[] t_request_list = new DT_SCM0520_SCMT_MASTER[1];
        DT_SCM0520_SCMT_MASTER t_request = new DT_SCM0520_SCMT_MASTER();
        
        t_request.setSTANDARD_DATE(reqDto.getPostingdate());
        t_request.setPOKEY(reqDto.getErppono());
        t_request.setPOLINE(reqDto.getPolineno());
        t_request.setSKU(reqDto.getItemcode());
        t_request.setCONVSERIALNO(reqDto.getHouseblno());
        t_request.setCUSTKEY(reqDto.getCustomercode());
        
        t_request_list[0] = t_request;
        fiData.setT_MASTER(t_request_list);
        
        DT_SCM0520_SCM_response response = null;
        response = proxy.sI_SCM0520_SCM_SO(fiData);
        
        if (response == null) {
            throw new UserHandleException("보세창고 원가정보 조회 중 에러 발생 : 응답이 없습니다.");
        }
        
        if (!"S".equals(response.getXSTAT())) {
            throw new UserHandleException("보세창고 원가정보 조회 중 에러 발생 : " + response.getXMSGS());
        }
        
        DT_SCM0520_SCM_responseT_MASTER[] t_master_list = response.getT_MASTER();
        DT_SCM0520_SCM_responseT_DETAIL[] t_detail_list = response.getT_DETAIL();   
        
        List<IbExpenseStatusResDto> headerList = commonDao.selectList(SERVICEID_PREFIX + "getMasterlist", reqDto);
        List<IbExpenseStatusResDto> detailList = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", reqDto);

        detailList.remove(detailList.size() - 1);

        if (t_master_list != null && t_master_list.length > 0) {   
            for (int i =  2 ; i >= 0; i--) {
                boolean isExist = false;
                for (DT_SCM0520_SCM_responseT_MASTER t_master : t_master_list) {
                    if (t_master == null) {
                        continue;
                    }
                    log.info("{}", headerList.size());
                    if (headerList != null && headerList.size() > 0) {
                        
                        log.info("t_master.getPAYMENT_CODE() {}", t_master.getPAYMENT_CODE());
                        log.info("headerList.getCode() {}", (((IbExpenseStatusResDto) headerList.get(i)).getCode()));
                        log.info("{}", headerList.size());
                        log.info("{}", headerList.get(i));
                        
                        String paymentCode = t_master.getPAYMENT_CODE() == null ? "" : t_master.getPAYMENT_CODE();
                        String headerCode = ((IbExpenseStatusResDto) headerList.get(i)).getCode() == null ? "" : ((IbExpenseStatusResDto) headerList.get(i)).getCode();
                        
                        if (headerCode.equals(paymentCode)) {
                            isExist = true;
                            headerList.get(i).setQuantity(headerList.get(i).getQuantity() + Double.parseDouble(t_master.getQTY()));
                            headerList.get(i).setQuantityUnit(t_master.getUOM());
                            headerList.get(i).setAmount(headerList.get(i).getAmount() + Double.parseDouble(t_master.getAMOUNT()));
                            headerList.get(i).setAmountUnit(t_master.getAMOUNT_UNIT());
    
                            if (t_master.getBASE_DATE() != null && t_master.getBASE_DATE().length() > 0) {
                                String baseDate = t_master.getBASE_DATE().replace("-", "").substring(0,8); 
                                if(headerList.get(i).getBaseDate() == null || baseDate.compareTo(headerList.get(i).getBaseDate()) == 1) {
                                    headerList.get(i).setBaseDate(baseDate);
                                }
                            }
                        }
                    }
                }
                
                if (!isExist) {
                    headerList.remove(i);
                }
            }
        }
        
        if (t_detail_list != null && t_detail_list.length > 0) {   
            for (DT_SCM0520_SCM_responseT_DETAIL t_detail : t_detail_list) {
                if (t_detail == null) {
                    continue;
                }
                
                IbExpenseStatusResDto detailDto = new IbExpenseStatusResDto();
                detailDto.setDocumentTypeCode(t_detail.getDOCTYPE());
                detailDto.setDocumentNumber(t_detail.getDOCNO());
                detailDto.setPoNo(t_detail.getTCSPOKEY());
                detailDto.setAccountDetailCode(t_detail.getACCOUNT_DETAIL_CODE());
                detailDto.setAccountDetailName(t_detail.getACCOUNT_DETAIL_NAME());
                detailDto.setQuantity(Double.parseDouble(t_detail.getQTY()));
                detailDto.setQuantityUnit(t_detail.getUOM());
                detailDto.setAmount(Double.parseDouble(t_detail.getAMOUNT()));
                detailDto.setAmountUnit(t_detail.getAMOUNT_UNIT());
                if (t_detail.getBASE_DATE() != null && t_detail.getBASE_DATE().length() > 0) {
                    detailDto.setBaseDate(t_detail.getBASE_DATE().replace("-", "").substring(0,8));
                }
                
                detailList.add(detailDto);
            }
        }
        
        result.setHeaderList(headerList);
        result.setDetailList(detailList);

        return (IbExpenseStatusReqDto) result;
	}
    
    /**
     * @description : 외부창고 원가관리리포트 Popup ERP 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.09.02    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<IbExpenseStatusPopupResDto> getPopupErpPoNoInfo(IbExpenseStatusPopupReqDto reqDto) {
        List<IbExpenseStatusPopupResDto> resultList = new ArrayList<IbExpenseStatusPopupResDto>();
        
        DTSCM0560SCMResponse response = null;
        try {
            // EAI Locater Service 호출
            Map<String, String> locator = null;
            locator = eaiLocaterService.getLocater("SCM0560");
            if (locator == null) {
                throw new Exception("locator is null.");
            }
            
            DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
            LocalDateTime now = LocalDateTime.now();
            String dayString  = now.format(dayFormatter);
            String timeString = now.format(timeFormatter);

            // JAX-WS Service & Port 생성
            SISCM0560SCMSOService service = new SISCM0560SCMSOService();
            SISCM0560SCMSO port = service.getPort(SISCM0560SCMSO.class);
            
            // SOAP 요청 기본 파라미터 설정
            DTSCM0560SCM request = new DTSCM0560SCM();
            request.setXSYS("WMS");
            request.setXDATS(dayString);
            request.setXTIMS(timeString);
            request.setXROWS("1");
            
            List<DTSCM0560SCM.TPARAM> paramList = new ArrayList<DTSCM0560SCM.TPARAM>();
            DTSCM0560SCM.TPARAM param = new DTSCM0560SCM.TPARAM();
            
            // 조회 요청 파라미터 설정 
            String SearchDateCategory = reqDto.getSearchDateCategory();
            String issuedateFrom = reqDto.getIssuedateFrom() != null ? reqDto.getIssuedateFrom() : null;
            String issuedateTo = reqDto.getIssuedateTo() != null ? reqDto.getIssuedateTo() : null;
            
            if ("issuedate".equals(SearchDateCategory)) {
                param.setISSUEDATEFR(issuedateFrom);
                param.setISSUEDATETO(issuedateTo);
            } else if ("shippingdate".equals(SearchDateCategory)) {
                param.setSHIPPINGDATEFR(issuedateFrom);
                param.setSHIPPINGDATETO(issuedateTo);
            } else if ("expirydate".equals(SearchDateCategory)) {
                param.setEXPIRYDATEFR(issuedateFrom);
                param.setEXPIRYDATETO(issuedateTo);
            } else if ("arrivaldate".equals(SearchDateCategory)) {
                param.setARRIVALDATEFR(issuedateFrom);
                param.setARRIVALDATETO(issuedateTo);
            } else if ("regdate".equals(SearchDateCategory)) {
                param.setREGDATEFR(issuedateFrom);
                param.setREGDATETO(issuedateTo);
            } else if ("moddate".equals(SearchDateCategory)) {
                param.setMODDATEFR(issuedateFrom);  
                param.setMODDATETO(issuedateTo);
            }
            
            String hblno = reqDto.getHblno() != null ? reqDto.getHblno() : null;
            String suppliername = reqDto.getSuppliername() != null ? reqDto.getSuppliername() : null;
            String businesstypename = reqDto.getBusinesstypename() != null ? reqDto.getBusinesstypename() : null;
            String erppoid = reqDto.getErppoid() != null ? reqDto.getErppoid() : null;
            String keyno = reqDto.getKeyno() != null ? reqDto.getKeyno() : null;
            
            param.setHBLNO(hblno);
            param.setSUPPLIERNAME(suppliername);  
            param.setBUSINESSTYPENAME(businesstypename); 
            param.setERPPOID(erppoid);
            param.setKEYNO(keyno);
            paramList.add(param); 
            request.setTPARAM(paramList);

            // Endpoint, User, Password 설정
            Map<String, Object> context = ((BindingProvider) port).getRequestContext();
            context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locator.get("endpoint"));
            context.put(BindingProvider.USERNAME_PROPERTY, locator.get("username"));
            context.put(BindingProvider.PASSWORD_PROPERTY, locator.get("password"));
            
            log.info("endpoint : {}", locator.get("endpoint"));
            log.info("username : {}", locator.get("username"));
            log.info("password : {}", locator.get("password"));

            // SOAP 호출
            response = port.siSCM0560SCMSO(request);
            log.info("response : {} ", response);
            if (response != null && !CollectionUtils.isEmpty(response.getTRETURN())) {
                log.info("TRETURN size : {} ", response.getTRETURN().size());
            
                List<IbExpenseStatusPopupResDto> stockList = new ArrayList<IbExpenseStatusPopupResDto>();
                
                for (DTSCM0560SCMResponse.TRETURN res: response.getTRETURN()) {
                    
                    log.info("res : {}", res);
                    
                     IbExpenseStatusPopupResDto resDto = new IbExpenseStatusPopupResDto();
                     resDto.setStatus(res.getSTATUS());
                     resDto.setErpPoId(res.getERPPOID());
                     resDto.setInvoiceFlag(res.getINVOICEFLAG());
                     resDto.setHblNo(res.getHBLNO());
                     resDto.setDept(res.getDEPT());
                     resDto.setKeyNo(res.getKEYNO());
                     resDto.setLovItem(res.getLOVITEM());
                     resDto.setBusinessTypeName(res.getBUSINESSTYPENAME());
                     resDto.setRevisionNo(res.getREVISIONNO());
                     resDto.setIssueDate(res.getISSUEDATE());
                     resDto.setShippingDate(res.getSHIPPINGDATE());
                     resDto.setExpiryDate(res.getEXPIRYDATE());
                     resDto.setArrivalDate(res.getARRIVALDATE());
                     resDto.setTransport(res.getTRANSPORT());
                     resDto.setAttributes4(res.getATTRIBUTES4());
                     resDto.setSupplierName(res.getSUPPLIERNAME());
                     resDto.setPriceTermsCode(res.getPRICETERMSCODE());
                     resDto.setPriceTermsName(res.getPRICETERMSNAME());
                     resDto.setPaymentTypeCode(res.getPAYMENTTYPECODE());
                     resDto.setPaymentName(res.getPAYMENTNAME());
                     resDto.setTotalAmount(new BigDecimal(res.getTOTALAMOUNT()));
                     resDto.setTotalAmountUnit(res.getTOTALAMOUNTUNIT());
                     resDto.setRegId(res.getREGID());
                     resDto.setRegDate(res.getREGDATE());
                     resDto.setModId(res.getMODID());
                     resDto.setModDate(res.getMODDATE());

                     resultList.add(resDto); 
                }

            } else {
                log.info("response is null.");
            }
            
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return (List<IbExpenseStatusPopupResDto>) resultList;
    }
    
    /**
     * @description : 외부창고 원가관리리포트 Popup Item 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.09.02    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<IbExpenseStatusPopupResDto> getPopupItemCodeInfo(IbExpenseStatusPopupReqDto reqDto) {
        List<IbExpenseStatusPopupResDto> resultList = new ArrayList<IbExpenseStatusPopupResDto>();
        
        DTSCM0550SCMResponse response = null;
        try {
            // EAI Locater Service 호출
            Map<String, String> locator = null;
            locator = eaiLocaterService.getLocater("SCM0550");
            if (locator == null) {
                throw new Exception("locator is null.");
            }
            
            DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
            LocalDateTime now = LocalDateTime.now();
            String dayString  = now.format(dayFormatter);
            String timeString = now.format(timeFormatter);

            // JAX-WS Service & Port 생성
            SISCM0550SCMSOService service = new SISCM0550SCMSOService();
            SISCM0550SCMSO port = service.getPort(SISCM0550SCMSO.class);
            
            // SOAP 요청 기본 파라미터 설정
            DTSCM0550SCM request = new DTSCM0550SCM();
            request.setXSYS("WMS");
            request.setXDATS(dayString);
            request.setXTIMS(timeString);
            request.setXROWS("1");
            
            List<DTSCM0550SCM.TPARAM> paramList = new ArrayList<DTSCM0550SCM.TPARAM>();
            DTSCM0550SCM.TPARAM param = new DTSCM0550SCM.TPARAM();
            
            // 조회 요청 파라미터 설정
            String issuedateFrom = reqDto.getIssuedateFrom() != null ? reqDto.getIssuedateFrom() : null;
            String issuedateTo = reqDto.getIssuedateTo() != null ? reqDto.getIssuedateTo() : null;
            String itemcode = reqDto.getItemcode() != null ? reqDto.getItemcode() : null;
            String itemdescription = reqDto.getItemdescription() != null ? reqDto.getItemdescription() : null;
            String erppoid = reqDto.getErppoid() != null ? reqDto.getErppoid() : null;
            
            param.setITEMCODE(itemcode);
            param.setITEMDESCRIPTION(itemdescription);  
            param.setERPPOID(erppoid); 
            param.setISSUEDATEFR(issuedateFrom);
            param.setISSUEDATETO(issuedateTo);
            paramList.add(param); 
            request.setTPARAM(paramList);

            // Endpoint, User, Password 설정
            Map<String, Object> context = ((BindingProvider) port).getRequestContext();
            context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locator.get("endpoint"));
            context.put(BindingProvider.USERNAME_PROPERTY, locator.get("username"));
            context.put(BindingProvider.PASSWORD_PROPERTY, locator.get("password"));
            
            log.info("endpoint : {}", locator.get("endpoint"));
            log.info("username : {}", locator.get("username"));
            log.info("password : {}", locator.get("password"));

            // SOAP 호출
            response = port.siSCM0550SCMSO(request);
            log.info("response : {} ", response);
            if (response != null && !CollectionUtils.isEmpty(response.getTRETURN())) {
                log.info("TRETURN size : {} ", response.getTRETURN().size());
            
                List<IbExpenseStatusPopupResDto> stockList = new ArrayList<IbExpenseStatusPopupResDto>();
                
                for (DTSCM0550SCMResponse.TRETURN res: response.getTRETURN()) {
                    
                    log.info("res : {}", res);
                    
                     IbExpenseStatusPopupResDto resDto = new IbExpenseStatusPopupResDto();
                     resDto.setStatus(res.getSTATUS());
                     resDto.setInvoiceFlag(res.getINVOICEFLAG());
                     resDto.setKeyNo(res.getKEYNO());
                     resDto.setIssueDate(res.getISSUEDATE());
                     resDto.setSupplierName(res.getSUPPLIERNAME());
                     resDto.setSn(res.getSN());
                     resDto.setPoSn(res.getPOSN());
                     resDto.setLineNo(res.getLINENO());
                     resDto.setItemCode(res.getITEMCODE());
                     resDto.setItemDescription(res.getITEMDESCRIPTION());
                     resDto.setHsCode(res.getHSCODE());
                     resDto.setQuantity(new BigDecimal(res.getQUANTITY()));
                     resDto.setQuantityUnit(res.getQUANTITYUNIT());
                     resDto.setUnitPrice(new BigDecimal(res.getUNITPRICE()));
                     resDto.setAmount(new BigDecimal(res.getAMOUNT()));
                     resDto.setAmountUnit(res.getAMOUNTUNIT());
                     resDto.setNetWeight(new BigDecimal(res.getNETWEIGHT()));
                     resDto.setNetWeightUnit(res.getNETWEIGHTUNIT());
                     resDto.setGrossWeight(new BigDecimal(res.getGROSSWEIGHT()));
                     resDto.setGrossWeightUnit(res.getGROSSWEIGHTUNIT());
                     resDto.setVolume(res.getVOLUME());
                     resDto.setErpPoId(res.getERPPOID());
                     resDto.setSaleCustomerCode(res.getSALECUSTOMERCODE());
                     resDto.setSaleCustomerName(res.getSALECUSTOMERNAME()); 
                     resDto.setHouseBlNo(res.getHOUSEBLNO());

                     resultList.add(resDto); 
                }

            } else {
                log.info("response is null.");
            }
            
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return (List<IbExpenseStatusPopupResDto>) resultList;
    }
    
    /**
     * @description : 외부창고 원가관리리포트 Popup HouseBLNo 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.09.02    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<IbExpenseStatusPopupResDto> getPopupHouseBLNoInfo(IbExpenseStatusPopupReqDto reqDto) {
        List<IbExpenseStatusPopupResDto> resultList = new ArrayList<IbExpenseStatusPopupResDto>();
        
        DTSCM0570SCMResponse response = null;
        try {
            // EAI Locater Service 호출
            Map<String, String> locator = null;
            locator = eaiLocaterService.getLocater("SCM0570");
            if (locator == null) {
                throw new Exception("locator is null.");
            }
            
            DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
            LocalDateTime now = LocalDateTime.now();
            String dayString  = now.format(dayFormatter);
            String timeString = now.format(timeFormatter);

            // JAX-WS Service & Port 생성
            SISCM0570SCMSOService service = new SISCM0570SCMSOService();
            SISCM0570SCMSO port = service.getPort(SISCM0570SCMSO.class);
            
            // SOAP 요청 기본 파라미터 설정
            DTSCM0570SCM request = new DTSCM0570SCM();
            request.setXSYS("WMS");
            request.setXDATS(dayString);
            request.setXTIMS(timeString);
            request.setXROWS("1");
            
            List<DTSCM0570SCM.TPARAM> paramList = new ArrayList<DTSCM0570SCM.TPARAM>();
            DTSCM0570SCM.TPARAM param = new DTSCM0570SCM.TPARAM();
            
            // 조회 요청 파라미터 설정 
            String SearchDateCategory = reqDto.getSearchDateCategory();
            String regdateFrom = reqDto.getRegdateFrom() != null ? reqDto.getRegdateFrom() : null;
            String regdateTo = reqDto.getRegdateTo() != null ? reqDto.getRegdateTo() : null;
            
            if ("issuedate".equals(SearchDateCategory)) {
                param.setISSUEDATEFR(regdateFrom);
                param.setISSUEDATETO(regdateTo);
            } else if ("regdate".equals(SearchDateCategory)) {
                param.setREGDATEFR(regdateFrom);
                param.setREGDATETO(regdateTo);
            } 
            
            String houseblno = reqDto.getHouseblno() != null ? reqDto.getHouseblno() : null;
            
            param.setHOUSEBLNO(houseblno);
            paramList.add(param); 
            request.setTPARAM(paramList);

            // Endpoint, User, Password 설정
            Map<String, Object> context = ((BindingProvider) port).getRequestContext();
            context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locator.get("endpoint"));
            context.put(BindingProvider.USERNAME_PROPERTY, locator.get("username"));
            context.put(BindingProvider.PASSWORD_PROPERTY, locator.get("password"));
            
            log.info("endpoint : {}", locator.get("endpoint"));
            log.info("username : {}", locator.get("username"));
            log.info("password : {}", locator.get("password"));

            // SOAP 호출
            response = port.siSCM0570SCMSO(request);
            log.info("response : {} ", response);
            if (response != null && !CollectionUtils.isEmpty(response.getTRETURN())) {
                log.info("TRETURN size : {} ", response.getTRETURN().size());
            
                List<IbExpenseStatusPopupResDto> stockList = new ArrayList<IbExpenseStatusPopupResDto>();
                
                for (DTSCM0570SCMResponse.TRETURN res: response.getTRETURN()) {
                    
                    log.info("res : {}", res);
                    
                     IbExpenseStatusPopupResDto resDto = new IbExpenseStatusPopupResDto();
                     resDto.setStatus(res.getSTATUS());
                     resDto.setLgFlag(res.getLGFLAG());
                     resDto.setAttributes1(res.getATTRIBUTES1());
                     resDto.setPlaceOfReceiptCode(res.getPLACEOFRECEIPTCODE());
                     resDto.setPlaceOfReceiptName(res.getPLACEOFRECEIPTNAME());
                     resDto.setHouseBlNo(res.getHOUSEBLNO());
                     resDto.setMasterBlNo(res.getMASTERBLNO());
                     resDto.setPoNo(res.getPONO());
                     resDto.setPurPerson(res.getPURPERSON());
                     resDto.setPaymentType(res.getPAYMENTTYPE());
                     resDto.setShipperCode(res.getSHIPPERCODE());
                     resDto.setShipperName(res.getSHIPPERNAME());
                     resDto.setTotalAmount(new BigDecimal(res.getTOTALAMOUNT()));
                     resDto.setTotalAmountUnit(res.getTOTALAMOUNTUNIT());
                     resDto.setOnboardDate(res.getONBOARDDATE());
                     resDto.setArrivalDate(res.getARRIVALDATE());
                     resDto.setIssueDate(res.getISSUEDATE());
                     resDto.setCustomsFlag(res.getCUSTOMSFLAG());
                     resDto.setCarrierName(res.getCARRIERNAME());
                     resDto.setShippingPortName(res.getSHIPPINGPORTNAME());
                     resDto.setArrivalPortName(res.getARRIVALPORTNAME());
                     resDto.setPriceTermsCode(res.getPRICETERMSCODE());
                     resDto.setPriceTermsName(res.getPRICETERMSNAME()); 
                     resDto.setTransportKind(res.getTRANSPORTKIND());
                     resDto.setTotalPacking(res.getTOTALPACKING());
                     resDto.setTotalGrossWeight(new BigDecimal(res.getTOTALGROSSWEIGHT()));
                     resDto.setMeasurement(res.getMEASUREMENT());
                     resDto.setReceiptDate(res.getRECEIPTDATE());
                     resDto.setPlaceOfReceiptName(res.getPLACEOFRECEIPTNAME());
                     resDto.setRegId(res.getREGID());
                     resDto.setRegDate(res.getREGDATE());
                     resDto.setModId(res.getMODID());
                     resDto.setModDate(res.getMODDATE());   
                     resDto.setSn(res.getSN());
                     resDto.setTotalQuantity(new BigDecimal(res.getTOTALQUANTITY()));
                     resDto.setTotalQuantityUnit(res.getTOTALQUANTITYUNIT());
                     resDto.setErpPoId(res.getERPPOID());

                     resultList.add(resDto); 
                }
                
            } else {
                log.info("response is null.");
            }
            
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return (List<IbExpenseStatusPopupResDto>) resultList;
    }
   
}
