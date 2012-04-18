/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-22
 */
package org.zengsource.portal.web.plugins;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.zengsource.mvc.plugin.PluginException;
import org.zengsource.mvc.plugin.PluginTemplate;

/**
 * @author zsn
 * @since 6.0
 */
public class UserPlugin extends PluginTemplate {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public boolean enable() throws PluginException {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			getRequest().setAttribute("username", currentUser.getPrincipal());
		}
		return false;
	}

	public void disable() throws PluginException {
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
