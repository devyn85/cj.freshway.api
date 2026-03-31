package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 성상수 (kduimux@cj.net) 
 * @date : 2025.05.09 
 * @description : 통합납품서출력 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 성상수 (kduimux@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "통합납품서출력 목록 결과")
public class WdInvoiceTotalResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** 체크여부 */
    @Schema(description = "체크여부", example = "0")
    private String checkyn;

    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carno;

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custkey;

    /** 거래처명 */
    @Schema(description = "거래처명")
    private String custname;

    /** 우선순위 */
    @Schema(description = "우선순위")
    private String priority;

    /** 배송그룹 */
    @Schema(description = "배송그룹")
    private String deliverygroup;

    /** 분할관리처코드 */
    @Schema(description = "분할관리처코드")
    private String mngplcid;

    /** 분할관리처명 */
    @Schema(description = "분할관리처명", example = "CJ대한통운(주)")
    private String mngplcname;

    /** 배송관리처코드 */
    @Schema(description = "배송관리처코드")
    private String shptoid;

    /** 인보이스출력유형 */
    @Schema(description = "인보이스출력유형", nullable = false, example = "WD,RT,RTP")
    private String invoiceprinttype;
    
    /** 인보이스유형 */
    @Schema(description = "인보이스유형", example = "WD")
    private String invoicetype;
    
    /** 피킹리스트번호 */
    @Schema(description = "피킹리스트번호", example = "PLN20250509")
    private String pickListNo;    
    
    @Schema(description = "프로세스 타입", example = "CREATE")
    private String processtype;    
    
    /** 인보이스출력키 */
    @Schema(description = "인보이스출력키", example = "IPK20250509")
    private String invoiceprintkey;    

    @Schema(description = "실착지 코드")
    private String truthcustkey;

    @Schema(description = "실착지 명")
    private String truthcustname;
}
