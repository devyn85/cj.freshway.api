package cjfw.wms.ms.service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.*;
import cjfw.wms.ms.service.district.DistrictProcessorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.02
 * @description : 기준정보 > 배송 권역관리 > 권역 Service 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.02 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsDeliveryDistrictService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 */
	private static final String SERVICEID_PREFIX = "msDeliveryDistrictService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final DistrictProcessorFactory<MsCenterDlvDistrictReqDto> dlvDistrictProcessorFactory;
	private final DistrictProcessorFactory<MsCenterDlvDistrictHjdongReqDto.Hjdong> dlvDistrictHjdongProcessorFactory;

	/**
	 * @description : 배송 권역 목록 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDlvDistrictResDto> getMasterList(MsCenterDlvDistrictReqDto dto){
		if(dto.getDccode() != null) {
			dto.setGMultiDccodeList(dto.getDccode().split(","));
		}
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	public String saveMasterList(List<MsCenterDlvDistrictReqDto> dtoList){
		return dlvDistrictProcessorFactory.saveMasterList(dtoList);
	}

	/**
	 * @description : 센터 배송 권역 / 그룹 폴리곤 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsDlvDistrictPolygonResDto> getDlvDistrictPolygon(MsDlvDistrictPolygonReqDto dto) {
		dto.setGMultiDccodeList(dto.getDccode().split(","));
		if((dto.getDlvDistrictType()).equals("DISTRICT")) {
			return commonDao.selectList(SERVICEID_PREFIX + "getDlvCenterDistrictPolygonList", dto);
		} else if((dto.getDlvDistrictType()).equals("GROUP")) {
			return commonDao.selectList(SERVICEID_PREFIX + "getDlvCenterDistrictGroupPolygonList", dto);
		} else {
			throw new UserHandleException("MSG.COM.VAL.121");
		}
	}

	public String saveHjdongList(MsCenterDlvDistrictHjdongReqDto dto) {
		for (MsCenterDlvDistrictHjdongReqDto.Hjdong hjdong : dto.getHjdongList()) {
			hjdong.setDccode(dto.getDccode());
			hjdong.setDlvdistrictId(dto.getDlvdistrictId());
			hjdong.setDlvgroupId(dto.getDlvgroupId() == null ? " " : dto.getDlvgroupId());
		}
		return dlvDistrictHjdongProcessorFactory.saveMasterList(dto.getHjdongList());
	}


	public List<MsCenterDlvDistrictHjdongResDto> getSaveHjdongListValidation(MsCenterDlvDistrictHjdongReqDto dto){
		List<MsCenterDlvDistrictHjdongResDto> resultList = new ArrayList<>();
		for(MsCenterDlvDistrictHjdongReqDto.Hjdong hjdongDto : dto.getHjdongList()) {
			MsCenterDlvDistrictHjdongResDto resDto = ModelMapperUtil.map(hjdongDto, userContext, MsCenterDlvDistrictHjdongResDto.class);
			resDto.setDccode(dto.getDccode());
			if((CanalFrameConstants.INSERT).equals(hjdongDto.getRowStatus())) {
				List<MsCenterDlvDistrictHjdongResDto> validateDataList = commonDao.selectList(SERVICEID_PREFIX + "getSaveHjdongValidate", resDto);
				if(validateDataList != null) {
					for(MsCenterDlvDistrictHjdongResDto validateRes : validateDataList) {
						resDto.addMessage(MessageUtil.getMessage("MSG.COM.VAL.067", new String[]{"권역 " + validateRes.getDlvdistrictId()}));
					}
				}
			}
			resultList.add(resDto);
		}
		return resultList;
	}
	
	/**
	 * @description : 배송 권역 행정동 목록 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDlvDistrictHjdongResDto> getHjdongList(MsCenterDlvDistrictReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDlvDistrictHjdongList", dto);
	}
	
	/**
	 * @description : 센터 배송 권역 사용 현황 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDlvDistrictHjdongResDto> getCenterDistrictUsageList(MsCenterDlvDistrictGroupReqDto dto){
		return commonDao.selectList(SERVICEID_PREFIX + "getCenterDistrictUsageList", dto);
	}
	
	/**
	 * @description : 센터 권역 신규 행정동 목록
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.20 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDistrictNewHjdongResDto> getNewHjdongList(MsCenterDlvDistrictReqDto dto){
		return commonDao.selectList(SERVICEID_PREFIX + "getNewHjdongList", dto);
	}

	public List<MsCenterHjdongIntersectionResDto> getCenterHjdongIntersectionList(MsCenterDlvDistrictReqDto dto) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");

		// 1. 센터행정동 ∩ 배송권역 교집합 기간 조회
		List<MsCenterHjdongIntersectionResDto> baseList = commonDao.selectList(SERVICEID_PREFIX + "getCenterHjdongIntersectionList", dto);

		// 2. 같은 센터 내 다른 배송권역에 이미 지정된 행정동 기간 조회
		List<Map<String, Object>> assignedList = commonDao.selectList(SERVICEID_PREFIX + "getAssignedDlvHjdongPeriods", dto);

		if (assignedList == null || assignedList.isEmpty()) {
			return baseList;
		}

		// 3. 이미 지정된 기간을 행정동별로 그룹핑
		Map<String, List<Map<String, Object>>> assignedByHjdong = assignedList.stream()
				.collect(Collectors.groupingBy(m -> (String) m.get("HJDONG_CD")));

		// 4. 각 행정동의 사용 가능 기간에서 이미 지정된 기간 차감
		List<MsCenterHjdongIntersectionResDto> result = new ArrayList<>();
		for (MsCenterHjdongIntersectionResDto base : baseList) {
			List<Map<String, Object>> exclusions = assignedByHjdong.get(base.getHjdongCd());
			if (exclusions == null || exclusions.isEmpty()) {
				result.add(base);
				continue;
			}

			// 기간 빼기: base [from, to] 에서 exclusion 구간들을 차감
			List<LocalDate[]> remaining = subtractDateRanges(
					LocalDate.parse(base.getFromDate(), fmt),
					LocalDate.parse(base.getToDate(), fmt),
					exclusions, fmt);

			for (LocalDate[] range : remaining) {
				MsCenterHjdongIntersectionResDto split = new MsCenterHjdongIntersectionResDto();
				split.setHjdongCd(base.getHjdongCd());
				split.setHjdongNm(base.getHjdongNm());
				split.setCtpKorNm(base.getCtpKorNm());
				split.setSigKorNm(base.getSigKorNm());
				split.setFromDate(range[0].format(fmt));
				split.setToDate(range[1].format(fmt));
				result.add(split);
			}
		}
		return result;
	}

	private List<LocalDate[]> subtractDateRanges(LocalDate from, LocalDate to,
			List<Map<String, Object>> exclusions, DateTimeFormatter fmt) {
		List<LocalDate[]> result = new ArrayList<>();
		LocalDate current = from;

		// exclusions는 이미 FROMDATE 순 정렬됨 (SQL ORDER BY)
		for (Map<String, Object> ex : exclusions) {
			LocalDate exFrom = LocalDate.parse((String) ex.get("FROMDATE"), fmt);
			LocalDate exTo = LocalDate.parse((String) ex.get("TODATE"), fmt);

			// base 범위와 겹치지 않으면 skip
			if (exTo.isBefore(current) || exFrom.isAfter(to)) {
				continue;
			}

			// exclusion 시작 전 구간이 있으면 추가
			if (exFrom.isAfter(current)) {
				result.add(new LocalDate[]{current, exFrom.minusDays(1)});
			}

			// current를 exclusion 끝 다음날로 이동
			if (exTo.plusDays(1).isAfter(current)) {
				current = exTo.plusDays(1);
			}
		}

		// 마지막 남은 구간
		if (!current.isAfter(to)) {
			result.add(new LocalDate[]{current, to});
		}
		return result;
	}

	public Map<String, String> getTodateChildImpact(List<MsCenterDlvDistrictReqDto> dtoList) {
		for (MsCenterDlvDistrictReqDto dto : dtoList) {
			int count = commonDao.selectOne(SERVICEID_PREFIX + "countDistrictChildBeyondTodate", dto);
			if (count > 0) {
				return Map.of("affectedYn", "Y");
			}
		}
		return Map.of("affectedYn", "N");
	}

}
