package cjfw.wms.tm.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.drm.DrmUtil;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.MessageUtil;
import cjfw.wms.tm.dto.TmDispatchExcelDto;
import cjfw.wms.tm.dto.TmDispatchJsonDto;
import cjfw.wms.tm.dto.TmDispatchJsonRowDto;
import cjfw.wms.tm.dto.TmDispatchRowDto;
import cjfw.wms.tm.dto.TmDispatchUploadResDto;
import cjfw.wms.tm.dto.TmDispatchValidationErrorDto;
import cjfw.wms.tm.dto.TmInplanUpdateDto;
import cjfw.wms.tm.dto.TmapDispatchRouteResDto;
import cjfw.wms.tm.service.TmDispatchUploadService;
import cjfw.wms.tm.service.TmTmapRouteService;
import cjfw.wms.tm.validator.TmDispatchValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : han@wemeetmobility.com
 * @date : 2025.11.28
 * @description : TM 배차 업로드 Controller (거래처 필터링 지원)
 */
@Tag(name = "TM > 배차관리", description = "TM 수동배차 업로드")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/tm/dispatch/manual")
@Validated
public class TmDispatchUploadController {

	private final UserContext userContext;
	private final TmDispatchUploadService tmDispatchUploadService;
	private final TmDispatchValidator tmDispatchValidator;
	private final TmTmapRouteService tmTmapRouteService;

	/**
	 * 배차 데이터 업로드 (Excel)
	 *
	 * @param file         업로드 파일 (.xlsx)
	 * @param dccode       물류센터 코드
	 * @param deliveryDate 배송일자 (YYYYMMDD)
	 * @return 업로드 결과 (총건수, 성공건수, 실패건수, 오류목록)
	 */
	@Operation(summary = "배차 데이터 업로드 (Excel) - 거래처 필터링", description = """
			배차 엑셀 파일을 업로드하여 TM_INPLAN을 업데이트합니다.

			## 업로드 처리 단계
			1. **Excel 파일 검증** - 파일 형식, 필수 컬럼 확인
			2. **데이터 파싱** - Excel → DTO 변환
			3. **비즈니스 검증** - 차량번호, 배송일자 등 검증
			4. **TM_INPLAN 업데이트** - 차량번호, 회차 등 업데이트
			5. **응답 반환** - 성공/실패 건수 및 오류 목록

			## 필수 파라미터
			- **file**: Excel 파일 (.xlsx)
			- **dccode**: 물류센터 코드
			- **deliveryDate**: 배송일자 (YYYYMMDD)

			## 업데이트 대상 컬럼
			- 차량번호
			- 회차
			- 기타 배차 정보

			## 응답 데이터
			- totalCount: 총 건수
			- successCount: 성공 건수
			- failCount: 실패 건수
			- errors: 검증 실패 목록
			""")

	@PostMapping(value = "/uploadSave")
	public ApiResult<TmDispatchUploadResDto> uploadSave(@RequestBody TmDispatchJsonDto jsonDto) {

		return tmDispatchUploadService.saveUpload(jsonDto, userContext);
	}

