package cjfw.wms.wd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.wd.dto.WdAllocationBatchGroupReqDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResCarDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResCheckDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1CarnoDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1CustDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1Dto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1SkuDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1SlipDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1ZoneDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab2DetailDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab2Dto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab3Dto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab4DetailDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab4Dto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab5Dto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab6Dto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab7Dto;
import cjfw.wms.wd.entity.WdAllocationBatchGroupTab3Entity;
import cjfw.wms.wd.entity.WdAllocationBatchGroupTab5Entity;
import cjfw.wms.wd.entity.WdAllocationBatchGroupTab6Entity;
import cjfw.wms.wd.entity.WdAllocationBatchGroupTab7Entity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.08 
 * @description : 출고재고분배 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdAllocationBatchGroupService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdAllocationBatchGroupService.";
	private transient static final String PAKAGE_NAME = "SPWD_ALLOCATION";
	private transient static final String ALLOCTYPE = "BATCHGROUP";
	private transient static final String ALLOCTYPE_TAB2 = "FIXSKU";
	private transient static final String ALLOCTYPE_CUST = "BATCHGROUP_CUST";
	private transient static final String ALLOCTYPE_SLIP = "BATCHGROUP_SLIP";
	private transient static final String PROCESSTYPE = "WD_ALLOCATION_BATCHGROUP";
	private transient static final String PROCESSTYPE_CUST = "WD_ALLOCATION_BATCHGROUP_CUST";
	private transient static final String PROCESSTYPE_STO = "WD_ALLOCATION_BATCHGROUP_STO";
	private transient static final String PROCESSTYPE_STO_CARNO = "WD_ALLOCATION_BATCHGROUP_STO_CARNO";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 출고재고분배-자동분배 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.08 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab1Dto> getTab1MasterList(WdAllocationBatchGroupReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		WdAllocationBatchGroupResCheckDto checkData = commonDao.selectOne(SERVICEID_PREFIX + "getDailyDeadlineYn", dto);
		
		dto.setDailyDeadlineYn(checkData.getDailyDeadlineYn());
		
		List<WdAllocationBatchGroupResTab1Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
		return list;
	}
	/**
	 * @description : 자동분배-거래처별 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.08 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab1CustDto> getTab1CustList(WdAllocationBatchGroupReqDto dto) {
		
		log.info("#### parameter = "+dto.toString());		
		
		WdAllocationBatchGroupResCheckDto checkData = commonDao.selectOne(SERVICEID_PREFIX + "getDailyDeadlineYn", dto);
		
		dto.setDailyDeadlineYn(checkData.getDailyDeadlineYn());
		
		List<WdAllocationBatchGroupResTab1CustDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1CustList", dto);
		return list;
	}
	/**
	 * @description : 자동분배-전표별 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.08 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab1SlipDto> getTab1SlipList(WdAllocationBatchGroupReqDto dto) {
		
		log.info("##### parameter = "+dto.toString());		
		
		WdAllocationBatchGroupResCheckDto checkData = commonDao.selectOne(SERVICEID_PREFIX + "getDailyDeadlineYn", dto);
		
		dto.setDailyDeadlineYn(checkData.getDailyDeadlineYn());
				
		List<WdAllocationBatchGroupResTab1SlipDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1SlipList", dto);
		return list;
	}
	
	/**
	 * @description : 자동분배-상품별 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.08 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab1SkuDto> getTab1SkuList(WdAllocationBatchGroupReqDto dto) { 
		
		log.info("##### parameter = "+dto.toString());		
		
		WdAllocationBatchGroupResCheckDto checkData = commonDao.selectOne(SERVICEID_PREFIX + "getDailyDeadlineYn", dto);
		
		dto.setDailyDeadlineYn(checkData.getDailyDeadlineYn());
		
		List<WdAllocationBatchGroupResTab1SkuDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1SkuList", dto);
		return list;
	}
	
	/**
	 * @description : 자동분배-차량별 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab1CarnoDto> getTab1CarnoList(WdAllocationBatchGroupReqDto dto) { 
		
		log.info("##### parameter = "+dto.toString());		
		
		WdAllocationBatchGroupResCheckDto checkData = commonDao.selectOne(SERVICEID_PREFIX + "getDailyDeadlineYn", dto);
		
		dto.setDailyDeadlineYn(checkData.getDailyDeadlineYn());
		
		List<WdAllocationBatchGroupResTab1CarnoDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1CarnoList", dto);
		return list;
	}
	
	/**
	 * @description : 자동분배-피킹존별 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab1ZoneDto> getTab1ZoneList(WdAllocationBatchGroupReqDto dto) { 
		
		log.info("##### parameter = "+dto.toString());		
		
		WdAllocationBatchGroupResCheckDto checkData = commonDao.selectOne(SERVICEID_PREFIX + "getDailyDeadlineYn", dto);
		
		dto.setDailyDeadlineYn(checkData.getDailyDeadlineYn());
		
		List<WdAllocationBatchGroupResTab1ZoneDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1ZoneList", dto);
		return list;
	}

	/**
	 * @description : 출고재고분배-지정분배 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab2Dto> getTab2MasterList(WdAllocationBatchGroupReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		WdAllocationBatchGroupResCheckDto checkData = commonDao.selectOne(SERVICEID_PREFIX + "getDailyDeadlineYn", dto);
		
		dto.setDailyDeadlineYn(checkData.getDailyDeadlineYn());
		
		List<WdAllocationBatchGroupResTab2Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 지정분배 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab2DetailDto> getTab2DetailList(WdAllocationBatchGroupReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdAllocationBatchGroupResTab2DetailDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2DetailList", dto);
		return list;
	}

	/**
	 * @description : 출고재고분배-피킹유형 미정의 관리처 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab3Dto> getTab3MasterList(WdAllocationBatchGroupReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdAllocationBatchGroupResTab3Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab3MasterList", dto);
		return list;
	}
	

	/**
	 * @description : 출고재고분배-차량별 피킹그룹내 CAR 원거리유형 존재여부 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.27 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResCarDto> getTab4CarInfo(WdAllocationBatchGroupReqDto dto) {
		log.info("### parameter = "+dto.toString());
		List<WdAllocationBatchGroupResCarDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab4CarInfo", dto); 
		return list;
	}

	/**
	 * @description : 출고재고분배-차량별 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab4Dto> getTab4MasterList(WdAllocationBatchGroupReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		WdAllocationBatchGroupResCheckDto checkData = commonDao.selectOne(SERVICEID_PREFIX + "getDailyDeadlineYn", dto);
		
		dto.setDailyDeadlineYn(checkData.getDailyDeadlineYn());
		
		List<WdAllocationBatchGroupResTab4Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab4MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 차량별 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab4DetailDto> getTab4DetailList(WdAllocationBatchGroupReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		WdAllocationBatchGroupResCheckDto checkData = commonDao.selectOne(SERVICEID_PREFIX + "getDailyDeadlineYn", dto);
		
		dto.setDailyDeadlineYn(checkData.getDailyDeadlineYn());
		
		List<WdAllocationBatchGroupResTab4DetailDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab4DetailList", dto);
		return list;
	}

	/**
	 * @description : 거래처상품별 상미율 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.20 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab5Dto> getTab5MasterList(WdAllocationBatchGroupReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdAllocationBatchGroupResTab5Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab5MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 분배예외처리거래처 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.21 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab6Dto> getTab6MasterList(WdAllocationBatchGroupReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdAllocationBatchGroupResTab6Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab6MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 분배예외처리상품 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.21 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdAllocationBatchGroupResTab7Dto> getTab7MasterList(WdAllocationBatchGroupReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdAllocationBatchGroupResTab7Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab7MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 배치별분배 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.09 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveBatch(WdAllocationBatchGroupReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
     // 파라미터 위변조 적용(paramDto->reqDto)
        WdAllocationBatchGroupReqDto reqDto = ModelMapperUtil.map(paramDto, WdAllocationBatchGroupReqDto.class);
        
        List<WdAllocationBatchGroupResTab1Dto> saveList = reqDto.getSaveList(); // 저장리스트
        
        
        log.info("▶saveList.size->{}",saveList);       
        WdAllocationBatchGroupResTab1Dto dto = saveList.get(0);
        
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		        
        	log.info("▶dto->{}",dto);
        	
        	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        	String[] keyList   = {"PROCEDURE" ,"ALLOCTYPE"  ,"PROCESSTYPE"	,"ALLOCFIXTYPE"				,"DCCODE"    	 ,"STORERKEY"	    ,"SLIPDT"		 ,"PLANT"	     ,"STORAGETYPE"		  ,"DISTANCETYPE"	    ,"BATCHGROUP"			};
    		Object[] valueList = {PAKAGE_NAME ,ALLOCTYPE 	,PROCESSTYPE	,reqDto.getAllocfixtype()	,dto.getDccode() ,dto.getStorerkey(),dto.getSlipdt() ,dto.getPlant() ,dto.getStoragetype(),dto.getDistancetype(),dto.getBatchgroup()	};
    		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));	
    		reqDto.setAvc_DCCODE(dto.getDccode());
    		int rv = cmCommonService.saveProcedure(reqDto); 
    		log.info("rv->{}",rv);
    		
    		// 프로시저 OUT Parameter(3/4)
    		resultCode    = StringUtil.nvl(reqDto.getResultCode());
    		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
    		log.info("resultCode->{}",resultCode);
    		log.info("resultMessage->{}",resultMessage);
    		
    		// 프로시저 Exception 처리(4/4)
    		if(!"0".equals(resultCode)){
    			log.error("▶지정취소시 오류 발생 ");
    			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"지정취소"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
    		}  	
    		 /*END.PAKAGE 호출*/  
		      
       
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : STO분배 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.25 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveSTOBatch(WdAllocationBatchGroupReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdAllocationBatchGroupReqDto reqDto = ModelMapperUtil.map(paramDto, WdAllocationBatchGroupReqDto.class);
		
		List<WdAllocationBatchGroupResTab1Dto> saveList = reqDto.getSaveList(); // 저장리스트
		
		
		log.info("▶saveList.size->{}",saveList);       
		WdAllocationBatchGroupResTab1Dto dto = saveList.get(0);
		
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		log.info("▶dto->{}",dto);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE" ,"ALLOCTYPE"  ,"PROCESSTYPE"		,"SEARCHTYPE"			,"DCCODE"    	 ,"STORERKEY"	    ,"SLIPDT"		 ,"PLANT"	     ,"STORAGETYPE"		  ,"DISTANCETYPE"	    ,"BATCHGROUP"			};
		Object[] valueList = {PAKAGE_NAME ,ALLOCTYPE 	,PROCESSTYPE_STO	,reqDto.getSearchtype()	,dto.getDccode() ,dto.getStorerkey(),dto.getSlipdt() ,dto.getPlant() ,dto.getStoragetype(),dto.getDistancetype(),dto.getBatchgroup()	};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));	
		reqDto.setAvc_DCCODE(dto.getDccode());
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶STO분배 저장시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"STO분배 저장"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 자동분배-거래처별 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveBatchDetail1(WdAllocationBatchGroupReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdAllocationBatchGroupReqDto reqDto = ModelMapperUtil.map(paramDto, WdAllocationBatchGroupReqDto.class);
		
		
		List<WdAllocationBatchGroupResTab1CustDto> saveList = reqDto.getSaveDetail1List(); // 저장리스트
		log.info("▶saveList->{}",saveList.size());
		WdAllocationBatchGroupResTab1CustDto dto = saveList.get(0);		
		log.info("▶dto->{}",dto);
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		String[] keyList   = {"PROCEDURE" ,"ALLOCTYPE"    ,"PROCESSTYPE"	,"ALLOCFIXTYPE"				,"DCCODE"    	 ,"STORERKEY"	     ,"SLIPDT"		  ,"PLANT"	      ,"STORAGETYPE"		,"DISTANCETYPE"	       ,"BATCHGROUP"	    ,"TO_CUSTKEY"	    ,"ORDERTYPE"		,"SERIALYN"			};
		Object[] valueList = {PAKAGE_NAME ,ALLOCTYPE_CUST ,PROCESSTYPE_CUST	,reqDto.getAllocfixtype()	,dto.getDccode() ,dto.getStorerkey() ,dto.getSlipdt() ,dto.getPlant() ,dto.getStoragetype() ,dto.getDistancetype() ,dto.getBatchgroup()	,dto.getToCustkey() ,dto.getOrdertype() ,dto.getSerialyn()	};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		reqDto.setAvc_DCCODE(dto.getDccode());		
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶거래처별분배시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"거래처별분배"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 자동분배-전표별 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveBatchDetail2(WdAllocationBatchGroupReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdAllocationBatchGroupReqDto reqDto = ModelMapperUtil.map(paramDto, WdAllocationBatchGroupReqDto.class);
				
		
		List<WdAllocationBatchGroupResTab1SlipDto> saveList = reqDto.getSaveDetail2List(); // 저장리스트
		log.info("▶saveList->{}",saveList.size());
		WdAllocationBatchGroupResTab1SlipDto dto = saveList.get(0);		
		log.info("▶dto->{}",dto);
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		String[] keyList   = {"PROCEDURE" ,"ALLOCTYPE"  	,"ALLOCFIXTYPE"				,"DCCODE"    	 ,"STORERKEY"	    ,"SLIPDT"		 ,"PLANT"	     ,"STORAGETYPE"		  ,"DISTANCETYPE"	    ,"BATCHGROUP"		 ,"SLIPNO"		  ,"TO_CUSTKEY"	      ,"ORDERTYPE"		  ,"SERIALYN"			};
		Object[] valueList = {PAKAGE_NAME ,ALLOCTYPE_SLIP 	,reqDto.getAllocfixtype()	,dto.getDccode() ,dto.getStorerkey(),dto.getSlipdt() ,dto.getPlant() ,dto.getStoragetype(),dto.getDistancetype(),dto.getBatchgroup() ,dto.getSlipno() ,dto.getToCustkey() ,dto.getOrdertype() ,dto.getSerialyn()	};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		reqDto.setAvc_DCCODE(dto.getDccode());		
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶전표별분배시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"전표별분배"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 자동분배-상품별 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveBatchDetail3(WdAllocationBatchGroupReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdAllocationBatchGroupReqDto reqDto = ModelMapperUtil.map(paramDto, WdAllocationBatchGroupReqDto.class);
				
		
		List<WdAllocationBatchGroupResTab1SkuDto> saveList = reqDto.getSaveDetail3List(); // 저장리스트
		log.info("▶saveList->{}",saveList.size());
		WdAllocationBatchGroupResTab1SkuDto dto = saveList.get(0);		
		log.info("▶dto->{}",dto);
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		String[] keyList   = {"PROCEDURE" ,"ALLOCTYPE"  	,"PROCESSTYPE"		,"ALLOCFIXTYPE"				,"DCCODE"    	 ,"STORERKEY"	    ,"SLIPDT"		 ,"PLANT"	     ,"STORAGETYPE"		  ,"DISTANCETYPE"	    ,"BATCHGROUP"		 ,"SERIALYN"		,"SKU"		};
		Object[] valueList = {PAKAGE_NAME ,ALLOCTYPE_CUST 	,PROCESSTYPE_CUST	,reqDto.getAllocfixtype()	,dto.getDccode() ,dto.getStorerkey(),dto.getSlipdt() ,dto.getPlant() ,dto.getStoragetype(),dto.getDistancetype(),dto.getBatchgroup() ,dto.getSerialyn() ,dto.getSku()	};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		reqDto.setAvc_DCCODE(dto.getDccode());		
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶상품별분배시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"상품별분배"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	

	/**
	 * @description : 자동분배-피킹존별 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveBatchDetail4(WdAllocationBatchGroupReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdAllocationBatchGroupReqDto reqDto = ModelMapperUtil.map(paramDto, WdAllocationBatchGroupReqDto.class);
		
		
		List<WdAllocationBatchGroupResTab1ZoneDto> saveList = reqDto.getSaveDetail4List(); // 저장리스트
		log.info("▶saveList->{}",saveList.size());
		WdAllocationBatchGroupResTab1ZoneDto dto = saveList.get(0);		
		log.info("▶dto->{}",dto);
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		String[] keyList   = {"PROCEDURE" ,"ALLOCTYPE"  	,"PROCESSTYPE"		,"ALLOCFIXTYPE"				,"DCCODE"    	 ,"STORERKEY"	    ,"SLIPDT"		 ,"PLANT"	     ,"STORAGETYPE"		  ,"DISTANCETYPE"	    ,"BATCHGROUP"		 ,"SERIALYN"		, "ZONE"		};
		Object[] valueList = {PAKAGE_NAME ,ALLOCTYPE_CUST 	,PROCESSTYPE_CUST	,reqDto.getAllocfixtype()	,dto.getDccode() ,dto.getStorerkey(),dto.getSlipdt() ,dto.getPlant() ,dto.getStoragetype(),dto.getDistancetype(),dto.getBatchgroup() ,dto.getSerialyn() , dto.getZone()	};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		reqDto.setAvc_DCCODE(dto.getDccode());
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶피킹존별분배시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"피킹존별분배"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 지정분배 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveBatchTab2(WdAllocationBatchGroupReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdAllocationBatchGroupReqDto reqDto = ModelMapperUtil.map(paramDto, WdAllocationBatchGroupReqDto.class);
		
		List<WdAllocationBatchGroupResTab2DetailDto> rawList = reqDto.getSaveTab2List();
		log.info("▶rawList.size->{}",rawList);     
		WdAllocationBatchGroupResTab2DetailDto dto  = rawList.get(0);			    
		log.info("▶dto->{}",dto);
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
				
		String[] keyList = {	"PROCEDURE" 
							  	,"ALLOCTYPE"    
							  	,"ALLOCFIXTYPE"				
							  	,"DCCODE"    	 	
							  	,"STORERKEY"	       
							  	,"ORGANIZE"			 
							  	,"CUSTKEY"			  
							  	,"SLIPDT"		   
							  	,"SLIPNO"		   
							  	,"SLIPLINE"		   
							  	,"SERIALYN"			 
							  	,"SKU"		      
							  	,"LOC"		
							  	, "LOT"		   
							  	,"STOCKID"		 
							  	,"STOCKGRADE"		  
							  	,"SERIALNO"		 
							  	,"BOXBARCODE"		  
							  	,"ALLOCATEQTY"		};														
		Object[] valueList = {	PAKAGE_NAME 
								,ALLOCTYPE_TAB2 
								,reqDto.getAllocfixtype()		
								,reqDto.getDccode() 
								,reqDto.getStorerkey() 
								,reqDto.getOrganize() 
								,reqDto.getCustkey() 
								,reqDto.getSlipdt() 
								,reqDto.getSlipno() 
								,reqDto.getSlipline() 
								,reqDto.getSerialyn() 
								,reqDto.getSku() 
								,dto.getLoc() 
								,dto.getLot() 
								,dto.getStockid() 
								,dto.getStockgrade() 
								,dto.getSerialno() 
								,dto.getBoxbarcode() 
								,dto.getAllocateqty()	};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		reqDto.setAvc_DCCODE(reqDto.getDccode());		
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶지정취소시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"지정취소"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 차량별분배 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.27 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveBatchCarno(WdAllocationBatchGroupReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
     // 파라미터 위변조 적용(paramDto->reqDto)
        WdAllocationBatchGroupReqDto reqDto = ModelMapperUtil.map(paramDto, WdAllocationBatchGroupReqDto.class);
        
        List<WdAllocationBatchGroupResTab4Dto> saveList = reqDto.getSaveDataCarnoList(); // 저장리스트
        
        
        log.info("▶saveList.size->{}",saveList);       
        WdAllocationBatchGroupResTab4Dto dto = saveList.get(0);
        
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		        
        	log.info("▶dto->{}",dto);
        	
        	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        	String[] keyList   = {"PROCEDURE" ,"ALLOCTYPE"  ,"PROCESSTYPE"	,"ALLOCFIXTYPE"				,"DCCODE"    	 ,"STORERKEY"	    ,"SLIPDT"		 ,"PLANT"	     ,"STORAGETYPE"		  ,"DISTANCETYPE"	    ,"BATCHGROUP"		,"CARNO"		};
    		Object[] valueList = {PAKAGE_NAME ,ALLOCTYPE 	,PROCESSTYPE	,reqDto.getAllocfixtype()	,dto.getDccode() ,dto.getStorerkey(),dto.getSlipdt() ,dto.getPlant() ,dto.getStoragetype(),dto.getDistancetype(),dto.getBatchgroup(),dto.getCarno()	};
    		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
    		reqDto.setAvc_DCCODE(dto.getDccode());		
    		int rv = cmCommonService.saveProcedure(reqDto); 
    		log.info("rv->{}",rv);
    		
    		// 프로시저 OUT Parameter(3/4)
    		resultCode    = StringUtil.nvl(reqDto.getResultCode());
    		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
    		log.info("resultCode->{}",resultCode);
    		log.info("resultMessage->{}",resultMessage);
    		
    		// 프로시저 Exception 처리(4/4)
    		if(!"0".equals(resultCode)){
    			log.error("▶차량별분배시 오류 발생 ");
    			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"차량별분배"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
    		}  	
    		 /*END.PAKAGE 호출*/  
		      
       
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * 
	 * @description : 피킹유형 미정의 관리처 원거리유형 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.21 		공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveTab3MasterList(List<WdAllocationBatchGroupReqDto> list) {
		if (null != list) {
			for (WdAllocationBatchGroupReqDto dto : list) {
				log.info("dto->{}",dto);
				var entity = ModelMapperUtil.map(dto, userContext, WdAllocationBatchGroupTab3Entity.class);
				commonDao.insert(SERVICEID_PREFIX +"saveTab3MasterList", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * 
	 * @description : 거래처상품별 상미율 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.20 		공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveTab5MasterList(List<WdAllocationBatchGroupReqDto> list) {
		if (null != list) {
			for (WdAllocationBatchGroupReqDto dto : list) {
				log.info("dto->{}",dto);
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				var entity = ModelMapperUtil.map(dto, userContext, WdAllocationBatchGroupTab5Entity.class);
				if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					commonDao.delete(SERVICEID_PREFIX +"deleteTab5MasterList", entity);
				} else {					
					commonDao.insert(SERVICEID_PREFIX +"saveTab5MasterList", entity);
				}				
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * 
	 * @description : 분배예외처리거래처 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.21 		공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveTab6MasterList(List<WdAllocationBatchGroupReqDto> list) {
		if (null != list) {
			for (WdAllocationBatchGroupReqDto dto : list) {
				log.info("dto->{}",dto);
				
				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				var entity = ModelMapperUtil.map(dto, userContext, WdAllocationBatchGroupTab6Entity.class);
				if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					commonDao.delete(SERVICEID_PREFIX +"deleteTab6MasterList", entity);
				} else {					
					commonDao.insert(SERVICEID_PREFIX +"saveTab6MasterList", entity);
				}				
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * 
	 * @description : 분배예외처리상품 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.21 		공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveTab7MasterList(List<WdAllocationBatchGroupReqDto> list) {
		if (null != list) {
			for (WdAllocationBatchGroupReqDto dto : list) {
				log.info("dto->{}",dto);

				if (StringUtil.isEmpty(dto.getRowStatus())) {
					throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_ROWSTATUS"));
				}
				
				var entity = ModelMapperUtil.map(dto, userContext, WdAllocationBatchGroupTab7Entity.class);
				if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					commonDao.delete(SERVICEID_PREFIX +"deleteTab7MasterList", entity);
				} else {					
					commonDao.insert(SERVICEID_PREFIX +"saveTab7MasterList", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
