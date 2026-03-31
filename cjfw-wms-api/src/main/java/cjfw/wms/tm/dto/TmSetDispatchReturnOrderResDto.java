package cjfw.wms.tm.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cjfw.core.exception.UserHandleException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.05 
 * @description : 착지 기준 반품 주문 목록 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.05 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "착지 기준 반품 주문 목록 응답 DTO")
public class TmSetDispatchReturnOrderResDto {
	
	/** ID */
	@Schema(description = "작업/주문 ID")
	private String id;
	
	@Schema(description = "식별 ID")
	private String uniqueId;
	
	/** 위치 좌표 */
	@Schema(description = "위치 좌표 [경도, 위도]")
	private List<Double> location;

	@Schema(description = "고객사코드")
	private String storerkey;

	@Schema(description = "거래처유형")
	private String custType;
	
	@Schema(description = "배송유형")
	private String tmDeliveryType;

	@Schema(description = "고객처명", example = "고객")
	private String custName;

	@Schema(description = "대면검수 YN", example = "Y")
	private String faceInspect;

	@Schema(description = "특수조건 YN", example = "Y")
	private String specialConditionYn;

	@Schema(description = "클레임 YN", example = "Y")
	private String claimYn;

	@Schema(description = "OTD 시작시간", example = "08:00")
	private String reqdlvtime1From;

	@Schema(description = "OTD 종료시간", example = "10:00")
	private String reqdlvtime1To;

	@Schema(description = "키 유형", example = "비밀번호")
	private String keyCustType;

	@Schema(description = "본POP [TODO]", example = "pop1")
	private String defDistrictCode;

	@Schema(description = "본차량 [TODO]", example = "car001")
	private String defCarno;
	
	@Schema(description = "실착지 주소", example = "강남")
	private String custAddress;
	
	@Schema(description = "체적", example = "10")
	private String cube;
	
	@Schema(description = "중량", example = "10")
	private String weight;
	
	@Schema(description = "수량", example = "10")
	private String orderQty;

    @Schema(description = "거래처키")
    private String custKey;

    @Schema(description = "위도")
    private String latitude;

    @Schema(description = "경도")
    private String longitude;

    @Schema(description = "작업시간")
    private String workTime;

    @Schema(description = "비밀번호유형")
    private String passwordType;

    @Schema(description = "비밀번호유형 코드")
    private String passwordTypeCd;

    @Schema(description = "전표번호")
    private String slipdt;

    @Schema(description = "요청otdFrom시간")
    private String otdTimeFrom;

    @Schema(description = "요청otdTo시간")
    private String otdTimeTo;

    /** 서비스 시간 */
    @Schema(description = "서비스 시간 (초)")
    private Integer service;

    public List<Double> getLocation() {
        if (ObjectUtils.isEmpty(longitude) || ObjectUtils.isEmpty(latitude)) {
            throw new UserHandleException("실착지 거래처 좌표가 없습니다. " + custKey + ":" + custName);
        }
        return new ArrayList<>(Arrays.asList(Double.parseDouble(longitude), Double.parseDouble(latitude)));
    }

    public void createUniqueId() {
		this.uniqueId = this.id + "-" + slipdt + "-return";
	}

    public static TmSetDispatchTruthCustResDto to(TmSetDispatchReturnOrderResDto rtOrder) {
		if (rtOrder == null) return null;
		
        return TmSetDispatchTruthCustResDto.builder()
            .truthCustKey(rtOrder.getId())
            .address(rtOrder.getCustAddress())
            .defCarno(rtOrder.getDefCarno())
            .latitude(rtOrder.getLatitude())
            .longitude(rtOrder.getLongitude())
            .custName(rtOrder.getCustName())
            .orderQty(rtOrder.getOrderQty())
            .cube(rtOrder.getCube())
            .weight(rtOrder.getWeight())
            .passwordType(rtOrder.getPasswordType())
            .reqdlvtime1From(rtOrder.getReqdlvtime1From())
            .faceInspect(rtOrder.getFaceInspect())
            .reqdlvtime1To(rtOrder.getReqdlvtime1To())
            .claimYn(rtOrder.getClaimYn())
            .workTime(rtOrder.getWorkTime())
            .tmDeliveryType(rtOrder.getTmDeliveryType())
            .returnYn("Y")
			.location(rtOrder.getLocation())
            .slipdt(rtOrder.getSlipdt())
            .custType(rtOrder.getCustType())
            .storerkey(rtOrder.getStorerkey())
            .otdTimeFrom(rtOrder.getOtdTimeFrom())
            .otdTimeTo(rtOrder.getOtdTimeTo())
            .build();
    }	
}
