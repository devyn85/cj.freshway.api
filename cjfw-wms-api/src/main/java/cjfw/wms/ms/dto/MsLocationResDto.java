package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.06.19 
 * @description : 로케이션정보(목록) 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "로케이션 일반조회 응답 DTO")
public class MsLocationResDto {
	@Schema(description = "로케이션", example = "A02-0010")
	private String loc;
	
	@Schema(description = "내역(설명)", example = "이천센터 3층 축육 냉동")
	private String description;
	
	@Schema(description = "창고구분", example = "1")
	private String whArea;
	
	@Schema(description = "창고층", example = "3층")
	private String whAreaFloor;
	
	@Schema(description = "피킹존", example = "A02")
	private String zone;
	
	@Schema(description = "로케이션유형", example = "피킹")
	private String locType;
	
	@Schema(description = "로케이션종류", example = "랙")
	private String locCategory;
	
	@Schema(description = "로케이션레벨", example = "저단")
	private String locLevel;
	
	@Schema(description = "로케이션 적재무게", example = "9")
	private Integer locWeight;
	
	@Schema(description = "로케이션구분", example = "정상")
	private String locFlag;
	
	@Schema(description = "멀티로케이션여부", example = "N")
	private String multiLocYn;
	
	@Schema(description = "삭제여부", example = "Y")
	private String delYn;
	
    @Schema(description = "등록자", example = "SYSTEM")
    private String addWho;

    @Schema(description = "등록일시", example = "2018-12-21 17:12:12")
    private String addDate;

    @Schema(description = "최종변경자", example = "hyejeon822")
    private String editWho;
    
    @Schema(description = "등록자ID", example = "SYSTEM")
    private String addWhoId;
    
    @Schema(description = "최종변경자ID", example = "hyejeon822")
    private String editWhoId;

    @Schema(description = "수정일시", example = "2018-12-21 17:15:03")
    private String editDate;
    
    @Schema(description = "입고 가능 여부", example = "Y")
    private String inYn;

    @Schema(description = "출고 가능 여부", example = "Y")
    private String outYn;
}
