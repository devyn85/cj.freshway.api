package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2026.02.20 
 * @description : 배송이슈 사진 파일 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.20 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "배송이슈 사진 파일 조회 응답")
public class CmIssuePicturePopupResDto {
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;
    
    /** 배송일 */
    @Schema(description = "배송일")
    private String deliverydt;
    
    /** 고객유형 */
    @Schema(description = "고객유형")
    private String custtype;
    
    /** 실착지코드 */
    @Schema(description = "실착지코드")
    private String truthcustkey;

    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carno;
    
    /** 회차 */
    @Schema(description = "회차")
    private String priority;
    
    /** 사진파일명 */
    @Schema(description = "사진파일명")
    private String filename;
    
    /** 사진파일경로 */
    @Schema(description = "사진파일경로")
    private String filelocation;

}