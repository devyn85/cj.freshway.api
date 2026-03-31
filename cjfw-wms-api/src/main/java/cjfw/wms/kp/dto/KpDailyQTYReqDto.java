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
 * @date : 2025.09.02
 * @description : 지표 > 센터 운영 > 일일 물동량 조회 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.02 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표 > 센터 운영 > 일일 물동량 조회 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpDailyQTYReqDto extends CommonProcedureDto {
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;	
    
    /** 물류센터(다중검색) */
    @MultiSearch
    @Schema(description = "물류센터-다중검색")
    private List<String> fixdccodeMulti;     	
	
	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;
	
	/* 출고일자(시작일) */
	@Schema(description = "출고일자(시작일)")
	private String docdtFrom;

	/* 출고일자(종료일) */
	@Schema(description = "출고일자(종료일)")
	private String docdtTo;

	/*저장유무 */
	@Schema(description = "저장유무")
	private String channel;
}
