package cjfw.wms.st.dto;
import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.11.24
 * @description : 재고조사결과처리 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.24 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고조사결과처리 Master Response DTO")
@Data
public class StInquiryMoveResDto extends CommonProcedureDto {
    /** 조사일자 */
    @Schema(description = "조사일자")
    private String inquirydt;

    /** 조사번호 */
    @Schema(description = "조사번호")
    private String inquiryno;
    
    /** 최종차수 */
    @Schema(description = "최종차수")
    private BigDecimal lastpriority;
    
	/** 조사별칭 */
	@Schema(description = "조사별칭")
	private String inquiryName;

	/** 상태명 */
	@Schema(description = "상태명")
	private String statusnm;

    /** 로케이션수 */
    @Schema(description = "로케이션수")
    private BigDecimal loccnt;

    /** 상품수 */
    @Schema(description = "상품수")
    private BigDecimal skucnt;

    /** 소스키 */
    @Schema(description = "소스키")
    private String sourcekey;

    /** 메모 */
    @Schema(description = "메모")
    private String memo;

    /** 등록일자 */
    @Schema(description = "등록일자")
    private String adddate;

    /** 등록자 */
    @Schema(description = "등록자")
    private String addwho;

    /** 사용자명 */
    @Schema(description = "사용자명")
    private String username;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 구역 */
    @Schema(description = "구역")
    private String area;

    /** 상태 */
    @Schema(description = "상태")
    private String status;
    
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/** 조사형태 */
	@Schema(description = "조사형태")
	private String inquirytype;    
	
	/** 모바일추가여부 */
	@Schema(description = "모바일추가여부")
	private String mobileAddYn;   	
	
	/** 지시여부 */
	@Schema(description = "지시여부")
	private String instructionYn;

}
