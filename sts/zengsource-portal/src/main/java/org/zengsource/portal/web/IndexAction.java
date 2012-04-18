/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-6
 */
package org.zengsource.portal.web;

import java.io.IOException;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.portal.service.UserService;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * @since 6.0
 */
public class IndexAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private UserService userService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		if (this.userService.isAuthenticated()) {
			try {
				getResponse().sendRedirect(getRequest().getContextPath() + "/home");
			} catch (IOException e) {
				throw new MvcException(e);
			}
			return null;
		}
		// 未登录
		if (StringUtil.isBlank(getForward())) {
			this.setForward("/themes/portal/nologin.jsp");
		}
		return super.doService();
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
