package cjfw.wms.tm.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : TM 배차 Excel 셀 DTO (업로드/다운로드 통합)
a */
@Data
@Getter
@NoArgsConstructor
public class TmDispatchJsonCellDto {

    public TmDispatchJsonColumn column;
    public Object value;
    public String errorMessage;
    
    public String getErrorReason() {
        return errorMessage;
    }

}
