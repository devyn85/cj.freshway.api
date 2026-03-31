package cjfw.core.apiLogging;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.email.model.MailAttachFile;
import cjfw.core.email.model.MailMessage;
import cjfw.core.email.utility.MailUtil;
import cjfw.core.email.utility.UserAuthentication;
import cjfw.core.exception.SystemException;
import cjfw.core.utility.ContextUtil;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.*;
import jakarta.mail.internet.MimeMessage.RecipientType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.*;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : yewon.kim
 * @date        : 2026.01.25
 * @description : API 호출 및 수신 로그 DB
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.25        yewon.kim          최초생성
 */
@Service
@RequiredArgsConstructor
public class LoggingService {

	private final CommonDao commonDao;

    /**
     * API 호출시 로그 기록
     **/
    public void saveApiLog(Map inParams) {
        commonDao.insert("loggingService.saveApiLog", inParams);
    }
}