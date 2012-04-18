/**
 * 
 */
package org.zengsource.portal.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.zengsource.portal.dao.PageDao;
import org.zengsource.portal.model.Module;
import org.zengsource.portal.model.Page;
import org.zengsource.portal.model.PortletInstance;
import org.zengsource.util.StringUtil;
import org.zengsource.util.cache.CacheService;

/**
 * @author snzeng
 * 
 */
public class PageServiceImpl implements PageService {

	// ~ 静态属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	private static final String CACHE_ALL_PAGES = "all-pages";

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	Logger logger = Logger.getLogger(getClass());

	private PageDao pageDao;
	private CacheService cacheService;
	private PortletService portletService;
	private PortletInstanceService portletInstanceService;

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public PageServiceImpl() {
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	private void clearCache() {
		this.cacheService.remove(CACHE_ALL_PAGES);
	}

	public List<?> getAll() {
		// 查缓存
		List<?> allPages = (List<?>) this.cacheService.get(CACHE_ALL_PAGES);
		if (allPages == null) {
			allPages = this.pageDao.query((String) null, 0, 0);
			this.cacheService.cache(CACHE_ALL_PAGES, allPages);
		}
		return allPages;
	}

	public void save(Page page) {
		if (page == null) {
			return;
		}
		// 先保存页面
		this.pageDao.updateTime(page);
		this.portletInstanceService.reload(page); // 先重新检查区块
		this.pageDao.save(page);
		this.clearCache();
	}

	public void reload(Module module) {
		List<?> oldPages = this.search(module, 0, 0);
		Set<Page> newPages = module.getPageSet();
		Set<Page> pageSet = new HashSet<Page>();
		Set<Page> ignoredSet = new HashSet<Page>();
		for (Object obj : oldPages) { // TODO 算法优化
			Page oldPage = (Page) obj;
			boolean delete = true;
			for (Page newPage : newPages) {
				if (oldPage.getUrl().equals(newPage.getUrl())) {
					oldPage.copy(newPage);
					this.save(oldPage); // 更新
					ignoredSet.add(newPage);
					pageSet.add(oldPage);
					delete = false; // 不删除
				}
			}
			if (delete) { // 删除旧页面
				this.delete(oldPage);
			}
		}
		// 增加新页面
		for (Page newPage : newPages) {
			boolean exists = false;
			for (Page ignore : ignoredSet) {
				if (ignore.equals(newPage)) {
					exists = true;
					break;
				}
			}
			if (!exists) { // 新增
				newPage.setModule(module);
				this.save(newPage); // 保存
				pageSet.add(newPage);
			}
		}
		// 取回
		module.setPageSet(pageSet);
	}

	private void delete(Page page) {
		if (page != null) {
			this.pageDao.delete(page);
		}
	}

	public Integer searchCount(String q) {
		return this.pageDao.queryCount(q);
	}

	public List<?> search(String q, Integer start, Integer limit) {
		return this.pageDao.query(q, start, limit);
	}

	public List<?> search(Module module, Integer start, Integer limit) {
		return this.pageDao.queryByModule(module, start, limit);
	}

	public Page getById(int pageId) {
		// 查询缓存
		List<?> allPages = this.getAll();
		for (Object obj : allPages) {
			Page page = (Page) obj;
			if (page.getId() == pageId) {
				return page;
			}
		}
		return (Page) this.pageDao.queryById(pageId);
	}

	public Page getByUrl(String url) {
		if (StringUtil.isBlank(url)) {
			return null;
		}
		// 查询缓存
		List<?> allPages = this.getAll();
		for (Object obj : allPages) {
			Page page = (Page) obj;
			if (page.getUrl().equals(url)) {
				return page;
			}
		}
		// 查询数据库
		return (Page) this.pageDao.queryUnique("url", url);
	}

	public void deletePortlets(Page page) {
		Page dbPage = this.getByUrl(page.getUrl());
		if (dbPage != null && dbPage.getPortletInstanceSet() != null) {
			for (PortletInstance pi : dbPage.getPortletInstanceSet()) {
				this.portletInstanceService.delete(pi);
			}
			dbPage.setPortletInstanceSet(null);
			this.save(dbPage);
		}
	}

	/**
	 * 末尾带 _revert 为通配页面，需要转换后才能查询页面。 <br />
	 * 例如，index.jxp?id=2&_revert 的页面地址为 index.jxp?id=(*)；<br />
	 * index.jxp?id=2&name=abc&_revert=id 的页面地址为 index.jxp?id=(*)&name=abc。
	 * 注：目前 XML 中还不支持使用 & 符号，TODO 转义 & - &amp;
	 */
	public String revert(String pageUrl) {
		Map<String, String> queryMap = StringUtil.parseQuery(pageUrl);
		if (queryMap != null) {
			String revertKey = queryMap.get("_revert");
			if (StringUtil.notBlank(revertKey)) { // 指定参数
				pageUrl = pageUrl.replace(queryMap.get(revertKey), "(*)");
				pageUrl = pageUrl.replace("&_revert=" + revertKey, "");
			} else { // 取第一个/唯一一个参数
				for (String key : queryMap.keySet()) {
					if (!("_revert".equals(key))) {
						// queryMap.put(key, "(*)");
						pageUrl = pageUrl.replace(queryMap.get(key), "(*)");
					}
				}
				pageUrl = pageUrl.replace("&_revert", ""); // 去掉
			}
		}
		logger.info("转换页面URL：" + pageUrl);
		return pageUrl;
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public PortletInstanceService getPortletInstanceService() {
		return portletInstanceService;
	}

	public void setPortletInstanceService(PortletInstanceService blockInstanceService) {
		this.portletInstanceService = blockInstanceService;
	}

	public PortletService getPortletService() {
		return portletService;
	}

	public void setPortletService(PortletService blockPrototypeService) {
		this.portletService = blockPrototypeService;
	}

}
