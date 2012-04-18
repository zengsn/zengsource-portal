/**
 * 
 */
package org.zengsource.portal.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.dao.ConfigDao;
import org.zengsource.portal.model.Config;
import org.zengsource.util.ClassLoaderUtil;
import org.zengsource.util.PropertiesUtil;
import org.zengsource.util.StringUtil;
import org.zengsource.util.cache.CacheService;

/**
 * @author snzeng
 * 
 */
public class ConfigServiceImpl implements ConfigService {

	// ~ 静态属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	private static final long serialVersionUID = 1L;
	private static final String CACHE_ID = "cache-configs";

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	Logger logger = Logger.getLogger(getClass());

	private ConfigDao configDao;
	private CacheService cacheService;

	private String cacheMode;

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public ConfigServiceImpl() {
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public Properties getDefaults() {
		Properties props = new Properties();
		try {
			props.load(ClassLoaderUtil.getResourceAsStream("defaults.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	public void initConfigs(String xmlPath) {
		// Parse XML
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(xmlPath));
			Element root = doc.getRootElement();
			String group = root.attributeValue("group");
			if (StringUtil.isBlank(group)) {
				// throw new NullGroupException("Null group of configuration!");
			}
			// Check database
			List<?> configList = getConfigsByGroup(group);
			if (configList != null && configList.size() > 0) {
				// throw new DuplicatedInitializationException(
				// "Group configurations are already initialized!");
			}
			List<?> configs = root.elements();
			for (Object obj : configs) {
				Element ele = (Element) obj;
				String key = ele.elementText("key");
				if (StringUtil.isBlank(key)) {
					// throw new NullKeyExcpetion("Null key of configuration!");
				}
				if (!key.startsWith(group + ".")) {
					// throw new
					// IllegalKeyExcpetion("Key should start with \"GROUP.\"!");
				}
				String value = ele.elementText("value");
				Config config = new Config();
				config.setKey(key);
				config.setGroup(group);
				config.setValue(value);
				// Save to DB
				this.configDao.save(config);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private boolean enableCache() {
		// Config config = null;
		// if (this.cacheMode == null) {
		// String key = "cache.mode";
		// config = this.configDao.queryByKey(key);
		// if (config != null) {
		// this.cacheMode = config.getValue();
		// }
		// }
		// return "memory".equals(this.cacheMode);
		return true;
	}

	private Config getConfigurationFromCache(String key) {
		Map<?, ?> cache = (Map<?, ?>) this.cacheService.get(CACHE_ID);
		if (cache == null) {
			cache = new HashMap<String, Config>();
			this.cacheService.cache(CACHE_ID, cache);
			return null;
		}
		return (Config) cache.get(key);
	}

	private void cacheConfiguration(Config config) {
		@SuppressWarnings("unchecked")
		Map<String, Config> cacheObj = (Map<String, Config>) this.cacheService.get(CACHE_ID);
		if (cacheObj == null) {
			cacheObj = new HashMap<String, Config>();
			this.cacheService.cache(CACHE_ID, cacheObj);
		}
		cacheObj.put(config.getKey(), config);
	}

	private void removeFromCache(Config config) {
		Map<?, ?> cacheObj = (Map<?, ?>) this.cacheService.get(CACHE_ID);
		if (cacheObj != null) {
			cacheObj.remove(config.getKey());
		}
	}

	// Management //////////////////////////////////////////////////////////

	public Config getByKey(String key) {
		Config config = null;
		if (enableCache()) { // find from cache
			config = getConfigurationFromCache(key);
		}
		if (config == null) { // find from database
			config = this.configDao.queryByKey(key);
			if (enableCache() && config != null) { // cahce to memory
				cacheConfiguration(config);
			}
		}
		if (config == null) { // find default configuration
			Properties defaults = getDefaults();
			String value = PropertiesUtil.getValue(defaults, key);
			config = new Config();
			config.setKey(key);
			config.setName(value);
			config.setValue(value);
			if (key.indexOf(".") > -1) {
				config.setGroup(key.substring(0, key.indexOf(".")));
			}
			save(config); // save
		}
		return config;
	}

	public void save(Config config) {
		Config dbConfig = this.configDao.queryByKey(config.getKey());
		if (dbConfig != null) {
			dbConfig.copyData(config);
			this.configDao.save(dbConfig);
			removeFromCache(config);
		}
		this.configDao.save(config);
	}

	public Config getById(int id) {
		return (Config) this.configDao.queryById(id);
	}

	public List<?> getConfigsByGroup(String group) {
		// TODO Auto-generated method stub
		return null;
	}

	public int searchCount(String q) {
		return this.configDao.queryCount(this.searchCriterions(q));
	}

	public List<?> searchByGroup(String q, String group, int start, int limit) {
		if (StringUtil.isBlank(group)) {
			return this.configDao.query(new Criterion[] {}, start, limit);
		}
		return this.configDao.query(new Criterion[] { //
				Restrictions.eq("group", group), //
						Restrictions.like("name", q, MatchMode.ANYWHERE) }, start, limit);
	}

	private Criterion[] searchCriterions(String q) {
		if (StringUtil.isBlank(q)) {
			return new Criterion[] {};
		}
		return new Criterion[] { Restrictions.like("name", q, MatchMode.ANYWHERE) };
	}

	// Application /////////////////////////////////////////////////////////

	public String getString(String key) {
		Config config = getByKey(key);
		return config == null ? null : config.getValue();
	}

	public boolean getBoolean(String key) {
		Config config = getByKey(key);
		return config != null && config.getBoolean();
	}

	// Convenience ////////////////////////////////////////////////////////

	public String getSiteHome() {
		return "";// getString(SystemConstants.CFG_SITE_HOME);
	}

	public String getSiteUpload() {
		return "";// getString(SystemConstants.CFG_SITE_UPLOAD);
	}

	public String getSiteTmp() {
		return "";// getString(SystemConstants.CFG_SITE_TMP);
	}

	public String getExtHome() {
		return "";// getString(SystemConstants.CFG_EXT_HOME);
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public ConfigDao getConfigDao() {
		return configDao;
	}

	public void setConfigDao(ConfigDao configurationDao) {
		this.configDao = configurationDao;
	}

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public String getCacheMode() {
		return cacheMode;
	}

	public void setCacheMode(String cacheMode) {
		this.cacheMode = cacheMode;
	}

}
