package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.09 
 * @description : 기사 검색 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량 검색 요청") 
public class CmCarPopupReqDto {
	/** 차량번호 */
	@Schema(description = "차량번호", nullable = false, example = "4900")
	private String searchVal;
	
	/** 차량번호-다중선택 */
	@Schema(description = "차량번호-다중선택", nullable = false, example = "4900,8774")
	private String multiSelect;
	
	/** carList  */	
	private String[] carList;
	
	/** code */
	@Schema(description = "code", nullable = false, example = "Y00050")
	private String code;
	
	/** name */
	@Schema(description = "name", nullable = false, example = "Y00050")
	private String name;
	
	/* 계약 유형 */
	@Schema(description = "contracttype", nullable = false, example = "홍길동")
	private String contracttype;
	
	/*
	 * MS_CAR_ENTRY_INFO 테이블을 이용하여 DCCODE 조건 추가 (25.09.03)
	 * 물류센터 
	 */
	@Schema(description = "customDccode", example = "2600")
	private String customDccode;
	
	/** 차량정보 */
	@Schema(description = "carno")
	private String carno;
	
}
