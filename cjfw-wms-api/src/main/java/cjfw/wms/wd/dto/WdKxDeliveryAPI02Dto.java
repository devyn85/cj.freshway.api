package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 택배송장발행(온라인) 택배접수 DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "택배송장발행(온라인)택배접수 DTO")
public class WdKxDeliveryAPI02Dto extends CommonDto {
	/*********************************************************
     * 1.요청
     ********************************************************/ 	
    /** 토큰번호 */
    @Schema(description = "토큰번호")
    private String tokenNum;
    
    /** 고객사ID */
    @Schema(description = "고객사ID")
    private String custId;	
    
	/**접수일자*/
	@Schema(description = "접수일자")
	private String rcptYmd;
	  
	/**고객사용번호-추적번호*/
	@Schema(description = "고객사용번호")
	private String custUseNo;
    

	
	
	 /** 접수구분 (01: 일반, 02: 반품) */
    @Schema(description = "접수구분 (01: 일반, 02: 반품)")
    private String rcptDv;

    /** 작업구분코드 */
    @Schema(description = "작업구분코드 (01: 일반)")
    private String workDvCd;

    /** 요청구분코드 */
    @Schema(description = "요청구분코드 (01: 요청, 02: 취소)")
    private String reqDvCd;

    /** 합포장 키 */
    @Schema(description = "합포장 키")
    private String mpckKey;

    /** 정산구분코드 */
    @Schema(description = "정산구분코드 (01: 계약 운임)")
    private String calDvCd;

    /** 운임구분코드 */
    @Schema(description = "운임구분코드 (01: 선불, 02: 착불, 03: 신용)")
    private String frtDvCd;

    /** 계약품목코드 */
    @Schema(description = "계약품목코드")
    private String cntrItemCd;

    /** 박스타입코드 */
    @Schema(description = "박스타입코드")
    private String boxTypeCd;

    /** 박스 수량 */
    @Schema(description = "박스 수량")
    private Integer boxQty;

    /** 운임 */
    @Schema(description = "운임")
    private Integer frt;

    /** 고객관리거래처코드 */
    @Schema(description = "고객관리거래처코드")
    private String custMgmtDlcmCd;

    /** 보내는분 명 */
    @Schema(description = "보내는분 명")
    private String sendrNm;

    /** 보내는분 전화번호1 */
    @Schema(description = "보내는분 전화번호1")
    private String sendrTelNo1;

    /** 보내는분 전화번호2 */
    @Schema(description = "보내는분 전화번호2")
    private String sendrTelNo2;

    /** 보내는분 전화번호3 */
    @Schema(description = "보내는분 전화번호3")
    private String sendrTelNo3;

    /** 보내는분 휴대폰번호1 */
    @Schema(description = "보내는분 휴대폰번호1")
    private String sendrCellNo1;

    /** 보내는분 휴대폰번호2 */
    @Schema(description = "보내는분 휴대폰번호2")
    private String sendrCellNo2;

    /** 보내는분 휴대폰번호3 */
    @Schema(description = "보내는분 휴대폰번호3")
    private String sendrCellNo3;

    /** 보내는분 안심번호1 */
    @Schema(description = "보내는분 안심번호1")
    private String sendrSafeNo1;

    /** 보내는분 안심번호2 */
    @Schema(description = "보내는분 안심번호2")
    private String sendrSafeNo2;

    /** 보내는분 안심번호3 */
    @Schema(description = "보내는분 안심번호3")
    private String sendrSafeNo3;

    /** 보내는분 우편번호 */
    @Schema(description = "보내는분 우편번호")
    private String sendrZipNo;

    /** 보내는분 주소 */
    @Schema(description = "보내는분 주소")
    private String sendrAddr;

    /** 보내는분 상세주소 */
    @Schema(description = "보내는분 상세주소")
    private String sendrDetailAddr;

    /** 받는분 명 */
    @Schema(description = "받는분 명")
    private String rcvrNm;

    /** 받는분 전화번호1 */
    @Schema(description = "받는분 전화번호1")
    private String rcvrTelNo1;

    /** 받는분 전화번호2 */
    @Schema(description = "받는분 전화번호2")
    private String rcvrTelNo2;

    /** 받는분 전화번호3 */
    @Schema(description = "받는분 전화번호3")
    private String rcvrTelNo3;

    /** 받는분 휴대폰번호1 */
    @Schema(description = "받는분 휴대폰번호1")
    private String rcvrCellNo1;

    /** 받는분 휴대폰번호2 */
    @Schema(description = "받는분 휴대폰번호2")
    private String rcvrCellNo2;

    /** 받는분 휴대폰번호3 */
    @Schema(description = "받는분 휴대폰번호3")
    private String rcvrCellNo3;

    /** 받는분 안심번호1 */
    @Schema(description = "받는분 안심번호1")
    private String rcvrSafeNo1;

    /** 받는분 안심번호2 */
    @Schema(description = "받는분 안심번호2")
    private String rcvrSafeNo2;

    /** 받는분 안심번호3 */
    @Schema(description = "받는분 안심번호3")
    private String rcvrSafeNo3;

    /** 받는분 우편번호 */
    @Schema(description = "받는분 우편번호")
    private String rcvrZipNo;

