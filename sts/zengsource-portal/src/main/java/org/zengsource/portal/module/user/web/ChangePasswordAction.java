/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-8
 */
package org.zengsource.portal.module.user.web;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.mvc.view.XmlResultView;
import org.zengsource.portal.model.User;
import org.zengsource.portal.service.UserService;
import org.zengsource.portal.web.ThemingAction;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * @since 6.0
 */
public class ChangePasswordAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	private UserService userService;
	
	private String oldPassword;
	private String newPassword;
	private String newPassword2;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public AbstractView doChange() throws MvcException {
		if (StringUtil.isBlank(getOldPassword())) {
			return new XmlErrorView("oldPassword", "原密码不能为空！");
		}
		User user = this.userService.getCurrentUser();
		if (user == null) {
			return new XmlErrorView("oldPassword", "用户不存在！");
		}
		User check = new User();
		check.setUsername(user.getUsername());
		check.setCreatedTime(user.getCreatedTime());
		check.setPassword(getOldPassword());
		this.userService.hashPassword(check); // 生成密码
		if (check.getPassword().equals(user.getPassword())) {
			if (getNewPassword() != null && getNewPassword().equals(getNewPassword2())) {
				user.setPassword(getNewPassword());
				this.userService.hashPassword(user);
				this.userService.save(user);
				return new XmlResultView("msg", "密码修改成功！");
			} else {
				return new XmlErrorView("newPassword", "新密码不匹配！");
			}
		} else {
			return new XmlErrorView("oldPassword", "原密码不匹配！");
		}
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
