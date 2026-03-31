package cjfw.wms.tm.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.04 
 * @description : 정산항목관리 기능을 구현한 Entity Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.04 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "정산항목관리 Entity") 
public class TmManageEntityEntity extends CommonDto {	
	 /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 정산항목코드 */
    @Schema(description = "정산항목코드")
    private String sttlItemCd;

    /** 정산항목설명 */
    @Schema(description = "정산항목설명")
    private String sttlItemDescr;

    /** 지입차적용여부 */
    @Schema(description = "지입차적용여부")
    private String entrustedCarYn;

    /** 고정차적용여부 */
    @Schema(description = "고정차적용여부")
    private String fixCarYn;

    /** 임시차적용여부 */
    @Schema(description = "임시차적용여부")
    private String tmpCarYn;

    /** 실비차적용여부 */
    @Schema(description = "실비차적용여부")
    private String actualCostCarYn;

    /** 여분차량분류1 */
    @Schema(description = "여분차량분류1")
    private String extraCarType1;

    /** 여분차량분류2 */
    @Schema(description = "여분차량분류2")
    private String extraCarType2;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간")
    private String addDate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간")
    private String editDate;

    /** 최초등록자 */
    @Schema(description = "최초등록자")
    private String addWho;
    private String courier;
    private String oldCourier;
    /** 최종변경자 */
    @Schema(description = "최종변경자")
    private String editWho;
    @Schema(description = "정산항목코드")
    private String oldSttlItemCd;
    
    @Schema(description = "월대용차")
    private String mmContractYn;
}
