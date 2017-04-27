package wxw.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 利用工具类封装 通用算法
 */
public class MD5Salt {
	private static final String SALT = "今天你吃了么?";

	public static String md5salt(String pwd) {
		return DigestUtils.md5Hex(pwd + SALT);
	}

	public static void main(String[] args) {
		String pwd = "123";
		System.out.println(md5salt(pwd));
	}
}
