package cjfw.wms.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderBoard;
import cjfw.core.utility.MessageUtil;
import cjfw.wms.cm.entity.CmFileUploadEntity;
import cjfw.wms.sys.dto.SysManualReqDto;
import cjfw.wms.sys.dto.SysManualResDto;
import cjfw.wms.sys.dto.SysProgramReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2026.01.29 
 * @description : ADMIN > 시스템운영 > 매뉴얼 정보를 조회 및 저장 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.01.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysManualService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "sysManualService.";
	
	private final CommonDao commonDao;

	private final FileUploaderBoard fileUploaderBoard;
	
	/**
	 * @description : 매뉴얼 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<SysManualResDto> getManualList(SysProgramReqDto sysProgramReqDto) {
		List<SysManualResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getManualList", sysProgramReqDto);

		// Tree 참조용 컬럼 추가(검색결과 처리용)
		for(SysManualResDto sysProgramResDto: list){
			String upperProgNo = sysProgramResDto.getProgNo().substring(0, sysProgramResDto.getProgNo().length() - 2);
			sysProgramResDto.setRefUpperProgNo(upperProgNo);
		}
		
		return list;
	}
	
	/**
	 * @description : 매뉴얼 파일 업로드
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveManual(SysManualReqDto sysManualReqDto, List<MultipartFile> files, List<FileUpload> fileInfoList) {
		
		// 필수값 체크
		if (sysManualReqDto.getProgCd() == null || "".equals(sysManualReqDto.getProgCd())) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"프로그램코드"})); // {0} 항목은 필수 입니다.
		}
		
		if (fileInfoList != null && fileInfoList.size() > 0) {
			// 첨부파일 삭제시 물리적인 삭제 없이 DB만 삭제
			for(FileUpload fileInfo : fileInfoList) {
				if ((CanalFrameConstants.DELETE).equals(fileInfo.getRowStatus())) {
					commonDao.delete("cmFileUploadService.deleteFileInfo", fileInfo);
				}
			}
		
			// 신규 첨부파일 존재시
			if (files != null && files.size() > 0) {
				// NAS 파일 업로드
				List<FileUpload> fileUploadList = fileUploaderBoard.saveFiles(files, fileInfoList, "manual", sysManualReqDto.getProgCd());
				
				for(FileUpload rFile : fileUploadList) {
					if ((CanalFrameConstants.INSERT).equals(rFile.getRowStatus())) {
						CmFileUploadEntity cmFileUploadEntity = new CmFileUploadEntity();
						cmFileUploadEntity.setType("Manual");
						cmFileUploadEntity.setRefKey(sysManualReqDto.getProgCd());
						cmFileUploadEntity.setSourceFileNm(rFile.getAttchFileNm());
						cmFileUploadEntity.setUploadedDirPath(rFile.getSavePathNm1());
						cmFileUploadEntity.setUploadedFileNm(rFile.getSavePathNm2());
						cmFileUploadEntity.setFileSize(rFile.getAttchFileSz());
						cmFileUploadEntity.setFileSeq(rFile.getFileSeq());
						commonDao.insert("cmFileUploadService.insertFileInfo", cmFileUploadEntity);
					}
				}
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
