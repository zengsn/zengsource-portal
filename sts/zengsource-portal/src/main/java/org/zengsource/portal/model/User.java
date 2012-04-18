/**
 * 
 */
package org.zengsource.portal.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.zengsource.util.StringUtil;

/**
 * @author zengsn
 * @since 1.6
 */
public class User {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	/** 新建，未验证邮件地址。 */
	public static final int NEW = 0;
	public static final int OPEN = 1;
	public static final int LOCKED = 2;
	public static final int CLOSED = 3;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private Integer id;
	private String username;
	private String password;
	private String salt;

	private String email;
	private String emailConfirm;
	private String emailShare;
	private int status;

	private String avatar;
	private String nickname;
	private String realname;
	private String realnameShare;
	private String location;
	private String locationShare;
	private String sex;
	private String sexShare;
	private Date birthday;
	private String birthdayShare;
	private String blog;
	private String blogShare;
	private String qq;
	private String qqShare;
	private String weibo;
	private String weiboShare;
	private String msn;
	private String msnShare;
	private String mobile;
	private String mobileShare;
	private String introduction;

	private Set<Role> roleSet;

	private Date createdTime;
	private Date updatedTime;

	// + CSTORS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public User() {
		this.roleSet = new HashSet<Role>();
	}

	// + METHODS ++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public boolean hasRole(String role) {
		if (this.roleSet != null) {
			for (Role roleObj : this.roleSet) {
				if (roleObj.getName().equals(role)) {
					return true;
				}
				while (roleObj.getParent() != null) {
					roleObj = roleObj.getParent();
					if (roleObj.getName().equals(role)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void addRole(Role role) {
		if (this.roleSet == null) {
			this.roleSet = new HashSet<Role>();
		}
		this.roleSet.add(role);
	}
	
	public String getLargeAvatar() {
		if (StringUtil.isBlank(this.getAvatar())) {
			return "/user/resources/images/l_.png";
		} else {
			return this.getAvatar().replace("o_", "l_");
		}
	}
	
	public String getMediumAvatar() {
		if (StringUtil.isBlank(this.getAvatar())) {
			return "/user/resources/images/m_.png";
		} else {
			return this.getAvatar().replace("o_", "m_");
		}
	}
	
	public String getSmallAvatar() {
		if (StringUtil.isBlank(this.getAvatar())) {
			return "/user/resources/images/s_.png";
		} else {
			return this.getAvatar().replace("o_", "s_");
		}
	}

	@Override
	public String toString() {
		return this.username;
	}

	// + G^SETTERS ++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public Integer getId() {
		return id;
	}

	public void setId(Integer uid) {
		this.id = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailConfirm() {
		return emailConfirm;
	}

	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getEmailShare() {
		return emailShare;
	}

	public void setEmailShare(String emailShare) {
		this.emailShare = emailShare;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRealnameShare() {
		return realnameShare;
	}

	public void setRealnameShare(String realnameShare) {
		this.realnameShare = realnameShare;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationShare() {
		return locationShare;
	}

	public void setLocationShare(String locationShare) {
		this.locationShare = locationShare;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSexShare() {
		return sexShare;
	}

	public void setSexShare(String sexShare) {
		this.sexShare = sexShare;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBirthdayShare() {
		return birthdayShare;
	}

	public void setBirthdayShare(String birthdayShare) {
		this.birthdayShare = birthdayShare;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getBlogShare() {
		return blogShare;
	}

	public void setBlogShare(String blogShare) {
		this.blogShare = blogShare;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getQqShare() {
		return qqShare;
	}

	public void setQqShare(String qqShare) {
		this.qqShare = qqShare;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getWeiboShare() {
		return weiboShare;
	}

	public void setWeiboShare(String weiboShare) {
		this.weiboShare = weiboShare;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getMsnShare() {
		return msnShare;
	}

	public void setMsnShare(String msnShare) {
		this.msnShare = msnShare;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobileShare() {
		return mobileShare;
	}

	public void setMobileShare(String mobileShare) {
		this.mobileShare = mobileShare;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
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

}
