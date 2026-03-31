package cjfw.wms.ib.service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.edms.EdmsFileDownloader;
import cjfw.core.edms.EdmsFileUploader;
import cjfw.core.exception.UserHandleException;
import cjfw.core.file.FileDownloader;
import cjfw.core.file.FileUploader;
import cjfw.core.file.FileUploaderEdms;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.ib.dto.IbApprovalListReqDto;
import cjfw.wms.ib.dto.IbApprovalListResDto;
import cjfw.wms.ib.dto.IbExpenseDoucmentHeaderPopupResDto;
import cjfw.wms.ib.dto.IfIbStocketcDDto;
import cjfw.wms.ib.dto.IfIbStocketcFileDto;
import cjfw.wms.ib.dto.IfIbStocketcHDto;
import cjfw.wms.ib.dto.IfIbStockskuBillDto;
import cjfw.wms.ib.dto.IfIbStockskuDDto;
import cjfw.wms.ib.dto.IfIbStockskuFileDto;
import cjfw.wms.ib.dto.IfIbStockskuHDto;
import cjfw.wms.ib.entity.IbApprovalListEntity;
import cjfw.wms.ib.entity.IbExpenseResultEntity;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * 
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.25 
 * @description : 외부창고 결재내역 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IbApprovalListService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "ibApprovalListService.";		
	private transient static final String SERVICEID_EXPENSE_PREFIX = "ibExpenseService.";
	private transient static final String PAKAGE_NAME = "SPIB_EXPENSE";    
	private transient static final String PAKAGE_DOC_NAME = "SPST_EXDCSTORAGE";	
    private transient static final String COMMAND_APPR_NAME = "INSERT_IB_APPROVAL_MULTI";      
    //private transient static final String TEMPTABLETYPE = "ST"; 

	private final CommonDao commonDao;
	private final CmCommonService cmCommonService;
	private final UserContext userContext;
	
	private final FileUploader fileUploader;
    private final FileDownloader fileDownloader;    
    private final EdmsFileUploader edmsFileUploader;    
    private final EdmsFileDownloader edmsFileDownloader;    
    private final FileUploaderEdms fileUploaderEdms;

	/**
	 * @description : 외부창고 비용결재 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE          AUTHOR                  MAJOR_ISSUE
	 * ----------------------------------------------------------- 
	 * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<IbApprovalListResDto> getMasterList(IbApprovalListReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterlist", reqDto);
	}
	
	   /**
     * @description : 외부창고 비용결재 라인 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<IbApprovalListResDto> getApprovalLine(IbApprovalListReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getApprovalLine", reqDto);
    }
	
	/**
     * @description : 외부창고 비용결재 상신취소
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveCancelMasterList(IbApprovalListReqDto paramDto)  {
        IbApprovalListReqDto reqDto = ModelMapperUtil.map(paramDto, IbApprovalListReqDto.class);
        
        List<IbApprovalListResDto> saveList = reqDto.getSaveList(); // 저장리스트
        
        if (null != saveList) {
            for (IbApprovalListResDto dto : saveList) {
                String approvalStatus = commonDao.selectOne(SERVICEID_PREFIX + "getApprovalStatus", dto); 
                
                // 결재최종상태(0:기안, 1:결재중, 2:완결,3:반려,4:상신취소,5:전결,6:후결,7:삭제 8:결재대기)
                // 기안상태가 아닐 경우 오류처리
                if (!"0".equals(approvalStatus)) {
                    throw new UserHandleException("상신취소가 불가능한 상태입니다.");
                }
                
                IbApprovalListEntity entity = ModelMapperUtil.map(dto, userContext, IbApprovalListEntity.class);
                
                // 결재마스터 상신취소 처리
                commonDao.update(SERVICEID_PREFIX + "updateApprovalMasterCancel", entity); 
                
                // 결재라인 상신취소 처리
                commonDao.update(SERVICEID_PREFIX + "updateApprovalLindCancel", entity); 
            }            
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 외부창고 결재내역 반려
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveRejectMasterList(IbApprovalListReqDto paramDto) {
        
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @throws RemoteException 
     * @description : 외부창고 비용결재 결재/반려
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.25    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveApprovelMasterList(IbApprovalListReqDto paramDto) throws RemoteException {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String errMsg = "";
        
        IbApprovalListReqDto reqDto = ModelMapperUtil.map(paramDto, IbApprovalListReqDto.class);
        
        List<IbApprovalListResDto> saveList = reqDto.getSaveList(); // 저장리스트
        
        if (null != saveList) {
            for (IbApprovalListResDto dto : saveList) {
                /*1. 패키지 실행 */
                // PKG 파라마터 세팅 - 공통(1/4)
                ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);
                dto.setAvc_DCCODE(reqDto.getAvc_DCCODE());
                
                // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                String[] keyList = {"SERIALKEY", "EXPENSE_SN", "APPROVAL_TYPE", "COMMIT"};
                Object[] valueList = {dto.getSerialkey(), dto.getExpenseSn(), reqDto.getApprovalType(), "FALSE"}; 
                dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                
                int rv = cmCommonService.saveProcedure(dto); 
                log.info("rv->{}",rv);
                
                // 프로시저 OUT Parameter(3/4)
                resultCode    = (String)dto.getResultCode();
                resultMessage = (String)dto.getResultMessage();
                
                log.info("resultCode->{}",resultCode);
                log.info("resultMessage->{}",resultMessage);
                
                // 프로시저 Exception 처리(4/4)
                if (!resultCode.equals("0")){
                    log.error("▶외부창고 비용결재 -> 저장 오류 발생 ");
                    throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"비용결 저장"}) + resultMessage );
                }
                
                
                /*2. 결재 후 Posting */ 
                String serialKey = String.valueOf(dto.getExpenseSn());
                
                if (serialKey.isEmpty()){
                    throw new UserHandleException("" + "SERIALKEY 값은 필수 입니다." );
                }
                
                IbExpenseDoucmentHeaderPopupResDto apprStatusDto = commonDao.selectOne(SERVICEID_EXPENSE_PREFIX + "getApprStatus", dto);
                
                if (apprStatusDto == null) {
                    throw new UserHandleException("" + "비용기표 내부결재 상태를 확인할 수 없습니다." );
                }
                
                String ifID = apprStatusDto.getIfId();       
                
                IfIbStocketcHDto ifDto = new IfIbStocketcHDto();
                ifDto.setSerialkey(String.valueOf(dto.getSerialkey()));
                ifDto.setIfType("POSTING");      

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");

                if ("SCM0480".equals(ifID)) {
                    List<IfIbStocketcHDto> expenseHeaderList = commonDao.selectList(SERVICEID_EXPENSE_PREFIX + "getIF_IB_STOCKETC_H", ifDto);
                    
                    if (expenseHeaderList == null || expenseHeaderList.size() == 0) {
                        throw new UserHandleException("" + "인터페이스 정보를 확인할 수 없습니다." );
                    }
                    
                    ifDto.setZinvoice(expenseHeaderList.get(0).getZinvoice());
                    
                    List<IfIbStocketcDDto> expenseDetailList = commonDao.selectList(SERVICEID_EXPENSE_PREFIX + "getIF_IB_STOCKETC_D", ifDto);
                    if (expenseDetailList == null || expenseDetailList.size() == 0) {
                        throw new UserHandleException("" + "인터페이스 정보를 확인할 수 없습니다." );
                    }
                    
                    List<IfIbStocketcFileDto> expenseFileList = commonDao.selectList(SERVICEID_EXPENSE_PREFIX + "getIF_IB_STOCKETC_FILE", ifDto);
                    
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
                        stocketc_h.setXUSER    (reqDto.getGUserId());
                        
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
                    responses = proxy.sI_SCM0480_SCM_SO(fiData);
                    
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
                    List<IfIbStockskuHDto> expenseHeaderList = commonDao.selectList(SERVICEID_EXPENSE_PREFIX + "getIF_IB_STOCKSKU_H", ifDto);
                    
                    if (expenseHeaderList == null || expenseHeaderList.size() == 0) {
                        // 예외로 던지는 것이 맞는 경우인지 확인필요하다 
                        errMsg =  "데이터를 찾을 수 없습니다.";            
                        log.error("▶비용결재 -> Posting 저장 오류 발생 / " + errMsg);
                        throw new UserHandleException(""+ errMsg);
                    }

                    IfIbStockskuHDto selDto = new IfIbStockskuHDto();
                    selDto.setZinvoice(expenseHeaderList.get(0).getZinvoice());            
                    List<IfIbStockskuDDto> expenseDetailLst = commonDao.selectList(SERVICEID_EXPENSE_PREFIX + "getIF_IB_STOCKSKU_D", selDto); 
                    List<IfIbStockskuBillDto> expenseBillLst = commonDao.selectList(SERVICEID_EXPENSE_PREFIX + "getIF_IB_STOCKSKU_BILL", selDto); 
                    List<IfIbStockskuFileDto> expenseFileLst = commonDao.selectList(SERVICEID_EXPENSE_PREFIX + "getIF_IB_STOCKSKU_FILE", selDto); 
        
                    if (expenseDetailLst == null || expenseDetailLst.size()== 0) {
                        // 예외로 던지는 것이 맞는 경우인지 확인필요하다
                        errMsg =  "데이터를 찾을 수 없습니다.";            
                        log.error("▶비용결재 -> Posting 저장 오류 발생 / " + errMsg);
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
                        stocksku_h.setXUSER    (reqDto.getGUserId());
                        
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
                    responses = proxy.sI_SCM0470_SCM_SO(fiData);
                    
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
                        
                        commonDao.update(SERVICEID_PREFIX + "updateIfResultIbExpense", entity);
                    }   
                }
                /*END-2. 결재 후 Posting */
            }
            //END-for (IbApprovalListResDto dto : saveList) {
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
	
}
