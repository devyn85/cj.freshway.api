package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsTplUserReqDto;
import cjfw.wms.ms.dto.MsTplUserResDto;
import cjfw.wms.ms.entity.MsTplUserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : ParkYoSep (dytpq362@cj.net)   
 * @date        : 2025.10.24
 * @description : 정산 > 위탁물류 > 화주정보관리 조회 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24      ParkYoSep (dytpq362@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsTplUserService {

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "msTplUserService.";
    private final CommonDao commonDao;
    private final UserContext userContext;
    
    /**
     * 
     * @description : 화주 목록 조회
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     *  2025.10.24      ParkYoSep (dytpq362@cj.net)  
     */
    public List<MsTplUserResDto> getMasterList(MsTplUserReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }    
    /**
     * 
     * @description : 화주 목록 사용자 조회
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     *  2025.10.27      ParkYoSep (dytpq362@cj.net)  
     */
    public List<MsTplUserResDto> getUserList() {
    	return commonDao.selectList(SERVICEID_PREFIX + "getUserList");
    }    
    
    /**
     * @description : 화주정보 저장 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.28 ParkYoSep 
     */
    public String saveConfirm(MsTplUserReqDto req) {
        if(req != null) {
        List<MsTplUserResDto> list = req.getSaveList();
        for (var dto : list) {
            var entity = ModelMapperUtil.map(dto, userContext, MsTplUserEntity.class);
            if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
                if(commonDao.insert(SERVICEID_PREFIX +"clientInsert", entity) == 0) {
                     throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
                } 
            } else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
                if(commonDao.update(SERVICEID_PREFIX +"clientUpdate", entity) == 0) {
                     throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
                } 
            }
        }
        }
    
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

}
