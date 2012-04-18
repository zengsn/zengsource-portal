/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-16
 */
package com.lobosi.module.notepad.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.XmlView;
import org.zengsource.portal.module.user.service.UserInfoService;
import org.zengsource.portal.web.ThemingAction;
import org.zengsource.util.DateUtil;
import org.zengsource.util.StringUtil;

import com.lobosi.module.notepad.model.Note;
import com.lobosi.module.notepad.service.NoteService;

/**
 * @author zsn
 * @since 6.0
 */
public class ViewAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private NoteService noteService;
	private UserInfoService userInfoService;

	private String date;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	/** 查看每月的记事。 */
	public AbstractView doMonth() throws MvcException {
		Date date = new Date();
		if (StringUtil.notBlank(getDate()) && getDate().length() == 19) {
			date = DateUtil.parse(getDate().substring(0, 10), "yyyy-MM-dd");
		}
		logger.info("月份显示的第一天：" + DateUtil.format(date, "yyyy-MM-dd"));
		date = calcFirstDate(date); // 计算第一天
		logger.info("月份显示的第一天：" + DateUtil.format(date, "yyyy-MM-dd"));
		Date lastDate = DateUtil.add(date, 41);
		logger.info("月份显示的最后一天：" + DateUtil.format(lastDate, "yyyy-MM-dd"));
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		List<?> list = this.noteService.search(this.userInfoService.getCurrent(), date, lastDate);
		String[] days = { "sun", "mon", "tue", "wed", "thr", "fri", "sat" };
		if (list != null) { // TODO 算法优化
			root.addElement("totalCount").addText("6");
			for (int i = 0; i < 6; i++) { // 6 行
				Element ele = root.addElement("Note");
				ele.addElement("id").addText(i + 1 + "");
				for (int j = 0; j < 7; j++) {
					Element dayEle = ele.addElement(days[j]);
					StringBuilder sb = new StringBuilder();
					sb.append(DateUtil.format(date, "d"));
					Object[] arr = list.toArray();
					List<String> notesInDate = new ArrayList<String>();
					for (int k = 0; k < arr.length; k++) {
						Note note = (Note) arr[k];
						if (DateUtil.isSameDate(note.getCreatedTime(), date)) {
							// sb.append("*");
							notesInDate.add( //
									"{id:" + note.getId() + ",title:'" + note.getTitle() + "'}");
							//list.remove(k);
						}
					}
					if (notesInDate.size()>0) {
						sb.append(notesInDate.toString());
					}
					dayEle.addText(sb.toString());
					date = DateUtil.add(date, 1); // 下一天
				}
			}
		}
		return new XmlView(doc);
	}

	private Date calcFirstDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.setTimeInMillis(calendar.getTimeInMillis() - (dayOfWeek - 1) * 24 * 60 * 60
				* 1000);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
