package cjfw.core.excel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : ExcelDownload 기능을 구현한 Class
 * 				  엑셀다운로드 기능 구현을 위한 모델 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Data
public class ExcelDownload {
	/** 다운로드 할 파일명  */
    private String fileName;
    
    /** Grid데이터(base64 인코딩)  */
    private String data;
    
    /** 확장자(xlsx)  */
    @JsonIgnore
    private String extension;
    
    /** 사용자ID(DRM처리용)  */
    @JsonIgnore
    private String userId;
    
    /** 사용자명(DRM처리용)  */
    @JsonIgnore
    private String userNm;
    
    /** DRM 사용 여부  */
    private String drmUseYn;
    
    /** 소속구분 */
    private String emptype;
}