/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-8
 */
package org.zengsource.portal.module.user.service;

import java.util.List;

import org.zengsource.portal.model.User;
import org.zengsource.portal.module.user.model.UserInfo;

/**
 * @author zsn
 * @since 6.0
 */
public interface UserInfoService {

	public UserInfo findByUser(User user);

	public UserInfo create(User user);

	public UserInfo getCurrent();

	public void save(UserInfo userInfo);

	public UserInfo findById(int uid);

	/** 查找兴趣相投的用户数。*/
	public int searchCountInterested(UserInfo user);

	/** 查找兴趣相投的用户列表。*/
	public List<?> searchInterested(UserInfo user, int start, int limit);

	/** 准备上传文件夹。*/
	public String prepareUploadFolder();

}
