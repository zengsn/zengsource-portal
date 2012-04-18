/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-7
 */
package com.lobosi.module.social.dao.orm;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.StringUtil;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

import com.lobosi.module.social.dao.FriendshipDao;
import com.lobosi.module.social.model.Friendship;

/**
 * @author zsn
 * @since 6.0
 */
public class HibernateFriendshipDao extends Hibernate3DaoTemplate implements FriendshipDao {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return Friendship.class;
	}

	public Friendship query(UserInfo partA, UserInfo partB) {
		return (Friendship) super.queryUnique(new Criterion[] { //
				Restrictions.or(
						Restrictions.and(Restrictions.eq("partA", partA),
								Restrictions.eq("partB", partB)),
						Restrictions.and(Restrictions.eq("partA", partB),
								Restrictions.eq("partB", partA))) });
	}

	public List<?> query(UserInfo partA, String q, Integer start, Integer limit) {
		return super.query(friendCriterions(partA, q), start, limit);
	}

	public int queryCount(UserInfo user, String q) {
		return super.queryCount(friendCriterions(user, q));
	}

	private Criterion[] friendCriterions(UserInfo user, String q) {
		if (StringUtil.isBlank(q)) {
			return new Criterion[] { //
			Restrictions.or(Restrictions.eq("partA", user), Restrictions.eq("partB", user)), //
					Restrictions.eq("status", Friendship.ACCEPTED) };
		} else {
			return new Criterion[] { //
			Restrictions.or(Restrictions.eq("partA", user), Restrictions.eq("partB", user)), //
					Restrictions.eq("status", Friendship.ACCEPTED) };
		}
	}

	public List<?> query(UserInfo partB, int status, Integer start, Integer limit) {
		return super.query(requestCriterions(partB, status), start, limit);
	}

	public int queryCount(UserInfo partB, int status) {
		return super.queryCount(requestCriterions(partB, status));
	}

	private Criterion[] requestCriterions(UserInfo partB, int status) {
		return new Criterion[] { //
		Restrictions.eq("partB", partB), Restrictions.eq("status", status) };
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
