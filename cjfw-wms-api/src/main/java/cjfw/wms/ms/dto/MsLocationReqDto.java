package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.06.19 
 * @description : 로케이션정보(목록) 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "로케이션정보(목록) 조회 요청 DTO")
public class MsLocationReqDto extends CommonDto{
	@Schema(description = "데이터번호 (PK)")
	private String serialKey;
	
	@Schema(description = "행상태", example = "I")
	private String rowStatus;
	
	@Schema(description = "물류센터", example = "2600")
	private String dccode;
	
	@Schema(description = "로케이션", example = "A02-0010")
	private String loc;
	
	@Schema(description = "내역(설명)", example = "이천센터 3층 축육 냉동")
	private String description;
	
	@Schema(description = "랙", example = "R01")
    private String rack;
	
	@Schema(description = "랙 라인", example = "1")
    private Integer rackLine;
	
	@Schema(description = "랙 컬럼", example = "1")
    private Integer rackColumn;
	
	@Schema(description = "창고구분", example = "1")
	private String whArea;
	
	@Schema(description = "창고층", example = "3층")
	private String whAreaFloor;
	
	@Schema(description = "피킹존", example = "A02")
	private String zone;
	
	@Schema(description = "로케이션유형", example = "피킹")
	private String locType;
	
	@Schema(description = "로케이션종류", example = "랙")
	private String locCategory;
	
	@Schema(description = "CUBE_CM3", example = "1000")
    private BigDecimal locCube;
	
    @Schema(description = "로케이션규격(너비)", example = "100")
    private BigDecimal locCubeW;

    @Schema(description = "로케이션규격(높이)", example = "100")
    private BigDecimal locCubeH;

    @Schema(description = "로케이션규격(길이)", example = "100")
    private BigDecimal locCubeL;
	
	@Schema(description = "재고 위치", example = "GOOD")
    private String stockType;
	
	@Schema(description = "수평 우선순위", example = "10")
//    private Integer logicalLocH;
	private BigDecimal logicalLocH;

    @Schema(description = "수직 우선순위", example = "20")
    private Integer logicalLocV;
    
    @Schema(description = "적재 중량", example = "9999999")
    private Integer locWeight;
	
	@Schema(description = "로케이션레벨", example = "저단")
	private String locLevel;
	
    @Schema(description = "입고 가능 여부", example = "Y")
    private String inYn;

    @Schema(description = "출고 가능 여부", example = "Y")
    private String outYn;

    @Schema(description = "상품 혼합 적재 여부", example = "Y")
    private String skuMixYn;

    @Schema(description = "로트 혼합 적재 여부", example = "Y")
    private String lotMixYn;
	
	@Schema(description = "로케이션구분", example = "정상")
	private String locFlag;
	
	@Schema(description = "이동 완료 여부", example = "Y")
    private String moveYn;
	
    @Schema(description = "저장 조건", example = "A")
    private String storageType;
    
    @Schema(description = "혼합대상", example = "10")
    private String mixtgtType;
    
    @Schema(description = "DPS 여부", example = "N")
    private String dpsYn;
    
    @Schema(description = "삭제 여부", example = "N")
    private String delYn;
    
    @Schema(description = "PLT 구분", example = "P")
    private String pltFlg;
    
    @Schema(description = "CAPA 적용 여부", example = "Y")
    private String capaYn;
    
	@Schema(description = "멀티로케이션여부", example = "N")
	private String multiLocYn;

    @Schema(description = "CAPA 로케이션 유형", example = "C1")
    private String capaLocType;
    
    @Schema(description = "최초등록시간", example = "20250619123456")
    private String addDate;

    @Schema(description = "최종변경시간", example = "20250620104532")
    private String editDate;

    @Schema(description = "최초등록자", example = "user1")
    private String addWho;

    @Schema(description = "최종변경자", example = "user2")
    private String editWho;
}
