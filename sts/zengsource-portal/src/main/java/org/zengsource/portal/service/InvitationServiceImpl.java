/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-20
 */
package org.zengsource.portal.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.zengsource.portal.dao.InvitationDao;
import org.zengsource.portal.model.Invitation;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * @since 6.0
 */
public class InvitationServiceImpl implements InvitationService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private InvitationDao invitationDao;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public void save(Invitation invitation) {
		if (invitation != null) {
			Date now = new Date();
			if (invitation.getCreatedTime() == null) {
				invitation.setCreatedTime(now);
			}
			invitation.setUpdatedTime(now);
			this.invitationDao.save(invitation);
		}
	}
	
	public Invitation findById(Integer id) {
		return (Invitation) this.invitationDao.queryById(id);
	}

	public Invitation findByEmail(String email) {
		if (StringUtil.isBlank(email)) {
			return null;
		}
		return (Invitation) this.invitationDao.queryUnique("email", email.trim());
	}

	public Invitation findByCode(String code) {
		if (StringUtil.isBlank(code)) {
			return null;
		}
		return (Invitation) this.invitationDao.queryUnique("code", code.trim());
	}

	public long searchCount(String q) {
		if (StringUtil.isBlank(q)) {
			return this.invitationDao.queryCount(new Criterion[0]);
		} else {
			return this.invitationDao.queryCount(searchCriterion(q));
		}
	}

	public List<?> search(String q, Integer start, Integer limit) {
		if (StringUtil.isBlank(q)) {
			return this.invitationDao.query(new Criterion[0], start, limit);
		} else {
			return this.invitationDao.query(searchCriterion(q), start, limit);
		}
	}

	private Criterion[] searchCriterion(String q) {
		return new Criterion[] { Restrictions.or(//
				Restrictions.like("email", q), //
				Restrictions.like("introduction", q)) };
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public InvitationDao getInvitationDao() {
		return invitationDao;
	}

	public void setInvitationDao(InvitationDao invitationDao) {
		this.invitationDao = invitationDao;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
