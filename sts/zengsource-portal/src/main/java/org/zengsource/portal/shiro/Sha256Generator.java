/**
 * 
 */
package org.zengsource.portal.shiro;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.SimpleByteSource;

/**
 * 生成测量密码密文。
 * 
 * @author zengsn
 * @since 1.6
 */
public class Sha256Generator {

	public static void main(String[] args) {
		// Sha256Hash sha256Hash = new Sha256Hash("secret");
		// Sha256Hash sha256Hash = new Sha256Hash("guest");
		//Sha256Hash sha256Hash = new Sha256Hash("123456");
		String username = "admin";
		Sha256Hash sha256Hash = new Sha256Hash("123456", (new SimpleByteSource(
				"random_salt_value_" + username)).getBytes());
		String result = sha256Hash.toHex();
		System.out.println(username + " simple salted hash: " + result);
		System.out.print(sha256Hash.toHex());
	}

}
