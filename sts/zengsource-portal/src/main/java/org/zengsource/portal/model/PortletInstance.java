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
public class PortletInstance implements Serializable, Comparable<PortletInstance> {

	private static final long serialVersionUID = 1L;

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	private int id;
	private String name;
	private String cls;
	private String style;
	private int index = 0;
	private int colspan = 1;
	private int rowspan = 1;
	private int width = 330;
	private int height = 330;
	private String html;
	private Set<Page> pageSet;
	private Portlet portlet;
	private Set<PortletSetting> settingSet;

	private String description;
	private Date createdTime;
	private Date updatedTime;

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public PortletInstance() {
		settingSet = new HashSet<PortletSetting>();
		pageSet = new HashSet<Page>();
	}

	public PortletInstance(Portlet prototype, Page page) {
		this();
		this.name = prototype.getName();
		// this.settings = prototype.getSettings();
		this.portlet = prototype;
		// this.addPage(page);
		if (page != null) {
			this.name = prototype.getName() + " (" + page.getName() + ")";
			page.addPortlet(this);
		}
		for (PortletSetting setting : prototype.getSettingSet()) {
			PortletSetting newSetting = new PortletSetting(setting);
			newSetting.setInstance(this);
			this.addSetting(newSetting);
		}
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public void addSetting(PortletSetting setting) {
		// for (BlockSetting bs : this.settings) {
		// if (bs != null && bs.getKey().equals(setting.getKey()) {
		// return; // Same setting exists.
		// }
		// }
		this.settingSet.add(setting);
	}

	public void addPage(Page page) {
		if (this.pageSet == null) {
			this.pageSet = new HashSet<Page>();
		}
		this.pageSet.add(page);
	}

	public void removePage(Page page) {
		if (this.pageSet == null) {
			return;
		}
		this.pageSet.remove(page);
	}

	public int compareTo(PortletInstance other) {
		return this.id == other.id ? 0 : 1;
	}

	public void copy(PortletInstance pi) {
		this.index = pi.index;
		this.width = pi.width;
		this.height = pi.height;
		this.colspan = pi.colspan;
		this.rowspan = pi.rowspan;
		this.settingSet = pi.settingSet;
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Page> getPageSet() {
		return pageSet;
	}

	public void setPageSet(Set<Page> pages) {
		this.pageSet = pages;
	}

	public Set<PortletSetting> getSettingSet() {
		return settingSet;
	}

	public void setSettingSet(Set<PortletSetting> settings) {
		this.settingSet = settings;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Portlet getPortlet() {
		return portlet;
	}

	public void setPortlet(Portlet portlet) {
		this.portlet = portlet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
