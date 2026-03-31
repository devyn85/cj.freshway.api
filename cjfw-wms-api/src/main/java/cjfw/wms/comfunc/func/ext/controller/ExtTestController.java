package cjfw.wms.comfunc.func.ext.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBeanBuilder;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;

import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.dataaccess.largedata.LargeExcelUtil;
import cjfw.core.exception.UserHandleException;
import cjfw.core.file.FileDownload;
import cjfw.core.file.FileDownloader;
import cjfw.core.file.FileUpload;
import cjfw.core.file.FileUploaderNew;
import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.core.sap.JCoUtil;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.dto.CmCodeReqDto;
import cjfw.wms.cm.dto.CmFaxReqDto;
import cjfw.wms.cm.dto.CmSendEmailReqDto;
import cjfw.wms.cm.dto.CmSendReqDto;
import cjfw.wms.cm.dto.CmUserManagerResDto;
import cjfw.wms.cm.service.CmEmailService;
import cjfw.wms.cm.service.CmFaxService;
import cjfw.wms.comfunc.func.ext.dto.UserCsvDto;
import cjfw.wms.comfunc.func.ext.service.ExtTestService;
import cjfw.wms.ms.dto.MsCreditInfoDto;
import cjfw.wms.st.dto.StExDCStorageResDto;
import cjfw.wms.webservice.credit.DT_SCM0070_SCM;
import cjfw.wms.webservice.credit.DT_SCM0070_SCMIF_MS_CUSTAMT;
import cjfw.wms.webservice.credit.DT_SCM0070_SCM_responseIF_MS_CUSTAMT_RET;
import cjfw.wms.webservice.credit.SI_SCM0070_SCM_SOProxy;
import cjfw.wms.webservice.sso.webservice.SSO_NON_SAP_EAIPortTypeProxy;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_REQUEST;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_RESPONSE;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.07.14 
 * @description : 타 시스템 연동 테스트
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/ext/test", "ltx/ext/test"})
public class ExtTestController {
	
	private final FileUploaderNew fileUploaderNew;
	
	private final FileDownloader fileDownloader;
	
	private final CmEmailService cmEmailService;
	
	private final CmFaxService cmFaxService;
	
	private final ExtTestService extTestService;
	
	private final UserContext userContext;

