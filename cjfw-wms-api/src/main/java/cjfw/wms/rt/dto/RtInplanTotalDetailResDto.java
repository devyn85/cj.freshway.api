package cjfw.wms.rt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.05 
 * @description : 반품진행현황 클레임내역 조회 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "반품진행현황 클레임내역 조회 결과")
public class RtInplanTotalDetailResDto {
	/**
     * 클레임번호
     */
    @Schema(description = "클레임번호", example = "C20250605-001")
    private String sapclaimno;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "컴퓨터")
    private String skuname;
    /**
     * 세부내역
     */
    @Schema(description = "세부내역", example = "고객 변심으로 인한 반품")
    private String memo;
    /**
     * 작성자
     */
    @Schema(description = "작성자", example = "홍길동")
    private String writer;
    /**
     * 작성일자
     */
    @Schema(description = "작성일자", example = "20250605")
    private String writedate;
    /**
     * 작성시간
     */
    @Schema(description = "작성시간", example = "103000")
    private String writetime;

    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
