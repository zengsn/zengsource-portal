/*
 * &copy; 2011 ZengSource.com
 * 2011-11-8 下午07:15:39
 */
package org.zengsource.portal.web.system;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipleAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.model.Permission;
import org.zengsource.portal.model.Role;
import org.zengsource.portal.service.PermissionService;
import org.zengsource.portal.service.RoleService;
import org.zengsource.util.NumberUtil;
import org.zengsource.util.StringUtil;


/**
 * 管理：权限。
 * 
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public class PermissionAction extends MultipleAction {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private String roleId;

	private RoleService roleService;
	private PermissionService permissionService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public PermissionAction() {
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		return super.doService();
	}
	
	public AbstractView doList() throws MvcException {
		List<?> permissionList = this.permissionService.search(getQ(), getStartInt(), getLimitInt());
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		if (permissionList == null) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(this.permissionService.searchCount(getQ()) + "");
			for (Object obj : permissionList) {
				Permission permission = (Permission) obj;
				Element ele = root.addElement("Permission");
				ele.addElement("pid").addText(permission.getId() + "");
				ele.addElement("name").addText(permission.getName());
				ele.addElement("roleName").addText(permission.getRole().getName());
				ele.addElement("createdTime").addText(permission.getCreatedTime() + "");
			}			
		}
		return new XmlView(doc);
	}

	public AbstractView doSave() throws MvcException {
		Permission permission = null;
		if (StringUtil.notBlank(getId())) {
			permission = this.permissionService.getById(NumberUtil.string2Integer(getId(), 0));
			if (permission == null) {
				return new XmlErrorView("name", "修改的权限不存在");
			}
		} else {
			if (StringUtil.isBlank(getName())) {
				return new XmlErrorView("name", "不能为空");
			}
			if (StringUtil.isBlank(getRoleId())) {
				return new XmlErrorView("roleName", "不能为空");
			}
			permission = new Permission();
		}
		permission.setName(getName());
		Role role = this.roleService.getById(NumberUtil.string2Integer(getRoleId(), 0));
		if (role == null && permission.getRole() == null) { // 新建
			return new XmlErrorView("roleName", "角色不存在");
		} else if (role != null) {
			permission.setRole(role);
		}
		
		if (this.permissionService.save(permission) > 0) {
			return XmlView.SUCCESS;
		} else {
			return new XmlErrorView("name", "保存失败！");
		}
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public String getRoleId() {
		return roleId;
	}
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public RoleService getRoleService() {
		return roleService;
	}
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	// + MAIN^TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
