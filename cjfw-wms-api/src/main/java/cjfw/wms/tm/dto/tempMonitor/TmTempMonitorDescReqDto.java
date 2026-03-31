package cjfw.wms.tm.dto.tempMonitor;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.01.27
 * @description : 온도 모니터링 상세 조회 요청 DTO
 * @issues :
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.27 System Generated 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "온도 모니터링 상세 조회 요청 DTO")
public class TmTempMonitorDescReqDto extends CommonDto {

    @Schema(description = "센터코드", example = "2600")
    private String dccode;

    @Schema(description = "시작배송일자", example = "20241202")
    private String fromDeliverydt;

    @Schema(description = "종료배송일자", example = "20241202")
    private String toDeliverydt;

    @Schema(description = "온도상태코드", example = "")
    private String tempStatus; // TODO:khd 온도상태코드 정상,이탈,확인불가 정의필요

    @Schema(description = "기준 차량(CAR), 거래처(CUST)", example = "CUST")
    private String base;

    @Schema(description = "회차", example = "1")
    private String priority;

    @Schema(description = "시간단위(1분/5분/10분/30분/60분)", example = "10")
    private int timeUnit;

    @Schema(description = "온도기록지 차량별 데이터 나누기 기준", example = "22")
    private int divUnit;

    @Schema(description = "차량번호 리스트")
    private List<String> carnoList;

    @Schema(description = "거래처코드 리스트")
    private List<String> custkeyList;

    @Schema(description = "거래처코드", example = "")
    private String custkey;

    @Schema(description = "출/도착건만 보기", example = "N")
    private String depArrYn;

    @Schema(description = "센터 포함 보기", example = "N")
    private String dcIncYn;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExclTempLogKey {
        @Schema(description = "차량번호 또는 이름", example = "김운전")
        private String carno;
        @Schema(description = "운행일시", example = "2025-10-01 19:15")
        private String workdate;

        @Override
        public boolean equals(Object o) {
            if (carno == null || workdate == null) return false;
            if (o == null) return false;
            if (this == o) return true;
            if (!(o instanceof ExclTempLogKey tempLogKey)) return false;
            return this.carno.equals(tempLogKey.carno) && this.workdate.equals(tempLogKey.workdate);
        }
    }
}
