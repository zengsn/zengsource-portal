/*
 * &copy; 2011 ZengSource.com
 * 2011-11-8 下午07:19:26
 */
package org.zengsource.portal.service;

import java.util.List;

import org.zengsource.portal.model.Permission;


/**
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public interface PermissionService {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	/**
	 * 保存。
	 * @return 1 - 成功
	 */
	public int save(Permission permission);

	public List<?> search(String q, Integer start, Integer limit);
	
	public Long searchCount(String q);

	public Permission getById(Integer string2Integer);
	
}
