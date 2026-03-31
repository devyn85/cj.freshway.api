package cjfw.wms.ms.service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.wms.cm.dto.CmCodeResDto;
import cjfw.wms.ms.dto.*;
import cjfw.wms.ms.service.district.DistrictProcessorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsCenterDistrictService {

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     *
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "msCenterDistrictService.";
    private final DistrictProcessorFactory<MsCenterDistrictHjdongReqDto> districtProcessorFactory;
    private final CommonDao commonDao;
    private final UserContext userContext;

    /**
     * @description : 센터별 행정동 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public List<MsCenterDistrictHjdongResDto> getMasterList(MsCenterDistrictReqDto dto) {
        if (!dto.getDccode().contains(",")) {
            CmCodeResDto resDto = commonDao.selectOne(SERVICEID_PREFIX + "getDcType", dto);
            if (resDto == null) {
                throw new UserHandleException("MSG.COM.ERR.999", new String[] { "센터 구분 정보가없습니다." });
            }
        }
        return commonDao.selectList(SERVICEID_PREFIX + "getCenterDistrictPolygonList", dto);
    }

    /**
     * @description : 행정동 데이터 검증 (신규건 포함)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.18 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public List<MsCenterDistrictValidationResDto> getHjdongValidationList(List<MsCenterDistrictHjdongReqDto> dtoList) {
        MsCenterDistrictValidationReqDto validationRequestDto = new MsCenterDistrictValidationReqDto();
        List<MsCenterDistrictValidationResDto> resultList = new ArrayList<>();
        dtoList.forEach(dto -> {
            if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
                validationRequestDto.addHjdong(dto);
                validationRequestDto.addKey(dto.getSerialkey());
            } else if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
                validationRequestDto.addHjdong(dto);
            }
        });

        if (validationRequestDto.getDistrictHjdongList() != null) {
            resultList = commonDao.selectList(SERVICEID_PREFIX + "getValidateInsertAndNew", validationRequestDto);
            resultList.forEach(res -> {
                res.setErrorMessage(
                        MessageUtil.getMessage("MSG.COM.VAL.067",
                                new String[]{
                                        "행정동 " +
                                                res.getHjdongNm() +
                                                " (" +
                                                res.getDcnameA() +
                                                ") " +
                                                res.getFromA() +
                                                " ~ " +
                                                res.getToA() +
                                                " (" +
                                                res.getDcnameB() +
                                                ") " +
                                                res.getFromB() +
                                                " ~ " +
                                                res.getToB()
                                }
                        )
                );
            });
        }

        return resultList;
    }


    /**
     * @description : 센터별 폴리곤 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public List<MsCenterDistrictResDto> getCenterDistrictPolyglon(MsCenterDistrictReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getCenterDistrictPolyglon", dto);
    }

    public String saveMasterList(List<MsCenterDistrictHjdongReqDto> dtoList) {
        return districtProcessorFactory.saveMasterList(dtoList);
    }

    /**
     * @description : 검증 데이터 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public List<MsCenterDistrictHjdongResDto> getMasterListValidation(MsCenterDistrictHjdongReqDto dto) {
        List<MsDistrictPolygonResDto> childList = commonDao.selectList(SERVICEID_PREFIX + "getMasterChildHjdongList", dto);
        List<MsCenterDistrictHjdongReqDto> childValidationDtoList = childList.stream()
                .map(child -> {
                    MsCenterDistrictHjdongReqDto copyDto = new MsCenterDistrictHjdongReqDto();
                    copyDto.setDccode(dto.getDccode());
                    copyDto.setFromDate(dto.getFromDate());
                    copyDto.setToDate(dto.getToDate());
                    copyDto.setHjdongNm(child.getHjdongNm());
                    copyDto.setHjdongCd(child.getHjdongCd());
                    copyDto.setRowStatus(dto.getRowStatus());
                    return copyDto;
                }).toList();

        return getMasterValidation(childValidationDtoList);
    }


    /**
     * @description : 센터별 입력 행정동 검증
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public List<MsCenterDistrictHjdongResDto> getMasterValidation(List<MsCenterDistrictHjdongReqDto> dtoList) {

        List<MsCenterDistrictHjdongResDto> resultList = new ArrayList<>();

        for (MsCenterDistrictHjdongReqDto dto : dtoList) {
            MsCenterDistrictHjdongResDto msCenterDistrictHjdongResDto = new MsCenterDistrictHjdongResDto();
            msCenterDistrictHjdongResDto.setDccode(dto.getDccode());
            msCenterDistrictHjdongResDto.setHjdongCd(dto.getHjdongCd());
            msCenterDistrictHjdongResDto.setHjdongNm(dto.getHjdongNm());

            if (CanalFrameConstants.INSERT.equals(dto.getRowStatus())) {
                List<MsDistrictPolygonResDto> masterValidationCheck = commonDao.selectList(SERVICEID_PREFIX + "getValidationMasterExist", dto);
                if (masterValidationCheck == null || masterValidationCheck.isEmpty()) {
                    msCenterDistrictHjdongResDto.addMessage(MessageUtil.getMessage("MSG.COM.VAL.121"));
                    resultList.add(msCenterDistrictHjdongResDto);
                } else {
                    List<MsCenterDistrictHjdongResDto> validationResultList = commonDao.selectList(SERVICEID_PREFIX + "getValidationInsertData", dto);
                    if (validationResultList != null && !validationResultList.isEmpty()) {
                        msCenterDistrictHjdongResDto.setHjdongNm(masterValidationCheck.get(0).getHjdongNm());
                        for (MsCenterDistrictHjdongResDto validationResult : validationResultList) {
                            msCenterDistrictHjdongResDto.addMessage(MessageUtil.getMessage(
                                            "MSG.COM.VAL.067",
                                            new String[]{String.format(
                                                    "행정동 %s (%s) [%s ~ %s]",
                                                    validationResult.getHjdongNm(),
                                                    validationResult.getDcname(),
                                                    validationResult.getFromDate(),
                                                    validationResult.getToDate())
                                            }
                                    )
                            );
                        }
                    }
                    resultList.add(msCenterDistrictHjdongResDto);
                }
            } else if (CanalFrameConstants.UPDATE.equals(dto.getRowStatus())) {
                List<MsDistrictPolygonResDto> masterValidationCheck = commonDao.selectList(SERVICEID_PREFIX + "getValidationMasterExist", dto);
                List<MsCenterDistrictHjdongResDto> validationResultList = commonDao.selectList(SERVICEID_PREFIX + "getValidationUpdateData", dto);
                if (validationResultList != null && !validationResultList.isEmpty()) {
                    msCenterDistrictHjdongResDto.setHjdongNm(masterValidationCheck.get(0).getHjdongNm());
                    for (MsCenterDistrictHjdongResDto validationResult : validationResultList) {
                        msCenterDistrictHjdongResDto.addMessage(MessageUtil.getMessage(
                                        "MSG.COM.VAL.067",
                                        new String[]{String.format(
                                                "행정동 %s (%s) [%s ~ %s]",
                                                validationResult.getHjdongNm(),
                                                validationResult.getDcname(),
                                                validationResult.getFromDate(),
                                                validationResult.getToDate())
                                        }
                                )
                        );
                    }
                }
                resultList.add(msCenterDistrictHjdongResDto);
            }
        }
        return resultList;
    }

    /**
     * @description : 센터 권역 신규 행정동 목록
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.17 LeeHyunsung (zoot0134@cj.net) 생성 </pre>
     */
    public List<MsCenterDistrictNewHjdongResDto> getNewHjdongList(MsCenterDistrictReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getNewHjdongList", dto);
    }

    public List<MsCenterDistrictNewHjdongResDto> getNewCreatedHjdongWithoutPolygon() {
        return commonDao.selectList(SERVICEID_PREFIX + "getNewCreatedHjdongWithoutPolygon");
    }

    public Map<String, String> getTodateChildImpact(List<MsCenterDistrictHjdongReqDto> dtoList) {
        for (MsCenterDistrictHjdongReqDto dto : dtoList) {
            int count = commonDao.selectOne(SERVICEID_PREFIX + "countHjdongChildBeyondTodate", dto);
            if (count > 0) {
                return Map.of("affectedYn", "Y");
            }
        }
        return Map.of("affectedYn", "N");
    }
}
