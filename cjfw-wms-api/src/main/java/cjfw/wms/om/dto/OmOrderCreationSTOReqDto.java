package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.09.26
 * @description : 물류센터간이체 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.26 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "물류센터간 이체 요청")
public class OmOrderCreationSTOReqDto extends CommonProcedureDto {
    @Schema(description = "저장 대상 리스트(임시테이블 적재용)")
    private java.util.List<OmOrderCreationSTOResDto> saveList;

    // 공통 필터
    @Schema(description = "STORERKEY")
    private String STORERKEY;
    
    @Schema(description = "출고 DC코드")
    private String FROM_DCCODE;
    
    @Schema(description = "출고 ORGANIZE")
    private String FROM_ORGANIZE;
    
    @Schema(description = "재고위치(STOCKTYPE)")
    private String STOCKTYPE;
    
    @Schema(description = "재고등급(STOCKGRADE)")
    private String STOCKGRADE;
    
    @Schema(description = "조회 LOC From")
    private String FROMLOC;
    
    @Schema(description = "조회 LOC To")
    private String TOLOC;
    
    @Schema(description = "피킹존")
    private String ZONE;
    
    @Schema(description = "시리얼번호")
    private String SERIALNO;
    
    @Schema(description = "BL번호(운송장)")
    private String BLNO;
    
    @Schema(description = "계약처 코드")
    private String CONTRACTCOMPANY;
    
    @Schema(description = "보관유형(STORAGETYPE)")
    private String STORAGETYPE;
    
    @Schema(description = "시리얼여부(Y/N)")
    private String SERIALYN;           // 'Y' / 'N'
    
    @Schema(description = "SKU 다중선택 CSV")
    private String MULTI_SKU;          // CSV string
    
    @Schema(description = "LOT1 필터 여부(Y:STD 제외, N:STD만)")
    private String LOTTABLE01YN;       // 'Y' or 'N'
    
    @Schema(description = "시리얼검색 여부(비어있지 않으면 INNER JOIN)")
    private String SEARCHSERIAL;       // not empty -> INNER JOIN
    
    @Schema(description = "정렬키(LOC/DATE)")
    private String SORTKEY;            // LOC / DATE

    @Schema(description = "공급 DC")
    private String DC_A;               // 공급 DC
    
    @Schema(description = "수령 DC")
    private String DC_B;               // 수령 DC
    
    @Schema(description = "출고일자(YYYY-MM-DD)")
    private String DELIVERYDATE;       // 출고일자

    // 호환 필드 (일부 화면에서 사용하는 별칭)
    @Schema(description = "출고 DC코드(별칭)")
    private String fromDccode;
    
    @Schema(description = "공급 DC(별칭)")
    private String dcA;

    @Schema(description = "이체유형(STO/RSTO 등)")
    private String STOTYPE;

    @Schema(description = "반품판정처리용")
    private String rtQCConfirmYn;
    
    @Schema(description = "공급받는센터")
    private String toDccode;
    
    @Schema(description = "공급받는창고")
    private String organize;
}
