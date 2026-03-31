package cjfw.wms.wd.entity;

import java.sql.Timestamp;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @date : 2025.08.21 
 * @description : 배송라벨출력 STO 라벨인쇄 여부 저장 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                 MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.21 	공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송라벨출력 STO 라벨인쇄 여부 저장 Entity") 
public class WdDeliveryLabelPrintStoEntity extends CommonDto {
    /** invoiceno */
    @Schema(description = "invoiceno", nullable = false, example = "")
    private String invoiceno;

    /** DOCNO */
    @Schema(description = "DOCNO", nullable = false, example = "")
    private String docno;
    

    /** DOCLINE */
    @Schema(description = "DOCLINE", nullable = false, example = "")
    private String docline;
    
    /** stotime */
    @Schema(description = "stotime", nullable = false, example = "")
    private Timestamp stotime;
    

}
