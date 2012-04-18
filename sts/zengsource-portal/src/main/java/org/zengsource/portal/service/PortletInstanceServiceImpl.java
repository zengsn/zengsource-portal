/**
 * 
 */
package org.zengsource.portal.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.zengsource.portal.dao.PortletInstanceDao;
import org.zengsource.portal.dao.PortletSettingDao;
import org.zengsource.portal.model.PortletInstance;
import org.zengsource.portal.model.Portlet;
import org.zengsource.portal.model.PortletSetting;
import org.zengsource.portal.model.Page;

/**
 * @author zeng.xiaoning
 * 
 */
public class PortletInstanceServiceImpl implements PortletInstanceService {

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	
	private Logger logger = Logger.getLogger(getClass());

	private PageService pageService;
	private PortletService portletService;
	private PortletSettingDao portletSettingDao;
	private PortletInstanceDao portletInstanceDao;

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public PortletInstanceServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public void save(PortletInstance instance) {
		if (instance == null) {
			return;
		}
		Set<PortletSetting> settings = instance.getSettingSet();
		Set<Page> pageSet = instance.getPageSet();
		// Save instance
		instance.setSettingSet(null);
		instance.setPageSet(null);
		if (instance.getName() == null && instance.getPortlet() != null) {
			instance.setName(instance.getPortlet().getName());
		}
		// 检查 Portlet，第一次为解释的对象
		if (instance.getPortlet() != null) {
			Portlet portlet = this.portletService.findByJsClass( //
					instance.getPortlet().getJsClass());
			if (portlet == null) {
				logger.warn("区块定义不存在：" + instance.getPortlet().getJsClass());
				return; // 区块定义未加载，不保存
			}
			instance.setPortlet(portlet);
		}
		this.portletInstanceDao.save(instance);
		// Save settings
		for (PortletSetting setting : settings) {
			// setting.setInstance(instance);
			this.portletSettingDao.save(setting);
		}
		// Save settings' instance
		instance.setSettingSet(settings);
		this.portletInstanceDao.save(instance);
		// 保存页面关系
		for (Page page : pageSet) {
			Page dbPage = this.pageService.getByUrl(page.getUrl());
			if (dbPage != null) {
				instance.addPage(dbPage);
			}
		}
		this.portletInstanceDao.save(instance);
	}

	public void reload(Page page) {
		Page dbPage = this.pageService.getByUrl(page.getUrl());
		Set<PortletInstance> oldInstances = dbPage == null ? null : dbPage.getPortletInstanceSet();
		Set<PortletInstance> newInstances = page.getPortletInstanceSet();
		Set<PortletInstance> instanceSet = new HashSet<PortletInstance>();
		Set<PortletInstance> ignoredSet = new HashSet<PortletInstance>();
		if (oldInstances != null && newInstances != null) {
			for (PortletInstance oldPi : oldInstances) { // TODO 算法优化
				boolean delete = true;
				for (PortletInstance newPi : newInstances) {
					if (oldPi.getPortlet().compareTo(newPi.getPortlet()) == 0) {
						oldPi.copy(newPi);
						this.save(oldPi); // 更新
						ignoredSet.add(newPi);
						instanceSet.add(oldPi);
						delete = false;
					}
				}
				if (delete) { // 删除新配置里没有的区块
					this.delete(oldPi);
				}
			}
		}
		// 增加新页面
		if (newInstances != null) {
			for (PortletInstance newPi : newInstances) {
				boolean exists = false;
				for (PortletInstance ignore : ignoredSet) {
					if (ignore.getPortlet().compareTo(newPi.getPortlet()) == 0) {
						exists = true;
						break;
					}
				}
				if (!exists) { // 新增
					this.save(newPi); // 保存
					if (newPi.getId() > 0) {
						instanceSet.add(newPi);
					}
				}
			}
		}
		// 取回
		page.setPortletInstanceSet(instanceSet);
	}

	public Integer searchCount(Page page, String q) {
		if (page == null) {
			return 0;
		}
		return this.portletInstanceDao.queryCount(page, q);
	}

	public List<?> search(Page page, String q, Integer start, Integer limit) {
		if (page == null) {
			return null;
		}
		return this.portletInstanceDao.query(page, q, start, limit);
	}

	public PortletInstance search(Page page, Portlet prototype) {
		if (page == null || prototype == null) {
			return null;
		}
		return this.portletInstanceDao.query(page, prototype);
	}

	public PortletInstance getById(int id) {
		return (PortletInstance) this.portletInstanceDao.queryById(id);
	}

	public List<?> search(Portlet prototype) {
		if (prototype == null) {
			return null;
		}
		return this.portletInstanceDao.query(prototype);
	}

	public void delete(PortletInstance instance) {
		if (instance != null) {
			this.portletInstanceDao.delete(instance);
		}
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public PageService getPageService() {
		return pageService;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	public PortletService getPortletService() {
		return portletService;
	}

	public void setPortletService(PortletService portletService) {
		this.portletService = portletService;
	}

	public PortletSettingDao getPortletSettingDao() {
		return portletSettingDao;
	}

	public void setPortletSettingDao(PortletSettingDao blockSettingDao) {
		this.portletSettingDao = blockSettingDao;
	}

	public PortletInstanceDao getPortletInstanceDao() {
		return portletInstanceDao;
	}

	public void setPortletInstanceDao(PortletInstanceDao blockInstanceDao) {
		this.portletInstanceDao = blockInstanceDao;
	}
}
