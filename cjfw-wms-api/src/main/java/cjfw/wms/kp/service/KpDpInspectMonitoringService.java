package cjfw.wms.kp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.service.CmEmailService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.kp.dto.KpDpInspectMonitoringDtlResDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringExcelResDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringPrintResDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringReqDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringResDto;
import cjfw.wms.kp.dto.KpDpInspectMonitoringSumResDto;
import cjfw.wms.kp.entity.KpDpInspectMonitoringEntity;
import cjfw.wms.kp.entity.KpDpInspectMonitoringSmsEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.12.26 
 * @description : 지표/모니터링 > 검수지표 > 입고검수현황 목록 조회 및 저장 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpDpInspectMonitoringService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	transient static final String SERVICEID_PREFIX = "kpDpInspectMonitoringService.";

	final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/** 공통.service */
	private final CmCommonService cmCommonService;
	private final CmEmailService cmEmailService;
	/** 프로시져명 - 패키지호출 */
	private transient String PAKAGE_NAME = "SPDP_INSPECT_COMP";	
	/** 프로세스타임 */
	private transient String PROCESSTYPE ;	
	/** 임시테이블 타입 */
	private transient String TEMPTABLETYPE = "DP" ;	
	
	
	/**
	 * @description : 입고검수현황 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<KpDpInspectMonitoringResDto> getHeaderList(KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		// 검색 기준에 따른 쿼리 분류
			return commonDao.selectList(SERVICEID_PREFIX + "getHeaderList", KpDpInspectMonitoringReqDto);
	}
	
	
	/**
	 * @description : 입고검수현황 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<KpDpInspectMonitoringDtlResDto> getDetailList(KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", KpDpInspectMonitoringReqDto);
		
	}
	
	/**
	 * @description : 검수완료 - 일괄
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public String saveInspectAll(KpDpInspectMonitoringReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		KpDpInspectMonitoringReqDto reqDto = ModelMapperUtil.map(paramDto, KpDpInspectMonitoringReqDto.class);
		
		List<KpDpInspectMonitoringResDto> saveList = reqDto.getSaveList(); // 저장리스트
		
		if (saveList.size() > 5) {
			reqDto.setAvc_COMMAND("BATCHPROCESSCONFIRM"); 
			reqDto.setProcesstype("DP_INSPECT_COMP"); // 프로세스타입
			
			PROCESSTYPE = reqDto.getProcesstype();     // 프로세스타입

			/*START.Temp Table Insert*/
	        // 임시테이블 삭제(1/3)
			commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
			
			// 임시테이블 Insert 
			// 200개씩 끊어서 Insert 처리 200개씩 끊어서 Insert 처리
			int chunkSize = 200;
			List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
			for (int i = 0; i < saveList.size(); i++) { 
				KpDpInspectMonitoringResDto dto = saveList.get(i);
				
				CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
				entity.setPackagename(PAKAGE_NAME); // 패키지명
				entity.setProcesstype(PROCESSTYPE); // 프로세스타입 
				
				/* DTO 필드(카멜) → Entity 필드(카멜) 1 : 1 순서 매핑 */
				String columnsDto    ="DCCODE|STORERKEY|FROMCUSTKEY|SLIPDT|CHANNEL";
				String columnsEntity ="DCCODE|STORERKEY|CUSTKEY|SLIPDT|STOCKTYPE";
				
				entity = (CmSyProcessTempWdEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);

	        	// UI.params
	        	// START.필수입력 check - 그리드 변수 등
	    		// END.필수입력 check

	    		insertList.add(entity);
	        	
	        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
	        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
	        		commonDao.insert(SERVICEID_PREFIX + "insertTemp", insertList); 
	            	insertList.clear();
	            }
	        }
	        /*END.Temp Table Insert*/	
			
	        /*START.PAKAGE 호출*/
			// PKG 파라마터 세팅 - 공통(1/4)
			KpDpInspectMonitoringReqDto dto = new KpDpInspectMonitoringReqDto();
			ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
			
			// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
			String[] keyList   = {"PROCEDURE", "PROCESSTYPE","TEMPTABLETYPE"};
			Object[] valueList = {PAKAGE_NAME, PROCESSTYPE, TEMPTABLETYPE};
			
			dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
			int rv = cmCommonService.saveProcedure(dto); // 패키지 호출 
			log.info("rv->{}",rv);
			
			// 프로시저 OUT Parameter(3/4)
			resultCode    = StringUtil.nvl(dto.getResultCode());
			resultMessage = StringUtil.nvl(dto.getResultMessage());
			
			// 프로시저 Exception 처리(4/4)
			if(!"0".equals(resultCode)){
				log.error("▶출고진행현황 - 상품제외처리 시 오류 발생 ");
				throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"출고진행현황-상품제외"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
			}  	
			/*END.PAKAGE 호출*/        
			
			return CanalFrameConstants.MSG_COM_SUC_CODE;
		} else {
			// 프로시저 에러코드 및 메세지
	        resultCode = "";
	        resultMessage  = "";
		        
//	        KpDpInspectMonitoringResDto dto = saveList.get(0);
	        
	        for (KpDpInspectMonitoringResDto dto : saveList) {
	        	ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
			    
				// PKG 파라마터 세팅 - 공통(1/4)
				
		    	log.info("▶dto->{}",dto);
		    	
		    	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
				String[] keyList   = {"DCCODE", "STORERKEY", "FROM_CUSTKEY", "SLIPDT", "CHANNEL"};
				Object[] valueList = {dto.getDccode(), dto.getStorerkey(), dto.getFromcustkey(), dto.getSlipdt(), dto.getChannel()};
				reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
				reqDto.setAvc_DCCODE(dto.getDccode());	
				reqDto.setAvc_COMMAND("CONFIRM");
				int rv = cmCommonService.saveProcedure(reqDto); 
				log.info("rv->{}",rv);
				
				// 프로시저 OUT Parameter(3/4)
				resultCode    = StringUtil.nvl(reqDto.getResultCode());
				resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
				log.info("resultCode->{}",resultCode);
				log.info("resultMessage->{}",resultMessage);
				
				// 프로시저 Exception 처리(4/4)
				if(!"0".equals(resultCode)){
					log.error("▶검수완료 오류 발생 ");
					throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"검수완료"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
				}  	
	        }
			 /*END.PAKAGE 호출*/  
	       
			return CanalFrameConstants.MSG_COM_SUC_CODE;
		}
	}
	
	/**
	 * @description : 검수완료 - 단건
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public String saveInspect(KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		if (null != KpDpInspectMonitoringReqDto.getSaveList()) {
			for (KpDpInspectMonitoringResDto dtlReq : KpDpInspectMonitoringReqDto.getSaveList()) {
				KpDpInspectMonitoringEntity entity = ModelMapperUtil.map(dtlReq, userContext, KpDpInspectMonitoringEntity.class);
				
				commonDao.insert(SERVICEID_PREFIX +"insertInspectComp", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	
	/**
	 * @description : SMS 버튼 - 일괄
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
//	@Transactional
	public String sendSMSAll(KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		if (null != KpDpInspectMonitoringReqDto.getSaveList()) {
			
			int count = 0;
			for (KpDpInspectMonitoringResDto dtlReq : KpDpInspectMonitoringReqDto.getSaveList()) {
				KpDpInspectMonitoringEntity entity = ModelMapperUtil.map(dtlReq, userContext, KpDpInspectMonitoringEntity.class);

				// SMS 신규전송
				count++;

				// 검수완료 내역 확인 
				Map<String,Object> cntchk = getInspectCompList(dtlReq);

				// 검수완료 내역이 없을 때
				if ("0".equals(cntchk.get("CNT").toString())) {
					// 검수완료 먼저 진행
					entity.setMsgrow(count);
					entity.setMsgMemo("검수를 완료해 주세요.");
					
					// 메세지 INSERT insertInspectCompMsg
					commonDao.insert(SERVICEID_PREFIX +"insertInspectCompMsg", entity);					
					throw new UserHandleException("검수확정 후 진행해주세요.");
				}
				
				// 결품 SMS 문구생성(대상: 미스켄,스켄중,스켄완료 결품발생시 사용문구)
				int missChk = commonDao.selectOne(SERVICEID_PREFIX + "getInspectMissChkList", dtlReq);
				
				dtlReq.setSendtitle("금일 결품내역 안내");
				dtlReq.setSendmessage("금일 결품내역이 있습니다.");
				
				// 미입고 내역 체크 및 SMS 문구생성(대상:스켄완료)
				if(missChk == 0) {
					dtlReq.setSendtitle("금일 미입고내역 없음 안내");
					dtlReq.setSendmessage("금일 미입고내역이 없습니다.");
				}
			
				// SMS 발송횟수  체크 
				if (!"0".equals(cntchk.get("SMSCNT").toString())) {
					// SMS 발송 INSERT
					entity.setMsgrow(count);
					entity.setMsgMemo("SMS 발송 할 내역이 없습니다.");
					
					commonDao.insert(SERVICEID_PREFIX +"insertInspectCompMsg", entity);
					throw new UserHandleException("SMS 발송 할 내역이 없습니다."); 
				}
				//협력사 담당자 정보 & 협력사 수신확대 정보 조회		
				List<Map<String,String>> custList = commonDao.selectList(SERVICEID_PREFIX + "getInspectCustDlvInfoChk", entity); // 협력사 정보
				List<Map<String,String>> smsList = commonDao.selectList(SERVICEID_PREFIX + "getInspectCustRcvSMS", entity); 
				List<Map<String,String>> emailList = commonDao.selectList(SERVICEID_PREFIX + "getInspectCustRcvEmail", entity);
				
				KpDpInspectMonitoringSmsEntity smsEntity = ModelMapperUtil.map(dtlReq, userContext, KpDpInspectMonitoringSmsEntity.class);


				// 협력사 담당자 정보 체크				
			    if (custList.get(0).get("EMPPHONE1").equals("N") || custList.get(0).get("EMAIL").equals("N") && smsList.size() == 0) {
			    	// msg 작성하고 return 시킴
			    	entity.setMsgrow(count);
					entity.setMsgMemo("협력사 담당자 정보가 없습니다.");
					
					commonDao.insert(SERVICEID_PREFIX +"insertInspectCompMsg", entity);
					throw new UserHandleException(entity.getMsgMemo()); 
			    } else { // 정보가 있으면 정보세팅
			    	smsEntity.setReceivephone(custList.get(0).get("EMPPHONE1"));
			    	smsEntity.setReceivename(custList.get(0).get("EMPNAME1"));				    	
			    }
				
				//1.Email 수신확대 (saveEmail에서 결품량 유무 확인후에 문자내용 변경)
				//  협력사 담당자 EMAIL정보가 있는경우 전송
				//  엑셀 수신확대 EMAIL정보가 있는경우 전송
			    List<String> RCV_NAMES = new ArrayList<String>();
				List<String> RCV_EMAILS = new ArrayList<String>();
			    if(custList.get(0).get("EMAIL").toString().equals("N")) {
			    	RCV_NAMES.add(custList.get(0).get("EMPNAME1").toString());
					RCV_EMAILS.add(custList.get(0).get("EMAIL").toString());
			    }
			    //  엑셀 수신확대 EMAIL정보가 있는경우 전송
				if(emailList.size() > 0){
					for (int i=0 ; i < emailList.size(); i++){
						RCV_NAMES.add(custList.get(i).get("EMPNAME1").toString());
						RCV_EMAILS.add(custList.get(i).get("EMAIL").toString());
					}
				}
				entity.setReceivename(StringUtils.join(RCV_NAMES, ","));
				entity.setReceiveemail(StringUtils.join(RCV_EMAILS, ","));
				entity.setSmssend("1ST");
			    saveEmail(entity,smsEntity);
				
				//2.SMS 수신확대
				//  협력사 담당자 핸드폰정보가 있는경우 전송
			    int smsCnt = 0;
				if (custList.size() > 0 ) {
					if(!custList.get(0).get("EMPPHONE1").equals("N")) {
						// SMS 발송 insert 
						int rs = commonDao.insert(SERVICEID_PREFIX +"insertInspectSms", smsEntity);
						if (rs > 0) {
							smsCnt++;
						}
					}
				}
				//  엑셀 수신확대 핸드폰정보가 있는경우 전송
				if (smsList.size() > 0 ) {
					for (Map<String,String> smsDtl : smsList) {
						smsEntity.setReceivephone(smsDtl.get("EMPPHONE1"));
						smsEntity.setReceivename(smsDtl.get("EMPNAME1"));
						// SMS 발송 insert 
						int rs = commonDao.insert(SERVICEID_PREFIX +"insertInspectSms", smsEntity);
						if (rs > 0) {
							smsCnt++;
						}
					}
				}
				//3.검수완료내역 UPDATE
				if (smsCnt > 0) {
					commonDao.update(SERVICEID_PREFIX +"updateInspectComp", entity);
				}
			} 
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : SMS 버튼 2차 - 일괄
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
//	@Transactional
	public String sendSMSAllResend (KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		if (null != KpDpInspectMonitoringReqDto.getSaveList()) {
			
			int count = 0;
			for (KpDpInspectMonitoringResDto dtlReq : KpDpInspectMonitoringReqDto.getSaveList()) {
				KpDpInspectMonitoringEntity entity = ModelMapperUtil.map(dtlReq, userContext, KpDpInspectMonitoringEntity.class);

				// SMS 신규전송
				count++;

				// 검수완료 내역 확인 
				Map<String,Object> cntchk = getInspectCompList(dtlReq);
				
				// 결품 SMS 문구생성(대상: 미스켄,스켄중,스켄완료 결품발생시 사용문구)
				int missChk = commonDao.selectOne(SERVICEID_PREFIX + "getInspectMissChkList", dtlReq);
				
				dtlReq.setSendtitle("금일 결품내역 안내");
				dtlReq.setSendmessage("금일 결품내역이 있습니다.");
				
				// 미입고 내역 체크 및 SMS 문구생성(대상:스켄완료)
				if(missChk == 0) {
					dtlReq.setSendtitle("금일 미입고내역 없음 안내");
					dtlReq.setSendmessage("금일 미입고내역이 없습니다.");
				}
			
				//협력사 담당자 정보 & 협력사 수신확대 정보 조회		
				List<Map<String,String>> custList = commonDao.selectList(SERVICEID_PREFIX + "getInspectCustDlvInfoChk", entity); // 협력사 정보
				List<Map<String,String>> smsList = commonDao.selectList(SERVICEID_PREFIX + "getInspectCustRcvSMS", entity); 
				List<Map<String,String>> emailList = commonDao.selectList(SERVICEID_PREFIX + "getInspectCustRcvEmail", entity);
				
				KpDpInspectMonitoringSmsEntity smsEntity = ModelMapperUtil.map(dtlReq, userContext, KpDpInspectMonitoringSmsEntity.class);


				// 협력사 담당자 정보 체크				
			    if (custList.get(0).get("EMPPHONE1").equals("N") || custList.get(0).get("EMAIL").equals("N") && smsList.size() == 0) {
			    	// msg 작성하고 return 시킴
			    	entity.setMsgrow(count);
					entity.setMsgMemo("협력사 담당자 정보가 없습니다.");
					
					commonDao.insert(SERVICEID_PREFIX +"insertInspectCompMsg", entity);
					throw new UserHandleException(entity.getMsgMemo()); 
			    } else { // 정보가 있으면 정보세팅
			    	smsEntity.setReceivephone(custList.get(0).get("EMPPHONE1"));
			    	smsEntity.setReceivename(custList.get(0).get("EMPNAME1"));				    	
			    }
				
				//1.Email 수신확대 (saveEmail에서 결품량 유무 확인후에 문자내용 변경)
				//  협력사 담당자 EMAIL정보가 있는경우 전송
				//  엑셀 수신확대 EMAIL정보가 있는경우 전송
			    List<String> RCV_NAMES = new ArrayList<String>();
				List<String> RCV_EMAILS = new ArrayList<String>();
			    if(custList.get(0).get("EMAIL").toString().equals("N")) {
			    	RCV_NAMES.add(custList.get(0).get("EMPNAME1").toString());
					RCV_EMAILS.add(custList.get(0).get("EMAIL").toString());
			    }
			    //  엑셀 수신확대 EMAIL정보가 있는경우 전송
				if(emailList.size() > 0){
					for (int i=0 ; i < emailList.size(); i++){
						RCV_NAMES.add(emailList.get(i).get("EMPNAME1").toString());
						RCV_EMAILS.add(emailList.get(i).get("EMAIL").toString());
					}
				}
				entity.setReceivename(StringUtils.join(RCV_NAMES, ","));
				entity.setReceiveemail(StringUtils.join(RCV_EMAILS, ","));
				entity.setSmssend("2ST");
			    saveEmail(entity,smsEntity);
			    
				
				//2.SMS 수신확대
				//  협력사 담당자 핸드폰정보가 있는경우 전송
			    int smsCnt = 0;
				if (custList.size() > 0 ) {
					if(!custList.get(0).get("EMPPHONE1").equals("N")) {
						// SMS 발송 insert 
						int rs = commonDao.insert(SERVICEID_PREFIX +"insertInspectSms", smsEntity);
						if (rs > 0) {
							smsCnt++;
						}

					}
				}
				//  엑셀 수신확대 핸드폰정보가 있는경우 전송
				if (smsList.size() > 0 ) {
					for (Map<String,String> smsDtl : smsList) {
						smsEntity.setReceivephone(smsDtl.get("EMPPHONE1"));
						smsEntity.setReceivename(smsDtl.get("EMPNAME1"));
						// SMS 발송 insert 
						int rs = commonDao.insert(SERVICEID_PREFIX +"insertInspectSms", smsEntity);
						if (rs > 0) {
							smsCnt++;
						}
					}
				}
				//3.검수완료내역 UPDATE
				if (smsCnt > 0) {
					commonDao.update(SERVICEID_PREFIX +"updateInspectComp", entity);
				}
			} 
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : SMS 버튼 - 단건
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
//	@Transactional
	public String saveSendSMS (KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		if (null != KpDpInspectMonitoringReqDto.getSaveList()) {
			
			KpDpInspectMonitoringResDto dtlReq = KpDpInspectMonitoringReqDto.getSaveList().get(0);
			
			int count = 0;
			KpDpInspectMonitoringEntity entity = ModelMapperUtil.map(dtlReq, userContext, KpDpInspectMonitoringEntity.class);

			// SMS 신규전송
			count++;

			// 검수완료 내역 확인 
			Map<String,Object> cntchk = getInspectCompList(dtlReq);
			
			// 결품 SMS 문구생성(대상: 미스켄,스켄중,스켄완료 결품발생시 사용문구)
			int missChk = commonDao.selectOne(SERVICEID_PREFIX + "getInspectMissChkList", dtlReq);
			
			dtlReq.setSendtitle("금일 결품내역 안내");
			dtlReq.setSendmessage("금일 결품내역이 있습니다.");
			
			// 미입고 내역 체크 및 SMS 문구생성(대상:스켄완료)
			if(missChk == 0) {
				dtlReq.setSendtitle("금일 미입고내역 없음 안내");
				dtlReq.setSendmessage("금일 미입고내역이 없습니다.");
			}
			
			// SMS 발송횟수  체크 
			if (!"0".equals(cntchk.get("SMSCNT").toString())) {
				// SMS 발송 INSERT
				entity.setMsgrow(count);
				entity.setMsgMemo("SMS 발송 할 내역이 없습니다.");
				
				commonDao.insert(SERVICEID_PREFIX +"insertInspectCompMsg", entity);
				return cntchk.get("SMSCNT").toString();
			}
		
			//협력사 담당자 정보 & 협력사 수신확대 정보 조회		
			List<Map<String,String>> custList = commonDao.selectList(SERVICEID_PREFIX + "getInspectCustDlvInfoChk", entity); // 협력사 정보
			List<Map<String,String>> smsList = commonDao.selectList(SERVICEID_PREFIX + "getInspectCustRcvSMS", entity); 
			List<Map<String,String>> emailList = commonDao.selectList(SERVICEID_PREFIX + "getInspectCustRcvEmail", entity);
			
			KpDpInspectMonitoringSmsEntity smsEntity = ModelMapperUtil.map(dtlReq, userContext, KpDpInspectMonitoringSmsEntity.class);


			// 협력사 담당자 정보 체크				
		    if (custList.get(0).get("EMPPHONE1").equals("N") || custList.get(0).get("EMAIL").equals("N") && smsList.size() == 0) {
		    	// msg 작성하고 return 시킴
		    	entity.setMsgrow(count);
				entity.setMsgMemo("협력사 담당자 정보가 없습니다.");
				
				commonDao.insert(SERVICEID_PREFIX +"insertInspectCompMsg", entity);
				throw new UserHandleException(entity.getMsgMemo()); 
		    } else { // 정보가 있으면 정보세팅
		    	smsEntity.setReceivephone(custList.get(0).get("EMPPHONE1"));
		    	smsEntity.setReceivename(custList.get(0).get("EMPNAME1"));				    	
		    }
			
			//1.Email 수신확대 (saveEmail에서 결품량 유무 확인후에 문자내용 변경)
			//  협력사 담당자 EMAIL정보가 있는경우 전송
			//  엑셀 수신확대 EMAIL정보가 있는경우 전송
		    List<String> RCV_NAMES = new ArrayList<String>();
			List<String> RCV_EMAILS = new ArrayList<String>();
		    if(custList.get(0).get("EMAIL").toString().equals("N")) {
		    	RCV_NAMES.add(custList.get(0).get("EMPNAME1").toString());
				RCV_EMAILS.add(custList.get(0).get("EMAIL").toString());
		    }
		    //  엑셀 수신확대 EMAIL정보가 있는경우 전송
			if(emailList.size() > 0){
				for (int i=0 ; i < emailList.size(); i++){
					RCV_NAMES.add(emailList.get(i).get("EMPNAME1").toString());
					RCV_EMAILS.add(emailList.get(i).get("EMAIL").toString());
				}
			}
			entity.setReceivename(StringUtils.join(RCV_NAMES, ","));
			entity.setReceiveemail(StringUtils.join(RCV_EMAILS, ","));
			entity.setSmssend("1ST");
		    saveEmail(entity,smsEntity);
		    
			
			//2.SMS 수신확대
			//  협력사 담당자 핸드폰정보가 있는경우 전송
		    int smsCnt = 0;
			if (custList.size() > 0 ) {
				if(!custList.get(0).get("EMPPHONE1").equals("N")) {
					// SMS 발송 insert 
					int rs = commonDao.insert(SERVICEID_PREFIX +"insertInspectSms", smsEntity);
					if (rs > 0) {
						smsCnt++;
					}

				}
			}
			//  엑셀 수신확대 핸드폰정보가 있는경우 전송
			if (smsList.size() > 0 ) {
				for (Map<String,String> smsDtl : smsList) {
					smsEntity.setReceivephone(smsDtl.get("EMPPHONE1"));
					smsEntity.setReceivename(smsDtl.get("EMPNAME1"));
					// SMS 발송 insert 
					int rs = commonDao.insert(SERVICEID_PREFIX +"insertInspectSms", smsEntity);
					if (rs > 0) {
						smsCnt++;
					}
				}
			}
			//3.검수완료내역 UPDATE
			if (smsCnt > 0) {
				commonDao.update(SERVICEID_PREFIX +"updateInspectComp", entity);
			}
		} 
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : SMS 버튼 - 단건
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
//	@Transactional
	public String saveSendSMSResend (KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		if (null != KpDpInspectMonitoringReqDto.getSaveList()) {
			
			KpDpInspectMonitoringResDto dtlReq = KpDpInspectMonitoringReqDto.getSaveList().get(0);
			
			int count = 0;
			KpDpInspectMonitoringEntity entity = ModelMapperUtil.map(dtlReq, userContext, KpDpInspectMonitoringEntity.class);

			// SMS 신규전송
			count++;

			// 검수완료 내역 확인 
			Map<String,Object> cntchk = getInspectCompList(dtlReq);
			
			// 결품 SMS 문구생성(대상: 미스켄,스켄중,스켄완료 결품발생시 사용문구)
			int missChk = commonDao.selectOne(SERVICEID_PREFIX + "getInspectMissChkList", dtlReq);
			
			dtlReq.setSendtitle("금일 결품내역 안내");
			dtlReq.setSendmessage("금일 결품내역이 있습니다.");
			
			// 미입고 내역 체크 및 SMS 문구생성(대상:스켄완료)
			if(missChk == 0) {
				dtlReq.setSendtitle("금일 미입고내역 없음 안내");
				dtlReq.setSendmessage("금일 미입고내역이 없습니다.");
			}
			
			// SMS 발송횟수  체크 
			if (!"0".equals(cntchk.get("SMSCNT").toString())) {
				// SMS 발송 INSERT
				entity.setMsgrow(count);
				entity.setMsgMemo("SMS 발송 할 내역이 없습니다.");
				
				commonDao.insert(SERVICEID_PREFIX +"insertInspectCompMsg", entity);
			}
		
			//협력사 담당자 정보 & 협력사 수신확대 정보 조회		
			List<Map<String,String>> custList = commonDao.selectList(SERVICEID_PREFIX + "getInspectCustDlvInfoChk", entity); // 협력사 정보
			List<Map<String,String>> smsList = commonDao.selectList(SERVICEID_PREFIX + "getInspectCustRcvSMS", entity); 
			List<Map<String,String>> emailList = commonDao.selectList(SERVICEID_PREFIX + "getInspectCustRcvEmail", entity);
			
			KpDpInspectMonitoringSmsEntity smsEntity = ModelMapperUtil.map(dtlReq, userContext, KpDpInspectMonitoringSmsEntity.class);


			// 협력사 담당자 정보 체크				
		    if (custList.get(0).get("EMPPHONE1").equals("N") || custList.get(0).get("EMAIL").equals("N") && smsList.size() == 0) {
		    	// msg 작성하고 return 시킴
		    	entity.setMsgrow(count);
				entity.setMsgMemo("협력사 담당자 정보가 없습니다.");
				
				commonDao.insert(SERVICEID_PREFIX +"insertInspectCompMsg", entity);
				throw new UserHandleException(entity.getMsgMemo()); 
		    } else { // 정보가 있으면 정보세팅
		    	smsEntity.setReceivephone(custList.get(0).get("EMPPHONE1"));
		    	smsEntity.setReceivename(custList.get(0).get("EMPNAME1"));				    	
		    }
			
			//1.Email 수신확대 (saveEmail에서 결품량 유무 확인후에 문자내용 변경)
			//  협력사 담당자 EMAIL정보가 있는경우 전송
			//  엑셀 수신확대 EMAIL정보가 있는경우 전송
		    List<String> RCV_NAMES = new ArrayList<String>();
			List<String> RCV_EMAILS = new ArrayList<String>();
		    if(custList.get(0).get("EMAIL").toString().equals("N")) {
		    	RCV_NAMES.add(custList.get(0).get("EMPNAME1").toString());
				RCV_EMAILS.add(custList.get(0).get("EMAIL").toString());
		    }
		    //  엑셀 수신확대 EMAIL정보가 있는경우 전송
			if(emailList.size() > 0){
				for (int i=0 ; i < emailList.size(); i++){
					RCV_NAMES.add(emailList.get(i).get("EMPNAME1").toString());
					RCV_EMAILS.add(emailList.get(i).get("EMAIL").toString());
				}
			}
			entity.setReceivename(StringUtils.join(RCV_NAMES, ","));
			entity.setReceiveemail(StringUtils.join(RCV_EMAILS, ","));
			entity.setSmssend("2ST");
		    saveEmail(entity,smsEntity);
			
			//2.SMS 수신확대
			//  협력사 담당자 핸드폰정보가 있는경우 전송
		    int smsCnt = 0;
			if (custList.size() > 0 ) {
				if(!custList.get(0).get("EMPPHONE1").equals("N")) {
					// SMS 발송 insert 
					int rs = commonDao.insert(SERVICEID_PREFIX +"insertInspectSms", smsEntity);
					if (rs > 0) {
						smsCnt++;
					}

				}
			}
			//  엑셀 수신확대 핸드폰정보가 있는경우 전송
			if (smsList.size() > 0 ) {
				for (Map<String,String> smsDtl : smsList) {
					smsEntity.setReceivephone(smsDtl.get("EMPPHONE1"));
					smsEntity.setReceivename(smsDtl.get("EMPNAME1"));
					// SMS 발송 insert 
					int rs = commonDao.insert(SERVICEID_PREFIX +"insertInspectSms", smsEntity);
					if (rs > 0) {
						smsCnt++;
					}
				}
			}
			//3.검수완료내역 UPDATE
			if (smsCnt > 0) {
				commonDao.update(SERVICEID_PREFIX +"updateInspectComp", entity);
			}
		} 
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	public void saveEmail(KpDpInspectMonitoringEntity entity,KpDpInspectMonitoringSmsEntity smsEntity) {
		List<Map<String,String>> emailList = commonDao.selectList(SERVICEID_PREFIX + "getInspectEmailList", entity); 

		String userId = entity.getGUserId();		
		entity.setSubtitle(" 미입고 내역 공유");
		String smsSendFlag = entity.getSmssend();
		String sms1Flag = entity.getSms1flag();
		String email1Yn = entity.getEmail1Yn();
		// 일괄,단독 SMS1차 발송인 경우 전송여부 설정값 적용 및 SMS 문구수정
		if(smsSendFlag.equals("1St") && sms1Flag.equals("Y") && email1Yn.equals("N")){
			String newSmsMsg = smsEntity.getSendmessage().toString().replace("세부사항은 e-mail 확인부탁드립니다", "");
			smsEntity.setSendmessage(newSmsMsg); 
			return;
		}

		// SMS2차 발송인경우만 결품안내(SMS1차 발송인경우 기존내용대로 입고안내만)
		if(smsSendFlag.equals("2St")){
			int maxShortageQty = 0;
			if(emailList.size() > 0) maxShortageQty = Integer.parseInt(emailList.get(0).get("MAXSHORTAGEQTY").toString());
			// 결품수량이 0인건 메일발송제외(대상: 미스켄, 스캔중, 스켄완료)
			if(maxShortageQty <= 0){
				String[] oldSmsMsg = smsEntity.getSendmessage().toString().split("\\.");
				String newSmsMsg = oldSmsMsg[0] + ". 금일 결품내역이 없습니다.";
				//SMS 문구변경
				smsEntity.setSendtitle("금일 결품내역 없음 안내");
				smsEntity.setSendmessage(newSmsMsg);
				return;
			} else {
				//Email 문구변경
				String oldTitle = entity.getTitle();
				String newTitle = oldTitle.replace("미입고", "결품");
				entity.setTitle(newTitle);
				entity.setSubtitle(" 결품내역 공유");
				//SMS 문구변경
				smsEntity.setSendtitle(entity.getSendtitle2());
				smsEntity.setSendmessage(entity.getSendmessage2());
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer html     = new StringBuffer();

		html.append("<form id='form1' runat='server'>   ").append("\\n");
		html.append("<div style='width: 1825px; font-family: Malgun Gothic; font-size: 12px;'>   ").append("\\n");
		html.append("   <div style='background: url('https://safs.ifresh.co.kr/com/images/mail_top_img.png'); width: 1825px; height: 86px;'></div>").append("\\n");
		html.append("   <div style='width: 1790px; color: rgb(41, 180, 213); padding-bottom: 4px; padding-left: 15px; font-size: 18px; font-weight: bold; margin-top: 27px; margin-left: 20px; border-bottom-color: currentColor; border-bottom-width: 2px; border-bottom-style: solid;'>").append("\\n");
		html.append("      [알림 메일 보내드립니다.]   ").append("\\n");
		html.append("   </div>   ").append("\\n");
		html.append("   <div style='border: 1px solid transparent; border-image: none;'>   ").append("\\n");
		html.append("      <ul style='width: 1825px; padding-left: 20px !important; list-style-type: none;'>").append("\\n");
		html.append("         <li style='width: 1805px; line-height: 20px; padding-top: 10px; padding-bottom: 3px; border-bottom-color: rgb(181, 181, 181); border-bottom-width: 2px; border-bottom-style: solid; display: inline-block;'>").append("\\n");
		html.append("            <div style='width: 100px; padding-left: 20px; font-size: 12px; font-weight: bold; float: left; display: inline-block;'>").append("\\n");
		html.append("               제목   ").append("\\n");
		html.append("            </div>   ").append("\\n");
		html.append("            <div style='width: 1665px; height: 100%; font-size: 12px; float: left;'>").append(entity.getNdate()).append(entity.getSubtitle()).append(" </div>").append("\\n");
		html.append("         </li>   ").append("\\n");
		html.append("      </ul>   ").append("\\n");
		html.append("   </div>   ").append("\\n");
		html.append("   <div style='width: 1805px; line-height: 24px; clear: both; margin-bottom: 10px; margin-left: 20px; border-bottom-color: rgb(181, 181, 181); border-bottom-width: 2px; border-bottom-style: solid;'>").append("\\n");
		html.append("      <div style='padding-top: 10px; padding-bottom: 100px; padding-left: 20px; font-size: 12px;'>").append("\\n");
		html.append("         <table class='tg' style='table-layout: fixed;'>   ").append("\\n");
		html.append("            <colgroup><col style='width: 50px;'><col style='width: 1215px;'></colgroup>").append("\\n");
		html.append("            <tbody>   ").append("\\n");
		html.append("               <tr>   ").append("\\n");
		html.append("                  <th class='tg-031e' style='font-weight: bold;'>수신자</th>   ").append("\\n");
		html.append("                  <td class='tg-031e' style='text-align: left;'>").append(entity.getReceivename()).append("<a>(</a>").append(entity.getReceiveemail()).append(")</td>").append("\\n");
		html.append("               </tr>   ").append("\\n");
		html.append("               <tr>   ").append("\\n");
		html.append("                  <th class='tg-031e' style='font-weight: bold;'></th>   ").append("\\n");
		html.append("                  <td class='tg-031e' style='text-align: left;'><br>   ").append("\\n");
		html.append("                  </td>   ").append("\\n");
		html.append("               </tr>   ").append("\\n");
		html.append("            </tbody>   ").append("\\n");
		html.append("         </table>   ").append("\\n");
		html.append("         <br>   ").append("\\n");
		html.append("         <table class='tg' style='width: 1675px; table-layout: fixed;'>   ").append("\\n");
		html.append("            <colgroup><col style='width: 50px;'><col style='width: 80px;'><col style='width: 30px;'><col style='width: 80px;'><col style='width: 80px;'><col style='width: 80px;'><col style='width: 80px;'><col style='width: 40px;'><col style='width: 70px;'><col style='width: 120px;'><col style='width: 70px;'><col style='width: 160px;'><col style='width: 65px;'><col style='width: 170px;'><col style='width: 50px;'><col style='width: 50px;'><col style='width: 50px;'><col style='width: 70px;'><col style='width: 70px;'><col style='width: 70px;'><col style='width: 100px;'></colgroup>").append("\\n");
		html.append("            <tbody>   ").append("\\n");
		html.append("               <tr>   ").append("\\n");
		html.append("                  <th class='tg-031e'>차수</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>협력사명</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>NO</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>입고전표</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>물류센터</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>납품일</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>주문번호</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>주문<br>순번</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>판매처</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>판매처명</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>관리처</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>관리처명</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>상품코드</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>상품내역</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>상품<br>&nbsp;단위</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>주문량</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>납품량</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>미입고량</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>결품량</th>   ").append("\\n");
		html.append("                  <th class='tg-031e'>결품사유</th>   ").append("\\n");
		html.append("               </tr>   ").append("\\n");

		for(int i = 0 ; i < emailList.size() ; i++) {
		    html.append("               <tr>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: right;'>").append(entity.getSmscnt()).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: right;'>").append(entity.getCustname()).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: right;'>").append(i+1).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: center;'>").append(emailList.get(i).get("DOCNO")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: left;'>").append(emailList.get(i).get("DCNAME")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: center;'>").append(emailList.get(i).get("DOCDT")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: right;'>").append(emailList.get(i).get("ORDERNO")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: right;'>").append(emailList.get(i).get("ORDERSEQ")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: center;'>").append(emailList.get(i).get("SELLERCD")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: left;'>").append(emailList.get(i).get("SELLERNM")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: center;'>").append(emailList.get(i).get("ADMINICD")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: left;'>").append(emailList.get(i).get("ADMININM")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: center;'>").append(emailList.get(i).get("SKU")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: left;'>").append(emailList.get(i).get("SKUNAME")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: center;'>").append(emailList.get(i).get("UOM")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: right;'>").append(emailList.get(i).get("ORDERQTY")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: right;'>").append(emailList.get(i).get("INSPECTQTY")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: right;'>").append(emailList.get(i).get("NOTINQTY")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: right;'>").append(emailList.get(i).get("SHORTAGEQTY")).append("</td>   ").append("\\n");
		    html.append("                  <td class='tg-031e' style='text-align: left;'>").append(emailList.get(i).get("REASONCODE")).append("</td>   ").append("\\n");
		    html.append("               </tr>   ").append("\\n");
		}
		html.append("            </tbody>   ").append("\\n");
		html.append("         </table>   ").append("\\n");
		html.append("         <p style='line-height: 1.2; font-family: 굴림; font-size: 12pt; margin-top: 0px; margin-bottom: 0px;'>&nbsp;</p>").append("\\n");
		html.append("      </div>   ").append("\\n");
		html.append("   </div>   ").append("\\n");
		html.append("   <div><span class='copyright' style='color: rgb(41, 180, 213); padding-left: 21px; font-size: 10px;'>copyright(c) 2015 by CJ Freshway. ALL rights reserved.</span></div>").append("\\n");
		html.append("</div>   ").append("\\n");
		html.append("</form>   ").append("\\n");
		html.toString();

		
		entity.setCnts(html.toString());
		entity.setTitle(smsEntity.getSendtitle());
		entity.setRefEmailAdd("");
		entity.setRcvrEmailAddr(entity.getReceiveemail());
		entity.setRcvrNm(entity.getReceivename());

		int rs = commonDao.insert(SERVICEID_PREFIX +"insertEmailLog", entity);
		
		return;
	}
	
    /**
     * 인쇄 - 마스터 그리드
     */
    public List<KpDpInspectMonitoringPrintResDto> getPrintData(KpDpInspectMonitoringReqDto dto) {
    	List<KpDpInspectMonitoringPrintResDto> rsList = new ArrayList<>();
    	
    	for (KpDpInspectMonitoringReqDto reqDto : dto.getReqList() ) {
    		rsList.addAll(commonDao.selectList(SERVICEID_PREFIX + "getPrintData", reqDto));
    	}
    	
        return rsList;
    }
    

	
	/**
	 * @description : 입고검수현황 엑셀자료 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<KpDpInspectMonitoringExcelResDto> getDataExcelList(KpDpInspectMonitoringReqDto dto) {
		
		return commonDao.selectList(SERVICEID_PREFIX + "getDataExcelList", dto);
	}
    
	/**
	 * @description : 집계PDP 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<KpDpInspectMonitoringSumResDto> getDpInspectSumPDP(KpDpInspectMonitoringReqDto KpDpInspectMonitoringReqDto) {
		// 검색 기준에 따른 쿼리 분류
			return commonDao.selectList(SERVICEID_PREFIX + "getDpInspectSumPDP", KpDpInspectMonitoringReqDto);
	}
	
	
	private Map<String, Object> getInspectCompList(KpDpInspectMonitoringResDto KpDpInspectMonitoringReqDto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getInspectCompList", KpDpInspectMonitoringReqDto);
	}
	

}



