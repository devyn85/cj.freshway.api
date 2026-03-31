package cjfw.wms.wd.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.dto.CmLabelPriorityReqDto;
import cjfw.wms.cm.service.CmLabelPriorityService;
import cjfw.wms.common.util.MaskingUtil;
import cjfw.wms.wd.dto.WdKxDeliveryAPI01Dto;
import cjfw.wms.wd.dto.WdKxDeliveryAPI02Dto;
import cjfw.wms.wd.dto.WdKxDeliveryAPI03Dto;
import cjfw.wms.wd.dto.WdKxDeliveryAPI04Dto;
import cjfw.wms.wd.dto.WdKxDeliveryInvoiceBoxResDto;
import cjfw.wms.wd.dto.WdKxDeliveryInvoicePrintResDto;
import cjfw.wms.wd.dto.WdKxDeliveryInvoiceReqDto;
import cjfw.wms.wd.dto.WdKxDeliveryInvoiceResDto;
import cjfw.wms.wd.entity.WdKxDeliveryInvoiceBoxEntity;
import cjfw.wms.wd.entity.WdKxDeliveryInvoiceEmpEntity;
import cjfw.wms.wd.entity.WdKxDeliveryInvoiceEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss
 * @date : 2025.12.26
 * @description : 택배송장발행(온라인) Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.26 sss 생성 </pre>
 */
/**
 * 
 * N배송분리 ->saveMasterNDeliveryDivide
 * 
 * 센터접수 ->saveMasterReceipt
 * 
 * 주문내역저장 ->saveMasterList01
 * 
 * 운송장번호채번 ->issueInvoiceNo
 * 
 * 운송장출력 ->printMasterList
 * 
 * 배송 저장 saveMasterList02
 * 
 * 택배예약 ->regBook
 * 
 * 주소정제 ->reqAddrRfnSm
 * 
 * 운송자채번 ->reqInvcNo
 * 
 *  송장분리 ->saveMasterInvoiceDivide
 * 
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdKxDeliveryInvoiceService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * 
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdKxDeliveryInvoiceService.";

	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;
	/** 공통.정열.service */
	private final CmLabelPriorityService cmLabelPriorityService;

	/**
	 * @description : 택배송장발행(온라인) - 주문 목록 조회
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */
	public List<WdKxDeliveryInvoiceResDto> getMasterList(WdKxDeliveryInvoiceReqDto reqDto) {
		/********************************************************************
		 * 1. 정열순서 절 사용 시
		 ********************************************************************/
		log.info("▶출력명칭:{}", reqDto.getPrtNm());
		if (!"".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getPrtNm()))) { // 조회조건 출력순서가 있을 경우
			// 화면별 정열순서 조회
			CmLabelPriorityReqDto cmReqDto = new CmLabelPriorityReqDto();
			cmReqDto.setFixdccode(reqDto.getFixdccode()); // 센터코드
			cmReqDto.setUsePgm(reqDto.getUsePgm()); // 메뉴코드
			cmReqDto.setPrtNm(reqDto.getPrtNm()); // 출력명칭
			cmLabelPriorityService.setSortPrdOrd(cmReqDto, reqDto); // 정열컬럼 세팅
		}

		reqDto.setStockYn("Y"); // 재고조회여부
		List<WdKxDeliveryInvoiceResDto> resultList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
		if (resultList.size() > 0) {
			for (WdKxDeliveryInvoiceResDto resultDto : resultList) {
				resultDto.setSendrTelNo(getPhoneFormat(resultDto.getSendrTelNo()));
				resultDto.setSendrCellNo(getPhoneFormat(resultDto.getSendrCellNo()));
				resultDto.setSendrSafeNo(getPhoneFormat(resultDto.getSendrSafeNo()));
				resultDto.setRcvrTelNo(getPhoneFormat(resultDto.getRcvrTelNo()));
				resultDto.setRcvrTelNo2(getPhoneFormat(resultDto.getRcvrTelNo2()));
				resultDto.setRcvrCellNo(getPhoneFormat(resultDto.getRcvrCellNo()));
				resultDto.setRcvrCellNo2(getPhoneFormat(resultDto.getRcvrCellNo2()));
				resultDto.setRcvrSafeNo(getPhoneFormat(resultDto.getRcvrSafeNo()));
				resultDto.setOrdrTelNo(getPhoneFormat(resultDto.getOrdrTelNo()));
				resultDto.setOrdrCellNo(getPhoneFormat(resultDto.getOrdrCellNo()));
				resultDto.setOrdrSafeNo(getPhoneFormat(resultDto.getOrdrSafeNo()));
			}
		}

