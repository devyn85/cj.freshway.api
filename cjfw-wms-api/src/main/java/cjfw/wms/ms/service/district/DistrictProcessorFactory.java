package cjfw.wms.ms.service.district;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.UserContext;
import cjfw.wms.common.extend.CommonDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cjfw.core.common.CanalFrameConstants.ERROR_MESSAGE;

@Component
public class DistrictProcessorFactory<T extends CommonDto> {
    private final Map<Type, AbstractDistrictProcessor<T>> processorMap = new HashMap<>();

    public DistrictProcessorFactory(List<AbstractDistrictProcessor<T>> processors, UserContext userContext) {
        for (AbstractDistrictProcessor<T> processor : processors) {
            processorMap.put(processor.getType(), processor);
        }
    }

    public String saveMasterList(List<T> reqDtoList) {
        if (CollectionUtils.isEmpty(reqDtoList)) {
            return CanalFrameConstants.MSG_COM_SUC_CODE;
        }
        Map<String, Object> sharedMap = new HashMap<>();
        AbstractDistrictProcessor<T> processor = getProcessor(reqDtoList.get(0));
        processor.process(reqDtoList, sharedMap);
        String errorMsg = (String) sharedMap.get(ERROR_MESSAGE);
        if (StringUtils.hasText(errorMsg)) {
            return errorMsg;
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    public AbstractDistrictProcessor<T> getProcessor(T dto) {
        AbstractDistrictProcessor<T> processor = processorMap.get(dto.getClass());
        if (processor == null) {
            throw new IllegalArgumentException("Processor not found for " + dto.getClass());
        }
        return processor;
    }
}
