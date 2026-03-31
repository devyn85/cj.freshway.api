package cjfw.wms.kp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.kp.dto.KpSyncStockMonitoringReqDto;
import cjfw.wms.kp.dto.KpSyncStockMonitoringResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * KP 재고동기화 모니터링 Service
 * 탭: TOBE(신규) EXISTS_YN='1', ASISTOBE(차이) EXISTS_YN='2'
 * sku 1000건 초과 시 AOP(ParamArrayManagerAop)가 skuMulti로 999건씩 OR 조건 변환
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpSyncStockMonitoringService {

	private static final String SERVICEID_PREFIX = "kpSyncStockMonitoringService.";

	private final CommonDao commonDao;

	/**
	 * 재고동기화 모니터링 목록 조회
	 * existsYn 이 '1' 또는 '2' 이면 해당 탭만 필터링하여 반환
	 */
	public List<KpSyncStockMonitoringResDto> getStockMonitoringList(KpSyncStockMonitoringReqDto reqDto) {
		List<KpSyncStockMonitoringResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getStockMonitoringList", reqDto);
		if (reqDto.getExistsYn() != null && !reqDto.getExistsYn().isBlank()) {
			return list.stream()
					.filter(dto -> reqDto.getExistsYn().equals(dto.getExistsYn()))
					.collect(Collectors.toList());
		}
		return list;
	}
}
