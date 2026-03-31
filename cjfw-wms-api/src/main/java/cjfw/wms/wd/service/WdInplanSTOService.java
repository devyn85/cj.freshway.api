package cjfw.wms.wd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.wd.dto.WdInplanSTOEntity;
import cjfw.wms.wd.dto.WdInplanSTOReqDto;
import cjfw.wms.wd.dto.WdInplanSTOResDto;
import cjfw.wms.wd.dto.WdInplanSTOResTab1Dto;
import cjfw.wms.wd.dto.WdInplanSTOResTab2Dto;
import cjfw.wms.wd.dto.WdInplanSTOResTab3Dto;
import cjfw.wms.wd.dto.WdInplanSTOResExcelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdInplanSTOService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
    private static final String SERVICEID_PREFIX = "wdInplanSTOService.";

    private final CommonDao commonDao;

    /**
	 * @description : 광역출고현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<WdInplanSTOResDto> getMasterList(WdInplanSTOReqDto dto) {
    	log.info("### parameter = "+dto.toString());
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }
    
    /**
	 * @description : 광역출고현황 - 주문현황
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<WdInplanSTOResTab1Dto> getMasterListTab1(WdInplanSTOReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterListTab1", dto);
    }
    
    /**
	 * @description : 광역출고현황 - 이력현황
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<WdInplanSTOResTab2Dto> getMasterListTab2(WdInplanSTOReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterListTab2", dto);
    }
    
    /**
	 * @description : 광역출고현황 - 출고이력정보
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public List<WdInplanSTOResTab3Dto> getMasterListTab3(WdInplanSTOReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterListTab3", dto);
    }
    
    
    /**
     * excel다운로드
     */
    public List<WdInplanSTOResExcelDto> getExcellist(WdInplanSTOReqDto dto) {
    	return commonDao.selectList(SERVICEID_PREFIX + "getExcellist", dto);
    }
    
    /**
     * 인쇄 - 마스터 그리드
     */
    public List<WdInplanSTOEntity> getPrintMasterInvoice(WdInplanSTOReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPrintMasterInvoice", dto);
    }
    
    /**
     * 인쇄 - 디테일 그리드
     */
    public List<WdInplanSTOEntity> getPrintDetailInvoice(WdInplanSTOReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPrintDetailInvoice", dto);
    }
}
