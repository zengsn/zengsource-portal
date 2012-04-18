/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-10
 */
package com.lobosi.module.task.service;

import java.util.List;

import com.lobosi.module.task.dao.TaskNoteDao;
import com.lobosi.module.task.model.TaskExecution;
import com.lobosi.module.task.model.TaskNote;

/**
 * @author zsn
 * @since 6.0
 */
public class TaskNoteServiceImpl implements TaskNoteService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private TaskNoteDao taskNoteDao;
	private TaskExecutionService taskExecutionService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int searchCount(TaskExecution execution, String q) {
		if (execution == null) {
			return 0;
		}
		return this.taskNoteDao.queryCount(execution, q);
	};
	
	public List<?> search(TaskExecution execution, String q, Integer start, Integer limit) {
		if (execution == null) {
			return null;
		}
		return this.taskNoteDao.query(execution, q, start, limit);
	}
	
	public void save(TaskNote note) {
		if (note != null) {
			this.taskNoteDao.updateTime(note);
			this.taskNoteDao.save(note);
		}
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public void setTaskNoteDao(TaskNoteDao taskNoteDao) {
		this.taskNoteDao = taskNoteDao;
	}

	public TaskNoteDao getTaskNoteDao() {
		return taskNoteDao;
	}
	
	public TaskExecutionService getTaskExecutionService() {
		return taskExecutionService;
	}
	
	public void setTaskExecutionService(TaskExecutionService taskExecutionService) {
		this.taskExecutionService = taskExecutionService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
