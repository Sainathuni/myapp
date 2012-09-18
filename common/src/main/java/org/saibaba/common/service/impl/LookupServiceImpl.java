package org.saibaba.common.service.impl;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.saibaba.common.persistent.LookupDao;
import org.saibaba.common.service.LookupService;
import org.saibaba.fw.domain.Lookup;
import org.saibaba.fw.exception.ServiceException;
import org.saibaba.fw.service.impl.AbstractServiceImpl;


public class LookupServiceImpl extends AbstractServiceImpl implements LookupService {

	private Hashtable<String, LookupCacheValue> cache = new Hashtable<String, LookupCacheValue>();

	public List<Lookup> findAll(String className) throws ServiceException {
		try {
			@SuppressWarnings("unchecked")
			Class<Lookup> lookup = (Class<Lookup>) Class.forName(className);
			return findAll(lookup);
		} catch(ClassNotFoundException ex) {
			throw new ServiceException("Error finding lookup.",ex);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Lookup> findAll(@SuppressWarnings("rawtypes") Class lookup) throws ServiceException {
		
		if(excludeFromCache != null && excludeFromCache.contains(lookup.getName())) {
			return lookupDao.findAll(lookup);
		}
		LookupCacheValue lcv = this.getLookupCacheValue(lookup);
		return lcv != null ? lcv.getLookupList() : null;
	}	

	public Lookup findByCode(String className, String code)
		throws ServiceException {
		try {
			@SuppressWarnings("unchecked")
			Class<Lookup> lookup = (Class<Lookup>) Class.forName(className);
			return this.getLookupDao().findByCode(lookup, code);
		} catch (Exception ex) {
			throw new ServiceException("findByCode() failed to get Lookup by code = " + code, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Lookup findByCode(@SuppressWarnings("rawtypes") Class lookup, String code)
		throws ServiceException {
		try {
			return this.getLookupDao().findByCode(lookup, code);
		} catch (Exception ex) {
			throw new ServiceException("findByCode() failed to get Lookup by code = " + code, ex);
		}
	}

	private LookupCacheValue getLookupCacheValue(Class<Lookup> lookup) throws ServiceException {
		try {
			LookupCacheValue lcv = cache.get(lookup.getName());
			if (lcv == null || (lcv.getCacheTime() + cacheExpirationPeriod < System.currentTimeMillis())) {
				List<Lookup> lookupList = lookupDao.findAll(lookup);
				if(lookupList != null && lookupList.size() > 0) {
					lcv = new LookupCacheValue(lookupList);
					cache.put(lookup.getName(), lcv);
				}
			}
			return lcv;
		} catch (Exception ex) {
			throw new ServiceException("getLookupCacheValue() failed to get lookup for " + lookup.getName(), ex);
		}
	}
	
	public LookupDao lookupDao;
	public long cacheExpirationPeriod = 3*60*60*1000;
	private List<String> excludeFromCache;

	public LookupDao getLookupDao() {
		return lookupDao;
	}

	public void setLookupDao(LookupDao lookupDao) {
		this.lookupDao = lookupDao;
	}

	public List<String> getExcludeFromCache() {
		return excludeFromCache;
	}

	public void setExcludeFromCache(List<String> excludeFromCache) {
		this.excludeFromCache = excludeFromCache;
	}
}

class LookupCacheValue {
	
	private List<Lookup> lookupList;
	private Map<String,Lookup> lookupMap;
	private long cacheTime;
	
	LookupCacheValue() {}
	
	LookupCacheValue(List<Lookup> list) {
		this.lookupList = list;
		this.lookupMap = listToMap(list);
		this.cacheTime = System.currentTimeMillis();
	}

	Lookup findByCode(String code) {
		return this.lookupMap != null ? lookupMap.get(code) : null;
	}
	
	List<Lookup> getLookupList() {
		return lookupList;
	}

	void setLookupList(List<Lookup> lookupList) {
		this.lookupList = lookupList;
	}

	Map<String, Lookup> getLookupMap() {
		return lookupMap;
	}

	void setLookupMap(Map<String, Lookup> lookupMap) {
		this.lookupMap = lookupMap;
	}

	long getCacheTime() {
		return cacheTime;
	}

	void setCacheTime(long cacheTime) {
		this.cacheTime = cacheTime;
	}

	private Map<String, Lookup> listToMap(List<Lookup> lookups) {
		if(lookups != null) {
			Map<String, Lookup> map = new HashMap<String, Lookup>();
			for(Lookup lookup : lookups) {
				if(lookup != null && lookup.getCode() != null) {
					map.put(lookup.getCode(), lookup);
				}
			}
			return map;
		}
		return null;
	}
}
