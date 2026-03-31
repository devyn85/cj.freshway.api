package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsCostCenterCtgyInfoExcelResDto;
import 	cjfw.wms.ms.dto.MsCostCenterCtgyInfoReqDto;
import 	cjfw.wms.ms.dto.MsCostCenterCtgyInfoResDto;
import cjfw.wms.ms.entity.MsCostCenterCtgyInfoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.12.08
 * @description : 마감 출고데이터 마스터 - Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.08 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCostCenterCtgyInfoService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "msCostCenterCtgyInfoService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description : 고객마스터 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.08 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<MsCostCenterCtgyInfoResDto> getMasterListTab1(MsCostCenterCtgyInfoReqDto req) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterListTab1", req);
    }

    /**
	 * @description : 미곡 상품 마스터 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.08 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<MsCostCenterCtgyInfoResDto> getMasterListTab2(MsCostCenterCtgyInfoReqDto req) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterListTab2", req);
    }

    /**
	 * @description : 전용 상품 마스터 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.08 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<MsCostCenterCtgyInfoResDto> getMasterListTab3(MsCostCenterCtgyInfoReqDto req) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterListTab3", req);
    }

    /**
     * @description : 엑셀 업로드 유효성 검사
     */
    public List<MsCostCenterCtgyInfoExcelResDto> validateExcelList(List<MsCostCenterCtgyInfoReqDto> reqList) {
        List<MsCostCenterCtgyInfoExcelResDto> result = new ArrayList<>();
        if (reqList != null) {
            for (MsCostCenterCtgyInfoReqDto req : reqList) {
                MsCostCenterCtgyInfoExcelResDto row = commonDao.selectOne(SERVICEID_PREFIX + "validateExcelList", req);
                result.add(row);
            }
        }
        return result;
    }

    /**
     * @description : 엑셀 업로드 저장
     */
    public String saveExcelList(List<MsCostCenterCtgyInfoReqDto> reqList) {
        if (reqList != null) {
            for (MsCostCenterCtgyInfoReqDto req : reqList) {
                commonDao.update(SERVICEID_PREFIX + "saveExcelList", req);
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 엑셀 업로드 유효성 검사
     */
    public List<MsCostCenterCtgyInfoResDto> validateExcelListTab3(MsCostCenterCtgyInfoReqDto reqList) {
    	List<MsCostCenterCtgyInfoResDto> result = new ArrayList<MsCostCenterCtgyInfoResDto>();
    	if (reqList != null) {
    		result = commonDao.selectList(SERVICEID_PREFIX + "validateExcelListTab3", reqList.getSaveList());
    	}
    	return result;
    }
    
    /**
     * @description : 엑셀 업로드 저장
     */
    public String saveExcelListTab3(MsCostCenterCtgyInfoReqDto reqList) {

    	if (reqList != null) {
      	  List<MsCostCenterCtgyInfoResDto> list = reqList.getSaveList();
    		for (var req : list) {
    			   var entity = ModelMapperUtil.map(req, userContext, MsCostCenterCtgyInfoEntity.class);
    			commonDao.update(SERVICEID_PREFIX + "saveExcelListTab3", entity);
    		}
    	}
    	return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
