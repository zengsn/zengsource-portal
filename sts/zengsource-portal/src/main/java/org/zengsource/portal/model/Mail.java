/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-20
 */
package org.zengsource.portal.model;

import java.io.Serializable;

/**
 * @author zsn
 * @since 6.0
 */
public class Mail implements Serializable{

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private String body;
	private String subject;
	private String toAddress;
	private String fromAddress;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getToAddress() {
		return toAddress;
	}
	
	public void setToAddress(String address) {
		this.toAddress = address;
	}
	
	public String getFromAddress() {
		return fromAddress;
	}
	
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
