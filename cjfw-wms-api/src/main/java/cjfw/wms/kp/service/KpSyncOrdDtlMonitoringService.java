package cjfw.wms.kp.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.kp.dto.KpSyncOrdDtlMonitoringAJResDto;
import cjfw.wms.kp.dto.KpSyncOrdDtlMonitoringDPResDto;
import cjfw.wms.kp.dto.KpSyncOrdDtlMonitoringReqDto;
import cjfw.wms.kp.dto.KpSyncOrdDtlMonitoringRTResDto;
import cjfw.wms.kp.dto.KpSyncOrdDtlMonitoringSTResDto;
import cjfw.wms.kp.dto.KpSyncOrdDtlMonitoringWDResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * KP 주문동기화 모니터링 상세(탭별) Service
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpSyncOrdDtlMonitoringService {

	private static final String SERVICEID_PREFIX = "kpSyncOrdDtlMonitoringService.";

	private final CommonDao commonDao;

	/** 프론트 dcCode "2410,2440,2400" → dccodeList 로 파싱 (KpSyncOrdMonitoring과 동일) */
	private static void fillDccodeList(KpSyncOrdDtlMonitoringReqDto reqDto) {
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

	/** 문서번호 또는 전표일자 기간 중 하나는 있어야 함. 둘 다 없으면 true(조회 생략) */
	private static boolean shouldSkipQuery(KpSyncOrdDtlMonitoringReqDto reqDto) {
		boolean hasDocno = reqDto.getDocno() != null && !reqDto.getDocno().isBlank();
		boolean hasSlipdtRange = reqDto.getSlipdtFrom() != null && !reqDto.getSlipdtFrom().isBlank()
				&& reqDto.getSlipdtTo() != null && !reqDto.getSlipdtTo().isBlank();
		return !hasDocno && !hasSlipdtRange;
	}

	public List<KpSyncOrdDtlMonitoringDPResDto> getDPInplanList(KpSyncOrdDtlMonitoringReqDto reqDto) {
		fillDccodeList(reqDto);
		if (shouldSkipQuery(reqDto)) {
			return Collections.emptyList();
		}
		return commonDao.selectList(SERVICEID_PREFIX + "getDPInplanList", reqDto);
	}

	public List<KpSyncOrdDtlMonitoringRTResDto> getRTInplanList(KpSyncOrdDtlMonitoringReqDto reqDto) {
		fillDccodeList(reqDto);
		if (shouldSkipQuery(reqDto)) {
			return Collections.emptyList();
		}
		return commonDao.selectList(SERVICEID_PREFIX + "getRTInplanList", reqDto);
	}

	public List<KpSyncOrdDtlMonitoringWDResDto> getWDInplanList(KpSyncOrdDtlMonitoringReqDto reqDto) {
		fillDccodeList(reqDto);
		if (shouldSkipQuery(reqDto)) {
			return Collections.emptyList();
		}
		return commonDao.selectList(SERVICEID_PREFIX + "getWDInplanList", reqDto);
	}

	public List<KpSyncOrdDtlMonitoringAJResDto> getAJInplanList(KpSyncOrdDtlMonitoringReqDto reqDto) {
		fillDccodeList(reqDto);
		if (shouldSkipQuery(reqDto)) {
			return Collections.emptyList();
		}
		return commonDao.selectList(SERVICEID_PREFIX + "getAJInplanList", reqDto);
	}

	public List<KpSyncOrdDtlMonitoringSTResDto> getSTInplanList(KpSyncOrdDtlMonitoringReqDto reqDto) {
		fillDccodeList(reqDto);
		if (shouldSkipQuery(reqDto)) {
			return Collections.emptyList();
		}
		return commonDao.selectList(SERVICEID_PREFIX + "getSTInplanList", reqDto);
	}
}
