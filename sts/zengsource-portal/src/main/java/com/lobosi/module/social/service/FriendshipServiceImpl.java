/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-7
 */
package com.lobosi.module.social.service;

import java.util.List;

import org.zengsource.portal.module.user.model.UserInfo;

import com.lobosi.module.social.dao.FriendshipDao;
import com.lobosi.module.social.model.Friendship;

/**
 * @author zsn
 * @since 6.0
 */
public class FriendshipServiceImpl implements FriendshipService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private FriendshipDao friendshipDao;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public void save(Friendship friendship) {
		this.friendshipDao.updateTime(friendship);
		this.friendshipDao.save(friendship);
	};
	
	public Friendship find(UserInfo partA, UserInfo partB) {
		if (partA == null || partB == null) {
			return null;
		}
		return this.friendshipDao.query(partA, partB);
	}
	
	public List<?> search(UserInfo partA, String q, Integer start, Integer limit) {
		if (partA == null) {
			return null;
		}
		return this.friendshipDao.query(partA, q, start, limit);
	}
	
	public int searchCount(UserInfo user, String q) {
		if (user == null) {
			return 0;
		}
		return this.friendshipDao.queryCount(user, q);
	}
	
	public int searchCount(UserInfo partB, int status) {
		if (partB == null) {
			return 0;
		}
		return this.friendshipDao.queryCount(partB, status);
	}
	
	public List<?> search(UserInfo partB, int status, Integer start, Integer limit) {
		if (partB == null) {
			return null;
		}
		return this.friendshipDao.query(partB, status, start, limit);
	}
	
	public Friendship getById(int id) {
		return (Friendship) this.friendshipDao.queryById(id);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public FriendshipDao getFriendshipDao() {
		return friendshipDao;
	}

	public void setFriendshipDao(FriendshipDao friendshipDao) {
		this.friendshipDao = friendshipDao;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
