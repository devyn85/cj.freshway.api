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
 * @date : 2025.09.24
 * @description : 기준정보 > 물류센터 정보 > 로케이션 라벨 출력 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.24 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "기준정보 > 센터기준정보 > 로케이션 바코드 출력 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsLocationPrintResDto extends CommonProcedureDto {
	
	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dccode;

	/* 02. 물류센터명 */
	@Schema(description = "02. 물류센터명")
	private String dcname;

	/* 03. 로케이션 그룹 */
	@Schema(description = "03. 로케이션 그룹")
	private String locGroup;

	/* 04. 로케이션 그룹명 */
	@Schema(description = "04. 로케이션 그룹명")
	private String locName;

	/* 05. 단수 */
	@Schema(description = "05. 단수")
	private String layer;

	/* 06. 피킹존 */
	@Schema(description = "06. 피킹존")
	private String pickingZone;

	/* 단수목록(라벨 출력용) */
	@Schema(description = "단수목록(라벨 출력용)")
	private String layerList;
	
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
}
