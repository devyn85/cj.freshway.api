package cjfw.wms.ms.service.district;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsCenterDistrictDcOrdGrpReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictDcOrdGrpResDto;
import cjfw.wms.ms.dto.MsCenterDistrictOrdGrpResDto;
import cjfw.wms.ms.dto.MsDistrictValidationReqDto;
import cjfw.wms.ms.service.MsDistrictOrderGroupMergeService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MsCenterDistrictOrdGrpProcessor extends AbstractDistrictProcessor<MsCenterDistrictDcOrdGrpReqDto> {
    private final MsDistrictOrderGroupMergeService msDistrictOrderGroupMergeService;

    public MsCenterDistrictOrdGrpProcessor(CommonDao commonDao, UserContext userContext,
            MsDistrictOrderGroupMergeService msDistrictOrderGroupMergeService) {
        super(MsCenterDistrictDcOrdGrpReqDto.class, commonDao, userContext);
        this.msDistrictOrderGroupMergeService = msDistrictOrderGroupMergeService;
    }

    @Override
    protected String createBatchKey() {
        return commonDao.selectOne(MS_DISTRICT_ORDER_GROUP_MERGE_NS + "getBatchKey", MsDistrictValidationReqDto.builder().build()).toString();
    }

    @Override
    protected List<MsCenterDistrictDcOrdGrpReqDto> doValidate(List<MsCenterDistrictDcOrdGrpReqDto> reqDtoList,
            List<MsCenterDistrictDcOrdGrpReqDto> insertList, List<MsCenterDistrictDcOrdGrpReqDto> updateList,
            List<MsCenterDistrictDcOrdGrpReqDto> deleteList, Map<String, Object> sharedMap) {

        List<MsCenterDistrictDcOrdGrpReqDto> validateReqDtoList = Stream.of(insertList, updateList).flatMap(List::stream).toList();
        Set<String> keySet = Stream.concat(updateList.stream(), deleteList.stream()).map(MsCenterDistrictDcOrdGrpReqDto::getSerialkey).collect(Collectors.toSet());
        List<MsCenterDistrictDcOrdGrpResDto> conflictList = commonDao.selectList(MS_CENTER_DISTRICT_DC_ORDGRP_NS + "getDcOrdgrpConflictList",
            MsDistrictValidationReqDto.builder().serialkeySet(keySet).list(validateReqDtoList).build());
        if (!conflictList.isEmpty()) {
            throw new UserHandleException("MSG.COM.ERR.999", new String[] {
                String.format("[%s] 주문그룹은 적용일자가 중복되어 등록이 불가합니다.", conflictList.get(0).getOrdGrpNm())
            });
        }
        return reqDtoList;
    }

    @Override
    protected Object insert(List<MsCenterDistrictDcOrdGrpReqDto> reqDtoList, Map<String, Object> sharedMap) {
        String[] batchKey = StringUtils.delimitedListToStringArray(getBatchKey(sharedMap), "_");
        Set<String> saveDcOrdgrpKeySet;
        Set<String> saveOrdgrpKeySet = keySetFromList(commonDao, "getSerialkeysFromOrdgrp", reqDtoList.size());
        Iterator<String> keyIt = saveOrdgrpKeySet.iterator();
        for (MsCenterDistrictDcOrdGrpReqDto reqDto : reqDtoList) {
            reqDto.setSerialkey(keyIt.next());
            reqDto.setEditDate(batchKey[0]);
            reqDto.setEditWho(batchKey[1]);
            commonDao.insert(MS_CENTER_DISTRICT_DC_ORDGRP_NS + "saveDcOrdgrp", reqDto);
        }
        List<MsCenterDistrictOrdGrpResDto> dcOrdgrpList = commonDao.selectList(MS_CENTER_DISTRICT_ORDGRP_NS + "getPlatformDcOrdgrpByOrdgrpKeyList",
                MsDistrictValidationReqDto.builder().serialkeySet(saveOrdgrpKeySet).build());
        saveDcOrdgrpKeySet = keySetFromList(commonDao, "getSerialkeysFromDcOrdgrp", dcOrdgrpList.size());
        Iterator<String> dcOrdgrpkeyIt = saveDcOrdgrpKeySet.iterator();
        if (!dcOrdgrpList.isEmpty()) {
            for (MsCenterDistrictOrdGrpResDto dto : dcOrdgrpList) {
                dto.setSerialkey(dcOrdgrpkeyIt.next());
                dto.setEditDate(batchKey[0]);
                dto.setEditWho(batchKey[1]);
                commonDao.insert(MS_CENTER_DISTRICT_ORDGRP_NS + "saveDcDistrictOrdgrp", dto);
            }
        }
        return Pair.of(saveOrdgrpKeySet, saveDcOrdgrpKeySet);
    }

    @Override
    protected Object update(List<MsCenterDistrictDcOrdGrpReqDto> reqDtoList, Map<String, Object> sharedMap) {
        String[] batchKey = StringUtils.delimitedListToStringArray(getBatchKey(sharedMap), "_");
        Set<String> updateOrdgrpKeySet = reqDtoList.stream().map(MsCenterDistrictDcOrdGrpReqDto::getSerialkey).collect(Collectors.toSet());
        for (MsCenterDistrictDcOrdGrpReqDto reqDto : reqDtoList) {
            reqDto.setEditDate(batchKey[0]);
            reqDto.setEditWho(batchKey[1]);
            commonDao.update(MS_CENTER_DISTRICT_DC_ORDGRP_NS + "updateDcOrdgrpByKey", reqDto);
            commonDao.update(MS_CENTER_DISTRICT_ORDGRP_NS + "updateMasterListByDcOrdGrp", reqDto);
        }
        return updateOrdgrpKeySet;
    }

    @Override
    protected Object delete(List<MsCenterDistrictDcOrdGrpReqDto> reqDtoList, Map<String, Object> sharedMap) {
        return update(reqDtoList, sharedMap);
    }

    private Set<String> getDcOrdgrpKeySet(ProcessResult processResult, boolean insert, boolean update, boolean delete) {
        Set<String> dcOrdgrpKeySet = new LinkedHashSet<>();
        Pair<Set<String>, Set<String>> insertResult = processResult.getInsertResult();
        Set<String> updateResult = processResult.getUpdateResult();
        Set<String> deleteResult = processResult.getDeleteResult();
        if (insertResult != null && insert) {
            dcOrdgrpKeySet.addAll(insertResult.getLeft());
        }
        if (updateResult != null && update) {
            dcOrdgrpKeySet.addAll(updateResult);
        }
        if (deleteResult != null && delete) {
            dcOrdgrpKeySet.addAll(deleteResult);
        }
        return dcOrdgrpKeySet;
    }

    @Override
    protected void history(List<MsCenterDistrictDcOrdGrpReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> dcOrdgrpHisKeySet = getDcOrdgrpKeySet(processResult, true, true, true);
        if (!CollectionUtils.isEmpty(dcOrdgrpHisKeySet)) {
            String[] batchKey = StringUtils.delimitedListToStringArray(processResult.getBatchKey(), "_");
            commonDao.insert(MS_CENTER_DISTRICT_HISTORY_NS + "saveDcOrdgrpHisByBatchKey",
                MsDistrictValidationReqDto.builder().editDate(batchKey[0]).editWho(batchKey[1]).build());
            commonDao.insert(MS_CENTER_DISTRICT_HISTORY_NS + "saveDcDistrictOrdgrpHisByBatchKey",
                MsDistrictValidationReqDto.builder().editDate(batchKey[0]).editWho(batchKey[1]).build());
        }
    }

    @Override
    protected void connect(List<MsCenterDistrictDcOrdGrpReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> dcOrdgrpHisKeySet = getDcOrdgrpKeySet(processResult, true, true, true);
        if (!org.springframework.util.CollectionUtils.isEmpty(dcOrdgrpHisKeySet)) {
            String[] batchKey = StringUtils.delimitedListToStringArray(processResult.getBatchKey(), "_");
            MsDistrictValidationReqDto validationReqDto = MsDistrictValidationReqDto.builder()
                .eventType(MsDistrictValidationReqDto.EventType.ORDGRP).editDate(batchKey[0]).editWho(batchKey[1]).build();
            insertIfAndMergeMsHjdongPop(validationReqDto);
        }
    }

    @Override
    protected void finish(List<MsCenterDistrictDcOrdGrpReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        String[] batchKey = StringUtils.delimitedListToStringArray(processResult.getBatchKey(), "_");
        MsDistrictValidationReqDto validationReqDto = MsDistrictValidationReqDto.builder().editDate(batchKey[0]).editWho(batchKey[1]).build();
        commonDao.delete("deleteFutureDistrictOrdgrpByBatchKey", validationReqDto);
        commonDao.delete("deleteFutureOrdgrpByByBatchKey", validationReqDto);
    }
}
