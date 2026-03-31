package cjfw.wms.tm.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.util.TmPlanCommon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import static cjfw.wms.tm.constant.TmConstant.TM_DELIVERY_TYPE_DELIVERY;
import static cjfw.wms.tm.constant.TmConstant.TM_DELIVERY_TYPE_RETURN;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.24 
 * @description : 차량 배차 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.24 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "차량 배차 요청 DTO")
public class TmPlanEtaStepReqDto extends CommonDto {

	@Schema(description = "작업 유형 (delivery/pickup)")
	private String type;
	
	@Schema(description = "작업/주문 ID")
	private String id;

	@Schema(description = "고객사코드")
	private String storerkey;
	
	@Schema(description = "거래처유형")
	private String custType;
	
	@Schema(description = "거래처유형")
	private String custCode;
	
	@Schema(description = "배송유형 1:배송 2:반품")
	private String tmDeliveryType;
	
	@Schema(description = "위치")
	private List<Double> location;
	
	@Schema(description = "체적")
	private String cube;
	
	@Schema(description = "중량")
	private String weight;
	
	@Schema(description = "수량")
	private String orderQty;
	
	@Schema(description = "거리", nullable = true)
	private String distance;
	
	@Schema(description = "소요시간", nullable = true)
	private String duration;
	
	@Schema(description = "도착시간", nullable = true)
	private String arrival;
	
	@Schema(description = "폴리라인", nullable = true)
	private String geometry;
	
	@Schema(description = "전표라인", nullable = true)
	private String slipline;
	
	@Schema(description = "전표일자", nullable = true)
	private String slipdt;
	
	@Schema(description = "전표번호", nullable = true)
	private String slipno;
	
	@Schema(description = "분할배송 Y/N")
	private String splitDeliveryYn;
	
	@Schema(description = "분할배송 번호")
	private String splitDeliverySeq;
	
	@Schema(description = "회차 번호")
	private Integer roundSeq;

	@Schema(description = "분할배송 상품 목록")
	private List<TmSplitItemDto> splitItems;

    public List<Integer> getAmount(TmPlanOption planOption) {

        List<Integer> capacity = new ArrayList<>();

        // 배차옵션 최대적재중량
        if (planOption.isOnMaxCbm()) {
            if (TM_DELIVERY_TYPE_RETURN.equals(tmDeliveryType))
                capacity.add(0);
            else
                capacity.add(TmPlanCommon.toIntMin1(cube, 1));
        }

        if (TM_DELIVERY_TYPE_RETURN.equals(tmDeliveryType))
            capacity.add(0);
        else
            capacity.add(TmPlanCommon.toIntMin1(weight, 1));

        // 배차 옵션 최대 착지수 설정시 차량에 설정된 최대착지수 설정하고 OFF 인경우 제한 없음
        if (planOption.isOnMaxLocation())
            capacity.add(1);

        return capacity;
    }

    public String getIdWithRoundSeq() {
        return id + "-" + (ObjectUtils.isEmpty(roundSeq) ? 1 : roundSeq);
    }
}
