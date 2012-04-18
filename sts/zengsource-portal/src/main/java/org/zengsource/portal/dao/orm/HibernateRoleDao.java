/*
 * &copy; 2011 ZengSource.com
 * 2011-11-4 下午04:08:34
 */
package org.zengsource.portal.dao.orm;

import org.zengsource.portal.dao.RoleDao;
import org.zengsource.portal.model.Role;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;


/**
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public class HibernateRoleDao extends Hibernate3DaoTemplate implements RoleDao {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return Role.class;
	}
	
	public void delete(Role role) {
		this.hibernateTemplate.delete(role);
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
