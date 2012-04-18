/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-10
 */
package com.lobosi.module.task.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zsn
 * @since 6.0
 */
public class TaskNote implements Serializable {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	public static final int VALID = 0;
	public static final int INVALID = -1;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private int id;
	private String text;
	private float progress;
	private int status; // -1 表示无效
	private TaskExecutor executor;
	private TaskExecution execution;
	private String attachment;
	private Date createdTime;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TaskExecutor getExecutor() {
		return executor;
	}

	public void setExecutor(TaskExecutor executor) {
		this.executor = executor;
	}

	public TaskExecution getExecution() {
		return execution;
	}

	public void setExecution(TaskExecution execution) {
		this.execution = execution;
	}
	
	public float getProgress() {
		return progress;
	}
	
	public void setProgress(float progress) {
		this.progress = progress;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
