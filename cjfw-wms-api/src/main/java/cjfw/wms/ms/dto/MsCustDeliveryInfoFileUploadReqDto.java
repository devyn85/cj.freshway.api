package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.08.12
 * @description : 차량정보 파일업로드 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.12        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "차량정보 파일업로드 DTO")
public class MsCustDeliveryInfoFileUploadReqDto extends CommonDto {
    
    @Schema(description = "문서형태")
    private String docType;
    
    @Schema(description = "파일명")
    private String fileName;
    
    @Schema(description = "확장자")
    private String fileExtension;
    
    @Schema(description = "위치")
    private String fileLocation;
    
    @Schema(description = "용량")
    private String fileSizeBytes;
    
    @Schema(description = "변경파일명")
    private String transFileName;
    
    @Schema(description = "업로드결과ID")
    private String uploadResDocId;
    
    @Schema(description = "업로드된파일명")
    private String uploadFileName;
    
    @Schema(description = "업로드위치ID")
    private String uploadWorkplaceId;
    
}