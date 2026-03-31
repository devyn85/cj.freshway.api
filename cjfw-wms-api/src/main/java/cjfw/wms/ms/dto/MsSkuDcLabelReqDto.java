package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.15 
 * @description : 센터상품라벨속성 조회/저장 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "센터상품라벨속성 조회 요청 DTO")
public class MsSkuDcLabelReqDto extends CommonDto {
	@Schema(description = "데이터번호(PK)")
    private String serialKey;
	
    @Schema(description = "고객사코드", example = "FW00")
    private String storerKey;

    @Schema(description = "센터코드", example = "2600")
    private String dcCode;

    @Schema(description = "단일 SKU")
    private String sku;

    @Schema(description = "분류(저장조건)")
    private String storageGubun;

    @Schema(description = "삭제 여부")
    private String delYn;
}
