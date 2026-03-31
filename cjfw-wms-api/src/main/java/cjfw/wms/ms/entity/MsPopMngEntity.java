package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.18 
 * @description : 거래처별POP관리 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MsPopMngEntity extends CommonProcedureDto {

	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "")
	private String serialkey;
	
	/** 신규데이터번호 */
	@Schema(description = "신규데이터번호", example = "")
	private String newSerialkey;
	
	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String dccode;

	/** 거래처코드 */
	@Schema(description = "거래처코드", example = "")
	private String custkey;

	/** 차량번호 */
	@Schema(description = "차량번호", example = "")
	private String carno;

	/** POP 번호 */
	@Schema(description = "POP 번호", example = "")
	private String popno;

	/** 고객코드 */
	@Schema(description = "고객코드", example = "")
	private String storerkey;

	/** 거래처유형 */
	@Schema(description = "거래처유형", example = "")
	private String custtype;

	/** 적용시작일자 */
	@Schema(description = "적용시작일자", example = "")
	private String fromdate;
	
	/** 적용종료일자 */
	@Schema(description = "적용종료일자", example = "")
	private String todate;

	/** 롤테이너 번호 */
	@Schema(description = "롤테이너 번호", example = "")
	private String rolltainerNo;

	/** BCR 사용 여부 */
	@Schema(description = "BCR 사용 여부", example = "")
	private String bcrYn;

	/** 납품그룹(대량업로드리스트(excel)) */
    @Schema(description = "납품그룹(대량업로드리스트(excel))", example = "popno")
    private String deliverygroup;

    /** 참조값2 (대량업로드리스트(excel)) */
    @Schema(description = "참조값2(대량업로드리스트(excel))", example = "bcrYn")
    private String other02;

    /** 참조값1 (대량업로드리스트(excel))*/
    @Schema(description = "참조값1(대량업로드리스트(excel))", example = "rolltainerNo")
    private String other01;

    /** 납품일자 (대량업로드리스트(excel))*/
    @Schema(description = "납품일자(대량업로드리스트(excel))", example = "fromdate")
    private String deliverydt;

    /** 전표일자 (대량업로드리스트(excel))*/
    @Schema(description = "전표일자(대량업로드리스트(excel))", example = "fromdate")
    private String slipdt;

    /** 전표번호 (대량업로드리스트(excel)) */
    @Schema(description = "전표번호(대량업로드리스트(excel))", example = "fromdate")
    private String slipno;

    /** 전표유형 (대량업로드리스트(excel)) */
    @Schema(description = "전표유형(대량업로드리스트(excel))", example = "fromdate")
    private String sliptype;
    
    /** 적용기간 */
	@Schema(description = "적용기간", example = "")
	private String basedescr;
	
	/** 코드리스트 */
	@Schema(description = "코드리스트", example = "")
	private String codelist;
	
	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "")
	private String delYn;
	
	 /** 성공 여부 */
    @Schema(description = "성공 여부", example = "")
    private Integer success;

    /** 에러 코드 */
    @Schema(description = "에러 코드", example = "")
    private Integer err;

    /** 에러 메시지 */
    @Schema(description = "에러 메시지", example = "")
    private String errmsg;
    
    /** 신규여부 */
	@Schema(description = "신규여부", example = "")
	private String isNew;
	
    private String staDt;
	
	private String endDt;
	
	private String prevSerialkey;
	
	
}