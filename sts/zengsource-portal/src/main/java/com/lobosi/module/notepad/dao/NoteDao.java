/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-16
 */
package com.lobosi.module.notepad.dao;

import java.util.Date;
import java.util.List;

import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.spring.dao.DaoInterface;

/**
 * @author zsn
 * @since 6.0
 */
public interface NoteDao extends DaoInterface {

	public List<?> queryRecent(UserInfo author, int limit);

	public List<?> queryBetween(UserInfo author, Date start, Date end);

}
