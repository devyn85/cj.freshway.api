package cjfw.wms.tm.service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.*;
import cjfw.wms.tm.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 정종문 (loters@cj.net)
 * @date : 2025.08.20
 * @description : TM 주문 목록 조회 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.20 정종문 (loters@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmOrderListService {

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     * @return .sqlx returns the location
     */
    @Value("${tm.test-mode:false}")
    private boolean testMode;

    private transient static final String SERVICEID_PREFIX = "tmOrderListService.";
    private final CommonDao commonDao;
    private final UserContext userContext;
    
    private static final String TMAP_URL = ContextUtil.getProperty("spring.tmap.url");
    private static final String TMAP_APPKEY = ContextUtil.getProperty("spring.tmap.appkey");
    private static final String TMAP_GEO_PATH = "/geo/fullAddrGeo";

    /**
     * @description : 주문 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.20 정종문 (loters@cj.net) 생성
     * 2025.08.26 정종문 (loters@cj.net) 매개변수 구조 변경
     * 2025.08.26 정종문 (loters@cj.net) client, search 검증 로직 제거, 새로운 쿼리 구조 적용
     * 2025.08.26 정종문 (loters@cj.net) 페이지네이션 기능 제거
     * 2025.08.26 정종문 (loters@cj.net) 거래처 검색 기능 추가 (관리처, 실적지 LIKE 검색)
     * 2025.08.26 정종문 (loters@cj.net) 균형적 쿼리 개선 완료 (기본정보+비즈니스정보+실착지정보)
     * 2025.08.26 정종문 (loters@cj.net) 거래처 코드 단일 검색 및 물류센터 코드 검색 기능 추가 </pre>
     */
    public List<TmOrderListResDto> getOrderList(TmOrderListReqDto dto) {
        List<TmOrderListResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getOrderListByDeliveryType", dto);
        // 자동배차 대상 배송건과 주문목록의 배송건 불일치 확인용
        if(testMode) {
            TmOrderListSummay deliverySummary = commonDao.selectOne(SERVICEID_PREFIX + "getDeliveryCountSummary", dto);
            log.info("{}", deliverySummary);
        }
        return result;
    }
    
    /**
     * @description : 조회 조건에 따른 착지 좌표 업데이트 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.24 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public int updateBulkCustDlvInfoPoint(TmOrderListReqDto dto) {
		List<TmCustPointIsNullResDto> custList = commonDao.selectList(SERVICEID_PREFIX + "getCustByLocationNull", dto);
		
        HttpHeaders headers = new HttpHeaders();
        headers.set("appkey", TMAP_APPKEY);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();

        int failCount = 0;
        String requestUrl;
        for(TmCustPointIsNullResDto cust : custList) {
            requestUrl = "";
        	try {
	            if(Objects.equals(cust.getAddress(), "")) {
                    failCount++;
                    continue;
                }
                requestUrl = UriComponentsBuilder.fromUriString(TMAP_URL + TMAP_GEO_PATH)
                        .queryParam("version", "1")
                        .queryParam("page", "1")
                        .queryParam("coordType", "WGS84GEO")
                        .queryParam("fullAddr", cust.getAddress())
                        .queryParam("format", "json")
                        .build()
                        .toUriString();

                ResponseEntity<TmTmapFullAddrGeoResDto> response = restTemplate.exchange(
                        requestUrl,
                        HttpMethod.GET,
                        entity,
                        TmTmapFullAddrGeoResDto.class
                );

                if(response.getBody() == null) {
                    failCount++;
                    continue;
                }

                if (response.getBody().getCoordinateInfo() != null && response.getBody().getCoordinateInfo().getCoordinate().length > 0) {
                    TmTmapFullAddrGeoResDto.Coordinate coordData = response.getBody().getCoordinateInfo().getCoordinate()[0];
                    String lat = coordData.getLat() == null || coordData.getLat().isBlank() ? coordData.getNewLat() : coordData.getLat();
                    String lon = coordData.getLon() == null || coordData.getLon().isBlank() ? coordData.getNewLon() : coordData.getLon();
                    String address = this.extractAddress(coordData);
                    String zipcode = coordData.getZipcode();

                    // 기존 좌표가 존재하면 기존 좌표 셋팅
                    if ((cust.getLatitude() != null && !cust.getLatitude().isBlank()) && (cust.getLongitude() != null && !cust.getLongitude().isBlank())) {
                        lat = cust.getLatitude();
                        lon = cust.getLongitude();
                    }

                    if ((lat != null && !lat.isBlank()) && (lon != null && !lon.isBlank())) {
                        TmCustDlvInfoPointReqDto tmCustDlvInfoPointReqDto = ModelMapperUtil.map(cust, userContext, TmCustDlvInfoPointReqDto.class);
                        tmCustDlvInfoPointReqDto.setCustType(cust.getCustType());
                        tmCustDlvInfoPointReqDto.setDlvcustkey(cust.getDlvcustkey());
                        tmCustDlvInfoPointReqDto.setLatitude(lat);
                        tmCustDlvInfoPointReqDto.setLongitude(lon);
                        tmCustDlvInfoPointReqDto.setAddress(address);
                        tmCustDlvInfoPointReqDto.setZipcode(zipcode);
                        this.updateLocationByCustNewTx(tmCustDlvInfoPointReqDto);
                    }
                }
        	}catch(Exception e) {
                failCount++;
        		log.info("Failed to get coordinates for cust: {}, {}, {}", requestUrl, cust, e.toString());
        	}
        }

        return failCount;
    }

    private void updateLocationByCustNewTx(TmCustDlvInfoPointReqDto dto) {
        commonDao.update(SERVICEID_PREFIX + "updateLocationByCust", dto);
    }

    private String extractAddress(TmTmapFullAddrGeoResDto.Coordinate coordinate) {
        if (coordinate == null) return "";

        List<String> addressParts = new ArrayList<>();

        // 1. 새주소(도로명) 유무 확인
        boolean hasNewAddr = isPresent(coordinate.getNewMatchFlag());
        // 2. 구주소(지번) 유무 확인
        boolean hasOldAddr = isPresent(coordinate.getMatchFlag());

        // 둘 다 없다면 빈 값 반환
        if (!hasNewAddr && !hasOldAddr) return "";

        // 공통 필수 요소 (시/도, 군/구)
        addIfPresent(addressParts, coordinate.getCity_do());
        addIfPresent(addressParts, coordinate.getGu_gun());

        // (우선순위: 법정동 > 읍면동 > 행정동)
        if (isPresent(coordinate.getLegalDong())) {
            addressParts.add(coordinate.getLegalDong());
        } else if (isPresent(coordinate.getEup_myun())) {
            addressParts.add(coordinate.getEup_myun());
        } else if (isPresent(coordinate.getAdminDong())) {
            addressParts.add(coordinate.getAdminDong());
        }

        // 공통 요소 (리, 지번)
        addIfPresent(addressParts, coordinate.getRi());
        addIfPresent(addressParts, coordinate.getBunji());

        // 3. 분기 처리: 새주소 전용 vs 구주소 전용 필드
        if (hasNewAddr) {
            // 새주소(도로명 주소) 전용 필드
            addIfPresent(addressParts, coordinate.getNewRoadName());
            addIfPresent(addressParts, coordinate.getNewBuildingIndex());
            addIfPresent(addressParts, coordinate.getNewBuildingName());
            addIfPresent(addressParts, coordinate.getNewBuildingDong());
        } else {
            // 구주소(지번 주소) 전용 필드
            addIfPresent(addressParts, coordinate.getBuildingName());
            addIfPresent(addressParts, coordinate.getBuildingDong());
        }

        return String.join(" ", addressParts);
    }

    private boolean isPresent(String s) {
        return s != null && !s.isEmpty();
    }

    private void addIfPresent(List<String> list, String value) {
        if (isPresent(value)) {
            list.add(value);
        }
    }
    
    /**
     * @description : 착지 좌표 수동 업데이트 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.24 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public String updateCustDlvInfoPoint(TmCustDlvInfoPointReqDto dto) {
    	commonDao.update(SERVICEID_PREFIX + "updateLocationByCustDlv", dto);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 배송 클레임 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.20 Auto Generated 생성 </pre>
     */
    public Page<TmClaimListResDto> getClaimList(TmClaimListReqDto dto) {
        // 페이지네이션 유틸리티를 사용하여 Page 객체 생성
        Page<TmDispatchListByPopResDto> page = PaginationUtil.createPage(
                dto.getPageNum(),
                dto.getListCount()
        );

        Page<TmClaimListResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getClaimList", dto, page);

        return result;
    }
}
