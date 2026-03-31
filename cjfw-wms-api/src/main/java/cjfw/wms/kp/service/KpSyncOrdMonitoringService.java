package cjfw.wms.kp.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.kp.dto.KpSyncOrdMonitoringDetailReqDto;
import cjfw.wms.kp.dto.KpSyncOrdMonitoringDetailResDto;
import cjfw.wms.kp.dto.KpSyncOrdMonitoringMasterReqDto;
import cjfw.wms.kp.dto.KpSyncOrdMonitoringMasterResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * KP 주문동기화 모니터링 Service
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpSyncOrdMonitoringService {

	private static final String SERVICEID_PREFIX = "kpSyncOrdMonitoringService.";

	private final CommonDao commonDao;

	/** 프론트 dcCode "2410,2440,2400" → dccodeList 로 파싱 */
	private static void fillDccodeList(KpSyncOrdMonitoringMasterReqDto reqDto) {
		if (reqDto.getDcCode() == null || reqDto.getDcCode().isBlank()) {
			reqDto.setDccodeList(Collections.emptyList());
			return;
		}
		List<String> list = Arrays.stream(reqDto.getDcCode().split(","))
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.collect(Collectors.toList());
		reqDto.setDccodeList(list);
	}

	/** 프론트 dccode 단일값(또는 "2410,2440" 콤마구분) → dccodeList 로 파싱 */
	private static void fillDccodeList(KpSyncOrdMonitoringDetailReqDto reqDto) {
		if (reqDto.getDccode() == null || reqDto.getDccode().isBlank()) {
			reqDto.setDccodeList(Collections.emptyList());
			return;
		}
		List<String> list = Arrays.stream(reqDto.getDccode().split(","))
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.collect(Collectors.toList());
		reqDto.setDccodeList(list);
	}

	/** 주문완료여부 정규화: "1"/"true"(대소문자무관) → "1", 그 외 → "0" (프론트 직렬화 차이 대응) */
	private static void normalizeOrderCompleteYn(KpSyncOrdMonitoringMasterReqDto reqDto) {
		String v = reqDto.getOrderCompleteYn();
		if (v != null && ("1".equals(v.trim()) || "true".equalsIgnoreCase(v.trim()))) {
			reqDto.setOrderCompleteYn("1");
		} else {
			reqDto.setOrderCompleteYn("0");
		}
	}

	public List<KpSyncOrdMonitoringMasterResDto> getMasterList(KpSyncOrdMonitoringMasterReqDto reqDto) {
		fillDccodeList(reqDto);
		normalizeOrderCompleteYn(reqDto);
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
	}

	public Page<KpSyncOrdMonitoringDetailResDto> getDetailList(KpSyncOrdMonitoringDetailReqDto reqDto, Page<?> page) {
		fillDccodeList(reqDto);
		return commonDao.selectPageList(SERVICEID_PREFIX + "getDetailList", reqDto, page);
	}
}
