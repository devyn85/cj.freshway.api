package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.30 
 * @description : 배차마스터체크결과 조회 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04 ParkJinWoo 생성
 */
@Data
@NoArgsConstructor            
@AllArgsConstructor
@Builder
@Schema(description = "배차작업로그 요청 DTO")
public class TmAllocationCheckDetailReqDto extends CommonDto  {
	/** 요약화면에서 선택한 CHECKCODE */
    @Schema(description = "체크 코드")
    private String checkCode;   // PVC_CHECKCODE

    /** 분기별 WHERE 절에 쓰이는 키 값들 */
    @Schema(description = "체크 키 1")
    private String checkKey1;   // PVC_CHECKKEY1

    @Schema(description = "체크 키 2")
    private String checkKey2;   // PVC_CHECKKEY2

    @Schema(description = "체크 키 3")
    private String checkKey3;   // PVC_CHECKKEY3

}
