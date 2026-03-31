package cjfw.wms.tm.dto;

import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingTelno;
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
@Schema(description = "일별메모 조회 응답 DTO")
public class TmCrmWmsMemoResDto extends CommonDto{
    
    private String checkYn;         // check yn for grid
    private String serialKey;       // 식별자
    private String storerKey;
    private String memoId;
    private String parentId;
    private String memoLevel;
    private String memoDate;
    private String custKey;
    private String custName;
    private String custStrategy4; // 고객전략
    private String custType;
    private String memoType;
    private String memoTypeNm;
    private String memoWriter;
    private String description;
    private String carNo;
    
    @MaskingTelno
    @Schema(description = "기사전화번호")
    private String driverPhone;
    
    @MaskingName
    @Schema(description = "기사명")
    private String driverName;
    
    private String edmsFileId;
    private String sourceSystem;
    private String sourceSystemNm;
    private String status;
    private String statusNm;
    private String transTarget;
    private String transTargetNm;
    private String inquiryDate;
    private String addDate;
    private String editDate;
    private String addWho;
    private String editWho;
    private Integer fileCnt;
    
    @MaskingTelno
    @Schema(description = "고객전화번호")
    private String phone;
    
    // 파일 정보
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
    
    @Schema(description = "기사명")
    private String empName;
    
    @MaskingTelno
    @Schema(description = "기사전화번호")
    private String empPhone;
    
}