	@PostMapping(value = "/uploadValidation", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResult<TmDispatchExcelDto> uploadValidtaion(

			@Parameter(description = "엑셀 파일 (.xlsx)", required = true) @RequestParam("file") MultipartFile file,

			@Parameter(description = "물류센터 코드", required = true, example = "2460") @RequestParam("dccode") @NotBlank(message = "물류센터 코드는 필수입니다") String dccode,

			@Parameter(description = "배송일자 (YYYYMMDD)", required = true, example = "20251019") @RequestParam("deliveryDate") @NotBlank(message = "배송일자는 필수입니다") String deliveryDate)
			throws IOException {

		if (Objects.isNull(file) || file.isEmpty()) {
			log.warn("업로드된 파일이 없습니다.");
			return ApiResult.createResult("파일이 없습니다.", 400);
		}
		log.info("배차 데이터 업로드 요청 - dccode={}, deliveryDate={}, filename={}", dccode, deliveryDate,
				file.getOriginalFilename());



		// 1. Excel 파일을 TmDispatchExcelDto로 변환
		TmDispatchExcelDto excelDto = TmDispatchExcelDto.from(file);


		// 2. 데이터 존재 여부 확인
		if (excelDto.isNoContent()) {
			log.warn("엑셀 파일에 데이터가 없습니다.");
			return ApiResult.createResult("엑셀 파일에 데이터가 없습니다.", 400);
		}

		// 3. 유효하지 않은 행 확인 (Phase 0: Excel 타입 검증 실패)
		if (excelDto.hasInvalidRows()) {
			log.warn("유효하지 않은 행이 {}건 발견되었습니다.", excelDto.getInvalidRows().size());

			int totalCount = excelDto.getTotalCount();
			int failedCount = excelDto.getInvalidRows().size();

			// 오류 요약 목록 생성
			List<TmDispatchValidationErrorDto> errors = createValidationErrors(excelDto.getInvalidRows());

			// Phase 0 실패 응답 반환
			TmDispatchUploadResDto failResult = TmDispatchUploadResDto.builder().totalCount(totalCount)
					.successCount(totalCount - failedCount).failCount(failedCount).errors(errors).build();

			return ApiResult.createResult(excelDto);
		}
		// 4. 차량번호 검증
		excelDto = tmDispatchValidator.validateVehicleInfo(excelDto, dccode);

		// 5. 휴일 검정
		excelDto = tmDispatchValidator.validateHolidayInfo(excelDto, dccode);


		// 6. TM_INPLAN 존재 여부 검증
		String storerkey = userContext.getStorerkey();

		excelDto = tmDispatchValidator.validateTmInplan(excelDto, dccode, deliveryDate, storerkey);
		return ApiResult.createResult(excelDto);

	}

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResult<TmDispatchUploadResDto> uploadDispatchExcel(
			@Parameter(description = "엑셀 파일 (.xlsx)", required = true) @RequestParam("file") MultipartFile file,

			@Parameter(description = "물류센터 코드", required = true, example = "2600") @RequestParam("dccode") @NotBlank(message = "물류센터 코드는 필수입니다") String dccode,

			@Parameter(description = "배송일자 (YYYYMMDD)", required = true, example = "20251019") @RequestParam("deliveryDate") @NotBlank(message = "배송일자는 필수입니다") String deliveryDate) {
		// 파일 유무 검증
		if (Objects.isNull(file) || file.isEmpty()) {
			log.warn("업로드된 파일이 없습니다.");
			return ApiResult.createResult("파일이 없습니다.", 400);
		}

		log.info("배차 데이터 업로드 요청 - dccode={}, deliveryDate={}, filename={}", dccode, deliveryDate,
				file.getOriginalFilename());

		// 1. Excel 파일을 TmDispatchExcelDto로 변환
		TmDispatchExcelDto excelDto = TmDispatchExcelDto.from(file);

		// 2. 데이터 존재 여부 확인
		if (excelDto.isNoContent()) {
			log.warn("엑셀 파일에 데이터가 없습니다.");
			return ApiResult.createResult("엑셀 파일에 데이터가 없습니다.", 400);
		}

		log.info("Excel 파일 파싱 완료 - 총 {}건", excelDto.getTotalCount());

		// 3. 유효하지 않은 행 확인 (Phase 0: Excel 타입 검증 실패)
		if (excelDto.hasInvalidRows()) {
			log.warn("유효하지 않은 행이 {}건 발견되었습니다.", excelDto.getInvalidRows().size());

			int totalCount = excelDto.getTotalCount();
			int failedCount = excelDto.getInvalidRows().size();

			// 오류 요약 목록 생성
			List<TmDispatchValidationErrorDto> errors = createValidationErrors(excelDto.getInvalidRows());

			// Phase 0 실패 응답 반환
			TmDispatchUploadResDto failResult = TmDispatchUploadResDto.builder().totalCount(totalCount)
					.successCount(totalCount - failedCount).failCount(failedCount).errors(errors).build();

			return ApiResult.createResult(failResult);
		}

		// 4. UserContext에서 storerkey 가져오기
		String storerkey = userContext.getStorerkey();

		// 4-1. 차량번호 검증
		excelDto = tmDispatchValidator.validateVehicleInfo(excelDto, dccode);

		// 4-2. 차량번호 검증 실패 확인
		if (excelDto.hasInvalidRows()) {
			log.warn("차량번호 검증 실패 - 유효하지 않은 행이 {}건 발견되었습니다.", excelDto.getInvalidRows().size());

			int totalCount = excelDto.getTotalCount();
			int failedCount = excelDto.getInvalidRows().size();

			// 오류 요약 목록 생성
			List<TmDispatchValidationErrorDto> errors = createValidationErrors(excelDto.getInvalidRows());

			// 차량번호 검증 실패 응답 반환
			TmDispatchUploadResDto failResult = TmDispatchUploadResDto.builder().totalCount(totalCount)
					.successCount(totalCount - failedCount).failCount(failedCount).errors(errors).build();

			return ApiResult.createResult(failResult);
		}

		// 4-3. TM_INPLAN 존재 여부 검증
		excelDto = tmDispatchValidator.validateTmInplan(excelDto, dccode, deliveryDate, storerkey);

		// 4-4. TM_INPLAN 검증 실패 확인
		if (excelDto.hasInvalidRows()) {
			log.warn("TM_INPLAN 검증 실패 - 유효하지 않은 행이 {}건 발견되었습니다.", excelDto.getInvalidRows().size());

			int totalCount = excelDto.getTotalCount();
			int failedCount = excelDto.getInvalidRows().size();

			// 오류 요약 목록 생성
			List<TmDispatchValidationErrorDto> errors = createValidationErrors(excelDto.getInvalidRows());

			// TM_INPLAN 검증 실패 응답 반환
			TmDispatchUploadResDto failResult = TmDispatchUploadResDto.builder().totalCount(totalCount)
					.successCount(totalCount - failedCount).failCount(failedCount).errors(errors).build();

			return ApiResult.createResult(failResult);
		}

		// 5. Excel 데이터를 TM_INPLAN 업데이트용 DTO로 변환
		List<TmInplanUpdateDto> updateList = convertToInplanUpdateList(excelDto);
		log.info("업데이트용 데이터 변환 완료 - {}건", updateList.size());

		// 6. T-map 경로 계산 (Phase 2)
		Map<String, TmapDispatchRouteResDto> routeResultsByCarno = tmTmapRouteService
				.getRoutesForVehicles(updateList, dccode, deliveryDate);
		log.info("T-map 경로 계산 완료 - 차량: {}대", routeResultsByCarno.size());

		// 7. DB 업데이트 (Phase 3)
		TmDispatchUploadResDto result = tmDispatchUploadService.saveDispatchExcel(updateList, routeResultsByCarno,
				dccode, deliveryDate, storerkey);

		// 8. 로그 및 응답 반환
		log.info("배차 데이터 업로드 완료 - 총: {}, 성공: {}, 실패: {}", result.getTotalCount(), result.getSuccessCount(),
				result.getFailCount());

		return ApiResult.createResult("수동 배차 업로드가 완료되었습니다.", 201);
	}

	/**
	 * ValidationErrorDto 리스트 생성
	 *
	 * @param invalidRows 유효하지 않은 행 목록
	 * @return 검증 오류 목록
	 */
	private List<TmDispatchValidationErrorDto> createValidationErrors(List<TmDispatchRowDto> invalidRows) {
		List<TmDispatchValidationErrorDto> errors = new ArrayList<>();

		invalidRows.forEach(row -> {
			row.getInvalidCells().forEach(cell -> {
				TmDispatchValidationErrorDto error = TmDispatchValidationErrorDto.builder().no(row.getRowNumber())
						.columnName(cell.getColumn().getKoreanName()).errorMessage(cell.getErrorReason())
						.inputValue(cell.getValue() != null ? cell.getValue().toString() : "").build();
				errors.add(error);

				log.warn("검증 실패 - 행: {}, 컬럼: {}, 오류: {}, 입력값: {}", row.getRowNumber(), cell.getColumn().getKoreanName(),
						cell.getErrorReason(), cell.getValue());
			});
		});

		return errors;
	}




	/**
	 * ValidationErrorDto 리스트 생성
	 *
	 * @param invalidRows 유효하지 않은 행 목록
	 * @return 검증 오류 목록
	 */
	private List<TmDispatchValidationErrorDto> createValidationJsonErrors(List<TmDispatchJsonRowDto> invalidRows) {
		List<TmDispatchValidationErrorDto> errors = new ArrayList<>();

		invalidRows.forEach(row -> {

			row.getInvalidCells().forEach(cell -> {
				TmDispatchValidationErrorDto error = TmDispatchValidationErrorDto.builder().no(row.getRowNumber())
						.columnName(cell.getColumn().getKoreanName()).errorMessage(cell.getErrorReason())
						.inputValue(cell.getValue() != null ? cell.getValue().toString() : "").build();
				errors.add(error);

				log.warn("검증 실패 - 행: {}, 컬럼: {}, 오류: {}, 입력값: {}", row.getRowNumber(), cell.getColumn().getKoreanName(),
						cell.getErrorReason(), cell.getValue());
			});
		});

		return errors;
	}

	/**
	 * Excel 행 데이터를 TM_INPLAN 업데이트용 DTO 리스트로 변환
	 *
	 * @param excelDto Excel DTO
	 * @return TM_INPLAN 업데이트용 DTO 리스트
	 */
	private List<TmInplanUpdateDto> convertToInplanUpdateList(TmDispatchExcelDto excelDto) {
		return excelDto.getRows().stream().map(row -> {
			TmInplanUpdateDto.TmInplanUpdateDtoBuilder builder = TmInplanUpdateDto.builder();

			// TM_INPLAN 업데이트 가능한 모든 컬럼 값 추출
			row.getCells().forEach(cell -> {
				Object value = cell.getValue();
				switch (cell.getColumn()) {
					// WHERE 조건 (PRIMARY KEY)
					case TRUTH_CUST_KEY -> builder.truthCustKey((String) value);
					case SLIP_NO -> builder.slipNo((String) value);
					//case DOC_TYPE -> builder.docType((String) value);
					// UPDATE 대상
					case POP_NAME -> builder.deliveryGroup((String) value);
					case AREA_GROUP_NAME -> builder.districtGroup((String) value);
					case AREA_NAME -> builder.districtCode((String) value);
					case DONG_CODE -> builder.hjdongCd((String) value);
					case ZIP_CODE -> builder.zipcode((String) value);
					case CUST_KEY -> builder.custKey((String) value);
					//case DELIVERY_PRIORITY -> builder.deliveryPriority((String) value);
					case CARNO -> builder.carno((String) value);
					case CONTRACT_TYPE -> builder.contractType((String) value);
					case DELIVERY_TYPE -> builder.deliveryType((String) value);
					case TURN -> builder.priority(tmDispatchUploadService.getInteger(value));
					case TOTAL_WEIGHT -> builder.weight(tmDispatchUploadService.getDouble(value));
					case TOTAL_CUBE -> builder.cube(tmDispatchUploadService.getDouble(value));
					default -> {
						log.warn("알 수 없는 case");
					}
				}
			});

			return builder.build();
		}).toList();
	}
}
