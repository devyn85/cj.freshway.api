package cjfw.wms.kp.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.09.24
 * @description : KP KX Close 요청 DTO
 */
@Data
@Schema(description = "KP KX Close 요청")
public class KpKxCloseReqDto extends CommonProcedureDto {
	
	private List<KpKxCloseReqDto> reqDtoList;
	
    /** 
     * Tab1 - 마감현황
     * */

    /** 납품일자 */
    @Schema(description = "납품일자", example = "202509")
    private String deliveryDate;
    
    /** 문서구분 */
    @Schema(description = "문서구분", example = "STO01")
    private String doctype;
    
    /** IF 테이블명 */
    @Schema(description = "IF 테이블명", example = "IF_DM_SENDDATA")
    private String baseTable;

    /** IF ID  - Tab6 조정내역반영*/
    @Schema(description = "IF ID", example = "COM0430")
    private String ifId;

    /** 
     * Tab2 - KX검증
     * */
    /** KX 주문일자(From) */
    @Schema(description = "KX 주문일자(From)")
    private String deliveryDateFrom;

    /** KX 주문일자(To) */
    @Schema(description = "KX 주문일자(To)")
    private String deliveryDateTo;

    /** DMD 일치여부 */
    @Schema(description = "DMD 일치여부", example = "1")
    private String dmdEqYn;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;

    /** 전표번호 */
    @Schema(description = "전표번호")
    private String slipno;

    /** 저장리스트 */
    @Schema(description = "저장리스트")
    private List<KpKxCloseResT2Dto> saveList;
    
    /** 
     * Tab6 - 조정요청내역
     * */
    /** 저장리스트 */
    @Schema(description = "저장리스트2")
    private List<KpKxCloseResT6Dto> saveList2;
    
    /** 
     * Tab7 - 협력사반출
     * */
    /** 저장리스트 */
    @Schema(description = "저장리스트4")
    private List<KpKxCloseResT7Dto> saveList4;

    /** 프로시저명 */
    @Schema(description = "프로시저명")
    private String procedure;
    
    /** 
     * Tab3 - 문서내역
     * */
    /** 문서구분 */
    @Schema(description = "품목번호", example = "10")
    private String docline;
    /** 문서구분 */
    @Schema(description = "원주문번호")
    private String sourcekey;
    /** 문서구분 */
    @Schema(description = "구매전표")
    private String pokey;
    /** 문서구분 */
    @Schema(description = "버튼기능종류")
    private String prType;
    
    @Schema(description = "저장리스트")
    private List<KpKxCloseResT3Dto> saveList3;
    
    

    /**
     * Tab5 - 수불확인
     */
    /** DC 코드 */
    @Schema(description = "DC 코드")
    private String dcCode;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** SKU */
    @Schema(description = "SKU")
    private String sku;
    
    /** 
     * Tab9 - 소유권확인
     * */
    /** 저장리스트 */
    @Schema(description = "저장리스트2")
    private List<KpKxCloseResT9Dto> saveList9;
    
    
    /**
     * Tab11 - 수불비교
     */
    /** 마이너스 수불건 모니터링 */
    @Schema(description = "마이너스 수불건")
    private String minusSubulYn;
    
    /**
     * Tab13 - 재고비교
     */
    /** 수량차이분 */
    @Schema(description = "수량차이분")
    private String diffYn;
    
    
    /**
     * Tab14 - IF 송수신
     */
    /** 송수신 구분 (DM_DOCUMENT_D) */
    @Schema(description = "송수신 구분 (DM_DOCUMENT_D)")
    private String dmD;

    /** 송수신 구분 (DM_SENDDATA) */
    @Schema(description = "송수신 구분 (DM_SENDDATA)")
    private String dmSndd;

    /** 송수신 구분 (DM_DOCUMENT_H) */
    @Schema(description = "송수신 구분 (DM_DOCUMENT_H)")
    private String dmH;

    /** 송수신 구분 (IF_ST_STOCK_SERIALINFO_R) */
    @Schema(description = "송수신 구분 (IF_ST_STOCK_SERIALINFO_R)")
    private String ifStR;

    /** 송수신 구분 (IF_ST_STOCK_SERIALINFO_S) */
    @Schema(description = "송수신 구분 (IF_ST_STOCK_SERIALINFO_S)")
    private String ifStS;

    /** 송수신 구분 (IF_SENDRESULT) */
    @Schema(description = "송수신 구분 (IF_SENDRESULT)")
    private String ifSndRst;

    /** IF 상태 */
    @Schema(description = "IF 상태")
    private String ifFlag;
    
    /** 전체건수 */	
    @Schema(description = "전체건수")
    private int totalCnt;	 
	
    /** 처리건수 */	
    @Schema(description = "처리건수")
    private int processCnt; 
    
    
    /** 저장 리스트 - 코스트센터 */
    List<KpKxCloseResT14Dto> saveList10; 
    /** 저장 리스트 - 오류 재처리 */
    List<KpKxCloseResT14Dto> saveList11;
    
    
    /**
     * Tab7 - 실적 미수신건
     */
    /** 저장 리스트 - 실적 미수신 */
    List<KpKxCloseResT8Dto> saveList8; 
    List<KpKxCloseResT8Dto> receiversList;
    List<KpKxCloseResT8Dto> detailList;
    
    /** 제목 */
    @Schema(description = "제목")
    private String title;

    /** 내용 */
    @Schema(description = "내용")
    private String conts;

    /** 받는사람 */
    @Schema(description = "받는사람")
    private String recvName;

    /** 받는이메일주소 */
    @Schema(description = "받는이메일주소")
    private String recvEmail;

    /** 받는이메일주소2 */
    @Schema(description = "받는이메일주소2")
    private String recvEmail2;

    /** 보내는사람 */
    @Schema(description = "보내는사람")
    private String sendName;

    /** 보내는이메일주소 */
    @Schema(description = "보내는이메일주소")
    private String sendEmail;

    /** 첨부파일 */
    @Schema(description = "첨부파일")
    private String attchFileName;

    /** 이메일발송유형 */
    @Schema(description = "이메일발송유형")
    private String sendType;    //'STD'(일반), 'RPT'(리포트파일첨부)

    /** 참조메일주소 */
    @Schema(description = "참조메일주소")
    private String refEmailAdd;
    
    
    /**
     * Tab15 - 조정전표수정
     */
    /** 사유내용 */
    @Schema(description = "사유내용")
    private String memo;
    
    
}