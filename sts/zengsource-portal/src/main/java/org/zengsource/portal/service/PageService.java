/**
 * 
 */
package org.zengsource.portal.service;

import java.util.List;

import org.zengsource.portal.model.Module;
import org.zengsource.portal.model.Page;

/**
 * @author snzeng
 *
 */
public interface PageService {
	
	public List<?> getAll();

	public List<?> search(String q, Integer start, Integer limit) ;
	
	public List<?> search(Module module, Integer start, Integer limit) ;

	public Integer searchCount(String q) ;

	public void save(Page page) ;

	public Page getById(int pageId) ;

	public Page getByUrl(String url) ;

	public void reload(Module module);

	public void deletePortlets(Page page);

	public String revert(String pageUrl);

}
