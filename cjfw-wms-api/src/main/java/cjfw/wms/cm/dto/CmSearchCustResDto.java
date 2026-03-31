package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.08 
 * @description : 거래처 검색 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "거래처 검색 응답")
public class CmSearchCustResDto {
	/** 거래처코드 */
	@Schema(description = "거래처코드", nullable = false, example = "1006692")
	private String code;
	
	/** 거래처명 */
	@Schema(description = "거래처명", nullable = false, example = "시립보라매병원")
	private String name;
	
	/** 기본주소 */
	@Schema(description = "기본주소", nullable = false, example = "개인정보삭제")
	private String address1;
	
	/** 거래처코드 */
	@Schema(description = "거래처코드", nullable = false, example = "1006692")
	private String dlvcustkey;
	
	/** 거래처명 */
	@Schema(description = "거래처명", nullable = false, example = "시립보라매병원")
	private String dlvdescription;
	
	/** 기본주소 */
	@Schema(description = "기본주소", nullable = false, example = "1006692")
	private String dlvaddress1;
	
	/** 거래처 유형 */
	@Schema(description = "거래처 유형", nullable = false, example = "매입업체")
	private String custtype;
}