package cjfw.wms.om.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.24 
 * @description : 저장품자동발주관리 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class OmAutoOrderSetupHistoryResDto {
			
	/** 발주실행일자 */
    @Schema(description = "발주실행일자", example = "")
    private String rundt;

    /** 발주코드 */
    @Schema(description = "발주코드", example = "")
    private String purchaseCd;

    /** 실행모드 */
    @Schema(description = "실행모드", example = "")
    private String runmode;

    /** 대상주문일 */
    @Schema(description = "대상주문일", example = "")
    private String orderDt;

    /** 대상발주일 */
    @Schema(description = "대상발주일", example = "")
    private String purchaseDt;

    /** 대상발주일 */
    @Schema(description = "대상발주일", example = "")
    private String docdt;

    /** 문서번호(생성된STO번호) */
    @Schema(description = "문서번호(생성된STO번호)", example = "")
    private String docno;

    /** 결과체크여부 */
    @Schema(description = "결과체크여부", example = "")
    private String resultChkYn;

    /** 결과체크시간 */
    @Schema(description = "결과체크시간", example = "")
    private String resultChkTerm;

    /** 결과회신여부(N-발송대상,R-재발송대상,Y-발송완료,Z-점검제외,X-발송제외) */
    @Schema(description = "결과회신여부(N-발송대상,R-재발송대상,Y-발송완료,Z-점검제외,X-발송제외)", example = "")
    private String resultSendYn;

    /** 결과회신일시 */
    @Schema(description = "결과회신일시", example = "")
    private String resultSendDate;

    /** 발주처리시작시간 */
    @Schema(description = "발주처리시작시간", example = "")
    private String startdate;

    /** 발주처리종료시간 */
    @Schema(description = "발주처리종료시간", example = "")
    private String enddate;

    /** 상태(00-처리중, 90-처리완료) */
    @Schema(description = "상태(00-처리중, 90-처리완료)", example = "")
    private String status;

    /** 처리메세지 */
    @Schema(description = "처리메세지", example = "")
    private String procrmsg;

    /** FROM_센터코드 */
    @Schema(description = "FROM_센터코드", example = "")
    private String fromDccode;

    /** FROM_조직코드 */
    @Schema(description = "FROM_조직코드", example = "")
    private String fromOrganize;

    /** TO_센터코드 */
    @Schema(description = "TO_센터코드", example = "")
    private String toDccode;

    /** TO_조직코드 */
    @Schema(description = "TO_조직코드", example = "")
    private String toOrganize;

    /** TO_상품코드 */
    @Schema(description = "TO_상품코드", example = "")
    private String toSku;

    /** 상품명 */
    @Schema(description = "상품명", example = "")
    private String skuname;

    /** TO UOM */
    @Schema(description = "TO UOM", example = "")
    private String toUom;

    /** TO 예정수량 */
    @Schema(description = "TO 예정수량", example = "")
    private Integer toOrderqty;

    /** 소스시리얼키1 */
    @Schema(description = "소스시리얼키1", example = "")
    private String receivedt;

    /** 로그일자 */
    @Schema(description = "로그일자", example = "")
    private String logdate;

    /** 로그생성자 */
    @Schema(description = "로그생성자", example = "")
    private String logwho;

    /** 변경로그1 */
    @Schema(description = "변경로그1", example = "")
    private String logdata1;

    /** 변경로그2 */
    @Schema(description = "변경로그2", example = "")
    private String logdata2;

    /** 변경로그3 */
    @Schema(description = "변경로그3", example = "")
    private String logdata3;
    
	/** 등록자 */
	@Schema(description = "등록자", example = "")
	private String addwho;

	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String editwho;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;
	
	/** 등록자 */
	@Schema(description = "등록자", example = "")
	private String regNm;
	
	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String updNm;
		
}