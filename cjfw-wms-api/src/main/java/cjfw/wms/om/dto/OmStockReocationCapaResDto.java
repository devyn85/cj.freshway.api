package cjfw.wms.om.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.24 
 * @description : 재고재배치조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class OmStockReocationCapaResDto {
			
	/** capa 항목조회 센터 */
    @Schema(description = "capa Plt 항목조회 센터", example = "")
    private String dccode;

    /** capa 항목조회 센터 */
    @Schema(description = "capa Plt 항목조회 센터", example = "")
    private String dcname;
    
    /** capa 항목조회 총량 */
    @Schema(description = "capa Plt 항목조회 총량", example = "")
    private String totalCapa;

    /** capa 항목조회 실온 */
    @Schema(description = "capa Plt 항목조회 실온", example = "")
    private String roomTemp;
    
    /** capa 항목조회 냉장 */
    @Schema(description = "capa Plt 항목조회 냉장", example = "")
    private String refrigeration;
    
    /** capa 항목조회 냉동 */
    @Schema(description = "capa Plt 항목조회 냉동", example = "")
    private String frozen;

    
	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String addwho;

	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String editwho;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;
		
}