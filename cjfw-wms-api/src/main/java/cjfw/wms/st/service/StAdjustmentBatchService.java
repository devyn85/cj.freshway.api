package cjfw.wms.st.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.dto.StAdjustmentBatchReqDto;
import cjfw.wms.st.dto.StAdjustmentBatchResDto;
import cjfw.wms.st.dto.StAdjustmentCommCdResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.09.24
 * @description : 일괄재고조정 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.24 JiHoPark 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StAdjustmentBatchService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stAdjustmentBatchService.";
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";

	private final CommonDao commonDao;

	/**
	 * @description : 일괄재고조정 - zone 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentCommCdResDto> getZoneList() {
		return commonDao.selectList(SERVICEID_PREFIX + "getZoneList");
	}

	/**
	 * @description : 일괄재고조정 - 재고조정 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.24 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentBatchResDto> getMasterList(StAdjustmentBatchReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	/**
	 * @description : 일괄재고조정 - 일괄재고조정 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 JiHoPark  생성 </pre>
	 */
	public List<StAdjustmentBatchResDto> saveMasterList1(StAdjustmentBatchReqDto dto) {
    	String userId = dto.getGUserId();
    	String userSpId = dto.getGSpid();
    	String procedure = "SPST_CONVERT";
    	String processtype = dto.getProcesstype();

		String docdt = dto.getDocdt(); // 조정일자
		String reasoncode = dto.getReasoncode(); // 발생사유
		String costcd = dto.getCostcd(); // 코스트센터
		String imputetype = dto.getImputetype(); // 귀책
		String processmain = dto.getProcessmain(); // 물류귀책배부
		String custkey = dto.getCustkey(); // 대표고객 코드

		String columnsDto    = "DCCODE|STORERKEY|ORGANIZE|AREA|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|QTY|TRANQTY|LOTTABLE01|ETCQTY1|ETCQTY2";
		String columnsEntity = "FROM_DCCODE|FROM_STORERKEY|FROM_ORGANIZE|FROM_AREA|FROM_LOC|FROM_SKU|FROM_UOM|FROM_LOT|FROM_STOCKID|FROM_STOCKGRADE|FROM_STOCKTYPE|FROM_ORDERQTY|FROM_CONFIRMQTY|FROM_IOTYPE|ETCQTY1|ETCQTY2";

        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String returnMessage = "";

        int chunkSize = 200;

        // temp table 데이터 삭제 진행
        CmSyProcessTempStEntity tempStDto = new CmSyProcessTempStEntity();
        tempStDto.setGUserId(userId);
        tempStDto.setProcesstype(processtype);
        tempStDto.setGSpid(userSpId);
    	commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempST", tempStDto);

    	// temp table 데이터 생성
    	List<CmSyProcessTempStEntity> insertList = new ArrayList<CmSyProcessTempStEntity>();
        List<StAdjustmentBatchResDto> saveList = dto.getSaveMasterList1(); // 저장리스트
    	for (int i = 0; i < saveList.size(); i++) {
    		tempStDto = new CmSyProcessTempStEntity();
    		StAdjustmentBatchResDto saveDto = saveList.get(i);

			tempStDto = (CmSyProcessTempStEntity) EtcUtil.conversionEntityToAsis(saveDto, tempStDto, columnsDto, columnsEntity);
			tempStDto.setProcesstype(processtype);
			tempStDto.setProcesscreator(userId);
			tempStDto.setSpid(userSpId);
			tempStDto.setAddwho(userId);
			tempStDto.setEditwho(userId);

			insertList.add(tempStDto);

    	    // 200개마다 혹은 마지막 루프일 때 insert
    	    if (insertList.size() == chunkSize) {
    	        commonDao.insert(SERVICEID_TEMP_PREFIX + "insertSyProcessTempSt", insertList);
    	        insertList.clear(); // 다음 배치 준비
    	    }

    	}

    	// 남은 데이터 insert
    	if (insertList.size() > 0) {
    		commonDao.insert(SERVICEID_TEMP_PREFIX + "insertSyProcessTempSt", insertList);
    	}

    	// package proceduce 실행
        if (insertList.size() > 0) {
        	StAdjustmentBatchResDto saveDto = new StAdjustmentBatchResDto();

	        // PKG 파라마터 세팅 - 공통(1/4)
	        ProcedureParametersFactory.initParamDto(dto, saveDto, procedure);

	        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        	String[] keyList = { "PROCEDURE"
        			, "PROCESSTYPE"
        			, "PROCESSCREATOR"
        			, "SPID"
        			, "DOCDT"
        			, "REASONCODE"
        			, "COSTCD"
        			, "IMPUTETYPE"
        			, "PROCESSMAIN"
        			, "CUSTKEY"
                    };


	        Object[] valueList = { procedure
				        		, processtype
				        		, userId
				        		, userSpId
				        		, docdt
				        		, reasoncode
				        		, costcd
				        		, imputetype
				        		, processmain
				        		, custkey
	        		};

	        saveDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
	        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, saveDto);

	        // 프로시저 OUT Parameter(3/4)
	        resultCode    = (String)saveDto.getResultCode();
	        resultMessage = (String)saveDto.getResultMessage();
	        returnMessage =  saveDto.getReturnMessage();

	        // 프로시저 Exception 처리(4/4)
	        if(!"0".equals(resultCode)){
	            log.error("▶일괄재고조정 - 재고조정 처리 오류발생 ");
	            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
	        }
	    }

        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", dto);

	}
}
