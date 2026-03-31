package cjfw.batch.common;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableEncryptableProperties
public class DataSourceConfig {

	@Value("${spring.datasource-oracle.url}")
	private String url;

	@Value("${spring.datasource-oracle.username}")
	private String username;

	@Value("${spring.datasource-oracle.password}")
	private String password;

	@Value("${spring.datasource-oracle.driver-class-name}")
	private String driverClassName;
	
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


	@Primary
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		
		config.setDriverClassName(driverClassName);
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		
		config.setPoolName(poolName);
		config.setMaximumPoolSize(maximumPoolSize);
		config.setMinimumIdle(minimumIdle);
		config.setConnectionTimeout(connectionTimeout);
		config.setValidationTimeout(validationTimeout);
		config.setMaxLifetime(maxLifetime);
		config.setIdleTimeout(idleTimeout);
		config.setKeepaliveTime(keepaliveTime);
		config.setLeakDetectionThreshold(leakDetectionThreshold);
		
		// 오라클 세션 식별자 추가
	    String program =
	        System.getenv("OBJ_NAME") != null
	            ? System.getenv("OBJ_NAME")
	            : (System.getenv("COMPUTERNAME") != null ? System.getenv("COMPUTERNAME") : "LOCAL");
	    config.addDataSourceProperty("v$session.program", program);
		
		return new HikariDataSource(config);
	}
}