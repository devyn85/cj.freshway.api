package cjfw.wms.portal.common.sample.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CntrPickingGroupBatchsaveReqDto {
//	DCCODE, PLANT, STORAGETYPE, DISTANCETYPE
	@Schema(description = "피킹 리스트", example = "", nullable = false)
	@NotEmpty
	private List<CntrPickingGroupBatchsaveReqDto.insertParams> insertParams;
	
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class insertParams{
	@Schema(description = "센터코드",nullable = false)
	private String	dccode;
	@Schema(description = "플랜트",nullable = false)
	private String	plant;
	@Schema(description = "저장유형",nullable = false)
	private String	storagetype;
	@Schema(description = "원거리타입",nullable = false)
	private String	distancetype;
	
	private String	serialkey;
	private String	batchgroup;
	private String	description;
	private String	status;
	private String	delyn;
	private String	trafficcop;
	private String	archivecop;
	private String	adddate;
	private String	editdate;
	private String	addwho;
	private String	editwho;
	private String	organize;
	private String	area;
	private String	ordertype;
	private String	etccode1;
	private String	etccode2;
	private String	etccode3;
	private String	etccode4;
	private String userId;
	@Schema(description = "피킹 리스트 플래그 ", example = "", nullable = false)
	private String flag;
	}
}
