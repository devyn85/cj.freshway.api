package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.st.dto.StConvertLotExDCReqDto;
import cjfw.wms.st.dto.StConvertLotExDCResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.16 
 * @description : 외부비축소비기한변경 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StConvertLotExDCService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stConvertLotExDCService.";	
	
	private transient static final String PAKAGE_NAME = "SPST_CONVERT";	
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 외부비축소비기한변경 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.16    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<StConvertLotExDCResDto> getDataHeaderList(StConvertLotExDCReqDto stConvertLotExDCReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderList", stConvertLotExDCReqDto);
	}
	
	/**
	 * @description : 외부비축소비기한변경 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.16    KimSunHo(sunhokim6229@cj.net)   생성
	 */
    public String saveStockConvertLot(StConvertLotExDCReqDto paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        StConvertLotExDCReqDto reqDto = ModelMapperUtil.map(paramDto, StConvertLotExDCReqDto.class);
        
        List<StConvertLotExDCResDto> saveList = reqDto.getSaveList(); // 저장리스트
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드
        
        log.info("▶saveList.size->{}",saveList);
        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
        if (null != saveList) {
            for (StConvertLotExDCResDto dto : reqDto.getSaveList()) { 
                // PKG 파라마터 세팅 - 공통(1/4)
                ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
                dto.setAvc_DCCODE(avc_DCCODE);
                
                // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                String[] keyList = {"DCCODE"
                                    ,"STORERKEY"
                                    ,"ORGANIZE"
                                    ,"AREA"
                                    ,"SKU"
                                    ,"UOM"
                                    ,"FROM_LOC"
                                    ,"FROM_LOT"
                                    ,"FROM_STOCKID"
                                    ,"FROM_STOCKTYPE"
                                    ,"FROM_STOCKGRADE"
                                    ,"TRANQTY"
                                    ,"FROM_LOTTABLE01"
                                    ,"TRANBOX"
                                    ,"CONVERTTYPE"
                                    ,"LOTTABLE01"
                                    ,"LOTTABLE02"
                                    ,"LOTTABLE03"
                                    ,"LOTTABLE04"
                                    ,"LOTTABLE05"
                                    ,"REASONCODE"
                                    ,"REASONMSG"
                                    };
                Object[] valueList = {reqDto.getFixdccode()
                                    , dto.getGStorerkey() 
                                    , dto.getOrganize()
                                    , dto.getArea()
                                    , dto.getSku()
                                    , dto.getUom()
                                    , dto.getFromLoc()
                                    , dto.getFromLot()
                                    , dto.getFromStockid()
                                    , dto.getFromStocktype()
                                    , dto.getFromStockgrade()
                                    , dto.getTranqty()
                                    , dto.getFromLottable01()
                                    , dto.getTranbox()
                                    , "CL"
                                    , reqDto.getInLottable01()
                                    , "STD"
                                    , "STD"
                                    , "STD"
                                    , "STD"
                                    , reqDto.getInReasoncode()
                                    , reqDto.getInReasonmsg()
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
                    log.error("▶외부비축소비기한변경 저장 -> 저장 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                }  
            }
            
        }

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
}
