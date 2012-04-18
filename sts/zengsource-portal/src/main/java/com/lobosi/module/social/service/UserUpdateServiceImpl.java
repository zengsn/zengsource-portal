/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-11
 */
package com.lobosi.module.social.service;

import java.io.File;
import java.util.List;

import org.zengsource.mvc.MvcContext;
import org.zengsource.portal.module.user.model.UserInfo;

import com.lobosi.module.social.dao.UserUpdateDao;
import com.lobosi.module.social.model.UserUpdate;

/**
 * @author zsn
 * @since 6.0
 */
public class UserUpdateServiceImpl implements UserUpdateService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	private UserUpdateDao userUpdateDao;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public UserUpdate findById(int id) {
		if (id == 0) {
			return null;
		}
		return (UserUpdate) this.userUpdateDao.queryById(id);
	}
	
	public UserUpdate findByUser(UserInfo userInfo) {
		if (userInfo == null) {
			return null;
		}
		return this.userUpdateDao.queryLastUpdate(userInfo);
	}
	
	public void save(UserUpdate uu) {
		if (uu != null) {
			this.userUpdateDao.updateTime(uu);
			this.userUpdateDao.save(uu);
		}
	}
	
	public String prepareUpload(UserInfo userInfo) {
		String rootPath = MvcContext.getInstance().getRootPath();
		String uploadPath = (rootPath + "upload/social");
		if (userInfo != null) {
			uploadPath += "/" + userInfo.getId();
		}
		File uploadFolder = new File(uploadPath);
		if (!(uploadFolder.exists())) {
			uploadFolder.mkdirs();
		}
		return uploadPath;
	}
	
	public int searchCount(String q) {
		return this.userUpdateDao.queryCount(q);
	}
	
	public List<?> search(String q, int start, int limit) {
		return this.userUpdateDao.query(q, start, limit);
	}
	
	public int searchCount(UserInfo userInfo) {
		if (userInfo == null) {
			return 0;
		}
		return this.userUpdateDao.queryCount(userInfo);
	}
	
	public List<?> search(UserInfo userInfo, Integer start, Integer limit) {
		if (userInfo == null) {
			return null;
		}
		return this.userUpdateDao.query(userInfo, start, limit);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public UserUpdateDao getUserUpdateDao() {
		return userUpdateDao;
	}
	
	public void setUserUpdateDao(UserUpdateDao userUpdateDao) {
		this.userUpdateDao = userUpdateDao;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
