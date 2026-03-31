/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.cm.service;

import org.springframework.stereotype.Service;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

import cjfw.core.exception.UserHandleException;
import cjfw.core.sap.JCoUtil;
import cjfw.wms.cm.dto.CmCheckSAPCloseReqDto;
import cjfw.wms.cm.dto.CmCheckSAPCloseResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.02 
 * @description : SAP 마감 체크 기능을 구현한 서비스 클래스 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmCheckSAPCloseService {
    
    private transient static final String SAP_FUNCTION_NAME = "Z_WMS_PO_CHECK";

    /**
     * @throws JCoException 
     * @description : SAP 마감여부 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.02    KimSunHo(sunhokim6229@cj.net)   생성
     */
	public CmCheckSAPCloseResDto getStatus(CmCheckSAPCloseReqDto cmCheckSAPCloseReqDto) throws JCoException {
	    CmCheckSAPCloseResDto resultDto = new CmCheckSAPCloseResDto();
	    resultDto.setResult("Y");
        resultDto.setErrorMsg("");
	    
	    if ("CJFW".equals(cmCheckSAPCloseReqDto.getStorerkey())) {
	    
    	    JCoDestination destination = JCoUtil.getDestination();
            JCoFunction function = JCoUtil.getFunction(SAP_FUNCTION_NAME);
            
            if (function == null) {
                resultDto.setResult("N");
                resultDto.setErrorMsg("Z_WMS_PO_CHECK not found in SAP.");
                
                log.error("▶SAP 마감여부 조회 -> " + SAP_FUNCTION_NAME + " not found in SAP.");
                throw new UserHandleException(SAP_FUNCTION_NAME + " not found in SAP.");
            }
    	    
            //I_GJAHR(회계년도), I_ZMONTH(월)
            JCoTable table = function.getTableParameterList().getTable("IT_CHECK");
            table.clear();
            table.appendRow();                  
            table.setValue("EBELN", cmCheckSAPCloseReqDto.getDocno());
            table.setValue("EBELP", cmCheckSAPCloseReqDto.getDocline());
            table.setValue("EINDT", cmCheckSAPCloseReqDto.getDeliverydate());
                
            function.execute(destination);
            
            for (int i = 0; i < table.getNumRows(); i++) {
                table.setRow(i);
                String result = table.getString("RESULT");
                String mess = table.getString("MESS");
                
                log.info(result);
                log.info(mess);
                
                resultDto.setResult(result);
                resultDto.setErrorMsg(mess);
            }	    
	    }

	    return resultDto;
	}
	
}
