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
import cjfw.wms.wd.dto.WdDeliveryLabelForceReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelForceResT1Dto;
import cjfw.wms.wd.dto.WdDeliveryLabelForceResT2Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.17
 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용) Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.17 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdDeliveryLabelForceService {
	
    private final CommonDao commonDao;
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(WdDeliveryLabelForceService.class.getSimpleName()) + ".";

    /**
     * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_분류표출력_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.17 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<WdDeliveryLabelForceResT1Dto> getMasterT1List(WdDeliveryLabelForceReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterT1List", dto);
    }
    
	/**
	 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_분류표출력_탭 인쇄시 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
     * 2025.10.17 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public String savePrintList(WdDeliveryLabelForceReqDto paramDto) {
		
		WdDeliveryLabelForceReqDto reqDto = ModelMapperUtil.map(paramDto, WdDeliveryLabelForceReqDto.class);
		
		if (null != reqDto) {
			
			commonDao.update(SERVICEID_PREFIX +"saveInvoiceNoPrtYn", reqDto);
			commonDao.update(SERVICEID_PREFIX +"deleteInvoiceSTO", reqDto);
					
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
    /**
     * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.17 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<WdDeliveryLabelForceResT2Dto> getMasterT2List(WdDeliveryLabelForceReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterT2List", dto);
    }
    
	/**
	 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
     * 2025.10.17 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public String saveMasterList(WdDeliveryLabelForceReqDto paramDto) {
		
		WdDeliveryLabelForceReqDto reqDto = ModelMapperUtil.map(paramDto, WdDeliveryLabelForceReqDto.class);
		
		List<WdDeliveryLabelForceResT2Dto> saveDataList = reqDto.getSaveDataList();
		
		if (null != saveDataList) {
			
			for (WdDeliveryLabelForceResT2Dto dto : saveDataList) {
				
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					
					String dname    = dto.getDcname();
					String prtNm    = dto.getPrtNm();
					
					int valdateData = commonDao.selectOne(SERVICEID_PREFIX + "getDupCheck", dto);
					
					if(valdateData != 0) {
						// 중복된 데이터가 존재합니다.(물류센터:{0}, 출력명칭:{1})
						throw new UserHandleException(MessageUtil.getMessage("WD_DELIVERY_LABEL_FORCE_MSG_001", new String[]{dname,prtNm}));
					}
					
					commonDao.insert(SERVICEID_PREFIX +"insertMaster", dto);
					
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateMaster", dto);
				}
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
    
    /**
     * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용) STO 등록 팝업 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.17 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<WdDeliveryLabelForceResT1Dto> getPopMasterList(WdDeliveryLabelForceReqDto dto) {
    	return commonDao.selectList(SERVICEID_PREFIX + "getPopMasterList", dto);
    }
    
	/**
	 * @description :  출고 > 출고 > 배송 라벨 출력(예외 기준 적용) STO 등록 팝업 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
     * 2025.10.17 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public String savePopMasterList(WdDeliveryLabelForceReqDto paramDto) {
		
		WdDeliveryLabelForceReqDto reqDto = ModelMapperUtil.map(paramDto, WdDeliveryLabelForceReqDto.class);
		
		commonDao.insert(SERVICEID_PREFIX + "savePopMaster", reqDto);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
