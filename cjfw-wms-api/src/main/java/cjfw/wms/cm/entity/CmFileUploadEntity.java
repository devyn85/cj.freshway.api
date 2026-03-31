package cjfw.wms.cm.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : breaker3317 
 * @date : 2025.08.30 
 * @description : 업로드파일정보 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.30 breaker3317 생성 </pre>
 */
@Data
public class CmFileUploadEntity {
	/** 게시판타입 */
	@Schema(description = "게시판타입", example = "board")
	private String type;
	
	/** 게시글번호 */
	@Schema(description = "게시글번호", example = "2023050300002")
	private String refKey;
	
	/** 파일순번 */
	@Schema(description = "파일순번", example = "1")
	private String fileSeq;
	
	/** 원본파일명 */
	@Schema(description = "원본파일명", example = "KX마감진행 현황_기말재고현황.xls")
	private String sourceFileNm;
	
	/** 파일경로 */
	@Schema(description = "파일경로", example = "board/0002/0180/516/000/010")
	private String uploadedDirPath;
	
	/** 파일명 */
	@Schema(description = "파일명", example = "00020180516000010001")
	private String uploadedFileNm;
	
	/** 파일크기 */
	@Schema(description = "파일크기", example = "45280")
	private String fileSize;
}