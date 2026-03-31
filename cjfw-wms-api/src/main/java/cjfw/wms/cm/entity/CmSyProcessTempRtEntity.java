package cjfw.wms.cm.entity;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 *
 * @author : KimDongHyeon (jungyun8667@cj.net)
 * @date : 2025.10.01
 * @description : 시스템작업 임시 RT Entity
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.01  KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
public class CmSyProcessTempRtEntity extends CommonProcedureDto {
    @Schema(name = "serialkey")
    private String serialkey;

    @Schema(name = "processtype")
    private String processtype;

    @Schema(name = "processcreator")
    private String processcreator;

    @Schema(name = "spid")
    private String spid;

    @Schema(name = "processflag")
    private String processflag;

    @Schema(name = "batch_no")
    private String batchNo;

    @Schema(name = "list_no")
    private String listNo;

    @Schema(name = "work_no")
    private String workNo;

    @Schema(name = "worker")
    private String worker;

    @Schema(name = "docdt")
    private String docdt;

    @Schema(name = "docno")
    private String docno;

    @Schema(name = "docline")
    private String docline;

    @Schema(name = "ordertype")
    private String ordertype;

    @Schema(name = "slipdt")
    private String slipdt;

    @Schema(name = "slipno")
    private String slipno;

    @Schema(name = "slipline")
    private String slipline;

    @Schema(name = "sliptype")
    private String sliptype;

    @Schema(name = "iotype")
    private String iotype;

    @Schema(name = "dccode")
    private String dccode;

    @Schema(name = "storerkey")
    private String storerkey;

    @Schema(name = "organize")
    private String organize;

    @Schema(name = "area")
    private String area;

    @Schema(name = "sku")
    private String sku;

    @Schema(name = "zone")
    private String zone;

    @Schema(name = "loc")
    private String loc;

    @Schema(name = "lot")
    private String lot;

    @Schema(name = "stockid")
    private String stockid;

    @Schema(name = "stockgrade")
    private String stockgrade;

    @Schema(name = "stocktype")
    private String stocktype;

    @Schema(name = "orderqty")
    private String orderqty;

    @Schema(name = "openqty")
    private String openqty;

    @Schema(name = "processqty")
    private String processqty;

    @Schema(name = "workqty")
    private String workqty;

    @Schema(name = "inspectqty")
    private String inspectqty;

    @Schema(name = "confirmqty")
    private String confirmqty;

    @Schema(name = "invoiceqty")
    private String invoiceqty;

    @Schema(name = "uom")
    private String uom;

    @Schema(name = "bunja")
    private String bunja;

    @Schema(name = "bunmo")
    private String bunmo;

    @Schema(name = "etcqty1")
    private String etcqty1;

    @Schema(name = "etcqty2")
    private String etcqty2;

    @Schema(name = "mixboxkey")
    private String mixboxkey;

    @Schema(name = "taskkey")
    private String taskkey;

    @Schema(name = "wavekey")
    private String wavekey;

    @Schema(name = "srcserialkey1")
    private String srcserialkey1;

    @Schema(name = "srcserialkey2")
    private String srcserialkey2;

    @Schema(name = "invoiceno")
    private String invoiceno;

    @Schema(name = "deliverygroup")
    private String deliverygroup;

    @Schema(name = "custkey")
    private String custkey;

    @Schema(name = "status")
    private String status;

    @Schema(name = "del_yn")
    private String delYn;

    @Schema(name = "trafficcop")
    private String trafficcop;

    @Schema(name = "archivecop")
    private String archivecop;

    @Schema(name = "processmsg")
    private String processmsg;

    @Schema(name = "adddate")
    private String adddate;

    @Schema(name = "editdate")
    private String editdate;

    @Schema(name = "addwho")
    private String addwho;

    @Schema(name = "editwho")
    private String editwho;
}
