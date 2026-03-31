package cjfw.wms.cm.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 *
 * @author : KimSunHo(sunhokim6229@cj.net)
 * @date : 2025.07.09
 * @description : 시스템작업 임시 ST Entity
 * @issues :
 * -----------------------------------------------------------
 * DATE          AUTHOR                  MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@NoArgsConstructor
public class CmSyProcessTempStEntity extends CommonProcedureDto {

	/** 처리자 */
	@Schema(description = "처리자")
	private String processcreator;

	/** SPID */
	@Schema(description = "SPID")
	private String spid;

	/** 처리플래그 */
	@Schema(description = "처리플래그")
	private String processflag;

	/** 배치번호 */
	@Schema(description = "배치번호")
	private String batchNo;

	/** 목록번호 */
	@Schema(description = "목록번호")
	private String listNo;

	/** 작업번호 */
	@Schema(description = "작업번호")
	private String workNo;

	/** 작업자 */
	@Schema(description = "작업자")
	private String worker;

	/** 문서일자 */
    @Schema(description = "문서일자")
    private String docdt;

	/** FROM_센터코드 */
	@Schema(description = "FROM_센터코드")
	private String fromDccode;

	/** FROM_고객사코드 */
    @Schema(description = "FROM_고객사코드")
    private String fromStorerkey;

    /** FROM_조직코드 */
    @Schema(description = "FROM_조직코드")
    private String fromOrganize;

    /** FROM_창고코드 SAP 창고 혹은 별도의 창고 코드 */
    @Schema(description = "FROM_창고코드 SAP 창고 혹은 별도의 창고 코드")
    private String fromArea;

    /** FROM_상품코드 */
    @Schema(description = "FROM_상품코드")
    private String fromSku;

    /** FROM_피킹존 */
    @Schema(description = "FROM_피킹존")
    private String fromZone;

    /** FROM_로케이션 */
    @Schema(description = "FROM_로케이션")
    private String fromLoc;

    /** FROM_재고 구분 LOT */
    @Schema(description = "FROM_재고 구분 LOT")
    private String fromLot;

    /** FROM_재고 구분 ID */
    @Schema(description = "FROM_재고 구분 ID")
    private String fromStockid;

    /** FROM_재고 등급( ABC ) */
    @Schema(description = "FROM_재고 등급( ABC)")
    private String fromStockgrade;

    /** FROM_재고유형 */
    @Schema(description = "FROM_재고유형")
    private String fromStocktype;

    /** FROM_재고처리유형 */
    @Schema(description = "FROM_재고처리유형")
    private String fromIotype;

    /** FROM 예정수량 */
    @Schema(description = "FROM 예정수량")
    private BigDecimal fromOrderqty;

    /** FROM 확정수량 */
    @Schema(description = "FROM 확정수량")
    private BigDecimal fromConfirmqty;

    /** FROM UOM */
    @Schema(description = "FROM UOM")
    private String fromUom;

    /** FROM 분자 */
    @Schema(description = "FROM 분자")
    private BigDecimal fromBunja;

    /** FROM 분모 */
    @Schema(description = "FROM 분모")
    private BigDecimal fromBunmo;

    /** TO_센터코드 */
    @Schema(description = "TO_센터코드")
    private String toDccode;

    /** TO_고객사코드 */
    @Schema(description = "TO_고객사코드")
    private String toStorerkey;

    /** TO_조직코드 */
    @Schema(description = "TO_조직코드")
    private String toOrganize;

    /** TO_창고코드 SAP 창고 혹은 별도의 창고 코드 */
    @Schema(description = "TO_창고코드 SAP 창고 혹은 별도의 창고 코드")
    private String toArea;

    /** TO_상품코드 */
    @Schema(description = "TO_상품코드")
    private String toSku;

    /** TO_피킹존 */
    @Schema(description = "TO_피킹존")
    private String toZone;

    /** TO_로케이션 */
    @Schema(description = "TO_로케이션")
    private String toLoc;

