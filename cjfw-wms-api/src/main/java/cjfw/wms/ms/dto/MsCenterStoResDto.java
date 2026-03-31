package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.08.04 
 * @description : 센터이체마스터 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsCenterStoResDto {
	
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "")
	private String serialkey;

	/** 센터마감유형 */
	@Schema(description = "센터마감유형", example = "")
	private String dcClosetype;
		
	/** 공급우선순위 */
	@Schema(description = "공급우선순위", example = "")
	private String fromPriority;
	
	/** 공급센터(이천) */
    @Schema(description = "공급센터(이천)")
    private String fromDccode2600;
    
    /** 공급센터(이천)명 */
    @Schema(description = "공급센터(이천)명")
    private String fromDccode2600Nm;
    
    /** 공급센터(이천)serialkey */
    @Schema(description = "공급센터(이천)serialkey")
    private String fromDccode2600Sk;

    /** 공급센터(수원) */
    @Schema(description = "공급센터(수원)")
    private String fromDccode2620;
    
    /** 공급센터(수원)명 */
    @Schema(description = "공급센터(수원)명")
    private String fromDccode2620Nm;
    
    /** 공급센터(이천)serialkey */
    @Schema(description = "공급센터(수원)serialkey")
    private String fromDccode2620Sk;

    /** 공급센터(수원2) */
    @Schema(description = "공급센터(수원2)")
    private String fromDccode2630;
    
    /** 공급센터(수원2)명 */
    @Schema(description = "공급센터(수원2)명")
    private String fromDccode2630Nm;

    /** 공급센터(이천)serialkey */
    @Schema(description = "공급센터(수원2)serialkey")
    private String fromDccode2630Sk;

    /** 공급센터(동탄) */
    @Schema(description = "공급센터(동탄)")
    private String fromDccode2650;

    /** 공급센터(동탄)명 */
    @Schema(description = "공급센터(동탄)명")
    private String fromDccode2650Nm;

    /** 공급센터(이천)serialkey */
    @Schema(description = "공급센터(동탄)serialkey")
    private String fromDccode2650Sk;

    /** 공급센터(동탄2) */
    @Schema(description = "공급센터(동탄2)")
    private String fromDccode2660;
    
    /** 공급센터(동탄2)명 */
    @Schema(description = "공급센터(동탄2)명")
    private String fromDccode2660Nm;

    /** 공급센터(이천)serialkey */
    @Schema(description = "공급센터(동탄2)serialkey")
    private String fromDccode2660Sk;

    /** 공급센터(장성) */
    @Schema(description = "공급센터(장성)")
    private String fromDccode2230;

    /** 공급센터(장성)명 */
    @Schema(description = "공급센터(장성)명")
    private String fromDccode2230Nm;

    /** 공급센터(이천)serialkey */
    @Schema(description = "공급센터(장성)serialkey")
    private String fromDccode2230Sk;

    /** 공급센터(양산) */
    @Schema(description = "공급센터(양산)")
    private String fromDccode2260;
	
    /** 공급센터(양산)명 */
    @Schema(description = "공급센터(양산)명")
    private String fromDccode2260Nm;
    
    /** 공급센터(이천)serialkey */
    @Schema(description = "공급센터(양산)serialkey")
    private String fromDccode2260Sk;
    
    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

}