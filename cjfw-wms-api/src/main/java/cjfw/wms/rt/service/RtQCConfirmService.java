package cjfw.wms.rt.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.entity.CmSyProcessTempRtEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.om.dto.OmOrderCreationSTOReqDto;
import cjfw.wms.om.dto.OmOrderCreationSTOResDto;
import cjfw.wms.om.service.OmOrderCreationSTOService;
import cjfw.wms.rt.dto.RtQCConfirmReqDto;
import cjfw.wms.rt.dto.RtQCConfirmResDto;
import cjfw.wms.st.dto.StLocMoveReqDto;
import cjfw.wms.st.dto.StLocMoveResDto;
import cjfw.wms.st.service.StLocMoveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.09.23
 * @description :반품판정처리  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RtQCConfirmService {
    private final UserContext userContext;
    private final StLocMoveService stLocMoveService;
    private final OmOrderCreationSTOService omOrderCreationSTOService;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPRT_QCCONFIRM";
    private transient static final String PROCESSTYPE = "RT_QCCONFIRM";
    private transient static final String TEMPTABLETYPE = "RT";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(RtQCConfirmService.class.getSimpleName()) + ".";

    /**
     * @description : 반품판정처리 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 반품판정처리 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", reqDto);
    }

    /**
     * @description : 반품판정처리 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", reqDto);
    }

    /**
     * @description : 반품판정처리 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getDetailList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList2", reqDto);
    }

    /**
     * @description : 반품판정처리 상세 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getPrintDetailList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDetailList2", reqDto);
    }

    /**
     * @description : 반품판정처리 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String saveMasterList(RtQCConfirmReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        RtQCConfirmReqDto reqDto = ModelMapperUtil.map(paramDto, RtQCConfirmReqDto.class);
        RtQCConfirmResDto data = reqDto.getSaveList().get(0); // 저장리스트의 첫번째 데이터

        RtQCConfirmReqDto dto = new RtQCConfirmReqDto();

        // PKG 파라마터 세팅 - 공통(1/4)
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"DCCODE","DOCDT","DOCNO","DOCLINE","SKU","UOM","BUNJA","BUNMO","SERIALNO","ROTYPE","RETURNOUTQTY","DISUSEQTY","GOODQTY","REASONCODE","REASONMSG","SLIPDT","SLIPNO","SLIPLINE", "TO_LOC", "RESP_TYPE", "LOGI_RESP_YN"};
        Object[] valueList = {
            data.getDccode(),
            data.getDocdt(),
            data.getDocno(),
            data.getDocline(),
            data.getSku(),
            data.getUom(),
            data.getBunja(),
            data.getBunmo(),
            StringUtil.nvl(data.getSerialno()),
            data.getRotype(),
            data.getReturnoutqty(),
            data.getDisuseqty(),
            data.getGoodqty(),
            data.getReasoncode(),
            StringUtil.nvl(data.getReasonmsg()),
            data.getSlipdt(),
            data.getSlipno(),
            data.getSlipline(),
            data.getToLoc(),
            data.getRespType(),
            data.getLogiRespYn()
        };
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        int rv = cmCommonService.saveProcedure(dto);
        log.info("rv->{}", rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl((String) dto.getResultMessage());
        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);

        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            log.error("▶저장 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"저장"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 반품판정처리 저장2
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    @Transactional
    public Map saveMasterList2(RtQCConfirmReqDto paramDto) throws Exception {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        RtQCConfirmReqDto reqDto = ModelMapperUtil.map(paramDto, RtQCConfirmReqDto.class);
        List<RtQCConfirmResDto> saveList = reqDto.getSaveList(); // 저장리스트
        log.info("▶saveList.size->{}", saveList);
        reqDto.setTemptabletype(TEMPTABLETYPE); // 임시테이블타입
        reqDto.setProcesstype(PROCESSTYPE); // 임시테이블타입

        /*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp" + TEMPTABLETYPE, reqDto);

        int chunkSize = 200;
        List<CmSyProcessTempRtEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            RtQCConfirmResDto dto = saveList.get(i);
            // 임시테이블에 등록(2/3)
            CmSyProcessTempRtEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempRtEntity.class);
            entity.setProcesstype(reqDto.getProcesstype()); // 프로세스타입
            entity.setTemptabletype(reqDto.getTemptabletype()); // 프로세스타입
            entity.setCustkey(dto.getVendor());
            entity.setWavekey(dto.getStorageloc());
            entity.setTrafficcop(dto.getStoDccode());
            // UI.params

            // START.필수입력 check CHECKYN|DELIVERYDT|DOCNO|SLIPDT|SLIPNO|CARNO|PRIORITY|DELIVERYGROUP|CUSTKEY
//            if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDto)dto).getDeliverydt())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"deliverydt"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
//            if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDto)dto).getCarno())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"carno"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
//            if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDto)dto).getPriority())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"priority"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
//            if ("".equals(cjfw.core.utility.StringUtil.nvl(((RoInvoiceResDto)dto).getDeliverygroup())))throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"deliverygroup"})); // 해당 정보가 없어 처리할 수 없습니다.-{0}
            // END.필수입력 check
            insertList.add(entity);

            // 200개마다 혹은 마지막 루프일 때 insert(3/3)
            if (insertList.size() == chunkSize || i == saveList.size() - 1) {
                commonDao.insert(SERVICEID_PREFIX + "insertTemp", insertList);
                insertList.clear();
            }
        }
        /*END.Temp Table Insert*/

        // PKG 파라마터 세팅 - 공통(1/4)
        RtQCConfirmReqDto dto = new RtQCConfirmReqDto();
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PROCEDURE","PROCESSTYPE","TEMPTABLETYPE","QCDT","WD_AUTOALLOC"};
        Object[] valueList = {
            PAKAGE_NAME,
            PROCESSTYPE,
            TEMPTABLETYPE,
            reqDto.getQcdt(),
            reqDto.getWdAutoalloc()
        };
        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
        dto.setAvc_DCCODE(paramDto.getFixdccode());
        int rv = cmCommonService.saveProcedure(dto);
        log.info("rv->{}", rv);

        // 프로시저 OUT Parameter(3/4)
        resultCode = StringUtil.nvl(dto.getResultCode());
        resultMessage = StringUtil.nvl((String) dto.getResultMessage());
        log.info("resultCode->{}", resultCode);
        log.info("resultMessage->{}", resultMessage);

        // 프로시저 Exception 처리(4/4)
        if (!resultCode.equals("0")) {
            log.error("▶저장 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"저장"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/

        /* 재고이동 호출 START*/
        StLocMoveReqDto moveParamDto = ModelMapperUtil.map(paramDto, StLocMoveReqDto.class);
        moveParamDto.setReasoncode("16"); //고객반품판정
        moveParamDto.setReasonmsg("반품 판정 처리 작업");
        List<StLocMoveResDto> moveList = new ArrayList<>();
        for(RtQCConfirmResDto moveDto : paramDto.getSaveList()){
            //N_기타 재고이동안함, 매입센터있으면 STO처리
            if("DISUSE".equals(moveDto.getProcesstype()) && !"60".equals(moveDto.getRespType()) && StringUtils.isEmpty(moveDto.getStoDccode())) {
                moveParamDto.setFixdccode(moveDto.getDccode());
                moveParamDto.setSku(moveDto.getSku());
                moveParamDto.setFromloc(moveDto.getLoc());
                moveParamDto.setToloc(moveDto.getLoc());
                moveParamDto.setStockgrade("S3"); //폐기
                StLocMoveResDto temp = (StLocMoveResDto)stLocMoveService.getTab1MasterList(moveParamDto).get(0);

                temp.setToLoc(moveDto.getToLoc());
                if("BOX".equals(moveDto.getStoreruom())){
                    temp.setToOrderqty(moveDto.getOrderqty().multiply(moveDto.getQtyperbox()));
                }else {
                    temp.setToOrderqty(moveDto.getOrderqty());
                }
                moveList.add(temp);
            }
        }
        moveParamDto.setSaveList(moveList);
        log.info("▶moveParamDto->{}", moveParamDto);
        moveParamDto.setAvc_COMMAND("BATCHCONFIRM");
        StLocMoveResDto moveResDto;
        if(moveList.size() > 0) {
            try {
                moveResDto = stLocMoveService.saveMasterList(moveParamDto);
                log.info("▶moveResDto->{}", moveResDto);
            }catch(Exception e) {
                e.printStackTrace();
                log.info("판정처리 완료, 재고이동중 에러발생: {}", e);
                throw new UserHandleException("반품판정처리 완료되었습니다. \n"+e.getMessage());
            }
        } else {
            moveResDto = null;
        }
        /* 재고이동 호출 END*/

        /* 매입센터 있으면 STO 호출 START*/
        OmOrderCreationSTOReqDto stoParamDto = ModelMapperUtil.map(paramDto, OmOrderCreationSTOReqDto.class);
        stoParamDto.setAvc_COMMAND("CONFIRM");
        stoParamDto.setDELIVERYDATE(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        List<OmOrderCreationSTOResDto> stoList = new ArrayList<>();
        for(RtQCConfirmResDto stoDto : paramDto.getSaveList()){
            if("RETURNOUT".equals(stoDto.getProcesstype()) && StringUtils.isNotEmpty(stoDto.getStoDccode())) {
                //루트dto
                stoParamDto.setDcA(stoDto.getDccode());
                stoParamDto.setDC_B(stoDto.getStoDccode());
                stoParamDto.setSTOTYPE("RSTO"); //반품STO
                stoParamDto.setSTOCKTYPE("BAD"); //반품STO

                //목록
                stoParamDto.setFixdccode(stoDto.getDccode());
                stoParamDto.setMULTI_SKU(stoDto.getSku());
                stoParamDto.setFROMLOC(stoDto.getLoc());
                stoParamDto.setTOLOC(stoDto.getLoc());
                stoParamDto.setSTOCKGRADE(stoDto.getStockgrade());
                OmOrderCreationSTOResDto temp = omOrderCreationSTOService.getMasterList(stoParamDto).get(0);

                if("BOX".equals(stoDto.getStoreruom())){
                    temp.setToOrderqty((stoDto.getOrderqty()).multiply((stoDto.getQtyperbox())));
                }else {
                    temp.setToOrderqty((stoDto.getOrderqty()));
                }
                temp.setToOrderqty((stoDto.getOrderqty()));
                temp.setToDccode(stoDto.getStoDccode());
                temp.setToOrganize(stoDto.getStoDccode());
                stoList.add(temp);
            }
        }
        stoParamDto.setSaveList(stoList);
        log.info("▶stoParamDto->{}", stoParamDto);
        List<OmOrderCreationSTOResDto> stoResDto;
        if(stoList.size() > 0) {
            try {
                stoResDto = omOrderCreationSTOService.saveMasterList(stoParamDto);
            }catch(Exception e) {
                e.printStackTrace();
                log.info("판정처리 완료, STO중 에러발생: {}", e);
                throw new UserHandleException("반품판정처리 완료되었습니다. \n"+e.getMessage());
            }
            log.info("▶stoParamDto->{}", stoResDto);
        } else {
            stoResDto = null;
        }
        /* 매입센터 있으면 STO 호출 END*/


        return new HashMap<>(){{
			put("move", moveResDto);
			put("sto", stoResDto);
		}};
    }



    /**
     * @description : 입고확정처리 대상확정
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public String deleteMasterList(RtQCConfirmReqDto paramDto) throws Exception {
        RtQCConfirmResDto dto = paramDto.getSaveList().get(0);
        commonDao.insert(SERVICEID_PREFIX + "insMgDatamodifylog", dto);
        commonDao.update(SERVICEID_PREFIX + "updateDelRtReceiptqc", dto);
        return CanalFrameConstants.MSG_COM_DEL_CODE;
    }
}
