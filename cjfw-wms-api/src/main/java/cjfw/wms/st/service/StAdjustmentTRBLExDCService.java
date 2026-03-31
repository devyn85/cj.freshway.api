package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.st.dto.StAdjustmentTRBLExDCReqDto;
import cjfw.wms.st.dto.StAdjustmentTRBLExDCResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.05 
 * @description : 외부비축BL내재고이관 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StAdjustmentTRBLExDCService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stAdjustmentTRBLExDCService.";	
	
	private transient static final String PAKAGE_NAME = "SPST_CONVERT";	
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 외부비축BL내재고이관 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.04    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<StAdjustmentTRBLExDCResDto> getMasterList(StAdjustmentTRBLExDCReqDto reqDto) {	    
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderList", reqDto);
	}
	
	
	/**
     * @description : 외부비축BL내재고이관 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.16    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveMasterList(StAdjustmentTRBLExDCReqDto reqDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        List<StAdjustmentTRBLExDCResDto> saveList = reqDto.getSaveList(); // 저장리스트
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드
        
        log.info("▶saveList.size->{}",saveList);
        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
        if (null != saveList) {
            
            for (StAdjustmentTRBLExDCResDto dto : reqDto.getSaveList()) { 
                // PKG 파라마터 세팅 - 공통(1/4)
                ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
                dto.setAvc_DCCODE(reqDto.getFixdccode());
                
                // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                String[] keyList = {"DCCODE"
                                    ,"STORERKEY"
                                    ,"ORGANIZE"
                                    ,"AREA"
                                    ,"SKU"
                                    ,"UOM"
                                    ,"LOC"
                                    ,"LOT"
                                    ,"DOCDT"
                                    ,"CONVERTTYPE" 
                                    ,"STOCKTRANSTYPE"
                                    ,"IF_SEND_TYPE"
                                    ,"WORKPROCESSCODE"
                                    ,"OMS_FLAG"
                                    ,"STOCKID"
                                    ,"STOCKGRADE"
                                    ,"TRANQTY"
                                    ,"LOTTABLE01"
                                    ,"SERIALNO"
                                    ,"REASONCODE"
                                    ,"REASONMSG"
                                    ,"IMPUTETYPE"
                                    ,"PROCESSMAIN"
                                    ,"ORDERTYPE"
                                    ,"COSTCD"
                                    ,"STOCKTYPE"
                                    ,"TRANBOX"
                                    ,"CUSTKEY"
                                    };
                Object[] valueList = {reqDto.getFixdccode()
                                    , dto.getGStorerkey() 
                                    , dto.getOrganize()
                                    , dto.getArea()
                                    , dto.getSku()
                                    , dto.getUom()
                                    , dto.getLoc()
                                    , dto.getLot()
                                    , reqDto.getDocdt()
                                    , "AJ"
                                    , "947"
                                    , "WMSAJ"
                                    , "WMSAJ"
                                    , "Y"
                                    , dto.getStockid()
                                    , dto.getStockgrade()
                                    , dto.getTranqty()
                                    , dto.getLottable01()
                                    , dto.getSerialno()
                                    , dto.getReasoncode()
                                    , dto.getReasonmsg()
                                    , dto.getImputetype() 
                                    , dto.getProcessmain()
                                    , dto.getOrdertype()
                                    , dto.getCostcd()
                                    , dto.getStocktype()
                                    , dto.getTranbox()
                                    , dto.getCustkey()
                                    };
                dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, dto); 
                
                // 프로시저 OUT Parameter(3/4)
                resultCode    = (String)dto.getResultCode();
                resultMessage = (String)dto.getResultMessage();
                
                log.info("resultCode->{}",resultCode);
                log.info("resultMessage->{}",resultMessage);
                
                // 프로시저 Exception 처리(4/4)
                if(!"0".equals(resultCode)){
                    log.error("▶외부비축BL내재고이관 저장 -> 저장 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                }  
            }
            
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }   
	
}
