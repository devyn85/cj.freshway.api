package cjfw.wms.ms.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.24
 * @description : 수발주정보 기능을 구현한 Entity Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "수발주정보 Entity") 
public class MsPurchaseCustEntity extends CommonProcedureDto {
	
	@Schema(description = "멀티 물류센터 코드", example = "DC001")
    private String multiDcCode[];
	
	@Schema(description = "물류센터 코드", example = "DC001")
	private String dcCode;
	
	@Schema(description = "데이터번호")
    private BigDecimal serialKey;

    @Schema(description = "구매유형", maxLength = 10)
    private String purchaseType;
    
    @Schema(description = "저장 구분")
    private String storageType;

    @Schema(description = "배송구분코드", defaultValue = "STD", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 20)
    private String deliveryType = "STD";
    
    @Schema(description = "식별 번호 유무 (시리얼 관리 여부)")
    private String serialYn;
    
    @Schema(description = "비정량 여부 (Line01)")
    private String line01;
    
    @Schema(description = "고객사 코드")
    private String storerKey;
    
    @Schema(description = "거래처 코드", example = "CUST001")
    private String custKey;

    @Schema(description = "경유지", maxLength = 10)
    private String route;

    @Schema(description = "경유지조직", maxLength = 10)
    private String routeOrganize;

    @Schema(description = "구매담당코드", defaultValue = "STD", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 20)
    private String buyerKey = "STD";

    @Schema(description = "구매담당명", maxLength = 35)
    private String buyerName;

