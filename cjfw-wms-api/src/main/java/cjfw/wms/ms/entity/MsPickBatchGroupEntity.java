package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.23
 * @description : 피킹배치그룹 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "피킹배치그룹 Entity") 
public class MsPickBatchGroupEntity extends CommonDto {	
	@Schema(description = "일련번호 (고유 키)", example = "123456789012345")
    private String serialKey;

    @Schema(description = "DC 코드", example = "DC001")
    private String dcCode;

    @Schema(description = "플랜트", example = "PLANT01")
    private String plant;

    @Schema(description = "저장 타입", example = "BULK")
    private String storageType;

    @Schema(description = "거리 타입", example = "STD")
    private String distanceType;

    @Schema(description = "배치 그룹", example = "BATCHGR001")
    private String batchGroup;

    @Schema(description = "설명", example = "표준 피킹 배치 그룹")
    private String description;

    @Schema(description = "상태", example = "00")
    private String status;

    @Schema(description = "삭제 여부 (Y/N)", example = "N")
    private String delYn;

    @Schema(description = "트래픽 코프", example = "NORMAL")
    private String trafficCop;

    @Schema(description = "아카이브 코프", example = "N")
    private String archiveCop;

    @Schema(description = "조직", example = "ORG01")
    private String organize;

    @Schema(description = "지역", example = "AREA01")
    private String area;

    @Schema(description = "주문 타입", example = "SALE")
    private String orderType;

    @Schema(description = "기타 코드 1", example = "ETC_VAL1")
    private String etcCode1;

    @Schema(description = "기타 코드 2", example = "ETC_VAL2")
    private String etcCode2;

    @Schema(description = "기타 코드 3", example = "ETC_VAL3")
    private String etcCode3;

    @Schema(description = "기타 코드 4", example = "ETC_VAL4")
    private String etcCode4;

    @Schema(description = "추가 일시", example = "2023-01-01T10:00:00")
    private String addDate;

    @Schema(description = "수정 일시", example = "2023-01-01T10:00:00")
    private String editDate;
    
    @Schema(description = "추가자", example = "SYSTEM")
    private String addWho;

    @Schema(description = "수정자", example = "SYSTEM")
    private String editWho;
    
    @Schema(description = "분배단위")
    private String alloUom;
    
    @Schema(description = "소비기한")
    private String usebyDateFreeRt;
    
    /** 프로시저 실행 성공여부 */
	@Schema(description = "프로시저 실행 성공여부", example = "")
	private Integer success;
	
	/** 프로시저 실행 에러코드 */
	@Schema(description = "프로시저 실행 에러코드", example = "")
	private Integer err;
	
	/** 프로시저 실행 에러메시지 */
	@Schema(description = "프로시저 실행 에러메시지", example = "")
	private String errmsg;
}
