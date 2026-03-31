package cjfw.batch.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

/**
 * Copyright 2024. CJ Freshway Co. all rights reserved.
 * @author : SYSTEM
 * @date : 2024.12.17
 * @description : 행정동 정보 DTO (배치용)
 * @issues :
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2024.12.17 SYSTEM                생성
 * </pre>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "행정동 정보 DTO")
public class MsHjdongDto {

    @Schema(description = "행정동코드", example = "1168010100")
    private String hjdongCd;

    @Schema(description = "시도명", example = "서울특별시")
    private String ctpKorNm;

    @Schema(description = "시군구명", example = "강남구")
    private String sigKorNm;

    @Schema(description = "행정동명", example = "역삼동")
    private String hjdongNm;

    @Schema(description = "이동사유코드", example = "31")
    private String mvmnResCd;

    @Schema(description = "최초등록시간", accessMode = Schema.AccessMode.READ_ONLY)
    private Date adddate;

    @Schema(description = "최종변경시간", accessMode = Schema.AccessMode.READ_ONLY)
    private Date editdate;

    @Schema(description = "최초등록자", example = "__BATCH")
    private String addwho;

    @Schema(description = "최종변경자", example = "__BATCH")
    private String editwho;

    /**
     * 전체 주소 반환 (시도 + 시군구 + 행정동)
     * @return 전체 주소
     */
    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        if (ctpKorNm != null && !ctpKorNm.isEmpty()) {
            sb.append(ctpKorNm);
        }
        if (sigKorNm != null && !sigKorNm.isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(sigKorNm);
        }
        if (hjdongNm != null && !hjdongNm.isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(hjdongNm);
        }
        return sb.toString();
    }
}
