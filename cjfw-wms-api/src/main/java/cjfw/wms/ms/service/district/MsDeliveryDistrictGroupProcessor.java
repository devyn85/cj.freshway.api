package cjfw.wms.ms.service.district;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsCenterDistrictDcOrdGrpReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictReqDto;
import cjfw.wms.ms.dto.MsDistrictValidationReqDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MsDeliveryDistrictGroupProcessor extends AbstractDistrictProcessor<MsCenterDlvDistrictGroupReqDto> {
    public MsDeliveryDistrictGroupProcessor(CommonDao commonDao, UserContext userContext) {
        super(MsCenterDlvDistrictGroupReqDto.class, commonDao, userContext);
    }

    @Override
    protected List<MsCenterDlvDistrictGroupReqDto> doValidate(
            List<MsCenterDlvDistrictGroupReqDto> reqDtoList, List<MsCenterDlvDistrictGroupReqDto> insertList, List<MsCenterDlvDistrictGroupReqDto> updateList,
            List<MsCenterDlvDistrictGroupReqDto> deleteList, Map<String, Object> sharedMap) {
        validateRepPop(reqDtoList.get(0).getDccode());

        if (!insertList.isEmpty()) {
            int countDlvgroupNm = commonDao.selectOne(MS_DELIVERY_DISTRICT_GROUP_NS + "countDlvgroupNm",
                MsDistrictValidationReqDto.builder().dataSet(insertList.stream().map(MsCenterDlvDistrictGroupReqDto::getDlvgroupNm).collect(Collectors.toSet())).build());
            if (countDlvgroupNm > 0) {
                throw new UserHandleException("MSG.COM.ERR.999", new String[] {
                    "권역그룹명이 중복입니다."
                });
            }
        }
        List<MsCenterDlvDistrictGroupReqDto> targetList = Stream.concat(updateList.stream(), deleteList.stream()).toList();
        if (!targetList.isEmpty()) {
            Set<String> keySet = targetList.stream().map(MsCenterDlvDistrictGroupReqDto::getSerialkey).collect(Collectors.toSet());
            int count = commonDao.selectOne(MS_DELIVERY_DISTRICT_GROUP_NS + "getIsUseDistrictGroupByKeySet",
                    MsDistrictValidationReqDto.builder().serialkeySet(keySet).build());
            if(count > 0) {
                throw new UserHandleException("MSG.COM.ERR.999", new String[] {"권역에 해당 권역 그룹이 설정되어 있어 삭제할 수 없습니다."});
            }
        }
        return reqDtoList;
    }

    @Override
    protected Object insert(List<MsCenterDlvDistrictGroupReqDto> insertList, Map<String, Object> sharedMap) {
        Set<String> saveKeySet = keySetFromList(commonDao, MS_DELIVERY_DISTRICT_GROUP_NS + "getSerialkeysFromDistrictgroup", insertList.size());
        Iterator<String> it = saveKeySet.iterator();
        for (MsCenterDlvDistrictGroupReqDto reqDto : insertList) {
            reqDto.setSerialkey(it.next());
            commonDao.insert(MS_DELIVERY_DISTRICT_GROUP_NS + "saveMaster", reqDto);
        }
        return saveKeySet;
    }

    @Override
    protected Object update(List<MsCenterDlvDistrictGroupReqDto> updateList, Map<String, Object> sharedMap) {
        for (MsCenterDlvDistrictGroupReqDto reqDto : updateList) {
            commonDao.update(MS_DELIVERY_DISTRICT_GROUP_NS + "updateMaster", reqDto);
        }
        return updateList.stream().map(MsCenterDlvDistrictGroupReqDto::getSerialkey).collect(Collectors.toSet());
    }

    @Override
    protected Object delete(List<MsCenterDlvDistrictGroupReqDto> deleteList, Map<String, Object> sharedMap) {
        for (MsCenterDlvDistrictGroupReqDto reqDto : deleteList) {
            commonDao.update(MS_DELIVERY_DISTRICT_GROUP_NS + "expireDistrictgroup", reqDto);
        }
        return deleteList.stream().map(MsCenterDlvDistrictGroupReqDto::getSerialkey).collect(Collectors.toSet());
    }

    @Override
    protected void history(List<MsCenterDlvDistrictGroupReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> keySet = processResult.getAllSetAs();
        if (!CollectionUtils.isEmpty(keySet)) {
            commonDao.insert(MS_DELIVERY_DISTRICT_HISTORY_NS + "saveHistoryListByDlvGroup", MsDistrictValidationReqDto.builder().serialkeySet(keySet).build());
        }
    }

    @Override
    protected void connect(List<MsCenterDlvDistrictGroupReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> keySet = processResult.getSetKeys(true, true, true);
        if (!CollectionUtils.isEmpty(keySet)) {
            MsDistrictValidationReqDto validationReqDto = MsDistrictValidationReqDto.builder()
                .eventType(MsDistrictValidationReqDto.EventType.DLV_GROUP).serialkeySet(keySet).build();
            insertIfAndMergeMsHjdongPop(validationReqDto);
        }
    }

    /**
     * 이력, 연계 후 미래데이터 삭제
     */
    @Override
    protected void finish(List<MsCenterDlvDistrictGroupReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> keySet = processResult.getSetKeys(false, true, true);
        if (!CollectionUtils.isEmpty(keySet)) {
            commonDao.delete(MS_DELIVERY_DISTRICT_GROUP_NS + "deleteFutureDistrictgroup",
                    MsDistrictValidationReqDto.builder().serialkeySet(keySet).build());
        }
    }

}
