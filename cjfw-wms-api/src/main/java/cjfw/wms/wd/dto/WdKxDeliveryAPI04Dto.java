package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 택배송장발행(온라인) 운송장채번 DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "택배송장발행(온라인)운송장채번 DTO")
public class WdKxDeliveryAPI04Dto extends CommonDto {
	
	/*********************************************************
     * 1.요청
     ********************************************************/ 	
	/** 토큰번호 */
    @Schema(description = "토큰번호")
    private String tokenNum;
    /** 고객ID  */
    @Schema(description = "고객ID")
    private String clntnum;

    

    /*********************************************************
     * 2.응답
     ********************************************************/ 	
    /** 조회결과코드 */
    @Schema(description = "조회결과코드")
    private String resultCd;	
    
    /** 조회결과 */
    @Schema(description = "조회결과")
    private String resultDetail;
    
    /** 운송장번호 */
    @Schema(description = "운송장번호")
    private String invcNo;

    /*********************************************************
     * 3.기타변수
     ********************************************************/ 
	/**시리얼키*/
	@Schema(description = "시리얼키")
	private String serialkey;    
}

