/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-7
 */
package com.lobosi.module.social.model;

import java.io.Serializable;
import java.util.Date;

import org.zengsource.portal.module.user.model.UserInfo;

/**
 * 用户建立的好友关系，由2名用户组成。
 * 
 * @author zsn
 * @since 6.0
 */
public class Friendship implements Serializable {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;
	public static final int NEW = 0;
	public static final int ACCEPTED = 1;
	public static final int REJECTED = 2;
	public static final int IGNORED = 3;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private int id;
	private UserInfo partA;
	private UserInfo partB;
	/** 0：A发起；1：B接收，确认为好友关系；2：被B拒绝；3：忽略。 */
	private int status = 0;
	private String messageA;
	private String messageB;
	/** 以电子邮件方式加好友。 */
	private String email;

	private Date createdTime;
	private Date updatedTime;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserInfo getPartA() {
		return partA;
	}

	public void setPartA(UserInfo partA) {
		this.partA = partA;
	}

	public UserInfo getPartB() {
		return partB;
	}

	public void setPartB(UserInfo partB) {
		this.partB = partB;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessageA() {
		return messageA;
	}

	public void setMessageA(String messageA) {
		this.messageA = messageA;
	}

	public String getMessageB() {
		return messageB;
	}

	public void setMessageB(String messageB) {
		this.messageB = messageB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
