package cjfw.batch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2025.11.11
 * @description : CM_ROAD_ADDRESS(도로명주소) 주소정제 DTO (배치용)  
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.04 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */

@Data
@Schema(description = "CM_ROAD_ADDRESS(도로명주소) 행정동코드 생성 ") 
public class CmRoadAddrResDto {

	@Schema(description = "도로명관리번호(PK)")
	private String adrMngNo;               // ADR_MNG_NO
	@Schema(description = "주소명(작업용)")
	private String addrNm;                 // ADDR_NM
	@Schema(description = "주소명(MANDT)")
	private String addrNmBase;             // ADDR_NM_BASE  (주소명(MANDT:CTP_KOR_NM + SIG_KOR_NM + EMD_KOR_NM))
	@Schema(description = "주소명(OPT1)")
	private String addrNmLi;               // ADDR_NM_LI    (주소명(OPT1:리명))
	@Schema(description = "주소명(OPT2)")
	private String addrNmBunji;            // ADDR_NM_BUNJI (주소명(OPT2:번지))
	@Schema(description = "경도(X)")
	private String longitude;              // LONGITUDE
	@Schema(description = "위도(Y)")
	private String latitude;               // LATITUDE
	@Schema(description = "행정동코드")
	private String hjdongCd;               // HJDONG_CD
	@Schema(description = "행정동명")
	private String hjdongNm;               // HJDONG_NM

	//추가항목(RESULT값 보관)
	@Schema(description = "STATUS")
	private String status;
	@Schema(description = "ERRCODE")
	private String errcode;
	@Schema(description = "ERRMSG")
	private String errmsg;
}
