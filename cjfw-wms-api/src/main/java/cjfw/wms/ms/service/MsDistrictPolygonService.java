package cjfw.wms.ms.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.dataaccess.largedata.LargeDataUtils;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.ms.dto.MsDistrictPolygonReqDto;
import cjfw.wms.ms.dto.MsDistrictPolygonResDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsDistrictPolygonService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msDistrictPolygonService.";

    private final CommonDao commonDao;
	
	private final SqlSessionFactory sqlSessionFactory;
	private final ObjectMapper objectMapper;
	
	/**
	 * @description : 권역 폴리곤 조회 기능을 구현한 Method 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.19 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	public List<MsDistrictPolygonResDto> getMasterList(MsDistrictPolygonReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

    /**
     * @description : 행정동 공간데이터 목록 조회(스트리밍)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.14 LeeHyunsung (zoot0134@cj.net) 생성 </pre>
     */
    public void getStreamMasterList(HttpServletResponse response, MsDistrictPolygonReqDto dto) {
        LargeDataUtils largeDataUtil = new LargeDataUtils(response);
        commonDao.selectLargeDataset(SERVICEID_PREFIX + "getMasterList", dto, largeDataUtil);
        largeDataUtil.writeResponseToJson();
    }
    
    /**
     * @description : 행정동 공간데이터 목록 조회(스트리밍)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.14 LeeHyunsung (zoot0134@cj.net) 생성 </pre>
     */
    public void getStreamMasterListWkb(HttpServletResponse response, MsDistrictPolygonReqDto dto) {
        LargeDataUtils largeDataUtil = new LargeDataUtils(response);
        commonDao.selectLargeDataset(SERVICEID_PREFIX + "getMasterList", dto, largeDataUtil);
        largeDataUtil.writeResponseToJson();
    }
    
    /**
     * @description : 행정동 공간데이터 조회 (임시/고도화 필요) 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.19 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
	public void getMasterList(MsDistrictPolygonReqDto dto, HttpServletResponse response) {
        // flush 대신 bufferSize 설정
        int bufferSize = 256 * 1024;
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(CanalFrameConstants.DEFAULT_CHARACTERSET);
        response.setBufferSize(bufferSize);

		try (SqlSession session = sqlSessionFactory.openSession()) {
                BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream(), bufferSize);
		        JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(bos);

                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName("data");
                jsonGenerator.writeStartArray();

		        try (Cursor<MsDistrictPolygonResDto> cursor = session.selectCursor(SERVICEID_PREFIX + "getMasterList", dto)) {
		            for (MsDistrictPolygonResDto item : cursor) {
                        // OOM 이슈 최소화를 위해서 writeObject에서 변경
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("hjdongCd", item.getHjdongCd());
                        jsonGenerator.writeStringField("ctpKorNm", item.getCtpKorNm());
                        jsonGenerator.writeStringField("sigKorNm", item.getSigKorNm());
                        jsonGenerator.writeStringField("hjdongNm", item.getHjdongNm());
                        jsonGenerator.writeStringField("geojson", item.getGeojson());
                        jsonGenerator.writeStringField("fromDate", item.getFromDate());
                        jsonGenerator.writeStringField("toDate", item.getToDate());
                        jsonGenerator.writeEndObject();
		            }
		        }

                jsonGenerator.writeEndArray();
                jsonGenerator.writeEndObject();  // JSON 객체 종료
                jsonGenerator.flush();
		} catch (Exception e) {
			log.error("폴리곤 데이터 로딩중 오류 발생", e);
		}
	}

    private static final String FILE_PATH = ContextUtil.getProperty("cf.file.polygonDataPath");
    public String saveMasterListFile() {
        List.of("CTP", "SIG", "HJDONG").forEach(type -> {
            MsDistrictPolygonReqDto dto = MsDistrictPolygonReqDto.builder().pg(true).districtType(type).build();
            File ingFile = new File(FILE_PATH + type.toLowerCase() + ".json.ing");
            File dir = ingFile.getParentFile();
            if (!ingFile.getParentFile().exists()) {
                if (!dir.exists() && !dir.mkdirs()) {
                    log.error("디렉토리 생성 실패: {}", dir.getAbsolutePath());
                    return;
                }
            }
            try (SqlSession session = sqlSessionFactory.openSession();
                 OutputStream out = new FileOutputStream(ingFile)) {
                try (Cursor<MsDistrictPolygonResDto> cursor = session.selectCursor(SERVICEID_PREFIX + "getHjdongPolygonList", dto)) {
                    for (MsDistrictPolygonResDto item : cursor) {
                        String json = objectMapper.writeValueAsString(item);
                        out.write(json.getBytes(CanalFrameConstants.DEFAULT_CHARACTERSET));
                        out.write('\n');
                        out.flush();
                    }
                    out.close();
                }
                if (ingFile.length() > 0) { // 0bytes overwrite 방지
                    File targetFile = new File(FILE_PATH + type.toLowerCase() + ".json");
                    if (ingFile.renameTo(targetFile)) {
                        log.info("임시 파일을 최종 파일로 변경 완료: {} -> {}", ingFile.getName(), targetFile.getName());
                    } else {
                        Files.move(ingFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        log.info("임시 파일을 최종 파일로 이동 완료 (대체 방법): {} -> {}", ingFile.getName(), targetFile.getName());
                    }
                    log.info("{} 생성 완료. 파일 크기={} bytes", targetFile.getName(), targetFile.length());
                }
            } catch (IOException e) {
                log.error("파일 IO 오류: {}", e.getMessage(), e);
                if (ingFile.delete()) {
                    log.info("임시 파일 삭제 완료: {}", ingFile.getName());
                }
            } catch (Exception e) {
                log.error("폴리곤 데이터 로딩중 오류 발생", e);
                if (ingFile.delete()) {
                    log.info("임시 파일 삭제 완료: {}", ingFile.getName());
                }
            }
        });
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    public void getMasterListOfFile(String districtType, HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_NDJSON_VALUE);
        response.setCharacterEncoding(CanalFrameConstants.DEFAULT_CHARACTERSET);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String file = FILE_PATH + districtType.toLowerCase() + ".json";
        try (InputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            StreamUtils.copy(in, out);
        } catch (Exception e) {
            log.error("JSON 파일 스트리밍 응답 중 오류 발생", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            stopWatch.stop();
            log.info(stopWatch.prettyPrint());
        }
    }

    public void getHjdongMasterListOfFile(String searchCtp, HttpServletResponse response) {

        response.setContentType(MediaType.APPLICATION_NDJSON_VALUE);
        response.setCharacterEncoding(CanalFrameConstants.DEFAULT_CHARACTERSET);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String filePath = FILE_PATH + "hjdong.json";
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(response.getWriter())) {
            String line;
            while ((line = reader.readLine()) != null) {
                Map<String, Object> json = objectMapper.readValue(line, new TypeReference<>() {});
                if (String.valueOf(json.get("hjdongCd")).startsWith(searchCtp)) {
                    writer.write(objectMapper.writeValueAsString(json));
                    writer.newLine();
                }
            }
            writer.flush();
        } catch (Exception e) {
            log.error("NDJSON 파일 필터링 응답 중 오류 발생", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            stopWatch.stop();
            log.info(stopWatch.prettyPrint());
        }
    }
}
