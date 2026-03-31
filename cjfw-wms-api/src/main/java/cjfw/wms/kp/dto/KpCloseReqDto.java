package cjfw.wms.kp.dto;

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
 * @date : 2025.08.22
 * @description : 모니터링 > 물동 > 물동마감 진행 현황 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.22 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "모니터링 > 물동 > 물동마감 진행 현황 Request DTO")
public class KpCloseReqDto extends CommonProcedureDto {
	/** 물류센터 */
	@Schema(description = "물류센터 코드", example = "2600")
	private String fixdccode;
	
    /** 물류센터(다중검색) */
    @MultiSearch
    @Schema(description = "물류센터-다중검색")
    private List<String> fixdccodeMulti; 
    
	/* 조회월 master */
	@Schema(description = "조회월")
	private String docdt;

	/* 조회시작일 master */
	@Schema(description = "조회시작일")
	private String docdtFrom;

	/* 조회종료일 master */
	@Schema(description = "조회종료일")
	private String docdtTo;

	/* 조회구분 master */
	@Schema(description = "조회구분")
	private String searchtype;

	/* 물류센터 master, detail */
	@Schema(description = "물류센터")
	private String dccode;
	
	/* 플랜트 detail */
	@Schema(description = "플랜트")
	private String plant;
	
	/* 반품예약 YN */
	@Schema(description = "반품예약 YN")
	private String reserveYn;
	
	/* 메인그리드 저장 리스트 */
	@Schema(description = "메인그리드 저장 리스트")
	List<KpCloseResDto> saveList;
}
