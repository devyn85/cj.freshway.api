package cjfw.wms.cm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.06.20 
 * @description : 사용자 관리 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "사용자 관리 요청 DTO")
public class CmUserManagerReqDto extends CommonProcedureDto {	
	/** 사용자ID */
	@Schema(description = "사용자ID", example = "")
	private String userId;
	
	/** 소속구분 */
	@Schema(description = "소속구분", example = "")
	private String empType;
	
	/** 사원번호 */
	@Schema(description = "사원번호", example = "")
	private String empNo;
	
	/** 사용자명 */
	@Schema(description = "사용자명", example = "")
	private String userNm;
	
	/** 상태 */
	@Schema(description = "상태", example = "00")
	private String status;
	
	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "N")
	private String delYn;
	
	/** 업체코드 */
	@Schema(description = "업체코드", example = "")
	private String custkey;
	
	/** 대표사용자ID여부 */
	@Schema(description = "대표사용자ID여부", example = "")
	private String repUserIdYn;
	
	/** 사용자 목록 */
	@Schema(description = "사용자 목록", example = "")
	private List<CmUserManagerReqDto> userList;
	
	/** 센터 권한 목록 */
	@Schema(description = "센터 권한 목록", example = "")
	private List<CmUserManagerConnectReqDto> connectList;
	
	/** 권한 목록 */
	@Schema(description = "권한 목록", example = "")
	private List<CmUserManagerAuthorityReqDto> authorityList;
	
	/** 사용자 상세 */
	@Schema(description = "사용자 상세", example = "")
	private CmUserManagerResDto userDtl;
	
	/** 사용자상태코드 */
	@Schema(description = "사용자상태코드", example = "01")
	private String userStsCd;
}