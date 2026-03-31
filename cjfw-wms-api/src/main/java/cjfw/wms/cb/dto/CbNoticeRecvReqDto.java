package cjfw.wms.cb.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.09.19 
 * @description : 공지사항 수신처 조회 요청 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "커뮤니티 게시판 조회 요청")
public class CbNoticeRecvReqDto extends CommonDto {
	
    /** 게시판번호 */
    @Schema(description = "게시판번호", example = "")
    private String brdNum;
    
    /** SEQ */
    @Schema(description = "SEQ", example = "1")
    private Integer seq;

    /** 수신자구분 */
    @Schema(description = "수신자구분", example = "R")
    private String rcvcustType;

    /** 수신그룹ID */
    @Schema(description = "수신그룹ID", example = "1")
    private Long recvGroupId;

    /** 사용자ID */
    @Schema(description = "사용자ID", example = "user01")
    private String userId;

    /** 등록일시 */
    @Schema(description = "등록일시", example = "20250918")
    private String addDate;

    /** 수정일시 */
    @Schema(description = "수정일시", example = "20250918")
    private String editDate;

    /** 등록자 */
    @Schema(description = "등록자", example = "SYSTEM")
    private String addWho;

    /** 수정자 */
    @Schema(description = "수정자", example = "SYSTEM")
    private String editWho;

 
}