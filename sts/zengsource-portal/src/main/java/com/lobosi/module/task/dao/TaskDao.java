/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.dao;

import java.util.List;

import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.spring.dao.DaoInterface;

/**
 * @author zsn
 * @since 6.0
 */
public interface TaskDao extends DaoInterface {

	public int queryCount(UserInfo requestor, String q);

	public List<?> query(UserInfo requestor, String q, Integer start, Integer limit);

}
