package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.25 
 * @description : 재고속성변경 헤더-Sub 목록 조회 Request DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.25 ParkJinWoo 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StConvertCgExDcSaveReqDto extends CommonProcedureDto {

	List<StConvertCgExDcDetailListResDto> saveList;
	
    @Schema(description = "CONVERTTYPE", nullable = false, example = "")
    private String convertType ;


//    private String convertType;

    /* ===== 상세 파라미터 (JSON 키 = 필드명) ===== */

    @Schema(description = "체크 여부(0 : 미선택, 1 : 선택)", example = "0")
    private String checkYn;

    @Schema(description = "센터 코드(DC)", example = "2600")
    private String dcCode;

    @Schema(description = "화주(고객사) 코드", example = "FW00")
    private String storerKey;

    @Schema(description = "조직 코드", example = "2600")
    private String organize;

    @Schema(description = "창고(AREA) 코드", example = "1000")
    private String area;

    @Schema(description = "FROM 위치", example = "RTN-협력사")
    private String fromLoc;

    @Schema(description = "상품(SKU) 코드", example = "441403")
    private String sku;

    @Schema(description = "단위(UOM)", example = "EA")
    private String uom;

    @Schema(description = "LOT 번호", example = "0000000001")
    private String fromLot;

    @Schema(description = "재고 ID", example = "RT20241227220885700710")
    private String fromStockId;

    @Schema(description = "재고 등급(GRADE)", example = "S4")
    private String fromStockGrade;

    @Schema(description = "재고 타입(TYPE)", example = "BAD")
    private String fromStockType;

    @Schema(description = "조정 수량", example = "240")
    private Double tranQty;

    @Schema(description = "LOT Table 01(유통기한 등)", example = "STD")
    private String lotTable01;

    @Schema(description = "사유 코드", example = "CG01")
    private String reasonCode;

    @Schema(description = "사유 메시지", example = "품질불량 → 가용전환")
    private String reasonMsg;

    @Schema(description = "변경 후 재고 등급", example = "STD")
    private String toStockGrade;

    @Schema(description = "조정 박스 수량", example = "0")
    private Double tranBox;

    
}
