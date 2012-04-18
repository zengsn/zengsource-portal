/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.dao.orm;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.StringUtil;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

import com.lobosi.module.task.dao.TaskDao;
import com.lobosi.module.task.model.Task;

/**
 * @author zsn
 * @since 6.0
 */
public class HibernateTaskDao extends Hibernate3DaoTemplate implements TaskDao {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return Task.class;
	}

	@Override
	protected Criterion[] queryCriterions(String q) {
		return new Criterion[] { //
		Restrictions.like("name", q, MatchMode.ANYWHERE), //
				Restrictions.like("introduction", q, MatchMode.ANYWHERE) };
	}

	public int queryCount(UserInfo requestor, String q) {
		if (requestor == null) {
			return 0;
		}
		return super.queryCount(this.requestorCriterions(requestor, q));
	}
	
	public List<?> query(UserInfo requestor, String q, Integer start, Integer limit) {
		if (requestor == null) {
			return null;
		}
		return super.query(this.requestorCriterions(requestor, q), start, limit, Order.desc("createdTime"));
	}

	private Criterion[] requestorCriterions(UserInfo requestor, String q) {
		if (StringUtil.isBlank(q)) {
			return new Criterion[] { Restrictions.eq("requestor", requestor) };
		}
		return new Criterion[] { //
		Restrictions.like("name", q, MatchMode.ANYWHERE), //
				Restrictions.like("introduction", q, MatchMode.ANYWHERE) };
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
