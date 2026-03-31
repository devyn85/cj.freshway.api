package cjfw.wms.ib.entity;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.08.21
 * @description : 지표/모니터링 > 마감지표 > 마감상태 관리 Entity
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.21 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표/모니터링 > 마감지표 > 마감상태 관리 Entity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbCloseEntity extends CommonProcedureDto {
    
	/* 마감일자 */
	@Schema(description = "마감일자")
	private String closedt;

	/* 물류센터코드 */
	@Schema(description = "물류센터코드")
	private String dccode;

	/* 물류센터명 */
	@Schema(description = "물류센터명")
	private String dcname;

	/* 플랜트 */
	@Schema(description = "플랜트")
	private String plant;

	/* 삭제여부 */
	@Schema(description = "삭제여부")
	private String delYn;
	
	/* SAP 등록여부 */
	@Schema(description = "SAP 등록여부")
	private String sapYn;

	/* 일련번호 */
	@Schema(description = "일련번호")
	private String serialkey;
    
}