package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.StringReader;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.01 
 * @description : 계획 경로 저장 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.01 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "계획 경로 저장 요청 DTO")
public class TmSavePlanRouteReqDto extends CommonProcedureDto {
	
	@Schema(description = "배송일자")
	private String deliveryDate;
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "실착지 키")
	private String truthCustkey;
	
	@Schema(description = "폴리라인")
	private StringReader geometry;
	
	@Schema(description = "예상 도착시간")
	private String eta;
	
	@Schema(description = "운행 거리")
	private String driveDistance;
	
	@Schema(description = "거래처 유형")
	private String custType;
	
	@Schema(description = "고객사 코드")
	private String storerkey;
	
	@Schema(description = "운행 시간")
	private String driveTime;
	
	@Schema(description = "차량 번호")
	private String carno;
	
	@Schema(description = "조차 코드")
	private String outGroupCd;
	
	@Schema(description = "배차 상태")
	private String status;
	
	@Schema(description = "배차 유형")
	private String deliveryType;
	
	@Schema(description = "분할배송 Y/N")
	private String splitDeliveryYn;
	
	@Schema(description = "분할배송 번호")
	private String splitDeliverySeq;
	
	@Schema(description = "전표일자")
	private String slipdt;
	
	@Schema(description = "전표번호")
	private String slipno;
	
	@Schema(description = "전표라인")
	private String slipline;

	@Schema(description = "주문수량")
	private String orderQty;

	@Schema(description = "체적")
	private String cube;

	@Schema(description = "중량")
	private String weight;

	@Schema(description = "회차 순번")
	private int roundSeq;

	@Schema(description = "배송순위")
    private int deliveryPriority;
	
    @Schema(description = "배송우선순위")
    private int dlvDeliveryPriority;

    @Schema(description = "배송계획시간(ETA)")
    private String deliveryPlanDt;

    @Schema(description = "차량의 계약유형")
    private String contractType;

    @Schema(description = "배차 배송유형")
    private String tmDeliveryType;

    @Schema(description = "tc센터코드")
    private String tcDcCode;

    @Schema(description = "입고전표번호")
    private String docno;
}
