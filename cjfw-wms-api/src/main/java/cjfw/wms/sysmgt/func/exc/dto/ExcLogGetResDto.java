package cjfw.wms.sysmgt.func.exc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 시스템 예외 이력 조회 Response DTO
 */
@Data
public class ExcLogGetResDto {
	@Schema(description = "일련번호", example = "2785")
    private Long exnNo; // 일련번호
	
	@Schema(description = "예외내용", example = "cjfw.core.exception.SystemException: ")
    private String excptCnts; // 예외내용
	
	@Schema(description = "발생일자", example = "2022-07-25 17:01:46")
    private String occrDy; // 발생일자
	
	@Schema(description = "클라이언트 주소", example = "0:0:0:0:0:0:0:1")
    private String clntAddr; // 클라이언트 주소
	
	@Schema(description = "서버 주소", example = "0:0:0:0:0:0:0:1")
    private String svrAddr; // 서버 주소
	
	@Schema(description = "호출URI", example = "/sysmgt/func/commoncodeI18N/searchGroupCd")
    private String callUri; // 호출URI
}

/**
  [ API 샘플 예시 ]
 {
     "statusCode": 0,
     "statusMessage": "",
     "data": [
         {
             "exnNo": 2541,
             "excptCnts": "cjfw.core.exception.UserHandleException: MSG.COM.ERR.001\n\tat cjfw.core.utility.RestClientUtil.getSvcUrl(RestClientUtil.java:51)\n",
             "occrDy": "2022-05-31 11:21:05",
             "clntAddr": "10.42.0.1",
             "svrAddr": "10.65.0.96",
             "callUri": "/sysmgt/func/svcapi/list"
         },
    ...
 */