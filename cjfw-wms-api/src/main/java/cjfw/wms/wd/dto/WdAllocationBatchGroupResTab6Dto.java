package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.21 
 * @description : 분배예외처리거래처 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.21 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "분배예외처리거래처 목록 결과")
public class WdAllocationBatchGroupResTab6Dto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;
    
    /** STORERKEY */
    @Schema(description = "STORERKEY", nullable = false, example = "")
    private String storerkey;

    /** 거래처코드 */
    @Schema(description = "거래처코드", nullable = false, example = "")
    private String custkey;
    
    /** 거래처명 */
    @Schema(description = "거래처명", nullable = false, example = "")
    private String custkeyname;
    

    /** 비고 */
    @Schema(description = "비고", nullable = false, example = "")
    private String rmk1;
    
    /** 사용여부 */
    @Schema(description = "사용여부", nullable = false, example = "")
    private String useYn;
    
    @Schema(description = "생성인", example = "홍길동")
    private String addwho;
    @Schema(description = "등록일자", example = "2025-06-19T10:00:00")
    private String adddate;
    @Schema(description = "최종변경자", example = "김철수")
    private String editwho;
    @Schema(description = "최종변경시간", example = "2025-06-19T11:30:00")
    private String editdate;
}
