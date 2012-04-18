/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-3-1
 */
package org.zengsource.portal.web;

import java.io.IOException;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.util.StringUtil;

import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

/**
 * @author zsn
 * @since 6.0
 */
public class WeiboAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private String code;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		if (StringUtil.isBlank(getCode())) {
			return doGotoAuthorize();
		} else {
			return doAfterAuthorize();
		}
	}

	/** 转到新浪微博授权和登录。 */
	private AbstractView doGotoAuthorize() {
		Oauth oauth = new Oauth();
		try {
			getResponse().sendRedirect(oauth.authorize("code"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 登录后处理。 */
	private AbstractView doAfterAuthorize() {
		Oauth oauth = new Oauth();
		try {
			AccessToken accessToken = oauth.getAccessTokenByCode(getCode());
			logger.info(accessToken);
			Weibo weibo = new Weibo();
			weibo.setToken(accessToken.getAccessToken());
			Users um = new Users();
			User user = um.showUserById(accessToken.getUid());
			logger.info(user.toString());
			// 创新微博用户
		} catch (WeiboException e) {
			if (401 == e.getStatusCode()) {
				logger.info("Unable to get the access token.");
			} else {
				e.printStackTrace();
			}
		}
		return super.doService();
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
