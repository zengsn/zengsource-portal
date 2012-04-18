/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.service;

import com.lobosi.module.task.model.FileTask;

/**
 * @author zsn
 * @since 6.0
 */
public interface FileTaskService {

	public String prepareUploadFolder(FileTask fileTask);

	public void save(FileTask fileTask);

}
