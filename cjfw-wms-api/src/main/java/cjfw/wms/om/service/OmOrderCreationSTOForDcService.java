package cjfw.wms.om.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.extend.CommonProcedureDto;
import cjfw.wms.om.dto.OmOrderCreationSTOForDcReqDto;
import cjfw.wms.om.dto.OmOrderCreationSTOForDcResDto;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCM;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCMIF_DM_DOCUMENT_D;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCMIF_DM_DOCUMENT_H;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCM_responseT_RETURN;
import cjfw.wms.webservice.mmStoIf.SI_SCM0160_SCM_SOProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * 
 * @author : YeoSeungCheol(pw6375@cj.net) 
 * @date : 2025.09.17 
 * @description : 저장품센터간이체 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.17    YeoSeungCheol(pw6375@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmOrderCreationSTOForDcService {

    private static final String SERVICEID_PREFIX = "omOrderCreationSTOForDcService.";
    private static final String TEMPTABLETYPE = "ST";
    private static final String PAKAGE_NAME = "SPOM_ORDERCREATIONSTO_FORDC";
    private static final String PROCESSTYPE = "OM_ORDERCREATIONSTO_FD";

    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private final UserContext userContext;

    /**
	 * @description : 저장품센터간이체 대상 목록 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<OmOrderCreationSTOForDcResDto> getMasterList(OmOrderCreationSTOForDcReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }

    /**
	 * @description : 저장품센터간이체 처리결과 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Transactional
    public List<OmOrderCreationSTOForDcResDto> getResultList(OmOrderCreationSTOForDcReqDto dto) {
        dto.setGUserId(userContext.getUserId());
        dto.setGSpid(userContext.getSpid());
        return commonDao.selectList(SERVICEID_PREFIX + "getDataResultlist", dto);
    }

    /**
	 * @description : 저장품센터간이체 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Transactional
    public List<OmOrderCreationSTOForDcResDto> saveMasterList(OmOrderCreationSTOForDcReqDto dto) throws Exception {
        dto.setGUserId(userContext.getUserId());
        dto.setGSpid(userContext.getSpid());

        OmOrderCreationSTOForDcReqDto reqDto = ModelMapperUtil.map(dto, OmOrderCreationSTOForDcReqDto.class);
        List<OmOrderCreationSTOForDcResDto> saveList = reqDto.getSaveList();

        if (saveList == null || saveList.isEmpty()) {
            return commonDao.selectList(SERVICEID_PREFIX + "getDataResultList", reqDto);
        }

        // clear temp
        commonDao.delete(
            SERVICEID_PREFIX + "deleteSyProcessTempST",
            java.util.Map.of("gUserId", userContext.getUserId(), "gSpid", userContext.getSpid())
        );

        // batch insert temp (chunk: 100)
        final int chunkSize = 100;
        List<CmSyProcessTempStEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            OmOrderCreationSTOForDcResDto row = saveList.get(i);
            CmSyProcessTempStEntity e = new CmSyProcessTempStEntity();

            e.setProcesstype(PROCESSTYPE);
            e.setGUserId(userContext.getUserId());
            e.setGSpid(userContext.getSpid());

            e.setFromDccode(row.getFromDccode());
            e.setFromStorerkey(userContext.getStorerkey());
            String fromOrg = row.getFromOrganize() != null ? row.getFromOrganize() : row.getFromDccode();
            e.setFromOrganize(fromOrg);
            e.setFromArea(row.getFromArea());
            e.setFromSku(row.getFromSku());
            e.setFromStockid(row.getFromStockid());
            e.setFromStockgrade(row.getFromStockgrade());
            e.setFromUom(row.getFromUom());

            e.setToOrderqty(row.getToOrderqty());
            e.setToDccode(row.getToDccode());
            e.setToStorerkey(userContext.getStorerkey());
            String toOrg = row.getToOrganize() != null ? row.getToOrganize() : row.getToDccode();
            e.setToOrganize(toOrg);
            e.setToArea(row.getToArea());
            e.setToSku(row.getToSku());
            e.setToStockid(row.getToStockid());
            e.setToStockgrade(row.getToStockgrade());
            e.setToUom(row.getToUom());

            insertList.add(e);
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTemp" + TEMPTABLETYPE, insertList);
                insertList.clear();
            }
        }

        // call package
        CommonProcedureDto procDto = new CommonProcedureDto();
        procDto.setPackagename(PAKAGE_NAME);
        procDto.setProcesstype(PROCESSTYPE);
        procDto.setTemptabletype(TEMPTABLETYPE);
        procDto.setAi_SPID(userContext.getSpid());
        procDto.setAvc_STORERKEY(userContext.getStorerkey());
        // 패키지 라인 처리 대상 매칭을 위해 WORKER 설정 (PROCESSCREATOR와 동일 사용자)
        procDto.setAvc_WORKER(userContext.getUserId());
        // 패키지에서 명령어 필수 처리: 기본값을 CONFIRM으로 설정(프론트에서 override 가능)
        String cmd = reqDto.getAvc_COMMAND();
        if (cmd == null || cmd.isEmpty()) cmd = "CONFIRM";
        procDto.setAvc_COMMAND(cmd);
        // DCCODE 우선순위: reqDto.avc_DCCODE -> reqDto.fromDccode -> reqDto.DC_A
        String dccode = reqDto.getAvc_DCCODE();
        if (dccode == null || dccode.isEmpty()) dccode = reqDto.getFromDccode();
        if (dccode == null || dccode.isEmpty()) dccode = reqDto.getDcA();
        procDto.setAvc_DCCODE(dccode);
        // EXECUTEMODE: '' => COMMIT (DEBUG/NOCOMMIT이 아니면 COMMIT)
        procDto.setAvc_EXECUTEMODE("NOCOMMIT");

        String[] keyList = {
            "PROCEDURE",
            "PROCESSTYPE",
            "PROCESSCREATOR",
            "SPID",
            "DELIVERYDATE",
            "CONVERTTYPE",
            "STOTYPE"
        };
        Object[] valueList = {
            PAKAGE_NAME,
            PROCESSTYPE,
            userContext.getUserId(),
            userContext.getSpid(),
            reqDto.getDELIVERYDATE(),
            "",
            "STO"
        };
        procDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));

        int rv = cmCommonService.saveProcedure(procDto);
        log.info("pkg rv={}, code={}, msg={}", rv, procDto.getResultCode(), procDto.getResultMessage());

        String resultCode = procDto.getResultCode();
        String resultMessage = procDto.getResultMessage();
        if (!"0".equals(resultCode)) {
            throw new UserHandleException(
                MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"저장품센터간이체"}) + resultMessage
            );
        }
        
        //SCM0160 실행
		
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
 						detail.setSTORAGELOC(ifDetail.get(j).get("STORAGELOC").toString());
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
 		}
 		
 		// 프로시저 Exception 처리(4/4)
 		if(!"0".equals(resultCode)){
 			log.error("▶저장품자동발주 - 발주요청(STO) 시 오류 발생 ");
 			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"저장품자동발주-발주요청(STO)"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
 		}  	
 		/*END.PAKAGE 호출*/
 		
        return commonDao.selectList(SERVICEID_PREFIX + "getDataResultlist", reqDto);
    }
    
    /**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.03 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmOrderCreationSTOForDcResDto> getValidateExcelList(OmOrderCreationSTOForDcReqDto dto) {
        List<OmOrderCreationSTOForDcResDto> saveList = dto.getSaveList();
        List<OmOrderCreationSTOForDcResDto> result = new ArrayList<OmOrderCreationSTOForDcResDto>();
		if(saveList != null) {
			result = commonDao.selectList(SERVICEID_PREFIX + "getValidateExcelList", saveList);
			//상품코드 중복확인
			Set<String> uniqueCustKeys = new HashSet<>();
			Set<String> duplicatedCustKeys = new HashSet<>();
			for(OmOrderCreationSTOForDcResDto resultDto : result) {
				
				String sku = resultDto.getSku();
				if (uniqueCustKeys.contains(sku)) {
		            duplicatedCustKeys.add(sku);
		            // 이 시점에서는 현재 데이터에 중복 오류를 즉시 설정하고,
		            // 이전 데이터들은 루프가 끝난 후 한 번에 업데이트
		            resultDto.setDuplicateYn("N");
		        } else {
		            // 처음 등장한 CUSTKEY는 uniqueCustKeys에 추가
		            uniqueCustKeys.add(sku);
		        }				
			}
		}
		return result;
	}
}
