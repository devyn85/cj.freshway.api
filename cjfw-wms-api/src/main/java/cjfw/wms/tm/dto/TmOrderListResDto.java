package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 정종문 (loters@cj.net)
 * @date : 2025.08.20
 * @description : TM 주문 목록 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.20 정종문 (loters@cj.net) 생성
 * 2025.08.20 정종문 (loters@cj.net) 프로젝트 패턴에 맞게 재구성
 * 2025.08.26 정종문 (loters@cj.net) 새로운 쿼리 구조에 맞게 필드 재구성
 * 2025.08.26 정종문 (loters@cj.net) 균형적 개선: 기본정보, 비즈니스정보, 실착지정보 추가
 * 2025.08.26 정종문 (loters@cj.net) MS_CUSTDLVINFO TRUTHADDRESS 기반으로 변경, 좌표 정보 추가 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TM 주문 목록 조회 응답 DTO")
public class TmOrderListResDto {

    @Schema(description = "창고코드")
    private String storerkey;

    @Schema(description = "물류센터코드")
    private String dccode;

    @Schema(description = "물류센터명")
    private String dcname;

    @Schema(description = "배송일자")
    private String deliverydate;

    @Schema(description = "배송유형")
    private String tmDeliverytype;

    @Schema(description = "배송유형명")
    private String tmDeliverytypeNm;

    @Schema(description = "주문유형코드")
    private String ordertype;

    @Schema(description = "주문유형")
    private String ordertypeNm;

    @Schema(description = "권역그룹")
    private String dlvgroupId;

    @Schema(description = "권역그룹명")
    private String dlvgroupNm;

    @Schema(description = "권역")
    private String dlvdistrictId;

    @Schema(description = "권역명")
    private String dlvdistrictNm;

    @Schema(description = "popno")
    private String popno;

    @Schema(description = "차량번호")
    private String carno;

    @Schema(description = "행정동코드")
    private String hjdongCd;

    @Schema(description = "클레임 여부")
    private String claimYn;

    @Schema(description = "TO 관리처")
    private String toCusttype;

    @Schema(description = "TO 관리처코드")
    private String toCustkey;

    @Schema(description = "TO 관리처명")
    private String toCustname;

    @Schema(description = "TO 관리처 주소")
    private String toCustAddress;

    @Schema(description = "TO 실착지코드")
    private String toTruthcustkey;

    @Schema(description = "TO 실착지명")
    private String toTruthcustname;

    @Schema(description = "TO 실착지 주소")
    private String toTruthcustAddress;

    @Schema(description = "좌표유무")
    private String coordinateYn;

    @Schema(description = "주문수량")
    private String orderQty;

    @Schema(description = "중량")
    private String orderWeight;

    @Schema(description = "체적")
    private String orderCube;

    @Schema(description = "위도", example = "65.123456")
    private BigDecimal toLatitude;

    @Schema(description = "경도", example = "123.123456")
    private BigDecimal toLongitude;

    /** 반경 */
    @Schema(description = "반경", example = "100")
    private BigDecimal toRadius;

    /** 체류시간 */
    @Schema(description = "체류시간", example = "100")
    private BigDecimal toStytime;

    @Schema(description = "고객자차 여부", example = "Y")
    private String selfTypeYn;

    @Schema(description = "실착지코드 존재 유무 - 실착지 관리처 배송정보 등록 상태 검증용", example = "Y")
    private String truthcustkeyExistsYn;

    @Schema(description = "차량번호 여부")
    private String carnoYn;

    @Schema(description = "DC 위도", example = "65.123456")
    private BigDecimal dcLatitude;

    @Schema(description = "DC 경도", example = "123.123456")
    private BigDecimal dcLongitude;

    @Schema(description = "배송요청시간2 FROM")
    private String reqdlvtime2From;

    @Schema(description = "배송요청시간2 TO")
    private String reqdlvtime2To;

    @Schema(description = "배송요청시간2 유효 여부 (REQDLVTIME2_FROM≠TO 이고 T2.DATA1/T3.DATA1이 TO 미만일 때 Y)")
    private String otdTimeYn;
}
