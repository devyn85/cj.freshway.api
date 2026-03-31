package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.18 
 * @description : 거래처별POP관리 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsPopMngResDto {

	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "")
	private String serialkey;
	
	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String dccode;
	
	/** 고객코드 */
	@Schema(description = "고객코드", example = "")
	private String storerkey;
	
	/** 거래처유형 */
	@Schema(description = "거래처유형", example = "")
	private String custtype;
	
	/** 거래처코드 */
	@Schema(description = "거래처코드", example = "")
	private String custkey;
	
	/** 거래처명 */
	@Schema(description = "거래처명", example = "")
	private String custname;
	
	/** 적용시작일자 */
	@Schema(description = "적용시작일자", example = "")
	private String fromdate;
	
	/** 적용종료일자 */
	@Schema(description = "적용종료일자", example = "")
	private String todate;
	
	/** POP 유형 */
	@Schema(description = "POP 유형", example = "")
	private String poptype;

	/** POP 번호 */
	@Schema(description = "POP 번호", example = "")
	private String popno;

	/** 롤테이너 번호 */
	@Schema(description = "롤테이너 번호", example = "")
	private String rolltainerNo;
	
	/** BCR 사용 여부 */
	@Schema(description = "BCR 사용 여부", example = "")
	private String bcrYn;
	
	/** 차량번호 */
	@Schema(description = "차량번호", example = "")
	private String carno;
	
	/** 거래처그룹 */
	@Schema(description = "거래처그룹", example = "")
	private String custgroup;
	
	/** 저장 여부 */
	@Schema(description = "저장 여부", example = "")
	private String saved;
	
	/** 시간 유형 */
	@Schema(description = "시간 유형", example = "")
	private String timeFlag;

	/** 라벨출력 옵션 */
	@Schema(description = "라벨출력 옵션", example = "")
	private String labelstrategy;
	
	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "")
	private String delYn;
	
	/** 프로세스 진행 여부 */
	@Schema(description = "프로세스 진행 여부", example = "")
	private String processYn;
	
	/** 프로세스 메시지 */
	@Schema(description = "프로세스 메시지", example = "")
	private String processMsg;
	
	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String addwho;

	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String editwho;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;
	
	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String regNm;
	
	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String updNm;
	
	/** 롤테이너최대개수 */
	@Schema(description = "롤테이너최대개수", example = "")
	private String rolltainerMax;

	/** 롤테이너시작번호 */
	@Schema(description = "롤테이너시작번호", example = "")
	private String rolltainerStartNo;

	/** 롤테이너종료번호 */
	@Schema(description = "롤테이너종료번호", example = "")
	private String rolltainerEndNo;

    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
    
	
	private String staDt;
	
	private String endDt;
	
	private String prevSerialkey;
	
	private String dataVal;
	
	private String dateVal;
	
	
}