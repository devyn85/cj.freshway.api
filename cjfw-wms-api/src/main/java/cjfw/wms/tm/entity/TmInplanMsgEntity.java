package cjfw.wms.tm.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Copyright 2025. CJ Freshway Co. all rights reserved.
* @author : ParkJinWoo 
* @date : 2025.08.07 
* @description : 배송전달사항 Entity 기능을 구현한 Entity Class 
* @issues : 
* ----------------------------------------------------------- 
* DATE AUTHOR MAJOR_ISSUE 
* ----------------------------------------------------------- 
* 2025.08.07 ParkJinWoo 생성
*/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송전달사항 Entity") 
public class TmInplanMsgEntity extends CommonDto {	
	/** 데이터번호 */
    @Schema(description = "데이터번호")
    private Long serialKey;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docdt;

    /** 문서유형 */
    @Schema(description = "문서유형")
    private String docType;

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custkey;

    /** 거래처명 */
    @Schema(description = "거래처명")
    private String custName;

    /** 영업그룹 */
    @Schema(description = "영업그룹")
    private String saleGroup;

    /** 영업부서 */
    @Schema(description = "영업부서")
    private String saleDepartment;

    /** 고객그룹 */
    @Schema(description = "고객그룹")
    private String custGroup;

    /** 구매번호 */
    @Schema(description = "구매번호")
    private String poType;

    /** 클레임분류 */
    @Schema(description = "클레임분류")
    private String claimType;

    /** 배송메시지 */
    @Schema(description = "배송메시지")
    private String deliveryMemo;

    /** 차량번호전체 */
    @Schema(description = "차량번호전체")
    private String carno;

    /** SAP등록자 */
    @Schema(description = "SAP등록자")
    private String sapaddwho;

    /** 상태 */
    @Schema(description = "상태")
    private String status;

    /** 삭제여부 */
    @Schema(description = "삭제여부")
    private String delYn;

    /** 데이터흐름제어 */
    @Schema(description = "데이터흐름제어")
    private String trafficCop;

    /** 아카이브제어 */
    @Schema(description = "아카이브제어")
    private String archiveCop;

    /** 처리에러코드 */
    @Schema(description = "처리에러코드")
    private String errCode;

    /** 에러메시지 */
    @Schema(description = "에러메시지")
    private String errMsg;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간")
    private String addDate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간")
    private String editDate;

    /** 최초등록자 */
    @Schema(description = "최초등록자")
    private String addWho;

    /** 최종변경자 */
    @Schema(description = "최종변경자")
    private String editWho;

    /** 순번 */
    @Schema(description = "순번")
    private Integer seq;

    /** 플랜트 */
    @Schema(description = "플랜트")
    private String plant;

    /** 기타정보 1 */
    @Schema(description = "기타정보 1")
    private String other01;

    /** 기타정보 2 */
    @Schema(description = "기타정보 2")
    private String other02;

    /** 기타정보 3 */
    @Schema(description = "기타정보 3")
    private String other03;

    /** 기타정보 4 */
    @Schema(description = "기타정보 4")
    private String other04;

    /** 기타정보 5 */
    @Schema(description = "기타정보 5")
    private String other05;

    /** 중요도 */
    @Schema(description = "중요도")
    private String importantType;

    /** SAP등록시간 */
    @Schema(description = "SAP등록시간")
    private String sapAddDate;

    /** 시작전달일시 */
    @Schema(description = "시작전달일시")
    private String fromInfoDt;

    /** 종료전달일시 */
    @Schema(description = "종료전달일시")
    private String toInfoDt;
}
