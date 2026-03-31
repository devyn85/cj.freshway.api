package cjfw.wms.tm.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.edms.ZnDocumentApi;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderEdms;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsCustDeliveryInfoPDetailResDto;
import cjfw.wms.ms.entity.MsCustEntity;
import cjfw.wms.tm.dto.TmIssueReqDto;
import cjfw.wms.tm.dto.TmIssueResDto;
import cjfw.wms.tm.entity.TmIssueFileUploadPopupEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author         : YeoSeungCheol (pw6375@cj.net) 
 * @date         : 2025.10.23 
 * @description : 배송이슈 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmIssueService {
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     * @return .sqlx returns the location
     */
    private static final String SERVICEID_PREFIX = "tmIssueService.";
    private final CommonDao commonDao;
    private final FileUploaderEdms fileUploaderEdms;

    private final UserContext context;

    /**
     * @description : 배송이슈 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    public List<TmIssueResDto> getMasterList(TmIssueReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 배송이슈 저장(신규/수정)
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    public String saveMasterList(List<TmIssueReqDto> list) {
        if (ObjectUtils.isNotEmpty(list)) {
            for (TmIssueReqDto reqDto : list) {
                switch (reqDto.getRowStatus()) {
                    case CanalFrameConstants.INSERT:
                        commonDao.insert(SERVICEID_PREFIX + "insertTMIssue", reqDto);
                        break;
                    case CanalFrameConstants.UPDATE:
                        commonDao.update(SERVICEID_PREFIX + "updateTMIssue", reqDto);
                        break;
                    default:
                        // S131 위배 방지 및 예외 상황 로그 기록
                        // 로직상 아무것도 안 하더라도 주석을 남겨 의도를 명확히 하는 것이 좋습니다.
                        log.debug("Unknown row status: {}", reqDto.getRowStatus());
                        break;
                }
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 배송이슈 삭제
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    public String deleteMasterList(List<TmIssueReqDto> list) {
        if (ObjectUtils.isNotEmpty(list)) {
            for (TmIssueReqDto reqDto : list) {
                commonDao.update(SERVICEID_PREFIX + "deleteTMIssue", reqDto);
                commonDao.update(SERVICEID_PREFIX + "saveFileDeleteForissueNo", reqDto);
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 배송이슈 확정
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    public String confirmMasterList(List<TmIssueReqDto> list) {
        if (ObjectUtils.isNotEmpty(list)) {
            for (TmIssueReqDto reqDto : list) {
                commonDao.update(SERVICEID_PREFIX + "updateConfirmTMIssue", reqDto);
                commonDao.update(SERVICEID_PREFIX + "insertTMIssueIFData", reqDto);
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 배송이슈 첨부파일 업로드 팝업 - 첨부파일 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
     */
    public List<Object> getPopupUploadFileList(TmIssueReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPopupUploadFileList", reqDto);
    }

    /**
     * @description : 배송이슈 첨부파일 업로드 팝업 - 첨부파일 업로드
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.23 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
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
            Map rMap = ZnDocumentApi.registerTCS(strTempFilePath, fileInfo.getTransFileNm(), context.getUserNo(), "100");

            // TO-DO : 파일정보 업무 TABLE에 MERGE
            List<Map<String, Object>> fileList = (List)rMap.get("filelist");
            for(Map<String, Object> file : fileList) {

                strFileName = fileInfo.getAttchFileNm();
                dot_idx = strFileName.lastIndexOf('.');
                strFileExtension = strFileName.substring(dot_idx+1, strFileName.length());

                TmIssueFileUploadPopupEntity entity = new TmIssueFileUploadPopupEntity();
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
     * @param reqDto 파일 삭제 요청 DTO
     * @return 저장 결과 코드
     */
    public String saveFileDelete(TmIssueReqDto reqDto) {
        commonDao.update(SERVICEID_PREFIX + "saveFileDelete", reqDto);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 파일 목록 삭제 기능을 구현한 Method 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.02.04 jun (kthis77@cj.net) 생성 </pre>
     */
    public String saveFileDeleteList(List<TmIssueReqDto> delList) {
        if (ObjectUtils.isEmpty(delList)) {
            for (TmIssueReqDto reqDto : delList) {
                commonDao.update(SERVICEID_PREFIX + "saveFileDelete", reqDto);
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * 이슈번호로 파일 삭제 정보를 저장합니다.
     * @param reqDto 파일 삭제 요청 DTO
     * @return 저장 결과 코드
     */
    public String saveFileDeleteForIssueNo(TmIssueReqDto reqDto) {
        commonDao.update(SERVICEID_PREFIX + "saveFileDeleteForISSUENO", reqDto);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * 파일 다운로드 횟수를 갱신합니다.
     * @param reqDto 다운로드 요청 DTO
     * @return 갱신 결과 코드
     */
    public String updateDownloadCnt(TmIssueReqDto reqDto) {
        commonDao.update(SERVICEID_PREFIX + "updateDownloadCnt", reqDto);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * 배송이슈 인터페이스 데이터를 저장합니다.
     * @param reqDto 인터페이스 데이터 요청 DTO
     * @return 저장 결과 코드
     */
    public String insertTMIssueIFData(TmIssueReqDto reqDto) {
        commonDao.insert(SERVICEID_PREFIX + "insertTMIssueIFData", reqDto);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /** @description : 배송 이슈 엑셀 업로드 validation 체크
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.02.05 jun (kthis77@cj.net) 생성 </pre>
    */
    public List<TmIssueResDto> getValidateExcelList(List<TmIssueReqDto> excelList) {
        
        List<TmIssueResDto> result = new ArrayList<TmIssueResDto>();
        
        if(ObjectUtils.isNotEmpty(excelList)) {
            result = commonDao.selectList(SERVICEID_PREFIX + "getValidateExcelList", excelList);
            // 고객회사코드(StorerKey) + 출고일자(deliveryDt) + 물류센터코드(dcCode) + 관리처코드(custKey) + 이슈코드(issueCode)
            this.getExcelDataDuplicationCheck(result);
            
        }
        return result;
    }
    
    /**
     * 엑셀 업로드 데이터의 중복을 체크합니다.
     * 기준: 고객회사코드(StorerKey) + 출고일자(deliveryDt) + 물류센터코드(dcCode) + 관리처코드(custKey) + 이슈코드(issueCode)
     */
    public void getExcelDataDuplicationCheck(List<TmIssueResDto> list) {
        
        if (ObjectUtils.isEmpty(list)) return;

        // 1. 중복 확인을 위한 Set 생성 (복합 키 저장)
        Set<String> seenKeys = new HashSet<>();

        for (TmIssueResDto dto : list) {
            // 2. 복합 키 생성 (Null 방지를 위해 Objects.toString 사용 및 공백 제거)
            String key = new StringBuilder()
                .append(Objects.toString(dto.getStorerKey(), "").trim()).append("|")
                .append(Objects.toString(dto.getDeliveryDt(), "").trim()).append("|")
                .append(Objects.toString(dto.getDcCode(), "").trim()).append("|")
                .append(Objects.toString(dto.getCustKey(), "").trim()).append("|")
                .append(Objects.toString(dto.getIssueCode(), "").trim())
                .toString();

            // 3. 중복 여부 확인
            if (seenKeys.contains(key)) {
                // [중복됨] 이미 존재하는 키인 경우
                dto.setDuplicateYn("N"); 
            } else {
                // [정상] 처음 나타난 키인 경우 Set에 추가
                seenKeys.add(key);
            }
        }
    }
    /**
	 * @description :  개인정보 복호화(단건)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.25 생성 </pre>
	 */
	public TmIssueResDto getDetailList(TmIssueReqDto dto) {
		TmIssueResDto result = new TmIssueResDto();
		result = commonDao.selectOne(SERVICEID_PREFIX + "getDetailList",dto); 
		
		return result;
	}
}
