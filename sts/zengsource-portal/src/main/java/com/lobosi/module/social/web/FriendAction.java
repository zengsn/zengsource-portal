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
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.portal.module.user.service.UserInfoService;
import org.zengsource.portal.web.ThemingAction;

import com.lobosi.module.social.model.Friendship;
import com.lobosi.module.social.model.UserUpdate;
import com.lobosi.module.social.service.FriendshipService;
import com.lobosi.module.social.service.UserUpdateService;

/**
 * @author zsn
 * @since 6.0
 */
public class FriendAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private UserInfoService userInfoService;
	private UserUpdateService userUpdateService;
	private FriendshipService friendshipService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	/** 查询我的好友。 */
	public AbstractView doList() throws MvcException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		UserInfo me = this.userInfoService.getCurrent();
		int totalCount = this.friendshipService.searchCount(me, getQ());
		if (totalCount == 0) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(totalCount + "");
			List<?> friends = this.friendshipService.search(me, getQ(), getStartInt(),
					getLimitInt());
			for (Object obj : friends) {
				Friendship f = (Friendship) obj;
				Element ele = root.addElement("Friend");
				ele.addElement("id").addText(f.getId() + "");
				UserInfo friend = f.getPartA();
				if (friend.getId() == me.getId()) {
					friend = f.getPartB();
				}
				ele.addElement("username").addText(friend.getUsername() + "");
				ele.addElement("nickname").addText(friend.getNickname() + "");
				ele.addElement("avatar").addText(friend.getUser().getAvatar() + "");
				UserUpdate uu = this.userUpdateService.findByUser(friend);
				if (uu != null) {
					ele.addElement("updates").addText(uu.getTextWithPicture());
				} else {
					ele.addElement("updates").addText("最近没啥更新……");
				}
			}
		}
		return new XmlView(doc);
	}

	/** 查询我的好友邀请。 */
	public AbstractView doGetMyInvites() throws MvcException {
		// TODO Auto-generated method stub
		return super.doService();
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

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
