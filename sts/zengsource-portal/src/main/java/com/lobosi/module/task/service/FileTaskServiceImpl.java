/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.service;

import com.lobosi.module.task.dao.FileTaskDao;
import com.lobosi.module.task.model.FileTask;

/**
 * @author zsn
 * @since 6.0
 */
public class FileTaskServiceImpl implements FileTaskService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private FileTaskDao fileTaskDao;

	private TaskService taskService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public String prepareUploadFolder(FileTask fileTask) {
		return this.taskService.prepareUploadFolder(fileTask.getTask());
	};

	public void save(FileTask fileTask) {
		if (fileTask == null) {
			return;
		}
		this.taskService.save(fileTask.getTask());
		this.fileTaskDao.updateTime(fileTask);
		this.fileTaskDao.save(fileTask);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public FileTaskDao getFileTaskDao() {
		return fileTaskDao;
	}

	public void setFileTaskDao(FileTaskDao fileTaskDao) {
		this.fileTaskDao = fileTaskDao;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
