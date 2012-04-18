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
import org.zengsource.portal.model.User;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.portal.module.user.service.UserInfoService;
import org.zengsource.portal.web.ThemingAction;
import org.zengsource.util.NumberUtil;

import com.lobosi.module.social.model.Friendship;
import com.lobosi.module.social.model.UserUpdate;
import com.lobosi.module.social.service.FriendshipService;
import com.lobosi.module.social.service.SocialService;
import com.lobosi.module.social.service.UserUpdateService;

/**
 * 查找好友
 * 
 * @author zsn
 * @since 6.0
 */
public class FindAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private UserInfoService userInfoService;
	private SocialService socialService;
	private UserUpdateService userUpdateService;
	private FriendshipService friendshipService;

	private String friend;
	private String message;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	/** 查询推荐好友。 */
	public AbstractView doGetFeatures() throws MvcException {
		int totalCount = this.socialService.searchCountFeatured();
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		if (totalCount == 0) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(totalCount + "");
			List<?> users = this.socialService.searchFeatured(getStartInt(), getLimitInt());
			for (Object obj : users) {
				Element ele = root.addElement("User");
				// UserInfo
				UserInfo userInfo = (UserInfo) obj;
				ele.addElement("nickname").addText(userInfo.getNickname() + "");
				ele.addElement("tags").addText(userInfo.getTags() + "");
				// User
				User user = userInfo.getUser();
				ele.addElement("uid").addText(user.getId() + "");
				ele.addElement("username").addText(user.getUsername() + "");
				ele.addElement("email").addText(user.getEmail() + "");
				ele.addElement("emailShare").addText(user.getEmailShare() + "");
				ele.addElement("roleNames").addText(user.getRoleSet() + "");
				ele.addElement("nickname").addText(user.getNickname() + "");
				ele.addElement("realname").addText(user.getRealname() + "");
				ele.addElement("realnameShare").addText(user.getRealnameShare() + "");
				ele.addElement("location").addText(user.getLocation() + "");
				ele.addElement("locationShare").addText(user.getLocationShare() + "");
				ele.addElement("sex").addText(user.getSex() + "");
				ele.addElement("sexShare").addText(user.getSexShare() + "");
				ele.addElement("birthday").addText(user.getBirthday() + "");
				ele.addElement("birthdayShare").addText(user.getBirthdayShare() + "");
				ele.addElement("blog").addText(user.getBlog() + "");
				ele.addElement("blogShare").addText(user.getBlogShare() + "");
				ele.addElement("qq").addText(user.getQq() + "");
				ele.addElement("qqShare").addText(user.getQqShare() + "");
				ele.addElement("weibo").addText(user.getWeibo() + "");
				ele.addElement("weiboShare").addText(user.getWeiboShare() + "");
				ele.addElement("msn").addText(user.getMsn() + "");
				ele.addElement("msnShare").addText(user.getMsnShare() + "");
				ele.addElement("mobile").addText(user.getMobile() + "");
				ele.addElement("mobileShare").addText(user.getMobileShare() + "");
				ele.addElement("avatar").addText(user.getAvatar() + "");
				ele.addElement("introduction").addText(user.getIntroduction() + "");
				ele.addElement("updatedTime").addText(user.getUpdatedTime() + "");
				ele.addElement("createdTime").addText(user.getCreatedTime() + "");
				// 最新动态和更新
				UserUpdate uu = this.userUpdateService.findByUser(userInfo);
				if (uu != null) {
					ele.addElement("updates").addText(uu.getTextWithPicture());
				} else {
					ele.addElement("updates").addText("最近没啥更新……");
				}
			}
		}
		return new XmlView(doc);
	}

	/** 邀请加为好友。 */
	public AbstractView doInvite() throws MvcException {
		UserInfo me = this.userInfoService.getCurrent();
		int friendId = NumberUtil.str2Int(getFriend(), 0);
		UserInfo friend = this.userInfoService.findById(friendId);
		if (friend == null) {
			return new JsonResultView("msg", "好友不存在！");
		}
		Friendship friendship = this.friendshipService.find(me, friend);
		if (friendship == null) {
			friendship = new Friendship();
			friendship.setPartA(me);
			friendship.setPartB(friend);
			friendship.setMessageA(getMessage());
			this.friendshipService.save(friendship);
			return new JsonResultView("msg", "好友邀请已发送！");
		} else if (friendship.getStatus() == Friendship.ACCEPTED) {
			return new JsonResultView("msg", "已经是好友！");
		} else if (friendship.getStatus() == Friendship.NEW) {
			if (friendship.getPartA().getId() == me.getId()) {
				return new JsonResultView("msg", "邀请已发送，不需要重要发送！");
			} else { // 对方已邀请
				return new JsonResultView("msg", "对方已邀请您，请查看自己的好友请求！");
			}
		} else {
			return new JsonResultView("msg", "无法建立好友关系 ！" + friendship.getStatus());
		}
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}
	
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public SocialService getSocialService() {
		return socialService;
	}

	public void setSocialService(SocialService socialService) {
		this.socialService = socialService;
	}
	
	public UserUpdateService getUserUpdateService() {
		return userUpdateService;
	}
	
	public void setUserUpdateService(UserUpdateService userUpdateService) {
		this.userUpdateService = userUpdateService;
	}

	public FriendshipService getFriendshipService() {
		return friendshipService;
	}

	public void setFriendshipService(FriendshipService friendshipService) {
		this.friendshipService = friendshipService;
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
