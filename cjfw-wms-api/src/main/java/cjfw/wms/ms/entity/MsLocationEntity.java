package cjfw.wms.ms.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : YeoSeungCheol (pw6375@cj.net)
 * @date        : 2025.07.03
 * @description : 로케이션 정보 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.03        YeoSeungCheol 		(pw6375@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "로케이션 정보 Entity")
public class MsLocationEntity extends CommonDto {
    @Schema(description = "데이터번호 (PK)", example = "1001")
    private String serialKey;

    @Schema(description = "물류센터 코드", example = "2600")
    private String dccode;

    @Schema(description = "로케이션", example = "A01-0101")
    private String loc;

    @Schema(description = "내역(설명)", example = "이천센터 3층 축육 냉동")
    private String description;

    @Schema(description = "창고구분", example = "상온")
    private String whArea;

    @Schema(description = "창고층", example = "1층")
    private String whAreaFloor;

    @Schema(description = "피킹존", example = "Z01")
    private String zone;

    @Schema(description = "랙", example = "R01")
    private String rack;

    @Schema(description = "랙행번호", example = "1")
    private String rackLine;

    @Schema(description = "랙열번호", example = "1")
    private String rackColumn;
    
    @Schema(description = "LOC적재무게", example = "100")
    private String locWeight;

    @Schema(description = "로케이션유형", example = "QC")
    private String locType;

    @Schema(description = "로케이션종류", example = "RACK")
    private String locCategory;

    @Schema(description = "로케이션 상태", example = "GOOD")
    private String locFlag;

    @Schema(description = "로케이션 레벨", example = "LOW")
    private String locLevel;

    @Schema(description = "재고위치", example = "GOOD")
    private String stockType;

    @Schema(description = "로케이션 수직우선순위계산", example = "0")
    private Integer logicalLocV;
    
    @Schema(description = "로케이션 수평우선순위계산", example = "0")
    private Integer logicalLocH;

    @Schema(description = "CUBE_CM3", example = "1000000")
    private BigDecimal locCube;

    @Schema(description = "로케이션규격(길이)", example = "100")
    private BigDecimal locCubeL;
    
    @Schema(description = "로케이션규격(너비)", example = "100")
    private BigDecimal locCubeW;

    @Schema(description = "로케이션규격(높이)", example = "100")
    private BigDecimal locCubeH;

    @Schema(description = "상품 혼합 여부", example = "Y")
    private String skuMixYn;
    
    @Schema(description = "로트 혼합 여부", example = "Y")
    private String lotMixYn;
    
    @Schema(description = "입고 가능 여부", example = "Y")
    private String inYn;

    @Schema(description = "출고 가능 여부", example = "Y")
    private String outYn;
    
    @Schema(description = "이동 가능 여부", example = "Y")
    private String moveYn;

    @Schema(description = "삭제여부", example = "N")
    private String delYn;

    @Schema(description = "저장유형", example = "DEFAULT")
    private String storageType;
    
    @Schema(description = "혼합대상", example = "10")
    private String mixtgtType;

    @Schema(description = "DPS 여부", example = "N")
    private String dpsYn;

    @Schema(description = "PLT 구분", example = "P")
    private String pltFlg;

    @Schema(description = "CAPA 여부", example = "Y")
    private String capaYn;

    @Schema(description = "CAPA 로케이션 유형", example = "PICK")
    private String capaLocType;
    
    @Schema(description = "멀티로케이션 여부", example = "N")
    private String multiLocYn;

    @Schema(description = "최초등록자", example = "SYSTEM")
    private String addWho;

    @Schema(description = "최초등록일시 (YYYYMMDDHH24MISS)", example = "20250630123045")
    private String addDate;

    @Schema(description = "최종변경자", example = "SYSTEM")
    private String editWho;

    @Schema(description = "최종변경일시 (YYYYMMDDHH24MISS)", example = "20250630123045")
    private String editDate;
}
