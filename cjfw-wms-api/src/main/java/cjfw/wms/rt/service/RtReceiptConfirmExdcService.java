package cjfw.wms.rt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.rt.dto.RtReceiptConfirmExdcMasterReqDto;
import cjfw.wms.rt.dto.RtReceiptConfirmExdcMasterResDto;
import cjfw.wms.rt.dto.RtReceiptConfirmExdcMasterSubReqDto;
import cjfw.wms.rt.dto.RtReceiptConfirmExdcMasterSubResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.18 
 * @description : 외부비축반품확정 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RtReceiptConfirmExdcService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "rtReceiptConfirmExdcService.";	
	
	private transient static final String PAKAGE_NAME = "SPRT_RECEIPT";	
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 외부비축반품확정 입고내역 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 ParkJinWoo 생성
	 */
	public List<RtReceiptConfirmExdcMasterResDto> getDataHeaderList(RtReceiptConfirmExdcMasterReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderList", reqDto);
	}
	
	/**
	 * @description : 외부비축반품확정 마스터 출고내역 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 ParkJinWoo 생성
	 */
	public List<RtReceiptConfirmExdcMasterSubResDto> getDataHeaderSubList(RtReceiptConfirmExdcMasterSubReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderSubList", reqDto);
	}
	

	
	/**
	 * @description : 외부비축반품확정 마스터 입고내역 저장을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22 ParkJinWoo 생성
	 */
	public String saveRtReceiptConfirmExdcHeader(RtReceiptConfirmExdcMasterReqDto reqDto) {
		String resultCode = "";
		String resultMessage = "";
		
		List<RtReceiptConfirmExdcMasterResDto> saveList = reqDto.getSaveMasterList(); // 저장리스트
		String avc_DCCODE = reqDto.getFixDcCode(); // 센터코드
	       log.info("▶saveList.size->{}",saveList);
	       log.info("▶avc_DCCODE->{}",avc_DCCODE);
	       if (null != saveList) {
	            for (RtReceiptConfirmExdcMasterResDto dto : reqDto.getSaveMasterList()) { 
	                // PKG 파라마터 세팅 - 공통(1/4)
					// 
	            	
	                ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
					
	                dto.setAvc_COMMAND("CONFIRM");              // 액션커멘드
	                dto.setAvc_EXECUTEMODE("NOCOMMIT");
	                dto.setAvc_DCCODE(dto.getDcCode());
	                /* ── 1) KEY 배열 ───────────────────────────────────────────── */
	                /* ── Serial-Key 확정 RES 파라미터 ──────────────────────────────── */
	                String[] keyList = { "CHECKYN"
	                                   ,"STORERKEY"
	                                   ,"DCCODE"
	                                   ,"ORGANIZE"
	                                   ,"AREA"
	                                   ,"DOCDT"
	                                   ,"DOCNO"
	                                   ,"DOCLINE"
	                                   ,"SLIPDT"
	                                   ,"SLIPNO"
	                                   ,"SLIPLINE"
	                                   ,"SKU"
	                                   ,"UOM"
	                                   ,"BUNJA"
	                                   ,"BUNMO"
	                                   ,"TRANQTY"
	                                   ,"LOTTABLE01"
	                                   ,"STOCKID"
	                                   ,"STOCKGRADE"
	                                   ,"SHORTAGETRANQTY"
	                                   ,"TOLOC"
	                                   ,"REASONCODE"
	                                   ,"REASONMSG"
	                                   ,"RETURNTYPE"
	                                   ,"INSPECTSERIALKEY"
	                                   ,"FORCEINSPECT"
	                                   ,"TRANBOX"
	                                   ,"CONFIRMTYPE" };

	                /* Res DTO 예시 변수명: res (필요하면 이름만 바꿔주세요) */
	                Object[] valueList = { dto.getCheckYn()
	                                     , dto.getStorerkey()
	                                     , dto.getDcCode()
	                                     , dto.getOrganize()
	                                     , dto.getArea()
	                                     , dto.getDocDt()
	                                     , dto.getDocNo()
	                                     , dto.getDocLine()
	                                     , dto.getSlipDt()
	                                     , dto.getSlipNo()
	                                     , dto.getSlipLine()
	                                     , dto.getSku()
	                                     , dto.getUom()
	                                     , dto.getBunJa()
	                                     , dto.getBunMo()
	                                     , dto.getTranQty()
	                                     , dto.getLotTable01()
	                                     , dto.getStockId()
	                                     , dto.getStockGrade()
	                                     , dto.getShortageTranQty()
	                                     , dto.getToLoc()
	                                     , dto.getReasonCode()
	                                     , dto.getReasonMsg()
	                                     , dto.getReturnType()
	                                     , dto.getInspectSerialKey()
	                                     , dto.getForceInspect()
	                                     , dto.getTranBox()
	                                     , "SERIALKEY"         // 고정 값
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

	/**
	 * @description : 외부비축반품확정 마스터 출고내역 저장 기능 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22 ParkJinWoo 생성
	 */
	public String saveRtReceiptConfirmExdcSub(RtReceiptConfirmExdcMasterSubReqDto reqDto) {
		String resultCode = "";
		String resultMessage = "";
		
		List<RtReceiptConfirmExdcMasterSubResDto> saveList = reqDto.getSaveMasterSubList(); // 저장리스트
		String avc_DCCODE = reqDto.getFixDcCode(); // 센터코드
	       log.info("▶saveList.size->{}",saveList);
	       log.info("▶avc_DCCODE->{}",avc_DCCODE);
	       if (null != saveList) {
	            for (RtReceiptConfirmExdcMasterSubResDto dto : reqDto.getSaveMasterSubList()) { 
	                // PKG 파라마터 세팅 - 공통(1/4)
					 // PKG 파라마터 세팅 - 공통(1/4)

	                ProcedureParametersFactory.initParamDto(reqDto,dto, PAKAGE_NAME);
					
	                dto.setAvc_COMMAND("CONFIRM");              // 액션커멘드
	                dto.setAvc_EXECUTEMODE("NOCOMMIT");
	                dto.setAvc_DCCODE(dto.getDcCode());

	                /* ── 1) KEY 배열 ───────────────────────────────────────────── */
	                /* ── DOCLINE 확정 RES 파라미터 ─────────────────────────────── */
	                String[] keyList = { "CHECKYN"
	                                   ,"STORERKEY"
	                                   ,"DCCODE"
	                                   ,"ORGANIZE"
	                                   ,"AREA"
	                                   ,"DOCDT"
	                                   ,"DOCNO"
	                                   ,"DOCLINE"
	                                   ,"SLIPDT"
	                                   ,"SLIPNO"
	                                   ,"SLIPLINE"
	                                   ,"SKU"
	                                   ,"UOM"
	                                   ,"BUNJA"
	                                   ,"BUNMO"
	                                   ,"TRANQTY"
	                                   ,"LOTTABLE01"
	                                   ,"STOCKID"
	                                   ,"STOCKGRADE"
	                                   ,"SHORTAGETRANQTY"
	                                   ,"TOLOC"
	                                   ,"REASONCODE"
	                                   ,"REASONMSG"
	                                   ,"RETURNTYPE"
	                                   ,"CONFIRMTYPE" };     // ← 상수 추가

	                Object[] valueList = { dto.getCheckYn()
	                                     , dto.getStorerkey()
	                                     , dto.getDcCode()
	                                     , dto.getOrganize()
	                                     , dto.getArea()
	                                     , dto.getDocDt()
	                                     , dto.getDocNo()
	                                     , dto.getDocLine()
	                                     , dto.getSlipDt()
	                                     , dto.getSlipNo()
	                                     , dto.getSlipLine()
	                                     , dto.getSku()
	                                     , dto.getUom()
	                                     , dto.getBunja()
	                                     , dto.getBunmo()
	                                     , dto.getTranQty()
	                                     , dto.getLotTable01()
	                                     , dto.getStockId()
	                                     , dto.getStockGrade()
	                                     , dto.getShortageTranQty()
	                                     , dto.getToLoc()
	                                     , dto.getReasonCode()
	                                     , dto.getReasonMsg()
	                                     , dto.getReturnType()
	                                     , "DOCLINE"          // ← CONFIRMTYPE 고정값
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
