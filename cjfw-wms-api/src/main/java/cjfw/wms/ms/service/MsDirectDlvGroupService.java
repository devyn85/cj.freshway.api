package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsDirectDlvGroupReqDto;
import cjfw.wms.ms.dto.MsDirectDlvGroupResDto;
import cjfw.wms.ms.entity.MsDirectDlvGroupEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.06.23 
 * @description : 기준정보 > 상품기준정보 > 발주직송그룹관리 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsDirectDlvGroupService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msDirectDlvGroupService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 거래처 정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.23 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsDirectDlvGroupResDto> getMasterList(MsDirectDlvGroupReqDto dto) {
		List<MsDirectDlvGroupResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
		
	/**
	 * @description :거래처 정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.23 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<MsDirectDlvGroupReqDto> dtoList) {
		if (null != dtoList) {
			for(MsDirectDlvGroupReqDto dto : dtoList) {
				MsDirectDlvGroupEntity entity = ModelMapperUtil.map(dto, userContext, MsDirectDlvGroupEntity.class);
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					//trigger
					//commonDao.selectOne(SERVICEID_PREFIX +"insertZoneInfo", msZoneManagerEntity);
					commonDao.insert(SERVICEID_PREFIX +"insertMasterList", entity);
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					MsDirectDlvGroupEntity result = commonDao.selectOne(SERVICEID_PREFIX +"updateMasterList", entity);
					if(result != null) {
						Integer errCode = (Integer) result.getErr();
						if(errCode != null && errCode != 0){
							throw new UserHandleException("에러코드 : "+ errCode+ ", 에러메세지 : " + (String)result.getErrmsg());
						}
					}
				} else if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
//					commonDao.delete(SERVICEID_PREFIX +"deleteMasterList", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
