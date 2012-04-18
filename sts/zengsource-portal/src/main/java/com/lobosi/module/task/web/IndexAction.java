/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-9
 */
package com.lobosi.module.task.web;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.web.ThemingAction;

import com.lobosi.module.task.model.TaskExecution;
import com.lobosi.module.task.model.TaskExecutor;
import com.lobosi.module.task.service.TaskExecutionService;
import com.lobosi.module.task.service.TaskExecutorService;
import com.lobosi.module.task.service.TaskNoteService;
import com.lobosi.module.task.service.TaskService;

/**
 * @author zsn
 * @since 6.0
 */
public class IndexAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private TaskService taskService;
	private TaskNoteService taskNoteService;
	private TaskExecutorService taskExecutorService;
	private TaskExecutionService taskExecutionService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	/** 查询当前用户执行的任务。*/
	public AbstractView doList() throws MvcException {
		TaskExecutor executor = this.taskExecutorService.getCurrent();
		int totalCount = this.taskExecutionService.searchCount(executor, getQ());
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		if (totalCount == 0) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(totalCount + "");
			List<?> results = this.taskExecutionService.search(executor, getQ(), getStartInt(), getLimitInt());
			for (Object obj : results) {
				TaskExecution execution = (TaskExecution) obj;
				Element ele = root.addElement("Execution");
				ele.addElement("id").addText(execution.getId() + "");
				ele.addElement("name").addText(execution.getTask().getName() + "");
				ele.addElement("introduction").addText(execution.getTask().getIntroduction() + "");
				ele.addElement("tags").addText(execution.getTask().getTags() + "");
				ele.addElement("requestor").addText(execution.getTask().getRequestor().getNickname());
				ele.addElement("executors").addText(execution.getTask().getExecutorSet() + "");
				ele.addElement("status").addText(execution.getStatus() + "");
				ele.addElement("feature").addText(execution.getTask().getFeature() + "");
				ele.addElement("startTime").addText(execution.getTask().getStartTime() + "");
				ele.addElement("endTime").addText(execution.getTask().getEndTime() + "");
				ele.addElement("progress").addText(execution.getProgress() + "");
				ele.addElement("lastUpdate").addText(execution.getLastNote()+ "");
				ele.addElement("createdTime").addText(execution.getCreatedTime() + "");
			}
		}
		return new XmlView(doc);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	public TaskNoteService getTaskNoteService() {
		return taskNoteService;
	}
	
	public void setTaskNoteService(TaskNoteService taskNoteService) {
		this.taskNoteService = taskNoteService;
	}
	
	public TaskExecutorService getTaskExecutorService() {
		return taskExecutorService;
	}
	
	public void setTaskExecutorService(TaskExecutorService taskExecutorService) {
		this.taskExecutorService = taskExecutorService;
	}
	
	public TaskExecutionService getTaskExecutionService() {
		return taskExecutionService;
	}
	
	public void setTaskExecutionService(TaskExecutionService taskExecutionService) {
		this.taskExecutionService = taskExecutionService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
