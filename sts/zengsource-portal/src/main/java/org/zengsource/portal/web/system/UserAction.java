/*
 * &copy; 2011 ZengSource.com
 * 2011-11-7 下午10:56:09
 */
package org.zengsource.portal.web.system;

import java.util.HashSet;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipleAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.model.Role;
import org.zengsource.portal.model.User;
import org.zengsource.portal.service.RoleService;
import org.zengsource.portal.service.UserService;
import org.zengsource.util.NumberUtil;
import org.zengsource.util.StringUtil;


/**
 * 管理：用户
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public class UserAction extends MultipleAction {

	// + STATIC  +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS   +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	private String email;
	private String status;
	private String username;
	private String roleNames;
	private String instroduction;
	
	private UserService userService;
	private RoleService roleService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public UserAction() {
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	@Override
	protected AbstractView doService() throws MvcException {
		return super.doService();
	}

	public AbstractView doList() throws MvcException {
		List<?> userList = this.userService.search(getQ(), getStartInt(), getLimitInt());
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		if (userList == null) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(this.userService.searchCount(getQ()) + "");
			for(Object obj : userList) {
				User user = (User) obj;
				Element ele = root.addElement("User");
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
				ele.addElement("introduction").addText(user.getIntroduction() + "");
				ele.addElement("updatedTime").addText(user.getUpdatedTime() + "");
				ele.addElement("createdTime").addText(user.getCreatedTime() + "");
			}
		}
		return new XmlView(doc);
	}	

	public AbstractView doSave() throws MvcException {
		User user  = null;		
		if (StringUtil.notBlank(getId())) {
			user = this.userService.getById(NumberUtil.string2Integer(getId(), 0));
			if (user == null) { // 已删除
				return new XmlErrorView("username", "用户不存在");
			}
		} else {
			user = new User();
		}
		if (StringUtil.notBlank(getUsername())) {
			user.setUsername(getUsername());
		} else {
			return new XmlErrorView("username", "用户名不能为空");
		}
		if (StringUtil.notBlank(getEmail())) {
			user.setEmail(getEmail());
		} else {
			return new XmlErrorView("email", "电子邮件不能为空");
		}
		// 状态
		// 简介
		if (this.userService.save(user) < 0) {
			return new XmlErrorView("username", " 保存失败！");
		}
		// 删除旧角色
		if (StringUtil.isBlank(getRoleNames()) || !getRoleNames().equals(user.getRoleSet().toString()) ) {
			//for(Role role : user.getRoleSet()) {
				//if (this.roleService.delete(role) < 0) {
					//logger.warn("doSave() -> " + role.getName() + " 删除失败！");
				//}
			//}
			user.setRoleSet(new HashSet<Role>());
			if (this.userService.save(user) < 0) {
				return new XmlErrorView("username", " 保存失败！");
			}
		}
		// 创建新角色
		if (StringUtil.notBlank(getRoleNames())) {
			String roleNames = getRoleNames().replace("[", "").replace("]", "");
			String[] roleNameArr =roleNames.split(",");
			for(String roleName : roleNameArr) {
				Role role = this.roleService.getByName(roleName);
				if (role == null) {
					logger.warn("doSave() -> 指定的角色不存在: " + roleName);
				} else {
					user.getRoleSet().add(role);
				}
			}
			if (this.userService.save(user) < 0) {
				return new XmlErrorView("username", " 保存失败！");
			}
		}
		return XmlView.SUCCESS;
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public RoleService getRoleService() {
		return roleService;
	}
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getInstroduction() {
		return instroduction;
	}

	public void setInstroduction(String instroduction) {
		this.instroduction = instroduction;
	}
	
	

	// + MAIN^TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
