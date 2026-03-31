package cjfw.wms.comfunc.func.paging.dto;

import lombok.Data;

/**
 * 페이징 샘플 Response DTO
 */
@Data
public class SamplePagingGetResDto {

    private Integer exnNo;
    private String excptCnts;
    private String occrDy;
    private String clntAddr;
    private String svrAddr;
    private String callUri;
}
