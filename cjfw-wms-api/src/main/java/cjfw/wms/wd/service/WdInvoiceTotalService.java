package cjfw.wms.wd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.sap.JCoUtil;
//import cjfw.core.utility.DamoScpDbUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.StringUtil;
import cjfw.wms.ms.dto.MsCreditInfoDto;
import cjfw.wms.ms.service.MsCreditInfoTotalService;
import cjfw.wms.wd.dto.WdInvoiceTotalDetailResDto;
import cjfw.wms.wd.dto.WdInvoiceTotalPrintResDto;
import cjfw.wms.wd.dto.WdInvoiceTotalReqDto;
import cjfw.wms.wd.dto.WdInvoiceTotalResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 성상수 (kduimux@cj.net) 
 * @date : 2025.05.16 
 * @description : 통합납품서출력 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.16 성상수 (kduimux@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdInvoiceTotalService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdInvoiceTotalService.";
	private transient static final String SERVICEID_PREFIX2 = "wdInvoiceService.";
	/** 프로시져명 - 패키지호출 */
	private transient static final String PAKAGE_NAME = "SPWD_INVOICE";	
	/** 프로세스타임 */
	private transient static final String PROCESSTYPE = "WD_INVOICECUSTTOTAL";	
	/** 프로세스타임-거래처별 */
	private transient static final String PROCESSTYPE2 = "WD_INVOICECUST";	
	/** 임시테이블 타입 */
	private transient static final String TEMPTABLETYPE = "WD";	
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;
	/** 공통.service */
	private final CmCommonService cmCommonService;
	/** 기준정보.여신정보.service */
	private final MsCreditInfoTotalService msCreditInfoTotalService;

	/**
	 * @description : 통합납품서출력 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.16 성상수 (kduimux@cj.net) 생성 </pre>
	 */
	public List<WdInvoiceTotalResDto> getMasterList(WdInvoiceTotalReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * @description : 통합납품서출력 상세목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.16 성상수 (kduimux@cj.net) 생성 </pre>
	 */
	public List<WdInvoiceTotalResDto> getDetailList(WdInvoiceTotalReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
	}
	
	
	/**
	 * @description : 통합납품서출력 출력 데이터 생성
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.30 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	public WdInvoiceTotalPrintResDto printMasterList(WdInvoiceTotalReqDto paramDto) throws Exception {
		WdInvoiceTotalPrintResDto resultDto = new WdInvoiceTotalPrintResDto();
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdInvoiceTotalReqDto reqDto = ModelMapperUtil.map(paramDto, WdInvoiceTotalReqDto.class);
		List<WdInvoiceTotalResDto> saveList = reqDto.getSaveList(); // 저장리스트
		
		String invoiceprintkey = reqDto.getInvoiceprintkey();       // 인보이스출력키
		reqDto.setProcesstype(PROCESSTYPE);     // 프로세스타입
		reqDto.setTemptabletype(TEMPTABLETYPE); // 임시테이블타입	
		
		// START.필수입력
		if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getInvoiceprintkey()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"Invoiceprintkey"} ) + resultMessage ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
		if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getDt1())))             throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"dt1"}             ) + resultMessage ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
		if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getDt2())))             throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"dt2"}             ) + resultMessage ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
		// END.필수입력
		
		/*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 

		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		for (int i = 0; i < saveList.size(); i++) { 
			WdInvoiceTotalResDto dto = saveList.get(i);
        	// 임시테이블에 등록(2/3)
        	CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입 
        	
        	// UI.params
        	entity.setPickListNo(invoiceprintkey);      // 피킹리스트번호에 인보이스출력키를 넣는다.	
        	entity.setInvoiceprintkey(invoiceprintkey); // 인보이스출력키
    		
        	// START.필수입력 check - 그리드 변수 등
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getCarno()))           ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"carno"} )  );   // 해당 정보가 없어 처리할 수 없습니다.-{0}
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPriority()))        ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"priority"} )  ); 
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDeliverygroup()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"deliverygroup"} )  );     
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getCustkey()))         ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"custkey"} )  );   
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickListNo()))      ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"pickListNo"} )  );    
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getInvoiceprintkey())) ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"sku"} )  );       
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getMngplcid()))        ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"mngplcid"} )  );       
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getShptoid()))         ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"shptoid"} )  );       
    		// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
            	insertList.clear();
            }
        }
        /*END.Temp Table Insert*/	
        
        
        /*START.PAKAGE 호출*/
		// PKG 파라마터 세팅 - 공통(1/4)
        WdInvoiceTotalReqDto dto = new WdInvoiceTotalReqDto();
		ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
        
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList = { "PROCEDURE"        ,"PROCESSTYPE"     ,"TEMPTABLETYPE"          
						    ,"INVOICEPRINTTYPE" ,"INVOICEPRINTKEY" ,"DELIVERYDT_FROM" ,"DELIVERYDT_TO"
						   };
		Object[] valueList = { PAKAGE_NAME, reqDto.getProcesstype(), reqDto.getTemptabletype()
						      ,reqDto.getInvoiceprinttype() ,reqDto.getInvoiceprintkey() ,reqDto.getDt1(), reqDto.getDt2()
						     };
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		int rv = cmCommonService.saveProcedure(dto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = cjfw.core.utility.StringUtil.nvl(dto.getResultCode());
		resultMessage = cjfw.core.utility.StringUtil.nvl(dto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶출고진행현황 - 상품제외처리 시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"출고진행현황-상품제외"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		 /*END.PAKAGE 호출*/        
		
		/*START.출력정보 조회 및 여신 조회*/
		String[] columns = {"csTel","empphone","driverPhone","toPhone","fromPhone1","fromFax","toPhone1","toFax"};
//		List<WdInvoiceTotalPrintResDto> reportHeaderList = DamoScpDbUtil.damoScpDecB64(commonDao.selectList(SERVICEID_PREFIX + "getHeaderPrintInvoice", reqDto),columns);
		List<WdInvoiceTotalPrintResDto> reportHeaderList = commonDao.selectList(SERVICEID_PREFIX + "getHeaderPrintInvoice", reqDto);
		List<MsCreditInfoDto> creditList = new ArrayList<MsCreditInfoDto>();
		for(WdInvoiceTotalPrintResDto printDto : reportHeaderList) { 
			printDto.setFromVataddress(cjfw.core.utility.StringUtil.nvl(printDto.getFromVataddress1()) + " " + cjfw.core.utility.StringUtil.nvl(printDto.getFromVataddress2()));
			printDto.setToVataddress(cjfw.core.utility.StringUtil.nvl(printDto.getToVataddress1()) + " " + cjfw.core.utility.StringUtil.nvl(printDto.getToVataddress2()));
			printDto.setToAddress(cjfw.core.utility.StringUtil.nvl(printDto.getToAddress1()) + " " + cjfw.core.utility.StringUtil.nvl(printDto.getToAddress2()));
			// 전화번호형식적용0
			printDto.setEmpphone(StringUtil.toTelephoneNumberFormat(printDto.getEmpphone()));
			printDto.setDriverPhone(StringUtil.toTelephoneNumberFormat(printDto.getDriverPhone()));
			printDto.setToPhone(StringUtil.toTelephoneNumberFormat(printDto.getToPhone()));
			
			// invoicetype이 "01,04,12" 이면 여신정보를 만든다.
			String invoiceType = printDto.getInvoicetype();
			String[] deliveryDt = printDto.getDeliverydt().split("-");		

			/**
			 * RFC 호출 START
			*/
			if ("27".equals(invoiceType)) {
				JCoUtil jco = new JCoUtil();
				JCoDestination destination = jco.getDestination();
				JCoFunction function = jco.getFunction("ZFI_GET_DAY_ACCOUNT_RFC");
				JCoParameterList params = null;
				JCoParameterList returnData = null;

				if(function == null) {
					throw new UserHandleException("ZFI_GET_DAY_ACCOUNT_RFC not found in SAP.");
				}

				params = function.getImportParameterList();

				params.setValue("I_BUKRS"  , "FW00");     // 회사코드
				params.setValue("I_KUNNR"  , String.format("%10s", printDto.getToCustkey().replace(' ', '0')));     // 고객번호
				params.setValue("I_FRDAT"  , printDto.getDeliverydt().replace("-", ""));     // FROM 날짜
				params.setValue("I_TODAT"  , printDto.getDeliverydt().replace("-", ""));     // TO 날짜

				// 통신 시작
				try {
					System.out.println("통신 시작");
					function.execute(destination);
					System.out.println("통신 종료");
				} catch(JCoException e) {
					e.printStackTrace(); // NO DATA 제외
					//LOG.error("JCoException",e);
					throw new UserHandleException( e.getMessage());
				}catch(Exception e){
					//LOG.error("Exception",e);
					throw new UserHandleException(e.getMessage());
				}

				returnData = function.getExportParameterList();

				JCoTable detailTable = function.getTableParameterList().getTable("T_OUT");

				if( detailTable.getNumRows() > 0) {
					for (int i = 0; i < detailTable.getNumRows(); i++) {
						detailTable.setRow(i); // i번째 행으로 커서 이동
						printDto.setTotalAmt(detailTable.getString("AMT01"));  // 전잔액
						printDto.setTodayAmount(detailTable.getString("AMT03"));  // 현잔액
						printDto.setTodayAmt(detailTable.getString("AMT04"));  // 당일입금액
					}
				}
			}

			if ("01".equals(invoiceType) || "04".equals(invoiceType) || "12".equals(invoiceType)) {
				MsCreditInfoDto creditDto = new MsCreditInfoDto();
				creditDto.setYear(deliveryDt[0]);
				creditDto.setMonth(deliveryDt[1]);
				creditDto.setDay(deliveryDt[2]);
				
				//creditDto.setDeliverydt(printDto.getDeliverydt().replaceAll("-", ""));
				String deliverydt = printDto.getDeliverydt();
				creditDto.setDeliverydt(deliverydt != null ? deliverydt.replace("-", "") : "");
				
				creditDto.setCustkey(printDto.getToCustkey());
				creditList.add(creditDto);
			}	
		}
		
		// 여신정보 조회
		MsCreditInfoDto creditDto = new MsCreditInfoDto();
		if (creditList.size() > 0) {
			creditDto.setYear(creditList.get(0).getYear());
			creditDto.setMonth(creditList.get(0).getMonth());
			creditDto.setDay(creditList.get(0).getDay());
			creditDto.setCreditList(creditList);
		}		
		msCreditInfoTotalService.saveData(creditDto);	
		/*END.출력정보 조회 및 여신 조회*/
		
		
		/*START.최종 출력물 데이터 조회*/
		resultDto.setReportHeader(reportHeaderList);
		resultDto.setReportDetailList(commonDao.selectList(SERVICEID_PREFIX2 + "getDetailPrintInvoice", reqDto));
		resultDto.setReportCredit(commonDao.selectList(SERVICEID_PREFIX + "getCreditPrintInvoice", reqDto));
		resultDto.setReportDlvCost(commonDao.selectList(SERVICEID_PREFIX + "getDlvCostPrintInvoice", reqDto));
		/*END.최종 출력물 데이터 조회*/
		
       
		return resultDto;
	}		
	
	/**
	 * @description : 통합납품서출력 출력 데이터 생성 - 거래처별	
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.30 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	public WdInvoiceTotalPrintResDto printDetailList(WdInvoiceTotalReqDto paramDto) throws Exception {
		WdInvoiceTotalPrintResDto resultDto = new WdInvoiceTotalPrintResDto();
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdInvoiceTotalReqDto reqDto = ModelMapperUtil.map(paramDto, WdInvoiceTotalReqDto.class);
		List<WdInvoiceTotalDetailResDto> saveList = reqDto.getSaveDetailList(); // 저장리스트
		
		String invoiceprintkey = reqDto.getInvoiceprintkey();       // 인보이스출력키
		reqDto.setProcesstype(PROCESSTYPE2);     // 프로세스타입
		reqDto.setTemptabletype(TEMPTABLETYPE); // 임시테이블타입	
		
		// START.필수입력
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getInvoiceprintkey()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"Invoiceprintkey"} ) + resultMessage ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
		// END.필수입력
		
		/*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 

		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		for (int i = 0; i < saveList.size(); i++) { 
			WdInvoiceTotalDetailResDto dto = saveList.get(i);
        	// 임시테이블에 등록(2/3)
        	CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE2); // 프로세스타입 
        	
        	// UI.params
        	entity.setPickListNo(invoiceprintkey);      // 피킹리스트번호에 인보이스출력키를 넣는다.	
        	entity.setInvoiceprintkey(invoiceprintkey); // 인보이스출력키
    		
        	// START.필수입력 check - 그리드 변수 등
        	if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDeliverydt()))      ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"deliverydt"} )  );   // 해당 정보가 없어 처리할 수 없습니다.-{0}
        	// Docno/Slipdt/Slipno는 그리드에 없어 주석
        	//if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno()))           ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"docno"}      )  );   
    		//if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipdt()))          ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"slipdt"} )  ); 
    		//if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipno()))          ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"slipno"} )  );
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getCarno()))           ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"carno"} )  );     
    		/**/
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPriority()))        ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"priority"} )  );     
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDeliverygroup()))   ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"deliverygroup"} )  );
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getCustkey()))         ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"custkey"} )  );   
    		/**/
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getInvoiceprinttype())) ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"invoiceprinttype"} )  );       
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getMngplcid()))         ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"mngplcid"} )  );       
    		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getShptoid()))          ) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"shptoid"} )  );
    		// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel2", insertList); 
            	insertList.clear();
            }
        }
        /*END.Temp Table Insert*/	
        
        
        /*START.PAKAGE 호출*/
		// PKG 파라마터 세팅 - 공통(1/4)
        WdInvoiceTotalReqDto dto = new WdInvoiceTotalReqDto();
		ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
        
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList = { "PROCEDURE"        ,"PROCESSTYPE"     ,"TEMPTABLETYPE"          
						    ,"INVOICEPRINTTYPE" ,"INVOICEPRINTKEY" 
						   };
		Object[] valueList = { PAKAGE_NAME, reqDto.getProcesstype(), reqDto.getTemptabletype()
						      ,reqDto.getInvoiceprinttype() ,reqDto.getInvoiceprintkey()
						     };
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		int rv = cmCommonService.saveProcedure(dto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = cjfw.core.utility.StringUtil.nvl(dto.getResultCode());
		resultMessage = cjfw.core.utility.StringUtil.nvl(dto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶출고진행현황 - 상품제외처리 시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"출고진행현황-상품제외"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		 /*END.PAKAGE 호출*/        
		
		/*START.출력정보 조회*/
		String[] columns = {"csTel","empphone","driverPhone","toPhone","fromPhone1","fromFax","toPhone1","toFax"};
//		List<WdInvoiceTotalPrintResDto> reportHeaderList = DamoScpDbUtil.damoScpDecB64(commonDao.selectList(SERVICEID_PREFIX + "getHeaderPrintInvoice", reqDto),columns);
		List<WdInvoiceTotalPrintResDto> reportHeaderList = commonDao.selectList(SERVICEID_PREFIX + "getHeaderPrintInvoice", reqDto);
		for(WdInvoiceTotalPrintResDto printDto : reportHeaderList) {
			printDto.setFromVataddress(cjfw.core.utility.StringUtil.nvl(printDto.getFromVataddress1()) + " " + cjfw.core.utility.StringUtil.nvl(printDto.getFromVataddress2()));
			printDto.setToVataddress(cjfw.core.utility.StringUtil.nvl(printDto.getToVataddress1()) + " " + cjfw.core.utility.StringUtil.nvl(printDto.getToVataddress2()));
			printDto.setToAddress(cjfw.core.utility.StringUtil.nvl(printDto.getToAddress1()) + " " + cjfw.core.utility.StringUtil.nvl(printDto.getToAddress2()));
			// 전화번호형식적용0
			printDto.setEmpphone(StringUtil.toTelephoneNumberFormat(printDto.getEmpphone()));
			printDto.setDriverPhone(StringUtil.toTelephoneNumberFormat(printDto.getDriverPhone()));
			printDto.setToPhone(StringUtil.toTelephoneNumberFormat(printDto.getToPhone()));

			String invoiceType = printDto.getInvoicetype();
			/**
			 * RFC 호출 START
			 */
			if ("27".equals(invoiceType)) {
				JCoUtil jco = new JCoUtil();
				JCoDestination destination = jco.getDestination();
				JCoFunction function = jco.getFunction("ZFI_GET_DAY_ACCOUNT_RFC");
				JCoParameterList params = null;
				JCoParameterList returnData = null;

				if(function == null) {
					throw new UserHandleException("ZFI_GET_DAY_ACCOUNT_RFC not found in SAP.");
				}

				params = function.getImportParameterList();

				params.setValue("I_BUKRS"  , "FW00");     // 회사코드
				params.setValue("I_KUNNR"  , String.format("%10s", printDto.getToCustkey().replace(' ', '0')));     // 고객번호
				params.setValue("I_FRDAT"  , printDto.getDeliverydt().replace("-", ""));     // FROM 날짜
				params.setValue("I_TODAT"  , printDto.getDeliverydt().replace("-", ""));     // TO 날짜

				// 통신 시작
				try {
					System.out.println("통신 시작");
					function.execute(destination);
					System.out.println("통신 종료");
				} catch(JCoException e) {
					e.printStackTrace(); // NO DATA 제외
					//LOG.error("JCoException",e);
					throw new UserHandleException( e.getMessage());
				}catch(Exception e){
					//LOG.error("Exception",e);
					throw new UserHandleException(e.getMessage());
				}

				returnData = function.getExportParameterList();

				JCoTable detailTable = function.getTableParameterList().getTable("T_OUT");

				if( detailTable.getNumRows() > 0) {
					for (int i = 0; i < detailTable.getNumRows(); i++) {
						detailTable.setRow(i); // i번째 행으로 커서 이동
						printDto.setTotalAmt(detailTable.getString("AMT01"));  // 전잔액
						printDto.setTodayAmount(detailTable.getString("AMT03"));  // 현잔액
						printDto.setTodayAmt(detailTable.getString("AMT04"));  // 당일입금액
					}
				}
			}
			/**
			 * RFC 호출 END
			 */
		}
		/*END.출력정보 조회*/
		
		/*START.최종 출력물 데이터 조회*/
		resultDto.setReportHeader(reportHeaderList);
		resultDto.setReportDetailList(commonDao.selectList(SERVICEID_PREFIX2 + "getDetailPrintInvoice", reqDto));
		resultDto.setReportCredit(commonDao.selectList(SERVICEID_PREFIX2 + "getCreditPrintInvoice", reqDto));
		resultDto.setReportDlvCost(commonDao.selectList(SERVICEID_PREFIX2 + "getDlvCostPrintInvoice", reqDto));
		
		// 최종 출력물 데이터 조회(CRM_CUSTDLVINFO 테이블 미존재로 인해 주석처리)	
		//resultDto.setReportFileList(commonDao.selectList(SERVICEID_PREFIX2 + "getDailyMemoFilePrint", reqDto));
		//List<WdInvoiceTotalPrintResDto> custXPopCountList = commonDao.selectList(SERVICEID_PREFIX2 + "getCustXPopCount", reqDto);
		//if (custXPopCountList != null && custXPopCountList.isEmpty()) {
		//	resultDto.setReportCrmCustdlv(commonDao.selectList(SERVICEID_PREFIX2 + "getCrmCustdlvinfo", reqDto));
		//} 
		
		/*END.최종 출력물 데이터 조회*/
       
		return resultDto;
	}			
	
}
