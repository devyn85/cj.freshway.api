package cjfw.batch.job.msSaleGroupHistory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "")
public class MsSaleGroupHistoryDto {

	@Schema(description = "STORERKEY", example = "", nullable = false)
	private String storerkey;

	@Schema(description = "SALEGROUP", example = "", nullable = false)
	private String saleGroup;

	@Schema(description = "SALEDEPARTMENT", example = "", nullable = false)
	private String saleDepartment;

	@Schema(description = "SALEORGANIZE", example = "", nullable = false)
	private String saleOrganize;

}
