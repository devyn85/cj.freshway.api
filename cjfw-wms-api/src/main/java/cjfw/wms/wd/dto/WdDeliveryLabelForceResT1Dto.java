package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.17
 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_분류표출력_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.17 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_분류표출력_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WdDeliveryLabelForceResT1Dto extends CommonDto {

	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dccode;

	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dcname;

	/* 02. 관리처코드 */
	@Schema(description = "02. 관리처코드")
	private String toCustkey;

	/* 03. 납품처명1 */
	@Schema(description = "03. 납품처명1")
	private String lblCustname1;

	/* 04. 납품처명2 */
	@Schema(description = "04. 납품처명2")
	private String lblCustname2;

	/* 05. 상품코드 */
	@Schema(description = "05. 상품코드")
	private String lblSku;
	
	/* 05. 상품코드 */
	@Schema(description = "05. 상품코드")
	private String sku;

	/* 06. 상품명1 */
	@Schema(description = "06. 상품명1")
	private String lblSkuname1;

	/* 07. 상품명2 */
	@Schema(description = "07. 상품명2")
	private String lblSkuname2;

	/* 08. 수량 */
	@Schema(description = "08. 수량")
	private String lblQty;

	/* 09. 수량2 */
	@Schema(description = "09. 수량2")
	private String lblQty_2;

	/* 10. 차량번호 */
	@Schema(description = "10. 차량번호")
	private String lblPageno1;

	/* 11. 출차조 */
	@Schema(description = "11. 출차조")
	private String lblCargroup;

	/* 12. POP */
	@Schema(description = "12. POP")
	private String lblDeliverygroup;

	/* 13. POP2 */
	@Schema(description = "13. POP2")
	private String lblDeliverygroupChg;

	/* 14. 특이사항 */
	@Schema(description = "14. 특이사항")
	private String lblMemo;

	/* 15. 로케이션 */
	@Schema(description = "15. 로케이션")
	private String lblLoc;

	/* 16. 저장조건 */
	@Schema(description = "16. 저장조건")
	private String lblStoragetype;
	
	/* 16. 저장조건 */
	@Schema(description = "16. 저장조건")
	private String lblRealStoragetype;

	/* 17. 저장조건1 */
	@Schema(description = "17. 저장조건1")
	private String lblStoragetype_1;

	/* 18. 저장조건2 */
	@Schema(description = "18. 저장조건2")
	private String lblStoragetype_2;

	/* 19. 배송일자 */
	@Schema(description = "19. 배송일자")
	private String lblDeliverydt;

	/* 20. 업체명 */
	@Schema(description = "20. 업체명")
	private String lblFromCustname;

	/* 21. 배송차량명 */
	@Schema(description = "21. 배송차량명")
	private String lblFromCarname;

	/* 22. 페이지번호 */
	@Schema(description = "22. 페이지번호")
	private String lblPageno2;

	/* 23. 바코드텍스트 */
	@Schema(description = "23. 바코드텍스트")
	private String lblBarcodetxt;

	/* 24. 바코드1 */
	@Schema(description = "24. 바코드1")
	private String lblBarcode1;

	/* 25. 바코드2 */
	@Schema(description = "25. 바코드2")
	private String lblBarcode2;

	/* 26. Sorter대상 */
	@Schema(description = "26. Sorter대상")
	private String lblSmsYn;

	/* 27. 피킹번호 */
	@Schema(description = "27. 피킹번호")
	private String pickNo;

	/* 28. 대배치키 */
	@Schema(description = "28. 대배치키")
	private String pickBatchNo;

	/* 29. 온푸비고란 */
	@Schema(description = "29. 온푸비고란")
	private String lblMemoOfn;

	/* 30. 타센터 sto 수량 */
	@Schema(description = "30. 타센터 sto 수량")
	private String lblStoqty;

	/* 31. 특별관리고객표기 */
	@Schema(description = "31. 특별관리고객표기")
	private String lblMarkword;

	/* LBL_MANUDATE */
	@Schema(description = "LBL_MANUDATE")
	private String lblManudate;

	/* LBL_PLACEOFORIGIN */
	@Schema(description = "LBL_PLACEOFORIGIN")
	private String lblPlaceoforigin;

	/* LBL_ROUTENAME */
	@Schema(description = "LBL_ROUTENAME")
	private String lblRoutename;

	/* STORAGE_GUBUN */
	@Schema(description = "STORAGE_GUBUN")
	private String storageGubun;

	/* LOC_YN */
	@Schema(description = "LOC_YN")
	private String locYn;

	/* INVOICESORT */
	@Schema(description = "INVOICESORT")
	private String invoicesort;

	/* SKUPRIORITY */
	@Schema(description = "SKUPRIORITY")
	private String skupriority;

	/* PREDELIVERYGROUP */
	@Schema(description = "PREDELIVERYGROUP")
	private String predeliverygroup;

	/* PRINT_YN */
	@Schema(description = "PRINT_YN")
	private String printYn;

	/* LBL_ETC_MSG */
	@Schema(description = "LBL_ETC_MSG")
	private String lblEtcMsg;

	/* DELIVERYGROUP_SEQ */
	@Schema(description = "DELIVERYGROUP_SEQ")
	private String deliverygroupSeq;

	/* LBL_FOLABEL_YN */
	@Schema(description = "LBL_FOLABEL_YN")
	private String lblFolabelYn;

	/* docno */
	@Schema(description = "docno")
	private String docno;

	/* deliverydt */
	@Schema(description = "deliverydt")
	private String deliverydt;

	/* yn */
	@Schema(description = "yn")
	private String yn;
	
	/* LBL_DCNAME */
	@Schema(description = "LBL_DCNAME")
	private String lblDcname;

	/* LBL_TITLE */
	@Schema(description = "LBL_TITLE")
	private String lblTitle;

	/* LBL_PAGENO3 */
	@Schema(description = "LBL_PAGENO3")
	private String lblPageno3;

	/* LBL_QTY1 */
	@Schema(description = "LBL_QTY1")
	private String lblQty1;

	/* LBL_STORAGETYPE1 */
	@Schema(description = "LBL_STORAGETYPE1")
	private String lblStoragetype1;

    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";

}
