/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-14
 */
package org.zengsource.portal.web;

import java.io.IOException;
import java.util.Date;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.SuperAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.portal.model.Mail;
import org.zengsource.portal.model.User;
import org.zengsource.portal.service.ConfigService;
import org.zengsource.portal.service.MailService;
import org.zengsource.portal.service.UserService;
import org.zengsource.util.DateUtil;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * @since 6.0
 */
public class ActivateAction extends SuperAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private UserService userService;
	private MailService mailService;
	private ConfigService configService;

	private String email;
	private String code;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		User user = this.userService.findByUsername(getEmail());
		if (user == null || // 用户不存在
				user.getStatus() != User.NEW || // 用户状态不对
				user.getEmailConfirm() == null || // 电子邮件确认码不存在
				!(user.getEmailConfirm().equals(getCode()))) { // 确认码不匹配
			try { // 不处理，直接返回首页
				getResponse().sendRedirect(getContext().getFullContextPath());
			} catch (IOException e) {
				throw new MvcException(e);
			}
		}
		user.setStatus(User.OPEN);
		this.userService.save(user);
		// 发送欢迎邮件
		Mail mail = new Mail();
		mail.setSubject("欢迎加入 Lobosi.com");
		mail.setFromAddress("admin@lobosi.com");
		mail.setToAddress(user.getEmail());
		// 取配置的欢迎邮件内容
		String welcome = this.configService.getString("mail.welcome");
		if (StringUtil.isBlank(welcome)) {
			mail.setBody("" //
					+ "<p>您好：</p><br/>" //
					+ "<p>您已经正式注册成为 Lobosi.com 会员，登录名为您的邮箱地址。</p>" //
					+ "<p>Lobosi.com 是一个专门为个人用户提供信息服务的社区。</p>" //
					+ "<p>目前，我们仍然还很渺小，但这正是我们追求进步的动力，希望您能见证我们的成长！</p>" //
					+ "<p>感谢！</p><br />" //
					+ "<p><a href=\"http://www.lobosi.com/\">Lobosi.com</a></p>" //
					+ "<p>" + DateUtil.format(new Date(), "yyyy-MM-dd") + "</p>");
		} else {
			mail.setBody(welcome);
		}
		this.mailService.send(mail, false); // 发送邮件
		try {
			getResponse().sendRedirect(
					getContext().getFullContextPath() + "/signin.jxp?confirm&email="
							+ user.getUsername());
		} catch (IOException e) {
			throw new MvcException(e);
		}
		return null;
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
