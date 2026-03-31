package cjfw.core.dataaccess.largedata;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.exception.UserHandleException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : LargeDataUtils 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Slf4j
public class LargeDataUtils {

	private HttpServletResponse response;

	@Getter
	private ServletOutputStream sos;

	/**
	 * 
	 * @description : LargeDataUtils의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public LargeDataUtils(HttpServletResponse response) {
		try {
			this.sos = response.getOutputStream();
		} catch (IOException e) {
			log.error("LargeDataUtils.writeResponseToJson.IOException : ", e);
			throw new UserHandleException("MSG_COM_ERR_001");
		}
		setResponseHeader(response);
	}

	/**
	 * 
	 * @description : setResponseHeader 기능을 구현한 Method
	 * 				  Response 정보 세팅
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private void setResponseHeader(HttpServletResponse response){
		this.response = response;
		this.response.setContentType("application/json");
		this.response.setCharacterEncoding(CanalFrameConstants.DEFAULT_CHARACTERSET);
	}

	/**
	 * 
	 * @description : writeResponseToJson 기능을 구현한 Method
	 * 				  Response에 대한 후처리 
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void writeResponseToJson() {
		try {
			this.sos.flush();
			this.response.flushBuffer();
		} catch(Exception e){
			log.error("LargeDataUtils.writeResponseToJson.IOException : ", e);
			throw new UserHandleException("MSG_COM_ERR_001");
		} finally {
			try {
				this.sos.close();
			} catch (IOException e) {
				log.error("LargeDataUtils.IOException", e);
			}
		}
	}
}