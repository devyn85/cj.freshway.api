package cjfw.wms.tm.dto.result;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TM INPLAN DTO")
public class TmInplanDto {
    private Long serialkey;
    private String dccode;
    private String wavekey;
    private String deliverydt;
    private String tmDeliverytype;
    private String carno;
    private String defcarno;
    private String slipno;
    private String slipdt;
    private String sliptype;
    private String storerkey;
    private String custtype;
    private String custkey;
    private String docno;
    private String docdt;
    private String doctype;
    private String ordertype;
    private String orderqty;
    private String weight;
    private String cube;
    private LocalDateTime deliveryreqdt;
    private LocalDateTime deliveryplanot;
    private LocalDateTime dcdeparturedt;
    private LocalDateTime custarrivaldt;
    private LocalDateTime custdeparturedt;
    private LocalDateTime dcarrivaldt;
    private LocalDateTime receivedt;
    private String receiver;
    private String priority;
    private String defdeliverygroup;
    private String defpriority;
    private String deliverygroup;
    private String unloadstatus;
    private String loadstatus;
    private String otdstatus;
    private String status;
    private String delYn;
    private LocalDateTime adddate;
    private String addwho;
}


