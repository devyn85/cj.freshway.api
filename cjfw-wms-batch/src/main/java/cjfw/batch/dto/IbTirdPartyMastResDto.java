package cjfw.batch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.15
 * @description : 3PL물류대행수수료정산 협력사관리_탭 협력사 관리 배치 DTO
 * @issues :ㅉ#
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.15 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "3PL물류대행수수료정산 협력사관리_탭 협력사")
public class IbTirdPartyMastResDto {

	/* DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/* CUSTKEY */
	@Schema(description = "CUSTKEY")
	private String custkey;
	
	/* STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;
	
	/* CNT */
	@Schema(description = "CNT")
	private String cnt;

}
