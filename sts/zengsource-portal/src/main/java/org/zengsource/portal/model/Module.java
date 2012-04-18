/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-16
 */
package org.zengsource.portal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 * @author zsn
 * @since 6.0
 */
public class Module implements Serializable{
	
	// + STATIC  +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;
	
	/** brand new module, need to be initialized. */
	public static final int UNINIT = 0;
	/** module has already been initialized and ready to work. */
	public static final int READY = 1;
	/** module is working online. */
	public static final int ONLINE = 2;
	/** module is closed by administrator. */
	public static final int OFFLINE = 3;
	
	/** 模块操作 */
	public static final String OPT_INFO = "info";
	public static final String OPT_MENU = "menu";
	public static final String OPT_PORTLET = "portlet";
	public static final String OPT_PAGE = "page";
	public static final String OPT_ALL = "info,menu,portlet,page";

	// + FIELDS   ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	private int id;
	private String name;
	private String title;
	private String description;
	
	private Integer status = UNINIT;
	private Integer index = 0;
	/** 1:n */
	private Set<Menu> menuSet;
	// TODO reserved
	private Map<String, String> iconMap;
	private List<Config> configurations;

	private Set<Portlet> portletSet;

	private Set<Page> pageSet;
	
	private Date createdTime;
	private Date updatedTime;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public Module() {
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public void copy(Module module) {
		//this.name = module.getName();
		this.title = module.title;
		this.description = module.description;
		this.menuSet = module.menuSet;
		this.pageSet = module.pageSet;
		this.portletSet = module.portletSet;
	}

	public void addMenu(Menu menu) {
		if (menu == null) {
			return;
		}
		if (getMenuSet() == null) {
			setMenuSet(new HashSet<Menu>());
		}
		getMenuSet().add(menu);
	}

	public void addPortlet(Portlet portlet) {
		if (portlet == null) {
			return;
		}
		if (getPortletSet() == null) {
			setPortletSet(new HashSet<Portlet>());
		}
		getPortletSet().add(portlet);
	}

	public void addPage(Page page) {
		if (page == null) {
			return;
		}
		if (getPageSet() == null) {
			setPageSet(new HashSet<Page>());
		}
		getPageSet().add(page);
		
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Set<Menu> getMenuSet() {
		return menuSet;
	}

	public void setMenuSet(Set<Menu> menuSet) {
		this.menuSet = menuSet;
	}

	public Map<String, String> getIconMap() {
		return iconMap;
	}

	public void setIconMap(Map<String, String> iconMap) {
		this.iconMap = iconMap;
	}

	public List<Config> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<Config> configurations) {
		this.configurations = configurations;
	}

	public Set<Portlet> getPortletSet() {
		return portletSet;
	}

	public void setPortletSet(Set<Portlet> portletSet) {
		this.portletSet = portletSet;
	}

	public Set<Page> getPageSet() {
		return pageSet;
	}

	public void setPageSet(Set<Page> pages) {
		this.pageSet = pages;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
