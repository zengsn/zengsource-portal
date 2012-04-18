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
public class TaskExecution implements Serializable {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;
	public static final int NEW = 0;
	public static final int ACCEPT = 1;
	public static final int PENDING = 2;
	public static final int AUDIT = 3;
	public static final int DONE = 4;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private int id;
	private Task task;
	private int status;
	private float progress;
	private boolean isMain;
	private TaskExecutor executor;
	private TaskNote lastNote;

	private Date updatedTime;
	private Date createdTime;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public void addProgress(float progress) {
		this.progress += progress;
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

	public float getProgress() {
		return progress;
	}

	public void setProgress(float pregress) {
		this.progress = pregress;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public boolean isMain() {
		return isMain;
	}

	public boolean getIsMain() {
		return isMain();
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}

	public void setIsMain(boolean isMain) {
		setMain(isMain);
	}

	public TaskNote getLastNote() {
		return lastNote;
	}

	public void setLastNote(TaskNote lastUpdate) {
		this.lastNote = lastUpdate;
	}

	public TaskExecutor getExecutor() {
		return executor;
	}

	public void setExecutor(TaskExecutor executor) {
		this.executor = executor;
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
