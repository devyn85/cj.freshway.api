package cjfw.wms.st.service;

import java.util.List;

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
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.dto.StInquiryMoveReqDto;
import cjfw.wms.st.dto.StInquiryMoveResSkuDto;
import cjfw.wms.st.entity.StInquiryMoveEntity;
import cjfw.wms.st.entity.StInquiryMoveResSkuEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.11.24
 * @description :재고조사결과처리  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.24 sss (kduimux@cj.net) 생성 </pre>
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class StInquiryMoveService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(StInquiryMoveService.class.getSimpleName()) + ".";
	/** PAKAGE NAME - 재고이동 처리 */
	private transient static final String PAKAGE_NAME = "SPST_MOVEMENT";	
	/** PAKAGE NAME - 소비기한 변경 */
	private transient static final String PAKAGE_NAME_CONVERT = "SPST_CONVERT";
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;	
	/** 공통.service */
	private final CmCommonService cmCommonService;	
	
	/** @description : 재고조사결과처리 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.24 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getMasterList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getMasterList", reqDto);
	}	

	/** @description : 재고조사결과처리 상세1 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.24 sss (kduimux@cj.net) 생성 </pre>
	*/
	public <R, T> List<R> getTab1DetailList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getDetailList1", reqDto);
	}

	/** @description : 재고조사결과처리 상세2 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.24 sss (kduimux@cj.net) 생성 </pre>
	*/
	public  <R, T> List<R> getTab2DetailList(T reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX+"getDetailList2", reqDto);
	}
	
	
	/** @description : 재고조사결과처리 저장 - 소비기한 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public StInquiryMoveReqDto saveMasterList(StInquiryMoveReqDto reqDto) {
		StInquiryMoveReqDto resultDto = new StInquiryMoveReqDto();
	    List<StInquiryMoveResSkuDto> saveList = reqDto.getSaveList();
        int iCnt = 0; // 등록 건수
        int uCnt = 0; // 수정 건수
        int dCnt = 0; // 삭제 건수
        
        log.info("▶saveList.size->{}", saveList);
        
		for (StInquiryMoveResSkuDto dto : saveList) {
			StInquiryMoveEntity entity = ModelMapperUtil.map(dto, userContext, StInquiryMoveEntity.class);
			if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
				uCnt += commonDao.exec(SERVICEID_PREFIX +"updateMasterList", entity);
			} 
		}
		
		if ((iCnt + uCnt + dCnt) < 1) {
			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
		} // 적용된 건수가 없습니다.		
		
		return resultDto;
	}	
	
//	/** @description : 재고조사결과처리 저장 - 소비기한변경 / 재고이동 처리 method
//	 * @issues :<pre>
//	 * -----------------------------------------------------------
//	 * DATE       AUTHOR                MAJOR_ISSUE
//	 * -----------------------------------------------------------
//	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
//	*/	
//	public StInquiryMoveReqDto saveMasterListAll(StInquiryMoveReqDto paramDto) {
//		StInquiryMoveReqDto resultDto = new StInquiryMoveReqDto();
//	    String inquirytype = StringUtil.nvl(paramDto.getInquirytype()); // 1:소비기한변경, 2:재고이동
//	    
//	    if("1".equals(inquirytype)) { // 1:소비기한변경
//	    	saveMasterList01(paramDto);
//	    } else if("2".equals(inquirytype)) { // 2:재고이동
//	    	saveMasterList02(paramDto);
//	    } else {
//	    	throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"inquirytype"})); // 해당 정보가 없어 처리할 수 없습니다. - {0}
//	    }
//		return resultDto;
//	}
	
	
//  	public StInquiryMoveReqDto saveMasterListAll(StInquiryMoveReqDto paramDto) {
//  	    StInquiryMoveReqDto resultDto = new StInquiryMoveReqDto();
//  	    // 파라미터 위변조 적용(paramDto->reqDto)
//  	  	StInquiryMoveReqDto reqDto = ModelMapperUtil.map(paramDto, StInquiryMoveReqDto.class);	
//  	  	
//  	  	List<StInquiryMoveResSkuDto> saveList = reqDto.getSaveList();
//  	  	StInquiryMoveResSkuDto dto = saveList.get(0); // 소비기한변경은 1건만 처리하기 때문에 0번째 데이터 기준으로 필수값 체크
//  	  	String caseFlag	= "1"; // 1:소비기한 변경만, 2:재고이동만, 3:재고이동 후 소비기한 변경
//  	  	
//  	  	String reasoncodeMove = StringUtil.nvl(reqDto.getReasoncodeMove()); // 재고이동 사유코드
//  	  	String canMoveYn      = StringUtil.nvl(dto.getCanMoveYn());         // row별 이동가능여부 추가 (차이수량이 음수인 경우에만 이동 가능 - 실물이 부족할 때)
//  	  	
//  	  	BigDecimal tranqty = dto.getTranqty(); // 이동가능수량
//  	  	
//  	  	log.info("▶canMoveYn(재고이동여부)->{}", canMoveYn);
//  	    log.info("▶reasoncodeMove->{}", reasoncodeMove);
//  	    
//
//  	    if("Y".equals(canMoveYn)) { // 재고이동 처리 여부	
//  	        reqDto.setReasoncode(reasoncodeMove); // 재고이동 사유코드 세팅
//  	        //dto.getTranqty()
//  	  	   
//  	        // 재고이동 처리
//  	        saveMasterList02(reqDto);
//  	    } 
//  	   
//  	    // 소비기한변경 처리
//  	    saveMasterList01(paramDto);	// 변조없이 원본 paramDto 전달해야 함(재고이동 처리 후 소비기한변경 처리해야 하기 때문에)   
//  	   
//  	  	return resultDto;
//  	  }		
	
	/** @description 
	 * <pre>
	 * 재고조사결과처리 저장 - 소비기한변경 / 재고이동 처리 method
	 *   ->반드시 재고이동 처리 후 소비기한변경 처리해야 함
	 *   ->케이스            
	 *         지시  조사  차이수량   이동가능수량
	 *         --------------------------
	 *         10	12	 2	     10       =>CASE1->[처리사항]소비기한 10개만 처리, 사유)실사결과가 실물이 더 많으면 실사조정처리 않함
	 *          ->소비기한만 처리
	 *         -------------------------- 
	 *         10	8	-2	     10       =>CASE2->[정상처리]실물이 2개  분실 10   재고수량 중 2개를 이동 처리 후 나머지 8개 소비기한 변경
	 *          ->ABS(차이수량) < 이동가능수량 일 경우
	 *          ->재고이동 2개 + 소비기한 변경 8개 처리
	 *         -------------------------- 
	 *         56	0	-56	      5       =>CASE3->[예외처리]실물이 56개 분실 56개 실제이동가능수량이 5개 밖에 없음 => 재고이동 처리 후 소비기한변경 처리 시 이동가능수량 5개로 소비기한변경 처리 시 처리 불필요 로 변경
	 *          ->ABS(차이수량) > 이동가능수량 일 경우
	 *          ->재고이동 5개 + 소비기한 변경 0개 처리(상태를 처리 불필요 만 변경)
 	 *         --------------------------     
	 *                  
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/	
	public StInquiryMoveReqDto saveMasterListAll(StInquiryMoveReqDto paramDto) {
        StInquiryMoveReqDto resultDto = new StInquiryMoveReqDto();
        int rv = 0; // 반환 값
        
        // 파라미터 위변조 적용(paramDto->reqDto)
		StInquiryMoveReqDto reqDto = ModelMapperUtil.map(paramDto, StInquiryMoveReqDto.class);	
		
		List<StInquiryMoveResSkuDto> saveList = reqDto.getSaveList();
		String reasoncodeMove = StringUtil.nvl(reqDto.getReasoncodeMove()); // 재고이동 사유코드
		String resultMessage = "";
		for (StInquiryMoveResSkuDto dto : saveList) { // ui에서 transaction loop 처리므로 그리드 데이터가 1건 전송되어 처리 됨
			String caseFlag      = StringUtil.nvl(dto.getCaseFlag()); // CASE1 ~ CASE3  
			log.info("▶caseFlag->{}", caseFlag);
		    log.info("▶reasoncodeMove->{}", reasoncodeMove);
		    
		   
	        if("CASE2".equals(caseFlag) || "CASE3".equals(caseFlag)) { // 재고이동 처리
	            // 1. tranqty를 재고이동 처리
	            log.info("▶재고이동.처리수량->{}", caseFlag);
	            saveMasterList02Imp(dto, reqDto);
	            
				resultMessage = "▶재고이동:" + dto.getTranqty(); // UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	            
	            if("CASE2".equals(caseFlag) ) { // 소비기한.처리수량 세팅
		        	log.info("▶소비기한.처리수량->{}", dto.getLottableTranqty());	        	
		            // 2. 이동가능수량(posbqty)에서 재고이동 처리 후 tranqty를 뺀 값으로 tranqty 재설정(재고이동 처리를 뺀 나머지)
	                dto.setTranqty(dto.getLottableTranqty()); // lottableTranqty : 소비기한 처리수량	   
	            }
                
		    } 
	        
	        if("CASE1".equals(caseFlag) || "CASE2".equals(caseFlag)) { // 소비기한 처리
	        	log.info("▶소비기한 처리");
			    saveMasterList01(dto, reqDto);	// 소비기한 처리
				resultMessage += "▶소비기한:" + dto.getTranqty(); // UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	        } else if("CASE3".equals(caseFlag)) {
	            // 상태 UPDATE
	            rv = commonDao.update(SERVICEID_PREFIX + "updateMasterList01", ModelMapperUtil.map(dto, userContext, StInquiryMoveResSkuEntity.class));
	    		if (rv < 1) {
	    			throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
	    		}
	    		
	    		resultMessage = "▶이동종료(재고 이동에 따른 소비기한 처리 대상 없음)"; // UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	        }
	        
		}
	   
		resultDto.setResultMessage("".equals(resultMessage)? "MSG.COM.SUC.003" : resultMessage); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
		return resultDto;
	}	
	
	/** @description : 재고조사결과처리 저장 - 소비기한변경
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public StInquiryMoveReqDto saveMasterList01(StInquiryMoveResSkuDto dto,StInquiryMoveReqDto paramDto) {
		StInquiryMoveReqDto resultDto = new StInquiryMoveReqDto();
	    String resultCode = "";
	    String resultMessage  = "";

	    StInquiryMoveReqDto reqDto = ModelMapperUtil.map(paramDto, StInquiryMoveReqDto.class);
        //List<StInquiryMoveResSkuDto> saveList = reqDto.getSaveList();
        //log.info("▶saveList.size->{}", saveList);
        
		// START.필수입력(2/4)
		if("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getConverttype())))  {throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_056",new String[]{"getConverttype"})); }// 해당 정보가 없어 처리할 수 없습니다.-{0}
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getConverttype()))) {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"converttype"}));    }
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getLottable02())))  {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"lottable02"}));     }
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getLottable03())))  {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"lottable03"}));     }
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getLottable04())))  {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"lottable04"}));     }
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getLottable05())))  {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"lottable05"}));     }
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReasoncode())))  {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"reasoncode"}));    }
		if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReasonmsg())))   {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"reasonmsg"}));     }
		
		// END.필수입력       

        //for (StInquiryMoveResSkuDto dto : saveList) {
        	// PKG 파라마터 세팅 - 공통(1/4)
            ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME_CONVERT);
            
            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
			// START.필수입력
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getDccode())))         {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));        }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getStorerkey())))      {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));     }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getOrganize())))       {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));      }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getArea())))           {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"area"}));          }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getSku())))            {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"sku"}));           }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getUom())))            {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"uom"}));           }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getFromLoc())))        {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromLoc"}));       }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getFromLot())))        {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromLot"}));       }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getFromStockid())))    {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromStockid"}));   }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getFromStocktype())))  {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromStocktype"})); }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getInquirydt())))      {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"inquirydt"}));     }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getInquiryno())))      {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"inquiryno"}));     }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getSku())))            {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"sku"}));           }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getFromStockid())))    {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"fromStockid"}));   }
			// END.필수입력      

    		String[] keyList   = {  
		               "PROCEDURE" 
		              ,"DCCODE"          ,"STORERKEY" ,"ORGANIZE"        ,"AREA"         ,"SKU"                     
		              ,"UOM"             ,"FROM_LOC"  ,"FROM_LOT"        ,"FROM_STOCKID" ,"FROM_STOCKTYPE" 
		              ,"FROM_STOCKGRADE" ,"TRANQTY"   ,"FROM_LOTTABLE01" ,"LOTTABLE01"   ,"REASONCODE"
		              ,"REASONMSG"       ,"INQUIRYDT" ,"INQUIRYNO"       ,"SKU"          ,"STOCKID"
		              //
		              ,"CONVERTTYPE"     ,"LOTTABLE02" ,"LOTTABLE03","LOTTABLE04"
		              ,"LOTTABLE05"  
		              //
		              ,"INQUIRY_NAME" ,"PRIORITY" ,"INQUIRYTYPE" ,"MOBILE_ADD_YN"  
		              //
		              ,"CALL_FROM"
		             };
			Object[] valueList = {
							      PAKAGE_NAME_CONVERT             
    				              // strColMerge
    				             ,dto.getDccode()         ,dto.getStorerkey()  ,dto.getOrganize()       ,dto.getArea()	       ,dto.getSku()             
    				             ,dto.getUom()            ,dto.getFromLoc()    ,dto.getFromLot()        ,dto.getFromStockid()  ,dto.getFromStocktype() 
    				             ,dto.getFromStockgrade() ,dto.getTranqty()    ,dto.getFromLottable01() ,dto.getToLot()        ,reqDto.getReasoncode()    
    				             ,reqDto.getReasonmsg()   ,dto.getInquirydt()  ,dto.getInquiryno()      ,dto.getSku()          ,dto.getFromStockid()
    				             // get Parameter
    				             ,reqDto.getConverttype() ,reqDto.getLottable02(),reqDto.getLottable03(),reqDto.getLottable03()
    				             ,reqDto.getLottable05()  
    				             ,dto.getInquiryName()  ,dto.getPriority(),dto.getInquirytype(),dto.getMobileAddYn()
    				             ,"1" // 호출하는곳(1:재고조사결과처리)
					             };
			dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
			dto.setAvc_DCCODE(dto.getDccode());
			dto.setAvc_ORGANIZE(dto.getOrganize());
			try {
	            int rv = cmCommonService.saveProcedure(dto);
	            log.info("rv->{}", rv);

	            resultCode = StringUtil.nvl(dto.getResultCode());
	            resultMessage = StringUtil.nvl(dto.getResultMessage());
			} catch(Exception e) {
				log.error("",e);
				resultCode = "-1";
				resultMessage = EtcUtil.getMessage(e);
			}

            log.info("resultCode->{}", resultCode);
            log.info("resultMessage->{}", resultMessage);

            if (!resultCode.equals("0")) {
                log.error("▶소비기한변경 처리 시 오류 발생 ");
                throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"소비기한변경"}) + resultMessage);
            }
        //}
	    return resultDto;
	}	
	
	/** @description : 재고조사결과처리 저장 - 재고이동 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public StInquiryMoveReqDto saveMasterList02(StInquiryMoveReqDto paramDto) {
		StInquiryMoveReqDto resultDto = new StInquiryMoveReqDto();
		StInquiryMoveReqDto reqDto = ModelMapperUtil.map(paramDto, StInquiryMoveReqDto.class);
		
		List<StInquiryMoveResSkuDto> saveList = reqDto.getSaveList();
		
		for (StInquiryMoveResSkuDto dto : saveList) {
			saveMasterList02Imp(dto, reqDto);
		}
		
		return resultDto;
	}
	
	/** @description : 재고조사결과처리 저장 - 재고이동 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.30 sss (kduimux@cj.net) 생성 </pre>
	*/
	public StInquiryMoveReqDto saveMasterList02Imp(StInquiryMoveResSkuDto dto, StInquiryMoveReqDto paramDto) {
		StInquiryMoveReqDto resultDto = new StInquiryMoveReqDto();
	    String resultCode = "";
	    String resultMessage  = "";

	    StInquiryMoveReqDto reqDto = ModelMapperUtil.map(paramDto, StInquiryMoveReqDto.class);
        //List<StInquiryMoveResSkuDto> saveList = reqDto.getSaveList();
        //log.info("▶saveList.size->{}", saveList);
        
        //for (StInquiryMoveResSkuDto dto : saveList) {
        
	    	// PKG 파라마터 세팅 - 공통(1/4)
	        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);
	        
	        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
			// START.필수입력
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getDccode())))        {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));    }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getGStorerkey())))    {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"})); }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getInquirydt())))     {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"inquirydt"})); }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getInquiryno())))     {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"inquiryno"})); }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getOrganize())))      {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));  }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(dto.getSku())))           {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"sku"}));       }
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getReasoncode()))) {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"reasoncode"}));}
			if ("".equals(cjfw.core.utility.StringUtil.nvl(reqDto.getLoc())))        {throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"loc"}));       }
				
			// END.필수입력  
			
	       // WHERE ST_INQUIRY.DCCODE        = C1.DCCODE        --1.센터코드                                
	       //   AND ST_INQUIRY.INQUIRYDT     = C1.INQUIRYDT     --2.조사일자                                
	       //   AND ST_INQUIRY.INQUIRYNO     = C1.INQUIRYNO     --3.조사번호                                
	       //   AND ST_INQUIRY.INQUIRY_NAME  = C1.INQUIRY_NAME  --4.조사명칭                                
	       //   AND ST_INQUIRY.PRIORITY      = C1.PRIORITY      --5.회차                                  
	       //   AND ST_INQUIRY.INQUIRYTYPE   = C1.INQUIRYTYPE   --6.유형                                  
	       //   AND ST_INQUIRY.STORERKEY     = C1.STORERKEY     --7.고객사코드                               
	       //   AND ST_INQUIRY.ORGANIZE      = C1.ORGANIZE      --8.조직코드                                
	       //   AND ST_INQUIRY.AREA          = C1.AREA          --9.창고코드 SAP 창고 혹은 별도의 창고 코드            
	       //   AND ST_INQUIRY.SKU           = C1.SKU           --10.상품코드                               
	       //   AND ST_INQUIRY.LOC           = C1.LOC           --11.로케이션                               
	       //   AND ST_INQUIRY.LOT           = C1.LOT           --12.재고 구분 LOT                          
	       //   AND ST_INQUIRY.STOCKID       = C1.STOCKID       --13.재고 구분 ID                           
	       //   AND ST_INQUIRY.STOCKGRADE    = C1.STOCKGRADE    --14.재고 등급( ABC )                       
	       //   AND ST_INQUIRY.MOBILE_ADD_YN = C1.MOBILE_ADD_YN --15.모바일추가여부         			
	
	        String[] keyList = {
					             "PROCEDURE"
					            ,"DCCODE"       ,"STORERKEY" ,"ORGANIZE"   ,"AREA"        ,"SKU"
					            ,"UOM"		    ,"LOT"       ,"STOCKID"    ,"STOCKGRADE"  ,"TRANQTY"
					            ,"FROM_LOC"     ,"INQUIRYDT" ,"INQUIRYNO"  ,"SKU"         ,"STOCKID"
					            ,"INQUIRY_NAME" ,"PRIORITY"  ,"INQUIRYTYPE","LOT"         ,"STOCKGRADE"  
					            ,"MOBILE_ADD_YN"
					            // getparameter로 넘길 값들
					            ,"TO_LOC"     ,"REASONCODE"
					            ,"CALL_FROM"
					           };
	        Object[] valueList = {
				                PAKAGE_NAME,
				                dto.getDccode()      ,dto.getGStorerkey()  ,dto.getOrganize()    ,dto.getArea()       ,dto.getSku()   
				               ,dto.getUom()         ,dto.getLot()         ,dto.getStockid()     ,dto.getStockgrade() ,dto.getTranqty()
				               ,dto.getFromLoc()     ,dto.getInquirydt()   ,dto.getInquiryno()   ,dto.getSku()        ,dto.getFromStockid()
				               ,dto.getInquiryName()  ,dto.getPriority()    ,dto.getInquirytype() ,dto.getLot()       ,dto.getStockgrade()
				               ,dto.getMobileAddYn()
				               // getparameter로 넘길 값들
				               ,reqDto.getLoc() , reqDto.getReasoncode()
				               ,"1" // 호출하는곳(1:재고조사결과처리)
				              
	        };
	        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
			dto.setAvc_DCCODE(dto.getDccode());
			dto.setAvc_ORGANIZE(dto.getOrganize());
			try {
	            int rv = cmCommonService.saveProcedure(dto);
	            log.info("rv->{}", rv);
	
	            resultCode = StringUtil.nvl(dto.getResultCode());
	            resultMessage = StringUtil.nvl(dto.getResultMessage());
			} catch(Exception e) {
				log.error("",e);
				resultCode = "-1";
				resultMessage = EtcUtil.getMessage(e);
			}
	
	        log.info("resultCode->{}", resultCode);
	        log.info("resultMessage->{}", resultMessage);
	
	        if (!resultCode.equals("0")) {
	            log.error("▶재고조사결과처리 처리 시 오류 발생 ");
	            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"재고조사결과처리"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
	        }
	    //}     

	    return resultDto;
	}	

}
