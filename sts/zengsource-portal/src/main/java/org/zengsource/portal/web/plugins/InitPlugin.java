package org.zengsource.portal.web.plugins;

import java.util.List;

import org.zengsource.mvc.MvcContext;
import org.zengsource.mvc.plugin.PluginException;
import org.zengsource.mvc.plugin.PluginTemplate;
import org.zengsource.portal.model.Page;
import org.zengsource.portal.service.ConfigService;
import org.zengsource.portal.service.ModuleService;
import org.zengsource.portal.service.PageService;
import org.zengsource.util.StringUtil;
import org.zengsource.util.cache.CacheService;

public class InitPlugin extends PluginTemplate {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;
	public static final String KEY_EXT = "ext.url";

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private PageService pageService;
	private CacheService cacheService;
	private ModuleService moduleService;
	private ConfigService configService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public boolean enable() throws PluginException {
		MvcContext context = MvcContext.getInstance();
		// 读取模块
		List<?> modules = this.moduleService.findByUser(null);
		getRequest().setAttribute("modules", modules);
		// 读取页面
		String pageUrl = "/" + context.getActionHierachy();
		if (pageUrl.contains("_revert")) {
			pageUrl = this.pageService.revert(pageUrl);
		}
		Page page = this.pageService.getByUrl(pageUrl);
		getRequest().setAttribute("page", page);
		logger.info("页面初始化完成……");
		// 页面地址参数
		String siteUrl = context.getFullContextPath();
		getRequest().setAttribute("siteUrl", siteUrl);
		// Ext URL
		String extUrl = this.configService.getString("ext.url");
		if (StringUtil.isBlank(extUrl)) {
			extUrl = "http://zeng.my.phpcloud.com/3rdp/sencha/extjs-4.0.7";
		}
		getRequest().setAttribute("extUrl", extUrl);
		logger.info("Site: " + siteUrl);
		logger.info("Ext : " + extUrl);
		return true;
	}

	public void disable() throws PluginException {
		// TODO Auto-generated method stub

	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public PageService getPageService() {
		return pageService;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

}
