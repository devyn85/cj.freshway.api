package cjfw.wms.common;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.05.28 
 * @description : cjfw-wms-com/cjfw-wms-bo-front에서 사용하는 공통 CommonConstants 선언
 *                ->멤버변수를 Call하는 리소스에 대해 관리가 용이함(Open call hierachy)
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.28 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
public final class CommonConstants {
	
	/** WMS오라클 패키지를 호출하는 공통 Call query id */
	public static final String COMMON_CALLPROCEDURE = "cmCommonService.callProcedure";
	/** WMS오라클 템픔테이블호출하는 공통 workspace id */
	public static final String COMMON_TEMP_PREFIX = "cmTempTableService.";
	
	
}