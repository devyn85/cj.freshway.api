package cjfw.wms.wd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.common.util.MaskingUtil;
import cjfw.wms.wd.dto.WdQuickMonCloseReqDto;
import cjfw.wms.wd.dto.WdQuickMonCloseResDto;
import cjfw.wms.wd.dto.WdQuickResAPI02Dto;
import cjfw.wms.wd.entity.WdQuickMonCloseEntity;
import cjfw.wms.wd.entity.WdQuickRequestEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description :퀵배송상세  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class WdQuickMonCloseService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(WdQuickMonCloseService.class.getSimpleName()) + ".";
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;	
	///** 공통.service */
	//private final CmCommonService cmCommonService;	

	/** @description : 퀵배송상세 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getMasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getMasterList", reqDto);
	}
	
	/** @description : 퀵배송상세 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public WdQuickMonCloseReqDto saveMasterList(WdQuickMonCloseReqDto paramDto) {
		WdQuickMonCloseReqDto resultDto = new WdQuickMonCloseReqDto();
        int iCnt = 0; // 등록 건수
        int uCnt = 0; // 수정 건수
        int dCnt = 0; // 삭제 건수
        int rv = 0; // 반환 값

        WdQuickMonCloseReqDto reqDto = ModelMapperUtil.map(paramDto, WdQuickMonCloseReqDto.class);
        List<WdQuickMonCloseResDto> saveList = reqDto.getSaveList();
        log.info("▶saveList.size->{}", saveList);
        
        for (WdQuickMonCloseResDto dto : saveList) {
        	if(CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {
        		
            	// START.Master.체크.가능여부
//                List<Map<String, Object>> chkListMap = commonDao.selectList(SERVICEID_PREFIX+"selectChkIntMaster", dto);
//                String chk1 = (String) chkListMap.get(0).get("CHK1"); // 체크.접수여부
//                log.info("chk1(체크.접수여부):{}",chk1);
//                String moreMsg  = "\n\n센터코드 : ["+dto.getDccode()+"]";
//                       moreMsg += "\n퀵주문번호   : ["+dto.getQuickDocno()+"]";
//
//                if("Y".equals(chk1)) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_059", new String[]{"[기 접수처리 완료]"}) + moreMsg ); // 처리할 수 없습니다 {0}.
                // END.Master.체크.가능여부    
                
                uCnt += commonDao.insert(SERVICEID_PREFIX + "updateMasterList01", ModelMapperUtil.map(dto, userContext, WdQuickRequestEntity.class));
                if (uCnt < 1) {
                    throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
                }   
        	}	
		}
		
		if ((iCnt + uCnt + dCnt) < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		} 
		
	    return resultDto;
	}
	
	
	/** 
	 * @description : 퀵배송상세 저장 Method - 마감	
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	 */
	public WdQuickMonCloseReqDto saveMasterCloseList(WdQuickMonCloseReqDto paramDto) {
		WdQuickMonCloseReqDto resultDto = new WdQuickMonCloseReqDto();
        int uCnt = 0; // 수정 건수

        WdQuickMonCloseReqDto reqDto = ModelMapperUtil.map(paramDto, WdQuickMonCloseReqDto.class);
        		
            	// START.Master.체크.가능여부
//                List<Map<String, Object>> chkListMap = commonDao.selectList(SERVICEID_PREFIX+"selectChkIntMaster", dto);
//                String chk1 = (String) chkListMap.get(0).get("CHK1"); // 체크.접수여부
//                log.info("chk1(체크.접수여부):{}",chk1);
//                String moreMsg  = "\n\n센터코드 : ["+dto.getDccode()+"]";
//                       moreMsg += "\n퀵주문번호   : ["+dto.getQuickDocno()+"]";
//
//                if("Y".equals(chk1)) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_059", new String[]{"[기 접수처리 완료]"}) + moreMsg ); // 처리할 수 없습니다 {0}.
                // END.Master.체크.가능여부    
                
        uCnt += commonDao.insert(SERVICEID_PREFIX + "updateCloseQuickList01", ModelMapperUtil.map(reqDto, userContext, WdQuickRequestEntity.class));
        //uCnt += commonDao.insert(SERVICEID_PREFIX + "mergeCloseQuickList", ModelMapperUtil.map(reqDto, userContext, WdQuickRequestEntity.class));
        uCnt += commonDao.insert(SERVICEID_PREFIX + "updateCloseQuickList02", ModelMapperUtil.map(reqDto, userContext, WdQuickRequestEntity.class));
        
        if (uCnt < 1) {
            throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
        }   
		
	    return resultDto;
	}			
	

	/** 
	 * @description : 퀵접수(VSR)및처리 퀵주문접수 API 전문 결과 update Method
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.09 sss (kduimux@cj.net) 생성 </pre>
	 */
	public WdQuickMonCloseReqDto mergeQuickList(WdQuickMonCloseReqDto reqDto) {
		WdQuickMonCloseReqDto resultDto = new WdQuickMonCloseReqDto();
		List<WdQuickResAPI02Dto> saveList = reqDto.getSaveList01(); // 저장리스트
		int uCnt = 0; // 수정 건수
		
		 /*START.퀵주문접수 API Merge*/		
		int chunkSize = 100;
		List<WdQuickMonCloseEntity> insertList = new ArrayList<>();
		for (int i = 0; i < saveList.size(); i++) { 
			WdQuickResAPI02Dto dto = saveList.get(i);
			dto.setSttlYm(reqDto.getSttlYm());
			
			WdQuickMonCloseEntity entity = ModelMapperUtil.map(dto, userContext, WdQuickMonCloseEntity.class);
			entity.setOrderDate(dto.getOrderDate());        // 주문일자
			// 추가변수 세팅(1/3)
			entity.setDccode(dto.getOEtc3());               // 2.물류센터 
			entity.setQuickDocno(dto.getSerialNumber());    
			entity.setTotalAmount(dto.getTotalCost());      // 5.지급금액
			entity.setBasicCost(dto.getBasicCost());        // 6.기본요금
			entity.setAddCost(dto.getAdditionCost());       // 7.추가요금
			entity.setDeliveryCost(dto.getDeliveryCost());  // 8.탁송요금
			//
			entity.setRespDept(dto.getOEtc4());				// 9.귀책부서
			entity.setRespReason(dto.getOEtc5());			// 10.귀책사유
			entity.setRespEmp(dto.getOEtc5());			    // 11.귀책담당자
			
        	log.info("▶dto->{}",MaskingUtil.maskLog(dto));
        
        	// START.필수입력 check - 그리드 변수 등(2/3)
    		if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSttlYm()))      ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"sttlYm"}));
    		if (entity.getBasicCost() == null                                        ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"basicCost"}));

    		// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		uCnt +=  commonDao.insert(SERVICEID_PREFIX + "mergeQuickList", insertList); 
        		insertList.clear();
            }
        }
        /*END.퀵주문접수 API Merge*/		
		
		if (uCnt < 1) {
			throw new UserHandleException("정산 데이터 정보가 없습니다."); // 정산 데이터 정보가 없습니다.
		} 		
		
		return resultDto;
	}
	


}
