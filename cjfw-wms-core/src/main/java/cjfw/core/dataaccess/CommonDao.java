package cjfw.core.dataaccess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Value;

import com.github.pagehelper.PageInfo;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.interceptor.MybatisPageHelper;
import cjfw.core.dataaccess.largedata.LargeDataHandler;
import cjfw.core.dataaccess.largedata.LargeDataRowHandler;
import cjfw.core.dataaccess.largedata.LargeDataUtils;
import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.dataaccess.largedata.LargeExcelDataRowHandler;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : CommonDao 기능을 구현한 Class
 * 				  일관적인 Backing Layer(ex. RDBMS, Cache 등)와의 연계를 위해 공통화한 DAO를 구현함(w/Mybatis)
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
@Slf4j
@RequiredArgsConstructor
public class CommonDao extends SqlSessionDaoSupport {
	
	private static final String REG_ID = "regId";
	private static final String MOD_ID = "modId";
	
	@Value("${cf.maxResultRows}")
	private int maxResultRows;

	private final UserContext userContext;

	/**
	 * 
	 * @description : selectList 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public <T> List<T> selectList(String sqlId) {
		List<T> result = getSqlSession().selectList(sqlId, new HashMap<String, Object>(), new RowBounds(0, maxResultRows + 1));
		return result;
	}

	/**
	 * 
	 * @description : selectList 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public List<Object> selectList(String sqlId, Map<String, Object> mapData) {
		List<Object> result = getSqlSession().selectList(sqlId, mapData, new RowBounds(0, maxResultRows + 1));
		return result;
	}
	
	/**
	 * 
	 * @description : selectList 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public List<Map<String, Object>> selectList2(String sqlId, Map<String, Object> mapData) {
		List<Map<String, Object>> result = getSqlSession().selectList(sqlId, mapData, new RowBounds(0, maxResultRows + 1));
		return result;
	}
	
	/**
	 * 
	 * @description : selectPageList 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public PageInfo<Object> selectPageList(String sqlId, Map<String, Object> mapData) {
		pagePreMap(mapData);
		List<Object> result = getSqlSession().selectList(sqlId, mapData, new RowBounds(0, maxResultRows + 1));
		PageInfo<Object> pageResult = PageInfo.of(result);
		return pageResult;
	}
	
	/**
	 * 
	 * @description : selectList 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public <T> List<T> selectList(String sqlId, Object params) {
		List<T> result = getSqlSession().selectList(sqlId, params, new RowBounds(0, maxResultRows + 1));
		return result;
	}

	/**
	 * 
	 * @description : selectPageList 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public PageInfo<Object> selectPageList(String sqlId, Object params) {
		pagePreObject(params);
		List<Object> result = getSqlSession().selectList(sqlId, params, new RowBounds(0, maxResultRows + 1));
		PageInfo<Object> pageResult = PageInfo.of(result);
		return pageResult;
	}

	/**
	 * 
	 * @description : selectPageList 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 * 2025.05.21        sunho.kim          현재 페이지 번호 및 전체 페이지수 추가
	 */
	public <T> Page<T> selectPageList(String sqlId, Object params, Page page) {
		pagePreDto(page);
		
		// 전체 DATA를 JAVA 메모리로 가져와서 페이징 처리에 맞게 잘라서 쓰는 RowBounds가 있을 이유가 없어보임 (JGS 2025-12-12)
		// 혹시 RowBounds 때문에 문제시 다시 살려야 함
		// List<T> result = getSqlSession().selectList(sqlId, params, new RowBounds(0, maxResultRows + 1));
		List<T> result = getSqlSession().selectList(sqlId, params);
		
		PageInfo<T> pageInfo = PageInfo.of(result);
		page.setTotalCount(pageInfo.getTotal());
		page.setList(result);
		page.setPageNum(pageInfo.getPageNum());
		//전체 페이지 수
		if (pageInfo.getPageSize() > 0) {
			int pgs = (int) (pageInfo.getTotal() / pageInfo.getPageSize());
			int mod = (int) (pageInfo.getTotal() % pageInfo.getPageSize());
			if (mod > 0) {
				pgs++;				
			}
			page.setTotalPages(pgs);
		} else {
			page.setTotalPages(0);
		}

		return page;
	}

