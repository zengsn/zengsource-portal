/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-10
 */
package com.lobosi.module.task.service;

import java.util.List;

import com.lobosi.module.task.model.TaskExecution;
import com.lobosi.module.task.model.TaskNote;

/**
 * @author zsn
 * @since 6.0
 */
public interface TaskNoteService {

	public int searchCount(TaskExecution execution, String q);

	public List<?> search(TaskExecution execution, String q, Integer start, Integer limit);

	public void save(TaskNote note);

}
