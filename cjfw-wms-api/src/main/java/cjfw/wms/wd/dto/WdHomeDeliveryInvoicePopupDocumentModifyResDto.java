package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.13 
 * @description : 주문변경내역 팝업 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.13 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "주문변경내역 팝업 결과")
public class WdHomeDeliveryInvoicePopupDocumentModifyResDto {
	@Schema(description = "품목번호", example = "1")
    private String docline;
    @Schema(description = "판매단위", example = "EA")
    private String uom;
    @Schema(description = "주문수량", example = "10")
    private BigDecimal orderqty;
    @Schema(description = "고객요청주문수정량", example = "5")
    private String orderadjustqty;
    @Schema(description = "삭제여부", example = "N")
    private String delYn;
    @Schema(description = "최종변경시간", example = "2025-06-13 10:30:00")
    private String editdate;
    @Schema(description = "최종변경자", example = "user123")
    private String editwho;
    @Schema(description = "", example = "INSERT")
    private String command;
    @Schema(description = "", example = "STORER001")
    private String storerkey;
    @Schema(description = "", example = "2025-06-13")
    private String docdt;
    @Schema(description = "", example = "ORDER")
    private String doctype;
    @Schema(description = "", example = "DOC001")
    private String docno;
    @Schema(description = "", example = "Y")
    private String procpossYn;
    @Schema(description = "", example = "성공")
    private String procpossMsg;
}
