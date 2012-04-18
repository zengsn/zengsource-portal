/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-16
 */
package com.lobosi.module.notepad.service;

import java.util.Date;
import java.util.List;

import org.zengsource.portal.module.user.model.UserInfo;

import com.lobosi.module.notepad.dao.NoteDao;
import com.lobosi.module.notepad.model.Note;

/**
 * @author zsn
 * @since 6.0
 */
public class NoteServiceImpl implements NoteService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private NoteDao noteDao;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public Note findById(int id) {
		if (id <= 0) {
			return null;
		}
		return (Note) this.noteDao.queryById(id);
	};
	
	public void save(Note note) {
		if (note != null) {
			this.noteDao.updateTime(note);
			this.noteDao.save(note);
		}
	}
	
	public List<?> searchRecent(UserInfo author, int limit) {
		if (author == null) {
			return null;
		}
		return this.noteDao.queryRecent(author, limit);
	}
	
	public List<?> search(UserInfo author, Date start, Date end) {
		if (author == null) {
			return null;
		}
		return this.noteDao.queryBetween(author, start, end);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public NoteDao getNoteDao() {
		return noteDao;
	}

	public void setNoteDao(NoteDao noteDao) {
		this.noteDao = noteDao;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
