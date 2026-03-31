package cjfw.wms.sysmgt.func.commoncode.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 그룹코드 Entity(테이블: TB_CM_CD_GRP)
 */
@Data
public class CommonCodeGrpEntity {

    private String comGrpCd;
    private String grpCdNm;
    private String grpCdDesc;
    private String useYn;
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