    /** TO_재고 구분 LOT */
    @Schema(description = "TO_재고 구분 LOT")
    private String toLot;

    /** TO_재고 구분 ID */
    @Schema(description = "TO_재고 구분 ID")
    private String toStockid;

    /** TO_재고 등급( ABC ) */
    @Schema(description = "TO_재고 등급( ABC)")
    private String toStockgrade;

    /** TO_재고유형 */
    @Schema(description = "TO_재고유형")
    private String toStocktype;

    /** TO_재고처리유형 */
    @Schema(description = "TO_재고처리유형")
    private String toIotype;

    /** TO 예정수량 */
    @Schema(description = "TO 예정수량")
    private BigDecimal toOrderqty;

    /** TO 확정수량 */
    @Schema(description = "TO 확정수량")
    private BigDecimal toConfirmqty;

    /** TO UOM */
    @Schema(description = "TO UOM")
    private String toUom;

    /** TO 분자 */
    @Schema(description = "TO 분자")
    private BigDecimal toBunja;

    /** TO 분모 */
    @Schema(description = "TO 분모")
    private BigDecimal toBunmo;

    /** 기타수량1 */
    @Schema(description = "기타수량1")
    private BigDecimal etcqty1;

    /** 기타수량2 */
    @Schema(description = "기타수량2")
    private BigDecimal etcqty2;

    /** 소스시리얼키1 */
    @Schema(description = "소스시리얼키1")
    private BigDecimal srcserialkey1;

    /** 소스시리얼키2*/
    @Schema(description = "소스시리얼키2")
    private BigDecimal srcserialkey2;

    /** 상태 */
    @Schema(description = "상태")
    private String status;

    /** 삭제여부 */
    @Schema(description = "삭제여부")
    private String delYn;

    /** 데이터흐름제어 */
    @Schema(description = "데이터흐름제어")
    private String trafficcop;

    /** 아카이브제어 */
    @Schema(description = "아카이브제어")
    private String archivecop;

    /** 배치처리메세지 */
    @Schema(description = "배치처리메세지")
    private String processmsg;

    /** 메모 */
    @Schema(description = "메모")
    private String memo1;

    /** 발생사유 */
    @Schema(description = "발생사유")
    private String reasoncode;

    /** 평균중량 */
    @Schema(description = "평균중량", example = "")
    private BigDecimal avgweight;

    /** 환산 박스 수 */
    @Schema(description = "환산 박스 수", example = "")
    private BigDecimal calbox;

    /** 실박스 예정 */
    @Schema(description = "실박스 예정", example = "")
    private BigDecimal realorderbox;

    /** 실박스 확정 */
    @Schema(description = "실박스 확정", example = "")
    private BigDecimal realcfmbox;

    /** 작업박스 수량 */
    @Schema(description = "작업박스 수량", example = "")
    private BigDecimal tranbox;

    /** 박스처리유무 */
    @Schema(description = "박스처리유무", example = "")
    private String boxflag;

    /** 재고 박스 수량 */
    @Schema(description = "재고 박스 수량", example = "")
    private BigDecimal stbox;

    /** 등록일자 */
    @Schema(description = "등록일자")
    private String adddate;

    /** 수정일자 */
    @Schema(description = "수정일자")
    private String editdate;

    /** 등록자 */
    @Schema(description = "등록자")
    private String addwho;

    /** 수정자 */
    @Schema(description = "수정자")
    private String editwho;

    /** 도착박스수량 */
    @Schema(description = "도착박스수량")
    private String toOrderqtyBox;

    /** 도착낱개수량 */
    @Schema(description = "도착낱개수량")
    private String toOrderqtyEa;

    /** 귀속부서 */
	@Schema(description = "귀속부서")
	private String costcd;

    /** 거래처 */
	@Schema(description = "거래처")
	private String custkey;
	
    /** 크로스도킹 */
    @Schema(description = "크로스도킹")
    private String crossdocktype;

    /** 사유메시지 */
    @Schema(description = "사유메시지")
    private String reasonmsg;

}
