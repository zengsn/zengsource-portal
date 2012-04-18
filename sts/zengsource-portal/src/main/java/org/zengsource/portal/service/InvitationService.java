/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-20
 */
package org.zengsource.portal.service;

import java.util.List;

import org.zengsource.portal.model.Invitation;

/**
 * @author zengsn
 * @since 6.0
 */
public interface InvitationService {

	public void save(Invitation invitation);

	public Invitation findByEmail(String email);

	public Invitation findByCode(String code);

	public long searchCount(String q);

	public List<?> search(String q, Integer start, Integer limit);

	public Invitation findById(Integer id);

}
