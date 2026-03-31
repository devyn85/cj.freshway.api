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
import cjfw.wms.cm.dto.CmSendEmailReqDto;
import cjfw.wms.cm.entity.CmEmailLogEntity;
import cjfw.wms.st.dto.StExDCStorageReqDto;
import cjfw.wms.st.dto.StExDCStorageResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.07.22 
 * @description : Fax 서비스
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.22 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmFaxService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmFaxService.";

	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
     * @description : FAX 전송
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.01 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public String saveFax(CmFaxReqDto paramDto) {
        
        CmFaxReqDto reqDto = ModelMapperUtil.map(paramDto, CmFaxReqDto.class);
        
        // FAX 일련번호 가져오기
        String batchId = (String) commonDao.selectOne("cmFaxService.getKey");
        reqDto.setTrBatchid(batchId);
        
        String sendfaxnum = reqDto.getSendFaxno();
        reqDto.setTrSendfaxnum(sendfaxnum);

        
        // TO-DO(RD) : 현재 보여지는 RD 리포트의 이미지를 서버의 특정 PATH에 저장시키는 로직 필요
        // TO-DO(RD) : 서버 특정 PATH "/app/uploads/dwaylo/upload/fax" 이여야 함
        // TO-DO(RD) : 저장시켜주는 파일명 서로간 협의해야 함 (ex. fc_20250314083631cgy6572_1184054071.tif)
        // ContextUtil.getProperty("cf.upload.dir.fax")
        if (reqDto.getTrDocname() == null || reqDto.getTrDocname().equals("") || reqDto.getTrDocname().isEmpty()) {
            reqDto.setTrDocname(reqDto.getAttchFileName()); // 예제 파일 (개발서버에 올라간 파일명임)
        }
        
        // 1번 수신자에게 발송  
        // FAX Meta 정보 등록
        commonDao.insert(SERVICEID_PREFIX + "insertFaxMeta", reqDto);
        
        // FAX Msg 정보 등록
        commonDao.insert(SERVICEID_PREFIX + "insertFaxMsg", reqDto);
        
        // FAX 발송 상태값 변경 (TR_SENDSTAT = '0' 값이면 전송 됨)
        commonDao.update(SERVICEID_PREFIX + "updateFaxMetaStatus", reqDto);
        
        // 2번 수신자에게 발송
        //reqDto.getTrPhone2()
        if (reqDto.getTrPhone2() != null && !reqDto.getTrPhone2().equals("") && !reqDto.getTrPhone2().isEmpty()) {
            // FAX 일련번호 가져오기
            batchId = (String) commonDao.selectOne("cmFaxService.getKey");
            reqDto.setTrBatchid(batchId);
            reqDto.setTrPhone(reqDto.getTrPhone2());
            
            // FAX Meta 정보 등록
            commonDao.insert(SERVICEID_PREFIX + "insertFaxMeta", reqDto);
            
            // FAX Msg 정보 등록
            commonDao.insert(SERVICEID_PREFIX + "insertFaxMsg", reqDto);
            
            // FAX 발송 상태값 변경 (TR_SENDSTAT = '0' 값이면 전송 됨)
            commonDao.update(SERVICEID_PREFIX + "updateFaxMetaStatus", reqDto);
        
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 팩스 전송 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.02.21   KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<StExDCStorageResDto> getDataFaxHistlist(CmFaxReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDataFaxHistlist", reqDto);
    }

}
