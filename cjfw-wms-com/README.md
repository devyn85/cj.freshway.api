# cjfw-wms-com

## [20250917]@Valid 필수 처리 시 WMS에 맞게 메세지 커스트마이징 추가
  - CustomExceptionAdvice.java 수정
    - @Valid 필수 처리 시 WMS에 맞게 메세지 커스트마이징
  - methodArgumentNotValidException.java @Override 처리


## [20250616]StringUtil.nvl() 추가
  - mybatis-config.xml
    - 인터셉트 cjfw-wms-com에 구성
    - MybatisInterceptor.java 신규 추가
     
  - 신규인터셉트 MybatisInterceptor.java
    - 개인정보마스킹,sql인젝션, 쿼리포맷 변경 적용   
    
  - 마스킹 어노테이션 추가
    - common/annotation/MaskingAcntno.java 외 18종
    - MaskingUtil.java 신규 추가
  

## [20250530]Exception 추가
  - CustomExceptionAdvice.java 신규추가
    - ExceptionAdvice extends 처리
    - ExceptionAdvice abstract 처리
    - SystemException/UserHandleException 상세처리
  - ExceptionDto.java 추가

## [20250530]CommonConstants 추가
  - CommonConstants.java 신규 추가
  - CommonProcedureDto.java 신규추가
  
  
## [202505이전]
  - CommonDto.java 신규 추가  