package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.10.15 
 * @description : 이력배송라벨출력-분류표생성 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력배송라벨출력-분류표생성 목록 결과")
public class WdDeliveryLabelSNResTab1Dto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 생성유무
     */
    @Schema(description = "생성유무", example = "생성완료")
    private String createdescr;
    /**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "01")
    private String dccode;
    /**
     * 창고
     */
    @Schema(description = "창고", example = "C10")
    private String organize;
    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "1000")
    private String plant;
    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-10-15")
    private String slipdt;
    /**
     * 주문유형
     */
    @Schema(description = "주문유형", example = "일반")
    private String ordertype;
    /**
     * 주문사유
     */
    @Schema(description = "주문사유", example = "정상출고")
    private String potype;
    /**
     * 주문번호
     */
    @Schema(description = "주문번호", example = "ORD12345")
    private String docno;
    /**
     * 영업조직
     */
    @Schema(description = "영업조직", example = "영업A팀")
    private String salegroup;
    /**
     * 사업장
     */
    @Schema(description = "사업장", example = "강남점")
    private String saledepartment;
    /**
     * 영업그룹
     */
    @Schema(description = "영업그룹", example = "CUSTGRPA")
    private String custgroup;
    /**
     * 판매처코드
     */
    @Schema(description = "판매처코드", example = "V12345")
    private String toVatno;
    /**
     * 판매처명
     */
    @Schema(description = "판매처명", example = "판매처A")
    private String toVatowner;
    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "MKEY001")
    private String toCustkey;
    /**
     * 관리처명
     */
    @Schema(description = "관리처명", example = "관리처B")
    private String toCustname;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "확정")
    private String status;
    /**
     * Storerkey
     */
    @Schema(description = "Storerkey", example = "KEY001")
    private String storerkey;
    /**
     * Area
     */
    @Schema(description = "Area", example = "A10")
    private String area;
    /**
     * Docdt
     */
    @Schema(description = "Docdt", example = "2025-10-15 10:00:00")
    private String docdt;
    /**
     * Doctype
     */
    @Schema(description = "Doctype", example = "WDO")
    private String doctype;
    /**
     * Slipno
     */
    @Schema(description = "Slipno", example = "SLP0001")
    private String slipno;
    /**
     * Sliptype
     */
    @Schema(description = "Sliptype", example = "WDP")
    private String sliptype;
    /**
     * Createyn
     */
    @Schema(description = "Createyn", example = "Y")
    private String createyn;
}
