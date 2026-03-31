package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.04 
 * @description : 정산항목관리 요청 기능을 구현한 Req Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.04 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "정산항목관리 요청 DTO")
public class TmManageEntityMasterListReqDto extends CommonDto  {
	/**세이브 리스트 */
    @Schema(description = "세이브리스트")
	private List<TmManageEntityMasterListResDto> saveList;
    /** DC 코드 */
    @Schema(description = "센터 코드")
    private String dcCode;

    /** 관리항목 */
    @Schema(description = "관리항목")
    private String sttlItemCd;
    @Schema(description = "관리항목")
    private String courier;
  
    /** 불러오기용 */
    private String importCarrier;
    private String importCarrierNm;

}
