package cjfw.wms.ms.service.district;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsDeliveryDistrictGroupXPopReqDto;
import cjfw.wms.ms.dto.MsDeliveryDistrictGroupXPopValidateResDto;
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
public class MsDeliveryDistrictGroupPopProcessor extends AbstractDistrictProcessor<MsDeliveryDistrictGroupXPopReqDto.Pop> {
    public MsDeliveryDistrictGroupPopProcessor(CommonDao commonDao, UserContext userContext) {
        super(MsDeliveryDistrictGroupXPopReqDto.Pop.class, commonDao, userContext);
    }

    @Override
    protected List<MsDeliveryDistrictGroupXPopReqDto.Pop> doValidate(List<MsDeliveryDistrictGroupXPopReqDto.Pop> reqDtoList, List<MsDeliveryDistrictGroupXPopReqDto.Pop> insertList,
            List<MsDeliveryDistrictGroupXPopReqDto.Pop> updateList, List<MsDeliveryDistrictGroupXPopReqDto.Pop> deleteList, Map<String, Object> sharedMap) {
        validateRepPop(reqDtoList.get(0).getDccode());

        Set<String> exceptSerialkeySet = Stream.concat(updateList.stream(), deleteList.stream())
                .map(MsDeliveryDistrictGroupXPopReqDto.Pop::getSerialkey)
                .collect(Collectors.toSet());
        List<MsDeliveryDistrictGroupXPopReqDto.Pop> validateReqDtoList = Stream.of(insertList, updateList).flatMap(List::stream).toList();
        MsDistrictValidationReqDto validationReqDto = MsDistrictValidationReqDto.builder()
            .dccode(reqDtoList.get(0).getDccode()).dlvgroupId(reqDtoList.get(0).getDlvgroupId()).serialkeySet(exceptSerialkeySet).list(validateReqDtoList).build();
        MsDeliveryDistrictGroupXPopValidateResDto validateDateResDto = commonDao.selectOne("getDistrictGroupXPopDateConflictList", validationReqDto);
        if ("Y".equals(validateDateResDto.getDuplicateDateYn())) {
            throw new UserHandleException("MSG.COM.ERR.999", new String[]{"동일한 대표 POP 번호가 존재하여 저장할 수 없습니다."});
        }
        if ("Y".equals(validateDateResDto.getThreeExistYn())) {
            throw new UserHandleException("MSG.COM.ERR.999", new String[]{"대표 POP는 최대 3개까지만 선택할 수 있습니다."});
        }
//        MsDeliveryDistrictGroupXPopValidateResDto validateTimeResDto = commonDao.selectOne("getDistrictGroupXPopTimeConflictList", validationReqDto);
//        if ("Y".equals(validateTimeResDto.getDuplicateHourYn())) {
//            throw new UserHandleException("MSG.COM.ERR.999", new String[]{"대표 POP의 시작, 종료시간은 서로 겹칠 수 없습니다."});
//        }
        return reqDtoList;
    }

    @Override
    protected Object insert(List<MsDeliveryDistrictGroupXPopReqDto.Pop> insertList, Map<String, Object> sharedMap) {
        Set<String> saveDlvDistrictKeySet = keySetFromList(commonDao, "getSerialkeysFromHjdong", insertList.size());
        Iterator<String> it = saveDlvDistrictKeySet.iterator();
        for (MsDeliveryDistrictGroupXPopReqDto.Pop reqDto : insertList) {
            reqDto.setSerialkey(it.next());
            commonDao.insert(MS_DELIVERY_DISTRICT_GROUP_NS + "saveDlvDistrictGroupPop", reqDto);
        }
        return saveDlvDistrictKeySet;
    }

    @Override
    protected Object update(List<MsDeliveryDistrictGroupXPopReqDto.Pop> updateList, Map<String, Object> sharedMap) {
        for (MsDeliveryDistrictGroupXPopReqDto.Pop reqDto : updateList) {
            commonDao.insert(MS_DELIVERY_DISTRICT_GROUP_NS + "updateDlvDistrictGroupPop", reqDto);
        }
        return updateList.stream().map(MsDeliveryDistrictGroupXPopReqDto.Pop::getSerialkey).collect(Collectors.toSet());
    }

    @Override
    protected Object delete(List<MsDeliveryDistrictGroupXPopReqDto.Pop> deleteList, Map<String, Object> sharedMap) {
        for (MsDeliveryDistrictGroupXPopReqDto.Pop reqDto : deleteList) {
            commonDao.insert(MS_DELIVERY_DISTRICT_GROUP_NS + "updateDlvDistrictGroupPop", reqDto);
        }
        return deleteList.stream().map(MsDeliveryDistrictGroupXPopReqDto.Pop::getSerialkey).collect(Collectors.toSet());
    }

    @Override
    protected void history(List<MsDeliveryDistrictGroupXPopReqDto.Pop> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> keySet = processResult.getAllSetAs();
        if (!CollectionUtils.isEmpty(keySet)) {
            commonDao.insert(MS_DELIVERY_DISTRICT_HISTORY_NS + "saveHistoryListByDlvGroupPop", MsDistrictValidationReqDto.builder().serialkeySet(keySet).build());
        }
    }

    @Override
    protected void connect(List<MsDeliveryDistrictGroupXPopReqDto.Pop> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> keySet = processResult.getSetKeys(true, true, true);
        if (!CollectionUtils.isEmpty(keySet)) {
            MsDistrictValidationReqDto validationReqDto = MsDistrictValidationReqDto.builder()
                .eventType(MsDistrictValidationReqDto.EventType.DLV_POP).serialkeySet(keySet).build();
            insertIfAndMergeMsHjdongPop(validationReqDto);
        }
    }

    @Override
    protected void finish(List<MsDeliveryDistrictGroupXPopReqDto.Pop> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> keySet = processResult.getSetKeys(false, true, true);
        if (!CollectionUtils.isEmpty(keySet)) {
            commonDao.insert(MS_DELIVERY_DISTRICT_GROUP_NS + "deleteFutureDlvDistrictGroupPop", MsDistrictValidationReqDto.builder().serialkeySet(keySet).build());
        }
    }
}
