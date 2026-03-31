package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.31 
 * @description : 신규 주문 실착지 거래처 응답 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "신규 주문 실착지 거래처 응답")
public class TmPlanOrderNewPopupCustResDto {
	
	@Schema(description = "실착지 거래처 키")
	private String truthCustkey;
	
	@Schema(description = "고객사 코드")
	private String storerkey;
	
	@Schema(description = "납품대기여부")
	private String dlvWaitYn;
	
	@Schema(description = "격오지 여부")
	private String distantYn;
	
	@Schema(description = "고객유형")
	private String custType;
	
	@Schema(description = "경도")
	private String longitude;
	
	@Schema(description = "위도")
	private String latitude;
	
	@Schema(description = "체적", example = "10")
	private String cube;
	
	@Schema(description = "중량", example = "10")
	private String weight;
	
	@Schema(description = "수량", example = "10")
	private String orderQty;
	
	@Schema(description = "요청도착시간")
	private String reqdlvtime1To;
	
	@Schema(description = "요청출발시간")
	private String reqdlvtime1From;
	
	@Schema(description = "키즈분류여부")
	private String kidsClYn;
	
	@Schema(description = "고객사명")
	private String description;
	
	@Schema(description = "키 상세")
	private String keyDetail;
	
	@Schema(description = "특수조건 Y/N")
	private String specialYn;
	
	@Schema(description = "대면검수 Y/N")
	private String faceInspect;
	
	@Schema(description = "클레임 Y/N")
	private String claimYn;
	
	@Schema(description = "장거리 Y/N")
	private String lngDistantYn;
	
	@Schema(description = "주소 1")
	private String address1;
	
	@Schema(description = "주소 2")
	private String address2;
	
}
