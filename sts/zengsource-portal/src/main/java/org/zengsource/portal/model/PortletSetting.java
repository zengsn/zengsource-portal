/**
 * 
 */
package org.zengsource.portal.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zeng.xiaoning
 * 
 */
public class PortletSetting implements Serializable, Comparable<PortletSetting> {

	private static final long serialVersionUID = 1L;

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	private String id;
	private String key;
	private String name;
	private String value;
	private String usage;

	private Portlet portlet;
	private PortletInstance instance;

	private Date createdTime;
	private Date updatedTime;

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public PortletSetting() {
	}
	
	public PortletSetting(PortletSetting setting) {
		this.key = setting.key;
		this.name = setting.name;
		this.value = setting.value;
		this.usage = setting.usage;
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	
	public int compareTo(PortletSetting o) {
		return this.key.compareTo(o.key);
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public Portlet getPortlet() {
		return portlet;
	}

	public void setPortlet(Portlet prototype) {
		this.portlet = prototype;
	}

	public PortletInstance getInstance() {
		return instance;
	}

	public void setInstance(PortletInstance instance) {
		this.instance = instance;
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
