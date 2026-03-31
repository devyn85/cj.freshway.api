package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.18 
 * @description : 광역입고현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.18 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "광역입고현황 목록 결과")
public class DpInplanSTOResDto {
	/**
     * 구매유형
     */
    @Schema(description = "구매유형", example = "일반구매")
    private String ordertypeName;
    /**
     * 광역입고일자
     */
    @Schema(description = "광역입고일자", example = "2025-06-18")
    private String slipdt;
    /**
     * 광역주문번호
     */
    @Schema(description = "광역주문번호", example = "DOC00000001")
    private String docno;
    /**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "DC001")
    private String fromDccode;
    /**
     * 물류센터명
     */
    @Schema(description = "물류센터명", example = "강남물류센터")
    private String fromDcname;
    /**
     * 창고
     */
    @Schema(description = "창고", example = "WH001")
    private String fromCustkey;
    /**
     * 창고명
     */
    @Schema(description = "창고명", example = "본사창고")
    private String fromCustname;
    /**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "DC002")
    private String toDccode;
    /**
     * 물류센터명
     */
    @Schema(description = "물류센터명", example = "강북물류센터")
    private String toDcname;
    /**
     * 창고
     */
    @Schema(description = "창고", example = "WH002")
    private String toCustkey;
    /**
     * 창고명
     */
    @Schema(description = "창고명", example = "지점창고")
    private String toCustname;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "완료")
    private String statusName;
    /**
     * 이체유형
     */
    @Schema(description = "이체유형", example = "유상사급")
    private String stotype;
    /**
     * 메모
     */
    @Schema(description = "메모", example = "긴급 주문")
    private String memo1;
    /**
     * 생성인
     */
    @Schema(description = "생성인", example = "홍길동")
    private String addwho;
    /**
     * 등록일자
     */
    @Schema(description = "등록일자", example = "2025-06-18 10:00:00")
    private String adddate;
    /**
     * 최종변경자
     */
    @Schema(description = "최종변경자", example = "김철수")
    private String editwho;
    /**
     * 최종변경시간
     */
    @Schema(description = "최종변경시간", example = "2025-06-18 11:00:00")
    private String editdate;
    /**
     * DOCDT
     */
    @Schema(description = "DOCDT", example = "2025-06-18")
    private String docdt;
    /**
     * ORDERTYPE
     */
    @Schema(description = "ORDERTYPE", example = "PO")
    private String ordertype;
    /**
     * FROM_ORGANIZE
     */
    @Schema(description = "FROM_ORGANIZE", example = "ORG001")
    private String fromOrganize;
    /**
     * TO_ORGANIZE
     */
    @Schema(description = "TO_ORGANIZE", example = "ORG002")
    private String toOrganize;
    /**
     * STATUS
     */
    @Schema(description = "STATUS", example = "90")
    private String status;
    /**
     * STORERKEY
     */
    @Schema(description = "STORERKEY", example = "STORER01")
    private String storerkey;
    /**
     * DOCTYPE
     */
    @Schema(description = "DOCTYPE", example = "GR")
    private String doctype;
    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
