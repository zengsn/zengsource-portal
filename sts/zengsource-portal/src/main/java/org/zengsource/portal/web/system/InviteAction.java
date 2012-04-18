/*
 * &copy; 2011 ZengSource.com
 * 2011-11-6 下午04:15:47
 */
package org.zengsource.portal.web.system;

import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipleAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.JsonResultView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.model.Invitation;
import org.zengsource.portal.model.Mail;
import org.zengsource.portal.model.User;
import org.zengsource.portal.service.InvitationService;
import org.zengsource.portal.service.MailService;
import org.zengsource.portal.service.UserService;
import org.zengsource.util.DateUtil;
import org.zengsource.util.NumberUtil;
import org.zengsource.util.StringUtil;

/**
 * 邀请码。
 * 
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public class InviteAction extends MultipleAction {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private InvitationService invitationService;
	private UserService userService;
	private MailService mailService;

	private String email;
	private String partnerEmail;
	private String instroduction;
	private String socialType;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public InviteAction() {
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		// TODO Auto-generated method stub
		return super.doService();
	}

	public AbstractView doSave() throws MvcException {

		// if (this.askService.save(ask)) {
		// return XmlView.SUCCESS;
		// } else {
		// return new XmlErrorView("email", "保存失败！");
		// }
		return null;
	}

	public AbstractView doList() throws MvcException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		List<?> list = this.invitationService.search(getQ(), getStartInt(), getLimitInt());
		if (list == null || list.size() == 0) {
			root.addElement("totalCount").addText(0 + "");
		} else {
			long totalCount = this.invitationService.searchCount(getQ());
			root.addElement("totalCount").addText(totalCount + "");
			for (Object obj : list) {
				Invitation in = (Invitation) obj;
				Element ele = root.addElement("Invitation");
				ele.addElement("id").addText(in.getId() + "");
				ele.addElement("email").addText(in.getEmail() + "");
				ele.addElement("code").addText(in.getCode() + "");
				ele.addElement("status").addText(in.getStatus() + "");
				ele.addElement("inviter").addText(in.getInviter() + "");
				ele.addElement("invitee").addText(in.getInvitee() + "");
				ele.addElement("introduction").addText(in.getIntroduction() + "");
				ele.addElement("updatedTime").addText(in.getUpdatedTime() + "");
				ele.addElement("createdTime").addText(in.getCreatedTime() + "");
			}
		}
		return new XmlView(doc);
	}

	/** 发送邀请码。 */
	public AbstractView doAccept() throws MvcException {
		if (StringUtil.notBlank(getId())) {
			String[] idArr = getId().split(",");
			for (String id : idArr) {
				Invitation in = this.invitationService.findById(NumberUtil.string2Integer(id, 0));
				if (in != null && in.getStatus() == Invitation.VALID) {
					User me = this.userService.getCurrentUser();
					in.setInviter(me); // 设置发放人
					in.setStatus(Invitation.ACCEPT); // 修改状态
					in.setCode(StringUtil.random(16)); // 生成邀请码
					this.invitationService.save(in); // 保存
					logger.info("保存邀请码：" + in.toString());
					this.sendInvitation(in); // 发送邀请码
				}
			}
		}
		return new JsonResultView("msg", "邀请码已发送！");
	}

	public AbstractView doResend() throws MvcException {
		if (StringUtil.notBlank(getId())) {
			String[] idArr = getId().split(",");
			for (String id : idArr) {
				Invitation in = this.invitationService.findById(NumberUtil.string2Integer(id, 0));
				if (in != null && in.getStatus() == Invitation.ACCEPT) {
					this.sendInvitation(in); // 发送邀请码
				}
			}			
		}
		return new JsonResultView("msg", "邀请码已发送！");
	}

	private void sendInvitation(Invitation in) {
		String url = "" //
				+ getContext().getFullContextPath() + "/signup.jxp" //
				+ "?email=" + in.getEmail() //
				+ "&code=" + in.getCode();
		Mail mail = new Mail();
		mail.setSubject("Lobosi.com 邀请码发送");
		mail.setFromAddress("admin@lobosi.com");
		mail.setToAddress(in.getEmail());
		mail.setBody("" //
				+ "<p>您好：</p><br/>" //
				+ "<p>您获得的邀请码是：<b>" + in.getCode() + "</b>&nbsp;，请使用这个邀请码注册新帐号。</p>" //
				+ "<p>或者：直接点击带邀请码的链接开始注册帐号：</p>" //
				+ "<p><a href=\"" + url + "\" target=\"_blank\">注册帐号</a></p>" //
				+ "<p>或者：复制下面的链接，粘贴到浏览器地址栏直接访问：</p>" //
				+ "<p>" + url + "</p><br/>" //
				+ "<p>（如果并非您本人注册，请忽略此邮件，打扰之处，忘请见谅！）</p><br/>" //
				+ "<p><a href=\"http://www.lobosi.com/\">Lobosi.com</a></p>" //
				+ "<p>" + DateUtil.format(new Date(), "yyyy-MM-dd") + "</p>");
		this.mailService.send(mail, false); // 发送邮件
		logger.info("发送邀请码：" + url);
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public String getEmail() {
		return email;
	}

	public InvitationService getInvitationService() {
		return invitationService;
	}

	public void setInvitationService(InvitationService invitationService) {
		this.invitationService = invitationService;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPartnerEmail() {
		return partnerEmail;
	}

	public void setPartnerEmail(String partnerEmail) {
		this.partnerEmail = partnerEmail;
	}

	public String getInstroduction() {
		return instroduction;
	}

	public void setInstroduction(String instroduction) {
		this.instroduction = instroduction;
	}

	public String getSocialType() {
		return socialType;
	}

	public void setSocialType(String socialType) {
		this.socialType = socialType;
	}

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

	// + MAIN^TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
