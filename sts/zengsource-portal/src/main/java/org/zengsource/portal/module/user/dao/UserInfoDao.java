/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-8
 */
package org.zengsource.portal.module.user.dao;

import java.util.List;

import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.spring.dao.DaoInterface;

/**
 * @author zsn
 * @since 6.0
 */
public interface UserInfoDao extends DaoInterface {

	public int queryCountInterested(UserInfo user);

	public List<?> queryInterested(UserInfo user, int start, int limit);

}