	/**
	 * @description : SAP 호출 테스트
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@GetMapping(value = "/v1.0/getSapTest")
	public ApiResult<String> getSapTest() throws JCoException {
		log.info("@@@@@@@@@@@@@@@@@@@@ SAP RFC 호출 start");
		JCoUtil jco = new JCoUtil();
		log.info("@@@@@@@@@@@@@@@@@@@@ 1");
        JCoDestination destination = jco.getDestination();
        log.info("@@@@@@@@@@@@@@@@@@@@ 2");
        JCoFunction function = jco.getFunction("ZMM_CHECK_PERIOD_CLOSING");
        log.info("@@@@@@@@@@@@@@@@@@@@ 3");
        
        JCoParameterList params = function.getImportParameterList();        
        params.setValue("I_BUDAT" , "202507");     // 전기일
		params.setValue("I_CODE"  , "TC003-0002");     // 마감코드
		
        // 통신 시작
 		try {
 			log.info("@@@@@@@@@@@@@@@@@@@@ 통신 시작");
 			function.execute(destination);
 			log.info("@@@@@@@@@@@@@@@@@@@@ 통신 종료");
 		} catch(JCoException e) {
 			log.error("JCoException",e);
 			throw new UserHandleException("MSG.COM.ERR.001");
 		}catch(Exception e){
 			log.error("Exception",e);
 			throw new UserHandleException("MSG.COM.ERR.001");
 		}
 		
 		JCoParameterList returnData = function.getExportParameterList();
 		log.info("@@@@@@@@@@@@@@@@@@@@ E_XSTAT : {}", returnData.getValue("E_XSTAT"));
        
		return ApiResult.createResult(""+returnData.getValue("E_XSTAT"), "");
	}
	
	/**
	 * @description : EAI 호출 테스트
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@GetMapping(value = "/v1.0/getEaiTest")
	public ApiResult<String> getEaiTest() {
		log.info("@@@@@@@@@@@@@@@@@@@@ EAI 호출 start");
		
		// 여신조회하는 Webservice
		SI_SCM0070_SCM_SOProxy proxy = new SI_SCM0070_SCM_SOProxy();
		
		String CREDITLIMIT = "";
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");		
			
			DT_SCM0070_SCM credittData = new DT_SCM0070_SCM();
			credittData.setXSYS("SCM");
			credittData.setXDATS(dateFormat.format(calendar.getTime()));
			credittData.setXTIMS(timeFormat.format(calendar.getTime()));
			
			DT_SCM0070_SCMIF_MS_CUSTAMT[] custAmtList = new DT_SCM0070_SCMIF_MS_CUSTAMT[1];
			DT_SCM0070_SCMIF_MS_CUSTAMT custInfo = new DT_SCM0070_SCMIF_MS_CUSTAMT();
				
			// 전체 Row 설정
			credittData.setXROWS("1");
			custInfo.setGJAHR("20250309".substring(0,4));
			custInfo.setZMONTH("20250309".substring(4,6));
			custInfo.setDELIVERYDATE("20250309");
			custInfo.setKUNNR("550351603");
			custAmtList[0] = custInfo;
			
			if(custAmtList != null) {
				credittData.setIF_MS_CUSTAMT(custAmtList);
				log.info("@@@@@@@@@@@@@@@@@@@@ 1");
				DT_SCM0070_SCM_responseIF_MS_CUSTAMT_RET[] amtResponse = proxy.si_scm0070_scm_so(credittData);
				log.info("@@@@@@@@@@@@@@@@@@@@ 2");
				
				if (amtResponse != null) {
					for(int i = 0; i < amtResponse.length; i++) {
					    MsCreditInfoDto creditInfoDto = new MsCreditInfoDto();
					    creditInfoDto.setGjahr(amtResponse[i].getGJAHR());
					    creditInfoDto.setZmonth(amtResponse[i].getZMONTH());
					    creditInfoDto.setDay(amtResponse[i].getDELIVERYDATE().substring(6, 8));
					    creditInfoDto.setDeliverydt(amtResponse[i].getDELIVERYDATE());
					    creditInfoDto.setKunnr(amtResponse[i].getKUNNR());
					    creditInfoDto.setKlimk(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getKLIMK()).trim()));
					    creditInfoDto.setSales(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getSALES()).trim()));
					    creditInfoDto.setZoutput(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getZOUTPUT()).trim()));
					    creditInfoDto.setZinput(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getZINPUT()).trim()));
					    creditInfoDto.setZdelayTxt(amtResponse[i].getZDELAY_TXT());
					    creditInfoDto.setZjango(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getZJANGO()).trim()));
					    creditInfoDto.setZjangoM(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getZJANGO_M()).trim()));
					    creditInfoDto.setMoutput(new BigDecimal(cjfw.core.utility.StringUtil.nvl(amtResponse[i].getMOUTPUT()).trim()));
					    
					    CREDITLIMIT = StringUtil.nvl(amtResponse[i].getKLIMK()).trim();
						log.info("@@@@@@@@@@@@@@@@@@@@ 결과값 : {}", creditInfoDto.toString());
					}
				}
			}
		} catch(Exception e) {
			log.error("Exception", e);
			throw new UserHandleException("MSG.COM.ERR.001");
		}
		
        
		return ApiResult.createResult(CREDITLIMIT, "");
	}
	
	/**
	 * @description : EAI 호출 테스트
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@GetMapping(value = "/v1.0/getEaiIamTest")
	public ApiResult<String> getEaiIamTest() {
		log.info("@@@@@@@@@@@@@@@@@@@@ EAI IAM 호출 start");
		
		// 전자결재용 SSO_ID 요청
		String ssoId = "";
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

            SSO_NON_SAP_REQUEST reqData = new SSO_NON_SAP_REQUEST();
            reqData.setXSYS("SCM");
            reqData.setXDATS(dateFormat.format(calendar.getTime()));
            reqData.setXTIMS(timeFormat.format(calendar.getTime()));
            reqData.setXROWS("1");
            reqData.setINT_SVC_NO("1000001317");

            SSO_NON_SAP_EAIPortTypeProxy proxy = new SSO_NON_SAP_EAIPortTypeProxy();
            log.info("@@@@@@@@@@@@@@@@@@@@ 1");
            SSO_NON_SAP_RESPONSE response = proxy.GET_SSO_TICKET(reqData);
            log.info("@@@@@@@@@@@@@@@@@@@@ 2");
            ssoId = response.getSSO_TICKET();

        } catch (Exception e) {
        	log.error("▶sso proxy 오류 발생 ");
        	throw new UserHandleException("MSG.COM.ERR.001");
        }
		
        
		return ApiResult.createResult(ssoId, "");
	}
	
	/**
	 * @description : 엑셀양식 파일 업로드 예제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.05 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@PostMapping(value = "/v1.0/saveExcelTemplateFileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ApiResult<String> saveExcelTemplateFileUpload(
			@RequestPart(value="file", required = true) List<MultipartFile> files,
			@RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList) {
		
		String fileFullPath = "";
		
		// NAS 파일 업로드
		fileUploaderNew.saveFiles(files, fileInfoList, ContextUtil.getProperty("cf.upload.dir.excelTemplate"));
		
		return ApiResult.createResult(fileFullPath, "");
	}
	
	/**
	 * @description : Fax 파일 업로드 예제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.21 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@PostMapping(value = "/v1.0/saveFaxFileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ApiResult<String> saveFaxFileUpload(
			@RequestPart(value="file", required = true) List<MultipartFile> files,
			@RequestPart(value="fileInfoList", required = true) List<FileUpload> fileInfoList) {
		
		String fileFullPath = "";
		
		// NAS 파일 업로드
		List<FileUpload> fileUploadList = fileUploaderNew.saveFiles(files, fileInfoList, ContextUtil.getProperty("cf.upload.dir.fax"));
		
		for(FileUpload rFile : fileUploadList) {
			fileFullPath = rFile.getSavePathNm1() + "/" + rFile.getSavePathNm2();
			log.info("@@@@@@@@@@@@@@@@@@@@ 업로드 결과 {}", rFile.toString());
		}
		
		return ApiResult.createResult(fileFullPath, "");
	}
	
	/**
	 * @description : Fax 파일 다운로드 예제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.21 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@PostMapping(value = "/v1.0/faxFileDownload")
	public void faxFileDownload(HttpServletResponse response, FileDownload fileDownload) {		
		// WAS 서버 파일 클라이언트로 다운로드
		fileDownloader.downloadFileNew(response, ContextUtil.getProperty("cf.upload.dir."+fileDownload.getDirType()) + "/" + fileDownload.getAttchFileNm(), fileDownload.getAttchFileNm(), null);
	}
	
	/**
	 * @description : 이메일 전송 예제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.22 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@GetMapping(value = "/v1.0/sendEmail")
	public ApiResult<String> sendEmail(CmSendReqDto cmSendReqDto) {
		
		String filePath = ContextUtil.getProperty("cf.upload.dir.email") + "/" + "2025_07_23_000456.tif";
//		String filePath = "/app/deploy/report/ReportingServer/report/2026/02/13/RPT20260213152441679.tif";
		
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@ {}", cmSendReqDto.toString());
		
		File file = new File(filePath);
		if (file.exists()) {
			try {
	            byte[] byteArray = Files.readAllBytes(Paths.get(filePath));
	            log.info("@@@@@@@@@@@@@@@@@@@@ 파일크기 : {}", byteArray.length);
	            cmEmailService.sendEmail("cjfreshway", "postmaster@fwportalqa01a.cjfwqa.com", cmSendReqDto.getRcvrNm(), cmSendReqDto.getRcvrEmail(), cmSendReqDto.getTitle(), cmSendReqDto.getCnts(), "2025_07_23_000456.tif", byteArray);
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new UserHandleException("MSG.COM.ERR.001");
	        }
        } else {
        	boolean nResult = cmEmailService.sendEmail("cjfreshway", "postmaster@fwportalqa01a.cjfwqa.com", cmSendReqDto.getRcvrNm(), cmSendReqDto.getRcvrEmail(), cmSendReqDto.getTitle(), cmSendReqDto.getCnts(), null, null);
        	if (!nResult) throw new UserHandleException("MSG.COM.ERR.001");
        }

		return ApiResult.createResult("S", "");
	}
	
	/**
	 * @description : FAX 전송 테스트
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.01 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@PostMapping(value = "/v1.0/saveFax")
	public ApiResult<String> saveFax(@RequestBody CmFaxReqDto cmFaxReqDto) {
		return ApiResult.createResult(extTestService.saveFax(cmFaxReqDto));
	}
	
	/**
	 * @description : 드라이버 사용자 ID 생성
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.17 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@GetMapping(value = "/v1.0/saveDriverUser")
	public ApiResult<String> saveDriverUser(CmUserManagerResDto dto) {
		return ApiResult.createResult(extTestService.saveDriverUser(dto));
	}
	
	/**
	 * @description : 공통코드 대용량 다운로드
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@PostMapping(value = "/v1.0/saveLargeDataExcel")
	public void saveLargeDataExcel(@RequestBody CmCodeReqDto cmCodeReqDto, HttpServletRequest request, HttpServletResponse response) {

		String[] headerColumns = new String[]{"회사","코드리스트","기본코드설명"}; // 헤더 칼럼 설정
		String[] dataColumns = new String[]{"storerkey","basecode","basedescr"}; // Data 매핑 DTO 칼럼 설정 (헤더 순서에 맞게)

		LargeExcel largeExcel = new LargeExcel();
		largeExcel.setHeaderColumns(headerColumns); // 헤더 컬럼
		largeExcel.setDataColumns(dataColumns); // 컬럼 매핑 키
		largeExcel.setExcelFileName("공통코드"); // 엑셀 파일명

		LargeExcelUtil excelUtil = new LargeExcelUtil(largeExcel);
		extTestService.getCodeHeaderList(cmCodeReqDto, largeExcel);
		largeExcel.setUserContext(userContext);
		excelUtil.makeExcelDownload(request, response, largeExcel);
	}
	
	/**
     * @throws IOException 
	 * @description : 이메일 전송 예제
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.22 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    @PostMapping(value = "/v1.0/saveEmail")
    public ApiResult<String> saveEmail(@RequestBody CmSendEmailReqDto cmSendEmailReqDto) throws IOException {
        
        return ApiResult.createResult(cmEmailService.saveEmail(cmSendEmailReqDto), "");

    }
    
    /**
     * @description : AI팀 CSV 파일 저장 예제
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.12.19 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    @PostMapping(value = "/v1.0/saveAICsv")
	public void saveAICsv() {
    	String apiUrl = "https://download.microsoft.com/download/5/B/2/5B2108F8-112B-4913-A761-38AFF2FD8598/Sample%20CSV%20file%20for%20importing%20contacts.csv";
        Path savePath = Path.of(ContextUtil.getProperty("cf.file.uploadPath") + "/ai" + "/20251219.csv"); // 저장 경로

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .header("Accept", "text/csv")
                .build();

        HttpResponse<InputStream> response;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
			
			// 상태 코드 체크
	        if (response.statusCode() == 200) {
	            // 폴더 없으면 생성
	            Files.createDirectories(savePath.getParent());

	            // 파일 저장
	            Files.copy(response.body(), savePath, StandardCopyOption.REPLACE_EXISTING);
	            log.info("@@@@@ CSV 파일 저장 완료: " + savePath);
	            
	            // CSV 파일 내용 읽어오기
	            Reader reader = Files.newBufferedReader(savePath, StandardCharsets.UTF_8);
	            List<UserCsvDto> list = new CsvToBeanBuilder<UserCsvDto>(reader)
                .withType(UserCsvDto.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();
	            
	            for (UserCsvDto dto : list) {
	            	log.info("@@@@@@@@@@@@@@@@@@@@@@ {}", dto.toString());
	            }
	        }
		} catch (Exception e) {
			log.error("@@@@@ AI CSV 파일 다운로드 실패 : {}", e);
		}
	}
    
    /**
     * @throws IOException 
     * @description : 이메일 전송 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.22 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    @PostMapping(value = "/v1.0/saveEmailService")
    public ApiResult<String> saveEmailService(@RequestBody CmSendEmailReqDto cmSendEmailReqDto) throws IOException {
        return ApiResult.createResult(cmEmailService.saveEmail(cmSendEmailReqDto), "");
    }
    
    /**
     * @description : FAX 전송
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.01 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    @PostMapping(value = "/v1.0/saveFaxService")
    public ApiResult<String> saveFaxService(@RequestBody CmFaxReqDto cmFaxReqDto) {
        return ApiResult.createResult(cmFaxService.saveFax(cmFaxReqDto));
    }
    
    /**
     * @description : 팩스 전송 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.02.21 KimSunHo(sunhokim6229@cj.net) 생성 </pre>
     */
    @PostMapping(value = "/v1.0/getDataFaxHistlist")
    public ApiResult<List<StExDCStorageResDto>> getDataFaxHistlist(CmFaxReqDto dto) {
        return ApiResult.createResult(cmFaxService.getDataFaxHistlist(dto));
    }
    
    /**
     * @description : SMS 전송
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.06 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    @PostMapping(value = "/v1.0/saveSms")
    public ApiResult<String> saveSms(@RequestBody CmSendReqDto cmSendReqDto) {
        return ApiResult.createResult(extTestService.saveSms(cmSendReqDto));
    }

}