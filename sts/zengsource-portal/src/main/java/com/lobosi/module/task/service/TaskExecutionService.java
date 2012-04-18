/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-10
 */
package com.lobosi.module.task.service;

import java.util.List;

import com.lobosi.module.task.model.Task;
import com.lobosi.module.task.model.TaskExecution;
import com.lobosi.module.task.model.TaskExecutor;

/**
 * @author zsn
 * @since 6.0
 */
public interface TaskExecutionService {

	public void save(TaskExecution execution);

	/** 搜索任务执行者所执行的任务进程。*/
	public int searchCount(TaskExecutor executor, String q);

	public List<?> search(TaskExecutor executor, String q, int start, int limit);

	/** 查找任务的主进程。*/
	public TaskExecution findMain(Task task);

	public TaskExecution findById(int id);

}
