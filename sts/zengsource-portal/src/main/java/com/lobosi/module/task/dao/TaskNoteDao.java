/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-10
 */
package com.lobosi.module.task.dao;

import java.util.List;

import org.zengsource.util.spring.dao.DaoInterface;

import com.lobosi.module.task.model.TaskExecution;

/**
 * @author zsn
 * @since 6.0
 */
public interface TaskNoteDao extends DaoInterface {

	public int queryCount(TaskExecution execution, String q);

	public List<?> query(TaskExecution execution, String q, Integer start, Integer limit);

}
