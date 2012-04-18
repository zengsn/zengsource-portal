/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-11
 */
package com.lobosi.module.social.model;

import java.io.Serializable;
import java.util.Date;

import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * @since 6.0
 */
public class UserUpdate implements Serializable {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	public static final int DELETED = -1;
	public static final int NORMAL = 0;
	public static final int SECRET = 1;
	public static final int TEMP = 2;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private int id;
	private int status;
	private String text;
	private String picture;
	private UserInfo owner;

	private Date createdTime;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public String getTextWithPicture() {
		return this.text + (StringUtil.notBlank(this.picture) ? " (图)" : "");
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserInfo getOwner() {
		return owner;
	}

	public void setOwner(UserInfo owner) {
		this.owner = owner;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
