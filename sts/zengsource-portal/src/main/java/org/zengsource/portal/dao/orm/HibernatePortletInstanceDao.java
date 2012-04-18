/**
 * 
 */
package org.zengsource.portal.dao.orm;

import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.dao.PortletInstanceDao;
import org.zengsource.portal.model.PortletInstance;
import org.zengsource.portal.model.Portlet;
import org.zengsource.portal.model.Page;
import org.zengsource.util.StringUtil;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

/**
 * @author snzeng
 * 
 */
public class HibernatePortletInstanceDao extends Hibernate3DaoTemplate implements
		PortletInstanceDao {

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public HibernatePortletInstanceDao() {
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	@Override
	public Class<?> getPrototypeClass() {
		return PortletInstance.class;
	}

	public Integer queryCount(final Page page, final String q) {
		return super.queryCount(queryWithPageCriterions(page, q));
	}

	public List<?> query(final Page page, final String q, final Integer start, final Integer limit) {
		return super.query(queryWithPageCriterions(page, q), start, limit);
	}

	private Criterion[] queryWithPageCriterions(Page page, String q) {
		Criterion[] queryCriterions = this.queryCriterions(q);
		if (page != null) {
			Criterion[] queryWithPageCriterions = Arrays.copyOf(queryCriterions,
					queryCriterions.length + 1);
			queryWithPageCriterions[queryCriterions.length] = Restrictions.eq("page", page);
			return queryWithPageCriterions;
		}
		return queryCriterions;
	}

	@Override
	protected Criterion[] queryCriterions(String q) {
		if (StringUtil.notBlank(q)) {
			return new Criterion[] { Restrictions.like("name", q, MatchMode.ANYWHERE) };
		} else {
			return super.queryCriterions(q);
		}
	}

	public List<?> query(final Portlet portlet) {
		return super.query(new Criterion[] { //
				Restrictions.eq("portlet", portlet) }, 0, 0);
	}

	public PortletInstance query(final Page page, final Portlet portlet) {
		return (PortletInstance) super.queryUnique(//
				new Criterion[] { //
				Restrictions.eq("portlet", portlet), //
						Restrictions.eq("page", page) });
	}

	public void delete(PortletInstance instance) {
		this.hibernateTemplate.delete(instance);
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

}
