/**
 * 
 */
package org.zengsource.portal.web.system;

import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipleAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.model.Module;
import org.zengsource.portal.model.Page;
import org.zengsource.portal.model.Portlet;
import org.zengsource.portal.model.PortletInstance;
import org.zengsource.portal.model.PortletSetting;
import org.zengsource.portal.service.ModuleService;
import org.zengsource.portal.service.PageService;
import org.zengsource.portal.service.PortletInstanceService;
import org.zengsource.portal.service.PortletService;
import org.zengsource.util.NumberUtil;
import org.zengsource.util.StringUtil;

/**
 * @author zeng.xiaoning
 * @since 6.0
 */
public class PortletAction extends MultipleAction {

	private static final long serialVersionUID = 1L;

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	private String page;
	private String instance;
	private String block;
	private String module;
	private String type;

	private String index;
	private String colspan;
	private String rowspan;
	private String cls;
	private String style;
	private String html;
	private String description;

	private String number;

	private ModuleService moduleService;
	private PortletService portletService;
	private PortletInstanceService portletInstanceService;
	private PageService pageService;

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public PortletAction() {
		// TODO Auto-generated constructor stub
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public AbstractView doList() throws MvcException {
		if ("portlet".equals(this.type)) {
			return list4Module();
		} else if ("instance".equals(this.type)) {
			return list4Page();
		} else {
			return listAll();
		}
	}

	/** 返回所有区块列表。*/
	private AbstractView listAll() throws MvcException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		int totalCount = this.portletService.searchCount(getQ());
		if (totalCount <= 0) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(totalCount + "");
			List<?> portlets = getPortletService().search(getQ(), getStartInt(), getLimitInt());
			for (Object obj : portlets) {
				Portlet portlet = (Portlet) obj;
				Element ele = root.addElement("Porlet");
				ele.addElement("id").addText(portlet.getId() + "");
				ele.addElement("name").addText(portlet.getName() + "");
				if (portlet.getModule() != null) {
					ele.addElement("module").addText(portlet.getModule().getName() + "");
				} else {
					ele.addElement("module").addText("Unknown");
				}

				StringBuilder pagesSB = new StringBuilder();
				for (Page page : portlet.getPages()) {
					pagesSB.append(page.getName() + ", ");
				}
				if (pagesSB.lastIndexOf(", ") > -1) {
					pagesSB.deleteCharAt(pagesSB.length() - 2);
				} else {
					pagesSB.append("无");
				}
				ele.addElement("pages").addText(pagesSB.toString());
				
				ele.addElement("singleton").addText(portlet.isSingleton() + "");
				ele.addElement("instances").addText(portlet.getInstanceSet().size() + "");
				ele.addElement("index").addText("-");
				ele.addElement("colspan").addText("-");
				ele.addElement("rowspan").addText("-");
				ele.addElement("description").addText(portlet.getDescription() + "");
			}
		}
		return new XmlView(doc);
	}

	private AbstractView list4Module() throws MvcException {
		// Condition
		Integer start = getStartInt();
		Integer limit = getLimitInt();

		// Module Selecting
		int moduleId = NumberUtil.str2Int(getModule(), 0);
		Module module = this.moduleService.findById(moduleId);

		// Total Count
		Integer totalCount = getPortletService().searchCount(module);

		// XML
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		if (totalCount <= 0) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(totalCount + "");
			List<?> portlets = getPortletService().search(module, start, limit);
			for (Object obj : portlets) {
				Portlet portlet = (Portlet) obj;
				Element ele = root.addElement("block");
				ele.addElement("id").addText(portlet.getId() + "");
				ele.addElement("name").addText(portlet.getName() + "");
				if (portlet.getModule() != null) {
					ele.addElement("module").addText(portlet.getModule().getName() + "");
				} else {
					ele.addElement("module").addText("Unknown");
				}

				StringBuilder pagesSB = new StringBuilder();
				for (Page page : portlet.getPages()) {
					pagesSB.append(page.getName() + ", ");
				}
				if (pagesSB.lastIndexOf(", ") > -1) {
					pagesSB.deleteCharAt(pagesSB.length() - 2);
				} else {
					pagesSB.append("无");
				}

				ele.addElement("pages").addText(pagesSB.toString());
				ele.addElement("singleton").addText(portlet.isSingleton() + "");
				ele.addElement("instances").addText(portlet.getInstanceSet().size() + "");
				ele.addElement("index").addText("-");
				ele.addElement("colspan").addText("-");
				ele.addElement("rowspan").addText("-");
				ele.addElement("description").addText(portlet.getDescription() + "");
			}
		}
		return new XmlView(doc);
	}

	private AbstractView list4Page() throws MvcException {
		// Condition
		Integer start = getStartInt();
		Integer limit = getLimitInt();
		String q = getQ();
		int pageId = NumberUtil.str2Int(getPage(), 0);
		Page page = this.pageService.getById(pageId);

		// Total Count
		Integer totalCount = getPortletInstanceService().searchCount(page, q);

		// XML
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		if (totalCount <= 0) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(totalCount + "");
			List<?> portlets = getPortletInstanceService().search(page, q, start, limit);
			for (Object obj : portlets) {
				PortletInstance instance = (PortletInstance) obj;
				Element ele = root.addElement("block");
				ele.addElement("id").addText(instance.getId() + "");
				ele.addElement("name").addText(instance.getName() + "");
				ele.addElement("module").addText(instance.getPortlet().getModule().getName() + "");
				ele.addElement("pages").addText("page1, page2, page3");
				ele.addElement("index").addText(instance.getIndex() + "");
				ele.addElement("colspan").addText(instance.getColspan() + "");
				ele.addElement("rowspan").addText(instance.getRowspan() + "");
				ele.addElement("description").addText(instance.getDescription() + "");
			}
		}
		return new XmlView(doc);
	}

	/**
	 * Get the instances of specific portlet.
	 * 
	 * @return XML with all instances of the portlet
	 * @throws MvcException
	 */
	public AbstractView doGetInstances() throws MvcException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("reponse").addAttribute("success", "true");
		// Search
		int portletId = NumberUtil.str2Int(getId(), 0);
		Portlet portlet = getPortletService().getById(portletId);
		if (portlet != null) {
			for (PortletInstance instance : portlet.getInstanceSet()) {
				Element ele = root.addElement("instance");
				this.instanceIntoElement(ele, instance);
			}
		}
		return new XmlView(doc);
	}

	/**
	 * If portlet has one or more instance, load the first instance; or load
	 * the portlet itself for creating new instance. Input:
	 * type(portlet|instance), id
	 * 
	 * @return XML with instance.
	 * @throws MvcException
	 */
	public AbstractView doLoad() throws MvcException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("reponse").addAttribute("success", "true");
		Element ele = root.addElement("instance");
		if ("portlet".equals(getType())) {
			int portletId = NumberUtil.str2Int(getId(), 0);
			Portlet portlet = getPortletService().getById(portletId);
			if (portlet != null) {
				PortletInstance instance = null;
				for (PortletInstance ins : portlet.getInstanceSet()) {
					if (ins != null) {
						instance = ins;
						break;
					}
				}
				if (instance == null) { // Create one default instance
					instance = portlet.newInstance(null);
					getPortletInstanceService().save(instance);
				}
				this.instanceIntoElement(ele, instance);
			}
		} else if ("instance".equals(getType())) {
			int instanceId = NumberUtil.str2Int(getId(), 0);
			PortletInstance instance = getPortletInstanceService().getById(instanceId);
			if (instance != null) {
				this.instanceIntoElement(ele, instance);
			}
		} else {
			// Nothing
		}
		return new XmlView(doc);
	}

	private void instanceIntoElement(Element ele, PortletInstance instance) {
		ele.addElement("id").addText(instance.getId() + "");
		ele.addElement("name").addText(instance.getName() + "");
		ele.addElement("module").addText(instance.getPortlet().getModule().getName() + "");
		ele.addElement("index").addText(instance.getIndex() + "");
		ele.addElement("cls").addText(instance.getCls() + "");
		ele.addElement("style").addText(instance.getStyle() + "");
		ele.addElement("colspan").addText(instance.getColspan() + "");
		ele.addElement("rowspan").addText(instance.getRowspan() + "");
		ele.addElement("template").addText(instance.getPortlet().getTemplate() + "");
		ele.addElement("html").addText(instance.getHtml() + "");
		ele.addElement("description").addText(instance.getDescription() + "");
	}

	/**
	 * Get page of specific instance.
	 * 
	 * @return XML with all pages that instance displays on.
	 * @throws MvcException
	 */
	public AbstractView doGetPages() throws MvcException {
		// XML
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("reponse").addAttribute("success", "true");
		// Search
		int instanceId = NumberUtil.str2Int(getId(), 0);
		PortletInstance instance = getPortletInstanceService().getById(instanceId);
		if (instance != null) { // Found
			Set<Page> pages = instance.getPageSet();
			for (Page page : pages) {
				addPageElement(root, page);
			}
		}
		return new XmlView(doc);
	}

	@Deprecated
	public AbstractView doListPages() throws MvcException {
		// XML
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("reponse").addAttribute("success", "true");
		// Search
		int portletId = NumberUtil.str2Int(getId(), 0);
		Portlet portlet = getPortletService().getById(portletId);
		if (portlet != null) { // Found
			Set<Page> pages = portlet.getPages();
			for (Page page : pages) {
				addPageElement(root, page);
			}
		}
		return new XmlView(doc);
	}

	private void addPageElement(Element root, Page page) {
		Element ele = root.addElement("page");
		ele.addElement("id").addText(page.getId() + "");
		ele.addElement("name").addText(page.getName() + "");
		ele.addElement("current").addText(page.getCurrentPortlets() + "");
	}

	/**
	 * Get all settings of specific instance.
	 * 
	 * @return XML with all settings.
	 * @throws MvcException
	 */
	public AbstractView doGetSettings() throws MvcException {
		// XML
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("reponse").addAttribute("success", "true");
		// Search
		int instanceId = NumberUtil.str2Int(getId(), 0);
		PortletInstance instance = getPortletInstanceService().getById(instanceId);
		if (instance != null) {
			Set<PortletSetting> settings = instance.getSettingSet();
			for (PortletSetting setting : settings) {
				Element ele = root.addElement("setting");
				ele.addElement("id").addText(setting.getId() + "");
				ele.addElement("name").addText(setting.getName() + "");
				ele.addElement("key").addText(setting.getKey() + "");
				ele.addElement("value").addText(setting.getValue() + "");
				ele.addElement("usage").addText(setting.getUsage() + "");
			}
		}
		return new XmlView(doc);
	}

	@Deprecated
	public AbstractView doListSettings() throws MvcException {
		// XML
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("reponse").addAttribute("success", "true");
		// Search
		int portletId = NumberUtil.str2Int(getId(), 0);
		Set<PortletSetting> settings = null;
		if (StringUtil.isBlank(getType()) || "portlet".equals(getType())) {
			Portlet portlet = getPortletService().getById(portletId);
			if (portlet != null) { // It's a Portlet.
				settings = portlet.getSettingSet();
				logger.info("Get settings of block portlet: " + portlet.getId());
			}
		} else if ("instance".equals(getType())) {
			PortletInstance instance = getPortletInstanceService().getById(portletId);
			if (instance != null) { // It's a PortletInstance
				settings = instance.getSettingSet();
				logger.info("Get settings of block instance: " + instance.getId());
			}
		}
		if (settings != null) {
			for (PortletSetting setting : settings) {
				Element ele = root.addElement("setting");
				ele.addElement("id").addText(setting.getId() + "");
				ele.addElement("name").addText(setting.getName() + "");
				ele.addElement("key").addText(setting.getKey() + "");
				ele.addElement("value").addText(setting.getValue() + "");
				ele.addElement("usage").addText(setting.getUsage() + "");
			}
		}
		return new XmlView(doc);
	}

	public AbstractView doDisplay() throws MvcException {
		int instanceId = NumberUtil.str2Int(getInstance(), 0);
		int pageId = NumberUtil.str2Int(getPage(), 0);
		Page page = getPageService().getById(pageId);
		if (page != null) {
			PortletInstance instance = getPortletInstanceService().getById(instanceId);
			if (instance != null) {
				instance.addPage(page);
				getPortletInstanceService().save(instance);
				return XmlView.SUCCESS;
			}
			return XmlView.SUCCESS; // TODO
		}
		return XmlView.SUCCESS; // TODO
	}

	public AbstractView doAddPages() throws MvcException {
		int portletId = NumberUtil.str2Int(getBlock(), 0);
		String pageIds = getPage();

		Portlet portlet = getPortletService().getById(portletId);
		if (portlet != null) {
			// Current displaying pages
			List<?> instances = getPortletInstanceService().search(portlet);
			if (StringUtil.notBlank(pageIds)) {
				// Save new-added pages
				String[] pageIdArr = pageIds.split(",");
				for (String pageId : pageIdArr) {
					Page page = getPageService().getById(NumberUtil.str2Int(pageId, 0));
					if (page != null) {
						for (PortletInstance ins : page.getPortletInstanceSet()) {
							if (ins != null && ins.getPortlet() != null
									&& portlet.getId() == ins.getPortlet().getId()) {
								return XmlView.SUCCESS;
							}
						}
						PortletInstance instance = new PortletInstance(portlet, page);
						logger.info("Save new block instance: " + instance.getName());
						getPortletInstanceService().save(instance);
					}
				}
				// Delete unused pages
				for (Object obj : instances) {
					PortletInstance ins = (PortletInstance) obj;
					if (ins != null) {
						// String pid = ins.getPage().getId();
						// if (Arrays.binarySearch(pageIdArr, pid) < 0) {
						// logger.info("Delete block instance: " + ins.getId());
						// getPortletInstanceService().delete(ins);
						// }
					}
				}
			} else { // Delete unused pages
				for (Object obj : instances) {
					PortletInstance ins = (PortletInstance) obj;
					if (ins != null) {
						logger.info("Delete block instance: " + ins.getId());
						getPortletInstanceService().delete(ins);
					}
				}
			}
		}
		return XmlView.SUCCESS;
	}

	public AbstractView doAddInstance() throws MvcException {
		int portletId = NumberUtil.str2Int(getBlock(), 0);
		int pageId = NumberUtil.str2Int(getPage(), 0);
		int instances = NumberUtil.string2Integer(getNumber(), 0);
		Page page = getPageService().getById(pageId);
		Portlet portlet = getPortletService().getById(portletId);
		if (page != null && portlet != null) {
			int currentInstances = 0;
			for (PortletInstance ins : page.getPortletInstanceSet()) {
				if (ins != null && ins.getPortlet() != null //
						&& portletId == ins.getPortlet().getId()) {
					currentInstances++;
				}
			}
			// Cannot add more than one instance to one page for singleton
			// portlet
			if (currentInstances >= 1 && portlet.isSingleton()) {
				return XmlView.SUCCESS;
			}
			for (int i = 0; i < instances - currentInstances; i++) {
				PortletInstance instance = new PortletInstance(portlet, page);
				instance.setName(portlet.getName() + "-" + (currentInstances + i + 1));
				logger.info("Save new block instance: " + instance.getName());
				getPortletInstanceService().save(instance);
			}
		}
		return XmlView.SUCCESS;
	}

	public AbstractView doSave() throws MvcException {
		int portletId = NumberUtil.str2Int(getId(), 0);
		PortletInstance instance = getPortletInstanceService().getById(portletId);
		if (instance != null) {
			// Name
			if (StringUtil.isBlank(getName())) {
				return new XmlErrorView("name", "模板名不能为空!");
			} else {
				instance.setName(getName());
			}
			// Index
			int index = NumberUtil.string2Integer(getIndex(), 1);
			instance.setIndex(index);
			// Colspan
			int colspan = NumberUtil.string2Integer(getColspan(), 1);
			instance.setColspan(colspan);
			// Rowspan
			int rowspan = NumberUtil.string2Integer(getRowspan(), 1);
			instance.setRowspan(rowspan);
			// Cls
			instance.setCls(getCls());
			// Style
			instance.setStyle(getStyle());
			// Description
			instance.setDescription(getDescription());
			// HTML
			instance.setHtml(getHtml());
			// Save
			getPortletInstanceService().save(instance);
		}
		return XmlView.SUCCESS;
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getColspan() {
		return colspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public String getRowspan() {
		return rowspan;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getHtml() {
		return html;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public PortletService getPortletService() {
		return portletService;
	}

	public void setPortletService(PortletService portletService) {
		this.portletService = portletService;
	}

	public PortletInstanceService getPortletInstanceService() {
		return portletInstanceService;
	}

	public void setPortletInstanceService(PortletInstanceService blockInstanceService) {
		this.portletInstanceService = blockInstanceService;
	}

	public PageService getPageService() {
		return pageService;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

}
