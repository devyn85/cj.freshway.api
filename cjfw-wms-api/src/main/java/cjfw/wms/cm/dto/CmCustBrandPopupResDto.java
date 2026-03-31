package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.13 
 * @description : 본점 검색 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.13 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "본점 검색 결과")
public class CmCustBrandPopupResDto {
	/** 귀속부서 */
	@Schema(description = "본점", nullable = false, example = "K921")
	private String code;
	
	/** 본점명 */
	@Schema(description = "본점명", nullable = false, example = "와이케이스틸점")
	private String name;
	
	/** CUSTKEY */
	@Schema(description = "CUSTKEY", nullable = false, example = "와이케이스틸점")
	private String custkey;
	
	/** ADDRESS1 */
	@Schema(description = "ADDRESS1", nullable = false, example = "와이케이스틸점")
	private String address1;
	
	/** DLVCUSTKEY */
	@Schema(description = "DLVCUSTKEY", nullable = false, example = "와이케이스틸점")
	private String dlvcustkey;
	
	/** DLVDESCRIPTION */
	@Schema(description = "DLVDESCRIPTION", nullable = false, example = "와이케이스틸점")
	private String dlvdescription;
	
	/** DLVADDRESS1 */
	@Schema(description = "DLVADDRESS1", nullable = false, example = "와이케이스틸점")
	private String dlvaddress1;
	
	/** CUSTTYPE */
	@Schema(description = "CUSTTYPE", nullable = false, example = "와이케이스틸점")
	private String custtype;
}
