package cjfw.wms.tm.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : TM 배차 Excel 전체 DTO (업로드/다운로드 통합)
 */
@Slf4j
@Getter
@Data
@NoArgsConstructor
public class TmDispatchJsonDto {

	public String dccode;

	public String deliveryDate;

	public int totalCount;
	public ArrayList<TmDispatchJsonRowDto> rows;
	public List<TmDispatchJsonRowDto> invalidRows;

	/** 센터-실착지 직선거리(km) 맵 (key: truthCustKey) */
	public Map<String, Double> linearDistanceKmByTruthCustKey;

	public void getPrintOutData() {

		for (int i = 0; i < rows.size(); i++) {
			TmDispatchJsonRowDto tmpRow = this.rows.get(i);

			for (int j = 0; j < tmpRow.getCells().size(); j++) {
				TmDispatchJsonCellDto cellTmp = tmpRow.getCells().get(j);

				log.debug("{}::{}", cellTmp.getColumn(), cellTmp.getValue());

			}

		}
	}


	public boolean hasInvalidRows() {
		return !invalidRows.isEmpty();
	}

}
