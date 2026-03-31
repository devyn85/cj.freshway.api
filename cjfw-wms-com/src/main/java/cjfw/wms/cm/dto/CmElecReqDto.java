package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.09.22 
 * @description : 전자결재 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.22 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "전자결재 요청 DTO")
public class CmElecReqDto {
	/** 전자결재 요청일자 */
	@Schema(description = "전자결재 요청일자", example = "")
	private String approvalReauestDt;
	
	/** 전자결재 요청번호 */
	@Schema(description = "전자결재 요청번호", example = "")
	private String approvalReauestNo;
	
	/** 전자결재 문서 폼ID */
	@Schema(description = "전자결재 문서 폼ID", example = "")
	private String formId;
	
	/** 전자결재 문서번호 */
	@Schema(description = "전자결재 문서번호", example = "")
	private String docId;
	
	/** 전자결재 상태 */
	@Schema(description = "전자결재 상태", example = "")
	private String status;
	
	/** 결재자(반려자)ID */
	@Schema(description = "결재자(반려자)ID", example = "")
	private String approveId;
	
	/** 결재자(반려자)명 */
	@Schema(description = "결재자(반려자)명", example = "")
	private String approveNm;
	
	/** 멀티 센터코드 */
	@Schema(description = "멀티 센터코드", example = "")
	private String multiDccode;
}