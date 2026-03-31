package cjfw.wms.tm.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkYoSep 
 * @date : 2025.10.15
 * @description : 수송경로관리 Entity 기능을 구현한 Entity Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 ParkYoSep 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "휴일관리 Entity") 
public class TmTrasportRoutingEntity extends CommonDto {
	
	 /** 일련번호 */
    @Schema(description = "일련번호")
    private Long serialKey;
    
    /** 노선번호 */
    @Schema(description = "노선번호")
    private Long routeSerialKey;

    /** 운송사 */
    @Schema(description = "운송사")
    private String courier;
    
    /** 운송사 명 */
    @Schema(description = "운송사명")
    private String courierNm;

    /** 노선명 */
    @Schema(description = "노선명")
    private String routeNm;

    /** 출발센터 */
    @Schema(description = "출발센터")
    private String fromDcCode;

    /** 출발창고 */
    @Schema(description = "출발창고")
    private String fromOrganize;

    /** 출발창고명*/
    @Schema(description = "출발창고명")
    private String fromOrganizeName;

    /** 도착센터 */
    @Schema(description = "도착센터")
    private String toDcCode;

    /** 도착창고 */
    @Schema(description = "도착창고")
    private String toOrganize;

    /** 도착창고명 */
    @Schema(description = "도착창고명")
    private String toOrganizeName;
    
    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;

    /** 저장구분 */
    @Schema(description = "저장구분")
    private String storageType;

    /** 경유여부 */
    @Schema(description = "경유여부")
    private String routeYn;

    /** 톤수 */
    @Schema(description = "톤수")
    private String ton;

    /** 단가 */
    @Schema(description = "단가")
    private Long price;

    /** 비고 */
    @Schema(description = "비고")
    private String rmk;

    /** 시작일자 */
    @Schema(description = "시작일자")
    private String fromDate;

    /** 종료일자 */
    @Schema(description = "종료일자")
    private String toDate;

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

    /** 등록자명 */
    @Schema(description = "등록자명")
    private String addWhoName;

    /** 수정자명 */
    @Schema(description = "수정자명")
    private String editWhoName; 
}
