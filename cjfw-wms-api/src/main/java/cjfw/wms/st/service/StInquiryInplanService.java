package cjfw.wms.st.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.SystemException;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.st.dto.StInquiryInplanReqDto;
import cjfw.wms.st.dto.StInquiryInplanResDto;
import cjfw.wms.st.entity.StInquiryInplanEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.28
 * @description : 재고 > 재고조사 > 재고 실사 지시 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.28 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StInquiryInplanService {
	
    private final CommonDao commonDao;
    
    private final UserContext userContext;

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StInquiryInplanService.class.getSimpleName()) + ".";

    /**
     * @description : 재고 > 재고조사 > 재고 실사 지시 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.28 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<StInquiryInplanResDto> getMasterList(StInquiryInplanReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }
    
    /**
     * @description : 재고 > 재고조사 > 재고 실사 지시 조사명칭 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.28 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
    public List<StInquiryInplanResDto> getInquiryName(StInquiryInplanReqDto dto) {
    	return commonDao.selectList(SERVICEID_PREFIX + "getInquiryName", dto);
    }

	/**
	 * @description : 재고 > 재고조사 > 재고 실사 지시 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.28 KimDongHan (liop0123@cj.net) 생성 </pre>
	 */
	public String saveMasterList(StInquiryInplanReqDto paramDto) {
		
		List<StInquiryInplanResDto> saveDataList = paramDto.getSaveDataList();
		
        //int iCnt = 0; // 등록 건수
        //int uCnt = 0; // 수정 건수
        //int dCnt = 0; // 삭제 건수
        
        List<StInquiryInplanResDto> res = commonDao.selectList(SERVICEID_PREFIX + "getInquiryName", paramDto);
        
		String inquiryName = res.get(0).getInquiryName();
		String inquiryno   = res.get(0).getInquiryno();
        
		for (StInquiryInplanResDto dto : saveDataList) {
			
			StInquiryInplanEntity entity = ModelMapperUtil.map(dto, userContext, StInquiryInplanEntity.class);

			entity.setInquiryno(inquiryno);
			entity.setInquiryName(inquiryName);
			
			commonDao.exec(SERVICEID_PREFIX +"insertMaster", entity);
			
			if(entity.getErrCode() != 0){
				//throw new SystemException(new UserHandleException(""+"에러코드 : "+ entity.getErrCode() + ", 에러메세지 : " + entity.getErrMsg()));
				throw new SystemException(new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_TRIGGER", new String[]{  String.valueOf(entity.getErrCode()) , entity.getErrMsg() })));
			}
			
		}
		
		return inquiryName;
	}
	
}
