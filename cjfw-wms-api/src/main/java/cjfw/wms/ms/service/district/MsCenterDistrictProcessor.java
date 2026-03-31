package cjfw.wms.ms.service.district;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsCenterDistrictHjdongReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictHjdongResDto;
import cjfw.wms.ms.dto.MsCenterDistrictOrdGrpResDto;
import cjfw.wms.ms.dto.MsDistrictValidationReqDto;
import cjfw.wms.ms.service.MsDistrictOrderGroupMergeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MsCenterDistrictProcessor extends AbstractDistrictProcessor<MsCenterDistrictHjdongReqDto> {
    private final MsDistrictOrderGroupMergeService msDistrictOrderGroupMergeService;

    public MsCenterDistrictProcessor(CommonDao commonDao, UserContext userContext,
            MsDistrictOrderGroupMergeService msDistrictOrderGroupMergeService) {
        super(MsCenterDistrictHjdongReqDto.class, commonDao, userContext);
        this.msDistrictOrderGroupMergeService = msDistrictOrderGroupMergeService;
    }

    @Override
    protected String createBatchKey() {
        return commonDao.selectOne(MS_DISTRICT_ORDER_GROUP_MERGE_NS + "getBatchKey", MsDistrictValidationReqDto.builder().build()).toString();
    }

    @Override
    protected List<MsCenterDistrictHjdongReqDto> doValidate(List<MsCenterDistrictHjdongReqDto> reqDtoList,
            List<MsCenterDistrictHjdongReqDto> insertList, List<MsCenterDistrictHjdongReqDto> updateList,
            List<MsCenterDistrictHjdongReqDto> deleteList, Map<String, Object> sharedMap) {
        String dccode = reqDtoList.get(0).getDccode();
        validateRepPop(dccode);

        int unSettingMatrixOrdgrpCount = commonDao.selectOne(MS_CENTER_DISTRICT_ORDGRP_NS + "countUnSettingMatrixOrdgrp", reqDtoList.get(0));
        if (unSettingMatrixOrdgrpCount > 0) {
            throw new UserHandleException("MSG.COM.ERR.999", new String[] { "플랫폼 주문유형이 설정되어 있지 않습니다. 플랫폼 주문유형 설정 후 사용해 주십시요" });
        }
        for (int i = 0; i < reqDtoList.size(); i++) {
            reqDtoList.get(i).setRowCount(i+1);
        }
        Set<String> exceptSerialkeySet = Stream.concat(updateList.stream(), deleteList.stream())
                .map(MsCenterDistrictHjdongReqDto::getSerialkey)
                .collect(Collectors.toSet());
        List<MsCenterDistrictHjdongResDto> duplicateList = commonDao.selectList(MS_CENTER_DISTRICT_NS + "getDupHjdongList",
                MsDistrictValidationReqDto.builder().serialkeySet(exceptSerialkeySet).list(reqDtoList).dccode(dccode).build());
        if (!duplicateList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            Map<String, List<MsCenterDistrictHjdongResDto>> dupMap = duplicateList.stream().collect(Collectors.groupingBy(MsCenterDistrictHjdongResDto::getDccode));
            dupMap.forEach((key, value) -> {
                if (!org.apache.commons.collections4.CollectionUtils.isEmpty(value)) {
                    MsCenterDistrictHjdongResDto first = value.get(0);
                    int extraCount = Math.max(0, value.size() - 1);
                    String msg = String.format(
                        extraCount > 0
                            ? "[%s]에 [%s]외 [%d]건"
                            : "[%s]에 [%s]",
                        first.getDcname(),
                        first.getHjdongNm(),
                        extraCount
                    );
                    sb.append(msg).append("\n");
                }
            });
            sb.append("중복된 행정동이 존재합니다.");
            throw new UserHandleException("MSG.COM.ERR.999", new String[] { sb.toString() });
        }
        return reqDtoList;
    }

    @Override
    protected Object insert(List<MsCenterDistrictHjdongReqDto> insertList, Map<String, Object> sharedMap) {
        String[] batchKey = StringUtils.delimitedListToStringArray(getBatchKey(sharedMap), "_");
        Set<String> saveHjdongKeySet = keySetFromList(commonDao, MS_CENTER_DISTRICT_NS + "getSerialkeysFromHjdong", insertList.size());
        Set<String> expireDcOrdgrpKeySet = Collections.emptySet();
        Set<String> saveDcOrdgrpKeySet = Collections.emptySet();
        Iterator<String> keyIt = saveHjdongKeySet.iterator();
        for (MsCenterDistrictHjdongReqDto reqDto : insertList) {
            reqDto.setSerialkey(keyIt.next());
            reqDto.setEditDate(batchKey[0]);
            reqDto.setEditWho(batchKey[1]);
            commonDao.insert(MS_CENTER_DISTRICT_NS + "saveDistrictHjdong", reqDto);
        }

//        commonDao.update(MS_CENTER_DISTRICT_DC_ORDGRP_NS + "mergeDcDistrictOrdgrpByPriorityByBatchKey", MsDistrictValidationReqDto.builder().editDate(batchKey[0]).editWho(batchKey[1]).build());
        List<MsCenterDistrictOrdGrpResDto> ordgrpList = commonDao.selectList(MS_CENTER_DISTRICT_DC_ORDGRP_NS + "getDcDistrictOrdgrpByPriorityByBatchKey",
                MsDistrictValidationReqDto.builder().editDate(batchKey[0]).editWho(batchKey[1]).build());
        if (!ordgrpList.isEmpty()) {
            saveDcOrdgrpKeySet = keySetFromList(commonDao, "getSerialkeysFromDcOrdgrp", ordgrpList.size());
            Iterator<String> ordgrpKeyIt = saveDcOrdgrpKeySet.iterator();
            for (MsCenterDistrictOrdGrpResDto dto : ordgrpList) {
                dto.setSerialkey(ordgrpKeyIt.next());
                dto.setEditDate(batchKey[0]);
                dto.setEditWho(batchKey[1]);
                commonDao.insert(MS_CENTER_DISTRICT_ORDGRP_NS + "saveDcDistrictOrdgrp", dto);
            }
        }
        return saveHjdongKeySet;
    }

    @Override
    protected Object update(List<MsCenterDistrictHjdongReqDto> updateList, Map<String, Object> sharedMap) {
        String[] batchKey = StringUtils.delimitedListToStringArray(getBatchKey(sharedMap), "_");
        updateList.forEach(reqDto -> {
            reqDto.setEditDate(batchKey[0]);
            reqDto.setEditWho(batchKey[1]);
        });
        Set<String> hjdongKeySet = updateList.stream().map(MsCenterDistrictHjdongReqDto::getSerialkey).collect(Collectors.toSet());
        Map<String, MsCenterDistrictHjdongReqDto> serialkeyMap = updateList.stream().collect(Collectors.toMap(MsCenterDistrictHjdongReqDto::getSerialkey, Function.identity()));
        List<Map<String, String>> districtOrdgrpList = commonDao.selectList(MS_CENTER_DISTRICT_ORDGRP_NS + "getDistrictOrdgrpByHjdongKeyList",
                MsDistrictValidationReqDto.builder().serialkeySet(hjdongKeySet).build());
        for (Map<String, String> districtOrdgrp : districtOrdgrpList) {
            String hjdongSerialkey = districtOrdgrp.get("HJDONG_SERIALKEY");
            MsCenterDistrictHjdongReqDto reqDto = serialkeyMap.get(hjdongSerialkey);
            MsCenterDistrictHjdongReqDto newReqDto = new MsCenterDistrictHjdongReqDto();
            BeanUtils.copyProperties(reqDto , newReqDto);
            newReqDto.setSerialkey(districtOrdgrp.get("SERIALKEY"));
            commonDao.update(MS_CENTER_DISTRICT_ORDGRP_NS + "updateDcDistrictOrdgrp", newReqDto);
        }
        for (MsCenterDistrictHjdongReqDto reqDto : updateList) {
            commonDao.update(MS_CENTER_DISTRICT_NS + "updateDistrictHjdong", reqDto);
        }
        return hjdongKeySet;
    }

    @Override
    protected Object delete(List<MsCenterDistrictHjdongReqDto> deleteList, Map<String, Object> sharedMap) {
        return update(deleteList, sharedMap);
    }

    @Override
    protected void history(List<MsCenterDistrictHjdongReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        String[] batchKey = StringUtils.delimitedListToStringArray(processResult.getBatchKey(), "_");
        commonDao.insert(MS_CENTER_DISTRICT_HISTORY_NS + "saveDcDistrictHisByHjdongBatchKey",
            MsDistrictValidationReqDto.builder().editDate(batchKey[0]).editWho(batchKey[1]).build());
    }

    @Override
    protected void connect(List<MsCenterDistrictHjdongReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        String[] batchKey = StringUtils.delimitedListToStringArray(processResult.getBatchKey(), "_");
        MsDistrictValidationReqDto validationReqDto = MsDistrictValidationReqDto.builder()
            .eventType(MsDistrictValidationReqDto.EventType.HJDONG).editDate(batchKey[0]).editWho(batchKey[1]).build();
        insertIfAndMergeMsHjdongPop(validationReqDto);
    }

    @Override
    protected void finish(List<MsCenterDistrictHjdongReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> hjdongHisKeySet = processResult.getSetKeys(false, true, true);
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(hjdongHisKeySet)) {
            commonDao.delete(MS_CENTER_DISTRICT_NS + "deleteFutureDcDistrictHjdong", MsDistrictValidationReqDto.builder().serialkeySet(hjdongHisKeySet).build());
        }
        String dccode = reqDtoList.get(0).getDccode();
        commonDao.update(MS_CENTER_DISTRICT_NS + "updateCenterDistrictPolygonInvalid", MsCenterDistrictHjdongReqDto.builder().dccode(dccode).build());
        commonDao.insert(MS_CENTER_DISTRICT_NS + "saveCenterDistrictPolygon", MsCenterDistrictHjdongReqDto.builder().dccode(dccode).build());
    }
}
