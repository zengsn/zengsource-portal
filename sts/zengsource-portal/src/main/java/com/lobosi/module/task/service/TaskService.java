/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.service;

import java.util.List;

import org.zengsource.portal.module.user.model.UserInfo;

import com.lobosi.module.task.model.Task;

/**
 * @author zsn
 * @since 6.0
 */
public interface TaskService {

	public void save(Task task);

	public Task findById(int id);

	public int searchCount(String q);

	public List<?> search(String q, Integer start, Integer limit);

	/** 查询某人发起的任务数。*/
	public int searchCount(UserInfo requestor, String q);

	/** 查询某人发起的任务列表。*/
	public List<?> search(UserInfo requestor, String q, Integer start, Integer limit);

	public String prepareUploadFolder(Task task);

}
