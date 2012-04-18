/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-20
 */
package org.zengsource.portal.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.zengsource.portal.model.Mail;
import org.zengsource.util.StringUtil;

/**
 * @author zsn
 * @since 6.0
 */
public class MailServiceImpl implements MailService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final String GMAIL_USERNAME = "gmail.username";
	private static final String GMAIL_PASSWORD = "gmail.password";

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private Logger logger = Logger.getLogger(getClass());

	private JavaMailSender mailSender;
	private ConfigService configService;
	private SimpleMailMessage templateMessage;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private void prepareSender() {
		if (this.mailSender != null) {
			JavaMailSenderImpl sender = (JavaMailSenderImpl) this.mailSender;
			if (StringUtil.isBlank(sender.getUsername())) { // 从配置取
				String gmailUsername = this.configService.getString(GMAIL_USERNAME);
				String gmailPassword = this.configService.getString(GMAIL_PASSWORD);
				if (StringUtil.isBlank(gmailUsername) || //
						StringUtil.isBlank(gmailPassword)) {
					throw new RuntimeException("请设置Gmail帐号密码！");
				}
				sender.setUsername(gmailUsername);
				sender.setPassword(gmailPassword);
			}
		}
	}

	public boolean send(final Mail mail) {
		this.prepareSender();
		// 处理邮件
		// 发送
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO,
						new InternetAddress(mail.getToAddress()));
				mimeMessage.setFrom(new InternetAddress(mail.getFromAddress()));
				mimeMessage.setSubject(mail.getSubject());
				// mimeMessage.setText(mail.getBody());
				mimeMessage.setContent(mail.getBody(), "text/html; charset=UTF-8");
			}
		};
		try {
			this.mailSender.send(preparator);
			logger.info("Mail to: " + mail.getToAddress());
			return true;
		} catch (MailException ex) {
			return false;
		}
	}

	public boolean send(final Mail mail, boolean sync) {
		if (sync) {
			return send(mail);
		}
		new Thread(new Runnable() {
			public void run() {
				send(mail);
			}
		}).start();
		return true;
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public SimpleMailMessage getTemplateMessage() {
		return templateMessage;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
