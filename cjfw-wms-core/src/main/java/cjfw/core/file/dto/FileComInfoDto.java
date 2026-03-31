package cjfw.core.file.dto;

import lombok.Data;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : FileComInfo(파일 공통 정보) 정보를 담은 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Data
public class FileComInfoDto {
	//순번
	private String rownum;
	//파일 이름
	private String attchFileNm;
	//파일 사이즈
	private String attchFileSz;
	//파일 그룹 번호
	private String attchFileGrpNo;
	//파일 번호
	private String attchFileNo;
	//파일 타입 (업무 타입)
	private String fileTpCd;
}
