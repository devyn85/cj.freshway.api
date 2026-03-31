package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.11 
 * @description : 통합수당관리 조회 요청Dto 기능을 구현한 Java Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.11 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "배송클레임정보 조회 요청Dto")
public class TmEntityRuleMasterListReqDto extends CommonDto {
	
	/**저장 리스트*/
	@Schema(description = "저장리스트")
	List<TmEntityRuleMasterListResDto> saveList;

  	 /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 정산항목코드 */
    @Schema(description = "정산항목코드")
    private String sttlItemCd;

    /** 창고코드 */
    @Schema(description = "창고코드")
    private String area;

    /** 운송사코드 */
    @Schema(description = "운송사코드")
    private String courier;

    /** 톤수 */
    @Schema(description = "톤수")
    private String ton;

    /** 마감유형 */
    @Schema(description = "마감유형")
    private String closeType;

    /** 기준 */
    @Schema(description = "기준")
    private String base;

    /** 적용시작일자(YYYYMMDD) */
    @Schema(description = "적용시작일자(YYYYMMDD)")
    private String standardDate;

  
    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;
    /** 비고 */
    @Schema(description = "비고")
    private String rmk;

    /** 이력조회 */
    @Schema(description = "이력조회")
    private String serialYn;
    
    /** 실비,임시 구분용 */
    @Schema(description = "실비,임시 구분용")
    private String contractYn;
    private String importCarrierNm;
    private String importCarrier;
    /** 이력조회 */
    @Schema(description = "시작일자")
    private String standardFromDate;
    /** 종료일자 */
    @Schema(description = "종료일자")
    private String standardToDate;
    
    
}
