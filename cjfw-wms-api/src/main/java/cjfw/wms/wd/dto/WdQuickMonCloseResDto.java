package cjfw.wms.wd.dto;
import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MaskingAddress;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 퀵배송상세 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "퀵배송상세 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WdQuickMonCloseResDto extends CommonDto {

    /** 퀵주문접수일시 */
    @Schema(description = "퀵주문접수일시")
    private String quickDate;

    /** 예약일시 */
    @Schema(description = "예약일시")
    private String reservedate;

    /** 진행상태(01:VOC퀵요청,02:센터접수,03:퀵주문등록,04:퀵주문취소등록,05:퀵주문처리완료,06:퀵주문취소완료) */
    @Schema(description = "진행상태(01:VOC퀵요청,02:센터접수,03:퀵주문등록,04:퀵주문취소등록,05:퀵주문처리완료,06:퀵주문취소완료)")
    private String status;

    /** 진행상태명 */
    @Schema(description = "진행상태명")
    private String statusnm;

    /** 퀵주문번호 */
    @Schema(description = "퀵주문번호")
    private String quickDocno;

    /** 요청부서 */
    @Schema(description = "요청부서")
    private String reqDepartment;

    /** 요청부서명 */
    @Schema(description = "요청부서명")
    private String reqDepartmentnm;

    /** 요청자 */
    @Schema(description = "요청자")
    private String reqId;

    /** 요청자명 */
    @Schema(description = "요청자명")
    private String reqNm;

    /** VOC번호 */
    @Schema(description = "VOC번호")
    private String vocno;

    /** 귀책부서 */
    @Schema(description = "귀책부서")
    private String respDept;
    
    /** 귀책부서명 */
    @Schema(description = "귀책부서명")
    private String respDeptnm;    

    /** 귀책사유 */
    @Schema(description = "귀책사유")
    private String respReason;
    
    /** 귀책사유명 */
    @Schema(description = "귀책사유명")
    private String respReasonnm;    

    /** 귀책담당자 */
    @Schema(description = "귀책담당자")
    private String respEmp;

    /** 집하지방문순서 */
    @Schema(description = "집하지방문순서")
    private String gthSeq;

    /** 집하지코드 */
    @Schema(description = "집하지코드")
    private String gthCd;

    /** 집하지명 */
    @Schema(description = "집하지명")
    private String gthNm;

    /** 집하지주소 */
    @Schema(description = "집하지주소")
    private String gthAddr;

    /** 집하지상세주소 */
    @MaskingAddress
    @Schema(description = "집하지상세주소")
    private String gthAddrdtl;

    /** 집하지담당자명 */
    @Schema(description = "집하지담당자명")
    private String gthEmpNm;

    /** 집하지연락처 */
    @MaskingTelno
    @Schema(description = "집하지연락처")
    private String gthTel;

    /** 배송방법 */
    @Schema(description = "배송방법")
    private String deliverytype;

    /** 배송수단 */
    @Schema(description = "배송수단")
    private String deliveryMethod;

    /** 배송선택 */
    @Schema(description = "배송선택")
    private String deliveryOption;

    /** 물품종류 */
    @Schema(description = "물품종류")
    private String articleType;

    /** 지급구분 */
    @Schema(description = "지급구분")
    private String payType;

    /** 기본요금 */
    @Schema(description = "기본요금")
    private BigDecimal basicCost;

    /** 추가요금 */
    @Schema(description = "추가요금")
    private BigDecimal addCost;

    /** 탁송요금 */
    @Schema(description = "탁송요금")
    private BigDecimal deliveryCost;

    /** 지급금액 */
    @Schema(description = "지급금액")
    private BigDecimal totalAmount;

    /** 고객사코드 */
    @Schema(description = "고객사코드")
    private String storerkey;

    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;

    /** 정산월 */
    @Schema(description = "정산월")
    private String sttlYm;

    /** 퀵주문번호 */
    @Schema(description = "퀵주문번호")
    private String quickDocno2;

    /** 귀책부서 */
    @Schema(description = "귀책부서")
    private String respDept2;

    /** 귀책사유 */
    @Schema(description = "귀책사유")
    private String respReason2;

    /** 귀책담당자 */
    @Schema(description = "귀책담당자")
    private String respEmp2;

    /** 정산엑셀발행여부 */
    @Schema(description = "정산엑셀발행여부")
    private String sttlExcelYn;

    /** 정산마감여부 */
    @Schema(description = "정산마감여부")
    private String sttlCloseYn;

    /** 정산마감일시 */
    @Schema(description = "정산마감일시")
    private String sttlCloseDate;
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;
    
    /** 기간구분(1:완료일,2:정산월(전월26~당월25) */
    @Schema(description = "기간구분")
    private String dateFlag;    
    
	/** 일자(From) */
	@Schema(description = "일자(From)")
	private String dt1;

	/** 일자(To) */
	@Schema(description = "일자(To)")
	private String dt2;    
	
	
	/** 퀵사용자id */
	@Schema(description = "퀵사용자id")
	private String quickUserId; 
	

    /** 퀵주문번호-API */
    @Schema(description = "퀵주문번호-API")
    private String apiQuickDocno;

    /** 귀책부서-API */
    @Schema(description = "귀책부서-API")
    private String apiRespDept;

    /** 귀책부서명-API */
    @Schema(description = "귀책부서명-API")
    private String apiRespDeptNm;

    /** 귀책사유-API */
    @Schema(description = "귀책사유-API")
    private String apiRespReason;

    /** 귀책사유명-API */
    @Schema(description = "귀책사유명-API")
    private String apiRespReasonNm;

    /** 귀책담당자-API */
    @Schema(description = "귀책담당자-API")
    private String apiRespEmp;
   
	/** 센터접수번호 */
	@Schema(description = "센터접수번호")
	private String rcptNo;
	
	/** 데이터구분 */
	@Schema(description = "데이터구분")
	private String dataFlag;	
}
