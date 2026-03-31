package cjfw.wms.ms.dto;

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
 * @date : 2025.05.27 
 * @description : 존정보 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.27 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MsZoneManagerReqDto extends CommonDto {
	/** 센터코드 */
	@Schema(description = "센터코드", example = "2690")
	private String dccode;
	
	/** 존코드 */
	@Schema(description = "존코드", example = "L05")
	private String zone;
	
	/** 존설명 */
	@Schema(description = "존설명", example = "일반 랙(보관/피킹)")
	private String description;
		
	/** 입고 전략 */
	@Schema(description = "입고 전략", example = "STD")
	private String instrategy;
	
	/** 출고 전략 */
	@Schema(description = "출고 전략", example = "STD")
	private String outstrategy;
	
	/** 저장유형 */
	@Schema(description = "저장유형", example = "")
	private String storagetype;
	
	/** 구간(방) */
	@Schema(description = "구간(방)", example = "")
	private String zoneGugan;
		
	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "N")
	private String delYn;
	
	/** 상태 */
	@Schema(description = "상태", example = "00")
	private String status;
	
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "619")
	private String serialkey;

}
