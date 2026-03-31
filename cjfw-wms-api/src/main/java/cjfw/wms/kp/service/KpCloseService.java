package cjfw.wms.kp.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.MessageUtil;
import cjfw.wms.kp.dto.KpCloseReqDto;
import cjfw.wms.kp.dto.KpCloseResDetailDto;
import cjfw.wms.kp.dto.KpCloseResDto;
import cjfw.wms.webservice.ficlose.DT_SCM0080_SCM;
import cjfw.wms.webservice.ficlose.DT_SCM0080_SCMT_PC;
import cjfw.wms.webservice.ficlose.DT_SCM0080_SCM_response;
import cjfw.wms.webservice.ficlose.SI_SCM0080_SCM_SOProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.08.22
 * @description : 모니터링 > 물동 > 물동마감 진행 현황 Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.22 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpCloseService {
	
    private final CommonDao commonDao;
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(KpCloseService.class.getSimpleName()) + ".";

    /**
     * @description : 모니터링 > 물동 > 물동마감 진행 현황 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<KpCloseResDto> getMasterList(KpCloseReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

    /**
     * @description : 모니터링 > 물동 > 물동마감 진행 현황 상세 조회 Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public List<KpCloseResDetailDto> getDetailList(KpCloseReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
	}

	/**
	 * @description : 모니터링 > 물동 > 물동마감 진행 현황 저장(월마감처리) Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
     * 2025.08.21 KimDongHan (liop0123@cj.net) 생성 </pre>
     */
	public String saveMasterList(KpCloseReqDto paramDto) {
		
		List<KpCloseResDto> masterList = paramDto.getSaveList();
		
		try {
			
			int row = 0;
			
			String docdt = "";
			String plant = "";
			
			if(masterList != null) {
				
				for (KpCloseResDto entity : masterList) {
					
					try {
						
						docdt = entity.getDocdt();
						plant = entity.getPlant();
						
						row = commonDao.insert(SERVICEID_PREFIX +"insertIbClose", entity);
					
						commonDao.insert(SERVICEID_PREFIX +"insertDailyInoutClose", entity);
						
					
					} catch (Exception e) {
						// 마감 데이터 생성오류 ( 화면 재조회 후 재시도 해주세요.)
						throw new UserHandleException(MessageUtil.getMessage("MSG_KP_CLOSE_003"));
					}
					
					if(row == 1) {
						
						log.info("====================================== WMS FI KP CLOSE IF START ====================================== ");
						
						SI_SCM0080_SCM_SOProxy proxy = new SI_SCM0080_SCM_SOProxy();
						DT_SCM0080_SCM fiData = null;
						DT_SCM0080_SCMT_PC sndData = null;
						
						fiData = new DT_SCM0080_SCM();
						
						Calendar calendar = Calendar.getInstance();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
						SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
						
						//fiData.setXSYS("SCM");
						fiData.setXDATS(dateFormat.format(calendar.getTime()));
						fiData.setXTIMS(timeFormat.format(calendar.getTime()));
						
						sndData = new DT_SCM0080_SCMT_PC();
						
						sndData.setSTORERKEY(paramDto.getGStorerkey());
						sndData.setDOCDT(docdt);
						sndData.setCLOSECD("WM001-"+plant);
						sndData.setEDITWHO(paramDto.getGUserId());
						sndData.setCLOSESTATUS("X");
						
						fiData.setT_PC(sndData);
						
						DT_SCM0080_SCM_response response = null;
						response = proxy.si_scm0080_scm_so(fiData);
						
						// 응답에 대한 결과 처리
						if(response != null && "E".equals(response.getT_RETURN().getIF_FLAG())) {
							// 인터페이스 처리중 에러발생[{0}]
							throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_IF", new String[]{response.getT_RETURN().getIF_MEMO()}));     
						}
						log.info("====================================== WMS FI KP CLOSE IF RESULT["+response.getT_RETURN().getIF_FLAG()+"]====================================== ");
						log.info("====================================== WMS FI KP CLOSE IF END ====================================== ");
					}else {
						// 마감 데이터 생성오류 (CNT : {0})
						throw new UserHandleException(MessageUtil.getMessage("MSG_KP_CLOSE_004", new String[]{String.valueOf(row)}));
					}
				}
			}
		} catch(UserHandleException e) {
			e.printStackTrace();
			log.error("Exception", e);
			throw new UserHandleException(e.getErrorMessage());
		} catch(Exception e) {
			e.printStackTrace();
			log.error("Exception", e);
			throw new UserHandleException(e);
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
