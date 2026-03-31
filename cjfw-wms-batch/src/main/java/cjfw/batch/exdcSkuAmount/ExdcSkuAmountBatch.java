package cjfw.batch.exdcSkuAmount;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.batch.exdcSkuAmount.dto.ExdcSkuAmountReqDto;
import cjfw.batch.exdcSkuAmount.dto.ExdcSkuAmountResDto;
import cjfw.batch.exdcSkuAmount.dto.ExdcSkuAmountEntity;
import cjfw.batch.exdcSkuAmount.soap.DT_MM0090_SCM;
import cjfw.batch.exdcSkuAmount.soap.DT_MM0090_SCMIF_ST_STOCKAMT;
import cjfw.batch.exdcSkuAmount.soap.DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET;
import cjfw.batch.exdcSkuAmount.soap.SI_MM0090_SCM_SOProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ExdcSkuAmountBatch {
    
    private static final String BATCH_JOB_NAME = "exdcSkuAmountJob"; 
    private final CommonDao commonDao;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private BatchJobListener batchJobListener;

    @Bean
    public Job exdcSkuAmountJob() {
        return new JobBuilder(BATCH_JOB_NAME, jobRepository)
                .listener(batchJobListener)
                .flow(execStep())
                .end()
                .build();
    }

    @Bean
    @StepScope
    public Step execStep() {
        return new StepBuilder("execStep", jobRepository)
                .tasklet(execTasklet(), transactionManager)
                .build();
    }

    public Tasklet execTasklet() {
        return (contribution, chunkContext) -> {
            // 배치수행로그
            BatchLogParamsDto batchLogDto = BatchLogParamsDto.builder()
                                            .jobExecutionId(Long.toString(chunkContext.getStepContext().getStepExecution().getJobExecution().getId()))
                                            .jobName(BATCH_JOB_NAME)
                                            .jobDiv("JAVA")
                                            .nodeLevel(0)
                                            .jobStatus("START")
                                            .command("")
                                            .lineNo("84")
                                            .resultCode("0")
                                            .resultMsg("")
                                            .build();
            
            try {
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
                
                saveSkuAmount();

            } catch (Exception e) {
                batchLogDto.setResultCode("-1");
                batchLogDto.setResultMsg(e.toString());
            } finally {
                batchLogDto.setJobStatus("END");
                batchLogDto.setLineNo("97");
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
            }

            return RepeatStatus.FINISHED;
        };
    }
    
    /**
     * @param dto 
     * @description : 외부창고 상품 조회 및 상품별 단가, 금액 조회 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.01 ParkJinWoo 생성
     */
    public String saveSkuAmount() {
        String searchKey = "";
        String resultKey = "";
        
        Integer serialkey = 0;
        Integer paramListCnt = 500;
        Integer paramRow = 0;
        Integer updateDsRow = 0;
        Integer findRow = 0;
        int rsltCnt = 0;
        
        ExdcSkuAmountReqDto reqDto = new ExdcSkuAmountReqDto();
        reqDto.setFixdccode("2170");
        reqDto.setSkuall("Y");
        reqDto.setSkutpl("Y");
        reqDto.setChkQty("0");
        reqDto.setChkQty1("0");
        
        List<ExdcSkuAmountResDto> searchResultList = new ArrayList<ExdcSkuAmountResDto>();
        searchResultList = commonDao.selectList("exdcSkuAmountService.getDatalist", reqDto);
        rsltCnt = searchResultList.size();
        
        SI_MM0090_SCM_SOProxy proxy = new SI_MM0090_SCM_SOProxy();

        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");

            DT_MM0090_SCM  reqData = new DT_MM0090_SCM();
            reqData.setXSYS("WMS");
            reqData.setXDATS(dateFormat.format(calendar.getTime()));
            reqData.setXTIMS(timeFormat.format(calendar.getTime()));
            reqData.setXROWS(String.valueOf('1'));

            DT_MM0090_SCMIF_ST_STOCKAMT param = new DT_MM0090_SCMIF_ST_STOCKAMT();
            DT_MM0090_SCMIF_ST_STOCKAMT[] paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
            if ( rsltCnt <= paramListCnt ) {
                paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ rsltCnt ];
            }
            else {
                paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
            }
            
            for (int DsRow = 0; DsRow < rsltCnt; DsRow++ ) {
                param = new DT_MM0090_SCMIF_ST_STOCKAMT();
                param.setPLANT(searchResultList.get(DsRow).getDcCode());
                param.setSTORAGELOC(searchResultList.get(DsRow).getStorageLoc());
                param.setSKU(searchResultList.get(DsRow).getSku());
                param.setUOM(searchResultList.get(DsRow).getUom());
                param.setQTY(searchResultList.get(DsRow).getSumQty().toString());
                param.setPOLINE(searchResultList.get(DsRow).getPoLine());
                param.setSTOCKQTY(searchResultList.get(DsRow).getStockQty().toString());
                
                if ("3".equals(searchResultList.get(DsRow).getSerialType())) {
                    param.setSERIALNO("");
                } else {
                    param.setSERIALNO(searchResultList.get(DsRow).getSerialNo());
                }
                
                param.setCONVSERIALNO(searchResultList.get(DsRow).getConvSerialNo());
                param.setSLIPDT("19000101");
                param.setSLIPNO(searchResultList.get(DsRow).getSerialKey());
                param.setSLIPLINE("00000"); 
                paramList[paramRow] = param;
                paramRow++;
                
                if ((paramRow.equals( paramListCnt )) || String.valueOf(DsRow + 1).equals( String.valueOf(rsltCnt))) {
                    reqData = new DT_MM0090_SCM();
                    reqData.setXSYS("WMS");
                    reqData.setXDATS(dateFormat.format(calendar.getTime()));
                    reqData.setXTIMS(timeFormat.format(calendar.getTime()));
                    reqData.setXROWS(String.valueOf(paramRow));    
                    reqData.setIF_ST_STOCKAMT(paramList);
                    
                    DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] response = proxy.si_mm0090_scm_so(reqData);

                    for (int i = 0; i < paramRow; i++ ) {
                        if ( updateDsRow >= rsltCnt) {
                            break;
                        }

                        searchKey =  searchResultList.get(updateDsRow).getDcCode()
                               + searchResultList.get(updateDsRow).getStorageLoc()
                               + searchResultList.get(updateDsRow).getSerialKey() 
                               + searchResultList.get(updateDsRow).getSku();

                        findRow = -1;
                        
                        for (int ii = 0; ii < paramRow; ii++) {
                            resultKey =  response[ii].getPLANT()
                                     + response[ii].getSTORAGELOC()
                                     + response[ii].getSLIPNO()
                                      + response[ii].getSKU();  
                            if ( searchKey.equals(resultKey) ) {
                                findRow = ii;
                                break;
                            }
                        }

                        if ( findRow >= 0 ) {
                            String stat = response[findRow].getXSTAT(); 
                            if("E".equals(stat)) {
                                searchResultList.get(updateDsRow).setStockamtmsg(response[findRow].getXMSGS());
                            } else {
                                searchResultList.get(updateDsRow).setStockamt(response[findRow].getSTOCKAMT());
                                searchResultList.get(updateDsRow).setStockPrice(response[findRow].getPURCHASEPRICE());
                                searchResultList.get(updateDsRow).setStockQty1(response[findRow].getSTOCKQTY().toString());
                            }
                        }

                        updateDsRow++;
                        if ( searchResultList.size() - DsRow > paramListCnt ) {
                            paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ paramListCnt ];
                        } else {
                            paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[ searchResultList.size() - DsRow];
                        }
                    }       
                    
                    paramRow=0;
                }//END..if ((paramRow.equals( paramListCnt )) || String.valueOf(DsRow + 1).equals( String.valueOf(rsltCnt)))
            }//END..for (int DsRow = 0; DsRow < rsltCnt; DsRow++ )
        } catch(Exception e) {
            log.error("재고금액 인터페이스 수신 에러 발생", e);
        }

        // 상품별 재고금액 기존 데이터 삭제 
        Map<String, Object> paramMap = new java.util.HashMap<>();
        commonDao.delete("exdcSkuAmountService.delete", paramMap);
        
        // 상품별 재고금액 데이터 저장
        serialkey = 0;
        for (ExdcSkuAmountResDto dto : searchResultList ) {
            try {
                ExdcSkuAmountEntity entityDto = new ExdcSkuAmountEntity();
                
                entityDto.setSerialkey(new BigDecimal(serialkey++));
                entityDto.setDccode(dto.getDcCode());
                entityDto.setOrganize(dto.getOrganize());
                entityDto.setSku(dto.getSku());
                entityDto.setStockgrade(dto.getStockGrade());
                entityDto.setQty(dto.getStockQty());

                String stockPrice = dto.getStockPrice();
                if (stockPrice != null && !stockPrice.trim().isEmpty()) {
                    stockPrice = stockPrice.trim().replace(",", "");
                    entityDto.setStockprice(new BigDecimal(stockPrice));
                } else {
                    entityDto.setStockprice(BigDecimal.ZERO);
                }
                
                String stockamt = dto.getStockamt();
                if (stockamt != null && !stockamt.trim().isEmpty()) {
                    stockamt = stockamt.trim().replace(",", "");
                    entityDto.setStockamt(new BigDecimal(stockamt));
                } else {
                    entityDto.setStockamt(BigDecimal.ZERO);
                }

                commonDao.insert("exdcSkuAmountService.insert", entityDto);
            } catch (Exception e) {
                log.error("재고금액 저장 에러 발생", e);
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

}