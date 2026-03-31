package cjfw.batch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2025.11.11
 * @description : MS_CUST(거래처마스터) 주소정제 DTO (배치용)  
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.11 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */

@Data
@Schema(description = "주소정제 대상 고객정보") 
public class MsCustResDto {

	@Schema(description = "데이터번호")
	private String serialkey;               // SERIALKEY
	@Schema(description = "고객사코드")
	private String storerkey;               // STORERKEY(PK)
	@Schema(description = "거래처코드")
	private String custkey;                 // CUSTKEY(PK)
	@Schema(description = "거래처 유형")
	private String custtype;                // CUSTTYPE(PK)
	//NAVER API 검색조건항목(위경도값 추출용)
	@Schema(description = "기본주소-지번")
	private String address1;                // ADDRESS1
	@Schema(description = "도로명주소")
	private String address3;                // ADDRESS3
	//NAVER API 검색조건항목(행정동코드 추출용)
	@Schema(description = "위도")
	private String latitude;                // LATITUDE
	@Schema(description = "경도")
	private String longitude;               // LONGITUDE

	//추가항목(API구분 및 RESULT값 보관)
	@Schema(description = "API구분")
	private String apiJob;
	@Schema(description = "STATUS")
	private String status;
	@Schema(description = "ERRCODE")
	private String errcode;
	@Schema(description = "ERRMSG")
	private String errmsg;
	@Schema(description = "경도(X)")
	private String x;
	@Schema(description = "위도(Y)")
	private String y;
	@Schema(description = "행정동코드")
	private String admcd;

}
