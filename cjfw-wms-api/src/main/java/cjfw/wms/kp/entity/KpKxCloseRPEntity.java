package cjfw.wms.kp.entity;



import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkYoSep(dytpq362@cj.net) 
 * @date : 2025.12.09 
 * @description : 협력사반출 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                 MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.09    박요셉 (dytpq362@cj.net) 생성 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Kx마감진행 협력사 반출확인 Entity") 
public class KpKxCloseRPEntity extends CommonDto {
    
    @Schema(description = "물류센터")
    private String dccode;

    @Schema(description = "창고코드")
    private String organize;
    
    @Schema(description = "문서번호")
    private String docno;
    
    @Schema(description = "품목번호")
    private String docline;
    
    @Schema(description = "상품코드")
    private String sku;

    @Schema(description = "DMD_TIMESTAMP")
    private String dmdTimestamp;

    @Schema(description = "DMH_TIMESTAMP")
    private String dmhTimestamp;

}
