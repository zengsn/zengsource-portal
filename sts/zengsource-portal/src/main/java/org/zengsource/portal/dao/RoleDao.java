/*
 * &copy; 2011 ZengSource.com
 * 2011-11-4 下午04:05:46
 */
package org.zengsource.portal.dao;

import org.zengsource.portal.model.Role;
import org.zengsource.util.spring.dao.DaoInterface;


/**
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public interface RoleDao extends DaoInterface {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS ++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public void delete(Role role);
	
}
