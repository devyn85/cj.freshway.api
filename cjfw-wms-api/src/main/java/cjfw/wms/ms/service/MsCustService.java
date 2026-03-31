package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsCustDetailResDto;
import cjfw.wms.ms.dto.MsCustHeaderResDto;
import cjfw.wms.ms.dto.MsCustReqDto;
import cjfw.wms.ms.dto.MsCustResDto;
import cjfw.wms.ms.entity.MsCustEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.06.09 
 * @description : 기준정보 > 거래처기준정보 > 고객정보(New) Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCustService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCustService.";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description :주출고센터 코드리스트 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCustHeaderResDto> getSelectDccodeList() {
		List<MsCustHeaderResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getSelectDccodeList");
		return result;
	}
	
	/**
	 * @description : 거래처 정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public Page<MsCustResDto> getMasterList(MsCustReqDto dto) {
		Page<MsCustResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getMasterList", dto, dto);
		return result;
	}
	
	
	/**
	 * @description :거래처 정보 조회 (단건)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public MsCustDetailResDto getMaster(MsCustReqDto dto) {
		MsCustDetailResDto result = commonDao.selectOne(SERVICEID_PREFIX + "getMaster", dto);
		return result;
	}
	
	/**
	 * @description :거래처 정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String saveMaster(MsCustReqDto dto) {
		
		if (null != dto) {
			MsCustEntity entity = ModelMapperUtil.map(dto, userContext, MsCustEntity.class);
			commonDao.selectOne(SERVICEID_PREFIX +"updateMaster", entity);
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
