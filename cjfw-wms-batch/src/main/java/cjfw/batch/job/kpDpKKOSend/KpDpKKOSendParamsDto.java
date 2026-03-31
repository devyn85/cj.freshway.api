package cjfw.batch.job.kpDpKKOSend;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@Data
@Schema(description = "")
public class KpDpKKOSendParamsDto {

    /*****************************************************************
     * SMS SEND 컬럼.
     *****************************************************************/
    @Schema(description = "STORERKEY", example = "", nullable = false)
    private String STORERKEY;

    @Schema(description = "DCCODE", example = "", nullable = false)
    private String DCCODE;

    @Schema(description = "DCNAME", example = "", nullable = false)
    private String DCNAME;

    @Schema(description = "DCPHONE", example = "", nullable = false)
    private String DCPHONE;

    @Schema(description = "SLIPDT", example = "", nullable = false)
    private String SLIPDT;

    @Schema(description = "CUSTKEY", example = "", nullable = false)
    private String CUSTKEY;

    @Schema(description = "CUSTNAME", example = "", nullable = false)
    private String CUSTNAME;

    @Schema(description = "CHANNEL", example = "", nullable = false)
    private String CHANNEL;

    @Schema(description = "DPUNLOADTIME", example = "", nullable = false)
    private String DPUNLOADTIME;

    @Schema(description = "PERSONTYPE", example = "", nullable = false)
    private String PERSONTYPE;

    @Schema(description = "EMPPHONE1", example = "", nullable = false)
    private String EMPPHONE1;

    @Schema(description = "EMPNAME1", example = "", nullable = false)
    private String EMPNAME1;

    @Schema(description = "SENDTITLE", example = "", nullable = false)
    private String SENDTITLE;

    @Schema(description = "SENDPHONE", example = "", nullable = false)
    private String SENDPHONE;

    @Schema(description = "TEMPLATECODE", example = "", nullable = false)
    private String TEMPLATECODE;

    @Schema(description = "TEMPLATETITLE", example = "", nullable = false)
    private String TEMPLATETITLE;

    @Schema(description = "PROFILEKEY", example = "", nullable = false)
    private String PROFILEKEY;

    @Schema(description = "RECEIVEPHONE", example = "", nullable = false)
    private String RECEIVEPHONE;

    @Schema(description = "RECEIVENAME", example = "", nullable = false)
    private String RECEIVENAME;

    @Schema(description = "SENDMESSAGE", example = "", nullable = false)
    private String SENDMESSAGE;

    @Schema(description = "SKU", example = "", nullable = false)
    private String SKU;

    @Schema(description = "SKUNAME", example = "", nullable = false)
    private String SKUNAME;

    @Schema(description = "NONINSPECTQTY", example = "", nullable = false)
    private String NONINSPECTQTY;

    @Schema(description = "SOMDNAME", example = "", nullable = false)
    private String SOMDNAME;

    @Schema(description = "SOMDCODE", example = "", nullable = false)
    private String SOMDCODE;

    @Schema(description = "MULTIMDCODE", example = "", nullable = false)
    private List<String> MULTIMDCODE;

    @Schema(description = "SHORTAGEQTY", example = "", nullable = false)
    private int SHORTAGEQTY;

    @Schema(description = "UOM", example = "", nullable = false)
    private String UOM;
}
