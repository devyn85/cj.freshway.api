package cjfw.core.file.dto;

import lombok.Data;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : FileUploadResDto(파일 업로드 조회 응답 정보) 정보를 담은 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Data
public class FileUploadResDto {
	// 첨부파일 그룹명
    private String attchFileGrpNo;  
}