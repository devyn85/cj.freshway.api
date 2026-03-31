package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.SystemException;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsPurchaseCustPopupReqDto;
import cjfw.wms.ms.dto.MsPurchaseCustPopupResDto;
import cjfw.wms.ms.entity.MsPurchaseCustPopupEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsPurchaseCustPopupService {
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     * @return .sqlx returns the location
     */
    private static final String SERVICEID_PREFIX = "msPurchaseCustPopup.";
    private final UserContext userContext;
    private final CommonDao commonDao;
    
    /**
     * @description : 수발주정보 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.30 YeoSeungCheol  		(pw6375@cj.net)  생성	</pre>
     */
    public List<MsPurchaseCustPopupResDto> getPurchaseCust(MsPurchaseCustPopupReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPurchaseCustList", reqDto);
    }

    /**
     * @description : 수발주정보 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.30 YeoSeungCheol  		(pw6375@cj.net)  생성</pre>
     */
    public String savePurchaseCust(List<MsPurchaseCustPopupReqDto> list) {
    	for (MsPurchaseCustPopupReqDto dto : list) {
    		if (list != null) {
    			MsPurchaseCustPopupEntity entity = ModelMapperUtil.map(dto, userContext, MsPurchaseCustPopupEntity.class);
            	
                // MS_PURCHASECUST 테이블 MERGE
    			// commonDao.update(SERVICEID_PREFIX + "updatePurchaseCust", entity);
    			
    			// TRMS_PURCHASECUST.TR_INSERT
    			commonDao.insert(SERVICEID_PREFIX + "insertPurchaseCust", entity);
    			if(entity.getErrCode() != 0){
					throw new UserHandleException(entity.getErrMsg());
				}
    			
                // MS_PURCHASEMST 테이블 MERGE
                commonDao.update(SERVICEID_PREFIX + "updatePurchaseMst", entity);
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
