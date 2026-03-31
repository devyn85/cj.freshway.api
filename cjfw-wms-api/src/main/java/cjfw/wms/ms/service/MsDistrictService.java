package cjfw.wms.ms.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.SystemException;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsDistrictReqDto;
import cjfw.wms.ms.dto.MsDistrictResDto;
import cjfw.wms.ms.entity.MsDistrictEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.08.08
 * @description : 기준정보 > 권역기준정보 > 권역별차량관리 목록 조회 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.08        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsDistrictService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msDistrictService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * 
	 * @description : 목록조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsDistrictResDto> getMasterList(MsDistrictReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * 
	 * @description : 목록 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveMasterList(List<MsDistrictReqDto> list) {
	    
	    if (!ObjectUtils.isEmpty(list)) {
	        for (MsDistrictReqDto dto : list) {
	            
	            // 1. 건별 유효성 검사 (검증 로직을 루프 안으로 이동)
	            // 검증 실패 시 UserHandleException이 발생하여 전체 롤백됨
	            this.getSingleRolltainerValidation(dto);

	            // 2. DTO -> Entity 변환
	            var entity = ModelMapperUtil.map(dto, userContext, MsDistrictEntity.class);
	            entity.setEditWho(userContext.getUserId());
	            entity.setAddWho(userContext.getUserId());

	            // 3. DB 저장/수정
	            if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
	                commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
	                
	                if(entity.getErrCode() != 0){
	                    String sTemp = entity.getErrMsg();
	                    if (sTemp != null &&sTemp.contains("ORA-00001") ) {
	                        throw new UserHandleException("이미 등록된 정보입니다. 확인하시고 다시 입력하시기 바랍니다 ");
	                    } else {
	                        throw new SystemException(new UserHandleException("에러코드 : "+ entity.getErrCode() + ", 에러메세지 : " + entity.getErrMsg()));  
	                    }
	                }
	            } else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {                                       
	                commonDao.selectOne(SERVICEID_PREFIX +"update", entity);
	                
	                if(entity.getErrCode() != 0){
	                    throw new SystemException(new UserHandleException("에러코드 : "+ entity.getErrCode() + ", 에러메세지 : " + entity.getErrMsg()));
	                }
	            }
	        }
	    }
	    return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
    
    /**
     * @description : 동일 물류센터, pop, carno 를 가지고 db 조회 하여 다음 조건에 맞는지 확인 
     *   1. 각 롤테이너(from, to) 값은 max롤테이너 값보다 작아야 한다.
     *   2. 롤테이너 from 은 to 보다 클수 없다. 
     *   3. 롤테이너 to 은 from 보다 작을수 없다.
     *   4.기존 db 에 등록된 차량 정보 중복될수 없다. dcCode, pop, carNo
     *   5.롤테이너 구간(from, to)이 기존 db 에 등록된 구간과 중복될수 없다. dcCode, pop, startNo, endNo
     *   return : true(유효함), false(유효하지 않음)
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.01.29 jun (kthis77@cj.net) 생성 </pre>
     */
    /**
     * 리스트 전체가 아닌, 단일 객체(Dto)에 대해 유효성을 검증하도록 변경
     */
    private void getSingleRolltainerValidation(MsDistrictReqDto dto) {
        
        BigDecimal rolltainerStartNo = dto.getRolltainerStartNo() != null ? new BigDecimal(dto.getRolltainerStartNo()) : BigDecimal.ZERO;
        BigDecimal rolltainerEndNo = dto.getRolltainerEndNo() != null ? new BigDecimal(dto.getRolltainerEndNo()) : BigDecimal.ZERO;
        BigDecimal rolltainerMax = dto.getRolltainerMax() != null ? new BigDecimal(dto.getRolltainerMax()) : BigDecimal.ZERO;
        
        // 1. max값 체크
        if (rolltainerStartNo.compareTo(rolltainerMax) > 0 || rolltainerEndNo.compareTo(rolltainerMax) > 0 ) {
            throw new UserHandleException("롤테이너 번호는 최대 롤테이너 번호보다 클 수 없습니다.");
        }
        // 2. start > end 체크
        if (rolltainerStartNo.compareTo(rolltainerEndNo) >= 0) {
            throw new UserHandleException("롤테이너 시작 번호는 종료 번호보다 클 수 없습니다.");
        }
        // 3. end < start 체크 (2번과 중복 로직이나 명시적으로 둠)
        if (rolltainerEndNo.compareTo(rolltainerStartNo) <= 0) {
            throw new UserHandleException("롤테이너 종료 번호는 시작 번호보다 작을 수 없습니다.");
        }
        
        // 4. 차량 정보 중복 체크 (DB 조회)
        // 주의: Update인 경우, 자기 자신(PK)은 제외하고 조회하도록 SQL이 작성되어 있어야 함
        int resultCnt = commonDao.selectOne(SERVICEID_PREFIX + "selectValidateRolltainerByCarNoAndPopAndDcCode", dto);
        if (resultCnt > 0) {
            throw new UserHandleException("차량 정보가 중복됩니다.");
        }
// 0322 주석처리
//        // 5. 롤테이너 구간 중복 체크 (DB 조회)
//        // 앞선 행이 Insert/Update 되면서 DB 상태가 변했으므로, 이 쿼리는 변경된 DB 상태를 반영하여 체크함
//        resultCnt = commonDao.selectOne(SERVICEID_PREFIX + "selectValidateRolltainerByPopAndDcCodeAndRolltainer", dto);
//        if (resultCnt == 0) {
//        	resultCnt = commonDao.selectOne(SERVICEID_PREFIX + "selectValidateRolltainerByPopAndDcCodeAndRolltainer2", dto);
//        	
//            if (resultCnt > 0) {
//                throw new UserHandleException("롤테이너 구간이 기존 등록된 구간과 중복됩니다.");
//            }
//            else {
//            	resultCnt = commonDao.selectOne(SERVICEID_PREFIX + "selectValidateRolltainerByPopAndDcCodeAndRolltainer3", dto);
//				if (resultCnt > 0) {
//					throw new UserHandleException("롤테이너 구간이 기존 등록된 구간과 중복됩니다.");
//				}
//            }
//        }
//        // 6. 롤테이너 구간 중복 체크 (DB 조회)
//        // 
        
    }

    /**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.02        JangJaeHyun (jhjang43@cj.net)       생성
	 */
    public List<MsDistrictResDto> getValidateExcelList(List<MsDistrictReqDto> dtoList) {
        List<MsDistrictResDto> checkedResult = new ArrayList<MsDistrictResDto>();
        if(dtoList != null) {
            for (MsDistrictReqDto dto : dtoList) {
                MsDistrictResDto result = commonDao.selectOne(SERVICEID_PREFIX + "getValidateExcelList2", dto);
                checkedResult.add(result);
            }
        }
        return checkedResult;
    }
}
