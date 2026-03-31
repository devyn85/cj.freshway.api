package cjfw.core.common;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : CanalFrame에서 사용하는 변수를 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
public final class CanalFrameConstants {
	
	/**
	 * 
	 * @description : CanalFrameConstants의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private CanalFrameConstants() {}
	
	public static final String DEFAULT_CHARACTERSET = "UTF-8";
	
	//CUD
	public static final String INSERT = "I";
	public static final String UPDATE = "U";
	public static final String DELETE = "D";
	
	//Paging
	public static final String START_ROW = "START_ROW";
	public static final String LIST_COUNT = "LIST_COUNT";
	public static final String TOTAL_COUNT = "totalCount";
	public static final String SKIP_COUNT = "SKIP_COUNT";
	
	//CODE
	public static final int ERROR_CODE_SUCCESS = 0;      	//처리 결과코드 - 정상
	/** 사용자 Exception -> -1 */
	public static final int ERROR_CODE_USEREXCEP = -1;   	//처리 결과코드 - UserHandleException
	/** 시스템 Exception -> -1000 */
	public static final int ERROR_CODE_SYSEXCEP = -1000;	//처리 결과코드 - SystemException
	public static final int ERROR_CODE_ACCESSTOKEN_EXPIRED = -1001;  //처리 결과코드 - AccessToken 토큰 만료
	public static final int ERROR_CODE_REFRESHTOKEN_NOTVALID = -1002;  //처리 결과코드 - RefreshToken 비정상
	public static final int ERROR_CODE_DUPLICATE_LOGIN = -1003;  //처리 결과코드 - 중복 로그인
	public static final int ERROR_CODE_DIFFERENT_LOGIN = -1004;  //처리 결과코드 - 같은 브라우저에서 다른 사용자ID 로그인
	public static final String ERROR_CODE = "ErrorCode";	//처리 결과코드 명
	public static final String ERROR_MESSAGE = "ErrorMsg";
	public static final String MESSAGE_CODE = "SVC_MSG_CD";
	public static final String MESSAGE_TEXT = "SVC_MSG_TEXT";
	public static final String ERROR_MESSAGE_CODE = "SVC_ERR_MSG_CD";
	public static final String ERROR_MESSAGE_TEXT = "SVC_ERR_MSG_TEXT";
	public static final String ERROR_DETAIL = "SVC_ERR_DETAIL";
	public static final String STATUS_MESSAGE_CODE = "SVC_STS_MSG_CD";
	public static final String STATUS_MESSAGE_TEXT = "SVC_STS_MSG_TEXT";
	public static final String BIND_MESSAGE = "SVC_BIND_MSG";
	public static final String CLIENT_IP = "remoteNetworkAddress";
	
	/** 
	 * 일반적인 페이지의 레이아웃 접두사
	 * base.contents.
	 */
	public static final String VIEW_PREFIX_CONTENTS = "base.contents.";

	// 저장 성공 공통 메시지 코드 ([25/08/19] "MSG.COM.SUC.003" => "MSG_COM_SUC_003"으로 변경 from.장광석)
	/** 저장 되었습니다 */
	public static final String MSG_COM_SUC_CODE = "MSG_COM_SUC_003"; // 저장 되었습니다
	/** 실패 되었습니다 */
	public static final String MSG_COM_ERR_CODE = "MSG_COM_ERR_900"; // 실패 되었습니다
	/** 삭제 되었습니다 */
	public static final String MSG_COM_DEL_CODE = "MSG_COM_SUC_006"; // 삭제 되었습니다
}