	/**
	 * 
	 * @description : selectOne 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public Object selectOne(String sqlId) {
		Object result = getSqlSession().selectOne(sqlId);
		return result;
	}
	
	/**
	 * 
	 * @description : selectOne 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public <T> T selectOne(String sqlId, Object object) {
		T result = getSqlSession().selectOne(sqlId, object);
		return result;
	}

	/**
	 * 
	 * @description : selectOne 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public Object selectOne(String sqlId, Map<String, Object> mapData) {
		Object result = getSqlSession().selectOne(sqlId, mapData);
		return result;
	}

	/**
	 * 
	 * @description : selectWithRowHandler 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void selectWithRowHandler(String sqlId, ResultHandler<?> resultHandler) {
		getSqlSession().select(sqlId, resultHandler);
	}

	/**
	 * 
	 * @description : selectWithRowHandler 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void selectWithRowHandler(String sqlId, Object params, ResultHandler<?> resultHandler) {
		getSqlSession().select(sqlId, params, resultHandler);
	}

	/**
	 * 
	 * @description : getTotalCount 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public int getTotalCount(String sqlId) {
		return getSqlSession().selectList(sqlId).size();
	}

	/**
	 * 
	 * @description : getTotalCount 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public int getTotalCount(String sqlId, Object object) {
		return getSqlSession().selectList(sqlId, object).size();
	}	

	/**
	 * 
	 * @description : delete 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public int delete(String sqlId, Object object) {
		int deleteCount = getSqlSession().delete(sqlId, object);
		return deleteCount;
	}

	/**
	 * 
	 * @description : delete 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public int delete(String sqlId, Map<String, Object> mapData) {
		int result = getSqlSession().delete(sqlId, mapData);
		return result;
	}

	/**
	 * 
	 * @description : delete 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public int delete(String sqlId) {
		int result = getSqlSession().delete(sqlId);
		return result;
	}

	/**
	 * 
	 * @description : update 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public int update(String sqlId, Object object) {
		int result = getSqlSession().update(sqlId, object);
		return result;
	}	

	/**
	 * 
	 * @description : update 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public int update(String sqlId, Map<String, Object> mapData) {
		mapData.put(REG_ID, userContext.getUserId());
		mapData.put(MOD_ID, userContext.getUserId());
		int result = getSqlSession().update(sqlId, mapData);
		return result;
	}

	/**
	 * 
	 * @description : insert 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public int insert(String sqlId, Object object) {
		int result = getSqlSession().insert(sqlId, object);
		return result;
	}
	

	/**
	 * @description : 프로시져를 수행 처리한다.
     * <pre>
     *  - 프로시져 수행결과는 -1을 return하기 때문에 강제적으로 1로 리턴토록 처리
     * </pre> 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.26 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
    public int exec(String queryId, Object parameterObject) {
    	int r = getSqlSession().insert(queryId, parameterObject);
    	log.info("r->{}",r==-1?1:0);
    	return r==-1?1:0;
    } 	

	/**
	 * 
	 * @description : insert 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public int insert(String sqlId, Map<String, Object> mapData) {
		mapData.put(REG_ID, userContext.getUserId());
		mapData.put(MOD_ID, userContext.getUserId());
		int result = getSqlSession().insert(sqlId, mapData);
		return result;
	}

	/**
	 * 
	 * @description : getBatchSqlSession 기능을 구현한 Method
	 * 				  Batch용 SQL Session을 반환하는 기능
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public SqlSession getBatchSqlSession() {
		SqlSessionFactory sqlSessionFactory = ((SqlSessionTemplate)getSqlSession()).getSqlSessionFactory();
		return sqlSessionFactory.openSession(ExecutorType.BATCH, false);
	}

	/**
	 * 
	 * @description : insertBatch 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void insertBatch(String sqlId, Object object) {
		getBatchSqlSession().insert(sqlId, object);
	}	

	/**
	 * 
	 * @description : insertBatch 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void insertBatch(String sqlId, Map<String, Object> mapData) {
		mapData.put(REG_ID, userContext.getUserId());
		mapData.put(MOD_ID, userContext.getUserId());
		getBatchSqlSession().insert(sqlId, mapData);
	}

	/**
	 * 
	 * @description : updateBatch 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void updateBatch(String sqlId, Object object) {
		getBatchSqlSession().update(sqlId, object);
	}	

	/**
	 * 
	 * @description : updateBatch 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void updateBatch(String sqlId, Map<String, Object> mapData) {
		mapData.put(REG_ID, userContext.getUserId());
		mapData.put(MOD_ID, userContext.getUserId());
		getBatchSqlSession().update(sqlId, mapData);
	}
	
	/**
	 * 
	 * @description : insertQuartz 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01        JnagGwangSeok       생성
	 */
	public int insertQuartz(String sqlId, Map<String, Object> mapData) {
		int result = getSqlSession().insert(sqlId, mapData);
		return result;
	}
	
