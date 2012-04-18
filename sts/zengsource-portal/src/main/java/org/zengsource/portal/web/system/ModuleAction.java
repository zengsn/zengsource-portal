/**
 * 
 */
package org.zengsource.portal.web.system;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipleAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.JsonResultView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.model.Module;
import org.zengsource.portal.service.ModuleService;
import org.zengsource.portal.service.UserService;
import org.zengsource.util.NumberUtil;
import org.zengsource.util.StringUtil;

/**
 * @author snzeng
 * @since 6.0
 */
public class ModuleAction extends MultipleAction {

	private static final long serialVersionUID = 1L;

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	private ModuleService moduleService;
	private UserService userService;

	private String moduleId;
	private String option;
	private String status;

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public ModuleAction() {
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	/** action=list */
	public AbstractView doList() throws MvcException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		//List<?> modules = this.moduleService.getByUser(this.userService.getCurrentUser());
		List<Module> modules = this.moduleService.findForAdmin();
		// logger.info("Get modules: " + modules);
		// Total count
		if (modules == null) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(modules.size() + "");
			// Modules
			for (Object obj : modules) {
				Module module = (Module) obj;
				Element e = root.addElement("Module");
				e.addElement("id").addText(module.getId()+"");
				e.addElement("name").addText(module.getName());
				e.addElement("title").addText(module.getTitle());
				e.addElement("index").addText(module.getIndex() + "");
				e.addElement("status").addText(module.getStatus() + "");
				//e.addElement("configFile").addText(module.getConfigFile() + "");
				e.addElement("createdTime").addText(module.getCreatedTime() + "");
				e.addElement("updatedTime").addText(module.getUpdatedTime() + "");
			}
		}
		return new XmlView(doc);
	}
	
	public AbstractView doSelectList() throws MvcException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		List<?> modules = this.moduleService.findByUser(this.userService.getCurrentUser());
		// logger.info("Get modules: " + modules);

		// All Select
		Element all = root.addElement("module");
		all.addElement("id").addText("");
		all.addElement("name").addText("所有模块");

		// Modules
		for (Object obj : modules) {
			Module module = (Module) obj;
			Element e = root.addElement("module");
			e.addElement("id").addText(module.getId()+"");
			e.addElement("name").addText(module.getName());
		}
		return new XmlView(doc);
	}

	public AbstractView doReload() throws MvcException {
		logger.info("重新加载模块：" + getModuleId());
		if (StringUtil.notBlank(getModuleId())) {
			String[] idArr = getModuleId().split(",");
			for (String id : idArr) {
				this.moduleService.reload(NumberUtil.string2Integer(id, 0), getOption());
			}
		}
		return XmlView.SUCCESS;
	}

	/** 修改模块状态。*/
	public AbstractView doStatus() throws MvcException {
		if (StringUtil.notBlank(getModuleId())) {
			int status = NumberUtil.str2Int(getStatus(), 0);
			String[] idArr = getModuleId().split(",");
			for (String id : idArr) {
				this.moduleService.changeStatus(NumberUtil.string2Integer(id, 0), status);
			}
			return new JsonResultView("msg", "模块：" + getModuleId() + " 已激活！");
		}
		return new JsonResultView("msg", "无模块ID！");
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	
	public String getOption() {
		return option;
	}
	
	public void setOption(String option) {
		this.option = option;
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}
