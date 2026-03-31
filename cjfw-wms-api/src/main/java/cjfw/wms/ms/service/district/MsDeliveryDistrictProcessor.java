package cjfw.wms.ms.service.district;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsCenterDlvDistrictHjdongResDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictResDto;
import cjfw.wms.ms.dto.MsDistrictValidationReqDto;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MsDeliveryDistrictProcessor extends AbstractDistrictProcessor<MsCenterDlvDistrictReqDto> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public MsDeliveryDistrictProcessor(CommonDao commonDao, UserContext userContext) {
        super(MsCenterDlvDistrictReqDto.class, commonDao, userContext);
    }

    static void updateCenterDlvDistrictGroupPolygon(CommonDao commonDao, MsCenterDlvDistrictReqDto reqDto) {
        Assert.hasText(reqDto.getDccode(), "reqDto [dccode] must not be empty");
        commonDao.delete(MS_DELIVERY_DISTRICT_NS + "deleteCenterDlvDistrictGroupPolygon", reqDto);
        commonDao.insert(MS_DELIVERY_DISTRICT_NS + "saveCenterDlvDistrictGroupPolygon", reqDto);
    }

    static void updateCenterDistrictPolygon(CommonDao commonDao, MsCenterDlvDistrictReqDto reqDto) {
        Assert.hasText(reqDto.getDccode(), "reqDto [dccode] must not be empty");
        Assert.hasText(reqDto.getDlvdistrictId(), "reqDto dlvdistrictId must not be empty");
        commonDao.delete(MS_DELIVERY_DISTRICT_NS + "deleteCenterDlvDistrictPolygonByDlvDistrictId", reqDto);
        commonDao.insert(MS_DELIVERY_DISTRICT_NS + "saveCenterDlvDistrictPolygon", reqDto);
    }

    @Override
    protected void init(List<MsCenterDlvDistrictReqDto> reqDtoList, Map<String, Object> sharedMap) {
        reqDtoList.forEach(reqDto -> {
            if (!StringUtils.hasText(reqDto.getDlvgroupId())) {
                reqDto.setDlvgroupId(" ");
            }
        });
    }

    @Override
    protected List<MsCenterDlvDistrictReqDto> doValidate(List<MsCenterDlvDistrictReqDto> reqDtoList, List<MsCenterDlvDistrictReqDto> insertList, List<MsCenterDlvDistrictReqDto> updateList,
                                 List<MsCenterDlvDistrictReqDto> deleteList, Map<String, Object> sharedMap) {
        /* 임시제외
        Set<String> dlvgroupIdSet = reqDtoList.stream().map(MsCenterDlvDistrictReqDto::getDlvgroupId).collect(Collectors.toSet());
        int count = commonDao.selectOne(MS_DELIVERY_DISTRICT_NS + "countSettingDlvPopByDlvgroupIdSet", MsDistrictValidationReqDto.builder().dlvgroupIdSet(dlvgroupIdSet).build());
        if (count < reqDtoList.size()) {
            throw new UserHandleException("MSG.COM.ERR.999", new String[] {
                "권역그룹의 대표POP는 필수입니다."
            });
        }*/
        validateRepPop(reqDtoList.get(0).getDccode());
        if (!insertList.isEmpty()) {
            int countDlvdistrictNm = commonDao.selectOne(MS_DELIVERY_DISTRICT_NS + "countDlvdistrictNm",
                MsDistrictValidationReqDto.builder().dataSet(insertList.stream().map(MsCenterDlvDistrictReqDto::getDlvdistrictNm).collect(Collectors.toSet())).build());
            if (countDlvdistrictNm > 0) {
                throw new UserHandleException("MSG.COM.ERR.999", new String[] {
                    "권역명이 중복입니다."
                });
            }
        }
        return reqDtoList;
    }

    @Override
    protected Object insert(List<MsCenterDlvDistrictReqDto> insertList, Map<String, Object> sharedMap) {
        Set<String> saveDlvDistrictKeySet = keySetFromList(commonDao, "getSerialkeysFromHjdong", insertList.size());
        Iterator<String> it = saveDlvDistrictKeySet.iterator();
        for (MsCenterDlvDistrictReqDto reqDto : insertList) {
            reqDto.setSerialkey(it.next());
            commonDao.insert(MS_DELIVERY_DISTRICT_NS + "saveDlvDistrict", reqDto);
        }
        return saveDlvDistrictKeySet;
    }

    private void updateHjdong(List<MsCenterDlvDistrictReqDto> reqDtoList) {
        Set<String> keySet = reqDtoList.stream().map(MsCenterDlvDistrictReqDto::getSerialkey).collect(Collectors.toSet());
        Map<String, MsCenterDlvDistrictReqDto> updateMap = reqDtoList.stream().collect(Collectors.toMap(MsCenterDlvDistrictReqDto::getSerialkey, Function.identity()));
        List<MsCenterDlvDistrictHjdongResDto> resDtoList = commonDao.selectList(MS_DELIVERY_DISTRICT_NS + "getDlvDistrictHjdongListByKeySet",
            MsDistrictValidationReqDto.builder().serialkeySet(keySet).build());
        for (MsCenterDlvDistrictHjdongResDto resDto : resDtoList) {
            MsCenterDlvDistrictReqDto reqDto = updateMap.get(resDto.getDistrictSerialkey());
            if ("Y".equals(reqDto.getDelYn())) {
                resDto.setToDate(reqDto.getToDate());
                resDto.setFromDate(reqDto.getFromDate());
                resDto.setDelYn(reqDto.getDelYn());
                resDto.setEditDate(LocalDateTime.now().format(formatter));
                resDto.setEditWho(resDto.getGUserId());
                commonDao.update(MS_DELIVERY_DISTRICT_NS + "updateDlvDistrictHjdong", resDto);
            }
        }
    }

    @Override
    protected Object update(List<MsCenterDlvDistrictReqDto> updateList, Map<String, Object> sharedMap) {
        for (MsCenterDlvDistrictReqDto reqDto : updateList) {
            commonDao.update(MS_DELIVERY_DISTRICT_NS + "updateMasterList", reqDto);
        }
        updateHjdong(updateList);
        return updateList.stream().map(MsCenterDlvDistrictReqDto::getSerialkey).collect(Collectors.toSet());
    }

    @Override
    protected Object delete(List<MsCenterDlvDistrictReqDto> deleteList, Map<String, Object> sharedMap) {
        for (MsCenterDlvDistrictReqDto dto : deleteList) {
            commonDao.update(MS_DELIVERY_DISTRICT_NS + "expireDlvDistrict", dto);
        }
        updateHjdong(deleteList);
        return deleteList.stream().map(MsCenterDlvDistrictReqDto::getSerialkey).collect(Collectors.toSet());
    }

    @Override
    protected void history(List<MsCenterDlvDistrictReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> keySet = processResult.getAllSetAs();
        if (!CollectionUtils.isEmpty(keySet)) {
            commonDao.insert(MS_DELIVERY_DISTRICT_HISTORY_NS + "saveHistoryListByDlv",
                MsDistrictValidationReqDto.builder().serialkeySet(keySet).build());
        }
    }

    @Override
    protected void connect(List<MsCenterDlvDistrictReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> keySet = processResult.getSetKeys(true, true, true);
        if (!CollectionUtils.isEmpty(keySet)) {
            MsDistrictValidationReqDto validationReqDto = MsDistrictValidationReqDto.builder()
                .eventType(MsDistrictValidationReqDto.EventType.DLV).serialkeySet(keySet).build();
            insertIfAndMergeMsHjdongPop(validationReqDto);
        }
    }

    @Override
    protected void finish(List<MsCenterDlvDistrictReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        List<MsCenterDlvDistrictReqDto> list = reqDtoList.stream().filter(reqDto -> !Objects.equals(reqDto.getRowStatus(), CanalFrameConstants.INSERT)).toList();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        Set<String> setKeys = processResult.getSetKeys(true, true, true);
        if (!CollectionUtils.isEmpty(setKeys)) {
            commonDao.delete(MS_DELIVERY_DISTRICT_NS + "deleteFutureDlvDistrictHjdongByDlvKeySet",
                MsDistrictValidationReqDto.builder().serialkeySet(setKeys).build());
            commonDao.delete(MS_DELIVERY_DISTRICT_NS + "deleteFutureDlvDistrict",
                MsDistrictValidationReqDto.builder().serialkeySet(setKeys).build());
        }
        Set<String> dlvGroupIdSet = list.stream().collect(Collectors.groupingBy(MsCenterDlvDistrictReqDto::getDlvgroupId)).keySet();
        if (!CollectionUtils.isEmpty(dlvGroupIdSet)) {
            String dccode = reqDtoList.get(0).getDccode();
            for (String dlvgroupId : dlvGroupIdSet) {
                updateCenterDlvDistrictGroupPolygon(commonDao,
                        MsCenterDlvDistrictReqDto.builder().dccode(dccode).dlvgroupId(dlvgroupId).build());
            }
        }
        Set<String> dlvDistrictIdGroupSet = list.stream().collect(Collectors.groupingBy(MsCenterDlvDistrictReqDto::getDlvdistrictId)).keySet();
        if (!CollectionUtils.isEmpty(dlvDistrictIdGroupSet)) {
            String dccode = reqDtoList.get(0).getDccode();
            for (String dlvdistrictId : dlvDistrictIdGroupSet) {
                updateCenterDistrictPolygon(commonDao,
                        MsCenterDlvDistrictReqDto.builder().dccode(dccode).dlvdistrictId(dlvdistrictId).build());
            }
        }
    }
}
