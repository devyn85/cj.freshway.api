package cjfw.wms.tm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송이슈 요청 DTO")
public class TmIssueReqDto extends CommonDto {
    // 조회 파라미터
    @Schema(description = "회사코드")
    private String schStorerKey;
    
    @Schema(description = "배송일자(YYYYMMDD)")
    private String schDeliveryDt;

    @Schema(description = "상태코드")
    private String schStatus;
    
    @Schema(description = "물류센터코드")
    private String schDcCode;

    @Schema(description = "고객키 멀티")
    private List<String> multiToCustkey;

    // 식별자
    @Schema(description = "이슈번호")
    private String issueNo;
    
    @Schema(description = "고객코드")
    private String schCustKey;

    // 등록/수정 필드
    private String deliveryDt;  // 출고일자
    private String storerKey;   // 회사 코드
    private String dcCode;      // 물류센터코드
    private String custKey;     // 관리처코드
    private String custName;    // 관리처명
    private String issueCode;   // 배송이슈코드
    private String sku;         // 상품코드
    private String skuNm;       // 상품명
    private String storageType; // 상태
    private String orderQty;    // 주문수량
    private String confirmQty;  // 확인수량
    private String workQty;     // 출고수량
    private String uom;         // 단위
    private String driverName;  // 기사명
    private String phone;           // 기사연락처
    private String deliveryTime;    // 도착 시간
    private String deliveryPlace;     // 배송 장소
    private String reasonCode;      // 사유코드
    private String reasonMsg;       // 기타 : 사유내용

    // 공통 행 상태(I/U)
    private String rowStatus;

    // 파일 업로드 메타
    private String docType;
    private String fileName;
    private String fileExtension;
    private String fileLocation;
    private Integer fileSizeBytes;
    private String transFileName;
    private String uploadResDocId;
    private String uploadFileName;
    private String uploadWorkplaceId;
    private String serialKey;
}
