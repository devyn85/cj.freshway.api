/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.ipallow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.sysmgt.func.ipallow.dto.IpAllowGetReqDto;
import cjfw.wms.sysmgt.func.ipallow.dto.IpAllowGetResDto;
import cjfw.wms.sysmgt.func.ipallow.dto.IpAllowSaveReqDto;
import cjfw.wms.sysmgt.func.ipallow.entity.IpAllowEntity;
import cjfw.wms.sysmgt.func.ipallow.entity.IpAllowEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class IpAllowService {

    private final CommonDao commonDao;

    private final UserContext userContext;

    /**
     * IP허용예외 리스트
     */
    public Page<List<IpAllowGetResDto>> getIpAllowList(IpAllowGetReqDto ipAllowGetReqDto, Page page) {
        return commonDao.selectPageList("ipAllowService.getIpAllowList", ipAllowGetReqDto, page);
    }

    /**
     * IP허용예외 저장(CUD)
     */
    public String saveIpAllow(IpAllowSaveReqDto ipAllowSaveReqDto) {

        List<IpAllowSaveReqDto.IpAllow> ipAllows = ipAllowSaveReqDto.getIpAllows();
        if(ipAllows != null){
            for(IpAllowSaveReqDto.IpAllow ipAllow: ipAllows){
                IpAllowEntity ipAllowEntity = IpAllowEntityMapper.INSTANCE.saveIpAllowDtoToEntity(ipAllow, userContext);

                log.info("{}", ipAllowEntity);
                if((CanalFrameConstants.INSERT).equals(ipAllow.getRowStatus())) {
                    commonDao.insert("ipAllowService.insertIpAllow", ipAllowEntity);
                } else if((CanalFrameConstants.UPDATE).equals(ipAllow.getRowStatus())) {
                    commonDao.update("ipAllowService.updateIpAllow", ipAllowEntity);
                } else if((CanalFrameConstants.DELETE).equals(ipAllow.getRowStatus())) {
                    commonDao.delete("ipAllowService.deleteIpAllow", ipAllowEntity);
                }
            }
        }
        // return MessageUtil.getMessage("MSG.COM.ERR.013", new Object[]{"arg1"}); // MessageUtil 사용 Return 임의 샘플코드
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}