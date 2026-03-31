package cjfw.wms.tm.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.edms.EdmsFileUploader;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderEdms;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsCenterDocRReqDto;
import cjfw.wms.ms.dto.MsCenterDocRResDto;
import cjfw.wms.ms.entity.MsCenterDocEntity;
import cjfw.wms.tm.dto.tempMonitor.TmTempDocReqDto;
import cjfw.wms.tm.dto.tempMonitor.TmTempMonitorDescReqDto;
import cjfw.wms.tm.dto.tempMonitor.TmTempMonitorDescResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.01.27
 * @description : 온도 모니터링 기능을 구현한 Service Class
 * @issues :
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.27 System Generated 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmLocationMonitorPreviewPopupService {

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "msCenterDocService.";
    private final CommonDao commonDao;
    private final UserContext userContext;

    private final EdmsFileUploader edmsFileUploader;
    private final FileUploaderEdms fileUploaderEdms;

    public static List<List<TmTempMonitorDescResDto>> displayUserStop(List<TmTempMonitorDescResDto> list, int divUnit) {
        List<List<TmTempMonitorDescResDto>> resultList = new ArrayList<>();
        Map<String, List<TmTempMonitorDescResDto>> resultMap = list.stream().collect(Collectors.groupingBy(TmTempMonitorDescResDto::getCarno, LinkedHashMap::new, Collectors.toList()));
        for (List<TmTempMonitorDescResDto> carGroupList : resultMap.values()) {
            Map<String, List<TmTempMonitorDescResDto>> deliverydtMap = carGroupList.stream().collect(Collectors.groupingBy(TmTempMonitorDescResDto::getDeliverydt, LinkedHashMap::new, Collectors.toList()));
            for (List<TmTempMonitorDescResDto> deliverydtGroupList : deliverydtMap.values()) {
                List<TmTempMonitorDescResDto> subList = new ArrayList<>();
                for (int i = 0; i < deliverydtGroupList.size(); i++) {
                    TmTempMonitorDescResDto item = deliverydtGroupList.get(i);
                    subList.add(item);

                    boolean isLastInGroup = (i == deliverydtGroupList.size() - 1);
                    boolean isBoundary = ((i + 1) % divUnit == 0);

                    //userStop 처리: divUnit 단위의 마지막 또는 그룹 마지막일 때만.
                    if (isBoundary || isLastInGroup) {
                        if (isLastInGroup) {
                            item.setUserStop(false);
                        }
                        resultList.add(subList);
                        subList = new ArrayList<>(); // 새 그룹 시작
                    }
                }
            }
        }
        return resultList;
    }

    public List<List<TmTempMonitorDescResDto>> getTempPreviewPopup(TmTempMonitorDescReqDto reqDto) {
        if (reqDto.getTimeUnit() == 0) {
            reqDto.setTimeUnit(10);
        }
        List<TmTempMonitorDescResDto> tempList;
        if ("Y".equals(reqDto.getDepArrYn())) {
            tempList = commonDao.selectList(TmLocationMonitorSummaryService.SERVICEID_PREFIX + "getTempMonitorDeparrDesc", reqDto);
        }
        else {
            if ("CAR".equals(reqDto.getBase())) {
                tempList = commonDao.selectList(TmLocationMonitorSummaryService.SERVICEID_PREFIX + "getTempMonitorDescByCar", reqDto);
            }
            else {
                tempList = commonDao.selectList(TmLocationMonitorSummaryService.SERVICEID_PREFIX + "getTempMonitorDescByCust", reqDto);
            }
        }
        tempList.forEach(item -> item.setTimeUnit(reqDto.getTimeUnit()));
        return displayUserStop(tempList, reqDto.getDivUnit());
    }

    public String saveUpload(List<MultipartFile> files, List<FileUpload> fileInfoList, TmTempDocReqDto docReqDto) {
        // 사업자번호 조회
        MsCenterDocRReqDto vatnoReqDto = new MsCenterDocRReqDto();
        vatnoReqDto.setReqNo(docReqDto.getReqNo());
        MsCenterDocRResDto vatnoResDto = commonDao.selectOne("msCenterDocService.selectVatnoByReqNo", vatnoReqDto);

        // EDMS와 연동하기 위한 파일명 변경
        int idx = 1;
        for (FileUpload fileInfo : fileInfoList) {
            fileInfo.setFileTp("cpt");
            String strFileName = vatnoResDto.getVatno() + "_" + fileInfo.getAttchFileNm();
            String strTransName = docReqDto.getReqNo() + "_" + idx + "_" + strFileName;
            fileInfo.setTransFileNm(strTransName);
            idx++;
        }

        // NAS 파일 업로드
        List<FileUpload> fileUploadList = fileUploaderEdms.saveFiles(files, fileInfoList);

        // EDMS 파일 업로드
        List<Map<String, Object>> rFileList = edmsFileUploader.batchFileRegister(fileUploadList, docReqDto.getReqDoc());

        if (CollectionUtils.isEmpty(rFileList)) {
            log.error("EDMS 파일 업로드 실패 - reqNo: {}", docReqDto.getReqNo());
            return CanalFrameConstants.MSG_COM_ERR_CODE;
        }

        for (int i = 0; i < rFileList.size(); i++) {
            Map<String, Object> file = rFileList.get(i);

            String strFileName = vatnoResDto.getVatno() + "_" + fileUploadList.get(i).getAttchFileNm();
            String strFileExtension = strFileName.substring(strFileName.lastIndexOf('.') + 1, strFileName.length());

            MsCenterDocEntity entity = new MsCenterDocEntity();
            entity.setSerialKey(docReqDto.getSerialKey());
            entity.setUploadResDocId(String.valueOf(file.get("docfileid")));
            entity.setFileName(strFileName);
            entity.setFileExtension(strFileExtension);
            entity.setFileSizeBytes(String.valueOf(file.get("filesize")));
            entity.setTransFileName(String.valueOf(file.get("orgname")));

            commonDao.insert(SERVICEID_PREFIX + "updateEdmsFile", entity);
        }

        // IF_MS_CENTER_DOC_S 인터페이스 테이블 INSERT
        MsCenterDocEntity masterEntity = new MsCenterDocEntity();
        masterEntity.setReqNo(docReqDto.getReqNo());
        masterEntity.setSerialKey(docReqDto.getSerialKey());
        masterEntity.setGUserId(userContext.getUserId());
        commonDao.insert(SERVICEID_PREFIX + "insertEdmsIf", masterEntity);

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
