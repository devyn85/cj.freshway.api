package cjfw.wms.kp.service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.dto.CmSendEmailReqDto;
import cjfw.wms.cm.entity.CmSyProcessTempAjEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.service.CmEmailService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.kp.dto.*;
import cjfw.wms.kp.entity.KpKxCloseEntity;
import cjfw.wms.kp.entity.KpKxCloseRPEntity;
import cjfw.wms.webservice.utility.CJServiceUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.09.24
 * @description : Admin > 시스템정보 > KX마감진행현황 정보를 조회 및 저장 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.24 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpKxCloseService {

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "kpKxCloseService.";

    private final CommonDao commonDao;

    private final UserContext userContext;

    /**
     * 공통.service
     */
    private final CmCommonService cmCommonService;

    /**
     * 공통.이메일.service
     */
    private final CmEmailService cmEmailService;


    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX2 = "wdInvoiceService.";
    /**
     * 프로시져명 - 패키지호출
     */
    private transient String PAKAGE_NAME = "SPOM_ORDERMODIFY_KX";
    /**
     * 프로세스타임
     */
    private transient String PROCESSTYPE;

    /**
     * 프로세스타임 - ORDERCLEAR
     */
    private transient String PROCESSTYPE_ORDERCLEAR = "KX_ORDERCLEAR";
    /**
     * 임시테이블 타입
     */
    private transient String TEMPTABLETYPE = "AJ";

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ApiResponse8 {
        private List<DataContainer8> data;
    }

    @Data
    private static class DataContainer8 {
        @JsonProperty("DATA_CNT")
        private int dataCnt;
        @JsonProperty("DATA_LIST")
        private List<KpKxCloseDocPopupResDto> dataList;
    }

    @Data
    private static class DataContainerT11 {
        @JsonProperty("DATA_CNT")
        private int dataCnt;
        @JsonProperty("DATA_LIST")
        private List<KpKxCloseResT11Dto> dataList;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ApiResponseT11 {
        private List<DataContainerT11> data;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ApiResponseT13 {
        private List<DataContainerT13> data;
    }

    @Data
    private static class DataContainerT13 {
        @JsonProperty("DATA_CNT")
        private int dataCnt;
        @JsonProperty("DATA_LIST")
        private List<KpKxCloseResT13Dto> dataList;
    }

    /**
     * @description : 마감현황 - 문서 기준 처리현황 검색 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.24 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT1Dto> getKxCloseList(KpKxCloseReqDto kpKxCloseReqDto) {
        List<KpKxCloseResT1Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getKxCloseList", kpKxCloseReqDto);

        return list;
    }

    /**
     * @description : 마감현황 - I/F 기준 처리현황 검색 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.24 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT1Dto> getKxCloseListIF(KpKxCloseReqDto kpKxCloseReqDto) {
        List<KpKxCloseResT1Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getKxCloseListIF", kpKxCloseReqDto);

        return list;
    }

    /**
     * @description : 마감현황 - 문서 기준 처리현황 상세 조회 (문서 기준 미처리 내역)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.24 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseDtlResT1Dto> getKxCloseDetailList(KpKxCloseReqDto kpKxCloseReqDto) {

        List<KpKxCloseDtlResT1Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxCloseDetailList", kpKxCloseReqDto);

        return res;
    }

    /**
     * @description : 마감현황 - I/F 기준 처리현황 상세 조회 (I/F 기준 미처리 내역)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.24 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseDtlResT1Dto> getKxCloseDetailListIF(KpKxCloseReqDto kpKxCloseReqDto) {

        List<KpKxCloseDtlResT1Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxCloseDetailListIF", kpKxCloseReqDto);

        return res;
    }

    /**
     * @description : KX검증 검색 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.26 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT2Dto> getKxCheckList(KpKxCloseReqDto kpKxCloseReqDto) {

        List<KpKxCloseResT2Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxCheckList", kpKxCloseReqDto);

        return res;
    }

    /**
     * @description : KX검증 저장 - IF상태 변경
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.23 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public String saveKxCheck(KpKxCloseReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        KpKxCloseReqDto reqDto = ModelMapperUtil.map(paramDto, KpKxCloseReqDto.class);

        List<KpKxCloseResT2Dto> saveList = reqDto.getSaveList(); // 저장리스트

        reqDto.setAvc_COMMAND("DATACHECK_REPROC");
        reqDto.setProcesstype("DATACHECK_REPROC"); // 프로세스타입

        PROCESSTYPE = reqDto.getProcesstype();     // 프로세스타입

        /*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp" + TEMPTABLETYPE, reqDto);

        // 임시테이블 Insert
        // 200개씩 끊어서 Insert 처리 200개씩 끊어서 Insert 처리
        int chunkSize = 200;
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            KpKxCloseResT2Dto dto = saveList.get(i);

            CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
            entity.setPackagename(PAKAGE_NAME); // 패키지명
            entity.setProcesstype(PROCESSTYPE); // 프로세스타입

            /* DTO 필드(카멜) → Entity 필드(카멜) 1 : 1 순서 매핑 */
            String columnsDto = "DOCNO|DOCLINE|SERIALKEY|IF_FLAG|CONFIRMQTY";
            String columnsEntity = "DOCNO|DOCLINE|OTHER01|OTHER02|CONFIRMQTY";

            entity = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);

            // UI.params
            // START.필수입력 check - 그리드 변수 등
            // END.필수입력 check

            insertList.add(entity);

            // 200개마다 혹은 마지막 루프일 때 insert(3/3)
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertTempAJ", insertList);
                insertList.clear();
            }
        }
        /*END.Temp Table Insert*/

        /*START.PAKAGE 호출*/
        // PKG 파라마터 세팅 - 공통(1/4)
        KpKxCloseReqDto dto = new KpKxCloseReqDto();
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PROCEDURE", "PROCESSTYPE", "PROCESSCREATOR", "SPID"};
        Object[] valueList = {PAKAGE_NAME, PROCESSTYPE, reqDto.getGUserId(), reqDto.getGSpid()};

        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(dto); // 패키지 호출
        log.info("rv->{}", rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl(dto.getResultMessage());

        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            log.error("▶출고진행현황 - 상품제외처리 시 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"출고진행현황-상품제외"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 문서내역 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.05 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT4Dto> getKxDocList(KpKxCloseReqDto kpKxCloseReqDto) {

        List<KpKxCloseResT4Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxDocList", kpKxCloseReqDto);

        return res;
    }

    /**
     * @description : 문서내역 상세 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.05 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT4Dto> getKxDocDetailList(KpKxCloseReqDto kpKxCloseReqDto) {

        List<KpKxCloseResT4Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxDocDetailList", kpKxCloseReqDto);

        return res;
    }

    /**
     *
     * @description : 문서내역 - 삭제오더초기화 저장
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.05        JiSooKim (jskim14@cj.net)       생성
     */
    @CacheEvict(cacheNames = "userCode", allEntries = true)
    public String saveDelOrderReset(KpKxCloseReqDto cmCodeReqDto) {
        if (null != cmCodeReqDto.getReqDtoList()) {
            for (KpKxCloseReqDto codeMst : cmCodeReqDto.getReqDtoList()) {
                KpKxCloseEntity cmCodeEntity = ModelMapperUtil.map(codeMst, userContext, KpKxCloseEntity.class);
                commonDao.update(SERVICEID_PREFIX + "delOrderReset", cmCodeEntity);
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     *
     * @description : 문서내역 - 프로시저 저장 (STO예외처리, 오더초기화, 강제결품)
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.05        JiSooKim (jskim14@cj.net)       생성
     */

    public String saveT3Pr(KpKxCloseReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        KpKxCloseReqDto reqDto = ModelMapperUtil.map(paramDto, KpKxCloseReqDto.class);

        List<KpKxCloseResT3Dto> saveList = reqDto.getSaveList3(); // 저장리스트

        reqDto.setAvc_COMMAND(reqDto.getPrType());
        PROCESSTYPE = reqDto.getPrType();     // 프로세스타입

        /*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp" + TEMPTABLETYPE, reqDto);

        // 임시테이블 Insert
        // 200개씩 끊어서 Insert 처리 200개씩 끊어서 Insert 처리
        int chunkSize = 200;
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            KpKxCloseResT3Dto dto = saveList.get(i);

            CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
            entity.setPackagename(PAKAGE_NAME); // 패키지명
            entity.setProcesstype(PROCESSTYPE); // 프로세스타입

            /* DTO 필드(카멜) → Entity 필드(카멜) 1 : 1 순서 매핑 */
            String columnsDto = "";
            String columnsEntity = "";

            // 문서내역 탭의 3개 프로시저 컬럼 모두 동일함
            columnsDto = "CHECKYN|DOCTYPE|ORGANIZE|DELIVERYDATE|DCCODE|DOCNO|DOCLINE|SKU";
            columnsEntity = "CHECKYN|OTHER01|ORGANIZE|OTHER02|DCCODE|DOCNO|DOCLINE|SKU";

            entity = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);

            // UI.params
            // START.필수입력 check - 그리드 변수 등
            // END.필수입력 check

            insertList.add(entity);

            // 200개마다 혹은 마지막 루프일 때 insert(3/3)
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertTempAJ_T3", insertList);
                insertList.clear();
            }
        }
        /*END.Temp Table Insert*/

        /*START.PAKAGE 호출*/
        // PKG 파라마터 세팅 - 공통(1/4)
        KpKxCloseReqDto dto = new KpKxCloseReqDto();
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PROCEDURE", "PROCESSTYPE", "PROCESSCREATOR", "SPID"};
        Object[] valueList = {PAKAGE_NAME, PROCESSTYPE, reqDto.getGUserId(), reqDto.getGSpid()};

        if (("KX_ORDERCLOSE").equals(reqDto.getPrType())) { // 강제결품일 때 batch parameter 추가
            keyList = new String[]{"PROCEDURE", "PROCESSTYPE", "PROCESSCREATOR", "SPID", "REASONCODE", "REASONMSG"};
            valueList = new Object[]{PAKAGE_NAME, PROCESSTYPE, reqDto.getGUserId(), reqDto.getGSpid(), reqDto.getReasoncode(), reqDto.getReasonmsg()};
        }

        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(dto); // 패키지 호출
//		log.info("rv->{}",rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl(dto.getResultMessage());

        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            log.error("▶문서내역 -  오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"KX마감진행현황-문서내역"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }


    /**
     * @description : 인수거절확인 검색 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.26 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT4Dto> getKxCdiiList(KpKxCloseReqDto kpKxCloseReqDto) {

        List<KpKxCloseResT4Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxCdiiList", kpKxCloseReqDto);

        return res;
    }

    /**
     * @description : 수불확인 검색 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.26 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT5Dto> getKxInoutListForSku(KpKxCloseReqDto kpKxCloseReqDto) {

        List<KpKxCloseResT5Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxInoutListForSku", kpKxCloseReqDto);

        return res;
    }

    /**
     * @description : 조정내역반영
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     *  2025.11.27 YoSepPark (dytpq362@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT6Dto> getKxAjRequestList(KpKxCloseReqDto kpKxCloseReqDto) {

        List<KpKxCloseResT6Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxAjRequestList", kpKxCloseReqDto);

        return res;
    }

    /**
     * @description : 조정내역반영 저장 및 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     *  2025.12.01 YoSepPark (dytpq362@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT6Dto> saveKxAj(KpKxCloseReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        KpKxCloseReqDto reqDto = ModelMapperUtil.map(paramDto, KpKxCloseReqDto.class);
        List<KpKxCloseResT6Dto> saveList = reqDto.getSaveList2(); // 저장리스트
        reqDto.setAvc_COMMAND("ORDERMODIFY");
        reqDto.setProcesstype("SPOM_ORDERMODIFY_KX"); // 프로세스타입
        reqDto.setTemptabletype("AJ"); // 임시테이블 타입
        reqDto.setPackagename("SPOM_ORDERMODIFY_KX"); // 패키지명

        PROCESSTYPE = reqDto.getProcesstype();     // 프로세스타입
        TEMPTABLETYPE = reqDto.getTemptabletype(); // 임시테이블 타입 Aj
        PAKAGE_NAME = reqDto.getPackagename();     // 패키지명

        /*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp" + TEMPTABLETYPE, reqDto);

        // 임시테이블 Insert
        // 200개씩 끊어서 Insert 처리 200개씩 끊어서 Insert 처리
        int chunkSize = 200;
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            KpKxCloseResT6Dto dto = saveList.get(i);

            CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
            entity.setPackagename(PAKAGE_NAME); // 패키지명
            entity.setProcesstype(PROCESSTYPE); // 프로세스타입

            /* DTO 필드(카멜) → Entity 필드(카멜) 1 : 1 순서 매핑 */
            String columnsDto = "DOCTYPE|KXTYPE|DELIVERYDATE|KXDCCODE|KXSLIPNO|KXSLIPLINENO|DOCNO|SKU|KXQTY|KXUOM|QTY|UOM";
            String columnsEntity = "OTHER01|OTHER02|SLIPDT|OTHER03|SLIPNO|SLIPLINE|DOCNO|SKU|CONFIRMQTY|OTHER04|OTHER05|UOM";

            entity = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);

            // UI.params
            // START.필수입력 check - 그리드 변수 등
            // END.필수입력 check
            insertList.add(entity);

            // 200개마다 혹은 마지막 루프일 때 insert(3/3)
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertTempAJ2", insertList);
                insertList.clear();
            }
        }
        /*END.Temp Table Insert*/

        /*START.PAKAGE 호출*/
        // PKG 파라마터 세팅 - 공통(1/4)
        KpKxCloseReqDto dto = new KpKxCloseReqDto();
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PROCEDURE", "PROCESSTYPE", "PROCESSCREATOR", "SPID"};
        Object[] valueList = {PAKAGE_NAME, PROCESSTYPE, reqDto.getGUserId(), reqDto.getGSpid()};

        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(dto); // 패키지 호출
        log.info("rv->{}", rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl(dto.getResultMessage());

        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            log.error("▶조정요청 - 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"조정요청-저장"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        List<KpKxCloseResT6Dto> printList = commonDao.selectList(SERVICEID_PREFIX + "getKxAjResultList", reqDto);
        return printList;
    }


    /**
     * @description : 협력사반출처리상태
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     *  2025.12.05 YoSepPark (dytpq362@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT7Dto> getKxRPList(KpKxCloseReqDto kpKxCloseReqDto) {

        List<KpKxCloseResT7Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxRPList", kpKxCloseReqDto);

        return res;
    }

    /**
     * @description : 협력사반출 예외처리 기능을 구현한 Method
     * @issues :
     * -----------------------------------------------------------
     * DATE AUTHOR MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.08 ParkYoSep
     */
    public String saveKxRPEx(KpKxCloseReqDto req) {
        if (req != null) {
            List<KpKxCloseResT7Dto> list = req.getSaveList4();
            for (var dto : list) {
                var entity = ModelMapperUtil.map(dto, userContext, KpKxCloseRPEntity.class);
                commonDao.update(SERVICEID_PREFIX + "updateForceProcRP", entity);
            }
        }


        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 협력사반출 저장 및 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     *  2025.12.08 YoSepPark (dytpq362@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT7Dto> saveKxRP(KpKxCloseReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        KpKxCloseReqDto reqDto = ModelMapperUtil.map(paramDto, KpKxCloseReqDto.class);
        List<KpKxCloseResT7Dto> saveList = reqDto.getSaveList4(); // 저장리스트
        reqDto.setAvc_COMMAND("RPMODIFY");
        reqDto.setProcesstype("RPMODIFY"); // 프로세스타입
        reqDto.setTemptabletype("AJ"); // 임시테이블 타입
        reqDto.setPackagename("SPOM_ORDERMODIFY_KX"); // 패키지명

        PROCESSTYPE = reqDto.getProcesstype();     // 프로세스타입
        TEMPTABLETYPE = reqDto.getTemptabletype(); // 임시테이블 타입 Aj
        PAKAGE_NAME = reqDto.getPackagename();     // 패키지명

        /*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp" + TEMPTABLETYPE, reqDto);

        // 임시테이블 Insert
        // 200개씩 끊어서 Insert 처리 200개씩 끊어서 Insert 처리
        int chunkSize = 200;
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            KpKxCloseResT7Dto dto = saveList.get(i);

            CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
            entity.setPackagename(PAKAGE_NAME); // 패키지명
            entity.setProcesstype(PROCESSTYPE); // 프로세스타입

            /* DTO 필드(카멜) → Entity 필드(카멜) 1 : 1 순서 매핑 */
            String columnsDto = "DCCODE|ORGANIZE|DOCNO|DOCLINE|SKU|REQ_QTY|REQ_UOM|DMD_TIMESTAMP|DMH_TIMESTAMP";
            String columnsEntity = "DCCODE|ORGANIZE|DOCNO|DOCLINE|SKU|CONFIRMQTY|UOM|OTHER01|OTHER02";

            CmSyProcessTempAjEntity entity2 = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);
            // UI.params
            // START.필수입력 check - 그리드 변수 등
            // END.필수입력 check
            insertList.add(entity);

            // 200개마다 혹은 마지막 루프일 때 insert(3/3)
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertTempAJ3", insertList);
                insertList.clear();
            }
        }
        /*END.Temp Table Insert*/

        /*START.PAKAGE 호출*/
        // PKG 파라마터 세팅 - 공통(1/4)
        KpKxCloseReqDto dto = new KpKxCloseReqDto();
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PROCEDURE", "PROCESSTYPE", "PROCESSCREATOR", "SPID"};
        Object[] valueList = {PAKAGE_NAME, PROCESSTYPE, reqDto.getGUserId(), reqDto.getGSpid()};

        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(dto); // 패키지 호출
        log.info("rv->{}", rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl(dto.getResultMessage());

        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            log.error("▶협력사반출 - 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"협력사반출-저장"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        List<KpKxCloseResT7Dto> printList = commonDao.selectList(SERVICEID_PREFIX + "getKxRPResultList", reqDto);
        return printList;
    }

    /**
     * @description : 소유권확인 탭 저장 및 결과 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     *  2026.01.15 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT9Dto> saveKxTrOrderCheck(KpKxCloseReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        KpKxCloseReqDto reqDto = ModelMapperUtil.map(paramDto, KpKxCloseReqDto.class);
        List<KpKxCloseResT9Dto> saveList = reqDto.getSaveList9(); // 저장리스트

        reqDto.setAvc_COMMAND("TR_ORDERCHECK");
        reqDto.setProcesstype("TR_ORDERCHECK"); // 프로세스타입
        PROCESSTYPE = reqDto.getProcesstype();    // 프로세스타입 TR_ORDERCHECK

        /*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp" + TEMPTABLETYPE, reqDto);

        // 임시테이블 Insert
        // 200개씩 끊어서 Insert 처리 200개씩 끊어서 Insert 처리
        int chunkSize = 200;
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            KpKxCloseResT9Dto dto = saveList.get(i);

            CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
            entity.setPackagename(PAKAGE_NAME); // 패키지명
            entity.setProcesstype(PROCESSTYPE); // 프로세스타입

            /* DTO 필드(카멜) → Entity 필드(카멜) 1 : 1 순서 매핑 */
            String columnsDto = "DOCTYPE|KXTYPE|DELIVERYDATE|KXDCCODE|KXSLIPNO|KXSLIPLINENO|DOCNO|SKU|KXQTY|KXUOM|QTY|UOM";
            String columnsEntity = "OTHER01|OTHER02|SLIPDT|OTHER03|SLIPNO|SLIPLINE|DOCNO|SKU|CONFIRMQTY|OTHER04|OTHER05|UOM";

            entity = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);

            // UI.params
            // START.필수입력 check - 그리드 변수 등
            // END.필수입력 check
            insertList.add(entity);

            // 200개마다 혹은 마지막 루프일 때 insert(3/3)
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertTempAJ2", insertList);
                insertList.clear();
            }
        }
        /*END.Temp Table Insert*/

        /*START.PAKAGE 호출*/
        // PKG 파라마터 세팅 - 공통(1/4)
        KpKxCloseReqDto dto = new KpKxCloseReqDto();
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PROCEDURE", "PROCESSTYPE", "PROCESSCREATOR", "SPID"};
        Object[] valueList = {PAKAGE_NAME, PROCESSTYPE, reqDto.getGUserId(), reqDto.getGSpid()};

        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(dto); // 패키지 호출
        log.info("rv->{}", rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl(dto.getResultMessage());

        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            log.error("▶조정요청 - 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"소유권확인-저장"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        List<KpKxCloseResT9Dto> printList = commonDao.selectList(SERVICEID_PREFIX + "getDataKXTrOrderCheck", reqDto);
        return printList;
    }

    /**
     * @description : 수불비교 - 월간 수불비교 검색 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT11Dto> getKxSubulMonthList(KpKxCloseReqDto kpKxCloseReqDto) {
        List<KpKxCloseResT11Dto> res = null;

        if (!"1".equals(kpKxCloseReqDto.getMinusSubulYn())) {
            res = commonDao.selectList(SERVICEID_PREFIX + "getKxSubulMonthList", kpKxCloseReqDto);
        } else { // (-)수불건 모니터링
            res = commonDao.selectList(SERVICEID_PREFIX + "getKxMinusSubulList", kpKxCloseReqDto);
        }

        return res;
    }

    /**
     * @description : 수불비교 - 월간 수불비교 검색 조회 (대한통운 API 버전)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.03 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT11Dto> getKxSubulMonthList2(KpKxCloseReqDto kpKxCloseReqDto) {
        // 필수값 체크
        if (kpKxCloseReqDto.getDeliveryDate() == null || "".equals(kpKxCloseReqDto.getDeliveryDate())) {
            throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"실행일자"})); // {0} 항목은 필수 입니다.
        }

        List<KpKxCloseResT11Dto> res = null;

        Map<String, String> params = new HashMap<>();
        params.put("ID", "FX");
        params.put("TR_NAME", "WEB_SUBUL_TO_FX");
        params.put("DELIVERYDATE", kpKxCloseReqDto.getDeliveryDate());
        params.put("ORGANIZE", kpKxCloseReqDto.getOrganize());
        params.put("SKU", kpKxCloseReqDto.getSku());

        // 대한통운 API 호출
        ApiResponseT11 apiRes = CJServiceUtil.callCJService(params, ApiResponseT11.class, "/service/CJ/inventory");
        if (apiRes != null && apiRes.getData() != null && !apiRes.getData().isEmpty()) {
            // DATA_LIST 추출
            List<KpKxCloseResT11Dto> dataList = apiRes.getData().get(0).getDataList();

            // DATA_LIST 정제
            for (KpKxCloseResT11Dto data : dataList) {
                data.setOrganize(data.getPlant() + "-" + data.getStorageLoc());
                data.setKxQty(data.getReceiptQty() - data.getOrderQty() + data.getAdjustQty() + data.getTransferQty());
            }

            // DATA_LIST "organize" + "sku" 기준으로 group by
            Map<String, Integer> groupMap =
                dataList.stream()
                    .collect(Collectors.groupingBy(
                        dto -> dto.getOrganize() + "_" + dto.getSku(),
                        Collectors.summingInt(KpKxCloseResT11Dto::getKxQty)
                    ));

            if (!"1".equals(kpKxCloseReqDto.getMinusSubulYn())) {
                res = commonDao.selectList(SERVICEID_PREFIX + "getKxSubulMonthList2", kpKxCloseReqDto);

                // KX 수량 매핑
                for (KpKxCloseResT11Dto resDto : res) {
                    if (dataList != null) {
                        resDto.setKxQty(groupMap.getOrDefault(resDto.getOrganize() + "_" + resDto.getSku(), 0));
                    }
                }
            } else { // (-)수불건 모니터링
                // group by 기준으로 새로운 목록 생성
                res = groupMap.entrySet().stream()
                    .map(e -> {
                        String[] key = e.getKey().split("_");

                        KpKxCloseResT11Dto dto = new KpKxCloseResT11Dto();
                        dto.setDeliveryDate(kpKxCloseReqDto.getDeliveryDate());
                        dto.setOrganize(key[0]);
                        dto.setSku(key[1]);
                        dto.setKxQty(e.getValue());

                        return dto;
                    })
                    .toList();

                if (res != null) {
                    res = res.stream()
                        .filter(dto -> dto.getKxQty() < 0)
                        .collect(Collectors.toList());
                }
            }
        }

        return res;
    }

    /**
     * @description : 수불비교 - 일간 수불비교 검색 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT11Dto> getKxSubulDayList(KpKxCloseReqDto kpKxCloseReqDto) {

        List<KpKxCloseResT11Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxSubulDayList", kpKxCloseReqDto);

        return res;
    }

    /**
     * @description : 수불비교 - 일간 수불비교 검색 조회 (대한통운 API 버전)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.03 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT11Dto> getKxSubulDayList2(KpKxCloseReqDto kpKxCloseReqDto) {

        List<KpKxCloseResT11Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxSubulDayList2", kpKxCloseReqDto);

        Map<String, String> params = new HashMap<>();
        params.put("ID", "FX");
        params.put("TR_NAME", "WEB_SUBUL_TO_FX");
        params.put("DELIVERYDATE", kpKxCloseReqDto.getDeliveryDate());
        params.put("ORGANIZE", kpKxCloseReqDto.getOrganize());
        params.put("SKU", kpKxCloseReqDto.getSku());

        // 대한통운 API 호출
        ApiResponseT11 apiRes = CJServiceUtil.callCJService(params, ApiResponseT11.class, "/service/CJ/inventory");
        if (apiRes != null && apiRes.getData() != null && !apiRes.getData().isEmpty()) {
            // DATA_LIST 추출
            List<KpKxCloseResT11Dto> dataList = apiRes.getData().get(0).getDataList();

            // DATA 정제
            for (KpKxCloseResT11Dto data : dataList) {
                data.setOrganize(data.getPlant() + "-" + data.getStorageLoc());
                data.setKxQty(data.getReceiptQty() - data.getOrderQty() + data.getAdjustQty() + data.getTransferQty());
                data.setFwQty(0);
            }

            // DATA_LIST "deliveryDate" + "organize" + "sku" 기준으로 group by
            Map<String, Integer> groupMap =
                dataList.stream()
                    .collect(Collectors.groupingBy(
                        dto -> dto.getDeliveryDate() + "_" + dto.getOrganize() + "_" + dto.getSku(),
                        Collectors.summingInt(KpKxCloseResT11Dto::getKxQty)
                    ));

            // 저장위치 "BAD","GOOD" KX 수량 합치기
            for (KpKxCloseResT11Dto data : dataList) {
                data.setKxQty(groupMap.getOrDefault(data.getDeliveryDate() + "_" + data.getOrganize() + "_" + data.getSku(), 0));
            }

            // KX 수량 매핑
            for (KpKxCloseResT11Dto resDto : res) {
                if (dataList != null) {
                    resDto.setKxQty(
                        dataList.stream()
                            .filter(dto ->
                                resDto.getDeliveryDate().equals(dto.getDeliveryDate()) &&
                                    resDto.getOrganize().equals(dto.getOrganize()) &&
                                    resDto.getSku().equals(dto.getSku()))
                            .map(KpKxCloseResT11Dto::getKxQty)
                            .findFirst()
                            .orElse(0)
                    );
                }
            }

            // 매핑 안된 KX 목록 추가
            if (dataList != null) {
                Set<String> resKeySet = res.stream()
                    .map(dto -> dto.getDeliveryDate() + "_" + dto.getOrganize() + "_" + dto.getSku())
                    .collect(Collectors.toSet());

                for (KpKxCloseResT11Dto dto : dataList) {
                    String key = dto.getDeliveryDate() + "_" + dto.getOrganize() + "_" + dto.getSku();
                    if (!resKeySet.contains(key)) {
                        res.add(dto);
                    }
                }
            }

            // 날짜 기준 목록 정렬
            res.sort(Comparator.comparing(KpKxCloseResT11Dto::getDeliveryDate));
        }

        return res;
    }

    /**
     * @description : 수불비교 - 일간 수불비교(상세) 검색 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT11Dto> getKxSubulDayDetailList(KpKxCloseReqDto kpKxCloseReqDto) {

        List<KpKxCloseResT11Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxSubulDayDetailList",
            kpKxCloseReqDto);

        return res;
    }

    /**
     * @description : 수불비교 - 일간 수불비교(상세) 검색 조회 (대한통운 API 버전)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.09 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT11Dto> getKxSubulDayDetailList2(KpKxCloseReqDto kpKxCloseReqDto) {

        // 필수값 체크
        if (kpKxCloseReqDto.getDeliveryDate() == null || "".equals(kpKxCloseReqDto.getDeliveryDate())) {
            throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"실행일자"})); // {0} 항목은 필수 입니다.
        }

        List<KpKxCloseResT11Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxSubulDayDetailList2",
            kpKxCloseReqDto);

        Map<String, String> params = new HashMap<>();
        params.put("ID", "FX");
        params.put("TR_NAME", "WEB_SUBUL_TO_FX");
        params.put("DELIVERYDATE", kpKxCloseReqDto.getDeliveryDate().substring(0, 6));
        params.put("ORGANIZE", kpKxCloseReqDto.getOrganize());
        params.put("SKU", kpKxCloseReqDto.getSku());

        // 대한통운 API 호출
        ApiResponseT11 apiRes = CJServiceUtil.callCJService(params, ApiResponseT11.class, "/service/CJ/inventory");
        if (apiRes != null && apiRes.getData() != null && !apiRes.getData().isEmpty()) {
            // DATA_LIST 추출
            List<KpKxCloseResT11Dto> dataList = apiRes.getData().get(0).getDataList();

            // DATA 정제
            for (KpKxCloseResT11Dto data : dataList) {
                data.setDataDiv("KX");
            }

            // 일자 기준 filter
            if (dataList != null) {
                dataList = dataList.stream()
                    .filter(dto -> dto.getDeliveryDate().equals(kpKxCloseReqDto.getDeliveryDate()))
                    .toList();

                // Retrun 목록에 KX DATA 추가
                res.addAll(dataList);
            }
        }

        return res;
    }

    /**
     * @description : 재고비교
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.15 JiSooKim (jskim14@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT13Dto> getKxStDiffList(KpKxCloseReqDto kpKxCloseReqDto) {
        List<KpKxCloseResT13Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxStDiffList", kpKxCloseReqDto);

        return res;
    }

    /**
     * @description : 재고비교 (대한통운 API 버전)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.04 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public List<KpKxCloseResT13Dto> getKxStDiffList2(KpKxCloseReqDto kpKxCloseReqDto) {
        List<KpKxCloseResT13Dto> res = commonDao.selectList(SERVICEID_PREFIX + "getKxStDiffList2", kpKxCloseReqDto);

        Map<String, String> params = new HashMap<>();
        params.put("ID", "FX");
        params.put("TR_NAME", "WEB_SUBUL_TO_FX");
        params.put("DELIVERYDATE", kpKxCloseReqDto.getDeliveryDate());

        // 대한통운 API 호출
        ApiResponseT13 apiRes = CJServiceUtil.callCJService(params, ApiResponseT13.class, "/service/CJ/inventory");
        if (apiRes != null && apiRes.getData() != null && !apiRes.getData().isEmpty()) {
            // DATA_LIST 추출
            List<KpKxCloseResT13Dto> dataList = apiRes.getData().get(0).getDataList();

            // KX 수량 매핑
            for (KpKxCloseResT13Dto resDto : res) {
                if (dataList != null) {
                    resDto.setKxQty(
                        dataList.stream()
                            .filter(dto ->
                                resDto.getOrganize().equals(dto.getOrganize()) &&
                                    resDto.getSku().equals(dto.getSku()))
                            .map(KpKxCloseResT13Dto::getKxQty)
                            .findFirst()
                            .orElse(0)
                    );

                    // 차이량(FW-KX)
                    resDto.setDiffQty(resDto.getFwQty() - resDto.getKxQty());
                }
            }

            // 수량차이분 목록 필터
            if ("1".equals(kpKxCloseReqDto.getDiffYn()) && res != null) {
                res = res.stream()
                    .filter(dto -> dto.getDiffQty() != 0)
                    .collect(Collectors.toList());
            }
        }

        return res;
    }

    /**
     * @description : IF 송수신 상태 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.15 sss (kduimux@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDataIfStatusList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDataIfStatusList", reqDto);
    }

    /**
     *
     * @description : 코스트센터 저장
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.05        sss (kduimux@cj.net)       생성
     */
    public KpKxCloseReqDto saveDuplicateCostCd(KpKxCloseReqDto reqDto) {
        KpKxCloseReqDto resultDto = new KpKxCloseReqDto();
        int rv = 0; // 반환 값
        int processCnt = 0; // 처리 건수
        KpKxCloseEntity entity = ModelMapperUtil.map(reqDto, userContext, KpKxCloseEntity.class);
        rv = commonDao.update(SERVICEID_PREFIX + "updateDuplicateCostCd", entity);
        if (rv < 1) {
            throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
        }
        processCnt = rv;

        resultDto.setProcessCnt(processCnt); // 처리 건수
        return resultDto;
    }

    /**
     *
     * @description : SRM1300 저장
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.05        sss (kduimux@cj.net)       생성
     */
    public KpKxCloseReqDto saveDuplicateSRM1300(KpKxCloseReqDto reqDto) {
        KpKxCloseReqDto resultDto = new KpKxCloseReqDto();
        int rv = 0; // 반환 값
        int processCnt = 0; // 처리 건수
        KpKxCloseEntity entity = ModelMapperUtil.map(reqDto, userContext, KpKxCloseEntity.class);
        rv = commonDao.update(SERVICEID_PREFIX + "updateDuplicateSRM1300", entity);
        if (rv < 1) {
            throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
        }
        processCnt = rv;

        resultDto.setProcessCnt(processCnt); // 처리 건수
        return resultDto;
    }

    /**
     *
     * @description : 코스트센터 저장
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.05        sss (kduimux@cj.net)       생성
     */
    public KpKxCloseReqDto saveDuplicateCostCd111(KpKxCloseReqDto paramDto) {
        KpKxCloseReqDto resultDto = new KpKxCloseReqDto();
        // 파라미터 위변조 적용(paramDto->reqDto)
        KpKxCloseReqDto reqDto = ModelMapperUtil.map(paramDto, KpKxCloseReqDto.class);

        List<KpKxCloseResT14Dto> saveList = reqDto.getSaveList10(); // 저장리스트

        KpKxCloseEntity entity = ModelMapperUtil.map(reqDto, userContext, KpKxCloseEntity.class);
        commonDao.update(SERVICEID_PREFIX + "updateDuplicateCostCd", entity);
        return resultDto;
    }

    /**
     * @description : IF 송수신 상태 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.15 sss (kduimux@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDataIfStatusDetailList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDataIfStatusDetailList", reqDto);
    }


    /**
     *
     * @description : 오류 재처리
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.05        sss (kduimux@cj.net)       생성
     */
    public KpKxCloseReqDto saveIFTables(KpKxCloseReqDto paramDto) {
        KpKxCloseReqDto resultDto = new KpKxCloseReqDto();
        int rv = 0; // 반환 값
        int processCnt = 0; // 처리 건수

        // 파라미터 위변조 적용(paramDto->reqDto)
        KpKxCloseReqDto reqDto = ModelMapperUtil.map(paramDto, KpKxCloseReqDto.class);
        List<KpKxCloseResT14Dto> saveList = null; // 저장리스트
        String ifFlag = StringUtil.nvl(reqDto.getIfFlag()); // IF 구분값(N:재처리, Y:제외처리)

        if ("N".equals(ifFlag)) { // 재처리
            saveList = reqDto.getSaveList10();
        } else { // 제외처리
            saveList = reqDto.getSaveList11();
        }
        log.info("▶saveList.size->{}", saveList);

        // 처리
        for (KpKxCloseResT14Dto dto : saveList) {
            //if(CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {
            String ifType = StringUtil.nvl(dto.getIfType());
            dto.setIfFlag(ifFlag); // IF 구분값(N:재처리, Y:제외처리)

            if ("IF_DM_DOCUMENT_D".equals(ifType) || "IF_DM_DOCUMENT_H".equals(ifType)
                || "IF_SENDRESULT".equals(ifType) || "IF_ST_STOCK_SERIALINFO_S".equals(ifType)
                || "IF_ST_STOCK_SERIALINFO_R".equals(ifType) || "IF_DM_SENDDATA".equals(ifType)
            ) {

                KpKxCloseEntity entity = ModelMapperUtil.map(dto, userContext, KpKxCloseEntity.class);
                rv = commonDao.update(SERVICEID_PREFIX + "updateIFTables_" + ifType, entity);
                log.info("▶ifType->{} / rv->{}", ifType, rv);
                if (rv < 1) {
                    throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.075")); // 적용된 건수가 없습니다.
                }
                processCnt += rv;
            }
            //}
        }

        resultDto.setProcessCnt(processCnt); // 처리 건수
        return resultDto;
    }


    /**
     * @description : 실적미수신 상태 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.15 sss (kduimux@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDataNotRcvList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDataNotRcvList", reqDto);
    }


    /**
     * @description : 실적미수신 KX접수여부 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.15 sss (kduimux@cj.net) 생성 </pre>
     */
    public String getKxAcceptYn(KpKxCloseReqDto reqDto) {
        Map<String, String> params = new HashMap<>();
        params.put("ID", "FX");
        params.put("TR_NAME", "WEB_ORDERS_TO_FX");
        params.put("DOCNO", reqDto.getDocno());
        params.put("SKU", reqDto.getSku());
        params.put("DOCLINE", reqDto.getDocline());

        // 대한통운 API 호출
        ApiResponse8 apiRes = CJServiceUtil.callCJService(params, ApiResponse8.class, "/service/CJ/result");

        if (apiRes != null && apiRes.getData() != null && !apiRes.getData().isEmpty()) {
            return apiRes.getData().get(0).getDataList().size() > 0 ? "Y" : "N";
        } else {
            return "N";
        }
    }


    /**
     *
     * @description : 실적미수신 - 오더강제결품 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.15 sss (kduimux@cj.net) 생성 </pre>
     */
    public KpKxCloseReqDto saveOrderClear(KpKxCloseReqDto paramDto) throws Exception {
        KpKxCloseReqDto resultDto = new KpKxCloseReqDto();

        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        KpKxCloseReqDto reqDto = ModelMapperUtil.map(paramDto, KpKxCloseReqDto.class);

        List<KpKxCloseResT8Dto> saveList = reqDto.getSaveList8(); // 저장리스트

        reqDto.setAvc_COMMAND(reqDto.getAvc_COMMAND());

        /*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp" + TEMPTABLETYPE, reqDto);

        // 임시테이블 Insert
        // 200개씩 끊어서 Insert 처리 200개씩 끊어서 Insert 처리
        int chunkSize = 200;
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            KpKxCloseResT8Dto dto = saveList.get(i);

            CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
            entity.setPackagename(PAKAGE_NAME); // 패키지명
            entity.setProcesstype(PROCESSTYPE_ORDERCLEAR); // 프로세스타입

            /* DTO 필드(카멜) → Entity 필드(카멜) 1 : 1 순서 매핑 */
            String columnsDto = "";
            String columnsEntity = "";

            // 문서내역 탭의 3개 프로시저 컬럼 모두 동일함
            columnsDto = "DOCTYPE|ORGANIZE|DELIVERYDATE|DCCODE|DOCNO|DOCLINE|SKU";
            columnsEntity = "OTHER01|ORGANIZE|OTHER02|DCCODE|DOCNO|DOCLINE|SKU";

            entity = (CmSyProcessTempAjEntity) EtcUtil.conversionEntityToAsis(dto, entity, columnsDto, columnsEntity);

            // UI.params
            // START.필수입력 check - 그리드 변수 등
            if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOther01()))) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"doctype"}));
            // END.필수입력 check

            insertList.add(entity);

            // 200개마다 혹은 마지막 루프일 때 insert(3/3)
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertTempAJ_NotRcv", insertList);
                insertList.clear();
            }
        }
        /*END.Temp Table Insert*/

        /*START.PAKAGE 호출*/
        // PKG 파라마터 세팅 - 공통(1/4)
        KpKxCloseReqDto dto = new KpKxCloseReqDto();
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PROCEDURE", "PROCESSTYPE", "PROCESSCREATOR", "SPID"
            // 업무파라이터
            //,"REASONCODE", "REASONMSG"

        };
        Object[] valueList = {PAKAGE_NAME, PROCESSTYPE_ORDERCLEAR, reqDto.getGUserId(), reqDto.getGSpid()
            // 업무파라이터
            //,reqDto.getReasoncode(),reqDto.getReasonmsg()
        };

        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(dto); // 패키지 호출
