/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.service;

import org.zengsource.portal.module.user.model.UserInfo;

import com.lobosi.module.task.model.Task;
import com.lobosi.module.task.model.TaskExecutor;

/**
 * @author zsn
 * @since 6.0
 */
public interface TaskExecutorService {

	public TaskExecutor findById(int uid);

	public void reload(Task task);

	public TaskExecutor findByUserInfo(UserInfo userInfo);

	public TaskExecutor getCurrent();

}
