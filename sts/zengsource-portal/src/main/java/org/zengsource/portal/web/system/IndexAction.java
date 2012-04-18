/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-23
 */
package org.zengsource.portal.web.system;

import java.util.List;
import java.util.Set;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipleAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.HtmlView;
import org.zengsource.portal.model.Menu;
import org.zengsource.portal.model.Module;
import org.zengsource.portal.service.ModuleService;

/**
 * @author zsn
 * @since 6.0
 */
public class IndexAction extends MultipleAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private ModuleService moduleService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		logger.info(getForward());
		return super.doService();
	}

	public AbstractView doListNodes() throws MvcException {
		List<?> allModules = this.moduleService.findByUser(null);
		if (allModules == null) {
			return new HtmlView("{}");
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{text:'.',children:[");
		boolean firstNode = true;
		int mdCount = 0;
		int mdTotal = allModules.size();
		for (Object obj : allModules) {
			Module module = (Module) obj;
			mdCount++;
			sb.append("{");
			sb.append("text:'" + module.getTitle() + "',");
			if (firstNode) {
				sb.append("expanded: true,");
			}
			sb.append("children:[");
			Set<Menu> menuSet = module.getMenuSet();
			int mnTotal = menuSet.size();
			int mnCount = 0;
			for (Menu menu : menuSet) {
				mnCount++;
				sb.append("{");
				sb.append("id:'" + menu.getName() + "',");
				sb.append("text:'" + menu.getTitle() + "',");
				sb.append("leaf:" + menu.isLeaf() + ",");
				sb.append("widget:'" + menu.getWidget() + "',");
				sb.append("controller:'" + menu.getController() + "'");
				sb.append("}");
				if (mnCount < mnTotal) {
					sb.append(",");
				}
			}
			sb.append("]");
			sb.append("}");
			if (mdCount < mdTotal) {
				sb.append(",");
			}
		}
		sb.append("]}");
		return new HtmlView(sb.toString());
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
