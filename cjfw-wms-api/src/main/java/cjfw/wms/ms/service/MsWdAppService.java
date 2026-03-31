package cjfw.wms.ms.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.ms.dto.MsWdAppReqDto;
import cjfw.wms.ms.dto.MsWdAppResDetailDto;
import cjfw.wms.ms.dto.MsWdAppResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.24
 * @description : 기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsWdAppService {
	
    private final CommonDao commonDao;

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(MsWdAppService.class.getSimpleName()) + ".";

    /**
     * @description : 기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<MsWdAppResDto> getMasterList(MsWdAppReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * @description : 기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 상세 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.24 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public List<MsWdAppResDetailDto> getDetailList(MsWdAppReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
	}
	
	/**
	 * @description : 기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.24 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public String saveMasterList(MsWdAppReqDto paramDto) {
		
		MsWdAppReqDto reqDto = ModelMapperUtil.map(paramDto, MsWdAppReqDto.class);
		
		List<MsWdAppResDto> saveDataList = reqDto.getSaveMasterList();
		
		if (null != saveDataList) {
			
			for (MsWdAppResDto dto : saveDataList) {
				
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					
					int count = commonDao.selectOne(SERVICEID_PREFIX + "getDupCheck", dto);
					
					if(count > 0) {
						
						//String dname    = dto.getDcname();
						String popGroup = dto.getPopGroup();
						
						// 중복된 데이터가 존재합니다.(물류센터:{0}, POP그룹명:{1})
						//throw new UserHandleException(MessageUtil.getMessage("MS_WD_APP_001", new String[]{dname,popGroup}));
						// 중복된 데이터가 존재합니다.(POP그룹명:{0})
						throw new UserHandleException(MessageUtil.getMessage("MS_WD_APP_001", new String[]{popGroup}));
					}else {
						commonDao.insert(SERVICEID_PREFIX +"insertMaster", dto);
					}
					
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					
					commonDao.update(SERVICEID_PREFIX +"updateMaster", dto);
					if(!dto.getUseYn().equals(dto.getOrgUseYn())) {
						// 마스터 사용여부 변경시 디테일도 변경.
						commonDao.update(SERVICEID_PREFIX +"updateDetail", dto);
					}
					
				}
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 상세 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.24 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public String saveDetailList(MsWdAppReqDto paramDto) {
		
		MsWdAppReqDto reqDto = ModelMapperUtil.map(paramDto, MsWdAppReqDto.class);
		
		List<MsWdAppResDetailDto> saveDataList = reqDto.getSaveDetailList();
		
		if (null != saveDataList) {
		
			for (MsWdAppResDetailDto dto : saveDataList) {
				
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					commonDao.delete(SERVICEID_PREFIX +"deleteDetail", dto);
				} 
			}
		}
		
		if (null != saveDataList) {
		
			for (MsWdAppResDetailDto dto : saveDataList) {
				
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					
					int count = commonDao.selectOne(SERVICEID_PREFIX + "getDupCheckPopno", dto);
					
					if(count > 0) {
						
						String dname    = dto.getDcname();
						String popno    = dto.getPopno();
						String popGroup    = dto.getPopGroup();
						
						// 중복된 데이터가 존재합니다.(물류센터:{0}, POPNO:{1})
						throw new UserHandleException(MessageUtil.getMessage("MS_WD_APP_002", new String[]{dname,popno,popGroup}));
					}else {
						commonDao.insert(SERVICEID_PREFIX +"insertDetail", dto);
					}
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateDetail", dto);
				}
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
