package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.29 
 * @description : 업로드 파일 정보 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "업로드 파일 정보 요청 DTO")
public class CmFileUploadReqDto {
	/** 게시판타입 */
	@Schema(description = "게시판타입", example = "board")
	private String type;

	/** 게시글번호 */
	@Schema(description = "게시글번호", example = "2023050300002")
	private String refKey;
	
	/** 멀티 게시글번호 */
	@Schema(description = "멀티 게시글번호", example = "2023050300002,202512010001")
	private String multiRefKey;
}