package cjfw.wms.kp.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net) 
 * @date        : 2025.12.26
 * @description : 입고검수현황 조회 응답 DTO
 * @issues      :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.26 JiSooKim (jskim14@cj.net)  생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "입고검수현황 조회 응답")
public class KpDpInspectMonitoringResDto extends CommonDto {
    @Schema(description = "체크여부(Y/N)")
    private String checkyn;

    @Schema(description = "센터코드")
    private String storerkey;

    @Schema(description = "센터코드")
    private String dccode;

    @Schema(description = "센터명")
    private String dcname;

    @Schema(description = "전표일자")
    private String slipdt;

    @Schema(description = "출고처코드")
    private String fromcustkey;

    @Schema(description = "출고처명")
    private String fromcustname;

    @Schema(description = "채널코드")
    private String channel;

    @Schema(description = "채널명")
    private String channelname;

    @Schema(description = "검수시작시간")
    private String inspectStartTime;

    @Schema(description = "검수종료시간")
    private String inspectEndTime;

    @Schema(description = "소요시간(분)")
    private String taketimeMin;

    @Schema(description = "검수대상수량")
    private Integer inspectopencnt;

    @Schema(description = "검수진행수량")
    private Integer inspectprocesscnt;

    @Schema(description = "검수진행수량2")
    private Integer inspectprocesscnt2;

    @Schema(description = "진행률")
    private java.math.BigDecimal inspectprocessrate;

    @Schema(description = "결과(Y:스캔완료, I:스캔중, N:미스캔)")
    private String result;

    @Schema(description = "미완료여부")
    private String undoneyn;

    @Schema(description = "발신그룹")
    private String sendgroup;

    @Schema(description = "발신전화번호")
    private String sendphone;

    @Schema(description = "발신자명")
    private String sendname;

    @Schema(description = "발신제목")
    private String sendtitle;

    @Schema(description = "발신메시지")
    private String sendmessage;

    @Schema(description = "수신그룹")
    private String receivegroup;

    @Schema(description = "수신전화번호")
    private String receivephone;

    @Schema(description = "수신자명")
    private String receivename;

    @Schema(description = "수신이메일")
    private String receiveemail;

    @Schema(description = "이메일제목")
    private String emailsendtitle;

    @Schema(description = "이메일주소")
    private String emailsendaddr;

    @Schema(description = "날짜(월일)")
    private String ndate;

    @Schema(description = "버튼플래그")
    private String btnflag;

    @Schema(description = "버튼플래그2")
    private String btnflag2;

    @Schema(description = "SMS1 플래그")
    private String sms1flag;

    @Schema(description = "SMS2 플래그")
    private String sms2flag;

    @Schema(description = "버튼플래그3")
    private String btnflag3;

    @Schema(description = "SMS 전송건수")
    private Integer smscnt;

    @Schema(description = "이메일1차 전송여부")
    private String email1yn;
    
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti; 
}