/**
 * 
 */
package org.zengsource.portal.dao;

import java.util.List;

import org.zengsource.portal.model.PortletInstance;
import org.zengsource.portal.model.Portlet;
import org.zengsource.portal.model.Page;
import org.zengsource.util.spring.dao.DaoInterface;

/**
 * @author snzeng
 * 
 */
public interface PortletInstanceDao extends DaoInterface{

	public Integer queryCount(Page page, String q);

	public List<?> query(Portlet prototype);

	public List<?> query(Page page, String q, Integer start, Integer limit);

	public PortletInstance query(Page page, Portlet prototype);

	public void delete(PortletInstance instance);

}
