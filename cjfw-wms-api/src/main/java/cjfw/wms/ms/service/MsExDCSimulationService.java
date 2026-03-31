package cjfw.wms.ms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.entity.CmSyProcessTempSimulationEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.ms.dto.MsExDCSimulationReqDto;
import cjfw.wms.ms.dto.MsExDCSimulationResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.23 
 * @description : 외부창고정산 시뮬레이션 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.23    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsExDCSimulationService {

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "msExDCSimulationService."; 
    private transient static final String PAKAGE_NAME = "SPMS_EXDCSIMULATION"; 
    private transient static final String TEMPTABLETYPE = "Simulation";
    private transient static final String PROCESSTYPE_ORGANZIE = "SPMS_SIMULATION_ORGANIZE"; 
    private transient static final String PROCESSTYPE_SKU = "SPMS_SIMULATION_SKU";
    
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private final UserContext userContext;

    
    /**
     * @description : 외부창고정산 시뮬레이션 창고비교 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.23   KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<MsExDCSimulationResDto> getMasterList(MsExDCSimulationReqDto msExDCSimulationReqDto) {
    	return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", msExDCSimulationReqDto);
    }
	
    /**
     * @description : 외부창고정산 시뮬레이션 상품 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.23  KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<MsExDCSimulationResDto> getSkuList(MsExDCSimulationReqDto msExDCSimulationReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getSkuList", msExDCSimulationReqDto);
    }
	
    /**
     * @description : 외부창고정산 시뮬레이션 창고비교
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.23    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<MsExDCSimulationResDto> saveMasterList(MsExDCSimulationReqDto paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        MsExDCSimulationReqDto reqDto = ModelMapperUtil.map(paramDto, userContext, MsExDCSimulationReqDto.class);
        List<MsExDCSimulationResDto> result = null;
        
        List<MsExDCSimulationResDto> saveList = reqDto.getSaveList();
        
        log.info(reqDto.getStockmonth());
        log.info(reqDto.getBaseOrganize());
        log.info(reqDto.getCfOrganize());
        
        /*1. 임시테이블 삭제 */
//        CmSyProcessTempSimulationEntity entity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempSimulationEntity.class);
//        entity.setProcesstype(PROCESSTYPE_ORGANZIE);        
//        commonDao.delete(SERVICEID_PREFIX + "deleteSyProcessTempSimulation", entity);
        
        /*2. 패키지 프로시저 실행 - 기준 창고 */
        MsExDCSimulationReqDto procedureDto = new MsExDCSimulationReqDto();
       
        ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
        procedureDto.setAvc_DCCODE("2170");
        
        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
        String[] keyList = {
                             "PROCEDURE"
                            ,"PROCESSTYPE"
                            ,"PROCESSCREATOR"
                            ,"SPID"
                            ,"YYYYMM"
                            ,"BASEORGANIZE"
                            ,"PROCORGANIZE"
                            ,"BASE_YN"
                            };
        Object[] valueList = { 
                             PAKAGE_NAME   
                            ,PROCESSTYPE_ORGANZIE
                            ,reqDto.getGUserId()
                            ,reqDto.getGSpid()
                            ,reqDto.getStockmonth()
                            ,reqDto.getBaseOrganize()
                            ,reqDto.getBaseOrganize()
                            ,"1"
                            };
        procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));

        int rv = cmCommonService.saveProcedure(procedureDto); 
        log.info("rv->{}",rv);
        
        // 프로시저 OUT Parameter(3/4)
        resultCode    = (String)procedureDto.getResultCode();
        resultMessage = (String)procedureDto.getResultMessage();
        
        log.info("resultCode->{}",resultCode);
        log.info("resultMessage->{}",resultMessage);
        
        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            log.error("▶외부창고정산 시뮬레이션 -> 창고비교 시뮬레이션 오류 발생 ");
            throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"외부창고정산 창고비교 시뮬레이션"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
        }        
        
        /*3. 패키지 프로시저 실행 - 비교 창고 */
        procedureDto = new MsExDCSimulationReqDto();
       
        ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
        procedureDto.setAvc_DCCODE("2170");
        
        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
        keyList = new String[] {
                             "PROCEDURE"
                            ,"PROCESSTYPE"
                            ,"PROCESSCREATOR"
                            ,"SPID"
                            ,"YYYYMM"
                            ,"BASEORGANIZE"
                            ,"PROCORGANIZE"
                            ,"BASE_YN"
                            };
        valueList = new Object[] { 
                             PAKAGE_NAME   
                            ,PROCESSTYPE_ORGANZIE
                            ,reqDto.getGUserId()
                            ,reqDto.getGSpid()
                            ,reqDto.getStockmonth()
                            ,reqDto.getBaseOrganize()
                            ,reqDto.getCfOrganize()
                            ,"2"
                            };
        
        procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));

        rv = cmCommonService.saveProcedure(procedureDto); 
        log.info("rv->{}",rv);
        
        // 프로시저 OUT Parameter(3/4)
        resultCode    = (String)procedureDto.getResultCode();
        resultMessage = (String)procedureDto.getResultMessage();
        
        log.info("resultCode->{}",resultCode);
        log.info("resultMessage->{}",resultMessage);
        
        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            log.error("▶외부창고정산 시뮬레이션 -> 창고비교 시뮬레이션 오류 발생 ");
            throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"외부창고정산 창고비교 시뮬레이션"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
        }  
        
        /*4. 결과 테이블 조회*/
        reqDto.setProcesstype(PROCESSTYPE_ORGANZIE);
        result = commonDao.selectList(SERVICEID_PREFIX + "getOrgnanizeSimulationList", reqDto);
        
        return result;
    }
    
    /**
     * @description : 외부창고정산 시뮬레이션 상품
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.23    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<MsExDCSimulationResDto> saveSkuList(MsExDCSimulationReqDto paramDto) {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        MsExDCSimulationReqDto reqDto = ModelMapperUtil.map(paramDto, userContext, MsExDCSimulationReqDto.class);
        List<MsExDCSimulationResDto> result = null;
        
        List<MsExDCSimulationResDto> saveList = reqDto.getSaveList();
        
        log.info("▶saveList.size->{}",saveList);
        
        if (null != saveList) {
            
            log.info(reqDto.getStockmonth());
            
            /*1. 임시테이블 삭제 */
            CmSyProcessTempSimulationEntity deleteEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempSimulationEntity.class);
            deleteEntity.setProcesstype(PROCESSTYPE_SKU);        
            commonDao.delete(SERVICEID_PREFIX + "deleteSyProcessTempSimulation", deleteEntity);
            
            /*2. 기준 값으로 임시테이블에 200개씩 저장 */
            int chunkSize = 200;
            List<CmSyProcessTempSimulationEntity> insertList = new ArrayList<CmSyProcessTempSimulationEntity>();
            
            for (Integer i = 0; i < saveList.size(); i++) {
                MsExDCSimulationResDto dto = saveList.get(i);
                log.info(dto.toString());

                CmSyProcessTempSimulationEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempSimulationEntity.class);
                
                entity.setProcesstype(PROCESSTYPE_SKU);
                entity.setTemptabletype(TEMPTABLETYPE);
                entity.setStorerkey(reqDto.getGStorerkey());
                entity.setSpid(reqDto.getGSpid());
                entity.setStockmonth(reqDto.getStockmonth());   
                entity.setSku(dto.getSku());
                entity.setGrQty(dto.getGrQty());
                entity.setGiQty(dto.getGiQty());
                entity.setBaseYn("1");
                entity.setPriceUom(dto.getPriceUom());
                entity.setBaseGrprice(dto.getBaseGrprice());
                entity.setBaseGiprice(dto.getBaseGiprice());
                entity.setBaseStorageprice(dto.getBaseStorageprice());
                entity.setBasePltprice(dto.getBasePltprice());
                entity.setBaseWghprice(dto.getBaseWghprice());
                entity.setBaseWorkprice(dto.getBaseWorkprice());      
                
                insertList.add(entity);
          
                // 200개마다 혹은 마지막 루프일 때 insert
                if (insertList.size() == chunkSize || i == saveList.size() -1) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempSimulation", insertList);
                    insertList.clear(); // 다음 배치 준비
                }
            }
            
            /*3. 패키지 프로시저 실행*/
            MsExDCSimulationReqDto procedureDto = new MsExDCSimulationReqDto();
            
            ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
            procedureDto.setAvc_DCCODE("2170");
            
            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
            String[] keyList = {
                                 "PROCEDURE"
                                ,"PROCESSTYPE"
                                ,"PROCESSCREATOR"
                                ,"SPID"
                                ,"YYYYMM"
                                ,"BASE_YN"
                                };
            Object[] valueList = { 
                                 PAKAGE_NAME   
                                ,PROCESSTYPE_SKU
                                ,reqDto.getGUserId()
                                ,reqDto.getGSpid()
                                ,reqDto.getStockmonth()
                                ,"1"
                                };
            procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));

            int rv = cmCommonService.saveProcedure(procedureDto); 
            log.info("rv->{}",rv);
            
            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)procedureDto.getResultCode();
            resultMessage = (String)procedureDto.getResultMessage();
            
            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);
            
            // 프로시저 Exception 처리(4/4)
            if(!"0".equals(resultCode)){
                log.error("▶외부창고정산 시뮬레이션 -> 상품 시뮬레이션 오류 발생 ");
                throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"외부창고정산 상품 시뮬레이션"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
            }
            
            
            /*3. 비교 값으로 임시테이블에 200개씩 저장 */
            insertList = new ArrayList<CmSyProcessTempSimulationEntity>();
            
            for (int i = 0; i < saveList.size(); i++) {
                MsExDCSimulationResDto dto = saveList.get(i);
                log.info(dto.toString());

                CmSyProcessTempSimulationEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempSimulationEntity.class);

                entity.setProcesstype(PROCESSTYPE_SKU);
                entity.setTemptabletype(TEMPTABLETYPE);
                entity.setStorerkey(reqDto.getGStorerkey());
                entity.setSpid(reqDto.getGSpid());
                entity.setStockmonth(reqDto.getStockmonth());
                entity.setSku(dto.getSku());
                entity.setGrQty(dto.getGrQty());
                entity.setGiQty(dto.getGiQty());
                entity.setBaseYn("2");
                entity.setPriceUom(dto.getPriceUom());
                entity.setBaseGrprice(dto.getCfGrprice());
                entity.setBaseGiprice(dto.getCfGiprice());
                entity.setBaseStorageprice(dto.getCfStorageprice());
                entity.setBasePltprice(dto.getCfPltprice());
                entity.setBaseWghprice(dto.getCfWghprice());
                entity.setBaseWorkprice(dto.getCfWorkprice()); 
                
                insertList.add(entity);
          
                // 200개마다 혹은 마지막 루프일 때 insert
                if (insertList.size() == chunkSize || i == saveList.size() -1) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempSimulation", insertList);
                    insertList.clear(); // 다음 배치 준비
                }
            }
            
            /*3. 패키지 프로시저 실행*/
            procedureDto = new MsExDCSimulationReqDto();
            
            ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
            procedureDto.setAvc_DCCODE("2170");
            
            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
            keyList = new String[] {
                                 "PROCEDURE"
                                ,"PROCESSTYPE"
                                ,"PROCESSCREATOR"
                                ,"SPID"
                                ,"YYYYMM"
                                ,"BASE_YN"
                                };
            valueList = new Object[] { 
                                 PAKAGE_NAME   
                                ,PROCESSTYPE_SKU
                                ,reqDto.getGUserId()
                                ,reqDto.getGSpid()
                                ,reqDto.getStockmonth()
                                ,"2"
                                };
            procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));

            rv = cmCommonService.saveProcedure(procedureDto); 
            log.info("rv->{}",rv);
            
            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)procedureDto.getResultCode();
            resultMessage = (String)procedureDto.getResultMessage();
            
            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);
            
            /* 프로시저 Exception 처리(4/4) */
            if(!"0".equals(resultCode)){
                log.error("▶외부창고정산 시뮬레이션 -> 상품 시뮬레이션 오류 발생 ");
                throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"외부창고정산 상품 시뮬레이션"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
            }
            
            
            /*4. 결과 테이블 조회*/
            reqDto.setProcesstype(PROCESSTYPE_SKU);
            result = commonDao.selectList(SERVICEID_PREFIX + "getSkuSimulationList", reqDto);
        }
        
        return result;
    }   
    
}
