package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 택배송장발행(온라인) 토큰발급 DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "택배송장발행(온라인)토큰발급 DTO")
public class WdKxDeliveryAPI01Dto extends CommonProcedureDto {
	/*********************************************************
     * 1.요청
     ********************************************************/ 	
    /** 고객사ID */
    @Schema(description = "고객사ID")
    private String custId;
    
    /** 사업자 번호  */
    @Schema(description = "사업자 번호")
    private String bizRegNum;
    
    /** 고객ID  */
    @Schema(description = "고객ID ")
    private String userId;
    
    /*********************************************************
	 * 2.응답
	 ********************************************************/
    /** 조회결과코드 */
    @Schema(description = "조회결과코드")
    private String resultCd;	
    
    /** 조회결과 */
    @Schema(description = "조회결과")
    private String resultDetail;
    
    /** 토큰번호 */
    @Schema(description = "토큰번호")
    private String tokenNum;
    
    /** 보안토큰유효기간 */
    @Schema(description = "보안토큰유효기간")
    private String tokenExprtnDtm;   
    
    /** 공지사항 */
    @Schema(description = "공지사항")
    private String notice;   
    
    /** KX고객사ID */
    @Schema(description = "KX고객사ID")
    private String kxCustId;
}
