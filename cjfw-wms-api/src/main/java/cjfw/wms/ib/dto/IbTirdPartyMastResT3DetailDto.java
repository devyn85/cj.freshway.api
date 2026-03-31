package cjfw.wms.ib.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.25
 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 상세 Response DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 상세 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbTirdPartyMastResT3DetailDto extends CommonProcedureDto {
	
	/* 01. 배송일자 */
	@Schema(description = "01. 배송일자")
	private String deliverydate;

	/* 02. 협력사코드 */
	@Schema(description = "02. 협력사코드")
	private String fromcustkey;

	/* 03. 협력사명 */
	@Schema(description = "03. 협력사명")
	private String fromcustnm;

	/* 04. 일배구분 */
	@Schema(description = "04. 배송채널")
	private String channel;

	/* 05. 물류센터 */
	@Schema(description = "05. 물류센터")
	private String dccode;

	/* 05. 물류센터명 */
	@Schema(description = "05. 물류센터명")
	private String dcname;

	/* 06. 거래처코드 */
	@Schema(description = "06. 거래처코드")
	private String tocustkey;

	/* 07. 거래처명 */
	@Schema(description = "07. 거래처명")
	private String tocustnm;

	/* 08. 라벨건수 */
	@Schema(description = "08. 라벨건수")
	private String barCnt;

	/* 09. 스캔완료 */
	@Schema(description = "09. 스캔완료")
	private String scanCnt;

	/* 10. 강제확정(3PL) */
	@Schema(description = "10. 강제확정(3PL)")
	private String cfPl3;

	/* 11. 강제확정사유(3PL) */
	@Schema(description = "11. 강제확정사유(3PL)")
	private String reasoncode2;

	/* 12. 귀책 */
	@Schema(description = "12. 귀책")
	private String reasonwho2;

	/* 13. 세부내역 */
	@Schema(description = "13. 세부내역")
	private String reasontext2;

	/* 14. 강제확정(협력사) */
	@Schema(description = "14. 강제확정(협력사)")
	private String cfCust;

	/* 15. 강제확정사유(협력사) */
	@Schema(description = "15. 강제확정사유(협력사)")
	private String reasoncode;

	/* 16. 귀책 */
	@Schema(description = "16. 귀책")
	private String reasonwho;

	/* 17. 세부내역 */
	@Schema(description = "17. 세부내역")
	private String reasontext;

	/* 18. 미입고 */
	@Schema(description = "18. 미입고")
	private String ndpCnt;

	/* 19. 강제확정사유(미입고) */
	@Schema(description = "19. 강제확정사유(미입고)")
	private String reasoncode3;

	/* 20. 귀책 */
	@Schema(description = "20. 귀책")
	private String reasonwho3;

	/* 21. 세부내역 */
	@Schema(description = "21. 세부내역")
	private String reasontext3;

	/* 22. 미완료 */
	@Schema(description = "22. 미완료")
	private String ncf;

	/* 23. 확정시간 */
	@Schema(description = "23. 확정시간")
	private String cfdate;

	/* DELIVERYTYPE_3PL */
	@Schema(description = "DELIVERYTYPE_3PL")
	private String deliverytypePl3;

	/* INSPECT_CNT */
	@Schema(description = "INSPECT_CNT")
	private String inspectCnt;
	
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";

}
