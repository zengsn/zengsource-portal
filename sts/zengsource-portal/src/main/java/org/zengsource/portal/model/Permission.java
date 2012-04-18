/*
 * &copy; 2011 ZengSource.com
 * 2011-11-6 下午10:47:25
 */
package org.zengsource.portal.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限。
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public class Permission implements Serializable{

	// + STATIC  +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS   +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	private Integer id;
	private String name;
	private Role role;
	
	private Date createdTime;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public Permission() {
	}

	public Permission(String name) {
		this();
		this.name = name;
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	@Override
	public String toString() {
		return this.name;
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	// + MAIN^TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
