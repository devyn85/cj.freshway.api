package cjfw.wms.wd.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.wd.dto.WdPltDpReqDto;
import cjfw.wms.wd.dto.WdPltDpResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.22
 * @description : 재고 > 공용기 관리업 > PLT 수불 관리   Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.22 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdPltDpService {
	
    private final CommonDao commonDao;
    
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(WdPltDpService.class.getSimpleName()) + ".";
   
    /**
     * @description : 재고 > 공용기 관리업 > PLT 수불 관리 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.22 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<WdPltDpResDto> getMasterList(WdPltDpReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
    /**
     * @description : 재고 > 공용기 관리업 > PLT 수불 관리 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.05 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<WdPltDpResDto> getStock(WdPltDpReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getStock", dto);
	}
	
	
	/**
	 * @description : 재고 > 공용기 관리업 > PLT 수불 관리 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
     * 2025.09.22 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public String saveMasterList(WdPltDpReqDto paramDto) {
		
		WdPltDpReqDto reqDto = ModelMapperUtil.map(paramDto, WdPltDpReqDto.class);
		
		List<WdPltDpResDto> saveDataList = reqDto.getSaveDataList();
		
		if (null != saveDataList) {
			
			for (WdPltDpResDto dto : saveDataList) {
				
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					commonDao.delete(SERVICEID_PREFIX +"deleteMaster", dto);
				} 
			}
		}
		
		
		if (null != saveDataList) {
			
			for (WdPltDpResDto dto : saveDataList) {
				
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertMaster", dto);
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateMaster", dto);
				}
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
