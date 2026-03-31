package cjfw.wms.wd.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.sap.JCoUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.wd.dto.WdShipmentReqDto;
import cjfw.wms.wd.dto.WdShipmentResBillYnDto;
import cjfw.wms.wd.dto.WdShipmentResDto;
import cjfw.wms.wd.dto.WdShipmentTab1DetailResDto;
import cjfw.wms.wd.dto.WdShipmentTab2DetailResDto;
import cjfw.wms.wd.dto.WdShipmentTab3DetailResDto;
import cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCMIT_ORDQTY;
import cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_response;
import cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_responseOT_ORDQTY;
import cjfw.wms.webservice.foOrdQty.SI_CJFO0060_SCM_SOProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.22 
 * @description : 출고확정처리 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdShipmentService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdShipmentService.";
	
	private transient static final String PAKAGE_NAME = "SPWD_SHIPMENT";
	
	private transient static final String PROCESSTYPE = "WD_SHIPMENT";
	
	private transient static final String TEMPTABLETYPE = "WD";		
	
	private transient static final String SAP_FUNCTION_NAME = "ZSD_CHECK_BILLYN";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 출고확정처리 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdShipmentResDto> getMasterList(WdShipmentReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdShipmentResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return list;
	}
	
	
	/**
	 * @description : 출고대상 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdShipmentTab1DetailResDto> getTab1DetailList(WdShipmentReqDto dto) throws RemoteException {
		log.info("### parameter = "+dto.toString());
		
		List<WdShipmentTab1DetailResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1DetailList", dto);
		
		int count = commonDao.selectOne(SERVICEID_PREFIX + "getFoSapIfYn", dto);
	    if (count > 0) {
	        int requestCnt = 0;
            for (int i = 0; i < list.size(); i++) {  
                if (!"85".equals(list.get(i).getStatus()) 
                    && !"90".equals(list.get(i).getStatus())
                    && !"Y".equals(list.get(i).getOther05())){
                    requestCnt++;
                }
            }
            if (requestCnt > 0) {
                SI_CJFO0060_SCM_SOProxy proxy = new SI_CJFO0060_SCM_SOProxy();
                DT_CJFO0060_SCMIT_ORDQTY[] itOrdQty = new DT_CJFO0060_SCMIT_ORDQTY[requestCnt];
                
                int requestIndex=0;
                for (int i = 0; i < list.size(); i++) {//    
                    if (!"85".equals(list.get(i).getStatus()) 
                        && !"90".equals(list.get(i).getStatus())
                        && !"Y".equals(list.get(i).getOther05()) ){
                        itOrdQty[requestIndex] = new DT_CJFO0060_SCMIT_ORDQTY();
                        itOrdQty[requestIndex].setVDATU(    list.get(i).getSlipdt());
                        itOrdQty[requestIndex].setVBELN_FW( list.get(i).getDocno());
                        itOrdQty[requestIndex].setPOSNR(    list.get(i).getDocline());
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
                            for (int targetRow = 0; targetRow < list.size(); targetRow++){
                                nTarget = list.get(targetRow).getDocno() + "_" + list.get(targetRow).getDocline();
                                if (nSource.equals(nTarget) 
                                    && !"85".equals(list.get(targetRow).getStatus()) 
                                    && !"90".equals(list.get(targetRow).getStatus())
                                    && !"Y".equals(list.get(targetRow).getOther05())) {
                                	list.get(targetRow).setConfirmqty(new BigDecimal(resultLst[sourceRow].getMENGE_FW()));    //출고수량 변경
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } 
		
		
		return list;
	}
	
	/**
	 * @description : 결품대상 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdShipmentTab2DetailResDto> getTab2DetailList(WdShipmentReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdShipmentTab2DetailResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2DetailList", dto);
		return list;
	}
	
	/**
	 * @description : 상차검수취소 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdShipmentTab3DetailResDto> getTab3DetailList(WdShipmentReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdShipmentTab3DetailResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab3DetailList", dto);
		return list;
	}
	
	/**
	 * @description : 세금계산서 발행여부 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.03 공두경 (medstorm@cj.net) 생성 </pre>
	 */	
	public WdShipmentResBillYnDto getBillYn(WdShipmentReqDto dto) throws JCoException {
		log.info("### parameter = "+dto.toString());
		WdShipmentResBillYnDto resultDto = new WdShipmentResBillYnDto();
	    
	    JCoDestination destination = JCoUtil.getDestination();
        JCoFunction function = JCoUtil.getFunction(SAP_FUNCTION_NAME);
        
        if (function == null) {
            resultDto.setResult("N");
            resultDto.setErrorMsg("ZSD_CHECK_BILLYN not found in SAP.");
            
            log.error("▶SAP 마감여부 조회 -> " + SAP_FUNCTION_NAME + " not found in SAP.");
            throw new UserHandleException(SAP_FUNCTION_NAME + " not found in SAP.");
        }
	    
        JCoTable table = function.getTableParameterList().getTable("T_ZSDT1084");
        table.clear();
        table.appendRow();                  
        table.setValue("VBELN", dto.getDocno());
        table.setValue("POSNR", dto.getDocline());
        
        log.info("### table = "+table.toString());
            
        function.execute(destination);
        
        JCoParameterList exportParams = function.getExportParameterList();
        String eMessage = exportParams.getString("E_XMSGS"); // 메시지
        String eStatus = exportParams.getString("E_XSTAT");  // 상태 ('S':성공, 'E':실패)

        log.info("eStatus="+ eStatus);
        log.info("eMessage="+ eMessage);
        
        for (int i = 0; i < table.getNumRows(); i++) {
            table.setRow(i);
            String zbillyn = table.getString("ZBILLYN");
            
            log.info(zbillyn);
            log.info(table.toString());
            
            resultDto.setZbillyn(zbillyn);
        }	    

	    return resultDto;
	}
	
	

	/**
	 * @description : 출고확정 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveConfirm(WdShipmentReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
		
        // 파라미터 위변조 적용(paramDto->reqDto)
        WdShipmentReqDto reqDto = ModelMapperUtil.map(paramDto, WdShipmentReqDto.class);
        List<WdShipmentResDto> saveList = reqDto.getSaveConfirmList(); // 저장리스트        
        
        log.info("▶saveList.size->{}",saveList);
        
        ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
        
	    WdShipmentResDto dto = saveList.get(0);
	    
		// PKG 파라마터 세팅 - 공통(1/4)
		
    	log.info("▶dto->{}",dto);
    	
    	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "STORERKEY", 		"DCCODE",  		  "DELIVERYDT", 	 "DELIVERYGROUP", 		"CARNO",	  	"PRIORITY"};
		Object[] valueList = {PAKAGE_NAME, dto.getStorerkey(), dto.getDccode(), dto.getDeliverydt(), dto.getDeliverygroup(), dto.getCarno() ,dto.getPriority() };
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		reqDto.setAvc_DCCODE(dto.getDccode());
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!resultCode.equals("0")){
			log.error("▶출고확정시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"출고확정"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		 /*END.PAKAGE 호출*/  
        	
		      
       
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	

	/**
	 * @description : 출고대상확정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveWD(WdShipmentReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		String sAVC_DCCODE = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdShipmentReqDto reqDto = ModelMapperUtil.map(paramDto, WdShipmentReqDto.class);
		List<WdShipmentTab1DetailResDto> saveList = reqDto.getSaveTab1List(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdShipmentTab1DetailResDto> list = new ArrayList<WdShipmentTab1DetailResDto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdShipmentTab1DetailResDto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTasksystem())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"tasksystem"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSrcserialkey1())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"srcserialkey1"}));			
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSku())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"sku"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getArea())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"area"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docdt"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocline())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docline"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipdt"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipno"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipline())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipline"}));
			if(entity.getBunja() == null || BigDecimal.ZERO.compareTo(entity.getBunja()) == 0) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"bunja"}));
			if(entity.getBunmo() == null || BigDecimal.ZERO.compareTo(entity.getBunmo()) == 0) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"bunmo"}));
			if(entity.getConfirmqty() == null || BigDecimal.ZERO.compareTo(entity.getConfirmqty()) == 0) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"출고작업량"}));

			// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
            	insertList.clear();
            }
        	sAVC_DCCODE = entity.getDccode();
		}
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"};
		Object[] valueList = {PAKAGE_NAME, PROCESSTYPE};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		reqDto.setAvc_DCCODE(sAVC_DCCODE);
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!resultCode.equals("0")){
			log.error("▶출고대상확정시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"출고대상확정"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	

	/**
	 * @description : 결품대상확정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveShortage(WdShipmentReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		String sAVC_DCCODE = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdShipmentReqDto reqDto = ModelMapperUtil.map(paramDto, WdShipmentReqDto.class);
		List<WdShipmentTab2DetailResDto> saveList = reqDto.getSaveTab2List(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdShipmentTab2DetailResDto> list = new ArrayList<WdShipmentTab2DetailResDto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdShipmentTab2DetailResDto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTasksystem())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"tasksystem"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSrcserialkey1())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"srcserialkey1"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSku())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"sku"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getArea())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"area"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docdt"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocline())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docline"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipdt"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipno"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipline())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"slipline"}));
			if(entity.getBunja() == null || BigDecimal.ZERO.compareTo(entity.getBunja()) == 0) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"bunja"}));
			if(entity.getBunmo() == null || BigDecimal.ZERO.compareTo(entity.getBunmo()) == 0) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"bunmo"}));
			if(entity.getEtcqty1() == null || BigDecimal.ZERO.compareTo(entity.getEtcqty1()) == 0) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"결품작업량"}));
			
			
			// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
            	insertList.clear();
            }
        	sAVC_DCCODE = entity.getDccode();
		}
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"};
		Object[] valueList = {PAKAGE_NAME, PROCESSTYPE};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		reqDto.setAvc_DCCODE(sAVC_DCCODE);
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!resultCode.equals("0")){
			log.error("▶결품대상확정시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"결품대상확정"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	

	/**
	 * @description : 상차검수취소 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveWdInspect(WdShipmentReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
		
        // 파라미터 위변조 적용(paramDto->reqDto)
        WdShipmentReqDto reqDto = ModelMapperUtil.map(paramDto, WdShipmentReqDto.class);
        List<WdShipmentTab3DetailResDto> saveList = reqDto.getSaveTab3List(); // 저장리스트        
        
        log.info("▶saveList.size->{}",saveList);
        
        ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
        
        WdShipmentTab3DetailResDto dto = saveList.get(0);
	    
		// PKG 파라마터 세팅 - 공통(1/4)
		
    	log.info("▶dto->{}",dto);
    	
    	
    	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE"
							, "DCCODE"
							, "STORERKEY"
							, "DOCTYPE"
							, "DELIVERYDT"
							, "PRIORITY"
							, "SLIPDT"
							, "DELIVERYGROUP"
							, "CARNO"
							, "INVOICENO"
							, "SERIALKEY"
		};
		Object[] valueList = {PAKAGE_NAME
							, dto.getDccode()
							, dto.getStorerkey()
							, dto.getDoctype()
							, dto.getDeliverydt()
							, dto.getPriority()
							, dto.getSlipdt()
							, dto.getDeliverygroup()
							, dto.getCarno()
							, dto.getInvoiceno()
							, dto.getSerialkey()	
		};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));	
		reqDto.setAvc_DCCODE(dto.getDccode());
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!resultCode.equals("0")){
			log.error("▶상차검수취소시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"상차검수취소"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		 /*END.PAKAGE 호출*/  
        	
		      
       
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	


}
