package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.15 
 * @description : 센터 배송 권역 행정동 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "센터 배송 권역 행정동 응답 DTO")
public class MsCenterDlvDistrictHjdongResDto extends CommonDto {
	
	@Schema(description = "테이블 키")
	private String serialkey;
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "배송 권역 아이디")
	private String dlvdistrictId;

	@Schema(description = "배송 권역 명")
	private String dlvdistrictNm;

	@Schema(description = "배송 권역 그룹 아이디")
	private String dlvgroupId;
	
	@Schema(description = "행정동 코드")
	private String hjdongCd;
	
	@Schema(description = "행정동 명")
	private String hjdongNm;
	
	@Schema(description = "시도명")
	private String ctpKorNm;
	
	@Schema(description = "시군구명")
	private String sigKorNm;
	
	@Schema(description = "삭제여부")
	private String delYn;
	
	@Schema(description = "유효 시작일자")
	private String fromDate;
	
	@Schema(description = "유효 종료일자")
	private String toDate;

	@Schema(description = "테이블 키")
	private String districtSerialkey;

	@Schema(description = "보기유무")
	private String showYn;

	@Hidden
	private int rowCount;

	@Schema(description = "검증 에러 메시지")
    private List<String> errorMessages;

	@Hidden
	private String editDate;
	@Hidden
	private String editWho;

    public void addMessage(String message) {
        if (errorMessages == null) {
            errorMessages = new ArrayList<>();
        }
        errorMessages.add(message);
    }
}
