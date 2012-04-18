/**
 * 
 */
package org.zengsource.portal.service;

import java.util.List;

import org.zengsource.portal.model.Portlet;
import org.zengsource.portal.model.Module;

/**
 * @author snzeng
 *
 */
public interface PortletService {

	public void save(Portlet prototype);

	public Integer searchCount(String q);

	public Integer searchCount(Module module);

	public List<?> search(String q, Integer start, Integer limit);

	public List<?> search(Module module, Integer start, Integer limit);

	public Portlet getById(int id);

	public Portlet getByPageUrl(String pageUrl);

	/** 重新加载模块中的区块配置。*/
	public void reload(Module module);

	public Portlet findByJsClass(String jsClass);


}
