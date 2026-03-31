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
@Schema(description = "Fax 전송 요청 DTO")
public class CmFaxReqDto extends CommonDto {
	/** FAX 일련번호 */
	@Schema(description = "FAX 일련번호", example = "145705")
	private String trBatchid;
	
	/** 발송 제목 */
	@Schema(description = "발송 제목", example = "[CJ프레시웨이] 상품/재고 출고 요청 (2025년03월14일)")
	private String trTitle;
	
	/** 발신자 번호 */
	@Schema(description = "발신자 번호", example = "0221496609")
	private String trSendfaxnum;
	
	/** 팩스발송 파일명들 */
	@Schema(description = "팩스발송 파일명들", example = "sample1.hwp|sample2.doc|sample3.xls|sample4.ppt")
	private String trDocname;
	
	/** 수신자 이름 */
	@Schema(description = "수신자 이름", example = "이스트밸리")
	private String trName;
	
	/** 수신자 팩스 번호 */
	@Schema(description = "수신자 팩스 번호", example = "0319995555")
	private String trPhone;
	
	/** 수신자 팩스 번호 */
    @Schema(description = "수신자 팩스 번호2", example = "0319995555")
    private String trPhone2;
    
    /** 발신자 팩스 번호 */
    @Schema(description = "발신자 팩스 번호", example = "0319995555")
    private String sendFaxno;
    
    /** 첨부파일 */
    @Schema(description = "첨부파일")
    private String attchFileName;
}
