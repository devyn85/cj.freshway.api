package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.21
 * @description : 출고 > 출고 > CS 출고 정정 요청 대응 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.21 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "출고 > 출고 > CS 출고 정정 요청 대응 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WdVsrModifyResDto extends CommonProcedureDto {
	
	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dccode;

	/* 02. 물류센터명 */
	@Schema(description = "02. 물류센터명")
	private String dcname;

	/* 03. 처리구분 */
	@Schema(description = "03. 처리구분")
	private String status;

	/* 04. 답변 */
	@Schema(description = "04. 답변")
	private String reasonmsg;

	/* 05. 일자 */
	@Schema(description = "05. 일자")
	private String ifDate;

	/* 06. 문서번호 */
	@Schema(description = "06. 문서번호")
	private String docno;

	/* 07. 품목번호 */
	@Schema(description = "07. 품목번호")
	private String docline;

	/* 08. 상세 */
	@Schema(description = "08. 상세")
	private String claimdtlid;

	/* 08. 상세 */
	@Schema(description = "08. 상세")
	private String claimdtlnm;

	/* 09. 납품일자 */
	@Schema(description = "09. 납품일자")
	private String deliverydate;

	/* 10. VSR번호 */
	@Schema(description = "10. VSR번호")
	private String vsrno;

	/* 11. VOC번호 */
	@Schema(description = "11. VOC번호")
	private String vocno;

	/* 12. VOC순번 */
	@Schema(description = "12. VOC순번")
	private String vocseq;

	/* 13. VOC사유 */
	@Schema(description = "13. VOC사유")
	private String memo;

	/* 14. 거래처 */
	@Schema(description = "14. 거래처")
	private String custkey;

	/* 15. 거래처명 */
	@Schema(description = "15. 거래처명")
	private String custname;

	/* 16. 상품코드 */
	@Schema(description = "16. 상품코드")
	private String sku;

	/* 17. 상품명칭 */
	@Schema(description = "17. 상품명칭")
	private String skuname;

	/* 18. 저장유무 */
	@Schema(description = "18. 저장유무")
	private String channel;

	/* 19. 입고번호 */
	@Schema(description = "19. 입고번호")
	private String pokey;

	/* 20. 입고라인 */
	@Schema(description = "20. 입고라인")
	private String poline;

	/* 21. 주문량 */
	@Schema(description = "21. 주문량")
	private String orderqty;

	/* 22. 납품량 */
	@Schema(description = "22. 납품량")
	private String confirmqty;

	/* 23. 주문단위 */
	@Schema(description = "23. 주문단위")
	private String orderuom;

	/* 24. VOC수량 */
	@Schema(description = "24. VOC수량")
	private String claimqty;

	/* 25. VOC단위 */
	@Schema(description = "25. VOC단위")
	private String claimuom;

	/* 26. 요구사항 */
	@Schema(description = "26. 요구사항")
	private String rmk;

	/* 27. SU/사업부 */
	@Schema(description = "27. SU/사업부")
	private String imputedevcd;

	/* 28. VOC등록자 */
	@Schema(description = "28. VOC등록자")
	private String writer;

	/* 28. VOC등록자 */
	@Schema(description = "28. VOC등록자")
	private String writerId;

	/* 29. VOC등록일시 */
	@Schema(description = "29. VOC등록일시")
	private String writedate;

	/* CUSTTYPE */
	@Schema(description = "CUSTTYPE")
	private String custtype;

	/* SERIALKEY */
	@Schema(description = "SERIALKEY")
	private String serialkey;
	
	/* IF_SERIALKEY */
	@Schema(description = "IF_SERIALKEY")
	private String ifSerialkey;

	/* DELIVERYDATE_PRE */
	@Schema(description = "DELIVERYDATE_PRE")
	private String deliverydatePre;

	/* IF_FLAG */
	@Schema(description = "IF_FLAG")
	private String ifFlag;

	/* SOURCEKEY */
	@Schema(description = "SOURCEKEY")
	private String sourcekey;

	/* SOURCELINE */
	@Schema(description = "SOURCELINE")
	private String sourceline;

    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
}
