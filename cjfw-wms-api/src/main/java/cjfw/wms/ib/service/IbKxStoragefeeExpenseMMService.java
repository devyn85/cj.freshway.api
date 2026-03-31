package cjfw.wms.ib.service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.ib.dto.IbExpenseDoucmentDetailPopupResDto;
import cjfw.wms.ib.dto.IbExpenseDoucmentHeaderPopupResDto;
import cjfw.wms.ib.dto.IbExpensePopupReqDto;
import cjfw.wms.ib.dto.IbExpenseReqDto;
import cjfw.wms.ib.dto.IbExpenseResDto;
import cjfw.wms.ib.dto.IbKxStoragefeeExpenseMMReqDto;
import cjfw.wms.ib.dto.IbKxStoragefeeExpenseMMResDto;
import cjfw.wms.ib.dto.IbKxStoragefeeExpenseReqDto;
import cjfw.wms.ib.dto.IbKxStoragefeeExpenseResDto;
import cjfw.wms.ib.dto.IfIbStocketcDDto;
import cjfw.wms.ib.dto.IfIbStocketcFileDto;
import cjfw.wms.ib.dto.IfIbStocketcHDto;
import cjfw.wms.ib.dto.IfIbStockskuBillDto;
import cjfw.wms.ib.dto.IfIbStockskuDDto;
import cjfw.wms.ib.dto.IfIbStockskuFileDto;
import cjfw.wms.ib.dto.IfIbStockskuHDto;
import cjfw.wms.ib.entity.IbExpenseIfIbStockEntity;
import cjfw.wms.ib.entity.IbExpenseResultEntity;
import cjfw.wms.ib.entity.IbKxStoragefeeExpenseEntity;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.12.29
 * @description :비용기표(1000센터) 기능을 구현한 Service Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.29         ParkYoSep (dytpq362@cj.net)      생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IbKxStoragefeeExpenseMMService {
	
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private final UserContext userContext;
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "ibKxStoragefeeExpenseMMService.";
    private transient static final String PAKAGE_NAME = "SPIB_EXPENSE_KX";  
    
    
	/**
	 * @description :  비용기표(1000센터) 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE              AUTHOR             MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<IbKxStoragefeeExpenseMMResDto> getMasterList(IbKxStoragefeeExpenseMMReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
    /**
     * @description : 비용기표(1000센터) 확정
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.01.05      생성
     */
    public String saveConfirm(IbKxStoragefeeExpenseMMReqDto paramDto) {     
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        IbKxStoragefeeExpenseMMReqDto reqDto = ModelMapperUtil.map(paramDto, IbKxStoragefeeExpenseMMReqDto.class);
        List<IbKxStoragefeeExpenseMMResDto> saveList = reqDto.getSaveList();
        
        if (null != saveList) {
            if (saveList.size() > 0) {
                for (IbKxStoragefeeExpenseMMResDto dto : saveList) {
                    // PKG 파라마터 세팅 - 공통(1/4)
                	IbKxStoragefeeExpenseMMReqDto procedureDto = new IbKxStoragefeeExpenseMMReqDto();
                    ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
                    procedureDto.setAvc_DCCODE(reqDto.getAvc_DCCODE());
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
                    String[] keyList = new String[] {"SERIALKEY","ISSUE_ID"};
                    Object[] valueList = new Object[]  {dto.getSerialKey(),dto.getIssueId()};
                   
                    procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    int rv = cmCommonService.saveProcedure(procedureDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)procedureDto.getResultCode();
                    resultMessage = (String)procedureDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if(!resultCode.equals("0")){
                        log.error("▶비용기표(1000센터) -> 비용기표(1000센터) 확정 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }                 
                }
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    /**
     * @description : 비용기표(1000센터) 확정취소
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.01.05      생성
     */
    public String saveConfirmCancel(IbKxStoragefeeExpenseMMReqDto paramDto) {     
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        IbKxStoragefeeExpenseMMReqDto reqDto = ModelMapperUtil.map(paramDto, IbKxStoragefeeExpenseMMReqDto.class);
        List<IbKxStoragefeeExpenseMMResDto> saveList = reqDto.getSaveList();
        
        if (null != saveList) {
            if (saveList.size() > 0) {
                for (IbKxStoragefeeExpenseMMResDto dto : saveList) {
                    // PKG 파라마터 세팅 - 공통(1/4)
                	IbKxStoragefeeExpenseMMReqDto procedureDto = new IbKxStoragefeeExpenseMMReqDto();
                    ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
                    procedureDto.setAvc_DCCODE(reqDto.getAvc_DCCODE());
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
                    String[] keyList = new String[] {"SERIALKEY","ISSUE_ID"};
                    Object[] valueList = new Object[]  {dto.getSerialKey(),dto.getIssueId()};
                   
                    procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    int rv = cmCommonService.saveProcedure(procedureDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)procedureDto.getResultCode();
                    resultMessage = (String)procedureDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if(!resultCode.equals("0")){
                        log.error("▶비용기표(1000센터) -> 비용기표(1000센터) 확정취소 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }                 
                }
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    /**
     * @throws RemoteException 
     * @description : 비용기표(1000) Posting 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.01.12       생성
     */
    public String posting(IbKxStoragefeeExpenseMMReqDto paramDto) throws RemoteException {     
    	IbKxStoragefeeExpenseMMReqDto reqDto = ModelMapperUtil.map(paramDto, IbKxStoragefeeExpenseMMReqDto.class);
        
        List<IbKxStoragefeeExpenseMMResDto> saveList = reqDto.getSaveList();

        String errMsg = "";
        String ifID = "";
        String ifDocNo = "";
        
        for (IbKxStoragefeeExpenseMMResDto saveDto : saveList) {
            // 문서 상태 검증
        	IbKxStoragefeeExpenseMMResDto apprStatusDto = commonDao.selectOne(SERVICEID_PREFIX + "getApprStatus", saveDto);
            
            if (apprStatusDto == null) {
                errMsg = "상태값 체크 중 에러 발생 : 비용데이터를 찾을 수 없습니다.";
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
            } else {
                ifDocNo = apprStatusDto.getIfDocNo();
                ifID = apprStatusDto.getIfId();           
            }
            
            if (!"".equals(errMsg)) {
                log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                throw new UserHandleException(""+ errMsg);
            }
            

            
            if ("SCM0470".equals(ifID)) {
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
            
            if("SCM0470".equals(ifID))  {
                IfIbStockskuHDto ifDto = new IfIbStockskuHDto();
                ifDto.setSerialkey(String.valueOf(saveDto.getSerialKey()));
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
//                List<IfIbStockskuBillDto> expenseBillLst = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_BILL", selDto); 
//                List<IfIbStockskuFileDto> expenseFileLst = commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_FILE", selDto); 
    
                if (expenseDetailLst == null || expenseDetailLst.size()== 0) {
                    errMsg =  "I/F 전송시 에러 발생 : 데이터를 찾을 수 없습니다.";            
                    log.error("▶비용기표 -> Posting 저장 오류 발생 / " + errMsg);
                    throw new UserHandleException(""+ errMsg);                
                }
                
                DT_SCM0470_SCMIF_IB_STOCKSKU_H[] stocksku_h_list = new DT_SCM0470_SCMIF_IB_STOCKSKU_H[expenseHeaderList.size()];
                DT_SCM0470_SCMIF_IB_STOCKSKU_D[] stocksku_d_list = new DT_SCM0470_SCMIF_IB_STOCKSKU_D[expenseDetailLst.size()];
//                DT_SCM0470_SCMIF_IB_STOCKSKU_BILL[] stocksku_bill_list = null;
//                DT_SCM0470_SCMIF_IB_STOCKSKU_FILE[] stocksku_file_list = new DT_SCM0470_SCMIF_IB_STOCKSKU_FILE[expenseFileLst.size()];
                
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
                    stocksku_d.setZTEXT1    (expenseDetailLst.get(j).getZtext1());
                   
                    stocksku_d.setZTEXT2    (expenseDetailLst.get(j).getZtext2());
                    stocksku_d.setZTEXT3    (expenseDetailLst.get(j).getZtext3());
                    stocksku_d.setZTEXT4    (expenseDetailLst.get(j).getZtext4());
                    stocksku_d.setZTEXT5    (expenseDetailLst.get(j).getZtext5());
                    
                    stocksku_d_list[j] = stocksku_d;
                }
                
                 
                
                fiData.setIF_IB_STOCKSKU_H(stocksku_h_list);
                fiData.setIF_IB_STOCKSKU_D(stocksku_d_list);
    
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
                    
                    entity.setSerialkey(String.valueOf(saveDto.getSerialKey()));
                    
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
     * @description : 비용기표(1000) Posting 취소
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.01.12       생성
     */
    public String postingCancel(IbKxStoragefeeExpenseMMReqDto paramDto) throws RemoteException {  
    	IbKxStoragefeeExpenseMMReqDto reqDto = ModelMapperUtil.map(paramDto, IbKxStoragefeeExpenseMMReqDto.class);
        
        List<IbKxStoragefeeExpenseMMResDto> saveList = reqDto.getSaveList();

        String errMsg = "";
        String ifID = "";
        
        // 마감 상태 체크 
//        for (IbExpenseDoucmentDetailPopupResDto saveDto : saveList) {
//            String closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getExdcClose", saveDto);
//            if ("Y".equals(closeyn)) {
//                errMsg =  "정산마감이 되어 Posting 취소 불가합니다";
//                log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
//                throw new UserHandleException(""+ errMsg);
//            }
//            
//            closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getIbClose", saveDto);
//            if ("Y".equals(closeyn)) {
//                errMsg =  "정산마감이 되어 Posting 취소 불가합니다."; 
//                log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
//                throw new UserHandleException(""+ errMsg);
//            }
//        }
        
        for (IbKxStoragefeeExpenseMMResDto saveDto : saveList) {
        	IbKxStoragefeeExpenseMMResDto apprStatusDto = commonDao.selectOne(SERVICEID_PREFIX + "getApprStatus", saveDto);
            
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
                
//            String closeyn =  commonDao.selectOne(SERVICEID_PREFIX + "getExdcClose", saveDto);
//            
//            if (closeyn.isEmpty()) {
//                errMsg = "보관료 마감 체크중 에러 발생 : 데이터를 찾을 수 없습니다.";
//                log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
//                throw new UserHandleException(""+ errMsg);
//            }
//            
//            if ("Y".equals(closeyn)) {
//                errMsg = "정산마감이 되어 Posting 취소 불가능 합니다.";
//                log.error("▶비용기표 -> Posting 취소 오류 발생 / " + errMsg);
//                throw new UserHandleException(""+ errMsg);
//            }

            ifID = apprStatusDto.getIfId();

            IfIbStocketcHDto ifDto = new IfIbStocketcHDto();
            ifDto.setSerialkey(String.valueOf(saveDto.getSerialKey()));
            ifDto.setIfType("POSTING_CANCEL");
            
            List<IfIbStocketcHDto> expenseHeaderList = null;
           //List<IfIbStockskuHDto> expenseUHeaderList = null;
            if ("SCM0470".equals(ifID)) {
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
                    
                    if("SCM0470".equals(ifID)) {
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
}
