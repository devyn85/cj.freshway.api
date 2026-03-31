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
import cjfw.wms.cb.dto.CbNoticeDetailResDto;
import cjfw.wms.cb.dto.CbNoticeRecvReqDto;
import cjfw.wms.cb.dto.CbNoticeRecvResDto;
import cjfw.wms.cb.dto.CbNoticeReqDto;
import cjfw.wms.cb.dto.CbNoticeResDto;
import cjfw.wms.cb.entity.CbNoticeEntity;
import cjfw.wms.cm.entity.CmFileUploadEntity;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.09.19 
 * @description : 기준정보 > 게시판관리 > 공지사항 정보를 조회 및 저장 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.19 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CbNoticeService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cbNoticeService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	private final FileUploaderBoard fileUploaderBoard;
	
	/**
	 * @description : 공지사항 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<CbNoticeResDto> getNoticeList(CbNoticeReqDto cbNoticeReqDto) {
		List<CbNoticeResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getNoticeList", cbNoticeReqDto);
		
		return list;
	}
	
	
	/**
	 * @description : 공지사항 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public CbNoticeDetailResDto getNoticeDetail(CbNoticeReqDto cbNoticeReqDto) {
		
		CbNoticeDetailResDto res = commonDao.selectOne(SERVICEID_PREFIX + "getNoticeDetail", cbNoticeReqDto);
		
		// 조회수 증가
		commonDao.selectOne(SERVICEID_PREFIX +"updateReadCount", cbNoticeReqDto);
		
		return res;
	}
	
	/**
	 * @description : 수신처 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<CbNoticeRecvResDto> getRecvList(CbNoticeReqDto cbNoticeReqDto) {
		
		List<CbNoticeRecvResDto> res = commonDao.selectList(SERVICEID_PREFIX + "getRecvList", cbNoticeReqDto);
		
		return res;
	}
	
	/**
	 * @description : 공지사항 저장(CUD)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public String saveBoard(List<CbNoticeReqDto> cbNoticeReqDto, List<MultipartFile> files, List<FileUpload> fileInfoList, List<CbNoticeRecvReqDto> recvGroupList) {
		if (null != cbNoticeReqDto) {
			for (CbNoticeReqDto board : cbNoticeReqDto) {	
				CbNoticeEntity cbNoticeEntity = ModelMapperUtil.map(board, userContext, CbNoticeEntity.class);
				
				cbNoticeEntity.setBrdUsrDcCode(board.getGDccode());
				cbNoticeEntity.setBrdUsrId(board.getGUserId());
				cbNoticeEntity.setAddWho(board.getGUserId());
				cbNoticeEntity.setEditWho(board.getGUserId());
				
				String boardKey = "";
				
				if ((CanalFrameConstants.INSERT).equals(board.getRowStatus())) {
					// 게시판번호 채번
					boardKey = (String) commonDao.selectOne(SERVICEID_PREFIX +"getBoardKey");
					cbNoticeEntity.setBrdNum(boardKey);
					
					commonDao.selectOne(SERVICEID_PREFIX +"insertBoard", cbNoticeEntity);
					
					if(cbNoticeEntity.getErrCode() != 0){
						throw new SystemException(new UserHandleException(""+"에러코드 : "+ cbNoticeEntity.getErrCode() + ", 에러메세지 : " + cbNoticeEntity.getErrMsg()));
					}
				} else if ((CanalFrameConstants.UPDATE).equals(board.getRowStatus())) {
					commonDao.selectOne(SERVICEID_PREFIX +"updateBoard", cbNoticeEntity);
					boardKey = cbNoticeEntity.getBrdNum();
					if(cbNoticeEntity.getErrCode() != 0){
						throw new SystemException(new UserHandleException(""+"에러코드 : "+ cbNoticeEntity.getErrCode() + ", 에러메세지 : " + cbNoticeEntity.getErrMsg()));
					}
				} else {
					commonDao.delete(SERVICEID_PREFIX +"deleteBoard", cbNoticeEntity);
				}
				
				if (board.getAllUserYn() != null && "Y".equals(board.getAllUserYn())) {
					board.setBrdNum(boardKey);
					
					// 전체 사용자 공지대상으로 추가
					commonDao.insert(SERVICEID_PREFIX +"insertRecvGroupAllUser", board);
					commonDao.insert(SERVICEID_PREFIX +"insertSendHistAllUser", board);
				} else if (recvGroupList != null && recvGroupList.size() > 0) {
					for(CbNoticeRecvReqDto recv : recvGroupList) {
						if ((CanalFrameConstants.INSERT).equals(recv.getRowStatus())) {
							recv.setAddWho(board.getGUserId());
							recv.setBrdNum(boardKey);
							
							// CM_NOTICES_RCVCUST (공지사항수신처) 삽입
							commonDao.insert(SERVICEID_PREFIX +"insertRecvGroupUser", recv);
							// CM_NOTICES_SEND_HIST (공지사항전송이력)
							int result = commonDao.insert(SERVICEID_PREFIX +"insertSendHist", recv);	
							
							if(result < 1) { // 소속된 수신처가 없는 사용자 삽입
								commonDao.insert(SERVICEID_PREFIX +"insertSendHistUser", recv);
							}
						}
					}
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
								cmFileUploadEntity.setType("NOTICE");
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
	
	/**
	 * @description : 공지사항 등록 및 수신처 전송
	 * @param : CbNoticeReqDto.java
	 * 			brdTit: 제목
	 * 			brdCntt: 내용
	 * 			brdDocKndCd: 게시문서종류코드 (EMERGENCY:긴급, OPP:운영정책, CENTER:센터운영, DSP:배차, SUPPLY:수급, EWH:외부창고, SYS:시스템, EVENT:행사, ETC:기타) (null 일때 default: SYS)
	 * 			popYn: 팝업으로 노출 여부 (Y/N) (null 일때 default: N)
	 * 			rcvcustType: 수신처유형 (R:수신 그룹ID, U:수신 사용자ID)
	 * 			recvGroupId: 수신그룹ID (/cm/cmReceiveGroup 메뉴의 수신그룹ID) (ex. "1,2,501")
	 * 			recvUserId: 수신사용자ID (ex. "hyeyeon822,hi31126,youn0927")
	 * 			redirectUrl: 이동URL (필수값 아님) (ex. /cm/cmCode)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.29 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveNotice(CbNoticeReqDto cbNoticeReqDto) {
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(cbNoticeReqDto, cbNoticeReqDto, "PRC_CM_NOTICE_SEND");
		
		// PKG 파라마터 세팅 및 실행 - 업무(2/4)
		String[] keyList = {"BRDTIT", "BRDCNTT", "BRDDOCKNDCD", "POP_YN", "RCVCUST_TYPE", "RECV_GROUP_ID", "RECV_USER_ID", "REDIRECT_URL"};
		Object[] valueList = {cbNoticeReqDto.getBrdTit(), cbNoticeReqDto.getBrdCntt(), cbNoticeReqDto.getBrdDocKndCd(), cbNoticeReqDto.getPopYn(), cbNoticeReqDto.getRcvcustType(), cbNoticeReqDto.getRecvGroupId(), cbNoticeReqDto.getRecvUserId(), cbNoticeReqDto.getRedirectUrl()};
		cbNoticeReqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		commonDao.exec(SERVICEID_PREFIX +"saveNotice", cbNoticeReqDto);
		
		// 프로시저 OUT Parameter(3/4)
		String resultCode    = cbNoticeReqDto.getResultCode();
		String resultMessage = cbNoticeReqDto.getResultMessage();
		
		// 프로시저 Exception 처리(4/4)
		if (!resultCode.equals("0")) {
			throw new SystemException(new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage));
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
