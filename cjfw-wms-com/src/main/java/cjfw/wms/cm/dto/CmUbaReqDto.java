package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.07.28 
 * @description : 그룹정보유출보안관제(UBA) 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Schema(description = "그룹정보유출보안관제(UBA) 요청")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CmUbaReqDto extends CommonDto {	
	/** 행위자의 시스템 ID */
    @Schema(description = "행위자의 시스템 ID", example = "")
    private String userId;
    
    /** 행위자의 CJWorld ID */
    @Schema(description = "행위자의 CJWorld ID", example = "")
    private String emailAlias;
    
    /** 행위자 이름 */
    @Schema(description = "행위자 이름", example = "")
    private String userName;
    
    /** 임직원 여부 구분 (임직원:Y, 그외:N) */
    @Schema(description = "임직원 여부 구분 (임직원:Y, 그외:N)", example = "")
    private String employeeYn;
    
    /** 접속 Client IP */
    @Schema(description = "접속 Client IP", example = "")
    private String clientIp;
    
    /** 접근 메뉴명 */
    @Schema(description = "접근 메뉴명", example = "")
    private String menuName;
    
    /** 조회:1, 수정:2, 삭제:3, 출력:4, 다운로드:5 */
    @Schema(description = "조회:1, 수정:2, 삭제:3, 출력:4, 다운로드:5", example = "")
    private String actionCode;
    
    /** 고유식별정보:1 / 그외:2(고유식별정보 : 주민등록번호/여권번호/운전면허번호/외국인등록번호 등) */
    @Schema(description = "고유식별정보:1 / 그외:2(고유식별정보 : 주민등록번호/여권번호/운전면허번호/외국인등록번호 등)", example = "")
    private String searchItem;
    
    /** 행위 1건에 포함되는 고객정보 개수 */
    @Schema(description = "행위 1건에 포함되는 고객정보 개수", example = "")
    private String qCount;
    
    /** 행위에 대하여 처리되는 대상(고객) 식별자(고객 ID, 이름, 회원번호, 송장번호 등) */
    @Schema(description = "행위에 대하여 처리되는 대상(고객) 식별자(고객 ID, 이름, 회원번호, 송장번호 등)", example = "")
    private String searchId;
}