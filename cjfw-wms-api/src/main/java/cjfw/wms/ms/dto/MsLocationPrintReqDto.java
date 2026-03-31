package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.24
 * @description : 기준정보 > 물류센터 정보 > 로케이션 라벨 출력 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.24 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "기준정보 > 센터기준정보 > 로케이션 바코드 출력 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsLocationPrintReqDto extends CommonProcedureDto {
	
	/* dccode */
	@Schema(description = "dccode")
	private String dccode;

	/* zone */
	@Schema(description = "zone")
	private String zone;

	/* whareafloor */
	@Schema(description = "whareafloor")
	private String whareafloor;

	/* loctype */
	@Schema(description = "loctype")
	private String loctype;

	/* loccategory */
	@Schema(description = "loccategory")
	private String loccategory;

	/* loclevel */
	@Schema(description = "loclevel")
	private String loclevel;

	/* locflag */
	@Schema(description = "locflag")
	private String locflag;
}
