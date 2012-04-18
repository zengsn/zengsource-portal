/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-20
 */
package org.zengsource.portal.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 邀请码。
 * 
 * @author zengsn
 * @since 6.0
 */
public class Invitation implements Serializable{

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	public static final int VALID = 0;
	public static final int ACCEPT = 1;
	public static final int USED = 2;
	public static final int EXPIRED = 3;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private int id;
	/** 邀请者。 */
	private User inviter;
	/** 被邀请者。 */
	private User invitee;
	private String email;
	/** 邀请方式：未知|邮件|第三方转发。 */
	private String method;

	private String code;
	private int status;
	private String introduction;

	private Date createdTime;
	private Date updatedTime;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	@Override
	public String toString() {
		return this.email + "|" + this.code;
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getInviter() {
		return inviter;
	}

	public void setInviter(User inviter) {
		this.inviter = inviter;
	}

	public User getInvitee() {
		return invitee;
	}

	public void setInvitee(User invitee) {
		this.invitee = invitee;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getIntroduction() {
		return introduction;
	}
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
