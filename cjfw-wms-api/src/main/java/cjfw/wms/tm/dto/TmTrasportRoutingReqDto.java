package cjfw.wms.tm.dto;

import java.util.List;

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
 * @description : 수송경로관리 기능을 구현한 REQ DTO Class 
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
@Schema(description = "수송경로관리 조회 reqDto")
public class TmTrasportRoutingReqDto extends CommonDto {
    
     /** 저장리스트 */
    @Schema(description = "저장리스트")
    List<TmTrasportRoutingResDto> saveList;
    
    /** 노선번호 */
    @Schema(description = "노선번호")
    private Long routeSerialKey;
    
    /** 출발센터 */
    @Schema(description = "출발센터")
    private String fromDcCode;

    /** 출발창고 */
    @Schema(description = "출발창고")
    private String fromOrganize;
    
    /** 도착센터코드 */
    @Schema(description = "도착센터코드")
    private String toDcCode;
    
    /** 도착창고 */
    @Schema(description = "도착창고")
    private String toOrganize;
    
    /** 시작일자 */
    @Schema(description = "시작일자")
    private String fromDate;

    /** 종료일자 */
    @Schema(description = "종료일자")
    private String toDate;

}