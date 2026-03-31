package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.30 
 * @description : 피킹취소처리 목록 요청 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.30 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "피킹취소처리 목록 요청 결과") 
public class WdPickingCancelResDto extends CommonDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	
	@Schema(description = "출고일자", example = "2025-06-30")
    private String slipdt;
    @Schema(description = "POP번호", example = "P12345")
    private String deliverygroup;
    @Schema(description = "주문유형", example = "일반주문")
    private String ordertype;
    @Schema(description = "관리처코드", example = "CUST001")
    private String toCustkey;
    @Schema(description = "배송인도처명", example = "홍길동")
    private String toCustname;
    @Schema(description = "고객주소", example = "서울특별시 강남구")
    private String toAddressDisp;
    @Schema(description = "주문번호", example = "ORD001")
    private String docno;
    @Schema(description = "영업경로", example = "온라인")
    private String channel;
    @Schema(description = "진행상태", example = "피킹완료")
    private String status;
    @Schema(description = "DC코드", example = "DC001")
    private String dccode;
    @Schema(description = "ORGANIZE", example = "ORG001")
    private String organize;
    @Schema(description = "STORERKEY", example = "STR001")
    private String storerkey;
    @Schema(description = "DOCDT", example = "2025-06-29")
    private String docdt;
    @Schema(description = "DOCTYPE", example = "PICKING")
    private String doctype;
    @Schema(description = "CUSTKEY", example = "CUST001")
    private String custkey;
    @Schema(description = "SLIPNO", example = "SLIP001")
    private String slipno;
    @Schema(description = "TO_VATNO", example = "123-45-67890")
    private String toVatno;
    @Schema(description = "TO_VATOWNER", example = "김갑동")
    private String toVatowner;
	
	
}
