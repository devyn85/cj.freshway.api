package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.20 
 * @description : 센터정책관리 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsCenterPolicyMngDetailResDto {
	
	/** 고객사코드 */
	@Schema(description = "고객사코드", nullable = false, example = "FW00")
	private String storerkey;

	/** 코드리스트 */
	@Schema(description = "코드리스트", example = "PARTSD")
	private String codelist;
	
	/** 기본코드값 */
	@Schema(description = "기본코드값", example = "430293")
	private String commCd;

	/** 기본코드설명 */
	@Schema(description = "기본코드설명", example = "소장")
	private String commDescr;
		
	/** 코드설명 */
	@Schema(description = "코드설명", example = "")
	private String codedescr;
	
	/** 데이터값2 */
	@Schema(description = "데이터값2", example = "")
	private String data2;
	
	/** 데이터값3 */
	@Schema(description = "데이터값3", example = "")
	private String data3;
	
	/** 상태 */
	@Schema(description = "상태", example = "90")
	private String status;

	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "N")
	private String delYn;
	
	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;
	
	/** 정책코드 */
	@Schema(description = "정책코드")
	private String plcycode;
	
	/** 적용여부 */
	@Schema(description = "적용여부")
	private String applyYn;
	
	/** 단일선택여부 */
	@Schema(description = "단일선택여부")
	private String slctYn;
	
	/** 연관공통코드 */
	@Schema(description = "연관공통코드", example = "")
	private String refCommCd;
		
	/** 연관공통코드명 */
	@Schema(description = "연관공통코드명")
	private String refCommDescr;

	/** 연관코드종류 */
	@Schema(description = "연관코드종류", example = "")
	private String refCdType;
    
    /** 연관설정값 */
    @Schema(description = "연관설정값")
    private String refCnf;
    
    /** 공통상세코드 */
    @Schema(description = "공통상세코드")
    private String commDtlCd;
    
    /** 공통상세코드명 */
    @Schema(description = "공통상세코드명")
    private String commDtlDescr;
    
    /** 코드 유효성 */
    @Schema(description = "코드 유효성")
    private Integer codeChk;
    
    /** 코드 유효성 */
    @Schema(description = "코드 유효성")
    private Integer detailCodeChk;
    
    /** 코드 유효성 */
    @Schema(description = "코드 유효성")
    private Integer refCodeChk;

}