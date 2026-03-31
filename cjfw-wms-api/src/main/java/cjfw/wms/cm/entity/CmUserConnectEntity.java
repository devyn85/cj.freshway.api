package cjfw.wms.cm.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.09.05 
 * @description : 사용자접속권한 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.05 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
public class CmUserConnectEntity {
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "24502")
	private String serialkey;
	
	/** 사용자아이디 */
	@Schema(description = "사용자아이디", example = "dev01")
	private String userId;
	
	/** 센터코드 */
	@Schema(description = "센터코드", example = "2600")
	private String dccode;
	
	/** 고객사코드 */
	@Schema(description = "고객사코드", example = "FW00")
	private String storerkey;
	
	/** 창고 */
	@Schema(description = "창고", example = "2170")
	private String organize;
	
	/** 구역 */
	@Schema(description = "구역", example = "1000")
	private String area;
	
	/** 권한코드 */
	@Schema(description = "권한코드", example = "00")
	private String authority;
	
	/** 접속서버 */
	@Schema(description = "접속서버", example = "")
	private String connServer;
	
	/** 접속데이터베이스 */
	@Schema(description = "접속데이터베이스", example = "")
	private String connDb;
	
	/** 접속유형 */
	@Schema(description = "접속유형", example = "")
	private String connType;
	
	/** 상태 */
	@Schema(description = "상태", example = "00")
	private String status;
	
	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "Y")
	private String delYn;
	
	/** 데이터흐름제어 */
	@Schema(description = "데이터흐름제어", example = "")
	private String trafficcop;
	
	/** 아카이브제어 */
	@Schema(description = "아카이브제어", example = "")
	private String archivecop;
	
	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;
	
	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;
	
	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "dev01")
	private String addwho;
	
	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "dev01")
	private String editwho;
}