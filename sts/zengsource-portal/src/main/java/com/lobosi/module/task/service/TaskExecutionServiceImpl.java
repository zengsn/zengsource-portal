/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-10
 */
package com.lobosi.module.task.service;

import java.util.List;

import com.lobosi.module.task.dao.TaskExecutionDao;
import com.lobosi.module.task.model.Task;
import com.lobosi.module.task.model.TaskExecution;
import com.lobosi.module.task.model.TaskExecutor;

/**
 * @author zsn
 * @since 6.0
 */
public class TaskExecutionServiceImpl implements TaskExecutionService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	private TaskExecutionDao taskExecutionDao;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public void save(TaskExecution execution) {
		if (execution != null) {
			this.taskExecutionDao.updateTime(execution);
			this.taskExecutionDao.save(execution);
		}
	}
	
	public int searchCount(TaskExecutor executor, String q) {
		return this.taskExecutionDao.queryCount(executor, q);
	}
	
	public List<?> search(TaskExecutor executor, String q, int start, int limit) {
		return this.taskExecutionDao.query(executor, q, start, limit);
	}
	
	public TaskExecution findMain(Task task) {
		if (task == null) {
			return null;
		}
		return this.taskExecutionDao.queryMain(task);
	}
	
	public TaskExecution findById(int id) {
		if (id <= 0) {
			return null;
		}
		return (TaskExecution) this.taskExecutionDao.queryById(id);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public TaskExecutionDao getTaskExecutionDao() {
		return taskExecutionDao;
	}
	
	public void setTaskExecutionDao(TaskExecutionDao taskExecutionDao) {
		this.taskExecutionDao = taskExecutionDao;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
