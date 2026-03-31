package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo (jw.park@cj.net) 
 * @date : 2025.05.09 
 * @description : 로케이션 팝업 리스트 조회 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 ParkJinWoo (jw.park@cj.net) 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CmCarPopPopupResDto {

    /**
     * 차량번호
     */
    @Schema(description = "차량번호", example = "서울82바7771")
    private String code;

    /**
     * 기사명
     */
    @Schema(description = "기사명", example = "홍길동")
    private String driverName;

    /**
     * 계약유형
     */
    @Schema(description = "계약유형", example = "")
    private String contractType;

    /**
     * 구역 코드
     */
    @Schema(description = "구역코드", example = "")
    private String name;
    
    /**
     * 운송협력사코드
     */
    @Schema(description = "운송협력사코드", example = "")
    private String custKey;

    @Schema(description = "운송사코드")
    private String courier;

    @Schema(description = "운송사명")
    private String couriername;

    @Schema(description = "2차 운송사코드")
    private String caragentkey;

    @Schema(description = "2차 운송사명")
    private String caragentname;
}
