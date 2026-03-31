package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.24
 * @description : 기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 Detail Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsWdAppResDetailDto extends CommonProcedureDto {
	
	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/* 물류센터명 */
	@Schema(description = "물류센터명")
	private String dcname;

	/* 고객사코드 */
	@Schema(description = "고객사코드")
	private String storerkey;

	/* POP그룹명 */
	@Schema(description = "POP그룹명")
	private String popGroup;

	/* POP번호 */
	@Schema(description = "POP번호")
	private String popno;

	/* 사용유무 */
	@Schema(description = "사용유무")
	private String useYn;

	/* 등록자 */
	@Schema(description = "등록자")
	private String addwho;

	/* 등록일시 */
	@Schema(description = "등록일시")
	private String adddate;

	/* 수정자 */
	@Schema(description = "수정자")
	private String editwho;

	/* 수정일시 */
	@Schema(description = "수정일시")
	private String editdate;
	
	/* 등록자 ID */
	@Schema(description = "등록자 ID")
	private String addId;
	
	/* 수정자 ID */
	@Schema(description = "수정자 ID")
	private String updId;

    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";

}
