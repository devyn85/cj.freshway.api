package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "배차목록(POP별) 조회 응답 DTO")
public class TmDispatchListByPopResDto{
    
    /**센터코드*/
    @Schema(description = "센터코드")
    private String dccode;

    /**센터명*/
    @Schema(description = "센터명")
    private String dcname;

    /**배송일자*/
    @Schema(description = "배송일자 (YYYY-MM-DD)")
    private String deliverydt;

    /**대표POP번호*/
    @Schema(description = "기본 대표POP")
    private String basePopno;

    /**POP번호*/
    @Schema(description = "POP번호")
    private String popno;

    /**롤테이너번호*/
    @Schema(description = "롤테이너번호")
    private String rolltainerNo;

    /**차량번호*/
    @Schema(description = "차량번호")
    private String carno;

    /**거래처수(실착지수)*/
    @Schema(description = "거래처수(실착지수) - 중복제거 COUNT")
    private Integer truthcustCount;

    /**물량*/
    @Schema(description = "물량 (SUM)")
    private String weight;

    /**CBM*/
    @Schema(description = "CBM (SUM)")
    private String cube;
}
