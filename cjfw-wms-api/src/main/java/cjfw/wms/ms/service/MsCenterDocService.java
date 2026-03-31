package cjfw.wms.ms.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.edms.EdmsFileUploader;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderEdms;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsCenterDocReqDto;
import cjfw.wms.ms.dto.MsCenterDocResDto;
import cjfw.wms.ms.entity.MsCenterDocEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.23
 * @description : 기준정보 > 센터기준정보 > 센터서류 목록 조회 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCenterDocService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCenterDocService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	private final EdmsFileUploader edmsFileUploader;
	private final FileUploaderEdms fileUploaderEdms;
	
	/**
	 * 
	 * @description : 목록조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsCenterDocResDto> getMasterList(MsCenterDocReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * 
	 * @description : 상품목록 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveMasterList(List<MsCenterDocReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsCenterDocEntity.class);				
				commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
				commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	/**
	 * @description : 차량정보 파일 업로드
	 * @issues :<pre> 
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String centerDocSaveFileUpload(MsCenterDocReqDto msCenterDocReqDto, List<MultipartFile> files, List<FileUpload> fileInfoList) {
		// EDMS와 연동하기 위한 파일명 변경
        int idx = 1;
        for(FileUpload fileInfo : fileInfoList) {
            String strFileName = fileInfo.getAttchFileNm();
            String strTransName = "";
            
            int dot_idx = strFileName.lastIndexOf('.');
            if (dot_idx > -1) {
                strTransName = msCenterDocReqDto.getReqNo() + "_" + idx + "_" + strFileName;
            } else {
                strTransName = msCenterDocReqDto.getReqNo() + "_" + idx + "_" + strFileName;
            }
            fileInfo.setTransFileNm(strTransName);
            idx++;
        }
        
		// NAS 파일 업로드
		List<FileUpload> fileUploadList = fileUploaderEdms.saveFiles(files, fileInfoList);
		
		// EDMS 파일 업로드
		List<Map<String, Object>> rFileList = edmsFileUploader.batchFileRegister(fileUploadList, msCenterDocReqDto.getReqDoc());	
		
		for (int i = 0; i < rFileList.size(); i++) {
			Map<String, Object> file = rFileList.get(i);
			
			String strFileName = fileUploadList.get(i).getAttchFileNm();
			String strFileExtension = strFileName.substring(strFileName.lastIndexOf('.') + 1, strFileName.length());
			
			MsCenterDocEntity entity = new MsCenterDocEntity();
			entity.setSerialKey(msCenterDocReqDto.getSerialKey());
			entity.setUploadResDocId(String.valueOf(file.get("docfileid")));
			entity.setFileName(strFileName);
			entity.setFileExtension(strFileExtension);
			entity.setFileSizeBytes(String.valueOf(file.get("filesize")));
			entity.setTransFileName(String.valueOf(file.get("orgname")));
//
            commonDao.insert(SERVICEID_PREFIX + "updateMasterFile", entity);
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 센터서류 파일 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.05 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<MsCenterDocResDto> getMasterFileList(MsCenterDocReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterFileList", dto);
	}
}
