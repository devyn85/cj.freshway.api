package cjfw.wms.om.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.20 
 * @description : 일배협력사별주문현황 주문현황 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.20 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "일배협력사별주문현황 주문현황 결과")
public class OmOrderCustDailyResDto {
	/**
    * 협력사코드
    */
   @Schema(description = "협력사코드", example = "CUST001")
   private String fromCustkey;
   /**
    * 협력사명
    */
   @Schema(description = "협력사명", example = "ABC협력사")
   private String fromCustname;
   /**
    * 오더건수
    */
   @Schema(description = "오더건수", example = "100")
   private String ordercnt;
   /**
    * 주문중량(kg)
    */
   @Schema(description = "주문중량(kg)", example = "1000.50")
   private String dpConfirmweight;
   /**
    * 출고중량(kg)
    */
   @Schema(description = "출고중량(kg)", example = "950.25")
   private String wdConfirmweight;
   /**
    * 결품건수
    */
   @Schema(description = "결품건수", example = "5")
   private String cancelcnt;
}
