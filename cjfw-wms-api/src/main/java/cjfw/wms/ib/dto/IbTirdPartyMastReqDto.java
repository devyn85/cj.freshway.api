package cjfw.wms.ib.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.25
 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbTirdPartyMastReqDto extends CommonProcedureDto {
	
	/* dccode */
	@Schema(description = "dccode")
	private String dccode;
	
	/* fromCustkey */
	@Schema(description = "fromCustkey")
	private String fromCustkey;
	
    /* 협력사-멀티 */
	@MultiSearch
    @Schema(description = "협력사-멀티")
    private List<List<String>> fromCustkeyMulti;

	/* calDocdtFrom */
	@Schema(description = "calDocdtFrom")
	private String calDocdtFrom;

	/* calDocdtTo */
	@Schema(description = "calDocdtTo")
	private String calDocdtTo;
	
	/* calSlipdtFrom */
	@Schema(description = "calSlipdtFrom")
	private String calSlipdtFrom;
	
	/* calSlipdtTo */
	@Schema(description = "calSlipdtTo")
	private String calSlipdtTo;
	
	/* ordertype */
	@Schema(description = "ordertype")
	private String ordertype;
	
	/* status */
	@Schema(description = "status")
	private String status;
	
	/* deliverydate */
	@Schema(description = "deliverydate")
	private String deliverydate;
	
	/* confirmyn */
	@Schema(description = "confirmyn")
	private String confirmyn;

	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;

	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti;
	
	/* 단가마스터_탭 저장 리스트 */
	@Schema(description = "단가마스터_탭 저장 리스트")
	List<IbTirdPartyMastResT1Dto> saveT1List;
	
	/* 협력사관리_탭 저장 리스트 */
	@Schema(description = "협력사관리_탭 저장 리스트")
	List<IbTirdPartyMastResT2Dto> saveT2List;

	/* 검수관리_탭 저장 리스트 */
	@Schema(description = "검수관리_탭 저장 리스트")
	List<IbTirdPartyMastResT3Dto> saveT3List;
	
	/* 검수관리_탭 상세 저장 리스트 */
	@Schema(description = "검수관리_탭 상세 저장 리스트")
	List<IbTirdPartyMastResT3DetailDto> saveT3DetailList;
	
	/* 정산관리_탭 저장 리스트 */
	@Schema(description = "정산관리_탭 저장 리스트")
	List<IbTirdPartyMastResT4Dto> saveT4List;
}