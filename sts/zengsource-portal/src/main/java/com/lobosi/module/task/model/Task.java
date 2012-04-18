/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.zengsource.portal.module.user.model.UserInfo;

/**
 * @author zsn
 * @since 6.0
 */
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int DRAFT = 0;
	public static final int START = 1;

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private int id;
	private String name;
	private String tags;
	private int status;
	private String feature;
	private float progress;
	private Date startTime;
	private Date endTime;
	private UserInfo requestor;
	private String introduction;
	private String attachment;
	private Set<TaskExecutor> executorSet;
	private Date updatedTime;
	private Date createdTime;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public void addTaskExecutor(TaskExecutor taskExecutor) {
		if (this.executorSet == null) {
			this.executorSet = new HashSet<TaskExecutor>();
		}
		this.executorSet.add(taskExecutor);
	}

	/** 创建主执行过程：未指定执行者。*/
	public TaskExecution createMainExecution() {
		TaskExecution tEn = new TaskExecution();
		tEn.setMain(true);
		tEn.setTask(this);
		tEn.setStatus(this.status);
		//tEn.setExecutor(); // 未指定
		return tEn;
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}
	
	public String getAttachment() {
		return attachment;
	}
	
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public UserInfo getRequestor() {
		return requestor;
	}

	public void setRequestor(UserInfo requestor) {
		this.requestor = requestor;
	}

	public Set<TaskExecutor> getExecutorSet() {
		return executorSet;
	}

	public void setExecutorSet(Set<TaskExecutor> reponderSet) {
		this.executorSet = reponderSet;
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
