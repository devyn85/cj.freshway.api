package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.07.28 
 * @description : Email/SMS 전송 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Email/SMS 전송 요청 요청")
public class CmSendReqDto extends CommonDto {
	/** 수신자 사용자ID */
	@Schema(description = "수신자 사용자ID", example = "dev01")
	private String rcvrId;
	
	/** 제목 */
	@Schema(description = "제목", example = "2022년06월02일 결품 내역 공유")
	private String title;
	
	/** 내용 */
	@Schema(description = "내용", example = "")
	private String cnts;
	
	/** 발신자핸드폰번호 */
	@Schema(description = "발신자핸드폰번호", example = "")
	private String sendPhone;
	
	/** 수신자명 */
	@Schema(description = "수신자명", example = "홍길동")
	private String rcvrNm;
	
	/** 수신자이메일주소 */
	@Schema(description = "수신자이메일주소", example = "")
	private String rcvrEmail;
	
	/** 수신자핸드폰번호 */
	@Schema(description = "수신자핸드폰번호", example = "")
	private String rcvrPhone;
}
