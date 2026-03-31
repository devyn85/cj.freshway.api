package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.sap.JCoUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmCheckSAPCloseResDto;
import cjfw.wms.st.dto.StDailyInoutCloseReqDto;
import cjfw.wms.st.dto.StDailyInoutCloseResDto;
import cjfw.wms.st.entity.StDailyInoutCloseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.22
 * @description : 수불마감정보 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.22        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StDailyInoutCloseService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stDailyInoutCloseService.";
	private transient static final String SAP_FUNCTION_NAME = "ZFI_RECV_ZFIT1162I_RFC";

	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description : 수불마감정보 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<StDailyInoutCloseResDto> getMasterList(StDailyInoutCloseReqDto stDailyInoutCloseReqDto) {
		List<StDailyInoutCloseResDto> resultList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", stDailyInoutCloseReqDto);
		
		for (var dto : resultList) {
			try {
				CmCheckSAPCloseResDto sapCloseDto = getStatus(dto);
				dto.setSapInoutCloseYn(sapCloseDto.getResult());
			} catch (JCoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultList; 
	}
	
	/**
	 * 
	 * @description : 수불마감정보 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.22        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveMasterList(List<StDailyInoutCloseReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, StDailyInoutCloseEntity.class);
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * 
	 * @description : 수불마감정보 일괄 마감 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.22        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveBatchClose(StDailyInoutCloseReqDto dto) {
		commonDao.update(SERVICEID_PREFIX +"saveBatchClose", dto);
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
     * @throws JCoException 
     * @description : SAP 마감여부 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.12        JangJaeHyun (jhjang43@cj.net)       생성
     */
	public CmCheckSAPCloseResDto getStatus(StDailyInoutCloseResDto stDailyInoutCloseReqDto) throws JCoException {
	    CmCheckSAPCloseResDto resultDto = new CmCheckSAPCloseResDto();
	    
	    JCoDestination destination = JCoUtil.getDestination();
        JCoFunction function = JCoUtil.getFunction(SAP_FUNCTION_NAME);
        
        if (function == null) {
            resultDto.setResult("E");
            resultDto.setErrorMsg(SAP_FUNCTION_NAME +" not found in SAP.");
            
            log.error("▶SAP 마감여부 조회 -> " + SAP_FUNCTION_NAME + " not found in SAP.");
            throw new UserHandleException(SAP_FUNCTION_NAME + " not found in SAP.");
        }
	    
        //I_PERIOD(전기월), I_CODE(결산코드)
        JCoParameterList importParams = function.getImportParameterList();
        importParams.setValue("I_PERIOD", stDailyInoutCloseReqDto.getInoutDt().substring(0, 6)); // 전기월
        importParams.setValue("I_CODE", "WM001-" + stDailyInoutCloseReqDto.getDcCode());         // 결산코드
            
        function.execute(destination);
        
        JCoParameterList exportParams = function.getExportParameterList();
        String eMessage = exportParams.getString("E_XMSGS"); // 메시지
        String eStatus = exportParams.getString("E_XSTAT");  // 상태 ('S':성공, 'E':실패)
        
        log.info(eStatus);
        log.info(eMessage);
        
        if(!eStatus.equals("S")) {
        	eStatus = "F";
        }
        
        resultDto.setResult(eStatus);
        resultDto.setErrorMsg(eMessage);

	    return resultDto;
	}
}
