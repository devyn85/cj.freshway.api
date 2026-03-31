package cjfw.wms.cm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmReceiveGroupDtlReqDto;
import cjfw.wms.cm.dto.CmReceiveGroupReqDto;
import cjfw.wms.cm.dto.CmReceiveGroupResDto;
import cjfw.wms.cm.entity.CmReceiveGroupEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.09.11 
 * @description : 기준정보 > 게시판관리 > 수신그룹관리 목록 조회 및 저장 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.11 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmReceiveGroupService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmReceiveGroupService.";

	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 수신그룹관리 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<CmReceiveGroupResDto> getRcvGrpHeaderList(CmReceiveGroupReqDto cmReceiveGroupReqDto) {
		// 검색 기준에 따른 쿼리 분류
			return commonDao.selectList(SERVICEID_PREFIX + "getHeaderList", cmReceiveGroupReqDto);
	}
	
	/**
	 * @description : 수신그룹관리 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<CmReceiveGroupResDto> getRcvGrpDetailList(CmReceiveGroupReqDto cmReceiveGroupReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", cmReceiveGroupReqDto);
		
	}
	
	/**
	 * @description : 그룹 수신그룹관리 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public String saveRcvGrpHeader(CmReceiveGroupReqDto cmReceiveGroupReqDto) {
		if (null != cmReceiveGroupReqDto.getDtlList()) {
			for (CmReceiveGroupDtlReqDto dtlReq : cmReceiveGroupReqDto.getDtlList()) {
				CmReceiveGroupEntity rcvGrpEntity = ModelMapperUtil.map(dtlReq, userContext, CmReceiveGroupEntity.class);
				if ((CanalFrameConstants.INSERT).equals(dtlReq.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertRcvGrpHeader", rcvGrpEntity);
				} else if ((CanalFrameConstants.UPDATE).equals(dtlReq.getRowStatus())) {
					// 데이터번호 빈값 체크
					if (!"".equals(rcvGrpEntity.getRecvGroupId())) {
						commonDao.insert(SERVICEID_PREFIX +"updateRcvGrpHeader", rcvGrpEntity);
					}
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 상세 수신그룹관리 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public String saveRcvGrpDetail(CmReceiveGroupReqDto cmReceiveGroupReqDto) {
		if (null != cmReceiveGroupReqDto.getDtlList()) {
			// 100건씩 끊어서 저장
			int bulkCnt = 0;
			List<CmReceiveGroupEntity> bulkList = new ArrayList<CmReceiveGroupEntity>();
			
			for (CmReceiveGroupDtlReqDto dtlReq : cmReceiveGroupReqDto.getDtlList()) {
				CmReceiveGroupEntity rcvGrpEntity = ModelMapperUtil.map(dtlReq, userContext, CmReceiveGroupEntity.class);
				if ((CanalFrameConstants.INSERT).equals(dtlReq.getRowStatus())) {
					bulkCnt++;
            		bulkList.add(rcvGrpEntity);
            		if (bulkCnt % 100 == 0) {
            			commonDao.insert(SERVICEID_PREFIX +"insertRcvGrpDtl", bulkList);
            			bulkList.clear();
                    }
 				} else if ((CanalFrameConstants.DELETE).equals(dtlReq.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"deleteRcvGrpDtl", rcvGrpEntity);
				}
			}
			
			// 나머지
			if (bulkList.size() > 0) {
				commonDao.insert(SERVICEID_PREFIX +"insertRcvGrpDtl", bulkList);
            }
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
