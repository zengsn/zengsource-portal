/**
 * 
 */
package org.zengsource.portal.web.system;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.DocumentHelper;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipleAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.model.Config;
import org.zengsource.portal.service.ConfigService;
import org.zengsource.util.NumberUtil;
import org.zengsource.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;

/**
 * @author snzeng
 * @since 6.0
 */
public class ConfigAction extends MultipleAction {

	private static final long serialVersionUID = 1L;

	// ~ 对象属性 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	private ConfigService configService;

	private String key;
	private String group;
	private String description;

	// ~ 构造方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public ConfigAction() {
	}

	// ~ 逻辑方法 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	
	@Override
	protected AbstractView doService() throws MvcException {
		return super.doService();
	}

	public AbstractView doSave() throws MvcException {
		Config config = new Config();
		if (StringUtil.notBlank(getId())) { // load data
			config = this.configService.getById(NumberUtil.string2Integer(getId(), 0));
			if (getField().equals("desc")) {
				config.setDescription(getValue());
			}
			try {
				BeanUtils.setProperty(config, getField(), getValue());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			config.setName(getName());
			config.setKey(getKey());
			config.setValue(getValue());
			config.setGroup(getGroup());
			config.setDescription(getDescription());
		}
		this.configService.save(config);

		return XmlView.SUCCESS;
	}

	public AbstractView doList() throws MvcException {
		// Check parameters
		int start = getStartInt();
		int limit = getLimitInt();
		String query = getQ();
		// Query total
		int totalCount = this.configService.searchCount(query);
		// Return XML
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		root.addElement("totalCount").addText(totalCount + "");
		if (totalCount > 0) {
			List<?> configs = this.configService.searchByGroup(query, group, start, limit);
			for (Object obj : configs) {
				Element cfgEle = root.addElement("Config");
				Config config = (Config) obj;
				cfgEle.addElement("id").addText(config.getId() + "");
				cfgEle.addElement("name").addText(config.getName() + "");
				cfgEle.addElement("key").addText(config.getKey() + "");
				cfgEle.addElement("value").addText(config.getValue() + "");
				cfgEle.addElement("group").addText(config.getGroup() + "");
				cfgEle.addElement("desc").addText(config.getDescription() + "");
			}
		}
		return new XmlView(doc);
	}

	// ~ g^setX ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

	public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configurationService) {
		this.configService = configurationService;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}
