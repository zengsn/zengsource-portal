/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-11
 */
package com.lobosi.module.task.web;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.HtmlView;
import org.zengsource.mvc.view.JsonResultView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.model.Config;
import org.zengsource.portal.service.ConfigService;
import org.zengsource.portal.web.ThemingAction;
import org.zengsource.util.NumberUtil;
import org.zengsource.util.StringUtil;

import com.lobosi.module.task.model.TaskExecution;
import com.lobosi.module.task.model.TaskNote;
import com.lobosi.module.task.service.TaskExecutionService;
import com.lobosi.module.task.service.TaskNoteService;
import com.lobosi.module.task.service.TaskService;

/**
 * @author zsn
 * @since 6.0
 */
public class ExecuteAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private TaskService taskService;
	private TaskNoteService taskNoteService;
	private TaskExecutionService taskExecutionService;
	private ConfigService configService;

	private String execution;
	private String text;
	private String progress;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		return super.doService();
	}

	public AbstractView doList() throws MvcException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response");
		TaskExecution execution = this.taskExecutionService.findById( //
				NumberUtil.str2Int(getId(), 0));
		if (execution == null) {
			root.addAttribute("success", "false");
		} else {
			int totalCount = this.taskNoteService.searchCount(execution, getQ());
			if (totalCount == 0) {
				root.addElement("totalCount").addText("0");
			} else {
				root.addElement("totalCount").addText(totalCount + "");
				List<?> notes = this.taskNoteService.search(execution, getQ(), getStartInt(),
						getLimitInt());
				if (notes != null) {
					for (Object obj : notes) {
						TaskNote note = (TaskNote) obj;
						Element ele = root.addElement("Note");
						ele.addElement("id").addText(note.getId() + "");
						ele.addElement("text").addText(note.getText() + "");
						ele.addElement("progress").addText(note.getProgress() + "");
						if (note.getAttachment() != null) {
							ele.addElement("attachment").addText( //
									this.taskService.prepareUploadFolder( //
											note.getExecution().getTask()) //
											+ "/" + note.getAttachment() + "");
						}
						ele.addElement("createdTime").addText(note.getCreatedTime() + "");
					}
				}
			}
		}
		return new XmlView(doc);
	}

	/** 查询执行进程信息。 */
	public AbstractView doExecution() throws MvcException {
		TaskExecution execution = this.taskExecutionService.findById( //
				NumberUtil.str2Int(getId(), 0));
		if (execution == null) {
			return new HtmlView("{response:''}");
		}
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("id:" + execution.getId() + ",");
		json.append("requestor:'" + execution.getTask().getRequestor().getNickname() + "',");
		json.append("task:'" + execution.getTask().getName() + "',");
		json.append("isMain:" + execution.isMain() + ",");
		// json.append("taskProgress:'" + execution.getTask().getProgress() *
		// 100 + "%',");
		json.append("progress:'" + execution.getProgress() * 100 + "%'");
		json.append("}");
		return new HtmlView(json.toString());
	}

	/** 增加进度信息信息。 */
	public AbstractView doAdd() throws MvcException {
		TaskExecution execution = this.taskExecutionService.findById( //
				NumberUtil.str2Int(getExecution(), 0));
		if (execution == null) {
			return new XmlErrorView("text", "当前进程不存在！");
		}
		TaskNote note = new TaskNote();
		note.setExecution(execution);
		if (StringUtil.isBlank(getText())) {
			return new XmlErrorView("text", "请填写进度说明！");
		}
		note.setText(getText());
		note.setExecutor(execution.getExecutor());
		note.setProgress(NumberUtil.string2Float(getProgress(), 0.0f) / 100);
		this.taskNoteService.save(note);
		// 更新进度
		execution.setProgress(note.getProgress());
		execution.setLastNote(note);
		this.taskExecutionService.save(execution);
		// 保存附件
		if (this.getFileItem("attachment") != null) {
			double maxSize = 5 * 1024 * 1024;
			Config config = this.configService.getByKey("upload.limit");
			if (config != null) {
				maxSize = NumberUtil.str2Dou(config.getValue(), maxSize);
			}
			if (this.getFileItem("attachment").getSize() > maxSize) {
				return new XmlErrorView("attachment", "文件大小最大为：" + maxSize / 1024 / 1024 + " MB！");
			}
			String folder = this.taskService.prepareUploadFolder(execution.getTask());
			String attachmentPath = "note-" + note.getId() + "-"
					+ this.getFileItem("attachment").getName();
			this.saveFile("attachment", folder + "/" + attachmentPath);
			logger.info("保存进度附件：" + folder + "/" + attachmentPath);
			note.setAttachment(attachmentPath);
			this.taskNoteService.save(note);
		}
		return new JsonResultView("msg", "进度已成功保存！");
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	public TaskNoteService getTaskNoteService() {
		return taskNoteService;
	}

	public void setTaskNoteService(TaskNoteService taskNoteService) {
		this.taskNoteService = taskNoteService;
	}

	public TaskExecutionService getTaskExecutionService() {
		return taskExecutionService;
	}

	public void setTaskExecutionService(TaskExecutionService taskExecutionService) {
		this.taskExecutionService = taskExecutionService;
	}

	public String getExecution() {
		return execution;
	}

	public void setExecution(String execution) {
		this.execution = execution;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
