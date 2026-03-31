package cjfw.wms.ms.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.29 
 * @description : 센터 권역 행정동 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@Schema(description = "센터 권역 행정동 응답 DTO")
public class MsCenterDistrictHjdongResDto {
	
	@Schema(description = "테이블 키")
	private String serialkey;
	
	@Schema(description = "센터 코드")
	private String dccode;
	
	@Schema(description = "센터명")
	private String dcname;
	
	@Schema(description = "행정동코드")
    private String hjdongCd;
	
	@Schema(description = "행정동명")
    private String hjdongNm;
	
	@Schema(description = "시군구명")
    private String sigKorNm;
	
	@Schema(description = "시도명")
    private String ctpKorNm;
	
	@Schema(description = "유효시작일")
    private String fromDate;
	
	@Schema(description = "유효종료일")
    private String toDate;
	
	@Schema(description = "삭제여부")
    private String delYn;

	@Schema(description = "비고")
    private String rmk;

	@Schema(description = "중복여부(FW/FO)")
    private Boolean isDuplicate;

    @Schema(description = "폐지여부 (MVMN_RES_CD=63이면 Y)")
    private String abolishedYn;
	
	@Schema(description = "FW/FO 중복 발생 최소 일자")
	private String overlapFromdate;
	
	@Schema(description = "FW/FO 중복 발생 최대 일자")
	private String overlapTodate;

	@Hidden
	private int rowCount;

	@Schema(description = "검증 에러 메시지")
    private List<String> errorMessages;

    public void addMessage(String message) {
        if (errorMessages == null) {
            errorMessages = new ArrayList<>();
        }
        errorMessages.add(message);
    }
}
