/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-6
 */
package org.zengsource.portal.web;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.SuperAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * @since 6.0
 */
public class ThemingAction extends SuperAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		if (StringUtil.isBlank(getForward())) {
			this.setForward("/themes/portal/index.jsp");
		}
		return super.doService();
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
