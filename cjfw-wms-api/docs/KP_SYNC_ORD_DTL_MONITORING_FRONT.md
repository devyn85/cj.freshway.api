# KP 주문동기화 모니터링 상세(탭) - 프론트 연동 가이드

## API Base URL
- Base: `api/kp/syncOrdDtlMonitoring`
- 예: `POST /api/kp/syncOrdDtlMonitoring/v1.0/getDPInplanList`

## 요청 파라미터 (공통)

| 파라미터 | 타입 | 필수 | 설명 |
|---------|------|------|------|
| docno | string | O | 문서번호 (마스터에서 선택한 DOCNO) |
| dcCode | string | O | 센터코드 (콤마구분, KpSyncOrdMonitoring과 동일. 예: "2410,2440,2400") |

## 탭별 API 엔드포인트

| 탭 | GET | POST (Body) | 응답 리스트 타입 |
|----|-----|-------------|------------------|
| DP_INPLAN | GET /v1.0/getDPInplanList | POST /v1.0/getDPInplanList | DP 컬럼 (DCCODE, SLIPDT, SLIPNO, ...) |
| RT_INPLAN | GET /v1.0/getRTInplanList | POST /v1.0/getRTInplanList | RT 컬럼 |
| WD_INPLAN | GET /v1.0/getWDInplanList | POST /v1.0/getWDInplanList | WD 컬럼 |
| AJ_INPLAN | GET /v1.0/getAJInplanList | POST /v1.0/getAJInplanList | AJ 컬럼 |
| ST_INPLAN | GET /v1.0/getSTInplanList | POST /v1.0/getSTInplanList | ST 컬럼 |

## 호출 예시

### GET (Query String)
```
GET /api/kp/syncOrdDtlMonitoring/v1.0/getDPInplanList?docno=문서번호&dcCode=2410,2440,2400
```

### POST (Request Body)
```json
POST /api/kp/syncOrdDtlMonitoring/v1.0/getDPInplanList
Content-Type: application/json

{
  "docno": "문서번호",
  "dcCode": "2410,2440,2400"
}
```

## 응답 구조
- `ApiResult.data`: 탭별 그리드 로우 배열
- 그리드 컬럼 `dataField`는 응답 객체 키와 동일 (대문자: DCCODE, SLIPNO, CHK_STATUS 등)

## 프론트 화면 구성 제안
1. **마스터 화면**: 기존 주문동기화 모니터링 Header 그리드에서 행 선택 시 `DOCNO`, `DCCODE` 확보
2. **상세 영역**: 탭(DP / RT / WD / AJ / ST)별로 그리드 구성
3. **탭 클릭/선택 시**: 해당 탭 API 호출 후 `response.data`를 그리드 데이터소스에 바인딩
4. **공통 파라미터**: 선택된 마스터 행의 `docno`, `dcCode`(또는 콤마 구분 다중 센터)를 모든 탭 API 요청에 포함

## 프론트 소스 생성 요청 시 전달할 내용
- **메뉴/화면명**: KP > 주문동기화 모니터링 상세 (또는 탭 화면명)
- **API 스펙**: 위 엔드포인트 5개 + 요청/응답 필드 (Swagger에서 확인 가능)
- **화면 요구사항**: 탭 5개, 탭별 그리드, 마스터에서 선택한 docno/dcCode로 조회
- **참고 API**: `api/kp/syncOrdMonitoring` (마스터/디테일) 와 동일한 인증/베이스URL
