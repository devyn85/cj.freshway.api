package cjfw.wms.cm.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCMIF_ST_STOCKAMT;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET;
import cjfw.wms.webservice.elecAmt.SI_MM0090_SCM_SOProxy;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.cm.dto.CmElecReqDto;
import cjfw.wms.cm.dto.CmElecResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net)
 * @date : 2025.09.22
 * @description : 전자결재 API Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.22 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmElecService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmElecService.";

	private final CommonDao commonDao;

	private final RestTemplate restTemplate;

	/**
	 * @description : 로그인 사용자 권한에 따른 메뉴 리스트 조회
	 * @issues :
	 * -----------------------------------------------------------
	 * DATE AUTHOR MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.04.28 JangGwangSeok (breaker3317@cj.net) 생성
	 */
	public CmElecResDto getElecHtml(CmElecReqDto cmElecReqDto) {
		CmElecResDto cmElecResDto = new CmElecResDto();
		StringBuffer html = new StringBuffer();

		// 폼ID
		String formId = cmElecReqDto.getFormId();

		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}", cmElecReqDto.toString());

		if ("SCM06".equals(formId)) {

			// 전자결재 대상건 조회
			List<Map<String, Object>> ds_dccode = commonDao.selectList(SERVICEID_PREFIX + "selectDccode", cmElecReqDto);

			StringBuffer MULTI_DCCODE = new StringBuffer();
			for (int i = 0; i < ds_dccode.size(); i++) {
				if (i > 0) {
					MULTI_DCCODE.append(",");
				}
				MULTI_DCCODE.append("'").append(ds_dccode.get(0).get("DCCODE")).append("' AS DCCODE")
						.append(String.valueOf(i)).append("\n");
			}
			cmElecReqDto.setMultiDccode(MULTI_DCCODE.toString());

			List<Map<String, Object>> ds_content1 = commonDao.selectList(SERVICEID_PREFIX + "selectElecForm1Cont1",
					cmElecReqDto);
			List<Map<String, Object>> ds_content2 = commonDao.selectList(SERVICEID_PREFIX + "selectElecForm1Cont2",
					cmElecReqDto);

			html.append("<table class='tablecontent tablecontent_grid' id='datatable1'>").append("\n");
			html.append("<colgroup>").append("\n");
			html.append("<col class='col1'>").append("\n");
			int colCnt = ds_dccode.size() + 1;
			for (int i = 2; i <= colCnt; i++) {
				html.append("<col class='col").append(i).append("'>").append("\n");
			}
			html.append("<col class='").append(colCnt + 1).append("'>").append("\n");
			html.append("</colgroup>").append("\n");
			html.append("<thead>").append("\n");
			html.append("<tr>").append("\n");
			html.append("<th>유형</th>").append("\n");

			// 센터명 찍기
			for (int j = 0; j < ds_dccode.size(); j++) {
				html.append("<th>").append(ds_dccode.get(j).get("DCNAME")).append("</th>").append("\n");
			}
			html.append("<th>총계</th>").append("\n");
			html.append("</tr>").append("\n");
			html.append("</thead>").append("\n");
			html.append("<tbody>").append("\n");

			// 데이터 찍기
			long[] vTot = new long[ds_dccode.size()];
			long hTot = 0;

			// 내용 값 찍기
			for (int i = 0; i < ds_content1.size(); i++) {
				html.append("<tr>").append("\n");
				html.append("<td>").append(String.valueOf(ds_content1.get(i).get("IOTYPENAME"))).append("</td>")
						.append("\n");
				// 센터별로 값 찍기
				hTot = 0;
				for (int j = 0; j < ds_dccode.size(); j++) {
					String tempAmt = (ds_content1.get(i).get("DCCODE" + String.valueOf(j)) == null) ? "0"
							: ds_content1.get(i).get("DCCODE" + String.valueOf(j)).toString();
					html.append("<td class='td_right'>").append(getComma(tempAmt)).append("</td>").append("\n");
					hTot += NumberUtils.toLong(tempAmt); // 총계 계산
					vTot[j] += NumberUtils.toLong(tempAmt); // 총합계 계산
				}
				html.append("<td class='td_right'>").append(getComma(String.valueOf(hTot))).append("</td>")
						.append("\n");
				html.append("</tr>").append("\n");
			}
			html.append("</tbody>").append("\n");
			html.append("<tfoot>").append("\n");
			html.append("<tr>").append("\n");
			html.append("<td>총합계</td>").append("\n");

			// 총합계 계산하여 찍기
			double totAmt = 0;
			for (int i = 0; i < vTot.length; i++) {
				html.append("<td class='td_right'>").append(getComma(String.valueOf(vTot[i]))).append("</td>")
						.append("\n");
				totAmt += vTot[i];
			}
			html.append("<td class='td_right'>").append(getComma(String.valueOf(totAmt))).append("</td>").append("\n");
			html.append("</tr>").append("\n");
			html.append("</tfoot>").append("\n");
			html.append("</table>").append("\n");

			html.append("<div class='tablecontent_sub_title'>").append("\n");
			html.append("<div class='label'>수불오차 SKU</div>").append("\n");
			html.append("</div>").append("\n");
			html.append("<table class='tablecontent tablecontent_grid' id='datatable2'>").append("\n");
			html.append("<colgroup>").append("\n");
			html.append("<col class='col1'>").append("\n");
			html.append("<col class='col2'>").append("\n");
			html.append("<col class='col3'>").append("\n");
			html.append("</colgroup>").append("\n");
			html.append("<thead>").append("\n");
			html.append("<tr>").append("\n");
			html.append("<th>SKU</th>").append("\n");
			html.append("<th>감모총액</th>").append("\n");
			html.append("<th>역감모 총액 (기중 포함)</th>").append("\n");
			html.append("</tr>").append("\n");
			html.append("</thead>").append("\n");
			html.append("<tbody>").append("\n");
			html.append("<tr>").append("\n");
			html.append("<td class='td_right'>")
					.append(getComma(String.valueOf(ds_content2.get(0).getOrDefault("SKU_CNT", "")))).append("</td>")
					.append("\n");
			html.append("<td class='td_right'>")
					.append(getComma(String.valueOf(ds_content2.get(0).getOrDefault("WD_AMT", "")))).append("</td>")
					.append("\n");
			html.append("<td class='td_right'>")
					.append(getComma(String.valueOf(ds_content2.get(0).getOrDefault("DP_AMT", "")))).append("</td>")
					.append("\n");
			html.append("</tr>").append("\n");
			html.append("</tbody>").append("\n");
			html.append("</table>").append("\n");

		} else if ("SCM08".equals(formId)) { // 재고감모승인요청서(외부창고)

			List<Map<String, Object>> ds_content = commonDao.selectList(SERVICEID_PREFIX + "selectElecForm2Cont",
					cmElecReqDto);

			html.append("<div class='tablecontent_sub_title'>").append("\n");
			html.append("<div class='label'>내역</div>").append("\n");
			html.append("</div>").append("\n");
			html.append("").append("\n");
			html.append("<div class='tablecontent_sub_title2'>").append("\n");
			html.append("<div class='label'>상계처리내역</div>").append("\n");
			html.append("</div>").append("\n");
			html.append("").append("\n");
			html.append("<table class='tablecontent tablecontent_grid' id='datatable1'>").append("\n");
			html.append("<colgroup>").append("\n");
			html.append("<col class='col1'>").append("\n");
			html.append("<col class='col2'>").append("\n");
			html.append("<col class='col3'>").append("\n");
			html.append("<col class='col4'>").append("\n");
			html.append("<col class='col5'>").append("\n");
			html.append("<col class='col6'>").append("\n");
			html.append("<col class='col7'>").append("\n");
			html.append("<col class='col8'>").append("\n");
			html.append("<col class='col9'>").append("\n");
			html.append("<col class='col10'>").append("\n");
			html.append("</colgroup> ").append("\n");
			html.append("<thead>").append("\n");
			html.append("<tr>").append("\n");
			html.append("<th>구분명</th>").append("\n");
			html.append("<th>품명</th> ").append("\n");
			html.append("<th>B/L NO.</th>").append("\n");
			html.append("<th>단위</th>").append("\n");
			html.append("<th>수량</th>").append("\n");
			html.append("<th>오류금액</th>").append("\n");
			html.append("<th>오류중량</th>").append("\n");
			html.append("<th>실금액</th>").append("\n");
			html.append("<th>실중량</th>").append("\n");
			html.append("<th>차이금액</th>").append("\n");
			html.append("<th>차이중량</th>").append("\n");
			html.append("</tr>").append("\n");
			html.append("</thead>").append("\n");
			html.append("<tbody>").append("\n");

			for (int i = 0; i < ds_content.size(); i++) {
				html.append("<tr>").append("\n");
				html.append("<td>").append(ds_content.get(i).get("IOTYPE")).append("</td>").append("\n");
				html.append("<td>").append(ds_content.get(i).get("SKUNAME")).append("</td>").append("\n");
				html.append("<td>").append(ds_content.get(i).get("BLNO")).append("</td>").append("\n");
				html.append("<td>").append(ds_content.get(i).get("UOM")).append("</td>").append("\n");
				html.append("<td class='td_right'>").append(ds_content.get(i).get("TRANQTY")).append("</td>")
						.append("\n");
				html.append("<td class='td_right'>").append(ds_content.get(i).get("AMT")).append("</td>").append("\n");
				html.append("<td class='td_right'>").append(ds_content.get(i).get("WEIGHT")).append("</td>")
						.append("\n");
				html.append("<td><input type='text' /></td>").append("\n");
				html.append("<td><input type='text' /></td>").append("\n");
				html.append("<td><input type='text' /></td>").append("\n");
				html.append("<td><input type='text' /></td>").append("\n");
				html.append("</tr>").append("\n");
			}
			html.append("</tbody>").append("\n");
			html.append("</table>").append("\n");

		} else if ("SCM07".equals(formId)) { // 재고 폐기 승인 요청서(FW센터)

			// 전자결재 대상건 조회
			List<Map<String, Object>> ds_costcd = commonDao.selectList(SERVICEID_PREFIX + "selectCostCd", cmElecReqDto);
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("approvalReauestDt", cmElecReqDto.getApprovalReauestDt());
			inParams.put("approvalReauestNo", cmElecReqDto.getApprovalReauestNo());
			inParams.put("CUSTCD_CNT", ds_costcd.size());

			if (ds_costcd.size() > 0) {
				for (int i = 0; i < ds_costcd.size(); i++) {
					inParams.put("COSTCD" + i, ds_costcd.get(i).get("COSTCD"));
				}

				List<Map<String, Object>> ds_content1 = commonDao.selectList2(SERVICEID_PREFIX + "selectElecForm3Cont1",
						inParams);

				html.append("<div class='tablecontent_sub_title'>").append("\n");
				html.append("<div class='label'>폐기내역</div>").append("\n");
				html.append("</div>").append("\n");
				html.append("").append("\n");
				html.append("<table class='tablecontent tablecontent_grid' id='datatable1'>").append("\n");
				html.append("<colgroup>").append("\n");
				html.append("<col class='col1'>").append("\n");
				html.append("<col class='col2'>").append("\n");
				html.append("<col class='col3'>").append("\n");
				int colCnt = ds_costcd.size() + 3;
				for (int i = 4; i <= colCnt; i++) {
					html.append("<col class='col").append(i).append("'>").append("\n");
				}
				html.append("</colgroup>").append("\n");
				html.append("<thead>").append("\n");
				html.append("<tr>").append("\n");
				html.append("<th rowspan='2'>저장품유/무</th>").append("\n");
				html.append("<th rowspan='2'>세부내역</th>").append("\n");
				html.append("<th rowspan='2'>발생사유</th>").append("\n");
				html.append("<th colspan='").append(ds_costcd.size()).append("'>귀책부서별 수량</th>").append("\n");
				html.append("</tr>").append("\n");
				html.append("<tr>").append("\n");
				for (int i = 0; i < ds_costcd.size(); i++) {
					html.append("<th>").append(ds_costcd.get(i).get("CODECDNAME").toString()).append("</th>")
							.append("\n");
				}
				html.append("</tr>").append("\n");
				html.append("</thead>").append("\n");
				html.append("<tbody>").append("\n");

				for (int i = 0; i < ds_content1.size(); i++) {

					if (!"99".equals(ds_content1.get(i).get("GUBUN"))) {
						html.append("<tr>").append("\n");
						if ("70".equals(ds_content1.get(i).get("GUBUN"))) {
							html.append("<td colspan= '3'>합계</td>").append("\n");
						} else {
							String rowNum1 = (String) ds_content1.get(i).getOrDefault("ROW_NUM1", "");
							if ("1".equals(rowNum1)) {
								html.append("<td rowspan='").append(ds_content1.get(i).get("ROW_CNT1")).append("'>")
										.append(ds_content1.get(i).get("CHANNELNAME")).append("</td>").append("\n");
							}
						}

						if ("50".equals(ds_content1.get(i).get("GUBUN"))) {
							html.append("<td colspan='2'>소계</td>").append("\n");
						} else {
							String rowNum2 = (String) ds_content1.get(i).getOrDefault("ROW_NUM2", "");
							if ("1".equals(rowNum2)) {
								html.append("<td rowspan='").append(ds_content1.get(i).get("ROW_CNT2")).append("'>")
										.append(ds_content1.get(i).get("DISUSETYPENAME")).append("</td>").append("\n");
							}
						}
						if ("30".equals(ds_content1.get(i).get("GUBUN"))) {
							html.append("<td>").append(ds_content1.get(i).get("REASONCODE")).append("</td>")
									.append("\n");
						}

						for (int j = 0; j < ds_costcd.size(); j++) {
							String tempWeight = ds_content1.get(i).get("COSTCD" + String.valueOf(j)).toString();
							html.append("<td class='td_right'>").append(getComma(tempWeight)).append(" KG </td> ")
									.append("\n");

						}
						html.append("</tr> ").append("\n");
					} else {
						html.append("</tbody>").append("\n");
						html.append("<tfoot> ").append("\n");
						html.append("<tr>").append("\n");
						html.append("<td colspan='3'>총합계</td>").append("\n");
						for (int j = 0; j < ds_costcd.size(); j++) {
							String tempWeight = ds_content1.get(i).get("COSTCD" + String.valueOf(j)).toString();
							html.append("<td class='td_right'>").append(getComma(tempWeight)).append(" KG </td> ")
									.append("\n");

						}
						html.append("</tr>").append("\n");
						html.append("</tfoot>").append("\n");
					}
				}
				html.append("</table>").append("\n");
			}

		} else if ("SCM09".equals(formId)) { // 재고폐승인요청서(KX센터)

			// 전자결재 대상건 조회
			List<Map<String, Object>> ds_costcd = commonDao.selectList(SERVICEID_PREFIX + "selectCostCd", cmElecReqDto);

			StringBuffer COSTCD1 = new StringBuffer();
			StringBuffer COSTCD2 = new StringBuffer();
			StringBuffer COSTCD3 = new StringBuffer();
			StringBuffer COSTCD4 = new StringBuffer();
			for (int i = 0; i < ds_costcd.size(); i++) {
				if (i > 0) {
					COSTCD1.append(","); // 피벗용
					COSTCD2.append(","); // 컬럼용
					COSTCD3.append(","); // sum컬럼용
					COSTCD4.append(","); // 포맷컬럼용
				}
				COSTCD1.append("'").append(ds_costcd.get(i).get("COSTCD")).append("' AS COSTCD")
						.append(String.valueOf(i)).append("\n");
				COSTCD2.append("NVL(COSTCD").append(String.valueOf(i)).append(",0) AS COSTCD").append(String.valueOf(i))
						.append("\n");
				COSTCD3.append("SUM(NVL(COSTCD").append(String.valueOf(i)).append(",0)) AS COSTCD")
						.append(String.valueOf(i)).append("\n");
				COSTCD4.append("COSTCD").append(String.valueOf(i)).append(" AS COSTCD").append(String.valueOf(i))
						.append("\n");
			}
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("approvalReauestDt", cmElecReqDto.getApprovalReauestDt());
			inParams.put("approvalReauestNo", cmElecReqDto.getApprovalReauestNo());
			inParams.put("COSTCD1", COSTCD1.toString());
			inParams.put("COSTCD2", COSTCD2.toString());
			inParams.put("COSTCD3", COSTCD3.toString());
			inParams.put("COSTCD4", COSTCD4.toString());

			List<Map<String, Object>> ds_content1 = commonDao.selectList2(SERVICEID_PREFIX + "selectElecForm2Cont1",
					inParams);

			html.append("<div class='tablecontent_sub_title'>").append("\n");
			html.append("<div class='label'>세부내역</div>").append("\n");
			html.append("</div>").append("\n");
			html.append("").append("\n");
			html.append("<table class='tablecontent tablecontent_grid' id='datatable1'>").append("\n");
			html.append("<colgroup>").append("\n");
			html.append("<col class='col1'>").append("\n");
			html.append("<col class='col2'>").append("\n");
			int colCnt = ds_costcd.size() + 3;
			for (int i = 4; i <= colCnt; i++) {
				html.append("<col class='col").append(i).append("'>").append("\n");
			}
			html.append("<col class='col").append(colCnt + 1).append("'>").append("\n");
			html.append("</colgroup>").append("\n");
			html.append("<thead>").append("\n");
			html.append("<tr>").append("\n");
			html.append("<th rowspan='2'>구분</th>").append("\n");
			html.append("<th rowspan='2'>세부내역</th>").append("\n");
			html.append("<th colspan='").append(ds_costcd.size()).append("'>귀책</th>").append("\n");
			html.append("<th rowspan='2'>총합계</th>").append("\n");
			html.append("</tr>").append("\n");
			html.append("").append("\n");
			html.append("<tr>").append("\n");
			for (int i = 0; i < ds_costcd.size(); i++) {
				html.append("<th>").append(ds_costcd.get(i).get("CODECDNAME").toString()).append("</th> ").append("\n");
			}
			html.append("</tr>").append("\n");
			html.append("</thead>").append("\n");
			html.append("<tbody>").append("\n");

			String oldType = "";
			String newType = "";

			for (int i = 0; i < ds_content1.size(); i++) {
				float total = 0;
				newType = ds_content1.get(i).get("DISUSETYPE") == null
						|| "null".equals(ds_content1.get(i).get("DISUSETYPE")) ? ""
								: (String) ds_content1.get(i).get("DISUSETYPE");

				html.append("<tr>").append("\n");
				if (!newType.equals(oldType)) {
					oldType = newType;
					if ("50".equals(ds_content1.get(i).get("GUBUN"))) {
						html.append("<td rowspan='5'>").append("합계").append("</td>").append("\n");
					} else {

						html.append("<td rowspan='5'>").append(ds_content1.get(i).get("DISUSETYPENAME")).append("</td>")
								.append("\n");
					}
				}
				html.append("<td>").append(ds_content1.get(i).get("DETAILNAME")).append("</td>").append("\n");
				for (int j = 0; j < ds_costcd.size(); j++) {
					String tempWeight = (ds_content1.get(i).get("COSTCD" + String.valueOf(j)) == null
							|| "null".equals(ds_content1.get(i).get("COSTCD" + String.valueOf(j)).toString())) ? "0"
									: ds_content1.get(i).get("COSTCD" + String.valueOf(j)).toString();
					total += Float.parseFloat(tempWeight);
					html.append("<td class='td_right'>").append(getComma(tempWeight)).append("</td> ").append("\n");

				}
				html.append("<td class='td_right'>").append(getComma(String.valueOf(total))).append("</td> ")
						.append("\n");
				html.append("</tr>").append("\n");
			}

			html.append("</tbody>").append("\n");
			html.append("</table>");

		} else if ("SCM10".equals(formId)) { // 밀키트 처리
			List<Map<String, Object>> ds_content1 = commonDao.selectList(SERVICEID_PREFIX + "selectElecForm5Cont1", cmElecReqDto);
			/*단가 가져오기 START*/
			Map<String, BigDecimal> responseStockAmtMap = new LinkedHashMap<>();
			String searchKey = "";
			String resultKey = "";
			String dccode = "";
			int paramListCnt = 500;
			int paramRow = 0;
			int updateDsRow = 0;
			int findRow = 0;
			int rsltCnt = 0;

			//중복제외리스트
			Map<String, Map<String, Object>> distinctMap = new LinkedHashMap<>();
			for (Map<String, Object> item : ds_content1) {
				String k =  String.valueOf(item.get("DCCODE"))
						   + String.valueOf(item.get("SKU"))
						   + String.valueOf(item.get("UOM"));
				distinctMap.putIfAbsent(k, item);
			}
			List<Map<String, Object>> distinctList = new ArrayList<>(distinctMap.values());

			rsltCnt = distinctList.size();

			SI_MM0090_SCM_SOProxy proxy = new SI_MM0090_SCM_SOProxy();

			try {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");

				DT_MM0090_SCMIF_ST_STOCKAMT param = new DT_MM0090_SCMIF_ST_STOCKAMT();
				DT_MM0090_SCMIF_ST_STOCKAMT[] paramList = null;
				if ( rsltCnt <= paramListCnt ) {
					paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ rsltCnt ];
				}else{
					paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
				}

				for (int DsRow = 0; DsRow < rsltCnt; DsRow++ ) {
					param = new DT_MM0090_SCMIF_ST_STOCKAMT();

					param.setPLANT(String.valueOf(distinctList.get(DsRow).get("DCCODE")));
					param.setSKU(String.valueOf(distinctList.get(DsRow).get("SKU")));
					param.setUOM(String.valueOf(distinctList.get(DsRow).get("UOM")));

					paramList[paramRow] = param;
					paramRow++;

					//500건씩 or 마지막row일떄 통신진행
					if (paramRow != paramListCnt && rsltCnt != (DsRow + 1)) {
						continue;
					}

					DT_MM0090_SCM reqData = new DT_MM0090_SCM();
					reqData.setXSYS("WMS");
					reqData.setXDATS(dateFormat.format(calendar.getTime()));
					reqData.setXTIMS(timeFormat.format(calendar.getTime()));
					reqData.setXROWS(String.valueOf(paramRow));

					reqData.setIF_ST_STOCKAMT(paramList);

					DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] response = proxy.si_mm0090_scm_so(reqData);

					//리스폰스 담기
					for (int i = 0; i < paramRow; i++ ) {
						resultKey =  response[i].getPLANT()
								   + response[i].getSKU()
								   + response[i].getUOM();
	//					responseStockAmtMap.putIfAbsent(resultKey, BigDecimal.valueOf(Double.parseDouble(response[i].getSTOCKAMT()))); //뭐가 단가인지 모름
						responseStockAmtMap.putIfAbsent(resultKey, BigDecimal.valueOf(Double.parseDouble(response[i].getPURCHASEPRICE()))); //뭐가 단가인지 모름
					}

					if ( rsltCnt - DsRow > paramListCnt ) {
						paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
					} else {
						paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ rsltCnt - DsRow];
					}

					paramRow = 0;
				}
			} catch(Exception e) {
				log.error("재고금액 처리중 에러발생", e);
			}

			//원본 리스트 stockamt 업데이트
			for(Map<String, Object> item : ds_content1) {
				String k =  String.valueOf(item.get("DCCODE"))
						   + String.valueOf(item.get("SKU"))
						   + String.valueOf(item.get("UOM"));
				item.put("PURCHASEPRICE", Objects.isNull(responseStockAmtMap.get(k)) ? BigDecimal.ZERO : responseStockAmtMap.get(k));
			}
			/*단가 가져오기 END*/

			html.append("<div class='tablecontent_sub_title'>").append("\n");
			html.append("<div class='label'>KIT상품/구성품 조정내용</div>").append("\n");
			html.append("</div>").append("\n");
			html.append("<table class='tablecontent tablecontent_grid' id='datatable1'>").append("\n");
			html.append("<colgroup>").append("\n");
			html.append("<col class='col1'>").append("\n");
			html.append("<col class='col3'>").append("\n");
			html.append("<col class='col1'>").append("\n");
			html.append("<col class='col1'>").append("\n");
			html.append("</colgroup>").append("\n");
			html.append("<thead>").append("\n");
			html.append("<tr>").append("\n");
			html.append("<th>상품코드</th>").append("\n");
			html.append("<th>상품명</th>").append("\n");
			html.append("<th>감모총액</th>").append("\n");
			html.append("<th>역감모총액</th>").append("\n");
			html.append("</tr>").append("\n");
			html.append("</thead>").append("\n");
			html.append("<tbody>").append("\n");

			DecimalFormat df = new DecimalFormat("#,###.###");
			for (int i = 0; i < ds_content1.size(); i++) {
				BigDecimal purchasePrice = BigDecimal.valueOf(Double.parseDouble(String.valueOf(ds_content1.get(i).get("PURCHASEPRICE"))));
				BigDecimal qty = BigDecimal.valueOf(Double.parseDouble(String.valueOf(ds_content1.get(i).get("QTY"))));

				html.append("<tr>").append("\n");
				html.append("<td>").append(ds_content1.get(i).get("SKU")).append("</td>").append("\n");
				html.append("<td class='td_left'>").append(ds_content1.get(i).get("SKU_NAME")).append("</td>").append("\n");
				if("Y".equals(ds_content1.get(i).get("KIT_YN"))) {
					html.append("<td class='td_right'>").append(0).append("</td>").append("\n");
					html.append("<td class='td_right'>").append(df.format(purchasePrice.multiply(qty))).append("</td>").append("\n");
				}else{
					html.append("<td class='td_right'>").append(df.format(purchasePrice.multiply(qty))).append("</td>").append("\n");
					html.append("<td class='td_right'>").append(0).append("</td>").append("\n");
				}
				html.append("</tr>").append("\n");
			}
			html.append("</tbody>").append("\n");
			html.append("</table>");

		} else if ("SCM11".equals(formId)) { // 매각출고처리(외부창고)
			List<Map<String, Object>> ds_content = commonDao.selectList(SERVICEID_PREFIX + "selectElecForm3Cont", cmElecReqDto);

			html.append("<div class='tablecontent_sub_title'>").append("\n");
			html.append("<div class='label'>내역</div>").append("\n");
			html.append("</div>").append("\n");
			html.append("").append("\n");
			html.append("<table class='tablecontent tablecontent_grid' id='datatable1'>").append("\n");
			html.append("<colgroup>").append("\n");
			html.append("<col class='col1'>").append("\n");
			html.append("<col class='col2'>").append("\n");
			html.append("<col class='col3'>").append("\n");
			html.append("<col class='col4'>").append("\n");
			html.append("<col class='col5'>").append("\n");
			html.append("<col class='col6'>").append("\n");
			html.append("<col class='col7'>").append("\n");
			html.append("<col class='col8'>").append("\n");
			html.append("<col class='col9'>").append("\n");
			html.append("</colgroup> ").append("\n");
			html.append("<thead>").append("\n");
			html.append("<tr>").append("\n");
			html.append("<th>상품코드</th>").append("\n");
			html.append("<th>상품명</th> ").append("\n");
			html.append("<th>B/L NO.</th>").append("\n");
			html.append("<th>단위</th>").append("\n");
			html.append("<th>수량</th>").append("\n");
			html.append("<th>귀속부서 코드/명</th>").append("\n");
			html.append("<th>매각금액</th>").append("\n");
			html.append("<th>입금액</th>").append("\n");
			html.append("<th>차이액</th>").append("\n");
			html.append("</tr>").append("\n");
			html.append("</thead>").append("\n");
			html.append("<tbody>").append("\n");

			for (int i = 0; i < ds_content.size(); i++) {
				html.append("<tr>").append("\n");
				html.append("<td>").append(ds_content.get(i).get("SKU")).append("</td>").append("\n");
				html.append("<td>").append(ds_content.get(i).get("SKUNAME")).append("</td>").append("\n");
				html.append("<td>").append(ds_content.get(i).get("BLNO")).append("</td>").append("\n");
				html.append("<td>").append(ds_content.get(i).get("UOM")).append("</td>").append("\n");
				html.append("<td class='td_right'>").append(ds_content.get(i).get("TRANQTY")).append("</td>").append("\n");
				html.append("<td>").append(ds_content.get(i).get("COSTCDNAME")).append("</td>").append("\n");
				html.append("<td class='td_right'>").append(ds_content.get(i).get("AMT")).append("</td>").append("\n");
				html.append("<td class='td_right'>").append(ds_content.get(i).get("DEPOSIT_AMOUNT")).append("</td>").append("\n");
				html.append("<td class='td_right'>").append(ds_content.get(i).get("WRBTR")).append("</td>").append("\n");
				html.append("</tr>").append("\n");
			}

			html.append("</tbody>").append("\n");
			html.append("</table>").append("\n");

		} else if ("SCM12".equals(formId)) { // 외부창고정산 비용기표

            // 전자결재 대상건 조회
            List<Map<String, Object>> ds_content = commonDao.selectList(SERVICEID_PREFIX + "selectElecForm6Cont1", cmElecReqDto);

            html.append("<div class='tablecontent_sub_title'>").append("\n");
            html.append("<div class='label'>내역</div>").append("\n");
            html.append("</div>").append("\n");
            html.append("").append("\n");
            html.append("<table class='tablecontent tablecontent_grid' id='datatable1'>").append("\n");
            html.append("<colgroup>").append("\n");
            html.append("<col class='col1'>").append("\n");
            html.append("<col class='col2'>").append("\n");
            html.append("<col class='col3'>").append("\n");
            html.append("<col class='col4'>").append("\n");
            html.append("<col class='col5'>").append("\n");
            html.append("<col class='col6'>").append("\n");
            html.append("<col class='col7'>").append("\n");
            html.append("<col class='col8'>").append("\n");
            html.append("</colgroup> ").append("\n");
            html.append("<thead>").append("\n");
            html.append("<tr>").append("\n");
            html.append("<th>전기일</th>").append("\n");
            html.append("<th>증빙일</th> ").append("\n");
            html.append("<th>협력사명</th>").append("\n");
            html.append("<th>비용구분</th>").append("\n");
            html.append("<th>증빙구분</th>").append("\n");
            html.append("<th>공급가액</th>").append("\n");
            html.append("<th>부가세</th>").append("\n");
            html.append("<th>총액</th>").append("\n");
            html.append("</tr>").append("\n");
            html.append("</thead>").append("\n");
            html.append("<tbody>").append("\n");

            for (int i = 0; i < ds_content.size(); i++) {
                html.append("<tr>").append("\n");
                html.append("<td>").append(ds_content.get(i).get("ISSUE_DATE")).append("</td>").append("\n");
                html.append("<td>").append(ds_content.get(i).get("TAX_YMD")).append("</td>").append("\n");
                html.append("<td>").append(ds_content.get(i).get("ADJUSTMENT_SUPPLIER_NAME")).append("</td>").append("\n");
                html.append("<td>").append(ds_content.get(i).get("ACCOUNT_DETAIL")).append("</td>").append("\n");
                html.append("<td>").append(ds_content.get(i).get("TAX_TAG")).append("</td>").append("\n");
                html.append("<td class='td_right'>").append(ds_content.get(i).get("SUPPLY_PRICE")).append("</td>").append("\n");
                html.append("<td class='td_right'>").append(ds_content.get(i).get("TAX_AMOUNT")).append("</td>").append("\n");
                html.append("<td class='td_right'>").append(ds_content.get(i).get("AMOUNT")).append("</td>").append("\n");
                html.append("</tr>").append("\n");
            }

            html.append("</tbody>").append("\n");
            html.append("</table>").append("\n");

		}

		cmElecResDto.setElecHtml(html.toString());

		return cmElecResDto;
	}

	/**
	 * @description : 전자결재 상태값 업데이트
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.22 JangGwangSeok (breaker3317@cj.net) 생성
	 * 2025.10.30 KimSunHo(sunhokim6229@cj.net) 외부비축센터의 비용기표 결재(승인) 후 처리 추가</pre>
	 */
	public CmElecResDto saveElecStatus(CmElecReqDto cmElecReqDto) {
        // 결재별 유형 조회 및 처리
        String approvalRequestNo = cmElecReqDto.getApprovalReauestNo();
        String approvalRequestDt = cmElecReqDto.getApprovalReauestDt();
        String status = cmElecReqDto.getStatus();
        String docId = cmElecReqDto.getDocId();
        String approveId = cmElecReqDto.getApproveId();
        String approveNm = cmElecReqDto.getApproveNm();

        // 서버 주소
        String serverUrl = ContextUtil.getProperty("cf.domainUrl");

        CmElecResDto cmElecResDto = new CmElecResDto();
        try {
            List<Map<String, Object>> ds_appr = commonDao.selectList(SERVICEID_PREFIX + "selectElecApprDoctype", cmElecReqDto);
            if (!ObjectUtils.isEmpty(ds_appr) && ds_appr.size() > 0) {
                if ("2170".equals(ds_appr.get(0).get("DCCODE")) && "3".equals(status) && "AJ".equals(ds_appr.get(0).get("DOCTYPE"))) {
                    // 외부비축 폐기, 감모, 매각 자동처리 (최종결재 시)
                    String params = "approvalreqdt="+approvalRequestDt+"&approvalreqno="+approvalRequestNo+"&apprstatus="+status+"&docId="+docId+"&approveId="+approveId+"&approveNm="+approveNm;
                    String url = serverUrl + "/api/st/adjustmentreqeustexdc/v1.0/saveStockAutoHandle?"+params;
                    String res = restTemplate.getForObject(url, String.class);
                    cmElecResDto.setApprovalAvailableYN("1");
                    cmElecResDto.setErrorMessage("");

                } else {
                    commonDao.update(SERVICEID_PREFIX + "updateElecStatus", cmElecReqDto);
                    cmElecResDto.setApprovalAvailableYN("1");
                    cmElecResDto.setErrorMessage("");

                    if (!approvalRequestNo.isEmpty()) {
                        if ("IBEXPENSE".equals(ds_appr.get(0).get("DOCTYPE")) && ("3".equals(status) || "0".equals(status))) {
                            //외부비축센터의 비용기표 결재(승인) 후 처리
                            String url = serverUrl + "/api/ib/expense/v1.0/getApprPosting/" + approvalRequestNo + "/" + status;
                            String res = restTemplate.getForObject(url, String.class);
                        }
                    }
                }
            } else {
                cmElecResDto.setApprovalAvailableYN("0");
                cmElecResDto.setErrorMessage("처리할 승인 건이 없습니다.");
            }
        } catch (Exception e) {
            cmElecResDto.setApprovalAvailableYN("0");
            cmElecResDto.setErrorMessage(e.getMessage());
        }

        return cmElecResDto;
	}

	public  String getComma(String strNum) {
		double dNum;
		String formatted;

		if (strNum == null) {
			dNum = 0;
		} else if(strNum.trim().length() == 0) {
			dNum = 0;
		} else if(strNum.indexOf(',') >= 0) {
			return strNum;
		} else {
			dNum = Double.parseDouble(strNum);
		}

		if(dNum == 0) {
			formatted = "";
		} else {
			formatted = numberFormat(dNum);
		}

		return formatted;
	}

	public  String numberFormat(double num) {
		return numberFormat(num, "");
	}

	public  String numberFormat(double num, String pattern) {
		DecimalFormat dFormatter = new DecimalFormat(pattern);
		NumberFormat nFormatter = NumberFormat.getInstance();

		String formatted;

		if(pattern.length() > 0) {
			formatted = dFormatter.format(num);
		} else {
			formatted = nFormatter.format(num);
		}

		return formatted;
	}

}
