package cjfw.wms.comfunc.func.paging.dto;

import lombok.Data;

/**
 * 페이징 샘플 Request DTO
 */
@Data
public class SamplePagingGetReqDto {
    private String fromDt;
    private String thruDt;
}
