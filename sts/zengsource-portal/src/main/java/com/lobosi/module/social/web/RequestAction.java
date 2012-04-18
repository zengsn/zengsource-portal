/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-6
 */
package com.lobosi.module.social.web;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.JsonResultView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.portal.module.user.service.UserInfoService;
import org.zengsource.portal.web.ThemingAction;
import org.zengsource.util.NumberUtil;

import com.lobosi.module.social.model.Friendship;
import com.lobosi.module.social.model.UserUpdate;
import com.lobosi.module.social.service.FriendshipService;
import com.lobosi.module.social.service.UserUpdateService;

/**
 * @author zsn
 * @since 6.0
 */
public class RequestAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private UserInfoService userInfoService;
	private UserUpdateService userUpdateService;
	private FriendshipService friendshipService;

	private String friend;
	private String message;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	/** 好友请求列表。 */
	public AbstractView doList() throws MvcException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		UserInfo partB = this.userInfoService.getCurrent();
		int totalCount = this.friendshipService.searchCount(partB, Friendship.NEW);
		if (totalCount == 0) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(totalCount + "");
			List<?> requests = this.friendshipService.search(partB, Friendship.NEW, getStartInt(),
					getLimitInt());
			for (Object obj : requests) {
				Friendship f = (Friendship) obj;
				Element ele = root.addElement("Request");
				ele.addElement("id").addText(f.getId() + "");
				ele.addElement("messageA").addText(f.getMessageA() + "");
				ele.addElement("username").addText(f.getPartA().getUsername() + "");
				ele.addElement("nickname").addText(f.getPartA().getNickname() + "");
				ele.addElement("introduction").addText(f.getPartA().getUser().getIntroduction() + "");
				ele.addElement("avatar").addText(f.getPartA().getUser().getMediumAvatar() + "");
				UserUpdate uu = this.userUpdateService.findByUser(f.getPartA());
				if (uu != null) {
					ele.addElement("updates").addText(uu.getTextWithPicture());
				} else {
					ele.addElement("updates").addText("最近没啥更新……");
				}
			}
		}
		return new XmlView(doc);
	}

	/** 同意好友请求。 */
	public AbstractView doAccept() throws MvcException {
		return this.handleRequest(Friendship.ACCEPTED);
	}

	/** 忽略好友请求。 */
	public AbstractView doIgnore() throws MvcException {
		return this.handleRequest(Friendship.IGNORED);
	}

	/** 拒绝好友请求。 */
	public AbstractView doReject() throws MvcException {
		return this.handleRequest(Friendship.REJECTED);
	}

	public AbstractView handleRequest(int status) throws MvcException {
		Friendship friendship = this.friendshipService.getById(NumberUtil.str2Int(getFriend(), 0));
		if (friendship == null) {
			return new JsonResultView("msg", "<font color=\"red\">请求不存在！</font>");
		}
		friendship.setMessageB(getMessage());
		friendship.setStatus(status);
		this.friendshipService.save(friendship);
		if (Friendship.ACCEPTED == status) {
			return new JsonResultView("msg", "<font color=\"green\">添加好友成功！</font>");
		} else if (Friendship.IGNORED == status) {
			return new JsonResultView("msg", "<font color=\"green\">忽略好友成功！</font>");
		} else if (Friendship.REJECTED == status) {
			return new JsonResultView("msg", "<font color=\"green\">拒绝好友成功！</font>");
		} else {
			return new JsonResultView("msg", "<font color=\"red\">未知操作！</font>");
		}
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}
	
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public FriendshipService getFriendshipService() {
		return friendshipService;
	}

	public void setFriendshipService(FriendshipService friendshipService) {
		this.friendshipService = friendshipService;
	}
	
	public UserUpdateService getUserUpdateService() {
		return userUpdateService;
	}
	
	public void setUserUpdateService(UserUpdateService userUpdateService) {
		this.userUpdateService = userUpdateService;
	}

	public String getFriend() {
		return friend;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
