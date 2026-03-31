package cjfw.wms.comfunc.func.popup.dto;

import lombok.Data;

/**
 * 팝업 사원조회 Response DTO
 */
@Data
public class SamplePopupEmpGetResDto {
    private String userId; // 사용자ID
    private String userNm; // 사용자명
    private String empNo; // 사원번호
    private String mailAddr; // 메일주소

}

/**
  [API 샘플 예시]
   "data": [
     {
         "userId": "cxadmin",
         "userNm": "cxadmin",
         "empNo": null,
         "mailAddr": "15"
     },
     ...
   ]

 */

/**
  [MPA 참조]
  [
    {"USER_NM":"cxadmin",
     "USER_ID":"cxadmin",
     "EMP_NO":null,"
     MAIL_ADDR":null},
  ]

 */