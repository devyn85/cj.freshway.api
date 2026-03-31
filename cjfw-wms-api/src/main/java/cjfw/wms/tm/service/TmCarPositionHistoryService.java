package cjfw.wms.tm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmCarPositionHistoryReqDto;
import cjfw.wms.tm.dto.TmCarPositionHistoryResDto;
import cjfw.wms.tm.entity.TmCarPositionHistoryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.14
 * @description : 운행일지 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.14 JiHoPark 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmCarPositionHistoryService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmCarPositionHistoryService.";

	private final CommonDao commonDao;
	private final UserContext userContext;

	/**
	 * @description : 운행일지 - 운행일지 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.30 JiHoPark  생성 </pre>
	 */
	public List<TmCarPositionHistoryResDto> getMasterList(TmCarPositionHistoryReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	/**
	 * @description : 운행일지 - 운행일지 상세 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.30 JiHoPark  생성 </pre>
	 */
	public List<TmCarPositionHistoryResDto> getMasterList2(TmCarPositionHistoryReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", dto);
	}

	/**
	 * @description : 차량운행일지 - report 정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.14 JiHoPark  생성 </pre>
	 */
	public TmCarPositionHistoryResDto getCarPositionHistoryInfo(TmCarPositionHistoryReqDto dto) {
		String userId = dto.getGUserId();
    	String userSpId = dto.getGSpid();

    	String processtype = dto.getProcesstype();

    	int chunkSize = 200;

    	TmCarPositionHistoryResDto returnDto = new TmCarPositionHistoryResDto();

        // temp table 데이터 삭제 진행
    	TmCarPositionHistoryEntity tempTmDto = new TmCarPositionHistoryEntity();
    	tempTmDto.setGUserId(userId);
    	tempTmDto.setGSpid(userSpId);
    	tempTmDto.setProcesstype(processtype);
    	commonDao.delete(SERVICEID_PREFIX +"deleteSyProcessTempTm", tempTmDto);

    	List<TmCarPositionHistoryResDto> printList = dto.getPrintMasterList();
    	if (printList.size() > 0) {
    		List<TmCarPositionHistoryEntity> insertList = new ArrayList<TmCarPositionHistoryEntity>();

    		for (int i = 0; i < printList.size(); i++) {
    			TmCarPositionHistoryResDto printDto = printList.get(i);

    			tempTmDto = ModelMapperUtil.map(printDto, userContext, TmCarPositionHistoryEntity.class);
    			tempTmDto.setProcesstype(processtype);
    			tempTmDto.setProcesscreator(userId);
    			tempTmDto.setSpid(userSpId);
    			tempTmDto.setAddwho(userId);
    			tempTmDto.setEditwho(userId);

        	    insertList.add(tempTmDto);

        	    // 200개마다 혹은 마지막 루프일 때 insert
        	    if (insertList.size() == chunkSize) {
        	        commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempTm", insertList);
        	        insertList.clear(); // 다음 배치 준비
        	    }

        	}

    		// 남은 데이터 insert
        	if (insertList.size() > 0) {
        		commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempTm", insertList);
        	}
    	}


    	// 운행일지 출력용 헤더 목록
    	returnDto.setDsReportHeader(commonDao.selectList(SERVICEID_PREFIX + "getPrintHeaderList", dto));

    	// 운행일지 출력용 상세 목록
    	returnDto.setDsReportDetail(commonDao.selectList(SERVICEID_PREFIX + "getPrintDetailList", dto));

		return returnDto;
	}

}
