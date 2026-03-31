package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.25 
 * @description : 고객배송조건 수신이력 팝업 조회 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.25 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "고객배송조건 수신이력 조회 응답 DTO")
public class MsCustDlvInfoHisPopupResDto {
	// 테이블 이관 후 Schema 작성 예정
	private String serialkey;
	private String custkey;
	private String custname;
	private String custtype;
	private String addressmatchyn;
	
//	변경
//	private String arrivalAddress;
	private String truthaddress1;
	
//  변경 20260220
//	private String arrivaldetailaddress;
	private String truthaddress2;
	
//  변경 20260220
//	private String arrivalpostalcode;
    private String truthzipcode;
	
//	변경
//	private String ftfInspectionYn;
	private String faceinspect;
	
	private String inspectionworkerphone;
	private String inspectorprintyn;
	private String parkingheight;
	private String keytype;
	private String keydetail;
	
//	변경
//	private String deliveryRequestTimeStart;
	private String reqdlvtime2From;
	
//	변경
//	private String deliveryRequestTimeEnd;
	private String reqdlvtime2To;
	
	private String deliveryavailabletime;
	private String buildingopentime;
	
//	변경
//	private String movementEntry;
	private String accessway;
	
//	변경
//	private String goodsLocationFrozen;
	private String freezeplace;
	
//	변경
//	private String goodsLocationRefrig;
	private String coldplace;
	
//	변경
//	private String goodsLocationRoom;
	private String htemperature;
	
//	변경
//	private String returnLocation;
	private String loadplace2;
	
	
	private String initrequestdt;
	private String initrequesttimestart;
	private String initrequesttimeend;
	private String initftfinspectionyn;
	private String initdeliverycontact;
	private String initdeliverydesc;
	private String deliverytype;
	private String temptarget;
	private String labelprinttype;
	private String deliverynotiyn;
	private String deliverynotiphone;
	private String deliverynotitimestart;
	private String deliverynotitimeend;
	private String employeenumber;
	private String createdbyemail;
	private String createdbyname;
	private String createddate;
	private String storetype;
	private String edmsfileid;
	private String sourceSystem;
	private String status;
	private String confirmdate;
	private String adddate;
	private String editdate;
	private String addwho;
	private String editwho;
	
	private String emploeenumber; // 사번
	
    private String deliveryavailabletimenm; // 배송가능시간명
    private String buildingopentimenm; // 건물오픈시간명
    private String deliverytypenm; // 배송유형명
    
    private String keytypenm; // 키유형명 
    private String labelprinttypenm; // 라벨출력유형명
    
    private String parkingheightnm; // 주차높이명
    
	
}
