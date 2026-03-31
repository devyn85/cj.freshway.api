package cjfw.wms.st.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.edms.EdmsFileDownloader;
import cjfw.core.edms.EdmsFileUploader;
import cjfw.core.exception.UserHandleException;
import cjfw.core.file.FileDownload;
import cjfw.core.file.FileDownloader;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderEdms;
import cjfw.core.file.FileUploaderNew;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.dto.StTplIssueReqPopupResDpDto;
import cjfw.wms.st.dto.StTplIssueReqPopupResDto;
import cjfw.wms.st.dto.StTplIssueReqReqDto;
import cjfw.wms.st.dto.StTplIssueReqResDto;
import cjfw.wms.st.dto.StTplReceiptReqUploadFileResDto;
import cjfw.wms.st.entity.StTplIssueReqEntity;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.11.27 
 * @description : 위탁출고요청 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.27 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StTplIssueReqService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "StTplIssueReqService.";
	private transient static final String SERVICEID_PREFIX1 = "stTplReceiptReqService.";
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	private final FileUploaderEdms fileUploaderEdms;
	private final EdmsFileUploader edmsFileUploader;
	private final FileUploaderNew fileUploaderNew;
	private final FileDownloader fileDownloader;
	private final UserContext userContext;
	
	/**
	 * @description : 위탁출고요청 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.03 ParkJinWoo 생성
	 */
	public List<StTplIssueReqResDto> getMasterList(StTplIssueReqReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * @description : 위탁상품팝업(WD) 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.03 ParkJinWoo 생성
	 */
	public Page<StTplIssueReqPopupResDto> getStTplIssueReqPopupData(StTplIssueReqReqDto dto) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getStTplIssueReqPopupWdData", dto, dto);	
	}
	
	/**
	 * @description : 위탁상품팝업(DP) 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.03 ParkJinWoo 생성
	 */
	public Page<StTplIssueReqPopupResDpDto> getStTplIssueReqPopupDpData(StTplIssueReqReqDto dto) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getStTplIssueReqPopupDpData", dto, dto);
	}

	/**
	 * @description : 위탁출고요청 저장 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.03 ParkJinWoo 생성
	 */
	public String saveList(StTplIssueReqReqDto dto) {
		String resultMessage = "";
		int listSize = 0;
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		List<StTplIssueReqReqDto> docList = new ArrayList<>();
		List<StTplIssueReqResDto> saveList= dto.getSaveList();
		
		if (saveList == null || saveList.isEmpty()) {
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"DOCUMENT 생성중"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}

		// 1) tplBcnrId 별 그룹핑 (순서 유지)
		Map<String, List<StTplIssueReqResDto>> grouped = saveList.stream()
		    .collect(Collectors.groupingBy(StTplIssueReqResDto::getTplBcnrId, LinkedHashMap::new, Collectors.toList()));

		for (Map.Entry<String, List<StTplIssueReqResDto>> entry : grouped.entrySet()) {
			StTplIssueReqReqDto tempReq = new StTplIssueReqReqDto();
		    String tplBcnrId = entry.getKey();
		    List<StTplIssueReqResDto> rows = entry.getValue();

		    // 2) 그룹 공통 기본값 세팅 + ORGANIZE 정제
		    for (StTplIssueReqResDto r : rows) {
		        r.setIfId("ID231");             // H/D 공통(필요시 D에서 바꿔치기)
		        r.setTplType("020");
		        r.setDocType("WD");
		        r.setOrderType("ZTP");
		        r.setDocdt(today);
		        r.setSlipDt(today);
//		        r.setFromCustKey("2170");
		        r.setTplBcnrId(tplBcnrId);
		        String org = r.getOrganize();
		        int i = (org == null) ? -1 : org.lastIndexOf('-');
		        r.setOrganize((i >= 0) ? org.substring(i + 1) : org);
		    }
		    
		    // 3) DOCNO는 그룹당 1번만 발번
		    
		    String docNo = "";

		    // 4) H INSERT (그룹 대표 1건으로 생성)
		    StTplIssueReqResDto headerSrc = rows.get(0);
		    if (headerSrc.getDocNo() == null) {
		    	StTplIssueReqResDto num = commonDao.selectOne(SERVICEID_PREFIX + "getDocNo", dto);
		    	docNo = num.getDocNo();
		    } else {
		    	docNo = headerSrc.getDocNo();
		    }
		    headerSrc.setDocNo(docNo);
		    // H 전용 IF_ID 필요하면 여기서 세팅
		    headerSrc.setIfId("ID231");
		    tempReq.setDocNo(docNo);
		    
		    docList.add(tempReq);
		    StTplIssueReqEntity hEntity = ModelMapperUtil.map(headerSrc, userContext, StTplIssueReqEntity.class);
		    commonDao.insert(SERVICEID_PREFIX + "insertDmDocumentH", hEntity);

		    // 5) D/Serialinfo INSERT (그룹의 모든 로우)
		    DecimalFormat df = new DecimalFormat("00000");
		    int line = 10; // 00010부터 시작

		    for (StTplIssueReqResDto d : rows) {
		        // 공통 필드 셋
		        d.setDocNo(docNo);
		        d.setDocLine(df.format(line)); // "00010", "00020", ...
		        if (StringUtils.isBlank(d.getSerialNo())) {
		            d.setSerialNo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
		        }
		        // D INSERT
		        StTplIssueReqEntity dEntity = ModelMapperUtil.map(d, userContext, StTplIssueReqEntity.class);
		        commonDao.insert(SERVICEID_PREFIX + "insertDmDocumentD", dEntity);

		        // Serialinfo INSERT (IF_ID 변경)
		        dEntity.setIfId("ID230");
		        commonDao.insert(SERVICEID_PREFIX + "insertIfStStockSerialinfoR", dEntity);
		        
		        line += 10;
		        listSize++;
		    }
		}
		
		if (listSize > 0) {
			// IF 프로시저 실행
	        Map<String, String> paramSetterMap = new HashMap<>();
	        paramSetterMap.put("AVC_IFID", "ID231");
	        paramSetterMap.put("AVC_IFMESSAGE", "");
	        paramSetterMap.put("AVC_IFRESULT", "");
	        commonDao.selectOne(SERVICEID_PREFIX + "saveDmDocument", paramSetterMap);
	        
	        if ( paramSetterMap.get("AVC_IFRESULT").equals("S")) {
		        paramSetterMap.put("AVC_IFID", "ID230");
		        paramSetterMap.put("AVC_IFMESSAGE", "");
		        paramSetterMap.put("AVC_IFRESULT", "");
		        commonDao.selectOne(SERVICEID_PREFIX + "saveSerialInfo", paramSetterMap);

//        		log.info("▶DOCUMENT 첨부파일 확정 처리 시작 ");
	        } else {
	        	log.error("▶DOCUMENT 생성중 오류 발생 ");
	        	throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"DOCUMENT 생성중"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
	        }
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 첨부파일 목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.27 ParkJinWoo 생성
	 */
	public List<StTplReceiptReqUploadFileResDto> getUploadFileList(StTplIssueReqReqDto dto) {
		List<StTplReceiptReqUploadFileResDto> result = commonDao.selectList(SERVICEID_PREFIX1 + "getUploadFileList", dto);
   	
		return result;
	}
	
	
	/**
	 * @description : 파일 업로드 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.27 ParkJinWoo 생성
	 */
    public String saveStTplReceiptReqFileUpload(List<MultipartFile> files, List<FileUpload> fileInfoList) {
		log.info("==================== files check {}", files.toString());
		log.info("==================== fileInfoList check {}", fileInfoList.toString());
		// NAS 파일 업로드
		List<FileUpload> fileUploadList = fileUploaderEdms.saveFiles(files, fileInfoList);		
		
		// EDMS 파일 업로드
		List<Map<String, Object>> rFileList = edmsFileUploader.batchFileRegister(fileUploadList, "1198");
		
		// TO-DO : 파일정보 업무 TABLE에 MERGE
		for(Map<String, Object> rFile : rFileList) {
			log.info("==================== EDMS 업로드 결과 {}", rFile.toString());
			
			StTplReceiptReqUploadFileResDto uploadDto = new StTplReceiptReqUploadFileResDto();			
			uploadDto.setDocType("100");
			uploadDto.setFileName((String) rFile.get("orgname"));
			uploadDto.setFileExtension(((String) rFile.get("orgname")).substring(((String) rFile.get("orgname")).lastIndexOf(".")+1));
			uploadDto.setFileLocation(ContextUtil.getProperty("cf.edms.tempDir") + "/" + userContext.getUserNo() + "/" + ((String) rFile.get("orgname")));
			uploadDto.setFileSizeBytes(String.valueOf(rFile.get("filesize")));
			uploadDto.setTransFileName((String) rFile.get("orgname"));
			uploadDto.setUploadResDocId(String.valueOf(rFile.get("docfileid")));
			uploadDto.setUploadFileName((String) rFile.get("orgname"));
			uploadDto.setUploadWorkplaceId(ContextUtil.getProperty("cf.edms.workPlaceId"));			
		}
		
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 첨부파일 임시저장(NAS에 저장, STATUS='N'으로 저장) 기능을 구현한 Method  기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.27 ParkJinWoo 생성
     */
    public String saveStTplReceiptReqFileUploadTemp(List<MultipartFile> files, List<FileUpload> fileInfoList, String docNo1, String docType, String uploadLocation1,String status) {
        // 경로 조작 (Java/JSP) 대응
        String uploadLocation = EtcUtil.setFilePathFilter(uploadLocation1);
        String docNo = EtcUtil.setFilePathFilter(docNo1);
    	
    	// 임시저장 전 transFileNm을 명시적으로 설정
        int idx = 0;
        for (FileUpload info : fileInfoList) {
            String src = info.getAttchFileNm();
            String ext = "";
            int dot = src != null ? src.lastIndexOf('.') : -1;
            if (dot > -1) {
                ext = src.substring(dot + 1);
            }
            String trans = "WMS_ST_TPLRECEIPTREQ_" + System.currentTimeMillis() + "_" + idx + (ext.isEmpty() ? "" : "." + ext);
            info.setTransFileNm(trans);
            idx++;
        }

        List<FileUpload> fileUploadList = fileUploaderNew.saveFiles(files, fileInfoList, ContextUtil.getProperty("cf.upload.dir.sttpl") + "/"+docNo);

        for (FileUpload f : fileUploadList) {
        	StTplReceiptReqUploadFileResDto uploadDto = new StTplReceiptReqUploadFileResDto();
            
            uploadDto.setDocType("100");
            uploadDto.setFileName(f.getAttchFileNm());
            String nm = f.getAttchFileNm();
            String ext = nm != null && nm.lastIndexOf('.') > -1 ? nm.substring(nm.lastIndexOf('.') + 1) : "";
            uploadDto.setFileExtension(ext);
            uploadDto.setFileLocation(f.getSavePathNm1() + "/" + f.getTransFileNm());
            uploadDto.setFileSizeBytes(String.valueOf(f.getAttchFileSz()));
            uploadDto.setTransFileName(f.getTransFileNm());
            // hashId/edms 값은 확정 시 세팅

            Map<String, Object> param = new HashMap<>();
            param.put("docNo", docNo);
            param.put("docType", uploadDto.getDocType());
            param.put("fileName", uploadDto.getFileName());
            param.put("fileExtension", uploadDto.getFileExtension());
            param.put("fileLocation", uploadDto.getFileLocation());
            param.put("fileSizeBytes", uploadDto.getFileSizeBytes());
            param.put("transFileName", uploadDto.getTransFileName());
            param.put("docType", docType);
            param.put("uploadLocation", uploadLocation);
            // 사용자 컨텍스트 바인딩
            param.put("gUserNo", userContext.getUserNo());
            param.put("gUserId", userContext.getUserId());
            // INSERT (STATUS='N' 기본)
            commonDao.insert(SERVICEID_PREFIX1 + "insertTplReceiptReqFile", param);
        }
        if(status.equals("N")) {
        	StTplIssueReqReqDto r = new StTplIssueReqReqDto();
			r.setDocNo(docNo);
		}
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 첨부파일 확정(EDMS 저장, STATUS='Y') 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.27 ParkJinWoo 생성
     */
    public String confirmStTplReceiptReqFileUpload(List<StTplIssueReqReqDto> dto) {
        if (dto == null ) {
            return CanalFrameConstants.MSG_COM_SUC_CODE;
        }
        
        for(StTplIssueReqReqDto r : dto) {
            // 임시 목록 조회
            List<Map> tempList = commonDao.selectList(SERVICEID_PREFIX1 + "selectTplReceiptReqTempFilesBySerialkeys", r);
            for (Object o : tempList) {
                @SuppressWarnings("unchecked") Map<String, Object> row = (Map<String, Object>) o;
                String serialkey = String.valueOf(row.get("SERIALKEY"));
                String fileLocation = String.valueOf(row.get("FILE_LOCATION"));
                String transFileName = String.valueOf(row.get("TRANS_FILE_NAME"));
                
                // EDMS 업로드
                String dirPath = fileLocation;
                if (dirPath != null) {
                    int p = dirPath.lastIndexOf('/') > -1 ? dirPath.lastIndexOf('/') : dirPath.lastIndexOf('\\');
                    if (p > -1) {
                        dirPath = dirPath.substring(0, p);
                    }
                }
                java.util.List<FileUpload> fuList = new java.util.ArrayList<>();
                FileUpload fu = new FileUpload();
                fu.setAttchFileNm(transFileName);
                fu.setSavePathNm1(dirPath);
                fu.setRowStatus("I");
                fuList.add(fu);
                
                java.util.List<java.util.Map<String, Object>> rList = edmsFileUploader.batchFileRegister(fuList, "1198");
                java.util.Map<String, Object> rMap0 = (rList != null && !rList.isEmpty()) ? rList.get(0) : new java.util.HashMap<>();
                String hashId = String.valueOf(rMap0.get("docfileid"));
                String orgname = String.valueOf(rMap0.get("orgname"));
                
                Map<String, Object> param = new HashMap<>();
                param.put("docNo", r.getDocNo());
                param.put("serialkey", serialkey);
                param.put("uploadResDocId", hashId);
                param.put("uploadFileName", orgname);
                param.put("uploadWorkplaceId", ContextUtil.getProperty("cf.edms.workPlaceId"));
                param.put("gUserNo", userContext.getUserNo());
                param.put("gUserId", userContext.getUserId());
                // 필요 시 uploadLocation 생성 가능
                commonDao.update(SERVICEID_PREFIX1 + "updateTplReceiptReqFileConfirm", param);
                
                // EDMS 업로드 완료 후 NAS 임시 파일 삭제
                try {
                    if (fileLocation != null && !fileLocation.isEmpty()) {
                    //  java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(fileLocation));
                    }
                } catch (Exception ignore) {
                    // ignore deletion failure
                }
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    /**
     * @description : 첨부 삭제(DEL_YN='Y') 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.27 ParkJinWoo 생성
     */
    public String deleteStTplReceiptReqFileUpload(StTplIssueReqReqDto dto) {
        // 1) 대상 행 조회 (임시/확정 공통)
        Map<String, Object> param = new HashMap<>();
        param.put("docNo", dto.getDocNo());
        param.put("docType", dto.getDocType());
        param.put("serialkeys", dto.getSerialkeys());
        List<?> rows = commonDao.selectList(SERVICEID_PREFIX1 + "selectTplReceiptReqFilesBySerialkeys", param);

        // 2) STATUS='N' (임시/NAS)인 경우 물리 파일 삭제 시도
        for (Object o : rows) {
            @SuppressWarnings("unchecked") Map<String, Object> r = (Map<String, Object>) o;
            String status = String.valueOf(r.get("STATUS"));
            String fileLocation = String.valueOf(r.get("FILE_LOCATION"));
            if ("N".equalsIgnoreCase(status)) {
                if (fileLocation != null && !fileLocation.isEmpty()) {
                    try {
                        java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(fileLocation));
                    } catch (Exception ignore) {
                    }
                }
            }
        }

        // 3) 메타정보 소프트 삭제 처리 (저장/확정 공통)
        param.put("gUserNo", userContext.getUserNo());
        commonDao.update(SERVICEID_PREFIX1 + "updateTplReceiptReqSoftDeleteConfirmedBySerialkeys", param);
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
	
    /**
     * @description : 파일 다운로드 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.27 ParkJinWoo 생성
     */
	public void stTplReceiptReqFileDownload(HttpServletResponse response, FileDownload fileDownload,String docNo) {
		fileDownloader.downloadFileNew(response,  ContextUtil.getProperty("cf.upload.dir.sttpl") + "/"+docNo + "/" + fileDownload.getSaveFileNm(), fileDownload.getAttchFileNm(), null);
	}
	
    /**
     * @description : 엑셀 업로드 검증 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.27 ParkJinWoo 생성
     */
	public List<StTplIssueReqResDto> getExcelCheck(StTplIssueReqReqDto dto){
        List<StTplIssueReqResDto> result = new ArrayList<>();
        List<StTplIssueReqResDto> chkList = dto.getSaveList();
        
        if (dto == null || chkList == null || chkList.isEmpty()) {
            return result;
        }
        
        if(chkList != null) {
        	for(StTplIssueReqResDto data : chkList) {
        	    data.setTplBcnrId(dto.getTplUser());
        	    data.setFromCustKey(dto.getFromCustkey());
        	    data.setToCustKey(dto.getToCustkey());
        	    data.setDcCode(dto.getDcCode());
        	    data.setOrganize(dto.getOrganize());
//        	    data.setCustKey(dto.getCustkey());
        		StTplIssueReqResDto dbResult = commonDao.selectOne(SERVICEID_PREFIX + "getExcelCheck", data);
        		// 공통 세팅
        	    data.setDocNo(null);
        	    data.setSkuName(dbResult.getSkuName());
        	    data.setBoxQty(dbResult.getBoxQty());
        	    data.setPltQty(dbResult.getPltQty());
        	    data.setAddDate("");
        	    data.setEditDate("");
        	    data.setEditWho("");
        	    data.setAddWho("");
        	    data.setAttachment("0");
        	    data.setRowStatus(dbResult.getRowStatus());
        	    data.setLottable01(dbResult.getLottable01());
        	    data.setDeliveryDate(dbResult.getDeliveryDate());
        	    data.setDeliverytype(dbResult.getDeliverytype());
        	    data.setFromCustKey(dbResult.getFromCustKey());

        	    // ★ 에러메시지 세팅
        	    data.setErrMsg(dbResult.getErrMsg());

        	    if (dbResult.getErrMsg() == null || dbResult.getErrMsg().isEmpty()) {
        	        data.setUploadFlag("S");
        	    } else {
        	        data.setUploadFlag("E");
        	    }

        	    result.add(data);
        	}
        }
        
        return result;
	}
	
	/**
	 * @description : DOCNO생성 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.27 ParkJinWoo 생성
	 */
	public StTplIssueReqResDto getDocNo (StTplIssueReqReqDto dto) {
		StTplIssueReqResDto num = commonDao.selectOne(SERVICEID_PREFIX + "getDocNo", dto);
		return num;
	}
	
}
