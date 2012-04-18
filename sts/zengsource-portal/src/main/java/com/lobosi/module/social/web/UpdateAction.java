/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-11
 */
package com.lobosi.module.social.web;

import java.io.File;
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
import org.zengsource.util.NumberUtil;
import org.zengsource.util.StringUtil;

import com.lobosi.module.social.model.UserUpdate;
import com.lobosi.module.social.service.UserUpdateService;

/**
 * @author zsn
 * @since 6.0
 */
public class UpdateAction extends ThemingAction {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private UserInfoService userInfoService;
	private UserUpdateService userUpdateService;

	private String text;
	private String temp;
	private String filename;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		return super.doService();
	}

	public AbstractView doSave() throws MvcException {
		UserUpdate uu = this.userUpdateService.findById(NumberUtil.str2Int(getId(), 0));
		if (uu == null) {
			uu = new UserUpdate();
		}
		uu.setText(getText());
		UserInfo userInfo = this.userInfoService.getCurrent();
		if (userInfo == null) {
			return new XmlErrorView("text", "用户未登录！");
		}
		uu.setOwner(userInfo);
		if ("true".equals(getTemp())) {
			uu.setStatus(UserUpdate.TEMP);
		} else {
			this.userUpdateService.save(uu);
		}
		Map<String, String> resultMap = new HashMap<String, String>();
		String uploadFolder = this.userUpdateService.prepareUpload(uu.getOwner());
		String picturePath = uploadFolder + "/";
		if (this.getFileItem("picture") != null) { // 第一次上传图片
			// 图片保存为 id.扩展名
			if ("true".equals(getTemp())) {
				picturePath += "temp_" + StringUtil.random(16);
			} else {
				picturePath += uu.getId() + "_" + StringUtil.random(16);
			}
			File pictureFile = this.saveFile("picture", picturePath);
			uu.setPicture(pictureFile.getName()); // 设置图片路径
		} 
		// 处理临时文件
		if (StringUtil.notBlank(getFilename())) {
			String finalName = getFilename().replace("temp_", uu.getId()+"_");
			String finalPath = picturePath + finalName;
			File tempFile = new File(picturePath + getFilename());
			if (tempFile.exists()) {
				tempFile.renameTo(new File(finalPath));
				tempFile.deleteOnExit();
				uu.setPicture(finalName); // 设置图片路径
			} 
		}
		// 返回信息
		if ("true".equals(getTemp())) {
			resultMap.put("ownerId", uu.getOwner().getId() + ""); 
			resultMap.put("picture", uu.getPicture());
		} else {
			this.userUpdateService.save(uu);
			resultMap.put("id", uu.getId() + "");
			resultMap.put("picture", uu.getPicture());
			resultMap.put("owner", uu.getOwner().getNickname());
			resultMap.put("ownerId", uu.getOwner().getId() + "");
			resultMap.put("ownerAvatar", uu.getOwner().getUser().getAvatar() + "");
			resultMap.put("createdTime", uu.getCreatedTime() + "");
			resultMap.put("operation", "done");
		}
		return new JsonResultView(resultMap);
	}

	public AbstractView doList() throws MvcException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response").addAttribute("success", "true");
		int totalCount = this.userUpdateService.searchCount(this.userInfoService.getCurrent());
		if (totalCount <= 0) {
			root.addElement("totalCount").addText("0");
		} else {
			root.addElement("totalCount").addText(totalCount + "");
			List<?> list = this.userUpdateService.search(this.userInfoService.getCurrent(), getStartInt(), getLimitInt());
			for(Object obj : list) {
				UserUpdate uu = (UserUpdate) obj;
				Element ele = root.addElement("Update");
				ele.addElement("id").addText(uu.getId() + "");
				ele.addElement("text").addText(uu.getText() + "");
				ele.addElement("owner").addText(uu.getOwner().getNickname() + "");
				ele.addElement("ownerId").addText(uu.getOwner().getId() + "");
				ele.addElement("ownerAvatar").addText(uu.getOwner().getUser().getAvatar() + "");
				ele.addElement("picture").addText(uu.getPicture() + "");
				ele.addElement("createdTime").addText(uu.getCreatedTime() + "");				
			}
		}
		return new XmlView(doc);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public UserUpdateService getUserUpdateService() {
		return userUpdateService;
	}

	public void setUserUpdateService(UserUpdateService userUpdateService) {
		this.userUpdateService = userUpdateService;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getTemp() {
		return temp;
	}
	
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
