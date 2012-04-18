/**
 * 
 */
package org.zengsource.portal.web.system;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipleAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.model.Page;
import org.zengsource.portal.model.Portlet;
import org.zengsource.portal.model.PortletInstance;
import org.zengsource.portal.service.PageService;
import org.zengsource.portal.service.PortletInstanceService;
import org.zengsource.portal.service.PortletService;
import org.zengsource.util.NumberUtil;
import org.zengsource.util.StringUtil;

/**
 * @author zeng.xiaoning
 * @since 6.0
 */
public class PageAction extends MultipleAction {

	private static final long serialVersionUID = 1L;

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	private PageService pageService;
	private PortletInstanceService portletInstanceService;
	private PortletService portletService;

	private String instance;
	private String portlet;

	private String cls;
	private String style;
	private String columns;
	private String description;

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public PageAction() {
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public AbstractView doList() throws MvcException {
		// Search
		String q = getQ();
		Integer start = getStartInt();
		Integer limit = getLimitInt();

		// Search Count
		Integer totalCount = getPageService().searchCount(q);

		// XML
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		if (totalCount == 0) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(totalCount + "");
			List<?> pages = getPageService().search(q, start, limit);
			for (Object obj : pages) {
				Page page = (Page) obj;
				Element ele = root.addElement("Page");
				ele.addElement("id").addText(page.getId() + "");
				ele.addElement("name").addText(page.getName() + "");
				ele.addElement("module").addText(page.getModule().getName() + "");
				ele.addElement("mindex").addText(page.getModule().getIndex() + "");
				ele.addElement("url").addText(page.getUrl() + "");
				if (StringUtil.notBlank(this.portlet)) {
					int instanceNum = 0;
					for (PortletInstance ins : page.getPortletInstanceSet()) {
						if (ins != null && ins.getPortlet() != null
								&& this.portlet.equals(ins.getPortlet().getId())) {
							instanceNum++;
						}
					}
					ele.addElement("instances").addText(instanceNum + "");
				}
				ele.addElement("current").addText(page.getCurrentPortlets() + "");
				ele.addElement("description").addText(page.getDescription() + "");
				ele.addElement("createdTime").addText(page.getCreatedTime() + "");
				ele.addElement("updatedTime").addText(page.getUpdatedTime() + "");
			}
		}
		return new XmlView(doc);
	}

	public AbstractView doLoad() throws MvcException {
		String pageId = getId();
		Page page = getPageService().getById(NumberUtil.string2Integer(pageId, 0));
		if (page == null) {
			return new XmlErrorView("name", "Page doesn't exist!");
		}
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		Element ele = root.addElement("Page");
		ele.addElement("id").addText(page.getId() + "");
		ele.addElement("name").addText(page.getName() + "");
		ele.addElement("columns").addText(page.getColumns() + "");
		ele.addElement("cls").addText(page.getCls() == null ? "" : page.getCls());
		ele.addElement("style").addText(page.getStyle() == null ? "" : page.getStyle());
		ele.addElement("description").addText(
				page.getDescription() == null ? "" : page.getDescription());
		return new XmlView(doc);
	}

	public AbstractView doConfigInstance() throws MvcException {
		// Params
		int pageId = NumberUtil.str2Int(getId(), 0);
		int instanceId = NumberUtil.str2Int(getInstance(), 0);
		String field = getField();
		String value = getValue();
		// Assign block of page
		Page page = getPageService().getById(pageId);
		if (page != null) {
			for (PortletInstance instance : page.getPortletInstanceSet()) {
				if (instance != null && instance.getId() == instanceId) {
					if ("index".equals(field)) {
						int oldIndex = instance.getIndex();
						int newIndex = NumberUtil.string2Integer(value, 1);
						logger.info("Adjust index [" + oldIndex + "] to [" + newIndex + "]");
						instance.setIndex(newIndex);
						// block
					} else if ("colspan".equals(field)) {
						int colspan = NumberUtil.string2Integer(value, 1);
						instance.setColspan(colspan);
						logger.info("Update colspan to " + colspan);
					} else if ("rowspan".equals(field)) {
						int rowspan = NumberUtil.string2Integer(value, 1);
						instance.setRowspan(rowspan);
						logger.info("Update rowspan to " + rowspan);
					}
					// Save
					logger.info("Save block instance: " + instance.getId());
					getPortletInstanceService().save(instance);
					break;
				}
			}
		} else {
			logger.warn("Page not found!");
		}
		return XmlView.SUCCESS;
	}

