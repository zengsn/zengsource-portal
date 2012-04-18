/**
 * 
 */
package org.zengsource.portal.dao.orm;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.dao.PageDao;
import org.zengsource.portal.model.Module;
import org.zengsource.portal.model.Page;
import org.zengsource.util.StringUtil;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

/**
 * @author snzeng
 * @since 6.0
 */
public class HibernatePageDao extends Hibernate3DaoTemplate implements PageDao {

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public HibernatePageDao() {
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	@Override
	public Class<?> getPrototypeClass() {
		return Page.class;
	}

	@Override
	protected Criterion[] queryCriterions(String q) {
		if (StringUtil.isBlank(q)) {
			return new Criterion[]{};
		}
		return new Criterion[] { Restrictions.or( //
				Restrictions.like("name", q, MatchMode.ANYWHERE),//
				Restrictions.like("url", q, MatchMode.ANYWHERE)) };
	}

	public void save(Page page) {
		this.updateTime(page);
		this.hibernateTemplate.saveOrUpdate(page);
	}

	public List<?> queryByModule(Module module, Integer start, Integer limit) {
		return super.query(//
				new Criterion[] { Restrictions.eq("module", module) },//
				start, limit);
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

}
