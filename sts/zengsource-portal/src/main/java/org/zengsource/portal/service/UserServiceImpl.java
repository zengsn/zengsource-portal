/*
 * &copy; 2011 ZengSource.com
 * 2011-11-4 下午04:10:39
 */
package org.zengsource.portal.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.SimpleByteSource;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.dao.UserDao;
import org.zengsource.portal.model.Role;
import org.zengsource.portal.model.User;
import org.zengsource.util.DateUtil;
import org.zengsource.util.StringUtil;

/**
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public class UserServiceImpl implements UserService {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private UserDao userDao;

	private Logger logger = Logger.getLogger(getClass());

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserServiceImpl() {
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	/**
	 * 用户认证（Apache Shiro）。
	 * 
	 * @return 0 - 已登录，1 - 成功。
	 */
	public int authenticate(String username, String password, Integer remember) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			return 0; // 已登录
		}
		if (StringUtil.isBlank(username) || StringUtil.isBlank(password)) {
			return -1; // 用户或密码不能为空
		}
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(remember == 1); // 1 表示记住
		try {
			currentUser.login(token);
			Session session = currentUser.getSession(true);
			session.setAttribute("user", this.getCurrentUser());
		} catch (UnknownAccountException uae) {
			return -2; // 用户不存在
		} catch (IncorrectCredentialsException ice) {
			return -3; // 用户密码错误
		} catch (LockedAccountException lae) {
			return -4; // 用户暂不能登录
		} catch (AuthenticationException ae) {
			return -9; // 帐号异常
		}
		return 1; // 登录成功
	};

	public boolean isAuthenticated() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			return true;
		}
		return false;
	}

	public User getCurrentUser() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			Object username = currentUser.getPrincipal(); // username
			if (username != null) {
				return this.findByUsername(username.toString());
			}
		}
		return null;
	}

	public void logout() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			currentUser.logout();
			Session session = currentUser.getSession(true);
			session.removeAttribute("user");
		}
	}

	public User findByUsername(String username) {
		if (StringUtil.notBlank(username)) {
			return (User) this.userDao.queryUnique("username", username);
		}
		return null;
	}

	public List<?> search(String q, Integer start, Integer limit) {
		if (StringUtil.isBlank(q)) {
			return this.userDao.query(new Criterion[] {}, start, limit);
		} else {
			return this.userDao.query(new Criterion[] { //
					Restrictions.like("username", q, MatchMode.ANYWHERE) }, start, limit);
		}
	}

	public Long searchCount(String q) {
		if (StringUtil.notBlank(q)) {
			return this.userDao.queryCountL(new Criterion[] {});
		} else {
			return this.userDao.queryCountL(new Criterion[] { //
					Restrictions.like("username", q, MatchMode.ANYWHERE) });
		}
	}

	public String getRoleNames(User user) {
		StringBuffer sb = new StringBuffer();
		for (Role role : user.getRoleSet()) {
			sb.append(role.getName()).append(",");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public User getById(Integer id) {
		return (User) this.userDao.queryById(id);
	}

	public int save(User user) {
		try {
			Date now = new Date();
			if (user.getCreatedTime() == null) {
				user.setCreatedTime(now);
				this.hashPassword(user);
			}
			user.setUpdatedTime(now);
			this.userDao.save(user);
			// TODO 清除缓存 Shiro
		} catch (Exception e) {
			return -1;
		}
		return 1;
	}

	public void hashPassword(User user) {
		if (StringUtil.isBlank(user.getSalt())) {
			if (user.getCreatedTime() == null) {
				user.setCreatedTime(new Date());
			}
			String salt = DateUtil.format(user.getCreatedTime(), "yyyyMMdd") //
					+ "_" + user.getUsername();
			user.setSalt(salt);
		}
		Sha256Hash sha256Hash = new Sha256Hash(user.getPassword(),
				(new SimpleByteSource(user.getSalt())).getBytes());
		user.setPassword(sha256Hash.toHex());
	}

	public void connect(User inviter, User invitee) {
		logger.error("To do ... ");
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// + MAIN^TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
