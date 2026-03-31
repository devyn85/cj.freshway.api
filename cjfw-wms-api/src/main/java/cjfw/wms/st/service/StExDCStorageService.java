package cjfw.wms.st.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.st.dto.StExDCEctFeePopupResDto;
import cjfw.wms.st.dto.StExDCEtcFeePopupReqDto;
import cjfw.wms.st.dto.StExDCStoragePopupReqDto;
import cjfw.wms.st.dto.StExDCStorageReqDto;
import cjfw.wms.st.dto.StExDCStorageResDto;
import cjfw.wms.st.entity.StExDCStorageEntity;
import cjfw.wms.sys.dto.SysAuthorityUserResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.21 
 * @description : 외부창고정산 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StExDCStorageService {

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "stExDCStorageService."; 
    private transient static final String PAKAGE_NAME = "SPST_EXDCSTORAGE"; 
    private transient static final String PROCEDURE_ETCFEE_H_NAME = "CHG_ALL_FEE_HEADER";
    private transient static final String PROCEDURE_ETCFEE_L_NAME = "CHG_ALL_FEE_LINES";
    private transient static final String PROCEDURE_ETCFEE_DF_NAME = "CHG_ALL_FEE_TAX_DIFF";    
    private transient static final String PROCEDURE_CALC_STORAGEFEE_NAME = "CALC_MONTHLY_FEE";      //보관료 계산    
    private transient static final String PROCEDURE_DSTB_STORAGEFEE_NAME = "DIVIDE_MONTHLY_FEE";    //보관료 배분
    private transient static final String PROCEDURE_CLOSE_STORAGEFEE_NAME = "CHG_MONTHLY_FEE";      //보관료 비용 마감
    private transient static final String PROCEDURE_CLOSE_BASESTOCK_NAME = "INS_MONTHLY_BASE_G_NC"; //기초 재고 마감
    
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private final UserContext userContext;

    
    /**
     * @description : 외부창고정산 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.21   KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<StExDCStorageResDto> getMasterList(StExDCStorageReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderList", reqDto);
    }
	
    /**
     * @description : 외부창고정산 엑셀 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.21  KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<StExDCStorageResDto> getMasterExcelList(StExDCStorageReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDataExcelList", reqDto);
    }
	
    /**
     * @description : 외부창고정산 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveMasterList(StExDCStorageReqDto paramDto) {
        StExDCStorageReqDto reqDto = ModelMapperUtil.map(paramDto, userContext,  StExDCStorageReqDto.class);
        
        List<StExDCStorageResDto> saveList = reqDto.getSaveList(); // 저장리스트
        
        log.info("▶saveList.size->{}",saveList);
        
        if (null != saveList) {
            for (int i = 0; i < saveList.size(); i++) {
                StExDCStorageResDto dto = saveList.get(i);
                StExDCStorageEntity entity = ModelMapperUtil.map(dto, userContext, StExDCStorageEntity.class);
                
                int rv = commonDao.update(SERVICEID_PREFIX + "saveConfirm", entity);
                
                if (rv == 0) {
                    log.error("▶외부창고정산 -> 비용이 진행된 건이 있습니다. 테이블시리얼번호[" + dto.getSerialkey() + "]");
                    throw new UserHandleException("비용이 진행된 건이 있습니다. 테이블시리얼번호[" + dto.getSerialkey() + "]");
                }
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }   
    
    /* @description : 외부창고정산 저장
    * @issues :<pre> 
    * ----------------------------------------------------------- 
    * DATE       AUTHOR                MAJOR_ISSUE 
    * ----------------------------------------------------------- 
    * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
    */
    public List<StExDCStorageResDto> saveExcelList(StExDCStorageReqDto paramDto) {
       StExDCStorageReqDto reqDto = ModelMapperUtil.map(paramDto, userContext,  StExDCStorageReqDto.class);
       
       List<StExDCStorageResDto> saveList = reqDto.getSaveList(); // 저장리스트
       
       log.info("▶saveList.size->{}",saveList);
       
       if (null != saveList) {
           for (int i = 0; i < saveList.size(); i++) {
               StExDCStorageResDto dto = saveList.get(i);
               StExDCStorageEntity entity = ModelMapperUtil.map(dto, userContext, StExDCStorageEntity.class);
               
               int rv = commonDao.update(SERVICEID_PREFIX + "saveConfirm", entity);
               
               if (rv == 0) {
                   log.error("▶외부창고정산 -> 비용이 진행된 건이 있습니다. 테이블시리얼번호[" + dto.getSerialkey() + "]");
                   dto.setProcessflag("FAIL");
                   dto.setProcessmsg("비용이 진행된 건입니다.");
               //    throw new UserHandleException("비용이 진행된 건이 있습니다. 테이블시리얼번호[" + dto.getSerialkey() + "]");
               } else {
                   dto.setProcessflag("SUCC");
                   dto.setProcessmsg("정상처리되었습니다.");
               }
           }
       }
       
       return saveList;
    }   
    
    /**
     * @description : 외부창고정산 기타비용등록
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveEtcFee(StExDCEtcFeePopupReqDto paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        String[] keyList;
        Object[] valueList;
        
        StExDCEtcFeePopupReqDto reqDto = ModelMapperUtil.map(paramDto, userContext, StExDCEtcFeePopupReqDto.class);
        
        List<StExDCEctFeePopupResDto> priceList = reqDto.getPriceList(); // 저장리스트
        List<StExDCStorageResDto> serialkeyList = reqDto.getSerialkeyList(); // 저장리스트
        
        log.info("▶priceList.size->{}",priceList);
        log.info("▶serialkeyList.size->{}",serialkeyList);
        
        // 고객사
        String custkey = reqDto.getCustkey();
        
        // 작업료트랜잭션 일련번호(WorkTransactionSN) 채번
        Long  workTransactionSN = commonDao.selectOne(SERVICEID_PREFIX + "getWorkTransactionSN", reqDto);
        
        //1.기타비용등록 대상 (정산내역) 건을 기본 저장
        if (null != serialkeyList) {            
            for (int i = 0; i < serialkeyList.size(); i++) {
                serialkeyList.get(i).setWorkTransactionSn(workTransactionSN); 
                
                StExDCStorageEntity entity = ModelMapperUtil.map(serialkeyList.get(i), userContext, StExDCStorageEntity.class);
                commonDao.insert(SERVICEID_PREFIX + "insertExDCStorageExpense", serialkeyList.get(i));
            }
        }
        //END..1.기타비용등록 대상 (정산내역) 건을 기본 저장
        
        //2.기타비용등록 헤더 저장
        // PKG 파라마터 세팅 - 공통(1/4)
        StExDCEctFeePopupResDto procedureDto = new StExDCEctFeePopupResDto();
        ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
        procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
        procedureDto.setAvc_COMMAND(PROCEDURE_ETCFEE_H_NAME);
        
        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
        keyList = new String[] {"WORK_TRANSACTION_SN", "CUSTKEY", "COMMIT"};
        valueList = new Object[] {workTransactionSN, custkey, "FALSE"};
        procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        
        int rv = cmCommonService.saveProcedure(procedureDto); 
        log.info("rv->{}",rv);
        
        // 프로시저 OUT Parameter(3/4)
        resultCode    = (String)procedureDto.getResultCode();
        resultMessage = (String)procedureDto.getResultMessage();
        
        log.info("resultCode->{}",resultCode);
        log.info("resultMessage->{}",resultMessage);
        
        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")){
            log.error("▶외부창고정산 -> 기타비용등록 헤더 저장 오류 발생 ");
            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        //END..2.기타비용등록 헤더 저장 
        
        //3.기타비용등록 항목별 저장
        if (null != priceList) {
            for (int i = 0; i < priceList.size(); i++) {
                if (priceList.get(i).getTranprice() > 0) {
                    // PKG 파라마터 세팅 - 공통(1/4)
                    procedureDto = new StExDCEctFeePopupResDto();
                    ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
                    procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
                    procedureDto.setAvc_COMMAND(PROCEDURE_ETCFEE_L_NAME);
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
                    if (i == priceList.size()) {
                        keyList = new String[] {"WORK_TRANSACTION_SN", "CUSTKEY", "COSTCODE", "AMOUNT"};
                        valueList = new Object[] {workTransactionSN, custkey, priceList.get(i).getBasecode(), priceList.get(i).getTranprice()};
                    } else {
                        keyList = new String[] {"WORK_TRANSACTION_SN", "CUSTKEY", "COSTCODE", "AMOUNT", "COMMIT"};
                        valueList = new Object[] {workTransactionSN, custkey, priceList.get(i).getBasecode(), priceList.get(i).getTranprice(), "FALSE"};
                    }
                    procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    rv = cmCommonService.saveProcedure(procedureDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)procedureDto.getResultCode();
                    resultMessage = (String)procedureDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if (!resultCode.equals("0")){
                        log.error("▶외부창고정산 -> 기타비용등록 항목별 저장 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }
                }
            }
        }
        //END..3.기타비용등록 항목별 저장
        
        
        //4.기타비용 등록 시 (총 공급가액/세액률)과 (세액 합산) 상이할 시 차이분 만큼 문서 내 가장 큰 Tax 값을 가진 Detail에 합산
        if (null != serialkeyList) {
            StExDCStorageResDto diffList = commonDao.selectOne(SERVICEID_PREFIX + "getTaxDiff", serialkeyList.get(0));
            
            if (diffList != null && !ObjectUtils.isEmpty(diffList)) {
                int expenseSN = diffList.getExpenseSn();
                double taxDiff = diffList.getTaxDiff();
                
                if (taxDiff > 0) {
                    // PKG 파라마터 세팅 - 공통(1/4)
                    procedureDto = new StExDCEctFeePopupResDto();
                    ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
                    procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
                    procedureDto.setAvc_COMMAND(PROCEDURE_ETCFEE_DF_NAME);
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
                    keyList = new String[] {"WORK_TRANSACTION_SN", "CUSTKEY", "TAX_DIFF", "EXPENSE_SN"};
                    valueList = new Object[] {workTransactionSN, custkey, taxDiff, expenseSN};
                   
                    procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    rv = cmCommonService.saveProcedure(procedureDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)procedureDto.getResultCode();
                    resultMessage = (String)procedureDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if (!resultCode.equals("0")){
                        log.error("▶외부창고정산 -> 기타비용등록 저장 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }  
                }
            }
        }
        //END..4.기타비용 등록 시 (총 공급가액/세액률)과 (세액 합산) 상이할 시 차이분 만큼 문서 내 가장 큰 Tax 값을 가진 Detail에 합산
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    } 
    
    /**
     * @description : 외부창고정산 보관료 비용 마감
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveExdcStorage(StExDCStoragePopupReqDto paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        int rv;
        
        String[] keyList;
        Object[] valueList; 
        
        StExDCStoragePopupReqDto reqDto = ModelMapperUtil.map(paramDto, userContext, StExDCStoragePopupReqDto.class);
        
        // 보관료 계산 실행 
        if ("CALC_STORAGEFEE".equals(reqDto.getProcType())) {
            // PKG 파라마터 세팅 - 공통(1/4)
            ProcedureParametersFactory.initParamDto(reqDto, reqDto, PAKAGE_NAME);
            reqDto.setAvc_DCCODE(reqDto.getFixdccode());
            reqDto.setAvc_COMMAND(PROCEDURE_CALC_STORAGEFEE_NAME);
            
            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
            keyList = new String[] {
                                    "YYYYMM"
                                    , "ORGANIZE"
                                    , "W1AMT"
                                    , "X3AMT"};
            valueList = new Object[] {
                                    reqDto.getYyyymm()
                                    , reqDto.getOrganize()
                                    , reqDto.getW1amt()
                                    , reqDto.getX3amt()};
           
            reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            
            rv = cmCommonService.saveProcedure(reqDto); 
            log.info("rv->{}",rv);
            
            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)reqDto.getResultCode();
            resultMessage = (String)reqDto.getResultMessage();
            
            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);
            
            // 프로시저 Exception 처리(4/4)
            if(!"0".equals(resultCode)){
                log.error("▶외부창고정산 -> 보관료 계산 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }  
        }
        
//        if(resultCode.equals("0")){
//        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
//        }
        
        // 보관료 비용 마감 실행
        if ("CLOSE_STORAGEFEE".equals(reqDto.getProcType())) {
            //1)보관료 비용 마감
            // PKG 파라마터 세팅 - 공통(1/4)
            ProcedureParametersFactory.initParamDto(reqDto, reqDto, PAKAGE_NAME);
            reqDto.setAvc_DCCODE(reqDto.getFixdccode());
            reqDto.setAvc_COMMAND(PROCEDURE_CLOSE_STORAGEFEE_NAME);
            
            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>     
            keyList = new String[] {
                                    "YYYYMM"
                                    , "ORGANIZE"
                                    , "W1AMT"
                                    , "X3AMT"};
            valueList = new Object[] {
                                    reqDto.getYyyymm()
                                    , reqDto.getOrganize()
                                    , reqDto.getW1amt()
                                    , reqDto.getX3amt()};
           
            reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            
            rv = cmCommonService.saveProcedure(reqDto); 
            log.info("rv->{}",rv);
            
            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)reqDto.getResultCode();
            resultMessage = (String)reqDto.getResultMessage();
            
            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);
            
            // 프로시저 Exception 처리(4/4)
            if(!"0".equals(resultCode)){
                log.error("▶외부창고정산 -> 보관료 비용 마감 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            } 
            //END..1)보관료 비용 마감
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 외부창고정산 수량 강제 조정
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.21    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveQtyEdit(StExDCStorageReqDto paramDto) {
        StExDCStorageReqDto reqDto = ModelMapperUtil.map(paramDto, userContext, StExDCStorageReqDto.class);
        
        List<StExDCStorageResDto> saveList = reqDto.getSaveList(); // 저장리스트
        
        log.info("▶saveList.size->{}",saveList);
        
        if (null != saveList) {
            for (int i = 0; i < saveList.size(); i++) {
                StExDCStorageResDto dto = saveList.get(i);
                StExDCStorageEntity entity = ModelMapperUtil.map(dto, userContext, StExDCStorageEntity.class);
                
                commonDao.insert(SERVICEID_PREFIX + "saveQtyEditLog", entity);
                commonDao.update(SERVICEID_PREFIX + "saveQtyEdit", entity);                
            }
        }
                
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 외부창고정산 기타비용등록 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.21   KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<StExDCEctFeePopupResDto> getJournalcodeList(StExDCEtcFeePopupReqDto stExDCEtcFeePopupReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getJournalcodeList", stExDCEtcFeePopupReqDto);
    }
    
    /**
     * @description : 외부창고정산 엑셀업로드 데이터 검증
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.22    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<StExDCStorageResDto> validateExcel(StExDCStorageReqDto reqDto) {
        List<StExDCStorageResDto> result = new ArrayList<StExDCStorageResDto>();
        
        if (null != reqDto) {
            for  (StExDCStorageResDto dto : reqDto.getSaveList()) {
                dto.setStorerkey(reqDto.getGStorerkey());
                dto.setDccode(reqDto.getDccode());
                dto.setGUserId(reqDto.getGUserId());
            }
            result = commonDao.selectList(SERVICEID_PREFIX + "getWithTempTable", reqDto.getSaveList());
        }
        return result;
    }
    
    /**
     * @description : 권한 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.21   KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<SysAuthorityUserResDto> getAuthority(StExDCStorageReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getAuthority", reqDto);
    }

}
