package cjfw.wms.st.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.09.12
 * @description : 저장품재고조회(CJ대한통운제공) 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.12    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
 */
@Data
@Schema(description = "저장품재고조회(CJ대한통운제공) 조회 결과")
public class StStockForCJLResDto extends CommonDto {
    
	/** 제품코드 */
	@Schema(description = "제품코드", nullable = true, example = "")
	private String sku;
	
	/** 제품명 */
	@Schema(description = "제품명", nullable = true, example = "")
	private String skuname;
	
	/** 유통기한 */
	@Schema(description = "유통기한", nullable = true, example = "")
	private String lot4;
	
	/** 출고기한 */
	@Schema(description = "출고기한", nullable = true, example = "")
	private String tare;

	/** 보관센터 */
	@Schema(description = "보관센터", nullable = true, example = "")
	private String custname;
	
	/** 상태 */
	@Schema(description = "상태", nullable = true, example = "")
	private String flag;
	
	/** 총재고량 */
	@Schema(description = "총재고량", nullable = true, example = "")
	private String qty;
	
	/** 가용재고량 */
	@Schema(description = "가용재고량", nullable = true, example = "")
	private String locqty;
	
	/** 중량 */
	@Schema(description = "중량", nullable = true, example = "")
	private String lot3;
	
	/** 제조일자 */
	@Schema(description = "제조일자", nullable = true, example = "")
	private String lot1;
	
	/** 입고일자 */
	@Schema(description = "입고일자", nullable = true, example = "")
	private String lot5;
	
	/** 물류센터 */
	@Schema(description = "물류센터", nullable = true, example = "")
	private String sl;
	
	/** 1000 */
	@Schema(description = "1000", nullable = true, example = "")
	private String pl;
	
	/**
     * API 응답값의 문자열 공백을 제거합니다.
     */
    public void trimFields() {
        if (this.lot1 != null) this.lot1 = this.lot1.trim();
        if (this.lot3 != null) this.lot3 = this.lot3.trim();
        if (this.lot4 != null) this.lot4 = this.lot4.trim();
        if (this.lot5 != null) this.lot5 = this.lot5.trim();
        if (this.custname != null) this.custname = this.custname.trim();
        if (this.sl != null) this.sl = this.sl.trim();
        if (this.pl != null) this.pl = this.pl.trim();
        if (this.sku != null) this.sku = this.sku.trim();
        if (this.flag != null) this.flag = this.flag.trim();
    }

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
