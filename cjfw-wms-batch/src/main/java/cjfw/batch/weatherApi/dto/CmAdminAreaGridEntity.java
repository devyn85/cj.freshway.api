package cjfw.batch.weatherApi.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2025.09.05
 * @description : 날씨 정보 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.05 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CmAdminAreaGridEntity {
	/** 행정동 코드 */
	@Schema(description = "행정동 코드", example = "1147000000")
	private String hjdongCd;
	
	/** 행정동 1단계 (시/도) */
	@Schema(description = "행정동 1단계 (시/도)", example = "서울특별시")
	private String ctpKorNm;

	/** 행정동 2단계 (시/군/구) */
	@Schema(description = "행정동 2단계 (시/군/구)", example = "양천구")
	private String sigKorNm;

	/** 행정동 3단계 (읍/면/동) */
	@Schema(description = "행정동 3단계 (읍/면/동)", example = "")
	private String hjdongNm;

	/** 격자 X 좌표 */
	@Schema(description = "격자 X 좌표", example = "20250905")
	private int nx;

	/** 격자 Y 좌표 */
	@Schema(description = "격자 Y 좌표", example = "126")
	private int ny;

	/** 경도 (시) */
	@Schema(description = "경도 (시)", example = "126")
	private int lonH;
	
	/** 경도 (분) */
	@Schema(description = "경도 (분)", example = "52")
	private int lonM;

	/** 경도 (초) */
	@Schema(description = "경도 (초)", example = "7.35")
	private int lonS;

	/** 위도 (시) */
	@Schema(description = "위도 (시)", example = "37")
	private int latH;

	/** 위도 (분) */
	@Schema(description = "위도 (분)", example = "30")
	private int latM;

	/** 위도 (초) */
	@Schema(description = "위도 (초)", example = "51.23")
	private int latS;

	/** 경도 (초/100) */
	@Schema(description = "경도 (초/100)", example = "126.868708333333")
	private int lonS100;

	/** 위도 (초/100) */
	@Schema(description = "위도 (초/100)", example = "37.514230555556")
	private int latS100;

	/** 위치 업데이트 일시 */
	@Schema(description = "위치 업데이트 일시", example = "20250905")
	private String updateDt;

    /** 시/도 별 카운트 */
    @Schema(description = "시/도 별 카운트", example = "23")
    private String ctpKorNmCnt;

    /** 미처리대상 조회 구분자 */
    @Schema(description = "미처리대상 조회 구분자", example = "S")
    private String searchGubun;

}