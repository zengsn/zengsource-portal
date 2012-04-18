/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-11
 */
package com.lobosi.module.social.dao.orm;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.StringUtil;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

import com.lobosi.module.social.dao.UserUpdateDao;
import com.lobosi.module.social.model.UserUpdate;

/**
 * @author zsn
 * @since 6.0
 */
public class HibernateUserUpdateDao extends Hibernate3DaoTemplate implements UserUpdateDao {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return UserUpdate.class;
	}

	@Override
	protected Criterion[] queryCriterions(String q) {
		if (StringUtil.notBlank(q)) {
			return new Criterion[] { Restrictions.like("text", q, MatchMode.ANYWHERE) };
		}
		return super.queryCriterions(q);
	}

	@Override
	public List<?> query(String q, Integer start, Integer limit) {
		return this.query(queryCriterions(q), start, limit, Order.desc("createdTime"));
	}

	public List<?> query(UserInfo userInfo, Integer start, Integer limit) {
		return super.query(userCriterions(userInfo), start, limit, Order.desc("createdTime"));
	}

	public int queryCount(UserInfo userInfo) {
		return super.queryCount(userCriterions(userInfo));
	}

	private Criterion[] userCriterions(UserInfo userInfo) {
		return new Criterion[] { Restrictions.eq("owner", userInfo) };
	}

	public UserUpdate queryLastUpdate(UserInfo userInfo) {
		List<?> lastUpdate = super.query(new Criterion[] { //
				Restrictions.eq("owner", userInfo) }, 0, 1, Order.desc("createdTime"));
		if (lastUpdate != null && lastUpdate.size() == 1) {
			return (UserUpdate) lastUpdate.get(0);
		}
		return null;
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
