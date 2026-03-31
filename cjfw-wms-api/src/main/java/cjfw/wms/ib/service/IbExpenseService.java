package cjfw.wms.ib.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.edms.EdmsFileDownloader;
import cjfw.core.edms.ZnDocumentApi;
import cjfw.core.exception.UserHandleException;
import cjfw.core.file.FileDownload;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderEdms;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.dto.CmElecReqDto;
import cjfw.wms.cm.dto.CmSearchCostCodePopupReqDto;
import cjfw.wms.cm.dto.CmSearchCostCodePopupResDto;
import cjfw.wms.cm.entity.CmSyProcessTempAjEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.ib.dto.IbApprovalListResDto;
import cjfw.wms.ib.dto.IbExpenseApprovalUserPopupResDto;
import cjfw.wms.ib.dto.IbExpenseDoucmentDetailPopupResDto;
import cjfw.wms.ib.dto.IbExpenseDoucmentHeaderPopupResDto;
import cjfw.wms.ib.dto.IbExpenseElecTaxPopupReqDto;
import cjfw.wms.ib.dto.IbExpenseElecTaxPopupResDto;
import cjfw.wms.ib.dto.IbExpenseFilePopupResDto;
import cjfw.wms.ib.dto.IbExpenseIfStatusPopupResDto;
import cjfw.wms.ib.dto.IbExpensePopupReqDto;
import cjfw.wms.ib.dto.IbExpenseReqDto;
import cjfw.wms.ib.dto.IbExpenseResDto;
import cjfw.wms.ib.dto.IfIbStocketcDDto;
import cjfw.wms.ib.dto.IfIbStocketcFileDto;
import cjfw.wms.ib.dto.IfIbStocketcHDto;
import cjfw.wms.ib.dto.IfIbStockskuBillDto;
import cjfw.wms.ib.dto.IfIbStockskuDDto;
import cjfw.wms.ib.dto.IfIbStockskuFileDto;
import cjfw.wms.ib.dto.IfIbStockskuHDto;
import cjfw.wms.ib.entity.IbExpenseApprovalUserEntity;
import cjfw.wms.ib.entity.IbExpenseDocumentDetailPopupEntity;
import cjfw.wms.ib.entity.IbExpenseDocumentHeaderPopupEntity;
import cjfw.wms.ib.entity.IbExpenseDocumentPopupEntity;
import cjfw.wms.ib.entity.IbExpenseEmailLogEntity;
import cjfw.wms.ib.entity.IbExpenseFilePopupEntity;
import cjfw.wms.ib.entity.IbExpenseIfIbStockEntity;
import cjfw.wms.ib.entity.IbExpenseResultEntity;
import cjfw.wms.st.dto.StExDCStorageResDto;
import cjfw.wms.webservice.elecTax.DT_SCM0500_SCM;
import cjfw.wms.webservice.elecTax.DT_SCM0500_SCMIB_EXPENSE;
import cjfw.wms.webservice.elecTax.DT_SCM0500_SCM_response;
import cjfw.wms.webservice.elecTax.DT_SCM0500_SCM_responseOUT_HEAD;
import cjfw.wms.webservice.elecTax.SI_SCM0500_SCM_SOProxy;
import cjfw.wms.webservice.exDCEtcFee.DT_SCM0480_SCM;
import cjfw.wms.webservice.exDCEtcFee.DT_SCM0480_SCMIF_IB_STOCKETC_D;
import cjfw.wms.webservice.exDCEtcFee.DT_SCM0480_SCMIF_IB_STOCKETC_FILE;
import cjfw.wms.webservice.exDCEtcFee.DT_SCM0480_SCMIF_IB_STOCKETC_H;
import cjfw.wms.webservice.exDCEtcFee.DT_SCM0480_SCM_responseIF_IB_STOCKETC_H_RET;
import cjfw.wms.webservice.exDCEtcFee.SI_SCM0480_SCM_SOProxy;
import cjfw.wms.webservice.exDCMonthlyFee.DT_SCM0470_SCM;
import cjfw.wms.webservice.exDCMonthlyFee.DT_SCM0470_SCMIF_IB_STOCKSKU_BILL;
import cjfw.wms.webservice.exDCMonthlyFee.DT_SCM0470_SCMIF_IB_STOCKSKU_D;
import cjfw.wms.webservice.exDCMonthlyFee.DT_SCM0470_SCMIF_IB_STOCKSKU_FILE;
import cjfw.wms.webservice.exDCMonthlyFee.DT_SCM0470_SCMIF_IB_STOCKSKU_H;
import cjfw.wms.webservice.exDCMonthlyFee.DT_SCM0470_SCM_responseT_RETURN;
import cjfw.wms.webservice.exDCMonthlyFee.SI_SCM0470_SCM_SOProxy;
import cjfw.wms.webservice.exDCSlipCancel.DT_SCM0510_SCMIF_SAPSLIPCANCEL;
import cjfw.wms.webservice.exDCSlipCancel.DT_SCM0510_SCM_responseRETURN;
import cjfw.wms.webservice.exDCSlipCancel.SI_SCM0510_SCM_SOProxy;
import cjfw.wms.webservice.sso.webservice.SSO_NON_SAP_EAIPortTypeProxy;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_REQUEST;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_RESPONSE;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * 
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.05 
 * @description : 비용기표 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IbExpenseService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "ibExpenseService.";		
	private transient static final String SERVICEID_EXPENSE_PREFIX = "ibExpenseService.";
	private transient static final String PAKAGE_NAME = "SPIB_EXPENSE";    
	private transient static final String PAKAGE_DOC_NAME = "SPST_EXDCSTORAGE";	
    private transient static final String COMMAND_APPR_NAME = "INSERT_IB_APPROVAL_MULTI";
    private transient static final String COMMAND_ELCL_NAME = "APPROVAL_ELEC_TRANSACT";
    private transient static final String COMMAND_ETCFEE_DF_NAME = "CHG_ALL_FEE_TAX_DIFF";
    private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";
    private transient static final String PROCESS_TYPE = "IBEXPENSE";
	private final CommonDao commonDao;
	private final CmCommonService cmCommonService;
	private final UserContext userContext;
	
    private final EdmsFileDownloader edmsFileDownloader;    
    private final FileUploaderEdms fileUploaderEdms;

	/**
	 * @description : 비용기표 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE          AUTHOR                  MAJOR_ISSUE
	 * ----------------------------------------------------------- 
	 * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<IbExpenseResDto> getMasterList(IbExpenseReqDto ibExpenseReqDto) {
	    return commonDao.selectList(SERVICEID_PREFIX + "getDataRead", ibExpenseReqDto);
	}
	
	
	/**
     * @throws RemoteException 
     * @description : 전자결재 승인 후 포스팅 처리
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String getApprPosting(String approvalRequestNo, String status)  {

        String resultCode = "";
        String resultMessage  = "";
        String errMsg = "";
            
        CmElecReqDto cmElecReqDto = new CmElecReqDto();
        cmElecReqDto.setApprovalReauestNo(approvalRequestNo);
        List<Map<String, Object>> ds_apprList = commonDao.selectList(SERVICEID_PREFIX + "selectElecApprDoctype", cmElecReqDto);
    
        for (Map<String, Object> ds_appr : ds_apprList) {
            if (!ObjectUtils.isEmpty(ds_appr) &&  ds_appr.get("DOCTYPE").equals("IBEXPENSE")) {
                String approvalstatus = (String) ds_appr.get("APPROVALSTATUS");
                String serialKey = (String) ds_appr.get("SLIPNO");
                String doctype = (String) ds_appr.get("DOCTYPE");
                String userId = (String) ds_appr.get("ADDWHO");
                
                //승인 처리
                if ("3".equals(status)) { 
                    if (serialKey.isEmpty()){
                        throw new UserHandleException("" + "SERIALKEY 값은 필수 입니다." );
                    }
                    
                    // 결재 상태 업데이트 
                    // PKG 파라마터 세팅 - 공통(1/4)
                    IbExpenseReqDto reqDto = new IbExpenseReqDto();
                    reqDto.setPackagename(PAKAGE_NAME);
                    reqDto.setAvc_COMMAND(COMMAND_ELCL_NAME);
                    reqDto.setAvc_SYSTEM("WMSAPP"); 
                    reqDto.setAvc_DCCODE("2170");
                    reqDto.setAvc_STORERKEY("FW00");
                    reqDto.setAvc_WORKER(userId); 
                    reqDto.setAvc_ORGANIZE("2170");
                    reqDto.setAvc_AREA("1000");
                    reqDto.setAi_SPID("1000000001");
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                    String[] keyList = { "SERIALKEY"
                                        , "DOCTYPE"
                                        , "APPROVALREQNO"
                                        , "SERIALKEY"
                                        };
                    Object[] valueList = { serialKey
                                        , doctype
                                        , approvalRequestNo
                                        , serialKey
                                        };
                    reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    int rv = cmCommonService.saveProcedure(reqDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)reqDto.getResultCode();
                    resultMessage = (String)reqDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 포스팅 처리   
                    IbApprovalListResDto dto = new IbApprovalListResDto();
                    dto.setSerialkey(serialKey);
                    
                    IbExpenseDoucmentHeaderPopupResDto apprStatusDto = commonDao.selectOne(SERVICEID_PREFIX + "getApprStatus", dto);
                    
                    if (apprStatusDto == null) {
                        throw new UserHandleException("" + "비용기표 결재 상태를 확인할 수 없습니다." );
                    } else if ("CCO".equals(apprStatusDto.getIfStatus())){
                        //errMsg = "인터페이스 취소건은 반드시 다음과 같은 순서로 진행해 주십시오. [CONFIRM CANCEL => CONFIRM => POSTING]";
                        return CanalFrameConstants.MSG_COM_SUC_CODE;
                    } else if ("ERR".equals(apprStatusDto.getIfStatus())){
                        //errMsg = "인터페이스 ERROR건은 반드시 다음과 같은 순서로 진행해 주십시오. [CONFIRM CANCEL => CONFIRM => POSTING]";
                        return CanalFrameConstants.MSG_COM_SUC_CODE;
                    } else if ("SCO".equals(apprStatusDto.getIfStatus())){
                        //errMsg = "Document No. : ["+ apprStatusDto.getKeyNo() +"] 번은 이미 정상적으로 처리된 건입니다.";
                        return CanalFrameConstants.MSG_COM_SUC_CODE;
                    } else if ("POE".equals(apprStatusDto.getStatus())){
                        //errMsg = "Posting 할 수 없는 상태입니다. 운영팀에 문의해주세요.";
                        return CanalFrameConstants.MSG_COM_SUC_CODE;
                    } else if (apprStatusDto.getFiIfStatus() != null && !"".equals(apprStatusDto.getFiIfStatus())){
                        //errMsg = "이미 Posting 요청된 상태입니다.";
                        return CanalFrameConstants.MSG_COM_SUC_CODE;
                    } else if (!"CFM".equals(apprStatusDto.getStatus())){
                        //errMsg = "Confirm 상태의 문서만 Posting 가능합니다.";
                        return CanalFrameConstants.MSG_COM_SUC_CODE;
                    } else if (null != apprStatusDto.getTplType() && "020".equals(apprStatusDto.getTplType())) {
                        //errMsg = "위탁물류 항목은 Posting 할 수 없습니다.";
                        return CanalFrameConstants.MSG_COM_SUC_CODE;
                    }
                    
                    commonDao.update(SERVICEID_PREFIX + "updateIfResultIfIbStockEtcHPosting", dto);
                    commonDao.update(SERVICEID_PREFIX + "updateIfResultIfIbStockSkuHPosting", dto);
                    
                    String ifID = apprStatusDto.getIfId();
                    
                    IfIbStocketcHDto ifDto = new IfIbStocketcHDto();
                    ifDto.setSerialkey(String.valueOf(dto.getSerialkey()));
                    ifDto.setIfType("POSTING");
    
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
    
                    if ("SCM0480".equals(ifID)) {
                        List<IfIbStocketcHDto> expenseHeaderList = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKETC_H", ifDto);
                        
                        if (expenseHeaderList == null || expenseHeaderList.size() == 0) {
                            //throw new UserHandleException("" + "인터페이스 정보를 확인할 수 없습니다." );
                            errMsg = "인터페이스 정보를 확인할 수 없습니다";
                        }
                        
                        ifDto.setZinvoice(expenseHeaderList.get(0).getZinvoice());
                        
                        List<IfIbStocketcDDto> expenseDetailList = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKETC_D", ifDto);
                        if (expenseDetailList == null || expenseDetailList.size() == 0) {
                            //throw new UserHandleException("" + "인터페이스 정보를 확인할 수 없습니다." );
                            errMsg = "인터페이스 정보를 확인할 수 없습니다.";
                        }
                        
                        List<IfIbStocketcFileDto> expenseFileList = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKETC_FILE", ifDto);
                        
                        SI_SCM0480_SCM_SOProxy proxy = new SI_SCM0480_SCM_SOProxy();
                        
                        DT_SCM0480_SCM fiData = new DT_SCM0480_SCM();
    
                        DT_SCM0480_SCMIF_IB_STOCKETC_H[] stocketc_h_list = new DT_SCM0480_SCMIF_IB_STOCKETC_H[expenseHeaderList.size()];
                        DT_SCM0480_SCMIF_IB_STOCKETC_D[] stocketc_d_list = new DT_SCM0480_SCMIF_IB_STOCKETC_D[expenseDetailList.size()];
                        DT_SCM0480_SCMIF_IB_STOCKETC_FILE[] stocketc_file_list = null;
    
                        for (int i = 0; i<expenseHeaderList.size();i++) {
                            DT_SCM0480_SCMIF_IB_STOCKETC_H stocketc_h = new DT_SCM0480_SCMIF_IB_STOCKETC_H();
            
                            stocketc_h.setZINVOICE (expenseHeaderList.get(i).getZinvoice());
                            stocketc_h.setZSEQ     (expenseHeaderList.get(i).getZseq());
                            stocketc_h.setXRECH    (expenseHeaderList.get(i).getXrech());
                            
                            stocketc_h.setZLAST    (expenseHeaderList.get(i).getZlast());
                            stocketc_h.setBUKRS    (expenseHeaderList.get(i).getBukrs());
                            stocketc_h.setLIFNR    (expenseHeaderList.get(i).getLifnr());
                            stocketc_h.setEMPFB    (expenseHeaderList.get(i).getEmpfB());
                            stocketc_h.setBLDAT    (expenseHeaderList.get(i).getBldat());
                            stocketc_h.setBUDAT    (expenseHeaderList.get(i).getBudat());
                            stocketc_h.setWAERS    (expenseHeaderList.get(i).getWaers());
                            stocketc_h.setMWSKZ1   (expenseHeaderList.get(i).getMwskz1());
                            stocketc_h.setZTERM    (expenseHeaderList.get(i).getZterm());
                            stocketc_h.setZFBDT    (expenseHeaderList.get(i).getZfbdt());
                            stocketc_h.setRMWWR    (expenseHeaderList.get(i).getRmwwr());
                            stocketc_h.setWMWST1   (expenseHeaderList.get(i).getWmwst1());
                            stocketc_h.setKIDNO    (expenseHeaderList.get(i).getKidno());
                            stocketc_h.setBKTXT    (expenseHeaderList.get(i).getBktxt());
                            stocketc_h.setZGUBUN   (expenseHeaderList.get(i).getZgubun());
                            stocketc_h.setZBILLNO  (expenseHeaderList.get(i).getZbillno());
                            stocketc_h.setCARD_NO  (expenseHeaderList.get(i).getCardNo());
                            stocketc_h.setAPPR_DATE(expenseHeaderList.get(i).getApprDate());
                            stocketc_h.setAPPR_DOCU(expenseHeaderList.get(i).getApprDocu());
                            stocketc_h.setCNCL_FLAG(expenseHeaderList.get(i).getCnclFlag());
                            stocketc_h.setSELL_DOCU(expenseHeaderList.get(i).getSellDocu());
                            stocketc_h.setZTEXT1   (expenseHeaderList.get(i).getZtext1());
                            stocketc_h.setZTEXT2   (expenseHeaderList.get(i).getZtext2());
                            stocketc_h.setZTEXT3   (expenseHeaderList.get(i).getZtext3());
                            stocketc_h.setZTEXT4   (expenseHeaderList.get(i).getZtext4());
                            stocketc_h.setZTEXT5   (expenseHeaderList.get(i).getZtext5());
                            
                            stocketc_h.setXDATS    (dateFormat.format(calendar.getTime()));
                            stocketc_h.setXTIMS    (timeFormat.format(calendar.getTime()));
                            stocketc_h.setXUSER    (userId);
                            
                            stocketc_h_list[i] = stocketc_h;
                        }
                        
                        for (int j = 0; j<expenseDetailList.size(); j++) {
                            DT_SCM0480_SCMIF_IB_STOCKETC_D stocketc_d = new DT_SCM0480_SCMIF_IB_STOCKETC_D();
                            
                            stocketc_d.setZINVOICE(expenseDetailList.get(j).getZinvoice());
                            stocketc_d.setBUZEI   (expenseDetailList.get(j).getBuzei());
                            stocketc_d.setZSEQ    (expenseDetailList.get(j).getZseq());
                            stocketc_d.setMWSKZ   (expenseDetailList.get(j).getMwskz());
                            stocketc_d.setWRBTR   (expenseDetailList.get(j).getWrbtr());
                            stocketc_d.setWERKS   (expenseDetailList.get(j).getWerks());
                            stocketc_d.setMATNR   (expenseDetailList.get(j).getMatnr());
                            stocketc_d.setFRBNR   (expenseDetailList.get(j).getFrbnr());
                            stocketc_d.setHISTNO  (expenseDetailList.get(j).getHistno());
                            stocketc_d.setZEBELN  (expenseDetailList.get(j).getZebeln());
                            stocketc_d.setZEBELP  (expenseDetailList.get(j).getZebelp());
                            stocketc_d.setMENGE   (expenseDetailList.get(j).getMenge());
                            stocketc_d.setBSTME   (expenseDetailList.get(j).getBstme());
                            stocketc_d.setZTEXT1  (expenseDetailList.get(j).getZtext1());
                            stocketc_d.setZTEXT2  (expenseDetailList.get(j).getZtext2());
                            stocketc_d.setZTEXT3  (expenseDetailList.get(j).getZtext3());
                            stocketc_d.setZTEXT4  (expenseDetailList.get(j).getZtext4());
                            stocketc_d.setZTEXT5  (expenseDetailList.get(j).getZtext5());
                            
                            stocketc_d_list[j] = stocketc_d;
                        }
                        
                        if (expenseFileList != null && expenseFileList.size() > 0)  {
                            stocketc_file_list = new DT_SCM0480_SCMIF_IB_STOCKETC_FILE[expenseFileList.size()];
                            for (int j = 0; j<expenseFileList.size();j++)  {
                                DT_SCM0480_SCMIF_IB_STOCKETC_FILE stocketc_file = new DT_SCM0480_SCMIF_IB_STOCKETC_FILE();
                                
                                stocketc_file.setBUKRS     (expenseFileList.get(j).getBukrs());
                                stocketc_file.setZSYST     (expenseFileList.get(j).getZsyst());
                                stocketc_file.setIF_NO     (expenseFileList.get(j).getIfNo());
                                stocketc_file.setSEQ       (expenseFileList.get(j).getSeq());
                                stocketc_file.setZDOCNO    (expenseFileList.get(j).getZdocno());
                                stocketc_file.setZEVID1    (expenseFileList.get(j).getZevid1());
                                stocketc_file.setZDOCTYPE  (expenseFileList.get(j).getZdoctype());
                                stocketc_file.setZATTID    (expenseFileList.get(j).getZattid());
                                stocketc_file.setZREQUESTNO(expenseFileList.get(j).getZrequestno());
                                stocketc_file.setZCATEGORY (expenseFileList.get(j).getZcategory());
                                
                                stocketc_file_list[j] = stocketc_file;
                            }
                        } else {
                            stocketc_file_list = new DT_SCM0480_SCMIF_IB_STOCKETC_FILE[0];
                        }
                        
                        fiData.setIF_IB_STOCKETC_H(stocketc_h_list);
                        fiData.setIF_IB_STOCKETC_D(stocketc_d_list);
                        fiData.setIF_IB_STOCKETC_FILE(stocketc_file_list);
                        
                        DT_SCM0480_SCM_responseIF_IB_STOCKETC_H_RET[] responses = null;
                        try {
                            responses = proxy.sI_SCM0480_SCM_SO(fiData);
                        } catch (RemoteException e) {
                            responses = null;
                        }
                        
                        // 응답에 대한 결과 처리
                        IbExpenseResultEntity entity = ModelMapperUtil.map(dto, userContext, IbExpenseResultEntity.class);
                        
                        if (responses != null && responses.length > 0) {
                            int iFailCount = 0;
                            for (int i = 0; i< responses.length; i++) {
                                DT_SCM0480_SCM_responseIF_IB_STOCKETC_H_RET response = responses[i];
    
                                entity.setIfType("POSTING");
                                entity.setZinvoice(response.getZINVOICE());
                                entity.setZseq(expenseHeaderList.get(i).getZseq());
                                entity.setXstat(response.getXSTAT());
                                entity.setXmsgs(response.getXMSGS());
                                entity.setZreturn(response.getZRETURN());
                                entity.setZreBelnr(response.getZRE_BELNR());
                                entity.setGUserId(userId);
    
                                if (!"S".equals(response.getXSTAT())) {
                                    entity.setXstat("E");
                                    iFailCount++;
                                } else if(response.getZRE_BELNR() == null || "".equals(response.getZRE_BELNR())){
                                    entity.setXstat("E");
                                    iFailCount++;
                                    
                                    errMsg = "ERROR : FI 전표 미생성"; 
                                    log.error("▶비용결재 -> Posting 저장 오류 발생 / " + errMsg);
                                }
                                
                                commonDao.update(SERVICEID_EXPENSE_PREFIX + "updateIfResultIfIbStockEtcH", entity);
                            }
                            
                            if (responses.length == expenseHeaderList.size() && iFailCount == 0) {
                                entity.setXstat("S");
                            } else if(responses.length == iFailCount) {
                                entity.setXstat("E");
                            } else {
                                entity.setXstat("POE");
                            }
                            
                            commonDao.update(SERVICEID_EXPENSE_PREFIX + "updateIfResultIbExpense", entity);
                        }
                        
                    } else if("SCM0470".equals(ifID))  {
                        //List<IfIbStockskuHDto> expenseHeaderList = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_H", ifDto);
                        List<IfIbStocketcHDto> expenseHeaderList = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_H", ifDto);
                        
                        if (expenseHeaderList == null || expenseHeaderList.size() == 0) {
                            // 예외로 던지는 것이 맞는 경우인지 확인필요하다 
                            errMsg =  "데이터를 찾을 수 없습니다.";            
                            log.error("▶비용결재 -> Posting 저장 오류 발생 / " + errMsg);
                            //throw new UserHandleException(""+ errMsg);
                        }
    
                        IfIbStockskuHDto selDto = new IfIbStockskuHDto();
                        selDto.setZinvoice(expenseHeaderList.get(0).getZinvoice());            
                        List<IfIbStockskuDDto> expenseDetailLst = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_D", selDto); 
                        List<IfIbStockskuBillDto> expenseBillLst = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_BILL", selDto); 
                        List<IfIbStockskuFileDto> expenseFileLst = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_FILE", selDto); 
            
                        if (expenseDetailLst == null || expenseDetailLst.size()== 0) {
                            // 예외로 던지는 것이 맞는 경우인지 확인필요하다
                            errMsg =  "데이터를 찾을 수 없습니다.";            
                            log.error("▶비용결재 -> Posting 저장 오류 발생 / " + errMsg);
                            //throw new UserHandleException(""+ errMsg);
                        }
             
                        DT_SCM0470_SCMIF_IB_STOCKSKU_H[] stocksku_h_list = new DT_SCM0470_SCMIF_IB_STOCKSKU_H[expenseHeaderList.size()];
                        DT_SCM0470_SCMIF_IB_STOCKSKU_D[] stocksku_d_list = new DT_SCM0470_SCMIF_IB_STOCKSKU_D[expenseDetailLst.size()];
                        DT_SCM0470_SCMIF_IB_STOCKSKU_BILL[] stocksku_bill_list = null;
                        DT_SCM0470_SCMIF_IB_STOCKSKU_FILE[] stocksku_file_list = new DT_SCM0470_SCMIF_IB_STOCKSKU_FILE[expenseFileLst.size()];
                        
                        SI_SCM0470_SCM_SOProxy proxy = new SI_SCM0470_SCM_SOProxy();
                        
                        DT_SCM0470_SCM fiData = new DT_SCM0470_SCM();
                        
                        for (int i = 0; i<expenseHeaderList.size(); i++) {
                            DT_SCM0470_SCMIF_IB_STOCKSKU_H stocksku_h = new DT_SCM0470_SCMIF_IB_STOCKSKU_H();
                            
                            stocksku_h.setZINVOICE (expenseHeaderList.get(i).getZinvoice());
                            stocksku_h.setZCAT     (expenseHeaderList.get(i).getZcat());
                            stocksku_h.setZSEQ     (expenseHeaderList.get(i).getZseq());
                            stocksku_h.setZLAST    (expenseHeaderList.get(i).getZlast());
                            stocksku_h.setXRECH    (expenseHeaderList.get(i).getXrech());
                            stocksku_h.setBUKRS    (expenseHeaderList.get(i).getBukrs());
                            stocksku_h.setLIFNR    (expenseHeaderList.get(i).getLifnr());
                            stocksku_h.setEMPFB    (expenseHeaderList.get(i).getEmpfB());
                            stocksku_h.setBLDAT    (expenseHeaderList.get(i).getBldat());
                            stocksku_h.setBUDAT    (expenseHeaderList.get(i).getBudat());
                            stocksku_h.setWAERS    (expenseHeaderList.get(i).getWaers());
                            stocksku_h.setMWSKZ1   (expenseHeaderList.get(i).getMwskz1());
                            stocksku_h.setZTERM    (expenseHeaderList.get(i).getZterm());
                            stocksku_h.setZFBDT    (expenseHeaderList.get(i).getZfbdt());
                            stocksku_h.setRMWWR    (expenseHeaderList.get(i).getRmwwr());
                            stocksku_h.setWMWST1   (expenseHeaderList.get(i).getWmwst1());
                            stocksku_h.setKIDNO    (expenseHeaderList.get(i).getKidno());
                            stocksku_h.setBKTXT    (expenseHeaderList.get(i).getBktxt());
                            stocksku_h.setZGUBUN   (expenseHeaderList.get(i).getZgubun());
                            stocksku_h.setZBILLNO  (expenseHeaderList.get(i).getZbillno());
                            stocksku_h.setCARD_NO  (expenseHeaderList.get(i).getCardNo());
                            stocksku_h.setAPPR_DATE(expenseHeaderList.get(i).getApprDate());
                            stocksku_h.setAPPR_DOCU(expenseHeaderList.get(i).getApprDocu());
                            stocksku_h.setCNCL_FLAG(expenseHeaderList.get(i).getCnclFlag());
                            stocksku_h.setSELL_DOCU(expenseHeaderList.get(i).getSellDocu());
                            stocksku_h.setZTEXT1   (expenseHeaderList.get(i).getZtext1());
                            stocksku_h.setZTEXT2   (expenseHeaderList.get(i).getZtext2());
                            stocksku_h.setZTEXT3   (expenseHeaderList.get(i).getZtext3());
                            stocksku_h.setZTEXT4   (expenseHeaderList.get(i).getZtext4());
                            stocksku_h.setZTEXT5   (expenseHeaderList.get(i).getZtext5());
                            
                            stocksku_h.setXDATS    (dateFormat.format(calendar.getTime()));
                            stocksku_h.setXTIMS    (timeFormat.format(calendar.getTime()));
                            stocksku_h.setXUSER    (userId);
                            
                            stocksku_h_list[i] = stocksku_h;
                        }    
                        
                        for (int j = 0; j<expenseDetailLst.size(); j++) {
                            DT_SCM0470_SCMIF_IB_STOCKSKU_D stocksku_d = new DT_SCM0470_SCMIF_IB_STOCKSKU_D();
                            
                            stocksku_d.setZINVOICE  (expenseDetailLst.get(j).getZinvoice()); 
                            stocksku_d.setZCAT      (expenseDetailLst.get(j).getZcat());
                            stocksku_d.setZSEQ      (expenseDetailLst.get(j).getZseq());
                            stocksku_d.setBUZEI     (expenseDetailLst.get(j).getBuzei());
                            stocksku_d.setGL_ACCOUNT(expenseDetailLst.get(j).getGlAccount());
                            stocksku_d.setMWSKZ     (expenseDetailLst.get(j).getMwskz());
                            stocksku_d.setPROFIT_CTR(expenseDetailLst.get(j).getProfitCtr());
                            stocksku_d.setSHKZG     (expenseDetailLst.get(j).getShkzg());
                            stocksku_d.setWRBTR     (expenseDetailLst.get(j).getWrbtr());
            
                            stocksku_d.setMATNR     (expenseDetailLst.get(j).getMatnr());
                            stocksku_d.setMENGE     (expenseDetailLst.get(j).getMenge());
                            stocksku_d.setBSTME     (expenseDetailLst.get(j).getBstme());
                            stocksku_d.setZEBELN    (expenseDetailLst.get(j).getZebeln());
                            stocksku_d.setZEBELP    (expenseDetailLst.get(j).getZebelp());
                            if(expenseBillLst != null && expenseBillLst.size() > 0 && j == 0) {
                                stocksku_d.setZTEXT1    (expenseBillLst.get(0).getMatnr());
                            }else{ 
                                stocksku_d.setZTEXT1    (expenseDetailLst.get(j).getZtext1());
                            }
            
                            stocksku_d.setZTEXT2    (expenseDetailLst.get(j).getZtext2());
                            stocksku_d.setZTEXT3    (expenseDetailLst.get(j).getZtext3());
                            stocksku_d.setZTEXT4    (expenseDetailLst.get(j).getZtext4());
                            stocksku_d.setZTEXT5    (expenseDetailLst.get(j).getZtext5());
                            
                            stocksku_d_list[j] = stocksku_d;
                        }
                        
                        if (expenseDetailLst != null && expenseDetailLst.size() > 0) {
                            for (int j = 0; j<expenseFileLst.size(); j++) {
                                DT_SCM0470_SCMIF_IB_STOCKSKU_FILE stocksku_file = new DT_SCM0470_SCMIF_IB_STOCKSKU_FILE();
                                
                                stocksku_file.setBUKRS     (expenseFileLst.get(j).getBukrs());
                                stocksku_file.setZSYST     (expenseFileLst.get(j).getZsyst());
                                stocksku_file.setIF_NO     (expenseFileLst.get(j).getIfNo());
                                stocksku_file.setSEQ       (expenseFileLst.get(j).getSeq());
                                stocksku_file.setZDOCNO    (expenseFileLst.get(j).getZdocno());
                                stocksku_file.setZEVID1    (expenseFileLst.get(j).getZevid1());
                                stocksku_file.setZDOCTYPE  (expenseFileLst.get(j).getZdoctype());
                                stocksku_file.setZATTID    (expenseFileLst.get(j).getZattid());
                                stocksku_file.setZREQUESTNO(expenseFileLst.get(j).getZrequestno());
                                stocksku_file.setZCATEGORY (expenseFileLst.get(j).getZcategory());
                                
                                stocksku_file_list[j] = stocksku_file;
                            }
                        } else {
                            stocksku_file_list = new DT_SCM0470_SCMIF_IB_STOCKSKU_FILE[0];
                        }
                        
                        fiData.setIF_IB_STOCKSKU_H(stocksku_h_list);
                        fiData.setIF_IB_STOCKSKU_D(stocksku_d_list);
                        fiData.setIF_IB_STOCKSKU_BILL(stocksku_bill_list);
                        fiData.setIF_IB_STOCKSKU_FILE(stocksku_file_list);
    
                        DT_SCM0470_SCM_responseT_RETURN[] responses = null;
                        try {
                            responses = proxy.sI_SCM0470_SCM_SO(fiData);
                        } catch (RemoteException e) {
                            responses = null;
                        }
                        
                        // 응답에 대한 결과 처리
                        IbExpenseResultEntity entity = ModelMapperUtil.map(dto, userContext, IbExpenseResultEntity.class);
                        
                        if (responses != null && responses.length > 0) {
                            int iFailCount = 0;
                            for (int i = 0; i< responses.length; i++) {
                                DT_SCM0470_SCM_responseT_RETURN response = responses[i];
    
                                entity.setIfType("POSTING");
                                entity.setZinvoice(response.getZINVOICE());
                                entity.setZseq(expenseHeaderList.get(i).getZseq());   
                                entity.setXstat(response.getXSTAT());
                                entity.setXmsgs(response.getXMSGS());
                                entity.setZreturn(response.getZRETURN());
                                entity.setZreBelnr(response.getZRE_BELNR());
                                entity.setGUserId(userId);
    
                                if (!"S".equals(response.getXSTAT())) {
                                    entity.setXstat("E");
                                    iFailCount++;
                                } else if (response.getZRE_BELNR() == null || "".equals(response.getZRE_BELNR())) {
                                    entity.setXstat("E");
                                    entity.setXmsgs("ERROR : FI 전표 미생성");
                                    iFailCount++;
                                }
                                
                                commonDao.update(SERVICEID_PREFIX + "updateIfResultIfIbStockSkuH", entity);
                            }
                            
                            if(responses.length == expenseHeaderList.size() && iFailCount == 0) {
                                entity.setXstat("S");
                            } else if (responses.length == iFailCount) {
                                entity.setXstat("E");
                            } else {
                                entity.setXstat("POE");
                            }
                            
                            entity.setSerialkey(serialKey);
                            
                            commonDao.update(SERVICEID_PREFIX + "updateIfResultIbExpense", entity);
                        }   
                    }
                }   //END.. 승인 처리  
                
                //반려, 취소 처리
                if ("0".equals(status)) {
                    // 결재 상태 업데이트 
                    // PKG 파라마터 세팅 - 공통(1/4)
                    IbExpenseReqDto reqDto = new IbExpenseReqDto();
                    reqDto.setPackagename(PAKAGE_NAME);
                    reqDto.setAvc_COMMAND(COMMAND_ELCL_NAME);
                    reqDto.setAvc_SYSTEM("WMSAPP"); 
                    reqDto.setAvc_DCCODE("2170");
                    reqDto.setAvc_STORERKEY("FW00");
                    reqDto.setAvc_WORKER(userId); 
                    reqDto.setAvc_ORGANIZE("2170");
                    reqDto.setAvc_AREA("1000");
                    reqDto.setAi_SPID("1000000001");

                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                    String[] keyList = { "SERIALKEY"
                                        , "DOCTYPE"
                                        , "APPROVALREQNO"
                                        };
                    Object[] valueList = { serialKey
                                        , doctype
                                        , approvalRequestNo
                                        };
                    reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    int rv = cmCommonService.saveProcedure(reqDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)reqDto.getResultCode();
                    resultMessage = (String)reqDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
    	            
    	        }  //END.. 반려 처리
    	        
    	    }//END..if (!ObjectUtils.isEmpty(ds_appr) && ds_appr.size() > 0 && ds_appr.get(0).get("DOCTYPE").equals("IBEXPENSE")) {
    	}//END..for
	
	    return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
	
	/**
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public IbExpenseDoucmentHeaderPopupResDto getPopupDocumentInfoHeader(IbExpensePopupReqDto ibExpensePopupReqDto) {
        return commonDao.selectOne(SERVICEID_PREFIX + "getPopupDocumentInfoHeader", ibExpensePopupReqDto);
    }
    
    /**
     * @description : 비용기표 문서정보 팝업 상세 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<IbExpenseDoucmentDetailPopupResDto> getPopupDocumentInfoDetail(IbExpensePopupReqDto ibExpensePopupReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPopupDocumentInfoDetail", ibExpensePopupReqDto);
    }
    
    /**
     * @description : 코스트코드 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<CmSearchCostCodePopupResDto> getCostCodeList(CmSearchCostCodePopupReqDto cmSearchCostCodePopupReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getCostCodeList", cmSearchCostCodePopupReqDto);
    }    
    
    /**
     * @description : 인터페이스 결과 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<IbExpenseIfStatusPopupResDto> getIFStatusList(IbExpensePopupReqDto ibExpensePopupReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPopupIFStatus", ibExpensePopupReqDto);
    }
    
    /**
     * @throws RemoteException 
     * @description : 매입세금계산서 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<IbExpenseElecTaxPopupResDto> getElecTaxList(IbExpenseElecTaxPopupReqDto paramDto) throws RemoteException {     
        IbExpenseElecTaxPopupReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpenseElecTaxPopupReqDto.class);
        
        List<IbExpenseElecTaxPopupResDto> result = new ArrayList<IbExpenseElecTaxPopupResDto>();
        
        String issueDateFrom = reqDto.getIssuedateFrom();
        String issueDateTo = reqDto.getIssuedateTo();
        
        // 날짜 포멧팅
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
        
        // 검색시작일
        if (StringUtils.isEmpty(issueDateFrom)) {
            Calendar fromCalendar = Calendar.getInstance();
            fromCalendar.add(Calendar.DAY_OF_MONTH, -10);
            issueDateFrom = dateFormat.format(fromCalendar.getTime());
        }
        
        // 검색종료일
        if (StringUtils.isEmpty(issueDateTo)) {
            Calendar toCalendar = Calendar.getInstance();
            toCalendar.add(Calendar.DAY_OF_MONTH, 10);
            issueDateTo = dateFormat.format(toCalendar.getTime());
        }
        
        // 전자세금계산서 조회 I/F
        SI_SCM0500_SCM_SOProxy proxy = new SI_SCM0500_SCM_SOProxy();        
        DT_SCM0500_SCM fiData = null;
        
        fiData = new DT_SCM0500_SCM();
        fiData.setXSYS("WMS");
        fiData.setXDATS(dateFormat.format(calendar.getTime()));
        fiData.setXTIMS(timeFormat.format(calendar.getTime()));
        fiData.setXROWS("1");
        
        DT_SCM0500_SCMIB_EXPENSE[] expenseList = new DT_SCM0500_SCMIB_EXPENSE[1];
        DT_SCM0500_SCMIB_EXPENSE expense = new DT_SCM0500_SCMIB_EXPENSE();
        
        expense.setSTORERKEY(reqDto.getGStorerkey());                                               // 회사코드
        expense.setBUPLA("0000");                                                                   // 세무사업장( 0000 : 고정값)
        expense.setISSUEDATE_FROM(issueDateFrom);                                                   // 전자세금계산서 작성일자
        expense.setISSUEDATE_TO(issueDateTo);                                                       // 전자세금계산서 작성일자
        expense.setCUSTTYPE("K");                                                                   // 고객(D) , 구매처(K) 구분
        expense.setVATNO(reqDto.getCbRegisno());                                                    // 사업자등록번호
        expense.setCUSTNAME(reqDto.getAdjustmentSupplierName());                                    // 사업체명
        expense.setEMAIL1("");                                                                      // 담당자EMAIL 1
        expense.setDOC_FLAG(StringUtils.isEmpty(reqDto.getDocFlag()) ? "" : reqDto.getDocFlag());   // 전표매핑유무(* : 전체, X : 매핑된 전자세금계산서, 공백 미맵핑된 전자세금계산서)
        expenseList[0] = expense;
        fiData.setIB_EXPENSE(expenseList);
        
        DT_SCM0500_SCM_response response = null;
        response = proxy.sI_SCM0500_SCM_SO(fiData);
        
        // 응답에 대한 결과 처리
        if (response != null) {
             if ("S".equals(response.getXSTAT())) {
             
                 DT_SCM0500_SCM_responseOUT_HEAD[] headList = response.getOUT_HEAD();
                 
                 int size = headList == null ? 0 : headList.length;
               
                 for (int i = 0; i < size; i++) {
                     IbExpenseElecTaxPopupResDto elecTaxDot = new IbExpenseElecTaxPopupResDto();
                     elecTaxDot.setBukrs(headList[i].getBUKRS());                                  // 회사코드
                     elecTaxDot.setIssueId(headList[i].getISSUE_ID());                             // 세금계산서 승인번호
                     elecTaxDot.setInvSeq(headList[i].getINV_SEQ());                               // 세금계산서 일련번호(년월일+회사코드+12) 내부사용
                     elecTaxDot.setChargetotal(headList[i].getCHARGETOTAL());                      // 총 공급가
                     elecTaxDot.setTaxtotal(headList[i].getTAXTOTAL());                            // 총 세액
                     elecTaxDot.setGrandtotal(headList[i].getGRANDTOTAL());                        // 총액(공급가액+세액)
                     elecTaxDot.setBpAddr(headList[i].getBP_ADDR());                               // 수탁사업자 주소
                     elecTaxDot.setBpRepres(headList[i].getBP_REPRES());                           // 수탁사업자 대표자명 
                     elecTaxDot.setBpDeptname(headList[i].getBP_DEPTNAME());                       // 수탁사업자 담당부서
                     elecTaxDot.setBpPersname(headList[i].getBP_PERSNAME());                       // 수탁사업자 담당자명                     
                     elecTaxDot.setEmail(headList[i].getBP_EMAIL());                               // 수탁사업자 이메일주소  
                     
                     if (StringUtil.isEmpty(headList[i].getISSUE_DT())) {
                         elecTaxDot.setIssueDt("");                                                 // 전자세금계산서 발행일시
                     } else {    
                         if ("0000".equals(headList[i].getISSUE_DT().substring(0, 4))) {
                             elecTaxDot.setIssueDt("");
                         } else {
                             elecTaxDot.setIssueDt((headList[i].getISSUE_DT()).substring(0, 8)); 
                         }
                     }
                     elecTaxDot.setIssueDate(headList[i].getISSUE_DATE());                         // 전자세금계산서 작성일자
                     elecTaxDot.setAedat(headList[i].getAEDAT());                                  // 변경일
                     elecTaxDot.setInvSign(headList[i].getINV_SIGN());                             // 역발행여부 
                   
                     result.add(elecTaxDot);
                 } 
             } else {
                 throw new UserHandleException(response.getXMSGS());
             }
        } else {
             throw new UserHandleException("데이터를 가져오는데 실패했습니다.");
        }
              
        return result;
    }
        
    /**
     * @throws RemoteException 
     * @description : Document Info 헤더 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String savePopupDocumentInfoHeader(IbExpensePopupReqDto paramDto) throws RemoteException {     
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        IbExpensePopupReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpensePopupReqDto.class);
        
        IbExpenseDoucmentHeaderPopupResDto documentHeaderInfo = reqDto.getSaveDocumentHeaderInfo(); 
        List<IbExpenseDoucmentDetailPopupResDto> saveList = reqDto.getSaveList();
        
        if (null != documentHeaderInfo) {
            IbExpenseDocumentHeaderPopupEntity entity = ModelMapperUtil.map(documentHeaderInfo, userContext, IbExpenseDocumentHeaderPopupEntity.class);
            commonDao.update(SERVICEID_PREFIX + "savePopupDocumentInfoHeader", entity);
            
            if (saveList.size() > 0) {
                for (IbExpenseDoucmentDetailPopupResDto dto : saveList) {
                    IbExpenseDocumentDetailPopupEntity detailEntity = ModelMapperUtil.map(dto, userContext, IbExpenseDocumentDetailPopupEntity.class);
                    commonDao.update(SERVICEID_PREFIX + "savePopupDocumentInfoDetail", detailEntity);                
                }
            }
            
            // PKG 파라마터 세팅 - 공통(1/4)
            IbExpensePopupReqDto procedureDto = new IbExpensePopupReqDto();
            ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_DOC_NAME);
            
            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
            String[] keyList = {"SERIALKEY_EX", "COMMIT"};
            Object[] valueList = {reqDto.getSerialkey(), "FALSE"};
           
            procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            
            int rv = cmCommonService.saveProcedure(procedureDto); 
            log.info("rv->{}",rv);
            
            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)procedureDto.getResultCode();
            resultMessage = (String)procedureDto.getResultMessage();
            
            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);
            
            // 프로시저 Exception 처리(4/4)
            if(!"0".equals(resultCode)){
                log.error("▶비용기표 -> DocumentInfo Header 저장 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }  
        }
                
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @throws RemoteException 
     * @description : Document Info 디테일 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String savePopupDocumentInfoDetail(IbExpensePopupReqDto paramDto) throws RemoteException {     
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        IbExpensePopupReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpensePopupReqDto.class);
        List<IbExpenseDoucmentDetailPopupResDto> saveList = reqDto.getSaveList();
        
        if (null != saveList) {
            if (saveList.size() > 0) {
                for (IbExpenseDoucmentDetailPopupResDto dto : saveList) {
                    IbExpenseDocumentDetailPopupEntity detailEntity = ModelMapperUtil.map(dto, userContext, IbExpenseDocumentDetailPopupEntity.class);
                    commonDao.update(SERVICEID_PREFIX + "savePopupDocumentInfoDetail", detailEntity);                
                }
            }
            
            int cnt = 0;
            
            for (IbExpenseDoucmentDetailPopupResDto dto : saveList) {
                cnt++;
                
                String[] keyList = null;
                Object[] valueList = null;
                
                // PKG 파라마터 세팅 - 공통(1/4)
                IbExpensePopupReqDto procedureDto = new IbExpensePopupReqDto();
                ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_DOC_NAME);
                
                // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
                if (cnt == saveList.size()) {
                    keyList = new String[] {"SERIALKEY_EX", "SERIALKEY_ED"};
                    valueList = new Object[]  {dto.getExpenseSn(), dto.getSerialkey()};
                } else {
                    keyList = new String[] {"SERIALKEY_EX", "SERIALKEY_ED", "COMMIT"};
                    valueList = new Object[]  {dto.getExpenseSn(), dto.getSerialkey(), "FALSE"};
                }                
               
                procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                
                int rv = cmCommonService.saveProcedure(procedureDto); 
                log.info("rv->{}",rv);
                
                // 프로시저 OUT Parameter(3/4)
                resultCode    = (String)procedureDto.getResultCode();
                resultMessage = (String)procedureDto.getResultMessage();
                
                log.info("resultCode->{}",resultCode);
                log.info("resultMessage->{}",resultMessage);
                
                // 프로시저 Exception 처리(4/4)
                if(!"0".equals(resultCode)){
                    log.error("▶비용기표 -> DocumentInfo Detail 저장 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                }  
            }
        }
                
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : Document Info KG 비용분배 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveKGPopupDocumentInfoDetail(IbExpensePopupReqDto paramDto) {     
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        IbExpensePopupReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpensePopupReqDto.class);
        List<IbExpenseDoucmentDetailPopupResDto> saveList = reqDto.getSaveList();
        IbExpenseDocumentPopupEntity headerEntity = ModelMapperUtil.map(reqDto, userContext, IbExpenseDocumentPopupEntity.class);
        
        if (null != saveList) {
            if (saveList.size() > 0) {
                for (IbExpenseDoucmentDetailPopupResDto dto : saveList) {
                    IbExpenseDocumentDetailPopupEntity detailEntity = ModelMapperUtil.map(dto, userContext, IbExpenseDocumentDetailPopupEntity.class);
                    commonDao.update(SERVICEID_PREFIX + "saveKGPopupDocumentInfoDetail", detailEntity);  
                }
            }
            
            StExDCStorageResDto diffList = commonDao.selectOne(SERVICEID_PREFIX + "getTaxDiff", reqDto);
            
            if (diffList != null && !ObjectUtils.isEmpty(diffList)) {
                int expenseSN = diffList.getExpenseSn();
                double taxDiff = diffList.getTaxDiff();
                double supplyPriceDiff = diffList.getSupplyPriceDiff();
                
                if (taxDiff != 0 || supplyPriceDiff != 0) {
                    headerEntity.setTaxDiff(new BigDecimal(taxDiff));
                    headerEntity.setSupplyPriceDiff(new BigDecimal(supplyPriceDiff));
                    commonDao.update(SERVICEID_PREFIX + "saveKGPopupDocumentInfoDetailAdjust", headerEntity); 
                }
            }
            
            commonDao.update(SERVICEID_PREFIX + "saveKGPopupDocumentInfoHeader", headerEntity);
        }
                
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
        
    /**
     * @description : 비용기표 확정
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveConfirm(IbExpenseReqDto paramDto) {     
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String errMsg = "";
        
        IbExpenseReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpenseReqDto.class);
        List<IbExpenseResDto> saveList = reqDto.getSaveList();
        
        if (null != saveList) {
            if (saveList.size() > 0) {
                // 마감 상태 체크 
                for (IbExpenseResDto saveDto : saveList) {
                    String closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getExdcClose", saveDto);
                    if ("Y".equals(closeyn)) {
                        errMsg =  "정산마감이 되어 확정 불가합니다";
                        log.error("▶비용기표 -> 확정 오류 발생 / " + errMsg);
                        throw new UserHandleException(""+ errMsg);
                    }
                    
                    closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getIbClose", saveDto);
                    if ("Y".equals(closeyn)) {
                        errMsg =  "정산마감이 되어 확정 불가합니다."; 
                        log.error("▶비용기표 -> 확정 오류 발생 / " + errMsg);
                        throw new UserHandleException(""+ errMsg);
                    }
                }
                
                for (IbExpenseResDto dto : saveList) {
                    // PKG 파라마터 세팅 - 공통(1/4)
                    IbExpensePopupReqDto procedureDto = new IbExpensePopupReqDto();
                    ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
                    procedureDto.setAvc_DCCODE(reqDto.getAvc_DCCODE());
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
                    String[] keyList = new String[] {"SERIALKEY"};
                    Object[] valueList = new Object[]  {dto.getSerialkey()};
                   
                    procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    int rv = cmCommonService.saveProcedure(procedureDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)procedureDto.getResultCode();
                    resultMessage = (String)procedureDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if(!"0".equals(resultCode)){
                        log.error("▶비용기표 -> 비용기표 확정 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }                 
                }
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 비용기표 확정 취소
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveConfirmCancel(IbExpenseReqDto paramDto) {     
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        IbExpenseReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpenseReqDto.class);
        List<IbExpenseResDto> saveList = reqDto.getSaveList();
        
        if (null != saveList) {
            if (saveList.size() > 0) {
                for (IbExpenseResDto dto : saveList) {
                    // PKG 파라마터 세팅 - 공통(1/4)
                    IbExpensePopupReqDto procedureDto = new IbExpensePopupReqDto();
                    ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
                    procedureDto.setAvc_DCCODE(reqDto.getAvc_DCCODE());
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
                    String[] keyList = new String[] {"SERIALKEY"};
                    Object[] valueList = new Object[]  {dto.getSerialkey()};
                   
                    procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    int rv = cmCommonService.saveProcedure(procedureDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)procedureDto.getResultCode();
                    resultMessage = (String)procedureDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if(!"0".equals(resultCode)){
                        log.error("▶비용기표 -> 비용기표 확정 취소 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }                 
                }
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 비용기표 삭제
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String deleteMasterList(IbExpenseReqDto paramDto) {     
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        IbExpenseReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpenseReqDto.class);
        List<IbExpenseResDto> saveList = reqDto.getSaveList();
        
        if (null != saveList) {
            if (saveList.size() > 0) {
                for (IbExpenseResDto dto : saveList) {
                    // PKG 파라마터 세팅 - 공통(1/4)
                    IbExpensePopupReqDto procedureDto = new IbExpensePopupReqDto();
                    ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
                    procedureDto.setAvc_DCCODE(reqDto.getAvc_DCCODE());
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
                    String[] keyList = new String[] {"SERIALKEY"};
                    Object[] valueList = new Object[]  {dto.getSerialkey()};
                   
                    procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    int rv = cmCommonService.saveProcedure(procedureDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)procedureDto.getResultCode();
                    resultMessage = (String)procedureDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if(!"0".equals(resultCode)){
                        log.error("▶비용기표 -> 비용기표 삭제 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }                 
                }
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }    
    
    /**
     * @description : 첨부파일 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.08.05    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<IbExpenseFilePopupResDto> getFileList(IbExpensePopupReqDto ibExpensePopupReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPopupUploadFileList", ibExpensePopupReqDto);
    }    
    
    /**
     * @description : 파일 업로드
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveFileUpload(IbExpensePopupReqDto paramDto, List<MultipartFile> files, List<FileUpload> fileInfoList) {
        IbExpensePopupReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpensePopupReqDto.class);
        
        // 파일 저장 설정
        String workPlaceId = ContextUtil.getProperty("cf.edms.workPlaceId");
        String serialkey = reqDto.getSerialkey();
        String docType = "100";
        
        // 파일명 변경
        int idx = 0;
        for(FileUpload fileInfo : fileInfoList) {
            String strFileName = fileInfo.getAttchFileNm();
            String strFileExtension = "";
            String strTransName = "";
            
            int dot_idx = strFileName.lastIndexOf('.');
            if (dot_idx > -1) {
                strFileExtension = strFileName.substring(dot_idx+1, strFileName.length());
                strTransName = "WMS_EXPENSE_"+System.currentTimeMillis() + "_" + idx + "." + strFileExtension;
            } else {
                strTransName = "WMS_EXPENSE_"+System.currentTimeMillis() + "_" + idx;
            }
            fileInfo.setTransFileNm(strTransName);
            idx++;
        }
        
        // NAS 파일 업로드
        List<FileUpload> fileUploadList = fileUploaderEdms.saveFiles(files, fileInfoList);
        
        // EDMS 파일 업로드
        for(FileUpload fileInfo : fileUploadList) {
            String strTempFilePath = fileInfo.getSavePathNm1() + "/" + fileInfo.getTransFileNm();
            Map rMap = ZnDocumentApi.registerTCS(strTempFilePath, fileInfo.getTransFileNm(), userContext.getUserNo(), "100");
            
            // 파일정보 업무 TABLE에 MERGE
            List<Map<String, Object>> fileList = (List)rMap.get("filelist");
            for(Map<String, Object> file : fileList) {
                log.info("==================== EDMS 업로드 결과 {}", file.toString());

                IbExpenseFilePopupEntity entity = ModelMapperUtil.map(reqDto, userContext, IbExpenseFilePopupEntity.class);
                entity.setSerialkey(serialkey);
                entity.setDocType(docType);  
                entity.setFileName(fileInfo.getAttchFileNm());
                entity.setFileExtension(getFileExtension(fileInfo.getAttchFileNm()));
                entity.setFileLocation(fileInfo.getSavePathNm1() + "/" + fileInfo.getTransFileNm());
                entity.setFileSizeBytes(fileInfo.getAttchFileSz());
                entity.setTransFileName(fileInfo.getTransFileNm()); 
                entity.setUploadResDocId(String.valueOf(file.get("docfileid")));
                entity.setUploadFileName(String.valueOf(file.get("orgname")));
                entity.setUploadWorkplaceId(workPlaceId);

                commonDao.insert(SERVICEID_PREFIX + "insertIbExpenseHttpDocAttach", entity);
            }
        }                
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 일괄 파일 업로드
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.20    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveFileUploadMulti(List<MultipartFile> files, List<FileUpload> fileInfoList,  List<IbExpenseDoucmentHeaderPopupResDto> saveList) {
        //IbExpensePopupReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpensePopupReqDto.class);
        
        // 파일 저장 설정
        String workPlaceId = ContextUtil.getProperty("cf.edms.workPlaceId");
        String serialkey = "";
        String docType = "100";  
        
        //List<IbExpenseDoucmentHeaderPopupResDto> saveList = reqDto.getSaveHeaderList();
        
        for (IbExpenseDoucmentHeaderPopupResDto headerDto : saveList) {
            serialkey = String.valueOf(headerDto.getSerialkey());
            
            // 파일명 변경
            int idx = 0;
            for(FileUpload fileInfo : fileInfoList) {
                String strFileName = fileInfo.getAttchFileNm();
                String strFileExtension = "";
                String strTransName = "";
                
                int dot_idx = strFileName.lastIndexOf('.');
                if (dot_idx > -1) {
                    strFileExtension = strFileName.substring(dot_idx+1, strFileName.length());
                    strTransName = "WMS_EXPENSE_"+System.currentTimeMillis() + "_" + idx + "." + strFileExtension;
                } else {
                    strTransName = "WMS_EXPENSE_"+System.currentTimeMillis() + "_" + idx;
                }
                fileInfo.setTransFileNm(strTransName);
                idx++;
            }
            
            // NAS 파일 업로드
            List<FileUpload> fileUploadList = fileUploaderEdms.saveFiles(files, fileInfoList);
            
            // EDMS 파일 업로드
            for(FileUpload fileInfo : fileUploadList) {
                String strTempFilePath = fileInfo.getSavePathNm1() + "/" + fileInfo.getTransFileNm();
                Map rMap = ZnDocumentApi.registerTCS(strTempFilePath, fileInfo.getTransFileNm(), userContext.getUserNo(), "100");
                
                // 파일정보 업무 TABLE에 MERGE
                List<Map<String, Object>> fileList = (List)rMap.get("filelist");
                for(Map<String, Object> file : fileList) {
                    log.info("==================== EDMS 업로드 결과 {}", file.toString());
    
                    IbExpenseFilePopupEntity entity = ModelMapperUtil.map(headerDto, userContext, IbExpenseFilePopupEntity.class);
                    entity.setSerialkey(serialkey);
                    entity.setDocType(docType);  
                    entity.setFileName(fileInfo.getAttchFileNm());
                    entity.setFileExtension(getFileExtension(fileInfo.getAttchFileNm()));
                    entity.setFileLocation(fileInfo.getSavePathNm1() + "/" + fileInfo.getTransFileNm());
                    entity.setFileSizeBytes(fileInfo.getAttchFileSz());
                    entity.setTransFileName(fileInfo.getTransFileNm()); 
                    entity.setUploadResDocId(String.valueOf(file.get("docfileid")));
                    entity.setUploadFileName(String.valueOf(file.get("orgname")));
                    entity.setUploadWorkplaceId(workPlaceId);
    
                    commonDao.insert(SERVICEID_PREFIX + "insertIbExpenseHttpDocAttach", entity);
                }
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 저장위치정보 파일 다운로드
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public void downloadFile(HttpServletResponse response, FileDownload fileDownload) {
    	if (fileDownload.getSaveFileNm() != null && !"".equals(fileDownload.getSaveFileNm())) {
    		edmsFileDownloader.downloadFile(response, fileDownload.getAttchFileNm(), "100", fileDownload.getSaveFileNm());
    	} else {
    		edmsFileDownloader.downloadFile(response, fileDownload.getAttchFileNm(), "100");
    	}
    }
    
    private String getFileExtension(String name) {
        //String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf + 1);
    }
    
    /**
     * @description : 결재자정보 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<IbExpenseApprovalUserPopupResDto> getPopupApprovalUserInfo(IbExpensePopupReqDto ibExpensePopupReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPopupApprovalUserInfo", ibExpensePopupReqDto);
    }
    
    /**
     * @description : 비용기표 헤더 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<IbExpenseDoucmentHeaderPopupResDto> getPopupApprovalExpenseInfo(IbExpensePopupReqDto ibExpensePopupReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPopupApprovalExpenseInfo", ibExpensePopupReqDto);
    }
    
    /**
     * @description : 비용기표 승인요청 저장 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveApprovalRequest(IbExpensePopupReqDto paramDto) { 
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String returnMessage  = "";
        
        IbExpensePopupReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpensePopupReqDto.class);
        List<IbExpenseDoucmentDetailPopupResDto> saveList = reqDto.getSaveList();
        
        if (null != saveList) {
            //0.마감 상태 체크 
            for (int i = 0; i < saveList.size(); i++) {
                IbExpenseDoucmentDetailPopupResDto dto = saveList.get(i);
                
                String closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getExdcClose", dto);
                if ("Y".equals(closeyn)) {
                    String errMsg =  "정산마감이 되어 상신불가합니다";
                    log.error("▶비용기표 -> 전자결재 오류 발생 / " + errMsg);
                    throw new UserHandleException(""+ errMsg);
                }
                
                closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getIbClose", dto);
                if ("Y".equals(closeyn)) {
                    String errMsg =  "정산마감이 되어 상신불가합니다."; 
                    log.error("▶비용기표 -> 전자결재 오류 발생 / " + errMsg);
                    throw new UserHandleException(""+ errMsg);
                }
            }
            
            //1. 임시테이블에 저장
            CmSyProcessTempAjEntity tempDeleteReqDto = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
            tempDeleteReqDto.setProcesstype(PROCESS_TYPE);
            tempDeleteReqDto.setSpid(reqDto.getGSpid());
            commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqDto);
            int chunkSize = 200;
            List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();

            for (int i = 0; i < saveList.size(); i++) {
                IbExpenseDoucmentDetailPopupResDto dto = saveList.get(i);
                //TEMP 테이블 데이터 세팅
                CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
                
                entity.setProcesstype(PROCESS_TYPE);
                entity.setTemptabletype("IB");
                entity.setSpid(reqDto.getGSpid());
                entity.setProcesscreator(entity.getGUserId());
                entity.setDccode(dto.getDccode());
                entity.setStorerkey(dto.getStorerkey());
                entity.setDocno(dto.getKeyNo());
                entity.setListno(String.valueOf(dto.getSerialkey()));
                
                insertList.add(entity);

                // 200개마다 혹은 마지막 루프일 때 insert
                if (insertList.size() == chunkSize || i == saveList.size() -1) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                    insertList.clear(); // 다음 배치 준비
                }
            }
            //end-1. 임시테이블에 저장
            
            //2. 프로시저 호출
            // PKG 파라마터 세팅 - 공통(1/4)
            IbExpensePopupReqDto procedureReqDto = new IbExpensePopupReqDto();
            ProcedureParametersFactory.initParamDto(reqDto, procedureReqDto, PAKAGE_NAME);
            procedureReqDto.setAvc_DCCODE(reqDto.getAvc_DCCODE());
            procedureReqDto.setProcesstype(PROCESS_TYPE);

            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
            String[] keyList = {
                    "PROCESSTYPE",
                    "PROCESSCREATOR",
                    "SPID"
                };

            Object[] valueList = {
                PROCESS_TYPE,
                reqDto.getGUserId(),
                reqDto.getGSpid() 
            };

            procedureReqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, procedureReqDto);

            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)procedureReqDto.getResultCode();
            resultMessage = (String)procedureReqDto.getReturnMessage();

            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);

            // 프로시저 Exception 처리(4/4)
            if(!"0".equals(resultCode)){
                log.error("▶외부창고정산 결재 -> 저장 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }
            //end-2. 프로시저 호출
            
            //3. 전자결재용 SSO_ID 요청
            try {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

                SSO_NON_SAP_REQUEST reqData = new SSO_NON_SAP_REQUEST();
                reqData.setXSYS("WMS");
                reqData.setXDATS(dateFormat.format(calendar.getTime()));
                reqData.setXTIMS(timeFormat.format(calendar.getTime()));
                reqData.setXROWS("1");
                reqData.setINT_SVC_NO(reqDto.getGUserNo());

                SSO_NON_SAP_EAIPortTypeProxy proxy = new SSO_NON_SAP_EAIPortTypeProxy();
                SSO_NON_SAP_RESPONSE response = proxy.GET_SSO_TICKET(reqData);
                String ssoId = "<SSOID>" + response.getSSO_TICKET() + "</SSOID>";
                resultMessage = resultMessage + ssoId;

            } catch (Exception e) {
                log.error("▶sso proxy 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }
            //end-3. 전자결재용 SSO_ID 요청

            //4. 결과메시지(SSO ID) 리턴   
            return resultMessage;
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    
    /**
     * @description : 비용기표 승인요청 저장 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveApprovalRequest_ASIS(IbExpensePopupReqDto paramDto) {     
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String returnMessage  = "";
        
        // 승인요청 정보
        String apprUsers = "";
        String serialkeys = "";
        String senderEmail ="";
        String rcvrEmail ="";
        String senderNM ="";
        String rcvrNM ="";
        String mailTitle = "";
        long dummyLineNo = 0;

        IbExpensePopupReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpensePopupReqDto.class);
        apprUsers = reqDto.getApprUsers();
        List<IbExpenseDoucmentDetailPopupResDto> saveList = reqDto.getSaveList();
        
        if (!apprUsers.isEmpty() && null != saveList) {            
            for (IbExpenseDoucmentDetailPopupResDto serialkeyDto : saveList) { 
                IbExpenseDoucmentHeaderPopupResDto apprStatusDto = commonDao.selectOne(SERVICEID_PREFIX + "getApprStatus", serialkeyDto);
                
                if (apprStatusDto == null) {
                    throw new UserHandleException("상태값 체크 중 에러 발생 : 비용데이터를 찾을 수 없습니다.");  
                }
                
                String status = apprStatusDto.getStatus();
                String apprStatus = apprStatusDto.getInternalApprStatus();
                
                if (!"CFM".equals(status)){ 
                    throw new UserHandleException("Confirm 항목만 결재처리 가능합니다.");
                }
                
                if (apprStatus != null
                        &&     apprStatus.length() > 0 
                        && !"4".equals(apprStatus)
                        && !"7".equals(apprStatus)
                        && !"3".equals(apprStatus)){  
                    throw new UserHandleException("결재가 이미 진행중인 항목입니다.");
                }
                
                String[] apprUserlist = apprUsers.split("\\|");
                
                if (apprUserlist.length > 0) {
                    dummyLineNo = commonDao.selectOne(SERVICEID_PREFIX + "getDummyLineNo", reqDto);
                    int i = -1;
                    for (String apprUser : apprUserlist) {
                        i++;
                        String[] apprUserInfo = apprUser.split(",");
                        
                        IbExpenseDoucmentHeaderPopupResDto headerInfoDto = commonDao.selectOne(SERVICEID_PREFIX + "getPopupDocumentInfoHeader", serialkeyDto);
                        
//                        if (headerInfoDto.getSummary() == null || headerInfoDto.getSummary().isBlank()) {
//                            mailTitle = "[통관전비용] " + headerInfoDto.getAdjustmentSupplierName() + " / " + String.valueOf(headerInfoDto.getAmount()) + " (" + headerInfoDto.getAmountUnit() + ")";
//                        } else {
//                            mailTitle = "[통관전비용] " + headerInfoDto.getAdjustmentSupplierName() + " / " + String.valueOf(headerInfoDto.getAmount()) + " (" + headerInfoDto.getAmountUnit() + ")" + " / " + headerInfoDto.getSummary();
//                        }
                        
                        long amount = Long.parseLong(String.valueOf(headerInfoDto.getAmount()));
                        String formattedAmount = String.format("%,d", amount);
                        
                        mailTitle = "[비용기표] " + headerInfoDto.getAccountDetailName() + " / " + headerInfoDto.getAdjustmentSupplierName() + " / " + formattedAmount;
                        
                        IbExpenseApprovalUserPopupResDto dto = new IbExpenseApprovalUserPopupResDto();
                        dto.setDummyLineNo(dummyLineNo);
                        dto.setRequesttype(apprUserInfo[0]);
                        dto.setUserId(apprUserInfo[2]);
                        dto.setUserNm(apprUserInfo[3]);
                        dto.setStorernm(apprUserInfo[4]);
                        dto.setDeptnm(apprUserInfo[5]);      
                        dto.setEmail(apprUserInfo[6]);
                        dto.setSeq(i);
                                                
                        if (i == 0) {
                            //기안자
                            senderEmail = dto.getEmail();   
                            senderNM    = dto.getUserNm();
                        } else {
                            //결재자
                            rcvrEmail = dto.getEmail();
                            rcvrNM    = dto.getUserNm();
                        }
                        
                        IbExpenseApprovalUserEntity entity = ModelMapperUtil.map(dto, userContext, IbExpenseApprovalUserEntity.class);
                        
                        commonDao.insert(SERVICEID_PREFIX + "insertIbApprovalLineDummy", entity);
                    }    
                    
                    // PKG 파라마터 세팅 - 공통(1/4)
                    IbExpensePopupReqDto procedureDto = new IbExpensePopupReqDto();
                    ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
                    procedureDto.setAvc_DCCODE(reqDto.getAvc_DCCODE());
                    procedureDto.setAvc_COMMAND(COMMAND_APPR_NAME);
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
                    String[] keyList = new String[] {"SERIALKEY_EX"
                                                    ,"PROCESS_ID"
                                                    ,"DOCUMENT_TABLE"
                                                    ,"TITLE"
                                                    ,"DUMMY_LINE_NO"
                        };
                    Object[] valueList = new Object[]  {serialkeyDto.getSerialkey()
                                                        ,"IM_EX_002"
                                                        ,"IB_EXPENSE"
                                                        ,mailTitle
                                                        ,dummyLineNo
                        };
                   
                    procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    int rv = cmCommonService.saveProcedure(procedureDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)procedureDto.getResultCode();
                    resultMessage = (String)procedureDto.getResultMessage();
                    returnMessage = (String)procedureDto.getReturnMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    log.info("returnMessage->{}",returnMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if (!resultCode.equals("0")){
                        log.error("▶비용기표 -> 내부결재상신 저장 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }   
                    
                    if (senderEmail != null && !"".equals(senderEmail)
                        && rcvrEmail != null   && !"".equals(rcvrEmail) ){
                        Map<String, String> map = new HashMap<String, String>();
                        StringBuffer html     = new StringBuffer();
                        
                        //returnMessage= '<TITLE>'    || PVC_TITLE   || '</TITLE>'
                        //            || '<APPR_NO>'  || VC_KEY_NO   || '</APPR_NO>'                    
                        String title = StringUtil.getParameter("SELECT", returnMessage, "TITLE", null);
                        String apprNo = StringUtil.getParameter("SELECT", returnMessage, "APPR_NO", null);  
                        
                        html.append("<div class='tablecontent_sub_title'>                                               ").append("\n");
                        html.append("   <div class='label'>").append("안녕하세요. CJ 프레시웨이 입니다. ").append("<br><br></div> ").append("\n");
                        html.append("   <div class='label'>").append(senderNM).append("으로부터 ").append(title).append("결재가 요청되었습니다. ").append("<br></div> ").append("\n");
                        html.append("   <div class='label'>").append("결재번호 : ").append(apprNo).append("<br><br></div>   ").append("\n");
                        html.append("   <div class='label'>").append("확인부탁드립니다.").append("<br><br></div>            ").append("\n");
                        html.append("   <div class='label'>").append("감사합니다. ").append("<br><br></div>                  ").append("\n");
                        html.append("</div>                                                                             ").append("\n");

                        IbExpenseEmailLogEntity entity = ModelMapperUtil.map(reqDto, userContext, IbExpenseEmailLogEntity.class);
                        entity.setTitle("[WMS 결재 도착] "+ title.toString() + "결재 요청 알림 - " + ((senderNM!=null)?senderNM.toString():""));
                        entity.setCnts(html.toString());
                        entity.setSendrEmailAddr(senderEmail.toString());
                        entity.setRcvrNm((rcvrNM!=null)?rcvrNM.toString():rcvrNM);
                        entity.setRcvrEmailAddr(rcvrEmail.toString());
                        entity.setRefEmailAddr(null);
                        
                        commonDao.insert(SERVICEID_PREFIX + "insertEmailLog", entity);     
                    }
                }            
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
   
    
    /**
     * @throws RemoteException 
     * @description : 비용기표 Posting 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String posting(IbExpensePopupReqDto paramDto) throws RemoteException {     
        IbExpensePopupReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpensePopupReqDto.class);
        
        List<IbExpenseDoucmentDetailPopupResDto> saveList = reqDto.getSaveList();

        String errMsg = "";
        String ifID = "";
        String ifDocNo = "";
        
        // 마감 상태 체크 
        for (IbExpenseDoucmentDetailPopupResDto saveDto : saveList) {
            String closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getExdcClose", saveDto);
            if ("Y".equals(closeyn)) {
                errMsg =  "정산마감이 되어 Posting 불가합니다";
                log.error("▶비용기표 -> Posting 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }
            
            closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getIbClose", saveDto);
            if ("Y".equals(closeyn)) {
                errMsg =  "정산마감이 되어 Posting 불가합니다."; 
                log.error("▶비용기표 -> Posting 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }
        }
        
        for (IbExpenseDoucmentDetailPopupResDto saveDto : saveList) {
            // 문서 상태 검증
            IbExpenseDoucmentHeaderPopupResDto apprStatusDto = commonDao.selectOne(SERVICEID_PREFIX + "getApprStatus", saveDto);
            
            if (apprStatusDto == null) {
                errMsg = "상태값 체크 중 에러 발생 : 비용데이터를 찾을 수 없습니다.";
            } else if (!"3".equals(apprStatusDto.getInternalApprStatus())){
                errMsg = "내부결재 완결된 항목만 Posting 처리 가능합니다.";
            } else if ("CCO".equals(apprStatusDto.getIfStatus())){
                errMsg = "인터페이스 취소건은 반드시 다음과 같은 순서로 진행해 주십시오. [CONFIRM CANCEL => CONFIRM => POSTING]";
            } else if ("ERR".equals(apprStatusDto.getIfStatus())){
                errMsg = "인터페이스 ERROR건은 반드시 다음과 같은 순서로 진행해 주십시오. [CONFIRM CANCEL => CONFIRM => POSTING]";
            } else if ("SCO".equals(apprStatusDto.getIfStatus())){
                errMsg = "Document No. : ["+ apprStatusDto.getKeyNo() +"] 번은 이미 정상적으로 처리된 건입니다.";
            } else if ("POE".equals(apprStatusDto.getStatus())){
                errMsg = "Posting 할 수 없는 상태입니다. 운영팀에 문의해주세요.";
//            } else if (apprStatusDto.getFiIfStatus() != null && !"".equals(apprStatusDto.getFiIfStatus())){
//                errMsg = "이미 Posting 요청된 상태입니다.";
            } else if (!"CFM".equals(apprStatusDto.getStatus())){
                errMsg = "Confirm 상태의 문서만 Posting 가능합니다.";
            } else if (null != apprStatusDto.getTplType() && "020".equals(apprStatusDto.getTplType())){
                errMsg = "위탁물류 항목은 Posting 할 수 없습니다.";    
            } else {
                ifDocNo = apprStatusDto.getIfDocNo();
                ifID = apprStatusDto.getIfId();           
            }
            
            if (!"".equals(errMsg)) {
                log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }
            
            // I/F 테이블 업데이트
            String closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getExdcClose", saveDto);
            
            if (closeyn.isEmpty()) {
                errMsg =  "보관료 마감 체크중 에러 발생 : 데이터를 찾을 수 없습니다.";            
                log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }
            if ("Y".equals(closeyn)) {
                errMsg =  "정산마감이 되어 Posting 불가능 합니다.";            
                log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }
            
            if ("SCM0480".equals(ifID)) { 
                IbExpenseIfIbStockEntity entity = ModelMapperUtil.map(saveDto, userContext, IbExpenseIfIbStockEntity.class);
                entity.setIfDocNo(ifDocNo);
                int rv = commonDao.update(SERVICEID_PREFIX + "updateIfIbStockEtcHeader", entity);
                if (rv == 0) {
                    errMsg =  "I/F 테이블 업데이트 중 에러 발생 : 데이터를 찾을 수 없습니다.";            
                    log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                    throw new UserHandleException(""+ errMsg);
                }
            } else if ("SCM0470".equals(ifID)) {
                IbExpenseIfIbStockEntity entity = ModelMapperUtil.map(saveDto, userContext, IbExpenseIfIbStockEntity.class);
                entity.setIfDocNo(ifDocNo);
                int rv = commonDao.update(SERVICEID_PREFIX + "updateIfIbStockSkuHeader", entity);
                if (rv == 0) {
                    errMsg =  "I/F 테이블 업데이트 중 에러 발생 : 데이터를 찾을 수 없습니다.";            
                    log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                    throw new UserHandleException(""+ errMsg);
                }
            }
            
            
            
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
            
            if ("SCM0480".equals(ifID)) {
                IfIbStocketcHDto ifDto = new IfIbStocketcHDto();
                ifDto.setSerialkey(String.valueOf(saveDto.getSerialkey()));
                ifDto.setIfType("POSTING");
                
                List<IfIbStocketcHDto> expenseHeaderList = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKETC_H", ifDto);            
                if (expenseHeaderList == null || expenseHeaderList.size() == 0) {
                    errMsg =  "I/F 전송시 에러 발생 : 데이터를 찾을 수 없습니다.";            
                    log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                    throw new UserHandleException(""+ errMsg);
                }
                
                ifDto.setZinvoice(expenseHeaderList.get(0).getZinvoice());
                
                List<IfIbStocketcDDto> expenseDetailList = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKETC_D", ifDto);            
                if (expenseDetailList == null || expenseDetailList.size() == 0) {
                    errMsg =  "I/F 전송시 에러 발생 : 데이터를 찾을 수 없습니다.";            
                    log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                    throw new UserHandleException(""+ errMsg);
                }
    
                List<IfIbStocketcFileDto> expenseFileList = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKETC_FILE", ifDto);
                
                SI_SCM0480_SCM_SOProxy proxy = new SI_SCM0480_SCM_SOProxy();
                
                DT_SCM0480_SCM fiData = new DT_SCM0480_SCM();
    
                DT_SCM0480_SCMIF_IB_STOCKETC_H[] stocketc_h_list = new DT_SCM0480_SCMIF_IB_STOCKETC_H[expenseHeaderList.size()];
                DT_SCM0480_SCMIF_IB_STOCKETC_D[] stocketc_d_list = new DT_SCM0480_SCMIF_IB_STOCKETC_D[expenseDetailList.size()];
                DT_SCM0480_SCMIF_IB_STOCKETC_FILE[] stocketc_file_list = null;
    
                for (int i = 0; i<expenseHeaderList.size();i++) {
                    DT_SCM0480_SCMIF_IB_STOCKETC_H stocketc_h = new DT_SCM0480_SCMIF_IB_STOCKETC_H();
    
                    stocketc_h.setZINVOICE (expenseHeaderList.get(i).getZinvoice());
                    stocketc_h.setZSEQ     (expenseHeaderList.get(i).getZseq());
                    stocketc_h.setXRECH    (expenseHeaderList.get(i).getXrech());
                    
                    stocketc_h.setZLAST    (expenseHeaderList.get(i).getZlast());
                    stocketc_h.setBUKRS    (expenseHeaderList.get(i).getBukrs());
                    stocketc_h.setLIFNR    (expenseHeaderList.get(i).getLifnr());
                    stocketc_h.setEMPFB    (expenseHeaderList.get(i).getEmpfB());
                    stocketc_h.setBLDAT    (expenseHeaderList.get(i).getBldat());
                    stocketc_h.setBUDAT    (expenseHeaderList.get(i).getBudat());
                    stocketc_h.setWAERS    (expenseHeaderList.get(i).getWaers());
                    stocketc_h.setMWSKZ1   (expenseHeaderList.get(i).getMwskz1());
                    stocketc_h.setZTERM    (expenseHeaderList.get(i).getZterm());
                    stocketc_h.setZFBDT    (expenseHeaderList.get(i).getZfbdt());
                    stocketc_h.setRMWWR    (expenseHeaderList.get(i).getRmwwr());
                    stocketc_h.setWMWST1   (expenseHeaderList.get(i).getWmwst1());
                    stocketc_h.setKIDNO    (expenseHeaderList.get(i).getKidno());
                    stocketc_h.setBKTXT    (expenseHeaderList.get(i).getBktxt());
                    stocketc_h.setZGUBUN   (expenseHeaderList.get(i).getZgubun());
                    stocketc_h.setZBILLNO  (expenseHeaderList.get(i).getZbillno());
                    stocketc_h.setCARD_NO  (expenseHeaderList.get(i).getCardNo());
                    stocketc_h.setAPPR_DATE(expenseHeaderList.get(i).getApprDate());
                    stocketc_h.setAPPR_DOCU(expenseHeaderList.get(i).getApprDocu());
                    stocketc_h.setCNCL_FLAG(expenseHeaderList.get(i).getCnclFlag());
                    stocketc_h.setSELL_DOCU(expenseHeaderList.get(i).getSellDocu());
                    stocketc_h.setZTEXT1   (expenseHeaderList.get(i).getZtext1());
                    stocketc_h.setZTEXT2   (expenseHeaderList.get(i).getZtext2());
                    stocketc_h.setZTEXT3   (expenseHeaderList.get(i).getZtext3());
                    stocketc_h.setZTEXT4   (expenseHeaderList.get(i).getZtext4());
                    stocketc_h.setZTEXT5   (expenseHeaderList.get(i).getZtext5());
                    
                    stocketc_h.setXDATS    (dateFormat.format(calendar.getTime()));
                    stocketc_h.setXTIMS    (timeFormat.format(calendar.getTime()));
                    stocketc_h.setXUSER    (saveDto.getGUserId());
                    
                    stocketc_h_list[i] = stocketc_h;
                }
                
                for (int j = 0; j<expenseDetailList.size(); j++) {
                    DT_SCM0480_SCMIF_IB_STOCKETC_D stocketc_d = new DT_SCM0480_SCMIF_IB_STOCKETC_D();
                    
                    stocketc_d.setZINVOICE(expenseDetailList.get(j).getZinvoice());
                    stocketc_d.setBUZEI   (expenseDetailList.get(j).getBuzei());
                    stocketc_d.setZSEQ    (expenseDetailList.get(j).getZseq());
                    stocketc_d.setMWSKZ   (expenseDetailList.get(j).getMwskz());
                    stocketc_d.setWRBTR   (expenseDetailList.get(j).getWrbtr());
                    stocketc_d.setWERKS   (expenseDetailList.get(j).getWerks());
                    stocketc_d.setMATNR   (expenseDetailList.get(j).getMatnr());
                    stocketc_d.setFRBNR   (expenseDetailList.get(j).getFrbnr());
                    stocketc_d.setHISTNO  (expenseDetailList.get(j).getHistno());
                    stocketc_d.setZEBELN  (expenseDetailList.get(j).getZebeln());
                    stocketc_d.setZEBELP  (expenseDetailList.get(j).getZebelp());
                    stocketc_d.setMENGE   (expenseDetailList.get(j).getMenge());
                    stocketc_d.setBSTME   (expenseDetailList.get(j).getBstme());
                    stocketc_d.setZTEXT1  (expenseDetailList.get(j).getZtext1());
                    stocketc_d.setZTEXT2  (expenseDetailList.get(j).getZtext2());
                    stocketc_d.setZTEXT3  (expenseDetailList.get(j).getZtext3());
                    stocketc_d.setZTEXT4  (expenseDetailList.get(j).getZtext4());
                    stocketc_d.setZTEXT5  (expenseDetailList.get(j).getZtext5());
                    
                    stocketc_d_list[j] = stocketc_d;
                }
                
                if (expenseFileList != null && expenseFileList.size() > 0)  {
                    stocketc_file_list = new DT_SCM0480_SCMIF_IB_STOCKETC_FILE[expenseFileList.size()];
                    for (int j = 0; j<expenseFileList.size();j++)  {
                        DT_SCM0480_SCMIF_IB_STOCKETC_FILE stocketc_file = new DT_SCM0480_SCMIF_IB_STOCKETC_FILE();
                        
                        stocketc_file.setBUKRS     (expenseFileList.get(j).getBukrs());
                        stocketc_file.setZSYST     (expenseFileList.get(j).getZsyst());
                        stocketc_file.setIF_NO     (expenseFileList.get(j).getIfNo());
                        stocketc_file.setSEQ       (expenseFileList.get(j).getSeq());
                        stocketc_file.setZDOCNO    (expenseFileList.get(j).getZdocno());
                        stocketc_file.setZEVID1    (expenseFileList.get(j).getZevid1());
                        stocketc_file.setZDOCTYPE  (expenseFileList.get(j).getZdoctype());
                        stocketc_file.setZATTID    (expenseFileList.get(j).getZattid());
                        stocketc_file.setZREQUESTNO(expenseFileList.get(j).getZrequestno());
                        stocketc_file.setZCATEGORY (expenseFileList.get(j).getZcategory());
                        
                        stocketc_file_list[j] = stocketc_file;
                    }
                } else {
                    stocketc_file_list = new DT_SCM0480_SCMIF_IB_STOCKETC_FILE[0];
                }
                
                fiData.setIF_IB_STOCKETC_H(stocketc_h_list);
                fiData.setIF_IB_STOCKETC_D(stocketc_d_list);
                fiData.setIF_IB_STOCKETC_FILE(stocketc_file_list);
                
                DT_SCM0480_SCM_responseIF_IB_STOCKETC_H_RET[] responses = null;
                try {
                    responses = proxy.sI_SCM0480_SCM_SO(fiData);
                } catch (Exception e) {
                    log.error("▶비용기표 -> Posting 저장 오류 발생 / " + e.getMessage());
                    throw new UserHandleException(""+ e.getMessage());
                }
                
                // 응답에 대한 결과 처리
                IbExpenseResultEntity entity = ModelMapperUtil.map(saveDto, userContext, IbExpenseResultEntity.class);
                
                if (responses != null && responses.length > 0) {
                    int iFailCount = 0;
                    for (int i = 0; i< responses.length; i++) {
                        DT_SCM0480_SCM_responseIF_IB_STOCKETC_H_RET response = responses[i];
    
                        entity.setIfType("POSTING");
                        entity.setZinvoice(response.getZINVOICE());
                        entity.setZseq(expenseHeaderList.get(i).getZseq());
                        entity.setXstat(response.getXSTAT());
                        entity.setXmsgs(response.getXMSGS());
                        entity.setZreturn(response.getZRETURN());
                        entity.setZreBelnr(response.getZRE_BELNR());
    
                        if (!"S".equals(response.getXSTAT())) {
                            entity.setXstat("E");
                            iFailCount++;
                            
                            errMsg = "ERROR : " + response.getXMSGS();            
                            log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                        }
                        else if(response.getZRE_BELNR() == null || "".equals(response.getZRE_BELNR())){
                            entity.setXstat("E");
                            entity.setXmsgs("ERROR : FI 전표 미생성");
                            iFailCount++;
                            
                            errMsg = "ERROR : FI 전표 미생성";            
                            log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                        }
    
                        commonDao.update(SERVICEID_PREFIX + "updateIfResultIfIbStockEtcH", entity);
                    }
                    
                    if (responses.length == expenseHeaderList.size() && iFailCount == 0) {
                        entity.setXstat("S");
                    } else if (responses.length == iFailCount) {
                        entity.setXstat("E");
                    } else {
                        entity.setXstat("POE");
                        
                        errMsg = "I/F 처리시 에러 발생 : 운영팀에 문의해 주세요.";            
                        log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                    }
                    
                    commonDao.update(SERVICEID_PREFIX + "updateIfResultIbExpense", entity);
                } else {
                    errMsg =  "I/F 전송시 에러 발생 : 응답이 없습니다.";            
                    log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                    throw new UserHandleException(""+ errMsg);
                }
            } else if("SCM0470".equals(ifID))  {
                IfIbStockskuHDto ifDto = new IfIbStockskuHDto();
                ifDto.setSerialkey(String.valueOf(saveDto.getSerialkey()));
                ifDto.setIfType("POSTING");
                List<IfIbStocketcHDto> expenseHeaderList = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_H", ifDto);
                
                if (expenseHeaderList == null || expenseHeaderList.size() == 0) {
                   errMsg =  "I/F 전송시 에러 발생 : 데이터를 찾을 수 없습니다.";            
                   log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                   throw new UserHandleException(""+ errMsg);
                }
                
                IfIbStockskuHDto selDto = new IfIbStockskuHDto();
                selDto.setZinvoice(expenseHeaderList.get(0).getZinvoice());            
                List<IfIbStockskuDDto> expenseDetailLst = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_D", selDto); 
                List<IfIbStockskuBillDto> expenseBillLst = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_BILL", selDto); 
                List<IfIbStockskuFileDto> expenseFileLst = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_FILE", selDto); 
    
                if (expenseDetailLst == null || expenseDetailLst.size()== 0) {
                    errMsg =  "I/F 전송시 에러 발생 : 데이터를 찾을 수 없습니다.";            
                    log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                    throw new UserHandleException(""+ errMsg);                
                }
                
                DT_SCM0470_SCMIF_IB_STOCKSKU_H[] stocksku_h_list = new DT_SCM0470_SCMIF_IB_STOCKSKU_H[expenseHeaderList.size()];
                DT_SCM0470_SCMIF_IB_STOCKSKU_D[] stocksku_d_list = new DT_SCM0470_SCMIF_IB_STOCKSKU_D[expenseDetailLst.size()];
                DT_SCM0470_SCMIF_IB_STOCKSKU_BILL[] stocksku_bill_list = null;
                DT_SCM0470_SCMIF_IB_STOCKSKU_FILE[] stocksku_file_list = new DT_SCM0470_SCMIF_IB_STOCKSKU_FILE[expenseFileLst.size()];
                
                SI_SCM0470_SCM_SOProxy proxy = new SI_SCM0470_SCM_SOProxy();
                
                DT_SCM0470_SCM fiData = new DT_SCM0470_SCM();
                
                for (int i = 0; i<expenseHeaderList.size(); i++) {
                    DT_SCM0470_SCMIF_IB_STOCKSKU_H stocksku_h = new DT_SCM0470_SCMIF_IB_STOCKSKU_H();
                    
                    stocksku_h.setZINVOICE (expenseHeaderList.get(i).getZinvoice());
                    stocksku_h.setZCAT     (expenseHeaderList.get(i).getZcat());
                    stocksku_h.setZSEQ     (expenseHeaderList.get(i).getZseq());
                    stocksku_h.setZLAST    (expenseHeaderList.get(i).getZlast());
                    stocksku_h.setXRECH    (expenseHeaderList.get(i).getXrech());
                    stocksku_h.setBUKRS    (expenseHeaderList.get(i).getBukrs());
                    stocksku_h.setLIFNR    (expenseHeaderList.get(i).getLifnr());
                    stocksku_h.setEMPFB    (expenseHeaderList.get(i).getEmpfB());
                    stocksku_h.setBLDAT    (expenseHeaderList.get(i).getBldat());
                    stocksku_h.setBUDAT    (expenseHeaderList.get(i).getBudat());
                    stocksku_h.setWAERS    (expenseHeaderList.get(i).getWaers());
                    stocksku_h.setMWSKZ1   (expenseHeaderList.get(i).getMwskz1());
                    stocksku_h.setZTERM    (expenseHeaderList.get(i).getZterm());
                    stocksku_h.setZFBDT    (expenseHeaderList.get(i).getZfbdt());
                    stocksku_h.setRMWWR    (expenseHeaderList.get(i).getRmwwr());
                    stocksku_h.setWMWST1   (expenseHeaderList.get(i).getWmwst1());
                    stocksku_h.setKIDNO    (expenseHeaderList.get(i).getKidno());
                    stocksku_h.setBKTXT    (expenseHeaderList.get(i).getBktxt());
                    stocksku_h.setZGUBUN   (expenseHeaderList.get(i).getZgubun());
                    stocksku_h.setZBILLNO  (expenseHeaderList.get(i).getZbillno());
                    stocksku_h.setCARD_NO  (expenseHeaderList.get(i).getCardNo());
                    stocksku_h.setAPPR_DATE(expenseHeaderList.get(i).getApprDate());
                    stocksku_h.setAPPR_DOCU(expenseHeaderList.get(i).getApprDocu());
                    stocksku_h.setCNCL_FLAG(expenseHeaderList.get(i).getCnclFlag());
                    stocksku_h.setSELL_DOCU(expenseHeaderList.get(i).getSellDocu());
                    stocksku_h.setZTEXT1   (expenseHeaderList.get(i).getZtext1());
                    stocksku_h.setZTEXT2   (expenseHeaderList.get(i).getZtext2());
                    stocksku_h.setZTEXT3   (expenseHeaderList.get(i).getZtext3());
                    stocksku_h.setZTEXT4   (expenseHeaderList.get(i).getZtext4());
                    stocksku_h.setZTEXT5   (expenseHeaderList.get(i).getZtext5());
                    
                    stocksku_h.setXDATS    (dateFormat.format(calendar.getTime()));
                    stocksku_h.setXTIMS    (timeFormat.format(calendar.getTime()));
                    stocksku_h.setXUSER    (saveDto.getGUserId());
                    
                    stocksku_h_list[i] = stocksku_h;
                }   
                
                for (int j = 0; j<expenseDetailLst.size(); j++) {
                    DT_SCM0470_SCMIF_IB_STOCKSKU_D stocksku_d = new DT_SCM0470_SCMIF_IB_STOCKSKU_D();
                    
                    stocksku_d.setZINVOICE  (expenseDetailLst.get(j).getZinvoice()); 
                    stocksku_d.setZCAT      (expenseDetailLst.get(j).getZcat());
                    stocksku_d.setZSEQ      (expenseDetailLst.get(j).getZseq());
                    stocksku_d.setBUZEI     (expenseDetailLst.get(j).getBuzei());
                    stocksku_d.setGL_ACCOUNT(expenseDetailLst.get(j).getGlAccount());
                    stocksku_d.setMWSKZ     (expenseDetailLst.get(j).getMwskz());
                    stocksku_d.setPROFIT_CTR(expenseDetailLst.get(j).getProfitCtr());
                    stocksku_d.setSHKZG     (expenseDetailLst.get(j).getShkzg());
                    stocksku_d.setWRBTR     (expenseDetailLst.get(j).getWrbtr());
    
                    stocksku_d.setMATNR     (expenseDetailLst.get(j).getMatnr());
                    stocksku_d.setMENGE     (expenseDetailLst.get(j).getMenge());
                    stocksku_d.setBSTME     (expenseDetailLst.get(j).getBstme());
                    stocksku_d.setZEBELN    (expenseDetailLst.get(j).getZebeln());
                    stocksku_d.setZEBELP    (expenseDetailLst.get(j).getZebelp());
                    if(expenseBillLst != null && expenseBillLst.size() > 0 && j == 0) {
                        stocksku_d.setZTEXT1    (expenseBillLst.get(0).getMatnr());
                    }else{ 
                        stocksku_d.setZTEXT1    (expenseDetailLst.get(j).getZtext1());
                    }
    
                    stocksku_d.setZTEXT2    (expenseDetailLst.get(j).getZtext2());
                    stocksku_d.setZTEXT3    (expenseDetailLst.get(j).getZtext3());
                    stocksku_d.setZTEXT4    (expenseDetailLst.get(j).getZtext4());
                    stocksku_d.setZTEXT5    (expenseDetailLst.get(j).getZtext5());
                    
                    stocksku_d_list[j] = stocksku_d;
                }
                
                if (expenseDetailLst != null && expenseDetailLst.size() > 0) {
                    for (int j = 0; j<expenseFileLst.size(); j++) {
                        DT_SCM0470_SCMIF_IB_STOCKSKU_FILE stocksku_file = new DT_SCM0470_SCMIF_IB_STOCKSKU_FILE();
                        
                        stocksku_file.setBUKRS     (expenseFileLst.get(j).getBukrs());
                        stocksku_file.setZSYST     (expenseFileLst.get(j).getZsyst());
                        stocksku_file.setIF_NO     (expenseFileLst.get(j).getIfNo());
                        stocksku_file.setSEQ       (expenseFileLst.get(j).getSeq());
                        stocksku_file.setZDOCNO    (expenseFileLst.get(j).getZdocno());
                        stocksku_file.setZEVID1    (expenseFileLst.get(j).getZevid1());
                        stocksku_file.setZDOCTYPE  (expenseFileLst.get(j).getZdoctype());
                        stocksku_file.setZATTID    (expenseFileLst.get(j).getZattid());
                        stocksku_file.setZREQUESTNO(expenseFileLst.get(j).getZrequestno());
                        stocksku_file.setZCATEGORY (expenseFileLst.get(j).getZcategory());
                        
                        stocksku_file_list[j] = stocksku_file;
                    }
                } else {
                    stocksku_file_list = new DT_SCM0470_SCMIF_IB_STOCKSKU_FILE[0];
                }
                
                fiData.setIF_IB_STOCKSKU_H(stocksku_h_list);
                fiData.setIF_IB_STOCKSKU_D(stocksku_d_list);
                fiData.setIF_IB_STOCKSKU_BILL(stocksku_bill_list);
                fiData.setIF_IB_STOCKSKU_FILE(stocksku_file_list);
    
                DT_SCM0470_SCM_responseT_RETURN[] responses = null;
                
                try {
                    responses = proxy.sI_SCM0470_SCM_SO(fiData);
                } catch (Exception e) {
                    log.error("▶비용기표 -> Posting (0470) 저장 오류 발생 / " + e.getMessage());
                    throw new UserHandleException(""+ e.getMessage());
                }
                
                // 응답에 대한 결과 처리
                IbExpenseResultEntity entity = ModelMapperUtil.map(saveDto, userContext, IbExpenseResultEntity.class);
                
                if (responses != null && responses.length > 0) {
                    int iFailCount = 0;
                    for (int i = 0; i< responses.length; i++) {
                        DT_SCM0470_SCM_responseT_RETURN response = responses[i];
                        
                        entity.setIfType("POSTING");
                        entity.setZinvoice(response.getZINVOICE());
                        entity.setZseq(expenseHeaderList.get(i).getZseq());   
                        entity.setXstat(response.getXSTAT());
                        entity.setXmsgs(response.getXMSGS());
                        entity.setZreturn(response.getZRETURN());
                        entity.setZreBelnr(response.getZRE_BELNR());
    
                        if (!"S".equals(response.getXSTAT())) {
                            entity.setXstat("E");
                            iFailCount++;
                            
                            errMsg = response.getXMSGS();            
                            log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                        } else if (response.getZRE_BELNR() == null || "".equals(response.getZRE_BELNR())) {
                            entity.setXstat("E");
                            entity.setXmsgs("ERROR : FI 전표 미생성");
                            iFailCount++;
                            
                            errMsg = "ERROR : FI 전표 미생성";            
                            log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                        }
                        
                        commonDao.update(SERVICEID_PREFIX + "updateIfResultIfIbStockSkuH", entity);
                    }
                    
                    if(responses.length == expenseHeaderList.size() && iFailCount == 0) {
                        entity.setXstat("S");
                    } else if (responses.length == iFailCount) {
                        entity.setXstat("E");
                    } else {
                        entity.setXstat("POE");
                        
                        errMsg = "I/F 처리시 에러 발생 : 운영팀에 문의해 주세요.";            
                        log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                    }
                    
                    entity.setSerialkey(String.valueOf(saveDto.getSerialkey()));
                    
                    commonDao.update(SERVICEID_PREFIX + "updateIfResultIbExpense", entity);                
                } else {
                    errMsg = "I/F 전송시 에러 발생 : 응답이 없습니다.";            
                    log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                    throw new UserHandleException(""+ errMsg);     
                }
            }        
        }
        
        if (!errMsg.isEmpty() ) {
            throw new UserHandleException(""+ errMsg);
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @throws RemoteException 
     * @description : 비용기표 Posting 취소
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String postingCancel(IbExpensePopupReqDto paramDto) throws RemoteException {  
        IbExpensePopupReqDto reqDto = ModelMapperUtil.map(paramDto, IbExpensePopupReqDto.class);
        
        List<IbExpenseDoucmentDetailPopupResDto> saveList = reqDto.getSaveList();

        String errMsg = "";
        String ifID = "";
        
        // 마감 상태 체크 
        for (IbExpenseDoucmentDetailPopupResDto saveDto : saveList) {
            String closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getExdcClose", saveDto);
            if ("Y".equals(closeyn)) {
                errMsg =  "정산마감이 되어 Posting 취소 불가합니다";
                log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }
            
            closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getIbClose", saveDto);
            if ("Y".equals(closeyn)) {
                errMsg =  "정산마감이 되어 Posting 취소 불가합니다."; 
                log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }
        }
        
        for (IbExpenseDoucmentDetailPopupResDto saveDto : saveList) {
            IbExpenseDoucmentHeaderPopupResDto apprStatusDto = commonDao.selectOne(SERVICEID_PREFIX + "getApprStatus", saveDto);
            
            if(apprStatusDto == null) {
                errMsg = "상태값 체크 중 에러 발생 : 비용데이터를 찾을 수 없습니다.";
                log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }
            
            if (!"POE".equals(apprStatusDto.getStatus())) {
                if (apprStatusDto.getFiIfStatus() == null 
                        ||"".equals(apprStatusDto.getFiIfStatus())
                        ||!"CNF".equals(apprStatusDto.getFiIfStatus())
                        ||!"SCO".equals(apprStatusDto.getIfStatus())
                        ||!"SCO".equals(apprStatusDto.getStatus())) {
                    errMsg = "Posting 된 문서만 취소 가능합니다.";
                    log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
                    throw new UserHandleException(""+ errMsg);
                }
            }
                
            String closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getExdcClose", saveDto);
            
            if (closeyn.isEmpty()) {
                errMsg = "보관료 마감 체크중 에러 발생 : 데이터를 찾을 수 없습니다.";
                log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }
            
            if ("Y".equals(closeyn)) {
                errMsg = "정산마감이 되어 Posting 취소 불가능 합니다.";
                log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }

            ifID = apprStatusDto.getIfId();

            IfIbStocketcHDto ifDto = new IfIbStocketcHDto();
            ifDto.setSerialkey(String.valueOf(saveDto.getSerialkey()));
            ifDto.setIfType("POSTING_CANCEL");
            
            List<IfIbStocketcHDto> expenseHeaderList = null;
           //List<IfIbStockskuHDto> expenseUHeaderList = null;
            
            if ("SCM0480".equals(ifID)) {
                expenseHeaderList = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKETC_H", ifDto); 
            } else {
                expenseHeaderList = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_H", ifDto);
            }

            if (expenseHeaderList == null ||expenseHeaderList.size() == 0) {
                errMsg = "I/F 취소 전송시 에러 발생 : 데이터를 찾을 수 없습니다.";
                log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }
            
            SI_SCM0510_SCM_SOProxy proxy = new SI_SCM0510_SCM_SOProxy();
            
            DT_SCM0510_SCMIF_SAPSLIPCANCEL[] slipCancel_list = new DT_SCM0510_SCMIF_SAPSLIPCANCEL[expenseHeaderList.size()];

            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            
            for (int i = 0; i < expenseHeaderList.size(); i++) { 
                
                DT_SCM0510_SCMIF_SAPSLIPCANCEL slipCancel = new DT_SCM0510_SCMIF_SAPSLIPCANCEL();
                slipCancel.setZINVOICE (expenseHeaderList.get(i).getZinvoice());
                slipCancel.setBELNR    (expenseHeaderList.get(i).getZreturn());
                slipCancel.setGJAHR    (expenseHeaderList.get(i).getBudat().substring(0, 4));
                slipCancel.setSTGRD    ("03");
                slipCancel.setBUDAT    ("");
                slipCancel.setZCLAIM   ("");

                if (!StringUtil.isEmpty(expenseHeaderList.get(i).getZbillno())) {
                    slipCancel.setBUKRS    (reqDto.getGStorerkey());
                    slipCancel.setZBILLNO  (expenseHeaderList.get(i).getZbillno());
                    slipCancel.setI_GB_D_K ("K");
                    slipCancel.setI_GUBUN  ("C");
                } else {
                    slipCancel.setBUKRS    ("");
                    slipCancel.setZBILLNO  ("");
                    slipCancel.setI_GB_D_K ("");
                    slipCancel.setI_GUBUN  ("");
                }
                slipCancel_list[i] = slipCancel;
            }
            
            DT_SCM0510_SCM_responseRETURN[] responses = null;
            responses = proxy.sI_SCM0510_SCM_SO(slipCancel_list);
            
            // 응답에 대한 결과 처리
            IbExpenseResultEntity entity = ModelMapperUtil.map(saveDto, userContext, IbExpenseResultEntity.class);
            
            if (responses != null && responses.length > 0) {
                int iFailCount = 0;
                for (int i = 0; i< responses.length; i++) {
                    DT_SCM0510_SCM_responseRETURN response = responses[i];
                    
                    entity.setIfType("POSTING_CANCEL");
                    entity.setZinvoice(response.getZINVOICE());
                    entity.setZseq(expenseHeaderList.get(i).getZseq());
                    entity.setXstat(response.getXSTAT());
                    entity.setXmsgs(response.getXMSGS());
                    entity.setZreturn(response.getZRETURN());
                    entity.setZreBelnr(response.getZRE_BELNR());

                    if (!"S".equals(response.getXSTAT())) {
                        entity.setXstat("E");
                        iFailCount++;

                        errMsg = "ERROR : " + response.getXMSGS();            
                        log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
                    }
                    
                    if ("SCM0480".equals(ifID)) {
                        commonDao.update(SERVICEID_PREFIX + "updateIfResultIfIbStockEtcH", entity);
                    } else if("SCM0470".equals(ifID)) {
                        commonDao.update(SERVICEID_PREFIX + "updateIfResultIfIbStockSkuH", entity);
                    }
                }
                
                if (responses.length == expenseHeaderList.size() && iFailCount == 0) {
                    entity.setXstat("S");
                } else if (responses.length == iFailCount) {
                    entity.setXstat("E");
                } else {
                    entity.setXstat("POE");
                    
                    errMsg = "I/F 처리시 에러 발생 : 운영팀에 문의해 주세요.";  
                    log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                }
                
                commonDao.update(SERVICEID_PREFIX + "updateIfResultIbExpense", entity);
            } else {
                errMsg = "I/F 전송시 에러 발생 : 응답이 없습니다.";            
                log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }
        }
        
        if (!errMsg.isEmpty() ) {
            throw new UserHandleException(""+ errMsg);
        } 
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 비용기표 체크 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.08.20    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<IbExpenseResDto> getKeynoList(IbExpensePopupReqDto ibExpensePopupReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getKeynoList", ibExpensePopupReqDto);
    }

}
