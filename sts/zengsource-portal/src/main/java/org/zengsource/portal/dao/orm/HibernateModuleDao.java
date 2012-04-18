/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-23
 */
package org.zengsource.portal.dao.orm;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.dao.ModuleDao;
import org.zengsource.portal.model.Module;
import org.zengsource.util.StringUtil;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

/**
 * @author zsn
 * @since 6.0
 */
public class HibernateModuleDao extends Hibernate3DaoTemplate implements ModuleDao {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return Module.class;
	}

	@Override
	protected Criterion[] queryCriterions(String q) {
		if (StringUtil.notBlank(q)) {
			return new Criterion[] { Restrictions.like("name", q, MatchMode.ANYWHERE) };
		}
		return super.queryCriterions(q);
	}

	public List<?> queryByName(String name) {
		return this.query(new Criterion[] { //
				Restrictions.eq("name", name) }, 0, 0);
	}

	public List<?> queryByStatus(int status, int start, int limit) {
		return super.query(new Criterion[] {//
				Restrictions.eq("status", status) }, start, limit, Order.asc("name"));
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
