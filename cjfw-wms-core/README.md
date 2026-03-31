# cjfw-wms-core

## [20250707]ContextUtil
- return getProperty(propertyName, null) - > return getProperty(propertyName, "")

## [20250616]StringUtil.nvl() 추가
  - mybatis-config.xml
    - 인터셉트 cjfw-wms-com에 구성
    - StatementInterceptor.java 주석 
    - MybatisInterceptor.java 신규 추가
     
  - 신규인터셉트 MybatisInterceptor.java
    - 개인정보마스킹,sql인젝션, 쿼리포맷 변경 적용   

## [20250613]StringUtil.nvl() 추가

- cjfw-wms-core/src/main/java/cjfw/core/utility/StringUtil.java

## [20250602]Exception 최종 Custom처리

  - Exception 최종 처리
    - core Exceptin 추상화 처리
     - cjfw-wms-core/src/main/java/cjfw/core/web/exception/ExceptionAdvice.java
    - com Exceptin 상속 처리
     - cjfw-wms-com/src/main/java/cjfw/wms/common/exception/CustomExceptionAdvice.java
  - LogicOne 패키지 공통처리 Service 추가
     - cjfw-wms-com/src/main/java/cjfw/wms/cm/service/CmCommonService.java

 