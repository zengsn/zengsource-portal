/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-16
 */
package com.lobosi.module.notepad.service;

import java.util.Date;
import java.util.List;

import org.zengsource.portal.module.user.model.UserInfo;

import com.lobosi.module.notepad.model.Note;

/**
 * @author zsn
 * @since 6.0
 */
public interface NoteService {

	public Note findById(int id);

	public void save(Note note);

	// 查询最近日记
	public List<?> searchRecent(UserInfo author, int limit);

	public List<?> search(UserInfo author, Date start, Date end);

}
