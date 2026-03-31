package cjfw.wms.wd.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.wd.dto.WdVsrModifyReqDto;
import cjfw.wms.wd.dto.WdVsrModifyResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.21
 * @description :출고 > 출고 > CS 출고 정정 요청 대응 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.21 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdVsrModifyService {
	
    private final CommonDao commonDao;
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(WdVsrModifyService.class.getSimpleName()) + ".";

    /**
     * @description : 출고 > 출고 > CS 출고 정정 요청 대응 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.21 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<WdVsrModifyResDto> getMasterList(WdVsrModifyReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }

	/**
	 * @description : 출고 > 출고 > CS 출고 정정 요청 대응 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
     * 2025.10.21 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public String saveMasterList(WdVsrModifyReqDto paramDto) {
		
		WdVsrModifyReqDto reqDto = ModelMapperUtil.map(paramDto, WdVsrModifyReqDto.class);
		
		List<WdVsrModifyResDto> saveDataList = reqDto.getSaveDataList();
		
		if (null != saveDataList) {
			
			for (WdVsrModifyResDto dto : saveDataList) {
				// insert
				if(StringUtils.isEmpty(dto.getSerialkey())) {
					commonDao.update(SERVICEID_PREFIX + "updateIfRtClaiminfoVsr", dto);
					commonDao.insert(SERVICEID_PREFIX + "insertRtClaiminfoVsr", dto);
					commonDao.insert(SERVICEID_PREFIX + "insertIfWdInplanAdj", dto);
				} else {
					commonDao.update(SERVICEID_PREFIX + "updateRtClaiminfoVsr", dto);
					commonDao.update(SERVICEID_PREFIX + "updateIfWdInplanAdj", dto);
				}
				
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
