/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-4
 */
package org.zengsource.portal.service;

import java.util.List;

import org.zengsource.portal.model.Menu;
import org.zengsource.portal.model.Module;

/**
 * @author zsn
 * @since 6.0
 */
public interface MenuService {
	
	public void save(Menu menu);
	
	public List<?> search(Module module, int start, int limit);

	/** 重新加载模块中的菜单配置。*/
	public void reload(Module module);

}
