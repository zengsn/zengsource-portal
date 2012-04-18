/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.dao;

import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.spring.dao.DaoInterface;

import com.lobosi.module.task.model.TaskExecutor;

/**
 * @author zsn
 * @since 6.0
 */
public interface TaskExecutorDao extends DaoInterface {

	public TaskExecutor queryByUser(UserInfo userInfo);

}
