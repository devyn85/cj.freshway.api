package cjfw.wms.cm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.04.30 
 * @description : 코드마스터 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "코드마스터 조회 요청")
public class CmCodeReqDto extends CommonDto {
	/** 고객사코드 */
	@Schema(description = "고객사코드", nullable = false, example = "FW00")
	private String storerkey;
	
	/** 코드리스트 */
	@Schema(description = "코드리스트", example = "PARTSD")
	private String codelist;
	
	/** 기본코드값 */
	@Schema(description = "기본코드값", example = "430293")
	private String basecode;
	
	/** 기본코드설명 */
	@Schema(description = "기본코드설명", example = "소장")
	private String basedescr;
	
	/** 그룹 코드 목록 */
	@Schema(description = "그룹 코드 목록", example = "")
	private List<CmCodeDtlReqDto> codeGrpList;
	
	/** 공통 코드 상세 목록 */
	@Schema(description = "상세 코드 목록", example = "")
	private List<CmCodeDtlReqDto> codeDtlList;
	
	// [WEB_110][8/22] 설정이 가능한 팝업도 조회영역 추가(김영문님) - 상세코드 또는 상세코드명, 사용여부(화면정의서에 맞게 변환해서 사용 중)
	@Schema(description = "삭제 여부", example = "N")
	private String delYn;
	
    @Schema(description = "상세코드 또는 상세코드명", example = "DC0001 또는 CJ대한통운")
    private String searchVal;
}
