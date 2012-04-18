/**
 * 
 */
package org.zengsource.portal.dao;

import org.zengsource.portal.model.Config;
import org.zengsource.util.spring.dao.DaoInterface;

/**
 * @author snzeng
 *
 */
public interface ConfigDao extends DaoInterface{

	public Config queryByKey(String key);

	
}
