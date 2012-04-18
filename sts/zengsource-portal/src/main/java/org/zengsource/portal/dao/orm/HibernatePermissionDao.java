/*
 * &copy; 2011 ZengSource.com
 * 2011-11-8 下午07:18:07
 */
package org.zengsource.portal.dao.orm;

import org.zengsource.portal.dao.PermissionDao;
import org.zengsource.portal.model.Permission;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;


/**
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public class HibernatePermissionDao extends Hibernate3DaoTemplate implements PermissionDao {

	// + STATIC  +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS   +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public HibernatePermissionDao() {
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return Permission.class;
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
