/**
 * 
 */
package org.zengsource.portal.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.dao.PortletDao;
import org.zengsource.portal.dao.PortletSettingDao;
import org.zengsource.portal.model.Portlet;
import org.zengsource.portal.model.PortletSetting;
import org.zengsource.portal.model.Module;
import org.zengsource.util.StringUtil;

/**
 * @author snzeng
 * 
 */
public class PortletServiceImpl implements PortletService {

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	private PortletSettingDao portletSettingDao;
	private PortletDao portletDao;

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public PortletServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public void save(Portlet portlet) {
		if (portlet == null) {
			return;
		}
		// Save portlet
		this.portletDao.updateTime(portlet);
		this.portletDao.save(portlet);

		Set<PortletSetting> settings = portlet.getSettingSet();
		// Save settings
		if (settings != null) {
			for (PortletSetting setting : settings) {
				setting.setPortlet(portlet);
				this.portletSettingDao.save(setting);
			}
		}
	}
	
	/** 重新加载模块中的区块配置。*/
	public void reload(Module module) {
		List<?> oldPortlets = this.search(module, 0, 0);
		Set<Portlet> newPortlets = module.getPortletSet();
		Set<Portlet> portletSet = new HashSet<Portlet>();
		Set<Portlet> ignoredSet = new HashSet<Portlet>();
		for(Object obj : oldPortlets) { // TODO 算法优化
			Portlet oldPortlet = (Portlet) obj;
			for(Portlet newPortlet : newPortlets) {
				if (oldPortlet.compareTo(newPortlet)==0) {
					oldPortlet.copy(newPortlet);
					this.save(oldPortlet); // 更新
					//newPortlets.remove(newPortlet);
					ignoredSet.add(newPortlet); 
					portletSet.add(oldPortlet);
				} 
			}
		}
		// 增加新区块
		for(Portlet newPortlet : newPortlets) {
			boolean exists = false;
			for(Portlet ignore : ignoredSet) {
				if (newPortlet.equals(ignore) ) {
					exists = true;
					break;
				}
			}
			if (!exists) { // 新增
				newPortlet.setModule(module);
				this.save(newPortlet); // 保存
				portletSet.add(newPortlet);
			}
		}
		// 取回
		module.setPortletSet(portletSet);
	}

	public Integer searchCount(String q) {
		return this.portletDao.queryCount(q);
	}

	public List<?> search(String q, Integer start, Integer limit) {
		return this.portletDao.query(q, start, limit);
	}

	public Integer searchCount(Module module) {
		return this.portletDao.queryCount(this.searchCriterions(module));
	}

	public List<?> search(Module module, Integer start, Integer limit) {
		return this.portletDao.query(this.searchCriterions(module), start, limit);
	}

	private Criterion[] searchCriterions(Module module) {
		return new Criterion[] { Restrictions.eq("module", module) };
	}

	public Portlet getById(int id) {
		return (Portlet) this.portletDao.queryById(id);
	}

	public Portlet getByPageUrl(String pageUrl) {
		if (StringUtil.notBlank(pageUrl)) {
			return this.portletDao.queryByPageUrl(pageUrl);
		}
		return null;
	}
	
	public Portlet findByJsClass(String jsClass) {
		if (StringUtil.isBlank(jsClass)) {
			return null;
		}
		return (Portlet) this.portletDao.queryUnique("jsClass", jsClass);
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public PortletSettingDao getPortletSettingDao() {
		return portletSettingDao;
	}

	public void setPortletSettingDao(PortletSettingDao blockSettingDao) {
		this.portletSettingDao = blockSettingDao;
	}

	public PortletDao getPortletDao() {
		return portletDao;
	}

	public void setPortletDao(PortletDao blockPrototypeDao) {
		this.portletDao = blockPrototypeDao;
	}

}
