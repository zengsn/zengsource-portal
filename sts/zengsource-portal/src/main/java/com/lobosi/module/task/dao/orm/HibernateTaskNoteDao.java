/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-10
 */
package com.lobosi.module.task.dao.orm;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

import com.lobosi.module.task.dao.TaskNoteDao;
import com.lobosi.module.task.model.TaskExecution;
import com.lobosi.module.task.model.TaskNote;

/**
 * @author zsn
 * @since 6.0
 */
public class HibernateTaskNoteDao extends Hibernate3DaoTemplate implements TaskNoteDao {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return TaskNote.class;
	}

	public int queryCount(TaskExecution execution, String q) {
		return super.queryCount(executionCriterions(execution, q));
	}

	public List<?> query(TaskExecution execution, String q, Integer start, Integer limit) {
		return super.query(executionCriterions(execution, q), start, limit, Order.desc("createdTime"));
	}

	private Criterion[] executionCriterions(TaskExecution execution, String q) {
		if (execution == null) {
			return new Criterion[] { Restrictions.eq("status", TaskNote.VALID) };
		}
		return new Criterion[] { Restrictions.eq("execution", execution),
				Restrictions.eq("status", TaskNote.VALID) };
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
