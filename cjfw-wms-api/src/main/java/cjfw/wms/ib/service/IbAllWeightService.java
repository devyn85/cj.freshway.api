package cjfw.wms.ib.service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.drm.DrmUtil;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.ib.dto.IbAllWeightReqDto;
import cjfw.wms.ib.dto.IbAllWeightResDto;
import cjfw.wms.ib.dto.IbStoWeightResDto;
import cjfw.wms.tm.service.TmAllocationCheckService;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCMIF_ST_STOCKAMT;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET;
import cjfw.wms.webservice.elecAmt.SI_MM0090_SCM_SOProxy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jxls.builder.JxlsOutputFile;
import org.jxls.common.Context;
import org.jxls.common.ContextImpl;
import org.jxls.transform.poi.JxlsPoiTemplateFillerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.11.12
 * @description :센터별물동량 정산  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IbAllWeightService {
    private final UserContext userContext;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPIB_RECEIPT";
    private transient static final String TEMPTABLETYPE = "IB";
    private transient static final String PROCESSTYPE = "";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(IbAllWeightService.class.getSimpleName()) + ".";
    private final TmAllocationCheckService tmAllocationCheckService;

    /**
     * @description : 센터별물동량 정산 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 센터별물동량 정산 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", reqDto);
    }

    /**
     * @description : 센터별물동량 정산 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    @Transactional
    public String saveMasterList2(IbAllWeightReqDto paramDto) throws Exception {
        int saveCnt = 0;
        for (IbAllWeightResDto dto : paramDto.getSaveList()) {
            dto.setStorerkey(paramDto.getGStorerkey());
            dto.setEditwho(paramDto.getGUserId());
            if ("D".equals(dto.getRowStatus())) {
                commonDao.update(SERVICEID_PREFIX + "deleteMaster2", dto);
                saveCnt++;
            }
            if ("U".equals(dto.getRowStatus())) {
                commonDao.update(SERVICEID_PREFIX + "updateMaster2", dto);
                saveCnt++;
            }
            if ("I".equals(dto.getRowStatus())) {
                commonDao.update(SERVICEID_PREFIX + "insertMaster2", dto);
                saveCnt++;
            }
        }
        if (saveCnt < 1) {
            return CanalFrameConstants.MSG_COM_ERR_CODE;
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 센터별물동량 정산 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    @Transactional
    public String copyMasterList2(IbAllWeightReqDto paramDto) throws Exception {
        commonDao.delete(SERVICEID_PREFIX + "deleteMasterList2", paramDto);
        commonDao.insert(SERVICEID_PREFIX + "copyMasterList2", paramDto);
        return CanalFrameConstants.MSG_COM_DEL_CODE;
    }

    /**
     * @description : 센터별물동량 정산 엑셀
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public void getExcel(IbAllWeightReqDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        dto.setSlipdtFrom(dto.getYyyymm() + "01");
        dto.setSlipdtTo(dto.getYyyymm() + "31");
        List<IbAllWeightResDto> selectList = commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", dto);
        //템플릿
        String templatePath = ContextUtil.getProperty("cf.upload.dir.excelTemplate") + "/account_template.xlsx";
        try (InputStream is = new FileInputStream(templatePath)) {
            JxlsPoiTemplateFillerBuilder builder = JxlsPoiTemplateFillerBuilder.newInstance().withTemplate(is);
            String downloadFileName = "하역비 정산서.xlsx";
            String downloadFilePath = "";
            Context context = new ContextImpl();

            /*1. 요약시트 start*/
            Map<String, Object> yoyakTab = new HashMap<>();
            yoyakTab.put("yyyymm", dto.getYyyymm());
            yoyakTab.put("yyyy", dto.getYyyy());
            yoyakTab.put("yy", dto.getYy());
            yoyakTab.put("mm", dto.getMm());
            yoyakTab.put("custname", dto.getCustname());
            yoyakTab.put("dcname", dto.getFixdcname());
            yoyakTab.put("dccode", dto.getFixdccode());

            context.putVar("yoyakTab", yoyakTab);
            /*1. 요약시트 end*/

            /*2. 전표시트 start*/
            Map<String, Object> jeonpyoTab = new HashMap<>();

            context.putVar("jeonpyoTab", jeonpyoTab);
            /*2. 전표시트 end*/

            /*3. 물동급시트 start*/
            Map<String, Object> muldongTab = new HashMap<>();

            context.putVar("muldongTab", muldongTab);
            /*3. 물동급시트 end*/

            /*4. 고정급시트 start*/
            Map<String, Object> gojeongTab = new HashMap<>();
            //OP
            List<IbAllWeightResDto> op = selectList.stream()
                .filter(e -> "30".equals(e.getPriceCl()) && "F".equals(e.getLclCd())).toList();
            //장비
            List<IbAllWeightResDto> jangbi = selectList.stream()
                .filter(e -> "30".equals(e.getPriceCl()) && "G".equals(e.getLclCd())).toList();
            //고정급(입고/총량) 입고/내부총량
            List<IbAllWeightResDto> gojeong = selectList.stream()
                .filter(e -> "30".equals(e.getPriceCl()) && "E".equals(e.getLclCd()) && "E001".equals(e.getMclCd())).toList();
            //고정급(입고/총량) 외부총량
            List<IbAllWeightResDto> gojeong2 = selectList.stream()
                .filter(e -> "30".equals(e.getPriceCl()) && "E".equals(e.getLclCd()) && "E002".equals(e.getMclCd())).toList();
            //추가비용
            Long gita = selectList.stream()
                .filter(e -> "60".equals(e.getPriceCl()) && "J".equals(e.getLclCd()))
                .mapToLong(e -> Long.parseLong(StringUtils.isEmpty(e.getPrice()) ? "0" : e.getPrice()))
                .sum();
            //입고+내부총량
            gojeong.forEach(e -> e.setDcname(e.getDcname() + "(입고+내부총량)"));
            //총량 상세
            List<IbStoWeightResDto> gojeongStoList = commonDao.selectList(SERVICEID_PREFIX + "getGojeongStoList", dto);
            gojeongStoList.forEach(e -> e.setPrice(CollectionUtils.isEmpty(gojeong2) ? "0" : gojeong2.get(0).getPrice()));

            gojeongTab.put("op", op);
            gojeongTab.put("jangbi", jangbi);
            gojeongTab.put("gojeong", gojeong);
            gojeongTab.put("gojeong2", gojeongStoList);
            gojeongTab.put("gita", gita);
            context.putVar("gojeongTab", gojeongTab);
            /*4. 고정급시트 end*/

            /*5. SLA 병합용 시트 start*/
            Map<String, Object> slaMergeTab = new HashMap<>();

            context.putVar("slaMergeTab", slaMergeTab);
            /*5. SLA 병합용 시트 end*/

            /*6. SLA 센터1개용 시트 start*/
            Map<String, Object> slaOneTab = new HashMap<>();

            context.putVar("slaOneTab", slaOneTab);
            /*6. SLA 센터1개용 시트 end*/

            /*7. 퀵시트 start*/
            Map<String, Object> quickTab = new HashMap<>();
//            List<IbStoWeightResDto> quickList = commonDao.selectList(SERVICEID_PREFIX + "getGammoList", dto);
//            fetchSapPrice(quickList); //sap 단가 조회
//            quickTab.put("list", quickList);
            context.putVar("quickTab", quickTab);
            /*7. 퀵시트 end*/

            /*8. 감모시트 start*/
            Map<String, Object> gammoTab = new HashMap<>();
            List<IbStoWeightResDto> gammoList = commonDao.selectList(SERVICEID_PREFIX + "getGammoList", dto);
            fetchSapPrice(gammoList); //sap 단가 조회
            gammoTab.put("list", gammoList);
            context.putVar("gammoTab", gammoTab);
            /*8. 감모시트 end*/

            /*9. 폐기시트 start*/
            Map<String, Object> pyegiTab = new HashMap<>();
            List<IbStoWeightResDto> pyegiList = commonDao.selectList(SERVICEID_PREFIX + "getPyegiList", dto);
            fetchSapPrice(pyegiList); //sap 단가 조회
            pyegiTab.put("list", pyegiList);
            context.putVar("pyegiTab", pyegiTab);
            /*9. 폐기시트 end*/

            //다운로드
            log.info("========== ExcelDownload 1 SETP START==========");
            String uploadFullPath = ContextUtil.getProperty("cf.excel.uploadPath") + "/noDRM_" + downloadFileName;
            log.info("### uploadFullPath:" + uploadFullPath);

            log.info("context ▶ {}", context.toMap());
//			builder.buildAndFill(context.toMap(), new JxlsOutputFile(new File(uploadFullPath)));
            builder.buildAndFill(context.toMap(), new JxlsOutputFile(new File("C:/CJFW_WMS/noDRM_" + downloadFileName)));
            log.info("========== ExcelDownload 1 STEP END ==========");

            log.info("========== ExcelDownload 2 SETP START==========");
            if (!"local".equals(System.getProperty("spring.profiles.active", "local")) && !"default".equals(System.getProperty("spring.profiles.active", "local"))) {
                log.info("########## DRM DRMEncoding ##########");
                // DRM 적용
                String userID = StringUtils.isEmpty(userContext.getUserId()) ? "" : userContext.getUserId();
                String userName = StringUtils.isEmpty(userContext.getUserNm()) ? "" : userContext.getUserNm();
                String deptCd = "";
                String deptNm = "";
                String tempPath = ContextUtil.getProperty("cf.excel.uploadPath");
                // DRM
                String destFilePath = tempPath + "/" + downloadFileName;

                boolean isSuccess = DrmUtil.packagingDRM(uploadFullPath, destFilePath, userID, userName, deptCd, deptNm);
                if (!isSuccess) {
                    throw new UserHandleException("엑셀 Export 중 오류가 발생하였습니다.");
                }
                File srcFile = new File(uploadFullPath);
                if (srcFile.exists()) {
                    Path path = Paths.get(srcFile.getPath());
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        log.error("LargeExcelUtil.Files.delete().IOException : ", e);
                    }
                }
                downloadFilePath = destFilePath;
            } else {
                downloadFilePath = uploadFullPath;
            }

            log.info("========== ExcelDownload 2 STEP END ==========");

            log.info("========== ExcelDownload 3 SETP START==========");
            // 다운로드 진행.
            download(request, response, downloadFilePath, downloadFileName);
            log.info("========== ExcelDownload 3 STEP END ==========");
        }
    }

    private static void download(HttpServletRequest request, HttpServletResponse response, String sourcePath, String filename) {
        String localSourcePath = sourcePath;
        String encodedFileName = filename;

        File file = null;
        try {
            request.setCharacterEncoding(CanalFrameConstants.DEFAULT_CHARACTERSET);

            localSourcePath = StringUtils.replace(localSourcePath, "..", "");
            //업로드 경로에 파일이 위치한 경우
            file = new File(localSourcePath);
            if (!(file.exists())) {
                log.info("File not found.");
            }

            log.info(" ### filename : " + encodedFileName);
            String header = request.getHeader("User-Agent");
            if (header.contains("MSIE") || header.contains("Trident") || header.contains("Chrome")) {
                encodedFileName = URLEncoder.encode(filename, CanalFrameConstants.DEFAULT_CHARACTERSET).replace("\\+", "%20");
            }
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
            response.setHeader("Content-Type", "application/x-download");
            response.setHeader("Content-Transfer-Encoding", "binary;");
            response.setHeader("Pragma", "no-cache;");
            response.setHeader("Expires", "-1;");
        } catch (Exception e2) {
            log.error("LargeExcelUtil.download.Exception : ", e2);
        }

        try (
            BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
        ) {
            byte[] readByte = new byte[8192];

            int bytes = 0;
            while ((bytes = fin.read(readByte)) != -1) {
                outs.write(readByte, 0, bytes);
            }
            outs.flush();
        } catch (Exception ex) {
            log.error("LargeExcelUtil.download.FileOutPutException : ", ex);
        } finally {
            try {
                if (null != file && file.exists()) {
                    Path path = Paths.get(file.getPath());
                    Files.delete(path);
                }
            } catch (Exception e) {
                log.error("LargeExcelUtil.download.FilesdeleteException : ", e);
            }
        }
    }

    /*
        T Dto 필수 필드 목록
        * String dccode
        * String sku
        * String uom
        * BigDecimal purchaseprice
    */
    public <T> void fetchSapPrice(List<T> list) {
        //정직원 EMPTYPE2 01, 02, 03 이아니면 리턴
		if(!Arrays.asList("01", "02", "03").contains(userContext.getEmptype())) {
			return;
		}

        try {
            Map<String, BigDecimal> responsePurchasepriceMap = new LinkedHashMap<>();
            String resultKey = "";

            int paramListCnt = 500;
            int paramRow = 0;

            int rsltCnt = 0;
            //중복제외리스트
            Map<String, T> distinctMap = new LinkedHashMap<>();
            for (T item : list) {
                Method getDccode = item.getClass().getMethod("getDccode");
                Method getSku = item.getClass().getMethod("getSku");
                Method getUom = item.getClass().getMethod("getUom");
                String k = String.valueOf(getDccode.invoke(item))
                    + String.valueOf(getSku.invoke(item))
                    + String.valueOf(getUom.invoke(item));
                distinctMap.putIfAbsent(k, item);
            }
            List<T> distinctList = new ArrayList<>(distinctMap.values());

            rsltCnt = distinctList.size();

            SI_MM0090_SCM_SOProxy proxy = new SI_MM0090_SCM_SOProxy();

            try {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");

                DT_MM0090_SCMIF_ST_STOCKAMT param = new DT_MM0090_SCMIF_ST_STOCKAMT();
                DT_MM0090_SCMIF_ST_STOCKAMT[] paramList = null;
                if (rsltCnt <= paramListCnt) {
                    paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[rsltCnt];
                } else {
                    paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[paramListCnt];
                }

                for (int DsRow = 0; DsRow < rsltCnt; DsRow++) {
                    param = new DT_MM0090_SCMIF_ST_STOCKAMT();

                    Method getDccode = distinctList.get(DsRow).getClass().getMethod("getDccode");
                    Method getSku = distinctList.get(DsRow).getClass().getMethod("getSku");
                    Method getUom = distinctList.get(DsRow).getClass().getMethod("getUom");

                    param.setPLANT(String.valueOf(getDccode.invoke(distinctList.get(DsRow))));
                    param.setSKU(String.valueOf(getSku.invoke(distinctList.get(DsRow))));
                    param.setUOM(String.valueOf(getUom.invoke(distinctList.get(DsRow))));

                    paramList[paramRow] = param;
                    paramRow++;

                    //500건씩 or 마지막row일떄 통신진행
                    if (paramRow != paramListCnt && rsltCnt != (DsRow + 1)) {
                        continue;
                    }

                    DT_MM0090_SCM reqData = new DT_MM0090_SCM();
                    reqData.setXSYS("WMS");
                    reqData.setXDATS(dateFormat.format(calendar.getTime()));
                    reqData.setXTIMS(timeFormat.format(calendar.getTime()));
                    reqData.setXROWS(String.valueOf(paramRow));

                    reqData.setIF_ST_STOCKAMT(paramList);

                    DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] response = proxy.si_mm0090_scm_so(reqData);

                    //리스폰스 담기
                    for (int i = 0; i < paramRow; i++) {
                        resultKey = response[i].getPLANT()
                            + response[i].getSKU()
                            + response[i].getUOM();
                        responsePurchasepriceMap.putIfAbsent(resultKey, BigDecimal.valueOf(Double.parseDouble(response[i].getPURCHASEPRICE())));
                    }

                    if (rsltCnt - DsRow > paramListCnt) {
                        paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[paramListCnt];
                    } else {
                        paramList = new DT_MM0090_SCMIF_ST_STOCKAMT[rsltCnt - DsRow];
                    }

                    paramRow = 0;
                }
            } catch (Exception e) {
                log.error("재고금액 처리중 에러발생", e);
            }

            //원본 리스트 stockamt 업데이트
            for (T item : list) {
                Method getDccode = item.getClass().getMethod("getDccode");
                Method getSku = item.getClass().getMethod("getSku");
                Method getUom = item.getClass().getMethod("getUom");
                Method setPurchaseprice = item.getClass().getMethod("setPurchaseprice", BigDecimal.class);
                String k = String.valueOf(getDccode.invoke(item))
                    + String.valueOf(getSku.invoke(item))
                    + String.valueOf(getUom.invoke(item));
                setPurchaseprice.invoke(item, Objects.isNull(responsePurchasepriceMap.get(k)) ? BigDecimal.ZERO : responsePurchasepriceMap.get(k));
            }
        } catch (Exception e) {
            //NoSuchMethodException, InvocationTargetException, IllegalAccessException
            e.printStackTrace();
        }
    }
}
