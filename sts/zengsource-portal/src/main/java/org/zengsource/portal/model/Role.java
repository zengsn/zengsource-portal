/**
 * 
 */
package org.zengsource.portal.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色类。
 * 
 * @author zengsn
 * @since 1.6
 */
public class Role {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private Integer id;
	private String name;
	private Role parent;
	private String description;

	private Set<Permission> permissionSet;

	private Date createdTime;
	private Date updatedTime;

	// + CSTORS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public Role() {
		this.permissionSet = new HashSet<Permission>();
	}

	public Role(String name) {
		this();
		this.name = name;
	}

	// + METHODS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public String toString() {
		return this.name;
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++ //

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

	public Role getParent() {
		return parent;
	}

	public void setParent(Role parent) {
		this.parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Permission> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<Permission> permissionSet) {
		this.permissionSet = permissionSet;
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

	// + MAIN +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

}
