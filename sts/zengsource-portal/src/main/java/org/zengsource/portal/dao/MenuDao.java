/**
 * 
 */
package org.zengsource.portal.dao;

import java.util.List;

import org.zengsource.portal.model.Menu;
import org.zengsource.portal.model.Module;
import org.zengsource.util.spring.dao.DaoInterface;


/**
 * @author snzeng
 *
 */
public interface MenuDao extends DaoInterface{

	public void save(Menu menu);

	public void delete(Menu menu);

	public List<?> queryByModule(Module module, int start, int limit);
	
}
