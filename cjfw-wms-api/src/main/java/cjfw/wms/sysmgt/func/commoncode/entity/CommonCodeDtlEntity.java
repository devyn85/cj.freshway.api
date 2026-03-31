package cjfw.wms.sysmgt.func.commoncode.entity;

import java.time.LocalDateTime;

import lombok.Data;
/**
 * 공통코드 Entity(테이블: TB_CM_CD_DTL)
 */
@Data
public class CommonCodeDtlEntity {

    private String comGrpCd;
    private String comCd;
    private String cdNm;
    private String cdDesc;
    private String useYn;
    private Integer sortNo;
    private String rsvStr1Val;
    private String rsvStr2Val;
    private String rsvStr3Val;
    private String rsvStr4Val;
    private String rsvStr5Val;
    private String rsvStr6Val;
    private String rsvStr7Val;
    private String rsvStr8Val;
    private String regId;
    private LocalDateTime regDt;
    private String modId;
    private LocalDateTime modDt;
}
