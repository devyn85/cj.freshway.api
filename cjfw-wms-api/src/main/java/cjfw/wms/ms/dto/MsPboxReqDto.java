package cjfw.wms.ms.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.18
 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 공용기 관리 > P-BOX 관리/사용 현황 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsPboxReqDto extends CommonProcedureDto {
	
	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/* 차량할당일 From */
	@Schema(description = "차량할당일 From")
	private String carAllocateDtFrom;

	/* 차량할당일 To */
	@Schema(description = "차량할당일 To")
	private String carAllocateDtTo;

	/* 출고일자 From */
	@Schema(description = "출고일자 From")
	private String deliverydtFrom;
	
	/* 출고일자 To */
	@Schema(description = "출고일자 To")
	private String deliverydtTo;

	/* 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

    /* 차량번호-멀티 */
	@MultiSearch
    @Schema(description = "차량번호-멀티")
    private List<List<String>> carnoMulti;
	
	/* 재출력 */
	@Schema(description = "재출력")
	private String reprint;
	
	/* 할당여부 */
	@Schema(description = "할당여부")
	private String allocatedYn;
	
	/* 사용여부 */
	@Schema(description = "사용여부")
	private String useYn;
	
	@Schema(description = "메인그리드 저장 리스트")
	List<MsPboxResT1Dto> saveDataList;
}
