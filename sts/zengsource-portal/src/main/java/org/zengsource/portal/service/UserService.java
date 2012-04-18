/*
 * &copy; 2011 ZengSource.com
 * 2011-11-4 下午04:09:43
 */
package org.zengsource.portal.service;

import java.util.List;

import org.zengsource.portal.model.User;


/**
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public interface UserService {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int authenticate(String username, String password, Integer remember);

	public void logout();

	public boolean isAuthenticated();

	public User getCurrentUser();

	public User findByUsername(String username);

	/** 查找用户列表。*/
	public List<?> search(String q, Integer start, Integer limit);

	/** 查找用户数量。*/
	public Long searchCount(String q);

	/** 将用户角色转换为字符串。*/
	public String getRoleNames(User user);

	public User getById(Integer id);

	public int save(User user);
	
	public void hashPassword(User user);

	/** TODO 社交功能：建立邀请者与被邀请者之差的关系。*/
	public void connect(User inviter, User invitee);
	
}
