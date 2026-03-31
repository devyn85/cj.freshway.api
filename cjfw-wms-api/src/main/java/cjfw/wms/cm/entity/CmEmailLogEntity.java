package cjfw.wms.cm.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.07.28 
 * @description : 이메일 LOG Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
public class CmEmailLogEntity {
	/** 이메일번호 */
	@Schema(description = "이메일번호", example = "47443")
	private String emailNo;
	
	/** 이메일제목 */
	@Schema(description = "이메일제목", example = "2022년06월02일 결품 내역 공유")
	private String title;
	
	/** 이메일내용 */
	@Schema(description = "이메일내용", example = "")
	private String cnts;
	
	/** 발송자이메일주소 */
	@Schema(description = "발송자이메일주소", example = "")
	private String sendrEmailAddr;
	
	/** 수신자명 */
	@Schema(description = "수신자명", example = "홍민지,현창길,조정우")
	private String rcvrNm;
	
	/** 수신자이메일주소 */
	@Schema(description = "수신자이메일주소", example = "")
	private String rcvrEmailAddr;
	
	/** 참조메일주소 */
	@Schema(description = "참조메일주소", example = "")
	private String refEmailAdd;
	
	/** 숨은참조메일주소 */
	@Schema(description = "숨은참조메일주소", example = "")
	private String hidRefMailAddr;
	
	/** 발송결과내용 */
	@Schema(description = "발송결과내용", example = "00")
	private String sendRedCnts;
	
	/** 발송일시 */
	@Schema(description = "발송일시", example = "2022-06-02 오전 6:18:02")
	private String sendDy;
	
	/** 발송자ID */
	@Schema(description = "발송자ID", example = "90")
	private String sendrId;
	
	/** 등록자ID */
	@Schema(description = "등록자ID", example = "ycc2n")
	private String regId;
	
	/** 등록일시 */
	@Schema(description = "등록일시", example = "2022-06-02 오전 6:18:02")
	private String regDtm;
	
	/** 수정자ID */
	@Schema(description = "수정자ID", example = "LOGISONEBATCH")
	private String updId;
	
	/** 수정일시 */
	@Schema(description = "수정일시", example = "2022-06-02 오전 6:18:08")
	private String updDtm;
}