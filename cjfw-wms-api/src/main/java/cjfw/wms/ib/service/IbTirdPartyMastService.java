package cjfw.wms.ib.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.ib.dto.IbTirdPartyMastReqDto;
import cjfw.wms.ib.dto.IbTirdPartyMastResT1Dto;
import cjfw.wms.ib.dto.IbTirdPartyMastResT2Dto;
import cjfw.wms.ib.dto.IbTirdPartyMastResT3DetailDto;
import cjfw.wms.ib.dto.IbTirdPartyMastResT3Dto;
import cjfw.wms.ib.dto.IbTirdPartyMastResT4Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.25
 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IbTirdPartyMastService {

    private final CommonDao commonDao;

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(IbTirdPartyMastService.class.getSimpleName()) + ".";

    /**
     * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_단가마스터_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.25 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<IbTirdPartyMastResT1Dto> getMasterT1List(IbTirdPartyMastReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterT1List", dto);
    }
    
    /**
     * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_협력사관리_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.25 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<IbTirdPartyMastResT2Dto> getMasterT2List(IbTirdPartyMastReqDto dto) {
    	return commonDao.selectList(SERVICEID_PREFIX + "getMasterT2List", dto);
    }
    
    /**
     * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.25 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<IbTirdPartyMastResT3Dto> getMasterT3List(IbTirdPartyMastReqDto dto) {
    	return commonDao.selectList(SERVICEID_PREFIX + "getMasterT3List", dto);
    }
    
    /**
     * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 상세 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.25 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<IbTirdPartyMastResT3DetailDto> getDetailT3List(IbTirdPartyMastReqDto dto) {
    	return commonDao.selectList(SERVICEID_PREFIX + "getDetailT3List", dto);
    }
    
    /**
     * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.25 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<IbTirdPartyMastResT4Dto> getMasterT4List(IbTirdPartyMastReqDto dto) {
    	return commonDao.selectList(SERVICEID_PREFIX + "getMasterT4List", dto);
    }
    
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_단가마스터_탭 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
     * 2025.09.25 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public String saveMasterT1List(IbTirdPartyMastReqDto paramDto) {
		
		IbTirdPartyMastReqDto reqDto = ModelMapperUtil.map(paramDto, IbTirdPartyMastReqDto.class);
		
		List<IbTirdPartyMastResT1Dto> saveList = reqDto.getSaveT1List();
		
		if (null != saveList) {
			
			for (IbTirdPartyMastResT1Dto dto : saveList) {
				
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX + "updateT1Master", dto);
				}
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					
					String dname       = dto.getDcname();
					String pricetypeNm = dto.getPricetypeNm();

					int valdateData = commonDao.selectOne(SERVICEID_PREFIX + "getT1DupCheck", dto);
					
					if(valdateData == 1) {
						// 중복된 데이터가 존재합니다.(물류센터:{0}, 단가유형:{1})
						throw new UserHandleException(MessageUtil.getMessage("MSG_IB_TIRD_PART_MAST_MSG_001", new String[]{dname,pricetypeNm}));
					} else {
						commonDao.insert(SERVICEID_PREFIX + "insertT1Master", dto);						
					}
					
				} 
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_협력사관리_탭 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public String saveMasterT2List(IbTirdPartyMastReqDto paramDto) {
		
		IbTirdPartyMastReqDto reqDto = ModelMapperUtil.map(paramDto, IbTirdPartyMastReqDto.class);
		
		List<IbTirdPartyMastResT2Dto> saveList = reqDto.getSaveT2List();
		
		if (null != saveList) {
			
			for (IbTirdPartyMastResT2Dto dto : saveList) {
				
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX + "updateT2Master", dto);
				}
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					
					String dname    = dto.getDcname();
					String custname = dto.getCustname();
					
					int valdateData = commonDao.selectOne(SERVICEID_PREFIX + "getT2DupCheck", dto);
					
					if(valdateData == 1) {
						// 중복된 데이터가 존재합니다.(물류센터:{0}, 협력사명:{1})
						throw new UserHandleException(MessageUtil.getMessage("MSG_IB_TIRD_PART_MAST_MSG_002", new String[]{dname,custname}));
					}
					
					commonDao.insert(SERVICEID_PREFIX + "insertT2Master", dto);
				} 
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 강제확정 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public String saveMasterT3List(IbTirdPartyMastReqDto paramDto) {
		
		IbTirdPartyMastReqDto reqDto = ModelMapperUtil.map(paramDto, IbTirdPartyMastReqDto.class);
		
		List<IbTirdPartyMastResT3DetailDto> saveList = reqDto.getSaveT3DetailList();
		
		if (null != saveList) {
			
			for (IbTirdPartyMastResT3DetailDto dto : saveList) {
				
				commonDao.insert(SERVICEID_PREFIX + "mergeT3Master", dto);				
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public String saveMasterT4List(IbTirdPartyMastReqDto paramDto) {
		
		IbTirdPartyMastReqDto reqDto = ModelMapperUtil.map(paramDto, IbTirdPartyMastReqDto.class);
		
		List<IbTirdPartyMastResT4Dto> saveList = reqDto.getSaveT4List();
		
		if (null != saveList) {
			
			for (IbTirdPartyMastResT4Dto dto : saveList) {
				
				String checkYn = commonDao.selectOne(SERVICEID_PREFIX + "getCheckFwYn", dto);
				
				if("01".equals(checkYn)) {
					commonDao.insert(SERVICEID_PREFIX + "updateT4Master", dto);
				} else {
					commonDao.insert(SERVICEID_PREFIX + "mergeT4Master", dto);
				}
				
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 수정 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public String updateMasterT4List(IbTirdPartyMastReqDto paramDto) {
		
		IbTirdPartyMastReqDto reqDto = ModelMapperUtil.map(paramDto, IbTirdPartyMastReqDto.class);
		
		List<IbTirdPartyMastResT4Dto> saveList = reqDto.getSaveT4List();
		
		if (null != saveList) {
			
			for (IbTirdPartyMastResT4Dto dto : saveList) {
				
				String checkYn = commonDao.selectOne(SERVICEID_PREFIX + "getCheckFwYn", dto);
				
				if("01".equals(checkYn)) {
					commonDao.insert(SERVICEID_PREFIX + "updateT4Master2", dto);
				} 
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
    
}
