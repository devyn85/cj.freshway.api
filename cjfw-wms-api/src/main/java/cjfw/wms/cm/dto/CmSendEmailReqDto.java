package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author      : YeoSeungCheol (pw6375@cj.net)
 * @date        : 2025.09.11
 * @description : 발주용휴일관리 조회(목록) 요청 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11        YeoSeungCheol (pw6375@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "이메일발송 요청 DTO")
public class CmSendEmailReqDto extends CommonDto {
    /** 제목 */
    @Schema(description = "제목")
    private String title;

    /** 내용 */
    @Schema(description = "내용")
    private String conts;

    /** 받는사람 */
    @Schema(description = "받는사람")
    private String recvName;

    /** 받는이메일주소 */
    @Schema(description = "받는이메일주소")
    private String recvEmail;

    /** 받는이메일주소2 */
    @Schema(description = "받는이메일주소2")
    private String recvEmail2;

    /** 보내는사람 */
    @Schema(description = "보내는사람")
    private String sendName;

    /** 보내는이메일주소 */
    @Schema(description = "보내는이메일주소")
    private String sendEmail;

    /** 첨부파일 */
    @Schema(description = "첨부파일")
    private String attchFileName;

    /** 이메일발송유형 */
    @Schema(description = "이메일발송유형")
    private String sendType;    //'STD'(일반), 'RPT'(리포트파일첨부)

    /** 참조메일주소 */
    @Schema(description = "참조메일주소")
    private String refEmailAdd;
    
}
