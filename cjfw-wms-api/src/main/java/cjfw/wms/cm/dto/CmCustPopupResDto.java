package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 팝업 고객조회 Response DTO
 */
@Data
public class CmCustPopupResDto {
	// 거래처코드
    private String code;
    // 거래처명
    private String name;
    // 거래처유형
    private String custType;
    // 주소
    private String address1;
    
    // 상세 주소
    private String address2;
    
    @Schema(description = "실착지 코드")
    private String truthCustKey;
    
    @Schema(description = "실착지 명")
    private String truthCustName;
    
    @Schema(description = "관리처 코드")
    private String mngCustkey;
    
    @Schema(description = "관리처 명")
    private String mngName;
    
    @Schema(description = "본점코드")
    private String hqCustKey;
    
    @Schema(description = "본점명")
    private String hqName;
    
    @Schema(description = "판매처 코드")
    private String saleCustKey;
    
    @Schema(description = "판채처명")
    private String saleName;
    
    @Schema(description = "물류센터코드")
    private String dlvDccode;
    
    @Schema(description = "본점코드")
    private String dlvCustKey;
    
    @Schema(description = "본점명")
    private String dlvDescription;
    
    @Schema(description = "본점주소")
    private String dlvAddress1;    
    
    @Schema(description = "사업주명")
    private String owner;
}