    @Schema(description = "최소주문값", defaultValue = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal minOrderQty = BigDecimal.ZERO;

    @Schema(description = "안전계수", defaultValue = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal coefficientSafety = BigDecimal.ZERO;

    @Schema(description = "구매리드타임", defaultValue = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal leadTime = BigDecimal.ONE;

    @Schema(description = "주문간격", defaultValue = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal purInterval = BigDecimal.ONE;

    @Schema(description = "참조데이터1", maxLength = 200)
    private String reference01;

    @Schema(description = "참조데이터2", maxLength = 200)
    private String reference02;

    @Schema(description = "참조데이터3", maxLength = 200)
    private String reference03;

    @Schema(description = "토요일발주가능여부", maxLength = 100)
    private String reference04;

    @Schema(description = "자동발주시간(SO-PO/SO-STO)", maxLength = 100)
    private String reference05;

    @Schema(description = "상태", defaultValue = "00", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 10)
    private String status = "00";

    @Schema(description = "삭제여부", defaultValue = "N", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 9)
    private String delYn = "N";

    @Schema(description = "데이터흐름제어", maxLength = 20)
    private String trafficCop;

    @Schema(description = "아카이브제어", maxLength = 1)
    private String archiveCop;

    @Schema(description = "발주가능여부(월)", defaultValue = "1", maxLength = 1)
    private String monYn = "1";

    @Schema(description = "발주가능여부(화)", defaultValue = "1", maxLength = 1)
    private String tueYn = "1";

    @Schema(description = "발주가능여부(수)", defaultValue = "1", maxLength = 1)
    private String wedYn = "1";

    @Schema(description = "발주가능여부(목)", defaultValue = "1", maxLength = 1)
    private String thuYn = "1";

    @Schema(description = "발주가능여부(금)", defaultValue = "1", maxLength = 1)
    private String friYn = "1";

    @Schema(description = "발주가능여부(토)", defaultValue = "0", maxLength = 1)
    private String satYn = "0";

    @Schema(description = "발주가능여부(일)", defaultValue = "0", maxLength = 1)
    private String sunYn = "0";

    @Schema(description = "수기재발주점", defaultValue = "0")
    private BigDecimal editReorderPoint = BigDecimal.ZERO;

    @Schema(description = "수기목표재고수량", defaultValue = "0")
    private BigDecimal editStockGoal = BigDecimal.ZERO;

    @Schema(description = "발주수량단위", defaultValue = "0")
    private BigDecimal orderQtyUnit = BigDecimal.ZERO;

    @Schema(description = "참조데이터4", maxLength = 200)
    private String reference06;

    @Schema(description = "자동발주 산정기간")
    private BigDecimal autoExecDay;

    @Schema(description = "자동발주 시간(00시-07시)", maxLength = 2)
    private String batchTimePeriod1;

    @Schema(description = "자동발주 시간(08시-15시)", maxLength = 2)
    private String batchTimePeriod2;

    @Schema(description = "자동발주 시간(16시-23시)", maxLength = 2)
    private String batchTimePeriod3;

    @Schema(description = "자동발주실행(월)", maxLength = 1)
    private String autoMonYn;

    @Schema(description = "자동발주실행(화)", maxLength = 1)
    private String autoTueYn;

    @Schema(description = "자동발주실행(수)", maxLength = 1)
    private String autoWedYn;

    @Schema(description = "자동발주실행(목)", maxLength = 1)
    private String autoThuYn;

    @Schema(description = "자동발주실행(금)", maxLength = 1)
    private String autoFriYn;

    @Schema(description = "자동발주실행(토)", maxLength = 1)
    private String autoSatYn;

    @Schema(description = "자동발주실행(일)", maxLength = 1)
    private String autoSunYn;

    @Schema(description = "MOQ(업체별)", maxLength = 10)
    private BigDecimal moqVender;

    @Schema(description = "최초등록시간 (자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private String addDate;

    @Schema(description = "최종변경시간 (자동 갱신)", accessMode = Schema.AccessMode.READ_ONLY)
    private String editDate;

    @Schema(description = "최초등록자", defaultValue = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 24)
    private String addWho = "SYSTEM";

    @Schema(description = "최종변경자", defaultValue = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 24)
    private String editWho = "SYSTEM";

    @Schema(description = "대체상품코드", maxLength = 20)
    private String alterSku;

    @Schema(description = "예외입고품의여부", maxLength = 1)
    private String excptDpApprYn;

    @Schema(description = "영업그룹코드", maxLength = 20)
    private String saleGroupCd;

    @Schema(description = "특이사항여부", maxLength = 1)
    private String spclNoteYn;
    
    @Schema(description = "거래처유형", maxLength = 10)
    private String custType;

    @Schema(description = "적용 시작일", defaultValue = "19000101", maxLength = 8)
    private String fromDate = "19000101";

    @Schema(description = "적용 종료일", defaultValue = "29991231", maxLength = 8)
    private String toDate = "29991231";
    
    @Schema(description = "플랜트", maxLength = 10)
    private String plant;

    @Schema(description = "사이트", maxLength = 20)
    private String site;

    @Schema(description = "채널", maxLength = 10)
    private String channel;

    @Schema(description = "단위", maxLength = 10)
    private String uom;

    @Schema(description = "최대주문수량", defaultValue = "0")
    private BigDecimal maxOrderQty = BigDecimal.ZERO;

    @Schema(description = "공장도가", defaultValue = "0")
    private BigDecimal factoryPrice = BigDecimal.ZERO;

    @Schema(description = "매입가", defaultValue = "0")
    private BigDecimal purchasePrice = BigDecimal.ZERO;

    @Schema(description = "판매가", defaultValue = "0")
    private BigDecimal salePrice = BigDecimal.ZERO;

    @Schema(description = "구매그룹", maxLength = 20)
    private String purchaseGroup;

    @Schema(description = "구매채널", maxLength = 20)
    private String purchaseChannel;

    @Schema(description = "배송주차", maxLength = 10)
    private String deliveryWeek;

    @Schema(description = "영업일유형", maxLength = 10)
    private String workDayType;

    @Schema(description = "통제유형", maxLength = 60)
    private String controlType;

    @Schema(description = "반품유형", maxLength = 10)
    private String returnType;

    @Schema(description = "기타01", maxLength = 100)
    private String other01;

    @Schema(description = "기타02", maxLength = 100)
    private String other02;

    @Schema(description = "기타03", maxLength = 100)
    private String other03;

    @Schema(description = "기타04", maxLength = 100)
    private String other04;

    @Schema(description = "기타05", maxLength = 100)
    private String other05;

    @Schema(description = "통제 시작일", maxLength = 8)
    private String controlFromDate;

    @Schema(description = "통제 종료일", maxLength = 8)
    private String controlToDate;
    
    @Schema(description = "상품 코드", example = "SKU001")
    private String sku;
    
    @Schema(description = "MS_SKUCHAIN_MOQ.MOQ_SKU")
    private BigDecimal moqSku;

    @Schema(description = "PLT 변환값 (레이어당 PLT 또는 MOQ 상품 PLT)", example = "10.5")
    private BigDecimal pltChange;

    @Schema(description = "MOQ 상품 (PLT)", example = "5")
    private BigDecimal moqSkuPlt = BigDecimal.ZERO;

    @Schema(description = "MOQ 협력사 (PLT)", example = "25")
    private BigDecimal moqVenderPlt = BigDecimal.ZERO;
    
    @Schema(description = "소진 시 중단 여부")
    private String exhaustionStopYn;
    
    @Schema(description = "키맨번호", maxLength = 200)
    private String reference07;
    
    @Schema(description = "파일내 순서")
    private Integer fileLineNo;
    
    @Schema(description = "상품명")
    private String skuName;
    
    @Schema(description = "박스당 낱개 수")
    private BigDecimal qtyPerBox;
    
    @Schema(description = "팔레트당 적재 단수")
    private BigDecimal layerPerPlt;
    
    @Schema(description = "적재 단당 박스 수")
    private BigDecimal boxPerLayer;
    
    @Schema(description = "거래처 명", example = "CUST001")
    private String custName;
    
    @Schema(description = "수급담당자", example = "CUST001")
    private String buyerKeyNm;
    
    /** 프로시저 실행 성공여부 */
	@Schema(description = "프로시저 실행 성공여부", example = "")
	private Integer success;
	
	/** 프로시저 실행 에러코드 */
	@Schema(description = "프로시저 실행 에러코드", example = "")
	private Integer err;
	
	/** 프로시저 실행 에러메시지 */
	@Schema(description = "프로시저 실행 에러메시지", example = "")
	private String errmsg;
}
