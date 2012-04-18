/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-10
 */
package com.lobosi.module.task.dao.orm;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.zengsource.util.StringUtil;
import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

import com.lobosi.module.task.dao.TaskExecutionDao;
import com.lobosi.module.task.model.Task;
import com.lobosi.module.task.model.TaskExecution;
import com.lobosi.module.task.model.TaskExecutor;

/**
 * @author zsn
 * @since 6.0
 */
public class HibernateTaskExecutionDao extends Hibernate3DaoTemplate implements TaskExecutionDao {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	public Class<?> getPrototypeClass() {
		return TaskExecution.class;
	}

	public int queryCount(TaskExecutor executor, String q) {
		if (executor == null) {
			return 0;
		}
		return super.queryCount(this.executorCriterions(executor, q));
	}

	public List<?> query(TaskExecutor executor, String q, int start, int limit) {
		if (executor == null) {
			return null;
		}
		return super.query(this.executorCriterions(executor, q), start, limit, Order.desc("createdTime"));
	}

	private Criterion[] executorCriterions(TaskExecutor executor, String q) {
		if (StringUtil.isBlank(q)) {
			return new Criterion[] { //
			Restrictions.eq("executor", executor) };
		}
		throw new RuntimeException("未实现");
		// return new Criterion[] { //
		// Restrictions.eq("executor", executor), //
		// Restrictions.like("", q) };
	}

	public TaskExecution queryMain(Task task) {
		return (TaskExecution) super.queryUnique(new Criterion[] { //
				Restrictions.eq("task", task), Restrictions.eq("isMain", Boolean.TRUE) });
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
