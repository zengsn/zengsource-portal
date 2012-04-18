/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.zengsource.portal.module.user.model.UserInfo;

/**
 * @author zsn
 * @since 6.0
 */
public class TaskExecutor implements Serializable {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private int id;
	private String avatar;
	private String username;
	private String nickname;
	private UserInfo user;
	private Set<Task> taskSet;

	private Date updatedTime;
	private Date createdTime;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public TaskExecutor() {
	}

	public TaskExecutor(UserInfo userInfo) {
		this.user = userInfo;
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Set<Task> getTaskSet() {
		return taskSet;
	}

	public void setTaskSet(Set<Task> taskSet) {
		this.taskSet = taskSet;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
