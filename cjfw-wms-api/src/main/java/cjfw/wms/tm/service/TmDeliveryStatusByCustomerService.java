package cjfw.wms.tm.service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.common.util.MaskingUtil;
import cjfw.wms.tm.dto.TmDeliveryStatusByCustomerProcessArrivalReqDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByCustomerReqDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByCustomerResDto;
import cjfw.wms.tm.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.11.13
 * @description : 배송현황(거래처별) 조회 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.13 OhEunbeom      생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmDeliveryStatusByCustomerService {

    private transient static final String SERVICEID_PREFIX = "tmDeliveryStatusByCustomerService.";

    private final CommonDao commonDao;
    private final SqlSession sqlSession;

    /**
     * @description : 배송현황(거래처별) 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.13 OhEunbeom      생성 </pre>
     */
    public Page<TmDeliveryStatusByCustomerResDto> getDeliveryStatusByCustomer(TmDeliveryStatusByCustomerReqDto reqDto) {
        // 페이지네이션 유틸리티를 사용하여 Page 객체 생성
        Page<TmDeliveryStatusByCustomerResDto> page = PaginationUtil.createPage(
            reqDto.getPageNum(),
            reqDto.getListCount()
        );

        Page<TmDeliveryStatusByCustomerResDto> result = commonDao.selectPageList(SERVICEID_PREFIX+"getDeliveryStatusByCustomer", reqDto, page);

        if (result != null && result.getList() != null) {
            List<TmDeliveryStatusByCustomerResDto> list = result.getList();
            for (TmDeliveryStatusByCustomerResDto dto : list) {
                if (dto.getCustarrivaldt() == null || dto.getCustdeparturedt() == null) {
                    dto.setRegwhoNm(null);
                    dto.setRegwho(null);
                } else {
                    // P, M, W, NULL
                    String startType = dto.getDeliverystartRegType();
                    String endType = dto.getDeliveryendRegType();

                    // 1) regType 둘 다 P or M 인 경우 자동 보고로 판단, 수정 불가 => 출도착 처리 : 차량번호
                    if (isPm(startType) && isPm(endType)) {
                        setByCar(dto);
                        continue;
                    }

                    // 2) regType 둘 다 W or NULL 인 경우 수동 보고로 판단, 수정 가능 => 출도착 처리 : 유저명(유저ID)
                    if (isWOrNull(startType) && isWOrNull(endType)) {
                        setByMaskedUser(dto);
                        continue;
                    }

                    Date startEdit = dto.getDeliverystartEditdate();
                    Date endEdit = dto.getDeliveryendEditdate();
                    if (startEdit == null && endEdit == null) {
                        dto.setRegwhoNm(null);
                        dto.setRegwho(null);
                        continue;
                    }

                    // 3) regType이 혼합으로 P/M or W/NULL 조합인 경우 => editdate 마지막 수정 기준으로 제공
                    String regType;
                    if (startEdit == null) {
                        regType = endType;
                    } else if (endEdit == null) {
                        regType = startType;
                    } else {
                        regType = (startEdit.compareTo(endEdit) >= 0) ? startType : endType; // 같으면 start 우선
                    }

                    if (isPm(regType)) {
                        setByCar(dto);
                    } else {
                        setByMaskedUser(dto);
                    }
                }
            }
        }
        return result;
    }

    /**
     * P or M 여부
     */
    private boolean isPm(String regType) {
        return "P".equals(regType) || "M".equals(regType);
    }

    /**
     * W or null 여부
     */
    private boolean isWOrNull(String regType) {
        return regType == null || "W".equals(regType);
    }

    /**
     * carno 셋팅
     */
    private void setByCar(TmDeliveryStatusByCustomerResDto dto) {
        dto.setRegwhoNm(dto.getCarno());
        dto.setRegwho(null);
    }

    /**
     * 사용자명 마스킹처리
     */
    private void setByMaskedUser(TmDeliveryStatusByCustomerResDto dto) {
        // 방어로직
        if (dto.getRegwhoNm() == null || dto.getRegwho() == null) {
            dto.setRegwhoNm(null);
            dto.setRegwho(null);
            return;
        }

        String maskedUserName = maskUserName(dto.getRegwhoNm()); // 성+마지막글자만 남기고 마스킹 등
        String maskedUserId   = maskUserId(dto.getRegwho());     // 앞 4자리 제외 마스킹
        dto.setRegwhoNm(maskedUserName + "(" + maskedUserId + ")");
        dto.setRegwho(null);
    }

    /**
     * 사용자명 마스킹: 성과 이름의 마지막 자리 제외한 나머지 마스킹
     * 예: "홍길동" → "홍*동", "김철수" → "김**수"
     */
    private String maskUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return userName;
        }

        int length = userName.length();
        if (length <= 1) {
            return userName;
        }

        // 첫자리와 마지막자리만 남기고 중간 마스킹
        return MaskingUtil.maskingInner(userName);
    }

    /**
     * 사용자ID 마스킹: 앞 네자리 제외한 나머지 마스킹
     * 예: "hyeyeon822" → "hyey*****", "user123" → "user***"
     */
    private String maskUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return userId;
        }

        int length = userId.length();
        if (length <= 4) {
            // 4자리 이하는 전체 마스킹
            char[] masked = new char[length];
            java.util.Arrays.fill(masked, '*');
            return new String(masked);
        }

        // 앞 4자리는 그대로, 나머지는 마스킹
        String prefix = userId.substring(0, 4);
        int maskLength = length - 4;
        char[] masked = new char[maskLength];
        java.util.Arrays.fill(masked, '*');
        return prefix + new String(masked);
    }

    /**
     * @description : 배송현황(거래처별) 도착처리
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.13 OhEunbeom      생성 </pre>
     */
    public void updateArrival(TmDeliveryStatusByCustomerProcessArrivalReqDto reqDto) {
        log.info("배송현황(거래처별) 출도착처리 요청: {}", reqDto);

        // 도착시간이 있을 경우에만 도착처리 쿼리 실행
        if (reqDto.getArrivedtime() != null && !reqDto.getArrivedtime().trim().isEmpty()) {
            int arrivalUpdateCount = commonDao.update(SERVICEID_PREFIX + "processArrival", reqDto);
            log.info("배송현황(거래처별) 도착처리 결과: {} 건 업데이트", arrivalUpdateCount);
        }

        // 출발시간이 있을 경우에만 출발처리 쿼리 실행
        if (reqDto.getDeparturetime() != null && !reqDto.getDeparturetime().trim().isEmpty()) {
            int departureUpdateCount = commonDao.update(SERVICEID_PREFIX + "processDeparture", reqDto);
            log.info("배송현황(거래처별) 출발처리 결과: {} 건 업데이트", departureUpdateCount);
        }

        // TM_INPLAN, TM_INPLAN_SPLIT 테이블 업데이트 (출도착시간이 있을 경우)
        if ((reqDto.getArrivedtime() != null && !reqDto.getArrivedtime().trim().isEmpty())
            || (reqDto.getDeparturetime() != null && !reqDto.getDeparturetime().trim().isEmpty())) {
            int inplanUpdateCount = commonDao.update(SERVICEID_PREFIX + "updateInplanArrivalDeparture", reqDto);
            log.info("배송현황(거래처별) TM_INPLAN 업데이트 결과: {} 건 업데이트", inplanUpdateCount);

            int inplanSplitUpdateCount = commonDao.update(SERVICEID_PREFIX + "updateInplanSplitArrivalDeparture", reqDto);
            log.info("배송현황(거래처별) TM_INPLAN_SPLIT 업데이트 결과: {} 건 업데이트", inplanSplitUpdateCount);
        }
    }

}

