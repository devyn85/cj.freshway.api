package cjfw.wms.ms.service.district;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsDistrictGroupPopReqDto;
import cjfw.wms.ms.dto.MsDistrictGroupPopResDto;
import cjfw.wms.ms.dto.MsDistrictValidationReqDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MsDeliveryDistrictRepPopProcessor extends AbstractDistrictProcessor<MsDistrictGroupPopReqDto> {
    public MsDeliveryDistrictRepPopProcessor(CommonDao commonDao, UserContext userContext) {
        super(MsDistrictGroupPopReqDto.class, commonDao, userContext);
    }

    @Override
    protected List<MsDistrictGroupPopReqDto> doValidate(List<MsDistrictGroupPopReqDto> reqDtoList,
            List<MsDistrictGroupPopReqDto> insertList, List<MsDistrictGroupPopReqDto> updateList, List<MsDistrictGroupPopReqDto> deleteList,
            Map<String, Object> sharedMap) {
        Set<String> exceptSerialkeySet = Stream.concat(updateList.stream(), deleteList.stream())
                        .map(MsDistrictGroupPopReqDto::getSerialkey)
                        .collect(Collectors.toSet());
        List<MsDistrictGroupPopReqDto> nonDeleteList = Stream.concat(insertList.stream(), updateList.stream()).toList();
        List<MsDistrictGroupPopResDto> conflictList = commonDao.selectList(MS_DELIVERY_DISTRICT_REPPOP_NS + "getPopConflictList",
                MsDistrictValidationReqDto.builder().dccode(reqDtoList.get(0).getDccode()).serialkeySet(exceptSerialkeySet).list(nonDeleteList).build());
        if (!conflictList.isEmpty()) {
            for (MsDistrictGroupPopResDto resDto : conflictList) {
                if ("POPNO".equalsIgnoreCase(resDto.getDupType())) {
                    String format = String.format("센터코드=%s 에 대해 popNo '%s' 가 중복되었습니다.", resDto.getDccode(), resDto.getPopNo());
                    throw new UserHandleException("MSG.COM.ERR.999", new String[]{format});
                }
                if ("BASE_YN".equalsIgnoreCase(resDto.getDupType())) {
                    String format = String.format("센터코드=%s 에 대해 baseYn=Y 는 1건만 가능합니다.", resDto.getDccode());
                    throw new UserHandleException("MSG.COM.ERR.999", new String[]{format});
                }
            }
        }
        return reqDtoList;
    }

    @Override
    protected Object insert(List<MsDistrictGroupPopReqDto> insertList, Map<String, Object> sharedMap) {
        Set<String> saveKeySet = keySetFromList(commonDao, MS_DELIVERY_DISTRICT_REPPOP_NS + "getSerialkeysFromDistrictgroupPop", insertList.size());
        Iterator<String> it = saveKeySet.iterator();
        for (MsDistrictGroupPopReqDto reqDto : insertList) {
            reqDto.setSerialkey(it.next());
            commonDao.insert(MS_DELIVERY_DISTRICT_REPPOP_NS + "saveDistrictgroupPop", reqDto);
        }
        return saveKeySet;
    }

    @Override
    protected Object update(List<MsDistrictGroupPopReqDto> updateList, Map<String, Object> sharedMap) {
        for (MsDistrictGroupPopReqDto reqDto : updateList) {
            commonDao.update(MS_DELIVERY_DISTRICT_REPPOP_NS + "updateDistrictGroupPop", reqDto);
        }
        return updateList.stream().map(MsDistrictGroupPopReqDto::getSerialkey).collect(Collectors.toSet());
    }

    @Override
    protected Object delete(List<MsDistrictGroupPopReqDto> deleteList, Map<String, Object> sharedMap) {
        for (MsDistrictGroupPopReqDto reqDto : deleteList) {
            commonDao.update(MS_DELIVERY_DISTRICT_REPPOP_NS + "expireDistrictGroupPopByKey", reqDto);
        }
        return deleteList.stream().map(MsDistrictGroupPopReqDto::getSerialkey).collect(Collectors.toSet());
    }

    @Override
    protected void history(List<MsDistrictGroupPopReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> keySet = processResult.getAllSetAs();
        commonDao.insert(MS_DELIVERY_DISTRICT_HISTORY_NS + "saveHistoryListByPop", MsDistrictValidationReqDto.builder().serialkeySet(keySet).build());
    }

    @Override
    protected void connect(List<MsDistrictGroupPopReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> keySet = processResult.getAllSetAs();
        MsDistrictValidationReqDto validationReqDto = MsDistrictValidationReqDto.builder()
            .eventType(MsDistrictValidationReqDto.EventType.POP).serialkeySet(keySet).build();
        insertIfAndMergeMsHjdongPop(validationReqDto);
    }

    @Override
    protected void finish(List<MsDistrictGroupPopReqDto> reqDtoList, ProcessResult processResult, Map<String, Object> sharedMap) {
        Set<String> keySet = processResult.getSetKeys(false, true, true);
        if (CollectionUtils.isNotEmpty(keySet)) {
            commonDao.delete("deleteDistrictGroupPopBySerialKeySet", MsDistrictValidationReqDto.builder().serialkeySet(keySet).build());
        }
    }
}
