/**
 * 
 */
package org.zengsource.portal.dao.orm;

import java.util.Date;

import org.zengsource.portal.dao.PortletSettingDao;
import org.zengsource.portal.model.PortletSetting;
import org.zengsource.util.IDUtil;
import org.zengsource.util.StringUtil;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

/**
 * @author zeng.xiaoning
 * @since 6.0
 */
public class HibernatePortletSettingDao extends Hibernate3DaoTemplate implements PortletSettingDao {

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public HibernatePortletSettingDao() {
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	@Override
	public Class<?> getPrototypeClass() {
		return PortletSetting.class;
	}

	public void save(PortletSetting setting) {
		Date now = new Date();
		if (setting.getCreatedTime() == null) {
			setting.setCreatedTime(now);
		}
		setting.setUpdatedTime(now);

		if (StringUtil.isBlank(setting.getId())) {
			setting.setId(IDUtil.generate());
			this.hibernateTemplate.save(setting);
		} else {
			this.hibernateTemplate.update(setting);
		}
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

}
