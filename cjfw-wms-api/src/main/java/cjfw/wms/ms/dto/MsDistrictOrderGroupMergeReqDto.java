package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.22 
 * @description :  주문그룹/대표 POP SRM 적재 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.22 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "주문그룹/대표 POP SRM 적재 요청 DTO")
public class MsDistrictOrderGroupMergeReqDto {
	
	@Schema(description = "주문그롭ID", example = "123")
	String ordGrp;
	
	@Schema(description = "배송권역그룹ID", example = "456")
	String dlvgroupId;
	
	@Schema(description = "pop번호", example = "1")
	String popno;
	
	@Schema(description = "행정동 코드", example = "5113038000")
	String hjdongCd;
	
	@Schema(description = "센터코드", example = "2600")
	String dccode;
	
	@Schema(description = "배송권역ID", example = "456")
	String dlvdistrictId;

    @Schema(description = "삭제여부", example = "N")
    private String delYn;

    @Schema(description = "인터페이스 아이디 영업용 : SCM0880, SRM용 : SCM0900", example = "N")
    private String ifId;
}
