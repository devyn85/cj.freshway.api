package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.08.29 
 * @description : 거래처별전용차량정보 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsCustUsageCarResDto {
			
	/** 회사코드 */
    @Schema(description = "회사코드", example = "")
    private String storerkey;

    /** 센터명 */
    @Schema(description = "센터명", example = "")
    private String dcname;

    /** 센터코드 */
    @Schema(description = "센터코드", example = "")
    private String dccode;

    /** 거래처차량유형 */
    @Schema(description = "거래처차량유형", example = "")
    private String custcartype;
    
    /** 거래처차량유형(전용) */
    @Schema(description = "거래처차량유형(전용)", example = "")
    private String custcartype10;
    
    /** 거래처차량유형(강성) */
    @Schema(description = "거래처차량유형(강성)", example = "")
    private String custcartype99;

    /** 거래처코드 */
    @Schema(description = "거래처코드", example = "")
    private String custkey;

    /** 적용시작일자 */
    @Schema(description = "적용시작일자", example = "")
    private String fromdate;

    /** 적용종료일자 */
    @Schema(description = "적용종료일자", example = "")
    private String todate;

    /** 차량번호 */
    @Schema(description = "차량번호", example = "")
    private String carno;

    /** 우선순위 */
    @Schema(description = "우선순위", example = "")
    private String priority;

    /** 상태 */
    @Schema(description = "상태", example = "")
    private String status;

    /** 거래처명 */
    @Schema(description = "거래처명", example = "")
    private String custname;

    /** 메모 */
    @Schema(description = "메모", example = "")
    private String memo;

    /** 적용여부 */
    @Schema(description = "적용여부", example = "")
    private String applyYn;

    /** 운전자1 */
    @Schema(description = "운전자1", example = "")
    private String driver1;

    /** 운전자1_ID */
    @Schema(description = "운전자1_ID", example = "")
    private String driver1Id;

    /** 운전자2 */
    @Schema(description = "운전자2", example = "")
    private String driver2;

    /** 운전자2_ID */
    @Schema(description = "운전자2_ID", example = "")
    private String driver2Id;
    
    /** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String addwho;

	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String editwho;
	
	/** 최초등록시간 */
    @Schema(description = "최초등록시간", example = "")
    private String adddate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", example = "")
    private String editdate;

    /** 등록자명 */
    @Schema(description = "등록자명", example = "")
    private String regNm;

    /** 수정자명 */
    @Schema(description = "수정자명", example = "")
    private String updNm;
    
    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
    /** 물류센터 유효성 */
    @Schema(description = "물류센터 유효성", example = "")
    private String dccodeChk;

    /** 고객코드 유효성 */
    @Schema(description = "고객코드 유효성", example = "")
    private String custkeyChk;
    
    /** 차량타입 유효성 */
    @Schema(description = "차량타입 유효성", example = "")
    private String custcartypeChk;
    
    /** 차량번호 유효성 */
    @Schema(description = "차량번호 유효성", example = "")
    private String carnoChk;
    
    /** 중복 유효성 */
    @Schema(description = "중복 유효성", example = "")
    private String duplicateChk;
    
    /** 수정여부 */
    @Schema(description = "수정여부", example = "")
    private String updateChk;
    
    /** 엑셀 순번 */
    @Schema(description = "엑셀 순번")
    private String rn;
	
}