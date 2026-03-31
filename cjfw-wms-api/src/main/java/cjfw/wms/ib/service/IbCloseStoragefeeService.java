package cjfw.wms.ib.service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.sap.JCoUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.ib.dto.IbCloseStockSkuBillPopupReqDto;
import cjfw.wms.ib.dto.IbCloseStockSkuBillPopupResDto;
import cjfw.wms.ib.dto.IbCloseStoragefeeReqDto;
import cjfw.wms.ib.dto.IbCloseStoragefeeResDto;
import cjfw.wms.ib.dto.IbCloseStoragefeeStatusReqDto;
import cjfw.wms.ib.dto.IbCloseStoragefeeStatusResDto;
import cjfw.wms.ib.dto.IfIbStockSkuBillResDto;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCMIF_ST_STOCKAMT;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET;
import cjfw.wms.webservice.elecAmt.SI_MM0090_SCM_SOProxy;
import cjfw.wms.webservice.exDCClose.DT_SCM0530_SCM;
import cjfw.wms.webservice.exDCClose.DT_SCM0530_SCMIF_IB_STOCKSKU_H;
import cjfw.wms.webservice.exDCClose.DT_SCM0530_SCM_response;
import cjfw.wms.webservice.exDCClose.SI_SCM0530_SCM_SOProxy;
import cjfw.wms.webservice.ficlose.DT_SCM0080_SCM;
import cjfw.wms.webservice.ficlose.DT_SCM0080_SCMT_PC;
import cjfw.wms.webservice.ficlose.DT_SCM0080_SCM_response;
import cjfw.wms.webservice.ficlose.SI_SCM0080_SCM_SOProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.29 
 * @description : 보관료마감처리 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IbCloseStoragefeeService {
	
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "ibCloseStoragefeeService.";
    private transient static final String PAKAGE_NAME = "SPIB_EXPENSE";
    private transient static final String PAKAGE_NAME_WMS = "SPST_EXDCSTORAGE";
    

	 /**
	  * @description : 보관료마감처리 현황 조회 기능을 구현한 Method 
	  * @issues : 
	  * ----------------------------------------------------------- 
	  * DATE AUTHOR MAJOR_ISSUE 
	  * ----------------------------------------------------------- 
	  * 2025.08.29 ParkJinWoo 생성
	  */
	public List<IbCloseStoragefeeStatusResDto> getDataStatusHeaderlist(IbCloseStoragefeeStatusReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataStatusHeaderlist", dto);
	}
	
	/**
	 * @description :  보관료마감처리 현황 마감 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 ParkJinWoo 생성
	 */
	public List<IbCloseStoragefeeResDto> getDataHeaderlist(IbCloseStoragefeeReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist", dto);
	}
	
	/**
	 * @description : 보관료마감처리 마감 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 ParkJinWoo 생성
	 */
	public String saveClose(IbCloseStoragefeeReqDto paramDto) throws RemoteException{
        IbCloseStoragefeeReqDto reqDto = ModelMapperUtil.map(paramDto, IbCloseStoragefeeReqDto.class);
        List<IbCloseStoragefeeResDto> saveList = reqDto.getSaveList();
        String errMsg = "";
        String ifId = "";
        String ifDocNo = "";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
        String resultCode = "";
        String resultMessage = "";
        String returnMessage = "";
		 
        try {
            paramDto.setPlant(paramDto.getDcCode());
        	paramDto.setClosedt(saveList.get(0).getClosedt());
        	List<IbCloseStoragefeeResDto> dsIbClose = commonDao.selectList(SERVICEID_PREFIX + "getCheckIbClose", paramDto);
        	if (dsIbClose == null || dsIbClose.size() == 0) {
				throw new UserHandleException("조회된 데이터가 없습니다."); // 조회된 데이터가 없습니다.
			}
        	if (dsIbClose.get(0).getCnt() > 0) {
				throw new UserHandleException("이미 마감처리된 데이터가 있습니다."); // 이미 마감처리된 데이터가 있습니다.
			}
			
        	//재무마감 저장
        	commonDao.insert(SERVICEID_PREFIX + "insertIbClose", paramDto);
        	
        	//SAP 마감정보 인터페이스 
        	SI_SCM0080_SCM_SOProxy proxy = new SI_SCM0080_SCM_SOProxy();
			DT_SCM0080_SCM fiData = null;
			DT_SCM0080_SCMT_PC sndData = null;
			
			fiData = new DT_SCM0080_SCM();
			//fiData.setXSYS("SCM");
			fiData.setXDATS(dateFormat.format(calendar.getTime()));
			fiData.setXTIMS(timeFormat.format(calendar.getTime()));
			//fiData.setXROWS("1");
			
			sndData = new DT_SCM0080_SCMT_PC();
	
			sndData.setSTORERKEY(paramDto.getGStorerkey());
			sndData.setDOCDT(paramDto.getClosedt());
			sndData.setCLOSECD("TC003-0002");
			sndData.setEDITWHO(paramDto.getGUserId());
			sndData.setCLOSESTATUS("X");
			
			fiData.setT_PC(sndData);
			
			DT_SCM0080_SCM_response response = null;
			response = proxy.si_scm0080_scm_so(fiData);
			
			//응답에 대한 결과 처리
			if (response != null && "E".equals(response.getT_RETURN().getIF_FLAG())) {
				throw new UserHandleException("인터페이스 처리중 에러발생["+response.getT_RETURN().getIF_MEMO()+"]");
			}
			
			//기초재고생성 대상 창고 목록 조회
			List<IbCloseStoragefeeStatusResDto> baseList = commonDao.selectList(SERVICEID_PREFIX + "getDataStatusHeaderlist", paramDto);
		       
			//창고별 기초재고생성 
			for(IbCloseStoragefeeStatusResDto dto : baseList) {
                IbCloseStoragefeeReqDto procedureDto = new IbCloseStoragefeeReqDto();
                ProcedureParametersFactory.initParamDto(dto, procedureDto, PAKAGE_NAME_WMS);
                
                procedureDto.setAvc_DCCODE(dto.getDcCode());  
                procedureDto.setAvc_COMMAND("INS_MONTHLY_BASE_G");
                
                // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
                String[] keyList = {"YYYYMM","STORAGELOCATION","ORGANIZE"};
                Object[] valueList = {dto.getYyyymm(),dto.getStorageLocation(),dto.getOrganize()};
               
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
                    log.error("▶기초재고생성 오류 -> 기초재고생성 패키지 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다
                }
            }
        } catch(UserHandleException e) {
            e.printStackTrace();
            log.error("Exception", e);
            throw new UserHandleException(e.getErrorMessage());
        } catch(Exception e) {
            e.printStackTrace();
            log.error("Exception", e);
            throw new UserHandleException(e);
        }
	
        return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description :  보관료마감처리 마감/취소 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 ParkJinWoo 생성
	 */
	public String saveCloseStoragefee(IbCloseStoragefeeReqDto paramDto) throws RemoteException{
		Map paramMap = null;
		String strParam = null;
		String strValue = null;
		
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String returnMessage  = "";
		
        IbCloseStoragefeeReqDto reqDto = ModelMapperUtil.map(paramDto, IbCloseStoragefeeReqDto.class);
        List<IbCloseStoragefeeResDto> saveList = reqDto.getSaveList();
            
        for (IbCloseStoragefeeResDto dto : saveList) {
            dto.setPlant(dto.getDcCode());
            dto.setClosedt(dto.getYyyymm());
            
            List<IbCloseStoragefeeResDto> dsIbClose = commonDao.selectList(SERVICEID_PREFIX + "getCheckIbClose", dto);
            
            if (dsIbClose == null || dsIbClose.size() == 0) {
                throw new UserHandleException("재무마감 체크중 에러발생");
            }
            
            if (dsIbClose.get(0).getCnt()>0) {
                throw new UserHandleException("이미 해당월로 마감 처리 되었습니다.");
            }
        
            /* RFC 호출 */
            /* RFC 함수 선언 */
            JCoUtil jco = new JCoUtil();
        
            JCoDestination destination = jco.getDestination();
            
            JCoFunction function = jco.getFunction("ZMM_CHECK_PERIOD_CLOSING");
            
            JCoParameterList params = null;
            JCoParameterList returnData = null;
        
            if (function == null) {
                throw new UserHandleException("ZMM_CHECK_PERIOD_CLOSING not found in SAP.");
            }
        
            params = function.getImportParameterList();
            
            if (log.isDebugEnabled()) {
                log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++ SAP 시작");
            }
            
            /* RFC 함수 Parameter 입력 */
            params.setValue("I_BUDAT" , dto.getYyyymm());     // 전기일
            params.setValue("I_CODE"  , "TC003-0002"                          );     // 마감코드
        
            if (log.isDebugEnabled()) {
                log.info(" I_BUDAT  : " + params.getValue("I_BUDAT" ));
                log.info(" I_CODE   : " + params.getValue("I_CODE" ));
            }
		
            // 통신 시작
            try {
                function.execute(destination);
            } catch(JCoException e) {
                e.printStackTrace(); // NO DATA 제외
                log.error("JCoException",e);
                throw new UserHandleException( e.getMessage());
            } catch(Exception e){
                log.error("Exception",e);
                throw new UserHandleException(e.getMessage());
            }
            
            returnData = function.getExportParameterList();
            
            if (!"S".equals((String) returnData.getValue("E_XSTAT"))) {
                throw new UserHandleException((String) returnData.getValue("E_XMSGS"));
            }
			
            //프로시저 호출
            dto.setPackagename(PAKAGE_NAME);
            IbCloseStoragefeeReqDto procedureDto = new IbCloseStoragefeeReqDto();
            ProcedureParametersFactory.initParamDto(dto, procedureDto, PAKAGE_NAME);
            if(dto.getCloseYn().equals("Y")) {
                procedureDto.setAvc_COMMAND("EXDCCLOSE_CANCEL");
            } else if (dto.getCloseYn().equals("N")){
                procedureDto.setAvc_COMMAND("EXDCCLOSE_CONFIRM");
            }
            
            procedureDto.setAvc_DCCODE(dto.getDcCode());  
            
            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
            String[] keyList = {"YYYYMM","STORAGELOCATION","COMMIT"};
            Object[] valueList = {dto.getYyyymm(),dto.getStorageLocation(),"FALSE"};
	         
            procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            int rv = cmCommonService.saveProcedure(procedureDto);
            log.info("rv->{}",rv);
            
            // SPID
            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)procedureDto.getResultCode();
            resultMessage = (String)procedureDto.getResultMessage();
            returnMessage = (String)procedureDto.getReturnMessage();
            
            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);
            log.info("returnMessage->{}",returnMessage);
        
            // 프로시저 Exception 처리(4/4)
            if (!resultCode.equals("0")){
                log.error("▶정산 오류 -> 정산 패키지 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            } 
            
            try {
                List<IfIbStockSkuBillResDto> dsExpense_BILL =commonDao.selectList(SERVICEID_PREFIX + "getIF_IB_STOCKSKU_BILL", dto); ;
                	
                if (dsExpense_BILL == null || dsExpense_BILL.size() == 0) {
                    throw new UserHandleException("I/F 전송시 에러 발생 : 데이터를 찾을 수 없습니다.");
                }
                
                SI_SCM0530_SCM_SOProxy proxy = null;
                
                DT_SCM0530_SCM fiData = null;
                
                DT_SCM0530_SCMIF_IB_STOCKSKU_H[] stocksku_bill_list = null;
                proxy = new SI_SCM0530_SCM_SOProxy();
                fiData = new DT_SCM0530_SCM();
                stocksku_bill_list = new DT_SCM0530_SCMIF_IB_STOCKSKU_H[dsExpense_BILL.size()];
    
                for (int j = 0; j<dsExpense_BILL.size();j++) {
                    DT_SCM0530_SCMIF_IB_STOCKSKU_H stocksku_bill = new DT_SCM0530_SCMIF_IB_STOCKSKU_H();
                    
                    stocksku_bill.setZMONTH     (dsExpense_BILL.get(j).getZmonth());
                    stocksku_bill.setSEQ        (dsExpense_BILL.get(j).getSeq());
                    stocksku_bill.setZCAT       (dsExpense_BILL.get(j).getZcat());
                    stocksku_bill.setLGORT      (dsExpense_BILL.get(j).getLgort());
                    stocksku_bill.setWERKS      (dsExpense_BILL.get(j).getWerks());
                    stocksku_bill.setMATNR      (dsExpense_BILL.get(j).getMatnr());
                    stocksku_bill.setFRBNR      (dsExpense_BILL.get(j).getFrbnr());
                    stocksku_bill.setHISTNO     (dsExpense_BILL.get(j).getHistno());
                    stocksku_bill.setZEBELN     (dsExpense_BILL.get(j).getZebeln());
                    stocksku_bill.setZEBELP     (dsExpense_BILL.get(j).getZebelp());
                    stocksku_bill.setBUDAT      (dsExpense_BILL.get(j).getBudat());
                    stocksku_bill.setZWRBTR_OUT (dsExpense_BILL.get(j).getZwrbtrOut());
                    stocksku_bill.setZWRBTR_IN  (dsExpense_BILL.get(j).getZwrbtrIn());
                    stocksku_bill.setWRBTR      (dsExpense_BILL.get(j).getWrbtr());
                    stocksku_bill.setMENGE      (dsExpense_BILL.get(j).getMenge());
                    stocksku_bill.setBSTME      (dsExpense_BILL.get(j).getBstme());
                    stocksku_bill.setZTEXT1     (dsExpense_BILL.get(j).getZtext1());
                    stocksku_bill.setZTEXT2     (dsExpense_BILL.get(j).getZtext2());
                    stocksku_bill.setZTEXT3     (dsExpense_BILL.get(j).getZtext3());
                    stocksku_bill.setZTEXT4     (dsExpense_BILL.get(j).getZtext4());
                    stocksku_bill.setZTEXT5     (dsExpense_BILL.get(j).getZtext5());
                    stocksku_bill_list[j] = stocksku_bill;
                }
                
                fiData.setIF_IB_STOCKSKU_H(stocksku_bill_list);

                DT_SCM0530_SCM_response response = null;
                response = proxy.sI_SCM0530_SCM_SO(fiData);
    			
                if (response != null) {
                    if(!"S".equals(response.getXSTAT())) {
                        log.error("DT_SCM0530_SCM_response response");
                        log.error(" response -> {}",response);
                        log.error(" response.getXSTAT() -> {}",response.getXSTAT());
                        log.error(" response.getXMSGS() -> {}",response.getXMSGS());
                        throw new UserHandleException(response.getXMSGS());
                    }
                }
            } catch(UserHandleException e) {
                log.error("Exception", e);
                throw new UserHandleException(e.getErrorMessage());
            } catch(Exception e) {
                log.error("Exception", e);
                throw new UserHandleException(e.getMessage());
            }
           
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
	
	/**
	 * @description : 보관료마감처리 강제마감 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 ParkJinWoo 생성
	 */
	public String saveCloseStoragefeeWMS(IbCloseStoragefeeReqDto paramDto) {
        String resultCode = "";
        String resultMessage = "";
        String returnMessage = "";
        IbCloseStoragefeeReqDto reqDto = ModelMapperUtil.map(paramDto, IbCloseStoragefeeReqDto.class);
        List<IbCloseStoragefeeResDto> saveList = reqDto.getSaveList();

        for (IbCloseStoragefeeResDto dto : saveList) {
            dto.setPlant(paramDto.getGDccode());
            dto.setClosedt(paramDto.getCloseMonth());
            
            // 마감 여부 확인 
            List<IbCloseStoragefeeResDto> dsIbClose = commonDao.selectList(SERVICEID_PREFIX + "getCheckIbCloseWms", dto);
            
            if (dsIbClose == null || dsIbClose.size() == 0) {
                throw new UserHandleException("창고마감 완료여부를 확인할 수 없습니다.");
            } else if (dsIbClose.get(0).getCnt() > 0) {
                throw new UserHandleException("이미 해당월로 마감 처리 되었습니다.");
            }
            
            // 비용기표 완료여부 확인
            List<IbCloseStoragefeeResDto> dsIbExpensComplted = commonDao.selectList(SERVICEID_PREFIX + "getIbexpenseComplete", dto);
            
            if (dsIbExpensComplted == null || dsIbExpensComplted.size() == 0) {
                throw new UserHandleException("비용기표 완료여부를 확인할 수 없습니다.");
            } else if (dsIbExpensComplted.get(0).getCnt() > 0) {
                throw new UserHandleException("비용기표가 완료되지 않은 건이 있습니다.");
            }
        
            commonDao.update(SERVICEID_PREFIX +"updateIbExdcClose", dto);
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 보관료마감처리 마감내역 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 ParkJinWoo 생성
	 */
	public List<IbCloseStockSkuBillPopupResDto> getDataRead(IbCloseStockSkuBillPopupReqDto dto) {
		Integer paramListCnt = 500;
		Integer paramRow = 0;
		Integer updatedsRow = 0;
		Integer findRow = 0;
		String  searchKey = "";
		String  resultKey = "";
		String  dccode = "";
		
		IbCloseStockSkuBillPopupReqDto reqDto = ModelMapperUtil.map(dto, IbCloseStockSkuBillPopupReqDto.class);
		List<IbCloseStockSkuBillPopupResDto> ds_amt = commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderCloselist", reqDto);
		
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
			
			DT_MM0090_SCMIF_ST_STOCKAMT[] paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
			DT_MM0090_SCMIF_ST_STOCKAMT param = new DT_MM0090_SCMIF_ST_STOCKAMT();
			
			if ( ds_amt.size() > paramListCnt ) {
				paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
			} else {
				paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ ds_amt.size() ];
			}
			
			for (int dsRow = 0; dsRow < ds_amt.size(); dsRow++ ) {
				param = new DT_MM0090_SCMIF_ST_STOCKAMT();
				
				dccode = ds_amt.get(dsRow).getDcCode();			
				param.setPLANT( ds_amt.get(dsRow).getDcCode());
				param.setSTORAGELOC (ds_amt.get(dsRow).getStorageLoc());
				param.setSKU        (ds_amt.get(dsRow).getSku());
				param.setQTY        (ds_amt.get(dsRow).getOrderQty());
				param.setUOM        (ds_amt.get(dsRow).getUom());
				param.setCONVSERIALNO(ds_amt.get(dsRow).getConvSerialNo());
				param.setSERIALNO   (ds_amt.get(dsRow).getSerialNo());
				param.setPOLINE     (ds_amt.get(dsRow).getPoLine());
				param.setSLIPDT     (ds_amt.get(dsRow).getSlipDt());
				param.setSLIPNO     (ds_amt.get(dsRow).getSlipNo());
				param.setSLIPLINE("00000");
				
				paramList[paramRow ] = param;
				paramRow++;

				if ((paramRow.equals( paramListCnt )  ) || String.valueOf(dsRow + 1).equals( String.valueOf(ds_amt.size())))  {
					reqData = new DT_MM0090_SCM();
					reqData.setXSYS("WMS");
					reqData.setXDATS(dateFormat.format(calendar.getTime()));
					reqData.setXTIMS(timeFormat.format(calendar.getTime()));
					reqData.setXROWS(String.valueOf(paramRow));
					
					reqData.setIF_ST_STOCKAMT(paramList);
					DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] response = proxy.si_mm0090_scm_so(reqData);

					for (int i = 0; i < paramRow; i++) {

						if (updatedsRow >= ds_amt.size()) { 
						    break; 
						}
						    
						searchKey =   ds_amt.get(updatedsRow).getDcCode().toString()
								   + ds_amt.get(updatedsRow).getSlipDt().toString()
		                           + ds_amt.get(updatedsRow).getSlipNo().toString()
		                           + ds_amt.get(updatedsRow).getSku().toString();
						findRow = -1;
						
						for (int ii = 0; ii < response.length; ii++) {
							resultKey =  response[ii].getPLANT()
									   + response[ii].getSLIPDT()
									   + response[ii].getSLIPNO()
									   + response[ii].getSKU();		
							if (searchKey.equals(resultKey)) {
								findRow = ii;
								break;
							}
						}
						
						if (findRow >= 0) {
							String stat = response[findRow].getXSTAT();	
							ds_amt.get(updatedsRow).setChkAmt(response[findRow].getXSTAT());
							if ("E".equals(stat)) {
								ds_amt.get(updatedsRow).setStockAmtMsg(response[findRow].getXMSGS());
							} else {
								ds_amt.get(updatedsRow).setStockAmt(response[findRow].getSTOCKAMT());
								ds_amt.get(updatedsRow).setPrice(response[findRow].getPURCHASEPRICE());
								ds_amt.get(updatedsRow).setStockQty(response[findRow].getSTOCKQTY());
							}
						}
						updatedsRow++;
					}
					
					if ( ds_amt.size() - dsRow > paramListCnt ) {
						paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[paramListCnt];
					} else {
						paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ds_amt.size() - dsRow];
					}
					
					paramRow = 0;
				}
			}			
		} catch(Exception e) {
			log.error("재고금액 처리중 에러발생", e);
		}
		return ds_amt;
	}
	
}
