package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.23
 * @description : 피킹그룹정보 조회 조건 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "피킹그룹정보 조회 조건 DTO")
public class MsPickBatchGroupReqDto extends CommonDto {
	
	@Schema(description = "테이블 시리얼 번호")
    private String serialKey;

    @Schema(description = "멀티 물류센터 코드", example = "2600")
    private String multiDcCode[];
    
    @Schema(description = "물류센터 코드", example = "2600")
    private String dcCode;

    @Schema(description = "플랜트 코드 및 설명")
    private String plant;

    @Schema(description = "저장 타입")
    private String storageType;

    @Schema(description = "거리 유형")
    private String distanceType;

    @Schema(description = "배치 그룹")
    private String batchGroup;

    @Schema(description = "설명")
    private String description;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "최초 등록 시간", example = "YYYYMMDDHH24MISS")
    private String addDate;

    @Schema(description = "최종 변경 시간", example = "YYYYMMDDHH24MISS")
    private String editDate;

    @Schema(description = "최초 등록자")
    private String addWho;

    @Schema(description = "최종 변경자")
    private String editWho;

    @Schema(description = "조직 코드")
    private String organize;

    @Schema(description = "지역 코드")
    private String area;

    @Schema(description = "주문 유형")
    private String orderType;

    @Schema(description = "기타 코드1")
    private String etcCode1;

    @Schema(description = "기타 코드2")
    private String etcCode2;

    @Schema(description = "기타 코드3")
    private String etcCode3;

    @Schema(description = "기타 코드4")
    private String etcCode4;
    
    @Schema(description = "분배단위")
    private String alloUom;
    
    @Schema(description = "소비기한")
    private String usebyDateFreeRt;
}
