package cjfw.wms.ms.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : YeoSeungCheol (pw6375@cj.net)
 * @date        : 2025.07.14
 * @description : 본점별브랜드마스터 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.14        YeoSeungCheol		pw6375@cj.net      생성
 */
@Data
@Schema
@EqualsAndHashCode(callSuper=false)
public class MsCustBrandEntity extends CommonDto{
	@Schema(description = "시리얼키 (PK)", requiredMode = Schema.RequiredMode.REQUIRED)
	private BigDecimal serialKey;
	
	@Schema(description = "고객사코드")
    private String storerKey;
	
	@Schema(description = "거래처코드")
    private String custKey;
	
	@Schema(description = "본점명")
	private String custName;
	
	@Schema(description = "거래처 유형")
	private String custType;
	
	@Schema(description = "브랜드 코드")
	private String brandCode;
	
	@Schema(description = "브랜드 이름")
	private String brandName;
	
	@Schema(description = "FC브랜드코드")
	private String reference01;
	
	@Schema(description = "FC브랜드명")
	private String reference02;
	
	@Schema(description = "기타정보03")
	private String reference03;
	
	@Schema(description = "최초등록시간 (자동 생성)")
	private String addDate;
	
	@Schema(description = "최종변경시간 (자동 갱신)")
	private String editDate;
	
    @Schema(description = "최초등록자", defaultValue = "SYSTEM", maxLength = 24)
    private String addWho;
    
    @Schema(description = "최종변경자", defaultValue = "SYSTEM", maxLength = 24)
    private String editWho;    
    
	@Schema(description = "프로시저 실행 성공여부", example = "")
	private Integer success;
	
	@Schema(description = "프로시저 실행 에러코드", example = "")
	private Integer err;
	
	@Schema(description = "프로시저 실행 에러메시지", example = "")
	private String errmsg;
}
