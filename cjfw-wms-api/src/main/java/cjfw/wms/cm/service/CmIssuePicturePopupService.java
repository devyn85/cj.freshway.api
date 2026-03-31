package cjfw.wms.cm.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.email.model.MailMessage;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmFaxReqDto;
import cjfw.wms.cm.dto.CmIssuePicturePopupReqDto;
import cjfw.wms.cm.dto.CmIssuePicturePopupResDto;
import cjfw.wms.cm.dto.CmSendEmailReqDto;
import cjfw.wms.cm.entity.CmEmailLogEntity;
import cjfw.wms.st.dto.StExDCStorageReqDto;
import cjfw.wms.st.dto.StExDCStorageResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author :  KimSunHo(sunhokim6229@cj.net) 
 * @date : 2026.02.20 
 * @description : 배송이슈 사진 파일 조회 서비스
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.20  KimSunHo(sunhokim6229@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmIssuePicturePopupService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmIssuePicturePopupService.";

	private final CommonDao commonDao;
	
	private final UserContext userContext;

    /**
     * @description : 배송이슈 사진 파일 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.02.20   KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<CmIssuePicturePopupResDto> getMasterList(CmIssuePicturePopupReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

}
