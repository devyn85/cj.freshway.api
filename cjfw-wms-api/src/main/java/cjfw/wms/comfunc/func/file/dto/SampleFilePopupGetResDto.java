package cjfw.wms.comfunc.func.file.dto;

import lombok.Data;

/*
 * 샘플 파일 저장 Request DTO
 */
@Data
public class SampleFilePopupGetResDto {

	private String fileTpCd;
	private String regDt;
	private String regNm;
	private String fileCnt;
	private String attchFileSz;
	private String attchFileGrpNo;
	private String savePathNm2;
	private String attchFileNo;
	private String savePathNm1;
	private String regId;
	private String attchFileExtNm;
	private String attchFileNm;

}

/**
 * [MPA 참조]
 * > 요청
 * FROM_DT: "2021-06-20"
 * THRU_DT: "2022-06-20"
 */
