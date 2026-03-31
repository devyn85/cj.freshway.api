package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.09
 * @description : 주문수신모니터링 Req DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "주문수신모니터링 조회")
public class OmInplanMonitoringReqDto extends CommonDto {
	@Schema(description = "멀티 물류센터 코드", example = "2600")
    private String multiDcCode[];
	
	@Schema(description = "물류센터 코드")
    private String dcCode;

    @Schema(description = "배송일자", example = "20250709")
    private String deliveryDt;
    
    @Schema(description = "저장유무")
    private String channel;
    
    @Schema(description = "고객마감유형")
    private String custOrderCloseType;
    
    @Schema(description = "선택유형")
    private String selectType;
    
    @Schema(description = "문서유형")
    private String docType;
    
    @Schema(description = "전송유형")
    private String ifSendType;
    
    @Schema(description = "마감유형")
    private String closeCode;
    
    @Schema(description = "고객코드")
    private String toCustKey;
    
    @Schema(description = "플랜트 코드")
    private String plant;
    
    @Schema(description = "문서번호")
    private String docNo;
    
    @Schema(description = "품목번호")
    private String docLine;
}
