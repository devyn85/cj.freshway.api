package cjfw.wms.st.service;

import java.util.List;

import cjfw.wms.ib.service.IbAllWeightService;
import org.apache.commons.lang3.StringUtils;
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
import cjfw.wms.st.dto.StInquiryResultReqDto;
import cjfw.wms.st.dto.StInquiryResultResDto;
import cjfw.wms.st.entity.StInquiryResultEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import java.util.ArrayList;
import cjfw.wms.common.util.EtcUtil;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description :조사지시현황  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class StInquiryResultService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StInquiryResultService.class.getSimpleName()) + ".";
	/** PAKAGE NAME */
	private transient static final String PAKAGE_NAME = "SPST_INQUIRY";
    private transient static final String PROCESSTYPE_NEW = "NEW_ST_INQUIRY";
    //private transient static final String PROCESSTYPE = "ST_INQUIRY";
    private transient static final String TEMPTABLETYPE = "ST";
	///** 프로세스타임 */
	//private transient static final String PROCESSTYPE = "XX";	
	///** 임시테이블 타입 */
	//private transient static final String TEMPTABLETYPE = "XXX";	
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;		
	/** 공통.service */
	private final CmCommonService cmCommonService;
    /**정산.service 금액가져오기*/
    private final IbAllWeightService ibAllWeightService;

	/** @description : 조사지시현황 로케이션별 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
	*/
	public List<StInquiryResultResDto> getTab1MasterList(StInquiryResultReqDto reqDto) {
		List<StInquiryResultResDto> list = commonDao.selectList(SERVICEID_PREFIX+"getTab1MasterList", reqDto);
		if("Y".equals(reqDto.getViewPriceYn())) {
			ibAllWeightService.fetchSapPrice(list);
			for(var el : list) {
				el.setTotaldiffamt(el.getTotaldiffqty().multiply(el.getPurchaseprice()));
			}
		}
		return list;
	}

	/** @description : 조사지시현황 상품별 조회 List Method - getTab1MasterList 공융사용
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getTab2MasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getTab1MasterList", reqDto);
	}
	
	/** @description : 조사지시현황 seq 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getSequenceNumber(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getSequenceNumber", reqDto);
	}	
	
	/** @description : 조사지시현황 - 재지시 처리 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성 </pre>
	*/
	public StInquiryResultReqDto saveMasterList(StInquiryResultReqDto paramDto) {
		StInquiryResultReqDto resultDto = new StInquiryResultReqDto();
        String resultCode = "";
        String resultMessage = "";
        
        StInquiryResultReqDto reqDto = ModelMapperUtil.map(paramDto, StInquiryResultReqDto.class);
        List<StInquiryResultResDto> saveList = reqDto.getSaveList();
        log.info("▶saveList.size->{}", saveList);
        
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE_NEW); 
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		
		int chunkSize = 200;
		List<CmSyProcessTempStEntity> insertList = new ArrayList<>();	
		
        for (int i = 0; i < saveList.size(); i++) {
        	StInquiryResultResDto dto = saveList.get(i);
        	dto.setReqFlag(reqDto.getReqFlag()); // 요청구분(1:로케이션별, 2:상품별)
        	
			CmSyProcessTempStEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempStEntity.class);
			
			entity.setPackagename(PAKAGE_NAME); // 패키지명
			entity.setProcesstype(PROCESSTYPE_NEW); // 프로세스타입
			
			String columnsDto    = "DCCODE     |INQUIRYDT  |INQUIRYNO   |INQUIRY_NAME|LASTPRIORITY|STORERKEY     |ORGANIZE     |AREA   |SKU     |LOC     |LOT     |STOCKID   |STOCKGRADE     |INQUIRY_NAME_NEW|LASTPRIORITY |INQUIRYNO_NEW|PRIORITY_NEW   |REQ_FLAG    |LOTTYPE   |REASONMSG"; // 그리드 변수
			String columnsEntity = "FROM_DCCODE|FROM_IOTYPE|FROM_STOCKID|BATCH_NO    |ETCQTY1     |FROM_STORERKEY|FROM_ORGANIZE|TO_AREA|FROM_SKU|FROM_LOC|FROM_LOT|TO_STOCKID|FROM_STOCKGRADE|BATCH_NO        |FROM_ORDERQTY|LIST_NO      |FROM_CONFIRMQTY|TO_STOCKTYPE|ARCHIVECOP|MEMO1    "; // 컬럼명
			entity = (CmSyProcessTempStEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
			
			// START.필수입력 check - 그리드 변수 생략함.
			// END.필수입력 check
			
			insertList.add(entity);
			// 200개마다 혹은 마지막 루프일 때 insert(3/3)
			if (insertList.size() == chunkSize || i == saveList.size() -1) {
				commonDao.insert(SERVICEID_PREFIX + "insertTempExcel2", insertList); 
				insertList.clear();
			}
		}		
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"};
		Object[] valueList = {PAKAGE_NAME, PROCESSTYPE_NEW};
		reqDto.setAvc_DCCODE(paramDto.getFixdccode());
		reqDto.setAvc_ORGANIZE(paramDto.getFixdorganize());
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		int rv = cmCommonService.saveProcedure(reqDto); 
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());

		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_057", new String[]{"재고조사 재지시"}) + resultMessage); // {0} 저장 시 문제가 발생했습니다.  
		}
		/*END.PAKAGE 호출*/ 
		
		return resultDto;

	}
	
	/** @description : 조사지시현황 예약재고조사 저장 Method
	 *                 ->PROCEDURE : SPST_INQUIRY.CREATION_NEXT
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public StInquiryResultReqDto saveReserveMasterList(StInquiryResultReqDto paramDto) {
	    StInquiryResultReqDto resultDto = new StInquiryResultReqDto();
	    String resultCode = "";
	    String resultMessage  = "";

        StInquiryResultReqDto reqDto = ModelMapperUtil.map(paramDto, StInquiryResultReqDto.class);
        List<StInquiryResultResDto> saveList = reqDto.getSaveList();
        log.info("▶saveList.size->{}", saveList);

        for (StInquiryResultResDto dto : saveList) {
        	// PKG 파라마터 세팅 - 공통(1/4)
            ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);
            
            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
			// START.필수입력
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getDccode())))      throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));  //	
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getGStorerkey())))  throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"})); //
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getInquirydt())))   throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"inquirydt"})); //
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getInquiryno())))   throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"inquiryno"})); //
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getOrganize())))    throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"})); //
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getSku())))         throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"sku"})); //
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getStockid())))     throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"stockid"})); //
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getStockgrade())))  throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"stockid"})); //
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getInquiryName()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"stockgrade"})); //
			if (dto.getLastpriority() == null)                   throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"priority"})); //
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getLottype())))     throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"lottype"})); //
			//
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReqFlag()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"LOTTYPE"})); //
				
			// END.필수입력            

            String[] keyList = {
					             "PROCEDURE"
					            ,"DCCODE"     ,"STORERKEY"    ,"INQUIRYDT" ,"INQUIRYNO" ,"REASONMSG"
					            ,"ORGANIZE"   ,"SKU"		  ,"LOC"       ,"LOT"       ,"STOCKID"
					            ,"STOCKGRADE" ,"INQUIRY_NAME" ,"PRIORITY"  ,"LOTTYPE"
					            //
					            ,"REQ_FLAG"
					           };
            Object[] valueList = {
                PAKAGE_NAME,
                dto.getDccode()     ,dto.getGStorerkey()  ,dto.getInquirydt()    ,dto.getInquiryno() ,dto.getReasonmsg()
               ,dto.getOrganize()   ,dto.getSku()         ,dto.getLoc()          ,dto.getLot()       ,dto.getStockid()
               ,dto.getStockgrade() ,dto.getInquiryName() ,dto.getLastpriority() ,dto.getLottype()
               ,reqDto.getReqFlag() // 요청구분(1:로케이션별, 2:상품별)요청구분(1:로케이션별, 2:상품별)
            };
            dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
			dto.setAvc_DCCODE(dto.getDccode());
			dto.setAvc_ORGANIZE(dto.getOrganize());
            int rv = cmCommonService.saveProcedure(dto);
            log.info("rv->{}", rv);

            resultCode = StringUtil.nvl(dto.getResultCode());
            resultMessage = StringUtil.nvl(dto.getResultMessage());
            log.info("resultCode->{}", resultCode);
            log.info("resultMessage->{}", resultMessage);

            if (!resultCode.equals("0")) {
                log.error("▶예약재고조사 처리 시 오류 발생 ");
                throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"예약재고조사"}) + resultMessage);
            }
        }

	    return resultDto;
	}
	
	/** @description : 조사지시현황 예약재고조사 - 종료처리 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public StInquiryResultReqDto saveCloseMasterList(StInquiryResultReqDto reqDto) {
	    StInquiryResultReqDto resultDto = new StInquiryResultReqDto();
	    List<StInquiryResultResDto> saveList = reqDto.getSaveList();
        //int iCnt = 0; // 등록 건수
        //int uCnt = 0; // 수정 건수
        //int dCnt = 0; // 삭제 건수
        
        log.info("▶saveList.size->{}", saveList);
        
		for (StInquiryResultResDto dto : saveList) {
			StInquiryResultEntity entity = ModelMapperUtil.map(dto, userContext, StInquiryResultEntity.class);
			if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				commonDao.exec(SERVICEID_PREFIX +"updateCloseMaster", entity);
			} 
		}
		
		//if ((iCnt + uCnt + dCnt) < 1) {
		//	throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		//} // 적용된 건수가 없습니다.		
		
		return resultDto;
	}	

}