//    	/********************************************************************
//    	 * 2.  쿼리에 변수를 넣지 않고 JAVA로 정열 시
//    	 ********************************************************************/    
//		List<T> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", reqDto);
//		/*
//		 프로그램 정렬 기준에 의애 재정열
//		   ->조회조건에 넘어온 출력명칭의 정열 처리(1/2) 
//		 */
//		CmLabelPriorityReqDto cmReqDto = new CmLabelPriorityReqDto();
//		cmReqDto.setFixdccode(reqDto.getFixdccode()); // 1.센터코드
//		cmReqDto.setUsePgm(reqDto.getUsePgm());       // 2.메뉴코드
//		cmReqDto.setPrtNm(reqDto.getPrtNm());         // 3.출력명칭
//		return cmLabelPriorityService.setListSort(cmReqDto, list); // 재정열		

		return resultList;
	}

	/**
	 * @description : 택배송장발행(온라인) - 전화번호 format 적용
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.30 sss  생성
	 *         </pre>
	 */
	private String getPhoneFormat(String orgNo) {
		String formatNo = "";
		if (StringUtils.isEmpty(orgNo)) {
			return formatNo;
		}

		if (orgNo.length() == 8) {
			formatNo = orgNo.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
		} else if (orgNo.length() == 12) {
			formatNo = orgNo.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
		} else {
			formatNo = orgNo.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
		}

		return formatNo;
	}

	/**
	 * @description : 택배송장발행(온라인) - 주문내역 저장
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */
	public WdKxDeliveryInvoiceReqDto saveMasterList01(WdKxDeliveryInvoiceReqDto reqDto) {
		WdKxDeliveryInvoiceReqDto resultDto = new WdKxDeliveryInvoiceReqDto();
		List<WdKxDeliveryInvoiceResDto> saveList = reqDto.getSaveList(); // 저장리스트
		int iCnt = 0; // 등록 건수
		int uCnt = 0; // 수정 건수
		int dCnt = 0; // 삭제 건수
		int rv = 0; // 반환 값
		
		List<WdKxDeliveryInvoiceEntity> bulkList = new ArrayList<>();
		int chunkSize = 100; // 청크 사이즈 설정
		
		for (int i = 0; i < saveList.size(); i++) {
			WdKxDeliveryInvoiceResDto dto = saveList.get(i);
			
			// START.Master.체크.가능여부
			List<Map<String, Object>> chkListMap = commonDao.selectList(SERVICEID_PREFIX + "selectChkIntMaster01", dto);
			String chk1 = (String) chkListMap.get(0).get("CHK1"); // 체크.IF존재여부
			String chk2 = (String) chkListMap.get(0).get("CHK2"); // 체크.접수여부
			String statusnm = (String) chkListMap.get(0).get("STATUSNM"); // 상태명
			log.info("chk1(체크.IF존재여부):{}", chk1);
			log.info("chk2(체크.접수여부):{}", chk2);
			log.info("statusnm):{}", statusnm);
			String moreMsg = "\n주문번호 : [" + dto.getEmpCustDocno() + "]";
			moreMsg += "\n상품코드 : [" + dto.getSku() + "]";
			moreMsg += "\n상품명  : [" + dto.getDescription() + "]";

			if ("Y".equals(chk1)) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_059", new String[] { "[기 접수확정 완료]" }) + moreMsg); // 해당 정보가 없어 처리할 수 없습니다.-{0}
			if ("Y".equals(chk2)) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_059", new String[] { "현재상태 : [" + statusnm + "]" })+ moreMsg); // 처리할 수 없습니다 {0}.
			// END.Master.체크.가능여부

			WdKxDeliveryInvoiceEntity entity = ModelMapperUtil.map(dto, userContext, WdKxDeliveryInvoiceEntity.class);
			log.info("▶dto->{}", MaskingUtil.maskLog(dto));

			// START.필수입력 check - 그리드 변수 등
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocnoFw()))) {
				throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] { "docnoFw" }));
			}
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDoclineFw()))) {
				throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] { "doclineFw" }));
			}
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getRcptHourType()))) {
				throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] { "rcptHourType" }));
			}
			// END.필수입력 check
			
			bulkList.add(entity);
			
			// 50개마다 혹은 마지막 루프일 때 insert(3/3) - merge로 성능	개선
			if (bulkList.size() == chunkSize || i == saveList.size() - 1) {
				rv =  commonDao.update(SERVICEID_PREFIX + "updateMasterList01", bulkList);
				if (rv < 1) {
					throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
				}
				uCnt += rv;
				
				bulkList.clear();
			}			
			
		}

		if ((iCnt + uCnt + dCnt) < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		}
		return resultDto;
	}

	/**
	 * @description : 택배송장발행(온라인) - N배송 분리 처리
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */
	public WdKxDeliveryInvoiceReqDto updateMasterNDeliveryDivideAll(WdKxDeliveryInvoiceReqDto reqDto) {
		WdKxDeliveryInvoiceReqDto resultDto = new WdKxDeliveryInvoiceReqDto();
		int uCnt = 0; // 수정 건수
		int processCnt = 0; // 처리 건수

		WdKxDeliveryInvoiceEmpEntity entity = ModelMapperUtil.map(reqDto, userContext,WdKxDeliveryInvoiceEmpEntity.class);

		// START.필수입력 check
		if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getReqDate()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] { "reqDate" })); // 해당 정보가 없어 처리할 수 없습니다.-{0}
		// END.필수입력 check
		
		// START.Master.체크.N배송 분리
		List<Map<String, Object>> chkListMap = commonDao.selectList(SERVICEID_PREFIX + "checkMasterNDeliveryDivideAll", reqDto);
		String chk1         = (String) chkListMap.get(0).get("CHK1"); // 체크.택배접수여부
		String chk2         = (String) chkListMap.get(0).get("CHK2"); // 체크.IF존재여부
		String empCustDocno = (String) chkListMap.get(0).get("EMP_CUST_DOCNO"); // 체크.택배접수여부
		log.debug("chk1(체크.시간대 존재여부):->{}", chk1);
		log.debug("chk2(체크.IF존재여부)   :->{}", chk2);
		log.debug("empCustDocno         :->{}", empCustDocno);
		String moreMsg  = "\n\n일자 : [" + reqDto.getReqDate() + "]";
		       moreMsg += "\n판매사이트주문번호  : [" + empCustDocno + "]";
		
		if ("Y".equals(chk1)) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] {"[접수 시간대]"})+moreMsg); // 해당 정보가 없어 처리할 수 없습니다. - {0}
		if ("Y".equals(chk2)) throw new UserHandleException("센터접수 내역이 존재합니다 취소처리 후 수행해 주세요." + moreMsg); // 해당 정보가 없어 처리할 수 없습니다.-{0}
		// END.Master.체크.N배송 분리		

		// N배송
		uCnt += commonDao.update(SERVICEID_PREFIX + "updateMasterNDeliveryDivideAll01", reqDto);

		// 배송분리 UPDATE
		uCnt += commonDao.update(SERVICEID_PREFIX + "updateMasterNDeliveryDivideAll02", reqDto);

		// 운송장대체번호 UPDATE
		processCnt = commonDao.update(SERVICEID_PREFIX + "updateMasterNDeliveryDivideAll03", reqDto);

		// 반품배송
		uCnt += commonDao.update(SERVICEID_PREFIX + "updateMasterNDeliveryDivideRefund", reqDto);

		resultDto.setProcessCnt(processCnt);
		return resultDto;
	}

	/**
	 * @description : 택배송장발행(온라인) - N배송 분리 처리
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */
	public WdKxDeliveryInvoiceReqDto saveMasterNDeliveryDivide(WdKxDeliveryInvoiceReqDto reqDto) {
		return updateMasterNDeliveryDivideAll(reqDto);
	}

	/**
	 * @description : 택배송장발행(온라인) - N배송 분리 처리 - 체크
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */
	public WdKxDeliveryInvoiceReqDto saveMasterNDeliveryYnChecked(WdKxDeliveryInvoiceReqDto reqDto) {
		WdKxDeliveryInvoiceReqDto resultDto = new WdKxDeliveryInvoiceReqDto();
		List<WdKxDeliveryInvoiceResDto> saveList = reqDto.getSaveList(); // 저장리스트
		int uCnt = 0; // 수정 건수

		int chunkSize = 50;
		List<WdKxDeliveryInvoiceEmpEntity> bulkList = new ArrayList<>();

		for (int i = 0; i < saveList.size(); i++) {
			WdKxDeliveryInvoiceResDto dto = saveList.get(i);
			WdKxDeliveryInvoiceEmpEntity entity = ModelMapperUtil.map(dto, userContext,
					WdKxDeliveryInvoiceEmpEntity.class);
			log.info("▶dto->{}", MaskingUtil.maskLog(dto));

			// START.필수입력 check - 그리드 변수 등
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getEmpCustkey()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] { "empCustkey" }));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSku()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] { "storerkey" }));
			// END.필수입력 check
			bulkList.add(entity);

			// 50개마다 혹은 마지막 루프일 때 insert(3/3)
			if (bulkList.size() == chunkSize || i == saveList.size() - 1) {
				commonDao.update(SERVICEID_PREFIX + "updateMasterNDeliveryYn", bulkList);
				uCnt++;
				bulkList.clear();
			}
		}

		if (uCnt < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		}

		return resultDto;
	}

	/**
	 * @description : 택배송장발행(온라인) - 접수 처리
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */
	public WdKxDeliveryInvoiceReqDto saveMasterReceipt(WdKxDeliveryInvoiceReqDto paramDto) {
		WdKxDeliveryInvoiceReqDto resultDto = new WdKxDeliveryInvoiceReqDto();
		// List<WdKxDeliveryInvoiceResDto> saveList = reqDto.getSaveList(); // 저장리스트
		int lineNo = 1;
		int uCnt = 0; // 수정 건수
		int iCnt = 0; // 등록 건수
		int dCnt = 0; // 삭제 건수
		int rv = 0; // 반환 값
		int processCnt = 0; // 처리 건수
		int totalCnt = 0; // 총 건수

		// 파라미터 위변조 적용(paramDto->reqDto)
		WdKxDeliveryInvoiceReqDto reqDto = ModelMapperUtil.map(paramDto, WdKxDeliveryInvoiceReqDto.class);
		
		// START.Master.체크.가능여부
		String moreMsg = "";
		//List<Map<String, Object>> chkListMap = commonDao.selectList(SERVICEID_PREFIX + "selectChkIntMasterReceipt01",reqDto);
		//String chkMst1 = (String) chkListMap.get(0).get("CHK1"); // 체크.IF존재여부
		//String empCustDocno1 = (String) chkListMap.get(0).get("EMP_CUST_DOCNO"); // 체크.사이트주문번호
		//log.info("chk2(체크.IF존재여부) :->{}", chkMst1);
		//moreMsg = "\n사이트주문번호 : [" + empCustDocno1 + "]";
		//if ("Y".equals(chkMst1)) throw new UserHandleException("센터접수 내역이 존재합니다 취소처리 후 수행해 주세요." + moreMsg); // 해당 정보가 없어 처리할 수 없습니다.-{0}
		// END.Master.체크.가능여부		

		// 성능향상을 위해 최소한의 KEY정보로 재조회
		reqDto.setNoMasking(true); // 마스킹 해제)
		List<WdKxDeliveryInvoiceResDto> saveList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
		
		// 성능개선.50건씩 분할하여 insert
		int chunkSize = 20;
		List<WdKxDeliveryInvoiceEmpEntity> receptList = new ArrayList<>();
		List<WdKxDeliveryInvoiceEmpEntity> bulkList = new ArrayList<>();
		
		for (int i = 0; i < saveList.size(); i++) {
			WdKxDeliveryInvoiceResDto dto = saveList.get(i);
			// START.Master.체크.가능여부
			//List<Map<String, Object>> chkListMap = commonDao.selectList(SERVICEID_PREFIX + "selectChkIntMasterReceipt",dto);
			//String chk1 = (String) chkListMap.get(0).get("CHK1"); // 체크.접수여부
			//String chk2 = (String) chkListMap.get(0).get("CHK3"); // 체크.제외사유여부
			//String empCustDocno = (String) chkListMap.get(0).get("EMP_CUST_DOCNO"); // 체크.사이트주문번호
			//log.info("chk1(체크.접수여부)   :->{}", chk1);
			//log.info("chk2(체크.제외사유여부) :->{}", chk2);
			//moreMsg = "\n주문번호 : [" + dto.getEmpCustDocno() + "]";
			//moreMsg += "\n상품코드 : [" + dto.getSku() + "]";
			//moreMsg += "\n상품명  : [" + dto.getDescription() + "]";
			//moreMsg += "\n사이트주문번호  : [" + empCustDocno + "]";

			//if ("Y".equals(chk1)) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_059", new String[] { "[기 접수처리 완료]" }) + moreMsg); // 해당 정보가 없어 처리할 수 없습니다.-{0}
			//if ("Y".equals(chk2)) throw new UserHandleException("제외사유" + moreMsg); 
			// END.Master.체크.가능여부

			// 저장 처리
			WdKxDeliveryInvoiceEmpEntity entity = ModelMapperUtil.map(dto, userContext,WdKxDeliveryInvoiceEmpEntity.class);

			// 접수 상태 처리
			entity.setPackagekey("N/A");
			entity.setPackagekeyLine(String.valueOf(lineNo));
		
			
			// 택배 데이터 등록
			// START.필수입력 check - 그리드 변수 등
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getEmpCustkey())))   throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] { "empCustkey" })); // 해당 정보가 없어 처리할 수 없습니다.-{0}
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getRcptHourType()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] { "rcptHourType" }));
			if (entity.getVolume() == 0) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] { "volume" }));
			// END.필수입력 check
			
			/*********************************************************
			 * 1.주소 정제 처리
			 ********************************************************/
			WdKxDeliveryAPI03Dto apiDto = new WdKxDeliveryAPI03Dto();
			apiDto.setClntnum(dto.getKxCustkey()); // 1.고객번호
			apiDto.setAddress(dto.getRcvrAddr()); // 5.주소
			//
			apiDto.setSerialkey(dto.getSerialkey()); // 일련번호
			WdKxDeliveryAPI03Dto addresDto = reqAddrRfnSm(apiDto); // 주소정제
			log.info("▶▶▶ addresDto.getResultCd()        : {}", addresDto.getResultCd());

			entity.setClsfcd(addresDto.getClsfcd()); // 도착지코드
			entity.setSubclsfcd(addresDto.getSubclsfcd()); // 세부도착지코드;
			entity.setClsfaddr(addresDto.getClsfaddr()); // 주소약칭
			entity.setClldlvbrannm(addresDto.getClldlvbrannm()); // 배송지점명
			entity.setClldlvempnm(addresDto.getClldlvempnm()); // 배송SM명
			entity.setClldlvempnicknm(addresDto.getClldlvempnicknm()); // SM분류코드
			entity.setRspsdiv(addresDto.getRspsdiv()); // 권역 구분
			entity.setP2pcd(addresDto.getP2pcd()); // P2P코드

			if ("S".equals(addresDto.getResultCd())) {
				processCnt++;
				receptList.add(entity);
			}

			totalCnt++;
		}
		
		// 저장 처리 - 50개씩 분할하여 insert
		for (int i = 0; i < receptList.size(); i++) {
			// 100개마다 혹은 마지막 루프일 때 insert(3/3)
		    if (bulkList.size() == chunkSize || i == receptList.size() - 1) {
		    	iCnt += commonDao.insert(SERVICEID_PREFIX + "insertMasterReceipt", receptList);  // 택배 데이터 등록
		        bulkList.clear();
		    }			
		}
	    
		if(iCnt != receptList.size()) {
			throw new UserHandleException("적용된 건수가 상이합니다. 등록 건수 : " + iCnt + ", 대상 건수 : " + saveList.size());
		}	
		
		resultDto.setTotalCnt(totalCnt);
		resultDto.setProcessCnt(processCnt);
		return resultDto;
	}

	//
	/**
	 * @description : 택배송장발행(온라인) - 운송장번호 채번
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */
	public String issueInvoiceNo(WdKxDeliveryInvoiceResDto dto) {
		int uCnt = 0; // 수정 건수
		WdKxDeliveryInvoiceResDto rowDto = (WdKxDeliveryInvoiceResDto) commonDao
				.selectList(SERVICEID_PREFIX + "getMasterKey", dto).get(0);

		String invoiceNo = rowDto.getInvoiceno();
		if (StringUtils.isEmpty(invoiceNo)) {
			throw new UserHandleException("운송장번호를 발급받을 수 없습니다.");
		}
		dto.setInvoiceno(invoiceNo.replace("-", ""));
		// 운송장번호 사용처리
		WdKxDeliveryInvoiceEmpEntity entity = ModelMapperUtil.map(rowDto, userContext,
				WdKxDeliveryInvoiceEmpEntity.class);
		uCnt = commonDao.update(SERVICEID_PREFIX + "updateDelYnInvoiceNo", entity);
		if (uCnt < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		}

		log.info("▶운송장번호 발급 완료 : {}", invoiceNo);

		return invoiceNo;
	}

	/**
	 * @description : 택배송장발행(온라인) - N배송 목록 조회
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */
	public <R, T> List<R> getMasterList2(WdKxDeliveryInvoiceReqDto reqDto) {
		reqDto.setStockYn("Y"); // 재고조회여부
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", reqDto);
	}

	/**
	 * @description : 택배송장발행(온라인) - 송장분리 처리
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */

	/*
	 * 
	 * 가. 고객 요구사항 요약 (한 문장씩) →온라인 주문을 택배사 인계용 데이터로 변환 →상품은 온도대별로 분리 →냉동(30) / 냉장(20)
	 * / 상온(10) →부피 큰 상품부터 포장 →온도별 박스표에서 가장 적합한 박스에 우선 포장 →이미 열린 박스 중 가장 적합한 박스에 우선
	 * 포장 →박스는 냉동 → 냉장 → 상온 순으로 번호 부여 →동일 수령자는 묶어서 처리 가능 →이미 완료된 주문은 SKIP →상품이 여러
	 * 박스로 나뉘면 주문 항목을 여러 건 생성 →박스가 꽉 차면 새 박스를 열고 번호 증가
	 * 
	 * 📌 포장 가능 규칙 --------------------------------------------- 상품 냉동박스(30)
	 * 냉장박스(20) 상온박스(10) --------------------------------------------- 냉동(30) ✅ ❌ ❌
	 * 냉장(20) ✅ ✅ ❌ 상온(10) ❌ ❌ ✅
	 * 
	 * 나. 핵심 개념 정리 (이걸 이해하면 끝)
	 * 
	 * 📦 박스(Box) 온도대별로 관리 용량(capacity)과 사용량(used)을 가짐 이미 열린 박스는 재사용 가능 📦 상품(Item)
	 * 온도대 부피(volume) 수량(qty) 📦 포장 원칙 열린 박스 → 가능하면 거기 안 되면 → 새 박스 한 박스에 다 안 들어가면 →
	 * 나눔
	 * 
	 * 
	 * 
	 * 
	 */

