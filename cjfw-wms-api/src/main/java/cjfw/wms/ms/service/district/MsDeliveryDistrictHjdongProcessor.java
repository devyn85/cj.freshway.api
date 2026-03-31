package cjfw.wms.ms.service.district;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsCenterDlvDistrictHjdongReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictHjdongResDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictReqDto;
import cjfw.wms.ms.dto.MsDistrictValidationReqDto;
import cjfw.wms.ms.entity.MsDlvDistrictHjdongEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MsDeliveryDistrictHjdongProcessor extends AbstractDistrictProcessor<MsCenterDlvDistrictHjdongReqDto.Hjdong> {
    private static final String DUPLICATE_LIST = MsDeliveryDistrictHjdongProcessor.class.getSimpleName() + ".DUPLICATE_LIST";
    private static final String SAME_DISTRICT_DELETE_LIST = MsDeliveryDistrictHjdongProcessor.class.getSimpleName() + ".SAME_DISTRICT_DELETE_LIST";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public MsDeliveryDistrictHjdongProcessor(CommonDao commonDao, UserContext userContext) {
        super(MsCenterDlvDistrictHjdongReqDto.Hjdong.class, commonDao, userContext);
    }

    @Override
    protected String createBatchKey() {
        return commonDao.selectOne(MS_DISTRICT_ORDER_GROUP_MERGE_NS + "getBatchKey", MsDistrictValidationReqDto.builder().build()).toString();
    }

    @Override
    protected List<MsCenterDlvDistrictHjdongReqDto.Hjdong> doValidate(
            List<MsCenterDlvDistrictHjdongReqDto.Hjdong> reqDtoList,
            List<MsCenterDlvDistrictHjdongReqDto.Hjdong> insertList,
            List<MsCenterDlvDistrictHjdongReqDto.Hjdong> updateList,
            List<MsCenterDlvDistrictHjdongReqDto.Hjdong> deleteList, Map<String, Object> sharedMap) {
        validateRepPop(reqDtoList.get(0).getDccode());
        
        for (int i = 0; i < reqDtoList.size(); i++) {
            reqDtoList.get(i).setRowCount(i+1);
        }
        Set<String> exceptSerialkeySet = Stream.concat(updateList.stream(), deleteList.stream())
            .map(MsCenterDlvDistrictHjdongReqDto.Hjdong::getSerialkey)
            .collect(Collectors.toSet());
        List<MsCenterDlvDistrictHjdongReqDto.Hjdong> validateReqDtoList = Stream.of(insertList, updateList).flatMap(List::stream).toList();
        MsDistrictValidationReqDto validationReqDto = MsDistrictValidationReqDto.builder().serialkeySet(exceptSerialkeySet).dccode(reqDtoList.get(0).getDccode()).list(validateReqDtoList).build();
        List<MsCenterDlvDistrictHjdongResDto> duplicateSameList = commonDao.selectList(MS_DELIVERY_DISTRICT_NS + "getDupDlvHjdongSameDlvDistrictIdList", validationReqDto);
        if (!CollectionUtils.isEmpty(duplicateSameList)) {
            StringBuilder sb = new StringBuilder();
            Map<String, List<MsCenterDlvDistrictHjdongResDto>> dupMap = duplicateSameList.stream().collect(Collectors.groupingBy(MsCenterDlvDistrictHjdongResDto::getDlvdistrictNm));
            dupMap.forEach((key, value) -> {
                if (!CollectionUtils.isEmpty(value)) {
                    MsCenterDlvDistrictHjdongResDto first = value.get(0);
                    int extraCount = Math.max(0, value.size() - 1);
                    String msg = String.format(
                        extraCount > 0
                            ? "[%s]에 [%s]외 [%d]건"
                            : "[%s]에 [%s]",
                        first.getDlvdistrictNm(),
                        first.getHjdongNm(),
                        extraCount
                    );
                    sb.append(msg).append("\n");
                }
            });
            sb.append("중복된 행정동이 존재합니다.");
            throw new UserHandleException("MSG.COM.ERR.999", new String[] { sb.toString() });
        }
/*      getCenterDistrictUsageList -> LEAST(HJDONG.TODATE, '29991231')
        List<MsCenterDlvDistrictHjdongResDto> getNotMatchCenterHjdongList = commonDao.selectList(MS_DELIVERY_DISTRICT_NS + "getNotMatchCenterHjdong", MsDistrictValidationReqDto.builder().list(insertList).build());
        if (!CollectionUtils.isEmpty(getNotMatchCenterHjdongList)) {
            MsCenterDlvDistrictHjdongResDto first = getNotMatchCenterHjdongList.get(0);
            String msg = String.format("센터 [%s]의 종료일은 [%s] 입니다.", first.getHjdongNm(), first.getToDate());
            throw new UserHandleException("MSG.COM.ERR.999", new String[] { msg });
        }
*/

        List<MsCenterDlvDistrictHjdongResDto> duplicateOtherList = commonDao.selectList(MS_DELIVERY_DISTRICT_NS + "getDupDlvHjdongOtherDlvDistrictIdList", validationReqDto);
        sharedMap.put(DUPLICATE_LIST, duplicateOtherList);

        // 동일 권역 DELETE+INSERT 매칭: INSERT 전에 물리 삭제 대상 저장
        List<MsCenterDlvDistrictHjdongReqDto.Hjdong> sameDistrictDeleteList = deleteList.stream()
            .filter(del -> insertList.stream().anyMatch(ins ->
                Objects.equals(del.getHjdongCd(), ins.getHjdongCd()) &&
                Objects.equals(del.getDlvdistrictId(), ins.getDlvdistrictId())))
            .toList();
        if (!CollectionUtils.isEmpty(sameDistrictDeleteList)) {
            sharedMap.put(SAME_DISTRICT_DELETE_LIST, sameDistrictDeleteList);
        }

        return reqDtoList;
    }

    @Override
    protected Object insert(List<MsCenterDlvDistrictHjdongReqDto.Hjdong> insertList, Map<String, Object> sharedMap) {
        String[] batchKey = StringUtils.split(getBatchKey(sharedMap), "_");
        Set<String> updateDlvDistrictHjdongKeySet = new HashSet<>();
        Map<Integer, MsCenterDlvDistrictHjdongReqDto.Hjdong> insertReqMap = insertList.stream().collect(Collectors.toMap(MsCenterDlvDistrictHjdongReqDto.Hjdong::getRowCount, Function.identity()));
        List<MsCenterDlvDistrictHjdongResDto> duplicateOtherList = getListAs(sharedMap, DUPLICATE_LIST, MsCenterDlvDistrictHjdongResDto.class);
        for (MsCenterDlvDistrictHjdongResDto resDto : duplicateOtherList) {
            MsCenterDlvDistrictHjdongReqDto.Hjdong req = insertReqMap.get(resDto.getRowCount());
            if (req != null) {
                updateDlvDistrictHjdongKeySet.add(resDto.getSerialkey());
                MsDlvDistrictHjdongEntity duplicateHjdongEntity = new MsDlvDistrictHjdongEntity();
                duplicateHjdongEntity.setSerialkey(resDto.getSerialkey());
                duplicateHjdongEntity.setDccode(resDto.getDccode());
                duplicateHjdongEntity.setDelYn("Y");
                duplicateHjdongEntity.setDlvdistrictId(resDto.getDlvdistrictId());
                duplicateHjdongEntity.setToDate(
                    LocalDate.parse(req.getFromDate(), dateTimeFormatter).minusDays(1).format(dateTimeFormatter)
                );
                resDto.setDelYn("Y");
                resDto.setEditDate(batchKey[0]);
                resDto.setEditWho(batchKey[1]);
                commonDao.update(MS_DELIVERY_DISTRICT_NS + "updateDlvDistrictHjdong", resDto);
            }
        }
        // 동일 권역 DELETE+INSERT: INSERT 전에 기존 레코드 물리 삭제 (unique constraint 방지)
        List<MsCenterDlvDistrictHjdongReqDto.Hjdong> sameDistrictDeleteList = getListAs(sharedMap, SAME_DISTRICT_DELETE_LIST, MsCenterDlvDistrictHjdongReqDto.Hjdong.class);
        for (MsCenterDlvDistrictHjdongReqDto.Hjdong deleteDto : sameDistrictDeleteList) {
            commonDao.delete(MS_DELIVERY_DISTRICT_NS + "deleteDlvDistrictHjdong", deleteDto);
        }

        Set<String> saveDlvDistrictHjdongKeySet = keySetFromList(commonDao, "getSerialkeysFromDlvHjdong", insertList.size());
        Iterator<String> it = saveDlvDistrictHjdongKeySet.iterator();
        for (MsCenterDlvDistrictHjdongReqDto.Hjdong hjdong : insertList) {
            hjdong.setSerialkey(it.next());
            hjdong.setEditDate(batchKey[0]);
            hjdong.setEditWho(batchKey[1]);
            commonDao.insert(MS_DELIVERY_DISTRICT_NS + "saveDlvDistrictHjdong", hjdong);
        }
        return Pair.of(updateDlvDistrictHjdongKeySet, saveDlvDistrictHjdongKeySet);
    }

    @Override
    protected Object update(List<MsCenterDlvDistrictHjdongReqDto.Hjdong> updateList, Map<String, Object> sharedMap) {
        String[] batchKey = StringUtils.split(getBatchKey(sharedMap), "_");
        for (MsCenterDlvDistrictHjdongReqDto.Hjdong hjdong : updateList) {
            hjdong.setEditDate(batchKey[0]);
            hjdong.setEditWho(batchKey[1]);
            commonDao.insert(MS_DELIVERY_DISTRICT_NS + "updateDlvDistrictHjdong", hjdong);
        }
        return updateList.stream().map(MsCenterDlvDistrictHjdongReqDto.Hjdong::getSerialkey).collect(Collectors.toSet());
    }

    @Override
    protected Object delete(List<MsCenterDlvDistrictHjdongReqDto.Hjdong> deleteList, Map<String, Object> sharedMap) {
        String[] batchKey = StringUtils.split(getBatchKey(sharedMap), "_");
        Set<String> deleteDlvDistrictHjdongKeySet = new LinkedHashSet<>();
        for (MsCenterDlvDistrictHjdongReqDto.Hjdong reqDto : deleteList) {
            deleteDlvDistrictHjdongKeySet.add(reqDto.getSerialkey());
            reqDto.setEditDate(batchKey[0]);
            reqDto.setEditWho(batchKey[1]);
            commonDao.update(MS_DELIVERY_DISTRICT_NS + "expireDlvDistrictHjdong", reqDto);
        }
        return deleteDlvDistrictHjdongKeySet;
    }

    @Override
    protected void history(List<MsCenterDlvDistrictHjdongReqDto.Hjdong> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        String[] batchKey = StringUtils.split(processResult.getBatchKey(), "_");
        commonDao.insert(MS_DELIVERY_DISTRICT_HISTORY_NS + "saveHistoryListByDlvHjdongBatchKey",
            MsDistrictValidationReqDto.builder().editDate(batchKey[0]).editWho(batchKey[1]).build());
    }

    @Override
    protected void connect(List<MsCenterDlvDistrictHjdongReqDto.Hjdong> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        String[] batchKey = StringUtils.split(processResult.getBatchKey(), "_");
        MsDistrictValidationReqDto validationReqDto = MsDistrictValidationReqDto.builder()
            .eventType(MsDistrictValidationReqDto.EventType.DLV_HJDONG).editDate(batchKey[0]).editWho(batchKey[1]).build();
        insertIfAndMergeMsHjdongPop(validationReqDto);
    }

    @Override
    protected void finish(List<MsCenterDlvDistrictHjdongReqDto.Hjdong> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        String dlvgroupId = StringUtils.defaultIfEmpty(reqDtoList.get(0).getDlvgroupId(), " ");
        String dlvdistrictId = reqDtoList.get(0).getDlvdistrictId();
        Assert.hasLength(dlvgroupId, "[dlvgroupId] must has length");
        Assert.hasText(dlvdistrictId, "[dlvdistrictId] must not be empty");

        Set<String> updateKeySet = processResult.getUpdateSetAs();
        Set<String> deleteKeySet = processResult.getDeleteSetAs();
        Set<String> keySet = Stream.of(updateKeySet, deleteKeySet)
            .flatMap(Set::stream)
            .collect(Collectors.toSet());
        if (!CollectionUtils.isEmpty(keySet)) {
            String[] batchKey = StringUtils.split(processResult.getBatchKey(), "_");
            commonDao.delete(MS_DELIVERY_DISTRICT_NS + "deleteFutureDlvDistrictHjdongByBatchKey",
                MsDistrictValidationReqDto.builder().editDate(batchKey[0]).editWho(batchKey[1]).build());
        }
        MsCenterDlvDistrictReqDto districtDto = new MsCenterDlvDistrictReqDto();
        districtDto.setDlvdistrictId(dlvdistrictId);
        districtDto.setDlvgroupId(dlvgroupId);
        districtDto.setDccode(reqDtoList.get(0).getDccode());
        commonDao.delete(MS_DELIVERY_DISTRICT_NS + "deleteCenterDlvDistrictPolygonByDlvDistrictId", districtDto);
        commonDao.insert(MS_DELIVERY_DISTRICT_NS + "saveCenterDlvDistrictPolygon", districtDto);
        commonDao.delete(MS_DELIVERY_DISTRICT_NS + "deleteCenterDlvDistrictGroupPolygon", districtDto);
        commonDao.insert(MS_DELIVERY_DISTRICT_NS + "saveCenterDlvDistrictGroupPolygon", districtDto);
    }

}
