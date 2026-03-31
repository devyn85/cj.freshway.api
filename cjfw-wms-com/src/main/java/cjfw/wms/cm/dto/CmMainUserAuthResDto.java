package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.12 
 * @description : 사용자 접속 권한 정보 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.12 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "사용자 접속 권한 정보 응답")
public class CmMainUserAuthResDto {
	/** 창고코드 */
    @Schema(description = "창고코드", example = "2650")
    private String dccode;
    
    /** 센터명 */
    @Schema(description = "센터명", example = "[2650]동탄물류센터")
    private String dcname;
    
    /** 고객사코드 MS_COMPANY코드 */
    @Schema(description = "고객사코드 MS_COMPANY코드", example = "FW00")
    private String storerkey;
    
    /** 고객사명 */
    @Schema(description = "고객사명", example = "[FW00]씨제이프레시웨이(주)")
    private String storerName;
    
    /** 조직코드 */
    @Schema(description = "조직코드", example = "2650")
    private String organize;
    
    /** 조직명 */
    @Schema(description = "조직명", example = "[2650]동탄물류센터")
    private String organizeName;
    
    /** 창고코드 SAP 창고 혹은 별도의 창고 코드 */
    @Schema(description = "창고코드 SAP 창고 혹은 별도의 창고 코드", example = "1000")
    private String area;
    
    /** 창고명 */
    @Schema(description = "창고명", example = "[1000]일반구역")
    private String areaName;
    
    /** 접속권한 */
    @Schema(description = "접속권한", example = "05")
    private String authority;
    
    /** 기본표시FLOATMASK */
    @Schema(description = "기본표시FLOATMASK", example = "#,##0")
    private String floatmask;
    
    /** 정렬순서 */
    @Schema(description = "정렬순서", example = "1")
    private String arrord;
}