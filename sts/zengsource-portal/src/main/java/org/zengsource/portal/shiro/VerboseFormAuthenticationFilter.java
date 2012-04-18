/**
 * 
 */
package org.zengsource.portal.shiro;

import javax.servlet.ServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * Shiro认证异常处理。
 * @author zengsn
 * @since 1.6
 */
public class VerboseFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		String message = ae.getMessage();
		request.setAttribute(getFailureKeyAttribute(), message);
	}
}
