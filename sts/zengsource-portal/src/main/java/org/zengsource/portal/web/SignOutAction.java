/*
 * &copy; 2011 ZengSource.com
 * 2011-11-4 下午05:22:06
 */
package org.zengsource.portal.web;

import java.io.IOException;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipleAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.portal.service.UserService;


/**
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public class SignOutAction extends MultipleAction {

	// + STATIC  +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS   +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	private UserService userService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public SignOutAction() {
		// TODO Auto-generated constructor stub
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	@Override
	protected AbstractView doService() throws MvcException {
		this.userService.logout();
		logger.info("doService()-> " + getRequest().getRemoteHost());
		try {
			getResponse().sendRedirect(getContext().getFullContextPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// + MAIN^TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
