package cjfw.wms.cm.dto;

import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo (jw.park@cj.net) 
 * @date : 2025.05.09 
 * @description : 로케이션 팝업 리스트조회 resDto 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 ParkJinWoo (jw.park@cj.net) 생성
 */
@Data
public class CmDriverPopupResDto extends CommonDto {

    /** 기사 코드*/
    @Schema(description = "기사 코드", example = "D001")
    private String code;

    /** 기사 이름 */
    @Schema(description = "기사 이름", example = "홍길동")
    private String name;
    
    /** 운전자 1 전화번호 */
    @MaskingTelno
    @Schema(description = "운전자 1 전화번호")
    private String phone1;

    /** 운전자 2 전화번호 */
    @MaskingTelno
    @Schema(description = "운전자 2 전화번호")
    private String phone2;    


}
