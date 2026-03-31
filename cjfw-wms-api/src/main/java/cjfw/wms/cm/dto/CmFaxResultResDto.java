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
 * @date : 2025.09.01 
 * @description : Fax 전송 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.01 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Fax 전송 결과 DTO")
public class CmFaxResultResDto extends CommonDto {
	/** FAX 일련번호 */
	@Schema(description = "FAX 일련번호")
	private String trBatchid;
	
	/** 발송일시 */
	@Schema(description = "발송일시")
	private String trSenddate;
	
	/** 발송상태 */
	@Schema(description = "발송상태")
	private String trSendstatNm;
	
	/** 실제 발송 시간 */
	@Schema(description = "실제 발송 시간")
	private String trSendtime;
	
	/** 발송결과 */
	@Schema(description = "발송결과")
	private String trRsltstatNm;
	
	/** 발송 제목 */
	@Schema(description = "발송 제목")
	private String trTitle;
	
	/** 발신자 이름 */
    @Schema(description = "발신자 이름")
    private String trSendname;
    
    /** 발신자 팩스 번호 */
    @Schema(description = "발신자 팩스 번호")
    private String trSendfaxnum;
    
    /** 수신자 이름 */
    @Schema(description = "수신자 이름")
    private String trName;
    
    /** 수신자 팩스 번호 */
    @Schema(description = "수신자 팩스 번호")
    private String trPhone;
}
