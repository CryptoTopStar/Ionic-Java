package com.smart.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

public class JPAListener<PH> {

	@Autowired
	private CacheManager cacheManager; 
	
	
	private Logger logger = LoggerFactory.getLogger(JPAListener.class);

	@PostPersist
	public void userPostPersist(final PH o) {
		logger.info("inside @class {} cache cleared {}",this.getClass().getName() + "class name " + o.getClass().getSimpleName());
		clearCache(o.getClass().getSimpleName());

	}

	@PostUpdate
	public void userPostUpdate(final PH o) {
		logger.info("inside @class {} cache cleared",this.getClass().getName());
		clearCache(o.getClass().getSimpleName());

	}

	@PostRemove
	public void userPostRemove(final PH o) {
		logger.info("inside @class {} cache cleared",this.getClass().getName());
		clearCache(o.getClass().getSimpleName());

	}

	private void clearCache(String cName) {
		cacheManager.getCacheNames().forEach((name) -> {
			System.err.println(cacheManager.getCache(name).getName() + "  name  " + cName );
			if(cacheManager.getCache(name).getName().equals(cName))
			cacheManager.getCache(name).clear();
		});

	}

}
