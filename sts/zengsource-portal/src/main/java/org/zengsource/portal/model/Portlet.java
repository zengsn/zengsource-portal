/**
 * 
 */
package org.zengsource.portal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author snzeng
 * 
 */
public class Portlet implements Comparable<Portlet>, Serializable {

	private static final long serialVersionUID = 1L;

	public static final String STATIC = "static";
	public static final String DYNAMIC = "dynamic";
	/** for multiple pages */
	public static final String PAGE = "page";

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	private int id;
	private String name;
	private String title;
	private String type;
	private String template;
	private String pageUrl;
	private String html = "Block: " + id;
	// private boolean isTemplate = false;

	private String jsSource;
	private String jsClass;
	private String jsWidget;
	private String jsController;
	private String jsRequiredModule;
	private int colspan = 1;
	private int rowspan = 1;
	private int width = 330;
	private int height = 330;
	private boolean closable = true;
	private boolean collapsible = true;
	private boolean draggable = false;

	private boolean singleton;
	/** 允许访问的角色。 */
	private String roles;

	private Module module;
	private Set<PortletSetting> settingSet;

	private Set<PortletInstance> instanceSet;

	private String description;
	private Date createdTime;
	private Date updatedTime;

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public Portlet() {
		type = STATIC;
		settingSet = new HashSet<PortletSetting>();
		instanceSet = new HashSet<PortletInstance>();
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public int compareTo(Portlet portlet2) {
		if (portlet2 == null) {
			return 1;
		}
		// if (portlet2.getId() == this.id) {
		// return 0;
		// }
		if (this.jsClass != null && this.jsClass.equals(portlet2.getJsClass())) {
			return 0;
		}
		return 1; // TODO
	};

	public void copy(Portlet p) {
		this.name = p.name;
		this.title = p.title;
		this.jsSource = p.jsSource;
		this.jsWidget = p.jsWidget;
		this.jsController = p.jsController;
		this.jsRequiredModule = p.jsRequiredModule;
		this.roles = p.roles;
		this.width = p.width;
		this.height = p.height;
		// TODO
	}

	/** Create block from page. */
	public void buildPageBlock(Page page) {
		setType(PAGE); // !!!
		// setId(page.getUrl());
		setName(page.getUrl());
		setPageUrl(page.getUrl());
		setModule(page.getModule());
	}

	public boolean isPage() {
		return PAGE.equals(type);
	}

	public void addSetting(PortletSetting setting) {
		this.settingSet.add(setting);
	}

	public Set<Page> getPages() {
		Set<Page> pages = new HashSet<Page>();
		for (PortletInstance ins : this.instanceSet) {
			pages.addAll(ins.getPageSet());
		}
		return pages;
	}

	public PortletInstance newInstance(Page page) {
		PortletInstance ins = new PortletInstance(this, page);
		this.instanceSet.add(ins);
		return ins;
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Set<PortletSetting> getSettingSet() {
		return settingSet;
	}

	public void setSettingSet(Set<PortletSetting> settings) {
		this.settingSet = settings;
	}

	public Set<PortletInstance> getInstanceSet() {
		return instanceSet;
	}

	public void setInstanceSet(Set<PortletInstance> instances) {
		this.instanceSet = instances;
	}

	public boolean isSingleton() {
		return singleton;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
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

	public String getJsSource() {
		return jsSource;
	}

	public void setJsSource(String jsSource) {
		this.jsSource = jsSource;
	}

	public String getJsClass() {
		return jsClass;
	}

	public void setJsClass(String jsClass) {
		this.jsClass = jsClass;
	}

	public String getJsWidget() {
		return jsWidget;
	}

	public void setJsWidget(String jsWidget) {
		this.jsWidget = jsWidget;
	}
	
	public String getJsController() {
		return jsController;
	}
	
	public void setJsController(String jsController) {
		this.jsController = jsController;
	}
	
	public String getJsRequiredModule() {
		return jsRequiredModule;
	}
	
	public void setJsRequiredModule(String jsRequiredModule) {
		this.jsRequiredModule = jsRequiredModule;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isClosable() {
		return closable;
	}

	public void setClosable(boolean closable) {
		this.closable = closable;
	}

	public boolean isCollapsible() {
		return collapsible;
	}

	public void setCollapsible(boolean collapsible) {
		this.collapsible = collapsible;
	}

	public boolean isDraggable() {
		return draggable;
	}

	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
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
}
