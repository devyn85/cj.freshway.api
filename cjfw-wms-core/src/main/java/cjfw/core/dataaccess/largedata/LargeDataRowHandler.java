package cjfw.core.dataaccess.largedata;

import com.google.gson.Gson;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.exception.SystemException;
import jakarta.servlet.ServletOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ResultContext;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : LargeDataRowHandler 기능을 구현한 Class
 * 				  대용량 조회를 위한 RowHandler 활용 기능
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Slf4j
public class LargeDataRowHandler<T> implements LargeDataHandler {

	private ServletOutputStream sos;
	private Gson gson;
	private boolean isStarted = false;

	/**
	 * 
	 * @description : LargeDataRowHandler의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public LargeDataRowHandler(LargeDataUtils largeDataUtils) {

		this.sos = largeDataUtils.getSos();
		this.gson = new Gson();

		String jsonStart = "{ \"data\" : [";
		try {
			sos.write(jsonStart.getBytes(CanalFrameConstants.DEFAULT_CHARACTERSET));
		} catch (IOException e) {
			log.error("LargeDataRowHandler.Exception : ", e);
			try {
				sos.close();
			} catch (IOException ex) {
				log.error("LargeDataRowHandler.IOException", ex);
			}
			throw new SystemException(e);
		}
	}

	/**
	 * 
	 * @overridden  : @see org.apache.ibatis.session.ResultHandler#handleResult(org.apache.ibatis.session.ResultContext)
	 * @description : handleResult 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void handleResult(ResultContext result) {
		T dataMap = (T)result.getResultObject();

		try {
			if(isStarted){
				sos.write(", ".getBytes(CanalFrameConstants.DEFAULT_CHARACTERSET));
			} else{
				isStarted = true;
			}
			sos.write(gson.toJson(dataMap).getBytes(CanalFrameConstants.DEFAULT_CHARACTERSET));
		} catch (IOException e) {
			log.error("LargeDataRowHandler.handleResult.Exception : ", e);
			try {
				sos.close();
			} catch (IOException ex) {
				log.error("LargeDataRowHandler.handleResult.IOException", ex);
			}
			throw new SystemException(e);
		}

	}
	
	/**
	 * 
	 * @overridden  : @see cjfw.core.dataaccess.largedata.LargeDataHandler#close()
	 * @description : close 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@Override
	public void close() {
		String jsonEnd = "], \"statusCode\": 0, \"statusMessage\": \"\" }";
		try {
			sos.write(jsonEnd.getBytes(CanalFrameConstants.DEFAULT_CHARACTERSET));
		} catch (IOException e) {
			log.error("LargeDataRowHandler.close.Exception : ", e);
			try {
				sos.close();
			} catch (IOException ex) {
				log.error("LargeDataRowHandler.close.IOException", ex);
			}
			throw new SystemException(e);
		}

	}
	
	/**
	 * 
	 * @overridden  : @see cjfw.core.dataaccess.largedata.LargeDataHandler#getResultCnt()
	 * @description : getResultCnt 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@Override
	public int getResultCnt() {
		return -1;
	}
}