    /** 받는분 주소 */
    @Schema(description = "받는분 주소")
    private String rcvrAddr;

    /** 받는분 상세주소 */
    @Schema(description = "받는분 상세주소")
    private String rcvrDetailAddr;

    /** 주문자 명 */
    @Schema(description = "주문자 명")
    private String ordrrNm;

    /** 주문자 전화번호1 */
    @Schema(description = "주문자 전화번호1")
    private String ordrrTelNo1;

    /** 주문자 전화번호2 */
    @Schema(description = "주문자 전화번호2")
    private String ordrrTelNo2;

    /** 주문자 전화번호3 */
    @Schema(description = "주문자 전화번호3")
    private String ordrrTelNo3;

    /** 주문자 휴대폰번호1 */
    @Schema(description = "주문자 휴대폰번호1")
    private String ordrrCellNo1;

    /** 주문자 휴대폰번호2 */
    @Schema(description = "주문자 휴대폰번호2")
    private String ordrrCellNo2;

    /** 주문자 휴대폰번호3 */
    @Schema(description = "주문자 휴대폰번호3")
    private String ordrrCellNo3;

    /** 주문자 안심번호1 */
    @Schema(description = "주문자 안심번호1")
    private String ordrrSafeNo1;

    /** 주문자 안심번호2 */
    @Schema(description = "주문자 안심번호2")
    private String ordrrSafeNo2;

    /** 주문자 안심번호3 */
    @Schema(description = "주문자 안심번호3")
    private String ordrrSafeNo3;

    /** 주문자 우편번호 */
    @Schema(description = "주문자 우편번호")
    private String ordrrZipNo;

    /** 주문자 주소 */
    @Schema(description = "주문자 주소")
    private String ordrrAddr;

    /** 주문자 상세주소 */
    @Schema(description = "주문자 상세주소")
    private String ordrrDetailAddr;

    /** 운송장번호 */
    @Schema(description = "운송장 번호")
    private String invcNo;

    /** 원운송장번호 */
    @Schema(description = "원운송장번호")
    private String oriInvcNo;

    /** 원주문번호 */
    @Schema(description = "원주문번호")
    private String oriOrdNo;

    /** 집화 예정일자 */
    @Schema(description = "집화 예정일자")
    private String colctExpctYmd;

    /** 집화 예정시간 */
    @Schema(description = "집화 예정시간")
    private String colctExpctHour;

    /** 배송 예정일자 */
    @Schema(description = "배송 예정일자")
    private String shipExpctYmd;

    /** 배송 예정시간 */
    @Schema(description = "배송 예정시간")
    private String shipExpctHour;

    /** 운송장 출력상태 */
    @Schema(description = "운송장 출력상태")
    private String prtSt;

    /** 물품가액 */
    @Schema(description = "물품가액")
    private Integer articleAmt;

    /** 비고1 */
    @Schema(description = "비고1")
    private String remark1;

    /** 비고2 */
    @Schema(description = "비고2")
    private String remark2;

    /** 비고3 */
    @Schema(description = "비고3")
    private String remark3;

    /** COD 여부 */
    @Schema(description = "COD 여부")
    private String codYn;

    /** 기타1 */
    @Schema(description = "기타1")
    private String etc1;

    /** 기타2 */
    @Schema(description = "기타2")
    private String etc2;

    /** 기타3 */
    @Schema(description = "기타3")
    private String etc3;

    /** 기타4 */
    @Schema(description = "기타4")
    private String etc4;

    /** 기타5 */
    @Schema(description = "기타5")
    private String etc5;

    /** 택배구분 */
    @Schema(description = "택배구분 (01: 택배)")
    private String dlvDv;

    /** 접수 시리얼 */
    @Schema(description = "접수 시리얼 번호")
    private String rcptSerial;

    /** 합포장 순번 */
    @Schema(description = "합포장 순번")
    private Integer mpckSeq;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String gdsCd;

    /** 상품명 */
    @Schema(description = "상품명")
    private String gdsNm;

    /** 상품수량 */
    @Schema(description = "상품수량")
    private Integer gdsQty;

    /** 단품코드 */
    @Schema(description = "단품코드")
    private String unitCd;

    /** 단품명 */
    @Schema(description = "단품명")
    private String unitNm;

    /** 상품가액 */
    @Schema(description = "상품가액")
    private BigDecimal gdsAmt;
    
    
    /*********************************************************
     * 2.응답
     ********************************************************/ 	
    /** 조회결과코드 */
    @Schema(description = "조회결과코드")
    private String resultCd;	
    
    /** 조회결과 */
    @Schema(description = "조회결과")
    private String resultDetail;
    

    
    
    /*********************************************************
     * 3.기타변수
     ********************************************************/ 	
	/**시리얼키*/
	@Schema(description = "시리얼키")
	private String serialkey;
	 
    /** 고객ID  */
    @Schema(description = "고객ID ")
    private String userId;
    
    /** 접수일자  */
    @Schema(description = "접수일자 ")
    private String docdt;
    
    /** 택배문서번호  */
    @Schema(description = "택배문서번호 ")
    private String docno;

    /** 전체건수 */	
    @Schema(description = "전체건수")
    private int totalCnt;	 
	
    /** 처리건수 */	
    @Schema(description = "처리건수")
    private int processCnt; 
}
