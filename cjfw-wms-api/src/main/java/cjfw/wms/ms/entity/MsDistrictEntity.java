package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonTriggerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.08.08
 * @description : 권역별차량관리 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.08        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "권역별차량관리 Entity") 
public class MsDistrictEntity extends CommonTriggerDto {	
	@Schema(description = "일련번호 (고유 키)", example = "123456789012345")
    private String serialKey;

    @Schema(description = "센터코드", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 14)
    private String dcCode;

    @Schema(description = "권역구분", defaultValue = "STD", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 10)
    private String districtType = "STD";

    @Schema(description = "권역그룹", defaultValue = "STD", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 30)
    private String districtGroup = "STD";

    @Schema(description = "권역코드", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 30)
    private String districtCode;

    @Schema(description = "권역명", maxLength = 100)
    private String districtName;

    @Schema(description = "우선순위", defaultValue = "1", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 30)
    private String priority = "1";

    @Schema(description = "거리 유형", defaultValue = "STD", maxLength = 10)
    private String distanceType = "STD";

    @Schema(description = "공통 권역구분", maxLength = 10)
    private String cmDistrictType;

    @Schema(description = "공통 권역그룹", maxLength = 20)
    private String cmDistrictGroup;

    @Schema(description = "공통 권역코드", maxLength = 20)
    private String cmDistrictCode;

    @Schema(description = "우편번호", maxLength = 6)
    private String zipCode;

    @Schema(description = "고객사코드", maxLength = 20)
    private String storerKey;

    @Schema(description = "거래처코드", maxLength = 20)
    private String custKey;

    @Schema(description = "운송사코드", maxLength = 30)
    private String carAgentKey;

    @Schema(description = "차량번호", maxLength = 40)
    private String carNo;

    @Schema(description = "기사명", maxLength = 35)
    private String driver;

    @Schema(description = "보조차량번호1", maxLength = 20)
    private String subCarNo1;

    @Schema(description = "보조차량번호2", maxLength = 20)
    private String subCarNo2;

    @Schema(description = "보조차량번호3", maxLength = 20)
    private String subCarNo3;

    @Schema(description = "게이트번호", maxLength = 10)
    private String gateNo;

    @Schema(description = "도크번호", maxLength = 20)
    private String dockNo;

    @Schema(description = "팝번호", maxLength = 50)
    private String workPopNo;

    @Schema(description = "기타 필드 1", maxLength = 100)
    private String other01;

    @Schema(description = "기타 필드 2", maxLength = 100)
    private String other02;

    @Schema(description = "기타 필드 3", maxLength = 100)
    private String other03;

    @Schema(description = "메모", maxLength = 500)
    private String memo;

    @Schema(description = "상태", defaultValue = "00", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 10)
    private String status = "00";

    @Schema(description = "삭제여부", defaultValue = "N", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 1)
    private String delYn = "N";

    @Schema(description = "데이터흐름제어", maxLength = 10)
    private String trafficCop;

    @Schema(description = "아카이브제어", maxLength = 1)
    private String archiveCop;

    @Schema(description = "최초등록시간 (자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private String addDate;

    @Schema(description = "최종변경시간 (자동 갱신)", accessMode = Schema.AccessMode.READ_ONLY)
    private String editDate;

    @Schema(description = "최초등록자", defaultValue = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 24)
    private String addWho = "SYSTEM";

    @Schema(description = "최종변경자", defaultValue = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 24)
    private String editWho = "SYSTEM";
    
    @Schema(description = "롤테이너(최대)")
    private String rolltainerMax;
    
    @Schema(description = "롤테이너번호FROM")
    private String rolltainerStartNo;
    
    @Schema(description = "롤테이너번호TO")
    private String rolltainerEndNo;
    
    @Schema(description = "슈트번호")
    private String chuteNo;
}
