package cjfw.wms.tm.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.ApiClient;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.edms.ZnDocumentApi;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderEdms;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.entity.CmSmsMmsSendEntity;
import cjfw.wms.tm.dto.TmCrmWmsMemoReqDto;
import cjfw.wms.tm.dto.TmCrmWmsMemoResDto;
import cjfw.wms.tm.entity.TmCrmWmsMemoEntity;
import cjfw.wms.tm.entity.TmCrmWmsMemoFileUploadPopupEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Service
@Slf4j
@RequiredArgsConstructor
public class TmCrmWmsMemoService {
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     * @return .sqlx returns the location
     */
    private static final String SERVICEID_PREFIX = "tmCrmWmsMemoService.";
    private final CommonDao commonDao;
    private final FileUploaderEdms fileUploaderEdms;
    
    private final UserContext userContext;
    
    @Autowired
    private ApiClient apiClient;

    /**
     * @description : CRM요청관리 목록 조회
     * @issues :<pre>
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    public List<TmCrmWmsMemoResDto> getMasterList(TmCrmWmsMemoReqDto reqDto) {
        // 관리처 코드 배열 변환        
        if (ObjectUtils.isNotEmpty(reqDto)&& StringUtils.isNotEmpty(reqDto.getSchCustKey())) {
            reqDto.setMultiToCustkey(
                Arrays.stream(reqDto.getSchCustKey().split(","))
                      .map(String::trim)
                      .filter(s -> !s.isEmpty())
                      .collect(Collectors.toList())
            );
        }
        
        // 차량번호 배열 변환
        if (ObjectUtils.isNotEmpty(reqDto)&& StringUtils.isNotEmpty(reqDto.getSchCarCode())) {
            reqDto.setMultiCarNo(
                Arrays.stream(reqDto.getSchCarCode().split(","))
                      .map(String::trim)
                      .filter(s -> !s.isEmpty())
                      .collect(Collectors.toList())
            );
        }
        
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 처리이력 목록 조회
     * @issues :<pre>
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    public List<TmCrmWmsMemoResDto> getDetailHistoryList(TmCrmWmsMemoReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailHistoryList", reqDto);
    }
    
    /**
     * @description : 처리이력 상세조회
     * @issues :<pre>
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    public TmCrmWmsMemoResDto getDetailInfo(TmCrmWmsMemoReqDto reqDto) {
        return commonDao.selectOne(SERVICEID_PREFIX + "getDetailInfo", reqDto);
    }
    

    /**
     * @description : 처리상세 확정 저장
     * @issues :<pre>
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.22 jun (kthis77@cj.net) 생성 </pre>
     */
    public String saveCrmApply(List<TmCrmWmsMemoResDto> masterList, List<TmCrmWmsMemoResDto> histList) {
        
        // master grid 확정 처리.
        if (ObjectUtils.isNotEmpty(masterList)) {
            log.info("master grid save comfirm ");
            this.sendComfirmProc(masterList, "M");
        }
        
        // hist grid 확정 처리.
        if (ObjectUtils.isNotEmpty(histList)) {
            log.info("hist grid save comfirm ");
            this.sendComfirmProc(histList, "H");
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    
    
    /**
     * @description : CRM 일일 배송 목록 연동 처리
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.02.07 jun (kthis77@cj.net) 생성 </pre>
     */
    public String sendComfirmProc(List<TmCrmWmsMemoResDto> saveList , String typeCd) {
        
        String transTarget = "";
        String custFs = "";
        
        var entity = ModelMapperUtil.map(new TmCrmWmsMemoResDto(), userContext, TmCrmWmsMemoEntity.class);
        
        // 처리이력을 수정하거나
        for (TmCrmWmsMemoResDto reqDto : saveList) {
            // list 에 자동 mapping 되는지 확인 필요.
            reqDto.setGUserId(entity.getGUserId());
            commonDao.update(SERVICEID_PREFIX + "saveCrmApply", reqDto);
            
            transTarget = reqDto.getTransTarget();
            custFs = StringUtils.isNotEmpty(reqDto.getCustStrategy4()) ? reqDto.getCustStrategy4() : "" ; 
            
            // 이력 목록 저장시 master의 상태값을 03( 확정? )로 업데이트 처리. (memoLevel이 C인 경우)
            if ( "C".equals(reqDto.getMemoLevel())) {
                commonDao.selectList(SERVICEID_PREFIX + "updateCrmMemoStatus", reqDto);
            }
            
            
            //전송대상에 따른 분기처리 
            //01 : crm -> crm api 
            //03 : fs -> 거래처 ma에게 알림톡 전송 
            //04 : cs -> 영업 api
            switch (transTarget) {
                case "01":  // CRM api call
                    log.info("send api crm transTarget 01 called....");
                    this.sendCrmInfoCall(reqDto);
                    break;
                case "04":  // CS api call
                    log.info("send api cs transTarget 04 called....");
                        this.sendCsInfoCall(reqDto, typeCd);
                    break;
                default:
                    log.info("crm, fs, cs not called....");
                    break;
            }
            
            if (custFs.equals("24")) {
                log.info("send kko message custFs 24 called....");
                this.sendKkoCust(reqDto);
            }
        }
        
        
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 고객마감유형이 fs면  해당 ma에게 알림톡 발
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.02.07 jun (kthis77@cj.net) 생성 </pre>
     */
    private String sendKkoCust(TmCrmWmsMemoResDto reqDto) {
        
        CmSmsMmsSendEntity sendEntity = new CmSmsMmsSendEntity();
        
        String custKey = reqDto.getCustKey();
        String custName = reqDto.getCustName();
        String memoDate = reqDto.getMemoDate();
        String carNo = StringUtils.isNotEmpty(reqDto.getCarNo()) ? reqDto.getCarNo() : "";
        String driverPhone = StringUtils.isNotEmpty(reqDto.getDriverPhone()) ? reqDto.getDriverPhone() : "";
        String driverName = StringUtils.isNotEmpty(reqDto.getDriverName()) ? reqDto.getDriverName() : "";
        String memo = reqDto.getDescription();
        
        //알림톡 내용 작성
        StringBuilder message = new StringBuilder();
        
        message.append("[점포정보] ").append(custKey).append(" ").append(custName).append("\n");
        message.append("[배송일자] ").append(memoDate).append("\n");
        message.append("[배송차량] ").append(carNo).append("\n");
        message.append("[배송기사] ").append(driverName).append(" ").append(driverPhone).append("\n\n");
        message.append(memo).append("\n\n");
        message.append("감사합니다.");
        
        sendEntity.setSendMessage(message.toString());
        sendEntity.setSendTitle("배송이슈 안내");
        sendEntity.setSendPhone("1588-8161"); // 010-4455-6842
        sendEntity.setTemplateCode("SCM_FS배송이슈QA");
        sendEntity.setTemplateTitle("CJ프레시웨이 배송이슈 알림톡 입니다.");
        sendEntity.setFailType("LMS");
        sendEntity.setKkoType("AT");
        // QA용
        sendEntity.setProfileKey(ContextUtil.getProperty("cf.kakao.appkey")); // 카카오알림톡.APIKEY
        
        // 수신자추가 -거래처 ma
        List<TmCrmWmsMemoResDto> rcvUserList = commonDao.selectList(SERVICEID_PREFIX + "getCustRcvSMS", reqDto);
        if ( ObjectUtils.isNotEmpty(rcvUserList)) {
            for(TmCrmWmsMemoResDto rcvUseDto : rcvUserList) {
                sendEntity.setReceivePhone(rcvUseDto.getEmpPhone());
                sendEntity.setReceiveName(rcvUseDto.getEmpName());
                commonDao.insert(SERVICEID_PREFIX + "insertInspectKKO", sendEntity);
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /** 
     * @description : cs -> 영업 api
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.02.07 jun (kthis77@cj.net) 생성 </pre>
     */
    public String sendCsInfoCall(TmCrmWmsMemoResDto reqDto, String typeCd) {
        
        // cs api function 호출
        try {
            // WMS to CS 일별메모 전송 : https://safsqa2.ifresh.co.kr/com/wms/ifReceive/DlvDataInfo.fo
            String id = "DlvDataInfo.fo";
            String url = ContextUtil.getProperty("cf.api.cs.url") + id;
            
            // 연동 정보
            Map paramsMap = commonDao.selectOne(SERVICEID_PREFIX +"getData_API", reqDto);
            // 파일 정보
            List<HashMap<String, Object>> fileList = commonDao.selectList(SERVICEID_PREFIX+"getFileData_API", reqDto);
            
            JSONObject response = apiClient.callApi(url, null , null , paramsMap, fileList);
            
            if("F".equals(response.optString("err_cd"))){
                throw new RuntimeException("CRM API 연동 오류: " + response.optString("err_msg_ctt"));
            }
        } catch (ParseException | IOException | JSONException e) {
            log.error("try catch exception message : {}",e.getMessage());
            e.printStackTrace();
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * 
     * @description : crm api 전송. 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.02.07 jun (kthsi77@cj.net) 생성 </pre>
     */
    public String sendCrmInfoCall(TmCrmWmsMemoResDto reqDto) {
        String id = "MD004";
        String url = ContextUtil.getProperty("cf.api.crm.url") + id;
        String interfaceId = ContextUtil.getProperty("cf.api.crm.interfaceId")+ id;
        String interfaceAuthKey = ContextUtil.getProperty("cf.api.crm.interfaceAuthKey");
        
        // 연동 정보
        Map<String, Object> paramsMap = commonDao.selectOne(SERVICEID_PREFIX +"getData_API", reqDto);
        // 파일 정보
        List<HashMap<String, Object>> fileList = commonDao.selectList(SERVICEID_PREFIX+"getFileData_API", reqDto);
        
        //crm api function 호출
        try {
            JSONObject response = apiClient.callApi(url, interfaceId, interfaceAuthKey, paramsMap, fileList);
            
            if("F".equals(response.optString("err_cd"))){
                throw new RuntimeException("SRM API 연동 오류: " + response.optString("err_msg_ctt"));
            }
        } catch (ParseException | IOException | JSONException e) {
            log.error("try catch exception message : {}",e.getMessage());
            e.printStackTrace();
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 목록 또는 처리이력 삭제
     * @issues :<pre>
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    public String deleteCrm(List<TmCrmWmsMemoResDto> masterList, List<TmCrmWmsMemoResDto> histList) {
        
        if ( ObjectUtils.isNotEmpty(masterList) ) {
            for (TmCrmWmsMemoResDto reqDto : masterList) {
              commonDao.update(SERVICEID_PREFIX + "crmDelete", reqDto);
            }
        }
        if ( ObjectUtils.isNotEmpty(histList) ) {
            for (TmCrmWmsMemoResDto reqDto : histList) {
              commonDao.update(SERVICEID_PREFIX + "crmDelete", reqDto);
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 처리상세 수정 저장
     * @issues :<pre>
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    public String saveDetail(TmCrmWmsMemoReqDto saveDto) {
        
        switch (saveDto.getRowStatus()) {
            case CanalFrameConstants.INSERT:
                
                log.info("save insert info {}", saveDto.toString());
                commonDao.insert(SERVICEID_PREFIX + "insertDetail", saveDto);
                
                if ("C".equals(saveDto.getMemoLevel())) {
                    // memoLevel이 C인 경우, master의 status 값을 04로 업데이트 처리.
                    commonDao.update(SERVICEID_PREFIX + "updateCrmMemoStatus2", saveDto);
                }
                break;
            case CanalFrameConstants.UPDATE:
                log.info("save update info {}", saveDto.toString());
                commonDao.update(SERVICEID_PREFIX + "updateDetail", saveDto);
                break;
            default:
                break;
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 첨부파일 조회
     * @issues :<pre>
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.22 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    public List<Object> getPopupUploadFileList(TmCrmWmsMemoReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPopupUploadFileList", reqDto);
    }
    
    /**
     * 파일 정보를 저장합니다.
     * @param reqDto 요청 DTO
     * @return 저장 결과 코드
     */
    public String insertFileInfo(List<MultipartFile> files, List<FileUpload> fileInfoList, String serialKey, String issueNo) {
        // 파일명 업무에 맞게 변경
        String workPlaceId = ContextUtil.getProperty("cf.edms.workPlaceId");
        String strFileName = "";
        String strFileExtension = "";
        String strTransName = "";
        String strDocType = "100";
        
        int idx = 0;
        int dot_idx = -1;
        
        for(FileUpload fileInfo : fileInfoList) {
            strFileName = fileInfo.getAttchFileNm();
            strFileExtension = "";
            strTransName = "";

            dot_idx = strFileName.lastIndexOf('.');
            if (dot_idx > -1) {
                strFileExtension = strFileName.substring(dot_idx+1, strFileName.length());
                strTransName = "WMS_ISSUE_"+System.currentTimeMillis() + "_" + idx + "." + strFileExtension;
            } else {
                strTransName = "WMS_ISSUE_"+System.currentTimeMillis() + "_" + idx;
            }

            fileInfo.setTransFileNm(strTransName);
            idx++;
        }
        
        // NAS 파일 업로드
        List<FileUpload> fileUploadList = fileUploaderEdms.saveFiles(files, fileInfoList);
        
        // EDMS 파일 업로드
                for(FileUpload fileInfo : fileUploadList) {
                    String strTempFilePath = fileInfo.getSavePathNm1() + "/" + fileInfo.getTransFileNm();
                    Map rMap = ZnDocumentApi.registerTCS(strTempFilePath, fileInfo.getTransFileNm(), userContext.getUserNo(), "100");

                    // TO-DO : 파일정보 업무 TABLE에 MERGE
                    List<Map<String, Object>> fileList = (List)rMap.get("filelist");
                    for(Map<String, Object> file : fileList) {

                        strFileName = fileInfo.getAttchFileNm();
                        dot_idx = strFileName.lastIndexOf('.');
                        strFileExtension = strFileName.substring(dot_idx+1, strFileName.length());

                        TmCrmWmsMemoFileUploadPopupEntity entity = new TmCrmWmsMemoFileUploadPopupEntity();
                        entity.setSerialKey(serialKey);
                        entity.setDocType(strDocType);
                        entity.setFileName(strFileName);
                        entity.setIssueNo(issueNo);
                        entity.setFileExtension(strFileExtension);
                        entity.setFileLocation(fileInfo.getSavePathNm1() + "/" + fileInfo.getTransFileNm());
                        entity.setFileSizeBytes(fileInfo.getAttchFileSz());
                        entity.setTransFileName(fileInfo.getTransFileNm());
                        entity.setUploadResDocId(String.valueOf(file.get("docfileid")));
                        entity.setUploadFileName(String.valueOf(file.get("orgname")));
                        entity.setUploadWorkplaceId(workPlaceId);

                        commonDao.insert(SERVICEID_PREFIX + "insertFileInfo", entity);
                }
            }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * 파일 삭제 정보를 저장합니다.
     * @param reqDto 요청 DTO
     * @return 삭제 결과 코드
     */
    public String saveFileDelete(TmCrmWmsMemoReqDto reqDto) {
        commonDao.update(SERVICEID_PREFIX + "saveFileDelete", reqDto);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * 파일 다운로드 횟수를 갱신합니다.
     * @param reqDto 요청 DTO
     * @return 갱신 결과 코드
     */
    public String updateDownloadCnt(TmCrmWmsMemoReqDto reqDto) {
        commonDao.update(SERVICEID_PREFIX + "updateDownloadCnt", reqDto);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * 고객 수신 SMS 목록을 조회합니다.  - 알림톡/CRM 보조 (선택사용)
     *  
     * @param reqDto 요청 DTO
     * @return SMS 목록 리스트
     */
    public List<Object> getCustRcvSMS(TmCrmWmsMemoReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getCustRcvSMS", reqDto);
    }
    
    /**
     * 알림톡/검수 정보를 저장합니다.
     * @param reqDto 요청 DTO
     * @return 저장 결과 코드
     */
    public String insertInspectKKO(TmCrmWmsMemoReqDto reqDto) {
        commonDao.insert(SERVICEID_PREFIX + "insertInspectKKO", reqDto);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
