package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.edms.EdmsFileDownloader;
import cjfw.core.edms.EdmsFileUploader;
import cjfw.core.edms.ZnDocumentApi;
import cjfw.core.file.FileDownload;
import cjfw.core.file.FileDownloader;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploader;
import cjfw.core.file.FileUploaderEdms;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.ms.dto.MsPlantXSLDetailReqDto;
import cjfw.wms.ms.dto.MsPlantXSLDetailResDto;
import cjfw.wms.ms.dto.MsPlantXSLListReqDto;
import cjfw.wms.ms.dto.MsPlantXSLListResDto;
import cjfw.wms.ms.dto.MsPlantXSLSaveReqDto;
import cjfw.wms.ms.dto.MsPlantXSLSaveResDto;
import cjfw.wms.ms.entity.MsPlantXSLFileEntity;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.05.29
 * @description : 저장위치정보 Controller Class
 * @issues : <pre>
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.29 KwonJungYun (jungyun8667@cj.net) 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsPlantXSLService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msPlantXSLService.";

	private final CommonDao commonDao;

	private final FileUploader fileUploader;

	private final FileDownloader fileDownloader;

	private final EdmsFileUploader edmsFileUploader;

	private final EdmsFileDownloader edmsFileDownloader;

	private final FileUploaderEdms fileUploaderEdms;

	private final UserContext userContext;

	private final UserContext context;

	/**
	 * @description : 저장위치정보 목록 페이징 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<MsPlantXSLListResDto> getPlantXSLList(MsPlantXSLListReqDto msPlantXSLReqDto) {
		List<MsPlantXSLListResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getPlantXSLList", msPlantXSLReqDto);
		return result;
	}

	/**
	 * @description : 저장위치정보 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public MsPlantXSLDetailResDto getPlantXSLDtl(MsPlantXSLDetailReqDto msPlantXSLReqDto) {
		MsPlantXSLDetailResDto resDto = new MsPlantXSLDetailResDto();
		msPlantXSLReqDto.setStorageloc(msPlantXSLReqDto.getStorageloc().replaceFirst("^2170-", ""));
		resDto = commonDao.selectOne(SERVICEID_PREFIX + "getPlantXSLDtl", msPlantXSLReqDto);
		msPlantXSLReqDto.setOrganize(msPlantXSLReqDto.getPlant()+"-"+msPlantXSLReqDto.getStorageloc());
		

        // 계약기간 연도 범위 조회
        List<Map<String, Object>> contractList =
            commonDao.selectList(SERVICEID_PREFIX + "getContractDateRangeList", msPlantXSLReqDto);

        // 연도 추출
        Set<Integer> yearSet = new TreeSet<>(Collections.reverseOrder());;
        for (Map<String, Object> row : contractList) {
            String fromDateStr = (String) row.get("FROMDATE");
            String toDateStr = (String) row.get("TODATE");

            int fromYear = Integer.parseInt(fromDateStr.substring(0, 4));
            int toYear = Integer.parseInt(toDateStr.substring(0, 4));

            for (int year = fromYear; year <= toYear; year++) {
                yearSet.add(year);
            }
        }

        List<Map<String, Object>> yearOptionList = new ArrayList<>();
        for (Integer year : yearSet) {
            Map<String, Object> map = new HashMap<>();
            map.put("contractYearName", String.valueOf(year));
            map.put("contractYearCode", String.valueOf(year));
            yearOptionList.add(map);
        }
        resDto.setContractyears(yearOptionList);

		return resDto;
	}

	/**
	 * @description : 저장위치정보 저장
	 * @issues :
	 * -----------------------------------------------------------
	 * DATE AUTHOR MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 KwonJungYun (jungyun8667@cj.net) 생성
	 */
	public String savePlantXSL(MsPlantXSLSaveReqDto msPlantXSLSaveReqDto) {
		commonDao.update(SERVICEID_PREFIX + "updateMsPlanxsl", msPlantXSLSaveReqDto);
//		// 첨부파일 삭제
//		commonDao.delete(SERVICEID_PREFIX + "deleteMsPlantxslFileList", msPlantXSLSaveReqDto);
//		// 첨부파일 저장
//		List<MsPlantXSLFileEntity> fileList = msPlantXSLSaveReqDto.getFileList();
//		if(!ObjectUtils.isEmpty(fileList)) {
//			for(MsPlantXSLFileEntity file: fileList) {
//				file.setDocId(msPlantXSLSaveReqDto.getSerialkey());
//				commonDao.insert(SERVICEID_PREFIX + "insertMsPlantxslFile", file);
//			}
//		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	public List<Map<String,String>> getPlantList(){
		return commonDao.selectList(SERVICEID_PREFIX + "getDataSelectPlant");
	}

	/**
	 * @description : 저장위치정보 파일정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.02 JiHoPark  생성 </pre>
	 */
	public List<MsPlantXSLFileEntity> getMasterFileList(MsPlantXSLSaveReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterFileList", dto);
	}

	/**
	 * @description : 저장위치정보 첨부파일 COUNT
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.18 JiHoPark  생성 </pre>
	 */
	public MsPlantXSLDetailResDto getAtchFileCnt(MsPlantXSLSaveReqDto dto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getAtchFileCnt", dto);
	}

	/**
	 * @description : 저장위치정보 파일 업로드
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03 JiHoPark (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveFileUpload(List<MultipartFile> files, List<FileUpload> fileInfoList, String serialkey) {
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
				strTransName = "WMS_PLANTXSL_"+System.currentTimeMillis() + "_" + idx + "." + strFileExtension;
			} else {
	        	strTransName = "WMS_PLANTXSL_"+System.currentTimeMillis() + "_" + idx;
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

				MsPlantXSLFileEntity entity = new MsPlantXSLFileEntity();
                entity.setSerialkey(serialkey);
                entity.setDocType(strDocType);
                entity.setFileName(strFileName);
                entity.setFileExtension(strFileExtension);
                entity.setFileLocation(fileInfo.getSavePathNm1() + "/" + fileInfo.getTransFileNm());
                entity.setFileSizeBytes(fileInfo.getAttchFileSz());
                entity.setTransFileName(fileInfo.getTransFileNm());
                entity.setUploadResDocId(String.valueOf(file.get("docfileid")));
                entity.setUploadFileName(String.valueOf(file.get("orgname")));
                entity.setUploadWorkplaceId(workPlaceId);

                commonDao.insert(SERVICEID_PREFIX + "insertMsPlantxslFile", entity);
			}
		}

        return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	/**
	 * @description : 저장위치정보 파일 다운로드
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03 JiHoPark (breaker3317@cj.net) 생성 </pre>
	 */
	public void saveFileDownload(HttpServletResponse response, FileDownload fileDownload) {
		edmsFileDownloader.downloadFile(response, fileDownload.getSaveFileNm(), "100", fileDownload.getAttchFileNm());
	}

	/**
	 * @description : 저장위치정보 파일 삭제
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03 JangGwaJiHoParkngSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveFileDelete(MsPlantXSLSaveReqDto dto) {
		MsPlantXSLFileEntity entity = new MsPlantXSLFileEntity();
        entity.setSerialkey(dto.getSerialkey());

        commonDao.delete(SERVICEID_PREFIX + "deleteMsPlantxslFile", entity);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
