package cjfw.wms.gwms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2025.11.05
 * @description : 중계 API > 국가주소연계 API 마스터 물류 송신(WM001)
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.05 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "우편번호POP정보") 
public class MsPostxpopResDto {

	@Schema(description = "데이터번호")
	private String serialkey;               // SERIALKEY
	@Schema(description = "센터코드")
	private String dccode;                  // DCCODE
	@Schema(description = "고객코드")
	private String storerkey;               // STORERKEY
	@Schema(description = "우편번호")
	private String zipcode;                 // ZIPCODE
	@Schema(description = "지번주소")
	private String address;                 // ADDRESS
	@Schema(description = "납품시작시간")
	private String fromhour;                // FROMHOUR
	@Schema(description = "납품종료시간")
	private String tohour;                  // TOHOUR
	@Schema(description = "적용시작일자")
	private String fromdate;                // FROMDATE
	@Schema(description = "적용종료일자")
	private String todate;                  // TODATE
	@Schema(description = "POP유형")
	private String poptype;                 // POPTYPE
	@Schema(description = "POP번호")
	private String popno;                   // POPNO
	@Schema(description = "차량번호 전체")
	private String carno;                   // CARNO
	@Schema(description = "POP명")
	private String popname;                 // POPNAME
	@Schema(description = "주문그룹")
	private String ordgrp;                  // ORDGRP
	@Schema(description = "메모")
	private String memo;                    // MEMO
	@Schema(description = "상태값")
	private String status;                  // STATUS
	@Schema(description = "삭제여부")
	private String delYn;                   // DEL_YN
	@Schema(description = "데이터흐름제어")
	private String trafficcop;              // TRAFFICCOP
	@Schema(description = "아카이브제어")
	private String archivecop;              // ARCHIVECOP
	@Schema(description = "롤테이너번호")
	private String rolltainerNo;            // ROLLTAINER_NO
	@Schema(description = "등록자")
	private String userid;

}
