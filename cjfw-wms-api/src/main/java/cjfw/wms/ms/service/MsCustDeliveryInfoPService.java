package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.ms.dto.MsCustDeliveryInfoPDetailPersonResDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoPDetailResDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoPReqDto;
import cjfw.wms.ms.dto.MsCustDeliveryInfoPResDto;
import cjfw.wms.ms.entity.MsCustDeliveryInfoPEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.30 
 * @description : 기준정보 > 거래처기준정보 > 협력사정보 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCustDeliveryInfoPService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCustDeliveryInfoPService.";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	
		
	/**
	 * @description : 거래처 정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public Page<MsCustDeliveryInfoPResDto> getMasterList(MsCustDeliveryInfoPReqDto dto) {
		Page<MsCustDeliveryInfoPResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getMasterList", dto, dto);
		return result;
	}
	
	/**
	 * @description : 거래처 정보 조회 (단건) & 협력사 입고검수결과 수신자 마스터정보 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public MsCustDeliveryInfoPResDto getMaster(MsCustDeliveryInfoPReqDto dto) {
		MsCustDeliveryInfoPResDto result = new MsCustDeliveryInfoPResDto();
		result.setMasterDto(commonDao.selectOne(SERVICEID_PREFIX + "getMaster", dto));
		result.setMasterDetailDto(commonDao.selectList(SERVICEID_PREFIX + "getMasterDetail",dto));
		result.setMasterPersonDto(commonDao.selectList(SERVICEID_PREFIX + "getMasterPersonList",dto)); 
		
		return result;
	}
		
	
	/**
	 * @description : 거래처 정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public MsCustDeliveryInfoPResDto saveMaster(MsCustDeliveryInfoPReqDto dto) {
		MsCustDeliveryInfoPEntity master = dto.getMaster();
		List<MsCustDeliveryInfoPEntity> detailList = dto.getExcelList();
		if(master != null) {
			String dlvDccode  = commonDao.selectOne(SERVICEID_PREFIX +"selectDefDcCodeByCarno", master);
			master.setDlvDccode(dlvDccode);
			String beforeProcLogiYn = dto.getBeforeData().getProcLogiYn();
			String procLogiYn 		= master.getProcLogiYn();
									
			commonDao.selectOne(SERVICEID_PREFIX +"updateMaster", master);
			commonDao.update(SERVICEID_PREFIX+"updateSmsYn", master);
			
			if("Y".equals(procLogiYn) || ("Y".equals(beforeProcLogiYn) && "N".equals(procLogiYn))) {
				commonDao.insert(SERVICEID_PREFIX +"insertIFSrm", master);	
			}
			
			
		}
		
		MsCustDeliveryInfoPResDto result = new MsCustDeliveryInfoPResDto();
		if(detailList != null) {
			for (MsCustDeliveryInfoPEntity entity : detailList) {
				commonDao.insert(SERVICEID_PREFIX +"updateDetailList", entity);
			}
			result.setMasterDetailDto(commonDao.selectList(SERVICEID_PREFIX + "getMasterDetail",dto));
			
		}
		
		return result;
	}
	
	/**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCustDeliveryInfoPDetailResDto> getValidateExcelList(List<MsCustDeliveryInfoPReqDto> dtoList) {
		List<MsCustDeliveryInfoPDetailResDto> result = new ArrayList<MsCustDeliveryInfoPDetailResDto>();
		if(dtoList != null) {
			result = commonDao.selectList(SERVICEID_PREFIX + "getValidateExcelList", dtoList);
			Set<String> uniqueKeys = new HashSet<>();
			Set<String> duplicatedKeys = new HashSet<>();
			//  중복된 조합(Key) 식별
			for (MsCustDeliveryInfoPDetailResDto resultDto : result) {
			    // UK 조건인 GUBUN + CUSTKEY + NAME 을 조합하여 고유 키 생성
			    String compositeKey = resultDto.getGubun() + "|" + resultDto.getCustkey() + "|" + resultDto.getCustname();
			    
			    if (uniqueKeys.contains(compositeKey)) {
			        duplicatedKeys.add(compositeKey);
			        resultDto.setDuplicateYn("N"); // 현재 행 마킹
			    } else {
			        uniqueKeys.add(compositeKey);
			    }
			}

			//  '첫 번째 데이터'까지 모두 N 처리
			if (!duplicatedKeys.isEmpty()) {
			    for (MsCustDeliveryInfoPDetailResDto editDto : result) {
			        String compositeKey = editDto.getGubun() + "|" + editDto.getCustkey() + "|" + editDto.getCustname();
			        
			        if (duplicatedKeys.contains(compositeKey)) {
			            editDto.setDuplicateYn("N");
			        }
			    }
			}
		}
		return result;
	}
	/**
	 * @description : 협력사 담당자 알림톡 수신 대상 개인정보 복호화(단건)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.17 생성 </pre>
	 */
	public MsCustDeliveryInfoPDetailPersonResDto getMasterPersonDetail(MsCustDeliveryInfoPDetailPersonResDto dto) {
		MsCustDeliveryInfoPDetailPersonResDto result = new MsCustDeliveryInfoPDetailPersonResDto();
		result = commonDao.selectOne(SERVICEID_PREFIX + "getMasterPersonDetail",dto); 
		
		return result;
	}
	/**
	 * @description : 협력사 담당자 알림톡 수신 대상 개인정보 복호화(단건)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.17 생성 </pre>
	 */
	public MsCustDeliveryInfoPDetailResDto getDetailList(MsCustDeliveryInfoPDetailResDto dto) {
		MsCustDeliveryInfoPDetailResDto result = new MsCustDeliveryInfoPDetailResDto();
		result = commonDao.selectOne(SERVICEID_PREFIX + "getDetailList",dto); 
		
		return result;
	}
}
