package cjfw.wms.ib.dto;

import java.util.List;

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
 * @date : 2025.08.21
 * @description : Admin > 모니터링 > 마감상태 관리 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.21 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "Admin > 모니터링 > 마감상태 관리 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbCloseReqDto extends CommonProcedureDto {
	
	/* 물류센터코드 */
	@Schema(description = "물류센터코드")
	private String dccode;

	/* 전표일자 */
	@Schema(description = "전표일자")
	private String docdt;

	/* 메인그리드 저장 리스트 */
	@Schema(description = "메인그리드 저장 리스트")
	List<IbCloseResDto> saveList;

}
