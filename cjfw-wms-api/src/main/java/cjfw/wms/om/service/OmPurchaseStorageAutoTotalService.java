package cjfw.wms.om.service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.dto.CmSendEmailReqDto;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.service.CmEmailService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.kp.dto.KpKxCloseReqDto;
import cjfw.wms.kp.dto.KpKxCloseResT8Dto;
import cjfw.wms.om.dto.OmPurchaseModifyReqDto;
import cjfw.wms.om.dto.OmPurchaseModifyResDto;
import cjfw.wms.om.dto.OmPurchaseStorageAutoTotalDetailLeftResDto;
import cjfw.wms.om.dto.OmPurchaseStorageAutoTotalDetailOrderResDto;
import cjfw.wms.om.dto.OmPurchaseStorageAutoTotalDetailRightResDto;
import cjfw.wms.om.dto.OmPurchaseStorageAutoTotalReqDto;
import cjfw.wms.om.dto.OmPurchaseStorageAutoTotalResDto;
import cjfw.wms.om.entity.OmPurchaseModifyEntity;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCM;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCMIF_DM_DOCUMENT_D;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCMIF_DM_DOCUMENT_H;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCM_responseT_RETURN;
import cjfw.wms.webservice.mmStoIf.SI_SCM0160_SCM_SOProxy;
import cjfw.wms.webservice.pocancel.DT_SCM0391_SCM;
import cjfw.wms.webservice.pocancel.DT_SCM0391_SCMT_REQUEST_CANCEL;
import cjfw.wms.webservice.pocancel.DT_SCM0391_SCM_responseT_RETURN;
import cjfw.wms.webservice.pocancel.SI_SCM0391_SCM_SOProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *  
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.10.14
 * @description : 저장품자동발주 기능을 구현한 Service Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmPurchaseStorageAutoTotalService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omPurchaseStorageAutoTotalService.";
	private transient static final String PAKAGE_NAME = "SPDP_PURCHASE";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 *  공통.Service
	 */
	private final CmCommonService cmCommonService;
	
	/** 공통.이메일.service */
	private final CmEmailService cmEmailService;
	
	private final OmPurchaseModifyService omPurchaseModifyService;

	/**
	 * @description : 저장품자동발주 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmPurchaseStorageAutoTotalResDto> getMasterList(OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		log.info("▶omPurchaseStorageAutoTotalReqDto->{}",omPurchaseStorageAutoTotalReqDto);
		List<OmPurchaseStorageAutoTotalResDto> resultList = new ArrayList<OmPurchaseStorageAutoTotalResDto>();
		
		if(omPurchaseStorageAutoTotalReqDto.getStoout() != null && omPurchaseStorageAutoTotalReqDto.getStoout().equals("Y")) {
			// 외부창고
			resultList = commonDao.selectList(SERVICEID_PREFIX + "getMasterListOut", omPurchaseStorageAutoTotalReqDto);
		} else {
			// 내부창고
			resultList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", omPurchaseStorageAutoTotalReqDto);
		}
		
		return resultList;
	}
	
	/**
	 * @description : 저장품자동발주 상세(왼쪽) 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmPurchaseStorageAutoTotalDetailLeftResDto> getDetailLeftInfo(OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailLeftInfo", omPurchaseStorageAutoTotalReqDto);
	}
	
	/**
	 * @description : 저장품자동발주 상세(주문) 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmPurchaseStorageAutoTotalDetailOrderResDto> getDetailOrderInfo(OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailOrderInfo", omPurchaseStorageAutoTotalReqDto);
	}
	
	/**
	 * @description : 저장품자동발주 상세(오른쪽) 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmPurchaseStorageAutoTotalDetailRightResDto> getDetailRightInfo(OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailRightInfo", omPurchaseStorageAutoTotalReqDto);
	}
	
	/**
	 * @description : 입출고 당일이후 집계 최종 생성시간 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public OmPurchaseStorageAutoTotalResDto getLastCreation(OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getLastCreation", omPurchaseStorageAutoTotalReqDto);
	}
	
	/**
	 * @description : 수발주 입출고 내역 갱신
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String recreationPurchaseTotal(OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		commonDao.selectOne(SERVICEID_PREFIX + "recreationPurchaseTotal", omPurchaseStorageAutoTotalReqDto);
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	/**
	 *  이체(STO) 발주 처리 
	 *   - 직송 및 이체 발주 저장
	 */
	@Transactional
	public String savePurchaseSTO(OmPurchaseStorageAutoTotalReqDto paramDto) throws Exception {		
		// 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        List<OmPurchaseStorageAutoTotalReqDto> saveList = paramDto.getSaveList(); // 저장리스트        
        
        log.info("▶saveList.size->{}",saveList);
        
        /*1. 임시테이블 삭제*/
        commonDao.delete(SERVICEID_PREFIX + "deleteTempTable", paramDto);
        
        /*2. 임시테이블 저장*/
		int bulkCnt =0;
		List<OmPurchaseStorageAutoTotalReqDto> list = new ArrayList<OmPurchaseStorageAutoTotalReqDto>();
		for (OmPurchaseStorageAutoTotalReqDto dto : paramDto.getSaveList()) { 
        	bulkCnt++;
        	
        	dto.setPackagename(PAKAGE_NAME); // 패키지명
        	dto.setStatus("00");
        	log.info("▶dto->{}",dto);
        	
        	list.add(dto);
            if (bulkCnt % 100 == 0) {
            	commonDao.insert(SERVICEID_PREFIX + "insertTempTable", list); 
            	list.clear();
            }
        }
        log.info("▶list.size : {}", list.size());
        if (list.size() > 0) { // 나머지
        	commonDao.insert(SERVICEID_PREFIX + "insertTempTable", list); 
        }
        /*END.Temp Table Insert*/
        
        /*START.PAKAGE 호출*/
		// PKG 파라마터 세팅 - 공통(1/4)
        OmPurchaseStorageAutoTotalResDto dto = new OmPurchaseStorageAutoTotalResDto();
		ProcedureParametersFactory.initParamDto(paramDto,dto, PAKAGE_NAME);
        
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList = {"PROCEDURE","PROCESSTYPE","PROCESSCREATOR","SPID","STORERKEY","RUNMODE"};
		Object[] valueList = {PAKAGE_NAME,"OM_TEMPORDER", dto.getGUserId(), dto.getGSpid(),dto.getStorerkey(),"MANUAL"};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		dto.setAvc_COMMAND("REQUESTSTO");
		dto.setAvc_EXECUTEMODE("NOCOMMIT");
		int rv = cmCommonService.saveProcedure(dto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(dto.getResultCode());
		resultMessage = StringUtil.nvl((String)dto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶저장품자동발주 - 발주요청(STO) 시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"저장품자동발주-발주요청(STO)"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/
		
		// SCM0160 실행
		// 1건씩 처리됨
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("userId", userContext.getUserId());
		List<HashMap> ifHeader = commonDao.selectList(SERVICEID_PREFIX + "getScm0160Header", headerMap);
		
		int headerCnt = ifHeader.size();
		String resultItemMsg = "";
		
		if(headerCnt > 0) {
			// 날짜 포멧팅
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
			
			SI_SCM0160_SCM_SOProxy proxy = new SI_SCM0160_SCM_SOProxy();
			DT_SCM0160_SCM mmData = null;
			DT_SCM0160_SCMIF_DM_DOCUMENT_H header = null;
			
			try {
				for(int i = 0; i < headerCnt; i++) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("searchIfFlag", "N");
					map.put("updateIfFlag", "P");
					map.put("updateIfMemo", "");
					map.put("docNo", ifHeader.get(i).get("DOCNO").toString());

					// 1건씩 처리됨
					commonDao.update(SERVICEID_PREFIX + "updateScm0160Header", map);
					commonDao.update(SERVICEID_PREFIX + "updateScm0160Detail", map);
					
					mmData = new DT_SCM0160_SCM();
					mmData.setXSYS("SCM");
					mmData.setXDATS(dateFormat.format(calendar.getTime()));
					mmData.setXTIMS(timeFormat.format(calendar.getTime()));
					mmData.setXROWS("1");
						
					header = new DT_SCM0160_SCMIF_DM_DOCUMENT_H();

					header.setORDERTYPE(ifHeader.get(i).get("ORDERTYPE").toString());
					header.setDCCODE(ifHeader.get(i).get("DCCODE").toString());
					header.setDOCNO(ifHeader.get(i).get("DOCNO").toString());
					header.setFROM_BILLTOKEY("FW00");
					header.setSTORERKEY(ifHeader.get(i).get("STORERKEY").toString());
					header.setSHOPPINGMALL("Z01");
					
					// 실제로 실적전송
					mmData.setIF_DM_DOCUMENT_H(header);
					
					Map<String, String> detailMap = new HashMap<String, String>();
					detailMap.put("docNo", ifHeader.get(i).get("DOCNO").toString());
					
					List<HashMap> ifDetail = commonDao.selectList(SERVICEID_PREFIX + "getScm0160Detail", detailMap);
					
					if(ifDetail.size() > 0) {
						int detailCnt = ifDetail.size();
							
						// 실적 Detail정보 설정
						DT_SCM0160_SCMIF_DM_DOCUMENT_D[] detailList = new DT_SCM0160_SCMIF_DM_DOCUMENT_D[detailCnt];
						DT_SCM0160_SCMIF_DM_DOCUMENT_D detail  = null;
							
						for(int j = 0; j < detailCnt; j++) {
							detail = new DT_SCM0160_SCMIF_DM_DOCUMENT_D();
							
							detail.setDOCNO(ifDetail.get(j).get("DOCNO").toString());
							detail.setDOCLINE(ifDetail.get(j).get("DOCLINE").toString());
							detail.setDEL_YN(ifDetail.get(j).get("DEL_YN").toString());
							detail.setPLANT(ifDetail.get(j).get("PLANT").toString());
							detail.setSTORAGELOC((String) ifDetail.get(j).get("STORAGELOC"));
							detail.setSKU(ifDetail.get(j).get("SKU").toString());
							detail.setSTORERORDERQTY(ifDetail.get(j).get("STORERORDERQTY").toString());
							detail.setSTORERUOM(ifDetail.get(j).get("STORERUOM").toString());
							detail.setDELIVERYDATE(ifDetail.get(j).get("DELIVERYDATE").toString());

							detailList[j] = detail;
						}						
						mmData.setIF_DM_DOCUMENT_D(detailList);

						DT_SCM0160_SCM_responseT_RETURN[] response = null;
						response = proxy.si_scm0160_scm_so(mmData);

						// 응답에 대한 결과 처리
						if(response != null && response.length > 0) {
							if("E".equals(response[0].getXSTAT())) {
								throw new UserHandleException("인터페이스 처리중 에러발생["+response[0].getXMSGS()+"]");
							}
						} else {
							throw new UserHandleException("인터페이스 처리중 에러발생[실적자료 전송중 오류발생]");
						}
					} else {
						throw new UserHandleException("인터페이스 처리중 에러발생[STO예정 문서 Detail 정보를 찾을 수 없습니다.]");
					}
					
					Map<String, String> mapIFResult = new HashMap<String, String>();
					mapIFResult.put("searchIfFlag", "P");
					mapIFResult.put("updateIfFlag", "Y");
					mapIFResult.put("updateIfMemo", "");
					mapIFResult.put("docNo", ifHeader.get(i).get("DOCNO").toString());
					
					// 처리결과 업데이트
					commonDao.update(SERVICEID_PREFIX + "updateScm0160Header", mapIFResult);
					commonDao.update(SERVICEID_PREFIX + "updateScm0160Detail", mapIFResult);
				}
			} catch(UserHandleException e) {
				e.printStackTrace();
				throw new UserHandleException(e.getErrorMessage());
			} catch(Exception e) {
				e.printStackTrace();
				throw new UserHandleException(e);
			}			
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 *  이체 발주 처리
	 */
	@Transactional
	public String savePurchase(OmPurchaseStorageAutoTotalReqDto paramDto) throws Exception {
		
		// 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        List<OmPurchaseStorageAutoTotalReqDto> saveList = paramDto.getSaveList(); // 저장리스트        
        
        log.info("▶saveList.size->{}",saveList);
        
        /*1. 임시테이블 삭제*/
        commonDao.delete(SERVICEID_PREFIX + "deleteTempTablePO", paramDto);
        
        /*2. 임시테이블 저장*/
		int bulkCnt =0;
		List<OmPurchaseStorageAutoTotalReqDto> list = new ArrayList<OmPurchaseStorageAutoTotalReqDto>();
		for (OmPurchaseStorageAutoTotalReqDto dto : paramDto.getSaveList()) { 
        	bulkCnt++;
        	
        	dto.setPackagename(PAKAGE_NAME); // 패키지명
        	log.info("▶dto->{}",dto);
        	
        	list.add(dto);
            if (bulkCnt % 100 == 0) {
            	commonDao.insert(SERVICEID_PREFIX + "insertTempTablePO", list); 
            	list.clear();
            }
        }
        log.info("▶list.size : {}", list.size());
        if (list.size() > 0) { // 나머지
        	commonDao.insert(SERVICEID_PREFIX + "insertTempTablePO", list); 
        }
        /*END.Temp Table Insert*/
        
        /*START.PAKAGE 호출*/
		// PKG 파라마터 세팅 - 공통(1/4)
        OmPurchaseStorageAutoTotalResDto dto = new OmPurchaseStorageAutoTotalResDto();
		ProcedureParametersFactory.initParamDto(paramDto,dto, PAKAGE_NAME);
        
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList = {"PROCEDURE","PROCESSTYPE","PROCESSCREATOR","SPID","STORERKEY","RUNMODE"};
		Object[] valueList = {PAKAGE_NAME,"PO_REQUEST", dto.getGUserId(), dto.getGSpid(),dto.getStorerkey(),"MANUAL"};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		dto.setAvc_COMMAND("REQUESTPURCHASE");
		dto.setAvc_EXECUTEMODE("NOCOMMIT");
		int rv = cmCommonService.saveProcedure(dto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(dto.getResultCode());
		resultMessage = StringUtil.nvl((String)dto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶저장품자동발주 - 발주요청(PO) 시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"저장품자동발주-발주요청(PO)"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 *  외부창고 이체(STO) 발주 처리 
	 *   - 직송 및 이체 발주 저장
	 */
	@Transactional
	public String savePurchaseOutSTO(OmPurchaseStorageAutoTotalReqDto paramDto) throws Exception {
		
		// 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        List<OmPurchaseStorageAutoTotalResDto> saveList = paramDto.getSaveOutSTOList(); // 저장리스트        
        
        log.info("▶saveList.size->{}",saveList);
        
        /*1. 임시테이블 삭제*/
        commonDao.delete(SERVICEID_PREFIX + "deleteTempTable", paramDto);
        
        /*2. 임시테이블 저장*/
		for (OmPurchaseStorageAutoTotalResDto dto : paramDto.getSaveOutSTOList()) { 
			commonDao.insert(SERVICEID_PREFIX + "insertTempTableOut", dto);
        }
        /*END.Temp Table Insert*/
        
        /*START.PAKAGE 호출*/
		// PKG 파라마터 세팅 - 공통(1/4)
        OmPurchaseStorageAutoTotalResDto dto = new OmPurchaseStorageAutoTotalResDto();
		ProcedureParametersFactory.initParamDto(paramDto,dto, PAKAGE_NAME);
        
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList = {"PROCEDURE","PROCESSTYPE","PROCESSCREATOR","SPID","STORERKEY","RUNMODE"};
		Object[] valueList = {PAKAGE_NAME,"OM_TEMPORDER", dto.getGUserId(), dto.getGSpid(),dto.getStorerkey(),"MANUAL"};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		dto.setAvc_COMMAND("REQUESTSTO");
		dto.setAvc_EXECUTEMODE("NOCOMMIT");
		int rv = cmCommonService.saveProcedure(dto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(dto.getResultCode());
		resultMessage = StringUtil.nvl((String)dto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶저장품자동발주 - 외부창고 발주요청(STO) 시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"저장품자동발주-외부창고 발주요청(STO)"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/
		
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("userId", userContext.getUserId());		

		List<HashMap> ifHeader = commonDao.selectList(SERVICEID_PREFIX + "getScm0160Header", headerMap);
		int headerCnt = ifHeader.size();
		
		if(headerCnt > 0) {
			// 날짜 포멧팅
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
			try{
				SI_SCM0160_SCM_SOProxy proxy = new SI_SCM0160_SCM_SOProxy();
				DT_SCM0160_SCM mmData = null;
				DT_SCM0160_SCMIF_DM_DOCUMENT_H header = null;
				
				for(int i = 0; i < headerCnt; i++) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("searchIfFlag", "N");
					map.put("updateIfFlag", "P");
					map.put("docNo", ifHeader.get(i).get("DOCNO").toString());

					// 1건씩 처리됨
					commonDao.update(SERVICEID_PREFIX + "updateScm0160Header", map);
					commonDao.update(SERVICEID_PREFIX + "updateScm0160Detail", map);					
					
					mmData = new DT_SCM0160_SCM();
					mmData.setXSYS("SCM");
					mmData.setXDATS(dateFormat.format(calendar.getTime()));
					mmData.setXTIMS(timeFormat.format(calendar.getTime()));
					mmData.setXROWS("1");
						
					header = new DT_SCM0160_SCMIF_DM_DOCUMENT_H();
			
					header.setORDERTYPE(ifHeader.get(i).get("ORDERTYPE").toString());
					header.setDCCODE(ifHeader.get(i).get("DCCODE").toString());
					header.setDOCNO(ifHeader.get(i).get("DOCNO").toString());
					header.setFROM_BILLTOKEY("FW00");
					header.setSTORERKEY(ifHeader.get(i).get("STORERKEY").toString());
					header.setSHOPPINGMALL("Z01");
							
					// 실제로 실적전송
					mmData.setIF_DM_DOCUMENT_H(header);
					
					Map<String, String> detailMap = new HashMap<String, String>();
					detailMap.put("docNo", ifHeader.get(i).get("DOCNO").toString());
					
					List<HashMap> ifDetail = commonDao.selectList(SERVICEID_PREFIX + "getScm0160Detail", detailMap);
					int detailCnt = ifDetail.size();
					
					if(detailCnt > 0) {
						DT_SCM0160_SCMIF_DM_DOCUMENT_D[] detailList = new DT_SCM0160_SCMIF_DM_DOCUMENT_D[detailCnt];
						DT_SCM0160_SCMIF_DM_DOCUMENT_D detail  = null;
						
						for(int j = 0; j < detailCnt; j++) {
							detail = new DT_SCM0160_SCMIF_DM_DOCUMENT_D();
							
							detail.setDOCNO(ifDetail.get(j).get("DOCNO").toString());
							detail.setDOCLINE(ifDetail.get(j).get("DOCLINE").toString());
							detail.setDEL_YN(ifDetail.get(j).get("DEL_YN").toString());
							detail.setPLANT(ifDetail.get(j).get("PLANT").toString());
							detail.setSTORAGELOC((String) ifDetail.get(j).get("STORAGELOC"));
							detail.setSKU(ifDetail.get(j).get("SKU").toString());
							detail.setSTORERORDERQTY(ifDetail.get(j).get("STORERORDERQTY").toString());
							detail.setSTORERUOM(ifDetail.get(j).get("STORERUOM").toString());
							detail.setDELIVERYDATE(ifDetail.get(j).get("DELIVERYDATE").toString());
							detail.setOTHER02("X"); // 삭제
							
							detailList[j] = detail;
						}
						mmData.setIF_DM_DOCUMENT_D(detailList);
						DT_SCM0160_SCM_responseT_RETURN[] response = null;
						response = proxy.si_scm0160_scm_so(mmData);
						
						// 응답에 대한 결과 처리
						if(response != null && response.length > 0) {
							if("E".equals(response[0].getXSTAT())) {
								throw new UserHandleException("인터페이스 처리중 에러발생["+response[0].getXMSGS()+"]");
							}
						} else {
							throw new UserHandleException("인터페이스 처리중 에러발생[실적자료 전송중 오류발생]");
						}
					} else {
						throw new UserHandleException("인터페이스 처리중 에러발생[STO예정 문서 Detail 정보를 찾을 수 없습니다.]");
					}
					
					Map<String, String> mapResult = new HashMap<String, String>();
					mapResult.put("searchIfFlag", "P");
					mapResult.put("updateIfFlag", "Y");
					mapResult.put("updateIfMemo", "");
					mapResult.put("docNo", ifHeader.get(i).get("DOCNO").toString());
					
					// 처리결과 업데이트
					commonDao.update(SERVICEID_PREFIX +"updateScm0160Header", mapResult);
					commonDao.update(SERVICEID_PREFIX +"updateScm0160Detail", mapResult);
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
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 *  조달 임시 저장
	 */
	public String tmpSave(OmPurchaseStorageAutoTotalReqDto paramDto) throws Exception {
		
        List<OmPurchaseStorageAutoTotalReqDto> saveList = paramDto.getSaveList(); // 저장리스트        
        
        log.info("▶saveList.size->{}",saveList);
        
        /*1. 임시테이블 삭제*/
        commonDao.delete(SERVICEID_PREFIX + "deleteTempTableProc", paramDto);
        
        /*2. 임시테이블 저장*/
		int bulkCnt =0;
		List<OmPurchaseStorageAutoTotalReqDto> list = new ArrayList<OmPurchaseStorageAutoTotalReqDto>();
		for (OmPurchaseStorageAutoTotalReqDto dto : paramDto.getSaveList()) { 
        	bulkCnt++;
        	
        	dto.setPackagename(PAKAGE_NAME); // 패키지명
        	dto.setStatus("10");
        	dto.setPoType("PROC-PO");
        	log.info("▶dto->{}",dto);
        	
        	list.add(dto);
            if (bulkCnt % 100 == 0) {
            	commonDao.insert(SERVICEID_PREFIX + "insertTempTableProc", list); 
            	list.clear();
            }
        }
        log.info("▶list.size : {}", list.size());
        if (list.size() > 0) { // 나머지
        	commonDao.insert(SERVICEID_PREFIX + "insertTempTableProc", list); 
        }
        /*END.Temp Table Insert*/
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 입출고집계 최종 생성시간 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
//	public List<OmPurchaseStorageAutoTotalResDto> getLastCreation(OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
//		return commonDao.selectList(SERVICEID_PREFIX + "getLastCreation", omPurchaseStorageAutoTotalReqDto);
//	}
	
	/**
	 * @description : 수발주 입출고 내역 갱신 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
//	public String recreationPurchaseTotal(OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
//		commonDao.update(SERVICEID_PREFIX + "recreationPurchaseTotal", omPurchaseStorageAutoTotalReqDto);
//		return CanalFrameConstants.MSG_COM_SUC_CODE;
//	}
	
	/**
	 * @description : 코드정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
//	public List<OmPurchaseStorageAutoTotalResDto> getIdSelect(OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
//		return commonDao.selectList(SERVICEID_PREFIX + "getIdSelect", omPurchaseStorageAutoTotalReqDto);
//	}
	
	/**
	 * @description : 수급 보조지표 이미지 호출 로그 생성
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveSuppImgLog(OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		//logKey 20자리 랜덤 알파벳+숫자 셋팅
		omPurchaseStorageAutoTotalReqDto.setLogKey(generateRandomAlphaNumeric(20));
				
		commonDao.insert(SERVICEID_PREFIX + "saveSuppImgLog", omPurchaseStorageAutoTotalReqDto);
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	private static final String ALPHANUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final SecureRandom RANDOM = new SecureRandom();

	private static String generateRandomAlphaNumeric(int length) {
	    StringBuilder sb = new StringBuilder(length);
	    for (int i = 0; i < length; i++) {
	        sb.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
	    }
	    return sb.toString();
	}
	
	@Transactional
	public String reorderMasterPO(OmPurchaseModifyReqDto paramDto) throws Exception {
//		String SERVICEID_PREFIX = "omPurchaseModify.";
		
		// 삭체 후 재발주 처리
		List<OmPurchaseModifyReqDto> saveList = paramDto.getSaveList();		
		log.info("▶saveList.size->{}",saveList);
		try {
			String resultItemMsg = omPurchaseModifyService.deleteMasterPO(paramDto);
			log.info("▶reorderMasterPO resultItemMsg->{}",resultItemMsg);
		} catch (Exception e) {
		    log.error("Error!", e);
		    throw e;
		}

		// 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        /*1. 임시테이블 삭제*/
        commonDao.delete(SERVICEID_PREFIX + "deleteTempTablePOReorder", paramDto);
        
        /*2. 임시테이블 저장*/
		int bulkCnt =0;
		List<OmPurchaseModifyReqDto> list = new ArrayList<OmPurchaseModifyReqDto>();
		for (OmPurchaseModifyReqDto dto : paramDto.getSaveList()) {
        	bulkCnt++;
        	
        	dto.setPackagename(PAKAGE_NAME); // 패키지명
        	dto.setSlipDt(dto.getModSlipDt()); // 전표일자 세팅 
        	dto.setRequestDt(dto.getModSlipDt()); // 변경입고일 세팅
        	log.info("▶dto->{}",dto);
        	
        	list.add(dto);
            if (bulkCnt % 100 == 0) {
            	commonDao.insert(SERVICEID_PREFIX + "insertTempTablePOReorder", list); 
            	list.clear();
            }
        }
        log.info("▶list.size : {}", list.size());
        if (list.size() > 0) { // 나머지
        	commonDao.insert(SERVICEID_PREFIX + "insertTempTablePOReorder", list); 
        }
        /*END.Temp Table Insert*/
        
        /*START.PAKAGE 호출*/
		// PKG 파라마터 세팅 - 공통(1/4)
        OmPurchaseModifyResDto dto = new OmPurchaseModifyResDto();
		ProcedureParametersFactory.initParamDto(paramDto,dto, PAKAGE_NAME);
        
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE" ,"PROCESSTYPE","TEMPTABLETYPE"     , "DCCODE"        , "STORERKEY"};
		Object[] valueList = {PAKAGE_NAME ,"PO_REQUEST" ,"SY_PROCESSTEMP_PO" ,dto.getGDccode() ,dto.getGStorerkey()};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		dto.setAvc_COMMAND("REQUESTPURCHASE");
		dto.setAvc_EXECUTEMODE("NOCOMMIT");
		int rv = cmCommonService.saveProcedure(dto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(dto.getResultCode());
		resultMessage = StringUtil.nvl((String)dto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶저장품자동발주 - 발주요청(PO) 시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"저장품자동발주-발주요청(PO)"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	@Transactional
	public String reorderMasterSTO(OmPurchaseModifyReqDto paramDto) throws Exception {
		
		OmPurchaseModifyReqDto dto = paramDto;

		try {
			String resultItemMsg = omPurchaseModifyService.deleteMasterSTO(dto);
			log.info("▶deleteMasterSTO resultItemMsg->{}",resultItemMsg);
		} catch (Exception e) {
		    log.error("Error!", e);
		    throw e;
		}		
		
		List<OmPurchaseModifyReqDto> saveList = dto.getSaveList();
		
		// 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        log.info("▶saveList.size->{}",saveList);
        
        /*1. 임시테이블 삭제*/
        commonDao.delete(SERVICEID_PREFIX + "deleteTempTableSTOReorder", paramDto);
        
        /*2. 임시테이블 저장*/
		int bulkCnt =0;
		List<OmPurchaseModifyReqDto> list = new ArrayList<OmPurchaseModifyReqDto>();
		for (OmPurchaseModifyReqDto reqDto : paramDto.getSaveList()) {
        	bulkCnt++;
        	
        	reqDto.setPackagename(PAKAGE_NAME); // 패키지명
        	reqDto.setSlipDt(reqDto.getModSlipDt()); // 전표일자 세팅 
        	reqDto.setRequestDt(reqDto.getModSlipDt()); // 변경입고일 세팅
        	log.info("▶dto->{}",reqDto);
        	
        	list.add(reqDto);
            if (bulkCnt % 100 == 0) {
            	commonDao.insert(SERVICEID_PREFIX + "insertTempTableSTOReorder", list); 
            	list.clear();
            }
        }
        log.info("▶list.size : {}", list.size());
        if (list.size() > 0) { // 나머지
        	commonDao.insert(SERVICEID_PREFIX + "insertTempTableSTOReorder", list); 
        }
        /*END.Temp Table Insert*/
        
        /*START.PAKAGE 호출*/
		// PKG 파라마터 세팅 - 공통(1/4)
        OmPurchaseModifyResDto ModDto = new OmPurchaseModifyResDto();
		ProcedureParametersFactory.initParamDto(paramDto,ModDto, PAKAGE_NAME);
        
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE" ,"PROCESSTYPE" ,"TEMPTABLETYPE"    , "DCCODE"       , "STORERKEY"};
		Object[] valueList = {PAKAGE_NAME ,"OM_TEMPORDER","SY_PROCESSTEMP_ST",ModDto.getGDccode(), ModDto.getGStorerkey()};
		ModDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		ModDto.setAvc_COMMAND("REQUESTSTO");
		ModDto.setAvc_EXECUTEMODE("NOCOMMIT");
		int rv = cmCommonService.saveProcedure(ModDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(ModDto.getResultCode());
		resultMessage = StringUtil.nvl((String)ModDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶저장품자동발주 - 발주요청(STO) 시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"저장품자동발주-발주요청(STO)"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/
		
		// SCM0160 실행
		
		// 1건씩 처리됨
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("userId", userContext.getUserId());
		List<HashMap> ifHeader = commonDao.selectList("omPurchaseStorageAutoTotalService.getScm0160Header", headerMap);
		
		int headerCnt = ifHeader.size();
		
		if(headerCnt > 0) {
			// 날짜 포멧팅
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
			
			SI_SCM0160_SCM_SOProxy proxy = new SI_SCM0160_SCM_SOProxy();
			DT_SCM0160_SCM mmData = null;
			DT_SCM0160_SCMIF_DM_DOCUMENT_H header = null;
			
			try {
				for(int i = 0; i < headerCnt; i++) {
					
					Map<String, String> map = new HashMap<String, String>();
					map.put("searchIfFlag", "N");
					map.put("updateIfFlag", "P");
					map.put("updateIfMemo", "");
					map.put("docNo", ifHeader.get(i).get("DOCNO").toString());

					// 1건씩 처리됨
					commonDao.update("omPurchaseStorageAutoTotalService.updateScm0160Header", map);
					commonDao.update("omPurchaseStorageAutoTotalService.updateScm0160Detail", map);
					
					mmData = new DT_SCM0160_SCM();
					mmData.setXSYS("SCM");
					mmData.setXDATS(dateFormat.format(calendar.getTime()));
					mmData.setXTIMS(timeFormat.format(calendar.getTime()));
					mmData.setXROWS("1");
						
					header = new DT_SCM0160_SCMIF_DM_DOCUMENT_H();

					header.setORDERTYPE(ifHeader.get(i).get("ORDERTYPE").toString());
					header.setDCCODE(ifHeader.get(i).get("DCCODE").toString());
					header.setDOCNO(ifHeader.get(i).get("DOCNO").toString());
					header.setFROM_BILLTOKEY("FW00");
					header.setSTORERKEY(ifHeader.get(i).get("STORERKEY").toString());
					header.setSHOPPINGMALL("Z01");
					
					// 실제로 실적전송
					mmData.setIF_DM_DOCUMENT_H(header);
					
					Map<String, String> detailMap = new HashMap<String, String>();
					detailMap.put("docNo", ifHeader.get(i).get("DOCNO").toString());
					
					List<HashMap> ifDetail = commonDao.selectList("omPurchaseStorageAutoTotalService.getScm0160Detail", detailMap);
					
					if(ifDetail.size() > 0) {
						int detailCnt = ifDetail.size();
							
						// 실적 Detail정보 설정
						DT_SCM0160_SCMIF_DM_DOCUMENT_D[] detailList = new DT_SCM0160_SCMIF_DM_DOCUMENT_D[detailCnt];
						DT_SCM0160_SCMIF_DM_DOCUMENT_D detail  = null;
							
						for(int j = 0; j < detailCnt; j++) {
							detail = new DT_SCM0160_SCMIF_DM_DOCUMENT_D();
							
							detail.setDOCNO(ifDetail.get(j).get("DOCNO").toString());
							detail.setDOCLINE(ifDetail.get(j).get("DOCLINE").toString());
							detail.setDEL_YN(ifDetail.get(j).get("DEL_YN").toString());
							detail.setPLANT(ifDetail.get(j).get("PLANT").toString());
							detail.setSTORAGELOC((String) ifDetail.get(j).get("STORAGELOC"));
							detail.setSKU(ifDetail.get(j).get("SKU").toString());
							detail.setSTORERORDERQTY(ifDetail.get(j).get("STORERORDERQTY").toString());
							detail.setSTORERUOM(ifDetail.get(j).get("STORERUOM").toString());
							detail.setDELIVERYDATE(ifDetail.get(j).get("DELIVERYDATE").toString());

							detailList[j] = detail;
						}						
						mmData.setIF_DM_DOCUMENT_D(detailList);

						DT_SCM0160_SCM_responseT_RETURN[] response = null;
						response = proxy.si_scm0160_scm_so(mmData);

						// 응답에 대한 결과 처리
						if(response != null && response.length > 0) {
							if("E".equals(response[0].getXSTAT())) {
								throw new UserHandleException("인터페이스 처리중 에러발생["+response[0].getXMSGS()+"]");
							}
						} else {
							throw new UserHandleException("인터페이스 처리중 에러발생[실적자료 전송중 오류발생]");
						}
					} else {
						throw new UserHandleException("인터페이스 처리중 에러발생[STO예정 문서 Detail 정보를 찾을 수 없습니다.]");
					}
					
					Map<String, String> mapIFResult = new HashMap<String, String>();
					mapIFResult.put("searchIfFlag", "P");
					mapIFResult.put("updateIfFlag", "Y");
					mapIFResult.put("updateIfMemo", "");
					mapIFResult.put("docNo", ifHeader.get(i).get("DOCNO").toString());
					
					// 처리결과 업데이트
					commonDao.update("omPurchaseStorageAutoTotalService.updateScm0160Header", mapIFResult);
					commonDao.update("omPurchaseStorageAutoTotalService.updateScm0160Detail", mapIFResult);
				}
			} catch(UserHandleException e) {
				e.printStackTrace();
				throw new UserHandleException(e.getErrorMessage());
			} catch(Exception e) {
				e.printStackTrace();
				throw new UserHandleException(e);
			}			
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	@Transactional
	public String reorderMasterOutSTO(OmPurchaseModifyReqDto paramDto) throws Exception {
		
		try {
			String resultItemMsg = omPurchaseModifyService.deleteMasterSTO(paramDto);
			log.info("▶deleteMasterSTO resultItemMsg->{}",resultItemMsg);
		} catch (Exception e) {
		    log.error("Error!", e);
		    throw e;
		}		
		
		List<OmPurchaseModifyReqDto> saveList = paramDto.getSaveList();
		
		// 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        log.info("▶saveList.size->{}",saveList);
        
        /*1. 임시테이블 삭제*/
        commonDao.delete(SERVICEID_PREFIX + "deleteTempTable", paramDto);
                
        /*2. 임시테이블 저장*/
		List<OmPurchaseModifyReqDto> list = new ArrayList<OmPurchaseModifyReqDto>();
		for (OmPurchaseModifyReqDto reqDto : paramDto.getSaveList()) {
           	commonDao.insert(SERVICEID_PREFIX + "insertTempTableOutReorder", reqDto); 
        }
        log.info("▶list.size : {}", list.size());
        /*END.Temp Table Insert*/
        
        /*START.PAKAGE 호출*/
        // PKG 파라마터 세팅 - 공통(1/4)
        OmPurchaseStorageAutoTotalResDto dto = new OmPurchaseStorageAutoTotalResDto();
		ProcedureParametersFactory.initParamDto(paramDto,dto, PAKAGE_NAME);
        
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList = {"PROCEDURE","PROCESSTYPE","PROCESSCREATOR","SPID","STORERKEY","RUNMODE"};
		Object[] valueList = {PAKAGE_NAME,"OM_TEMPORDER", dto.getGUserId(), dto.getGSpid(),dto.getStorerkey(),"MANUAL"};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		dto.setAvc_COMMAND("REQUESTSTO");
		dto.setAvc_EXECUTEMODE("NOCOMMIT");
		int rv = cmCommonService.saveProcedure(dto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(dto.getResultCode());
		resultMessage = StringUtil.nvl((String)dto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶저장품자동발주 - 외부창고 발주요청(STO) 시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"저장품자동발주-외부창고 발주요청(STO)"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/
		
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("userId", userContext.getUserId());		

		List<HashMap> ifHeader = commonDao.selectList(SERVICEID_PREFIX + "getScm0160Header", headerMap);
		int headerCnt = ifHeader.size();
		
		if(headerCnt > 0) {
			// 날짜 포멧팅
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
			try{
				SI_SCM0160_SCM_SOProxy proxy = new SI_SCM0160_SCM_SOProxy();
				DT_SCM0160_SCM mmData = null;
				DT_SCM0160_SCMIF_DM_DOCUMENT_H header = null;
				
				for(int i = 0; i < headerCnt; i++) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("searchIfFlag", "N");
					map.put("updateIfFlag", "P");
					map.put("docNo", ifHeader.get(i).get("DOCNO").toString());

					// 1건씩 처리됨
					commonDao.update(SERVICEID_PREFIX + "updateScm0160Header", map);
					commonDao.update(SERVICEID_PREFIX + "updateScm0160Detail", map);					
					
					mmData = new DT_SCM0160_SCM();
					mmData.setXSYS("SCM");
					mmData.setXDATS(dateFormat.format(calendar.getTime()));
					mmData.setXTIMS(timeFormat.format(calendar.getTime()));
					mmData.setXROWS("1");
						
					header = new DT_SCM0160_SCMIF_DM_DOCUMENT_H();
			
					header.setORDERTYPE(ifHeader.get(i).get("ORDERTYPE").toString());
					header.setDCCODE(ifHeader.get(i).get("DCCODE").toString());
					header.setDOCNO(ifHeader.get(i).get("DOCNO").toString());
					header.setFROM_BILLTOKEY("FW00");
					header.setSTORERKEY(ifHeader.get(i).get("STORERKEY").toString());
					header.setSHOPPINGMALL("Z01");
							
					// 실제로 실적전송
					mmData.setIF_DM_DOCUMENT_H(header);
					
					Map<String, String> detailMap = new HashMap<String, String>();
					detailMap.put("docNo", ifHeader.get(i).get("DOCNO").toString());
					
					List<HashMap> ifDetail = commonDao.selectList(SERVICEID_PREFIX + "getScm0160Detail", detailMap);
					int detailCnt = ifDetail.size();
					
					if(detailCnt > 0) {
						DT_SCM0160_SCMIF_DM_DOCUMENT_D[] detailList = new DT_SCM0160_SCMIF_DM_DOCUMENT_D[detailCnt];
						DT_SCM0160_SCMIF_DM_DOCUMENT_D detail  = null;
						
						for(int j = 0; j < detailCnt; j++) {
							detail = new DT_SCM0160_SCMIF_DM_DOCUMENT_D();
							
							detail.setDOCNO(ifDetail.get(j).get("DOCNO").toString());
							detail.setDOCLINE(ifDetail.get(j).get("DOCLINE").toString());
							detail.setDEL_YN(ifDetail.get(j).get("DEL_YN").toString());
							detail.setPLANT(ifDetail.get(j).get("PLANT").toString());
							detail.setSTORAGELOC((String) ifDetail.get(j).get("STORAGELOC"));
							detail.setSKU(ifDetail.get(j).get("SKU").toString());
							detail.setSTORERORDERQTY(ifDetail.get(j).get("STORERORDERQTY").toString());
							detail.setSTORERUOM(ifDetail.get(j).get("STORERUOM").toString());
							detail.setDELIVERYDATE(ifDetail.get(j).get("DELIVERYDATE").toString());
							detail.setOTHER02("X"); // 삭제
							
							detailList[j] = detail;
						}
						mmData.setIF_DM_DOCUMENT_D(detailList);
						DT_SCM0160_SCM_responseT_RETURN[] response = null;
						response = proxy.si_scm0160_scm_so(mmData);
						
						// 응답에 대한 결과 처리
						if(response != null && response.length > 0) {
							if("E".equals(response[0].getXSTAT())) {
								throw new UserHandleException("인터페이스 처리중 에러발생["+response[0].getXMSGS()+"]");
							}
						} else {
							throw new UserHandleException("인터페이스 처리중 에러발생[실적자료 전송중 오류발생]");
						}
					} else {
						throw new UserHandleException("인터페이스 처리중 에러발생[STO예정 문서 Detail 정보를 찾을 수 없습니다.]");
					}
					
					Map<String, String> mapResult = new HashMap<String, String>();
					mapResult.put("searchIfFlag", "P");
					mapResult.put("updateIfFlag", "Y");
					mapResult.put("updateIfMemo", "");
					mapResult.put("docNo", ifHeader.get(i).get("DOCNO").toString());
					
					// 처리결과 업데이트
					commonDao.update(SERVICEID_PREFIX +"updateScm0160Header", mapResult);
					commonDao.update(SERVICEID_PREFIX +"updateScm0160Detail", mapResult);
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
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 발주보류 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.05        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String savePoHoldYn(OmPurchaseStorageAutoTotalReqDto omPurchaseStorageAutoTotalReqDto) {
		commonDao.update(SERVICEID_PREFIX + "updatePoHoldYn", omPurchaseStorageAutoTotalReqDto);
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 외부창고 이메일 전송
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.02.23        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public OmPurchaseStorageAutoTotalReqDto saveEmail(OmPurchaseStorageAutoTotalReqDto paramDto) throws Exception {
		OmPurchaseStorageAutoTotalReqDto resultDto = new OmPurchaseStorageAutoTotalReqDto();
		CmSendEmailReqDto emailDto = new CmSendEmailReqDto();
		StringBuffer html     = new StringBuffer();
		int processCnt = 0; // 처리 건수
		
        // 파라미터 위변조 적용(paramDto->reqDto)
		OmPurchaseStorageAutoTotalReqDto reqDto = ModelMapperUtil.map(paramDto, OmPurchaseStorageAutoTotalReqDto.class);
		
	    List<OmPurchaseStorageAutoTotalResDto> receiversList = reqDto.getReceiversList(); // 수신자 리스트
	    List<OmPurchaseStorageAutoTotalResDto> detailList = reqDto.getDetailList(); // 상세 리스트		
		
		String cnts = reqDto.getConts(); // UI의 메일 내용 
		
		html.append("<div class=\"tablecontent_sub_title\" width=\"800\" style=\"font:12 굴림\">            ").append("\n");
	    html.append("    <div class=\"label\">").append(cnts).append("</div>      ").append("\n");
	    html.append("</div>                                                                         ").append("\n");
	    
	    html.append("                                                                               ").append("\n");
	    html.append("<table class=\"tablecontent tablecontent_grid\" id=\"datatable1\" border=\"1\" cellpadding=\"5\" cellspacing=\"0\" bordercolor=\"lightgrey\" width=\"800\" style=\"font:12 굴림\">      ").append("\n");
	    html.append("    <colgroup>                         ").append("\n");
	    html.append("        <col class=\"col1\" width=\"150\"> ").append("\n");
	    html.append("        <col class=\"col2\" width=\"200\"> ").append("\n");
	    html.append("        <col class=\"col3\" width=\"80\">  ").append("\n");
	    html.append("        <col class=\"col4\" width=\"300\"> ").append("\n");
	    html.append("        <col class=\"col5\" width=\"50\">  ").append("\n");
	    html.append("        <col class=\"col6\" width=\"50\">  ").append("\n");
	    html.append("        <col class=\"col7\" width=\"100\"> ").append("\n");
	    html.append("        <col class=\"col8\" width=\"150\"> ").append("\n");
	    html.append("        <col class=\"col9\" width=\"100\"> ").append("\n");
	    html.append("        <col class=\"col10\" width=\"200\">").append("\n");
	    html.append("        <col class=\"col11\" width=\"100\">").append("\n");
	    html.append("        <col class=\"col12\" width=\"130\">").append("\n");
	    html.append("        <col class=\"col13\">            ").append("\n");
	    html.append("        <col class=\"col14\">            ").append("\n");
	    html.append("        <col class=\"col15\">            ").append("\n");
	    html.append("        <col class=\"col16\">            ").append("\n");
	    html.append("        <col class=\"col17\">            ").append("\n");
	    html.append("        <col class=\"col18\">            ").append("\n");
	    html.append("        <col class=\"col19\">            ").append("\n");
	    html.append("        <col class=\"col20\">            ").append("\n");
	    html.append("    </colgroup>                        ").append("\n");
	    html.append("    <thead bgcolor=\"#70D3E9\">          ").append("\n");
	    html.append("        <tr>                           ").append("\n");
	    html.append("            <th>창고</th>            ").append("\n");
	    html.append("            <th>창고명</th>           ").append("\n");
	    html.append("            <th>상품코드</th>          ").append("\n");
	    html.append("            <th>상품명칭</th>          ").append("\n");
	    html.append("            <th>저장조건</th>          ").append("\n");
	    html.append("            <th>구매단위</th>          ").append("\n");
	    html.append("            <th>발주수량</th>          ").append("\n");
	    html.append("            <th>수급센터</th>          ").append("\n");
	    html.append("            <th>협력사코드</th>         ").append("\n");
	    html.append("            <th>협력사명</th>          ").append("\n");
	    html.append("            <th>확정오더량보유일</th>     ").append("\n");
	    html.append("            <th>환산수량(PLT)</th>     ").append("\n");
	    html.append("            <th>중량(KG)</th>         ").append("\n");
	    html.append("            <th>소비기한 마감일</th>     ").append("\n");
	    html.append("            <th>소비기한</th>          ").append("\n");
	    html.append("            <th>BL번호</th>           ").append("\n");
	    html.append("            <th>세금코드</th>          ").append("\n");
	    html.append("            <th>계약업체</th>          ").append("\n");
	    html.append("            <th>계약업체명</th>         ").append("\n");
	    html.append("            <th>계약유형</th>          ").append("\n");
	    html.append("        </tr>                       ").append("\n");
	    html.append("    </thead>                        ").append("\n");
	    html.append("    <tbody>                         ").append("\n");
	    for(int i = 0 ; i < detailList.size() ; i++) {
	        OmPurchaseStorageAutoTotalResDto dto = detailList.get(i);
	        String finalPltStr = dto.getFinalPlt() != null ? dto.getFinalPlt().toPlainString() : "";
	        String stockQtyDispStockDayStr = dto.getStockQtyDispStockDay() != null ? dto.getStockQtyDispStockDay().toPlainString() : "";
	        String pltTransStr = dto.getPltTrans() != null ? dto.getPltTrans().toPlainString() : "";
	        String kgCalStr = dto.getKgCal() != null ? dto.getKgCal().toPlainString() : "";
	        
	        html.append("        <tr>                                                                            ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getOutOrganize())).append("</td>              ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getOutOrganizeName())).append("</td>        ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getSku())).append("</td>                  ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getSkuName())).append("</td>              ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getStorageTypeNm())).append("</td>          ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getPurchaseUom())).append("</td>          ").append("\n");
	        html.append("            <td align=\"right\">").append(StringUtil.nvl(finalPltStr)).append("</td>      ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getDcName())).append("</td>          ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getCustKey())).append("</td>          ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getCustName())).append("</td>          ").append("\n");
	        html.append("            <td align=\"right\">").append(StringUtil.nvl(stockQtyDispStockDayStr)).append("</td>   ").append("\n");
	        html.append("            <td align=\"right\">").append(StringUtil.nvl(pltTransStr)).append("</td>   ").append("\n");
	        html.append("            <td align=\"right\">").append(StringUtil.nvl(kgCalStr)).append("</td>   ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getDuration())).append("</td>     ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getDurationTerm())).append("</td>     ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getConvSerialNo())).append("</td>     ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getTaxCode())).append("</td>     ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getContractCustkey())).append("</td>     ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getContractCustNm())).append("</td>     ").append("\n");
	        html.append("            <td>").append(StringUtil.nvl(dto.getContractType())).append("</td>     ").append("\n");
	        html.append("        </tr>                                                                       ").append("\n");
	    }
	    html.append("    </tbody>                                                                        ").append("\n");
	    html.append("</table>                                                                            ").append("\n");
		
		if ( receiversList != null ) {
			for(int k = 0 ; k < receiversList.size() ; k++) {
				processCnt++;
				
				OmPurchaseStorageAutoTotalResDto rcvEmailDto = receiversList.get(k);
	    		cmEmailService.saveEmail(CmSendEmailReqDto.builder()
			    				.title(reqDto.getTitle())
			    				.conts(html.toString())
			    				.sendEmail(reqDto.getSendEmail())
			    				.sendName(reqDto.getSendName())
			    				.refEmailAdd(reqDto.getRefEmailAdd())
			    				//
			    				.recvEmail(rcvEmailDto.getRcvrEmail())
			    				.recvName(rcvEmailDto.getRcvrNm())
			    				.build()
			    			);
	    		
			}
		}		
		
		resultDto.setProcessCnt(processCnt); // 처리 건수
		return resultDto;
	}
}
