/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.dao.orm;

import org.zengsource.util.spring.dao.orm.Hibernate3DaoTemplate;

import com.lobosi.module.task.dao.FileTaskDao;
import com.lobosi.module.task.model.FileTask;

/**
 * @author zsn
 * @since 6.0
 */
public class HibernateFileTaskDao extends Hibernate3DaoTemplate implements FileTaskDao {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	@Override
	public Class<?> getPrototypeClass() {
		return FileTask.class;
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
