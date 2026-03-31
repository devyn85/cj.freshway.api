package cjfw.core.dataaccess;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.io.Resource;

import cjfw.core.exception.SystemException;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : RefreshableSqlSessionFactoryBean 기능을 구현한 Class
 * 				  Sql 파일 변경 실시간으로 반영해주기 위한 생산성 보조 기능
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
public class RefreshableSqlSessionFactoryBean extends SqlSessionFactoryBean implements DisposableBean {

	private static final Logger log = LoggerFactory.getLogger(RefreshableSqlSessionFactoryBean.class);
	private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private static final Lock r = rwl.readLock();
	private static final Lock w = rwl.writeLock();
	private SqlSessionFactory proxy;
	private int interval = 1000;
	private Timer timer;
	private TimerTask task;
	private Resource[] mapperLocations;
	private boolean running = false;

	/**
	 * 
	 * @overridden  : @see org.mybatis.spring.SqlSessionFactoryBean#setMapperLocations(org.springframework.core.io.Resource[])
	 * @description : setMapperLocations 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setMapperLocations(Resource[] mapperLocations) {
		super.setMapperLocations(mapperLocations);
		this.mapperLocations = mapperLocations;
	}

	/**
	 * 
	 * @description : setInterval 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}

	/**
	 * 
	 * @description : refresh 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void refresh() throws SystemException {
		w.lock();
		try {
			super.afterPropertiesSet();
		} catch (Exception e) {
			log.error("RefreshableSqlSessionFactoryBean.refresh.Exception : ", e);
		} finally {
			w.unlock();
		}
		
		log.info("sqlMapClient refreshed.");
	}

	/**
	 * 
	 * @overridden  : @see org.mybatis.spring.SqlSessionFactoryBean#afterPropertiesSet()
	 * @description : afterPropertiesSet 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void afterPropertiesSet() throws SystemException {
		try {
			super.afterPropertiesSet();
		} catch (Exception e) {
			log.error("RefreshableSqlSessionFactoryBean.afterPropertiesSet.Exception : ", e);
		}
	}

	/**
	 * 
	 * @description : setRefreshable 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private void setRefreshable() {
		proxy = (SqlSessionFactory) Proxy.newProxyInstance(SqlSessionFactory.class.getClassLoader(),
				new Class[] { SqlSessionFactory.class }, new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						return method.invoke(getParentObject(), args);
					}
				});

		task = new TimerTask() {
			private Map<Resource, Long> map = new HashMap<Resource, Long>();

			public void run() {
				if (isModified()) {
					try {
						refresh();
					} catch (Exception e) {
						log.error("RefreshableSqlSessionFactoryBean.setRefreshable.Exception : ", e);
					}
				}
			}

			private boolean isModified() {
				boolean retVal = false;

				if (mapperLocations != null) {
					for (int i = 0; i < mapperLocations.length; i++) {
						Resource mappingLocation = mapperLocations[i];
						retVal |= findModifiedResource(mappingLocation);
					}
				}

				return retVal;
			}

			private boolean findModifiedResource(Resource resource) {
				boolean retVal = false;
				List<String> modifiedResources = new ArrayList<String>();

				try {
					long modified = resource.lastModified();

					if (map.containsKey(resource)) {
						long lastModified = ((Long) map.get(resource)).longValue();

						if (lastModified != modified) {
							map.put(resource, modified);
							modifiedResources.add(resource.getDescription());
							retVal = true;
						}
					} else {
						map.put(resource, modified);
					}
				} catch (IOException e) {
					log.error("RefreshableSqlSessionFactoryBean.IOException : ", e);
				}
				if (retVal) {
					log.info("modified files : " + modifiedResources);
				}
				return retVal;
			}
		};

		timer = new Timer(true);
		resetInterval();

	}

	/** 
	 * 
	 * @description : getParentObject 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private Object getParentObject() throws Exception {
		r.lock();
		try {
			return super.getObject();
		} finally {
			r.unlock();
		}
	}

	/**
	 * 
	 * @overridden  : @see org.mybatis.spring.SqlSessionFactoryBean#getObject()
	 * @description : getObject 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public SqlSessionFactory getObject() throws SystemException {
		if(this.proxy == null) {
			setRefreshable();
		}
		return this.proxy;
	}

	/**
	 * 
	 * @overridden  : @see org.mybatis.spring.SqlSessionFactoryBean#getObjectType()
	 * @description : getObjectType 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public Class<? extends SqlSessionFactory> getObjectType() {
		return (this.proxy != null ? this.proxy.getClass() : SqlSessionFactory.class);
	}

	/**
	 * 
	 * @overridden  : @see org.mybatis.spring.SqlSessionFactoryBean#isSingleton()
	 * @description : isSingleton 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public boolean isSingleton() {
		return true;
	}

	/**
	 * 
	 * @description : setCheckInterval 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void setCheckInterval(int ms) {
		interval = ms;

		if (timer != null) {
			resetInterval();
		}
	}

	/**
	 * 
	 * @description : resetInterval 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	private void resetInterval() {
		if (running) {
			timer.cancel();
			running = false;
		}
		if (interval > 0) {
			timer.schedule(task, 0, interval);
			running = true;
		}
	}

	/**
	 * 
	 * @overridden  : @see org.springframework.beans.factory.DisposableBean#destroy()
	 * @description : destroy 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public void destroy() throws SystemException {
		timer.cancel();
	}
}
