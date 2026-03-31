package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.18 
 * @description : 보충생성-상품 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "보충생성-상품 결과")
public class StLocMoveRPCustResTab1SkuDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	@Schema(description = "물류센터", example = "물류센터명 예시")
    private String dcname; // AS DCNAME -> dcname (언더바 없으므로 소문자)
    @Schema(description = "상품코드", example = "SKU123")
    private String sku; // AS SKU -> sku (언더바 없으므로 소문자)
    @Schema(description = "상품명칭", example = "상품명 예시")
    private String skuname; // AS SKUNAME -> skuname (언더바 없으므로 소문자)
    @Schema(description = "MC", example = "MC 예시")
    private String mc; // AS MC -> mc (언더바 없으므로 소문자)
    @Schema(description = "저장조건", example = "저장조건 예시")
    private String storagetypename; // AS STORAGETYPENAME -> storagetypename (언더바 없으므로 소문자)
    @Schema(description = "단위", example = "EA")
    private String uom; // AS UOM -> uom (언더바 없으므로 소문자)
    @Schema(description = "재고ID", example = "STOCKID123")
    private String stockid; // AS STOCKID -> stockid (언더바 없으므로 소문자)
    @Schema(description = "로케이션종류", example = "로케이션종류 예시")
    private String loccategory; // AS LOCCATEGORY -> loccategory (언더바 없으므로 소문자)
    @Schema(description = "FROM 로케이션", example = "FROM_LOC_001")
    private String fromloc; // AS FROMLOC -> fromloc (언더바 없으므로 소문자)
    @Schema(description = "TO 로케이션", example = "TO_LOC_001")
    private String toloc; // AS TOLOC -> toloc (언더바 없으므로 소문자)
    @Schema(description = "박스입수", example = "10")
    private String qtyperbox; // AS QTYPERBOX -> qtyperbox (언더바 없으므로 소문자)
    @Schema(description = "BOX", example = "5")
    private String openqtyBox; // AS OPENQTY_BOX -> openqtyBox (언더바 있으므로 카멜 케이스)
    @Schema(description = "EA", example = "3")
    private String openqtyEa; // AS OPENQTY_EA -> openqtyEa (언더바 있으므로 카멜 케이스)
    @Schema(description = "재고속성", example = "재고속성 예시")
    private String stockgradename; // AS STOCKGRADENAME -> stockgradename (언더바 없으므로 소문자)
    @Schema(description = "BOX", example = "7")
    private String confirmqtyBox; // AS CONFIRMQTY_BOX -> confirmqtyBox (언더바 있으므로 카멜 케이스)
    @Schema(description = "EA", example = "2")
    private String confirmqtyEa; // AS CONFIRMQTY_EA -> confirmqtyEa (언더바 있으므로 카멜 케이스)
    @Schema(description = "기준일(소비,제조)", example = "20250716")
    private String lottable01; // AS LOTTABLE01 -> lottable01 (언더바 없으므로 소문자)
    @Schema(description = "소비기간(잔여/전체)", example = "30/90")
    private String durationTerm; // AS DURATION_TERM -> durationTerm (언더바 있으므로 카멜 케이스)
    @Schema(description = "발행여부", example = "Y")
    private String printynname; // AS PRINTYNNAME -> printynname (언더바 없으므로 소문자)
    @Schema(description = "작업상태", example = "완료")
    private String status; // AS STATUS -> status (언더바 없으므로 소문자)
    @Schema(description = "ASRS지시여부", example = "Y")
    private String ifFlag; // AS IF_FLAG -> ifFlag (언더바 있으므로 카멜 케이스)
    @Schema(description = "ASRS처리결과", example = "성공")
    private String ifSendFile; // AS IF_SEND_FILE -> ifSendFile (언더바 있으므로 카멜 케이스)
    @Schema(description = "전송시간", example = "2025-07-16 10:00:00")
    private String ifDate; // AS IF_DATE -> ifDate (언더바 있으므로 카멜 케이스)
    @Schema(description = "보충번호", example = "SUPPLNO001")
    private String supplno; // AS SUPPLNO -> supplno (언더바 없으므로 소문자)
    @Schema(description = "보충라인", example = "1")
    private String supplline; // AS SUPPLLINE -> supplline (언더바 없으므로 소문자)
    @Schema(description = "STORERKEY", example = "STORERKEY_EX")
    private String storerkey; // AS STORERKEY -> storerkey (언더바 없으므로 소문자)
    @Schema(description = "DCCODE", example = "DCCODE_EX")
    private String dccode; // AS DCCODE -> dccode (언더바 없으므로 소문자)
    @Schema(description = "ORGANIZE", example = "ORGANIZE_EX")
    private String organize; // AS ORGANIZE -> organize (언더바 없으므로 소문자)
    @Schema(description = "STORAGETYPE", example = "STORAGETYPE_EX")
    private String storagetype; // AS STORAGETYPE -> storagetype (언더바 없으므로 소문자)
    @Schema(description = "OPENQTY", example = "100")
    private String openqty; // AS OPENQTY -> openqty (언더바 없으므로 소문자)
    @Schema(description = "CONFIRMQTY", example = "80")
    private String confirmqty; // AS CONFIRMQTY -> confirmqty (언더바 없으므로 소문자)
    @Schema(description = "STOCKGRADE", example = "GOOD")
    private String stockgrade; // AS STOCKGRADE -> stockgrade (언더바 없으므로 소문자)
    @Schema(description = "DURATIONTYPE", example = "TYPE_A")
    private String durationtype; // AS DURATIONTYPE -> durationtype (언더바 없으므로 소문자)
    @Schema(description = "PRINTYN", example = "N")
    private String printyn; // AS PRINTYN -> printyn (언더바 없으므로 소문자)
    @Schema(description = "STATUSNAME", example = "STATUS_NAME_EX")
    private String statusname; // AS STATUSNAME -> statusname (언더바 없으므로 소문자)
    @Schema(description = "ORDERTYPE", example = "ORDER_TYPE_EX")
    private String ordertype; // AS ORDERTYPE -> ordertype (언더바 없으므로 소문자)
    @Schema(description = "SLIPDT", example = "20250716")
    private String slipdt; // AS SLIPDT -> slipdt (언더바 없으므로 소문자)
    @Schema(description = "SLIPNO", example = "SLIPNO_EX")
    private String slipno; // AS SLIPNO -> slipno (언더바 없으므로 소문자)
    @Schema(description = "SLIPLINE", example = "SLIPLINE_EX")
    private String slipline; // AS SLIPLINE -> slipline (언더바 없으므로 소문자)
    @Schema(description = "ASRSYN", example = "Y")
    private String asrsyn; // AS ASRSYN -> asrsyn (언더바 없으므로 소문자)
}
