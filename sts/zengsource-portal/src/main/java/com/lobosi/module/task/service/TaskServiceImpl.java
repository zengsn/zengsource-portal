/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.service;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.zengsource.mvc.MvcContext;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.portal.module.user.service.UserInfoService;

import com.lobosi.module.task.dao.TaskDao;
import com.lobosi.module.task.model.Task;
import com.lobosi.module.task.model.TaskExecution;
import com.lobosi.module.task.model.TaskExecutor;

/**
 * @author zsn
 * @since 6.0
 */
public class TaskServiceImpl implements TaskService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private TaskDao taskDao;
	private UserInfoService userInfoService;
	private TaskExecutorService taskExecutorService;
	private TaskExecutionService taskExecutionService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public void save(Task task) {
		this.taskDao.updateTime(task);
		// 设置发起人
		task.setRequestor(this.userInfoService.getCurrent());
		Set<TaskExecutor> taskExecutorSet = task.getExecutorSet();
		task.setExecutorSet(null);
		this.taskDao.save(task);
		// 保存执行者
		task.setExecutorSet(taskExecutorSet);
		this.taskExecutorService.reload(task);
		// 保存关系
		this.taskDao.save(task);
		// 创建主执行进程
		if (task.getStatus() > Task.DRAFT) {
			TaskExecution mainExecution = this.taskExecutionService.findMain(task);
			if (mainExecution == null) {
				mainExecution = task.createMainExecution();
				mainExecution.setExecutor(this.taskExecutorService.findByUserInfo(task.getRequestor()));
				this.taskExecutionService.save(mainExecution);
			}
		}
	};

	public Task findById(int id) {
		if (id == 0) {
			return null;
		}
		return (Task) this.taskDao.queryById(id);
	}

	public int searchCount(String q) {
		return this.taskDao.queryCount(q);
	}

	public List<?> search(String q, Integer start, Integer limit) {
		return this.taskDao.query(q, start, limit);
	}
	
	public int searchCount(UserInfo requestor, String q) {
		return this.taskDao.queryCount(requestor, q);
	}
	
	public List<?> search(UserInfo requestor, String q, Integer start, Integer limit) {
		return this.taskDao.query(requestor, q, start, limit);
	}
	
	public String prepareUploadFolder(Task task) {
		String rootPath = MvcContext.getInstance().getRootPath();
		String preparePath = rootPath + "upload/task";
		if (task != null && task.getId() > 0) {
			preparePath += "/" + task.getId();
		}
		File moduleDir = new File(preparePath);
		if (!moduleDir.exists()) {
			moduleDir.mkdirs();
		}
		return moduleDir.getAbsolutePath();
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public TaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public TaskExecutorService getTaskExecutorService() {
		return taskExecutorService;
	}

	public void setTaskExecutorService(TaskExecutorService taskManService) {
		this.taskExecutorService = taskManService;
	}
	
	public TaskExecutionService getTaskExecutionService() {
		return taskExecutionService;
	}
	
	public void setTaskExecutionService(TaskExecutionService taskExecutionService) {
		this.taskExecutionService = taskExecutionService;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
