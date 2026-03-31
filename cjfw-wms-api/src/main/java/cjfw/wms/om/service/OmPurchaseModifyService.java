package cjfw.wms.om.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseResDto;
import cjfw.wms.om.dto.OmPurchaseModifyReqDto;
import cjfw.wms.om.dto.OmPurchaseModifyResDto;
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
 * @date        : 2025.09.12
 * @description : 저장품발주삭제 기능을 구현한 Service Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.12        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmPurchaseModifyService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omPurchaseModifyService.";
	private transient static final String PAKAGE_NAME = "SPDP_PURCHASE";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 *  공통.Service
	 */
	private final CmCommonService cmCommonService;

	/**
	 * @description : 저장품발주삭제PO 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmPurchaseModifyResDto> getMasterListPO(OmPurchaseModifyReqDto omPurchaseModifyReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterListPO", omPurchaseModifyReqDto);
	}
	
	/**
	 * @description : 저장품발주삭제PO 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmPurchaseModifyResDto> getDetailListPO(OmPurchaseModifyReqDto omPurchaseModifyReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailListPO", omPurchaseModifyReqDto);
	}

	/**
	 * @description : 저장품발주삭제STO 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmPurchaseModifyResDto> getMasterListSTO(OmPurchaseModifyReqDto omPurchaseModifyReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterListSTO", omPurchaseModifyReqDto);
	}
	
	/**
	 * @description : 저장품발주삭제STO 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmPurchaseModifyResDto> getDetailListSTO(OmPurchaseModifyReqDto omPurchaseModifyReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailListSTO", omPurchaseModifyReqDto);
	}
	
	/**
	 *  저장품 발주(PO) 삭제 요청 처리
	 *  LOOP TRANSACTION으로 한건씩 처리됨 
	 *  CASE 1. 발주요청 내역이 SRM I/F 전송전일시 
	 *             ==> IF_POREQUEST_RESULT.DEL_YN = 'Y'
	 *             ==> DP_PURCHASE.DEL_YN = 'Y'
	 *  CASE 2. 발주요청 내역이 SRM I/F 전송완료
	 *             ==> DP_PURCHASE.DEL_YN = 'Y' , DP_PURCHASE.DELIVERYMEMO = 'SEND' (I/F 전송완료내역 확인을 위해 사용)
	 *             ==> SRM 삭제요청 I/F 처리
	 */
	@Transactional
	public String deleteMasterPO(OmPurchaseModifyReqDto dto) throws Exception {
		
		List<OmPurchaseModifyReqDto> saveList = dto.getSaveList();
		String resultItemMsg = "";
		if (null != saveList) {
			for (OmPurchaseModifyReqDto omPurchaseModifyReqDto : saveList) { 
				var entity = ModelMapperUtil.map(omPurchaseModifyReqDto, userContext, OmPurchaseModifyEntity.class);
				
				commonDao.update(SERVICEID_PREFIX +"updatePurchaseDelYn", entity);
				
				resultItemMsg = "요청번호(" + entity.getRequestNo() + ") 의 상품명(" + entity.getSkuName() + ")";
				
				// 한건식 처리됨
				Map<String, String> checkMap = new HashMap<String, String>();
				checkMap.put("requestNo", omPurchaseModifyReqDto.getRequestNo());
				checkMap.put("requestLine", omPurchaseModifyReqDto.getRequestLine());
				// y/n 데이터 리턴됨
				int chk = commonDao.selectOne(SERVICEID_PREFIX + "getDataCheckSend", checkMap);
				
				log.info("▶SRM I/F 전송여부 chk->{}",chk);
								
				// srm i/f 처리
				if(chk > 0){
					int headerCnt = 1; //inDetailDs.getRowCount();
					
					// 날짜 포멧팅
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
					SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
					
					try {
						SI_SCM0391_SCM_SOProxy proxy = new SI_SCM0391_SCM_SOProxy();
						DT_SCM0391_SCM mmData = null;
						DT_SCM0391_SCMT_REQUEST_CANCEL[] header = new DT_SCM0391_SCMT_REQUEST_CANCEL[headerCnt];
						
						mmData = new DT_SCM0391_SCM();
						mmData.setXSYS("SCM");
						mmData.setXDATS(dateFormat.format(calendar.getTime()));
						mmData.setXTIMS(timeFormat.format(calendar.getTime()));
						mmData.setXROWS("1");
						
						for(int i = 0; i < headerCnt; i++) {
							
							DT_SCM0391_SCMT_REQUEST_CANCEL oneData = new DT_SCM0391_SCMT_REQUEST_CANCEL(); 
								
							oneData.setREQUESTNO(omPurchaseModifyReqDto.getRequestNo());
							oneData.setREQUESTLINE(omPurchaseModifyReqDto.getRequestLine());
							oneData.setREASONMSG("CANCEL");
							oneData.setADDWHO(userContext.getUserId());
							header[i] = oneData;
						}
						
						mmData.setT_REQUEST_CANCEL(header);

						DT_SCM0391_SCM_responseT_RETURN[] response = null;
						response = proxy.si_SCM0391_scm_so(mmData);

						// 응답에 대한 결과 처리
						if(response != null && response.length > 0) {
							if("E".equals(response[0].getXSTAT())) {
								resultItemMsg = resultItemMsg + "인터페이스 처리중 에러발생["+response[0].getXMSGS()+"]";
								throw new UserHandleException(resultItemMsg);
							}
						} else {
							resultItemMsg = resultItemMsg + "인터페이스 처리중 에러발생[실적자료 전송중 오류발생]";
							throw new UserHandleException(resultItemMsg);
						}
					} catch(UserHandleException e) {
						e.printStackTrace();
						throw new UserHandleException(e.getErrorMessage());
					} catch(Exception e) {
						e.printStackTrace();
						throw new UserHandleException(e);
					}
				}
			}
		}		
		return resultItemMsg + " 을(를) 발주 삭제 완료 했습니다.";		
	}

	/**
	 * 
	 * @description : 저장품 발주(STO) 삭제 요청 처리를 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Transactional
	public String deleteMasterSTO(OmPurchaseModifyReqDto dto) {
		log.info(">>>>>>>>>>>>>>>>>>> dto: {}", dto);
		List<OmPurchaseModifyReqDto> saveList = dto.getSaveList();
		
		// 1. slipNo 기준으로 데이터 그룹화
	    Map<String, List<OmPurchaseModifyReqDto>> groupedBySlipNo = saveList.stream()
	            .collect(Collectors.groupingBy(OmPurchaseModifyReqDto::getSlipNo));

	    StringBuilder finalResultMessage = new StringBuilder();
	    
	    SI_SCM0160_SCM_SOProxy proxy = new SI_SCM0160_SCM_SOProxy();
	    
	    // interface Call
	 	OmOrderCreationSTOOrdBaseResDto callDto = commonDao.selectOne("omOrderCreationSTOOrdBaseService.getCallYn", dto);
	 	String callYn = callDto.getCallYn();
	    
	    // 2. 각 slipNo 그룹별로 루프 수행
	    for (String currentSlipNo : groupedBySlipNo.keySet()) {
	        List<OmPurchaseModifyReqDto> groupItems = groupedBySlipNo.get(currentSlipNo);
	        String groupItemMsg = "";
	        
	        // Header 정보 생성 (그룹의 첫 번째 아이템 기준)
	        var headerEntity = ModelMapperUtil.map(groupItems.get(0), userContext, OmPurchaseModifyEntity.class);
	        
	        // 기존 IF header COPY 처리
	        // commonDao.update(SERVICEID_PREFIX + "insertIFHeader", headerEntity);

	        // 상세 아이템 처리
	        for (OmPurchaseModifyReqDto itemDto : groupItems) {
	            var entity = ModelMapperUtil.map(itemDto, userContext, OmPurchaseModifyEntity.class);
	            commonDao.update(SERVICEID_PREFIX + "updateSTODelYn", entity);
	            commonDao.update(SERVICEID_PREFIX + "insertIFDetail", entity);

	            // DM_DOCUMENT_D 조회
	            Map<String, String> paramMap = new HashMap<>();
	            paramMap.put("dccode", entity.getDcCode());
	            paramMap.put("docno", entity.getSlipNo());
	            paramMap.put("sku", entity.getSku());
	            
	            List<HashMap> soList = commonDao.selectList(SERVICEID_PREFIX + "getSokeySolineList", paramMap);
	            
	            // OM_SDY_REGN_REPL_D 삭제
	            if (soList != null && !soList.isEmpty()) {
	            	for (HashMap so: soList) {
	            		
	                    if (so == null) {
	                        continue;
	                    }
	                    
	                    Object dccodeObj = so.get("DOCNO");
	            		Object sokeyObj = so.get("SOKEY");
	            		Object solineObj = so.get("SOLINE");
	            		
	            		String dccode = dccodeObj != null ? dccodeObj.toString().trim() : "";
	            		String sokey = sokeyObj != null ? sokeyObj.toString().trim() : "";
	            		String soline = solineObj != null ? solineObj.toString().trim() : "";
	            		// null check
	            		if (sokey.isEmpty() || soline.isEmpty()) {
	            			continue;	// null 이면 중지
	            		}
	            		Map<String, String> delMap = new HashMap<>();
	            		delMap.put("docno", dccode);
	            		delMap.put("sokey", sokey);
	            		delMap.put("soline", soline);
	            		
	            		commonDao.delete(SERVICEID_PREFIX + "deleteSdyRegn", delMap);
	            	}
	            }
	            
	            groupItemMsg = "요청번호(" + entity.getSlipNo() + ") 의 상품명(" + entity.getSkuName() + ")";
	        }

	        // kx 처리 추가 필요
     		// case 1. 총라인 5개 중 2개 삭제시 => 나머지 3개에 대한 데이터 새로 생성 
 			// case 2. 총라인 5개 중 5개 삭제시 => 5개 모두 del_yn = 'Y'로 5개 모두 생성해야 함
	        Map<String, String> checkMap = new HashMap<>();
	        checkMap.put("slipNo", currentSlipNo);

	        // DP_INPLAN DEL_YN = 'N'인건이 있는지 확인
	        List<HashMap> dmD = commonDao.selectList(SERVICEID_PREFIX + "getDpInplanDelynN", checkMap);
	        if (dmD.size() > 0) { // case 1
	            commonDao.insert(SERVICEID_PREFIX + "insertKXData", headerEntity);
	        } else { // case 2
	            commonDao.insert(SERVICEID_PREFIX + "insertKXDataAll", headerEntity);
	        }
			
			if(callYn.equals("Y")) {
				// 인터페이스(SAP) 연동 로직 시작
		        Map<String, String> map = new HashMap<>();
		        map.put("docNo", currentSlipNo); // 요청하신 docNo 명칭 유지
		        map.put("gUserId", headerEntity.getGUserId());

		        List<HashMap> ifHeader = commonDao.selectList(SERVICEID_PREFIX + "getScm0160Header", map);
		        if (ifHeader == null || ifHeader.isEmpty()) {
		            throw new UserHandleException(groupItemMsg + " 인터페이스 처리중 에러발생[헤더자료 조회중 오류발생]");
		        }

		        // 날짜/시간 설정
		        Calendar calendar = Calendar.getInstance();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
		        
		        try{
		        	DT_SCM0160_SCM mmData = new DT_SCM0160_SCM();
			        mmData.setXSYS("SCM");
			        mmData.setXDATS(dateFormat.format(calendar.getTime()));
			        mmData.setXTIMS(timeFormat.format(calendar.getTime()));
			        mmData.setXROWS("1");

			        DT_SCM0160_SCMIF_DM_DOCUMENT_H header = new DT_SCM0160_SCMIF_DM_DOCUMENT_H();
			        header.setORDERTYPE(ifHeader.get(0).get("ORDERTYPE").toString());
			        header.setDCCODE(ifHeader.get(0).get("DCCODE").toString());
			        header.setDOCNO(ifHeader.get(0).get("DOCNO").toString());
			        header.setFROM_BILLTOKEY("FW00");
			        header.setSTORERKEY(ifHeader.get(0).get("STORERKEY").toString());
			        header.setSHOPPINGMALL("Z01");
			        mmData.setIF_DM_DOCUMENT_H(header);

			        // 상세 목록 생성
			        DT_SCM0160_SCMIF_DM_DOCUMENT_D[] detailList = new DT_SCM0160_SCMIF_DM_DOCUMENT_D[groupItems.size()];
			        for (int i = 0; i < groupItems.size(); i++) {
			            Map<String, String> detailMap = new HashMap<>();
			            detailMap.put("DOCNO", groupItems.get(i).getSlipNo());
			            detailMap.put("DOCLINE", groupItems.get(i).getSlipLine());

			            List<HashMap> detailDoc = commonDao.selectList(SERVICEID_PREFIX + "getScm0160Detail", detailMap);
			            if (detailDoc == null || detailDoc.isEmpty()) continue;

			            DT_SCM0160_SCMIF_DM_DOCUMENT_D detail = new DT_SCM0160_SCMIF_DM_DOCUMENT_D();
			            detail.setDOCNO(detailDoc.get(0).get("DOCNO").toString());
			            detail.setDOCLINE(detailDoc.get(0).get("DOCLINE").toString());
			            detail.setDEL_YN(detailDoc.get(0).get("DEL_YN").toString());
			            detail.setPLANT(detailDoc.get(0).get("PLANT").toString());
			            detail.setSTORAGELOC((String) detailDoc.get(0).get("STORAGELOC"));
			            detail.setSKU(detailDoc.get(0).get("SKU").toString());
			            detail.setSTORERORDERQTY(detailDoc.get(0).get("STORERORDERQTY").toString());
			            detail.setSTORERUOM(detailDoc.get(0).get("STORERUOM").toString());
			            detail.setDELIVERYDATE(detailDoc.get(0).get("DELIVERYDATE").toString());
			            detail.setOTHER02("X");

			            detailList[i] = detail;
			        }
			        mmData.setIF_DM_DOCUMENT_D(detailList);

			        // 인터페이스 전송		        
			        DT_SCM0160_SCM_responseT_RETURN[] response = proxy.si_scm0160_scm_so(mmData);

			        if (response != null && response.length > 0) {
			            if ("E".equals(response[0].getXSTAT())) {
			                throw new UserHandleException(groupItemMsg + " 인터페이스 처리중 에러발생[" + response[0].getXMSGS() + "]");
			            }
			        } else {
			            throw new UserHandleException(groupItemMsg + " 인터페이스 처리중 에러발생[실적자료 전송중 오류발생]");
			        }

			        if (finalResultMessage.length() > 0) finalResultMessage.append(", ");
			        finalResultMessage.append(currentSlipNo);
		        } catch(UserHandleException e) {
					e.printStackTrace();
					throw new UserHandleException(e.getErrorMessage());
				} catch(Exception e) {
					e.printStackTrace();
					throw new UserHandleException(e);
				}				
			}
	    }
		
		return finalResultMessage.toString() + " 을(를) 삭제 완료 했습니다.";	
	}
}
