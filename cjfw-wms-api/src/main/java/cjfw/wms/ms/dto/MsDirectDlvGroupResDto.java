package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.23 
 * @description : 발주직송그룹관리 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsDirectDlvGroupResDto {
	
	/** 체크박스 */
	@Schema(description = "체크박스", example = "")
	private String checkyn;
			
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "")
	private String serialkey;

	/** 직송그룹 */
	@Schema(description = "직송그룹", example = "")
	private String directdlvgroup;

	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String dccode;

	/** 센터명 */
	@Schema(description = "센터명", example = "")
	private String dcname;

	/** 고객사코드 */
	@Schema(description = "고객사코드", example = "")
	private String storerkey;

	/** 조직코드 */
	@Schema(description = "조직코드", example = "")
	private String organize;

	/** 조직명칭 */
	@Schema(description = "조직명칭", example = "")
	private String organizename;

	/** 협력사유형 */
	@Schema(description = "협력사유형", example = "")
	private String custtype;

	/** 협력사코드 */
	@Schema(description = "협력사코드", example = "")
	private String custkey;

	/** 조직명칭 */
	@Schema(description = "조직명칭", example = "")
	private String custname;

	/** 총발주량 */
	@Schema(description = "총발주량", example = "")
	private String orderqty;

	/** 단위 */
	@Schema(description = "단위", example = "")
	private String uom;

	/** 메모사항 */
	@Schema(description = "메모사항", example = "")
	private String memo;

	/** 저장 여부 */
	@Schema(description = "저장 여부", example = "")
	private String saved;
	
	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;

	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String addwho;

	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String editwho;
	
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