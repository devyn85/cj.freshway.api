package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.21 
 * @description : 출고확정처리 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.21 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고확정처리 목록 결과")
public class WdShipmentResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2024-01-01")
    private String slipdt;
    /**
     * 배송그룹
     */
    @Schema(description = "배송그룹", example = "CJ대한통운")
    private String courier;
    /**
     * POP번호
     */
    @Schema(description = "POP번호", example = "12345")
    private String deliverygroup;
    /**
     * 회차
     */
    @Schema(description = "회차", example = "1")
    private String priority;
    /**
     * 차량번호
     */
    @Schema(description = "차량번호", example = "12가1234")
    private String carno;
    /**
     * 기사명
     */
    @Schema(description = "기사명", example = "홍길동")
    private String drivername;
    /**
     * 출차시간
     */
    @Schema(description = "출차시간", example = "2024-01-01 10:00:00")
    private String dcparturedt;
    /**
     * 주문수량
     */
    @Schema(description = "주문수량", example = "100")
    private BigDecimal orderqty;
    /**
     * 상차완료량
     */
    @Schema(description = "상차완료량", example = "90")
    private BigDecimal loadcmpqty;
    /**
     * 상차진행률
     */
    @Schema(description = "상차진행률", example = "90.00")
    private BigDecimal loadrate;
    /**
     * 상차진행률(%)
     */
    @Schema(description = "상차진행률(%)", example = "90.00")
    private String loadrateText;
    /**
     * 하차완료량
     */
    @Schema(description = "하차완료량", example = "80")
    private BigDecimal unloadcmpqty;
    /**
     * 하차진행률
     */
    @Schema(description = "하차진행률", example = "80.00")
    private BigDecimal unloadrate;
    /**
     * 하차진행률(%)
     */
    @Schema(description = "하차진행률(%)", example = "80.00")
    private String unloadrateText;
    /**
     * 결품수량
     */
    @Schema(description = "결품수량", example = "10")
    private BigDecimal shortageqty;
    /**
     * 결품율
     */
    @Schema(description = "결품율", example = "10.00")
    private BigDecimal shortagerate;
    /**
     * 결품율(%)
     */
    @Schema(description = "결품율(%)", example = "10.00")
    private String shortagerateText;
    /**
     * 강제완료량
     */
    @Schema(description = "강제완료량", example = "5")
    private BigDecimal forcecmpqty;
    /**
     * 강제완료률
     */
    @Schema(description = "강제완료률", example = "5.00")
    private BigDecimal forcecmprate;
    /**
     * 강제완료률(%)
     */
    @Schema(description = "강제완료률(%)", example = "5.00")
    private String forcecmprateText;
    /**
     * 미확정수량
     */
    @Schema(description = "미확정수량", example = "20")
    private BigDecimal unconfirmqty;
    /**
     * 확정수량
     */
    @Schema(description = "확정수량", example = "70")
    private BigDecimal confirmqty;
    /**
     * 확정율
     */
    @Schema(description = "확정율", example = "70.00")
    private BigDecimal confirmrate;
    /**
     * 확정율(%)
     */
    @Schema(description = "확정율(%)", example = "70.00")
    private String confirmrateText;
    /**
     * 검수완료여부
     */
    @Schema(description = "검수완료여부", example = "Y")
    private String forcecmpyn;
    /**
     * */
    @Schema(description = "", example = "KEY001")
    private String storerkey;
    /**
     * */
    @Schema(description = "", example = "DC01")
    private String dccode;
    /**
     * */
    @Schema(description = "", example = "DO")
    private String doctype;
    /**
     * */
    @Schema(description = "", example = "2024-01-02")
    private String deliverydt;
    /**
     * */
    @Schema(description = "", example = "COMPLETED")
    private String loadstatus;
    /**
     * */
    @Schema(description = "", example = "100")
    private BigDecimal loadplanqty;
    /**
     * 작업유형
     * */
    @Schema(description = "작업유형", example = "AL")
    private String tasktype;
}
