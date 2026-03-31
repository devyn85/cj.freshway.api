package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author        : YeoSeungCheol (pw6375@cj.net) 
 * @date        : 2025.07.17 
 * @description : 운송사정보(목록) 조회 응답 DTO 
 * @issues        :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "운송사정보(목록) 조회 응답 DTO")
public class MsTplUserResDto extends CommonDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
    @Schema(description = "데이터번호")
    private String serialKey;
    
    @Schema(description = "사용자ID")
    private String userId;
    @Schema(description = "사용자ID")
    private String frameOneUserId;
    
    @Schema(description = "사용자명")
    private String userNm;
    @Schema(description = "userNmList")
    private String userNmList;
    
    @Schema(description = "거래처코드")
    private String custKey;
    
    @Schema(description = "거래처명")
    private String custNm;
    
    @Schema(description = "협력사코드")
    private String partnerCd;

    @Schema(description = "협력사명")
    private String partnerNm;
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dcCode;
    
    /** 창고 */
    @Schema(description = "창고")
    private String organize;
    
    /** 창고명*/
    @Schema(description = "창고명")
    private String organizeNm;
    
    /** 시작일자 */
    @Schema(description = "시작일자")
    private String fromDate;

    /** 종료일자 */
    @Schema(description = "종료일자")
    private String toDate;
    
    /** 비고 */
    @Schema(description = "비고")
    private String rmk;
    
    /** 공통 - 작성자*/
    /** 최초등록자*/
    @Schema(description = "최초등록자")
    private String addWho;
    
    /** 최초등록시간*/
    @Schema(description = "최초등록시간")
    private String addDate;
    
    /** 최종변경자*/
    @Schema(description = "최종변경자")
    private String editWho;
    
    /** 최종변경시간*/
    @Schema(description = "최종변경시간")
    private String editDate;
    
    /** 등록자명 */
    @Schema(description = "등록자명")
    private String addWhoName;

    /** 수정자명 */
    @Schema(description = "수정자명")
    private String editWhoName;
    
    /** 행 상태 */
    @Schema(description = "행 상태")
    private String rowStatus;
}
