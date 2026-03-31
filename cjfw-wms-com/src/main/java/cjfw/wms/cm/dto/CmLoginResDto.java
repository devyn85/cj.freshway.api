package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.10.01 
 * @description : 로그인 정보 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.01 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "로그인 정보 응답 DTO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CmLoginResDto {
	
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "100004")
	private String serialkey;
	
	/** 사용자ID */
	@Schema(description = "사용자ID", example = "dev01")
	private String userId;
	
	/** 사용자명 */
	@Schema(description = "사용자명", example = "홍길동")
	private String userNm;
	
	/** 핸드폰번호 */
	@Schema(description = "핸드폰번호", example = "010-2222-3333")
	private String handphoneNo;

	/** 중복 개수 */
    @Schema(description = "중복 개수", nullable = false, example = "0")
    private String dupCnt;
    
}
