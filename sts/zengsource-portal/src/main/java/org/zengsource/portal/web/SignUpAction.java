/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-17
 */
package org.zengsource.portal.web;

import java.util.Date;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipleAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.DispatchView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.mvc.view.XmlResultView;
import org.zengsource.portal.model.Invitation;
import org.zengsource.portal.model.Mail;
import org.zengsource.portal.model.Role;
import org.zengsource.portal.model.User;
import org.zengsource.portal.service.InvitationService;
import org.zengsource.portal.service.MailService;
import org.zengsource.portal.service.RoleService;
import org.zengsource.portal.service.UserService;
import org.zengsource.util.DateUtil;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * 
 */
public class SignUpAction extends MultipleAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private String email;
	private String password;
	private String invitation;

	private UserService userService;
	private MailService mailService;
	private RoleService roleService;
	private InvitationService invitationService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		getRequest().setAttribute("portlets", "/system/portlets/portlets.json");
		getRequest().setAttribute("cfgHeight", 500);
		return super.doService();
	}

	public AbstractView doCreate() throws MvcException {
		if (StringUtil.isBlank(getEmail())) {
			return new XmlErrorView("email", "Not allow to blank!");
		}
		if (StringUtil.isBlank(getPassword())) {
			return new XmlErrorView("password", "Not allow to blank!");
		}
		if (StringUtil.isBlank(getInvitation())) {
			return new XmlErrorView("invitation", "Not allow to blank!");
		}
		// 检查用户是否已存在
		User dbUser = this.userService.findByUsername(getEmail());
		if (dbUser != null && dbUser.getStatus() != User.NEW) {
			return new XmlErrorView("email", "此 Email 已经注册！");
		} else if (dbUser != null && dbUser.getStatus() == User.NEW) { // 已注册，但未激活
			return new XmlErrorView("email", "此 Email 已经注册但未激活！<br/>请联系管理员激活帐号！");
		}
		// 检查邀请码
		Invitation dbInvitation = this.invitationService.findByCode(getInvitation());
		if (dbInvitation == null // 邀请码已的失效
				|| dbInvitation.getStatus() != Invitation.ACCEPT) {
			return new XmlErrorView("invitation", "此邀请码已失效！");
		}
		if (dbInvitation.getEmail() != null // 邀请码邮件不匹配
				&& !(dbInvitation.getEmail().equals(getEmail()))) {
			return new XmlErrorView("invitation", "无此邀请码，请先申请邀请码！");
		}
		// 开始创建用户
		User user = new User();
		user.setUsername(getEmail());
		user.setNickname(StringUtil.substring(getEmail(), "@"));
		user.setEmail(getEmail());
		user.setPassword(getPassword());
		user.setIntroduction(dbInvitation.getIntroduction());
		// 默认值
		user.setAvatar("/user/resources/images/o_.png");
		// 设置角色
		Role role = this.roleService.getByName("member");
		if (role == null) {
			throw new MvcException("默认角色 member 未准备好！");
		}
		user.addRole(role); // 设置为会员角色 
		if (this.userService.save(user) > 0) {
			// 修改邀请码 ...
			dbInvitation.setInvitee(user);
			dbInvitation.setStatus(Invitation.USED);
			this.invitationService.save(dbInvitation);
			// 建立邀请者关系 ...
			this.userService.connect(dbInvitation.getInviter(), user); // TODO
			// 发送确认邮件 ...
			user.setEmailConfirm(StringUtil.random(16));
			String url = "" //
					+ getContext().getFullContextPath() + "/activate.jxp" //
					// + "http://www.lobosi.com/activate.jxp" //
					+ "?email=" + user.getEmail() //
					+ "&code=" + user.getEmailConfirm();
			Mail mail = new Mail();
			mail.setSubject("Lobosi.com 帐号激活邮件");
			mail.setFromAddress("admin@lobosi.com");
			mail.setToAddress(user.getEmail());
			mail.setBody("" //
					+ "<p>您好：</p><br/>" //
					+ "<p>您的邮箱正在注册 Lobosi.com 新帐号，要确认注册，请点击：</p>" //
					+ "<p><a href=\"" + url + "\" target=\"_blank\">激活帐号</a></p>" //
					+ "<p>或者复制下面的链接，粘贴到浏览器地址栏直接访问：</p>" //
					+ "<p>" + url + "</p><br/>" //
					+ "<p>（如果并非您本人注册，请忽略此邮件，打扰之处，忘请见谅！）</p><br/>" //
					+ "<p><a href=\"http://www.lobosi.com/\">Lobosi.com</a></p>" //
					+ "<p>" + DateUtil.format(new Date(), "yyyy-MM-dd") + "</p>");
			this.mailService.send(mail, false); // 发送邮件
			logger.info("发送激活码：" + url);
			this.userService.save(user); // 保存激活码
			return new XmlResultView("email", user.getEmail());
		} else {
			return new XmlErrorView("email", "Error!");
		}
	}

	public AbstractView doSuccess() throws MvcException {
		getRequest().setAttribute("portlets", "/system/portlets/signup-success.json");
		getRequest().setAttribute("cfgHeight", 500);
		return new DispatchView(getForward());
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	public RoleService getRoleService() {
		return roleService;
	}
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	public InvitationService getInvitationService() {
		return invitationService;
	}

	public void setInvitationService(InvitationService invitationService) {
		this.invitationService = invitationService;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInvitation() {
		return invitation;
	}

	public void setInvitation(String invitation) {
		this.invitation = invitation;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
