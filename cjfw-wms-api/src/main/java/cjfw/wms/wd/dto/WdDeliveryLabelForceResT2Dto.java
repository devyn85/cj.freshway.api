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
 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.17 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WdDeliveryLabelForceResT2Dto extends CommonDto {
	
	/* 01. 물류센터코드 */
	@Schema(description = "01. 물류센터코드")
	private String dccode;

	/* 02. 물류센터명 */
	@Schema(description = "02. 물류센터명")
	private String dcname;

	/* 03. 출력명칭 */
	@Schema(description = "03. 출력명칭")
	private String prtNm;

	/* 04. 출력순서1 */
	@Schema(description = "04. 출력순서1")
	private String prdOrd1;

	/* 05. 정렬1 */
	@Schema(description = "05. 정렬1")
	private String seq1;

	/* 06. 출력순서2 */
	@Schema(description = "06. 출력순서2")
	private String prdOrd2;

	/* 07. 정렬2 */
	@Schema(description = "07. 정렬2")
	private String seq2;

	/* 08. 출력순서3 */
	@Schema(description = "08. 출력순서3")
	private String prdOrd3;

	/* 09. 정렬3 */
	@Schema(description = "09. 정렬3")
	private String seq3;

	/* 10. 출력순서4 */
	@Schema(description = "10. 출력순서4")
	private String prdOrd4;

	/* 11. 정렬4 */
	@Schema(description = "11. 정렬4")
	private String seq4;

	/* 12. 출력순서5 */
	@Schema(description = "12. 출력순서5")
	private String prdOrd5;

	/* 13. 정렬5 */
	@Schema(description = "13. 정렬5")
	private String seq5;

	/* 14. 출력순서6 */
	@Schema(description = "14. 출력순서6")
	private String prdOrd6;

	/* 15. 정렬6 */
	@Schema(description = "15. 정렬6")
	private String seq6;

	/* 16. 출력순서7 */
	@Schema(description = "16. 출력순서7")
	private String prdOrd7;

	/* 17. 정렬7 */
	@Schema(description = "17. 정렬7")
	private String seq7;

	/* 18. 출력순서8 */
	@Schema(description = "18. 출력순서8")
	private String prdOrd8;

	/* 19. 정렬8 */
	@Schema(description = "19. 정렬8")
	private String seq8;

	/* 20. 비고 */
	@Schema(description = "20. 비고")
	private String rmk;

	/* 21. 등록자 */
	@Schema(description = "21. 등록자")
	private String addwho;

	/* 22. 등록일시 */
	@Schema(description = "22. 등록일시")
	private String adddate;

	/* 23. 수정자 */
	@Schema(description = "23. 수정자")
	private String editwho;

	/* 24. 수정일시 */
	@Schema(description = "24. 수정일시")
	private String editdate;

	/* USE_PGM */
	@Schema(description = "USE_PGM")
	private String usePgm;
	
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
    
	/* 원출력명칭 */
	@Schema(description = "원출력명칭")
	private String prtNmOri;

}