	public AbstractView doAddInstance() throws MvcException {
		// Params
		String pageId = getId();
		String field = getField();
		String value = getValue();
		// Add instance into page
		if ("prototype".equals(field)) {
			Page page = getPageService().getById(NumberUtil.string2Integer(pageId, 0));
			if (page != null) {
				Portlet prototype = getPortletService().getById(
						NumberUtil.string2Integer(value, 0));
				if (prototype != null) {
					PortletInstance instance = prototype.newInstance(page);
					getPortletInstanceService().save(instance);
				}
			}
		} else {
			throw new MvcException("Impossible!");
		}
		return XmlView.SUCCESS;
	}

	/**
	 * Remove block instance from page.
	 * 
	 * @return XML
	 * @throws MvcException
	 */
	public AbstractView doRemoveInstance() throws MvcException {
		// Params
		String pageId = getId();
		String instanceIds = getInstance();
		// Search
		Page page = getPageService().getById(NumberUtil.string2Integer(pageId, 0));
		if (page != null && instanceIds != null) {
			String[] idArr = instanceIds.split(",");
			for (String id : idArr) {
				PortletInstance removed = page.removePortlet(id);
				getPageService().save(page);
				if (removed != null && removed.getPageSet().size() == 0) {
					getPortletInstanceService().delete(removed);
				}
			}
		} else {
			//
		}
		return XmlView.SUCCESS;

	}

	public AbstractView doGetInstances() throws MvcException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		String pageId = getId();
		Page page = getPageService().getById(NumberUtil.string2Integer(pageId, 0));
		if (page != null && page.getPortletInstanceSet() != null) {
			PortletInstance[] instances = new PortletInstance[page.getPortletInstanceSet().size()];
			page.getPortletInstanceSet().toArray(instances);
			Arrays.sort(instances, new Comparator<PortletInstance>() {
				public int compare(PortletInstance o1, PortletInstance o2) {
					if (o1 == null || o2 == null) {
						return 0;
					}
					return o1.getIndex() - o2.getIndex();
				}
			});
			for (PortletInstance instance : instances) {
				if (instance != null && instance.getPortlet() != null) {
					Element ele = root.addElement("instance");
					this.instanceIntoElement(ele, instance);
				}
			}
		}
		return new XmlView(doc);
	}

	private void instanceIntoElement(Element ele, PortletInstance instance) {
		if (instance != null && instance.getPortlet() != null) {
			ele.addElement("id").addText(instance.getId() + "");
			ele.addElement("name").addText(instance.getName() + "");
			// ele.addElement("module").addText(instance.getPortlet().getModule
			// ().
			// getName() + "");
			ele.addElement("prototype").addText(instance.getPortlet().getName() + "");
			ele.addElement("index").addText(instance.getIndex() + "");
			ele.addElement("style").addText(instance.getStyle() + "");
			ele.addElement("colspan").addText(instance.getColspan() + "");
			ele.addElement("rowspan").addText(instance.getRowspan() + "");
			ele.addElement("template").addText(instance.getPortlet().getTemplate() + "");
			ele.addElement("html").addText(instance.getHtml() + "");
			ele.addElement("description").addText(instance.getDescription() + "");
		}
	}

	public AbstractView doSave() throws MvcException {
		String pageId = getId();
		Page page = getPageService().getById(NumberUtil.string2Integer(pageId, 0));
		if (page != null) {
			if (StringUtil.notBlank(getName())) {
				page.setName(getName());
			} else {
				return new XmlErrorView("name", "Name cannot be empty!");
			}
			page.setColumns(NumberUtil.string2Integer(getColumns(), 1));
			page.setCls(getCls());
			page.setStyle(getStyle());
			page.setDescription(getDescription());
			getPageService().save(page);
		} else {
			// TODO
		}
		return XmlView.SUCCESS;
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public String getPortlet() {
		return portlet;
	}

	public PageService getPageService() {
		return pageService;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	public PortletInstanceService getPortletInstanceService() {
		return portletInstanceService;
	}

	public void setPortletInstanceService(PortletInstanceService blockInstanceService) {
		this.portletInstanceService = blockInstanceService;
	}

	public PortletService getPortletService() {
		return portletService;
	}

	public void setPortletService(PortletService blockPrototypeService) {
		this.portletService = blockPrototypeService;
	}

	public void setPortlet(String prototype) {
		this.portlet = prototype;
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

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
