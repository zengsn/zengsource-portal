/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-16
 */
package com.lobosi.module.notepad.dao.orm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

import com.lobosi.module.notepad.dao.NoteDao;
import com.lobosi.module.notepad.model.Note;

/**
 * @author zsn
 * @since 6.0
 */
public class HibernateNoteDao extends Hibernate3DaoTemplate implements NoteDao {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return Note.class;
	}

	public List<?> queryRecent(UserInfo author, int limit) {
		return super.query(
				new Criterion[] {//
				// Restrictions.eq("author", author),
						Restrictions.sqlRestriction("authorid_ = ?", author.getId(),
								IntegerType.INSTANCE), Restrictions.eq("status", Note.NORMAL) },
				0, limit, Order.desc("createdTime"));
	}

	public List<?> queryBetween(UserInfo author, Date start, Date end) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.sqlRestriction("authorid_ = ?", author.getId(),
				IntegerType.INSTANCE));
		if (start != null) {
			criterions.add(Restrictions.ge("createdTime", start));
		}
		if (end != null) {
			criterions.add(Restrictions.le("createdTime", end));
		}
		Criterion[] criterionArr = new Criterion[criterions.size()];
		for (int i = 0; i < criterionArr.length; i++) {
			criterionArr[i] = criterions.get(i);
		}
		return super.query(criterionArr, 0, 0, Order.asc("createdTime"));
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
