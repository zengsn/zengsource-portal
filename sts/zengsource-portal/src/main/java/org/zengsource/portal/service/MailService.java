/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-20
 */
package org.zengsource.portal.service;

import org.zengsource.portal.model.Mail;

/**
 * @author zsn
 * @since 6.0
 */
public interface MailService {
	
	/** 默认发送邮件。*/
	public boolean send(Mail mail);
	
	/**
	 * 发送邮件。
	 * @param mail 邮件内容
	 * @param sync true 为同步发送；false 为异步发送。
	 * @return
	 */
	public boolean send(Mail mail, boolean sync);

}
