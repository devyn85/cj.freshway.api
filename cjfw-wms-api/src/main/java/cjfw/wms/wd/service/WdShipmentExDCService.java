package cjfw.wms.wd.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.sap.JCoUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ApiApplication;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.wd.dto.WdShipmentExDCPricePopupResDto;
import cjfw.wms.wd.dto.WdShipmentExDCReqDto;
import cjfw.wms.wd.dto.WdShipmentExDCResDto;
import cjfw.wms.wd.dto.WdShipmentExDCStockpriceResDto;
import cjfw.wms.wd.entity.WdShipmentExDCEntity;
import cjfw.wms.wd.entity.WdShipmentExDCPricePopupEntity;
import cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCMIT_ORDQTY;
import cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_response;
import cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_responseOT_ORDQTY;
import cjfw.wms.webservice.foOrdQty.SI_CJFO0060_SCM_SOProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.23 
 * @description : 외부비축출고처리 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.30    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdShipmentExDCService {

    private final ApiApplication apiApplication;

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdShipmentExDCService.";	
	private transient static final String PAKAGE_NAME = "SPWD_SHIPMENT_EXDC";
	private transient static final String SAP_FUNCTION_NAME = "ZMM_CHECK_GI_STATUS";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @throws RemoteException 
	 * @description : 외부비축출고처리 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.30    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<WdShipmentExDCResDto> getMasterList(WdShipmentExDCReqDto wdShipmentExDCReqDto) throws RemoteException {
	    List<WdShipmentExDCResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", wdShipmentExDCReqDto);
		
	    int count = commonDao.selectOne(SERVICEID_PREFIX + "getFoSapIfYn", wdShipmentExDCReqDto);
	    if (count > 0) {
	        int requestCnt = 0;
            for (int i = 0; i < result.size(); i++) {  
                if (!"85".equals(result.get(i).getStatus()) 
                    && !"90".equals(result.get(i).getStatus())
                    && !"Y".equals(result.get(i).getSerialyn())){
                    requestCnt++;
                }
            }
            if (requestCnt > 0) {
                SI_CJFO0060_SCM_SOProxy proxy = new SI_CJFO0060_SCM_SOProxy();
                DT_CJFO0060_SCMIT_ORDQTY[] itOrdQty = new DT_CJFO0060_SCMIT_ORDQTY[requestCnt];
                
                int requestIndex=0;
                for (int i = 0; i < result.size(); i++) {//    
                    if (!"85".equals(result.get(i).getStatus()) 
                        && !"90".equals(result.get(i).getStatus())
                        && !"Y".equals(result.get(i).getSerialyn()) ){
                        itOrdQty[requestIndex] = new DT_CJFO0060_SCMIT_ORDQTY();
                        itOrdQty[requestIndex].setVDATU(    result.get(i).getSlipdt());
                        itOrdQty[requestIndex].setVBELN_FW( result.get(i).getDocno());
                        itOrdQty[requestIndex].setPOSNR(    result.get(i).getDocline());
                        requestIndex++;
                    }
                }

                DT_CJFO0060_SCM_response response = null;
                response = proxy.SI_CJFO0060_SCM_SO(itOrdQty);

                if ("S".equals(response.getES_RETURN().getTYPE())){
                    DT_CJFO0060_SCM_responseOT_ORDQTY[] resultLst = response.getOT_ORDQTY();
                    if (resultLst != null && resultLst.length > 0){
                        String nSource, nTarget;
                        for (int sourceRow = 0; sourceRow < resultLst.length; sourceRow++) {
                            nSource = resultLst[sourceRow].getVBELN_FW() + "_" + resultLst[sourceRow].getPOSNR();
                            for (int targetRow = 0; targetRow < result.size(); targetRow++){
                                nTarget = result.get(targetRow).getDocno() + "_" + result.get(targetRow).getDocline();
                                if (nSource.equals(nTarget) 
                                    && !"85".equals(result.get(targetRow).getStatus()) 
                                    && !"90".equals(result.get(targetRow).getStatus())
                                    && !"Y".equals(result.get(targetRow).getSerialyn())) {
                                    result.get(targetRow).setConfirmqty(new BigDecimal(resultLst[sourceRow].getMENGE_FW()));    //출고수량 변경
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } 
	
	    return result;
	}
	
	/**
	 * @throws JCoException 
	 * @description : 외부비축출고처리 대상 확정 저장
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE          AUTHOR                  MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public String saveMasterList(WdShipmentExDCReqDto paramDto) throws JCoException {
	    // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String[] keyList = null;
        Object[] valueList= null;
        
        WdShipmentExDCReqDto reqDto = ModelMapperUtil.map(paramDto, WdShipmentExDCReqDto.class);
                
        List<WdShipmentExDCResDto> saveList = reqDto.getSaveList();
        
        if (null != saveList) {
            for (WdShipmentExDCResDto dto : saveList) { 
                Integer existCnt = commonDao.selectOne(SERVICEID_PREFIX + "getExistDoc", dto); 
                if (existCnt > 0) {
                    throw new UserHandleException("외부창고 보관료 전표가 이미 존재합니다.");
                }
            
                List<WdShipmentExDCResDto> skuList = commonDao.selectList(SERVICEID_PREFIX + "getSkuForCheck", dto);
                
                if (skuList != null && skuList.size() > 0) {
                    JCoUtil jco = new JCoUtil();
                    JCoDestination destination = jco.getDestination();
                    JCoFunction function = jco.getFunction(SAP_FUNCTION_NAME);
                    JCoParameterList params = function.getImportParameterList();
                    JCoParameterList returnData = null;
                    
                    for (WdShipmentExDCResDto snInfo : skuList) { 
                        // RFC 함수 선언                         
                        if (function == null) {
                            log.error("▶외부비축출고처리 저장 -> " + SAP_FUNCTION_NAME + " not found in SAP.");
                            throw new UserHandleException(SAP_FUNCTION_NAME + " not found in SAP.");
                        }
                       
                        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++ SAP 시작");
                        
                        // RFC 함수 Parameter 입력
                        params.setValue("I_WERKS" , snInfo.getDccode());           // 플랜트
                        params.setValue("I_MATNR" , snInfo.getSku());              // 상품
                        params.setValue("I_FRBNR" , snInfo.getConvserialno());     // B/L번호
                        params.setValue("I_HISTNO", snInfo.getSerialno());         // 유통이력번호
                        params.setValue("I_ZEBELN", snInfo.getPokey());            // PO번호
                        params.setValue("I_ZEBELP", snInfo.getPoline());           // POLINE번호
                        
                        log.info(" I_WERKS  : " + params.getValue("I_WERKS" ));
                        log.info(" I_MATNR  : " + params.getValue("I_MATNR" ));
                        log.info(" I_FRBNR  : " + params.getValue("I_FRBNR" ));
                        log.info(" I_HISTNO : " + params.getValue("I_HISTNO"));
                        log.info(" I_ZEBELN : " + params.getValue("I_ZEBELN"));
                        log.info(" I_ZEBELP : " + params.getValue("I_ZEBELP"));
                        
                        // 통신 시작
                        function.execute(destination);
                        returnData = function.getExportParameterList();
                        
                        if (!"S".equals((String) returnData.getValue("E_XSTAT"))) {
                            log.error("▶외부비축출고처리 저장 -> SAP 통신 오류 발생 " + (String) returnData.getValue("E_XMSGS"));
                            throw new UserHandleException((String) returnData.getValue("E_XMSGS"));
                        }                        
                        
                        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++ SAP 끝");
                    }
                }
            }
        }
        
        if (null != saveList) {
            for (WdShipmentExDCResDto dto : saveList) { 
                // PKG 파라마터 세팅 - 공통(1/4)
                ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);
                dto.setAvc_DCCODE(reqDto.getFixdccode());
                
                // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>        
                if ("WDCLOSE".equals(reqDto.getAvc_COMMAND())) {
                    //전표종결
                    keyList = new String[] {
                                    "DCCODE"
                                    ,"STORERKEY"
                                    ,"ORGANIZE"
                                    ,"SLIPDT"
                                    ,"SLIPNO"
                                    ,"REASONCODE"
                                    ,"REASONMSG"
                                    };
                    valueList = new Object[] {
                                      dto.getDccode()
                                    , dto.getGStorerkey() 
                                    , dto.getOrganize()
                                    , dto.getSlipdt()
                                    , dto.getSlipno()
                                    , reqDto.getInReasoncode()
                                    , reqDto.getInReasonmsg()
                                    };
                } else if ("BATCHCONFIRM".equals(reqDto.getAvc_COMMAND())) {
                    //저장
                    keyList = new String[] {
                            "DCCODE"
                            ,"STORERKEY"
                            ,"ORGANIZE"
                            ,"SLIPDT"
                            ,"SLIPNO"
                            };
                    valueList = new Object[] {
                              dto.getDccode()
                            , dto.getGStorerkey() 
                            , dto.getOrganize()
                            , dto.getSlipdt()
                            , dto.getSlipno()
                            };
                
                } else if ("CONFIRM".equals(reqDto.getAvc_COMMAND())) {
                    BigDecimal tranqty = dto.getTranqty();
                    BigDecimal shortagetranqty = dto.getShortagetranqty();
                    
                    //대상확정
                    if (tranqty.compareTo(BigDecimal.ZERO) != 0) {
                        //출고확정
                        keyList = new String[] {
                                "DCCODE"
                                ,"STORERKEY"
                                ,"ORGANIZE"
                                ,"DOCDT"
                                ,"DOCNO"
                                ,"DOCLINE"
                                ,"SLIPDT"
                                ,"SLIPNO"
                                ,"SLIPLINE" 
                                ,"SKU"
                                ,"UOM"
                                ,"BUNJA"
                                ,"BUNMO"
                                ,"TRANQTY"
                                ,"SHORTAGETRANQTY"
                                ,"LOTTABLE01"
                                ,"STOCKID"
                                ,"STOCKGRADE"
                                ,"REASONCODE"
                                ,"REASONMSG"
                                ,"REFERENCE02"
                                ,"TRANBOX"
                                ,"REFERENCE10"
                                ,"TOCUSTKEY"
                                };
                        valueList = new Object[] {
                                dto.getDccode()
                                , dto.getGStorerkey() 
                                , dto.getOrganize() 
                                , dto.getDocdt()
                                , dto.getDocno()
                                , dto.getDocline()
                                , dto.getSlipdt()
                                , dto.getSlipno()
                                , dto.getSlipline()
                                , dto.getSku()
                                , dto.getUom()
                                , dto.getBunja()
                                , dto.getBunmo()
                                , tranqty
                                , 0 
                                , dto.getLottable01()
                                , dto.getStockid()
                                , dto.getStockgrade()
                                , dto.getReasoncode()
                                , dto.getReasonmsg()
                                , dto.getReference02()
                                , dto.getTranbox()
                                , dto.getReference10()
                                , dto.getToCustkey()
                               };
                        
                        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, dto); 
                        
                        // 프로시저 OUT Parameter(3/4)
                        resultCode    = (String)dto.getResultCode();
                        resultMessage = (String)dto.getResultMessage();
                        
                        log.info("resultCode->{}",resultCode);
                        log.info("resultMessage->{}",resultMessage);
                        
                        // 프로시저 Exception 처리(4/4)
                        if (!resultCode.equals("0")) {
                            log.error("▶외부비축출고처리 저장 -> 저장 오류 발생 ");
                            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                        }  
                    } 
                    
                    if (("".equals(resultCode) || resultCode.equals("0")) && shortagetranqty.compareTo(BigDecimal.ZERO) != 0) {
                        resultCode = "";
                        resultMessage  = "";
                        
                        //결품확정
                        keyList = new String[] {
                                "DCCODE"
                                ,"STORERKEY"
                                ,"ORGANIZE"
                                ,"DOCDT"
                                ,"DOCNO"
                                ,"DOCLINE"
                                ,"SLIPDT"
                                ,"SLIPNO"
                                ,"SLIPLINE" 
                                ,"SKU"
                                ,"UOM"
                                ,"BUNJA"
                                ,"BUNMO"
                                ,"TRANQTY"
                                ,"SHORTAGETRANQTY"
                                ,"REASONCODE"
                                ,"REASONMSG"
                                ,"REFERENCE02"
                                ,"STOCKID"
                                };
                        valueList = new Object[] {
                                dto.getDccode()
                                , dto.getGStorerkey() 
                                , dto.getOrganize() 
                                , dto.getDocdt()
                                , dto.getDocno()
                                , dto.getDocline()
                                , dto.getSlipdt()
                                , dto.getSlipno()
                                , dto.getSlipline()
                                , dto.getSku()
                                , dto.getUom()
                                , dto.getBunja()
                                , dto.getBunmo()
                                , 0 
                                , shortagetranqty
                                , dto.getReasoncode()
                                , dto.getReasonmsg()
                                , dto.getReference02()
                                , dto.getStockid()
                                };
                        
                        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, dto); 
                        
                        // 프로시저 OUT Parameter(3/4)
                        resultCode    = (String)dto.getResultCode();
                        resultMessage = (String)dto.getResultMessage();
                        
                        log.info("resultCode->{}",resultCode);
                        log.info("resultMessage->{}",resultMessage);
                        
                        // 프로시저 Exception 처리(4/4)
                        if (!resultCode.equals("0")) {
                            log.error("▶외부비축출고처리 저장 -> 저장 오류 발생 ");
                            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                        }
                    }
                } 
            }
        }
        
        if (null != saveList) {
            for (WdShipmentExDCResDto dto : saveList) { 
                List<WdShipmentExDCResDto> stockCheckList = commonDao.selectList(SERVICEID_PREFIX + "getStockCntForExdc", dto);
                
                StringBuffer skuList = new StringBuffer();
                skuList.setLength(0);
                if (stockCheckList != null && stockCheckList.size() > 0) {
                    for (WdShipmentExDCResDto stockInfo : stockCheckList) { 
                        if (skuList.length() > 0) {
                            skuList.append("\n");
                        }
                        skuList.append("(");
                        skuList.append(stockInfo.getSku());
                        skuList.append("-");
                        skuList.append(("5".equals(stockInfo.getPoDivCd()) ? "직수입":"국내매입"));
                        skuList.append(")");
                    }
                    
                    throw new UserHandleException("박스의 수량이 '0'이지만 잔여중량이 있습니다. 감모 또는 매입반품 처리바랍니다. " + skuList.toString());
                }
            }
        }
 
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
	
	/**
     * @description : 외부비축출고처리 같은 운송비그룹 대상 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<WdShipmentExDCResDto> getPriceMasterList(WdShipmentExDCReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPriceMasterList", dto);
    }
	
	/**
     * @description : 외부비축출고처리 운송료 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<WdShipmentExDCPricePopupResDto> getPriceList(WdShipmentExDCReqDto dto) {
        Long serialkeyGroup = dto.getSerialkeyGroup();
        
        if (serialkeyGroup != null) {
            return commonDao.selectList(SERVICEID_PREFIX + "getExdcTrspCost", dto);
        } else {
            return commonDao.selectList(SERVICEID_PREFIX + "getCarrierPriceList", dto);
        }
    }
    
    /**
     * @description : 외부비축출고처리 운임단가 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<WdShipmentExDCPricePopupResDto> getCarrierPriceList(WdShipmentExDCReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getCarrierPriceList", dto);
    }
    
    /** 
     * @description : 외부비축출고처리 운송료 배부 저장
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String savePriceList(WdShipmentExDCReqDto paramDto) {
        WdShipmentExDCReqDto reqDto = ModelMapperUtil.map(paramDto, WdShipmentExDCReqDto.class);
        
        List<WdShipmentExDCResDto> saveList = reqDto.getSaveList();
        List<WdShipmentExDCPricePopupResDto> savePriceList = reqDto.getSavePriceList();
        
        if (null != saveList && null != savePriceList) {
            Long transactionSN = 0L;
            
            if (null != savePriceList.get(0).getSerialkeyGroup()) {
                transactionSN = savePriceList.get(0).getSerialkeyGroup();
            } else {
                // 일련번호 채번
                transactionSN = commonDao.selectOne(SERVICEID_PREFIX + "getCarrierPriceSN", reqDto);
            }
            
            // DM_DOCUMENT_D 에 운송비 저장
            for (WdShipmentExDCResDto dto : saveList) { 
                WdShipmentExDCEntity entity = ModelMapperUtil.map(dto, userContext, WdShipmentExDCEntity.class);
                entity.setTransactionSn(transactionSN);
                commonDao.selectOne(SERVICEID_PREFIX +"updateCarrierPrice", entity);
            }
            
            // 운송비 내역 저장 
            for (WdShipmentExDCPricePopupResDto dto : savePriceList) { 
                WdShipmentExDCPricePopupEntity entity = ModelMapperUtil.map(dto, userContext, WdShipmentExDCPricePopupEntity.class);
                entity.setTransactionSn(transactionSN);
                if (CanalFrameConstants.DELETE.equals(entity.getRowStatus()) ||  0 == entity.getCarcnt()) {
                    commonDao.selectOne(SERVICEID_PREFIX +"deleteExdcTrspCost", entity);
                } else {
                    commonDao.selectOne(SERVICEID_PREFIX +"saveExdcTrspCost", entity);
                }
            }
        }
            
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /** 
     * @description : 외부비축출고처리 운송료 저장
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveREFERENCE10(WdShipmentExDCReqDto paramDto) {
        WdShipmentExDCReqDto reqDto = ModelMapperUtil.map(paramDto, WdShipmentExDCReqDto.class);
        
        List<WdShipmentExDCResDto> saveList = reqDto.getSaveList();
        
        if (null != saveList) {
            // DM_DOCUMENT_D 에 운송비 저장
            for (WdShipmentExDCResDto dto : saveList) { 
                WdShipmentExDCEntity entity = ModelMapperUtil.map(dto, userContext, WdShipmentExDCEntity.class);
                commonDao.selectOne(SERVICEID_PREFIX +"updateREFERENCE10", entity);
            }
        }
            
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : SCM담당자 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<WdShipmentExDCResDto> getScmUserList(WdShipmentExDCReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getScmUserList", dto);
    }
    
    /**
     * @description : 보관료 계산 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<WdShipmentExDCStockpriceResDto> getStockPrice(WdShipmentExDCReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getStockPrice", dto);
    }
}
