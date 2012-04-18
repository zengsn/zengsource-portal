/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-6
 */
package org.zengsource.portal.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.HtmlView;
import org.zengsource.portal.model.Module;
import org.zengsource.portal.model.Page;
import org.zengsource.portal.model.PortletInstance;
import org.zengsource.portal.service.PageService;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * @since 6.0
 */
public class PageAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private PageService pageService;

	private String page;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		return this.doGetInlines();
	}

	/**
	 * { <br />
	 * path: { 'JXP.system.frontend':'system/frontend' }, <br />
	 * requires: ['JXP.system.frontend.controller.SignIn'], <br />
	 * portlets: [<br />
	 * {xtype:'signinportlet',width:1000,height:270,colspan:3,closable: true} <br />
	 * ] <br />
	 * }
	 */
	public AbstractView doGetInlines() throws MvcException {
		String fullContextPath = getContext().getFullContextPath();
		String contextPath = getRequest().getContextPath();
		String pageUrl = getPage().replace(fullContextPath, "").replace(contextPath, "");
		Map<String, String> queryMap = null;
		if (pageUrl.endsWith("/")) {
			pageUrl += "index.jxp";
		} else if (pageUrl.contains("_revert")) {
			queryMap = StringUtil.parseQuery(pageUrl);
			pageUrl = this.pageService.revert(pageUrl);
		}
		Page page = this.pageService.getByUrl(pageUrl);
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (page != null && page.getPortletInstanceSet() != null) { // 创建 JSON
			// 模块加载路径
			json.append("path:{");
			List<Module> usedModules = page.getUsedModules();
			List<String> pathList = new ArrayList<String>();
			for (Module module : usedModules) {
				pathList.add(//
				"'JXP." + module.getName() + ".frontend':'" + getContext().getFullContextPath()
						+ "/" + module.getName() + "/frontend'");
			}
			List<String> jsRequiredModules = page.getJsRequiredModules();
			if (jsRequiredModules != null) {
				for (String name : jsRequiredModules) {
					pathList.add("'JXP." + name + ".frontend':'"
							+ getContext().getFullContextPath() + "/" + name + "/frontend'");
				}
			}
			String pathString = pathList.toString();// 去掉前后的 [ ]
			pathString = pathString.substring(1, pathString.length() - 1);
			json.append(pathString);
			json.append("},");
			List<String> ctrlList = new ArrayList<String>();
			List<String> portletList = new ArrayList<String>();
			List<PortletInstance> sorted = page.getSortedPortletInstances();
			for (PortletInstance pi : sorted) {
				ctrlList.add("'" + pi.getPortlet().getJsController() + "'");
				portletList.add("{" //
						+ "xtype:'" + pi.getPortlet().getJsWidget() //
						+ "',width:" + pi.getWidth() //
						+ ",height:" + pi.getHeight() //
						+ ",colspan:" + pi.getColspan() //
						+ ",rowspan:" + pi.getRowspan() + ",closable:true}");
			}
			// 控制器类名
			json.append("requires:");
			json.append(ctrlList.toString());
			json.append(",");
			// 区块配置
			json.append("portlets:");
			json.append(portletList.toString());
			// 数据
			if (queryMap != null) {
				List<String> dataList = new ArrayList<String>();
				for (String key : queryMap.keySet()) {
					dataList.add("'" + key + "':'" + queryMap.get(key) + "'");
				}
				String dataString = dataList.toString();// 去掉前后的 [ ]
				dataString = dataString.substring(1, dataString.length() - 1);
				json.append(",data:{");
				json.append(dataString);
				json.append("}");
			}
			json.append("");
		}
		json.append("}");
		return new HtmlView(json.toString());
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public PageService getPageService() {
		return pageService;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
