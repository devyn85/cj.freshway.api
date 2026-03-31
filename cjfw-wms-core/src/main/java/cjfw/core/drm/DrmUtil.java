package cjfw.core.drm;

import com.fasoo.adk.packager.WorkPackager;

import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.StringUtil;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : DrmUtil 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
public class DrmUtil {
	
	/**
	 * 
	 * @description : DrmUtil의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private DrmUtil() {}

	private static final Logger log = LoggerFactory.getLogger(DrmUtil.class);
	private static final String CONF_FSDINIT_PATH = "cf.drm.fsdinitPath";
	private static final String CONF_DSDCODE = "cf.drm.dsdcode";
	private static final String CONF_DRM_ENABLE = "cf.drm.enable";
	private static String oHomeDir = "";
	private static String oServerID = "";
	private static boolean isEnable = true;
	static {
		oHomeDir = ContextUtil.getProperty(CONF_FSDINIT_PATH);
		oServerID = ContextUtil.getProperty(CONF_DSDCODE);
		isEnable = Boolean.parseBoolean(ContextUtil.getProperty(CONF_DRM_ENABLE));
	}

	/**
	 * 
	 * @description : FileTypeStr 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static String FileTypeStr(int fileType) {
		String ret = null;
		switch(fileType) {
			case 20:
				ret = "### 파일을 찾을 수 없습니다.";
				break;
			case 21:
				ret = "### 파일 사이즈가 0 입니다.";
				break;
			case 22:
				ret = "### 파일을 읽을 수 없습니다.";
				break;
			case 29:
				ret = "### 암호화 파일이 아닙니다.";
				break;
			case 26:
				ret = "### FSD 파일입니다.";
				break;
			case 105:
				ret = "### Wrapsody 파일입니다.";
				break;
			case 106:
				ret = "### NX 파일입니다.";
				break;
			case 101:
				ret = "### MarkAny 파일입니다.";
				break;
			case 104:
				ret = "### INCAPS 파일입니다.";
				break;
			case 103:
				ret = "### FSN 파일입니다.";
				break;
			default:
				break;
		}
		return ret;
	}

	/**
	 * 
	 * @description : packagingDRM 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	static public boolean packagingDRM(String srcFile, String targetFile, String userID, String userName, String deptID, String deptName) throws DrmException {
		return packagingDRM(srcFile, targetFile, userID, userName, deptID, deptName, Boolean.parseBoolean(ContextUtil.getProperty(CONF_DRM_ENABLE)));
	}

	/**
	 * 
	 * @description : packagingDRM 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static boolean packagingDRM(String srcFile, String targetFile, String userID, String userName, String deptID, String deptName, boolean drmEnable) throws DrmException {
		boolean result = false;
		int fileType = 0;
		WorkPackager wPackager = null;
		try {
			// DRM을 사용하지 않는 경우
			if(!drmEnable) {
				File sourceFile = new File(srcFile);
				File destFile = new File(targetFile);
				FileUtils.copyFile(sourceFile, destFile);
				return true;
			}
			wPackager = new WorkPackager();
			//wPackager.setCharset("eucKR");	//암/복호화 연동 시 한글이 깨지면 사용
			//복호화 된문서가 암호화된 문서를 덮어쓰지 않음
			wPackager.setOverWriteFlag(false);
			//01.대상 파일의 암호화 여부 확인
			fileType = wPackager.GetFileType(srcFile);
			log.info("### file type {}", FileTypeStr(fileType));

			if(fileType != 29) {
				log.warn("### This file [{}] is already DRM packaged file or not DRM supported.", srcFile);
				File sourceFile = new File(srcFile);
				File destFile = new File(targetFile);
				FileUtils.copyFile(sourceFile, destFile);
				return true;
			}
			result = wPackager.DoPackagingFsn2(oHomeDir, //fsdinit 폴더 FullPath 설정
					oServerID, //고객사 Key(default)
					srcFile, //암호화 대상 문서 FullPath + FileName
					targetFile, //암호화 된 문서 FullPath + FileName
					targetFile, //파일 명
					userID, //신청자 ID
					userName, //신청자 명
					userID, userName, StringUtil.isEmpty(deptID) ? "none" : deptID , StringUtil.isEmpty(deptName) ? "none" : deptName, //신청자 ID, 신청자 명, 부서코드, 부서명
					userID, userName, StringUtil.isEmpty(deptID) ? "none" : deptID , StringUtil.isEmpty(deptName) ? "none" : deptName, //신청자 ID, 신청자 명, 부서코드, 부서명
					"1" //각사 그룹한 등급 코드로 동일
			);
		} catch(DrmException pe) {
			throw pe;
		} catch(Exception e) {
			log.error("### DrmUtil.packagingDRM.Exception : ", e);
		}
		if(wPackager != null) {
			log.info("### packaged result value : {}, error code : {}, error value : {}", result , wPackager.getLastErrorNum(), wPackager.getLastErrorStr());
		}
		return result;
	}

	/**
	 * 
	 * @description : extractDRM 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static boolean extractDRM(String srcFile, String targetFile) {
		boolean result = false;
		int fileType = 0;
		WorkPackager wPackager = null;

		try {
			// 업로드 파일이 암호화 대상인 파일인 경우로 분기함
			if(!isDrmEnable()) {
				File sourceFile = new File(srcFile);
				File destFile = new File(targetFile);
				FileUtils.copyFile(sourceFile, destFile);
				return true;
			}
			wPackager = new WorkPackager();
			/* 로그 설정 적용(보안팀 디버깅용 코드), 설정경로(/app/logs/applog)에 디버그 로그 생성됨 */ 
			//복호화 된문서가 암호화된 문서를 덮어쓰지 않음
			wPackager.setOverWriteFlag(false);
			//01.대상 파일의 암호화 여부 확인
			fileType = wPackager.GetFileType(srcFile);
				log.info("### src file : {} / targetFile : {}", srcFile , targetFile);
				log.info("### file type: {}" , FileTypeStr(fileType));
			//02.업로드 파일이 drm이 아닌경우
			if(fileType != 103) {
				log.info("### This file [{}] is not DRM packaged file.", srcFile);
				File sourceFile = new File(srcFile);
				File destFile = new File(targetFile);
				FileUtils.copyFile(sourceFile, destFile);
				return true;
			}
			//02.대상 파일이 drm 파일인 경우
			result = wPackager.DoExtract(oHomeDir, //fsdinit 폴더 FullPath 설정
					oServerID, //고객사 Key(default)
					srcFile, //복호화 대상 문서 FullPath + FileName
					targetFile //복호화 된 문서 FullPath + FileName
					);
		} catch(Exception e) {
			log.error("### DrmUtil.extractDRM().Exception : ", e);
		}
		log.info("### depackaged result value : {} , error code : {}, error value : {}", result, (wPackager == null ? " -1" : wPackager.getLastErrorNum())
				, (wPackager == null ? " fail to load fasoo library" : wPackager.getLastErrorStr()));
		return result;
	}

	/**
	 * 
	 * @description : isDrmEnable 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public static boolean isDrmEnable() {
		return DrmUtil.isEnable;
	}
}
