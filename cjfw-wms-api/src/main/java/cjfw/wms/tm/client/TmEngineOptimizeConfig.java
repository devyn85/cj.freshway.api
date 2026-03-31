package cjfw.wms.tm.client;

import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.dto.engine.TmEngineOptionsDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties("tm.engine.optimize")
@Configuration
public class TmEngineOptimizeConfig {

    @Value("${tm.engine.optimize.level:standard}")
    private String optimizationLevel;       // ("fast"|"standard"|"thorough"): 최적화 수준(속도 vs 품질 트레이드오프)

    @Value("${tm.engine.optimize.priority_weight:1.5}")
    private double priorityWeight;           // 우선순위 요소 가중치

    @Value("${tm.engine.optimize.distance_weight:1}")
    private double distanceWeight;           // 거리 요소 가중치

    @Value("${tm.engine.optimize.time_weight:1.2}")
    private double timeWeight;               // 시간(소요/제약) 요소 가중치


    public TmEngineOptionsDto getDefaultOptions() {
        return TmEngineOptionsDto.getDefault();
    }

    public TmEngineOptionsDto getOptimizeOption(TmPlanOption planOption) {
        TmEngineOptionsDto options = getDefaultOptions();

        options.getMulti_trip().setEnabled(planOption.isOnMultiTurn());
        options.setOptimization_level(this.optimizationLevel);
        options.setPriority_weight(this.priorityWeight);
        options.setDistance_weight(this.distanceWeight);
        options.setTime_weight(this.timeWeight);
        if (planOption.isOnMaxPopCount()) {
            options.setPop_grouping_enabled(true);
            options.setMax_locations_per_pop(planOption.getPopCount());
        }

        return options;
    }
}
