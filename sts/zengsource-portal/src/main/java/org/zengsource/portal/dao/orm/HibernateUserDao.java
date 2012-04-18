/**
 * 
 */
package org.zengsource.portal.dao.orm;

import org.zengsource.portal.dao.UserDao;
import org.zengsource.portal.model.User;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;


/**
 * @author zengsn
 * @since 1.6
 */
public class HibernateUserDao extends Hibernate3DaoTemplate implements UserDao {

	// + STATIC            ++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS             ++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS          ++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public HibernateUserDao() {
	}

	// + METHODS      ++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return User.class;
	}

	// + G^SETTERS    ++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN              ++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
