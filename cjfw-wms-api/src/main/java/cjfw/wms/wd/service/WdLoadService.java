package cjfw.wms.wd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.wd.dto.WdLoadReqDto;
import cjfw.wms.wd.dto.WdLoadResDto;
import cjfw.wms.wd.entity.WdLoadEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.12
 * @description : 출차지시처리 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 JiHoPark 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdLoadService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdLoadService.";

	private final CommonDao commonDao;
	private final UserContext userContext;

	/**
	 * @description : 출차지시처리 - 출차지시처리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.12 JiHoPark  생성 </pre>
	 */
	public List<WdLoadResDto> getMasterList(WdLoadReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

	/**
	 * @description : 출차지시처리 - 출차지시처리 상세 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.12 JiHoPark  생성 </pre>
	 */
	public List<WdLoadResDto> getMasterList2(WdLoadReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", dto);
	}

	/**
	 * @description : 출차지시처리 - report 정보 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.13 JiHoPark  생성 </pre>
	 */
	public WdLoadResDto getLoadReportInfo(WdLoadReqDto dto) {
		String userId = dto.getGUserId();
    	String userSpId = dto.getGSpid();

    	String processtype = dto.getProcesstype();
    	String searchtype = dto.getSearchtype();
    	String searchgubun = dto.getSearchgubun();

    	int chunkSize = 200;

    	WdLoadResDto returnDto = new WdLoadResDto();

        // temp table 데이터 삭제 진행
    	WdLoadEntity tempTmDto = new WdLoadEntity();
    	tempTmDto.setGUserId(userId);
    	tempTmDto.setProcesstype(processtype);
    	tempTmDto.setGSpid(userSpId);
    	commonDao.delete(SERVICEID_PREFIX +"deleteSyProcessTempTm", tempTmDto);

    	List<WdLoadResDto> printList = dto.getPrintMasterList();
    	if (printList.size() > 0) {
    		List<WdLoadEntity> insertList = new ArrayList<WdLoadEntity>();

    		for (int i = 0; i < printList.size(); i++) {
    			WdLoadResDto printDto = printList.get(i);

    			tempTmDto = ModelMapperUtil.map(printDto, userContext, WdLoadEntity.class);
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

    	if ("1".equals(searchtype) && "0".equals(searchgubun)) {
    		// 상차지시서 출력용 헤더 목록
        	returnDto.setDsReportHeader(commonDao.selectList(SERVICEID_PREFIX + "getPrintHeaderList2", dto));
    	} else {
    		// 상차지시서 출력용 헤더 목록
        	returnDto.setDsReportHeader(commonDao.selectList(SERVICEID_PREFIX + "getPrintHeaderList", dto));

        	// 출상차지시서 출력용 메모사항 목록
        	returnDto.setDsReportMemo(commonDao.selectList(SERVICEID_PREFIX + "getPrintMemoList", dto));
    	}

    	// 상차지시서 출력 목록
    	returnDto.setDsReportDetail(commonDao.selectList(SERVICEID_PREFIX + "getPrintList", dto));

		return returnDto;
	}

}
