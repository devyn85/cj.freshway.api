package cjfw.core.file;

import lombok.Data;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : FileDownload 관련 처리를 위한 모델을 정의한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Data
public class FileDownload {
	// 저장위치 타입(fax, email, board, approval)
    private String dirType;
    // 저장 경로
    private String savePathNm;
    // 실제 파일 이름
    private String attchFileNm;
    // 다운로드 파일 이름
    private String saveFileNm;
    
    /** DRM 사용 여부  */
    private String drmUseYn;
}
