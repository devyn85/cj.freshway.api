package cjfw.wms.ms.dto;

import java.util.List;

import cjfw.wms.common.annotation.MaskingBizno;
import cjfw.wms.common.annotation.MaskingEmail;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.dev.dto.DevPilot02MaskResDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.08.20 
 * @description : 협력사정보 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@Schema(description = "GPS좌표등록 응답 DTO")
public class MsCustDeliveryInfoPResDto extends CommonDto{
	
	/** 고객코드 */
	@Schema(description = "고객코드", example = "12340")
	private String custkey;
	
	/** 거래처 유형 */
	@Schema(description = "거래처 유형", example = "C")
	private String custtype;
	
	/** 위도 */
	@Schema(description = "위도", example = "35.2272799475294")
	private String latitude;
	
	/** 경도 */
	@Schema(description = "경도", example = "128.635822693316")
	private String longitude;
	
	/** 체크결과 */
	@Schema(description = "체크결과", example = "N")
	private String processYn;
	
	/** 체크메세지 */
	@Schema(description = "체크메세지", example = "존재하지 않는 거래처유형 코드입니다.")
	private String processMsg;
	
	/** CHECK YN */
    @Schema(description = "CHECK YN", example = "")
    private String checkyn;

    /** CUST NAME */
    @Schema(description = "CUST NAME", example = "")
    private String custname;

    /** 거래처 그룹 */
    @Schema(description = "거래처 그룹", example = "")
    private String custgroup;

    /** 고객사코드 */
    @Schema(description = "고객사코드", example = "")
    private String storerkey;

    /** 배송처코드 */
    @Schema(description = "배송처코드", example = "")
    private String dlvcustkey;

    /** DLV CUST NAME */
    @Schema(description = "DLV CUST NAME", example = "")
    private String dlvcustname;

    /** 인도처코드 */
    @Schema(description = "인도처코드", example = "")
    private String dropcustkey;

    /** 배송인도처 */
    @Schema(description = "배송인도처", example = "")
    private String dropcustname;

    /** 기본주소 */
    @Schema(description = "기본주소", example = "")
    private String address;

    /** 기본주소1 */
    @Schema(description = "기본주소", example = "")
    private String address1;

    /** 기본주소2 */
    @Schema(description = "기본주소", example = "")
    private String address2;

    /** 검품유형 */
    @Schema(description = "검품유형", example = "")
    private String qctype;
    
    /** 3PL 담당자 */
    @Schema(description = "3PL 담당자", example = "")
    private String gubun;

    /** 검수유형 */
    @Schema(description = "검수유형", example = "")
    private String inspecttype;

    /** 협력사 납품 담당자 */
    @MaskingName
    @Schema(description = "협력사 남품 담당자", example = "")
    private String empname1;

    /** 담당자 연락처 */
    @Schema(description = "담당자 연락처", example = "")
    private String empphone1;

    /** 담당자 e-mail */
    @MaskingEmail
    @Schema(description = "담당자 e-mail", example = "")
    private String email;

    /** 입차시작시간 */
    @Schema(description = "입차시작시간", example = "")
    private String loadcondition1;

    /** 입차종료시간 */
    @Schema(description = "입차종료시간", example = "")
    private String loadcondition2;

    /** 배송요청요일 */
    @Schema(description = "배송요청요일", example = "")
    private String reqdlvweek;

    /** 배송요청일 */
    @Schema(description = "배송요청일", example = "")
    private String reqdlvdate;

    /** 배송요청시간 시작 */
    @Schema(description = "배송요청시간 시작", example = "")
    private String reqdlvtime1From;

    /** 배송요청시간 종료 */
    @Schema(description = "배송요청시간 종료", example = "")
    private String reqdlvtime1To;

    /** 배송요청시간 시작 */
    @Schema(description = "배송요청시간 시작", example = "")
    private String reqdlvtime2From;

    /** 배송요청시간 종료 */
    @Schema(description = "배송요청시간 종료", example = "")
    private String reqdlvtime2To;

    /** 열쇠보관및수령장소 */
    @Schema(description = "열쇠보관및수령장소", example = "")
    private String keyplace;

    /** 열쇠타입 */
    @Schema(description = "열쇠타입", example = "")
    private String keytype;

    /** 상태 */
    @Schema(description = "상태", example = "")
    private String status;

    /** DISTRICT TYPE */
    @Schema(description = "DISTRICT TYPE", example = "")
    private String districttype;

    /** DISTRICT CODE */
    @Schema(description = "DISTRICT CODE", example = "")
    private String districtcode;

    /** DISTRICT GROUP */
    @Schema(description = "DISTRICT GROUP", example = "")
    private String districtgroup;

    /** CAR NO */
    @Schema(description = "CAR NO", example = "")
    private String carno;

    /** 주출고처 */
    @Schema(description = "주출고처", example = "")
    private String dlvDccode;

    /** 원거리유형 */
    @Schema(description = "원거리유형", example = "")
    private String distancetype;

    /** 롤테이너번호 */
    @Schema(description = "롤테이너번호", example = "")
    private String loadplace;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간", example = "")
    private String adddate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", example = "")
    private String editdate;

    /** ADDR LENG */
    @Schema(description = "ADDR_LENG", example = "")
    private String addrLeng;

    /** FROM CUST NAME */
    @Schema(description = "FROM CUST NAME", example = "")
    private String fromCustname;

    /** 거래처코드(FROM) */
    @Schema(description = "거래처코드", example = "")
    private String fromCustkey;

    /** 거래처 유형 */
    @Schema(description = "거래처 유형", example = "")
    private String custtypeNm;

    /** 지역구분(대) */
    @Schema(description = "지역구분(대)", example = "")
    private String other03;

    /** 지역구분(중) */
    @Schema(description = "지역구분(중)", example = "")
    private String other04;

    /** 지역구분(소) */
    @Schema(description = "지역구분(소)", example = "")
    private String other05;
    
    /** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String addwho;

	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String editwho;
	
	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String regNm;
	
	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String updNm;
	
	/** 거래처 정보 조회 (단건) */
	@Schema(description = "거래처 정보 조회 (단건)", example = "")
	private MsCustDeliveryInfoPMasterResDto masterDto;
	
	/** 협력사 입고검수결과 수신자 마스터정보 조회(목록) */
	@Schema(description = "협력사 입고검수결과 수신자 마스터정보 조회(목록)", example = "")
	private List<MsCustDeliveryInfoPDetailResDto> masterDetailDto;
	
	/** 협력사 입고검수결과 수신자 마스터정보 조회(목록) */
	@Schema(description = "협력사 입고검수결과 수신자 마스터정보 조회(목록)", example = "")
	private List<MsCustDeliveryInfoPDetailPersonResDto> masterPersonDto;
	
	/** 사업자등록번호 */
	@MaskingBizno
	@Schema(description = "사업자등록번호)")
    private String vatNo;
	
	/** 3PL 업체명 */
	@Schema(description = "")
	private String cust3pl;
	
	/** 3PL 담당자명 */
	@MaskingName
	@Schema(description = "")
	private String name3pl;
	
	/** 조달물류여부 */
    @Schema(description = "조달물류여부")
    private String procLogiYn;
	
}
