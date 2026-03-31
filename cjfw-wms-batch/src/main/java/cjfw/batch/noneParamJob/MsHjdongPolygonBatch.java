package cjfw.batch.noneParamJob;

import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.dto.MsHjdongDto;
import cjfw.batch.dto.MsHjdongPolygonDto;
import cjfw.batch.dto.IfMsHjdongPolygonDto;
import cjfw.batch.service.GeometryConverterService;
import cjfw.batch.service.HjdongCodeService;
import cjfw.batch.service.TmapApiService;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.utility.ContextUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.ResourceAccessException;

import cjfw.batch.common.BatchJobListener;
import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class MsHjdongPolygonBatch {

	private final CommonDao commonDao;
	private final HjdongCodeService hjdongCodeService;
	private final TmapApiService tmapApiService;
	private final GeometryConverterService geometryConverterService;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private BatchJobListener batchJobListener;

	@Bean
	public Job msHjdongPolygonJob() {
		return new JobBuilder("msHjdongPolygonJob", jobRepository)
				.listener(batchJobListener)
				.flow(execStep())
				.end()
				.build();
	}

	@Bean
	@StepScope
	public Step execStep() {
		return new StepBuilder("execStep", jobRepository)
				.tasklet(execTasklet(), transactionManager)
				.build();
	}

	public Tasklet execTasklet() {
		return (contribution, chunkContext) -> {

            String jobExecutionId = Long.toString(chunkContext.getStepContext().getStepExecution().getJobExecution().getId());

            BatchLogParamsDto batchLogDto = BatchLogParamsDto.builder()
                    .jobExecutionId(jobExecutionId)
                    .jobName("msHjdongPolygonJob")
                    .jobDiv("JAVA")
                    .nodeLevel(0)
                    .jobStatus("START")
                    .command("")
                    .lineNo("1")
                    .resultCode("0")
                    .resultMsg("")
                    .build();

            commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);

			try {
				updateMsHjdongPolygon(jobExecutionId);
                savePolygonFile(jobExecutionId);
            } catch (Exception e) {
                batchLogDto.setResultCode("-1");
                batchLogDto.setResultMsg(e.toString());
            } finally {
                batchLogDto.setJobStatus("END");
                batchLogDto.setLineNo("2");
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
            }

			return RepeatStatus.FINISHED;
		};
	}
	
	/**
	 * @description : 변동된 행정동 정보 가져오기 및 TMAP API를 통한 폴리곤 정보 업데이트
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.17 OhEunbeom (oob00@cj.net) 생성
	 * 2025.09.24 OhEunbeom TMAP API 연동 로직 추가 </pre>
	 */
	public void updateMsHjdongPolygon(String jobExecutionId) {
		log.info("=== 행정동 폴리곤 배치 시작 ===");

		// 1. 모든 행정동 정보 조회 (신규:31, 수정:34, 폐지:63)
	    List<MsHjdongDto> hjdongList = commonDao.selectList("msHjdongPolygonService.getHjdongByMvmnResCd");
		log.info("처리 대상 행정동 수: {}", hjdongList.size());
		
		if (hjdongList.isEmpty()) {
			log.info("처리할 행정동 데이터가 없습니다.");
			return;
		}
		
		// 2. 각 행정동에 대해 폴리곤 정보 처리
		int successCount = 0;
		int skipCount = 0;
		int errorCount = 0;
		
		for (MsHjdongDto hjdong : hjdongList) {
			try {
				boolean processed = processHjdongPolygon(hjdong, jobExecutionId);
				if (processed) {
					successCount++;
				} else {
					skipCount++;
				}
			} catch (Exception e) {
				log.error("행정동 폴리곤 처리 중 오류 발생: {} - {}", 
					hjdong.getHjdongCd(), hjdong.getHjdongNm(), e);
				errorCount++;
			}
		}
		
		log.info("=== 행정동 폴리곤 배치 완료 ===");
		log.info("처리 결과 - 전체: {}, 성공: {}, 건너뛰기: {}, 오류: {}", 
			hjdongList.size(), successCount, skipCount, errorCount);
		
		// 3. IF_MS_HJDONG_POLYGON 테이블의 IF_FLAG = 'N'인 데이터를 MS_HJDONG_POLYGON에 적용
		log.info("=== IF_MS_HJDONG_POLYGON 데이터를 MS_HJDONG_POLYGON에 적용 시작 ===");
		applyIfPolygonToMsPolygon(jobExecutionId);
		log.info("=== IF_MS_HJDONG_POLYGON 데이터를 MS_HJDONG_POLYGON에 적용 완료 ===");
	}
	
	/**
	 * 개별 행정동 폴리곤 처리
	 * @param hjdong 행정동 정보
	 * @return 처리 성공 여부
	 */
	private boolean processHjdongPolygon(MsHjdongDto hjdong, String jobExecutionId) {
		log.debug("행정동 폴리곤 처리 시작: {} - {} (이동사유: {})", 
			hjdong.getHjdongCd(), hjdong.getHjdongNm(), hjdong.getMvmnResCd());
		
		try {
			// 1. 행정동 코드 유효성 검증
			if (!hjdongCodeService.isValidHjdongCode(hjdong.getHjdongCd())) {
				log.warn("유효하지 않은 행정동코드: {}", hjdong.getHjdongCd());
				return false;
			}
			
			// 2. 행정동명 정규화
			String normalizedHjdongNm = hjdongCodeService.normalizeHjdongName(hjdong.getHjdongNm());

			// 3. 신규/수정된 행정동 처리
			return processNewOrUpdatedHjdong(hjdong, normalizedHjdongNm, jobExecutionId);
			
		} catch (Exception e) {
			log.error("행정동 폴리곤 처리 중 오류 발생: {} - {}", 
				hjdong.getHjdongCd(), hjdong.getHjdongNm(), e);
			throw e;
		}
	}
	
	/**
	 * 신규/수정된 행정동 처리
	 * @param hjdong 행정동 정보
	 * @param normalizedHjdongNm 정규화된 행정동명
	 * @return 처리 성공 여부
	 */
	private boolean processNewOrUpdatedHjdong(MsHjdongDto hjdong, String normalizedHjdongNm, String jobExecutionId) {
		log.info("신규/수정된 행정동 처리: {} - {} (이동사유: {})", 
			hjdong.getHjdongCd(), normalizedHjdongNm, hjdong.getMvmnResCd());
		
		try {
			// 기존 폴리곤 데이터 조회 (IF_MS_HJDONG_POLYGON 테이블에서 조회)
			String existingWktGeometry = null;
            IfMsHjdongPolygonDto existingPolygon = commonDao.selectOne("msHjdongPolygonService.getHjdongPolygonByHjdongCd", hjdong);
            if (existingPolygon != null && existingPolygon.getSpatialGeomStr() != null) {
                existingWktGeometry = existingPolygon.getSpatialGeomStr();
            }
			
			// TMAP API를 통한 폴리곤 정보 조회
			String newWktGeometry;
			try {
				newWktGeometry = getPolygonFromTmapApi(hjdong, normalizedHjdongNm, jobExecutionId);
			} catch (ResourceAccessException e) {
				log.warn("TMAP 서버 접근 실패로 폴리곤 정보 업데이트 건너뛰기: {} - {} (오류: {})", 
					hjdong.getHjdongCd(), normalizedHjdongNm, e.getMessage());
				saveErrorLog(jobExecutionId, "6", "TMAP 서버 접근 실패: " + e.getMessage());
				return false; // 업데이트 건너뛰기
			}

            // 비교를 위한 TMAP 폴리곤 정보 컨버트
            if(newWktGeometry != null) {
                MsHjdongPolygonDto newPolygon = new MsHjdongPolygonDto();
                newPolygon.setSpatialGeom(new StringReader(newWktGeometry));

                newWktGeometry = commonDao.selectOne("msHjdongPolygonService.getHjdongPolygonByGeom", newPolygon);
            }

			// fromDate는 업데이트 기준일로 설정
			LocalDateTime updateDate = LocalDateTime.now();
			String fromDate = updateDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));

 			if (existingPolygon != null &&  Objects.equals(existingWktGeometry, newWktGeometry)) {
				log.info("폴리곤 데이터가 둘다 없거나 동일하여 업데이트 건너뛰기: {} - {}", hjdong.getHjdongCd(), normalizedHjdongNm);
				return false; // 변경사항 없음

			}

			// IF_MS_HJDONG_POLYGON 테이블에 저장
			MsHjdongPolygonDto polygonDto = MsHjdongPolygonDto.builder()
				.hjdongCd(hjdong.getHjdongCd())
				.hjdongNm(normalizedHjdongNm)
				.spatialGeom(newWktGeometry != null ? new StringReader(newWktGeometry) : null)
				.ctpKorNm(hjdong.getCtpKorNm())
				.sigKorNm(hjdong.getSigKorNm())
				.delYn("N")
				.fromDate(fromDate)
				.toDate("99991231")
				.addDate(LocalDateTime.now())
				.editDate(LocalDateTime.now())
				.addWho("BATCH_SYSTEM")
				.editWho("BATCH_SYSTEM")
				.build();
			saveOrUpdateIfPolygon(polygonDto, hjdong);

			log.info("행정동 폴리곤 처리 완료: {} - {} (폴리곤: {})",
				hjdong.getHjdongCd(), normalizedHjdongNm, newWktGeometry != null ? "있음" : "없음");
			return true;
			
		} catch (Exception e) {
			log.error("신규/수정된 행정동 처리 중 오류 발생: {} - {}", 
				hjdong.getHjdongCd(), hjdong.getHjdongNm(), e);
            saveErrorLog(jobExecutionId, "6", e.toString());
			throw e;
		}
	}
	
	/**
	 * TMAP API를 통한 폴리곤 정보 조회
	 * @param hjdong 행정동 정보
	 * @param normalizedHjdongNm 정규화된 행정동명
	 * @return WKT 형식의 폴리곤 데이터 또는 null
	 */
	private String getPolygonFromTmapApi(MsHjdongDto hjdong, String normalizedHjdongNm, String jobExecutionId) {
		try {
			// 1. TMAP API 카테고리 결정
			String category = hjdongCodeService.determineCategory(hjdong.getHjdongCd());
			log.debug("TMAP API 카테고리 결정: {} -> {}", hjdong.getHjdongCd(), category);
			
			// 2. TMAP API 공간검색
			String regionId = tmapApiService.searchSpatialRegion(
				hjdong.getHjdongCd(), 
				normalizedHjdongNm, 
				category,
				hjdong.getCtpKorNm(),
				hjdong.getSigKorNm(),
                jobExecutionId
			);

            // 3. 원본명으로 TMAP API 공간 재검색
            if (regionId == null) {
                regionId = tmapApiService.searchSpatialRegion(
                        hjdong.getHjdongCd(),
                        hjdong.getHjdongNm(),
                        category,
                        hjdong.getCtpKorNm(),
                        hjdong.getSigKorNm(),
                        jobExecutionId
                );
            }
			
			if (regionId == null) {
				log.warn("TMAP API에서 regionId를 찾지 못함: {} - {}", hjdong.getHjdongCd(), normalizedHjdongNm);
				return null;
			}
			
			// 4. TMAP API 폴리곤 정보 조회
			String polygonData = tmapApiService.getPolygonData(regionId, jobExecutionId);
			if (polygonData == null) {
				log.warn("TMAP API에서 폴리곤 정보를 조회하지 못함: regionId={}", regionId);
				return null;
			}

			// 5. GeoJSON을 WKT로 변환
			String wktGeometry = geometryConverterService.convertGeoJsonToWkt(polygonData, jobExecutionId);
			if (wktGeometry == null) {
				log.warn("GeoJSON을 WKT로 변환 실패: regionId={}", regionId);
				return null;
			}
			
			log.debug("TMAP API 폴리곤 조회 성공: {} - {} (regionId: {})", 
				hjdong.getHjdongCd(), normalizedHjdongNm, regionId);
			
			return wktGeometry;

		} catch (ResourceAccessException e) {
			log.error("TMAP 서버 접근 실패: {} - {}", 
				hjdong.getHjdongCd(), hjdong.getHjdongNm(), e);
			throw e; // ResourceAccessException은 다시 던져서 호출하는 쪽에서 처리
		} catch (Exception e) {
			log.error("TMAP API 폴리곤 조회 중 오류 발생: {} - {}", 
				hjdong.getHjdongCd(), hjdong.getHjdongNm(), e);
			return null;
		}
	}

	/**
	 * IF_MS_HJDONG_POLYGON 테이블에 폴리곤 데이터 저장
	 * @param polygon 폴리곤 데이터
	 * @param hjdong 행정동 정보 (regionId 등 추가 정보용)
	 */
	private void saveOrUpdateIfPolygon(MsHjdongPolygonDto polygon, MsHjdongDto hjdong) {
		try {
			// IF_MS_HJDONG_POLYGON DTO 생성
			IfMsHjdongPolygonDto ifPolygon = IfMsHjdongPolygonDto.builder()
				.hjdongCd(polygon.getHjdongCd())
				.hjdongNm(polygon.getHjdongNm())
				.sigKorNm(polygon.getSigKorNm())
				.ctpKorNm(polygon.getCtpKorNm())
				.spatialGeom(polygon.getSpatialGeom())
				.ifFlag("N")  // 신규 및 변경 내용은 IF_FLAG = 'N'으로 설정
				.ifDate(LocalDateTime.now())
				.ifTimestamp(LocalDateTime.now())
				.build();
			
			// IF_MS_HJDONG_POLYGON 테이블에 삽입
			log.debug("IF_MS_HJDONG_POLYGON 테이블에 데이터 삽입: {}", polygon.getHjdongCd());
			commonDao.insertQuartz("msHjdongPolygonService.insertIfHjdongPolygon", ifPolygon);
			
			log.debug("IF_MS_HJDONG_POLYGON 데이터 저장 완료: {} - {}", polygon.getHjdongCd(), polygon.getHjdongNm());
			
		} catch (Exception e) {
			log.error("IF_MS_HJDONG_POLYGON 데이터 저장 실패: {} - {}", 
				polygon.getHjdongCd(), polygon.getHjdongNm(), e);
			throw e;
		}
	}
	
	/**
	 * IF_MS_HJDONG_POLYGON 테이블의 IF_FLAG = 'N'인 데이터를 MS_HJDONG_POLYGON에 적용
	 * @param jobExecutionId 작업 실행 ID
	 */
	private void applyIfPolygonToMsPolygon(String jobExecutionId) {
		try {
			// IF_FLAG = 'N'인 데이터 조회
			List<IfMsHjdongPolygonDto> ifPolygonList = commonDao.selectList("msHjdongPolygonService.selectIfHjdongPolygonByIfFlag");
			log.info("MS_HJDONG_POLYGON에 적용할 IF 데이터 수: {}", ifPolygonList.size());
			
			if (ifPolygonList.isEmpty()) {
				log.info("적용할 IF 데이터가 없습니다.");
				return;
			}
			
			int successCount = 0;
			int errorCount = 0;
			
			for (IfMsHjdongPolygonDto ifPolygon : ifPolygonList) {
				try {
					// MS_HJDONG_POLYGON에 적용
					applySingleIfPolygonToMsPolygon(ifPolygon);
					
					// IF_FLAG를 'Y'로 업데이트 (적용 완료)
					ifPolygon.setIfFlag("Y");
					commonDao.update("msHjdongPolygonService.updateIfHjdongPolygonFlag", ifPolygon);
					
					successCount++;
					log.debug("IF 데이터 적용 완료: {} - {}", ifPolygon.getHjdongCd(), ifPolygon.getHjdongNm());
					
				} catch (Exception e) {
					log.error("IF 데이터 적용 중 오류 발생: {} - {}", 
						ifPolygon.getHjdongCd(), ifPolygon.getHjdongNm(), e);
					errorCount++;
					saveErrorLog(jobExecutionId, "8", "IF 데이터 적용 오류: " + e.getMessage());
				}
			}
			
			log.info("IF 데이터 적용 결과 - 전체: {}, 성공: {}, 오류: {}", 
				ifPolygonList.size(), successCount, errorCount);
			
		} catch (Exception e) {
			log.error("IF 데이터 적용 중 오류 발생", e);
			saveErrorLog(jobExecutionId, "8", "IF 데이터 적용 중 오류 발생: " + e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 단일 IF 데이터를 MS_HJDONG_POLYGON에 적용
	 * @param ifPolygon IF 데이터
	 */
	private void applySingleIfPolygonToMsPolygon(IfMsHjdongPolygonDto ifPolygon) {
		try {
			// 기존 데이터 존재 여부 확인
			MsHjdongPolygonDto checkDto = new MsHjdongPolygonDto();
			checkDto.setHjdongCd(ifPolygon.getHjdongCd());
			int existingCount = commonDao.selectOne("msHjdongPolygonService.countHjdongPolygonByHjdongCd", checkDto);
			
			// fromDate는 업데이트 기준일로 설정
			LocalDateTime updateDate = LocalDateTime.now();
			String fromDate = updateDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
			
			// MsHjdongPolygonDto 객체 생성
			MsHjdongPolygonDto polygonDto = MsHjdongPolygonDto.builder()
				.hjdongCd(ifPolygon.getHjdongCd())
				.hjdongNm(ifPolygon.getHjdongNm())
				.spatialGeom(ifPolygon.getSpatialGeomStr() != null ? new StringReader(ifPolygon.getSpatialGeomStr()) : null)
				.ctpKorNm(ifPolygon.getCtpKorNm())
				.sigKorNm(ifPolygon.getSigKorNm())
				.delYn("N")
				.fromDate(fromDate)
				.toDate("99991231")
				.addDate(LocalDateTime.now())
				.editDate(LocalDateTime.now())
				.addWho("BATCH_SYSTEM")
				.editWho("BATCH_SYSTEM")
				.build();
			
			if (existingCount > 0) {
				log.debug("기존 폴리곤 데이터 종료일 업데이트: {}", polygonDto.getHjdongCd());
				// 기존 레코드의 종료일을 변경일 기준 +2일로 업데이트
				commonDao.update("msHjdongPolygonService.updateHjdongPolygonEndDate", polygonDto);
				
				// 새로운 폴리곤 데이터의 시작일을 작성일 기준 +3일로 설정
				LocalDateTime startDate = LocalDateTime.now().plusDays(3);
				String startDateStr = startDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
				polygonDto.setFromDate(startDateStr);
				polygonDto.setAddDate(LocalDateTime.now());
				polygonDto.setEditDate(LocalDateTime.now());
				polygonDto.setDelYn("N");

				// fromDate 기준으로 존재 여부 체크 (하루에 스케줄 2번 이상 경우)
				checkDto.setFromDate(startDateStr);
				existingCount = commonDao.selectOne("msHjdongPolygonService.countHjdongPolygonByHjdongCdAndFromDate", checkDto);

				if(existingCount > 0){
					log.debug("새 폴리곤 데이터 업데이트 (시작일: {}): {}", startDateStr, polygonDto.getHjdongCd());
					commonDao.insertQuartz("msHjdongPolygonService.updateHjdongPolygon", polygonDto);
				}else if (polygonDto.getSpatialGeom() != null){ //폐지가 아닐경우에만
					log.debug("새 폴리곤 데이터 삽입 (시작일: {}): {}", startDateStr, polygonDto.getHjdongCd());
					commonDao.insertQuartz("msHjdongPolygonService.insertHjdongPolygon", polygonDto);
				}
			} else {
				// 새 데이터 삽입
				log.debug("새 폴리곤 데이터 삽입: {}", polygonDto.getHjdongCd());
				commonDao.insertQuartz("msHjdongPolygonService.insertHjdongPolygon", polygonDto);
			}
			
			log.debug("MS_HJDONG_POLYGON 데이터 적용 완료: {} - {}", polygonDto.getHjdongCd(), polygonDto.getHjdongNm());
			
		} catch (Exception e) {
			log.error("MS_HJDONG_POLYGON 데이터 적용 실패: {} - {}", 
				ifPolygon.getHjdongCd(), ifPolygon.getHjdongNm(), e);
			throw e;
		}
	}

    private static final String FILE_PATH = ContextUtil.getProperty("cf.file.polygonDataPath");;
    private final SqlSessionFactory sqlSessionFactory;
    private final ObjectMapper objectMapper;
    public void savePolygonFile(String jobExecutionId) throws Exception {
        for (String type: List.of("CTP", "SIG", "HJDONG")) {
            File ingFile = new File(FILE_PATH + type.toLowerCase() + ".json.ing");
            File dir = ingFile.getParentFile();
            if (!ingFile.getParentFile().exists()) {
                if (!dir.exists() && !dir.mkdirs()) {
                    log.error("디렉토리 생성 실패: {}", dir.getAbsolutePath());
                    throw new IOException("디렉토리 생성 실패: " + dir.getAbsolutePath());
                }
            }
            try (SqlSession session = sqlSessionFactory.openSession();
                 OutputStream out = new FileOutputStream(ingFile)) {
                try (Cursor<MsHjdongPolygonDto> cursor = session.selectCursor("msHjdongPolygonService.getHjdongPolygonList", type)) {
                    for (MsHjdongPolygonDto item : cursor) {
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
						String msg = MessageFormatter.format("임시 파일을 최종 파일로 변경 완료: {}({})",
							targetFile.getAbsolutePath(), Files.getLastModifiedTime(targetFile.toPath()).toString()).getMessage();
                        log.info(msg);
						saveErrorLog(jobExecutionId, "0", "7", msg);
                    } else {
                        Files.move(ingFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
						String msg = MessageFormatter.format("임시 파일을 최종 파일로 이동 완료 (대체 방법): {}({})",
							targetFile.getAbsolutePath(), Files.getLastModifiedTime(targetFile.toPath()).toString()).getMessage();
                        log.info(msg);
						saveErrorLog(jobExecutionId, "0", "7", msg);
                    }
                    log.info("{} 생성 완료. 파일 크기={} bytes", targetFile.getAbsolutePath(), targetFile.length());
                }
            } catch (IOException e) {
				String errMsg = MessageFormatter.format("파일 IO 오류: {}", e.getMessage()).getMessage();
                log.error(errMsg, e);
                if (ingFile.delete()) {
                    log.info("임시 파일 삭제 완료: {}", ingFile.getName());
                }
                saveErrorLog(jobExecutionId, "7", errMsg);
            } catch (Exception e) {
                log.error("폴리곤 데이터 로딩중 오류 발생", e);
                if (ingFile.delete()) {
                    log.info("임시 파일 삭제 완료: {}", ingFile.getName());
                }
                saveErrorLog(jobExecutionId, "7", "폴리곤 데이터 로딩중 오류 발생: " + e.getMessage());
            }
        }
    }

    public void saveErrorLog(String jobExecutionId, String lineNo, String resultCode, String resultMsg){
        BatchLogParamsDto batchLogDto = BatchLogParamsDto.builder()
                .jobExecutionId(jobExecutionId)
                .jobName("msHjdongPolygonJob")
                .jobDiv("JAVA")
                .nodeLevel(0)
                .jobStatus("INFO")
                .command("")
                .lineNo(lineNo)
                .resultCode(resultCode)
                .resultMsg(resultMsg)
                .build();

        commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
    }

    public void saveErrorLog(String jobExecutionId, String lineNo, String resultMsg){
		saveErrorLog(jobExecutionId, lineNo, "-1", resultMsg);
    }

	public enum MvmnResCd {
        EMPTY(""),
		CREATE("31"),
		UPDATE("34"),
		DELETE("63");

		private final String code;

		MvmnResCd(String code) {
			this.code = code;
		}
		public String getCode() {
			return code;
		}
		public static MvmnResCd fromCode(String code) {
			for (MvmnResCd resCd : values()) {
				if (resCd.code.equals(code)) {
					return resCd;
				}
			}
            return EMPTY;
		}
	}
}