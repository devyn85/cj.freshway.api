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
 * @date : 2025.07.02 
 * @description : 로케이션 정보(단건) 저장 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.02 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "로케이션 정보(단건) 저장 요청 DTO")
public class MsLocationSaveReqDto extends CommonDto {
    @Schema(description = "데이터 번호(SerialKey)", example = "12345")
    private String serialKey;

    @Schema(description = "물류센터 코드", example = "2600")
    private String dcCode;

    @Schema(description = "로케이션", example = "A02-0010")
    private String loc;

    @Schema(description = "내역", example = "이천센터 3층 축육 냉동")
    private String description;

    @Schema(description = "창고구분", example = "AREA01")
    private String whArea;

    @Schema(description = "창고층", example = "3층")
    private String whAreaFloor;

    @Schema(description = "피킹존", example = "A02")
    private String zone;
    
    @Schema(description = "로케이션 유형", example = "피킹")
    private String locType;

    @Schema(description = "로케이션 종류", example = "랙")
    private String locCategory;    
    
    @Schema(description = "로케이션 구분", example = "정상")
    private String locFlag;
    
    @Schema(description = "로케이션 레벨", example = "LOW")
    private String locLevel;
    
    @Schema(description = "로케이션 수직우선순위", example = "1")
    private Integer logicalLocV;
    
    @Schema(description = "로케이션 수평우선순위", example = "1")
    private Integer logicalLocH;

    @Schema(description = "재고위치", example = "GOOD")
    private String stockType;
    
    @Schema(description = "랙", example = "R01")
    private String rack;

    @Schema(description = "랙행번호", example = "1")
    private Integer rackLine;

    @Schema(description = "랙열번호", example = "1")
    private Integer rackColumn;
    
    @Schema(description = "LOC적재무게", example = "9999999")
    private Integer locWeight;

    @Schema(description = "CUBE_CM3", example = "1000")
    private BigDecimal locCube;
    
    @Schema(description = "로케이션규격(길이)", example = "100")
    private BigDecimal locCubeL;

    @Schema(description = "로케이션규격(너비)", example = "100")
    private BigDecimal locCubeW;

    @Schema(description = "로케이션규격(높이)", example = "100")
    private BigDecimal locCubeH;
    
    @Schema(description = "상품혼합여부", example = "Y")
    private String skuMixYn;
    
    @Schema(description = "로트혼합여부", example = "Y")
    private String lotMixYn;

    @Schema(description = "입고가능", example = "Y")
    private String inYn;

    @Schema(description = "출고가능", example = "Y")
    private String outYn;
    
    @Schema(description = "이동완료여부", example = "Y")
    private String moveYn;
    
    @Schema(description = "저장조건", example = "A")
    private String storageType;
    
    @Schema(description = "DPS 여부", example = "N")
    private String dpsYn;

    @Schema(description = "등록일시", example = "20250619123456")
    private String addDate;
    
    @Schema(description = "수정일시", example = "20250620104532")
    private String editDate;
    
    @Schema(description = "등록자", example = "user1")
    private String addWho;
    
    @Schema(description = "수정자", example = "user2")
    private String editWho;
    
    @Schema(description = "삭제여부", example = "N")
    private String delYn;
    
    @Schema(description = "PLT 구분", example = "P")
    private String pltFlg;
    
    @Schema(description = "CAPA 적용 여부", example = "Y")
    private String capaYn;
    
    @Schema(description = "CAPA 로케이션 유형", example = "C1")
    private String capaLocType;

    @Schema(description = "상태", example = "NORMAL")
    private String status;

    @Schema(description = "정렬 키", example = "10")
    private Integer sortKey;
}
