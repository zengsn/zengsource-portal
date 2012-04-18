/**
 * 
 */
package org.zengsource.portal.dao.orm;

import java.util.Date;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.dao.PortletDao;
import org.zengsource.portal.model.Portlet;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

/**
 * @author snzeng
 * @since 6.0
 */
public class HibernatePortletDao extends Hibernate3DaoTemplate implements PortletDao {

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public HibernatePortletDao() {
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	@Override
	public Class<?> getPrototypeClass() {
		return Portlet.class;
	}

	public void save(Portlet portlet) {
		Date now = new Date();
		if (portlet.getCreatedTime() == null) {
			portlet.setCreatedTime(now);
		}
		portlet.setUpdatedTime(now);
		this.hibernateTemplate.saveOrUpdate(portlet);
	}

	public Portlet queryByPageUrl(String pageUrl) {
		return (Portlet) queryUnique("pageUrl", pageUrl);
	}

	@Override
	protected Criterion[] queryCriterions(String q) {
		return new Criterion[] { Restrictions.or( //
				Restrictions.like("name", q, MatchMode.ANYWHERE), //
				Restrictions.like("name", q, MatchMode.ANYWHERE)) };
	}
	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

}
