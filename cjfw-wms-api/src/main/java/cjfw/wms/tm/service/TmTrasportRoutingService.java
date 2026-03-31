package cjfw.wms.tm.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmTrasportRoutingReqDto;
import cjfw.wms.tm.dto.TmTrasportRoutingResDto;
import cjfw.wms.tm.entity.TmTrasportRoutingEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkYoSep 
 * @date : 2025.10.15
 * @description : 수송경로관리 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 ParkYoSep 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmTrasportRoutingService {

    private transient static final String SERVICEID_PREFIX = "tmTrasportRoutingService."; 
    
    private final CommonDao commonDao;
    
    private final UserContext userContext;

    /**
     * @description : 수송경로관리 노선 조회 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.15 ParkYoSep 생성
     */
    public List<TmTrasportRoutingResDto> getMasterList(TmTrasportRoutingReqDto tmTrasportRoutingReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList",tmTrasportRoutingReqDto);
    }
    
    /**
     * @description : 수송경로관리 운영단가 조회 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.31 ParkYoSep 생성
     */
    public List<TmTrasportRoutingResDto> getDetailList(TmTrasportRoutingReqDto tmTrasportRoutingReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList",tmTrasportRoutingReqDto);
    }
    
    /**
     * @description : 수송경로관리 도착센터조회 조회 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.29 ParkYoSep 생성
     */
    public List<TmTrasportRoutingResDto> getToCenterList() {
        return commonDao.selectList(SERVICEID_PREFIX + "getToCenterList");
    }
    
    
    /**
     * @description : 수송경로관리 노선 저장 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.15 ParkYoSep 
     */
    public String saveMasterList(TmTrasportRoutingReqDto req) {
        if(req != null) {
        List<TmTrasportRoutingResDto> list = req.getSaveList();
        for (var dto : list) {
            var entity = ModelMapperUtil.map(dto, userContext, TmTrasportRoutingEntity.class);
            if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
                if(commonDao.insert(SERVICEID_PREFIX +"carrierRouteInsert", entity) == 0) {
                     throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
                } 
            } else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
                if(commonDao.update(SERVICEID_PREFIX +"carrierRouteUpdate", entity) == 0) {
                     throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
                } 
            }
        }
        }
    
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    /**
     * @description : 수송경로관리 운용단가 저장 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.03 ParkYoSep 
     */
    public String saveDetailList(TmTrasportRoutingReqDto req) {
        if(req != null) {
        List<TmTrasportRoutingResDto> list = req.getSaveList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        for (var dto : list) {
            var entity = ModelMapperUtil.map(dto, userContext, TmTrasportRoutingEntity.class);
            if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
                if("Y".equals(dto.getIsDuplicate())) { // 엑셀 업로드시 중복데이터 일 경우 기존 데이터 업데이트 처리  
                    String From = dto.getFromDate();
                    LocalDate date = LocalDate.parse(From, formatter);
                    LocalDate prevDate = date.minusDays(1);
                    String result = prevDate.format(formatter);
                    dto.setToDate(result);
                     var entity2 = ModelMapperUtil.map(dto, userContext, TmTrasportRoutingEntity.class);
                    commonDao.update(SERVICEID_PREFIX +"carrierPriceUpdate2", entity2);
                    
                }
                if(commonDao.insert(SERVICEID_PREFIX +"carrierPriceInsert", entity) == 0) {
                     throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
                } 
            } else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
                if(commonDao.update(SERVICEID_PREFIX +"carrierPriceUpdate", entity) == 0) {
                     throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
                } 
            }
        }
        }
    
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 수송경로관리 엑셀업로드 데이터 검증
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.21    ParkYoSep(dytpq362@cj.net)   생성
     */
    public List<TmTrasportRoutingResDto> validateExcel(TmTrasportRoutingReqDto reqDto) {
        List<TmTrasportRoutingResDto> result = new ArrayList<TmTrasportRoutingResDto>();
        
        if (null != reqDto) {
            result = commonDao.selectList(SERVICEID_PREFIX + "getWithTempTable", reqDto.getSaveList());
        }
        return result;
    }
}
