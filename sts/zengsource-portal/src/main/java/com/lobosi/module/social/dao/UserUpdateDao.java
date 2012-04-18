/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-11
 */
package com.lobosi.module.social.dao;

import java.util.List;

import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.spring.dao.DaoInterface;

import com.lobosi.module.social.model.UserUpdate;

/**
 * @author zsn
 * @since 6.0
 */
public interface UserUpdateDao extends DaoInterface {

	public int queryCount(UserInfo userInfo);

	public List<?> query(UserInfo userInfo, Integer start, Integer limit);

	public UserUpdate queryLastUpdate(UserInfo userInfo);

}
