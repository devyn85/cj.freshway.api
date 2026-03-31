package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsSkuDcLabelReqDto;
import cjfw.wms.ms.dto.MsSkuDcLabelResDto;
import cjfw.wms.ms.entity.MsSkuDcLabelEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsSkuDcLabelService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "msSkuDcLabelService.";
	private final UserContext userContext;
	private final CommonDao commonDao;
	
	/**
	 * @description : 센터상품라벨속성 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.15 YeoSeungCheol 	(pw6375@cj.net) 생성 </pre>
	 */
	public List<MsSkuDcLabelResDto> getMasterList (MsSkuDcLabelReqDto msSkuDcLabelReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", msSkuDcLabelReqDto);
	}
	
	/**
	 * @description : 센터상품라벨속성 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.15 YeoSeungCheol 	(pw6375@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<MsSkuDcLabelReqDto> list) {
		for (MsSkuDcLabelReqDto dto : list) {
			if (null != list) {
				MsSkuDcLabelEntity entity = ModelMapperUtil.map(dto, userContext, MsSkuDcLabelEntity.class);
				
				commonDao.update(SERVICEID_PREFIX + "saveMasterList", entity);
			}			
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
