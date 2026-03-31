package cjfw.wms.sysmgt.func.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : wisujang
 * @date : 2022.11.15
 * @description : 사용자 저장 Response DTO
 * @issues :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2022.11.15        wisujang       생성
 */
@Data
@Builder
public class UsersSaveResDto {
    @Schema(description = "인증코드 기능 샘플용", nullable = false, example = "111111")
    private String initPwd;
}
