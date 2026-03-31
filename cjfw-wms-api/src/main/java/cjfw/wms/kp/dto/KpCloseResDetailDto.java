package cjfw.wms.kp.dto;

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
 * @date : 2025.08.22
 * @description : 모니터링 > 물동 > 물동마감 진행 현황 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.22 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "모니터링 > 물동 > 물동마감 진행 현황 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpCloseResDetailDto extends CommonProcedureDto {
	
	/* 00. 물류센터 */
	@Schema(description = "00. 물류센터")
	private String dccode;

	/* 00. 문서유형 */
	@Schema(description = "00. 문서유형")
	private String doctype;

	/* 00. 고객사코드 */
	@Schema(description = "00. 고객사코드")
	private String storerkey;

	/* 00. 문서일자 */
	@Schema(description = "00. 문서일자")
	private String docdt;

	/* 01. 문서유형 */
	@Schema(description = "01. 문서유형")
	private String doctypenm;

	/* 02. 납품요청일 */
	@Schema(description = "02. 납품요청일")
	private String deliverydate;

	/* 03. 오더번호 */
	@Schema(description = "03. 오더번호")
	private String docno;

	/* 04. 업체코드 */
	@Schema(description = "04. 업체코드")
	private String custkey;

	/* 05. 업체명 */
	@Schema(description = "05. 업체명")
	private String custname;

	/* 06. 오더품목 */
	@Schema(description = "06. 오더품목")
	private String docline;

	/* 07. 오더유형 */
	@Schema(description = "07. 오더유형")
	private String ordertype;

	/* 07. 오더타입 */
	@Schema(description = "07. 오더타입")
	private String ordertypenm;

	/* 08. 진행상태 */
	@Schema(description = "08. 진행상태")
	private String status;

	/* 09. 상품코드 */
	@Schema(description = "09. 상품코드")
	private String sku;

	/* 10. 상품명칭 */
	@Schema(description = "10. 상품명칭")
	private String skuname;

	/* 11. 단위 */
	@Schema(description = "11. 단위")
	private String uom;

	/* 12. 구매수량 */
	@Schema(description = "12. 구매수량")
	private String dpOrderqty;

	/* 13. 입고수량 */
	@Schema(description = "13. 입고수량")
	private String dpConfirmqty;

	/* 14. 주문수량 */
	@Schema(description = "14. 주문수량")
	private String wdOrderqty;

	/* 15. 출고수량 */
	@Schema(description = "15. 출고수량")
	private String wdConfirmqty;

	/* 16. FROM센터 */
	@Schema(description = "16. FROM센터")
	private String fromDccode;

	/* 17. 저장위치 */
	@Schema(description = "17. 저장위치")
	private String fromLoc;

	/* 18. TO센터 */
	@Schema(description = "18. TO센터")
	private String toDccode;

	/* 19. TO저장위치 */
	@Schema(description = "19. TO저장위치")
	private String toLoc;
	
	/* 20. 등록자 */
	@Schema(description = "20. 등록자")
	private String addwho;

	/* 21. 수정자 */
	@Schema(description = "21. 수정자")
	private String editwho;
	
	/* 등록자 ID */
	@Schema(description = "등록자 ID")
	private String addId;
	
	/* 수정자 ID */
	@Schema(description = "수정자 ID")
	private String updId;
	
	/* CUSTTYPE*/
	@Schema(description = "CUSTTYPE")
	private String custtype;


}
