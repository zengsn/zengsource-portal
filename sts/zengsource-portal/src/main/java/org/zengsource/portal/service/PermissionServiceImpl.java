/*
 * &copy; 2011 ZengSource.com
 * 2011-11-8 下午07:19:54
 */
package org.zengsource.portal.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.dao.PermissionDao;
import org.zengsource.portal.model.Permission;
import org.zengsource.util.StringUtil;


/**
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public class PermissionServiceImpl implements PermissionService {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private PermissionDao permissionDao;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public PermissionServiceImpl() {
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int save(Permission permission) {
		try {
			Date now = new Date();
			if (permission.getCreatedTime() == null) {
				permission.setCreatedTime(now);
			}
			this.permissionDao.save(permission);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	};
	
	public List<?> search(String q, Integer start, Integer limit) {
		if (StringUtil.isBlank(q)) {
			return this.permissionDao.query(new Criterion[] {}, start, limit);
		} else {
			return this.permissionDao.query(new Criterion[] { //
					Restrictions.like("name", q, MatchMode.ANYWHERE)
			}, start, limit);
		}
	}
	
	public Long searchCount(String q) {
		if (StringUtil.isBlank(q)) {
			return this.permissionDao.queryCountL(new Criterion[] {});
		} else {
			return this.permissionDao.queryCountL(new Criterion[] { //
					Restrictions.like("name", q, MatchMode.ANYWHERE)
			});
		}
	}
	
	public Permission getById(Integer id) {
		return (Permission) this.permissionDao.queryById(id);
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public PermissionDao getPermissionDao() {
		return permissionDao;
	}
	
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	// + MAIN^TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
