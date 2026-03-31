package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.23 
 * @description : 마감주문반영-전표모니터링 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "마감주문반영-전표모니터링 결과")
public class OmCloseMonitoringResDto extends CommonProcedureDto {
	@Schema(description = "물류센터", example = "DC001")
    private String dccode;
    @Schema(description = "배송일자", example = "2025-06-23")
    private String deliverydate;
    @Schema(description = "문서유형", example = "SO")
    private String doctype;
    @Schema(description = "문서번호", example = "DOC001")
    private String docno;
    @Schema(description = "품목번호", example = "1")
    private String docline;
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    @Schema(description = "상품명칭", example = "상품명")
    private String skuname;
    @Schema(description = "원수량", example = "10")
    private String storerorderqty;
    @Schema(description = "고객실적수량", example = "10")
    private String storerconfirmqty;
    @Schema(description = "주문수량", example = "10")
    private String orderqty;
    @Schema(description = "진행수량", example = "5")
    private String processqty;
    @Schema(description = "현장작업량", example = "5")
    private String workqty;
    @Schema(description = "검품수량", example = "5")
    private String inspectqty;
    @Schema(description = "운송장수량", example = "5")
    private String invoiceqty;
    @Schema(description = "확정수량", example = "10")
    private String confirmqty;
    @Schema(description = "배송채널", example = "ONLINE")
    private String channel;
    @Schema(description = "에러사항", example = "삭제미처리")
    private String errmsg;
    @Schema(description = "마감기준시간", example = "D1 17:00 급식(FS)/키즈/유통")
    private String closetimeStandard;
    @Schema(description = "거래처", example = "")
    private String custkey;
    @Schema(description = "거래처명", example = "")
    private String custname;
    @Schema(description = "STORERKEY", example = "STR001")
    private String storerkey;
    @Schema(description = "DOCDT", example = "2025-06-23")
    private String docdt;
}
