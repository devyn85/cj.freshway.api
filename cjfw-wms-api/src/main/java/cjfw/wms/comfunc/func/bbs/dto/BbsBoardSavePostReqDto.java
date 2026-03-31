package cjfw.wms.comfunc.func.bbs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 공지사항 저장 Request DTO
 */
@Data
public class BbsBoardSavePostReqDto {
    private Integer bbsSeq;

    @NotEmpty
    private String bbsTpCd;
    @NotEmpty
    private String bbsScpCd;
    @NotEmpty
    private String fromDt;
    @NotEmpty
    private String thruDt;
    @NotEmpty
    private String popYn;
    @NotEmpty
    private String topYn;
    @NotEmpty
    private String bbsTitle;
    @NotEmpty
    private String bbsNote;
    private Integer attchFileGrpNo;
    private String vwYn;
    
    @JsonIgnore
    private String userId;
}
