package cjfw.core.file.dto;

import lombok.Data;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : FileDownloadResDto(파일 다운로드 조회 응답 정보) 정보를 담은 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Data
public class FileDownloadResDto {
    //첨부파일 원 파일 이름
    private String attchFileNm;
    //첨부파일 경로
    private String savePathNm1;
    //첨부파일 파일이름
    private String savePathNm2;
    //첨부파일 확장자
    private String attchFileExtNm;
}
