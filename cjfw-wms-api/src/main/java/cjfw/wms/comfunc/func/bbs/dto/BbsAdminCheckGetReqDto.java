package cjfw.wms.comfunc.func.bbs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 공지사항 Admin 체크 Request DTO
 */
@Data
public class BbsAdminCheckGetReqDto {
	@JsonIgnore
	private String userId;
    
}
