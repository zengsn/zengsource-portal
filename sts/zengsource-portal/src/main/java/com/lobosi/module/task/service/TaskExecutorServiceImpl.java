/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.service;

import java.util.HashSet;
import java.util.Set;

import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.portal.module.user.service.UserInfoService;

import com.lobosi.module.task.dao.TaskExecutorDao;
import com.lobosi.module.task.model.Task;
import com.lobosi.module.task.model.TaskExecutor;

/**
 * @author zsn
 * @since 6.0
 */
public class TaskExecutorServiceImpl implements TaskExecutorService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private TaskExecutorDao taskExecutorDao;
	private TaskService taskService;
	private UserInfoService userInfoService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public TaskExecutor getCurrent() {
		return this.findByUserInfo(this.userInfoService.getCurrent());
	};

	public TaskExecutor findById(int uid) {
		if (uid == 0) {
			return null;
		}
		UserInfo userInfo = this.userInfoService.findById(uid);
		if (userInfo == null) {
			return null;
		}
		TaskExecutor taskMan = this.findByUserInfo(userInfo);
		return taskMan;
	};

	public TaskExecutor findByUserInfo(UserInfo userInfo) {
		if (userInfo == null) {
			return null;
		}
		TaskExecutor taskMan = this.taskExecutorDao.queryByUser(userInfo);
		if (taskMan == null) {
			taskMan = this.create(userInfo);
		}
		return taskMan;
	}

	private TaskExecutor create(UserInfo userInfo) {
		TaskExecutor taskMan = new TaskExecutor(userInfo);
		taskMan.setAvatar(userInfo.getUser().getAvatar());
		taskMan.setUsername(userInfo.getUsername());
		taskMan.setNickname(userInfo.getNickname());
		this.taskExecutorDao.updateTime(taskMan);
		this.taskExecutorDao.save(taskMan);
		return taskMan;
	}

	public void reload(Task task) {
		if (task == null) {
			return;
		}
		Task oldTask = this.taskService.findById(task.getId());
		Set<TaskExecutor> newTaskMen = task.getExecutorSet();
		Set<TaskExecutor> ignoredSet = new HashSet<TaskExecutor>();
		Set<TaskExecutor> resultSet = new HashSet<TaskExecutor>();
		// 处理旧的执行者
		if (oldTask != null) {
			Set<TaskExecutor> oldTaskMen = oldTask.getExecutorSet();
			if (oldTaskMen != null) {
				for (TaskExecutor oldTaskMan : oldTaskMen) {
					boolean delete = true; // 是否删除
					for (TaskExecutor newTaskMan : newTaskMen) {
						if (oldTaskMan.getId() == newTaskMan.getId()) {
							resultSet.add(oldTaskMan); // 直接使用原有对象
							ignoredSet.add(newTaskMan); // 新增时忽略此对象
							delete = false; // 重用，不需要删除
						}
					}
					if (delete) { // 删除未指定的执行者
						this.delete(oldTaskMan);
					}
				}
			}
		}
		// 处理新增执行者
		if (newTaskMen != null) {
			for (TaskExecutor newTaskMan : newTaskMen) {
				boolean fresh = true;
				for (TaskExecutor ignore : ignoredSet) {
					if (newTaskMan.getId() == ignore.getId()) {
						fresh = false; // 忽略
					}
				}
				if (fresh) { // 新增
					resultSet.add(newTaskMan);
				}
			}
		}
		// 保存返回
		task.setExecutorSet(resultSet);
	}

	private void delete(TaskExecutor taskMan) {
		if (taskMan != null) {
			this.taskExecutorDao.delete(taskMan);
		}
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public TaskExecutorDao getTaskExecutorDao() {
		return taskExecutorDao;
	}

	public void setTaskExecutorDao(TaskExecutorDao taskManDao) {
		this.taskExecutorDao = taskManDao;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
