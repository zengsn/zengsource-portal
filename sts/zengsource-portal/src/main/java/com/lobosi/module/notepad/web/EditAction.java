/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-16
 */
package com.lobosi.module.notepad.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.JsonResultView;
import org.zengsource.mvc.view.XmlErrorView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.portal.module.user.service.UserInfoService;
import org.zengsource.portal.web.ThemingAction;
import org.zengsource.util.DateUtil;
import org.zengsource.util.NumberUtil;

import com.lobosi.module.notepad.model.Note;
import com.lobosi.module.notepad.service.NoteService;

/**
 * @author zsn
 * @since 6.0
 */
public class EditAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private NoteService noteService;
	private UserInfoService userInfoService;

	private String text;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public AbstractView doSave() throws MvcException {
		UserInfo author = this.userInfoService.getCurrent();
		if (author == null) {
			return new XmlErrorView("text", "用户未登录！");
		}
		Note note = this.getNoteService().findById(NumberUtil.str2Int(getId(), 0));
		if (note == null) { // 新建
			note = new Note();
		}
		note.setAuthor(author);
		note.setText(text + "\n" // 
				+ (note.getCreatedTime() == null ? // 
						"-- 保存：" + DateUtil.format(new Date(), DateUtil.FULL_CN2) : //
							"-- 修改：" + DateUtil.format(new Date(), DateUtil.FULL_CN2)));
		note.buildTitle();
		this.noteService.save(note);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("id", note.getId() + "");
		resultMap.put("msg", "日记已保存：" + DateUtil.format(note.getCreatedTime(), "HH:mm:SS"));
		return new JsonResultView(resultMap);
	}

	public AbstractView doRecentList() throws MvcException {
		List<?> list = this.noteService.searchRecent(this.userInfoService.getCurrent(), 10);
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		if (list != null) {
			for (Object obj : list) {
				Note note = (Note) obj;
				Element ele = root.addElement("Note");
				ele.addElement("id").addText(note.getId() + "");
				ele.addElement("title").addText( //
						"[" + DateUtil.format(note.getCreatedTime(), "MM-dd") + "] "
								+ note.getTitle() + "");
			}
		}
		return new XmlView(doc);
	}

	public AbstractView doLoad() throws MvcException {
		UserInfo author = this.userInfoService.getCurrent();
		if (author == null) {
			return new XmlErrorView("text", "用户未登录！");
		}
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		Note note = this.getNoteService().findById(NumberUtil.str2Int(getId(), 0));
		if (note != null && note.getAuthor().getId() == author.getId()) {
			Element ele = root.addElement("Note");
			ele.addElement("id").addText(note.getId() + "");
			ele.addElement("title").addText(note.getTitle() + "");
			ele.addElement("text").addText(note.getText() + "");
			ele.addElement("status").addText(note.getStatus() + "");
			ele.addElement("createdTime").addText(note.getCreatedTime() + "");
			ele.addElement("updatedTime").addText(note.getUpdatedTime() + "");
		}
		return new XmlView(doc);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public NoteService getNoteService() {
		return noteService;
	}

	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
