package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsExDCStatusDetailReqDto;
import cjfw.wms.ms.dto.MsExDCStatusDetailResDto;
import cjfw.wms.ms.dto.MsExDCStatusListReqDto;
import cjfw.wms.ms.dto.MsExDCStatusListResDto;
import cjfw.wms.ms.entity.MsExDCSpecRateEntity;
import cjfw.wms.ms.entity.MsPlantXSLFileEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.29
 * @description : 외부창고현황 Controller Class
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.29 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsExDCStatusService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msExDCStatusService.";

	private final CommonDao commonDao;

	private final UserContext userContext;

	/**
	 * @description : 외부창고현황 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.17 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	public List<MsExDCStatusListResDto> getExDCStatusList(MsExDCStatusListReqDto msExDCStatusReqDto) {
		List<MsExDCStatusListResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getExDCStatusList", msExDCStatusReqDto);
		return result;
	}

	/**
	 * @description : 외부창고현황 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public MsExDCStatusDetailResDto getExDCStatusDtl(MsExDCStatusDetailReqDto msExDCStatusReqDto) {
		MsExDCStatusDetailResDto resDto = new MsExDCStatusDetailResDto();
		resDto = commonDao.selectOne(SERVICEID_PREFIX + "getExDCStatusDtl", msExDCStatusReqDto);
		msExDCStatusReqDto.setOrganize(msExDCStatusReqDto.getPlant()+"-"+msExDCStatusReqDto.getStorageloc());
		// 연도 추출 20251219 보여지지 않기로 함
        /*
        // 계약기간 연도 범위 조회
        List<Map<String, Object>> contractList =
            commonDao.selectList(SERVICEID_PREFIX + "getContractDateRangeList", msExDCStatusReqDto);

        Set<Integer> yearSet = new TreeSet<>(Collections.reverseOrder());
        for (Map<String, Object> row : contractList) {
            String fromDateStr = (String) row.get("FROMDATE");
            String toDateStr = (String) row.get("TODATE");

            int fromYear = Integer.parseInt(fromDateStr.substring(0, 4));
            int toYear = Integer.parseInt(toDateStr.substring(0, 4));

            for (int year = fromYear; year <= toYear; year++) {
                yearSet.add(year);
            }
        }

        List<Map<String, Object>> yearOptionList = new ArrayList<>();
        for (Integer year : yearSet) {
            Map<String, Object> map = new HashMap<>();
            map.put("contractYearName", String.valueOf(year));
            map.put("contractYearCode", String.valueOf(year));
            yearOptionList.add(map);
        }
        resDto.setContractyears(yearOptionList);
         */

//        //첨부파일 조회
//        List<MsPlantXSLFileEntity> fileList = commonDao.selectList(SERVICEID_PREFIX + "getMsPlantxslFileList", msExDCStatusReqDto);
//        if(!ObjectUtils.isEmpty(fileList)) {
//        	resDto.setFileList(fileList);
//        }
        List<MsExDCSpecRateEntity> skuSpecRateList = commonDao.selectList(SERVICEID_PREFIX + "getExDCStatusSkuRateList", msExDCStatusReqDto);
        resDto.setSkuList(skuSpecRateList);
		return resDto;
	}

	public List<Map<String,String>> getPlantList(){
		return commonDao.selectList(SERVICEID_PREFIX + "getDataSelectPlant");
	}

}
