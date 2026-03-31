package cjfw.wms.kp.service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.kp.dto.KpWdRequestCancelqtyReqDto;
import cjfw.wms.kp.dto.KpWdRequestCancelqtyResTab1Dto;
import cjfw.wms.kp.dto.KpWdRequestCancelqtyResTab2Dto;
import cjfw.wms.kp.dto.KpWdRequestCancelqtyResTab3Dto;
import cjfw.wms.kp.entity.KpWdRequestCancelqtyEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.08.07 
 * @description : 결품대응현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpWdRequestCancelqtyService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "kpWdRequestCancelqtyService.";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 결품대응현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<KpWdRequestCancelqtyResTab1Dto> getTab1MasterList(KpWdRequestCancelqtyReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<KpWdRequestCancelqtyResTab1Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 분류피킹(출고센터) 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<KpWdRequestCancelqtyResTab2Dto> getTab2MasterList(KpWdRequestCancelqtyReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<KpWdRequestCancelqtyResTab2Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 분류피킹(공급센터) 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<KpWdRequestCancelqtyResTab3Dto> getTab3MasterList(KpWdRequestCancelqtyReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<KpWdRequestCancelqtyResTab3Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab3MasterList", dto);
		return list;
	}
	

	/**
	 * @description : 삭제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String delete(KpWdRequestCancelqtyReqDto paramDto) throws Exception {
		int iResult = 0;
		int chunkSize = 200;
		
		// 파라미터 위변조 적용(paramDto->reqDto)
        KpWdRequestCancelqtyReqDto reqDto = ModelMapperUtil.map(paramDto, KpWdRequestCancelqtyReqDto.class);
        
        List<KpWdRequestCancelqtyResTab1Dto> saveList = reqDto.getSaveDataList(); // 삭제리스트
                
        log.info("▶saveList.size->{}",saveList);
		
		List<KpWdRequestCancelqtyEntity> insertList = new ArrayList<>();
		
		// 현재 시각 구하기
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        
        for (int i = 0; i < saveList.size(); i++) {
        	KpWdRequestCancelqtyResTab1Dto dto = saveList.get(i);
			
			KpWdRequestCancelqtyEntity entity = ModelMapperUtil.map(dto, userContext, KpWdRequestCancelqtyEntity.class);
			
			entity.setSavetime(now);
							
			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocline())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docline"}));			

			// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "del", insertList); 
            	insertList.clear();
            }
        }
        
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String save(KpWdRequestCancelqtyReqDto paramDto) throws Exception {
		int iResult = 0;
		int chunkSize = 200;
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		KpWdRequestCancelqtyReqDto reqDto = ModelMapperUtil.map(paramDto, KpWdRequestCancelqtyReqDto.class);
		
		List<KpWdRequestCancelqtyResTab1Dto> saveList = reqDto.getSaveDataList(); // 삭제리스트
		
		log.info("▶saveList.size->{}",saveList);       
		
		List<KpWdRequestCancelqtyEntity> insertList = new ArrayList<>();
		
		// 현재 시각 구하기
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		
		for (int i = 0; i < saveList.size(); i++) {
			KpWdRequestCancelqtyResTab1Dto dto = saveList.get(i);
			
			KpWdRequestCancelqtyEntity entity = ModelMapperUtil.map(dto, userContext, KpWdRequestCancelqtyEntity.class);
			
			entity.setSavetime(now);
							
			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocline())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docline"}));			

			// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "saveconfirm", insertList); 
            	insertList.clear();
            }
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	/**
	 * @description : tab2 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveTab2(KpWdRequestCancelqtyReqDto paramDto) throws Exception {
		int iResult = 0;
		int chunkSize = 200;
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		KpWdRequestCancelqtyReqDto reqDto = ModelMapperUtil.map(paramDto, KpWdRequestCancelqtyReqDto.class);
		
		List<KpWdRequestCancelqtyResTab2Dto> saveList = reqDto.getSaveDataTab2List(); // 삭제리스트
		
		log.info("▶saveList.size->{}",saveList);       
		
		List<KpWdRequestCancelqtyEntity> insertList = new ArrayList<>();
		
		// 현재 시각 구하기
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		
		for (int i = 0; i < saveList.size(); i++) {
			KpWdRequestCancelqtyResTab2Dto dto = saveList.get(i);
			
			KpWdRequestCancelqtyEntity entity = ModelMapperUtil.map(dto, userContext, KpWdRequestCancelqtyEntity.class);
			
			entity.setSavetime(now);
			
			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocline())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docline"}));			
			
			// END.필수입력 check
			insertList.add(entity);
			
			// 200개마다 혹은 마지막 루프일 때 insert(3/3)
			if (insertList.size() == chunkSize || i == saveList.size() -1) {
				commonDao.insert(SERVICEID_PREFIX + "saveTab2confirm", insertList); 
				insertList.clear();
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 대응불가
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String statusCancel(KpWdRequestCancelqtyReqDto paramDto) throws Exception {
		int iResult = 0;
		int chunkSize = 200;
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		KpWdRequestCancelqtyReqDto reqDto = ModelMapperUtil.map(paramDto, KpWdRequestCancelqtyReqDto.class);
		
		List<KpWdRequestCancelqtyResTab1Dto> saveList = reqDto.getSaveDataList(); // 삭제리스트
		
		log.info("▶saveList.size->{}",saveList);
		
		List<KpWdRequestCancelqtyEntity> insertList = new ArrayList<>();
		
		// 현재 시각 구하기
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());					
			
		for (int i = 0; i < saveList.size(); i++) {
			KpWdRequestCancelqtyResTab1Dto dto = saveList.get(i);
			
			KpWdRequestCancelqtyEntity entity = ModelMapperUtil.map(dto, userContext, KpWdRequestCancelqtyEntity.class);
			
			entity.setSavetime(now);
							
			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocline())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docline"}));			

			// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "updateStatusCancel", insertList); 
            	insertList.clear();
            }
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	///**
	// * @description : STO요청
	// * @issues :<pre> 
	// * ----------------------------------------------------------- 
	// * DATE       AUTHOR                MAJOR_ISSUE 
	// * ----------------------------------------------------------- 
	// * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
	// */
	//public String stoRequest(KpWdRequestCancelqtyReqDto paramDto) throws Exception {		
	//	int iResult = 0;
	//	int chunkSize = 200;
	//	
	//	// 파라미터 위변조 적용(paramDto->reqDto)
	//	KpWdRequestCancelqtyReqDto reqDto = ModelMapperUtil.map(paramDto, KpWdRequestCancelqtyReqDto.class);
	//	
	//	List<KpWdRequestCancelqtyResTab1Dto> saveList = reqDto.getSaveDataList(); // 삭제리스트
	//	
	//	log.info("▶saveList.size->{}",saveList);
	//	
	//	List<KpWdRequestCancelqtyEntity> insertList = new ArrayList<>();
	//	
	//	// 현재 시각 구하기
	//	Timestamp now = Timestamp.valueOf(LocalDateTime.now());					
	//		
	//	for (int i = 0; i < saveList.size(); i++) {
	//		KpWdRequestCancelqtyResTab1Dto dto = saveList.get(i);
	//		
	//		KpWdRequestCancelqtyEntity entity = ModelMapperUtil.map(dto, userContext, KpWdRequestCancelqtyEntity.class);
	//		
	//		entity.setSavetime(now);
	//						
	//		// START.필수입력 check - 그리드 변수 등
	//		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
	//		if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocline())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docline"}));			
    //
	//		// END.필수입력 check
    //    	insertList.add(entity);
    //    	
    //    	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
    //    	if (insertList.size() == chunkSize || i == saveList.size() -1) {
    //    		commonDao.insert(SERVICEID_PREFIX + "insertStoRequest", insertList); 
    //        	insertList.clear();
    //        }
	//	}
	//	return CanalFrameConstants.MSG_COM_SUC_CODE;
	//}	
	
	/**
	 * @description : STO요청 - Tab2
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String stoRequest(KpWdRequestCancelqtyReqDto paramDto) throws Exception {		
		// 파라미터 위변조 적용(paramDto->reqDto)
		KpWdRequestCancelqtyReqDto reqDto = ModelMapperUtil.map(paramDto, KpWdRequestCancelqtyReqDto.class);
		List<KpWdRequestCancelqtyResTab2Dto> saveList = reqDto.getSaveDataTab2List(); // 리스트
		log.info("▶saveList.size->{}",saveList);
		
		// 현재 시각 구하기
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		String sDocno = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		DecimalFormat df = new DecimalFormat("00000");
	    int line = 10; // 00010부터 시작
			
		for (int i = 0; i < saveList.size(); i++) {
			KpWdRequestCancelqtyResTab2Dto dto = saveList.get(i);
			KpWdRequestCancelqtyEntity entity = ModelMapperUtil.map(dto, userContext, KpWdRequestCancelqtyEntity.class);
			
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) ) {
				entity.setDocno( "D" + sDocno);
				entity.setDocline(df.format(line).toString());
				line += 10;	
				entity.setFromDccode(entity.getMissDccode());
			}
			
			// START.필수입력 check - 그리드 변수 등
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getMissDocdt())) )     throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"이체일자"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) )     throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocline())) )   throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docline"}));			
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getFromDccode()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromDccode"}));			
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSku())) )       throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"sku"}));			
			if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getUom())) )       throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"uom"}));			
			if( entity.getStoOrderqty() == null )                                  throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"STO요청수량"}));			
			// END.필수입력 check
			
			entity.setSavetime(now);
			
			// 2025.09.09@OM_STO_MISSING_H로 변경 후 건별처리로 변경
			commonDao.insert(SERVICEID_PREFIX + "mergeStoRequest", entity);
			
			commonDao.update(SERVICEID_PREFIX + "updateStoRequest", entity); 
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}		
	
	
}
