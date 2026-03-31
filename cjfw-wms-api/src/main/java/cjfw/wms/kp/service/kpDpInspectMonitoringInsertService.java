package cjfw.wms.kp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.kp.entity.KpDpInspectMonitoringEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class kpDpInspectMonitoringInsertService {

	final CommonDao commonDao;

	/** @description : InsertService의 생성자 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.24 User 생성 </pre> 
	*/

	@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertData(KpDpInspectMonitoringEntity rcvGrpEntity) {
		log.info(">>> insertData() start");
    	commonDao.insert(KpDpInspectMonitoringService.SERVICEID_PREFIX +"insertInspectCompMsg", rcvGrpEntity);
    	log.info(">>> insertData() end");
    }
}