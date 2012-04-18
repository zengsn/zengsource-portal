/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-19
 */
package com.lobosi.module.social.service;

import java.util.List;

/**
 * @author zsn
 * @since 6.0
 */
public interface SocialService {

	/** 查询系统推荐用户数。*/
	public int searchCountFeatured();

	/** 查询系统推荐用户列表。*/
	public List<?> searchFeatured(int start, int limit);

}
