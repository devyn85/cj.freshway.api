package cjfw.core.dataaccess.interceptor;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : OO 기능을 구현한 Controller Class
 *  pagehelper-spring-boot-starter 커스터마이징 클래스
	  - 기존 소스는 파라메타 중 RowBounds가 DEFAULT가 아니면 페이정 쿼리를 적용
	    -> CommonDao.java의 모든 selectList()메소드의 파라메타 "new RowBounds(0, maxResultRows + 1)"가 추가 되어 있음
	    -> 결과적으로 모든 selectList쿼리에 페이징 쿼리가 적용되는 문제
	  ==> 페이징 관련 파라메타가 없으면 skip 되도록 커스터마이징
	
	 [yaml 설정 참조 용]
	 pagehelper:
	   	dialect: cjfw.core.dataaccess.interceptor.MybatisPageHelper
	  	reasonable: true  # 0 <= pageNum <= pages 로 실행
	  	helper-dialect: oracle   # DB지정[oracle, mysql, mariadb, sqlite, hsqldb, postgresql, db2, sqlserver, informix, h2, sqlserver2012, derby]
	  	# auto-dialect: true # DB 자동 설정 (default : false)
	  	# page-size-zero: false # pageSize=0 으로 실행 (default : false)
	  	# offset-as-page-num: false # RowBound를 사용할 때 offset param을 pageNum으로 사용 (default : false)
	  	# row-bounds-with-count: false # RowBound를 사용할 때 Count Query 수행 (default : false)
	  	# auto-runtime-dialect: false # Runtime에 DB 자동 설정. auto-dialect 보다 우선 (default : false)
	  	# close-conn: true  # DB Connection을 닫을 지 여부 (default : true)
	  	# default-count: true
	  	# supportMethodsArguments: true
	  	# params: count=countSql
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Slf4j
public class MybatisPageHelper extends PageHelper {

	/**
	 * 
	 * @overridden  : @see com.github.pagehelper.PageHelper#skip(org.apache.ibatis.mapping.MappedStatement, java.lang.Object, org.apache.ibatis.session.RowBounds)
	 * @description : skip 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
    @Override
    public boolean skip(MappedStatement ms, Object parameterObject, RowBounds rowBounds) {
        Page page = PageHelper.getLocalPage();
        if(page == null){  // page정보가 없으면 skip
            return true;
        }
        log.info("Page pageSize: {}, startRow: {}", page.getPageSize(), page.getStartRow());
        return super.skip(ms, parameterObject, rowBounds);
    }

}
