package cjfw.wms.comfunc.func.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : wisujang
 * @date : 2023.07.04
 * @description : REST API 샘플 Posts PUT메소드 요청,응답 샘플 DTO
 * @issues :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.07.04        wisujang       생성
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostsPutDto {
    private Integer id;
    private String title;
    private String body;
    private Integer userId;
}
