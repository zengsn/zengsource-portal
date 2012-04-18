/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-17
 */
package org.zengsource.portal.service;

import java.util.List;

import org.zengsource.portal.model.Module;
import org.zengsource.portal.model.User;

/**
 * @author zsn
 * @since 6.0
 */
public interface ModuleService {
	
	public List<?> findByUser(User user);
	
	public List<Module> load(String rootPath);
	
	public Module loadOne(String modulePath);

	public Module reload(int id, String option);

	public void changeStatus(int id, int status);
	
	public void save(Module module);

	public Module findById(int id);

	public void init();

	public List<Module> findForAdmin();
	
	/** 根据状态查询模块。*/
	public List<?> search(int status, int start, int limit);

	/** 根据关键字查询模块，关键字为空是查询所有模块。*/
	public List<?> search(String q, int start, int limit);

}
