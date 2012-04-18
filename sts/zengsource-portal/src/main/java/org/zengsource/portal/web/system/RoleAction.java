/*
 * &copy; 2011 ZengSource.com
 * 2011-11-8 下午07:32:55
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
import org.zengsource.portal.model.Role;
import org.zengsource.portal.service.RoleService;
import org.zengsource.util.StringUtil;


/**
 * 管理：角色。
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public class RoleAction extends MultipleAction {

	// + STATIC  +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS   +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	private String description;
	
	private RoleService roleService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public RoleAction() {
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	@Override
	protected AbstractView doService() throws MvcException {
		return super.doService();
	}

	public AbstractView doList() throws MvcException {
		List<?> roleList = this.roleService.search(getQ(), getStartInt(), getLimitInt());
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		if (roleList == null) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(this.roleService.searchCount(getQ()) + "");
			for(Object obj : roleList) {
				Role role = (Role) obj;
				Element ele = root.addElement("Role");
				ele.addElement("rid").addText(role.getId() + "");
				ele.addElement("name").addText(role.getName());
				ele.addElement("description").addText(role.getDescription());
				ele.addElement("permissions").addText(role.getPermissionSet() + "");
				ele.addElement("createdTime").addText(role.getCreatedTime() + "");
			}
		}
		return new XmlView(doc);
	}

	public AbstractView doSave() throws MvcException {
		if (StringUtil.isBlank(getName())) {
			return new XmlErrorView("name", "名称不能为空");
		}
		if (StringUtil.isBlank(getDescription())) {
			//return new XmlErrorView("description", "描述不能为空");
		}
		Role role = new Role(getName());
		role.setDescription(getDescription() + "");
		if (this.roleService.save(role) > 0) {
			return XmlView.SUCCESS;
		} else {
			return new XmlErrorView("name", "保存失败！");
		}
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public RoleService getRoleService() {
		return roleService;
	}
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	// + MAIN^TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
