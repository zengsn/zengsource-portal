/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-17
 */
package org.zengsource.portal.service;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.zengsource.mvc.MvcContext;
import org.zengsource.portal.dao.ModuleDao;
import org.zengsource.portal.model.Menu;
import org.zengsource.portal.model.Module;
import org.zengsource.portal.model.Page;
import org.zengsource.portal.model.Portlet;
import org.zengsource.portal.model.PortletInstance;
import org.zengsource.portal.model.Role;
import org.zengsource.portal.model.User;
import org.zengsource.util.NumberUtil;
import org.zengsource.util.StringUtil;
import org.zengsource.util.cache.CacheService;

/**
 * @author zengsn
 * @since 6.0
 */
public class ModuleServiceImpl implements ModuleService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final String CACHE_ONLINE_MODULES = "modules-all";
	private static final String CACHE_GUEST_MODULES = "modules-guest";

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private ModuleDao moduleDao;
	private MenuService menuService;
	private PageService pageService;
	private PortletService portletService;

	private UserService userService;
	private RoleService roleService;
	private CacheService cacheService;
	private ConfigService configService;

	private Logger logger = Logger.getLogger(getClass());

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public ModuleServiceImpl() {
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private void clearCache() {
		this.cacheService.remove(CACHE_ONLINE_MODULES);
		this.cacheService.remove(CACHE_GUEST_MODULES);
	}

	public void init() {
		List<?> modules = this.load(MvcContext.getInstance().getRootPath());
		if (modules != null) {
			// this.cacheService.cache(CACHE_ALL_MODULES, modules);
		}
	};

	/** 查询用户关联的模块。 */
	public List<?> findByUser(User user) {
		List<?> modules = null;
		if (user == null) { // 取当前登录用户的模块
			user = this.userService.getCurrentUser();
		}
		if (user == null) { // 未登录，只取回访客模块
			modules = this.getGuestModules();
		} else if (user.hasRole("admin")) { // 管理员可以访问所有模块
			modules = this.getOnlineModules(); // TODO 修改为关联
		} else { // 一般用户，取用户安装模块
			logger.error("Not yet ...."); // 暂时为空
		}
		return modules;
	}

	/** 查询在线模块，用于创建页面。 */
	private List<?> getOnlineModules() {
		List<?> modules = (List<?>) this.cacheService.get(CACHE_ONLINE_MODULES);
		if (modules == null || modules.size() == 0) { // 查询数据库
			modules = this.search(Module.ONLINE, 0, 0); // 查询上线模块
			logger.info("从数据库查询模块：" + modules);
			if (modules == null || modules.size() == 0) { // 数据库无上线模块，第一次初始化
				modules = this.load(MvcContext.getInstance().getRootPath());
				logger.info("第一次加载模块：" + modules);
				// 完成后再查询一次：至少 system 模块是上线的
				modules = this.search(Module.ONLINE, 0, 0); // 查询上线模块
			}
		}
		// 缓存
		if (modules != null && modules.size() > 0) {
			this.cacheService.cache(CACHE_ONLINE_MODULES, modules);
		}
		logger.info("已上线模块：" + modules);
		return modules;
	}

	private Module getByName(String name) {
		Module module = null;
		// 先从缓存的在线模块查询
		List<?> modules = this.getOnlineModules();
		for (Object obj : modules) {
			module = (Module) obj;
			if (module.getName().equals(name)) {
				return module;
			}
		}
		// 缓存没有就直接查询数据库
		module = (Module) this.moduleDao.queryUnique("name", name);
		return module;
	}

	/** 查询访客允许访问的模块，一般为 System 及其他一些允许访客访问的模块。 */
	private List<?> getGuestModules() {
		List<?> modules = (List<?>) this.cacheService.get(CACHE_GUEST_MODULES);
		if (modules == null || modules.size() == 0) { // 查询数据库
			List<Module> guestModules = new ArrayList<Module>();
			List<?> allModules = this.getOnlineModules();
			if (allModules == null) {
				return null;
			}
			for (Object obj : allModules) {
				Module module = (Module) obj;
				if ("system".equals(module.getName())) {
					guestModules.add(module);
				} else { // TODO 其他模块

				}
			}
			if (guestModules != null) { // 缓存
				this.cacheService.cache(CACHE_GUEST_MODULES, guestModules);
			}
			return guestModules;
		}
		return modules;
	}

	public List<?> search(int status, int start, int limit) {
		return this.moduleDao.queryByStatus(status, start, limit);
	}

	public List<?> search(String q, int start, int limit) {
		return this.moduleDao.query(q, start, limit);
	}

	public void save(Module module, String option) {
		if (StringUtil.isBlank(option)) {
			option = Module.OPT_INFO;
		}
		// 保存模块信息
		if (option.contains(Module.OPT_INFO)) {
			this.moduleDao.updateTime(module);
			this.moduleDao.save(module);
		}
		boolean needToUpdateTime = false;
		// 保存菜单
		if (option.contains(Module.OPT_MENU)) {
			this.menuService.reload(module);
			needToUpdateTime = true;
		}
		// 保存区块
		if (option.contains(Module.OPT_PORTLET)) {
			this.portletService.reload(module);
			needToUpdateTime = true;
		}
		// 保存页面
		if (option.contains(Module.OPT_PAGE)) {
			this.pageService.reload(module);
			needToUpdateTime = true;
		}
		// 删除缓存
		this.clearCache();
		// 修改更新时间
		if (needToUpdateTime) {
			this.moduleDao.updateTime(module);
			this.moduleDao.save(module);
		}
	}

	public void save(Module module) {
		this.save(module, Module.OPT_INFO);
	}

	/** 查询模块进行管理，包括初始化新增模块。 */
	public List<Module> findForAdmin() {
		File[] moduleDirs = loadDirs(MvcContext.getInstance().getRootPath());
		if (moduleDirs == null) {
			return null;
		}
		List<Module> modules = new ArrayList<Module>();
		for (File moduleDir : moduleDirs) {
			Module module = this.getByName(moduleDir.getName());
			if (module == null) {
				module = this.loadOne(moduleDir.getAbsolutePath());
				module.setStatus(Module.READY);
				this.save(module); // 新增模块
			}
			modules.add(module);
		}
		return modules;
	};

	/** 重新从配置文件加载模块。 */
	public Module reload(int id, String option) {
		Module module = (Module) this.moduleDao.queryUnique("id", id);
		if (module != null) {
			String rootPath = MvcContext.getInstance().getRootPath();
			String modulePath = rootPath + module.getName();
			module = this.loadOne(modulePath);
			if (module.getStatus() < Module.READY) {
				module.setStatus(Module.READY);
			}
			this.save(module, option);
		}
		return module;
	}

	/** 修改状态。 */
	public void changeStatus(int id, int status) {
		Module module = (Module) this.moduleDao.queryUnique("id", id);
		if (module != null && module.getStatus() != status) {
			module.setStatus(status);
			this.save(module);
		}
	}

	private File[] loadDirs(String rootPath) {
		if (StringUtil.isBlank(rootPath)) {
			logger.error("Directory not found!!!");
			return null; // 不能为空
		}
		// 取回服务器根目录下的所有模块目录:包含 module.xml
		File rootDir = new File(rootPath);
		File[] dirArr = rootDir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				if (!file.exists() //
						|| file.isFile()) {
					return false;
				}
				File cfgFile = new File(file.getAbsolutePath() + "/module.xml");
				if (!cfgFile.exists()) {
					return false;
				}
				return true;
			}
		});
		if (dirArr == null || dirArr.length == 0) {
			logger.error("No subdirectory found inside <> " + rootPath);
			return null; // 无目录
		}
		return dirArr;
	}

	/** 重新加载所有模块。 */
	public List<Module> load(String rootPath) {
		if (StringUtil.isBlank(rootPath)) {
			logger.error("Directory not found!!!");
			return null; // 不能为空
		}
		// 取回服务器根目录下的所有子目录
		File rootDir = new File(rootPath);
		File[] dirArr = rootDir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				if (!file.exists() //
						|| file.isFile()) {
					return false;
				}
				return true;
			}
		});
		if (dirArr == null || dirArr.length == 0) {
			logger.error("No subdirectory found inside <> " + rootPath);
			return null; // 无目录
		}
		// 检查和解析模块配置
		List<Module> moduleList = new ArrayList<Module>();
		for (File dir : dirArr) {
			Module module = loadOne(dir.getAbsolutePath());
			if (module != null) {
				module.setStatus(Module.READY);
				// 系统模块必须初始化页面和区块
				if ("system".equals(module.getName())) {
					module.setStatus(Module.ONLINE);
					this.save(module, Module.OPT_ALL);
				} else {
					this.save(module);
				}
				moduleList.add(module);
				logger.info("模块【" + module.getName() + "】 初始化完成 ……");
			}
		}
		return moduleList;
	}

	/** 加载一个模块的配置。 */
	public Module loadOne(String modulePath) {
		if (StringUtil.isBlank(modulePath)) {
			return null;
		}
		File modDir = new File(modulePath);
		if (!modDir.exists() || modDir.isFile()) {
			return null;
		}
		// 解析模块配置
		File cfgFile = new File(modulePath + "/module.xml");
		if (cfgFile.exists() && cfgFile.isFile()) {
			Module module = readCfg(cfgFile.getAbsolutePath());
			if (module != null) {
				module.setName(modDir.getName());
				logger.info("Module loaded <> " + module.getName());
				Module dbModule = (Module) this.moduleDao.queryUnique("name", module.getName());
				if (dbModule == null) {
					// this.save(module);
					dbModule = module;
				} else {
					dbModule.copy(module);
					// this.save(dbModule);
				}
				return dbModule;
			}
		}
		return null;
	}

	/** 从模块配置文件读取模块信息。 */
	private Module readCfg(String cfgPath) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(cfgPath));
			Element root = doc.getRootElement();
			Module module = new Module();
			Iterator<?> iter = root.elementIterator();
			for (; iter.hasNext();) { // 遍历子元素
				Element ele = (Element) iter.next();
				if ("users".equals(ele.getName())) { // 用户
					// 只有系统模块允许添加默认用户：admin
					if (cfgPath.endsWith("\\system\\module.xml")
							|| cfgPath.endsWith("/system/module.xml")) {
						Element usersEle = root.element("users");
						createSuperUser(module, usersEle);
					}
				} else if ("menus".equals(ele.getName())) { // 菜单
					readMenuCfg(module, ele); // 解析菜单配置
				} else if ("portlets".equals(ele.getName())) { // 区块
					readPortletCfg(module, ele); // 解析区块配置
				} else if ("pages".equals(ele.getName())) { // 页面
					readPageCfg(module, ele); // 解析页面配置
				} else { // 其他一般属性
					// 如：module.setTitle(root.elementText("title"));
					BeanUtils.setProperty(module, ele.getName(), ele.getTextTrim());
				}
			}
			return module;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void createSuperUser(Module module, Element usersEle) {
		Iterator<?> iter = usersEle.elementIterator("user");
		for (; iter.hasNext();) {
			Element userEle = (Element) iter.next();
			String username = userEle.elementText("username");
			User user = this.userService.findByUsername(username);
			if (user == null) {
				user = new User();
				user.setUsername(username);
				user.setPassword(userEle.elementText("password"));
				user.setEmail(username);
				user.setIntroduction("超人……");
				String roles = userEle.elementText("role");
				Role role = this.roleService.create(roles);
				user.addRole(role); // 添加角色
				this.userService.hashPassword(user);
				this.userService.save(user);
			}
		}
	}

	/** 解析菜单配置。 */
	private void readMenuCfg(Module module, Element ele) {
		Iterator<?> iter = ele.elementIterator("menu");
		for (; iter.hasNext();) {
			Menu menu = new Menu();
			Element menuEle = (Element) iter.next();
			Iterator<?> iter2 = menuEle.elementIterator();
			int index = 1;
			for (; iter2.hasNext();) {
				Element ele2 = (Element) iter2.next();
				if ("index".equals(ele2.getName())) {
					menu.setIndex(NumberUtil.string2Integer(ele2.getTextTrim(), 1));
				} else { // 其他一般属性
					try {
						BeanUtils.setProperty(menu, ele2.getName(), ele2.getTextTrim());
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				if (menu.getIndex() == 0) {
					menu.setIndex(index);
				}
				index++;
			}
			module.addMenu(menu);
		}
	}

	/** 读取模块的Portlet配置。 */
	private void readPortletCfg(Module module, Element ele) {
		Iterator<?> iter = ele.elementIterator("portlet");
		for (; iter.hasNext();) {
			Portlet portlet = new Portlet();
			Element portletEle = (Element) iter.next();
			Iterator<?> iter2 = portletEle.elementIterator();
			for (; iter2.hasNext();) {
				Element ele2 = (Element) iter2.next();
				if ("width".equals(ele2.getName())) {
					portlet.setWidth(NumberUtil.string2Integer(ele2.getTextTrim(), 330));
				} else if ("height".equals(ele2.getName())) {
					portlet.setHeight(NumberUtil.string2Integer(ele2.getTextTrim(), 330));
				} else { // 其他一般属性
					try {
						BeanUtils.setProperty(portlet, ele2.getName(), ele2.getTextTrim());
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			module.addPortlet(portlet);
		}
	}

	/** 读取模块的页面配置。 */
	private void readPageCfg(Module module, Element ele) {
		Iterator<?> iter = ele.elementIterator("page");
		for (; iter.hasNext();) {
			Page page = new Page();
			Element pageEle = (Element) iter.next();
			Iterator<?> iter2 = pageEle.elementIterator();
			for (; iter2.hasNext();) {
				Element ele2 = (Element) iter2.next();
				if ("portlets".equals(ele2.getName())) {
					readPagePortletCfg(page, ele2);
				} else if ("columns".equals(ele2.getName())) {
					page.setColumns(NumberUtil.string2Integer(ele2.getTextTrim(), 1));
				} else { // 自动设置其他属性
					try {
						BeanUtils.setProperty(page, ele2.getName(), ele2.getTextTrim());
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			module.addPage(page);
		}
	}

	/** 读取页面的 Portlet 设置。 */
	private void readPagePortletCfg(Page page, Element ele) {
		// this.pageService.deletePortlets(page); // 删除旧实例
		Iterator<?> iter = ele.elementIterator("portlet");
		int index = 0;
		for (; iter.hasNext();) {
			Element portletEle = (Element) iter.next();
			PortletInstance pi = new PortletInstance();
			Iterator<?> iter2 = portletEle.elementIterator();
			for (; iter2.hasNext();) {
				Element ele2 = (Element) iter2.next();
				if ("width".equals(ele2.getName())) {
					pi.setWidth(NumberUtil.string2Integer(ele2.getTextTrim(), 330));
				} else if ("height".equals(ele2.getName())) {
					pi.setHeight(NumberUtil.string2Integer(ele2.getTextTrim(), 330));
				} else if ("jsClass".equals(ele2.getName())) {
					Portlet portlet = new Portlet();
					portlet.setJsClass(ele2.getTextTrim());
					pi.setPortlet(portlet);
				} else { // 其余属性
					try {
						BeanUtils.setProperty(pi, ele2.getName(), ele2.getTextTrim());
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				if (pi.getIndex() == 0) {
					pi.setIndex(++index);
				}
			}
			page.addPortlet(pi);
		}
	}

	public Module findById(int id) {
		return (Module) this.moduleDao.queryById(id);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	public ModuleDao getModuleDao() {
		return moduleDao;
	}

	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public PageService getPageService() {
		return pageService;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public PortletService getPortletService() {
		return portletService;
	}

	public void setPortletService(PortletService portletService) {
		this.portletService = portletService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

}
