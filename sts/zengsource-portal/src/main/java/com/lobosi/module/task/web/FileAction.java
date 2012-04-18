/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.web;

import java.io.File;

import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipartAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.JsonResultView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.util.DateUtil;
import org.zengsource.util.NumberUtil;
import org.zengsource.util.StringUtil;

import com.lobosi.module.task.model.FileTask;
import com.lobosi.module.task.model.Task;
import com.lobosi.module.task.model.TaskExecutor;
import com.lobosi.module.task.service.FileTaskService;
import com.lobosi.module.task.service.TaskExecutorService;

/**
 * @author zsn
 * @since 6.0
 */
public class FileAction extends MultipartAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private FileTaskService fileTaskService;
	private TaskExecutorService taskExecutorService;

	private String name;
	private String introduction;
	private String tags;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String nameRegex;
	private String allowFormat;
	private String maxFileSize;
	private String repondors = "";
	private String type; // send or draft

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		FileTask fileTask = new FileTask(new Task());
		if (StringUtil.isBlank(getName())) {
			return new XmlErrorView("name", "名称不能为空！");
		}
		fileTask.getTask().setName(getName());
		fileTask.getTask().setIntroduction(getIntroduction());
		fileTask.getTask().setTags(getTags());
		fileTask.getTask().setFeature("file");
		fileTask.getTask().setStatus(0); //
		fileTask.getTask().setStartTime( //
				DateUtil.parse(getStartDate() + " " + getStartTime(), "yyyy-MM-dd HH:mm:ss"));
		fileTask.getTask().setEndTime( // 
				DateUtil.parse(getEndDate() + " " + getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		if ("send".equals(getType())) { // 状态
			fileTask.getTask().setStatus(Task.START);
		}
		// 保存执行人
		if (StringUtil.notBlank(getRepondors())) {
			String[] arr = getRepondors().split(",");
			for(String uid : arr) {
				TaskExecutor taskMan = this.taskExecutorService.findById(NumberUtil.str2Int(uid, 0));
				if (taskMan != null) {
					fileTask.getTask().addTaskExecutor(taskMan);
				}
			}
		}
		// 保存文件设置 
		fileTask.setNameRegex(getNameRegex());
		fileTask.setAllowFormat(getAllowFormat());
		fileTask.setMaxFileSize( //
				NumberUtil.str2Dou(StringUtil.substring(getMaxFileSize(), " MB"), 0.0) * 1024 * 1024);
		this.fileTaskService.save(fileTask);
		String uploadDir = this.fileTaskService.prepareUploadFolder(fileTask);
		if (this.getFileItem("template") != null) {
			File templateFile = this.saveFile("template",
					uploadDir + "/template-" + this.getFileItem("template").getName());
			logger.info("保存模板文件：" + templateFile.getAbsolutePath());
			fileTask.setTemplate("template-" + this.getFileItem("template").getName());
			this.fileTaskService.save(fileTask);
		}
		return new JsonResultView("msg", "文件任务创建成功！");
	}

	public void setRepondors(String repondors) {
		this.repondors += repondors + ",";
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public FileTaskService getFileTaskService() {
		return fileTaskService;
	}

	public void setFileTaskService(FileTaskService fileTaskService) {
		this.fileTaskService = fileTaskService;
	}
	
	public TaskExecutorService getTaskExecutorService() {
		return taskExecutorService;
	}
	
	public void setTaskExecutorService(TaskExecutorService taskManService) {
		this.taskExecutorService = taskManService;
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

	public void setTags(String tages) {
		this.tags = tages;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
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

	public String getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public String getRepondors() {
		return repondors;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
