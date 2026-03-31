package cjfw.wms.ms.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import cjfw.wms.ms.entity.MsPopMngEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.18 
 * @description : 거래처별POP관리 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MsPopMngReqDto extends CommonProcedureDto {
	
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "")
	private String serialkey;
	
	/** 신규데이터번호 */
	@Schema(description = "신규데이터번호", example = "")
	private String newSerialkey;
	
	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String dccode;	

	/** 거래처코드 */
	@Schema(description = "거래처코드", example = "")
	private String custkey;
	
	/** 거래처코드 */
	@MultiSearch
	@Schema(description = "거래처코드-다중OR검색", example = "")
	private List<List<String>> custkeyMulti;

	/** 차량번호 */
	@Schema(description = "차량번호", example = "")
	private String carno;
	
	/** 차량번호 */
	@MultiSearch
	@Schema(description = "차량번호-다중OR검색", example = "")
	private List<List<String>> carnoMulti;

	/** POP 번호 */
	@Schema(description = "POP 번호", example = "")
	private String popno;
	
	/** POP 번호 */
	@MultiSearch
	@Schema(description = "POP 번호-다중OR검색", example = "")
	private List<List<String>> popnoMulti;

	/** 고객코드 */
	@Schema(description = "고객코드", example = "")
	private String storerkey;

	/** 거래처유형 */
	@Schema(description = "거래처유형", example = "")
	private String custtype;

	/** 적용시작일자 */
	@Schema(description = "적용시작일자", example = "")
	private String fromdate;
	
	/** 적용종료일자 */
	@Schema(description = "적용시작일자", example = "")
	private String todate;

	/** 롤테이너 번호 */
	@Schema(description = "롤테이너 번호", example = "")
	private String rolltainerNo;

	/** BCR 사용 여부 */
	@Schema(description = "BCR 사용 여부", example = "")
	private String bcrYn;
	
	/** 대량업로드리스트(excel)관련 컬럼 */
	/** POP 번호 */
	@Schema(description = "POP 번호", example = "")
	private String deliverygroup;
	
	/** BCR 사용 여부 */
	@Schema(description = "BCR 사용 여부", example = "")
	private String other02;
	
	/** 롤테이너 번호 */
	@Schema(description = "롤테이너 번호", example = "")
	private String other01;
	
	/** 적용시작일자 */
	@Schema(description = "적용시작일자", example = "")
	private String deliverydt;
	
	/** 적용시작일자 */
	@Schema(description = "적용시작일자", example = "")
	private String slipdt;
	
	/** 적용시작일자 */
	@Schema(description = "적용시작일자", example = "")
	private String slipno;
	
	/** 적용시작일자 */
	@Schema(description = "적용시작일자", example = "")
	private String sliptype;
	
	/** 대량업로드리스트(excel) */
	@Schema(description = "대량업로드리스트(excel)")
	private List<MsPopMngEntity> saveList;
	
	/** spid */
	@Schema(description = "spid", example = "")
	private String spid;
	
	/** 권한 */
	@Schema(description = "권한", example = "")
	private String authority;
	
	/** 기본코드값 */
	@Schema(description = "기본코드값", example = "")
	private String[] multiBasecode;
			
	/** 적용기간 */
	@Schema(description = "적용기간", example = "")
	private String basedescr;
	
	/** 코드리스트 */
	@Schema(description = "코드리스트", example = "")
	private String codelist;
	
	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "")
	private String delYn;

}
