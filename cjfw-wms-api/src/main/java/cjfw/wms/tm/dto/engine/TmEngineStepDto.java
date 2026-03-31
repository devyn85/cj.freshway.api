package cjfw.wms.tm.dto.engine;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cjfw.core.utility.StringUtil;
import cjfw.wms.tm.dto.TmSplitItemDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Engine 경로 단계 정보")
public class TmEngineStepDto {

	/** 단계 타입 */
	@Schema(description = "단계 타입 (start, job, end)")
	private String type;

	/** 유니크 아이디 */
	@Schema(description = "식별용 아이디")
	private String uniqueId;
	
	/** 도착 시간 */
	@Schema(description = "도착 시간 (초)")
	private String arrival;

	/** 소요 시간 */
	@Schema(description = "소요 시간 (초)")
	private String duration;
	
	/** 소요 시간 */
	@Schema(description = "단계별 소요 시간 (초)")
	private String stepDuration;

	/** 설정 시간 */
	@Schema(description = "설정 시간 (초)")
	private Integer setup;

	/** 서비스 시간 */
	@Schema(description = "서비스 시간 (초)")
	private Integer service;

	/** 대기 시간 */
	@Schema(description = "대기 시간 (초)")
	private Integer waitingTime;

	/** 위반 사항 */
	@Schema(description = "제약 조건 위반 사항")
	private List<Object> violations;

	/** 위치 좌표 */
	@Schema(description = "위치 좌표 [경도, 위도]")
	private List<Double> location;

	/** 거리 */
	@Schema(description = "누적 이동 거리")
	private String distance;
	
	@Schema(description = "단계별 이동 거리")
	private String stepDistance;

	/** ID */
	@Schema(description = "작업/주문 ID")
	private String id;

	/** 위치 인덱스 */
	@Schema(description = "위치 인덱스")
	private Integer locationIdx;
	
	/** 예상 도착시간 정보 HH:MM */
	@Schema(description = "예상 도착시간")
	private String expectedArrivalTime;

	/** 설명 */
	@Schema(description = "단계 설명")
	private String description;

	@Schema(description = "고객사코드")
	private String storerkey;

	@Schema(description = "거래처유형")
	private String custType;

	@Schema(description = "고객처명", example = "고객")
	private String custName;

	@Schema(description = "대면검수 YN", example = "Y")
	private String faceInspect;

	@Schema(description = "특수조건 YN", example = "Y")
	private String specialConditionYn;

	@Schema(description = "클레임 YN", example = "Y")
	private String claimYn;

	@Schema(description = "OTD 시작시간", example = "08:00")
	private String reqdlvtime1From;

	@Schema(description = "OTD 종료시간", example = "10:00")
	private String reqdlvtime1To;

	@Schema(description = "키 유형", example = "비밀번호")
	private String keyCustType;

	@Schema(description = "본POP [TODO]", example = "pop1")
	private String defDistrictCode;

	@Schema(description = "고정 차량")
	private String defCarno;

	@Schema(description = "현재 배차된 차량")
	private String carno;

	@Schema(description = "반품여부 [TODO]", example = "car001")
	private String returnYn;
	
	@Schema(description = "실착지 주소", example = "강남")
	private String custAddress;

	/** 지오메트리 */
	@Schema(description = "단계 지오메트리 (CJ 전처리 서버에서 제공하는 형태, 주로 WKT Point)", example = "POINT(37.566500 126.978000)", nullable = true)
	private String geometry;
	
	@Schema(description = "체적", example = "10")
	private String cube;
	
	@Schema(description = "중량", example = "10")
	private String weight;
	
	@Schema(description = "수량", example = "10")
	private String orderQty;
	
	@Schema(description = "배송유형", example = "1")
	private String tmDeliveryType;
	
	@Schema(description = "전표일자")
	private String slipdt;
	
	@Schema(description = "전표번호")
	private String slipno;
	
	@Schema(description = "전표라인")
	private String slipline;
	
	@Schema(description = "분할배송 Y/N", example = "N")
	private String splitDeliveryYn;
	
	@Schema(description = "분할배송 번호", example = "0")
	private String splitDeliverySeq;
	
	@Schema(description = "회차 번호", example = "1")
    @Builder.Default
	private int roundSeq = 1;

    @Schema(description = "로케이션경도")
    private Double longitude;

    @Schema(description = "로케이션위도")
    private Double latitude;

    @Schema(description = "비밀번호유형")
    private String passwordType;

    @Schema(description = "비밀번호유형 코드")
    private String passwordTypeCd;

    @Schema(description = "현재 배차된 POP")
    private String pop;

    @Schema(description = "고정 POP")
    private String defPop;

    @Schema(description = "대표 POP (고객 마스터 기본 POP)")
    private String basePop;

    @Schema(description = "분할배송 상품 목록")
    private List<TmSplitItemDto> splitItems;

    @Schema(description = "작업 완료 시간 (epoch seconds) - 도착시간 + service 시간")
    private String completeTime;

	public void createUniqueId() {
		if(!"start".equals(this.type) && !"end".equals(this.type)) {
			this.uniqueId = this.id + "-" + this.type + "-" + StringUtil.nvl(this.slipline, "null") + "-" + StringUtil.nvl(this.slipno, "null") + "-" + StringUtil.nvl(this.splitDeliverySeq, "null");
		}
	}

}
