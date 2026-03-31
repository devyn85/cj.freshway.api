package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.18 
 * @description : 거래처별POP관리 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsPopMngSTOResDto {
			
	/** 광역센터코드 */
	@Schema(description = "광역센터코드", example = "")
	private String dccode;

	/** 고객코드 */
	@Schema(description = "고객코드", example = "")
	private String storerkey;
	
	/** 센터코드 */
	@Schema(description = "실 주문센터", example = "")
	private String toDccode;	

	/** POP 번호 */
	@Schema(description = "POP 번호", example = "")
	private String popno;
	
	/** 저장 여부 */
	@Schema(description = "저장 여부", example = "")
	private String saved;
	
	/** 시간 유형 */
	@Schema(description = "시간 유형", example = "")
	private String timeFlag;
	
	/** TO 배송일자 */
	@Schema(description = "TO 배송일자", example = "")
	private String toDeliverydt;

	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String addwho;

	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String editwho;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;

	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String regNm;
	
	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String updNm;

    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
}