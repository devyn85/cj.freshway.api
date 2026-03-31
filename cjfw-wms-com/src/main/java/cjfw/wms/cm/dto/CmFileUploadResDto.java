package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.29 
 * @description : 업로드 파일 정보 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "업로드 파일 정보 응답 DTO")
public class CmFileUploadResDto {
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
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}