/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-1
 */
package org.zengsource.portal.dao.orm;

import org.zengsource.portal.dao.ConfigDao;
import org.zengsource.portal.model.Config;
import org.zengsource.util.StringUtil;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

/**
 * @author zsn
 * @since 6.0
 */
public class HibernateConfigDao extends Hibernate3DaoTemplate implements ConfigDao {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return Config.class;
	}

	public Config queryByKey(String key) {
		if (StringUtil.isBlank(key)) {
			return null;
		}
		return (Config) super.queryUnique("key", key);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
