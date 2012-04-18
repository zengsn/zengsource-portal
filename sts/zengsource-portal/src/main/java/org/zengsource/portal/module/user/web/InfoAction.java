/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-6
 */
package org.zengsource.portal.module.user.web;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.portal.module.user.service.UserInfoService;
import org.zengsource.portal.service.UserService;
import org.zengsource.portal.web.ThemingAction;

/**
 * @author zsn
 * @since 6.0
 */
public class InfoAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private UserService userService;
	private UserInfoService userInfoService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public InfoAction() {
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public AbstractView doLoad() throws MvcException {
		UserInfo userInfo = this.userInfoService.getCurrent();
		if (userInfo == null) {
			return new XmlErrorView("username", "用户不存在！");
		}
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("reponse").addAttribute("success", "true");
		Element ele = root.addElement("User");
		ele.addElement("id").addText(userInfo.getId() + "");
		ele.addElement("nickname").addText(userInfo.getNickname() + "");
		ele.addElement("tags").addText(userInfo.getTags() + "");

		ele.addElement("username").addText(userInfo.getUser().getUsername() + "");
		ele.addElement("blog").addText(userInfo.getUser().getBlog() + "");
		ele.addElement("blogShare").addText(userInfo.getUser().getBlogShare() + "");
		ele.addElement("weibo").addText(userInfo.getUser().getWeibo() + "");
		ele.addElement("weiboShare").addText(userInfo.getUser().getWeiboShare() + "");
		ele.addElement("qq").addText(userInfo.getUser().getQq() + "");
		ele.addElement("qqShare").addText(userInfo.getUser().getQqShare() + "");
		ele.addElement("msn").addText(userInfo.getUser().getMsn() + "");
		ele.addElement("msnShare").addText(userInfo.getUser().getMsnShare() + "");
		ele.addElement("mobile").addText(userInfo.getUser().getMobile() + "");
		ele.addElement("mobileShare").addText(userInfo.getUser().getMobileShare() + "");
		ele.addElement("email").addText(userInfo.getUser().getEmail() + "");
		ele.addElement("emailShare").addText(userInfo.getUser().getEmailShare() + "");
		ele.addElement("introduction").addText(userInfo.getUser().getIntroduction() + "");

		ele.addElement("createdTime").addText(userInfo.getCreatedTime() + "");
		ele.addElement("updatedTime").addText(userInfo.getUpdatedTime() + "");

		return new XmlView(doc);
	}

	private String nickname;
	private String introduction;
	private String tags;
	private String blog;
	private String blogShare;
	private String qq;
	private String qqShare;
	private String msn;
	private String msnShare;
	private String email;
	private String emailShare;
	private String mobile;
	private String mobileShare;
	private String weibo;
	private String weiboShare;

	public AbstractView doEdit() throws MvcException {
		UserInfo userInfo = this.userInfoService.getCurrent();
		if (userInfo == null) {
			return new XmlErrorView("username", "用户不存在！");
		}
		userInfo.setNickname(getNickname());
		userInfo.getUser().setNickname(getNickname());
		userInfo.getUser().setIntroduction(getIntroduction());
		userInfo.setTags(getTags());
		userInfo.getUser().setBlog(getBlog());
		userInfo.getUser().setBlogShare(getBlogShare());
		userInfo.getUser().setQq(getQq());
		userInfo.getUser().setQqShare(getQqShare());
		userInfo.getUser().setMsn(getMsn());
		userInfo.getUser().setMsnShare(getMsnShare());
		userInfo.getUser().setEmail(getEmail());
		userInfo.getUser().setEmailShare(getEmailShare());
		userInfo.getUser().setMobile(getMobile());
		userInfo.getUser().setMobileShare(getMobileShare());
		userInfo.getUser().setWeibo(getWeibo());
		this.userService.save(userInfo.getUser());
		this.userInfoService.save(userInfo);
		return XmlView.SUCCESS;
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getWeiboShare() {
		return weiboShare;
	}

	public void setWeiboShare(String weiboShare) {
		this.weiboShare = weiboShare;
	}

	public String getBlogShare() {
		return blogShare;
	}

	public void setBlogShare(String blogShare) {
		this.blogShare = blogShare;
	}

	public String getQqShare() {
		return qqShare;
	}

	public void setQqShare(String qqShare) {
		this.qqShare = qqShare;
	}

	public String getMsnShare() {
		return msnShare;
	}

	public void setMsnShare(String msnShare) {
		this.msnShare = msnShare;
	}

	public String getEmailShare() {
		return emailShare;
	}

	public void setEmailShare(String emailShare) {
		this.emailShare = emailShare;
	}

	public String getMobileShare() {
		return mobileShare;
	}

	public void setMobileShare(String mobileShare) {
		this.mobileShare = mobileShare;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
