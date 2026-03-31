package cjfw.wms.cm.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.18 
 * @description : 사용자메뉴 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
public class CmMyMenuEntity {
	/** 사용자ID */
	@Schema(description = "사용자ID", example = "")
	private String userId;
	
	/** 프로그램코드 */
	@Schema(description = "프로그램코드", example = "")
	private String progCd;
	
	/** 메뉴순번 */
	@Schema(description = "메뉴순번", example = "")
	private String menuSeq;
	
    @Schema(description = "메뉴 타입", example = "M")
    private String menuType;

    @Schema(description = "폴더 명", example = "즐겨찾기")
    private String folderNm;

    @Schema(description = "상위 폴더 ID", example = "ROOT")
    private String uprFolderId;
	
	/** 수정자ID */
	@Schema(description = "수정자ID", example = "")
	private String updId;
	
	/** 등록자ID */
	@Schema(description = "등록자ID", example = "")
	private String regId;
	
	/** 등록일시 */
	@Schema(description = "등록일시", example = "")
	private String regDtm;
	
	/** 수정일시 */
	@Schema(description = "수정일시", example = "")
	private String updDtm;
}