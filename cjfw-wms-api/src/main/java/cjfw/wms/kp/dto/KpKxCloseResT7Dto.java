package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YoSepPark (dytpq362@cj.net)
 * @date : 2025.11.27
 * @description : 조정요청내역) DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "조정요청내역")
public class KpKxCloseResT7Dto extends CommonDto {
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
    

    @Schema(description = "물류센터")
    private String dccode;

    @Schema(description = "창고코드")
    private String organize;
    
    @Schema(description = "문서번호")
    private String docno;
    
    @Schema(description = "품목번호")
    private String docline;
    
    // req_qty|req_uom|dmd_timestamp|dmh_timestamp
    @Schema(description = "상품코드")
    private String sku;

    @Schema(description = "수신건수")
    private String cnt;

    @Schema(description = "IF_MEMO")
    private String ifMemo;

    @Schema(description = "DMD_TIMESTAMP")
    private String dmdTimestamp;

    @Schema(description = "DMH_TIMESTAMP")
    private String dmhTimestamp;

    @Schema(description = "가용재고")
    private String stQty;

    @Schema(description = "가용외(속성)")
    private String stSQty;

    @Schema(description = "가용(속성)")
    private String stStdQty;

    @Schema(description = "요청량")
    private BigDecimal reqQty;

    @Schema(description = "요청단위")
    private String reqUom;

    
    @Schema(description = "처리상태")
    private String processflag;

    @Schema(description = "처리결과메시지")
    private String processmsg;
}
