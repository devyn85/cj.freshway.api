package cjfw.wms.cm.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CmSyProcessTempAjEntity extends CommonProcedureDto {

	/** 처리자 */
	@Schema(description = "PROCESSCREATOR")
	private String processcreator;

	/** SPID */
	@Schema(description = "SPID")
	private String spid;

	/** 처리플래그 */
	@Schema(description = "PROCESSFLAG")
	private String processflag;

	/** 배치번호 */
	@Schema(description = "BATCH_NO")
	private String batchno;

	/** 목록번호 */
	@Schema(description = "LIST_NO")
	private String listno;

	/** 작업번호 */
	@Schema(description = "WORK_NO")
	private String workno;

	/** 작업자 */
	@Schema(description = "WORKER")
	private String worker;

	/** 문서일자 */
	@Schema(description = "DOCDT")
	private String docdt;

	/** 문서번호 */
	@Schema(description = "DOCNO")
	private String docno;

	/** 문서라인 */
	@Schema(description = "DOCLINE")
	private String docline;

	/** 주문유형 */
	@Schema(description = "ORDERTYPE")
	private String ordertype;

	/** 전표일자 */
	@Schema(description = "SLIPDT")
	private String slipdt;

	/** 전표번호 */
	@Schema(description = "SLIPNO")
	private String slipno;

	/** 전표라인 */
	@Schema(description = "SLIPLINE")
	private String slipline;

	/** 전표유형 */
	@Schema(description = "SLIPTYPE")
	private String sliptype;

	/** 입출고유형 */
	@Schema(description = "IOTYPE")
	private String iotype;

	/** 센터코드 */
	@Schema(description = "DCCODE")
	private String dccode;

	/** 재고주체키 */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/** 조직코드 */
	@Schema(description = "ORGANIZE")
	private String organize;

	/** 구역코드 */
	@Schema(description = "AREA")
	private String area;

	/** 상품코드 */
	@Schema(description = "SKU")
	private String sku;

	/** 존 */
	@Schema(description = "ZONE")
	private String zone;

	/** 로케이션 */
	@Schema(description = "LOC")
	private String loc;

	/** LOT */
	@Schema(description = "LOT")
	private String lot;

	/** 재고ID */
	@Schema(description = "STOCKID")
	private String stockid;

	/** 재고등급 */
	@Schema(description = "STOCKGRADE")
	private String stockgrade;
	
	/** 재고등급명 */
    @Schema(description = "STOCKGRADENAME")
    private String stockgradename;

	/** 재고유형 */
	@Schema(description = "STOCKTYPE")
	private String stocktype;
	
	/** 재고유형명 */
    @Schema(description = "STOCKTYPENM")
    private String stocktypenm;
    
    /** 수량 */
    @Schema(description = "QTY")
    private BigDecimal qty;

	/** 주문수량 */
	@Schema(description = "ORDERQTY")
	private BigDecimal orderqty;

	/** 가용수량 */
	@Schema(description = "OPENQTY")
	private BigDecimal openqty;

	/** 처리수량 */
	@Schema(description = "PROCESSQTY")
	private BigDecimal processqty;

	/** 작업수량 */
	@Schema(description = "WORKQTY")
	private BigDecimal workqty;

	/** 검사수량 */
	@Schema(description = "INSPECTQTY")
	private BigDecimal inspectqty;

	/** 확정수량 */
	@Schema(description = "CONFIRMQTY")
	private BigDecimal confirmqty;

	/** 송장수량 */
	@Schema(description = "INVOICEQTY")
	private BigDecimal invoiceqty;

	/** 단위 */
	@Schema(description = "UOM")
	private String uom;

	/** 분자 */
	@Schema(description = "BUNJA")
	private BigDecimal bunja;

	/** 분모 */
	@Schema(description = "BUNMO")
	private BigDecimal bunmo;

	/** 기타수량1 */
	@Schema(description = "ETCQTY1")
	private BigDecimal etcqty1;

	/** 기타수량2 */
	@Schema(description = "ETCQTY2")
	private BigDecimal etcqty2;

	/** 혼합박스키 */
	@Schema(description = "MIXBOXKEY")
	private String mixboxkey;

	/** 작업키 */
	@Schema(description = "TASKKEY")
	private String taskkey;

	/** 웨이브키 */
	@Schema(description = "WAVEKEY")
	private String wavekey;

	/** 출처이력키1 */
	@Schema(description = "SRCSERIALKEY1")
	private BigDecimal srcserialkey1;

	/** 출처이력키2 */
	@Schema(description = "SRCSERIALKEY2")
	private BigDecimal srcserialkey2;

	/** 송장번호 */
	@Schema(description = "INVOICENO")
	private String invoiceno;

	/** 배송그룹 */
	@Schema(description = "DELIVERYGROUP")
	private String deliverygroup;

	/** 거래처키 */
	@Schema(description = "CUSTKEY")
	private String custkey;

	/** 상태 */
	@Schema(description = "STATUS")
	private String status;

	/** 삭제여부 */
	@Schema(description = "DEL_YN")
	private String delyn;

	/** 교통담당자 */
	@Schema(description = "TRAFFICCOP")
	private String trafficcop;

	/** 보관담당자 */
	@Schema(description = "ARCHIVECOP")
	private String archivecop;

	/** 처리메시지 */
	@Schema(description = "PROCESSMSG")
	private String processmsg;

	/** 기타01 */
	@Schema(description = "OTHER01")
	private String other01;

	/** 기타02 */
	@Schema(description = "OTHER02")
	private String other02;

	/** 기타03 */
	@Schema(description = "OTHER03")
	private String other03;

	/** 기타04 */
	@Schema(description = "OTHER04")
	private String other04;

	/** 기타05 */
	@Schema(description = "OTHER05")
	private String other05;

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
	
	/** 시리얼키 */
	@Schema(description = "SERIALKEY")
	private String serialkey;
	
    @Schema(description = "IF 플래그")
    private String ifFlag;
    
    /** 폐기구분 */
    @Schema(description = "폐기구분")
    private String disuseDivReq;    
    /** 폐기구분 */
    @Schema(description = "폐기구분")
    private String disuseDiv;    
    
    /** 귀속부서코드 */
    @Schema(description = "귀속부서코드")
    private String respDeptCd;
    
	/** seq */
	@Schema(description = "SRCSERIALKEY2")
	private BigDecimal seq;
	
	/** QTYALLOCATED */
    @Schema(description = "QTYALLOCATED")
    private BigDecimal qtyallocated;
    
    /** QTYPICKED */
    @Schema(description = "QTYPICKED")
    private BigDecimal qtypicked;
    
    /** CALLQTY */
    @Schema(description = "CALLQTY")
    private BigDecimal callqty;
    
    /** 소비기한 */
    @Schema(description = "소비기한")
    private String duration;
    
    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;
    
    /** DISUSETYPE */
    @Schema(description = "DISUSETYPE")
    private String disusetype;
    
    /** IMPUTETYPE */
    @Schema(description = "IMPUTETYPE")
    private String imputetype;
    
    /** PROCESSMAIN */
    @Schema(description = "PROCESSMAIN")
    private String processmain;
    
    /** COSTCD */
    @Schema(description = "COSTCD")
    private String costcd;
    
    /** KIT상품코드 */
    @Schema(description = "KIT상품코드")
    private String kitSku;
    
    /** PLANORDER */
    @Schema(description = "PLANORDER")
    private String planorder;
    
    /** 계획일자 */
    @Schema(description = "계획일자")
    private String plandt;
	
}
