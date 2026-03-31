package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsCarrierDetailReqDto;
import cjfw.wms.ms.dto.MsCarrierDetailResDto;
import cjfw.wms.ms.dto.MsCarrierReqDto;
import cjfw.wms.ms.dto.MsCarrierResDto;
import cjfw.wms.ms.entity.MsCarrierEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MsCarrierService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "msCarrier.";
	private final UserContext userContext;
	private final CommonDao commonDao;
	
	/**
	 * @description : 운송사정보(목록) 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public List<MsCarrierResDto> getMasterList (MsCarrierReqDto msCarrierReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", msCarrierReqDto);
	}
	
	/**
	 * @description : 운송사정보(단건) 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	public MsCarrierDetailResDto getMaster(MsCarrierDetailReqDto msCarrierDetailReqDto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getMaster", msCarrierDetailReqDto);
	}
	
	/**
	 * @description : 운송사정보(목록) 추가
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public String saveMasterList(List<MsCarrierDetailReqDto> list) {
        if (null != list) {
            for (var dto : list) {
                var entity = ModelMapperUtil.map(dto, userContext, MsCarrierEntity.class);

                // 중복 검증
                // - 역할: DB 반영(INSERT/UPDATE) 전에 동일 운송사명/주소/키+유형 중복 여부를 검사하여 예외 처리
                validateDuplicateCarrier(entity);

                if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
                    commonDao.insert(SERVICEID_PREFIX +"saveMasterList", entity);
                } else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
                    commonDao.update(SERVICEID_PREFIX + "saveMaster", entity);
                }
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 운송사정보(단건) 수정
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public String saveMaster(List<MsCarrierDetailReqDto> list) {
        if (null != list) {
            for (var dto : list) {
                var entity = ModelMapperUtil.map(dto, userContext, MsCarrierEntity.class);

                // 중복 검증
                // - 역할: DB 반영(UPDATE) 전에 동일 운송사명/주소/키+유형 중복 여부를 검사하여 예외 처리
                validateDuplicateCarrier(entity);

                if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
                    commonDao.selectOne(SERVICEID_PREFIX +"saveMaster", entity);
                }
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    // ========================= 중복 검증 유틸리티 =========================
    // 운송사명 비교용 정규화 함수
    // - "(주)" 문자열 제거 후 앞뒤 공백 제거
    private String normalizeName(String value) {
        if (value == null) return "";
        return value.replace("(주)", "").trim();
    }

    // 주소 비교용 정규화 함수
    // - 앞뒤 공백 제거
    private String normalizeAddr(String value) {
        if (value == null) return "";
        return value.trim();
    }

    // 중복 검증 함수
    // - 목적: INSERT/UPDATE 수행 전 동일 운송사명/주소 또는 (운송사키+운송사유형) 동시 중복 여부 검사
    // - 범위: 동일 storerKey 내에서 비교, 삭제건(DEL_YN='Y') 제외, 수정 시 자기 자신(serialKey) 제외
    //
    // - 규칙:
    //   * 운송사명: "(주)" 제거 + trim 후 비교
    //   * 주소: trim 후 비교
    //   * 운송사키/유형: 둘 다 값이 있을 때 조합이 동일하면 중복으로 간주
    private void validateDuplicateCarrier(MsCarrierEntity entity) {
    	
        // - 역할: 비교 대상 운송사 목록 조회를 위한 검색 조건 생성
        var req = cjfw.wms.ms.dto.MsCarrierReqDto.builder()
                .storerKey(entity.getStorerKey())
                .build();
        
        // gStorerkey는 CommonDto의 전역 파라미터이므로 별도 세터로 지정
        req.setGStorerkey(entity.getStorerKey());

        // 기존 운송사 목록 조회
        // - 역할: DB로부터 비교 대상 운송사 목록 조회
        java.util.List<cjfw.wms.ms.dto.MsCarrierResDto> exists =
                commonDao.selectList(SERVICEID_PREFIX + "getMasterList", req);

        // 입력값 정규화
        // - 역할: 비교 기준이 되는 입력값(운송사명/주소) 가공본 생성
        String targetName   = normalizeName(entity.getDescription());
        String targetAddr1  = normalizeAddr(entity.getAddress1());
        String targetAddr2  = normalizeAddr(entity.getAddress2());
        String targetZip    = entity.getZipcode() == null ? "" : entity.getZipcode().trim();

        // 운송사키/유형 조합(사용자 용어: custKey/custType → 시스템 필드: carrierKey/carrierType)
        String targetCarrierKey   = entity.getCarrierKey() == null ? "" : entity.getCarrierKey().trim();
        String targetCarrierType  = entity.getCarrierType() == null ? "" : entity.getCarrierType().trim();
        String targetCarrierTypeUp= targetCarrierType.toUpperCase();
        String selfSerialKey      = entity.getSerialKey();

        for (cjfw.wms.ms.dto.MsCarrierResDto row : exists) {
            if (row == null) continue;

            // 삭제건 제외
//            if ("Y".equalsIgnoreCase(row.getDelYn())) continue;

            // 수정 시 자기 자신 제외
            if (selfSerialKey != null && selfSerialKey.equals(row.getSerialKey())) continue;
            

            /*
             * 2025.10.28 수정
             * - 1차 운송사 내 동일 코드값 있으면 안됨
             * - 2차 운송사 내 동일값 있으면 안됨
             * - 1차 운송사이면서 2차 운송사일 수 있음
             * */
            // (1) 운송사명 중복 검사
            if (!targetName.isEmpty() && targetName.equals(normalizeName(row.getDescription())) && targetCarrierTypeUp.equals(row.getCarrierType().toUpperCase())) {
                throw new UserHandleException("동일한 운송사명을 가진 운송사가 존재합니다.");
            }

            // (2) 기본주소 중복 검사
            if (!targetAddr1.isEmpty() && targetAddr1.equals(normalizeAddr(row.getAddress1())) && targetCarrierTypeUp.equals(row.getCarrierType().toUpperCase())) {
                throw new UserHandleException("동일한 기본주소를 가진 운송사가 존재합니다.");
            }

            // (3) 상세주소 중복 검사
            if (!targetAddr2.isEmpty() && targetAddr2.equals(normalizeAddr(row.getAddress2())) && targetCarrierTypeUp.equals(row.getCarrierType().toUpperCase())) {
                throw new UserHandleException("동일한 상세주소를 가진 운송사가 존재합니다.");
            }

            // (4) 운송사키 + 운송사유형 동시 중복 검사 (carrierType이 SUBC 또는 LOCAL 인 경우 수행)
            if (!targetCarrierKey.isEmpty() && !targetCarrierType.isEmpty()
                    && ("SUBC".equalsIgnoreCase(targetCarrierType) || "LOCAL".equalsIgnoreCase(targetCarrierType))) {
                String rowCarrierKey  = row.getCarrierKey()  == null ? "" : row.getCarrierKey().trim();
                String rowCarrierType = row.getCarrierType() == null ? "" : row.getCarrierType().trim();
                if (targetCarrierKey.equals(rowCarrierKey) && targetCarrierType.equals(rowCarrierType)) {
                    throw new UserHandleException("동일한 운송사키와 운송사유형을 가진 운송사가 존재합니다.");
                }
            }

            // (5) 운송사코드가 null이거나 'SUBC'로 시작하는 경우, [운송사명 + 우편번호 + 기본주소 + 상세주소] 동시 중복 검사
            boolean keyNullOrSubc = targetCarrierKey.isEmpty() || targetCarrierKey.toUpperCase().startsWith("SUBC");
            if (keyNullOrSubc) {
                String rowName  = normalizeName(row.getDescription());
                String rowZip   = row.getZipcode() == null ? "" : row.getZipcode().trim();
                String rowAddr1 = normalizeAddr(row.getAddress1());
                String rowAddr2 = normalizeAddr(row.getAddress2());

                if (!targetName.isEmpty() && !targetZip.isEmpty() && !targetAddr1.isEmpty() && !targetAddr2.isEmpty()) {
                    if (targetName.equals(rowName)
                            && targetZip.equals(rowZip)
                            && targetAddr1.equals(rowAddr1)
                            && targetAddr2.equals(rowAddr2)) {
                        throw new UserHandleException("동일한 운송사명/우편번호/기본주소/상세주소를 가진 운송사가 존재합니다.");
                    }
                }
            }
        }
        
        /**
         * 2025.11.19 수정
         * - "영문 약어" 추가
         */
        // 신규행 입력시에만 검증
        if ("I".equalsIgnoreCase(entity.getRowStatus()) && "SUBC".equalsIgnoreCase(targetCarrierType)) {
            String memo = entity.getMemo();
            String targetType = entity.getCarrierType().toUpperCase();
            
            // 1) 영문 약어 존재 여부 검사
            if (memo == null || memo.isEmpty()) {
            	throw new UserHandleException("신규 입력 시 영문 약어는 필수값입니다.");
            }
            
            // 2) 영문 약어 형식 검사
            if (!memo.matches("^[A-Z]{3}$")) {
            	throw new UserHandleException("영문 약어는 영문 대문자 세 글자여야 합니다.");
            }
            
            // 3) 2차 운송사라면 접두어 "S-"를 추가(ex. 입력값이 CJL일 때 => S-CJL)
            // FWNEXTWMS-5487 주석처리
//            if ("SUBC".equalsIgnoreCase(targetType)) {
//            	entity.setMemo("S" + memo);
//            }
            	
            // 4) 운송사 유형이 동일한 기존행 항목중 영문약어가 동일한 항목이 있는지 체크
            for (MsCarrierResDto row : exists) {

                if (row == null) continue;
                if (selfSerialKey != null && selfSerialKey.equals(row.getSerialKey())) continue;

                // 4) 운송사 유형이 동일한 기존행 항목중 영문약어가 동일한 항목이 있는지 체크
                if ("I".equalsIgnoreCase(entity.getRowStatus())) {

                    String targetMemo = entity.getMemo();

                    String rowCarrierType = row.getCarrierType() == null ? "" : row.getCarrierType().toUpperCase();
                    String rowMemo = row.getMemo();

                    if (!targetType.isEmpty()
                            && targetType.equals(rowCarrierType)
                            && targetMemo != null && !targetMemo.isEmpty()
                            && rowMemo != null && !rowMemo.isEmpty()
                            && targetMemo.equals(rowMemo)) {
                        throw new UserHandleException("동일 운송사유형 내에서 중복된 영문 약어가 존재합니다.");
                    }
                }
            }
            
		}
    }
}
