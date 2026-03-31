package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.08.08
 * @description : 권역별차량관리 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.08        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "권역별차량관리 조회 결과 DTO")
public class MsDistrictResDto {
	@Schema(description = "데이터 번호")
    private String serialKey;

    @Schema(description = "물류센터 코드")
    private String dcCode;

    @Schema(description = "POP 번호")
    private String districtCode;

    @Schema(description = "롤테이너 최대 개수")
    private BigDecimal rolltainerMax;

    @Schema(description = "진행 상태")
    private String status;

    @Schema(description = "삭제 여부")
    private String delYn;
    
    @Schema(description = "차량 ID")
    private String carId;

    @Schema(description = "차량 번호")
    private String carNo;

    @Schema(description = "롤테이너 시작 번호")
    private BigDecimal rolltainerStartNo;

    @Schema(description = "롤테이너 종료 번호")
    private BigDecimal rolltainerEndNo;
    
    @Schema(description = "배송권역")
    private String workPopNo;
    
    @Schema(description = "배송권역ID")
    private String dlvDistrictId;
    
    @Schema(description = "배송권역명")
    private String dlvDistrictNm;

    @Schema(description = "서브 차량 번호1")
    private String subCarNo1;

    @Schema(description = "서브 차량 번호2")
    private String subCarNo2;

    @Schema(description = "서브 차량 번호3")
    private String subCarNo3;

    @Schema(description = "등록일")
    private String addDate;

    @Schema(description = "수정일")
    private String editDate;
   
    @Schema(description = "등록자 ID")
    private String addWho;

    @Schema(description = "수정자 ID")
    private String editWho;

    @Schema(description = "등록자명")
    private String addWhoNm;

    @Schema(description = "수정자명")
    private String editWhoNm;
    
    @Schema(description = "커스텀 엑스트라 체크박스(없으면 전체 선택 해제 안 됨)", example = "N")
    private String customRowCheckYn = "N";
    
    /** 체크결과 */
	@Schema(description = "체크결과", example = "N")
	private String processYn;
	
	/** 체크메세지 */
	@Schema(description = "체크메세지", example = "존재하지 않는 거래처유형 코드입니다.")
	private String processMsg;
	
    @Schema(description = "슈트번호")
    private String chuteNo;
}
