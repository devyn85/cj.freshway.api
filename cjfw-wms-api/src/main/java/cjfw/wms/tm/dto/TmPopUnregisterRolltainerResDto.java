package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.02
 * @description : 거래처별 배송이력 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "차량별 롤테이너별 배송 조회 결과")
public class TmPopUnregisterRolltainerResDto {

	/** row 번호 */
    @Schema(description = "row 번호", nullable = false, example = "")
    private Integer rowcnt;

    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;

    /** 고객사 */
    @Schema(description = "고객사", nullable = false, example = "")
    private String storerkey;
    
    /** POP */
    @Schema(description = "POP", nullable = false, example = "")
    private String deliverygroup;
    
    /** 롤테이너 */
    @Schema(description = "롤테이너", nullable = false, example = "")
    private String rolltainerNo;
    
    /** 차량번호 */
    @Schema(description = "차량번호", nullable = false, example = "")
    private String carno;

    /** 일평균물량 */
    @Schema(description = "일평균물량(kg)", nullable = false, example = "")
    private java.math.BigDecimal avgWeight;
    
    /** 일평균체적 */
    @Schema(description = "일평균체적(m3)", nullable = false, example = "")
    private java.math.BigDecimal avgCube;
    
    /** 일평균고객(건) */
    @Schema(description = "일평균고객(건)", nullable = false, example = "")
    private java.math.BigDecimal avgDeliveryCnt;   
    
    /** 기준체적 */
    @Schema(description = "기준체적", nullable = false, example = "")
    private java.math.BigDecimal stdCube;

    /** 중량율 */
    @Schema(description = "중량율", nullable = false, example = "")
    private java.math.BigDecimal rateWeight;
    
    /** 체적율 */
    @Schema(description = "체적율", nullable = false, example = "")
    private java.math.BigDecimal rateCube;
    
    /** 롤테이너 중량 */
    @Schema(description = "롤테이너 중량", nullable = false, example = "")
    private java.math.BigDecimal rolltainerWeight;
    
    /** 롤테이너 체적 */
    @Schema(description = "롤테이너 체적", nullable = false, example = "")
    private java.math.BigDecimal rolltainerCube;
}
