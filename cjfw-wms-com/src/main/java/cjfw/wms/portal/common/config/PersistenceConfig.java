/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.portal.common.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.dataaccess.RefreshableSqlSessionFactoryBean;
import cjfw.core.model.UserContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableTransactionManagement(order = 1, proxyTargetClass = true)
public class PersistenceConfig {
	
	@Value("${spring.profiles.active:}")
	private String activeProfile;
	
	@Value("${spring.datasource-oracle.username}")
	private String username;

	@Value("${spring.datasource-oracle.password}")
	private String password;

	@Value("${spring.datasource-oracle.url}")
	private String url;
	
	@Value("${spring.datasource-oracle.hikari.pool-name}")
	private String poolName;
	
	@Value("${spring.datasource-oracle.hikari.maximum-pool-size}")
	private int maximumPoolSize;
	
	@Value("${spring.datasource-oracle.hikari.minimum-idle}")
	private int minimumIdle;
	
	@Value("${spring.datasource-oracle.hikari.connection-timeout}")
	private int connectionTimeout;
	
	@Value("${spring.datasource-oracle.hikari.validation-timeout}")
	private int validationTimeout;
	
	@Value("${spring.datasource-oracle.hikari.max-lifetime}")
	private int maxLifetime;
	
	@Value("${spring.datasource-oracle.hikari.idle-timeout}")
	private int idleTimeout;
	
	@Value("${spring.datasource-oracle.hikari.keepalive-time}")
	private int keepaliveTime;
	
	@Value("${spring.datasource-oracle.hikari.leak-detection-threshold}")
	private int leakDetectionThreshold;

	private final UserContext userContext;
	private final ApplicationContext applicationContext;
	
	@Bean
    @Qualifier("dataSource")
	@Primary
	public DataSource oracleDataSource() {
		HikariConfig hikariConfig = new HikariConfig();

		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setJdbcUrl(url);

		hikariConfig.setPoolName(poolName);
		hikariConfig.setMaximumPoolSize(maximumPoolSize);
		hikariConfig.setMinimumIdle(minimumIdle);
		hikariConfig.setConnectionTimeout(connectionTimeout);
		hikariConfig.setValidationTimeout(validationTimeout);
		hikariConfig.setMaxLifetime(maxLifetime);
		hikariConfig.setIdleTimeout(idleTimeout);
		hikariConfig.setKeepaliveTime(keepaliveTime);
		hikariConfig.setLeakDetectionThreshold(leakDetectionThreshold);
		
		// 오라클 세션 식별자 추가
	    String program =
	        System.getenv("OBJ_NAME") != null
	            ? System.getenv("OBJ_NAME")
	            : (System.getenv("COMPUTERNAME") != null ? System.getenv("COMPUTERNAME") : "LOCAL");
	    hikariConfig.addDataSourceProperty("v$session.program", program);

		return new HikariDataSource(hikariConfig); 
    }
	
	@Bean
	@Qualifier("sqlSessionFactory")
    @Primary
    public SqlSessionFactory oracleSqlSessionFactory() throws Exception {
    	
    	SqlSessionFactoryBean factoryBean = null;
    	if(!"prd".equals(activeProfile)) {
    		factoryBean = new RefreshableSqlSessionFactoryBean();
    		((RefreshableSqlSessionFactoryBean) factoryBean).setInterval(1000);
    	} else {
    		 factoryBean = new SqlSessionFactoryBean();
    	}
        factoryBean.setDataSource(oracleDataSource());
        factoryBean.setVfs(SpringBootVFS.class);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/mybatis-config.xml"));
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = resolver.getResources("classpath*:mybatis/oracle/**/*.sqlx");
        factoryBean.setMapperLocations(resource);
		factoryBean.setTypeAliasesPackage("cjfw.**.dto, cjfw.**.entity"); // sql ResultType 클래스가 위치한 패키지 경로(여러개 시 ","로 구분)

        return factoryBean.getObject();
    }

	@Bean
	@Qualifier("commonDao")
	@Primary
	public CommonDao commonDao() throws Exception {
		CommonDao commonDao = new CommonDao(userContext);
		commonDao.setSqlSessionFactory(oracleSqlSessionFactory());
		return commonDao;
	}
	
	@Bean
	@Qualifier("transactionManager")
	@Primary
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(oracleDataSource());
    }
	
	@Bean
	@Qualifier("txAdvice")
	@Primary
	public TransactionInterceptor txAdvice() {
		TransactionInterceptor txAdvice = new TransactionInterceptor();
		Properties txAttributes = new Properties();
		List<RollbackRuleAttribute> rollbackRules = new ArrayList<RollbackRuleAttribute>();
		rollbackRules.add(new RollbackRuleAttribute(Exception.class));
		
		// 단순 SELECT는 Transaction 없이 실행 (성능테스트 요청사항 2026-02-03)
//		DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(
//				TransactionDefinition.PROPAGATION_SUPPORTS);
		DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(
				TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
		readOnlyAttribute.setReadOnly(true);

		RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
		
		RuleBasedTransactionAttribute writeNewAttribute = new RuleBasedTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRES_NEW, rollbackRules);
		
		String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
		String writeTransactionAttributesDefinition = writeAttribute.toString();
		String writeNewTransactionAttributesDefinition = writeNewAttribute.toString();

		txAttributes.setProperty("create*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("insert*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("update*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("delete*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("save*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("print*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("create*NewTx", writeNewTransactionAttributesDefinition);
		txAttributes.setProperty("insert*NewTx", writeNewTransactionAttributesDefinition);
		txAttributes.setProperty("update*NewTx", writeNewTransactionAttributesDefinition);
		txAttributes.setProperty("delete*NewTx", writeNewTransactionAttributesDefinition);
		txAttributes.setProperty("save*NewTx", writeNewTransactionAttributesDefinition);
		txAttributes.setProperty("print*NewTx", writeNewTransactionAttributesDefinition);
		txAttributes.setProperty("*", readOnlyTransactionAttributesDefinition);

		txAdvice.setTransactionAttributes(txAttributes);
		txAdvice.setTransactionManager(transactionManager());
		return txAdvice;
	}
	
	@Bean
	@Qualifier("txAdviceAdvisor")
	@Primary
	public Advisor txAdviceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* *..service.*Service.*(..))");
		return new DefaultPointcutAdvisor(pointcut, txAdvice());
	}
}