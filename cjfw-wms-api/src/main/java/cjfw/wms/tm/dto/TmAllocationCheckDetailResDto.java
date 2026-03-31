package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.30 
 * @description : 배차마스터체크결과 조회 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04 ParkJinWoo 생성
 */
@Data
@Builder
@Schema(description = "배차작업로그 요청 DTO")
public class TmAllocationCheckDetailResDto  {
	 @Schema(description = "메시지")
     private String message;

	 /**checkKey1*/
     @Schema(description = "checkKey1")
     private String checkKey1;

     /**checkKey2*/
     @Schema(description = "checkKey2")
     private String checkKey2;

     /**checkKey3*/
     @Schema(description = "checkKey3")
     private String checkKey3;
}
