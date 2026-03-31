package cjfw.batch.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MsBatchDto {
    private String editDate;
    private String editWho;
    private Date batchDate;
}
