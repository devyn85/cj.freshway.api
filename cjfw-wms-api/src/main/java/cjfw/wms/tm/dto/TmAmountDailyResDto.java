package cjfw.wms.tm.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.08.01
 * @description : 일자별 수당관리 RES DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "일자별 수당관리 응답 DTO")
public class TmAmountDailyResDto extends CommonDto {

    /**serialkey*/
    @Schema(description = "serialkey")
    private String serialkey;

    /**물류센터*/
    @Schema(description = "물류센터")
    private String dccode;

    /**운송사코드*/
    @Schema(description = "운송사코드")
    private String courier;

    /**운송사코드명*/
    @Schema(description = "운송사코드명")
    private String couriername;

    /**차량번호*/
    @Schema(description = "차량번호")
    private String carno;

    /**정산항목코드*/
    @Schema(description = "정산항목코드")
    private String sttlItemCd;

    /**적용유형*/
    @Schema(description = "적용유형")
    private String applyType;

    /**금액*/
    @Schema(description = "금액")
    private BigDecimal amount;

    /**시작일자*/
    @Schema(description = "수당일자")
    private String allowanceDate;

    /**비고*/
    @Schema(description = "비고")
    private String rmk;

    /**등록일자*/
    @Schema(description = "등록일자")
    private String adddate;

    /**수정일자*/
    @Schema(description = "수정일자")
    private String editdate;

    /**등록자*/
    @Schema(description = "등록자")
    private String addwho;
    
    /**등록자*/
    @Schema(description = "등록자")
    private String addwhoName;

    /**수정자*/
    @Schema(description = "수정자")
    private String editwho;
    
    /**수정자*/
    @Schema(description = "수정자")
    private String editwhoName;
    
    /**톤수*/
    @Schema(description = "톤수")
    private String carcapacity;

    /**운송사*/
    @Schema(description = "운송사")
    private String caragentkey;

    /**2차운송사명*/
    @Schema(description = "2차운송사명")
    private String caragentname;
    
    /**2차운송사코드*/
    @Schema(description = "2차운송사코드")
    private String caragentcd;
    
    /**계약유형*/
    @Schema(description = "계약유형")
    private String contracttype;
    
    /**첨부파일 갯수*/
    @Schema(description = "첨부파일 갯수")
    private Integer attachment;

    /** GridRow 저장 구분 */
    @Schema(description = "GridRow 저장 구분", example = "U", allowableValues = {"I", "U", "D"})
    private String rowStatus;
    
    private String errYn; 
    private String valChk; 
    private String errMsg;
    
}
