package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.12.02 
 * @description : 신규 마스터 정보 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "신규 마스터 정보 응답")
public class KpDcMonitoringResDto {

	/** 구분 */
	@Schema(description = "구분", example = "")
	private String gubun;
	
	/** 구분명 */
	@Schema(description = "구분명", example = "")
	private String gubunNm;
	
	/** 구분타입 */
	@Schema(description = "구분타입", example = "")
	private String gubunType;
	
	/** 이전 개수 */
	@Schema(description = "이전 개수", example = "")
	private String preCnt;
	
	/** 현재 개수 */
	@Schema(description = "현재 개수", example = "")
	private String curCnt;
	
	/** 코드 */
	@Schema(description = "코드", example = "")
	private String code;
	
	/** 명칭 */
	@Schema(description = "명칭", example = "")
	private String name;

}
