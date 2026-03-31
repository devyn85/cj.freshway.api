package cjfw.wms.ms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsExDcRateCheckRateAvgReqDto;
import cjfw.wms.ms.dto.MsExDcRateCheckRateAvgResDto;
import cjfw.wms.ms.dto.MsExDcRateExcelCheckReqDto;
import cjfw.wms.ms.dto.MsExDcRateExcelCheckResDto;
import cjfw.wms.ms.dto.MsExDcRateGetSkuDataSelectReqDto;
import cjfw.wms.ms.dto.MsExDcRateGetSkuDataSelectResDto;
import cjfw.wms.ms.dto.MsExDcRateGetSkuSpecReqDto;
import cjfw.wms.ms.dto.MsExDcRateGetSkuSpecResDto;
import cjfw.wms.ms.dto.MsExDcRateReqDto;
import cjfw.wms.ms.dto.MsExDcRateResDto;
import cjfw.wms.ms.dto.MsExDcRateSaveReqDto;
import cjfw.wms.ms.entity.MsExDcRateEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.06.04 
 * @description : 외부창고요율관리 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsExDcRateService {

    
    private transient static final String SERVICEID_PREFIX = "msExDcRateService.";
    private final CommonDao commonDao;
    private final UserContext userContext;

    /**
     * 
     * @description : 외부창고요율관리 목록 조회
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR       MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.05.29 ParkJinWoo   생성
     */
    public List<MsExDcRateResDto> getExDcRateList(MsExDcRateReqDto msExDcRateReqDto){
        return commonDao.selectList(SERVICEID_PREFIX + "getExDcRateList", msExDcRateReqDto);
    }

    /**
     * 
     * @description : 외부창고요율관리 저장
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR       MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.04 ParkJinWoo   생성r
     */
    public String saveConfirm(List<MsExDcRateSaveReqDto> saveList) {
    	//시리얼키를 기준으로 insert,update구분
        for (MsExDcRateSaveReqDto dto : saveList) {
        	
            MsExDcRateEntity entity = ModelMapperUtil.map(dto, userContext, MsExDcRateEntity.class);
            if(dto.getState().equals("removed")) {
        		List<MsExDcRateSaveReqDto> deleteList = new ArrayList<>();
        		deleteList.add(dto);
        		deleteConfirm(deleteList);
        		
        	}
           else  if (dto.getSerialKey() == null || dto.getSerialKey().isEmpty()) {
               String serialKey = commonDao.selectOne(SERVICEID_PREFIX + "getMsExDCRateSerialKey").toString();
               dto.setSerialKey(serialKey);
               entity.setExDcRateRank("10");
               entity.setSerialKey(serialKey);
               
               if (commonDao.insert(SERVICEID_PREFIX + "insert", entity) == 0) {
                   throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
               }
               commonDao.insert(SERVICEID_PREFIX + "insertIf", entity);
           } 
            else {
            	if(commonDao.selectList(SERVICEID_PREFIX+"updateChk",entity).size()>0) {
            	 	throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
            	}
                if (commonDao.insert(SERVICEID_PREFIX + "insertIfDel", entity) == 0)
                    throw new UserHandleException("IF_MS_EXDCRATE Insert 중 에러 발생[데이터를 찾을 수 없습니다]");
                List<MsExDcRateResDto> list = commonDao.selectList(SERVICEID_PREFIX+"getUpdateCheck",entity);
                if(list.size() >0)
                	throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
                if (commonDao.update(SERVICEID_PREFIX + "update", entity) == 0)
                    throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
                commonDao.insert(SERVICEID_PREFIX + "insertIf", entity);
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * 
     * @description : 외부창고요율관리 평균치 검증
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR       MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.04 ParkJinWoo   생성
     */
    public List<MsExDcRateCheckRateAvgResDto> checkRateAvg(List<MsExDcRateCheckRateAvgReqDto> reqList) {
    	List<MsExDcRateCheckRateAvgResDto> reuslt = new ArrayList<>();
    	
        for (MsExDcRateCheckRateAvgReqDto dto : reqList) {
//            int exists = commonDao.selectOne(SERVICEID_PREFIX + "checkRateAvg", dto);
        	MsExDcRateCheckRateAvgResDto res = commonDao.selectOne(SERVICEID_PREFIX + "checkRateAvg", dto);
        	reuslt.add(res);
//            if (exists == 0)
//                throw new UserHandleException("요율 평균치에서 벗어납니다.");
        }
        return reuslt;	
    }

    /**
     * 
     * @description : 외부창고요율관리 삭제 처리
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR       MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.04 ParkJinWoo   생성
     */
    public String deleteConfirm(List<MsExDcRateSaveReqDto> deleteList) {
    	//1. 삭제를 원하는 리스트의 시리얼 번호를 리스트로 변환
    	String serialKeyList = "";
    	serialKeyList = deleteList.stream()
    	.map(dto -> dto.getSerialKey()).collect(Collectors.joining(","));
    	
    	//2.해당 시리얼 번호를 가지고 있는 데이터만 추출(삭제 여부,수정인,수정시간만 업데이트 하기 위함)
    	MsExDcRateSaveReqDto param = new MsExDcRateSaveReqDto();
    	param.setSerialKey(serialKeyList);
    	List<MsExDcRateSaveReqDto> delList= commonDao.selectList(SERVICEID_PREFIX + "getExDcRateDeleteList",param);
    	if(delList.size()>0) {
        for (MsExDcRateSaveReqDto dto : delList) {
            MsExDcRateEntity entity = ModelMapperUtil.map(dto, userContext, MsExDcRateEntity.class);
            if (commonDao.insert(SERVICEID_PREFIX + "insertIfDel", entity) == 0)
                throw new UserHandleException("IF_MS_EXDCRATE Insert 중 에러 발생[데이터를 찾을 수 없습니다]");
            if (commonDao.update(SERVICEID_PREFIX + "update", entity) == 0)
                throw new UserHandleException("MSG.COM.VAL.067", new String[]{"데이터"});
        }
    	}
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * 
     * @description : 엑셀 업로드 데이터 유효성 체크
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR       MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.04 ParkJinWoo   생성
     */
    public List<MsExDcRateExcelCheckResDto> getExcelCheck(MsExDcRateExcelCheckReqDto list) {
        List<MsExDcRateExcelCheckResDto> result = new ArrayList<>();
        List<MsExDcRateExcelCheckResDto> chkList = list.getExcelList();
          if (list == null || list.getExcelList() == null || list.getExcelList().isEmpty()) {
            return result;
        }
        if (list != null) {
            for (MsExDcRateExcelCheckResDto excel : chkList) {
                String strErr = "";
                try {
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                    String from = excel.getFromDate().replace("-", "");
                    String to = excel.getToDate().replace("-", "");
                    df.parse(from);
                    df.parse(to);
                    if(excel.getAreaPriceUom().equals("PLT")) {
                    	excel.setAreaPriceUom("PAL");
                    }
                    if (from.compareTo(to) > 0)
                        strErr = "종료일자보다 시작일자가 더 큽니다.";
                    excel.setFromDate(from);
                    excel.setToDate(to);
                } catch (Exception e) {
                    strErr = "날짜 포맷 오류";
                }

                if (!strErr.isEmpty()) {
                	excel.setUploadFlag("E");
                	excel.setUploadMsg(strErr);
                	excel.setCheckYn("0");
                }

                MsExDcRateExcelCheckResDto dbResult = commonDao.selectOne(SERVICEID_PREFIX + "getExcelCheck", excel);
                if (dbResult.getErrMsg() == null || dbResult.getErrMsg().isEmpty()) {
                	excel.setUploadFlag("S");
                	excel.setState("edited");
                	excel.setCheckYn("1");
                	excel.setSerialKey(null);
                	excel.setDelYn(dbResult.getDelYn());
                	excel.setOrganizeNm(dbResult.getOrganizeNm());
                	excel.setSku(dbResult.getSku());
                	excel.setSkuName(dbResult.getSkuName());
                	excel.setCustname(dbResult.getCustname());
                	excel.setCustKey(dbResult.getCustKey());
                	excel.setSpecCode(dbResult.getSpecCode());
                	excel.setExpenseType(dbResult.getExpenseType());
                	excel.setDelYn(dbResult.getDelYn());
                	excel.setSkuName(dbResult.getSkuName());
                	excel.setStorageTypeSku(dbResult.getStorageTypeSku());
                	excel.setBaseUom(dbResult.getBaseUom());
                	excel.setQtyPerBox(dbResult.getQtyPerBox());
                	excel.setNetWeight(dbResult.getNetWeight());
                	excel.setAddDate("");
                	excel.setAddWho("");
                	excel.setEditDate("");
                	excel.setEditWho("");
                    if (excel.getSku() != null && !excel.getSku().isEmpty())
                    	excel.setStorageType("00");
                } else {
                	excel.setUploadFlag("E");
                	excel.setDelYn(dbResult.getDelYn());
                	excel.setUploadMsg(dbResult.getErrMsg());
	                //에러여도 기본값은 보여줘야함
                	excel.setBaseUom(dbResult.getBaseUom());
                	excel.setDelYn(dbResult.getDelYn());
                	excel.setOrganizeNm(dbResult.getOrganizeNm());
                	excel.setSkuName(dbResult.getSkuName());
                	excel.setStorageTypeSku(dbResult.getStorageTypeSku());
                	excel.setQtyPerBox(dbResult.getQtyPerBox());
                	excel.setNetWeight(dbResult.getNetWeight());
                	excel.setSku(dbResult.getSku());
                	excel.setCustname(dbResult.getCustname());
                	excel.setCustKey(dbResult.getCustKey());
                	excel.setSpecCode(dbResult.getSpecCode());
                	excel.setExpenseType(dbResult.getExpenseType());
                	excel.setDelYn(dbResult.getDelYn());
                	excel.setCheckYn("0");
                }
                result.add(excel);
            }
//            MsExDcRateExcelCheckReqDto param = new MsExDcRateExcelCheckReqDto();
//            param.setExcelList(result);
//            result = commonDao.selectList(SERVICEID_PREFIX + "dulChkAll", param);
            
        }
        return result;
    }

    /**
     * 
     * @description : 상품 대분류 목록 조회
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR       MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.04 ParkJinWoo   생성
     */
    public List<MsExDcRateGetSkuSpecResDto> getSkuSpecForMsExDcRate(MsExDcRateGetSkuSpecReqDto reqDto){
        return commonDao.selectList(SERVICEID_PREFIX + "getSkuSpecForMsExDcRate", reqDto);
    }

    /**
     * 
     * @description : 상품 데이터 조회
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR       MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.04 ParkJinWoo   생성
     */
    public MsExDcRateGetSkuDataSelectResDto getDataSelectSkuForMsExDcRate(MsExDcRateGetSkuDataSelectReqDto reqDto) {
        return commonDao.selectOne(SERVICEID_PREFIX + "MsExDcRateGetSkuDataSelect", reqDto);
    }


}
