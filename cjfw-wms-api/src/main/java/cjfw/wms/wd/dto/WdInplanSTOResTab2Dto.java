package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author 		: YeoSeungCheol (pw6375@cj.net) 
 * @date 		: 2025.11.13
 * @description : 광역출고현황 - 이력현황 목록 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "광역출고현황 - 이력현황 목록 조회 요청 DTO")
public class WdInplanSTOResTab2Dto {
	@Schema(description="품목번호")
	private String docline;

	@Schema(description="상품코드")
	private String sku;

	@Schema(description="상품명칭")
	private String skuname;

	@Schema(description="플랜트")
	private String plant;

	@Schema(description="평균중량")
	private String avgweight;

	@Schema(description="환산주문박스")
	private String calorderbox;

	@Schema(description="환산확정박스")
	private String calconfirmbox;

	@Schema(description="실박스")
	private String realbox;

	@Schema(description="주문수량")
	private String orderqtyWd;

	@Schema(description="분배량")
	private String distributeqtyWd;

	@Schema(description="피킹량")
	private String workqtyWd;

	@Schema(description="출고검수량")
	private String inspectqtyWd;

	@Schema(description="출고수량")
	private String confirmqtyWd;

	@Schema(description="이체단위")
	private String uomSto;

	@Schema(description="진행상태")
	private String statusWd;

	@Schema(description="기준일(유통, 제조)")
	private String lottable01;

	@Schema(description="유통기간(잔여/전체)")
	private String durationTerm;

	@Schema(description="이력번호")
	private String serialno;

	@Schema(description="바코드")
	private String barcode;

	@Schema(description="B/L 번호")
	private String blno;

	@Schema(description="도축일자")
	private String butcherydt;

	@Schema(description="도축장")
	private String factoryname;

	@Schema(description="계약유형")
	private String contracttype;

	@Schema(description="계약업체")
	private String contractcompany;

	@Schema(description="계약업체명")
	private String contractcompanyname;

	@Schema(description="유효일자(FROM)")
	private String fromvaliddt;

	@Schema(description="유효일자(TO)")
	private String tovaliddt;

	@Schema(description="스캔예정량")
	private String serialorderqty;

	@Schema(description="스캔량")
	private String serialinspectqty;

	@Schema(description="스캔중량")
	private String serialscanweight;
}
