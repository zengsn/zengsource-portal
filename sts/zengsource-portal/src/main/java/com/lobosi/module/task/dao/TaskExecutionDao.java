/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-10
 */
package com.lobosi.module.task.dao;

import java.util.List;

import org.zengsource.util.spring.dao.DaoInterface;

import com.lobosi.module.task.model.Task;
import com.lobosi.module.task.model.TaskExecution;
import com.lobosi.module.task.model.TaskExecutor;

/**
 * @author zsn
 * @since 6.0
 */
public interface TaskExecutionDao extends DaoInterface {

	public int queryCount(TaskExecutor executor, String q);

	public List<?> query(TaskExecutor executor, String q, int start, int limit);

	public TaskExecution queryMain(Task task);

}
