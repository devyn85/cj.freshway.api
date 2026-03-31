package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

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
@Builder
@Schema(description = "배차작업로그 요청 DTO")
public class TmAllocationCheckMasterResDto  {
	 /** 체크코드 */
    @Schema(description = "체크 코드")
    private String checkCode;

    /** 설명 */
    @Schema(description = "설명")
    private String checkMemo;

    /** 건수 */
    @Schema(description = "건수")
    private Integer checkCnt;

    /** CHECKKEY1 */
    @Schema(description = "추가 키 1")
    private String checkKey1;

    /** CHECKKEY2 */
    @Schema(description = "추가 키 2")
    private String checkKey2;

    /** CHECKKEY3 */
    @Schema(description = "추가 키 3")
    private String checkKey3;
}
