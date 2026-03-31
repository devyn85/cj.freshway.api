package cjfw.wms.dp.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.dp.dto.DpReceiptExDCDetailResDto;
import cjfw.wms.dp.dto.DpReceiptExDCReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.14 
 * @description : 외부비축입고처리 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DpReceiptExDCService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "dpReceiptExDCService.";	
	
	private transient static final String PAKAGE_NAME = "SPDP_RECEIPT_EXDC";    
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 외부비축입고처리 헤더 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<DpReceiptExDCDetailResDto> getMasterList(DpReceiptExDCReqDto paramDto) {	    
	    DpReceiptExDCReqDto reqDto = ModelMapperUtil.map(paramDto, DpReceiptExDCReqDto.class);
	    
	    return commonDao.selectList(SERVICEID_PREFIX + "getExDcDataDetailList", reqDto);
	}
	
	
	/**
     * @description : 외부비축입고처리 상세 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.14    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<DpReceiptExDCDetailResDto> getDetailList(DpReceiptExDCReqDto paramDto) {     
        DpReceiptExDCReqDto reqDto = ModelMapperUtil.map(paramDto, DpReceiptExDCReqDto.class);
        
        if ("C".equals(reqDto.getCfmType())) {
            // 확정/취소 탭
            return commonDao.selectList(SERVICEID_PREFIX + "getExDcDataDetailList", reqDto);
        } else {
            // 결품처리 탭
            return commonDao.selectList(SERVICEID_PREFIX + "getExDcDataDetailSerialList", reqDto);
        }
    }
    
    /**
     * @description : 외부비축입고처리 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.14    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveMasterList(DpReceiptExDCReqDto paramDto) {        
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        DpReceiptExDCReqDto reqDto = ModelMapperUtil.map(paramDto, DpReceiptExDCReqDto.class);
        
        List<DpReceiptExDCDetailResDto> saveList = reqDto.getSaveList(); // 저장리스트
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드
        
        log.info("▶saveList.size->{}",saveList);
        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
        if (null != saveList) {
            for (DpReceiptExDCDetailResDto dto : saveList) { 
                // 외부창고 요율이 있는지 체크 
                if ("2170".equals(dto.getDccode()) && !"2170-3000".equals(dto.getOrganize())) {
                    int errCnt = commonDao.selectOne(SERVICEID_PREFIX + "getExDcRateChk", dto);
                    if (errCnt > 0) {
                        log.error("▶외부비축입고처리 저장 -> 외부창고 요율이 없습니다.");
                        throw new UserHandleException(""+"외부창고 요율이 없습니다. 재고통관팀에 문의하세요."); 
                    }
                }

                // PKG 파라마터 세팅 - 공통(1/4)
                ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
                dto.setAvc_DCCODE(reqDto.getFixdccode());
                
                // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>  
                String[] keyList = null;
                Object[] valueList = null;
                
                if ("H".equals(reqDto.getDivType())) {
                    // 헤더 저장
                    keyList = new String[] {
                             "DCCODE"
                            ,"STORERKEY"
                            ,"ORGANIZE"
                            ,"SLIPDT"
                            ,"SLIPNO"
                            };
                    valueList = new Object[] {
                              dto.getDccode()
                            , dto.getGStorerkey() 
                            , dto.getOrganize()
                            , dto.getSlipdt()
                            , dto.getSlipno()
                            };
                } else {
                    BigDecimal tranqty = dto.getTranqty();
                    BigDecimal shortagetranqty = dto.getShortagetranqty();
                    
                    if (tranqty.compareTo(BigDecimal.ZERO) != 0) {
                        // 상세 확정
                        keyList = new String[] {
                                "DCCODE"
                               ,"STORERKEY"
                               ,"ORGANIZE"
                               ,"SLIPDT"
                               ,"SLIPNO"
                               ,"SLIPLINE"
                               ,"DOCDT"
                               ,"DOCNO"
                               ,"DOCLINE"
                               ,"SKU"
                               ,"UOM"
                               ,"BUNJA"
                               ,"BUNMO"
                               ,"TRANQTY"
                               ,"SHORTAGETRANQTY"
                               ,"TOLOC"
                               ,"LOTTABLE01"
                               ,"STOCKID"
                               ,"STOCKGRADE"
                               ,"REASONCODE"
                               ,"REASONMSG"
                               ,"REFERENCE02"
                               ,"TRANBOX"
                               ,"FROMCUSTKEY"
                            };
                        valueList = new Object[]  {
                                 dto.getDccode()
                               , dto.getGStorerkey() 
                               , dto.getOrganize()
                               , dto.getSlipdt()
                               , dto.getSlipno()
                               , dto.getSlipline()
                               , dto.getDocdt()
                               , dto.getDocno()
                               , dto.getDocline()
                               , dto.getSku()
                               , dto.getUom()
                               , dto.getBunja()
                               , dto.getBunmo()
                               , tranqty 
                               , 0 
                               , dto.getToloc()
                               , dto.getLottable01()
                               , dto.getStockid()
                               , dto.getStockgrade()
                               , dto.getReasoncode()
                               , dto.getReasonmsg()
                               , dto.getReference02()
                               , dto.getTranbox()
                               , dto.getFromCustkey()
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
                            log.error("▶외부비축입고처리 저장 -> 저장 오류 발생 ");
                            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                        } 
                    } 
                    
                    if (("".equals(resultCode) || resultCode.equals("0")) && shortagetranqty.compareTo(BigDecimal.ZERO) != 0) {
                        resultCode = "";
                        resultMessage  = "";
                        
                        // 상세 결품       
                        keyList = new String[] {
                                "DCCODE"
                               ,"STORERKEY"
                               ,"ORGANIZE"
                               ,"SLIPDT"
                               ,"SLIPNO"
                               ,"SLIPLINE"
                               ,"DOCDT"
                               ,"DOCNO"
                               ,"DOCLINE"
                               ,"SKU"
                               ,"UOM"
                               ,"BUNJA"
                               ,"BUNMO"
                               ,"TRANQTY"
                               ,"SHORTAGETRANQTY"
                               ,"TOLOC"
                               ,"LOTTABLE01"
                               ,"STOCKID"
                               ,"STOCKGRADE"
                               ,"REASONCODE"
                               ,"REASONMSG"
                               ,"REFERENCE02"
                           };
                        valueList = new Object[]  {
                                 dto.getDccode()
                               , dto.getGStorerkey() 
                               , dto.getOrganize()
                               , dto.getSlipdt()
                               , dto.getSlipno()
                               , dto.getSlipline()
                               , dto.getDocdt()
                               , dto.getDocno()
                               , dto.getDocline()
                               , dto.getSku()
                               , dto.getUom()
                               , dto.getBunja()
                               , dto.getBunmo()
                               , 0 
                               , shortagetranqty
                               , dto.getToloc()
                               , dto.getLottable01()
                               , dto.getStockid()
                               , dto.getStockgrade()
                               , dto.getReasoncode()
                               , dto.getReasonmsg()
                               , dto.getReference02()
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
                            log.error("▶외부비축입고처리 저장 -> 저장 오류 발생 ");
                            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                        }
                    }
                }
            }            
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;        
    }
    
    /**
     * @description : 중계영업확정처리 확정
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String confirmMasterList(DpReceiptExDCReqDto paramDto) {        
        DpReceiptExDCReqDto reqDto = ModelMapperUtil.map(paramDto, DpReceiptExDCReqDto.class);
        
        if (null != reqDto) {
            for (DpReceiptExDCDetailResDto dto : reqDto.getSaveList()) {
                
                int checkExistCnt = 0;
                HashMap<String, Object> checkExist = commonDao.selectOne(SERVICEID_PREFIX + "checkExist", dto);

                if (checkExist == null || checkExist.isEmpty()) {
                    checkExistCnt = 0;
                } else {
                    Object cntObj = checkExist.get("CHECK_CNT");
                    if (cntObj != null) {
                        checkExistCnt = Integer.parseInt(cntObj.toString());
                    }
                }
                
                if((checkExistCnt > 0)){
                    log.error("▶외부창고 입고 확정 확정 -> 이미 확정되었습니다.");
                    throw new UserHandleException(""+"이미 확정되었습니다."); 
                } else {
                    commonDao.insert(SERVICEID_PREFIX +"confirmLocal", dto);
                }
            }                
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 중계영업확정처리 반려
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String rejectMasterList(DpReceiptExDCReqDto paramDto) {        
        DpReceiptExDCReqDto reqDto = ModelMapperUtil.map(paramDto, DpReceiptExDCReqDto.class);
        
        if (null != reqDto) {
            for (DpReceiptExDCDetailResDto dto : reqDto.getSaveList()) {
                
                int checkExistCnt = 0;
                HashMap<String, Object> checkExist = commonDao.selectOne(SERVICEID_PREFIX + "checkExist", dto);

                if (checkExist == null || checkExist.isEmpty()) {
                    checkExistCnt = 0;
                } else {
                    Object cntObj = checkExist.get("CHECK_CNT");
                    if (cntObj != null) {
                        checkExistCnt = Integer.parseInt(cntObj.toString());
                    }
                }
                
                if((checkExistCnt > 0)){
                    log.error("▶외부창고 입고 확정 반려 -> 이미 반려되었습니다.");
                    throw new UserHandleException(""+"이미 반려되었습니다."); 
                } else {
                    commonDao.insert(SERVICEID_PREFIX +"rejectLocal", dto);
                }
            }                
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;        
    }
    
}
