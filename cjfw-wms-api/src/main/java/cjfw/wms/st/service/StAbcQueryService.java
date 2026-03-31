package cjfw.wms.st.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.st.dto.StAbcQueryReqDto;
import cjfw.wms.st.dto.StAbcQueryResT1Dto;
import cjfw.wms.st.dto.StAbcQueryResT2Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.12
 * @description : 재고 > 재고운영 > ABC 분석  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StAbcQueryService {
	
    private final CommonDao commonDao;

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StAbcQueryService.class.getSimpleName()) + ".";

    /**
     * @description : 재고 > 재고운영 > ABC 분석 분석_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.02 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<StAbcQueryResT1Dto> getMasterT1List(StAbcQueryReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterT1List", dto);
    }
    
    /**
     * @description : 재고 > 재고운영 > ABC 분석 기준_탭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.02 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<StAbcQueryResT2Dto> getMasterT2List(StAbcQueryReqDto dto) {
    	return commonDao.selectList(SERVICEID_PREFIX + "getMasterT2List", dto);
    }
    
	/**
	 * @description : 재고 > 재고운영 > ABC 분석 기준_탭 조회 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.02 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public String saveMasterT2List(StAbcQueryReqDto paramDto) {
		/*
		1. 센터에 사용여부 Y 는 무조건 1개만 존재해야함
		2. 분석명칭은 중복되서는 안된다... (Y/N 여부 떠나서)
		3. 분석명칭 사용여부 N 는 여러 개 저장 될 수 있다
		*/
		StAbcQueryReqDto reqDto = ModelMapperUtil.map(paramDto, StAbcQueryReqDto.class);
		
		List<StAbcQueryResT2Dto> saveDataList = reqDto.getSaveDataT2List();
		
		// 1. 저장 전 파라미터로 받은 사용여부가 Y 인 갯수가 1개 초과	
		if (null != saveDataList) {
			
			int useYnCnt = 0;
			
			for (StAbcQueryResT2Dto dto : saveDataList) {
				
				if (!(CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					if(dto.getUseYn().equals("Y")) {
						useYnCnt++;
					}
				}
				
			} // for end
			
			System.out.println("updateCntA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + useYnCnt);			
			
			if(useYnCnt > 1) {
				// 해당 센터 사용여부 Y인 기준정보는 1개 여야 합니다.
				throw new UserHandleException(MessageUtil.getMessage("ST_ABC_QUERY_003"));
			}
		}
		
		
		if (null != saveDataList) {
			
			for (StAbcQueryResT2Dto dto : saveDataList) {
				
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
				
					// 1. 센터에 사용여부 Y 는 무조건 1개만 존재해야함
					if(dto.getUseYn().equals("Y")) {
						
						int countUseYn = commonDao.selectOne(SERVICEID_PREFIX + "getDupCheckUseYn", dto);
						
						if(countUseYn > 0) {
							
							String dname    = dto.getDcname();
							
							// 해당 센터에 사용중인 기준정보가 존재합니다.(물류센터:{0})
							throw new UserHandleException(MessageUtil.getMessage("ST_ABC_QUERY_001", new String[]{dname}));
						}
					}
					
					int count = commonDao.selectOne(SERVICEID_PREFIX + "getDupCheck", dto);
					
					if(count > 0) {
						
						String dname    = dto.getDcname();
						String abcName  = dto.getAbcName();
						
						// 중복된 데이터가 존재합니다.(물류센터:{0}, 분석명칭:{1})
						throw new UserHandleException(MessageUtil.getMessage("ST_ABC_QUERY_002", new String[]{dname,abcName}));
					}else {
						commonDao.insert(SERVICEID_PREFIX +"insertMaster", dto);
					}
					
				} 
			}
		}
		
		
			

		for (StAbcQueryResT2Dto dto : saveDataList) {
				
			if (StringUtil.isEmpty(dto.getRowStatus())) {
				throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
			}
			
			if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				
				if(dto.getUseYn().equals("Y")) {
					
					int countUseYn = commonDao.selectOne(SERVICEID_PREFIX + "getDupCheckUseYn", dto);
					
					if(countUseYn > 0) {
						
						// 해당 센터 사용여부 Y인 기준정보는 1개 여야 합니다.
						throw new UserHandleException(MessageUtil.getMessage("ST_ABC_QUERY_003"));
					}
				}
				
				commonDao.update(SERVICEID_PREFIX +"updateMaster", dto);
			} 
		}

			
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
