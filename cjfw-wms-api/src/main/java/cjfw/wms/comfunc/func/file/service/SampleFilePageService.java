/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.file.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.comfunc.func.file.dto.SampleFilePageGetReqDto;
import cjfw.wms.comfunc.func.file.dto.SampleFilePageGetResDto;
import cjfw.wms.comfunc.func.file.dto.SampleFilePagePostReqDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SampleFilePageService {

	private final CommonDao commonDao;
	
//	private static final Logger log = LoggerFactory.getLogger(SampleFilePageService.class);
	
	
	/**
	 * 공지사항 목록 데이터를 조회한다.<br>
	 * Grid내 파일첨부 샘플 데이터로 공지사항 데이터를 사용<br>
	 */
	 public List<SampleFilePageGetResDto> getBoardListAll(SampleFilePageGetReqDto sampleFilePageGetReqDto) {
	        List<SampleFilePageGetResDto> list = commonDao.selectList("sampleFilePageService.getBoardListAll", sampleFilePageGetReqDto);
	        return list;
	 }
	 
	/**
	 * 공지사항 첨부파일 정보를 저장<br>
	 */
	public String saveBoardAttchFileGrpNo(SampleFilePagePostReqDto sampleFilePagePostReqDto) {
		commonDao.update("sampleFilePageService.updateBoardAttchFileGrpNo", sampleFilePagePostReqDto);

		return "MSG.COM.SUC.003";
		
	}
}
