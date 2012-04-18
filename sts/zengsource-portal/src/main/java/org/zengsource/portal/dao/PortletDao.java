/**
 * 
 */
package org.zengsource.portal.dao;


import org.zengsource.portal.model.Portlet;
import org.zengsource.util.spring.dao.DaoInterface;

/**
 * @author snzeng
 * 
 */
public interface PortletDao extends DaoInterface{

	public Portlet queryByPageUrl(String pageUrl);

}
