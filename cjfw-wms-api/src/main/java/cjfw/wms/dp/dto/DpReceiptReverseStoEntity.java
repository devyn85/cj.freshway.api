package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.08.22
 * @description : 역 STO Entity Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "역 STO Entity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpReceiptReverseStoEntity {
	@Schema(description = "from_dccode")
	private String fromDccode;
	
	@Schema(description = "docno")
	private String docno;
	
	@Schema(description = "docline")
	private String docline;
	
	@Schema(description = "returnqty")
	private String returnqty;
	
	@Schema(description = "sto_docdt")
	private String stoDocdt;
	
	@Schema(description = "sto_docno")
	private String stoDocno;

	@Schema(description = "sto_docline")
	private String stoDocline;
	
	@Schema(description = "rmk")
	private String rmk;
	
	@Schema(description = "adddate")
	private String adddate;
	
	@Schema(description = "editdate")
	private String editdate;
	
	@Schema(description = "addwho")
	private String addwho;
	
	@Schema(description = "editwho")
	private String editwho;
}
