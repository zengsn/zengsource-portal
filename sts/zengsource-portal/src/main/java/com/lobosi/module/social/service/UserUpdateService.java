/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-11
 */
package com.lobosi.module.social.service;

import java.util.List;

import org.zengsource.portal.module.user.model.UserInfo;

import com.lobosi.module.social.model.UserUpdate;

/**
 * @author zsn
 * @since 6.0
 */
public interface UserUpdateService {

	public UserUpdate findById(int id);

	public void save(UserUpdate uu);

	public String prepareUpload(UserInfo userInfo);

	public int searchCount(String q);

	public List<?> search(String q, int start, int limit);

	public int searchCount(UserInfo userInfo);

	public List<?> search(UserInfo userInfo, Integer start, Integer limit);

	/** 查找用户的最新更新。*/
	public UserUpdate findByUser(UserInfo userInfo);

}
