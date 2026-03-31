package cjfw.wms.tm.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : TM 배차 Excel 행 DTO (업로드/다운로드 통합)
 */
@Getter
@Data
@NoArgsConstructor
public class TmDispatchJsonRowDto {
    public int rowNumber;
    public String dcCode;
    public String deliveryDate;
    public ArrayList<TmDispatchJsonCellDto> cells;
    public ArrayList<TmDispatchJsonCellDto> invalidCells;
    
}
