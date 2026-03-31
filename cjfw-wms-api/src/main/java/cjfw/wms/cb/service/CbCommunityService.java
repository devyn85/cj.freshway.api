package cjfw.wms.cb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.SystemException;
import cjfw.core.exception.UserHandleException;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderBoard;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cb.dto.CbCommunityDetailResDto;
import cjfw.wms.cb.dto.CbCommunityReqDto;
import cjfw.wms.cb.dto.CbCommunityResDto;
import cjfw.wms.cb.entity.CbCommunityEntity;
import cjfw.wms.cm.entity.CmFileUploadEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.08.29 
 * @description : Admin > 시스템정보 > 시스템운영자열람자료 정보를 조회 및 저장 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CbCommunityService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cbCommunityService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	private final FileUploaderBoard fileUploaderBoard;
	
	/**
	 * @description : 시스템운영자열람자료 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<CbCommunityResDto> getCommunityList(CbCommunityReqDto cbCommunityReqDto) {
		List<CbCommunityResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getCommunityBoardList", cbCommunityReqDto);
		
		return list;
	}
	
	
	/**
	 * @description : 시스템운영자열람자료 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public CbCommunityDetailResDto getDetail(CbCommunityReqDto cbCommunityReqDto) {
		
		CbCommunityDetailResDto res = commonDao.selectOne(SERVICEID_PREFIX + "getCommunityBoardDetail", cbCommunityReqDto);
		
		// 조회수 증가
		commonDao.selectOne(SERVICEID_PREFIX +"updateReadCount", cbCommunityReqDto);
		
		return res;
	}
	
	/**
	 * @description : 시스템운영자열람자료 저장(CUD)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public String saveBoard(List<CbCommunityReqDto> cbCommunityReqDto, List<MultipartFile> files, List<FileUpload> fileInfoList) {
		if (null != cbCommunityReqDto) {
			for (CbCommunityReqDto board : cbCommunityReqDto) {	
				CbCommunityEntity cbCommunityEntity = ModelMapperUtil.map(board, userContext, CbCommunityEntity.class);
				
				cbCommunityEntity.setBrdUsrDcCode(board.getGDccode());
				cbCommunityEntity.setBrdUsrId(board.getGUserId());
				cbCommunityEntity.setAddWho(board.getGUserId());
				cbCommunityEntity.setEditWho(board.getGUserId());
				
				String boardKey = "";
				
				if ((CanalFrameConstants.INSERT).equals(board.getRowStatus())) {
					// 게시판번호 채번
					boardKey = (String) commonDao.selectOne(SERVICEID_PREFIX +"getBoardKey");
					cbCommunityEntity.setBrdNum(boardKey);
					
					commonDao.selectOne(SERVICEID_PREFIX +"insertBoard", cbCommunityEntity);
					
					if(cbCommunityEntity.getErrCode() != 0){
						throw new SystemException(new UserHandleException(""+"에러코드 : "+ cbCommunityEntity.getErrCode() + ", 에러메세지 : " + cbCommunityEntity.getErrMsg()));
					}
				} else if ((CanalFrameConstants.UPDATE).equals(board.getRowStatus())) {
					commonDao.selectOne(SERVICEID_PREFIX +"updateBoard", cbCommunityEntity);
					boardKey = cbCommunityEntity.getBrdNum();
					if(cbCommunityEntity.getErrCode() != 0){
						throw new SystemException(new UserHandleException(""+"에러코드 : "+ cbCommunityEntity.getErrCode() + ", 에러메세지 : " + cbCommunityEntity.getErrMsg()));
					}
				} else {
					commonDao.delete(SERVICEID_PREFIX +"deleteBoard", cbCommunityEntity);
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
						List<FileUpload> fileUploadList = fileUploaderBoard.saveFiles(files, fileInfoList, "board", boardKey);
						
						for(FileUpload rFile : fileUploadList) {
							if ((CanalFrameConstants.INSERT).equals(rFile.getRowStatus())) {
								CmFileUploadEntity cmFileUploadEntity = new CmFileUploadEntity();
								cmFileUploadEntity.setType("board");
								cmFileUploadEntity.setRefKey(boardKey);
								cmFileUploadEntity.setSourceFileNm(rFile.getAttchFileNm());
								cmFileUploadEntity.setUploadedDirPath(rFile.getSavePathNm1());
								cmFileUploadEntity.setUploadedFileNm(rFile.getSavePathNm2());
								cmFileUploadEntity.setFileSize(rFile.getAttchFileSz());
								commonDao.insert("cmFileUploadService.insertFileInfo", cmFileUploadEntity);
							}
						}
					}
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
