package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.22
 * @description : 수불마감정보 기능을 구현한 Res DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.22        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "수불마감정보 조회 ")
public class StDailyInoutCloseResDto {

	@Schema(description = "데이터 번호")
    private String serialKey;

    @Schema(description = "센터 코드", example = "2650")
    private String dcCode;

    @Schema(description = "고객사 코드")
    private String storerKey;

    @Schema(description = "수불 일자", example = "20240731")
    private String inoutDt;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "최초 등록 시간", example = "YYYYMMDDHH24MISS")
    private String addDate;

    @Schema(description = "최종 변경 시간", example = "YYYYMMDDHH24MISS")
    private String editDate;

    @Schema(description = "최초 등록자")
    private String addWho;

    @Schema(description = "최종 변경자")
    private String editWho;

    @Schema(description = "상태")
    private String status; 
    
    @Schema(description = "SAP수불마감 여부")
    private String sapInoutCloseYn;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
