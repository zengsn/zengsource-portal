/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-20
 */
package org.zengsource.portal.dao.orm;

import org.zengsource.portal.dao.InvitationDao;
import org.zengsource.portal.model.Invitation;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

/**
 * @author zsn
 * @since 6.0
 */
public class HibernateInvitationDao extends Hibernate3DaoTemplate implements InvitationDao {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return Invitation.class;
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
