package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.05.27 
 * @description : 존정보 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.27 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MsZoneManagerEntity extends CommonDto {
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "619")
	private String serialkey;
	
	/** 센터코드 */
	@Schema(description = "센터코드", example = "2690")
	private String dccode;

	/** 멀티센터코드 */
	@Schema(description = "멀티센터코드", example = "2690, 2600")
	private String multiDccode;
		
	/** 존코드 */
	@Schema(description = "존코드", example = "L05")
	private String zone;
	
	/** 멀티존코드 */
	@Schema(description = "멀티존코드", example = "L05, F02")
	private String multiZone;
	
	/** 존설명 */
	@Schema(description = "존설명", example = "일반 랙(보관/피킹)")
	private String description;
		
	/** 입고 전략 */
	@Schema(description = "입고 전략", example = "STD")
	private String instrategy;
	
	/** 출고 전략 */
	@Schema(description = "출고 전략", example = "STD")
	private String outstrategy;
		
	/** 상태 */
	@Schema(description = "상태", example = "00")
	private String status;		

	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "N")
	private String delYn;
	
	/** 저장유형 */
	@Schema(description = "저장유형", example = "")
	private String storagetype;
	
	/** 데이터흐름제어 */
	@Schema(description = "데이터흐름제어", example = "")
	private String trafficcop;
	
	/** 아카이브제어 */
	@Schema(description = "아카이브제어", example = "")
	private String archivecop;
	
	/** 구간(방) */
	@Schema(description = "구간(방)", example = "")
	private String zoneGugan;
	
	/** 등록자 */
	@Schema(description = "등록자", example = "")
	private String addwho;
	
	/** 등록일시 */
	@Schema(description = "등록일시", example = "")
	private String adddate;
	
	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String editwho;
	
	/** 수정일시 */
	@Schema(description = "수정일시", example = "")
	private String editdate;

}