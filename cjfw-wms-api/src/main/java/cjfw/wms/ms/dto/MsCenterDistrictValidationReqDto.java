package cjfw.wms.ms.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.18 
 * @description : 센터 권역 검증 DTO (신규건 포함 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.18 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "센터 권역 조회 조건 DTO")
public class MsCenterDistrictValidationReqDto {
	
	private Set<String> serialkeySet;
	
	private List<MsCenterDistrictHjdongReqDto> districtHjdongList;

	public void addKey(String value) {
		if(serialkeySet == null) {
			serialkeySet = new HashSet<>();
		}
		serialkeySet.add(value);
	}
	
	public void addHjdong(MsCenterDistrictHjdongReqDto dto) {
		if(districtHjdongList == null) {
			districtHjdongList = new ArrayList<>();
		}
		districtHjdongList.add(dto);
	}
}
