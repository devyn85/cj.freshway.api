package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.22 
 * @description : 고객사배송정보 좌표 업데이트 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.22 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
public class TmCustDlvInfoPointReqDto {
	@Schema(description = "고객사 코드")
	private String storerkey;

	@Schema(description = "거래처유형")
	private String custType;

	@Schema(description = "배송처 코드")
	private String dlvcustkey;

	@Schema(description = "경도")
	private String longitude;

	@Schema(description = "위도")
	private String latitude;

	@Schema(description = "주소")
	private String address;

    @Schema(description = "우편번호")
    private String zipcode;

    @Schema(description = "반경", example = "100")
    private String radius;

    @Schema(description = "체류시간", example = "100")
    private String stytime;
}
