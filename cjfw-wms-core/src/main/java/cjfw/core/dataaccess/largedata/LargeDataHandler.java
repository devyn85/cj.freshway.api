package cjfw.core.dataaccess.largedata;

import org.apache.ibatis.session.ResultHandler;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : LargeDataHandler 기능을 구현한 Interface Class
 * 				  대량 데이터 처리 기능을 위해 사용함
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@SuppressWarnings("rawtypes")
public interface LargeDataHandler extends ResultHandler {

	/**
	 * 
	 * @description : close 기능을 구현한 Method
	 * 				  데이터를 모두 처리하고 자원해제하는 메소드
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void close();

	/**
	 * 
	 * @description : getResultCnt 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public int getResultCnt();
}
