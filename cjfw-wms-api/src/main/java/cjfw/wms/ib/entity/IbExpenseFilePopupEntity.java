package cjfw.wms.ib.entity;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.11 
 * @description : 비용기표 파일 Entity
 * @issues :<pre> 
  * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.08    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
public class IbExpenseFilePopupEntity extends CommonProcedureDto {
    
    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private String serialkey;

    /** 문서유형 */
    @Schema(description = "문서유형", nullable = false, example = "")
    private String docType;
    
    /** 파일명 */
    @Schema(description = "파일명", nullable = false, example = "")
    private String fileName;
    
    /** 파일확장자 */
    @Schema(description = "파일확장자", nullable = false, example = "")
    private String fileExtension;
    
    /** 파일저장위치 */
    @Schema(description = "파일저장위치", nullable = false, example = "")
    private String fileLocation;
    
    /** 파일크기 */
    @Schema(description = "파일크기", nullable = false, example = "")
    private String fileSizeBytes;
    
    /** 파일저장명 */
    @Schema(description = "파일저장명", nullable = false, example = "")
    private String transFileName;
    
    /** 서버전송 후 리턴받은 고유파일ID(HASH_ID + TRNAS_FILE_NAME) */
    @Schema(description = "서버전송 후 리턴받은 고유파일ID(HASH_ID + TRNAS_FILE_NAME)", nullable = false, example = "")
    private String uploadResDocId;
    
    /** 변환 후 업로드된 파일명 */
    @Schema(description = "변환 후 업로드된 파일명", nullable = false, example = "")
    private String uploadFileName;
    
    /** 업무모듈별 WORKPLACE ID */
    @Schema(description = "업무모듈별 WORKPLACE ID", nullable = false, example = "")
    private String uploadWorkplaceId;
        
}