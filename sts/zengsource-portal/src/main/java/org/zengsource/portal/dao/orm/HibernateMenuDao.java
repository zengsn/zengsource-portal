/**
 * 
 */
package org.zengsource.portal.dao.orm;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.dao.MenuDao;
import org.zengsource.portal.model.Menu;
import org.zengsource.portal.model.Module;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

/**
 * @author snzeng
 * @since 6.0
 */
public class HibernateMenuDao extends Hibernate3DaoTemplate implements MenuDao {

	public HibernateMenuDao() {
	}

	@Override
	public Class<?> getPrototypeClass() {
		return Menu.class;
	}

	public void save(Menu menu) {
		this.updateTime(menu);
		logger.info("Save menu: " + menu.getName());

		Set<Menu> children = menu.getChildren();
		if (children != null && children.size() > 0) {
			for (Menu item : children) {
				item.setModule(menu.getModule());
				this.save(item);
			}
		}

		this.hibernateTemplate.saveOrUpdate(menu);
	}

	public void delete(Menu menu) {
		this.hibernateTemplate.delete(menu);
	}

	public List<?> queryByModule(Module module, int start, int limit) {
		if (module == null) {
			return null;
		}
		return this.query(new Criterion[] {//
				Restrictions.eq("module", module) }, start, limit);
	}

}
