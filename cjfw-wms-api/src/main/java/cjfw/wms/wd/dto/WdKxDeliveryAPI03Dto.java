package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 택배송장발행(온라인) 주소정제 DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "택배송장발행(온라인)주소정제 DTO")
public class WdKxDeliveryAPI03Dto extends CommonDto {
	
	/*********************************************************
     * 1.요청
     ********************************************************/ 	
	/** 토큰번호 */
    @Schema(description = "토큰번호")
    private String tokenNum;
    /** 고객ID  */
    @Schema(description = "고객ID")
    private String clntnum;

    /** 협력사 코드   */
    @Schema(description = "협력사 코드")
    private String clntmgmcustcd;

    /** 중개업체ID  */
    @Schema(description = "중개업체ID ")
    private String userId;
    
    /** 주소 */
    @Schema(description = "주소")
    private String address;

    /*********************************************************
     * 2.응답
     ********************************************************/ 	
    /** 조회결과코드 */
    @Schema(description = "조회결과코드")
    private String resultCd;	
    
    /** 조회결과 */
    @Schema(description = "조회결과")
    private String resultDetail;
    
    /** CLSFCD 도착지 코드 */
    @Schema(description = "도착지 코드")
    private String clsfcd;

    /** SUBCLSFCD 도착지 서브 코드 */
    @Schema(description = "도착지 서브 코드")
    private String subclsfcd;

    /** CLSFADDR 주소 약칭 */
    @Schema(description = "주소 약칭")
    private String clsfaddr;

    /** CLLDLVBRANNM 배송집배점 명 */
    @Schema(description = "배송집배점 명")
    private String clldlvbrannm;

    /** CLLDLVEMPNM 배송SM명 */
    @Schema(description = "배송SM명")
    private String clldlvempnm;

    /** CLLDLVEMPNICKNM SM분류코드 */
    @Schema(description = "SM분류코드")
    private String clldlvempnicknm;

    /** RSPSDIV 권역 구분 */
    @Schema(description = "권역 구분")
    private String rspsdiv;

    /** P2PCD P2P코드 */
    @Schema(description = "P2P코드")
    private String p2pcd;


    /*********************************************************
     * 3.기타변수
     ********************************************************/ 
	/**시리얼키*/
	@Schema(description = "시리얼키")
	private String serialkey;
	
	/**오류여부*/
	@Schema(description = "오류여부")
	private String rcptErrYn;
	
	/**오류메세지*/
	@Schema(description = "오류메세지")
	private String rcptErrMsg;		
}

