package cjfw.wms.comfunc.func.bbs.dto;

import java.util.List;

import cjfw.core.file.dto.FileComInfoDto;
import lombok.Data;

/**
 * 파일 리스트 Response DTO
 */
@Data
public class BbsBoardFileGetResDto {
	private List<FileComInfoDto> fileGroup;
	
}
