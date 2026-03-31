package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.29 
 * @description : 거래처 상세팝업 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "거래처 상세팝업 응답 DTO")
public class TmPlanCustomerDetailPopupResDto {
	
	@Schema(description = "고객사코드")
	private String storerkey;

    @Schema(description = "거래처유형")
    private String custtype;
	
	@Schema(description = "거래처코드")
	private String custkey;
	
	@Schema(description = "거래처명")
	private String custname;

    @Schema(description = "실착지 거래처 키")
    private String truthCustkey;

    @Schema(description = "실착지 거래처명")
    private String truthCustName;
	
	@Schema(description = "실착지 거래처주소")
	private String truthAddress1;
	
	@Schema(description = "실착지 상세주소")
	private String truthAddress2;
	
	@Schema(description = "건물 진입가능 높이 코드")
	private String parkingHeight;
	
	@Schema(description = "건물 진입가능 높이")
	private String parkingHeightNm;

	@Schema(description = "업장 출입")
	private String accessway;
	
	@Schema(description = "고객사 납품 가능 시간")
	private String deliveryAvailableTime;
	
	@Schema(description = "고객사 건물 개방 시간")
	private String buildingOpenTime;
	
	@Schema(description = "배송요청시간 시작 OTD(From)")
	private String reqDlvTime2From;
	
	@Schema(description = "배송요청시간 종료 OTD(To)")
	private String reqDlvTime2To;
	
	@Schema(description = "OTD")
	private String otd;
	
	@Schema(description = "대면검수여부")
	private String faceInspect;
	
	@Schema(description = "특수조건 YN")
	private String specialYn;
	
	@Schema(description = "격오지여부")
	private String distantYn;

	@Schema(description = "납품대기여부")
	private String dlvWaitYn;
	
	@Schema(description = "키즈분류여부")
	private String kidsClYn;
	
	@Schema(description = "장거리여부")
	private String lngDistantYn;
	
	@Schema(description = "하차난이도")
	private String unloadLvlCd;
	
	@Schema(description = "적재위치")
	private String freezePlace;
	
	@Schema(description = "적재위치(냉장/상온)")
	private String coldPlace;
	
	@Schema(description = "적재위치(상온)")
	private String hTemperature;
	
	@Schema(description = "거래처 열쇠")
	private String keyType;
	
	@Schema(description = "거래처 열쇠 상세")
	private String keyDetail;

    @Schema(description = "일별 메모 가능 여부")
    private String tmMemoEditYn;
	
	@Schema(description = "일별 메모")
	private String tmMemo;
	
	@Schema(description = "기타사항")
	private String memo;
	
	@Schema(description = "요청사항(거래처카드)")
	private String cardMemo;
	
	@Schema(description = "메모(납품서반영)")
	private String supplyMemo;
}
