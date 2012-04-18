/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-16
 */
package com.lobosi.module.notepad.model;

import java.io.Serializable;
import java.util.Date;

import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * @since 6.0
 */
public class Note implements Serializable {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;
	
	public static final int NORMAL = 0;
	public static final int HIDDEN = -1;
	public static final int DELETED = -2;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private int id;
	private UserInfo author;
	private int status;
	private String title;
	private String text;
	private Date createdTime;
	private Date updatedTime;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public void buildTitle() {
		if (StringUtil.notBlank(this.text)) {
			int pos = this.text.indexOf('\n');
			if (pos > -1) {
				this.title = this.text.substring(0, pos);
			} else {
				int len = this.text.length();
				this.title = this.text.substring(0, len > 128 ? 128 : len);
			}
		}
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserInfo getAuthor() {
		return author;
	}

	public void setAuthor(UserInfo author) {
		this.author = author;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
