package cjfw.wms.tm.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.file.FileDownload;
import cjfw.core.file.FileDownloader;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderNew;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.tm.dto.TmAmountByCarFilePopupResDto;
import cjfw.wms.tm.dto.TmAmountByCarReqDto;
import cjfw.wms.tm.dto.TmAmountByCarResDto;
import cjfw.wms.tm.entity.TmAmountByCarFilePopupEntity;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.08.01
 * @description : 차량별 수당관리
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.01 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmAmountByCarService {

	private transient static final String SERVICEID_PREFIX = "tmAmountByCarService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
    private final FileUploaderNew fileUploaderNew;
    private static final Pattern SAFE_TOKEN = Pattern.compile("^[A-Za-z0-9_\\-]{1,64}$");
    private static final Pattern SAFE_LOCATION = Pattern.compile("^[A-Za-z0-9_\\-]{0,64}$");
    private static final String ATTATCH_DOC_NAME = "TRSP_BYCAR";
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
	public List<TmAmountByCarResDto> getMasterList(TmAmountByCarReqDto reqDto) {
		List<TmAmountByCarResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
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
	public List<TmAmountByCarResDto> getCarInfo(TmAmountByCarReqDto reqDto) {
		List<TmAmountByCarResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getCarInfo", reqDto);
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
	public String saveMasterList(TmAmountByCarReqDto reqDto) {
		List<TmAmountByCarResDto> originList = reqDto.getSaveList();
		if (!ObjectUtils.isEmpty(originList)) {
			// 물류센터, 운송사, 차량번호, 정산항목별로 그룹핑한다.
			Map<String, List<TmAmountByCarResDto>> grouped = originList.stream()
				    .collect(Collectors.groupingBy(item ->
				        item.getDccode() + "_" + item.getCourier() + "_" + item.getCarno() + "_" + item.getSttlItemCd()
				    ));
			for (Map.Entry<String, List<TmAmountByCarResDto>> entry : grouped.entrySet()) {
			    String key = entry.getKey();
			    String[] keys = key.split("_", -1);

			    if (keys.length != 4) {
			        throw new UserHandleException("물류센터, 운송사, 차량번호, 정산항목은 필수값입니다.");
			    }
			    // 물류센터, 운송사, 차량번호, 정산항목별 로 그룹핑한 목록
			    List<TmAmountByCarResDto> groupList = entry.getValue();
			    // db 조회시 delete건과 update건은 조회할때 빼야한다.
			    TmAmountByCarReqDto durationReqDto = new TmAmountByCarReqDto();
			    durationReqDto.setGMultiDccode(keys[0]);
			    durationReqDto.setCourier(keys[1]);
			    durationReqDto.setCarno(keys[2]);
			    durationReqDto.setSttlitemcd(keys[3]);
			    // update, delete 키 추출
		    	List<String> excludeKeys = new ArrayList<>();
		    	excludeKeys.addAll(groupList.stream()
			    	    .filter(d -> CanalFrameConstants.DELETE.equals(d.getRowStatus())||CanalFrameConstants.UPDATE.equals(d.getRowStatus()))
			    	    .map(TmAmountByCarResDto::getSerialkey)
			    	    .filter(Objects::nonNull)
			    	    .collect(Collectors.toSet()));

		    	// DTO에 세팅
		    	durationReqDto.setExcludeKeys(excludeKeys);
				// 물류센터, 운송사, 차량번호, 정산항목별 시작일, 종료일을 조회한다.
		    	List<TmAmountByCarResDto> duraionList = commonDao.selectList(SERVICEID_PREFIX + "getDurationList", durationReqDto);
		    	//delete를 제외한 요청한 데이터들도 같이 기간 검증을 한다.
			    duraionList.addAll(groupList.stream().filter(dto -> !CanalFrameConstants.DELETE.equals(dto.getRowStatus())).collect(Collectors.toList()));
			    duraionList.sort(Comparator.comparing(TmAmountByCarResDto::getFromdate));

			    // 기간 검증 및 데이터 db 업데이트
			    for (TmAmountByCarResDto dto : groupList) {
					TmAmountByCarResDto entity = ModelMapperUtil.map(dto, userContext, TmAmountByCarResDto.class);
					if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus()) || (CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
						List<TmAmountByCarResDto> filteredDurationList = duraionList.stream()
					            .filter(row ->
					            !Objects.equals(row.getSerialkey(), entity.getSerialkey()) &&
			                    isDateRangeOverlap(row.getFromdate(), row.getTodate(), entity.getFromdate(), entity.getTodate())
			            ).collect(Collectors.toList());
						
						if (!filteredDurationList.isEmpty()) {
							throw new UserHandleException(String.format(
							        "입력하신 기간이 기존 등록된 기간과 겹칩니다.\n" + "차량번호: %s, 정산항목: %s\n" + "기존 등록 기간: %s ~ %s",
							        entity.getCarno(),entity.getSttlItemCd(),entity.getFromdate(),entity.getTodate())
								);
						}
						commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
					} else if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
						commonDao.delete(SERVICEID_PREFIX +"deleteMaster", entity);
					}
			    }
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 기간 중복 체크 로직
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.07 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	private boolean isDateRangeOverlap(String from1, String to1, String from2, String to2) {
	    int f1 = Integer.parseInt(from1);
	    int t1 = Integer.parseInt(to1);
	    int f2 = Integer.parseInt(from2);
	    int t2 = Integer.parseInt(to2);
	    return f1 <= t2 && f2 <= t1;
	}

	/**
     * @description : 엑셀 업로드 데이터 검증
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
     */
	public List<TmAmountByCarResDto>getExcelValChk(TmAmountByCarReqDto reqDto) {
		List<TmAmountByCarResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getExcelValChk", reqDto.getSaveList());
		return result;
	}
	
	/**
     * @description : 엑셀 업로드 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
     */
	public String saveExcel(TmAmountByCarReqDto req) {
		if(req != null) {
    		List<TmAmountByCarResDto> list = req.getSaveList();
    		for (var dto : list) {
    			   /*
    	          CASE1 ) UPDATE A
    		           |---------------------------| ->A
    		           |                             ->B
                     => 정보만 UPDATE A
    	          CASE2 ) UPDATE A, INSERT B
    		           |---------------------------| ->A
    		               |                         ->B
    		           =>
    		           |---|
    		                |----------------------|
    
    			  CASE3 ) UPDATE A, INSERT B
    		           |-------|           |-------| ->A
    		               |                         ->B
    		           =>
    		           |---|               |-------| ->A
    		                |-------------|          ->B
    
    		                
    		                
    		      CASE2 상 )          
    		           |--|                             ->A
    		                     |                      ->B
    		           =>
    		           |---|
    		                     |----------------------|
               */
    			var entity = ModelMapperUtil.map(dto, userContext, TmAmountByCarResDto.class);

    			if ("Y".equals(dto.getExpiredYn()))  {
    				commonDao.update(SERVICEID_PREFIX +"expireAndDeleteTmAmountCar01", entity);
    				commonDao.delete(SERVICEID_PREFIX +"deleteTmAmountCar01", entity);
    			} else {
    				List<TmAmountByCarResDto> result = commonDao.selectList(SERVICEID_PREFIX +"chkVal", entity);
    				String chk1  = result.get(0).getChk1();
    				String staDt = result.get(0).getStaDt();
    				String endDt = result.get(0).getEndDt();
    				String serialKey = result.get(0).getSerialkey();
    				
    				if (chk1.equals("Y")) {
    					commonDao.update(SERVICEID_PREFIX +"updateTmAmountCar01", entity);
    				} else {
    					if(staDt != null && !"".equals(staDt)) {
    						entity.setSerialkey(serialKey);
    						commonDao.update(SERVICEID_PREFIX +"updateTmAmountCar02", entity);
    					}
    					if (endDt != null && !"".equals(endDt)) {
    						entity.setTodate(endDt);
    					}
    					commonDao.insert(SERVICEID_PREFIX +"insertExcelMaster", entity);
    				}
    			}
    		}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 마감확인 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.12 ParkJinWoo 생성
	 */
	public List<TmAmountByCarResDto> trspCloseChk(TmAmountByCarReqDto reqDto) {
		if (reqDto == null) {
			return null;
		}
		List<TmAmountByCarResDto> result = new ArrayList<>();
		List<TmAmountByCarResDto> list = reqDto.getSaveList();
		for (TmAmountByCarResDto dto : list) {
			TmAmountByCarResDto chkval = (commonDao.selectOne(SERVICEID_PREFIX + "trspCloseChk", dto));
			result.add(chkval);
		}

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
    public List<TmAmountByCarFilePopupResDto> getFileList(TmAmountByCarReqDto reqDto) {
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
    public String deleteUploadFile(TmAmountByCarReqDto reqDto) {
        TmAmountByCarFilePopupEntity entity = ModelMapperUtil.map(reqDto, userContext, TmAmountByCarFilePopupEntity.class);
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
    public String saveFileUpload(TmAmountByCarReqDto paramDto, List<MultipartFile> files, List<FileUpload> fileInfoList) {
        TmAmountByCarReqDto reqDto = ModelMapperUtil.map(paramDto, TmAmountByCarReqDto.class);
        
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
            String trans = "TRSP_BYCAR_" + System.currentTimeMillis() + "_" + idx + (ext.isEmpty() ? "" : "." + ext);
            info.setTransFileNm(trans);
            idx++;
        }
        
        // 일자별 수당 조회 by serialkey 
        HashMap<?, ?> dsDayAllwoance =  commonDao.selectOne(SERVICEID_PREFIX + "getCarAllowance", reqDto);
        
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
        List<FileUpload> fileUploadList = fileUploaderNew.saveFiles(files, fileInfoList, dirPath + File.separator + "trspbycar" + File.separator + allowanceDate.substring(0, 4) + File.separator + allowanceDate.substring(4, 6));

        // 파일 정보를 테이블에 저장
        for (FileUpload f : fileUploadList) { 
            TmAmountByCarFilePopupEntity entity = new TmAmountByCarFilePopupEntity();
            
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
    public void downloadFile(HttpServletResponse response,  FileDownload fileDownload, TmAmountByCarReqDto paramDto) {
        TmAmountByCarReqDto reqDto = ModelMapperUtil.map(paramDto, TmAmountByCarReqDto.class);
        
        List<TmAmountByCarFilePopupResDto> fileList = commonDao.selectList(SERVICEID_PREFIX + "getPopupUploadFileList", reqDto);
        
        if (!ObjectUtils.isEmpty(fileList) && fileList.size() > 0) {
            TmAmountByCarFilePopupResDto resDto = fileList.get(0);
            
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