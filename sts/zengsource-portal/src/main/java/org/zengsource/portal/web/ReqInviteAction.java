/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-19
 */
package org.zengsource.portal.web;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipleAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.model.Invitation;
import org.zengsource.portal.service.InvitationService;
import org.zengsource.util.StringUtil;

/**
 * 申请邀请码。
 * 
 * @author zsn
 * @since 6.0
 */
public class ReqInviteAction extends MultipleAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private String email;
	private String introduction;

	private InvitationService invitationService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		getRequest().setAttribute("portlets", "/system/portlets/reqinvite.json");
		return super.doService();
	}

	/** 申请邀请码。 */
	public AbstractView doApply() throws MvcException {
		if (StringUtil.isEmail(getEmail())) {
			Invitation dbInvitation = this.invitationService.findByEmail(getEmail());
			if (dbInvitation != null) {
				return new XmlErrorView("email", "不需要重复注册！");
			}
			Invitation invitation = new Invitation();
			invitation.setEmail(getEmail());
			invitation.setIntroduction(//
					StringUtil.isBlank(getIntroduction()) ? "无。" : getIntroduction());
			StringBuilder userTools = new StringBuilder(" 喜欢使用：");
			if ("on".equals(getRequest().getParameter("qq"))) {
				userTools.append("QQ，");
			}
			if ("on".equals(getRequest().getParameter("weibo"))) {
				userTools.append("微博，");
			}
			if ("on".equals(getRequest().getParameter("alipay"))) {
				userTools.append("支付宝，");
			}
			if ("on".equals(getRequest().getParameter("renren"))) {
				userTools.append("人人网，");
			}
			if ("on".equals(getRequest().getParameter("baidu"))) {
				userTools.append("百度，");
			}
			if ("on".equals(getRequest().getParameter("kaixin001"))) {
				userTools.append("开心网，");
			}
			invitation.setIntroduction(getIntroduction() + userTools);
			this.invitationService.save(invitation);
			return XmlView.SUCCESS;
		}
		return new XmlErrorView("email", "Error!");
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public InvitationService getInvitationService() {
		return invitationService;
	}

	public void setInvitationService(InvitationService invitationService) {
		this.invitationService = invitationService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
