package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.04.30 
 * @description : 코드마스터 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "코드마스터 조회 응답")
public class CmCodeResDto {
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "34791")
	private String serialkey;
	
	/** 고객사코드 */
	@Schema(description = "고객사코드", example = "FW00")
	private String storerkey;

	/** 코드리스트 */
	@Schema(description = "코드리스트", example = "PARTSD")
	private String codelist;
	
	/** 정렬값 */
	@Schema(description = "정렬값", example = "380")
	private String seqno;

	/** 기본코드값 */
	@Schema(description = "기본코드값", example = "430293")
	private String basecode;

	/** 기본코드설명 */
	@Schema(description = "기본코드설명", example = "소장")
	private String basedescr;
	
	/** 변환코드값 */
	@Schema(description = "변환코드값", example = "430293")
	private String convcode;
	
	/** 변환코드설명 */
	@Schema(description = "변환코드설명", example = "소장")
	private String convdescr;
	
	/** 데이터값1 */
	@Schema(description = "데이터값1", example = "")
	private String data1;
	
	/** 데이터값2 */
	@Schema(description = "데이터값2", example = "")
	private String data2;
	
	/** 데이터값3 */
	@Schema(description = "데이터값3", example = "")
	private String data3;
	
	/** 데이터값4 */
	@Schema(description = "데이터값4", example = "")
	private String data4;

	/** 상태 */
	@Schema(description = "상태", example = "90")
	private String status;

	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "N")
	private String delYn;
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

}