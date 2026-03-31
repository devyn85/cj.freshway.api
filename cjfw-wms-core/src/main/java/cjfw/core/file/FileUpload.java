package cjfw.core.file;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : FileUpload 관련 처리를 위한 모델을 정의한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUpload {
	//파일타입 코드
	private String fileTpCd;
	//파일타입
	private String fileTp;
	//원 파일 이름
    private String attchFileNm; 
    //연동 파일 이름
    private String transFileNm; 
    //파일 사이즈
    private String attchFileSz; 
    //파일 확장자명
    private String attchFileExtNm;
    //첨부파일그룹
    private String attchFileGrpNo;
    //첨부파일번호
    private String attchFileNo;
    //상태 (I/U/D)
    private String rowStatus;
    //저장 경로
    private String savePathNm1;
    //실 저장된 파일 이름
    private String savePathNm2;
    @JsonIgnore
    private MultipartFile file;
    @JsonIgnore
    private String userId;
    @JsonIgnore
    private String attchFileCmnt;
    
    @Schema(description = "게시판타입", example = "board")
	private String type;
    
	@Schema(description = "게시글번호", example = "2023050300002")
	private String refKey;
	
	@Schema(description = "파일순번", example = "1")
	private String fileSeq;
}

/*
 * Data 예시
[
 	//신규 파일 저장
 	{"fileTp" : "cpt",
	"fileTpCd" : "2",
	"attchFileNm" : "제목 없음.png",
	"attchFileSz" : "3860",
	"attchFileExtNm" : "png",
	"attchFileGrpNo" : "",
	"attchFileNo" : "",
	"rowStatus" : "I"},
	//신규 파일 저장
	{"fileTp" : "cpt",
	"fileTpCd" : "2",
	"attchFileNm" : "제목 없음 - 복사본.png",
	"attchFileSz" : "3860",
	"attchFileExtNm" : "png",
	"attchFileGrpNo" : "",
	"attchFileNo" : "",
	"rowStatus" : "I"},
	
	//파일 삭제
	{"attchFileGrpNo":"2146",
	 "attchFileNo":"1",
	 "savePathNm1":"cpt/2022/0628",
	 "savePathNm2":"00000000000021460001",
	 "rowStatus":"D"}
]
 */
