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
@Schema(description = "일별메모 요청 DTO")
public class TmCrmWmsMemoReqDto extends CommonDto {

    @Schema(description = "조회 시작일(YYYYMMDD)")
    private String schMemoDateFrom;

    @Schema(description = "조회 종료일(YYYYMMDD)")
    private String schMemoDateTo;
    
    @Schema(description = "작성출처")
    private String schSourceSystem;
    
    @Schema(description = "진행상태")
    private String schStatus;
    
    @Schema(description = "확정제외")
    private String schExcludConfirm;
    
    @Schema(description = "관리처코드")
    private String schCustKey;
    
    @Schema(description = "자동차번호")
    private String schCarCode;
    
    @Schema(description = "메모유형")
    private String schMemoType;
    
    @Schema(description = "물류센터")
    private String schStorerKey;
    
    @Schema(description = "회사코드")
    private String schDcCode;
    
    
    @Schema(description = "작성출처")
    private String sourceSystem;

    @Schema(description = "처리상태")
    private String status;

    @Schema(description = "오류 필터(1: STATUS<>'03')")
    private String error;

    @Schema(description = "메모 유형")
    private String memoType;
    
    @Schema(description = "고객키")
    private String custKey;

    @Schema(description = "고객키 멀티")
    private List<String> multiToCustkey;
    
    @Schema(description = "차량번호")
    private String carCode;

    @Schema(description = "차량번호 멀티")
    private List<String> multiCarNo;

    // 식별자
    @Schema(description = "일련번호")
    private String serialKey;

    @Schema(description = "메모 ID")
    private String memoId;

    // 저장/적용/삭제 등 업무 필드
    @Schema(description = "행 상태(I/U)")
    private String rowStatus;

    @Schema(description = "메모 레벨(M/C)")
    private String memoLevel;

    @Schema(description = "부모 ID(코멘트용)")
    private String parentId;

    @Schema(description = "메모 내용")
    private String description;

    @Schema(description = "차량번호")
    private String carNo;

    @Schema(description = "기사 연락처")
    private String driverPhone;

    @Schema(description = "기사명")
    private String driverName;

    @Schema(description = "EDMS 파일 ID")
    private String edmsFileId;

    @Schema(description = "전송 대상")
    private String transTarget;

    @Schema(description = "문의일자")
    private String inquiryDate;
    
    @Schema(description = "물류센터")
    private String storerKey;
    
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
    private String issueNo;
    
    private String custFs;
    
    private List<TmCrmWmsMemoResDto> masterList;
    private List<TmCrmWmsMemoResDto> histList;
}
