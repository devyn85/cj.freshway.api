package cjfw.wms.cb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.09.19
 * @description : 공지사항 수신처 정보 응답 DTO
 */
@Data
@Schema(description = "공지사항 수신처 정보 응답")
public class CbNoticeRecvResDto {
    @Schema(description = "수신자 구분 (R: 그룹, U: 사용자)", example = "R")
    private String rcvcustType;

    @Schema(description = "수신자명(그룹명 또는 사용자명)", example = "서울센터그룹")
    private String recvNm;
    
    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
