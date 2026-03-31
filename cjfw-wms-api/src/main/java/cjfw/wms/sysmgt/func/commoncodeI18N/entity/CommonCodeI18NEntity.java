package cjfw.wms.sysmgt.func.commoncodeI18N.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 공통코드 다국어 Entity(테이블: TB_CM_CD_DTL_I18N)
 */
@Data
public class CommonCodeI18NEntity {
    private String comGrpCd; // 공통코드 그룹
    private String comCd; // 공통코드
    private String languageCd; // 다국어 코드
    private String cdNm; // 코드 명
    private String useYn; // 사용여부
    private String regId;
    private LocalDateTime regDt;
    private String modId;
    private LocalDateTime modDt;
}
