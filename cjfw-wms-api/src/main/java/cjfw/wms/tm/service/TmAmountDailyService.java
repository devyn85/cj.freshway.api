package cjfw.wms.tm.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.file.FileDownload;
import cjfw.core.file.FileDownloader;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderNew;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmAmountDailyFilePopupResDto;
import cjfw.wms.tm.dto.TmAmountDailyReqDto;
import cjfw.wms.tm.dto.TmAmountDailyResDto;
import cjfw.wms.tm.entity.TmAmountDailyFilePopupEntity;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.08.01
 * @description : 일자별 수당관리
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmAmountDailyService {

	private transient static final String SERVICEID_PREFIX = "tmAmountDailyService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final FileUploaderNew fileUploaderNew;
	private static final Pattern SAFE_TOKEN = Pattern.compile("^[A-Za-z0-9_\\-]{1,64}$");
	private static final Pattern SAFE_LOCATION = Pattern.compile("^[A-Za-z0-9_\\-]{0,64}$");
	private static final String ATTATCH_DOC_NAME = "TRSP_DAILY";
	private static final int MAX_FILENAME_LENGTH = 255;
	
	private final FileDownloader fileDownloader;

	/**
	 * @description : 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	public List<TmAmountDailyResDto> getMasterList(TmAmountDailyReqDto reqDto) {
		List<TmAmountDailyResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
		return result;
	}
	
	/**
	 * @description : 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	public List<TmAmountDailyResDto> getCarInfo(TmAmountDailyReqDto reqDto) {
		List<TmAmountDailyResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getCarInfo", reqDto);
		return result;
	}

	/**
	 * @description : 마감확인 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.12 ParkJinWoo 생성
	 */
	public List<TmAmountDailyResDto> trspCloseChk(TmAmountDailyReqDto reqDto) {
		if (reqDto == null) {
			return null;
		}
		List<TmAmountDailyResDto> result = new ArrayList<>();
		List<TmAmountDailyResDto> list = reqDto.getSaveList();
		for(TmAmountDailyResDto dto : list) {
			TmAmountDailyResDto chkval = (commonDao.selectOne(SERVICEID_PREFIX + "trspCloseChk", dto));
			result.add(chkval);
		}
		
		return result;
	}
	
	/**
	 * @description : 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	public String saveMasterList(TmAmountDailyReqDto reqDto) {
		List<TmAmountDailyResDto> list = reqDto.getSaveList();

		if (!ObjectUtils.isEmpty(list)) {
			for (TmAmountDailyResDto dto : list) {
				TmAmountDailyResDto entity = ModelMapperUtil.map(dto, userContext, TmAmountDailyResDto.class);
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
				} else if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					commonDao.delete(SERVICEID_PREFIX +"deleteMaster", entity);
				}
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 운송단가 삭제
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.07 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	public String deleteMasterList(TmAmountDailyReqDto reqDto) {
		List<TmAmountDailyResDto> list = reqDto.getSaveList();
		if (!ObjectUtils.isEmpty(list)) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, TmAmountDailyResDto.class);
				commonDao.delete(SERVICEID_PREFIX +"deleteMaster", entity);
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
     * @description : 엑셀 업로드 데이터 검증
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
     */ 
	public List<TmAmountDailyResDto>getExcelValChk(TmAmountDailyReqDto reqDto) {
		List<TmAmountDailyResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getExcelValChk", reqDto.getSaveList());
		return result;
	}
    	
    /**
     * @description : 첨부파일 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2026.03.22    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<TmAmountDailyFilePopupResDto> getFileList(TmAmountDailyReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getPopupUploadFileList", reqDto);
    } 
    
    /**
     * @description : 첨부파일 삭제 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2026.03.22    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String deleteUploadFile(TmAmountDailyReqDto reqDto) {
        TmAmountDailyFilePopupEntity entity = ModelMapperUtil.map(reqDto, userContext, TmAmountDailyFilePopupEntity.class);
        commonDao.delete(SERVICEID_PREFIX + "deleteUploadFile", entity);
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    } 
    
    /**
     * @description : 파일 업로드
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.22    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveFileUpload(TmAmountDailyReqDto paramDto, List<MultipartFile> files, List<FileUpload> fileInfoList) {
        TmAmountDailyReqDto reqDto = ModelMapperUtil.map(paramDto, TmAmountDailyReqDto.class);
        
        String serialkeyH = reqDto.getSerialkeyH();
        String dccode = null;
        String allowanceDate = null;
        
        // 리소스 주입 방지를 위해 serialkeyH 검증
        if (serialkeyH == null || serialkeyH.isEmpty() || !SAFE_TOKEN.matcher(serialkeyH).matches()) {
            throw new IllegalArgumentException("Invalid 헤더ID");
        }

        // 업로드된 파일 이름 검증 (경로 조작 방지)
        if (files != null) {
            for (MultipartFile mf : files) {
                String original = StringUtils.cleanPath(mf.getOriginalFilename() == null ? "" : mf.getOriginalFilename());
                if (original.isBlank()) {
                    throw new IllegalArgumentException("Empty file name is not allowed.");
                }
                if (original.contains("..") || original.contains("/") || original.contains("\\")) {
                    throw new IllegalArgumentException("Invalid file name.");
                }
                if (original.length() > MAX_FILENAME_LENGTH) {
                    throw new IllegalArgumentException("File name too long.");
                }
            }
        }
        
        // fileInfoList 항목 검증 (가능한 경우 파일명 검사)
        if (fileInfoList != null) {
            for (FileUpload fu : fileInfoList) {
                try {
                    // 일부 구현체에 getFileName 메서드가 있을 수 있어 리플렉션으로 확인
                    String fname = null;
                    try {
                        fname = (String) fu.getClass().getMethod("getFileName").invoke(fu);
                    } catch (NoSuchMethodException nsme) {
                        // getFileName이 없을 수도 있으므로 무시
                    }
                    if (fname != null) {
                        String cleaned = StringUtils.cleanPath(fname);
                        if (cleaned.contains("..") || cleaned.contains("/") || cleaned.contains("\\")) {
                            throw new IllegalArgumentException("Invalid fileInfoList file name.");
                        }
                        if (cleaned.length() > MAX_FILENAME_LENGTH) {
                            throw new IllegalArgumentException("FileInfoList file name too long.");
                        }
                    }
                } catch (ReflectiveOperationException roe) {
                    // 리플렉션 실패 시 안전하게 입력 거부
                    throw new IllegalArgumentException("Invalid fileInfoList contents.");
                }
            }
        }  
        
        // 임시저장 전 transFileNm을 명시적으로 설정
        int idx = 0;
        for (FileUpload info : fileInfoList) {
            String src = info.getAttchFileNm();
            String ext = "";
            int dot = src != null ? src.lastIndexOf('.') : -1;
            if (dot > -1) {
                ext = src.substring(dot + 1);
            }
            String trans = "TRSP_DAILY_" + System.currentTimeMillis() + "_" + idx + (ext.isEmpty() ? "" : "." + ext);
            info.setTransFileNm(trans);
            idx++;
        }
        
        // 일자별 수당 조회 by serialkey 
        HashMap<?, ?> dsDayAllwoance =  commonDao.selectOne(SERVICEID_PREFIX + "getDayAllowance", reqDto);
        
        if (ObjectUtils.isEmpty(dsDayAllwoance)) {
            log.error("비용 등록 데이터를 찾을 수 없습니다. 데이터번호 : " + serialkeyH);
            throw new IllegalArgumentException("비용 저장 후 증빙파일을 등록할 수 있습니다.");
        }
        
        // 기본 저장 경로 설정 
        String dirPath = ContextUtil.getProperty("cf.upload.dir.trsp");
        // 물류센터코드
        dccode = (String) dsDayAllwoance.get("DCCODE");
        // 비용발생일자
        allowanceDate = (String) dsDayAllwoance.get("ALLOWANCE_DATE");

        // 파일 저장 
        List<FileUpload> fileUploadList = fileUploaderNew.saveFiles(files, fileInfoList, dirPath + File.separator + "trspdaily" + File.separator + allowanceDate.substring(0, 4) + File.separator + allowanceDate.substring(4, 6));

        // 파일 정보를 테이블에 저장
        for (FileUpload f : fileUploadList) { 
            TmAmountDailyFilePopupEntity entity = new TmAmountDailyFilePopupEntity();
            
            String nm = f.getAttchFileNm();
            String ext = nm != null && nm.lastIndexOf('.') > -1 ? nm.substring(nm.lastIndexOf('.') + 1) : "";
            
            entity.setSerialkeyH(serialkeyH);
            entity.setDocName(ATTATCH_DOC_NAME);
            entity.setCourier((String) dsDayAllwoance.get("COURIER"));
            entity.setCarno((String) dsDayAllwoance.get("CARNO"));
            entity.setFileNm(f.getAttchFileNm());
            entity.setAttchFileExtNm(ext);
            entity.setFileLocation(f.getSavePathNm1() + "/" + f.getTransFileNm());
            entity.setAttchFileSz(new BigDecimal(f.getAttchFileSz()));
            entity.setUploadFileName(f.getTransFileNm());
            entity.setGUserId(userContext.getUserId());
            
            commonDao.insert(SERVICEID_PREFIX + "insertUploadFile", entity);
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 파일 다운로드
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.22    KimSunHo(sunhokim6229@cj.net)   생성 </pre>
     */
    public void downloadFile(HttpServletResponse response,  FileDownload fileDownload, TmAmountDailyReqDto paramDto) {
        TmAmountDailyReqDto reqDto = ModelMapperUtil.map(paramDto, TmAmountDailyReqDto.class);

        List<TmAmountDailyFilePopupResDto> fileList = commonDao.selectList(SERVICEID_PREFIX + "getPopupUploadFileList", reqDto);
        
        if (!ObjectUtils.isEmpty(fileList) && fileList.size() > 0) {
            TmAmountDailyFilePopupResDto resDto = fileList.get(0);
            
            String fileLocation = resDto.getFileLocation();
            String fileNm = resDto.getFileNm();
            
            if (fileLocation.contains("..") || fileNm.contains("..") || fileNm.contains("/") || fileNm.contains("\\")) {
                throw new IllegalArgumentException("Invalid file name.");
            }
            if (fileNm.length() > MAX_FILENAME_LENGTH) {
                throw new IllegalArgumentException("File name too long.");
            }
            
            fileDownloader.downloadFileNew(response, fileLocation, fileNm, null);
        }
    }
	
}