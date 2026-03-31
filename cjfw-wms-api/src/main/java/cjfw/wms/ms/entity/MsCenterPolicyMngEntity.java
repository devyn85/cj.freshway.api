package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.08.04 
 * @description : 센터이체마스터 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MsCenterPolicyMngEntity extends CommonDto {
	
	/** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 정책코드 */
    @Schema(description = "정책코드")
    private String plcycode;

    /** 적용여부 */
    @Schema(description = "적용여부")
    private String applyYn;
    
    /** 정책설명 */
    @Schema(description = "정책설명")
    private String plcyDesc;

    /** 정책명 */
    @Schema(description = "정책명")
    private String plcyNm;
    
	/** 공통코드 */
	@Schema(description = "공통코드", example = "")
	private String commCd;	
	
	/** 공통코드명 */
	@Schema(description = "공통코드명", example = "")
	private String commDescr;
	
	/** 상세코드 */
	@Schema(description = "상세코드", example = "")
	private String commDtlCd;
	
	/** 상세코드명 */
	@Schema(description = "상세코드명", example = "")
	private String commDtlDescr;
	
	/** 연관코드 */
	@Schema(description = "연관코드", example = "")
	private String refCommCd;	
	
	/** 연관코드명 */
	@Schema(description = "연관코드명", example = "")
	private String refCommDescr;
	
	/** 코드설명 */
	@Schema(description = "코드설명", example = "")
	private String codedescr;
	
	/** 단일선택여부 */
	@Schema(description = "단일선택여부")
	private String slctYn;
	
	/** 연관코드종류 */
	@Schema(description = "연관코드종류", example = "")
	private String refCdType;
	
	/** 연관설정값 */
    @Schema(description = "연관설정값")
    private String refCnf;
    
    /** serialkey */
	@Schema(description = "serialkey", example = "")
	private String serialkey;
	
	/** 멀티적용 */
	@Schema(description = "멀티적용", example = "N")
	private String multiLocYn;
	
	/** 성공 여부 */
    @Schema(description = "성공 여부", example = "")
    private Integer success;

    /** 에러 코드 */
    @Schema(description = "에러 코드", example = "")
    private Integer err;

    /** 에러 메시지 */
    @Schema(description = "에러 메시지", example = "")
    private String errmsg;
    
}