//		log.info("rv->{}",rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl(dto.getResultMessage());

        // 프로시저 Exception 처리(4/4)
        if (!"0".equals(resultCode)) {
            log.error("▶실적미수신 -  오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"KX마감진행현황-실적미수신"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        return resultDto;
    }

    /**
     *
     * @description : 실적미수신 - 오더강제결품 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.15 sss (kduimux@cj.net) 생성 </pre>
     */
    public KpKxCloseReqDto saveEmail(KpKxCloseReqDto paramDto) throws Exception {
        KpKxCloseReqDto resultDto = new KpKxCloseReqDto();
        CmSendEmailReqDto emailDto = new CmSendEmailReqDto();
        StringBuffer html = new StringBuffer();
        int processCnt = 0; // 처리 건수

        // 파라미터 위변조 적용(paramDto->reqDto)
		KpKxCloseReqDto reqDto = ModelMapperUtil.map(paramDto, KpKxCloseReqDto.class);
		
	    List<KpKxCloseResT8Dto> receiversList = reqDto.getReceiversList(); // 수신자 리스트
	    List<KpKxCloseResT8Dto> detailList = reqDto.getDetailList(); // 상세 리스트		
		
		String cnts = reqDto.getConts(); // UI의 메일 내용 
		
		html.append("<div class=\"tablecontent_sub_title\" width=\"800\" style=\"font:12 굴림\">     ").append("\n");
	    html.append("    <div class=\"label\">").append(cnts).append("</div>                        ").append("\n");
	    html.append("</div>                                                                         ").append("\n");

	    html.append("                                                                               ").append("\n");
	    html.append("<table class=\"tablecontent tablecontent_grid\" id=\"datatable1\" border=\"1\" cellpadding=\"5\" cellspacing=\"0\" bordercolor=\"lightgrey\" width=\"800\" style=\"font:12 굴림\">      ").append("\n");
	    html.append("    <colgroup>                             ").append("\n");
	    html.append("        <col class=\"col1\" width=\"80\">  ").append("\n");
	    html.append("        <col class=\"col2\" width=\"90\">  ").append("\n");
	    html.append("        <col class=\"col3\" width=\"80\">  ").append("\n");
	    html.append("        <col class=\"col4\" width=\"50\">  ").append("\n");
	    html.append("        <col class=\"col5\" width=\"50\">  ").append("\n");
	    html.append("        <col class=\"col6\" width=\"50\">  ").append("\n");
	    html.append("        <col class=\"col7\" width=\"50\">  ").append("\n");
	    html.append("        <col class=\"col8\" width=\"70\">  ").append("\n");
	    html.append("        <col class=\"col9\" width=\"150\"> ").append("\n");
	//  html.append("        <col class=\"col10\">            ").append("\n");
	//  html.append("        <col class=\"col11\">            ").append("\n");
	    html.append("        <col class=\"col12\" width=\"130\">").append("\n");
	    html.append("    </colgroup>                        ").append("\n");
	    html.append("    <thead bgcolor=\"#70D3E9\">          ").append("\n");
	    html.append("        <tr>                           ").append("\n");
	    html.append("            <th>문서구분</th>             ").append("\n");
	    html.append("            <th>입/출고일자</th>           ").append("\n");
	    html.append("            <th>문서번호</th>             ").append("\n");
	    html.append("            <th>항목번호</th>             ").append("\n");
	    html.append("            <th>상품코드</th>             ").append("\n");
	    html.append("            <th>주문수량</th>             ").append("\n");
	    html.append("            <th>확정수량</th>             ").append("\n");
	    html.append("            <th>창고</th>                ").append("\n");
	    html.append("            <th>비고</th>                ").append("\n");
	//  html.append("            <th>실적수신여부</th>          ").append("\n");
	//  html.append("            <th>KX접수여부</th>           ").append("\n");
	    html.append("            <th>등록일</th>              ").append("\n");
	    html.append("        </tr>                          ").append("\n");
	    html.append("    </thead>                           ").append("\n");
	    html.append("    <tbody>                            ").append("\n");
	    for(int i = 0 ; i < detailList.size() ; i++) {
	        KpKxCloseResT8Dto dto = detailList.get(i);
	        html.append("        <tr>                                                                  ").append("\n");
	        html.append("            <td>").append(dto.getDoctype()).append("</td>                     ").append("\n");
	        html.append("            <td>").append(dto.getDeliverydate()).append("</td>                ").append("\n"); 
	        html.append("            <td>").append(dto.getDocno()).append("</td>                       ").append("\n");
	        html.append("            <td>").append(dto.getDocline()).append("</td>                     ").append("\n");
	        html.append("            <td>").append(dto.getSku()).append("</td>                         ").append("\n");
	        html.append("            <td align=\"right\">").append(dto.getOpenqty()).append("</td>      ").append("\n");
	        html.append("            <td align=\"right\">").append(dto.getConfirmqty()).append("</td>   ").append("\n");                
	        html.append("            <td>").append(dto.getOrganize()).append("</td>     ").append("\n");
	        html.append("            <td>").append(dto.getEtcTxt()).append("</td>      ").append("\n");
//	      html.append("            <td>").append(ds_content.get(i, \"IF_CFM_YN\")).append("</td>    ").append("\n");
//	      html.append("            <td>").append(ds_content.get(i, \"KX_ACCEPT_YN\")).append("</td>      ").append("\n");
	        html.append("            <td>").append(dto.getAdddate()).append("</td>         ").append("\n");
	        html.append("        </tr>                                                                       ").append("\n");
	    }
	    html.append("    </tbody>                                                                        ").append("\n");
	    html.append("</table>                                                                            ").append("\n");	
		
		if ( receiversList != null ) {
			for(int k = 0 ; k < receiversList.size() ; k++) {
				processCnt++;
				
				KpKxCloseResT8Dto rcvEmailDto = receiversList.get(k);
	    		cmEmailService.saveEmail(CmSendEmailReqDto.builder()
			    				.title(reqDto.getTitle())
			    				.conts(html.toString())
			    				.sendEmail(reqDto.getSendEmail())
			    				.sendName(reqDto.getSendName())
			    				.refEmailAdd(reqDto.getRefEmailAdd())
			    				//
			    				.recvEmail(rcvEmailDto.getRcvrEmail())
			    				.recvName(rcvEmailDto.getRcvrNm())
			    				.build()
			    			);
	    		
			}
		}		
		
		resultDto.setProcessCnt(processCnt); // 처리 건수
		return resultDto;
	}
	
	/**
	 * @description : 재고 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.11 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public List<KpKxCloseResT10Dto> getKxStockList(KpKxCloseReqDto kpKxCloseReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getKxStockList", kpKxCloseReqDto);
	}
	
	/**
	 * @description : STOCKID(개체식별/유통이력) 초기화 처리
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.11 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String saveStockIdInit(KpKxCloseReqDto kpKxCloseReqDto) {
		/*START.PAKAGE 호출*/
		// PKG 파라마터 세팅 - 공통(1/4)
        KpKxCloseReqDto dto = new KpKxCloseReqDto();
        ProcedureParametersFactory.initParamDto(kpKxCloseReqDto, dto, "SPOM_ORDERMODIFY_KX");

        // PKG 파라마터 세팅 - 업무(2/4)
        String[] keyList = {"PROCEDURE", "PROCESSTYPE", "PROCESSCREATOR", "SPID"};
        Object[] valueList = {"SPOM_ORDERMODIFY_KX", "KX_STOCKIDINIT", kpKxCloseReqDto.getGUserId(), kpKxCloseReqDto.getGSpid()};

        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        dto.setAvc_COMMAND("KX_STOCKIDINIT");
        dto.setProcesstype("KX_STOCKIDINIT");
        cmCommonService.saveProcedure(dto); // 패키지 호출

        // 프로시저 OUT Parameter(3/4)
        String resultCode = StringUtil.nvl(dto.getResultCode());
        String resultMessage = StringUtil.nvl(dto.getResultMessage());

        // 프로시저 Exception 처리(4/4)
        if (!"0".equals(resultCode)) {
            log.error("▶STOCKID(개체식별/유통이력) 초기화 처리 -  오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"KX마감진행현황-STOCKID(개체식별/유통이력) 초기화"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 전표 수정
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.02.13 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public String saveInplanZero(KpKxCloseReqDto kpKxCloseReqDto) {

        // 필수값 체크
        if (kpKxCloseReqDto.getDocno() == null || "".equals(kpKxCloseReqDto.getDocno())) {
            throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"전표번호"})); // {0} 항목은 필수 입니다.
        } else if (kpKxCloseReqDto.getDocline() == null || "".equals(kpKxCloseReqDto.getDocline())) {
            throw new UserHandleException(MessageUtil.getMessage("MSG.COM.ERR.074", new String[]{"라인번호"})); // {0} 항목은 필수 입니다.
        }

        KpKxCloseEntity entity = ModelMapperUtil.map(kpKxCloseReqDto, userContext, KpKxCloseEntity.class);

        // 확정량 변경
        commonDao.update(SERVICEID_PREFIX + "saveInplanZero", entity);

        // 예정량/원지시량/취소수량 변경
        commonDao.update(SERVICEID_PREFIX + "saveInplanZero2", entity);

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

}