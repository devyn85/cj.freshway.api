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
import cjfw.wms.ms.dto.MsPboxReqDto;
import cjfw.wms.ms.dto.MsPboxResT1Dto;
import cjfw.wms.ms.dto.MsPboxResT2Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.18
 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.18 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsPboxService {
	
    private final CommonDao commonDao;

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(MsPboxService.class.getSimpleName()) + ".";

    /**
     * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.18 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<MsPboxResT1Dto> getMasterT1List(MsPboxReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT1List", dto);
	}
	
	/**
	 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 사용현황_탭 유효한 차량번호 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public List<MsPboxResT1Dto> getCheckCarNo(MsPboxReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getCheckCarNo", dto);
	}
	
	/**
	 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 사용현황_탭 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.22 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public List<MsPboxResT2Dto> getMasterT2List(MsPboxReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterT2List", dto);
	}

	/**
	 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
     * 2025.09.18 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public String saveMasterList(MsPboxReqDto paramDto) {
		
		MsPboxReqDto reqDto = ModelMapperUtil.map(paramDto, MsPboxReqDto.class);
		
		List<MsPboxResT1Dto> saveDataList = reqDto.getSaveDataList();
		
		if (null != saveDataList) {
			
			for (MsPboxResT1Dto dto : saveDataList) {
				
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"deleteMaster", dto);
				} 
			}
		}
		
		if (null != saveDataList) {
			
			for (MsPboxResT1Dto dto : saveDataList) {
				
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					
					String carno	= dto.getCarno();
					
					if(carno != null && carno != "") {
						
						List<MsPboxResT1Dto> dupCheckList = commonDao.selectList(SERVICEID_PREFIX +"getCheckCarNo", dto);
						
						if(dupCheckList.get(0).getCnt().equals("0")) {
							// 잘못된 차량번호 입니다.(차량번호:{0})
							throw new UserHandleException(MessageUtil.getMessage("MS_PBOX_001", new String[]{carno}));
						}
					}
					
					commonDao.insert(SERVICEID_PREFIX +"insertMaster", dto);
					
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {

					String carno	= dto.getCarno();
					
					if(carno != null && carno != "") {

						List<MsPboxResT1Dto> dupCheckList = commonDao.selectList(SERVICEID_PREFIX +"getCheckCarNo", dto);
						
						if(dupCheckList.get(0).getCnt().equals("0")) {
							// 잘못된 차량번호 입니다.(차량번호:{0})
							throw new UserHandleException(MessageUtil.getMessage("MS_PBOX_001", new String[]{carno}));
						}
					}

					commonDao.update(SERVICEID_PREFIX +"updateMaster", dto);
				}
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 재고 > 공용기 관리 > P-BOX 관리/사용 현황 P-BOX등록_탭 인쇄 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.18 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public String savePrintList(MsPboxReqDto paramDto) {
		
		MsPboxReqDto reqDto = ModelMapperUtil.map(paramDto, MsPboxReqDto.class);
		
		List<MsPboxResT1Dto> saveDataList = reqDto.getSaveDataList();
		
		if (null != saveDataList) {
			
			for (MsPboxResT1Dto dto : saveDataList) {
				
				commonDao.insert(SERVICEID_PREFIX + "updatePrintMaster", dto);
//				if (StringUtil.isEmpty(dto.getRowStatus())) {
//					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
//				}
//				
//				if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
//					commonDao.insert(SERVICEID_PREFIX + "updatePrintMaster", dto);
//				} 
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
		
}
