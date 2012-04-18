/*
 * &copy; 2011 ZengSource.com
 * 2011-11-4 下午04:15:05
 */
package org.zengsource.portal.web;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipleAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.service.UserService;
import org.zengsource.util.NumberUtil;

/**
 * 登录。
 * 
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public class SignInAction extends MultipleAction {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private String username;
	private String password;
	private String remember;

	private UserService userService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		getRequest().setAttribute("portlets", "/system/portlets/signin.json");
		getRequest().setAttribute("cfgHeight", 500);
		logger.info("doService() -> " + getRequest().getRemoteHost());
		if (this.userService.isAuthenticated()) { // 已登录
			this.redirect(getRequest().getContextPath());
			return null;
		}
		return super.doService();
	}

	public AbstractView doAuthentication() throws MvcException {
		if (this.userService.isAuthenticated()) { // 已登录
			return XmlView.SUCCESS;
		}
		int result = this.userService.authenticate(//
				getUsername(), getPassword(), NumberUtil.string2Integer(getRemember(), 0));
		if (result == 1) {
			logger.info("doService()-> " + getUsername() + " 登录成功！");
			return XmlView.SUCCESS;
		} else {
			logger.info("doService()-> " + getUsername() + " 登录失败 [" + result + "]");
			// TODO: 登录失败原因
			return new XmlErrorView("username", "登录失败！");
		}
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}

	// + MAIN^TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
