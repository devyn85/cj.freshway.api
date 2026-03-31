package cjfw.wms.st.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.dto.StOutMoveMasterListReqDto;
import cjfw.wms.st.dto.StOutMoveMasterListResDto;
import cjfw.wms.st.dto.StOutMoveResultListReqDto;
import cjfw.wms.st.dto.StOutMoveResultListResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.07.29
 * @description : 외부비축센터간이동 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.29 ParkJinWoo 생성 
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StOutMoveService{
	private transient static final String SEERVICED_PREFIX = "stOutMoveService.";
	private transient static final String PAKAGE_NAME = "SPST_CONVERT";
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description : 외부비축센터간이동 헤더목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.29 ParkJinWoo 생성
	 */
	public List<StOutMoveMasterListResDto> getStOutMoveMasterList(StOutMoveMasterListReqDto stOutMasterListReqDto){
		return commonDao.selectList(SEERVICED_PREFIX + "getDataHeaderlist",stOutMasterListReqDto);
		
	}
	
	/**
	 * @description : 외부비축센터간이동 헤더목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.29 ParkJinWoo 생성
	 */
	public StOutMoveMasterListResDto getStockPrice(StOutMoveMasterListReqDto stOutMasterListReqDto){
		return commonDao.selectOne(SEERVICED_PREFIX + "getStockPrice",stOutMasterListReqDto);
		
	}
	
	/**
	 * @description : 외부비축센터간이동 저장결과 조회을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.29 ParkJinWoo 생성
	 */
	public List<StOutMoveResultListResDto> getStOutMoveResultList(StOutMoveResultListReqDto stOutMoveResultListReqDto){
		return commonDao.selectList(SEERVICED_PREFIX + "getDataResultList",stOutMoveResultListReqDto);
		
	}
	/**
	 * @description : 외부비축센터간이동 저장결과 조회을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.29 ParkJinWoo 생성
	 */
	public List<StOutMoveMasterListResDto> getExcelValChk(StOutMoveMasterListReqDto StOutMoveMasterListReqDto){
		List<StOutMoveMasterListResDto> result = new ArrayList<StOutMoveMasterListResDto>();
		if(StOutMoveMasterListReqDto != null) {
			result = commonDao.selectList(SEERVICED_PREFIX + "excelValChkOutMove",StOutMoveMasterListReqDto.getSaveList());
			if(result.size()>0) {
				for (StOutMoveMasterListResDto row : result) {

				    // ===== 0) base locals (원본값 캐싱) =====
				    final String dcCode      = StOutMoveMasterListReqDto.getDcCode();
				    final String storerKey   = row.getStorerkey();
				    final String organize    = row.getOrganize();
				    final String area        = row.getArea();
				    final String sku         = row.getSku();
				    final String loc         = row.getLoc();
				    final String lot         = row.getLot();
				    final String stockId     = row.getStockId();
				    final String stockType   = row.getStockType();
				    final String stockGrade  = row.getStockGrade();
				    final String uom         = row.getUom();
				    final BigDecimal qty     = nvl(row.getQty(), BigDecimal.ZERO);

				    // ===== 1) FROM_* 기본 세팅 (비었을 때만) =====
				    if (isBlank(row.getFromDccode()))      row.setFromDccode(dcCode);
				    if (isBlank(row.getFromStorerkey()))   row.setFromStorerkey(storerKey);
				    if (isBlank(row.getFromOrganize()))    row.setFromOrganize(organize);
				    if (isBlank(row.getFromArea()))        row.setFromArea(area);
				    if (isBlank(row.getFromSku()))         row.setFromSku(sku);
				    if (isBlank(row.getFromLoc()))         row.setFromLoc(loc);
				    if (isBlank(row.getFromLot()))         row.setFromLot(lot);
				    if (isBlank(row.getFromStockid()))     row.setFromStockid(stockId);
				    if (isBlank(row.getFromStocktype()))   row.setFromStocktype(stockType);
				    if (isBlank(row.getFromStockgrade()))  row.setFromStockgrade(stockGrade);
				    if (row.getFromOrderqty() == null)     row.setFromOrderqty(qty);
				    if (isBlank(row.getFromUom()))         row.setFromUom(uom);

				    // ===== 2) TO_* 기본 세팅 (사용자 입력이 우선 → 비었을 때만 복제) =====
				    if (isBlank(row.getToDccode()))        row.setToDccode(dcCode);
				    if (isBlank(row.getToStorerkey()))     row.setToStorerkey(storerKey);
				    if (isBlank(row.getToArea()))          row.setToArea(area);
				    if (isBlank(row.getToSku()))           row.setToSku(sku);
				    if (isBlank(row.getToLoc()))           row.setToLoc(loc);
				    if (isBlank(row.getToLot()))           row.setToLot(lot);
				    if (isBlank(row.getToStockid()))       row.setToStockid(stockId);
				    if (isBlank(row.getToStocktype()))     row.setToStocktype(stockType);
				    if (isBlank(row.getToStockgrade()))    row.setToStockgrade(stockGrade);
				    if (row.getToOrderqty() == null)       row.setToOrderqty(BigDecimal.ZERO); // 기본 0 (엑셀 값 있으면 안 덮음)
				    if (isBlank(row.getToUom()))           row.setToUom(uom);
				    // toOrganize / toOrganizeName / processmsg / tranDeliveryPrice / memo1 등은 엑셀 입력값 우선 → 여기선 미스만 보정

				    // ===== 3) 숫자형 null 안전 보정(필요시) =====
				    if (row.getQtyAllocated() == null)     row.setQtyAllocated(BigDecimal.ZERO);
				    if (row.getQtyPicked() == null)        row.setQtyPicked(BigDecimal.ZERO);
				    if (row.getEtcqty1() == null)          row.setEtcqty1(BigDecimal.ZERO);
				    if (row.getEtcqty2() == null)          row.setEtcqty2(BigDecimal.ZERO);
				    if (row.getStockPrice() == null)       row.setStockPrice(BigDecimal.ZERO);
				    if (row.getTranDeliveryPrice() == null)row.setTranDeliveryPrice(BigDecimal.ZERO);
				    row.setToOrganizeName(row.getToOrganizeName());
				    // ===== 4) 필수값 검증 → errCode/errMsg 세팅 (SQL에서 이미 했어도, 자바에서 2차 방어) =====
				    StringBuilder em = new StringBuilder();
				    if (isNullOrZero(row.getToOrderqty()))        em.append("이동수량(toOrderQty) 미입력 | ");
//				    if (isNullOrZero(row.getTranDeliveryPrice())) em.append("운송료(공급가)(tranDeliveryPrice) 미입력 | ");
				    if (isBlank(row.getProcessmsg()))             em.append("사유코드(processmsg) 미입력 | ");
				    if (isBlank(row.getToOrganize()))             em.append("창고(toOrganize) 미입력 | ");
				    if (isBlank(nvl(row.getMemo1(), row.getMemo01()))) em.append("사유내용(MEMO1) 미입력 | ");
				    if(row.getFromOrganize().equals(row.getToOrganize())) em.append("동일한 창고를 입력 할 수 없습니다.");

				    String errMsg = trimTail(em.toString(), " | ");
				    if (!errMsg.isEmpty()) {
				        row.setErrCode("Y");
				        // 기존 DB에서 온 에러 메시지가 있다면 이어붙임
				        row.setErrMsg(concatWithBar(nvl(row.getErrMsg(), ""), errMsg));
				    } else {
				        if (isBlank(row.getErrCode())) row.setErrCode("N");
				    }
				}
			}
		}
		return result;
		
	}
	
	
	/**
	 * @description : 외부비축센터간이동 저장기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.29 ParkJinWoo 생성
	 */
	public String saveDataList(StOutMoveMasterListReqDto paramDto){
		String resultCode = "";
		String resultMessage = "";
		 
		StOutMoveMasterListReqDto reqDto = ModelMapperUtil.map(paramDto, userContext, StOutMoveMasterListReqDto.class);
		
		reqDto.setAvc_COMMAND("CONFIRM");
		reqDto.setProcesstype("ST_ORGANIZEMOVE");
		reqDto.setTemptabletype("ST");
		reqDto.setConvertType("TR");
		reqDto.setAvc_DCCODE(reqDto.getFixDcCode());
//		reqDto.setDeliveryDate(paramDto.getSlipDt());

        
    	CmSyProcessTempStEntity tempStDeleteReqDto = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempStEntity.class);
    	tempStDeleteReqDto.setSpid(reqDto.getGSpid());
    	
    	commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempST", tempStDeleteReqDto);
    	int chunkSize = 200;
		String columnsDto ="FROM_DCCODE|FROM_STORERKEY|FROM_ORGANIZE|FROM_AREA|FROM_SKU|FROM_LOC|FROM_LOT|FROM_STOCKID|FROM_STOCKGRADE|FROM_STOCKTYPE|FROM_ORDERQTY|FROM_UOM|TO_DCCODE|TO_STORERKEY|TO_AREA|TO_SKU|TO_LOC|TO_LOT|TO_STOCKID|TO_STOCKGRADE|TO_STOCKTYPE|TO_ORDERQTY|TO_UOM|ETCQTY1|ETCQTY2|WORK_NO|TO_ORGANIZE|PROCESSMSG|MEMO1";
		String columnsEntity ="FROM_DCCODE|FROM_STORERKEY|FROM_ORGANIZE|FROM_AREA|FROM_SKU|FROM_LOC|FROM_LOT|FROM_STOCKID|FROM_STOCKGRADE|FROM_STOCKTYPE|FROM_ORDERQTY|FROM_UOM|TO_DCCODE|TO_STORERKEY|TO_AREA|TO_SKU|TO_LOC|TO_LOT|TO_STOCKID|TO_STOCKGRADE|TO_STOCKTYPE|TO_ORDERQTY|TO_UOM|ETCQTY1|ETCQTY2|WORK_NO|TO_ORGANIZE|PROCESSMSG|MEMO1";
		  
	
    	
    	List<CmSyProcessTempStEntity> insertList = new ArrayList<CmSyProcessTempStEntity>();
		List<StOutMoveMasterListResDto> saveList = paramDto.getSaveList();
		
		for (int i = 0; i < saveList.size(); i++) {
    	    StOutMoveMasterListResDto dto = saveList.get(i);
        	CmSyProcessTempStEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempStEntity.class);
//            ProcedureParametersFactory.initParamDto(reqDto,entity, PAKAGE_NAME);

			log.info("▶dto->{}",dto);
			/* DTO 필드(카멜) → Entity 필드(카멜) 1 : 1 순서 매핑 */
					
			entity = (CmSyProcessTempStEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
            entity.setProcesstype("ST_ORGANIZEMOVE");
            entity.setTemptabletype("ST");
            entity.setProcesscreator(dto.getGUserId());
            reqDto.setAvc_DCCODE(dto.getDcCode());
			insertList.add(entity);
			
    	    // 200개마다 혹은 마지막 루프일 때 insert
    	    if (saveList.size() == chunkSize || i == saveList.size() -1) {
    	        commonDao.insert(SERVICEID_TEMP_PREFIX + "insertSyProcessTempSt", insertList);
    	        insertList.clear(); // 다음 배치 준비
    	    }
    	}
		// 남은 데이터 insert
    	if (insertList.size() > 0) {
    		commonDao.insert(SERVICEID_TEMP_PREFIX + "insertSyProcessTempSt", insertList);
    	}		
    	
    	ProcedureParametersFactory.initParamDto(paramDto,reqDto, PAKAGE_NAME);
	    String[] keyList = {
	    		"PROCEDURE",
	    		"PROCESSTYPE",
	    		"PROCESSCREATOR",
	    		"SPID",
        	    "CONVERTTYPE",
        	    "DELIVERYDATE",
//        	    "AVC_COMMAND"
        	    
        	    
        	  
        	};
        Object[] valueList = {
        		PAKAGE_NAME,
        		reqDto.getProcesstype(),
        		reqDto.getGUserId(),
        		reqDto.getGSpid(),
        		reqDto.getConvertType(),
        		paramDto.getDeliveryDate(),
//        		"CONFIRM"
        		
        		};
        	reqDto.setAvc_DCCODE("2170");
//        	reqDto.setAvc_EXECUTEMODE("DEBUG");
        	reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		   commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, reqDto);
           resultCode    = (String)reqDto.getResultCode();
           resultMessage = (String)reqDto.getResultMessage();
           
           log.info("resultCode->{}",resultCode);
           log.info("resultMessage->{}",resultMessage);
           
           // 프로시저 Exception 처리(4/4)
           if(!"0".equals(resultCode)){
               log.error("▶외부비축소비기한변경 저장 -> 저장 오류 발생 ");
               throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
           
           }
//		패키지 콜 기존과 똑같이
//		return commonDao.selectList(SEERVICED_PREFIX + "getDataResultList",paramDto);
		 return CanalFrameConstants.MSG_COM_SUC_CODE;   
	}
	private static boolean isBlank(String s) {
	    return s == null || s.trim().isEmpty();
	}
	private static <T> T nvl(T v, T defVal) {
	    return v == null ? defVal : v;
	}
	private static boolean isNullOrZero(BigDecimal v) {
	    return v == null || v.compareTo(BigDecimal.ZERO) == 0;
	}
	private static String nvl(String s, String def) {
	    return s == null ? def : s;
	}
	private static String trimTail(String s, String tail) {
	    if (s == null || s.isEmpty()) return "";
	    String r = s;
	    while (r.endsWith(tail)) r = r.substring(0, r.length() - tail.length());
	    return r;
	}
	private static String concatWithBar(String a, String b) {
	    if (isBlank(a)) return b;
	    if (isBlank(b)) return a;
	    return a + " | " + b;
	}
} 