package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.09 
 * @description : 출고진행현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고진행현황 목록 결과")
public class StAdjustResultResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "001") // 예시: '001' 또는 'ML1'과 같은 물류센터 코드
    private String dccode;

    /**
     * 고객사
     */
    @Schema(description = "고객사", example = "CUST001") // 예시: 'CUST001' 또는 'APPLE'과 같은 고객사 코드
    private String storerkey;

    /**
     * 주문일자
     */
    @Schema(description = "주문일자", example = "20250522") // 예시: 'YYYYMMDD' 형식의 날짜
    private String docdt;

    /**
     * 창고
     */
    @Schema(description = "창고", example = "WH01") // 예시: 'WH01' 또는 'MAIN_WH'과 같은 창고 코드
    private String organize;

    /**
     * 조정일자
     */
    @Schema(description = "조정일자", example = "20250522") // 예시: 'YYYYMMDD' 형식의 날짜
    private String trandt;

    /**
     * 주문번호
     */
    @Schema(description = "주문번호", example = "DOC20250522001") // 예시: 'DOCYYYYMMDDXXX' 형태의 주문번호
    private String docno;

    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "ITEM001") // 예시: 'ITEM001' 또는 'SKU12345'와 같은 상품 코드
    private String sku;

    /**
     * 상품명
     */
    @Schema(description = "상품명", example = "사과") // 예시: '사과' 또는 '고급 노트북'과 같은 상품명
    private String skuname;

    /**
     * 식별번호 유무
     */
    @Schema(description = "식별번호 유무", example = "Y") // 예시: 'Y' 또는 'N' (Yes/No)
    private String serialyn;

    /**
     * 조정수량
     */
    @Schema(description = "조정수량", example = "10.0") // 예시: 수량 (소수점 포함 가능)
    private Double confirmqty;

    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA") // 예시: 'EA', 'BOX', 'KG'
    private String uom;

    /**
     * 평균중량
     */
    @Schema(description = "평균중량", example = "1.5") // 예시: 평균 중량 (소수점 포함 가능)
    private Double avgweight;

    /**
     * 환산박스
     */
    @Schema(description = "환산박스", example = "2.5") // 예시: 환산된 박스 수량 (소수점 포함 가능)
    private Double calbox;

    /**
     * 실박스예정
     */
    @Schema(description = "실박스예정", example = "5.0") // 예시: 실제 박스 예정 수량 (소수점 포함 가능)
    private Double realorderbox;

    /**
     * 실박스확정
     */
    @Schema(description = "실박스확정", example = "4.0") // 예시: 실제 박스 확정 수량 (소수점 포함 가능)
    private Double realcfmbox;

    /**
     * 발생사유
     */
    @Schema(description = "발생사유", example = "파손") // 예시: '파손', '분실' 등 발생 사유
    private String reasoncode;

    /**
     * 귀책
     */
    @Schema(description = "귀책", example = "물류팀") // 예시: '물류팀', '판매자' 등 귀책 부서/주체
    private String other01;

    /**
     * 물류귀책배부
     */
    @Schema(description = "물류귀책배부", example = "창고") // 예시: '창고', '운송' 등 물류 귀책 배부
    private String other05;

    /**
     * 증감여부
     */
    @Schema(description = "증감여부", example = "증가") // 예시: '증가', '감소'
    private String iotype;

    /**
     * 비고 (사유)
     */
    @Schema(description = "비고 (사유)", example = "특이사항 없음") // 예시: 추가 메모
    private String memo2;

    /**
     * 생성인
     */
    @Schema(description = "생성인", example = "") // 예시: 구체적인 사유
    private String addwho;

    /**
     * 등록일자
     */
    @Schema(description = "등록일자", example = "2025-05-22") // 예시: 'YYYY-MM-DD' 형식의 등록일자
    private String adddate;

    /**
     * 생성시간
     */
    @Schema(description = "생성시간", example = "14:30:00") // 예시: 'HH24:MI:SS' 형식의 생성시간
    private String addtime;


    /**
     * 재고위치
     */
    @Schema(description = "재고위치", example = "정상") // 예시: '정상', '불량'
    private String stocktype;

}
