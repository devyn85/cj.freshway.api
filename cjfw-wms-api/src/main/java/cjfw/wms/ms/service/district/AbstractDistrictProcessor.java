package cjfw.wms.ms.service.district;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.ms.dto.MsDistrictValidationReqDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractDistrictProcessor<T extends CommonDto> {
    public static final String BATCH_KEY = "BATCH_KEY";
    public static final String MS_CENTER_DISTRICT_NS = "msCenterDistrictService.";
    public static final String MS_CENTER_DISTRICT_DC_ORDGRP_NS = "msCenterDistrictDcOrdGrpService.";
    public static final String MS_CENTER_DISTRICT_ORDGRP_NS = "msCenterDistrictOrdGrpService.";
    public static final String MS_DELIVERY_DISTRICT_NS = "msDeliveryDistrictService.";
    public static final String MS_DELIVERY_DISTRICT_GROUP_NS = "msDeliveryDistrictGroupService.";
    public static final String MS_DELIVERY_DISTRICT_REPPOP_NS = "msDeliveryDistrictRepPopService.";
    public static final String MS_CENTER_DISTRICT_HISTORY_NS = "msCenterDistrictHistoryService.";
    public static final String MS_DELIVERY_DISTRICT_HISTORY_NS = "msDeliveryDistrictHistoryService.";
    public static final String MS_DISTRICT_ORDER_GROUP_MERGE_NS = "msDistrictOrderGroupMergeService.";
    protected static final List<String> IF_ID_DATA = List.of("SCM0880", "SCM0900", "SCM0950");

    @Getter
    private final Class<T> type;
    protected final CommonDao commonDao;
    protected final UserContext userContext;

    protected String createBatchKey() {
        return UUID.randomUUID().toString();
    }

    protected String getBatchKey(Map<String, Object> sharedMap) {
        return getAs(sharedMap, BATCH_KEY, String.class);
    }

    protected void validateRepPop(String dccode) {
        int repPopCount = commonDao.selectOne(MS_DELIVERY_DISTRICT_REPPOP_NS + "countBaseRepPopByDccode", MsDistrictValidationReqDto.builder().dccode(dccode).build());
        if (repPopCount == 0) {
            throw new UserHandleException("MSG.COM.ERR.999", new String[] { "센터의 기본대표POP가 설정되어 있지 않습니다. 기본 대표POP를 설정 후 사용해 주십시요" });
        }
    }

    public void process(List<T> reqDtoList, Map<String, Object> sharedMap) {
        /*
          BATCH_KEY 생성
          INSERT, UPDATE, DELETE시 serialkey ProcessResult에 Set<String> key 저장시 수백개 이상일경우
          BATCH_KEY 방식필요 -> 대상 테이블 컬럼 EDITDATE(TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS'), EDITWHO('SYSTEM'+NANOSECONDS)처리
         */
        String batchKey = createBatchKey();
        sharedMap.put(BATCH_KEY, batchKey);

        List<T> insertList = new ArrayList<>();
        List<T> updateList = new ArrayList<>();
        List<T> deleteList = new ArrayList<>();
        for (T dto : reqDtoList) {
            switch (dto.getRowStatus()) {
                case CanalFrameConstants.INSERT -> insertList.add(dto);
                case CanalFrameConstants.UPDATE -> updateList.add(dto);
                case CanalFrameConstants.DELETE -> deleteList.add(dto);
                default -> {
                    log.warn("알 수 없는 rowStatus: {}", dto.getRowStatus());
                }
            }
        }
        init(reqDtoList, sharedMap);
        List<T> validReqDtoList = validate(reqDtoList, insertList, updateList, deleteList, sharedMap);
        if (CollectionUtils.isEmpty(validReqDtoList)) {
            return;
        }

        ProcessResult processResult = new ProcessResult();
        processResult.setBatchKey(batchKey);
        if (!CollectionUtils.isEmpty(insertList)) {
            Object result = insert(insertList, sharedMap);
            processResult.setInsertResult(result);
        }
        if (!CollectionUtils.isEmpty(updateList)) {
            Object result = update(updateList, sharedMap);
            processResult.setUpdateResult(result);
        }
        if (!CollectionUtils.isEmpty(deleteList)) {
            Object result = delete(deleteList, sharedMap);
            processResult.setDeleteResult(result);
        }

        history(validReqDtoList, processResult, sharedMap);
        connect(validReqDtoList, processResult, sharedMap);
        finish(validReqDtoList, processResult, sharedMap);
//        throw new RuntimeException("[TEST] FORCE ROLLBACK ★★★★★★★★★★★★★★★★★★★★★★★★");
    }

    /**
     * 초기화 (필요시 오버라이드)
     */
    protected void init(List<T> reqDtoList, Map<String, Object> sharedMap) {
    }

    /**
     * 기본검증 적용시작일자가 적용종료일자보다 클수없다.
     */
    protected List<T> validate(List<T> reqDtoList, List<T> insertList, List<T> updateList,
            List<T> deleteList, Map<String, Object> sharedMap) {
        reqDtoList.forEach(reqDto -> {
            BeanWrapper wrapper = new BeanWrapperImpl(reqDto);
            if (wrapper.isReadableProperty("fromDate") && wrapper.isReadableProperty("toDate")) {
                String fromDate = (String) wrapper.getPropertyValue("fromDate");
                String toDate = (String) wrapper.getPropertyValue("toDate");
                if (StringUtils.hasText(fromDate) && StringUtils.hasText(toDate) &&
                    fromDate.compareTo(toDate) > 0) {
                    throw new UserHandleException("MSG.COM.VAL.067", new String[] {"적용종료일자는 적용시작일자 이후로 입력 가능합니다."});
                }
            }
        });
        return doValidate(reqDtoList, insertList, updateList, deleteList, sharedMap);
    }

    /**
     * 검증 처리 (필요시 오버라이드)
     */
    protected List<T> doValidate(List<T> reqDtoList, List<T> insertList, List<T> updateList,
                               List<T> deleteList, Map<String, Object> sharedMap) {
        return reqDtoList;
    }

    /**
     * INSERT
     */
    protected abstract Object insert(List<T> insertList, Map<String, Object> sharedMap);

    /**
     * UPDATE
     */
    protected abstract Object update(List<T> updateList, Map<String, Object> sharedMap);

    /**
     * DELETE
     */
    protected abstract Object delete(List<T> deleteList, Map<String, Object> sharedMap);

    /**
     * 이력 남김 (필요시 오버라이드)
     */
    protected void history(List<T> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {}
    /**
     * 연계 처리 (필요시 오버라이드)
     */
    protected void connect(List<T> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {}

    /**
     * MS_HJDONG_POP 비교 방식 IF 적재 + MERGE.
     * DEL_YN='Y'(삭제)를 먼저 적재하여 외부 시스템(SRM)이 SERIALKEY 순서대로 처리할 수 있도록 보장.
     */
    protected void insertIfAndMergeMsHjdongPop(MsDistrictValidationReqDto validationReqDto) {
        int seqOffset = 0;
        validationReqDto.setBatchDate(new Date());

        // STEP 1: IF INSERT DEL_YN='Y' (forward diff + stale reverse diff)
        validationReqDto.setDelYnFilter("Y");
        for (String ifId : IF_ID_DATA) {
            validationReqDto.setIfId(ifId);
            validationReqDto.setSeqOffset(seqOffset);
            seqOffset += commonDao.insert("msHjdongPopService.insertIfFromMsHjdongPop", validationReqDto);
        }
        // STEP 2: IF INSERT DEL_YN='N' (forward diff only)
        validationReqDto.setDelYnFilter("N");
        for (String ifId : IF_ID_DATA) {
            validationReqDto.setIfId(ifId);
            validationReqDto.setSeqOffset(seqOffset);
            seqOffset += commonDao.insert("msHjdongPopService.insertIfFromMsHjdongPop", validationReqDto);
        }
        // STEP 3: MERGE MS_HJDONG_POP (forward upsert)
        validationReqDto.setDelYnFilter(null);
        commonDao.update("msHjdongPopService.mergeMsHjdongPop", validationReqDto);
        // STEP 4: Stale MS_HJDONG_POP → DEL_YN='Y' (SAMPLE에 없는 행 cleanup)
        commonDao.update("msHjdongPopService.cleanupStaleMsHjdongPop", validationReqDto);
    }
    /**
     * 완료 처리 (필요시 오버라이드)
     */
    protected void finish(List<T> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {}

    @SuppressWarnings("unchecked")
    public static <T> T getAs(Map<String, Object> map, String key, Class<T> type) {
        Object value = map.get(key);
        if (value == null) {
            return null; // null-safe
        }
        return (T) value;
    }

    public static <E> Set<E> getSetAs(Map<String, Object> map, String key, Class<E> type) {
        Object value = map.get(key);
        if (value == null) {
            return Collections.emptySet();
        }
        @SuppressWarnings("unchecked")
        Set<E> safe = (Set<E>) value;
        return safe;
    }

    public static <E> List<E> getListAs(Map<String, Object> map, String key, Class<E> type) {
        Object value = map.get(key);
        if (value == null) {
            return Collections.emptyList();
        }
        @SuppressWarnings("unchecked")
        List<E> safe = (List<E>) value;
        return safe;
    }

    public static <T> void assignKeys(List<T> items, Supplier<List<String>> keySupplier, BiConsumer<T, String> processorWithKey) {
        List<String> keys = keySupplier.get();
        Assert.isTrue(keys.size() == items.size(), "조회한 key 개수와 items 개수가 다릅니다.");

        Iterator<String> it = keys.iterator();
        for (T item : items) {
            processorWithKey.accept(item, it.next());
        }
    }

    public static Set<String> keySetFromList(CommonDao commonDao, String getSerialkeysQueryId, int size) {
        List<String> serialkeys = commonDao.selectList(getSerialkeysQueryId, size);
        return new LinkedHashSet<>(serialkeys);
    }
}
