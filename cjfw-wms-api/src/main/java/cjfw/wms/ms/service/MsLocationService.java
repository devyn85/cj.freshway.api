package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsLocationDetailReqDto;
import cjfw.wms.ms.dto.MsLocationDetailResDto;
import cjfw.wms.ms.dto.MsLocationReqDto;
import cjfw.wms.ms.dto.MsLocationResDto;
import cjfw.wms.ms.entity.MsLocationEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsLocationService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "msLocation.";
	private final UserContext userContext;
	private final CommonDao commonDao;
	
	/**
	 * @description : 로케이션정보(목록) 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.19 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public List<MsLocationResDto> getMasterList (MsLocationReqDto msLocationReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", msLocationReqDto);
	}
	
	/**
	 * @description : 로케이션정보(단건) 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.19 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public MsLocationDetailResDto getMaster(MsLocationDetailReqDto msLocationDetailReqDto) {
        return commonDao.selectOne(SERVICEID_PREFIX + "getMaster", msLocationDetailReqDto);
    }
	
	/**
	 * @description : 로케이션정보 목록 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.02 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public String saveMasterList(List<MsLocationReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsLocationEntity.class);
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					commonDao.selectOne(SERVICEID_PREFIX +"insertMasterList", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 로케이션정보 정보 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.07 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public String saveMaster(List<MsLocationReqDto> list) {
        if (null != list) {
            for (var dto : list) {
                var entity = ModelMapperUtil.map(dto, userContext, MsLocationEntity.class);
                
                if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
                    commonDao.selectOne(SERVICEID_PREFIX +"updateMaster", entity);
                }
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
	 * @description : 로케이션 정보 UPSERT
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.16 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public String upsertMaster(List<MsLocationReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsLocationEntity.class);
				
//				if ("I".equals(entity.getRowStatus()) && entity.getSerialKey() == null) {
				if ("I".equals(entity.getRowStatus())) {
					validateLocation(entity);
				}
				
				commonDao.selectOne(SERVICEID_PREFIX + "upsertMaster", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	private void validateLocation(MsLocationEntity entity) {
		// 신규행(I)만 검증
		if (!CanalFrameConstants.INSERT.equals(entity.getRowStatus())) return;
		
		// 기존 로케이션 조회
		MsLocationDetailReqDto req = new MsLocationDetailReqDto();
		req.setLoc(entity.getLoc());
		req.setDccode(entity.getDccode());
		
		// 기존과 동일한 LOC 제외
		if (commonDao.selectOne(SERVICEID_PREFIX + "getMaster", req) != null) {
			throw new UserHandleException("이미 존재하는 로케이션입니다.");
		}
	}
}
