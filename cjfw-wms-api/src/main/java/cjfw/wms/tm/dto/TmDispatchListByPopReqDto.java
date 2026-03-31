package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.core.model.Page;
import cjfw.wms.common.annotation.MultiSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "배차목록(POP별) 조회 요청 DTO")
public class TmDispatchListByPopReqDto extends Page<Object> {
    
    /**배송시작일자*/
    @Schema(description = "배송시작일자", required = true)
    private String deliverydtFrom;

    /**배송종료일자*/
    @Schema(description = "배송종료일자", required = true)
    private String deliverydtTo;

    /**센터코드*/
    @Schema(description = "센터코드", required = true)
    private String dccode;

    /**POP번호*/
    @Schema(description = "POP번호")
    private String popno;

    /** POP 번호 */
    @MultiSearch
    @Schema(description = "POP 번호-다중OR검색")
    private List<String> popnoMulti;

    /**차량번호 리스트*/
    @Schema(description = "차량번호 리스트")
    private List<String> carnoList;
}
