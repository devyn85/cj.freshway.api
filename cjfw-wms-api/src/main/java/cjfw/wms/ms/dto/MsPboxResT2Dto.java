package cjfw.wms.ms.dto;

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
 * @date : 2025.09.18
 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 사용현황_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 사용현황_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsPboxResT2Dto extends CommonProcedureDto {
	
	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dccode;

	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dcname;

	/* 02. 출고일자 */
	@Schema(description = "02. 출고일자")
	private String deliverydt;

	/* 02. P_BOX 번호 */
	@Schema(description = "02. P_BOX 번호")
	private String pboxNo;
	
	/* 03. 기사명 */
	@Schema(description = "03. 기사명")
	private String drivername;
	
	/* 03. 차량번호 */
	@Schema(description = "03. 차량번호")
	private String carno;

	/* 04. 관리처코드 */
	@Schema(description = "04. 관리처코드")
	private String toCustkey;

	/* 05. 관리처명 */
	@Schema(description = "05. 관리처명")
	private String toCustname;

	/* 06. 상품코드 */
	@Schema(description = "06. 상품코드")
	private String sku;

	/* 07. 상품명칭 */
	@Schema(description = "07. 상품명칭")
	private String skuname;

	/* 08. 판매단위 */
	@Schema(description = "08. 판매단위")
	private String baseuom;

	/* 09. 주문수량 */
	@Schema(description = "09. 주문수량")
	private String orderqty;

	/* 10. 출고수량 */
	@Schema(description = "10. 출고수량")
	private String inspectqty;
	
	/* 11. 등록자 */
	@Schema(description = "11. 등록자")
	private String addwho;

	/* 12. 등록일시 */
	@Schema(description = "12. 등록일시")
	private String adddate;

	/* 13. 수정자 */
	@Schema(description = "13. 수정자")
	private String editwho;

	/* 14. 수정일시 */
	@Schema(description = "14. 수정일시")
	private String editdate;	


}
