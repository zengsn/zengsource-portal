/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-7
 */
package com.lobosi.module.social.service;

import java.util.List;

import org.zengsource.portal.module.user.model.UserInfo;

import com.lobosi.module.social.model.Friendship;

/**
 * @author zsn
 * @since 6.0
 */
public interface FriendshipService {

	public void save(Friendship friendship);

	public Friendship find(UserInfo partA, UserInfo partB);

	/** 查询好友列表：可以是partA，也可以是partB。*/
	public List<?> search(UserInfo user, String q, Integer start, Integer limit);

	/** 查询好友数：可以是partA，也可以是partB。*/
	public int searchCount(UserInfo user, String q);

	/** 查询好友请求数。*/
	public int searchCount(UserInfo partB, int status);

	/** 查询好友请求列表。*/
	public List<?> search(UserInfo partB, int status, Integer start, Integer limit);

	public Friendship getById(int id);

}
