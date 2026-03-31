package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.08.07 
 * @description : 결품대응현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.07 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "결품대응현황 목록 결과")
public class KpWdRequestCancelqtyResTab1Dto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	/**
     * 대응요청시간
     */
    @Schema(description = "대응요청시간", example = "2024-01-01")
    private String inspectdt;
    /**
     * 출차예정시간
     */
    @Schema(description = "출차예정시간", example = "2024-01-01")
    private String outdt;
    /**
     * 정상POP
     */
    @Schema(description = "정상POP", example = "12345")
    private String deliverygroup;
    /**
     * 오피킹POP
     */
    @Schema(description = "오피킹POP", example = "12345")
    private String dockdeliverygroup;
    /**
     * 도크번호
     */
    @Schema(description = "도크번호", example = "1")
    private String dockerno;
    /**
     * 유형
     */
    @Schema(description = "유형", example = "01")
    private String reasoncode;
    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String channel;
    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String channeldescr;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "일반")
    private String storagetypedescr;
    /**
     * 로케이션
     */
    @Schema(description = "로케이션", example = "타센터 운용재고")
    private String locName;
    /**
     * 고객코드
     */
    @Schema(description = "고객코드", example = "CUST01")
    private String toCustkey;
    /**
     * 고객명
     */
    @Schema(description = "고객명", example = "CJ대한통운")
    private String toCustname;
    /**
     * 협력사코드
     */
    @Schema(description = "협력사코드", example = "PARTNER01")
    private String fromCustkey;
    /**
     * 협력사명
     */
    @Schema(description = "협력사명", example = "CJ올리브네트웍스")
    private String fromCustname;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "ITEM01")
    private String sku;
    /**
     * 상품명
     */
    @Schema(description = "상품명", example = "상품1")
    private String description;
    /**
     * 수량
     */
    @Schema(description = "수량", example = "10")
    private BigDecimal openqty;
    /**
     * 일부대응수량
     */
    @Schema(description = "일부대응수량", example = "5")
    private BigDecimal confirmqty;
    /**
    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;
    /**
     * 차량번호
     */
    @Schema(description = "차량번호", example = "123가1234")
    private String carno;
    /**
     * 대응여부
     */
    @Schema(description = "대응여부", example = "Y")
    private String statusCode;
    /**
     * 대응자
     */
    @Schema(description = "대응자", example = "홍길동")
    private String statusWho;
    /**
     * 비고
     */
    @Schema(description = "비고", example = "메모내용")
    private String memo;
    /**
     * 입고검수량
     */
    @Schema(description = "입고검수량", example = "15")
    private BigDecimal inspectqty;
    /**
     * serialkey
     */
    @Schema(description = "serialkey", example = "KEY001")
    private String serialkey;
    /**
     * wavekey
     */
    @Schema(description = "wavekey", example = "WAVE001")
    private String wavekey;
    /**
     * docdt
     */
    @Schema(description = "docdt", example = "2024-01-01")
    private String docdt;
    /**
     * docno
     */
    @Schema(description = "docno", example = "DOC001")
    private String docno;
    /**
     * docline
     */
    @Schema(description = "docline", example = "1")
    private String docline;
    /**
     * loc
     */
    @Schema(description = "loc", example = "LOC01")
    private String loc;
    /**
     * deliverydate
     */
    @Schema(description = "deliverydate", example = "2024-01-01")
    private String deliverydate;
    /**
     * DCCODE
     */
    @Schema(description = "DCCODE", example = "2600")
    private String dccode;
    /**
     * FROM_DCCODE
     */
    @Schema(description = "FROM_DCCODE", example = "2630")
    private String fromDccode;
    /**
     * MISS_DOCDT
     */
    @Schema(description = "MISS_DOCDT", example = "20240101")
    private String missDocdt;
    
    /**모바일요청수량 */
    @Schema(description = "모바일요청수량", example = "10")
    private BigDecimal missOrderqty;
    
    /**STO요청수량 */
    @Schema(description = "STO요청수량", example = "10")
    private BigDecimal stoOrderqty;
    
    /**
     * ADDDATE
     */
    @Schema(description = "ADDDATE", example = "")
    private String adddate;
    
    /**
     * EDITDATE
     */
    @Schema(description = "EDITDATE", example = "")
    private String editdate;
    
    /**
     * ADDWHO
     */
    @Schema(description = "ADDWHO", example = "")
    private String addwho;
    
    /**
     * EDITWHO
     */
    @Schema(description = "EDITWHO", example = "")
    private String editwho;
    
    /**
     * ADDWHONM
     */
    @Schema(description = "ADDWHONM", example = "")
    private String addwhonm;
    
    /**
     * EDITWHONM
     */
    @Schema(description = "EDITWHONM", example = "")
    private String editwhonm;
    

    /**상태*/
    @Schema(description = "상태")
    private String reqStatus;
    
    /**상태*/
    @Schema(description = "상태")
    private String reqStatusNm;
    
    /**이체상태*00:등록,90:확정) */
    @Schema(description = "이체상태*00:등록,90:확정)")
    private String stoStatus;
}
