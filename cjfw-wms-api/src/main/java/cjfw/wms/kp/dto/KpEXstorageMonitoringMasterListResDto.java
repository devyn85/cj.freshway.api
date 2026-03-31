package cjfw.wms.kp.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.14 
 * @description : 외부창고재고모니터링  기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고재고모니터링 res DTO")
public class KpEXstorageMonitoringMasterListResDto {

    /** MAPKEY */
    @Schema(description = "승인번호")
    private String mapkeyNo;              // MAPKEYNO

    /** POKEY */
    @Schema(description = "PO 번호")
    private String poKey;                 // POKEY

    /** POLINE */
    @Schema(description = "PO 항번")
    private String poLine;                // POLINE

    /** 납품예정일 */
    @Schema(description = "납품예정일")
    private String deliveryDate;          // DELIVERYDATE

    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docDt;                 // DOCDT

    /** 임시 DPKEY */
    @Schema(description = "가확정납품번호Y")
    private String tmpDpkey;              // TMPDPKEY

    /** DPKEY */
    @Schema(description = "진확정납품번호")
    private String dpkey;                 // DPKEY

    /** 거래처키 */
    @Schema(description = "출고처 코드")
    private String fromCustKey;           // FROMCUSTKEY

    /** 거래처명 */
    @Schema(description = "출고처 명칭")
    private String fromCustName;          // FROMCUSTNAME

    /** 창고 */
    @Schema(description = "창고")
    private String organize;              // ORGANIZE

    /** 창고명 */
    @Schema(description = "창고명")
    private String organizeName;          // ORGANIZENAME

    /** 계약타입 ※ */
    @Schema(description = "계약타입")
    private String contractType;          // CONTRACTTYPE

    /** 이동 여부 ※ */
    @Schema(description = "이동 여부")
    private String moveYn;                // MOVEYN

    /** 임시 처리 여부 ※ */
    @Schema(description = "임시 처리 여부")
    private String tempYn;                // TEMPYN

    /** 상태코드 ※ */
    @Schema(description = "상태코드")
    private String status;                // STATUS

    /** 임시 DP 상태 */
    @Schema(description = "임시 DP 상태")
    private String tmpDpStatus;           // TMPDPSTATUS

    /** DP 상태 */
    @Schema(description = "DP 상태")
    private String dpStatus;              // DPSTATUS

    /** 추가 DP 상태 */
    @Schema(description = "추가 DP 상태")
    private String addDpStatus;           // ADDDPSTATUS

    /** 시리얼 확정 여부 */
    @Schema(description = "시리얼 확정 여부")
    private String serialinfoCfmYn;       // SERIALINFO_CFM_YN

    /** 임시 SO 삭제 여부 */
    @Schema(description = "임시 SO 삭제 여부")
    private String tmpSoDelYn;            // TMPSODELYN

    /** 추가 여부 */
    @Schema(description = "추가 여부")
    private String addYn;                 // ADDYN

    /** 수량 */
    @Schema(description = "수량")
    private String qty;                   // QTY

    /** 미배정 수량 */
    @Schema(description = "미배정 수량")
    private String openQty;               // OPENQTY

    /** 박스 수량 */
    @Schema(description = "박스 수량")
    private String boxQty;                // BOXQTY

    /** UOM ※ */
    @Schema(description = "UOM")
    private String uom;                   // UOM

    /** 박스당 수량 */
    @Schema(description = "박스당 수량")
    private String qtyPerBox;             // QTYPERBOX

    /** ISSUE NO ※ */
    @Schema(description = "ISSUE NO")
    private String issueNo;               // ISSUENO

    /** SOKEY ※ */
    @Schema(description = "SO KEY")
    private String soKey;                 // SOKEY

    /** 추가자 ※ */
    @Schema(description = "추가자")
    private String addWho;                // ADDWHO

    /** 수정자 ※ */
    @Schema(description = "수정자")
    private String editWho;               // EDITWHO

    /** 생성자 ※ */
    @Schema(description = "생성자")
    private String createWho;             // CREATEWHO

    /** 등록자 ※ */
    @Schema(description = "등록자")
    private String regWho;                // REGWHO

    /** 등록일자 ※ */
    @Schema(description = "등록일자")
    private String regDate;               // REGDATE

    /** 최종수정자 ※ */
    @Schema(description = "최종수정자")
    private String lastWho;               // LASTWHO

    /** 최종수정일 ※ */
    @Schema(description = "최종수정일")
    private String lastDate;              // LASTDATE

    /** 오류 상태 ※ */
    @Schema(description = "오류 상태")
    private String errorStatus;           // ERRORSTATUS

    /** 글자색상 ※ */
    @Schema(description = "글자색상")
    private String fontColor;             // FONTCOLOR

    /** 배경색상 ※ */
    @Schema(description = "배경색상")
    private String bgColor;               // BGCOLOR

    /** 기타데이터1 ※ */
    @Schema(description = "추가데이터1")
    private String data1;                 // DATA1
}
