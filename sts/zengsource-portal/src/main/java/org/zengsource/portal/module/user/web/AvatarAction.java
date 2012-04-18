/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-8
 */
package org.zengsource.portal.module.user.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.zengsource.mvc.MvcContext;
import org.zengsource.mvc.MvcException;
import org.zengsource.mvc.action.MultipartAction;
import org.zengsource.mvc.view.AbstractView;
import org.zengsource.mvc.view.JsonResultView;
import org.zengsource.portal.module.user.model.UserInfo;
import org.zengsource.portal.module.user.service.UserInfoService;
import org.zengsource.portal.service.UserService;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * @since 6.0
 */
public class AvatarAction extends MultipartAction {

	private static final long serialVersionUID = 1L;

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private UserInfoService userInfoService;
	private UserService userService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	@Override
	protected AbstractView doService() throws MvcException {
		UserInfo userInfo = this.userInfoService.getCurrent();
		int uid = userInfo.getUser().getId();
		String rootPath = MvcContext.getInstance().getRootPath();
		String avatarPath = this.userInfoService.prepareUploadFolder();
		String finalPath = rootPath + avatarPath; 
		logger.info("上传目录：" + finalPath);
		File file = this.saveFile("avatar", finalPath  + "/o_" + uid);
		logger.info("保存头像到：" + file.getAbsolutePath());
		userInfo.getUser().setAvatar(avatarPath + "/" + file.getName());
		this.userService.save(userInfo.getUser());
		
		try { // 创建小、中、大头像
			String extension = StringUtil.substring2(file.getName(), ".").replace(".", "");
			if (StringUtil.isBlank(extension)) {
				extension = "png"; // 默认格式
			}
			BufferedImage image = ImageIO.read(file);
			BufferedImage largeAvatar = Scalr.resize(image, 128, 128); // 大头像
			ImageIO.write(largeAvatar, extension, new File(file.getAbsolutePath().replace("o_", "l_")));
			BufferedImage mediumAvatar = Scalr.resize(image, 48, 48); // 中头像
			ImageIO.write(mediumAvatar, extension, new File(file.getAbsolutePath().replace("o_", "m_")));
			BufferedImage smallAvatar = Scalr.resize(image, 16, 16); // 小头像
			ImageIO.write(smallAvatar, extension, new File(file.getAbsolutePath().replace("o_", "s_")));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return new JsonResultView("file", avatarPath);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