//////////////////////////////////////////////////sssssssssss

	public WdKxDeliveryInvoiceReqDto saveMasterInvoiceDivide(WdKxDeliveryInvoiceReqDto paramDto) {

		WdKxDeliveryInvoiceReqDto resultDto = new WdKxDeliveryInvoiceReqDto();
		Map<String, List<WdKxDeliveryInvoiceResDto>> groupMap = new HashMap<>();

		WdKxDeliveryInvoiceReqDto reqDto = ModelMapperUtil.map(paramDto, WdKxDeliveryInvoiceReqDto.class);
		reqDto.setNoMasking(true);

		// 박스 정보 조회
		boxInfoList = commonDao.selectList(SERVICEID_PREFIX + "getBoxInfoList", reqDto);
		if (boxInfoList.isEmpty()) {
			throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] {"[박스 정보]"})); // 해당 정보가 없어 처리할 수 없습니다. - {0}
		}

		// ⭐ 중요: 큰 박스부터 정렬
		boxInfoList.sort((a, b) -> b.getCapacity() - a.getCapacity());

		// 송장 대상
		List<WdKxDeliveryInvoiceResDto> saveList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", reqDto);
		log.info("saveList->{}", saveList.size());
		
		if (saveList.isEmpty()) {
			throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] {"[접수내역]"})); // 해당 정보가 없어 처리할 수 없습니다. - {0}
		}

		// 수령자 그룹핑
		for (WdKxDeliveryInvoiceResDto dto : saveList) {
			String key = dto.getRcvrNm() + dto.getRcvrAddr() + dto.getRcvrTelNo();
			groupMap.computeIfAbsent(key, k -> new ArrayList<>()).add(dto);
		}

		int groupIndex = 1;

		for (String key : groupMap.keySet()) {
			int myReceiveBoxCnt = 0;
			// ✅ 그룹별 박스 리스트 (전역 제거)
			List<WdKxDeliveryInvoiceResDto> openedBoxes = new ArrayList<>();

			processReceiverGroup(resultDto, groupMap.get(key), reqDto.getSerialkey(), reqDto.getSerialkeyEmp(),
					groupIndex, myReceiveBoxCnt, openedBoxes); // ⭐ 전달
			groupIndex++;
		}

		return resultDto;
	}

	/**
	 * @description : 택배송장발행(온라인) - 수령자 그룹별 포장 처리
	 * @param resultDto
	 * @param groupList
	 * @param serialkey
	 * @param serialkeyEmp
	 * @param groupIndex
	 * @param myReceiveBoxCnt
	 */
	private void processReceiverGroup(WdKxDeliveryInvoiceReqDto resultDto, List<WdKxDeliveryInvoiceResDto> groupList,
			String serialkey, BigDecimal serialkeyEmp, int groupIndex, int myReceiveBoxCnt,
			List<WdKxDeliveryInvoiceResDto> openedBoxes) {

		String[] storageOrder = { "30", "20", "10" };

		for (String storageType : storageOrder) {

			List<WdKxDeliveryInvoiceResDto> items = new ArrayList<>();

			for (WdKxDeliveryInvoiceResDto dto : groupList) {
				if (storageType.equals(dto.getStoragetype())) {
					items.add(dto);
				}
			}

			sortByVolumeDesc(items);

			packItems(resultDto, items, storageType, groupIndex, myReceiveBoxCnt, openedBoxes);
		}
	}

	// 부피 정렬
	private void sortByVolumeDesc(List<WdKxDeliveryInvoiceResDto> list) {
		list.sort((a, b) -> b.getVolume() - a.getVolume());
	}

	private WdKxDeliveryInvoiceResDto createBox(WdKxDeliveryInvoiceBoxResDto info, String storageType) {
		WdKxDeliveryInvoiceResDto box = new WdKxDeliveryInvoiceResDto();

		box.setBoxno(info.getBoxno());
		box.setStoragetype(storageType);
		box.setBoxtype(info.getBoxno());
		box.setCapacity(info.getCapacity());
		box.setUsed(0);

		return box;
	}

	// 포장 처리
	private void packItems(WdKxDeliveryInvoiceReqDto resultDto, List<WdKxDeliveryInvoiceResDto> storageItemsList,
			String storageType, int groupIndex, int myReceiveBoxCnt, List<WdKxDeliveryInvoiceResDto> openedBoxes) {

		int lineNo = 1;

		if (storageItemsList.isEmpty())
			return;

		// ✅ 1️⃣ 전체 체적 계산
		int totalVolume = 0;
		for (WdKxDeliveryInvoiceResDto item : storageItemsList) {
			totalVolume += item.getVolume() * item.getOrderqty().intValue();
		}

		// ✅ 2️⃣ 최적 박스 조합 생성 (여기서 끝)
		List<WdKxDeliveryInvoiceResDto> optimalBoxes = findOptimalBoxes(totalVolume, storageType);

		for (WdKxDeliveryInvoiceResDto box : optimalBoxes) {
			openedBoxes.add(box);
			myReceiveBoxCnt++;
		}

		// ✅ 3️⃣ 아이템 채우기 (박스 추가 생성 ❌)
		for (WdKxDeliveryInvoiceResDto item : storageItemsList) {

			String serialkey1 = item.getSerialkey();
			String serialkeyEmp1 = item.getSerialkeyEmp().toString();

			int remainQty = item.getOrderqty().intValue();

			while (remainQty > 0) {

				WdKxDeliveryInvoiceResDto box = null;

				// 1️⃣ 무조건 현재 열린 박스 먼저 사용
				for (WdKxDeliveryInvoiceResDto b : openedBoxes) {
				    if (canPutInBox(item.getStoragetype(), b.getStoragetype())) {
				        int remain = b.getCapacity() - b.getUsed();
				        if (remain >= item.getVolume()) {
				            box = b;
				            break;
				        }
				    }
				}

				// 2️⃣ 없으면 그때 새 박스
				if (box == null) {
				    box = openBestBox(item.getVolume(), remainQty, storageType);
				    openedBoxes.add(box);
				    myReceiveBoxCnt++;
				}

				// ❗ 절대 여기서 박스 생성하면 안됨
				//if (box == null) {
				//	throw new RuntimeException("박스 부족 - 로직 오류");
				//}
				
				// ✅ 수정 (fallback)
				if (box == null) {
				    box = findAnyAvailableBox(item.getStoragetype(), item.getVolume());

				    if (box == null) {
				        throw new RuntimeException("진짜 박스 부족");
				    }
				}				

				int remainCapacity = box.getCapacity() - box.getUsed();
				int canPackQty = remainCapacity / item.getVolume();

				if (canPackQty <= 0) {
					box.setUsed(box.getCapacity());
					continue;
				}

				int packQty = Math.min(canPackQty, remainQty);

				box.setUsed(box.getUsed() + (packQty * item.getVolume()));
				remainQty -= packQty;

				saveItemToBox(item, box, packQty, lineNo, serialkey1, groupIndex, getBoxSeq(openedBoxes, box));

				lineNo++;
				resultDto.setProcessCnt(resultDto.getProcessCnt() + 1);
			}

			// 기존 로직 유지
			WdKxDeliveryInvoiceEmpEntity entity = ModelMapperUtil.map(item, userContext,
					WdKxDeliveryInvoiceEmpEntity.class);

			entity.setSerialkey(new BigDecimal(serialkey1));
			commonDao.insert(SERVICEID_PREFIX + "deleteMasterInvoceDivide", entity);

			entity.setSerialkeyEmp(new BigDecimal(serialkeyEmp1));
			int uCnt = commonDao.insert(SERVICEID_PREFIX + "updateDeliveryStatusDivide", entity);

			if (uCnt < 1) {
				throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075"));
			}
		}
	}
	
	private WdKxDeliveryInvoiceResDto findAnyAvailableBox(String itemSt, int volume) {

	    for (WdKxDeliveryInvoiceResDto box : openedBoxes) {

	        if (!canPutInBox(itemSt, box.getStoragetype())) continue;

	        int remain = box.getCapacity() - box.getUsed();

	        if (remain >= volume) {
	            return box; // 그냥 넣어
	        }
	    }

	    return null;
	}	

	private void addBoxes(List<WdKxDeliveryInvoiceResDto> list, WdKxDeliveryInvoiceBoxResDto info, int count,
			String storageType) {

		for (int i = 0; i < count; i++) {
			WdKxDeliveryInvoiceResDto box = new WdKxDeliveryInvoiceResDto();
			box.setBoxno(info.getBoxno());
			box.setBoxtype(info.getBoxno());
			box.setStoragetype(storageType);
			box.setCapacity(info.getCapacity());
			box.setUsed(0);
			list.add(box);
		}
	}
	
	private int getBoxSeq(List<WdKxDeliveryInvoiceResDto> boxes, WdKxDeliveryInvoiceResDto target) {

		for (int i = 0; i < boxes.size(); i++) {
			if (boxes.get(i) == target) {
				return i + 1;
			}
		}
		return 1;
	}	

	private List<WdKxDeliveryInvoiceResDto> findOptimalBoxes(int totalVolume, String storageType) {

		List<WdKxDeliveryInvoiceBoxResDto> candidates = new ArrayList<>();

		for (WdKxDeliveryInvoiceBoxResDto b : boxInfoList) {
			if (b.getStoragetype().equals(storageType)) {
				candidates.add(b);
			}
		}

		List<WdKxDeliveryInvoiceResDto> best = null;
		int minBoxCnt = Integer.MAX_VALUE;
		int minRemain = Integer.MAX_VALUE;

		// ✅ 완전탐색 (3종류 기준이면 충분히 빠름)
		for (int i = 0; i <= 10; i++) {
			for (int j = 0; j <= 10; j++) {
				for (int k = 0; k <= 10; k++) {

					int boxCnt = i + j + k;
					if (boxCnt == 0)
						continue;

					int capacity = i * candidates.get(0).getCapacity() + j * candidates.get(1).getCapacity()
							+ k * candidates.get(2).getCapacity();

					if (capacity < totalVolume)
						continue;

					int remain = capacity - totalVolume;

					if (boxCnt < minBoxCnt || (boxCnt == minBoxCnt && remain < minRemain)) {

						minBoxCnt = boxCnt;
						minRemain = remain;

						best = new ArrayList<>();

						addBoxes(best, candidates.get(0), i, storageType);
						addBoxes(best, candidates.get(1), j, storageType);
						addBoxes(best, candidates.get(2), k, storageType);
					}
				}
			}
		}

		if (best == null) {
			throw new RuntimeException("박스 조합 없음");
		}

		return best;
	}

	private WdKxDeliveryInvoiceResDto openBestBoxByTotalVolume(int totalVolume, String storageType) {

		WdKxDeliveryInvoiceBoxResDto best = null;
		int minRemain = Integer.MAX_VALUE;

		for (WdKxDeliveryInvoiceBoxResDto boxInfo : boxInfoList) {

			if (!boxInfo.getStoragetype().equals(storageType))
				continue;

			int capacity = boxInfo.getCapacity();

			if (capacity >= totalVolume) {
				int remain = capacity - totalVolume;

				if (remain < minRemain) {
					minRemain = remain;
					best = boxInfo;
				}
			}
		}

		if (best == null) {
			throw new UserHandleException("박스 부족 storageType=" + storageType);
		}

		WdKxDeliveryInvoiceResDto box = new WdKxDeliveryInvoiceResDto();

		box.setBoxno(best.getBoxno());
		box.setStoragetype(storageType);
		box.setBoxtype(best.getBoxno());
		box.setCapacity(best.getCapacity());
		box.setUsed(0);

		return box;
	}

	private WdKxDeliveryInvoiceResDto openBestBox(int itemVolume, int remainQty, String storageType) {

		WdKxDeliveryInvoiceBoxResDto bestBoxInfo = null;

		int minBoxCount = Integer.MAX_VALUE;
		int minRemainSpace = Integer.MAX_VALUE; // 🔥 추가

		for (WdKxDeliveryInvoiceBoxResDto boxInfo : boxInfoList) {

			if (!boxInfo.getStoragetype().equals(storageType))
				continue;

			int capacity = boxInfo.getCapacity();

			int perBoxQty = capacity / itemVolume;
			if (perBoxQty == 0)
				continue;

			int neededBoxCnt = (int) Math.ceil((double) remainQty / perBoxQty);

			int totalCapacity = neededBoxCnt * capacity;
			int remainSpace = totalCapacity - (remainQty * itemVolume); // 🔥 핵심

			// 🔥 변경된 기준
			if (neededBoxCnt < minBoxCount || (neededBoxCnt == minBoxCount && remainSpace < minRemainSpace)) {

				minBoxCount = neededBoxCnt;
				minRemainSpace = remainSpace;
				bestBoxInfo = boxInfo;
			}
		}

		if (bestBoxInfo == null) {
			throw new UserHandleException("박스 없음 storageType=" + storageType);
		}

		WdKxDeliveryInvoiceResDto box = new WdKxDeliveryInvoiceResDto();

		box.setBoxno(bestBoxInfo.getBoxno());
		box.setStoragetype(storageType);
		box.setBoxtype(bestBoxInfo.getBoxno());
		box.setCapacity(bestBoxInfo.getCapacity());
		box.setUsed(0);

		return box;
	}

	private WdKxDeliveryInvoiceResDto findBestFitBox(String itemSt, int volume) {

		WdKxDeliveryInvoiceResDto best = null;
		int minRemain = Integer.MAX_VALUE;

		for (WdKxDeliveryInvoiceResDto box : openedBoxes) {

			if (!canPutInBox(itemSt, box.getStoragetype()))
				continue;

			int remain = box.getCapacity() - box.getUsed();

			if (remain >= volume && (remain - volume) < minRemain) {
				best = box;
				minRemain = remain - volume;
			}
		}

		return best;
	}

	// 박스에 넣을 수 있는지 여부
	private boolean canPutInBox(String itemSt, String boxSt) {
		// 냉동 → 냉동만
		if ("30".equals(itemSt)) {
			return "30".equals(boxSt);
		}

		// 냉장 → 냉장 or 냉동
		if ("20".equals(itemSt)) {
			return "20".equals(boxSt) || "30".equals(boxSt);
		}

		// 상온 → 상온만
		if ("10".equals(itemSt)) {
			return "10".equals(boxSt);
		}

		return false;
	}

	/** 열린 박스 목록 */
	private List<WdKxDeliveryInvoiceResDto> openedBoxes = new ArrayList<WdKxDeliveryInvoiceResDto>();
	/** 박스 정보 목록 */
	List<WdKxDeliveryInvoiceBoxResDto> boxInfoList = new ArrayList<>();

	// 주문 ITEM 분리 생성
	// item_seq 증가
	// box_no, box_id, item_qty 저장
	private void saveItemToBox(WdKxDeliveryInvoiceResDto itemDto, WdKxDeliveryInvoiceResDto box, int qty, int lineNo,
			String serialkey, int groupIndex, int myReceiveBoxCnt) {
		int iCnt = 0; // 등록 건수
		WdKxDeliveryInvoiceEntity entity = ModelMapperUtil.map(itemDto, userContext, WdKxDeliveryInvoiceEntity.class);

		entity.setBoxtype(box.getBoxno());
		entity.setBoxqty(BigDecimal.valueOf(qty));
		entity.setSeq(BigDecimal.valueOf(myReceiveBoxCnt)); // 수령자그룹 기준 박스번호
		entity.setSerialkey(serialkey); // 본인 키 추가
		//entity.setGroupIndex(BigDecimal.valueOf(groupIndex)); // 수령자그룹 인덱스 - 미사용
		iCnt = commonDao.insert(SERVICEID_PREFIX + "insertMasterInvoceDivide", entity);
		if (iCnt < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		}
	}

	////////////////////////////////////////////////// sssssssssss

	/**
	 * @description : 퀵접수(VSR)및처리 퀵주문접수 API 전문 조회 List Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성
	 *         </pre>
	 */
	public <R, T> List<R> getOrderRequest01(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getOrderRequest01", reqDto);
	}

	/**
	 * Day 토큰 발행 ->대한통운에서 제공하는 서비스를 사용하기 위한 유효기간 1일인 보안 토큰 발행
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public String getAccessToken(WdKxDeliveryAPI01Dto dto) {
		// START.필수입력 check
		if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getKxCustId()))) {
			throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] { "kxCustId" }));
		}
		// END.필수입력 check

		// 01.유효한 token 조회
		String tokenNum = commonDao.selectOne(SERVICEID_PREFIX + "getAccessToken", dto);
		log.info("▶tokenNum->{}", tokenNum);

		// 02.유효한 token 없을 경우 token 발행
		if ("".equals(StringUtil.nvl(tokenNum))) {
			// Day 토큰 재발행
			WdKxDeliveryAPI01Dto tokenDto = refreshAccessToken(dto);
			tokenNum = tokenDto.getTokenNum();
		}
		dto.setTokenNum(tokenNum);
		return dto.getTokenNum();
	}

	/**
	 * Day 토큰 재발행 ->대한통운에서 제공하는 서비스를 사용하기 위한 유효기간 1일인 보안 토큰 발행
	 * 
	 * <pre>
	 *  --토큰발급결과 예시
	 *  { 
	 *    "RESULT_CD": "S", 
	 *    "RESULT_DETAIL": "Success", 
	 *    "DATA": { 
	 *    "TOKEN_NUM": "23c5c70e-97f8-4a46-9d4a-8b15b098429z", 
	 *    "TOKEN_ EXPRTN_DTM ": "20210327141012", 
	 *            "NOTICE": "3월14일 00:00~03:00 PM작업 예정. 채번, 주소정제 외 사용 불가", 
	 *        } 
	 *  }
	 * </pre>
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private WdKxDeliveryAPI01Dto refreshAccessToken(WdKxDeliveryAPI01Dto paramDto) {
		// String url = "https://dxapi-dev.cjlogistics.com:5054/ReqOneDayToken";
		String url = ContextUtil.getProperty("cf.kxdelivery.url_token"); // URL
		String bizNo = ContextUtil.getProperty("cf.kxdelivery.bizNo"); // 사업자번호
		String appkey = "";
		final String serverUrl = url + "?api_key=" + appkey;
		String tokenNum = "";
		String tokenExprtnDtm = ""; // 토큰만료시간(YYYYMMDDHHMMSS)
		int uCnt = 0; // 수정 건수
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdKxDeliveryAPI01Dto returnDto = ModelMapperUtil.map(paramDto, WdKxDeliveryAPI01Dto.class);

		try {
			/*********************************************************
			 * 1.헤더정보 설정
			 ********************************************************/
			HttpHeaders headers = new HttpHeaders();
			RestTemplate restTemplate = new RestTemplate();
			headers.setContentType(MediaType.APPLICATION_JSON);

			/*********************************************************
			 * 2.전문 데이터 조회
			 ********************************************************/
			JSONObject params = new JSONObject();
			JSONObject bodyMap = new JSONObject();
			params.put("CUST_ID", paramDto.getKxCustId()); // 고객ID
			params.put("BIZ_REG_NUM", bizNo); // 사업자등록번호
			bodyMap.put("DATA", params);
			// log.info("▶bodyMap->{}",MaskingUtil.maskLog(bodyMap.toJSONString()));

			// 데이터가 담긴 json을 hhtp객체에 담아 요청
			HttpEntity<String> req = new HttpEntity<>(bodyMap.toJSONString(), headers);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(serverUrl, req, String.class);

			log.info("");
			log.info("▶요청.url: " + url);
			log.info("▶1.택배토큰요청.Headers");
			log.info(" ▶요청.Headers->{}", req.getHeaders());
			log.info(" ▶요청.Body   ->{}", MaskingUtil.maskLog(bodyMap.toJSONString()));

			// log.info("API call finish. Received data from api...");
			// log.info("--- data ResponseEntity : {}", responseEntity);

			// API 호출 수신 값
			String received = responseEntity.getBody();
			// log.info("Received Data : {}", received);

			log.info("▶2.택배토큰요청 응답");
			log.info(" ▶응답.Body   ->{}", received);
			log.info("");

			JSONParser parser = new JSONParser();
			JSONObject resultBody = (JSONObject) parser.parse(received);
			JSONObject detail = (JSONObject) resultBody.get("DATA");

			tokenNum = (String) detail.get("TOKEN_NUM");
			tokenExprtnDtm = (String) detail.get("TOKEN_EXPRTN_DTM");
			String RESULT_CD = "";
			String RESULT_DETAIL = "";
			RESULT_CD = StringUtil.nvl((String) resultBody.get("RESULT_CD"));
			RESULT_DETAIL = StringUtil.nvl((String) resultBody.get("RESULT_DETAIL"));

			log.info("▶▶▶ resultBody      : {}", resultBody.toString());
			log.info("▶▶▶ tokenNum     : {}", tokenNum);
			log.info("▶▶▶ tokenExprtnDtm  : {}", tokenExprtnDtm);

			returnDto.setResultCd(RESULT_CD);
			returnDto.setResultDetail(RESULT_DETAIL);
			returnDto.setTokenNum(tokenNum);
			returnDto.setTokenExprtnDtm(tokenExprtnDtm);
			WdKxDeliveryInvoiceEntity entity = ModelMapperUtil.map(returnDto, userContext,
					WdKxDeliveryInvoiceEntity.class);

			log.info("▶▶▶ RESULT_CD        : {}", RESULT_CD);
			if ("S".equals(RESULT_CD)) {
				// 03.token 저장
				uCnt += commonDao.update(SERVICEID_PREFIX + "updateAccessToken", entity);
				if (uCnt < 1) {
					throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
				}
			} else {
				log.error("▶택배 API처리 중 토큰정보를 조회하지 못했습니다. resultBody: {}", resultBody.toString());
				throw new UserHandleException(RESULT_DETAIL);
			}
		} catch (Exception e) {
			log.error("{}", e);
			// 필요 시 퀵 시스템 담당자 알림처리
			throw new UserHandleException(
					MessageUtil.getMessage("MSG_COM_ERR_055", new String[] { "CJ택배 API" }) + e.getMessage());
		}

		return returnDto;
	} // Day 토큰 재발행

	/**
	 * 주소정제 ->대한통운에서 제공하는 서비스를 사용하기 위한 주소정제
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @return
	 */
	public WdKxDeliveryAPI03Dto reqAddrRfnSm(WdKxDeliveryAPI03Dto paramDto) {
		final String serverUrl = ContextUtil.getProperty("cf.kxdelivery.url_addr"); // URL
		//String clntnum = ContextUtil.getProperty("cf.kxdelivery.clientNumber"); // 고객ID - 자체적인 주소정제 시 사용
		int uCnt = 0; // 수정 건수

		// 파라미터 위변조 적용(paramDto->reqDto)
		WdKxDeliveryAPI03Dto apiDto = ModelMapperUtil.map(paramDto, WdKxDeliveryAPI03Dto.class);

		/*********************************************************
		 * 1. 토큰조회 API 호출
		 ********************************************************/
		String tokenNum = "";
		WdKxDeliveryAPI01Dto wdKxDeliveryAPI01Dto = new WdKxDeliveryAPI01Dto();
		wdKxDeliveryAPI01Dto.setKxCustId(apiDto.getClntnum()); // 고객ID
		tokenNum = getAccessToken(wdKxDeliveryAPI01Dto);

		/*********************************************************
		 * 2. 주소정제 API 요청 값
		 ********************************************************/
		apiDto.setTokenNum(tokenNum); // 1.토큰번호
		apiDto.setClntnum(apiDto.getClntnum()); // 2.고객ID
		apiDto.setClntmgmcustcd(apiDto.getClntnum()); // 3.협력사코드가 없을 경우 고객ID로 대체
		apiDto.setUserId(""); // 4.중개업체ID
		apiDto.setAddress(apiDto.getAddress()); // 5.주소

		try {
			/*********************************************************
			 * 1.헤더정보 설정
			 ********************************************************/
			HttpHeaders headers = new HttpHeaders();
			RestTemplate restTemplate = new RestTemplate();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("CJ-Gateway-APIKey", apiDto.getTokenNum());

			/*********************************************************
			 * 2.전문 데이터 조회
			 ********************************************************/
			JSONObject params = new JSONObject();
			JSONObject bodyMap = new JSONObject();
			params.put("TOKEN_NUM", apiDto.getTokenNum()); // 1.토큰번호
			params.put("CLNTNUM", apiDto.getClntnum()); // 2.고객ID
			params.put("CLNTMGMCUSTCD", apiDto.getClntmgmcustcd()); // 3.협력사 코드 - 협력사코드가 없을 경우 고객ID로 대체
			params.put("USER_ID", apiDto.getUserId()); // 4.중개업체ID
			params.put("ADDRESS", apiDto.getAddress()); // 5.주소
			bodyMap.put("DATA", params);
			// log.info("▶bodyMap->{}",MaskingUtil.maskLog(bodyMap.toJSONString()));

			// 데이터가 담긴 json을 hhtp객체에 담아 요청
			HttpEntity<String> req = new HttpEntity<>(bodyMap.toJSONString(), headers);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(serverUrl, req, String.class);

			log.info("");
			log.info("▶요청.url: " + serverUrl);
			log.info("▶1.요청.CJ택배.주소정제");
			log.info(" ▶요청.Headers->{}", req.getHeaders());
			log.info(" ▶요청.Body   ->{}", MaskingUtil.maskLog(bodyMap.toJSONString()));

			// log.info("API call finish. Received data from api...");
			// log.info("--- data ResponseEntity : {}", responseEntity);

			// API 호출 수신 값
			String received = responseEntity.getBody();
			// log.info("Received Data : {}", received);

			log.info("▶2.응답.CJ택배.주소정제");
			log.info(" ▶응답.Body   ->{}", received);
			log.info("");

			JSONParser parser = new JSONParser();
			JSONObject resultBody = (JSONObject) parser.parse(received);
			JSONObject detail = (JSONObject) resultBody.get("DATA");

			String RESULT_CD = "";
			String RESULT_DETAIL = "";
			RESULT_CD = StringUtil.nvl((String) resultBody.get("RESULT_CD"));
			RESULT_DETAIL = StringUtil.nvl((String) resultBody.get("RESULT_DETAIL"));

			log.info("▶▶▶ resultBody      : {}", resultBody.toString());

			apiDto.setResultCd(RESULT_CD);
			apiDto.setResultDetail(RESULT_DETAIL);

			apiDto.setRcptErrMsg(RESULT_DETAIL);

			log.info("▶▶▶ RESULT_CD        : {}", RESULT_CD);
			if ("S".equals(RESULT_CD)) {
				// 성공 시에만 주소정제 결과값 셋팅
				String CLSFCD = (String) detail.get("CLSFCD"); // 도착지코드
				String SUBCLSFCD = (String) detail.get("SUBCLSFCD"); // 도착지 서브 코드
				String CLSFADDR = (String) detail.get("CLSFADDR"); // 주소 약칭
				String CLLDLVBRANNM = (String) detail.get("CLLDLVBRANNM"); // 배송집배점 명
				String CLLDLVEMPNM = (String) detail.get("CLLDLVEMPNM"); // 배송SM명
				String CLLDLVEMPNICKNM = (String) detail.get("CLLDLVEMPNICKNM"); // SM분류코드
				String RSPSDIV = (String) detail.get("RSPSDIV"); // 권역 구분
				String P2PCD = (String) detail.get("P2PCD"); // P2P코드

				apiDto.setClsfcd(SUBCLSFCD);
				apiDto.setSubclsfcd(SUBCLSFCD);
				apiDto.setClsfaddr(CLSFADDR);
				apiDto.setClldlvbrannm(CLLDLVBRANNM);
				apiDto.setClldlvempnm(CLLDLVEMPNM);
				apiDto.setClldlvempnicknm(CLLDLVEMPNICKNM);
				apiDto.setRspsdiv(RSPSDIV); // 권역 구분
				apiDto.setP2pcd(P2PCD); // P2P코드
				log.info("▶▶▶ CLSFCD          : {}", CLSFCD);
				log.info("▶▶▶ SUBCLSFCD       : {}", SUBCLSFCD);
				log.info("▶▶▶ CLSFADDR        : {}", CLSFADDR);
				log.info("▶▶▶ CLLDLVBRANNM    : {}", CLLDLVBRANNM);
				log.info("▶▶▶ CLLDLVEMPNM     : {}", CLLDLVEMPNM);
				log.info("▶▶▶ CLLDLVEMPNICKNM : {}", CLLDLVEMPNICKNM);
				log.info("▶▶▶ RSPSDIV         : {}", RSPSDIV);
				log.info("▶▶▶ P2PCD           : {}", P2PCD);

				apiDto.setRcptErrYn("N");
			} else {
				apiDto.setRcptErrYn("Y");
				log.error("▶주소정제 API처리 중 정보를 조회하지 못했습니다. resultBody: {}", resultBody.toString());
			}

			// 03.주소정제 결과 저장
			WdKxDeliveryInvoiceEntity entity = ModelMapperUtil.map(apiDto, userContext, WdKxDeliveryInvoiceEntity.class);
			uCnt += commonDao.update(SERVICEID_PREFIX + "updateAddrRfnSmResult", entity);
			if (uCnt < 1) {
				throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
			}

		} catch (Exception e) {
			log.error("CJ택배 주소정제 API 실패->{}", e);
			// 03.주소정제 결과 저장
			WdKxDeliveryInvoiceEntity entity = ModelMapperUtil.map(apiDto, userContext, WdKxDeliveryInvoiceEntity.class);
			entity.setRcptErrYn("N");
			entity.setRcptErrMsg("CJ택배 주소정제 API 실패:" + StringUtil.nvl(e.getMessage()).substring(0,Math.min(StringUtil.nvl(e.getMessage()).length(), 200)));
			uCnt += commonDao.update(SERVICEID_PREFIX + "updateAddrRfnSmResult", entity);
			if (uCnt < 1) {
				throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
			}
		}

		return apiDto;
	}

	/**
	 * 운송장번호 채번 ->고객사에 운송장 번호 단건 제공
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @return
	 */
	private WdKxDeliveryAPI04Dto reqInvcNo(WdKxDeliveryAPI04Dto paramDto) {
		final String serverUrl = ContextUtil.getProperty("cf.kxdelivery.url_invoice"); // URL
		// String clntnum = ContextUtil.getProperty("cf.kxdelivery.clientNumber"); //
		// 고객ID
		int uCnt = 0; // 수정 건수
		int rv = 0; // 적용 건수
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdKxDeliveryAPI04Dto apiDto = ModelMapperUtil.map(paramDto, WdKxDeliveryAPI04Dto.class);

		/*********************************************************
		 * 1. 토큰조회 API 호출
		 ********************************************************/
		String tokenNum = "";
		WdKxDeliveryAPI01Dto wdKxDeliveryAPI01Dto = new WdKxDeliveryAPI01Dto();
		wdKxDeliveryAPI01Dto.setKxCustId(apiDto.getClntnum()); // 고객ID
		tokenNum = getAccessToken(wdKxDeliveryAPI01Dto);

		/*********************************************************
		 * 2. 주소정제 API 요청 값
		 ********************************************************/
		apiDto.setTokenNum(tokenNum); // 1.토큰번호
		apiDto.setClntnum(apiDto.getClntnum()); // 2.고객ID
		try {
			/*********************************************************
			 * 1.헤더정보 설정
			 ********************************************************/
			HttpHeaders headers = new HttpHeaders();
			RestTemplate restTemplate = new RestTemplate();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("CJ-Gateway-APIKey", apiDto.getTokenNum());

			/*********************************************************
			 * 2.전문 데이터 조회
			 ********************************************************/
			JSONObject params = new JSONObject();
			JSONObject bodyMap = new JSONObject();
			params.put("TOKEN_NUM", apiDto.getTokenNum()); // 1.토큰번호
			params.put("CLNTNUM", apiDto.getClntnum()); // 2.고객ID
			bodyMap.put("DATA", params);
			// log.info("▶bodyMap->{}",MaskingUtil.maskLog(bodyMap.toJSONString()));

			// 데이터가 담긴 json을 hhtp객체에 담아 요청
			HttpEntity<String> req = new HttpEntity<>(bodyMap.toJSONString(), headers);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(serverUrl, req, String.class);

			log.info("");
			log.info("▶1.요청.CJ택배.운송자채번");
			log.info(" ▶요청.Headers->{}", req.getHeaders());
			log.info(" ▶요청.Body   ->{}", MaskingUtil.maskLog(bodyMap.toJSONString()));

			// API 호출 수신 값
			String received = responseEntity.getBody();

			log.info("▶2.응답.CJ택배.운송자채번");
			log.info(" ▶응답.Body   ->{}", received);
			log.info("");

			JSONParser parser = new JSONParser();
			JSONObject resultBody = (JSONObject) parser.parse(received);
			JSONObject detail = (JSONObject) resultBody.get("DATA");

			String RESULT_CD = "";
			String RESULT_DETAIL = "";
			RESULT_CD = StringUtil.nvl((String) resultBody.get("RESULT_CD"));
			RESULT_DETAIL = StringUtil.nvl((String) resultBody.get("RESULT_DETAIL"));

			log.info("▶▶▶ resultBody      : {}", resultBody.toString());

			apiDto.setResultCd(RESULT_CD);
			apiDto.setResultDetail(RESULT_DETAIL);

			WdKxDeliveryInvoiceEntity entity = ModelMapperUtil.map(apiDto, userContext,
					WdKxDeliveryInvoiceEntity.class);

			entity.setRcptErrMsg(RESULT_DETAIL);

			log.info("▶▶▶ RESULT_CD        : {}", RESULT_CD);
			if ("S".equals(RESULT_CD)) {
				// 성공 시에만 결과값 셋팅
				String INVC_NO = (String) detail.get("INVC_NO"); // 운송장 번호
				apiDto.setInvcNo(INVC_NO);
				log.info("▶▶▶ INVC_NO        : {}", INVC_NO);

				entity.setRcptErrYn("N");
			} else {
				log.error("▶운송자채번 API처리 중 정보를 조회하지 못했습니다. resultBody: {}", resultBody.toString());
				entity.setRcptErrYn("Y");

				// 03.결과 저장
				rv = commonDao.update(SERVICEID_PREFIX + "updateReqInvcNoResult", entity);
				if (rv < 1) {
					throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
				}
				uCnt++;
			}

		} catch (Exception e) {
			log.error("CJ택배 운송자채번 API 실패->{}", e);
			// 03.운송자채번 결과 저장
			WdKxDeliveryInvoiceEntity entity = ModelMapperUtil.map(apiDto, userContext,
					WdKxDeliveryInvoiceEntity.class);
			entity.setRcptErrYn("N");
			entity.setRcptErrMsg("CJ택배 운송자채번 API 실패:" + StringUtil.nvl(e.getMessage()).substring(0,
					Math.min(StringUtil.nvl(e.getMessage()).length(), 200)));
			uCnt += commonDao.update(SERVICEID_PREFIX + "updateReqInvcNoResult", entity);
			if (uCnt < 1) {
				throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
			}
		}

		return apiDto;
	}

	/**
	 * @description : 예약접수 - 배송요청정보 등록 ->고객사의 배송 요청정보를 대한통운에 등록
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.30 sss                   생성
	 *         </pre>
	 */
	@SuppressWarnings("unchecked")
	public WdKxDeliveryAPI02Dto regBook(WdKxDeliveryAPI02Dto paramDto) {
		String url = ContextUtil.getProperty("cf.kxdelivery.url_book"); // URL
		String appkey = "";
		final String serverUrl = url + "?api_key=" + appkey;

		int iCnt = 0; // 등록 건수A
		int uCnt = 0; // 수정 건수
		int processCnt = 0; // 처리 건수
		String invoiceno = "";

		// 파라미터 위변조 적용(paramDto->reqDto)
		WdKxDeliveryAPI02Dto apiDto = ModelMapperUtil.map(paramDto, WdKxDeliveryAPI02Dto.class);

		try {
			/*********************************************************
			 * 1.헤더정보 설정
			 ********************************************************/
			HttpHeaders headers = new HttpHeaders();
			RestTemplate restTemplate = new RestTemplate();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("CJ-Gateway-APIKey", apiDto.getTokenNum());

			/*********************************************************
			 * 2.전문 데이터 조회
			 ********************************************************/
			JSONObject params = new JSONObject();
			JSONObject bodyMap = new JSONObject();
			params.put("TOKEN_NUM", apiDto.getTokenNum()); // 1.토큰번호
			params.put("USER_ID", apiDto.getCustId()); // 2.중개업체ID
			params.put("CUST_ID", apiDto.getCustId()); // 3.KX고객사ID
			params.put("RCPT_YMD", apiDto.getRcptYmd()); // 4.접수일자
			params.put("CUST_USE_NO", apiDto.getCustUseNo()); // 5.고객사용번호

			params.put("RCPT_DV", apiDto.getRcptDv()); // 6.접수구분 (01: 일반 02: 반품)
			params.put("WORK_DV_CD", apiDto.getWorkDvCd()); // 7.작업구분코드 (01: 일반)
			params.put("REQ_DV_CD", apiDto.getReqDvCd()); // 8.요청구분코드 (01: 요청 02: 취소)
			params.put("MPCK_KEY", apiDto.getMpckKey()); // 9.합포장 키 (다수 데이터 한 송장 출력 시 사용 무결성 보장)
			params.put("CAL_DV_CD", apiDto.getCalDvCd()); // 10.정산구분코드 (01: 계약 운임)

			params.put("FRT_DV_CD", apiDto.getFrtDvCd()); // 11.운임구분코드 (01: 선불 02: 착불 03: 신용)
			params.put("CNTR_ITEM_CD", apiDto.getCntrItemCd()); // 12.계약품목코드 (01: 일반 품목)
			params.put("BOX_TYPE_CD", apiDto.getBoxTypeCd()); // 13.박스타입코드
			params.put("BOX_QTY", String.valueOf(apiDto.getBoxQty())); // 14.박스 수량
			params.put("FRT", apiDto.getFrt()); // 15.운임

			params.put("CUST_MGMT_DLCM_CD", apiDto.getCustMgmtDlcmCd()); // 고객관리거래처코드
			params.put("SENDR_NM", apiDto.getSendrNm()); // 보내는분 명
			params.put("SENDR_TEL_NO1", apiDto.getSendrTelNo1()); // 보내는분 전화번호1
			params.put("SENDR_TEL_NO2", apiDto.getSendrTelNo2()); // 보내는분 전화번호2
			params.put("SENDR_TEL_NO3", apiDto.getSendrTelNo3()); // 보내는분 전화번호3
			params.put("SENDR_CELL_NO1", apiDto.getSendrCellNo1()); // 보내는분 휴대폰번호1
			params.put("SENDR_CELL_NO2", apiDto.getSendrCellNo2()); // 보내는분 휴대폰번호2
			params.put("SENDR_CELL_NO3", apiDto.getSendrCellNo3()); // 보내는분 휴대폰번호3
			params.put("SENDR_SAFE_NO1", apiDto.getSendrSafeNo1()); // 보내는분 안심번호1
			params.put("SENDR_SAFE_NO2", apiDto.getSendrSafeNo2()); // 보내는분 안심번호2
			params.put("SENDR_SAFE_NO3", apiDto.getSendrSafeNo3()); // 보내는분 안심번호3
			params.put("SENDR_ZIP_NO", apiDto.getSendrZipNo()); // 보내는분 우편번호
			params.put("SENDR_ADDR", apiDto.getSendrAddr()); // 보내는분 주소
			params.put("SENDR_DETAIL_ADDR", apiDto.getSendrDetailAddr()); // 보내는분 상세주소
			//
			params.put("RCVR_NM", apiDto.getRcvrNm()); // 받는분 명 (반품 시 고정 회수지)
			params.put("RCVR_TEL_NO1", apiDto.getRcvrTelNo1()); // 받는분 전화번호1
			params.put("RCVR_TEL_NO2", apiDto.getRcvrTelNo2()); // 받는분 전화번호2
			params.put("RCVR_TEL_NO3", apiDto.getRcvrTelNo3()); // 받는분 전화번호3
			params.put("RCVR_CELL_NO1", apiDto.getRcvrCellNo1()); // 받는분 휴대폰번호1
			params.put("RCVR_CELL_NO2", apiDto.getRcvrCellNo2()); // 받는분 휴대폰번호2
			params.put("RCVR_CELL_NO3", apiDto.getRcvrCellNo3()); // 받는분 휴대폰번호3
			params.put("RCVR_SAFE_NO1", apiDto.getRcvrSafeNo1()); // 받는분 안심번호1
			params.put("RCVR_SAFE_NO2", apiDto.getRcvrSafeNo2()); // 받는분 안심번호2
			params.put("RCVR_SAFE_NO3", apiDto.getRcvrSafeNo3()); // 받는분 안심번호3
			params.put("RCVR_ZIP_NO", apiDto.getRcvrZipNo()); // 받는분 우편번호
			params.put("RCVR_ADDR", apiDto.getRcvrAddr()); // 받는분 주소
			params.put("RCVR_DETAIL_ADDR", apiDto.getRcvrDetailAddr()); // 받는분 상세주소
			//
			params.put("ORDRR_NM", apiDto.getOrdrrNm()); // 주문자 명
			params.put("ORDRR_TEL_NO1", apiDto.getOrdrrTelNo1()); // 주문자 전화번호1
			params.put("ORDRR_TEL_NO2", apiDto.getOrdrrTelNo2()); // 주문자 전화번호2
			params.put("ORDRR_TEL_NO3", apiDto.getOrdrrTelNo3()); // 주문자 전화번호3
			params.put("ORDRR_CELL_NO1", apiDto.getOrdrrCellNo1()); // 주문자 휴대폰번호1
			params.put("ORDRR_CELL_NO2", apiDto.getOrdrrCellNo2()); // 주문자 휴대폰번호2
			params.put("ORDRR_CELL_NO3", apiDto.getOrdrrCellNo3()); // 주문자 휴대폰번호3
			params.put("ORDRR_SAFE_NO1", apiDto.getOrdrrSafeNo1()); // 주문자 안심번호1
			params.put("ORDRR_SAFE_NO2", apiDto.getOrdrrSafeNo2()); // 주문자 안심번호2
			params.put("ORDRR_SAFE_NO3", apiDto.getOrdrrSafeNo3()); // 주문자 안심번호3
			params.put("ORDRR_ZIP_NO", apiDto.getOrdrrZipNo()); // 주문자 우편번호
			params.put("ORDRR_ADDR", apiDto.getOrdrrAddr()); // 주문자 주소
			params.put("ORDRR_DETAIL_ADDR", apiDto.getOrdrrDetailAddr()); // 주문자 상세주소
			// 운송장 번호 - 실패 시 롤백처리 위해 여기서 셋팅

			WdKxDeliveryAPI04Dto api04Dto = new WdKxDeliveryAPI04Dto();
			api04Dto.setClntnum(apiDto.getCustId()); // 고객ID
			api04Dto.setSerialkey(apiDto.getSerialkey()); // 고객사용번호
			invoiceno = reqInvcNo(api04Dto).getInvcNo(); // 운송장번호 채번 API 호출
			apiDto.setInvcNo(invoiceno);
			// apiDto.setInvcNo(issueInvoiceNo(invoiceDto)); // 송장번호 셋팅
			if ("".equals(invoiceno)) {
				throw new UserHandleException("운송장번호가 존재하지 않습니다.");
			}

			params.put("INVC_NO", apiDto.getInvcNo()); // 운송장 번호 (반품 시 미사용)
			params.put("ORI_INVC_NO", apiDto.getOriInvcNo()); // 원운송장번호 (반품/회수 대상)
			params.put("ORI_ORD_NO", apiDto.getOriOrdNo()); // 원주문 번호
			//
			params.put("COLCT_EXPCT_YMD", apiDto.getColctExpctYmd()); // 집화 예정일자
			params.put("COLCT_EXPCT_HOUR", apiDto.getColctExpctHour()); // 집화 예정시간
			params.put("SHIP_EXPCT_YMD", apiDto.getShipExpctYmd()); // 배송 예정일자
			params.put("SHIP_EXPCT_HOUR", apiDto.getShipExpctHour()); // 배송 예정시간

			params.put("PRT_ST", apiDto.getPrtSt()); // 운송장 출력상태
			params.put("ARTICLE_AMT", apiDto.getArticleAmt()); // 물품가 액

			params.put("REMARK_1", apiDto.getRemark1()); // 비고1 (배송메세지)
			params.put("REMARK_2", apiDto.getRemark2()); // 비고2 (보내는분 비고)
			params.put("REMARK_3", apiDto.getRemark3()); // 비고3 (받는분 비고)

			params.put("COD_YN", apiDto.getCodYn()); // COD 여부

			params.put("ETC_1", apiDto.getEtc1()); // 기타1
			params.put("ETC_2", apiDto.getEtc2()); // 기타2
			params.put("ETC_3", apiDto.getEtc3()); // 기타3
			params.put("ETC_4", apiDto.getEtc4()); // 기타4
			params.put("ETC_5", apiDto.getEtc5()); // 기타5

			params.put("DLV_DV", apiDto.getDlvDv()); // 택배구분 (01: 택배)
			params.put("RCPT_SERIAL", apiDto.getRcptSerial()); // 접수 시리얼 번호

			// 상품정보
			log.info("▶상품정보 조회 파라미터: {}", MaskingUtil.maskLog(apiDto.toString()));
			List<Map<String, Object>> gdsList = commonDao.selectList(SERVICEID_PREFIX + "getGdsList", apiDto);
			if (gdsList.size() < 1) {
				throw new UserHandleException("상품정보가 존재하지 않습니다.");
			}
			JSONArray array = new JSONArray();
			for (Map<String, Object> gds : gdsList) {
				JSONObject obj = new JSONObject();
				obj.put("MPCK_SEQ", String.valueOf(gds.get("MPCK_SEQ")));
				obj.put("GDS_CD", String.valueOf(gds.get("GDS_CD")));
				obj.put("GDS_NM", String.valueOf(gds.get("GDS_NM")));
				obj.put("GDS_QTY", String.valueOf(gds.get("GDS_QTY")));
				obj.put("UNIT_CD", String.valueOf(gds.get("UNIT_CD")));
				obj.put("UNIT_NM", String.valueOf(gds.get("UNIT_NM")));
				obj.put("GDS_AMT", String.valueOf(gds.get("GDS_AMT")));
				array.add(obj);
			}
			params.put("ARRAY", array);
			// 상품정보를 ARRAY로 보내는 형태이므로 주석처리
			// params.put("MPCK_SEQ" ,apiDto.getMpckSeq()); // 합포장 순번 (합포 없을 경우 1)
			// params.put("GDS_CD" ,apiDto.getGdsCd()); // 상품코드
			// params.put("GDS_NM" ,apiDto.getGdsNm()); // 상품명
			// params.put("GDS_QTY" ,apiDto.getGdsQty()); // 상품수량
			// params.put("UNIT_CD" ,apiDto.getUnitCd()); // 단품코드
			// params.put("UNIT_NM" ,apiDto.getUnitNm()); // 단품명
			// params.put("GDS_AMT" ,apiDto.getGdsAmt()); // 상품가액
			//
			log.info("▶요청.url: " + url);
			bodyMap.put("DATA", params);

			// 데이터가 담긴 json을 hhtp객체에 담아 요청
			HttpEntity<String> req = new HttpEntity<>(bodyMap.toJSONString(), headers);

			log.info("");
			log.info("▶1.택배예약요청.Headers");
			log.info(" ▶요청.Headers->{}", req.getHeaders());
			log.info(" ▶요청.Body   ->{}", MaskingUtil.maskLog(bodyMap.toJSONString()));

			// API 호출
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(serverUrl, req, String.class);

			// 응답 헤더 출력
			// log.info("--- data ResponseEntity : {}", responseEntity);
			// 응답 바디 출력
			String received = responseEntity.getBody();
			log.info("▶2.택배예약 응답");
			log.info(" ▶응답.Body   ->{}", received);
			log.info("");

			// String으로 되어져있는 바디부분을 다시 JSON형태로 파싱
			JSONParser parser = new JSONParser();
			JSONObject resultBody = (JSONObject) parser.parse(received);

			String RESULT_CD = "";
			String RESULT_DETAIL = "";
			RESULT_CD = StringUtil.nvl((String) resultBody.get("RESULT_CD"));
			RESULT_DETAIL = StringUtil.nvl((String) resultBody.get("RESULT_DETAIL"));

			apiDto.setResultCd(RESULT_CD);
			apiDto.setResultDetail(RESULT_DETAIL);
			WdKxDeliveryInvoiceEntity entity = ModelMapperUtil.map(apiDto, userContext,WdKxDeliveryInvoiceEntity.class);

			log.info("▶▶▶ RESULT_CD        : {}", RESULT_CD);
			if ("S".equals(RESULT_CD)) {
				// 엽업 I/F
				iCnt = commonDao.insert(SERVICEID_PREFIX + "insertIfCS", entity);
				if (iCnt < 1) {
					throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
				}
				processCnt++;
			} else {
				log.error("▶택배 API 처리 실패 resultBody: {}", resultBody.toString());
			}

			// 03.결과 저장 - 배송요청 결과 저장
			uCnt = commonDao.update(SERVICEID_PREFIX + "updateKxDeliveryResult01", entity);
			if (uCnt < 1) {
				throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
			}

			// 04.결과 저장 - 접수확정 결과 저장
			uCnt = commonDao.update(SERVICEID_PREFIX + "updateKxDeliveryResult02", entity);
			if (uCnt < 1) {
				throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
			}

		} catch (Exception e) {
			log.error("{}", e);
			// 필요 시 퀵 시스템 담당자 알림처리
			apiDto.setResultCd("E");
			apiDto.setResultDetail("WMS내부오류->" + e.getMessage());
			WdKxDeliveryInvoiceEntity entity = ModelMapperUtil.map(apiDto, userContext,WdKxDeliveryInvoiceEntity.class);
			uCnt = commonDao.update(SERVICEID_PREFIX + "updateKxDeliveryResult01", entity);
			// 주문데이터는 접수확정까지만 status가 관리되므로 여기서는 주석처리
			// uCnt = commonDao.update(SERVICEID_PREFIX + "updateKxDeliveryResult02",
			// entity);
		}

		apiDto.setProcessCnt(processCnt);

		return apiDto;
	} // 예약접수 - 배송요청정보 등록

	/**
	 * @description : 택배송장발행(온라인) - N배송 저장
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */
	public WdKxDeliveryInvoiceReqDto saveMasterList02(WdKxDeliveryInvoiceReqDto reqDto) {
		WdKxDeliveryInvoiceReqDto resultDto = new WdKxDeliveryInvoiceReqDto();
		List<WdKxDeliveryInvoiceResDto> saveList = reqDto.getSaveList(); // 저장리스트
		int iCnt = 0; // 등록 건수
		int uCnt = 0; // 수정 건수
		int dCnt = 0; // 삭제 건수
		int rv = 0; // 반환 값

		for (WdKxDeliveryInvoiceResDto dto : saveList) {
			WdKxDeliveryInvoiceEntity entity = ModelMapperUtil.map(dto, userContext, WdKxDeliveryInvoiceEntity.class);

			if (!CanalFrameConstants.DELETE.equals(dto.getRowStatus())) {
				// START.필수입력 check - 그리드 변수 등
				if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getBoxtype()))) {
					throw new UserHandleException(
							MessageUtil.getMessage("MSG_COM_ERR_056", new String[] { "boxtype" }));
				}
				if (entity.getBoxqty() == null || entity.getBoxqty().compareTo(BigDecimal.ZERO) == 0) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[] { "boxqty" }));
				}
				// END.필수입력 check
			}

			// START.Master.체크.가능여부
			List<Map<String, Object>> chkListMap = commonDao.selectList(SERVICEID_PREFIX + "selectChkIntMaster02", dto);
			String chk1 = (String) chkListMap.get(0).get("CHK1"); // 체크.택배접수여부
			String statusnm = (String) chkListMap.get(0).get("STATUSNM"); // 체크.상태명
			log.info("chk1(체크.택배접수여부):{}", chk1);
			String moreMsg = "\n주문번호 : [" + dto.getEmpCustDocno() + "]";
			moreMsg += "\n상품코드 : [" + dto.getSku() + "]";
			moreMsg += "\n상품명  : [" + dto.getDescription() + "]";

			if ("Y".equals(chk1)) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_059", new String[] { "현재 진행상태[" + statusnm + "] " }) + moreMsg); // 처리할 수 없습니다 {0}.
			// END.Master.체크.가능여부

			if (CanalFrameConstants.INSERT.equals(dto.getRowStatus())) {
				rv = commonDao.insert(SERVICEID_PREFIX + "insertMasterList02", entity);
				if (rv < 1) {
					throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
				}
				iCnt += rv;
			} else if (CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {
				rv = commonDao.update(SERVICEID_PREFIX + "updateMasterList02", entity);
				if (rv < 1) {
					throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
				}
				uCnt += rv;

			} else if (CanalFrameConstants.DELETE.equals(dto.getRowStatus())) {
				// 삭제처리(1/3).같은 BOXTYPE에 최종 SEQ에 삭제된 수량을 더해줌 - 없으면 SKIP 후 수동처리 - 적용건수 불필요
				rv = commonDao.update(SERVICEID_PREFIX + "updateMasterList02StatusBoxqty", entity);
				log.info("▶updateMasterList02StatusBoxqty rv->{}", rv);
				uCnt += rv;

				// 삭제처리(2/3).실제 데이터 삭제 - 적용건수 필요
				rv = commonDao.delete(SERVICEID_PREFIX + "deleteMasterList02", entity);
				if (rv < 1) {
					throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
				}
				dCnt += rv;

				// 삭제처리(3/3).송장분리 후 분리된 모든 전표를 삭제 시 업로드로 돌림 - 적용건수 불필요
				rv = commonDao.update(SERVICEID_PREFIX + "updateMasterList02Status", entity);
				log.info("▶updateMasterList02Status rv->{}", rv);
				uCnt += rv;
			}

		}
		log.info("▶▶▶ iCnt:{}, uCnt:{}, dCnt:{}", iCnt, uCnt, dCnt);
		if ((iCnt + uCnt + dCnt) < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		}
		return resultDto;
	}

