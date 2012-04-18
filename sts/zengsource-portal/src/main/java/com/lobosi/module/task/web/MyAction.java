/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-10
 */
package com.lobosi.module.task.web;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.portal.module.user.service.UserInfoService;
import org.zengsource.portal.web.ThemingAction;

import com.lobosi.module.task.model.Task;
import com.lobosi.module.task.service.TaskService;

/**
 * @author zsn
 * @since 6.0
 */
public class MyAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private TaskService taskService;
	private UserInfoService userInfoService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public AbstractView doList() throws MvcException {
		UserInfo current = this.userInfoService.getCurrent();
		int totalCount = this.taskService.searchCount(current, getQ());
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		if (totalCount == 0) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(totalCount + "");
			List<?> results = this.taskService.search(current, getQ(), getStartInt(), getLimitInt());
			for (Object obj : results) {
				Task task = (Task) obj;
				Element ele = root.addElement("Task");
				ele.addElement("id").addText(task.getId() + "");
				ele.addElement("name").addText(task.getName() + "");
				ele.addElement("introduction").addText(task.getIntroduction() + "");
				ele.addElement("tags").addText(task.getTags() + "");
				ele.addElement("requestor").addText(task.getRequestor().getNickname());
				ele.addElement("repondors").addText(task.getExecutorSet() + "");
				ele.addElement("status").addText(task.getStatus() + "");
				ele.addElement("feature").addText(task.getFeature() + "");
				ele.addElement("startTime").addText(task.getStartTime() + "");
				ele.addElement("endTime").addText(task.getEndTime() + "");
				ele.addElement("progress").addText(task.getProgress() + "");
				ele.addElement("createdTime").addText(task.getCreatedTime() + "");
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
	
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}
	
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
