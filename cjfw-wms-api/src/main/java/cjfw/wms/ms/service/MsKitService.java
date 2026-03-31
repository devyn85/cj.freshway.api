package cjfw.wms.ms.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.sap.JCoUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsKitReqDto;
import cjfw.wms.ms.dto.MsKitResDto;
import cjfw.wms.ms.entity.MsKitEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.02
 * @description : 기준정보 > 상품기준정보 > KIT상품기준정보 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.02        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsKitService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msKitService.";
	private transient static final String SAP_FUNCTION_NAME = "ZMM_CREATE_MATNR_KIT";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * 
	 * @description : Kit상품기준정보 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.02        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsKitResDto> getMasterList(MsKitReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * 
	 * @description : Kit상품기준정보 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.02        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsKitResDto> getKitSkuList(MsKitReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getKitSkuList", dto);
	}
	
	/**
	 * 
	 * @throws JCoException 
	 * @description : Kit상품기준정보 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.02        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Transactional
	public String saveMasterList(List<MsKitReqDto> list) throws JCoException {
		if (!ObjectUtils.isEmpty(list)) {
		    
		    if ( this.isValidByKitInfo(list)) {
		     // 1. MATNR (KitSku) 기준으로 리스트를 그룹화
	            Map<String, List<MsKitReqDto>> groupedList = list.stream().collect(Collectors.groupingBy(MsKitReqDto::getKitSku));
	            
	            // 2. 그룹화된 리스트를 순회하며 N번 SAP 호출
	            for (Map.Entry<String, List<MsKitReqDto>> entry : groupedList.entrySet()) {
	                String matnr = entry.getKey();
	                List<MsKitReqDto> subList = entry.getValue();
	                
	                JCoUtil jco = new JCoUtil();
	                JCoDestination destination = jco.getDestination();
	                JCoFunction function = jco.getFunction(SAP_FUNCTION_NAME);
	                
	                // RFC 함수 선언
	                if (function == null) {
	                    log.error("▶Kit상품기준정보 저장 -> " + SAP_FUNCTION_NAME + " not found in SAP.");
	                    throw new UserHandleException(SAP_FUNCTION_NAME + " not found in SAP.");
	                }
	                
	                JCoParameterList params = function.getImportParameterList();
	                JCoParameterList returnData = null;
	                JCoTable table = function.getTableParameterList().getTable("T_ZMMT1800");
	                table.clear();
	                
	                log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++ SAP 시작");
	                
	                String iRewk = ""; 
	                char xCrud = ' ';
	                
	                for (MsKitReqDto dto : subList) {
	                    var entity = ModelMapperUtil.map(dto, userContext, MsKitEntity.class);
	                    
	                    // 중복 저장 체크
	                    if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
	                        int duplicateCount = commonDao.selectOne(SERVICEID_PREFIX + "getDuplicateCount", entity);
	                        if (duplicateCount > 0) {
	                            log.error("▶Kit상품기준정보 저장 (상품: {}) -> 기간이 중복된 데이터가 존재합니다.", dto.getKitSku());
	                            throw new UserHandleException("기간이 중복된 데이터가 존재합니다. (상품: " + dto.getKitSku() + ")");
	                        }
	                        
	                        commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
	                    } else {
	                        commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
	                    }
//	                    commonDao.update(SERVICEID_PREFIX +"saveMaster", entity);
	                    
	                    
	            		// START.필수입력
	            		if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getKitNm()))) throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"kitNm"} ) ); // 해당 정보가 없어 처리할 수 없습니다.-{0}
	            		// END.필수입력	                    
	                    commonDao.update(SERVICEID_PREFIX +"saveKitSkuMasterInfoHis", entity);
	                    
	                    List<MsKitResDto> masterList = getMasterList(dto);

	                    // I_REWK, XCRUD 설정
	                    if (dto.getRowStatus().equals("I")) {
	                        iRewk = "1";
	                        xCrud = 'C';
	                    } else {
	                        iRewk = "2";
	                        xCrud = 'U';
	                    }
	                    
	                    table.appendRow();
	                    
	                    // 날짜/시간 포맷팅
	                    LocalDateTime addDateTime = LocalDateTime.parse(
	                        masterList.get(0).getAddDate(), 
	                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
	                    );
	                    String sapDate = addDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	                    String sapTimestamp = addDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

	                    // SAP 테이블 값 설정
	                    table.setValue("ZID", sapTimestamp);
	                    table.setValue("IFDATE", sapDate);
	                    table.setValue("IFSEQ", masterList.get(0).getSerialKey());
	                    table.setValue("WERKS" , entity.getDcCode());
	                    table.setValue("MATNR" , entity.getKitSku());
	                    table.setValue("MENGE" , entity.getQty());
	                    table.setValue("MEINS", masterList.get(0).getKitUom());
	                    table.setValue("MATNR_K", entity.getSku());
	                    table.setValue("MENGE_K", entity.getUomQty());
	                    table.setValue("MEINS_K", entity.getUom());                    
	                    if(entity.getDelYn().equals("Y")) {
	                        table.setValue("ZSTAT", "X");
	                    } else {
	                        table.setValue("ZSTAT", "");
	                    }
	                    table.setValue("ZTEXT1", entity.getPrtnYn());
	                    table.setValue("ZTEXT2", entity.getFromDate());
	                    table.setValue("ZTEXT3", entity.getToDate());
	                    table.setValue("XUSER", entity.getGUserId());
	                    table.setValue("XROWS", 1);
	                    table.setValue("XCRUD", xCrud);
	                }
	                
	                // Import Parameter 설정 (그룹의 마지막 요소 기준)
	                params.setValue("I_REWK", iRewk);
	                
	                log.info(function.toXML());
	                
	                try {
	                    function.execute(destination);
	                } catch (JCoException e) {
	                    log.error("▶Kit상품기준정보 저장 (MATNR: {}) -> SAP execute failed: {}", matnr, e.getMessage(), e);
	                    throw new UserHandleException("SAP execute failed for MATNR " + matnr + ": " + e.getMessage());
	                }

	                // 4. T_RETURN 확인
	                JCoTable returnTable = function.getTableParameterList().getTable("T_RETURN");
	                if (returnTable == null || returnTable.getNumRows() == 0) {
	                    log.error("▶Kit상품기준정보 저장 (MATNR: {}) -> SAP로부터 T_RETURN이 반환되지 않았습니다.", matnr);
	                    throw new UserHandleException("SAP로부터 T_RETURN이 반환되지 않았습니다. (MATNR: " + matnr + ")");
	                }

	                returnTable.setRow(0);
	                String status = returnTable.getString("XSTAT");
	                String message = returnTable.getString("XMSGS");

	                if (!"S".equals(status)) {
	                    log.error("▶Kit상품기준정보 저장 (MATNR: {}) -> SAP 통신 오류 발생: {}", matnr, message);
	                    throw new UserHandleException(message != null && !message.isEmpty() ? message : "SAP returned error (MATNR: " + matnr + ")");
	                }
	                
	                log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++ SAP 끝");
	            }
		    }
            
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.02        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsKitResDto> getValidateExcelList(List<MsKitReqDto> dtoList) {
		List<MsKitResDto> checkedResult = new ArrayList<MsKitResDto>();
		if(dtoList != null) {
			for (MsKitReqDto dto : dtoList) {
				MsKitResDto result = commonDao.selectOne(SERVICEID_PREFIX + "getValidateExcelList", dto);
				checkedResult.add(result);
			}
		}
		return checkedResult;
	}
	
	
	/**
     * @description : 동일 물류센터, kit상품코드, 구성상품코드, 시작일자, 중단일자 를 가지고 db 조회 하여 다음 조건에 맞는지 확인 
     *   return : true(유효함), false(유효하지 않음)
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.01.31 jun (kthis77@cj.net) 생성 </pre>
     */
    private Boolean isValidByKitInfo(List<MsKitReqDto> saveList) {
        
        boolean isValid = true;
        
        for(MsKitReqDto dto : saveList) {
            var entity = ModelMapperUtil.map(dto, userContext, MsKitEntity.class);
            int duplicateCount = commonDao.selectOne(SERVICEID_PREFIX + "getDuplicateCount", entity);
            if (duplicateCount > 0) {
                isValid= false;
                throw new UserHandleException("중복 정보가 존재 합니다.");
            }
        }
        return isValid;
    }
}
