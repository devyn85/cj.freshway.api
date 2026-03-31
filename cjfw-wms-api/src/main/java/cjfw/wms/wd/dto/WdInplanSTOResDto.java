package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author 		: YeoSeungCheol (pw6375@cj.net) 
 * @date 		: 2025.11.12
 * @description : 광역출고현황 목록 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.12 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Schema(description = "광역출고현황 목록 응답 DTO")
public class WdInplanSTOResDto {
	
	
	@Schema(description="물류센터")
	private String dccode;

	
	@Schema(description="주문유형")
	private String ordertype;

	@Schema(description="광역출고일자")
	private String docdt;

	@Schema(description="광역주문번호")
	private String docno;

	@Schema(description="물류센터")
	private String fromDccode;

	@Schema(description="물류센터명")
	private String fromDcname;

	@Schema(description="창고")
	private String fromOrganize;

	@Schema(description="창고명")
	private String fromCustname;

	@Schema(description="물류센터")
	private String toDccode;

	@Schema(description="물류센터명")
	private String toDcname;

	@Schema(description="창고")
	private String toOrganize;

	@Schema(description="창고명")
	private String toCustname;

	@Schema(description="진행상태")
	private String status;

	@Schema(description="차량번호")
	private String carno;

	@Schema(description="출차시간")
	private String dcdeparturedt;

	@Schema(description="이체유형")
	private String stotype;

	@Schema(description="메모")
	private String memo1;
	
	// hidden start
	@Schema(description="저장조건")
	private String storagetype;
	
	// hidden end	

	@Schema(description="등록자")
	private String addwho;

	@Schema(description="등록일시")
	private String adddate;

	@Schema(description="수정자")
	private String editwho;

	@Schema(description="수정일시")
	private String editdate;
	
	@Schema(description = "커스텀 엑스트라 체크박스")
	private String customRowCheckYn="N";
}
