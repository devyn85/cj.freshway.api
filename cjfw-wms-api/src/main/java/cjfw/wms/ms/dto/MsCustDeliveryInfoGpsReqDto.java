package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.06.19 
 * @description : GPS좌표등록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "GPS좌표등록 요청 목록 DTO")
public class MsCustDeliveryInfoGpsReqDto extends CommonDto {	
	/** processType */
	@Schema(description = "processtype", example = "SPMS_CUSTDLVINFO_EXLCHK")
	private String processtype;
	
	/** 거래처 유형 */
	@Schema(description = "거래처 유형", example = "C")
	private String custtype;
	
	/** 고객코드 */
	@Schema(description = "고객코드", example = "12340")
	private String custkey;
	
	/** 위도 */
	@Schema(description = "위도", example = "35.2272799475294")
	private String latitude;
	
	/** 경도 */
	@Schema(description = "경도", example = "128.635822693316")
	private String longitude;
	
	/** 주소 */
	@Schema(description = "주소", example = "")
	private String address;
	
	/** 주소 */
	@Schema(description = "상세주소", example = "")
	private String address2;
}
