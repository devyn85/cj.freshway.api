package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : YeoSeungCheol (pw6375@cj.net)
 * @date        : 2025.07.15
 * @description : 센터상품라벨속성 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.15        YeoSeungCheol 		(pw6375@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "센터상품라벨속성 Entity")
public class MsSkuDcLabelEntity extends CommonDto {
	@Schema(description = "시리얼키(PK)")
    private String serialKey;

	@Schema(description = "고객사코드")
    private String storerKey;

	@Schema(description = "센터코드")
    private String dcCode;

	@Schema(description = "상품코드")
    private String sku;

	@Schema(description = "분류(저장조건)")
    private String storageGubun;

	@Schema(description = "삭제 여부")
    private String delYn;

	@Schema(description = "최초등록시간 (자동 생성)")
    private String addDate;

	@Schema(description = "최종변경시간 (자동 갱신)")
	private String editDate;
	
	@Schema(description = "최초등록자")
    private String addWho;

	@Schema(description = "최종변경자")
    private String editWho;
}
