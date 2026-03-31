package cjfw.wms.kp.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.18 
 * @description : 분류피킹(출고센터) 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.18 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "분류피킹(출고센터) 목록 결과")
public class KpWdRequestCancelqtyResTab2Dto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
    /**구분(1:취소,2:누락분) */
    @Schema(description = "구분(1:취소,2:누락분)")
    private String flag;
    
    /**상태*/
    @Schema(description = "상태")
    private String reqStatus;

    /**상태*/
    @Schema(description = "상태")
    private String reqStatusNm;
    
    /**이체상태*00:등록,90:확정) */
    @Schema(description = "이체상태*00:등록,90:확정)")
    private String stoStatus;
    
    /**배송일자 */
    @Schema(description = "배송일자")
    private String deliverydate;
    
    /**PRIORITY */
    @Schema(description = "PRIORITY", example = "1")
    private String priority;
	
    /**문서일자 */
    @Schema(description = "문서일자")
    private String docdt;
	
    /**처리구분 */
    @Schema(description = "처리구분", example = "00")
    private String processtype;

    /**요청번호 */
    @Schema(description = "요청번호", example = "S12345")
    private String serialkey;

    /**문서번호 */
    @Schema(description = "문서번호", example = "D12345")
    private String docno;

    /**문서라인 */
    @Schema(description = "문서라인", example = "1")
    private String docline;

    /**저장품누락요청센터 */
    @Schema(description = "저장품누락요청센터", example = "DC01")
    private String missDccode;
    
    /**출고센터 */
    @Schema(description = "출고센터", example = "DC01")
    private String fromDccode;

    /**출고센터로케이션 */
    @Schema(description = "출고센터로케이션", example = "A01-01")
    private String fromLoc;

    /**상품코드 */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;
    
    /**상품명 */
    @Schema(description = "상품명", example = "샘플상품")
    private String skuname;

    /**단위 */
    @Schema(description = "단위", example = "EA")
    private String uom;

    /**모바일요청수량 */
    @Schema(description = "모바일요청수량", example = "10")
    private BigDecimal missOrderqty;

    /**STO요청수량 */
    @Schema(description = "STO요청수량", example = "10")
    private BigDecimal stoOrderqty;
    
    /**수급센터처리수량 */
    @Schema(description = "수급센터처리수량", example = "8")
    private BigDecimal moveQty;

    /**수급센터현재고 */
    @Schema(description = "수급센터현재고", example = "100")
    private BigDecimal stockqty;
    
    /**이체센터현재고 */
    @Schema(description = "이체센터현재고", example = "100")
    private BigDecimal wdStockqty;
    
    /**주문수량 */
    @Schema(description = "주문수량", example = "100")
    private BigDecimal orderqty;
    
    /**CONFIRMQTY */
    @Schema(description = "CONFIRMQTY", example = "100")
    private BigDecimal confirmqty;

    /**moveDt */
    @Schema(description = "moveDt", example = "100")
    private String moveDt;

    /**deliverygroup */
    @Schema(description = "deliverygroup", example = "STO요청용")
    private String deliverygroup;

    /**배송라벨 */
    @Schema(description = "배송라벨", example = "STO요청용")
    private String dlvLabel;
    
    /**이체센터 */
    @Schema(description = "이체센터", example = "2620")
    private String wdDccode;
        
    /**
     * MISS_DOCDT
     */
    @Schema(description = "MISS_DOCDT", example = "20240101")
    private String missDocdt;
    

    /**
     * ADDDATE
     */
    @Schema(description = "ADDDATE", example = "")
    private String adddate;
    
    /**
     * EDITDATE
     */
    @Schema(description = "EDITDATE", example = "")
    private String editdate;
    
    /**
     * ADDWHO
     */
    @Schema(description = "ADDWHO", example = "")
    private String addwho;
    
    /**
     * EDITWHO
     */
    @Schema(description = "EDITWHO", example = "")
    private String editwho;
    
    /**
     * ADDWHONM
     */
    @Schema(description = "ADDWHONM", example = "")
    private String addwhonm;
    
    /**
     * EDITWHONM
     */
    @Schema(description = "EDITWHONM", example = "")
    private String editwhonm;
}
