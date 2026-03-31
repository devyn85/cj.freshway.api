package cjfw.wms.tm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 정종문 (loters@cj.net)
 * @date : 2025.08.20
 * @description : TM 주문 목록 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.20 정종문 (loters@cj.net) 생성
 * 2025.08.20 정종문 (loters@cj.net) 매개변수 구조 변경
 * 2025.08.26 정종문 (loters@cj.net) client, search 제거, deliveryType 기본값 추가
 * 2025.08.26 정종문 (loters@cj.net) 거래처 코드(custCode) 및 물류센터 코드(dcCode) 추가
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Slf4j
@Schema(description = "TM 주문 목록 조회 요청 DTO")
public class TmOrderListReqDto extends CommonDto {

    @Schema( description = "배송일자")
    private String deliveryDate;

    @Schema(description = "배송유형")
    private String deliveryType;

    @Schema(description = "거래처명")
    private String custName;

    @Schema(description = "거래처코드")
    private String custCode;
    
}
