/**
 * 
 */
package org.zengsource.portal.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.zengsource.portal.model.Permission;
import org.zengsource.portal.model.Role;
import org.zengsource.portal.model.User;
import org.zengsource.portal.service.UserService;


/**
 * 使用Hibernate保存用户登录信息。
 * 
 * @author zengsn
 * @since 1.6
 */
public class HibernateRealm extends JdbcRealm {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	Logger logger = Logger.getLogger(getClass());

	 private UserService userService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		logger.info("doGetAuthenticationInfo() -> " + token);
		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		String username = userPassToken.getUsername();
		if (username == null) {
			logger.debug("Username is null.");
			return null;
		}
		// read password hash and salt from db
		User user = getUserService().findByUsername(username);
		if (user == null) {
			logger.debug("No account found for user [" + username + "]");
			return null;
		}
		// return salted credentials
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(),
				getName());
		info.setCredentialsSalt(new SimpleByteSource(user.getSalt()));

		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// null usernames are invalid
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}
		String username = (String) getAvailablePrincipal(principals);
		Set<String> roleNames = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();

		User currentUser = this.getUserService().findByUsername(username);
		if (currentUser == null) {
			throw new AuthorizationException("User not exist!");
		}
		for (Role role : currentUser.getRoleSet()) {
			if (role != null) {
				roleNames.add(role.getName());
				while(role.getParent() != null) {
					role = role.getParent();
					roleNames.add(role.getName());
				}
				if (permissionsLookupEnabled) {
					for (Permission p : role.getPermissionSet()) {
						if (p != null) {
							permissions.add(p.getName());
							while(role.getParent() != null) {
								role = role.getParent();
								permissions.add(p.getName());
							}
						}
					}
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
		info.setStringPermissions(permissions);
		return info;
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
