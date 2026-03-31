package cjfw.wms.tm.dto;

import static cjfw.wms.tm.dto.TmManualDispatchAlign.CENTER;
import static cjfw.wms.tm.dto.TmManualDispatchAlign.LEFT;
import static cjfw.wms.tm.dto.TmManualDispatchAlign.RIGHT;
import static cjfw.wms.tm.dto.TmManualDispatchColumnType.DECIMAL;
import static cjfw.wms.tm.dto.TmManualDispatchColumnType.INTEGER;
import static cjfw.wms.tm.dto.TmManualDispatchColumnType.STRING;
import static cjfw.wms.tm.dto.TmManualDispatchFieldType.EDITABLE;
import static cjfw.wms.tm.dto.TmManualDispatchFieldType.FIXED;

import lombok.Getter;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : TM 배차 엑셀 컬럼 스키마 정의 (업로드/다운로드 통합, 29개 컬럼)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.27 han@wemeetmobility.com 생성
 * </pre>
 */
@Getter
public enum TmDispatchJsonColumn {


	 NO("no", "순서", "순서", 1, STRING, FIXED, CENTER, false, true),	
   // 물류센터
	DCCODE("dccode", "물류센터코드", "물류센터", 2, STRING, FIXED, LEFT, false, true),
   DCNAME("dcname", "물류센터", "물류센터", 3 , STRING, FIXED, LEFT, false, true),
    
   // 배송정보 
   DELIVERY_DATE("deliveryDate", "배송일자", "배송정보", 4, STRING, FIXED, LEFT, false, true),
   DELIVERY_TYPE("deliveryType", "배송유형", "배송정보", 5, STRING, EDITABLE, CENTER, false, true),
   
   // 권역정보
   AREA_GROUP_NAME("areaGroupName", "권역그룹", "권역정보", 6, STRING, FIXED, LEFT, false, false),
   AREA_NAME("areaName", "권역", "권역정보", 7, STRING, FIXED, LEFT, false, false),
   DONG_CODE("dongCode", "행정동코드", "권역정보", 8, STRING, FIXED, LEFT, false, false),
   ZIP_CODE("zipCode", "우편번호", "권역정보", 9, STRING, FIXED, CENTER, false, true),
  
   // 클레임
   CLAIM_YN("claimYn", "클레임", "클레임", 10, STRING, FIXED, CENTER, false, true),
   
   // POP
   POP_NAME("popName", "POP", "POP", 11, STRING, FIXED, LEFT, false, true),
   
   // 고객정보
   TRUTH_CUST_KEY("truthCustKey", "실착지코드", "고객정보", 12, STRING, FIXED, CENTER, false, true),
   CUST_NAME("custName", "실착지명", "고객정보", 13, STRING, FIXED, LEFT, false, true),
   CUST_KEY("custKey", "관리처코드", "고객정보", 14, STRING, FIXED, CENTER, false, true),
   FROM_CUST_NAME("fromCustName", "관리처명", "고객정보", 15, STRING, FIXED, LEFT, false, true),
   
   // 전표번호
   SLIP_NO("slipNo", "전표번호", "고객정보", 16, STRING, FIXED, CENTER, false, true),
   CLOSE_ROUTE("closeRoute", "주문마감경로", "고객정보", 17, STRING, FIXED, LEFT, false, false),
   
   // Vehicle Info (수정 가능)
   CARNO("carno", "차량번호", "차량정보", 18, STRING, EDITABLE, LEFT, false, true),
   TURN("turn", "회차", "차량정보", 19, STRING, FIXED, CENTER, false, false),
   DRIVER_NAME("driverName", "기사명", "차량정보", 20, STRING, EDITABLE, LEFT, false, false),
   DRIVER_PHONE("driverPhone", "전화번호", "차량정보", 21, STRING, EDITABLE, CENTER, false, false),

   // 계약정보 
   CONTRACT_TYPE("contractType", "계약유형", "차량정보", 22, STRING, EDITABLE, CENTER, false, false),

   // 물량 (천단위 구분 기호 사용)
   TOTAL_WEIGHT("totalWeight", "중량(kg)", "물량", 23, DECIMAL, FIXED, RIGHT, true, true),
   TOTAL_CUBE("totalCube", "체적(m³)", "물량", 24, DECIMAL, FIXED, RIGHT, true, true),

   // 배송조건
   FACE_CHECK_YN("faceCheckYn", "대면검수여부", "배송조건", 25, STRING, FIXED, CENTER, false, false),
   OTD("otd", "OTD", "배송조건", 26, STRING, FIXED, CENTER, false, true),
   SPECIAL_CONDITION_YN("specialConditionYn", "특수조건", "배송조건", 27, STRING, FIXED, CENTER, false, true),
   KEY_YN("keyYn", "키 유무", "배송조건", 28, STRING, FIXED, CENTER, false, false),
   KEY_DETAIL("keyDetail", "키 상세조건", "배송조건", 29, STRING, FIXED, LEFT, false, false),

   // 주소
   ADDRESS("address", "주소", "고객정보", 30, STRING, FIXED, LEFT, false, true),

	// 삭제항목 
   DOC_TYPE("docType", "전표유형", "고객정보", 11, STRING, FIXED, CENTER, false, true),
   DELIVERY_PRIORITY("deliveryPriority", "배송순서", "배송순서", 15, STRING, FIXED, LEFT, false, false); 
   
   
	private final String columnName;
   private final String koreanName;
   private final String columnGroup;
   private final int columnIndex;
   private final TmManualDispatchColumnType type;
   private final TmManualDispatchFieldType fieldType;
   private final TmManualDispatchAlign align;
   private final boolean useThousandsSeparator;
   private final boolean required;

   TmDispatchJsonColumn(
       String columnName,
       String koreanName,
       String columnGroup,
       int columnIndex,
       TmManualDispatchColumnType type,
       TmManualDispatchFieldType fieldType,
       TmManualDispatchAlign align,
       boolean useThousandsSeparator,
       boolean required
   ) {
       this.columnName = columnName;
       this.koreanName = koreanName;
       this.columnGroup = columnGroup;
       this.columnIndex = columnIndex;
       this.type = type;
       this.fieldType = fieldType;
       this.align = align;
       this.useThousandsSeparator = useThousandsSeparator;
       this.required = required;
   }
}