//	public List<WdKxDeliveryInvoiceResDto>  printMasterList(WdKxDeliveryInvoiceReqDto paramDto) {
//		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", paramDto);
//	}

//	/**
//	 * @description : 택배송장발행(온라인) - 운송장출력여부 업데이트
//	 * @issues :
//	 *         <pre>
//	 * -----------------------------------------------------------
//	 * DATE       AUTHOR                MAJOR_ISSUE
//	 * -----------------------------------------------------------
//	 * 2025.12.26 sss  생성
//	 *         </pre>
//	 */
//	public WdKxDeliveryInvoiceReqDto updateDelYnInvoiceprtSt(WdKxDeliveryInvoiceReqDto reqDto) {
//		WdKxDeliveryInvoiceReqDto resultDto = new WdKxDeliveryInvoiceReqDto();
//		int uCnt = 0; // 수정 건수
//		int rv = 0; // 반환 값
//		
//		// 송장출력여부 업데이트 - entity는 List로 못받아서 dto로 받음
//		//WdKxDeliveryInvoiceEntity entity = ModelMapperUtil.map(resultDto, userContext, WdKxDeliveryInvoiceEntity.class);
//		rv = commonDao.insert(SERVICEID_PREFIX + "updateDelYnInvoiceprtSt", reqDto);
//		if (rv < 1) {
//			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
//		}
//		uCnt += rv;
//		return resultDto;
//	}

	/**
	 * @description : 택배송장발행(온라인) - 운송장출력
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */
	public WdKxDeliveryInvoiceReqDto printMasterList(WdKxDeliveryInvoiceReqDto paramDto) {
		WdKxDeliveryInvoiceReqDto resultDto = new WdKxDeliveryInvoiceReqDto();
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdKxDeliveryInvoiceReqDto reqDto = ModelMapperUtil.map(paramDto, WdKxDeliveryInvoiceReqDto.class);
		int uCnt = 0; // 수정 건수
		int rv = 0; // 반환 값
		reqDto.setNoMasking(true); // 마스킹 해제
		List<WdKxDeliveryInvoicePrintResDto> saveList = commonDao.selectList(SERVICEID_PREFIX + "printMasterList",reqDto);

		// 송장단위로 처리
		for (WdKxDeliveryInvoicePrintResDto dto : saveList) {
			// 송장출력여부 업데이트
			WdKxDeliveryInvoiceEntity entity = ModelMapperUtil.map(dto, userContext, WdKxDeliveryInvoiceEntity.class);
			rv = commonDao.insert(SERVICEID_PREFIX + "updateDelYnInvoiceprtSt", entity);
			if (rv < 1) {
				throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
			}
			uCnt += rv;
		}

		if (uCnt < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		}

		/* START.최종 출력물 데이터 조회 */
		resultDto.setReportList(saveList);
		/* START.최종 출력물 데이터 조회 */

		return resultDto;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * @description : 택배송장발행(온라인) - 일반택배 목록 조회 - getMasterList2 공용사용
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 *    생성
	 *         </pre>
	 */
	public <R, T> List<R> getMasterList3(WdKxDeliveryInvoiceReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", dto);
	}

	/**
	 * @description : 택배송장발행(온라인) - 반품택배 목록 조회
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 *    생성
	 *         </pre>
	 */
	public <R, T> List<R> getMasterList4(WdKxDeliveryInvoiceReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList4", dto);
	}

	/**
	 * @description : 택배송장발행(온라인) - 택배기준 목록 조회
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */
	public <R, T> List<R> getMasterList5(WdKxDeliveryInvoiceReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList5", dto);
	}

	/**
	 * @description : 택배송장발행(온라인) - 택배기준 저장
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성
	 *         </pre>
	 */
	public String saveMasterList5(WdKxDeliveryInvoiceReqDto dto) {
		WdKxDeliveryInvoiceBoxEntity entity = new WdKxDeliveryInvoiceBoxEntity();

		// 신규
		List<WdKxDeliveryInvoiceBoxResDto> insertList = dto.getInsertMaster5();
		if (insertList.size() > 0) {
			for (WdKxDeliveryInvoiceBoxResDto insertDto : insertList) {
				entity = ModelMapperUtil.map(insertDto, userContext, WdKxDeliveryInvoiceBoxEntity.class);
				commonDao.update(SERVICEID_PREFIX + "insertMaster5", entity);
			}
		}

		// update
		List<WdKxDeliveryInvoiceBoxResDto> updateList = dto.getUpdateMaster5();
		if (updateList.size() > 0) {
			for (WdKxDeliveryInvoiceBoxResDto updateDto : updateList) {
				entity = ModelMapperUtil.map(updateDto, userContext, WdKxDeliveryInvoiceBoxEntity.class);
				commonDao.update(SERVICEID_PREFIX + "updateMaster5", entity);
			}
		}

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
