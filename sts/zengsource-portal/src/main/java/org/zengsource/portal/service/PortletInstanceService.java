/**
 * 
 */
package org.zengsource.portal.service;

import java.util.List;

import org.zengsource.portal.model.PortletInstance;
import org.zengsource.portal.model.Portlet;
import org.zengsource.portal.model.Page;

/**
 * @author zeng.xiaoning
 * 
 */
public interface PortletInstanceService {

	public Integer searchCount(Page page, String q);

	public List<?> search(Portlet portlet);

	public List<?> search(Page page, String q, Integer start, Integer limit);

	public void save(PortletInstance instance);

	public void delete(PortletInstance instance);

	public PortletInstance search(Page page, Portlet defaultPortlet);

	public PortletInstance getById(int id);

	public void reload(Page page);

}
