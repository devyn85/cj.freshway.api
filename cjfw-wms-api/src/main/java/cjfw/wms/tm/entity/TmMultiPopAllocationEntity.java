package cjfw.wms.tm.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.23
 * @description : 거래처별이중 POP 배차 Entity
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "거래처별이중 POP 배차 Entity")
public class TmMultiPopAllocationEntity extends CommonDto {

	/** row 번호 */
    @Schema(description = "row 번호", nullable = false, example = "")
    private Integer rowcnt;
    
    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private Integer serialkey;

    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;
    
    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String fixdccode;

    /** 고객사 */
    @Schema(description = "고객사", nullable = false, example = "")
    private String storerkey;
    
    /** 문서번호 */
    @Schema(description = "문서번호", nullable = false, example = "")
    private String doctype;
    
    /** 전표번호 */
    @Schema(description = "전표번호", nullable = false, example = "")
    private String slipno;
    
    /** 배송일자 */
    @Schema(description = "배송일자", nullable = false, example = "")
    private String deliverydt;
    
    /** 관리처코드 */
    @Schema(description = "관리처코드", nullable = false, example = "")
    private String shptoId;    
    
    /** 관리처명 */
    @Schema(description = "관리처명", nullable = false, example = "")
    private String shptoIdName;
    
    /** 분할관리처코드 */
    @Schema(description = "분할관리처코드", nullable = false, example = "")
    private String mngplcId;
    
    /** 분할관리처명 */
    @Schema(description = "분할관리처명", nullable = false, example = "")
    private String mngplcIdName;
    
    /** 선배차POP */
    @Schema(description = "선배차POP", nullable = false, example = "")
    private String predeliverygroup;

    /** 배차POP */
    @Schema(description = "배차POP", nullable = false, example = "")
    private String deliverygroup;

    /** 차량번호 */
    @Schema(description = "차량번호", nullable = false, example = "")
    private String carno;    

    /** 미등록일수 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** 상품명 */
    @Schema(description = "상품명", nullable = false, example = "")
    private String skuname;
    
    /** 주문단위 */
    @Schema(description = "주문단위", nullable = false, example = "")
    private String storeruom;
    
    /** 주문수량 */
    @Schema(description = "주문수량", nullable = false, example = "")
    private java.math.BigDecimal storeropenqty;    

    /** 중량 */
    @Schema(description = "중량", nullable = false, example = "")
    private java.math.BigDecimal weightkg;    
    
    /** 변경 배차POP */
    @Schema(description = "변경 배차POP", nullable = false, example = "")
    private String newDeliverygroup;

    /** 변경 차량번호 */
    @Schema(description = "변경 차량번호", nullable = false, example = "")
    private String newCarno;
    
    /** 변경 회차 */
    @Schema(description = "변경 회차", nullable = false, example = "")
    private String newPriority;
    
    /** 성공여부(사용하지 않음) */
    @Schema(description = "성공여부(사용하지 않음)", example = "")
    private int success;
    
    /** 결과에러코드(0:성공) */
    @Schema(description = "결과에러코드(0:성공)", example = "")
    private int errCode;
    
    /** 프로시저 실행 에러코드 */
    @Schema(description = "프로시저 실행 에러코드", example = "")
    private Integer err;
    
    /** 에러메세지 */
    @Schema(description = "에러메세지", example = "")
    private String errMsg;
  
}
