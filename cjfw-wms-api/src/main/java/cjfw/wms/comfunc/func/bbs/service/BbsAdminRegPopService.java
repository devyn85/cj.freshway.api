/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.bbs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardDeletePostReqDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardDetailGetReqDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardDetailGetResDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardFileGetReqDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardFileGetResDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardSavePostReqDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BbsAdminRegPopService{
	
	private static final Logger log = LoggerFactory.getLogger(BbsAdminRegPopService.class);
	//private static final String BBS_NOTE = "BBS_NOTE";
	
	private final CommonDao commonDao;
	
	private final UserContext context;
	
	private final UserContext userContext;
	/**
	 * 공지사항을 등록한다.<br>
	 */
	public String saveBoard(BbsBoardSavePostReqDto bbsBoardPostReqDto) {
		bbsBoardPostReqDto.setUserId(userContext.getUserId());
		int cnt = 0;
		if(!"".equals(bbsBoardPostReqDto.getBbsSeq()) && bbsBoardPostReqDto.getBbsSeq() != null ) {
			log.info("======================= 게시판  수정 ==========================");
			cnt = commonDao.update("bbsAdminRegPopService.updateBoard", bbsBoardPostReqDto);
		}else {
			log.info("======================= 게시판  추가 ==========================");
			int boardSeq =  (int) commonDao.selectOne("bbsAdminRegPopService.selectBoardSeq");
			if(boardSeq < 1){
				boardSeq = 1;
			}
			bbsBoardPostReqDto.setBbsSeq(boardSeq);
			cnt = commonDao.insert("bbsAdminRegPopService.insertBoard", bbsBoardPostReqDto);
		}
		if(cnt < 1) {
			//저장 대상 자료가 존재 하지 않습니다
			return "MSG.COM.ERR.008";
		}
		//저장되었습니다
		return "MSG.COM.SUC.003";
	}
	
	/**
	 * 공지사항을 삭제한다.<br>
	 */
	public String deleteBoard(BbsBoardDeletePostReqDto bbsBoardDeletePostReqDto) {
		bbsBoardDeletePostReqDto.setUserId(userContext.getUserId());
		int result = commonDao.delete("bbsAdminRegPopService.deleteBoard", bbsBoardDeletePostReqDto);
		if(result < 1 ) {
			//저장 대상 자료가 존재 하지 않습니다
			return "MSG.COM.ERR.008";
		}
		//삭제 되었습니다
		return "MSG.COM.SUC.006";
	}
	
	/**
	 * 공지사항을 조회한다.<br>
	 * 수정 화면에서의 조회는 조회수가 증가하지 않는다.<br>
	 */
	public List<BbsBoardDetailGetResDto> getBoardDetail(BbsBoardDetailGetReqDto bbsBoardDetailGetReqDto) {
		
		List<BbsBoardDetailGetResDto> bbsBoardDetailList = commonDao.selectList("bbsUserViewPopService.getBoardDetail", bbsBoardDetailGetReqDto);
              
		for(BbsBoardDetailGetResDto bbsBoardDetail : bbsBoardDetailList) {
			//첨부파일 그룹 번호가 있을 경우 첨부파일 리스트 조회
			if(bbsBoardDetail.getAttchFileGrpNo() != null) {
				bbsBoardDetail.setFileGroup(commonDao.selectList("bbsUserViewPopService.getAttachFileList", bbsBoardDetail));
			}
		}
		return bbsBoardDetailList;
	}
	
	public List<BbsBoardFileGetResDto> getBoardFile(BbsBoardFileGetReqDto bbsBoardFileGetReqDto) {
		List<BbsBoardFileGetResDto> bbsBoardFileGetResDto = commonDao.selectList("bbsUserViewPopService.getAttachFileList", bbsBoardFileGetReqDto);
		//첨부파일 그룹 번호가 있을 경우 첨부파일 리스트 조회
		return bbsBoardFileGetResDto;
	}
}
