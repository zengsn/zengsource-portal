/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-23
 */
package org.zengsource.portal.dao;

import java.util.List;

import org.zengsource.util.spring.dao.DaoInterface;

/**
 * @author zsn
 * @since 6.0
 */
public interface ModuleDao extends DaoInterface {

	public List<?> queryByName(String name);

	public List<?> queryByStatus(int status, int start, int limit);

}
