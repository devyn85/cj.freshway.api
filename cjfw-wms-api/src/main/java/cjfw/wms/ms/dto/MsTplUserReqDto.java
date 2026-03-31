package cjfw.wms.ms.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author      : ParkYoSep (dytpq362@cj.net)
 * @date        : 2025.10.24
 * @description : 화주관리 조회 요청 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24      ParkYoSep (dytpq362@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor            
@AllArgsConstructor
@Schema(description = "화주관리 조회(목록) 요청 DTO")
public class MsTplUserReqDto extends CommonDto {
    
     /** 저장리스트 */
    @Schema(description = "저장리스트")
    List<MsTplUserResDto> saveList;
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dcCode;
    
    /** 시작일자 */
    @Schema(description = "시작일자")
    private String fromDate;

    /** 종료일자 */
    @Schema(description = "종료일자")
    private String toDate;

}
