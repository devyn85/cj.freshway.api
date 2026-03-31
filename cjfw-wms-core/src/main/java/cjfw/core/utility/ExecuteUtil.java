package cjfw.core.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cjfw.core.exception.SystemException;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : ExecuteUtil 기능을 구현한 Class
 * 				  Java runtime을 사용하여 커맨드라인 명령을 수행하기 위해 만든 유틸
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
public class ExecuteUtil {
	private static final Logger log = LoggerFactory.getLogger(ExecuteUtil.class);

	/**
	 * 
	 * @description : ExecuteUtil의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	private ExecuteUtil() {}
	
	/**
	 * 
	 * @description : execute 기능을 구현한 Method
	 * 				  명령을 shell로 실행하고, output을 반환
	 * 
	 * 				  cmd - 실행할 명령
	 * 				  timeout - 대기할 최대 시간. 단위 ms
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String execute(String cmd, long timeout) {
		return execute(cmd, timeout, null);
	}

	/**
	 * 
	 * @description : execute 기능을 구현한 Method
	 * 				  명령을 shell로 실행하고, output을 반환
	 * 
	 * 				  cmd - 실행할 명령
	 * 				  timeout - 대기할 최대 시간. 단위 ms
	 * 				  workingPath - Runtime.exec()의 인수로 들어가는 workingPath
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static String execute(String cmd, long timeout, String workingPath) {
		//타임아웃 처리
		new Thread(new Waker(Thread.currentThread(), timeout)).start();
		Process process = null;
		try {
			if(workingPath == null) {
				process = Runtime.getRuntime().exec(cmd);
			} else {
				process = Runtime.getRuntime().exec(cmd, null, new File(workingPath));
			}
		} catch (IOException e) {
			log.error("ExecuteUtil.execute.IOException : ", e);
		}
		
		if(process == null || ObjectUtils.isEmpty(process)) {
			throw new SystemException("process is null");
		}
		
		try (
			BufferedInputStream bis = new BufferedInputStream(process.getInputStream());
		){
			byte[] buff = new byte[1024];
			//동기화 처리
			process.waitFor();
			StringBuilder sbout = new StringBuilder();
			while(bis.read(buff) != -1) {
				sbout.append(new String(buff, 0, bis.read(buff)));
			}
			return sbout.toString();
		} catch(InterruptedException e) {
			log.error("ExecuteUtil.execute.InterruptedException", e);
			Thread.currentThread().interrupt();
			throw new SystemException(e);
		} catch(Exception e) {
			log.error("ExecuteUtil.execute.Exception : ", e);
			throw new SystemException(e);
		} finally {
			process.destroy();
		}
	}

	/**
	 * 
	 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
	 *
	 * @author      : sungyeon.lee
	 * @date        : 2023.10.22
	 * @description : Waker 기능을 구현한 Class
	 * 				  커맨드라인 명령 수행시 Timeout을 주기 위해 만든 클래스
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	static class Waker implements Runnable {

		private Thread t;
		private long timeout;

		/**
		 * 생성자<br>
		 * <br>
		 * @param toWake interrupt할 Thread
		 * @param timeout 설정한 타임아웃
		 */
		Waker(Thread toWake, long timeout) {
			this.t = toWake;
			this.timeout = timeout;
		}
		
		/**
		 * 타임아웃만큼 기다린 후 t 스레드를 interrupt한다.<br>
		 */
		public void run() {
			synchronized(this) {
				boolean flag = true;
				try {
					while (flag) {
						this.wait(timeout);
						flag = false;
					}
				} catch(InterruptedException e) {
					log.error("ExecuteUtil.$Waker.run.InterruptedException", e);
					Thread.currentThread().interrupt();
				}
			}
			t.interrupt();
		}
	}
}
