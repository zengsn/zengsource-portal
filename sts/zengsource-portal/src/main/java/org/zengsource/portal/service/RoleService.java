/*
 * &copy; 2011 ZengSource.com
 * 2011-11-8 下午07:30:56
 */
package org.zengsource.portal.service;

import java.util.List;

import org.zengsource.portal.model.Role;


/**
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public interface RoleService {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public List<?> search(String q, Integer startInt, Integer limitInt);

	public Long searchCount(String q);

	/**
	 * 保存。
	 * @param role
	 * @return　１- 表示成功
	 */
	public int save(Role role);

	public Role getById(Integer id);

	public int delete(Role role);

	public Role getByName(String roleName);

	/** 
	 * 创建一个或多个角色。<br />
	 * 格式：member > admin, member > teacher, guest 
	 * @return 返回最后的权限
	 */
	public Role create(String roles);
	
}
