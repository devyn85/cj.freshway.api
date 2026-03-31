package cjfw.wms.cm.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CmSyProcessTempSimulationEntity extends CommonProcedureDto {

    /** 데이터번호 */
    @Schema(description = "SERIALKEY")
    private Integer serialkey;
    
    /** 프로세스타입 */
    @Schema(description = "PROCESSTYPE")
    private String processtype;
    
    /** 프로세스생성자 */
	@Schema(description = "PROCESSCREATOR")
	private String processcreator;

	/** 배치고유DBID */
	@Schema(description = "SPID")
	private String spid;	

	/** 처리플래그 */
	@Schema(description = "PROCESSFLAG")
	private String processflag;
	
	/** 재고주체키 */
    @Schema(description = "STORERKEY")
    private String storerkey;
	
	/** 센터코드 */
    @Schema(description = "DCCODE")
    private String dccode;
    
    /** 조직코드 */
    @Schema(description = "ORGANIZE")
    private String organize;
    
    /** 구역코드 */
    @Schema(description = "AREA")
    private String area;
    
	/** 기준년월 */
	@Schema(description = "STOCKMONTH")
	private String stockmonth;
	
	/** 문서번호 */
    @Schema(description = "DOCNO")
    private String docno;

    /** 문서라인 */
    @Schema(description = "DOCLINE")
    private String docline;
    
    /** 입출고일자 */
    @Schema(description = "DELIVERYDATE")
    private String deliverydate;
    
    /** 상품코드 */
    @Schema(description = "SKU")
    private String sku;
    
    /** 기준대상여부  */
    @Schema(description = "BASE_YN")
    private String baseYn;
    
    /** 수량 */
    @Schema(description = "QTY")
    private BigDecimal qty;
    
    /** 입고수량 */
    @Schema(description = "GR_QTY")
    private BigDecimal grQty;
    
    /** 출고수량 */
    @Schema(description = "GI_QTY")
    private BigDecimal giQty;
    
    /** 박스수량 */
    @Schema(description = "BOXQTY")
    private BigDecimal boxqty;

	/** 단위 */
	@Schema(description = "UOM")
	private String uom;
	
	/** 입고/출고구분 */
    @Schema(description = "IO_FLAG")
    private String ioFlag;
	
    /** 발생유형구분 */
    @Schema(description = "IO_TYPE")
    private String ioType;
    
    /** 과/면세구분 */
    @Schema(description = "TAX_CLS")
    private String taxCls;
	
    /** 비용타입 */
    @Schema(description = "EXPENSETYPE")
    private String expensetype;    
    
    /** 거래처키 */
    @Schema(description = "CUSTKEY")
    private String custkey;
    
    /** 재고구분ID */
    @Schema(description = "STOCKID")
    private String stockid;
    
    /** 화주이체여부 */
    @Schema(description = "DELIVERYFLAG")
    private String deliveryflag;
    
    /** 선하증권번호 */
    @Schema(description = "DELIVERYFLAG")
    private String convserialno;
    
    /** 유통이력번호 */
    @Schema(description = "SERIALNO")
    private String serialno;
    
    /** 단가_단위 */
    @Schema(description = "PRICE_UOM")
    private String priceUom;

    /** 기준 단가_입고료 */
    @Schema(description = "기준 단가_입고료")
    private BigDecimal baseGrprice;
    
    /** 기준 단가_출고료 */
    @Schema(description = "기준 단가_출고료")
    private BigDecimal baseGiprice;
    
    /** 기준 단가_창고료 */
    @Schema(description = "기준 단가_창고료")
    private BigDecimal baseStorageprice;
    
    /** 기준 단가_팔렛료 */
    @Schema(description = "기준 단가_팔렛료")
    private BigDecimal basePltprice;
    
    /** 기준 단가_계근료 */
    @Schema(description = "기준 단가_계근료")
    private BigDecimal baseWghprice;
    
    /** 기준 단가_작업료 */
    @Schema(description = "기준 단가_작업료")
    private BigDecimal baseWorkprice;  

    /** 비교 단가_입고료 */
    @Schema(description = "비교 단가_입고료")
    private BigDecimal cfGrprice;
    
    /** 비교 단가_출고료 */
    @Schema(description = "비교 단가_출고료")
    private BigDecimal cfGiprice;
    
    /** 비교 단가_창고료 */
    @Schema(description = "비교 단가_창고료")
    private BigDecimal cfStorageprice;
    
    /** 비교 단가_팔렛료 */
    @Schema(description = "비교 단가_팔렛료")
    private BigDecimal cfPltprice;
    
    /** 비교 단가_계근료 */
    @Schema(description = "비교 단가_계근료")
    private BigDecimal cfWghprice;
    
    /** 비교 단가_작업료 */
    @Schema(description = "비교 단가_작업료")
    private BigDecimal cfWorkprice; 

	/** 등록일자 */
	@Schema(description = "ADDDATE")
	private String adddate;

	/** 수정일자 */
	@Schema(description = "EDITDATE")
	private String editdate;

	/** 등록자 */
	@Schema(description = "ADDWHO")
	private String addwho;

	/** 수정자 */
	@Schema(description = "EDITWHO")
	private String editwho;
	
}
