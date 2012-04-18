/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zsn
 * @since 6.0
 */
public class FileTask implements Serializable {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private int id;
	private Task task;
	private String nameRegex;
	private String allowFormat;
	private double maxFileSize;
	private String template;

	private Date createdTime;
	private Date updatedTime;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public FileTask() {
	}

	public FileTask(Task task) {
		this();
		this.task = task;
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getNameRegex() {
		return nameRegex;
	}

	public void setNameRegex(String nameRegex) {
		this.nameRegex = nameRegex;
	}

	public String getAllowFormat() {
		return allowFormat;
	}

	public void setAllowFormat(String allowFormat) {
		this.allowFormat = allowFormat;
	}

	public double getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(double maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
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
