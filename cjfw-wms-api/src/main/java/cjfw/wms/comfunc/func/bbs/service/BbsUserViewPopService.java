/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardDetailGetReqDto;
import cjfw.wms.comfunc.func.bbs.dto.BbsBoardDetailGetResDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BbsUserViewPopService {

	private final CommonDao commonDao;
	
	/**
	 * 공지사항을 조회한다.<br>
	 */
	public List<BbsBoardDetailGetResDto> getBoardDetail(BbsBoardDetailGetReqDto bbsBoardDetailGetReqDto) {
		List<BbsBoardDetailGetResDto> bbsBoardDetailList = commonDao.selectList("bbsUserViewPopService.getBoardDetail", bbsBoardDetailGetReqDto);
		commonDao.update("bbsUserViewPopService.updateViewCnt", bbsBoardDetailGetReqDto);
		for(BbsBoardDetailGetResDto bbsBoardDetail : bbsBoardDetailList) {
			//첨부파일 그룹 번호가 있을 경우 첨부파일 리스트 조회
			if(bbsBoardDetail.getAttchFileGrpNo() != null) {
				bbsBoardDetail.setFileGroup(commonDao.selectList("bbsUserViewPopService.getAttachFileList", bbsBoardDetail));
			}
		}
		return bbsBoardDetailList;
	}
	
}
