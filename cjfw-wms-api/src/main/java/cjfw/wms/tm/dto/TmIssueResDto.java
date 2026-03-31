package cjfw.wms.tm.dto;

import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "배송이슈 응답 DTO")
public class TmIssueResDto extends CommonDto{
    
    @Schema(description = "체크 YN", example = "0")
    private String checkYn;
    
    @Schema(description = "물류센터코드", example = "2600")
    private String dcCode;

    @Schema(description = "회사코드", example = "FW00")
    private String storerKey;
    
    @Schema(description = "배송이슈번호", example = "1214255")
    private String issueNo;
    
    @Schema(description = "출고일자", example = "20260116")
    private String deliveryDt;
    
    @Schema(description = "관리처코드", example = "501342278")
    private String custKey;
    
    @Schema(description = "관리처명", example = "투썸플레이스(인천법원점)")
    private String custName;
    
    @Schema(description = "이슈코드", example = "02")
    private String issueCode;
    
    @Schema(description = "상품코드", example = "433532")
    private String sku;
    
    @Schema(description = "상품명", example = "던킨 미니카카오츄이스티도넛(27g*10입 270g/EA)")
    private String skuName;
    
    @Schema(description = "저장유형", example = "10")
    private String storageType;
    
    @Schema(description = "주문수량", example = "1")
    private String orderQty;
    
    @Schema(description = "출고수량", example = "1")
    private String confirmQty;
    
    @Schema(description = "확인수량", example = "1")
    private String workQty;
    
    @Schema(description = "단위", example = "BOX")
    private String uom;
    
    @MaskingName
    @Schema(description = "기사명", example = "기사1")
    private String driverName;
    
    @MaskingTelno
    @Schema(description = "기사전화번호")
    private String phone;
    
    @Schema(description = "도착시간", example = "01:00")
    private String deliveryTime;
    
    @Schema(description = "도착장소", example = "03")
    private String deliveryPlace;
    
    @Schema(description = "결품사유", example = "02")
    private String reasonCode;
    
    @Schema(description = "기타", example = "")
    private String reasonMsg;
    
    @Schema(description = "첨부파일갯수", example = "1")
    private Integer fileCnt;
    
    @Schema(description = "진행상태", example = "02")
    private String status;
    
    @Schema(description = "확정일", example = "2026-01-20 14:01:23.000")
    private String confirmDate;
    
    @Schema(description = "확정자", example = "XXXID")
    private String confirmWho;
    
    @Schema(description = "생성일", example = "2026-01-20 14:01:23.000")
    private String addDate;
    
    @Schema(description = "생성자ID", example = "XXXID")
    private String addWho;
    
    @Schema(description = "생성자명", example = "XXX님[XXXID]")
    private String addWhoNm;
    
    @Schema(description = "수정일", example = "2026-01-20 14:01:23.000")
    private String editDate;
    
    @Schema(description = "수정자ID", example = "XXXID")
    private String editWho;
    
    @Schema(description = "수정자명", example = "XXX님[XXXID]")
    private String editWhoNm;

    
    @Schema(description = "삭제여부", example = "N")
    private String delYn;
    
    
    @Schema(description = "파일 일련번호", example = "165")
    private String serialKey;
    
    /** 문서 타입 */
    @Schema(description = "문서 타입", example = "")
    private String docType;

    /** 문서 이름 */
    @Schema(description = "문서 이름", example = "")
    private String docName;

    /** 파일 이름 */
    @Schema(description = "파일 이름", example = "")
    private String fileName;

    /** 파일 확장자 */
    @Schema(description = "파일 확장자", example = "")
    private String fileExtension;

    /** 파일 위치 */
    @Schema(description = "파일 위치", example = "")
    private String fileLocation;

    /** 파일 크기 (바이트) */
    @Schema(description = "파일 크기 (바이트)", example = "")
    private Integer fileSizeBytes;

    /** 변환 파일 이름 */
    @Schema(description = "변환 파일 이름", example = "")
    private String transFileName;

    /** 업로드 해시 ID */
    @Schema(description = "업로드 해시 ID", example = "")
    private String uploadHashId;

    /** 업로드 리소스 문서 ID */
    @Schema(description = "업로드 리소스 문서 ID", example = "")
    private String uploadResDocId;
    
    /** 업로드 파일 이름 */
    @Schema(description = "업로드 파일 이름", example = "")
    private String uploadFileName;

    /** 업로드 작업장 ID */
    @Schema(description = "업로드 작업장 ID", example = "")
    private String uploadWorkplaceId;

    /** 업로드 위치 */
    @Schema(description = "업로드 위치", example = "")
    private String uploadLocation;

    /** 다운로드 횟수 */
    @Schema(description = "다운로드 횟수", example = "")
    private String downloadCount;

    /** 파일 타입 */
    @Schema(description = "파일 타입", example = "")
    private String fileType;
    
    
    
    /* 엑셀 업로드 validation 관련 start */
    @Schema(description = "출고일자 존재 유무", example = "N")
    private String deliveryDtYn;
    
    @Schema(description = "중복값 존재 유무", example = "N")
    private String duplicateYn;
    
    @Schema(description = "기존 정보 중복값 존재 유무", example = "N")
    private String databaseYn;
    
    @Schema(description = "관리처 코드 존재 유무", example = "N")
    private String custKeyYn;
    
    @Schema(description = "이슈 코드 존재 유무", example = "N")
    private String issueCodeYn;

    @Schema(description = "품목 코드 존재 유무", example = "N")
    private String skuYn;

    @Schema(description = "저장유형 코드 존재 유무", example = "N")
    private String storageTypeYn;

    @Schema(description = "출고수량 유무", example = "N")
    private String workQtyYn;

    @Schema(description = "단위 유무", example = "N")
    private String uomYn;

    @Schema(description = "기사명 유무", example = "N")
    private String driverNameYn;

    @Schema(description = "도착시간 유무", example = "N")
    private String deliveryTimeYn;

    @Schema(description = "배송장소 유무", example = "N")
    private String deliveryPlaceYn;

    @Schema(description = "이슈사유코드 존재 유무", example = "N")
    private String reasonCodeYn;
    
    @Schema(description = "물류센터 유무", example = "N")
    private String dcCodeYn;

    @Schema(description = "사용자명")
    private String userNm;
    
    /* 엑셀 업로드 validation 관련 end */
}
