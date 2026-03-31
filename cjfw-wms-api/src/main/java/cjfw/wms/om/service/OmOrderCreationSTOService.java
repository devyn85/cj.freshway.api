package cjfw.wms.om.service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.extend.CommonProcedureDto;
import cjfw.wms.om.dto.OmOrderCreationSTOReqDto;
import cjfw.wms.om.dto.OmOrderCreationSTOResDto;
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
 * @date : 2025.09.26
 * @description : 물류센터간이체 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.17    YeoSeungCheol(pw6375@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmOrderCreationSTOService {

    private static final String SERVICEID_PREFIX = "omOrderCreationSTOService.";
    private static final String TEMPTABLETYPE = "ST";
    private static final String PAKAGE_NAME = "SPOM_ORDERCREATIONSTO";
    private static final String PROCESSTYPE = "OM_ORDERCREATIONSTO";

    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private final UserContext userContext;

    /**
	 * @description : 물류센터간 이체 목록 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.26 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<OmOrderCreationSTOResDto> getMasterList(OmOrderCreationSTOReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }

    /**
	 * @description : 물류센터간 이체 결과 조회 (임시테이블)
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.26 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Transactional(readOnly = true)
    public List<OmOrderCreationSTOResDto> getResultList(OmOrderCreationSTOReqDto dto) {
        dto.setGUserId(userContext.getUserId());
        dto.setGSpid(userContext.getSpid());
        return commonDao.selectList(SERVICEID_PREFIX + "getDataResultlist", dto);
    }

    /**
	 * @throws Exception 
     * @description : 물류센터간 이체 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.26 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Transactional
    public List<OmOrderCreationSTOResDto> saveMasterList(OmOrderCreationSTOReqDto dto) throws Exception {
        dto.setGUserId(userContext.getUserId());
        dto.setGSpid(userContext.getSpid());

        OmOrderCreationSTOReqDto reqDto = ModelMapperUtil.map(dto, OmOrderCreationSTOReqDto.class);
        List<OmOrderCreationSTOResDto> saveList = reqDto.getSaveList();

        if (saveList == null || saveList.isEmpty()) {
            return commonDao.selectList(SERVICEID_PREFIX + "getDataResultlist", reqDto);
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
            OmOrderCreationSTOResDto row = saveList.get(i);
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
            e.setFromLoc(row.getFromLoc());
            e.setFromLot(row.getFromLot());
            e.setFromStocktype(row.getFromStocktype());
            e.setFromOrderqty(row.getFromOrderqty());

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
            e.setToLoc(row.getToLoc());
            e.setToLot(row.getToLot());
            e.setToStocktype(row.getToStocktype());

            e.setMemo1(row.getMemo1());

            insertList.add(e);
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempST", insertList);
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
        procDto.setAvc_WORKER(userContext.getUserId());
        procDto.setAvc_ORGANIZE(userContext.getOrganize());
        procDto.setAvc_AREA(userContext.getArea());
        procDto.setAvc_EXECUTEMODE("NOCOMMIT");
        procDto.setAvc_COMMAND("CONFIRM");

        String dccode = reqDto.getAvc_DCCODE();
        if (dccode == null || dccode.isEmpty()) dccode = reqDto.getFromDccode();
        if (dccode == null || dccode.isEmpty()) dccode = reqDto.getDcA();
        procDto.setAvc_DCCODE(dccode);

        String[] keyList = {
            "PROCEDURE",
            "PROCESSTYPE",
            "PROCESSCREATOR",
            "SPID",
            "DELIVERYDATE",
            "CONVERTTYPE",
            "STOTYPE",
            "TO_DCCCODE",
            "TO_ORGANIZE"
        };
        Object[] valueList = {
            PAKAGE_NAME,
            PROCESSTYPE,
            userContext.getUserId(),
            userContext.getSpid(),
            reqDto.getDELIVERYDATE(),
            "TR",
            (reqDto.getSTOTYPE() != null && !reqDto.getSTOTYPE().isEmpty()) ? reqDto.getSTOTYPE() : "STO",
            reqDto.getToDccode(),
            reqDto.getOrganize() != null ? reqDto.getOrganize() : reqDto.getToDccode()
        };
        procDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));

        int rv = cmCommonService.saveProcedure(procDto);
        log.info("pkg rv={}, code={}, msg={}", rv, procDto.getResultCode(), procDto.getResultMessage());

        String resultCode = procDto.getResultCode();
        String resultMessage = procDto.getResultMessage();
        if (!"0".equals(resultCode)) {
            throw new UserHandleException(
                MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"물류센터간이체"}) + resultMessage
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
}



