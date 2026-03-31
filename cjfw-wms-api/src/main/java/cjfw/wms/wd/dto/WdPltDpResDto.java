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
 * @date : 2025.09.22
 * @description : 재고 > 공용기 관리업 > PLT 수불 관리 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.22 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 공용기 관리업 > PLT 수불 관리 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WdPltDpResDto extends CommonDto {
	
	/* 00. 데이터번호 */
	@Schema(description = "00. 데이터번호")
	private String serialkey;

	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dccode;

	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dcname;

	/* 02. 출고일자 */
	@Schema(description = "02. 출고일자")
	private String deliverydate;

	/* 03. 파렛트 구분(공통코드 : PLT_COMPANY) */
	@Schema(description = "03. 파렛트 구분(공통코드 : PLT_COMPANY)")
	private String pltComDv;

	/* 04. 입/출 구분(공통코드 : DOCTYPE_VESSEL) */
	@Schema(description = "04. 입/출 구분(공통코드 : DOCTYPE_VESSEL)")
	private String inoutDv;

	/* 05. 수량 */
	@Schema(description = "05. 수량")
	private String qty;

	/* 06. 거래처코드 */
	@Schema(description = "06. 거래처코드")
	private String custkey;

	/* 07. 거래처명 */
	@Schema(description = "07. 거래처명")
	private String custkeyname;
	
	/* 08. 파렛트 전표번호 */
	@Schema(description = "08. 파렛트 전표번호")
	private String pltSlipno;

	/* 09. 비고 */
	@Schema(description = "09. 비고")
	private String rmk;

	/* 10. 등록자 */
	@Schema(description = "10. 등록자")
	private String addwho;

	/* 11. 등록일시 */
	@Schema(description = "11. 등록일시")
	private String adddate;

	/* 12. 수정자 */
	@Schema(description = "12. 수정자")
	private String editwho;

	/* 13. 수정일시 */
	@Schema(description = "13. 수정일시")
	private String editdate;

	@Schema(description = "flag")
	private String flag;
	
	/* 등록자 ID */
	@Schema(description = "등록자 ID")
	private String addId;
	
	/* 수정자 ID */
	@Schema(description = "수정자 ID")
	private String updId;

    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
}
