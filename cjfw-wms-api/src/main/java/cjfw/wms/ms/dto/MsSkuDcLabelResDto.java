package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.15 
 * @description : 센터상품라벨속성 조회/저장 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.15 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "센터상품라벨속성 조회/저장 응답 DTO")
public class MsSkuDcLabelResDto {
    @Schema(description = "시리얼 키")
    private String serialKey;

    @Schema(description = "체크 여부")
    private String checkYn;

    @Schema(description = "고객사코드")
    private String storerKey;

    @Schema(description = "센터코드")
    private String dcCode;

    @Schema(description = "상품 코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "분류(저장조건)")
    private String storageGubun;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "최초 등록자")
    private String addWho;
    
    @Schema(description = "최초 등록 시간")
    private String addDate;

    @Schema(description = "최종 변경자")
    private String editWho;
    
    @Schema(description = "최종 변경 시간")
    private String editDate;

    @Schema(description = "저장 상태")
    private String saved;
    
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
