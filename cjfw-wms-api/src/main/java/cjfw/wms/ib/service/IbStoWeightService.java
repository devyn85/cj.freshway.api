package cjfw.wms.ib.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.ib.dto.IbStoWeightReqDto;
import cjfw.wms.ib.dto.IbStoWeightResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.10.24
 * @description :센터별물동량  Service Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IbStoWeightService {
    private final UserContext userContext;
    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private transient static final String PAKAGE_NAME = "SPMS_REPORTKEY";
    private transient static final String TEMPTABLETYPE = "TM";
    private transient static final String PROCESSTYPE = "";

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = StringUtils.uncapitalize(IbStoWeightService.class.getSimpleName()) + ".";

    /**
     * @description : 센터별물동량 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R> List<R> getMasterList(IbStoWeightReqDto reqDto) {
        switch (reqDto.getActiveKey()) {
            case "1":
                reqDto.setFlowType("STO");
                 break;
            case "2":
                reqDto.setFlowType("SO");
                 break;
            case "3":
            default:
                reqDto.setFlowType("DP");
        }
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
    }

    /**
     * @description : 센터별물동량
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    @Transactional
    public String saveBatchMasterList(IbStoWeightReqDto reqDto) {
        List<IbStoWeightResDto> list = null;
        reqDto.setEditwho(reqDto.getGUserId());
        switch (reqDto.getActiveKey()) {
            case "1":
                reqDto.setFlowType("STO");
                commonDao.delete(SERVICEID_PREFIX + "deleteBatch", reqDto);
                commonDao.insert(SERVICEID_PREFIX + "insertBatchSto", reqDto);
                break;
            case "2":
                reqDto.setFlowType("SO");
                commonDao.delete(SERVICEID_PREFIX + "deleteBatch", reqDto);
                commonDao.insert(SERVICEID_PREFIX + "insertBatchSo", reqDto);
                break;
            case "3":
            default:
                reqDto.setFlowType("DP");
                commonDao.delete(SERVICEID_PREFIX + "deleteBatch", reqDto);
                commonDao.insert(SERVICEID_PREFIX + "insertBatchDp", reqDto);
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 센터별물동량 조회 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public <R, T> List<R> getMasterList2(T reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList2", reqDto);
    }

    /**
     * @description : 센터별물동량 전월복사 Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    public String copyMasterList(IbStoWeightReqDto paramDto) throws Exception {
        commonDao.delete(SERVICEID_PREFIX + "deleteMasterList", paramDto);
        commonDao.insert(SERVICEID_PREFIX + "copyMasterList", paramDto);
        return CanalFrameConstants.MSG_COM_DEL_CODE;
    }


    /**
     * @description : 센터별물동량 저장 Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    public String saveMasterList(IbStoWeightReqDto paramDto) throws Exception {
        //삭제리스트
        for(IbStoWeightResDto delDto : paramDto.getDeleteList()) {
            delDto.setGStorerkey(paramDto.getGStorerkey());
            delDto.setMasterkey(paramDto.getMasterkey());
            commonDao.delete(SERVICEID_PREFIX + "deleteMaster", delDto);
        }
        if(paramDto.getSaveList() == null || paramDto.getSaveList().size() == 0) {
            return CanalFrameConstants.MSG_COM_SUC_CODE;
        }
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage = "";

        // 파라미터 위변조 적용(paramDto->reqDto)
        IbStoWeightReqDto reqDto = ModelMapperUtil.map(paramDto, IbStoWeightReqDto.class);
        List<IbStoWeightResDto> saveList = reqDto.getSaveList(); // 저장리스트

        /*START.Temp Table Insert*/
        // 임시테이블 삭제(1/3)
        commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto);

        int chunkSize = 200;
        List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
        for (int i = 0; i < saveList.size(); i++) {
            IbStoWeightResDto dto = saveList.get(i);
            // 임시테이블에 등록(2/3)
            CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
            entity.setProcesstype(reqDto.getProcesstype()); // 프로세스타입
            entity.setTemptabletype(TEMPTABLETYPE); //임시테이블타입
            entity.setStorerkey(reqDto.getGStorerkey());
            entity.setDeliverygroup(reqDto.getMasterkey());
            entity.setDccode(StringUtil.isEmpty(dto.getDccode()) ? "STD" : dto.getDccode());
            
            // 20260208@저장품은 STD로 고정 박*병님 요청 by sss
            if("STO_DC".equals(reqDto.getMasterkey())||"SO_DC".equals(reqDto.getMasterkey())||"DP_DC".equals(reqDto.getMasterkey())  ) {
            	entity.setOther01("STD");
			} else {
				entity.setOther01(dto.getYyyymm());
			}
            
            entity.setOther02(dto.getFilterkey());
            entity.setOther03(dto.getOrgYyyymm());
            entity.setOther04(dto.getOrgDccode());
            entity.setOther05(dto.getOrgFilterkey());
            // UI.params

            // START.필수입력 check - 그리드 변수 등
            if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode()))         ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
            if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOther02()))     ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"filterkey"}));
            // END.필수입력 check
            insertList.add(entity);

            // 200개마다 혹은 마지막 루프일 때 insert(3/3)
            if (insertList.size() == chunkSize || i == saveList.size() -1) {
                commonDao.insert(SERVICEID_PREFIX + "insertTemp", insertList);
                insertList.clear();
            }
        }
        /*END.Temp Table Insert*/

        // PKG 파라마터 세팅 - 공통(1/4)
        // PKG 파라마터 세팅 - 공통(1/4)
        IbStoWeightReqDto dto = new IbStoWeightReqDto();
        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);

        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList = {"PROCEDURE", "PROCESSTYPE"};
        Object[] valueList = {PAKAGE_NAME,
            reqDto.getProcesstype()
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
            log.error("▶저장시 오류 발생 ");
            throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"저장"}) + resultMessage); // {0} 처리 시 문제가 발생했습니다.
        }
        /*END.PAKAGE 호출*/


        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }


    /**
     * @description : 센터별물동량 유효성 List Method
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    public List<IbStoWeightResDto> getExcelValChk(IbStoWeightReqDto reqDto) {
        List<IbStoWeightResDto> resultList = reqDto.getSaveList();
        for(IbStoWeightResDto dto : resultList) {
            dto.setStorerkey(reqDto.getGStorerkey());
            IbStoWeightResDto resDto = null;
            if(StringUtils.isNotEmpty(dto.getDccode())) {
                resDto = commonDao.selectOne(SERVICEID_PREFIX + "getDccode", dto);
                dto.setDcname(Objects.isNull(resDto) ? "" : resDto.getDcname());
            }
            switch (reqDto.getCheckType()) {
                case "dccode":
                    dto.setDccode(dto.getToDccode());
                    resDto = commonDao.selectOne(SERVICEID_PREFIX + "getDccode", dto);
                    dto.setTodcname(Objects.isNull(resDto) ? "" : resDto.getDcname());
                    break;
                case "channel":
                    resDto = commonDao.selectOne(SERVICEID_PREFIX + "getChannel", dto);
                    dto.setFilterkey(Objects.isNull(resDto) ? "" : resDto.getFilterkey());
                    break;
                case "sku":
                    resDto = commonDao.selectOne(SERVICEID_PREFIX + "getSku", dto);
                    dto.setSkuname(Objects.isNull(resDto) ? "" : resDto.getSkuname());
                    break;
                case "cust":
                    resDto = commonDao.selectOne(SERVICEID_PREFIX + "getCust", dto);
                    dto.setCustname(Objects.isNull(resDto) ? "" : resDto.getCustname());
                    break;
                case "partner":
                    resDto = commonDao.selectOne(SERVICEID_PREFIX + "getPartner", dto);
                    dto.setPartnrname(Objects.isNull(resDto) ? "" : resDto.getPartnrname());
                    break;
                default:
                    resDto = null;
            }
        }
        return resultList;
    }
}
