package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsCenterDistrictDcOrdGrpReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictHjdongReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictOrdGrpReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictOrdGrpResDto;
import cjfw.wms.ms.dto.MsDistrictOrderGroupMergeReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsCenterDistrictOrdGrpService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * 
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCenterDistrictOrdGrpService.";
	private final MsDistrictOrderGroupMergeService msDistrictOrderGroupMergeService;
	private final MsCenterDistrictHistoryService msCenterDistrictHistoryService;
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description : 주문 그룹 리스트 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDistrictOrdGrpResDto> getOrdGrpList(){
		return commonDao.selectList(SERVICEID_PREFIX + "getOrdGrpList");
	}
	
	/**
	 * @description : 주문 그룹 우선순위 리스트 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsCenterDistrictOrdGrpResDto> getDcOrdGrpList(MsCenterDistrictOrdGrpReqDto dto){
		return commonDao.selectList(SERVICEID_PREFIX + "getDcOrdGrpList");
	}
	
	/**
	 * @description : 센터 행정동에 에 할당된 주문그룹 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	
	public List<MsCenterDistrictOrdGrpResDto> getMasterList(MsCenterDistrictOrdGrpReqDto dto){
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * @description : 주문그룹 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 * 2025.10.22 JuSungbin (wntjdqls9818@cj.net) SRM 공유 추가 </pre>
	 */
	public String saveMasterList(List<MsCenterDistrictOrdGrpReqDto> dtoList){
		for(MsCenterDistrictOrdGrpReqDto dto: dtoList) {
			MsDistrictOrderGroupMergeReqDto srmReqDto =  ModelMapperUtil.map(dto, userContext, MsDistrictOrderGroupMergeReqDto.class);
			if(CanalFrameConstants.INSERT.equals(dto.getRowStatus())) {
				List<MsCenterDistrictOrdGrpResDto> data = commonDao.selectList(SERVICEID_PREFIX + "getMasterValidationInsert", dto);
				if(data != null && !data.isEmpty()) {
					throw new UserHandleException("MSG.COM.VAL.067" , new String[] { String.format(
							"주문그룹 [%s] - [%s]",
							data.get(0).getDcname(),
							data.get(0).getHjdongNm()
						) 
					});
				}
				commonDao.insert(SERVICEID_PREFIX + "saveMasterList", dto);
				
				// SRM
				msDistrictOrderGroupMergeService.saveMaster(srmReqDto);
			}else if(CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {
				List<MsCenterDistrictOrdGrpResDto> data = commonDao.selectList(SERVICEID_PREFIX + "getMasterValidationUpdate", dto);
				if(data != null && !data.isEmpty()) {
					throw new UserHandleException("MSG.COM.VAL.067" , new String[] { String.format(
							"주문그룹 [%s] - [%s]",
							data.get(0).getDcname(),
							data.get(0).getHjdongNm()
						) 
					});
				}
				commonDao.update(SERVICEID_PREFIX + "updateMasterList", dto);
				
				// SRM
				msDistrictOrderGroupMergeService.saveMaster(srmReqDto);
			}else if(CanalFrameConstants.DELETE.equals(dto.getRowStatus())) {
				commonDao.update(SERVICEID_PREFIX + "deleteMasterList", dto);
				
				// SRM
				msDistrictOrderGroupMergeService.saveMaster(srmReqDto);
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 행정동 저장에 따른 주문그룹 저장 메서드 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public String saveMasterList(MsCenterDistrictHjdongReqDto dto){
			if(CanalFrameConstants.INSERT.equals(dto.getRowStatus())) {
				msCenterDistrictHistoryService.saveMasterList(dto);
				commonDao.insert(SERVICEID_PREFIX + "saveMasterListByHjdong", dto);
			}else if(CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {
				msCenterDistrictHistoryService.saveMasterList(dto);
				commonDao.update(SERVICEID_PREFIX + "updateMasterListByHjdong", dto);
			}else if(CanalFrameConstants.DELETE.equals(dto.getRowStatus())) {
				msCenterDistrictHistoryService.saveMasterList(dto);
				commonDao.update(SERVICEID_PREFIX + "deleteMasterListByHjdong", dto);
			}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 주문 그룹 우선순위 저장에 따른 주문그룹 저장 메서드 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public String saveMasterList(MsCenterDistrictDcOrdGrpReqDto dto){
		MsDistrictOrderGroupMergeReqDto srmReqDto = new MsDistrictOrderGroupMergeReqDto();
		srmReqDto.setOrdGrp(dto.getOrdGrp());
		if(CanalFrameConstants.INSERT.equals(dto.getRowStatus())) {
			msCenterDistrictHistoryService.saveMasterList(dto);
			commonDao.insert(SERVICEID_PREFIX + "saveMasterListByDcOrdGrp", dto);
			msDistrictOrderGroupMergeService.saveMasterListNew(srmReqDto);
		}else if(CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {
			msCenterDistrictHistoryService.saveMasterList(dto);
			commonDao.update(SERVICEID_PREFIX + "updateMasterListByDcOrdGrp", dto);
			msDistrictOrderGroupMergeService.saveMasterListNew(srmReqDto);
		}else if(CanalFrameConstants.DELETE.equals(dto.getRowStatus())) {
			msCenterDistrictHistoryService.saveMasterList(dto);
            srmReqDto.setDelYn("Y");
            msDistrictOrderGroupMergeService.saveMasterListNew(srmReqDto);
			commonDao.update(SERVICEID_PREFIX + "deleteMasterListByDcOrdGrp", dto);
		}
	
	return CanalFrameConstants.MSG_COM_SUC_CODE;
}
	
}
