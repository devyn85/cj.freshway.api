package cjfw.wms.ms.dto;

//import java.util.List;

//import org.apache.poi.ss.usermodel.Row;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.09.02 
 * @description : 로케이션 일괄등록 팝업 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.02 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "로케이션등록 일괄업로드 요청 DTO")
public class MsPopUploadLocationReqDto extends CommonProcedureDto {
    @Schema(description = "그리드 업로드목록")
    private java.util.List<java.util.Map<String, Object>> rows;
    
//    @Schema(description = "프로시저 명", example = "SPMS_LOCATION")
//    private String procedure;

    @Schema(description = "프로세스 타입 (임시/결과 식별용)", example = "LOCHK")
    private String processtype;
    
    private String packagename;

    @Schema(description = "임시테이블 타입", example = "TM")
    private String tempTableType;

    /* AS-IS 기준 */
    @Schema(description = "파라미터 병합 기준", example = "STORERKEY")
    private String parameterMerge;

    /* AS-IS 기준 */
    @Schema(description = "검증/적용 후 결과 조회용 쿼리ID", example = "getDataPopExcelUploadLocResult")
    private String resultQueryId;

    @Schema(description = "검증만 수행 여부 (true=유효성검증, false=적용)", example = "true")
    private Boolean validateOnly;

    /** 정렬/로그용 라인번호 (서비스에서 i+1로 세팅 권장) */
    @Schema(description = "파일 라인번호", example = "1")
    private Integer fileLineNo;

    // === DATA_VALUE01 ~ DATA_VALUE30 매핑 대상 ===
    @Schema(description = "로케이션", example = "I09-0302")
    private String loc;                // V01

    @Schema(description = "로케이션명", example = "이천센터 2층 냉장 소분렉")
    private String description;        // V02

    @Schema(description = "로케이션종류", example = "RACK")
    private String locCategory;        // V03

    @Schema(description = "로케이션구분", example = "GOOD")
    private String locFlag;            // V04

    @Schema(description = "로케이션유형", example = "PICK")
    private String locType;            // V05

    @Schema(description = "재고구분", example = "GOOD")
    private String stockType;          // V06

    @Schema(description = "저장조건", example = "10")
    private String storageType;        // V07

    @Schema(description = "창고구분", example = "1")
    private String whArea;             // V08

    @Schema(description = "창고층", example = "2")
    private String whAreaFloor;        // V09

    @Schema(description = "랙열번호", example = "1")
    private String rackColumn;         // V10

    @Schema(description = "랙행번호", example = "1")
    private String rackLine;           // V11

    @Schema(description = "피킹존", example = "I09")
    private String zone;               // V12

    @Schema(description = "랙", example = "I09")
    private String rack;               // V13

    @Schema(description = "작업키", example = "1")
    private String sortKey;            // V14

    @Schema(description = "LOC 수평우선순위", example = "0")
    private String logicalLocH;        // V15

    @Schema(description = "LOC 수직우선순위", example = "0")
    private String logicalLocV;        // V16

    @Schema(description = "로케이션 레벨", example = "LOW")
    private String locLevel;           // V17

    @Schema(description = "출고가능(Y/N)", example = "Y")
    private String outYn;              // V18

    @Schema(description = "입고가능(Y/N)", example = "Y")
    private String inYn;               // V19

    @Schema(description = "이동완료여부(Y/N)", example = "Y")
    private String moveYn;             // V20

    @Schema(description = "상품혼합여부(Y/N)", example = "Y")
    private String skuMixYn;           // V21

    @Schema(description = "로트혼합여부(Y/N)", example = "Y")
    private String lotMixYn;           // V22

    @Schema(description = "로케이션 체적", example = "1728000")
    private String locCube;            // V23

    @Schema(description = "로케이션 높이", example = "1200")
    private String locCubeH;           // V24

    @Schema(description = "로케이션 길이", example = "1200")
    private String locCubeL;           // V25

    @Schema(description = "로케이션 너비", example = "1200")
    private String locCubeW;           // V26

    @Schema(description = "LOC 적재무게", example = "1000")
    private String locWeight;          // V27

    @Schema(description = "PLT 구분", example = "S")
    private String pltFlg;             // V28

    @Schema(description = "CAPA 적용여부(Y/N)", example = "Y")
    private String capaYn;             // V29

    @Schema(description = "CAPA 로케이션유형", example = "PICK")
    private String capaLocType;        // V30
    
    private String gDccode;
    
}
