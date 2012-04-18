/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-19
 */
package com.lobosi.module.social.service;

import java.util.List;

import org.zengsource.portal.module.user.service.UserInfoService;

/**
 * @author zsn
 * @since 6.0
 */
public class SocialServiceImpl implements SocialService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private UserInfoService userInfoService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int searchCountFeatured() {
		return this.userInfoService.searchCountInterested(this.userInfoService.getCurrent());
	}

	public List<?> searchFeatured(int start, int limit) {
		return this.userInfoService.searchInterested(this.userInfoService.getCurrent(), start, limit);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