	/**
	 * 
	 * @description : insertQuartz 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.01        JnagGwangSeok       생성
	 */
	public int insertQuartz(String sqlId, Object object) {
		int result = getSqlSession().insert(sqlId, object);
		return result;
	}

	/**
	 * 
	 * @description : flushStatements 기능을 구현한 Method
     *
	 * [ Batch Flush ]
	 *
	 * BatchExecutor를 이용하면 flushStatements() 가 호출되어 세션이 끝날 때 까지
	 * DB로 보내지지 않고 모든 업데이트 문이 캐시된다.
	 *
	 * 따라서, 얼마나 많은 레코드가 바뀌었는지 알 수 없기 때문에 유효하지 않은 범위의 음수를 리턴한다.
	 * insert/update/delete 어디에서든 affected rows 대신 -2147482646을 리턴한다.
	 * 
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public List<BatchResult> flushStatements() {
		return getBatchSqlSession().flushStatements();
	}

	/**
	 * 
	 * @description : batchCommit 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void batchCommit() {
		getBatchSqlSession().commit();
	}

	/**
	 * 
	 * @description : batchRollback 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void batchRollback() {
		getBatchSqlSession().rollback();
	}

	/**
	 * 
	 * @description : batchClose 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void batchClose() {
		getBatchSqlSession().clearCache();
		getBatchSqlSession().close();
	}

	/**
	 * 
	 * @description : selectLargeDataset 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void selectLargeDataset(String sqlId, Object object, LargeDataUtils largeDataUtils) {
		LargeDataHandler rowHandler = new LargeDataRowHandler(largeDataUtils);
		try {
			this.selectWithRowHandler(sqlId, object, (ResultHandler<?>) rowHandler);
		} finally {
			rowHandler.close();
		}
	}

	/**
	 * 
	 * @description : selectLargeExcelDataset 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void selectLargeExcelDataset(String sqlId, Object object, LargeExcel largeExcel) {
		LargeDataHandler rowHandler = new LargeExcelDataRowHandler(largeExcel);
		try {
			this.selectWithRowHandler(sqlId, object, (ResultHandler<?>) rowHandler);
		} finally {
			rowHandler.close();
		}
	}

	/**
	 * 
	 * @description : pagePreDto 기능을 구현한 Method
	 *                page 관련 parameter에 대한 전처리
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 * 2025.05.21        sss               첫 페이지에서만 count 쿼리 수행되도록 추가 
	 */
	private void pagePreDto(Page page) {
		/** 카운터쿼리수행여부(true:수행) */
		boolean countryQuery = true;
		page.setSkipCount(true);
		
		if(page.getStartRow() == 0) { // 최조일 경우만 1회 수행
			page.setSkipCount(false);
		}
		
		if(page.isSkipCount() ){
			countryQuery = false;
		}
		MybatisPageHelper.offsetPage(page.getStartRow(), page.getListCount(), countryQuery);
	}

	/**
	 * @deprecated
	 * 
	 * @description : (대체 예정) pagePreObject 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@SuppressWarnings("unchecked")
	@Deprecated(since = "1.0")
	private void pagePreObject(Object object) {
		Map<String, Object> map = (Map<String, Object>) object;
		pagePreMap(map);
	}
	
	/**
	 * @deprecated
	 * 
	 * @description : (대체 예정) pagePreMap 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	@Deprecated(since = "1.0")
	private void pagePreMap(Map<String, Object> params) {
		int startRow = Integer.valueOf((String)params.get(CanalFrameConstants.START_ROW));
		int listCount = Integer.valueOf((String)params.get(CanalFrameConstants.LIST_COUNT));
		boolean countryQuery = true;
		if(Boolean.valueOf((String)params.get(CanalFrameConstants.SKIP_COUNT))){
			countryQuery = false;
		}
		MybatisPageHelper.offsetPage(startRow, listCount, countryQuery);
	}

	
	/**
	 * 
	 * @description : selectListMap 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public List<Map<String, Object>> selectListMap(String sqlId) {
		return getSqlSession().selectList(sqlId, new HashMap<String, Object>(), new RowBounds(0, maxResultRows + 1));
	}

	/**
	 * 
	 * @description : selectListMap 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public List<Map<String, Object>> selectListMap(String sqlId, Map<String, Object> mapData) {
		return getSqlSession().selectList(sqlId, mapData, new RowBounds(0, maxResultRows + 1));
	}
}
