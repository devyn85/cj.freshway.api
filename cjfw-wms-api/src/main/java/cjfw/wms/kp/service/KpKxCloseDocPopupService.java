package cjfw.wms.kp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.wms.kp.dto.KpKxCloseDocPopupReqDto;
import cjfw.wms.kp.dto.KpKxCloseDocPopupResDto;
import cjfw.wms.kp.dto.KpKxCloseDocPopupTransactionResDto;
import cjfw.wms.webservice.utility.CJServiceUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.12.12
 * @description : KX문서정보 팝업 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.12 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpKxCloseDocPopupService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "kpKxCloseDocPopupService.";
	
	private final CommonDao commonDao;
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class ApiResponse {
	 private List<DataContainer> data;
	}
	
	@Data
	private static class DataContainer {
		@JsonProperty("DATA_CNT")
		private int dataCnt;
		@JsonProperty("DATA_LIST")
		private List<KpKxCloseDocPopupResDto> dataList;
	}
	
	/** 
	 * @description : 문서정보 탭 목록 조회(전체)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12  JiSooKim (jskim14@cj.net)       생성
	 * ----------------------------------------------------------- 
	 */
	public KpKxCloseDocPopupResDto getDocinfo(KpKxCloseDocPopupReqDto cmPopupReqDto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getDocinfo", cmPopupReqDto); 
	}
	
	/** 
     * @description : 재고처리현황 탭 목록 조회(전체)
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.12  JiSooKim (jskim14@cj.net)       생성
     * ----------------------------------------------------------- 
     */
    public List<KpKxCloseDocPopupTransactionResDto> getTransactionList(KpKxCloseDocPopupReqDto cmPopupReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getTransactionList", cmPopupReqDto); 
    }
    public List<KpKxCloseDocPopupTransactionResDto> getDocumentDetailForDocno(KpKxCloseDocPopupReqDto cmPopupReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDocumentDetailForDocno", cmPopupReqDto); 
    }
    
    
    public List<KpKxCloseDocPopupTransactionResDto> getDocumentModifyDetailForDocno(KpKxCloseDocPopupReqDto cmPopupReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDocumentModifyDetailForDocno", cmPopupReqDto); 
    }
    
	/** 
     * @description : KX실적현황 탭 목록 조회(전체)
     * @issues      : 
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.12  JiSooKim (jskim14@cj.net)       생성
     * ----------------------------------------------------------- 
     */
    public List<KpKxCloseDocPopupResDto> getDocumentKx(KpKxCloseDocPopupReqDto cmPopupReqDto) {
    	List<KpKxCloseDocPopupResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDocumentHDcCode", cmPopupReqDto); 
    	
    	if(list.size() > 0) {
    		list = commonDao.selectList(SERVICEID_PREFIX + "getSendfxlist", cmPopupReqDto);
    	} else { 
    		throw new UserHandleException("검색된 결과가 없습니다."); 
    	}
    	
        return list; 
    }
    
    /**
     * @description : KX실적현황 탭 목록 조회(전체) (대한통운 API 버전)
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.04 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public List<KpKxCloseDocPopupResDto> getDocumentKx2(KpKxCloseDocPopupReqDto cmPopupReqDto) {
    	List<KpKxCloseDocPopupResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDocumentHDcCode", cmPopupReqDto); 
    	
    	if(list.size() > 0) {
    		Map<String, String> params = new HashMap<>();
    		params.put("ID", "FX");
    		params.put("TR_NAME", "WEB_ORDERS_TO_FX");
    		params.put("DOCNO", cmPopupReqDto.getDocno());

    		// 대한통운 API 호출
    		ApiResponse apiRes = CJServiceUtil.callCJService(params, ApiResponse.class, "/service/CJ/result");
    		
    		if (apiRes != null && apiRes.getData() != null && !apiRes.getData().isEmpty()) {
                // DATA_LIST 추출
    			list = apiRes.getData().get(0).getDataList();
    			
    			// 날짜 포맷에 맞게 변환
                for (KpKxCloseDocPopupResDto resDto : list) {
                	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd:HHmmss.SSSSSS");
					if(Objects.nonNull(resDto.getProcTime())) {
						LocalDateTime dateTime = LocalDateTime.parse(resDto.getProcTime(), formatter);
						DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						String formatted = dateTime.format(outputFormatter);
						resDto.setProcTime(formatted);
					}
                }
    		}
    	} else { 
    		throw new UserHandleException("검색된 결과가 없습니다."); 
    	}
    	
        return list; 
    }
    
	/** 
     * @description : KX실적현황 탭 목록 조회(TCS 소유권처리상태)
     * @issues      : 
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.12  JiSooKim (jskim14@cj.net)       생성
     * ----------------------------------------------------------- 
     */
    public List<KpKxCloseDocPopupResDto> getKxCloseDocPopupTCS(KpKxCloseDocPopupReqDto cmPopupReqDto) {    	
        return commonDao.selectList(SERVICEID_PREFIX + "getKxCloseDocPopupTCS", cmPopupReqDto);
    }
    
    /**
     * @description : KX실적현황 탭 목록 조회(TCS 소유권처리상태) (대한통운 API 버전)
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.04 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public List<KpKxCloseDocPopupResDto> getKxCloseDocPopupTCS2(KpKxCloseDocPopupReqDto cmPopupReqDto) {  
    	List<KpKxCloseDocPopupResDto> list = null;
    			
    	Map<String, String> params = new HashMap<>();
		params.put("ID", "FX");
		params.put("TR_NAME", "WEB_TRANSFER_TO_FX");
		params.put("KX_DCCODE", cmPopupReqDto.getKxDccode());
		params.put("TRANSFERKEY", cmPopupReqDto.getTransferkey());
//		params.put("KX_DCCODE", "1100");
//		params.put("TRANSFERKEY", "5111303096");
		
		// 대한통운 API 호출
		ApiResponse apiRes = CJServiceUtil.callCJService(params, ApiResponse.class, "/service/CJ/result");
		
		if (apiRes != null && apiRes.getData() != null && !apiRes.getData().isEmpty()) {
            // DATA_LIST 추출
			list = apiRes.getData().get(0).getDataList();
			
			// 날짜 포맷에 맞게 변환
            for (KpKxCloseDocPopupResDto resDto : list) {
            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd:HHmmss.SSSSSS");
                LocalDateTime dateTime = LocalDateTime.parse(resDto.getCreateTime(), formatter);
            	DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            	String formatted = dateTime.format(outputFormatter);
            	resDto.setCreateTime(formatted);
            }
		}
		
        return list;
    }

}



