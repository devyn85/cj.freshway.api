package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "광역출고 Entity")
public class WdInplanSTOEntity extends CommonDto {
	@Schema(description="주문유형")
	private String ordertype;

	@Schema(description="광역출고일자")
	private String docdt;

	@Schema(description="광역주문번호")
	private String docno;

	/* 상품코드-멀티 */
	@MultiSearch
    @Schema(description = "상품코드-멀티")
    private List<List<String>> docnoMulti;  
	
	@Schema(description="물류센터")
	private String fromDccode;

	@Schema(description="물류센터명")
	private String fromDcname;

	@Schema(description="창고")
	private String fromOrganize;

	@Schema(description="창고명")
	private String fromCustname;

	@Schema(description="물류센터")
	private String toDccode;

	@Schema(description="물류센터명")
	private String toDcname;

	@Schema(description="창고")
	private String toOrganize;

	@Schema(description="창고명")
	private String toCustname;

	@Schema(description="차량번호")
	private String carno;

	@Schema(description="출차시간")
	private String dcdeparturedt;

	@Schema(description="이체유형")
	private String stotype;

	@Schema(description="메모")
	private String memo1;

	@Schema(description="등록자")
	private String addwho;

	@Schema(description="등록일시")
	private String adddate;

	@Schema(description="수정자")
	private String editwho;

	@Schema(description="수정일시")
	private String editdate;
	
	@Schema(description = "저장유무")
	private String channelDmd;
	
	@Schema(description = "저장조건")
	private String storagetype;
	
	@Schema(description = "검수량 - 입고")
	private String tostoInspectqty;
	
	@Schema(description = "확정수량 - 입고")
	private String tostoConfirmqty;
	
	@Schema(description = "중량 - 출고")
	private String confirmweight;
	
	@Schema(description = "중량 - 입고")
	private String tostoConfirmweight;
	
	@Schema(description = "진행상태 - 출고")
	private String status;
	
	@Schema(description = "진행상태 - 입고")
	private String tostoStatus;

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
	
	@Schema(description="품목번호")
	private String docline;

	@Schema(description="상품코드")
	private String sku;

	@Schema(description="상품명칭")
	private String skuname;

	@Schema(description="로케이션")
	private String loc;

	@Schema(description="기준일(유통,제조)")
	private String lottable01;

	@Schema(description="단위")
	private String uom;

	@Schema(description="진행수량")
	private String processqty;

	@Schema(description="현장작업량")
	private String workqty;

	@Schema(description="검품수량")
	private String inspectqty;

	@Schema(description="확정수량")
	private String confirmqty;
	

	/* CHANNEL */
	@Schema(description = "CHANNEL")
	private String channel;


	/* BOXQTY */
	@Schema(description = "BOXQTY")
	private String boxqty;

	/* ORDERQTY */
	@Schema(description = "ORDERQTY")
	private String orderqty;

	/* QTYPERBOX */
	@Schema(description = "QTYPERBOX")
	private String qtyperbox;
	
	/* DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/* DELIVERYDT */
	@Schema(description = "DELIVERYDT")
	private String deliverydt;

	/* FROM_CUSTKEY */
	@Schema(description = "FROM_CUSTKEY")
	private String fromCustkey;


	/* FROM_CUSTADDRESS */
	@Schema(description = "FROM_CUSTADDRESS")
	private String fromCustaddress;



	/* DOCTYPE */
	@Schema(description = "DOCTYPE")
	private String doctype;

	/* TO_CUSTKEY */
	@Schema(description = "TO_CUSTKEY")
	private String toCustkey;


	/* TO_CUSTADDRESS */
	@Schema(description = "TO_CUSTADDRESS")
	private String toCustaddress;



}
