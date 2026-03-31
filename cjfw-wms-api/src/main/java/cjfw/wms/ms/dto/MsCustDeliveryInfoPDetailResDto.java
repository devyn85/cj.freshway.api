package cjfw.wms.ms.dto;

import cjfw.wms.common.annotation.MaskingEmail;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.08.20 
 * @description : 협력사 입고검수결과 수신자 마스터정보 조회(목록) 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "협력사 입고검수결과 수신자 마스터정보 조회(목록)")
public class MsCustDeliveryInfoPDetailResDto extends CommonDto{
	/** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialkey;
    
	/** 유효성 체크결과 */
    @Schema(description = "유효성 체크결과", example = "")
    private String processYn;
    
    /** 거래처코드 유효성 */
    @Schema(description = "거래처코드 유효성", example = "")
    private String custkeyYn;
    
    /** 거래처명 유효성 */
    @Schema(description = "거래처명 유효성", example = "")
    private String custnameYn;
    
    /** 업데이트 여부 */
    @Schema(description = "업데이트 여부", example = "")
    private String updateYn;
    
    /** 거래처코드 중복 여부 */
    @Schema(description = "거래처코드 중복 여부", example = "")
    private String duplicateYn;
    
	/** checkyn */
    @Schema(description = "", example = "")
    private String checkyn;

    /** 구분코드 */
    @Schema(description = "구분코드", example = "")
    private String gubun;

    /** 고객사코드 */
    @Schema(description = "고객사코드", example = "")
    private String storerkey;

    /** 거래처코드 */
    @Schema(description = "거래처코드", example = "")
    private String custkey;

    /** 거래처명 */
    @Schema(description = "거래처명", example = "")
    private String custname;

    /** 수신범위코드 */
    @Schema(description = "수신범위코드", example = "")
    private String rcvCd;

    /** SMS수신유무 */
    @Schema(description = "SMS수신유무", example = "")
    private String smsYn;

    /** 메일수신유무 */
    @Schema(description = "메일수신유무", example = "")
    private String mailYn;

    /** 성명 */
    @MaskingName
    @Schema(description = "성명", example = "")
    private String name;

    /** 전화번호 */
    @MaskingTelno
    @Schema(description = "전화번호", example = "")
    private String phone;

    /** email */
    @MaskingEmail
    @Schema(description = "이메일", example = "")
    private String email;

    /** etc */
    @Schema(description = "기타", example = "")
    private String etc;

    /** 삭제여부 */
    @Schema(description = "삭제여부", example = "")
    private String delYn;

    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
