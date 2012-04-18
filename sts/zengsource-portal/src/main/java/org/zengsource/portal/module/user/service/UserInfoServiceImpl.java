/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-8
 */
package org.zengsource.portal.module.user.service;

import java.io.File;
import java.util.List;

import org.zengsource.mvc.MvcContext;
import org.zengsource.portal.model.User;
import org.zengsource.portal.module.user.dao.UserInfoDao;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.portal.service.UserService;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * @since 6.0
 */
public class UserInfoServiceImpl implements UserInfoService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private UserInfoDao userInfoDao;
	private UserService userService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public UserInfo getCurrent() {
		User user = this.userService.getCurrentUser();
		if (user == null) {
			return null;
		}
		UserInfo userInfo = this.findByUser(user);
		if (userInfo == null) {
			userInfo = this.create(user);
		}
		return userInfo;
	};
	
	/** 前面带 /。*/
	public String prepareUploadFolder() {
		if (this.getCurrent() == null) {
			throw new RuntimeException("未登录用户不允许创建目录！");
		}
		String rootPath = MvcContext.getInstance().getRootPath();
		String userUploadPath = "upload/user/" + this.getCurrent().getId(); 
		File userFolder = new File(rootPath + userUploadPath);
		if (!(userFolder.exists())) {
			userFolder.mkdirs();
		}
		return userUploadPath;
	}

	public UserInfo findByUser(User user) {
		if (user == null) {
			return null;
		}
		return (UserInfo) this.userInfoDao.queryUnique("user", user);
	}

	public UserInfo create(User user) {
		if (user == null) {
			return null;
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setUser(user);
		userInfo.setUsername(user.getUsername());
		userInfo.setNickname(user.getNickname() == null ? StringUtil.substring(user.getUsername(),
				"@") : user.getNickname());
		userInfo.setShared(true);
		this.save(userInfo);
		return userInfo;
	}

	public void save(UserInfo userInfo) {
		this.userInfoDao.updateTime(userInfo);
		this.userInfoDao.save(userInfo);
	}
	
	public UserInfo findById(int uid) {
		if (uid == 0) {
			return null;
		}
		return (UserInfo) this.userInfoDao.queryById(uid);
	}
	
	public int searchCountInterested(UserInfo user) {
		if (user == null) {
			return 0;
		}
		return this.userInfoDao.queryCountInterested(user);
	}
	
	public List<?> searchInterested(UserInfo user, int start, int limit) {
		if (user == null) {
			return null;
		}
		return this.userInfoDao.queryInterested(user, start, limit);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

}
