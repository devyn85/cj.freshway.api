package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.ms.dto.MsPopUploadLocationReqDto;
import cjfw.wms.ms.dto.MsPopUploadLocationResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net)
 * @date : 2025.09.02
 * @description : 로케이션 일괄등록 팝업 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.02 YeoSeungCheol (pw6375@cj.net) 최초 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsPopUploadLocationService {

    /** Namespace must match lowerCamel(ServiceClassName) per convention */
    private static final String SERVICEID_PREFIX = "msPopUploadLocationService.";
    private static final String PACKAGE_NAME = "SPMS_LOCATION";
    private static final String DEFAULT_PROCESSTYPE = "LOCHK";

    private final CommonDao commonDao;
    private final UserContext userContext;

    /**
     * Validation only (DATACHECK)
     */
    public List<MsPopUploadLocationResDto> validate(MsPopUploadLocationReqDto dto) {
        if (dto.getProcesstype() == null || dto.getProcesstype().isEmpty()) {
            dto.setProcesstype(DEFAULT_PROCESSTYPE);
        }
        // 1) clear temp
        commonDao.delete(SERVICEID_PREFIX + "deleteTempTable", dto);
        // 2) insert temp
        commonDao.insert(SERVICEID_PREFIX + "insertTempTable", dto);

        // 3) build procedure params and call
        dto.setAvc_COMMAND("DATACHECK");
        dto.setAvc_EXECUTEMODE("NOCOMMIT");
        ProcedureParametersFactory.initParamDto(dto, dto, PACKAGE_NAME);
        String[] keyList = {"PROCESSTYPE","PROCESSCREATOR","SPID","DCCODE","STORERKEY"};
        Object[] valueList = {dto.getProcesstype(), dto.getGUserId(), dto.getGSpid(), dto.getGDccode(), dto.getGStorerkey()};
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, dto);

        // 4) handle result
        String resultCode = dto.getResultCode();
        String resultMessage = dto.getResultMessage();
        if (!"0".equals(resultCode)) {
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"로케이션 유효성검증"}) + resultMessage);
        }

        // 5) fetch results
//        return commonDao.selectList(SERVICEID_PREFIX + "getDataPopExcelUploadLocResult", dto);
        List<MsPopUploadLocationResDto> list =
        	    commonDao.selectList(SERVICEID_PREFIX + "getDataPopExcelUploadLocResult", dto);

        	// DATACHECK에서 ERRCODE='N'은 "검증통과(적용 전)"인데
        	// 프론트가 N을 실패로 오해하는 경우가 있어서 validate 응답에서만 Y로 보여주기
        	for (MsPopUploadLocationResDto r : list) {
        	    if ("N".equals(r.getProcessYn())) {
        	        r.setProcessYn("Y");
        	    }
        	}

        	return list;
    }

    /**
     * Upload (DATAUPLOAD)
     */
    public List<MsPopUploadLocationResDto> upload(MsPopUploadLocationReqDto dto) {
        if (dto.getProcesstype() == null || dto.getProcesstype().isEmpty()) {
            dto.setProcesstype(DEFAULT_PROCESSTYPE);
        }
        // 1) clear temp
        commonDao.delete(SERVICEID_PREFIX + "deleteTempTable", dto);
        // 2) insert temp
        commonDao.insert(SERVICEID_PREFIX + "insertTempTable", dto);

        // 3) build procedure params and call
        dto.setAvc_COMMAND("DATAUPLOAD");
        dto.setAvc_EXECUTEMODE("NOCOMMIT");
        ProcedureParametersFactory.initParamDto(dto, dto, PACKAGE_NAME);
        String[] keyList = {"PROCESSTYPE","PROCESSCREATOR","SPID","DCCODE","STORERKEY"};
        Object[] valueList = {dto.getProcesstype(), dto.getGUserId(), dto.getGSpid(), dto.getGDccode(), dto.getGStorerkey()};
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, dto);

        // 4) handle result
        String resultCode = dto.getResultCode();
        String resultMessage = dto.getResultMessage();
        if (!"0".equals(resultCode)) {
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"로케이션 업로드"}) + resultMessage);
        }

        // 5) fetch results
        return commonDao.selectList(SERVICEID_PREFIX + "getDataPopExcelUploadLocResult", dto);
    }

    /**
     * Backward-compatible entry: use validateOnly flag
     */
    public List<MsPopUploadLocationResDto> saveExcel(MsPopUploadLocationReqDto dto) {
        if (Boolean.TRUE.equals(dto.getValidateOnly())) {
            return validate(dto);
        }
        return upload(dto);
    }
}

