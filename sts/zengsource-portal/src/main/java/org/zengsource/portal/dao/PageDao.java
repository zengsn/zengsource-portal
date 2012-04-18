/**
 * 
 */
package org.zengsource.portal.dao;

import java.util.List;

import org.zengsource.portal.model.Module;
import org.zengsource.util.spring.dao.DaoInterface;

/**
 * @author snzeng
 * 
 */
public interface PageDao extends DaoInterface{

	public List<?> queryByModule(Module module, Integer start, Integer limit);

}
