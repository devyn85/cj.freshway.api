package cjfw.wms.comfunc.func.largedata.dto;

import lombok.Data;

/**
 * 대량데이터 조회 Response DTO
 */
@Data
public class SampleLargedataGetResDto {
    private Long exnNo; 		// 일련번호
    private String excptCnts; 	// 예외내용
    private String occrDy; 		// 발생일자
    private String clntAddr; 	// 클라이언트 주소
    private String svrAddr; 	// 서버 주소
    private String callUri; 	// 호출URI
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