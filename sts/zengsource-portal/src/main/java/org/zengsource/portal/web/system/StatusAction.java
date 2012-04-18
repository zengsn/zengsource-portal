/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-14
 */
package org.zengsource.portal.web.system;

import java.util.ArrayList;
import java.util.List;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.SuperAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.HtmlView;
import org.zengsource.portal.service.ConfigService;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * @since 6.0
 */
public class StatusAction extends SuperAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	private ConfigService configService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public AbstractView doCheck() throws MvcException {
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("errors:"); // 错误信息：
		List<String> errors = new ArrayList<String>();
		// 检查Gmail配置
		String gmailUsername = this.configService.getString("gmail.username");
		String gmailPassword = this.configService.getString("gmail.password");
		if (StringUtil.isBlank(gmailUsername) || //
				StringUtil.isBlank(gmailPassword)) {
			errors.add("{msg:'Gmail设置异常：需要配置 gmail.username 和 gmail.password！'}");
		}
		json.append(errors.toString());
		// 警告信息：
		json.append("}");
		return new HtmlView(json.toString());
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public ConfigService getConfigService() {
		return configService;
	}
	
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
