package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.06.20 
 * @description : 사용자 센터 권한 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "사용자 센터 권한 응답 DTO")
public class CmUserManagerConnectResDto {
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "24502")
	private String serialkey;
	
	/** 센터코드 */
	@Schema(description = "센터코드", example = "2600")
	private String dccode;
	
	/** 센터명 */
	@Schema(description = "센터명", example = "이천물류센터")
	private String dcname;
	
	/** 고객사코드 */
	@Schema(description = "고객사코드", example = "FW00")
	private String storerkey;
	
	/** 창고 */
	@Schema(description = "창고", example = "2170")
	private String organize;
	
	/** 구역 */
	@Schema(description = "구역", example = "1000")
	private String area;
	
	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "Y")
	private String delYn;
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}