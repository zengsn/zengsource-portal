/*
 * &copy; 2011 ZengSource.com
 * 2011-11-8 下午07:31:08
 */
package org.zengsource.portal.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.dao.RoleDao;
import org.zengsource.portal.model.Role;
import org.zengsource.util.StringUtil;


/**
 * @author zengsn
 * @since 1.6
 * @version 1.0.0
 */
public class RoleServiceImpl implements RoleService {

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private RoleDao roleDao;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public List<?> search(String q, Integer start, Integer limit) {
		if (StringUtil.isBlank(q)) {
			return this.roleDao.query(new Criterion[] {}, start, limit);
		} else {
			return this.roleDao.query(new Criterion[] {// 
					Restrictions.like("name", q, MatchMode.ANYWHERE)
			}, start, limit);
		}
	};
	
	public Long searchCount(String q) {
		if (StringUtil.isBlank(q)) {
			return this.roleDao.queryCountL(new Criterion[] {});
		} else {
			return this.roleDao.queryCountL(new Criterion[] {// 
					Restrictions.like("name", q, MatchMode.ANYWHERE)
			});
		}
	}
	
	public int save(Role role) {
		try {
			Date now = new Date();
			if (role.getCreatedTime() == null) {
				role.setCreatedTime(now);
			}
			role.setUpdatedTime(now);
			this.roleDao.save(role);
		} catch (Exception e) {
			return -1;
		}
		return 1;
	}
	
	public Role create(String roles) {
		String[] parts = roles.split(",");
		Role lastRole = null;
		for(String part : parts) {
			lastRole = null;
			part = part.trim();
			String[] roleArr = part.split(">");
			for(String roleName : roleArr) {
				roleName = roleName.trim();
				Role role = this.getByName(roleName);
				if (role == null) {
					role = new Role(roleName);
					role.setDescription(roleName);
					if (lastRole != null) {
						role.setParent(lastRole);
					}
					this.save(role);
				}
				lastRole = role;
			}
		}
		return lastRole;
	}
	
	public Role getById(Integer id) {
		return (Role) this.roleDao.queryById(id);
	}
	
	public int delete(Role role) {
		try {
			this.roleDao.delete(role);
		} catch (Exception e) {
			return -1;
		}
		return 1;
	}
	
	public Role getByName(String roleName) {
		if (StringUtil.notBlank(roleName)) {
			return (Role) this.roleDao.queryUnique(new Criterion[] { //
					Restrictions.eq("name", roleName)
			});
		}
		return null;
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
