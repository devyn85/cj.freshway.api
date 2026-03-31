package cjfw.wms.ms.service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.*;
import cjfw.wms.ms.service.district.DistrictProcessorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsDeliveryDistrictGroupService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 *
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msDeliveryDistrictGroupService.";
	private static final String AFFECTED_YN = "affectedYn";
	private final CommonDao commonDao;
	private final DistrictProcessorFactory<MsCenterDlvDistrictGroupReqDto> districtGroupProcessorFactory;
	private final DistrictProcessorFactory<MsDeliveryDistrictGroupXPopReqDto.Pop> districtGroupPopProcessorFactory;

	public String saveDistrictGroup(List<MsCenterDlvDistrictGroupReqDto> dtoList) {
		return districtGroupProcessorFactory.saveMasterList(dtoList);
	}

	/**
	 * @description : 센터 배송 권역 그룹 리스트 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.12 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDlvDistrictGroupResDto> getDistrictGroupList(MsCenterDlvDistrictGroupReqDto dto){
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}


	/**
	 * @description : 센터 배송 권역 X POP 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsDeliveryDistrictGroupXPopResDto> getDistrictGroupXPop(MsCenterDlvDistrictGroupPopReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDistrictGroupXPop", dto);
	}

	public List<MsDeliveryDistrictGroupXPopResDto> getDlvDistrictgroupPopList(MsCenterDlvDistrictGroupReqDto dto) {
		List<MsDeliveryDistrictGroupXPopResDto> resultList = new ArrayList<>();
		List<MsDeliveryDistrictGroupXPopResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDlvDistrictgroupPopList", dto);
		Map<String, List<MsDeliveryDistrictGroupXPopResDto>> group = list.stream().collect(Collectors.groupingBy(MsDeliveryDistrictGroupXPopResDto::getDlvgroupId));
		group.forEach((key, value) -> {
			MsDeliveryDistrictGroupXPopResDto resDto = ModelMapperUtil.map(value.get(0), MsDeliveryDistrictGroupXPopResDto.class);
			List<Map<String, String>> subList = new ArrayList<>();
			for (MsDeliveryDistrictGroupXPopResDto v : value) {
				if (StringUtils.hasText(v.getPopNo())) {
					Map<String, String> m = new HashMap<>();
					m.put("popNo", v.getPopNo());
					m.put("popName", v.getPopName());
					m.put("fromDate", v.getPopFromdate());
					m.put("toDate", v.getPopTodate());
					subList.add(m);
				}
			}
			resDto.setGroupPopList(subList);
			resultList.add(resDto);
		});
		return resultList;
	}

	public String saveGroupXPopList(MsDeliveryDistrictGroupXPopReqDto dto) {
		for (MsDeliveryDistrictGroupXPopReqDto.Pop reqDto : dto.getPopList()) {
			reqDto.setDccode(dto.getDccode());
			reqDto.setDlvgroupId(dto.getDlvgroupId());
		}
		return districtGroupPopProcessorFactory.saveMasterList(dto.getPopList());
	}

	public Map<String, String> getTodateChildImpact(List<MsCenterDlvDistrictGroupReqDto> dtoList) {
		for (MsCenterDlvDistrictGroupReqDto dto : dtoList) {
			int districtCount = commonDao.selectOne(SERVICEID_PREFIX + "countGroupChildBeyondTodate", dto);
			if (districtCount > 0) {
				return Map.of(AFFECTED_YN, "Y",
						"message", "배송권역 관리 > 권역에서 해당 권역그룹을 사용하고 있습니다. \n 권역에서 해당 권역그룹을 삭제 후 진행해주세요.");
			}
			int popCount = commonDao.selectOne(SERVICEID_PREFIX + "countGroupPopChildBeyondTodate", dto);
			if (popCount > 0) {
				return Map.of(AFFECTED_YN, "Y",
						"message", "현재 해당 페이지의 대표 POP에서 사용중인 권역그룹이 존재합니다. \n 삭제후 진행해주세요.");
			}
		}
		return Map.of(AFFECTED_YN, "N");
	}

	public Map<String, String> getDeleteGroupPopImpact(List<MsCenterDlvDistrictGroupReqDto> dtoList) {
		Set<String> keySet = dtoList.stream()
				.map(MsCenterDlvDistrictGroupReqDto::getSerialkey).collect(Collectors.toSet());
		int count = commonDao.selectOne(SERVICEID_PREFIX + "countGroupPopByKeySet",
				MsDistrictValidationReqDto.builder().serialkeySet(keySet).build());
		if (count > 0) {
			return Map.of("hasPopYn", "Y");
		}
		return Map.of("hasPopYn", "N");
	}

	public Map<String, Object> getDeleteGroupXPopHjdongImpact(MsDeliveryDistrictGroupXPopReqDto dto) {
		int count = commonDao.selectOne(SERVICEID_PREFIX + "countGroupXPopHjdongByDlvgroupId", dto);
		List<String> allSerialkeys = dto.getPopList().stream()
				.map(MsDeliveryDistrictGroupXPopReqDto.Pop::getSerialkey)
				.toList();
		if (count > 0) {
			return Map.of("deletableSerialkeys", List.of(), "nonDeletableSerialkeys", allSerialkeys);
		}
		return Map.of("deletableSerialkeys", allSerialkeys, "nonDeletableSerialkeys", List.of());
	}

}
