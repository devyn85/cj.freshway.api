package cjfw.wms.ms.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsCreditInfoDto;
import cjfw.wms.webservice.credit.DT_SCM0070_SCM;
import cjfw.wms.webservice.credit.DT_SCM0070_SCMIF_MS_CUSTAMT;
import cjfw.wms.webservice.credit.DT_SCM0070_SCM_responseIF_MS_CUSTAMT_RET;
import cjfw.wms.webservice.credit.SI_SCM0070_SCM_SOProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : SangSuSung(kduimux@cj.com) 
 * @date        : 2025.06.23
 * @description : 여신정보 IF Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.10 SangSuSung(kduimux@cj.cj.com) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCreditInfoTotalService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private final UserContext userContext;
	private final MsCreditInfoService msCreditInfoService;
	
	
	/**
	 * 여신정보 조회<br>
	 * AS-IS : MsCreditInfoTotalService.java >>> saveData() 구현
	 * 
	 * @param inParams
	 * @return
	 */
	public String saveData(MsCreditInfoDto dto) {		
		String storerkey = userContext.getStorerkey();
		
		List<MsCreditInfoDto> creditList = new ArrayList<MsCreditInfoDto>();
		List<MsCreditInfoDto> inList = dto.getCreditList();
		log.info("inList : {}", inList);
		
		// 현재날짜+1  구하기
		Calendar cal = new GregorianCalendar();
	    cal.add(Calendar.DATE, 1);
	    Date date = cal.getTime();	    
	    String nextDate = new SimpleDateFormat("yyyyMMdd").format(date);
		
	    /* 여신정보가 이미 저장되어 있는지 체크한다.
		 * 현재날자+1  = 출고일자 같으면 무조건 여신정보를 가져오고
		 * 현재날자+1 != 출고일자이면 여신정보가 없으면 호출할수 있도록 
		 * ds_credit에다가 정보를 담아 그 정보에 따라 호출한다.
		 * 2015.10.21 sypark 
		 */	
		if (inList != null && inList.size() > 0) {
			for(MsCreditInfoDto creditInfoDto : inList) {
				creditInfoDto.setYear(creditInfoDto.getDeliverydt().substring(0,4));
				creditInfoDto.setMonth(creditInfoDto.getDeliverydt().substring(4,6));
				creditInfoDto.setDay(creditInfoDto.getDeliverydt().substring(6,8));
				
				int totalCnt = 0;
				totalCnt = msCreditInfoService.getTotalCount(creditInfoDto);
				
				String deliveryDt = creditInfoDto.getDeliverydt();
				
				if(totalCnt == 0  || deliveryDt.equals(nextDate)) {
					MsCreditInfoDto credit = new MsCreditInfoDto();
					credit.setYear(creditInfoDto.getDeliverydt().substring(0,4));
					credit.setMonth(creditInfoDto.getDeliverydt().substring(4,6));
					credit.setCustkey(creditInfoDto.getCustkey());
					credit.setDeliverydt(creditInfoDto.getDeliverydt());
					creditList.add(credit);
				}
			}
		}
		
		if ("CJFW".equals(storerkey)) {
			// "CJFW" 회사 제외 대상으로 해당 로직 미구현
		} else if ("FW00".equals(storerkey)) {
			// 여신조회하는 Webservice
			SI_SCM0070_SCM_SOProxy proxy = new SI_SCM0070_SCM_SOProxy();
			
			try {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");		
				
				DT_SCM0070_SCM credittData = new DT_SCM0070_SCM();
				credittData.setXSYS("SCM");
				credittData.setXDATS(dateFormat.format(calendar.getTime()));
				credittData.setXTIMS(timeFormat.format(calendar.getTime()));
				
				DT_SCM0070_SCMIF_MS_CUSTAMT[] custAmtList = null;
				if(creditList.size() > 0) {
					int rowSize = creditList.size();
					custAmtList = new DT_SCM0070_SCMIF_MS_CUSTAMT[rowSize];
					DT_SCM0070_SCMIF_MS_CUSTAMT custInfo = new DT_SCM0070_SCMIF_MS_CUSTAMT();
					
					// 전체 Row 설정
					credittData.setXROWS(Integer.toString(rowSize));
					
					for(int i = 0; i < creditList.size(); i++) {
						MsCreditInfoDto credit = creditList.get(i);
						custInfo = new DT_SCM0070_SCMIF_MS_CUSTAMT();
						
						custInfo.setGJAHR(credit.getDeliverydt().substring(0,4));
						custInfo.setZMONTH(credit.getDeliverydt().substring(4,6));
						custInfo.setDELIVERYDATE(credit.getDeliverydt());
						custInfo.setKUNNR(credit.getCustkey());
						custAmtList[i] = custInfo;
					}
				}
				
				if(custAmtList != null) {
					credittData.setIF_MS_CUSTAMT(custAmtList);
					DT_SCM0070_SCM_responseIF_MS_CUSTAMT_RET[] amtResponse = proxy.si_scm0070_scm_so(credittData);
					
					if (amtResponse != null) {
						for(int i = 0; i < amtResponse.length; i++) {
						    MsCreditInfoDto creditInfoDto = new MsCreditInfoDto();
						    creditInfoDto.setGjahr(amtResponse[i].getGJAHR());
						    creditInfoDto.setZmonth(amtResponse[i].getZMONTH());
						    creditInfoDto.setDay(amtResponse[i].getDELIVERYDATE().substring(6, 8));
						    creditInfoDto.setDeliverydt(amtResponse[i].getDELIVERYDATE());
						    creditInfoDto.setKunnr(amtResponse[i].getKUNNR());
						    creditInfoDto.setKlimk(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getKLIMK()).trim()));
						    creditInfoDto.setSales(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getSALES()).trim()));
						    creditInfoDto.setZoutput(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getZOUTPUT()).trim()));
						    creditInfoDto.setZinput(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getZINPUT()).trim()));
						    creditInfoDto.setZdelayTxt(amtResponse[i].getZDELAY_TXT());
						    creditInfoDto.setZjango(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getZJANGO()).trim()));
						    creditInfoDto.setZjangoM(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getZJANGO_M()).trim()));
						    creditInfoDto.setMoutput(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getMOUTPUT()).trim()));
							log.info("@@@ proxy.si_scm0070_scm_so 결과값 : {}", creditInfoDto);
							msCreditInfoService.insertToBe(creditInfoDto);
						}
					}
				}
			} catch(Exception e) {
				log.error("Exception", e);
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	
}
