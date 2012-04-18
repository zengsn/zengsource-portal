/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-8
 */
package org.zengsource.portal.module.user.dao.orm;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.module.user.dao.UserInfoDao;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

/**
 * @author zsn
 * @since 6.0
 */
public class HibernateUserInfoDao extends Hibernate3DaoTemplate implements UserInfoDao {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return UserInfo.class;
	}

	public int queryCountInterested(UserInfo user) {
		return this.queryCount(interestedCriterions(user));
	}

	public List<?> queryInterested(UserInfo user, int start, int limit) {
		return this.query(interestedCriterions(user), start, limit);
	}

	private Criterion[] interestedCriterions(UserInfo user) {
		return new Criterion[] { // TODO
		Restrictions.ne("id", user.getId()) };
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
