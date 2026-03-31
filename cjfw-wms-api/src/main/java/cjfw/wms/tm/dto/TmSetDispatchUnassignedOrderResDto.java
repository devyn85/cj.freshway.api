package cjfw.wms.tm.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import cjfw.core.utility.StringUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import static cjfw.wms.tm.controller.TmNewEngineDataMapper.SEOUL_ZONE_ID;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.15 
 * @description : 배차 미할당 주문 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배차 미할당 주문 응답 DTO")
public class TmSetDispatchUnassignedOrderResDto {
	
	/** ID */
	@Schema(description = "작업/주문 ID")
	private String id;
	
	@Schema(description = "식별 ID")
	private String uniqueId;
	
	/** 위치 좌표 */
	@Schema(description = "위치 좌표 [경도, 위도]")
	private List<Double> location;
	
	/** 설명 */
	@Schema(description = "단계 설명")
	private String description;

	/** 위치 인덱스 */
	@Schema(description = "위치 인덱스")
	private Integer locationIdx;
	
	/** 서비스 시간 */
	@Schema(description = "서비스 시간 (초)")
	private Integer service;
	
	/** 예상 도착시간 정보 HH:MM */
	@Schema(description = "예상 도착시간")
	private String expectedArrivalTime;

	@Schema(description = "고객사코드")
	private String storerkey;

	@Schema(description = "거래처유형")
	private String custType;

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

	@Schema(description = "고정 차량")
	private String defCarno;

	@Schema(description = "현재 배차된 차량")
	private String carno;

	@Schema(description = "반품여부 [TODO]", example = "car001")
	private String returnYn;
	
	@Schema(description = "실착지 주소", example = "강남")
	private String custAddress;
	
	@Schema(description = "체적", example = "10")
	private String cube;
	
	@Schema(description = "중량", example = "10")
	private String weight;
	
	@Schema(description = "수량", example = "10")
	private String orderQty;
	
	@Schema(description = "전표라인", nullable = true)
	private String slipline;
	
	@Schema(description = "전표일자", nullable = true)
	private String slipdt;
	
	@Schema(description = "전표번호", nullable = true)
	private String slipno;
	
	@Schema(description = "분할배송 Y/N")
	private String splitDeliveryYn;
	
	@Schema(description = "분할배송 번호")
	private String splitDeliverySeq;
	
	@Schema(description = "배송유형", example = "1")
	private String tmDeliveryType;

    @Schema(description = "로케이션경도", example = "127.385509102457")
    private Double locationLng;

    @Schema(description = "로케이션위도", example = "36.3433156081082")
    private Double locationLat;

    @Schema(description = "비밀번호유형")
    private String passwordType;

    @Schema(description = "비밀번호유형 코드")
    private String passwordTypeCd;

    @Schema(description = "현재 배차된 POP")
    private String pop;

    @Schema(description = "고정 POP")
    private String defPop;

    @Schema(description = "대표 POP (고객 마스터 기본 POP)")
    private String basePop;

    @Schema(description = "요청otdFrom시간")
    private String otdTimeFrom;

    @Schema(description = "요청otdTo시간")
    private String otdTimeTo;

    @Schema(description = "전용차량 목록")
    private String dedicatedCarnoList;

    @Schema(description = "전용차량 차량유형")
    private String dedicatedCustCarType;

    @Schema(description = "배송금지차량 목록")
    private String blockedCarnoList;

    @Schema(description = "배송금지차량 차량유형")
    private String blockedCustCarType;

    public void createUniqueId() {
		this.uniqueId = this.id + "-" + "unassigned" + "-" + StringUtil.nvl(this.slipline, "null") + "-" + StringUtil.nvl(this.slipno, "null") + "-" + StringUtil.nvl(this.splitDeliverySeq, "null");
	}

    // ordTime 생성
    public List<Long> getOtdTime(String deliveryDate) {
        long min = 0L;
        long max = Long.MAX_VALUE;
        LocalDateTime from = null, to = null;
        if (!ObjectUtils.isEmpty(otdTimeFrom)) {
            from = convertFormat(deliveryDate + otdTimeFrom);
            min = from.atZone(SEOUL_ZONE_ID).toEpochSecond();
        }
        if (!ObjectUtils.isEmpty(otdTimeTo)) {
            to = convertFormat(deliveryDate + otdTimeTo);
            max = to.atZone(SEOUL_ZONE_ID).toEpochSecond();
        }

        if (from != null && to != null && !to.isAfter(from)) {
            from = from.minusDays(1);
            min = from.atZone(SEOUL_ZONE_ID).toEpochSecond();
        }

        return Arrays.asList(min, max);
    }

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHH:mm");
    private LocalDateTime convertFormat(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME_FORMATTER);
    }

}
