/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-11
 */
package com.lobosi.module.task.web;

import java.io.File;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.JsonResultView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.portal.web.ThemingAction;
import org.zengsource.util.DateUtil;
import org.zengsource.util.NumberUtil;
import org.zengsource.util.StringUtil;

import com.lobosi.module.task.model.Task;
import com.lobosi.module.task.model.TaskExecutor;
import com.lobosi.module.task.service.TaskExecutorService;
import com.lobosi.module.task.service.TaskService;

/**
 * @author zsn
 * @since 6.0
 */
public class CreateAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private TaskService taskService;
	private TaskExecutorService taskExecutorService;

	private String name;
	private String introduction;
	private String tags;
	private String feature;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String repondors = "";
	private String type; // send or draft

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		return super.doService();
	}

	/** 创建任务。 */
	public AbstractView doSave() throws MvcException {
		Task task = new Task();
		if (StringUtil.isBlank(getName())) {
			return new XmlErrorView("name", "名称不能为空！");
		}
		task.setName(getName());
		task.setName(getName());
		task.setIntroduction(getIntroduction());
		task.setTags(getTags());
		//task.setFeature("file");
		task.setStatus(0); //
		String timeFormat = "yyyy-MM-dd HH:mm:ss";
		task.setStartTime(DateUtil.parse(getStartDate() + " " + getStartTime(), timeFormat));
		task.setEndTime(DateUtil.parse(getEndDate() + " " + getEndTime(), timeFormat));
		if ("send".equals(getType())) { // 状态
			task.setStatus(Task.START);
		}
		// 保存执行人
		if (StringUtil.notBlank(getRepondors())) {
			String[] arr = getRepondors().split(",");
			for (String uid : arr) {
				TaskExecutor taskExecutor = this.taskExecutorService.findById( //
						NumberUtil.str2Int(uid, 0));
				if (taskExecutor != null) {
					task.addTaskExecutor(taskExecutor);
				}
			}
		}
		// 保存到数据库
		this.taskService.save(task); 
		// 保存文件设置
		String uploadDir = this.taskService.prepareUploadFolder(task);
		if (this.getFileItem("attachment") != null) {
			File templateFile = this.saveFile("attachment", uploadDir + "/attachment-"
					+ this.getFileItem("attachment").getName());
			logger.info("保存任务附件：" + templateFile.getAbsolutePath());
			task.setAttachment("attachment-" + this.getFileItem("attachment").getName());
			this.taskService.save(task);
		}
		return new JsonResultView("msg", "任务创建成功！");
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public TaskExecutorService getTaskExecutorService() {
		return taskExecutorService;
	}

	public void setTaskExecutorService(TaskExecutorService taskExecutorService) {
		this.taskExecutorService = taskExecutorService;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRepondors() {
		return repondors;
	}

	public void setRepondors(String repondors) {
		this.repondors = repondors;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
