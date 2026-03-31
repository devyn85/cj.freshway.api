package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.07.28 
 * @description : SMS 발송 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
public class CmKkoMsgSendEntity {
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "86")
	private String serialKey;
	
	/** 전송일자 */
	@Schema(description = "전송일자", example = "20151109171008")
	private String sendDt;
	
	/** 메세지유형 */
	@Schema(description = "메세지유형", example = "SMS")
	private String messageType;
	
	/** 전송그룹 */
	@Schema(description = "전송그룹", example = "SCM")
	private String sendGroup;
	
	/** 전송번호 */
	@Schema(description = "전송번호", example = "")
	private String sendPhone;
	
	/** 전송자 */
	@Schema(description = "전송자", example = "시스템")
	private String sendName;
	
	/** 제목 */
	@Schema(description = "제목", example = "자동발주")
	private String sendTitle;
	
	/** 메세지 */
	@Schema(description = "메세지", example = "장성 D-2 17:10 STO(하선정) 수행되었습니다.[성공:3,실패0]")
	private String sendMessage;
	
	/** 수신그룹 */
	@Schema(description = "수신그룹", example = "SCM")
	private String receiveGroup;
	
	/** 수신자 */
	@Schema(description = "수신자", example = "")
	private String receivePhone;
	
	/** 수신자명 */
	@Schema(description = "수신자명", example = "신한섭")
	private String receiveName;
	
	/** 상태 */
	@Schema(description = "상태", example = "90")
	private String status;
	
	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "N")
	private String delYn;
	
	/** 데이터흐름제어 */
	@Schema(description = "데이터흐름제어", example = "")
	private String trafficCop;
	
	/** 아카이브제어 */
	@Schema(description = "아카이브제어", example = "")
	private String archiveCop;
	
	/** 인터페이스 플래그 */
	@Schema(description = "인터페이스 플래그", example = "N")
	private String ifFlag;
	
	/** 인터페이스일자 */
	@Schema(description = "인터페이스일자", example = "2015-12-18 오전 10:18:08")
	private String ifDate;
	
	/** 인터페이스 메모 */
	@Schema(description = "인터페이스 메모", example = "")
	private String ifMemo;
	
	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "2015-11-09 오후 5:10:08")
	private String addDate;
	
	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "2015-11-09 오후 5:11:03")
	private String editDate;
	
	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "SYSTEM")
	private String addWho;
	
	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "LOGISONEBATCH")
	private String editWho;
}