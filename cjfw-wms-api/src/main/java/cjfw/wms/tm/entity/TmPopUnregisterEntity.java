package cjfw.wms.tm.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.10 
 * @description : 거래처 POP Entity
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.10    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "거래처 POP Entity") 
public class TmPopUnregisterEntity extends CommonDto {	
	/** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private Long serialkey;

	/** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;
    
    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String fixdccode;

    /** 고객사 */
    @Schema(description = "고객사", nullable = false, example = "")
    private String storerkey;

    /** 거래처코드 */
    @Schema(description = "거래처코드", nullable = false, example = "")
    private String custkey;

    /** 거래처유형코드 */
    @Schema(description = "거래처유형코드", nullable = false, example = "")
    private String custtype;

    /** 적용시작일자 */
    @Schema(description = "적용시작일자", nullable = false, example = "")
    private String fromdate;

    /** POP번호 */
    @Schema(description = "POP번호", nullable = false, example = "")
    private String popno;
    
    /** 차량번호 */
    @Schema(description = "차량번호", nullable = false, example = "")
    private String carno;
    
    /** 롤테이너 */
    @Schema(description = "롤테이너", nullable = false, example = "")
    private String rolltainerNo;
    
    /** 등록자ID */
	@Schema(description = "등록자ID", example = "dev01")
	private String regId;

	/** 등록일시 */
	@Schema(description = "등록일시", example = "2015-10-23 오후 6:08:43")
	private String regDtm;

	/** 수정자ID */
	@Schema(description = "수정자ID", example = "dev01")
	private String updId;

	/** 수정일시 */
	@Schema(description = "수정일시", example = "2021-10-27 오후 5:39:05")
	private String updDtm;
}
