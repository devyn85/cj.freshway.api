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
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.08.01
 * @description : 차량별 수당관리 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량별 수당관리 요청 DTO")
public class TmAmountByCarReqDto extends CommonDto {

    /**물류센터*/
    @Schema(description = "물류센터")
    private String fixdccode;
    
    /**시작일자*/
    @Schema(description = "시작일자")
    private String fromdt;

    /**종료일자*/
    @Schema(description = "종료일자")
    private String todt;

    /**정산항목코드*/
    @Schema(description = "정산항목코드")
    private String sttlitemcd;

    /**운송사코드*/
    @Schema(description = "운송사코드")
    private String courier;

    /**차량번호*/
    @Schema(description = "차량번호")
    private String carno;

    /**톤수*/
    @Schema(description = "톤수")
    private String carcapacity;

    /**제외할 시리얼키*/
    @Schema(description = "제외할 시리얼키")
    private List<String> excludeKeys;
    
    /**물류센터*/
    @Schema(description = "물류센터")
    private String dcCode;

    /**계약유형*/
    @Schema(description = "계약유형")
    private String contractType;

    /**2차운송사*/
    @Schema(description = "2차운송사")
    private String caragentkey;
    
    /**데이터번호*/
    @Schema(description = "데이터번호")
    private String serialkey;
    
    /**헤더ID*/
    @Schema(description = "")
    private String serialkeyH;
    
	/** 그리드 저장용 리스트 */
	@Schema(description = "그리드 저장용 리스트", nullable = false, example = "")
	private List<TmAmountByCarResDto> saveList;

}
