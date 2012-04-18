/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-7
 */
package com.lobosi.module.social.dao;

import java.util.List;

import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.spring.dao.DaoInterface;

import com.lobosi.module.social.model.Friendship;

/**
 * @author zsn
 * @since 6.0
 */
public interface FriendshipDao extends DaoInterface {

	/** 查询好友关系：同时查双方。*/
	public Friendship query(UserInfo partA, UserInfo partB);

	/** 查询好友列表。*/
	public List<?> query(UserInfo user, String q, Integer start, Integer limit);

	/** 查询好友数。*/
	public int queryCount(UserInfo user, String q);

	/** 查询好友请求数。*/
	public int queryCount(UserInfo partB, int status);

	/** 查询好友请求列表。*/
	public List<?> query(UserInfo partB, int status, Integer start, Integer limit);

}
