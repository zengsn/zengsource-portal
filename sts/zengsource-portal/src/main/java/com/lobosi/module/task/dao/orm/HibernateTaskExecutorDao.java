/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.dao.orm;

import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

import com.lobosi.module.task.dao.TaskExecutorDao;
import com.lobosi.module.task.model.TaskExecutor;

/**
 * @author zsn
 * @since 6.0
 */
public class HibernateTaskExecutorDao extends Hibernate3DaoTemplate implements TaskExecutorDao {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	@Override
	public Class<?> getPrototypeClass() {
		return TaskExecutor.class;
	}
	
	public TaskExecutor queryByUser(UserInfo userInfo) {
		if (userInfo == null) {
			return null;
		}
		return (TaskExecutor) super.queryUnique("user", userInfo);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
