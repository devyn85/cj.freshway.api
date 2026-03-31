package cjfw.wms.comfunc.func.mail.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Simple메일 전송 샘플 Request DTO
 */
@Data
@Schema(description = "메일 샘플 요청")
public class SampleMailSendReqDto {

    @Schema(description = "수신자 이메일", example = "test@test.net", nullable = true)
    private String receiverAddr; // 수신 이메일 주소(샘플 기준 1개)

    // 샘플기준 Request에 불필요한 필드 Ignore
    @JsonIgnore
    private String mailTemplateId; // 메일 템플릿 아이디
    @JsonIgnore
    private String mailTitle; //메일제목
    @JsonIgnore
    private String senderAddr; // 발송 할  이메일 주소 (from DB)
    @JsonIgnore
    private String senderId; //보내는 ID (DB로그 기록시 수정자 정보로 사용)

    // 첨부파일 정보(샘플 기준 1개)
    @JsonIgnore
    private String newFileName; // 전송될 파일의 새 이름
    @JsonIgnore
    private String physicFilePath; // 첨부파일 경로
    @JsonIgnore
    private String physicFileName; // 첨부파일명(physicFilePath)
}

/**
 * [API 요청 샘플]
 * {
 *     "receiverAddr": "test@test.net"
 * }
 */